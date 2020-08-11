# Tree Structure Provider Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Tree Structure View in IntelliJ SDK Docs][docs:tree_structure_view]*

## Quickstart

Tree Structure Provider sample project implements `treeStructureProvider` Extension Point, which allows modifying
the structure of the project tree in the Project View panel. This implementation limits the presented files
to the Plain Text files only.

Current implementation checks if a Project View nodes represents directories or file of the `PlainTextFileType` type.
Otherwise, an element is not included in the results list, so only directories and plain text files are rendered. 

### Extension Points

| Name                                 | Implementation                                                      | Extension Point Class                              |
| ------------------------------------ | ------------------------------------------------------------------- | -------------------------------------------------- |
| `com.intellij.treeStructureProvider` | [TextOnlyTreeStructureProvider][file:TextOnlyTreeStructureProvider] | [TreeStructureProvider][sdk:TreeStructureProvider] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:tree_structure_view]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/tree_structure_view.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html

[file:TextOnlyTreeStructureProvider]: ./src/main/java/org/intellij/sdk/treeStructureProvider/TextOnlyTreeStructureProvider.java

[sdk:TreeStructureProvider]: upsource:///platform/editor-ui-api/src/com/intellij/ide/projectView/TreeStructureProvider.java
