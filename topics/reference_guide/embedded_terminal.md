<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Embedded Terminal

<primary-label ref="2025.3"/>

<link-summary>Describes In-IDE terminal API that allows interacting with the shell.</link-summary>

IntelliJ Platform provides several terminal implementations:

* Classic Terminal — the initial implementation that was the default option for a long time.
* [Reworked Terminal](https://blog.jetbrains.com/platform/2025/07/the-reworked-terminal-becomes-the-default-in-2025-2/) — the default option since the 2025.2 release.
* [Experimental Terminal](https://blog.jetbrains.com/idea/2024/02/the-new-terminal-beta-is-now-in-jetbrains-ides/) — the deprecated experimental implementation.

All three implementations have different APIs, and the preferred one is the Reworked Terminal API.
This page describes only the Reworked Terminal API.
Starting from this point, the *Terminal* term will always refer to the Reworked Terminal implementation.

> The Reworked Terminal API is available since the 2025.3 release in the experimental status and is under development.
> Some aspects of it may change in future releases.
> {style="warning" title="Experimental"}

## Adding dependency to the Terminal plugin

The Terminal API is provided by the Terminal plugin bundled into the IntelliJ Platform-based IDEs.
To use it, the dependency to the Terminal plugin should be added.
Terminal plugin ID is `org.jetbrains.plugins.terminal`.
See [](plugin_dependencies.md)for details.

## Getting an instance of the Terminal

The Terminal instance is represented by `com.intellij.terminal.frontend.view.TerminalView`.
Currently, the only place where the Reworked Terminal is available is the Terminal tool window
whose tabs are managed by the `com.intellij.terminal.frontend.toolwindow.TerminalToolWindowTabsManager`.

* Use `TerminalToolWindowTabsManager.getTabs()` to access already opened terminal tabs.
* Use `TerminalToolWindowTabsManager.createTabBuilder()` to create a new terminal tab.
* Use `TerminalView.DATA_KEY` to get the `TerminalView` from `com.intellij.openapi.actionSystem.DataContext`.
  For example, when implementing [`AnAction`](action_system.md#action-implementation).

## Accessing the Terminal output

The terminal has two output buffers: regular and the alternative one.
The regular buffer is used for executing commands and displaying their output.
While the alternative one is usually used by "fullscreen" terminal applications like vim, nano, mc and so on.

Both buffers are represented by `org.jetbrains.plugins.terminal.view.TerminalOutputModel`,
stored in `org.jetbrains.plugins.terminal.view.TerminalOutputModelsSet`,
and can be accessed via `TerminalView.outputModels`.

`TerminalOutputModel` is a read-only view of the terminal screen and the output history.
It can be thought of as a string that contains the currently displayed text and some previous history
that is removed ("trimmed") from time to time to avoid consuming too much memory.
Because of trimming, this model is using absolute offsets to navigate in it.
They are represented by `TerminalOffset` and `TerminalLineIndex`.
The currently available "window" has the length `TerminalOutputModel.textLength`
and is located between `TerminalOutputModel.startOffset` and `TerminalOutputModel.endOffset`.

## Sending input to the shell

Text can be sent to the shell using `TerminalView.sendText()`.
It will asynchronously send the text to the input stream of the process as is.

Some additional options are provided through `TerminalView.createSendTextBuilder()`:

* `TerminalSendTextBuilder.shouldExecute()` - inserts the line wrap after the provided text to execute the command.
  Prefer using this option rather than adding a line wrap manually to the text.
* `TerminalSendTextBuilder.useBracketedPasteMode()` - wraps the provided text into a bracketed paste mode escape sequences (if it is supported by the shell).
  It makes the shell treat the text like it was pasted from the clipboard.
  And also ensure that the text won't be interpreted as a shell key binding.

## Adding actions to the Terminal

To provide some custom handling for a shortcut in the terminal,
[`AnAction`](action_system.md#action-implementation) should be implemented.
But key events handling in the terminal are different compared to other places of the IDE,
so just registering an action in the <path>plugin.xml</path> won't be enough.

Terminal has an option [*Override IDE shortcuts*](https://www.jetbrains.com/help/idea/settings-tools-terminal.html#application-settings) that limits the list of actions that can be executed
by shortcuts in the terminal to avoid conflicts of the IDE actions with the shell key bindings.
For example, to make Ctrl+R shortcut to be handled by the shell (and invoke search in commands history)
instead of starting a Run Configuration in the IDE.

To make an action allowed to be called by shortcut in the terminal,
its ID should be provided to the terminal by overriding `com.intellij.terminal.frontend.view.TerminalAllowedActionsProvider` extension point
and defining it in the plugin.xml.

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
  <action id="MyPlugin.MyTerminalAction" class="my.plugin.MyTerminalAction">
    <keyboard-shortcut first-keystroke="shift ENTER" keymap="$default"/>
  </action>
</actions>

<extensions defaultExtensionNs="org.jetbrains.plugins.terminal">
<allowedActionsProvider implementation="my.plugin.MyTerminalAllowedActionsProvider"/>
</extensions>
```

## Shell Integration

When the shell process is started, the Terminal plugin injects shell scripts into its startup.
To get the information about the environment and subscribe for events.
For example, to know the positions of the prompt, command and the command output in the shell output.

All APIs that rely on the shell integration are available in `org.jetbrains.plugins.terminal.view.shellIntegration.TerminalShellIntegration`.
It can be accessed via `TerminalView.shellIntegrationDeferred`.
It is not available until the shell process is started and the shell integration is initialized.
You can use `shellIntegrationDeferred.await()` to wait for the shell integration initialization.

> The shell integration is currently available only for *Bash*, *Zsh* and *PowerShell*.
> `TerminalView.shellIntegrationDeferred.await()` call may never succeed for other shells.
> And it is not guaranteed that it will succeed even in supported shells because it highly depends on the user's shell configuration.
> Also, it depends on the state of the [*Shell Integration*](https://www.jetbrains.com/help/idea/settings-tools-terminal.html#application-settings) option in the Terminal settings.
> {style="warning"}

### Exploring terminal output structure

Information about previously executed commands and the current one is stored in `org.jetbrains.plugins.terminal.view.shellIntegration.TerminalBlocksModel`.
This model is built of *Terminal Blocks*.

Terminal block represents a range of text in the **regular** `TerminalOutputModel`
and some additional information about the content and meaning of this text.
Currently, there is a single type of the block: `org.jetbrains.plugins.terminal.view.shellIntegration.TerminalCommandBlock`.

`TerminalCommandBlock` represents the range of the shell output that can contain prompt, command and the command output.
Also, it provides additional metadata about the command, such as working directory, executed command and exit code.
Note that not every command block is an executed terminal command.
Generally, it is just a range of text between the start of the shell prompt until the next prompt.

### Listening for command execution

To get notified when a command is executed in the shell, add a listener using `TerminalShellIntegration.addCommandExecutionListener()`.
Note that this listener is called only if the shell interprets the typed text as a valid command.
For example, it may not be called if the typed command text was blank and the user pressed Enter.

To know the current state of the shell, for example, whether a command is executing or not,
`TerminalShellIntegration.outputStatus` can be used. Also, it allows listening for changes of the shell output state.
