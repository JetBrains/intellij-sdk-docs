[//]: # (title: Project View)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Please refer to the [Project tool window](https://www.jetbrains.com/help/idea/project-tool-window.html) section in IntelliJ IDEA Web Help.

* [](tree_structure_view.md)

## Decorating Project View Nodes

Plugin authors can modify the representation of nodes in the project view.
This is used to, e.g., change the icon of module nodes to reflect the module type or add URL and server path
to Python Jupyter directories as location strings.

To modify project view node representations, implement
[`ProjectViewNodeDecorator`](upsource:///platform/lang-impl/src/com/intellij/ide/projectView/ProjectViewNodeDecorator.java)
and register it as `com.intellij.projectViewNodeDecorator` extension.
From the interface only the `decorate()` method that modifies `ProjectViewNode`s needs to be implemented.
If you need to update your node representation on certain events, please use
[`ProjectView.update()`](upsource:///platform/lang-impl/src/com/intellij/ide/projectView/ProjectView.java).
