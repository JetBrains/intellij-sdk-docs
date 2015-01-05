Customizing Project View
================
**TODO** provide links to community repo
To allow a custom plugin to modify the structure of a project as displayed in the project view
TreeStructureProvider
is used.

This class should be derived and the inheritor should be registered as
*treeStructureProvider*
extension point in the
plugin.xml
plugin configuration file:

    <extensions defaultExtensionNs="com.intellij">
        <treeStructureProvider implementation="org.jetbrains.plugins.sample.tree.TextOnlyTreeStructureProvider"/>
    </extensions>

Two methods of the parent class need to be overridden.
Those allow to modify lists of sub-nodes seen in the project view and get user data object of the specified type for the specified selection in the project view.

#Modifying list of child nodes
To provide it override the following method in your custom TreeStructureProvider:

```
Collection<AbstractTreeNode> modify(@NotNull AbstractTreeNode parent, @NotNull Collection<AbstractTreeNode> children, ViewSettings settings);
```

Implementation of this method let a plugin to modify the list of child nodes displayed for the specified node in the
project view. Depending on the properties of the parent it's child list can be modified according to the default project structure.
Elements of the collection passed as the second parameters should be of type
[ProjectViewNode.java]().
The method should return the modified collection of children nodes or the initial children list if no modifications
are required.
The following
[code sample]()
illustrate how to filter out all the files from the project view except those which represent plain text file type.
