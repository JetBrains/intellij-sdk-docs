# IntelliJ Platform SDK Code Samples

[![official JetBrains project](https://jb.gg/badges/official-flat-square.svg)][jb:github]
[![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg?style=flat-square)][jb:docs]
[![Bluesky](https://img.shields.io/badge/%40platform.jetbrains.com-0285FF?logo=bluesky&logoColor=fff)][jb:bsky]
[![X](https://img.shields.io/badge/%40JBPlatform-1DA1F2?logo=x)][jb:x]
[![Build](https://img.shields.io/github/actions/workflow/status/JetBrains/intellij-sdk-docs/code-samples.yml?branch=main&style=flat-square)][gh:workflow-code-samples]
[![JetBrains Platform Forum Status](https://img.shields.io/discourse/status?server=https%3A%2F%2Fplatform.jetbrains.com)][jb:forum]

Learn how to build plugins using IntelliJ Platform SDK for the [JetBrains products][jb:products] by experimenting with our code samples.
These samples show you how features work and help you jumpstart your plugins.

> [!TIP]
> To start a new plugin project, consider using [IntelliJ Platform Plugin Template][gh:template] which offers a pure boilerplate template to make it easier to create a new plugin project.
>
> The code samples can also be found in the [IntelliJ SDK Code Samples](https://github.com/JetBrains/intellij-sdk-code-samples) mirror repository.

To learn more, browse [available Extension Points][docs:eps], explore Extension Point usages in open-source plugins using [IntelliJ Platform Explorer](https://jb.gg/ipe) and learn how to [Explore the IntelliJ Platform API][docs:explore-api].

## Target Platform

All Code Samples target the _Latest GA - 3 Releases_ platform.

## Structure

Code Samples depend on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the `plugin.xml` file, which is created according to the [Plugin Configuration File documentation][docs:plugin.xml].
It describes definitions of the actions, extensions, or listeners provided by the plugin.

## Code Samples

Please see [Code Samples][docs:code-samples] topic on how to import and run code samples.

In the following table, you may find all available samples provided in the separated directories as stand-alone projects available for running with the Gradle `runIde` task.

| Code Sample                                                                 | Description                                                                                                                                                       |
|-----------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Action Basics](./action_basics)                                            | Action and Action Group patterns implementation, adds entries to the Tools menu.                                                                                  |
| [Comparing References Inspection](./comparing_string_references_inspection) | Local Inspection Tool, adds entries to **Settings &#124; Editor &#124; Inspections &#124; Java &#124; Probable Bugs**.                                            |
| [Conditional Operator Intention](./conditional_operator_intention)          | Intention action, suggests converting a ternary operator into an `if` block and adds entry to **Settings &#124; Editor &#124; Intentions &#124; SDK Intentions**. |
| [Editor Basics](./editor_basics)                                            | Basic Editor APIs example with editor popup menu with extra actions.                                                                                              |
| [Framework Basics](./framework_basics)                                      | Basic *SDK Demo Framework* support added to the **File &#124; New &#124; Project &#124; Java** wizard.                                                            |
| [Live Templates](./live_templates)                                          | Live templates for Markdown language, adds an entry to the **Settings &#124; Editor &#124; Live Templates** dialog.                                               |
| [Max Opened Projects](./max_opened_projects)                                | Application services and listeners, shows warning dialog when more than 3 open projects are opened.                                                               |
| [Module](./module)                                                          | *SDK Demo Module* module type added to the **File &#124; New &#124; Project...** wizard.                                                                          |
| [Project Model](./project_model)                                            | Interacts with the project model, adds menu items to **Tools** and **Editor Context** menus.                                                                      |
| [Project View Pane](./project_view_pane)                                    | Project View Pane listing only image files.                                                                                                                       |
| [Project Wizard](./project_wizard)                                          | Project Wizard example with demo steps.                                                                                                                           |
| [PSI Demo](./psi_demo)                                                      | PSI Navigation features presentation.                                                                                                                             |
| [Run Configuration](./run_configuration)                                    | Run configuration implementation with factory, options and UI.                                                                                                    |
| [Settings](./settings)                                                      | Custom settings panel, adds a settings panel to the **Settings** panel under **Tools**.                                                                           |
| [Simple Language Plugin](./simple_language_plugin)                          | Custom language support, defines a new *Simple language* with syntax highlighting, annotations, code completion, and other features.                              |
| [Theme Basics](./theme_basics)                                              | Sample *theme* plugin with basic interface modifications.                                                                                                         |
| [Tool Window](./tool_window)                                                | Custom Tool Window example plugin.                                                                                                                                |
| [Tree Structure Provider](./tree_structure_provider)                        | Tree Structure Provider showing only plain text files.                                                                                                            |

[gh:workflow-code-samples]: https://github.com/JetBrains/intellij-sdk-docs/actions/workflows/code-samples.yml
[gh:template]: https://github.com/JetBrains/intellij-platform-plugin-template

[jb:github]: https://github.com/JetBrains/.github/blob/main/profile/README.md
[jb:docs]: https://plugins.jetbrains.com/docs/intellij/
[jb:products]: https://www.jetbrains.com/products.html
[jb:forum]: https://platform.jetbrains.com
[jb:bsky]: https://bsky.app/profile/platform.jetbrains.com
[jb:x]: https://x.com/JBPlatform

[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:code-samples]: https://plugins.jetbrains.com/docs/intellij/code-samples.html
[docs:eps]: https://plugins.jetbrains.com/docs/intellij/extension-point-list.html
[docs:gradle]: https://plugins.jetbrains.com/docs/intellij/developing-plugins.html
[docs:plugin.xml]: https://plugins.jetbrains.com/docs/intellij/plugin-configuration-file.html
[docs:explore-api]: https://plugins.jetbrains.com/docs/intellij/explore-api.html
