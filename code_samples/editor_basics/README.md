# Editor Sample Project [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Basics of Working with the Editor in IntelliJ SDK Docs][docs:editor_basics]*

## Quickstart

Editor Sample Project provides a [TypedHandlerDelegate][sdk:TypedHandlerDelegate] implementation, which inserts
`editor_basics` on the top of the edited document any time user types a character. In addition, three actions
are available in the Editor context menu:

- Editor Replace Text - replaces selected text with `editor_basics`,
- Editor Add Caret - adds extra caret below the current one,
- Caret Position - shows message dialog with information about the caret position.

## Structure

Editor Sample Project
plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Extension Points

| Name         | Implementation Class                  | Interface                                        |
| ------------ | ------------------------------------- | ------------------------------------------------ |
| typedHandler | [MyTypedHandler][file:MyTypedHandler] | [TypedHandlerDelegate][sdk:TypedHandlerDelegate] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*

### Actions

| Name                        | Implementation Class                                        | Interface                |
| --------------------------- | ----------------------------------------------------------- | ------------------------ |
| EditorIllustrationAction    | [EditorIllustrationAction][file:EditorIllustrationAction]   | [AnAction][sdk:AnAction] |
| EditorHandlerIllustration   | [EditorHandlerIllustration][file:EditorHandlerIllustration] | [AnAction][sdk:AnAction] |
| LogicalPositionIllustration | [EditorAreaIllustration][file:EditorAreaIllustration]       | [AnAction][sdk:AnAction] |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*


[docs]: http://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:editor_basics]: http://www.jetbrains.org/intellij/sdk/docs/tutorials/editor_basics.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extension_points.html
[docs:gradle]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html

[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[file:MyTypedHandler]: ./src/main/java/org/intellij/sdk/editor/MyTypedHandler.java
[file:EditorIllustrationAction]: ./src/main/java/org/intellij/sdk/editor/EditorIllustrationAction.java
[file:EditorHandlerIllustration]: ./src/main/java/org/intellij/sdk/editor/EditorHandlerIllustration.java
[file:EditorAreaIllustration]: ./src/main/java/org/intellij/sdk/editor/EditorAreaIllustration.java

[sdk:TypedHandlerDelegate]: upsource:///platform/lang-api/src/com/intellij/codeInsight/editorActions/TypedHandlerDelegate.java
[sdk:AnAction]: upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
