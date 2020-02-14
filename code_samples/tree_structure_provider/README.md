[IntelliJ Platform SDK Code Samples](../README.md)

# Tree Structure Provider

Sample project implementing `treeStructureProvider` Extension Point, which allows modifying the structure of the project
tree in the Project View panel. This implementation limits the presented files to the Plain Text files only.

See the [Tree Structure View](https://www.jetbrains.org/intellij/sdk/docs/tutorials/tree_structure_view.html)
documentation page for more information.

## Implemented Extension Points

| Extension Point | Implementation Class | Interface |
| --------------- | -------------------- | --------- |
| treeStructureProvider | [TextOnlyTreeStructureProvider][treeStructureProvider_class] | [TreeStructureProvider][treeStructureProvider_interface] |


[treeStructureProvider_class]: ./src/main/java/org/intellij/sdk/treeStructureProvider/TextOnlyTreeStructureProvider.java
[treeStructureProvider_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/ide/projectView/TreeStructureProvider.java
