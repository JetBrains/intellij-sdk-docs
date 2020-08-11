# Tree Structure Provider Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Tree Structure View in IntelliJ SDK Docs][docs:tree_structure_view]*

## Quickstart

Tree Structure Provider sample project implements `treeStructureProvider` Extension Point, which allows modifying
the structure of the project tree in the Project View panel. This implementation limits the presented files
to the Plain Text files only.

When the plugin is built and run in the IntelliJ IDE (ref. [Running a Simple Gradle-Based IntelliJ Platform Plugin][docs_run]),
it registers a `TreeStructureProvider` extension, which modifies the Project View presentation structure.

Project View, when collecting the elements for the tree presentation, triggers `AbstractTreeStructure.getChildElements`
method which refers to the registered `TreeStructureProvider` instances. Each of the provider holds a `modify` method
which alters the list of the tree nodes to present:

```java
public Collection<AbstractTreeNode> modify(@NotNull AbstractTreeNode parent,
                                           @NotNull Collection<AbstractTreeNode> children,
                                           ViewSettings settings)
```

Current implementation iterates through the `children` list of `AbstractTreeNode` objects and checks if a node
is of the `PsiFileNode` (tree view also contains nodes other than files or directories - such as libraries and scratches).
In the next step, there is a `VirtualFile` extracted out of the `PsiFileNode` and tested if it is a directory or a file
of the `PlainTextFileType` type. Otherwise, an element is not included in the results list, so only directories and plain text
files are presented. 

### Extension Points

| Name                                 | Implementation Class                                                | Interface                                          |
| ------------------------------------ | ------------------------------------------------------------------- | -------------------------------------------------- |
| `com.intellij.treeStructureProvider` | [TextOnlyTreeStructureProvider][file:TextOnlyTreeStructureProvider] | [TreeStructureProvider][sdk:TreeStructureProvider] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:tree_structure_view]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/tree_structure_view.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html

[file:TextOnlyTreeStructureProvider]: ./src/main/java/org/intellij/sdk/treeStructureProvider/TextOnlyTreeStructureProvider.java

[sdk:TreeStructureProvider]: upsource:///platform/editor-ui-api/src/com/intellij/ide/projectView/TreeStructureProvider.java
