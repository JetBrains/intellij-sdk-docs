# Editor Sample Project [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Basics of Working with the Editor in IntelliJ SDK Docs][docs:editor_basics]*

## Quickstart

Editor Sample Project provides a [TypedHandlerDelegate][sdk:TypedHandlerDelegate] implementation, which inserts
`editor_basics` on the top of the edited document any time user types a character. In addition, three actions
are available in the Editor context menu:

- Editor Replace Text - replaces selected text with `editor_basics`,
- Editor Add Caret - adds extra caret below the current one,
- Caret Position - shows message dialog with information about the caret position.

### Extension Points

| Name                        | Implementation Class                  | Interface                                        |
| --------------------------- | ------------------------------------- | ------------------------------------------------ |
| `com.intellij.typedHandler` | [MyTypedHandler][file:MyTypedHandler] | [TypedHandlerDelegate][sdk:TypedHandlerDelegate] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*

### Actions

| ID                                         | Implementation Class                                        | Interface                |
| ------------------------------------------ | ----------------------------------------------------------- | ------------------------ |
| `EditorBasics.EditorIllustrationAction`    | [EditorIllustrationAction][file:EditorIllustrationAction]   | [AnAction][sdk:AnAction] |
| `EditorBasics.EditorHandlerIllustration`   | [EditorHandlerIllustration][file:EditorHandlerIllustration] | [AnAction][sdk:AnAction] |
| `EditorBasics.LogicalPositionIllustration` | [EditorAreaIllustration][file:EditorAreaIllustration]       | [AnAction][sdk:AnAction] |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:editor_basics]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/editor_basics.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html

[file:MyTypedHandler]: ./src/main/java/org/intellij/sdk/editor/MyTypedHandler.java
[file:EditorIllustrationAction]: ./src/main/java/org/intellij/sdk/editor/EditorIllustrationAction.java
[file:EditorHandlerIllustration]: ./src/main/java/org/intellij/sdk/editor/EditorHandlerIllustration.java
[file:EditorAreaIllustration]: ./src/main/java/org/intellij/sdk/editor/EditorAreaIllustration.java

[sdk:TypedHandlerDelegate]: upsource:///platform/lang-api/src/com/intellij/codeInsight/editorActions/TypedHandlerDelegate.java
[sdk:AnAction]: upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java
