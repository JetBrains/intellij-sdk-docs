[//]: # (title: Explore the IntelliJ Platform API)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2 -->

<excerpt>Strategies and tools for exploring the API.</excerpt>

Sometimes it can be challenging to implement plugin features for the IntelliJ Platform,
especially when you've hit a roadblock and you're unsure how to move forward.
This usually happens in two situations:

- You're trying to implement a feature that you've already seen in the IDE and now you need to find the appropriate extension point or
  class that allows you to hook into the relevant mechanisms.
- You have already started working on a feature but you're unsure how the different parts of the IntelliJ Platform interact with each other.
  In such situations, it is helpful to be able to navigate the IntelliJ Platform code confidently and to find relevant examples in other plugins.

This guide provides a list of proven strategies that can help you overcome these challenges and gather enough information to continue your work.
Furthermore, the tips below will help build your confidence as you explore the IntelliJ Platform.

## 1 Extension Points (EPs)

### 1.1 Browse Lists of EPs

The most important resource for discovering new EPs is the extensive list provided directly in the
[IntelliJ Platform SDK Documentation](extension_point_list.md).
On this page, you will find all of the EPs, and each entry includes a link to the online source code and a link to the
[IntelliJ Platform Explorer](https://jb.gg/ipe),
which helps you find examples of this EP in other plugins.
Additionally, dedicated Extension Point Lists specific to IDEs are available under _Part VIII — Product Specific_.

### 1.2 Use Autocompletion Information

Another way to discover EPs is by using autocompletion or navigating through EP XML files.
When you open a new tag in your <path>plugin.xml</path> file (inside the `extensions` block with `defaultExtensionNs="com.intellij"`),
the IDE will automatically suggest possible EPs.

![Using Completion Suggestions](plugin_xml_completion_suggestion.png){width="706"}{animated="true"}{border-effect="rounded"}

This is the first step in discovering new features that haven't been explicitly mentioned in the IntelliJ Platform Docs.
Note that in the completion popup, you can call
[quick documentation](https://www.jetbrains.com/help/idea/viewing-reference-information.html#inline-quick-documentation),
which then shows its properties, the implementation class, as well as a direct link to open usage results from
[IntelliJ Platform Explorer](https://jb.gg/ipe).

### 1.3 Search the IntelliJ Platform Code

Use
[Go to Declaration](https://www.jetbrains.com/help/idea/navigating-through-the-source-code.html#go_to_declaration)
on EPs that are implemented in <path>plugin.xml</path> to navigate to its definition in the XML file.
There you'll find more EPs, and browsing through this list helps you discover features you might not have been aware of.
[Search everywhere](https://www.jetbrains.com/help/idea/searching-everywhere.html)
or
[Go to file](https://www.jetbrains.com/help/idea/discover-intellij-idea.html#navigation-and-search)
helps you search for all files containing extension points.
Just use <path>*ExtensionPoints.xml</path> as the search pattern and enable the <control>include non-project items</control> checkbox.

However, if a bundled or third-party plugin exposes EPs for others to implement, these EPs are defined in the <path>plugin.xml</path> files
of the plugins and not in the <path>*ExtensionPoints.xml</path> files of the IntelliJ Platform.
One such example is the EPs exposed by the Markdown plugin that adds support for custom languages inside fenced code blocks of Markdown files.

### 1.4 Use Advanced Search

Explore the <path>plugin.xml</path> files of bundled or 3rd party plugins.
If you have the IntelliJ Platform sources available either in your own plugin project or in a separate instance,
you can use
[Structural Search](https://www.jetbrains.com/help/idea/structural-search-and-replace.html)
to find all the files that meet the following criteria:

- The file type is XML
- It contains the tag `<idea-plugin>`
- The file is in the scope <control>Project and Libraries</control>

![Search Structurally for plugin.xml](search_structurally_for_plugin_xml.png)

The search results will contain many plugin XML files.
To find specific implementations of EPs in third-party plugins, use the IntelliJ Platform Explorer ([see 3.2](explore_api.md#32-search-the-intellij-platform-explorer)).
Inspecting the <path>plugin.xml</path> files of other plugins not only helps you discover new features but also shows how things like menu entries or
notification groups can be defined in the XML file.

## 2 Navigating the IntelliJ Platform Source Code

The following tips will help you navigate through the IntelliJ Platform source code if you already have an idea of what you're looking for.
It's important that you're familiar with
[navigating](https://www.jetbrains.com/help/idea/reference-keymap-win-default.html#navigate_from_symbols) and
[searching](https://www.jetbrains.com/help/idea/reference-keymap-win-default.html#find_everything)
source code, as well as other basic features of IntelliJ IDEA.

Many developers keep the
[IntelliJ Community source code](https://github.com/JetBrains/intellij-community)
open in a separate window while working on their plugin.
Others simply search the source code of the IntelliJ Platform that is attached by default when using a
[Gradle IntelliJ Plugin](tools_gradle_intellij_plugin.md)-based project.
While both methods work, it should be noted that developing plugins without inspecting the IntelliJ Platform code is nearly impossible,
and all of the tips below assume that you have the source code available.

### 2.1 Find Example Implementations

When working with interfaces or abstract classes of EPs, use IntelliJ IDEA's
[Go to Implementation](https://www.jetbrains.com/help/idea/navigating-through-the-source-code.html#go_to_implementation) or
[Find Usages](https://www.jetbrains.com/help/idea/find-usages-dialog.html)
feature to discover examples of how they are used in the IntelliJ Platform.

### 2.2 Look for Particular Class Names

Access to many features is provided through the `Manager` and `Service` classes, such as:

- `com.intellij.openapi.application.ApplicationManager`
- `com.intellij.psi.PsiManager`

Therefore, it can be helpful to search for classes that match the pattern `com.intellij.*Manager` and look through the list of results.
Note that not all of these classes have the `com.intellij` prefix, and also that you can
[define custom scopes](https://www.jetbrains.com/help/idea/configuring-scopes-and-file-colors.html)
to limit your searches, for example to only <path>idea-xxx.jar</path> files.

### 2.3 Inspect the Contents of Packages

If you open an EP's interface or abstract class, it is always helpful to inspect the contents of its package.
For instance, the interface of the `com.intellij.sdkType` EP lives in the `com.intellij.openapi.projectRoots` package.
Inspecting the contents of this package shows many related classes that will be useful if you are implementing this feature.

### 2.4 Search for Symbol Names

As a last resort, it is sometimes helpful to search directly for a method, class, and class member if you can guess a part of its name.
You can either use
[Search Everything or Go to Symbol](https://www.jetbrains.com/help/idea/reference-keymap-win-default.html#find_everything).
Note that you need to change the search scope to <control>All Places</control> in the search window to find all occurrences of symbols.

### 2.5 Refrain from Using Internal Classes

As a general remark, the use of internal classes is strongly discouraged (i.e. classes ending with `Impl` in their name,
located under `impl` package, or included in <path>*-impl.jar</path>).

Also, API annotated with
[`org.jetbrains.annotations.ApiStatus.Internal`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/ApiStatus.java)
should not be used, see [](api_internal.md) for more details and replacements.

## 3 Tools and References

### 3.1 Use Internal Mode and PsiViewer

As a plugin developer, you should enable [internal mode](enabling_internal.md) in IntelliJ IDEA.
This provides access to a suite of tools to help you develop, debug, and test IntelliJ Platform plugins.

One of its most helpful features is the [UI Inspector](internal_ui_inspector.md),
which lets you investigate all parts of the UI of every IntelliJ-based IDE by simply clicking on them.
Equally important is the <menupath>Tools | Internal Actions | UI Debugger</menupath> tool.
It will display all actions that are run by the IDE when you interact with UI elements, for example, by clicking a button.

Finally, internal mode provides the <menupath>Tools | View PSI Structure…</menupath> and <menupath>Tools | View PSI Structure of Current File…</menupath> actions,
which allow you to analyze the [PSI tree](psi.md), please see [documentation](https://www.jetbrains.com/help/idea/psi-viewer.html).
The [PsiViewer plugin](https://plugins.jetbrains.com/plugin/227-psiviewer) is a separate plugin with similar capabilities for inspecting PSI trees,
and it comes with a dedicated tool window that displays information on the fly.
However, it does not display information about [stubs](stub_indexes.md) or [formatting models](code_formatting.md).

### 3.2 Search the IntelliJ Platform Explorer

The [IntelliJ Platform Explorer](https://plugins.jetbrains.com/intellij-platform-explorer)
is a search tool for browsing [Extension Points](plugin_extensions.md) (EP) and [Listeners](plugin_listeners.md) inside existing implementations of all open-source IntelliJ Platform plugins published on [JetBrains Marketplace](https://plugins.jetbrains.com).
You can navigate directly to the source files to find inspiration when implementing your own extensions and listeners for IntelliJ-based IDEs.

### 3.3 Browse Available References

The IntelliJ Platform SDK Documentation should always be the first resource you check for information.
Here is a condensed list you can use for further reference:

- [](useful_links.md)
- [](learning_resources.md)
- [](extension_point_list.md)
- Section on [exploring module and plugin APIs](plugin_compatibility.md#exploring-module-and-plugin-apis).
- List of [notable](api_notable.md) and [incompatible](api_changes_list.md) API changes.
