<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Project View

<link-summary>Changing the project view nodes representation.</link-summary>

<tldr>

**Product Help:** [Project tool window](https://www.jetbrains.com/help/idea/project-tool-window.html)

</tldr>

* [](tree_structure_view.md)

## Decorating Project View Nodes

Plugin authors can modify the representation of nodes in the project view.
This is used to, e.g., change the icon of module nodes to reflect the module type or add URL and server path
to Python Jupyter directories as location strings.

To modify project view node representations, implement
[`ProjectViewNodeDecorator`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/projectView/ProjectViewNodeDecorator.java)
and register it in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.projectViewNodeDecorator"/></include>.
From the interface only the `decorate()` method that modifies `ProjectViewNode`s needs to be implemented.

To update the node representation on certain events, use
[`ProjectView.refresh()`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/projectView/ProjectView.java).
