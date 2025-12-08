<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Embedded Terminal

<primary-label ref="2025.3"/>

<link-summary>Describes In-IDE terminal API that allows interacting with the shell.</link-summary>

IntelliJ Platform provides several terminal implementations:

* Classic Terminal — the initial implementation that was the default option for a long time.
* [Reworked Terminal](https://blog.jetbrains.com/platform/2025/07/the-reworked-terminal-becomes-the-default-in-2025-2/) — the default option since the 2025.2 release.
* [Experimental Terminal](https://blog.jetbrains.com/idea/2024/02/the-new-terminal-beta-is-now-in-jetbrains-ides/) — the deprecated experimental implementation.

All three implementations expose different APIs, and using _Reworked Terminal API_ (described on this page) is recommended.
Starting from this point, the _Terminal_ term will always refer to the _Reworked Terminal_.

> The Terminal API is available since the 2025.3.
> It is under development and in the experimental status.
> Some aspects of it may change in future releases.
>
{style="warning" title="Experimental"}

## Adding Dependency to the Terminal Plugin

The Terminal API is provided by the _Terminal_ plugin bundled into the IntelliJ Platform-based IDEs.
To use the API, [add the dependency](plugin_dependencies.md) on the Terminal plugin (ID: `org.jetbrains.plugins.terminal`).

## Getting a Terminal Instance

The Terminal instance is represented by [`TerminalView`](%gh-ic%/plugins/terminal/frontend/src/com/intellij/terminal/frontend/view/TerminalView.kt).
Currently, the only place where the Reworked Terminal is available is the <control>Terminal</control> tool window
whose tabs are managed by the [`TerminalToolWindowTabsManager`](%gh-ic%/plugins/terminal/frontend/src/com/intellij/terminal/frontend/toolwindow/TerminalToolWindowTabsManager.kt).

* Use `TerminalToolWindowTabsManager.getTabs()` to access already opened terminal tabs.
* Use `TerminalToolWindowTabsManager.createTabBuilder()` to create a new terminal tab.
* Use `TerminalView.DATA_KEY` to get the `TerminalView` from [action's](action_system.md#action-implementation) [`DataContext`](%gh-ic%/platform/core-ui/src/openapi/actionSystem/DataContext.java).

## Accessing the Terminal Output

The terminal has two output buffers:
- regular — used for executing commands and displaying their output
- alternative — usually used by "fullscreen" terminal applications like vim, nano, mc, and similar

Both buffers are represented by [`TerminalOutputModel`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/view/TerminalOutputModel.kt),
stored in [`TerminalOutputModelsSet`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/view/TerminalOutputModelsSet.kt),
and can be accessed via `TerminalView.outputModels`.

`TerminalOutputModel` is a read-only view of the terminal screen and the output history.
It can be thought of as a string that contains the currently displayed text and some previous history
that is removed ("trimmed") from time to time to avoid consuming too much memory.

Because of trimming, this model uses absolute offsets to navigate in it.
They are represented by [`TerminalOffset`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/view/TerminalOutputModel.kt) and [`TerminalLineIndex`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/view/TerminalOutputModel.kt).
The currently available "window" has the length `TerminalOutputModel.textLength`
and is located between `TerminalOutputModel.startOffset` and `TerminalOutputModel.endOffset`.

## Sending Input to the Shell

Text can be sent to the shell using `TerminalView.sendText()`.
It will asynchronously send the text to the input stream of the process as is.

Some additional options are provided via [`TerminalSendTextBuilder`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/view/TerminalSendTextBuilder.kt) created with `TerminalView.createSendTextBuilder()`:

* `shouldExecute()` - inserts the line wrap after the provided text to execute the command.
  Prefer using this option rather than adding a line wrap manually to the text.
* `useBracketedPasteMode()` - wraps the provided text into a bracketed paste mode escape sequences (if it is supported by the shell).
  It makes the shell treat the text like it was pasted from the clipboard.
  And also ensure that the text won't be interpreted as a shell key binding.

## Adding Actions to the Terminal

To provide some custom handling for a shortcut in the terminal,
[`AnAction`](action_system.md#action-implementation) should be implemented.
But key events handling in the terminal are different compared to other places of the IDE,
so just registering an action in the [<path>plugin.xml</path>](plugin_configuration_file.md) won't be enough.

Terminal has an option [*Override IDE shortcuts*](https://www.jetbrains.com/help/idea/settings-tools-terminal.html#application-settings) that limits the list of actions that can be executed
by shortcuts in the terminal to avoid conflicts of the IDE actions with the shell key bindings.
For example, it allows handling the <shortcut>Ctrl+R</shortcut> shortcut by the shell (and invoke search in commands history) instead of starting a [Run Configuration](run_configurations.md) in the IDE.

To make an action available by shortcut in the terminal,
its ID should be provided to the terminal by implementing
[`TerminalAllowedActionsProvider`](%gh-ic%/plugins/terminal/frontend/src/com/intellij/terminal/frontend/view/TerminalAllowedActionsProvider.kt)
and registering it in <include from="snippets.topic" element-id="ep"><var name="ep" value="org.jetbrains.plugins.terminal.allowedActionsProvider"/></include>.

Consider the following example.

<path>MyTerminalAction.kt</path>

```kotlin
class MyTerminalAction : DumbAwareAction() {
  override fun actionPerformed(e: AnActionEvent) {
    val terminal = e.getData(TerminalView.DATA_KEY) ?: return
    // perform the action in the terminal
  }
}

class MyTerminalAllowedActionsProvider : TerminalAllowedActionsProvider {
  override fun getActionIds(): List<String> {
    return listOf("MyPlugin.MyTerminalAction")
  }
}
```

<path>plugin.xml</path>

```xml
<actions>
  <action id="MyPlugin.MyTerminalAction" class="com.example.MyTerminalAction">
    <keyboard-shortcut first-keystroke="shift ENTER" keymap="$default"/>
  </action>
</actions>

<extensions defaultExtensionNs="org.jetbrains.plugins.terminal">
  <allowedActionsProvider
      implementation="com.example.MyTerminalAllowedActionsProvider"/>
</extensions>
```

## Shell Integration

When the shell process is started, the Terminal plugin injects shell scripts into its startup
to get information about the environment and subscribe to events.
For example, this allows tracking the positions of the prompt, command, and command output in the shell output.

All APIs that rely on the shell integration are available in [`TerminalShellIntegration`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/view/shellIntegration/TerminalShellIntegration.kt).
It can be accessed via `TerminalView.shellIntegrationDeferred`.
It is not available until the shell process is started and the shell integration is initialized.
To wait for the shell integration initialization, use `shellIntegrationDeferred.await()`.

> The shell integration is currently available only for *Bash*, *Zsh* and *PowerShell*.
> `TerminalView.shellIntegrationDeferred.await()` call may never succeed for other shells.
> And it is not guaranteed that it will succeed even in supported shells because it highly depends on the user's shell configuration.
> Also, it depends on the state of the [*Shell Integration*](https://www.jetbrains.com/help/idea/settings-tools-terminal.html#application-settings) option in the Terminal settings.
>
{style="warning"}

### Exploring Terminal Output Structure

Information about previously executed commands and the current one is stored in [`TerminalBlocksModel`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/view/shellIntegration/TerminalBlocksModel.kt).
This model is built of _Terminal Blocks_.

Terminal block represents a range of text in the **regular** `TerminalOutputModel`
and some additional information about the content and meaning of this text.
Currently, there is a single type of the block: [`TerminalCommandBlock`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/view/shellIntegration/TerminalBlocks.kt).

`TerminalCommandBlock` represents the range of the shell output that can contain prompt, command and the command output.
Also, it provides additional metadata about the command, such as working directory, executed command, and exit code.
Note that not every command block is an executed terminal command.
Generally, it is just a range of text between the start of the shell prompt until the next prompt.

### Listening for Command Execution

To get notified when a command is executed in the shell, add a listener using `TerminalShellIntegration.addCommandExecutionListener()`.
Note that this listener is called only if the shell interprets the typed text as a valid command.
For example, it may not be called if the typed command text was blank and the user pressed <shortcut>Enter</shortcut>.

To know the current state of the shell, for example, whether a command is executing or not, use `TerminalShellIntegration.outputStatus`.
Also, it allows listening for changes of the shell output state.

<include from="snippets.topic" element-id="missingContent"/>
