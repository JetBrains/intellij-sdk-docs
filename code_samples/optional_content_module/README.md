# Optional Content Module Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Modular Plugins in IntelliJ SDK Docs][docs:modular_plugins], [Plugin Content in IntelliJ SDK Docs][docs:plugin_content]*

*Video Overview:* [Gradle Setup Powering Multi-module IntelliJ Plugins](https://youtu.be/7fEdrktCYDo)

## Quickstart

Optional Content Module Sample demonstrates a multi-module plugin that uses Plugin Model v2.
The plugin declares two plugin content modules:

- a required `mincssrel.shared` content module with functionality available to other content modules,
- an optional `mincssrel.css` content module that uses CSS PSI functionality when available.

The required content module exposes project-scoped [SharedMessageService][file:SharedMessageService].
It also registers an action that uses this service and demonstrates its availability.

The optional CSS content module depends on the `intellij.css` bundled module and registers an action that creates a CSS PSI file and displays its selector names.
If the CSS module dependency is unavailable, the optional module is skipped without preventing the required module from loading.
This can be demonstrated by running the plugin under IntelliJ IDEA 2025.3 or earlier without a subscription, where CSS PSI functionality is not freely available.

### Content Modules

The plugin descriptor [plugin.xml][file:pluginXml] file is intentionally minimal.
It contains only the `<content>` declaration and lists the plugin content modules.
Actions and other functionality are declared in the module descriptor files.

| Name               | Loading    | Descriptor                                      | Description                                |
|--------------------|------------|-------------------------------------------------|--------------------------------------------|
| `mincssrel.shared` | `required` | [mincssrel.shared.xml][file:sharedDescriptor]   | Registers shared plugin functionality.     |
| `mincssrel.css`    | `optional` | [mincssrel.css.xml][file:cssDescriptor]         | Adds CSS-specific functionality when CSS APIs are available. |

*Reference: [Modular Plugins in IntelliJ SDK Docs][docs:modular_plugins]*

### Naming Conventions

This sample demonstrates the naming convention for Gradle subprojects, plugin content module names, and plugin content module descriptor filenames.

| Gradle Subproject Directory | Gradle Project Path | Content Module Name | Module Descriptor                                                       |
|-----------------------------|---------------------|---------------------|-------------------------------------------------------------------------|
| `shared`                    | `:shared`           | `mincssrel.shared`  | [shared/src/main/resources/mincssrel.shared.xml][file:sharedDescriptor] |
| `css`                       | `:css`              | `mincssrel.css`     | [css/src/main/resources/mincssrel.css.xml][file:cssDescriptor]          |

The `mincssrel` prefix is taken from the `rootProject.name` value declared in [settings.gradle.kts][file:settingsBuild].
For each content module, the descriptor filename matches the content module name with an `.xml` extension.
The module descriptor is placed directly under the content module subproject's `src/main/resources` directory.

The content module names are referenced from the root [plugin.xml][file:pluginXml] file:

```xml
<content>
  <module name="mincssrel.shared" loading="required" />
  <module name="mincssrel.css" loading="optional" />
</content>
```

### Actions

| ID                                             | Implementation                        | Module              |
|------------------------------------------------|---------------------------------------|---------------------|
| `com.github.novotnyr.mincssrel.shared.SharedAction` | [SharedAction][file:SharedAction] | `mincssrel.shared`  |
| `com.github.novotnyr.mincssrel.css.CssAction`       | [CssAction][file:CssAction]       | `mincssrel.css`     |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*

### Gradle Dependencies

This sample is built with the IntelliJ Platform Gradle Plugin 2.x.

The [settings.gradle.kts][file:settingsBuild] file applies the `org.jetbrains.intellij.platform.settings` plugin and configures IntelliJ Platform default repositories for all projects.
It also includes the `shared` and `css` Gradle subprojects, each producing one plugin content module.

The root [build.gradle.kts][file:rootBuild] declares project dependencies on both content module subprojects.
These project dependencies make the `mincssrel.shared` and `mincssrel.css` module artifacts part of the composed plugin build.
These content module artifacts are placed in the `lib/modules` directory of the resulting ZIP plugin artifact.

The root build uses a `subprojects {}` block to apply common conventions to every content module subproject:

```kotlin
subprojects {
  apply(plugin = "org.jetbrains.kotlin.jvm")
  apply(plugin = "org.jetbrains.intellij.platform.module")
}
```

Each plugin content module subproject gets Kotlin support and the IntelliJ Platform Module plugin.
This simplifies the submodule build scripts.
Individual content modules then declare only their specific dependencies.
For example, [css/build.gradle.kts][file:cssBuild] depends on the shared module and adds the `intellij.css` bundled module to access CSS PSI APIs.

| Build File                       | Dependency                                     | Purpose |
|----------------------------------|------------------------------------------------|---------|
| [build.gradle.kts][file:rootBuild] | `implementation(project(":shared"))`         | Adds the required shared content module to the root plugin project. |
| [build.gradle.kts][file:rootBuild] | `implementation(project(":css"))`            | Adds the optional CSS content module to the root plugin project. |
| [css/build.gradle.kts][file:cssBuild] | `bundledModule("intellij.css")`          | Adds CSS APIs to the optional module compilation classpath. |

*Reference: [IntelliJ Platform Gradle Plugin Dependencies Extension in IntelliJ SDK Docs][docs:gradle_dependencies]*

[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:actions]: https://plugins.jetbrains.com/docs/intellij/action-system.html
[docs:gradle_dependencies]: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin-dependencies-extension.html#bundled-modules
[docs:modular_plugins]: https://plugins.jetbrains.com/docs/intellij/modular-plugins.html
[docs:plugin_content]: https://plugins.jetbrains.com/docs/intellij/plugin-content.html

[file:pluginXml]: ./src/main/resources/META-INF/plugin.xml
[file:settingsBuild]: ./settings.gradle.kts
[file:rootBuild]: ./build.gradle.kts
[file:cssBuild]: ./css/build.gradle.kts
[file:sharedDescriptor]: ./shared/src/main/resources/mincssrel.shared.xml
[file:cssDescriptor]: ./css/src/main/resources/mincssrel.css.xml
[file:SharedAction]: ./shared/src/main/kotlin/com/github/novotnyr/mincssrel/shared/SharedAction.kt
[file:SharedMessageService]: ./shared/src/main/kotlin/com/github/novotnyr/mincssrel/shared/SharedMessageService.kt
[file:CssAction]: ./css/src/main/kotlin/com/github/novotnyr/mincssrel/css/CssAction.kt
