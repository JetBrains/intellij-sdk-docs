<!-- Copyright 2000-2025 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license found in the LICENSE file. -->

# Kotlin Notebook Integration

<link-summary>Interactive IntelliJ Platform development using Kotlin Notebook.</link-summary>
<primary-label ref="2025.2"/>

<tldr id="tldr">

**GitHub**: [Kotlin Notebook IntelliJ Platform](https://github.com/Kotlin/kotlin-notebook-intellij-platform)

**Examples**: [Kotlin Notebook IntelliJ Platform examples](https://github.com/Kotlin/kotlin-notebook-intellij-platform/tree/master/examples)

**JetBrains Platform Forum**: [Kotlin Notebook](https://platform.jetbrains.com/c/intellij-platform/kotlin-notebook/25) category

</tldr>

The Kotlin Notebook IntelliJ Platform integration enables interactive development and experimentation with IntelliJ Platform APIs directly within a notebook environment. This integration allows developers to run IntelliJ Platform code within the active IDE runtime, removing traditional barriers associated with plugin project setup, compilation, and deployment.

Like Jupyter Notebooks, Kotlin Notebook provides an interactive platform for prototyping, testing, and exploring IntelliJ Platform functionality through executable code cells.

## Getting Started

To begin using the IntelliJ Platform integration:

<procedure>

1. Create a new Kotlin Notebook file (`.ipynb`) in your project or as a scratch file using <shortcut>⌘⇧N</shortcut> on macOS or <shortcut>Ctrl+Shift+N</shortcut> on Windows/Linux.
2. **Important**: Switch to **Run in IDE Process** mode in the notebook toolbar.
3. In the first code cell, declare:
    ```kotlin
    %use intellij-platform
    ```
</procedure>

Once executed, the necessary IntelliJ Platform libraries are loaded into the Kotlin Notebook classpath, making them available for import in subsequent cells.

## Core Features

### UI Rendering

The integration enables direct rendering of UI components within notebook cells. When working with [Kotlin UI DSL](kotlin_ui_dsl_version_2.md) or standard Swing components, returning them as a response causes immediate rendering with full interactivity.

```kotlin
import com.intellij.ui.dsl.builder.panel
import java.awt.Dimension

panel {
  row {
    checkBox("Check me!")
  }
}.apply {
  size = Dimension(500, 200)
}
```

Use the `runInEdt` helper to ensure code execution on the [Event Dispatch Thread (EDT)](threading_model.md) and handle exceptions gracefully:

```kotlin
runInEdt {
    // UI code that must run on EDT
}
```

### Resource Management

The integration provides automatic resource management through the IntelliJ Platform's [Disposer](disposers.md) mechanism. A dedicated `notebookDisposable` variable allows registration of elements that need cleanup:

```kotlin
Disposer.register(notebookDisposable, myDisposable)

Disposer.register(notebookDisposable) {
  // ...
}
```

### Plugin Loading

By default, only the core IntelliJ Platform is loaded into the classpath. To use other plugins (bundled or installed), explicitly load them using:

```kotlin
loadPlugins("Git4Idea", "com.intellij.java")
```

### Extension Registration

Unlike traditional plugin development with <path>plugin.xml</path>, the integration provides programmatic extension registration:

```kotlin
registerExtension(extensionPointName, instance)
```

## Global Variables
{id="variables"}

When the IntelliJ Platform integration is loaded, the following variables are automatically available in your Kotlin Notebook environment.
These variables provide access to essential IDE components, configuration information, and management interfaces.

| Variable                   | Type                                                                                                                                                 | Description                                                                                                                          |
|----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------|
| `idePath`                  | `Path`                                                                                                                                               | Represents the resolved file system path to the IntelliJ Platform installation directory.                                            |
| `ide`                      | [`ProductInfoBasedIde`](%gh-pv%/intellij-plugin-structure/structure-ide/src/main/java/com/jetbrains/plugin/structure/ide/ProductInfoBasedIde.kt)     | Represents an IDE instance, which allows interacting with plugins and resolving their dependencies based on the IDE's configuration. |
| `notebookDisposable`       | [`Disposable`](%gh-ic%/platform/util/src/com/intellij/openapi/Disposable.java)                                                                       | Represents a disposable used for managing the IntelliJ Platform lifetime of the current notebook.                                    |
| `notebookPluginDescriptor` | [`PluginMainDescriptor`](%gh-ic%/platform/core-impl/src/com/intellij/ide/plugins/IdeaPluginDescriptorImpl.kt)                                        | Represents a plugin descriptor for the plugin created with Kotlin Notebook IntelliJ Platform integration.                            |
| `pluginManager`            | [`PluginManager`](%gh-ic%/platform/core-impl/src/com/intellij/ide/plugins/PluginManager.java)                                                        | Instance of the `PluginManager`, which allows accessing information about plugins and their states in the current IDE environment.   |
| `pluginRepository`         | [`PluginRepository`](%gh-pv%/intellij-plugin-verifier/verifier-repository/src/main/java/com/jetbrains/pluginverifier/repository/PluginRepository.kt) | Instance of `PluginRepository`, responsible for managing plugins via the JetBrains Marketplace plugin repository.                    |
| `productInfo`              | [`ProductInfo`](%gh-pv%/intellij-plugin-structure/structure-intellij/src/main/java/com/jetbrains/plugin/structure/intellij/platform/ProductInfo.kt)  | Lazily initialized property containing current IntelliJ Platform product information.                                                |

## Notebook Helpers
{id="notebook-helpers"}

| Function                                | Description                                                                                                                                                                  |
|-----------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `error(message: String)`                | Throws an exception with the specified message, which will be printed as the cell's output.                                                                                  |
| `loadPlugins(vararg pluginIds: String)` | Loads plugins installed in the current IDE into the script context based on their plugin IDs. This method also supports optionally loading plugin classes and class loaders. |
| `runInEdt(block: () -> Unit)`           | Runs the given block in the [Event Dispatch Thread (EDT)](threading_model.md). If an exception is thrown, it is displayed in the cell's output.                              |

## IntelliJ Platform Helpers
{id="intellij-platform-helpers"}

| Function                                                                    | Description                                                                 |
|-----------------------------------------------------------------------------|-----------------------------------------------------------------------------|
| `currentProject()`                                                          | Returns the current open `Project` instance. |
| `currentEditor()`                                                           | Returns the current `FileEditor` instance or null if no editor is open.     |
| `registerExtension(extensionPointName: ExtensionPointName<T>, instance: T)` | Registers an extension programmatically for the given extension point.      |

## Examples {id="examples"}

> See [Kotlin Notebook IntelliJ Platform](https://github.com/Kotlin/kotlin-notebook-intellij-platform/tree/master/examples) project repository for more examples.
>
{style="note"}

### Accessing IDE Information

```kotlin
println("IDE: ${productInfo.name}")
println("Version: ${productInfo.version}")
println("Build: ${productInfo.buildNumber}")
```

### Loading and Using Plugins

```kotlin
loadPlugins("com.intellij.mcpServer")
```

```kotlin
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.ui.DialogEarthquakeShaker
import com.intellij.openapi.wm.WindowManager
import com.intellij.mcpserver.McpToolset
import com.intellij.mcpserver.annotations.McpDescription
import com.intellij.mcpserver.annotations.McpTool
import com.intellij.mcpserver.project
import kotlinx.coroutines.delay
import kotlin.coroutines.coroutineContext

class ShakeMcpToolset : McpToolset {

  @McpTool
  @McpDescription("Shakes your JetBrains IDE 😵‍💫")
  suspend fun shakeIde(
    @McpDescription("How many times to shake your IDE?")
    times: Int = 1
  ) {
    val project = coroutineContext.project
    val window = WindowManager.getInstance().getFrame(project)

    repeat(times) {
      DialogEarthquakeShaker.shake(window)
      delay(500)
    }
  }
}

registerExtension(McpToolset.EP, ShakeMcpToolset())
```

### Rendering Kotlin UI DSL Components

```kotlin
import com.intellij.ui.dsl.builder.panel
import java.awt.Dimension

panel {
  row {
    checkBox("Check me!")
      .comment("A comment for this checkbox")
  }
}.apply {
  size = Dimension(500, 200)
}
```

### Rendering Dialog

```kotlin
import com.intellij.openapi.ui.Messages

runInEdt {
  Messages.showMessageDialog(
    null,
    "This is a modal dialog message",
    "Dialog Title",
    Messages.getInformationIcon()
  )
}
```

### Creating Disposable Tool Window

```kotlin
import com.intellij.icons.AllIcons
import com.intellij.openapi.application.runInEdt
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.ToolWindowManager

val project = currentProject() ?: error("Project not found")
val toolWindowManager = ToolWindowManager.getInstance(project)

runInEdt {
  val toolWindow = toolWindowManager.getToolWindow("My Tool Window")
    ?: toolWindowManager.registerToolWindow("My Tool Window") {
      icon = AllIcons.General.Error
    }

  Disposer.register(notebookDisposable) {
    toolWindow.remove()
  }
}
```
