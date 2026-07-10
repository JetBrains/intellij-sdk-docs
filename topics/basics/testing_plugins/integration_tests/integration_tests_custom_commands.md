<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Integration Tests: Custom Commands

<primary-label ref="2023.2"/>

<link-summary>Walkthrough how to implement a custom command by extending the performanceTestingPlugin.</link-summary>

> This page is part of the [](integration_tests.md) tutorial.

For introduction and setting up dependencies, refer to [](integration_tests_intro.md).

[`performanceTestingPlugin`](%gh-ic%/plugins/performanceTesting) provides a way to execute commands inside the IDE.
A command is an abstraction that performs an action using the internal IntelliJ Platform API.
It's based on macros, but the main difference is that there is a predefined set of commands that don't finish until the action is finished.

For example, when completion is invoked via a macro,
the macro finishes as soon as the completion action is invoked.
When the `doComplete` command is used,
it finishes only after all the completion contributors have provided their results and the final list is sorted.

To implement custom commands, create a plugin that extends the `performanceTestingPlugin`.

A basic setup looks like this:

Create a <path>resources/META-INF/plugin.xml</path> file:

```xml
<idea-plugin>
  <name>Plugin name</name>
  <id>com.intellij.performancePlugin.myPlugin</id>

  <description>My integration tests</description>
  <depends>com.jetbrains.performancePlugin</depends>
  <depends>com.intellij.modules.lang</depends>

  <extensions defaultExtensionNs="com.jetbrains">
    <performancePlugin.commandProvider implementation="com.intellij.myPlugin.performanceTesting.MyPluginCommandProvider"/>
  </extensions>
</idea-plugin>
```

Then, create a command provider:

```kotlin
package com.intellij.myPlugin.performanceTesting

class MyPluginCommandProvider : CommandProvider {
  override fun getCommands() = mapOf(
      Pair(MyCommand.PREFIX, CreateCommand(::MyCommand)),
    )
}
```

Then, implement the command:

```kotlin
package com.intellij.myPlugin.performanceTesting.command

import com.intellij.openapi.ui.playback.PlaybackContext
import com.intellij.openapi.ui.playback.commands.PlaybackCommandCoroutineAdapter

internal class MyCommand(text: String, line: Int) : PlaybackCommandCoroutineAdapter(text, line) {
  companion object {
    const val PREFIX = CMD_PREFIX + "myCommandName"
  }

  override suspend fun doExecute(context: PlaybackContext) {
    // implementation goes here
  }
}
```

The test implementation that uses `Starter` to invoke the command looks like this:

```kotlin
fun <T : CommandChain> T.runMyCommand(): T {
  addCommand(CMD_PREFIX + "myCommandName")
  return this
}

class ExampleOfMyCommandTest {

  @Test
  fun invokeMyCommand() {
    val context = Starter.newContext(testName = CurrentTestMethod.hyphenateWithClass(), testCase = IdeaUltimateCases.JitPackAndroidExample)
      .skipIndicesInitialization() // skip indices if indexing isn't necessary for the test

    context.runIDE(
      commands = CommandChain()
        .runMyCommand()
        .exitApp()
    )
  }
}
```
