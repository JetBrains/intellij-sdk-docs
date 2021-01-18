# Editor Sample Project [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Basics of Working with the Editor in IntelliJ SDK Docs][docs:editor_basics]*

## Quickstart

Editor Sample Project provides a [TypedHandlerDelegate][sdk:TypedHandlerDelegate] implementation, which inserts `editor_basics` on the top of the edited document any time user types a character.
In addition, three actions are available in the Editor context menu:

- Editor Replace Text - replaces the selected text with `editor_basics`,
- Editor Add Caret - adds extra caret below the current one,
- Caret Position - shows message dialog with information about the caret position.

### Extension Points

| Name                        | Implementation                        | Extension Point Class  |
| --------------------------- | ------------------------------------- | ---------------------- |
| `com.intellij.typedHandler` | [MyTypedHandler][file:MyTypedHandler] | `TypedHandlerDelegate` |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*

### Actions

| ID                                         | Implementation                                              | Base Action Class |
| ------------------------------------------ | ----------------------------------------------------------- | ----------------- |
| `EditorBasics.EditorIllustrationAction`    | [EditorIllustrationAction][file:EditorIllustrationAction]   | `AnAction`        |
| `EditorBasics.EditorHandlerIllustration`   | [EditorHandlerIllustration][file:EditorHandlerIllustration] | `AnAction`        |
| `EditorBasics.LogicalPositionIllustration` | [EditorAreaIllustration][file:EditorAreaIllustration]       | `AnAction`        |

*Reference: [Action System in IntelliJ SDK Docs][docs:actions]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:actions]: https://plugins.jetbrains.com/docs/intellij/basic-action-system.html
[docs:editor_basics]: https://plugins.jetbrains.com/docs/intellij/editor-basics.html
[docs:ep]: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html

[file:MyTypedHandler]: ./src/main/java/org/intellij/sdk/editor/MyTypedHandler.java
[file:EditorIllustrationAction]: ./src/main/java/org/intellij/sdk/editor/EditorIllustrationAction.java
[file:EditorHandlerIllustration]: ./src/main/java/org/intellij/sdk/editor/EditorHandlerIllustration.java
[file:EditorAreaIllustration]: ./src/main/java/org/intellij/sdk/editor/EditorAreaIllustration.java
