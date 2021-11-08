# Tree Structure Provider Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Tree Structure View in IntelliJ SDK Docs][docs:tree_structure_view]*

## Quickstart

The Tree Structure Provider sample project implements `com.intellij.treeStructureProvider` Extension Point, which allows modifying the structure of the project tree in the _Project View_ panel.
This implementation limits the presented files to the Plain Text files only.

The current implementation checks if a Project View node represents a directory or file of the `PlainTextFileType` type.
Otherwise, an element is not included in the results list, so only directories and plain text files are rendered.

### Extension Points

| Name                                 | Implementation                                                      | Extension Point Class   |
|--------------------------------------|---------------------------------------------------------------------|-------------------------|
| `com.intellij.treeStructureProvider` | [TextOnlyTreeStructureProvider][file:TextOnlyTreeStructureProvider] | `TreeStructureProvider` |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:tree_structure_view]: https://plugins.jetbrains.com/docs/intellij/tree-structure-view.html
[docs:ep]: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html

[file:TextOnlyTreeStructureProvider]: ./src/main/java/org/intellij/sdk/treeStructureProvider/TextOnlyTreeStructureProvider.java
