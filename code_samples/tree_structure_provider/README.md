[IntelliJ Platform SDK Code Samples](../README.md)

# Tree Structure Provider

## Quickstart

Tree Structure Provider sample project implements `treeStructureProvider` Extension Point, which allows modifying
the structure of the project tree in the Project View panel. This implementation limits the presented files
to the Plain Text files only.

See the [Tree Structure View][docs_tree_structure_view] documentation page for more information.

## Structure

The plugin was developed using the [IntelliJ Platform SDK][docs_sdk].

The main file is [plugin.xml][plugin.xml], which is created accordingly to the [Plugin Configuration File documentation][docs_pluginxml].
It describes definitions of the actions, extensions, or listeners provided by the plugin:

### Extension Points

| Name | Implementation Class | Interface |
| ---- | -------------------- | --------- |
| treeStructureProvider | [TextOnlyTreeStructureProvider][treeStructureProvider_implementation] | [TreeStructureProvider][treeStructureProvider_interface] |

[Extension Points documentation][docs_ep]

## Function

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


[plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[docs_tree_structure_view]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/tree_structure_view.html
[docs_pluginxml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html
[docs_sdk]: https://www.jetbrains.org/intellij/sdk/docs/intro/about.html
[docs_ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extension_points.html
[docs_run]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system/prerequisites.html#running-a-simple-gradle-based-intellij-platform-plugin

[treeStructureProvider_implementation]: ./src/main/java/org/intellij/sdk/treeStructureProvider/TextOnlyTreeStructureProvider.java
[treeStructureProvider_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/editor-ui-api/src/com/intellij/ide/projectView/TreeStructureProvider.java
