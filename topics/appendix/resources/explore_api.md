<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Explore the IntelliJ Platform API

<web-summary>
Strategies and tools for exploring the IntelliJ Platform API, navigate extension points, search code, and find examples.
</web-summary>

<link-summary>Strategies and tools for exploring the API.</link-summary>

Sometimes it can be challenging to implement plugin features for the IntelliJ Platform.
This usually happens in two situations:

- You're trying to implement a feature that you've already seen in the IDE, and now you need to find the appropriate extension point or
  class that allows you to hook into the relevant mechanisms.
- You've already started working on a feature, but you're unsure how the different parts of the IntelliJ Platform interact with each other.
  In such situations, it is helpful to be able to navigate the IntelliJ Platform code confidently and to find relevant examples in other plugins.

This guide provides a list of proven strategies that can help you overcome these challenges and gather enough information to continue your work.
Furthermore, the tips below will help build your confidence as you explore the IntelliJ Platform.

<include from="intellij_platform.md" element-id="pluginAlternatives"/>

> See also [](plugin_required_experience.md) about necessary technology knowledge.

## Extension Points (EPs)

### Browse Lists of EPs

The most important resource for discovering new EPs is the extensive list provided directly in the
[IntelliJ Platform SDK Documentation](intellij_platform_extension_point_list.md).
On this page, you will find all the EPs, and each entry includes a link to the online source code and a link to the
[IntelliJ Platform Explorer](https://jb.gg/ipe),
which helps you find examples of this EP in other plugins.
Additionally, dedicated Extension Point Lists specific to IDEs are available under _Product Specific_.

### Use Autocompletion Information

Another way to discover EPs is by using autocompletion or navigating through EP XML files.
When you open a new tag in your <path>[plugin.xml](plugin_configuration_file.md)</path> file (inside the [`<extensions>`](plugin_configuration_file.md#idea-plugin__extensions) block with `defaultExtensionNs="com.intellij"`),
the IDE will automatically suggest possible EPs.

<img src="plugin_xml_completion_suggestion.gif" alt="Using Completion Suggestions" width="706" border-effect="rounded"/>

This is the first step in discovering new features that haven't been explicitly mentioned in the IntelliJ Platform Docs.
Note that in the completion popup, you can call
[quick documentation](https://www.jetbrains.com/help/idea/viewing-reference-information.html#inline-quick-documentation),
which then shows its properties, the implementation class, as well as a direct link to open usage results from
[IntelliJ Platform Explorer](https://jb.gg/ipe).

### Search the IntelliJ Platform Code

Use
[Go to Declaration](https://www.jetbrains.com/help/idea/navigating-through-the-source-code.html#go_to_declaration)
on EPs that are implemented in <path>plugin.xml</path> to navigate to its definition in the XML file.
There you'll find more EPs, and browsing through this list helps you discover features you might not have been aware of.
[Search everywhere](https://www.jetbrains.com/help/idea/searching-everywhere.html)
or
[Go to file](https://www.jetbrains.com/help/idea/discover-intellij-idea.html#navigation-and-search)
helps you search for all files containing extension points.
Use <path>*ExtensionPoints.xml</path> as the search pattern and select the <control>All Places</control> scope.

However, if a bundled or third-party plugin exposes EPs for others to implement, these EPs are defined in the <path>plugin.xml</path> files
of the plugins and not in the <path>*ExtensionPoints.xml</path> files of the IntelliJ Platform.
One such example is the EPs exposed by the Markdown plugin that adds support for custom languages inside fenced code blocks of Markdown files.

### Use Advanced Search

Explore the <path>plugin.xml</path> files of bundled or third party plugins.
If you have the IntelliJ Platform sources available either in your own plugin project or in a separate instance,
you can use
[Structural Search](https://www.jetbrains.com/help/idea/structural-search-and-replace.html)
to find all the files that meet the following criteria:

- The file type is XML
- It contains the tag [`<idea-plugin>`](plugin_configuration_file.md#idea-plugin)
- The file is in the scope <control>Project and Libraries</control>

![Search Structurally for plugin.xml](search_structurally_for_plugin_xml.png)

The search results will contain many plugin XML files.
To find specific implementations of EPs in third-party plugins, use the IntelliJ Platform Explorer ([](explore_api.md#search-the-intellij-platform-explorer)).
Inspecting the <path>plugin.xml</path> files of other plugins not only helps you discover new features but also shows how things like menu entries or
notification groups can be defined in the XML file.

## Navigating the IntelliJ Platform Source Code

The following tips will help you navigate through the IntelliJ Platform source code if you already have an idea of what you're looking for.
It's important that you're familiar with
[navigating](https://www.jetbrains.com/help/idea/reference-keymap-win-default.html#navigate_from_symbols) and
[searching](https://www.jetbrains.com/help/idea/reference-keymap-win-default.html#find_everything)
source code, as well as other basic features of IntelliJ IDEA.

Many developers keep the
[IntelliJ Community source code](%gh-ic%/README.md)
open in a separate IDE project while working on their plugin.
Others search the source code of the IntelliJ Platform that is attached by default when using a [Gradle](creating_plugin_project.md)-based project.
While both methods work, it should be noted that developing plugins without inspecting the IntelliJ Platform code is nearly impossible,
and all the tips below assume having the sources available.

### Find Example Implementations

When working with interfaces or abstract classes of EPs, use IntelliJ IDEA's
[Go to Implementation](https://www.jetbrains.com/help/idea/navigating-through-the-source-code.html#go_to_implementation) or
[Find Usages](https://www.jetbrains.com/help/idea/find-usages-dialog.html)
feature to discover examples of how they are used in the IntelliJ Platform.

### Look for Particular Class Names

Access to many features is provided through the `Manager` and `Service` classes, such as:

- `com.intellij.openapi.application.ApplicationManager`
- `com.intellij.psi.PsiManager`

Therefore, it can be helpful to search for classes that match the pattern `com.intellij.*Manager` and look through the list of results.
Note that not all of these classes have the `com.intellij` prefix, and also that you can
[define custom scopes](https://www.jetbrains.com/help/idea/configuring-scopes-and-file-colors.html)
to limit your searches, for example, to only <path>idea-xxx.jar</path> files.

### Inspect the Contents of Packages

If you open an EP's interface or abstract class, it is always helpful to inspect the contents of its package.
For instance, the interface of the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.sdkType"/></include> lives in the `com.intellij.openapi.projectRoots` package.
Inspecting the contents of this package shows many related classes that will be useful if you are implementing this feature.

### Search for Symbol Names

It is sometimes helpful to search directly for a method, class, and class member if you can guess a part of its name.
You can either use
[Search Everything or Go to Symbol](https://www.jetbrains.com/help/idea/reference-keymap-win-default.html#find_everything).
Note that you need to change the search scope to <control>All Places</control> in the search window to find all occurrences of symbols.

### Search by UI Text

If you want to implement a functionality that is similar to an existing IDE feature, but you can't guess the name of the extension point or implementation class, the underlying implementation can be found by the texts displayed in the UI.

* Use the displayed text or its part as the [target for a search](https://www.jetbrains.com/help/idea/finding-and-replacing-text-in-project.html) within the IntelliJ Community project.
  * If the text is localized, this will identify a bundle file there the text is defined. Copy the key from the bundle file identified by the search.
  * Use the key text as the target for a search within the IntelliJ Community project.
    This search locates the implementation or related class, or [plugin configuration file](plugin_configuration_file.md) that uses the text key in an [extension](plugin_extensions.md) declaration.
  * If the key is found in the extension declaration in the <path>plugin.xml</path> file, find the implementing class attribute value (in most cases it is `implementationClass`) and
    [navigate to a declaration](https://www.jetbrains.com/help/rider/Navigation_and_Search__Go_to_Declaration.html#74fa64b7),
    or use attribute value as the
    [target of a class search](https://www.jetbrains.com/help/idea/searching-everywhere.html#Searching_Everywhere.xml)
    in the IntelliJ Community codebase to find the implementation.
* If the text is not localized, the search will most probably find the desired implementation or related class.
  In this case, search for the found method/class usages and repeat this until the actual implementation class is found.

### Refrain from Using Internal Classes

As a general remark, the use of implementation classes is strongly discouraged (i.e., classes ending with `Impl` in their name,
located under `impl` package, or included in <path>*-impl.jar</path>).

API annotated with
[`@ApiStatus.Internal`](%gh-java-annotations%/common/src/main/java/org/jetbrains/annotations/ApiStatus.java)
must not be used, see [](api_internal.md) for more details and replacements.

## Tools and References

### Use Internal Mode and PsiViewer
{id="internalMode"}

When developing plugins, always enable the [internal mode](enabling_internal.md) in IntelliJ IDEA.
This provides access to a suite of tools to help you develop, debug, and test IntelliJ Platform plugins.

One of its most helpful features is the [UI Inspector](internal_ui_inspector.md),
which lets you investigate all parts of the UI of every IntelliJ-based IDE by simply clicking on them.
Equally important is the <ui-path>Tools | Internal Actions | UI Debugger</ui-path> tool.
It will display all actions that are run by the IDE when you interact with UI elements, for example, by clicking a button.

Finally, the internal mode provides the <ui-path>Tools | View PSI Structure…</ui-path> and <ui-path>Tools | View PSI Structure of Current File…</ui-path> actions,
which allow you to analyze the [PSI tree](psi.md), please see [documentation](https://www.jetbrains.com/help/idea/psi-viewer.html).
The [PsiViewer plugin](https://plugins.jetbrains.com/plugin/227-psiviewer) is a separate plugin with similar capabilities for inspecting PSI trees,
and it comes with a dedicated tool window that displays information on the fly.
However, it does not display information about [stubs](stub_indexes.md) or [formatting models](code_formatting.md).

### Search the IntelliJ Platform Explorer

The [IntelliJ Platform Explorer](https://jb.gg/ipe)
is a search tool for browsing [Extension Points](plugin_extensions.md) (EP) and [Listeners](plugin_listeners.md) inside existing implementations of all open-source IntelliJ Platform plugins published on [JetBrains Marketplace](https://plugins.jetbrains.com).
You can navigate directly to the source files to find inspiration when implementing your own extensions and listeners for IntelliJ-based IDEs.

### Browse Available References

The IntelliJ Platform SDK Documentation should always be the first resource you check for information.
Here is a condensed list you can use for further reference:

- [](useful_links.md)
- [](learning_resources.md)
- [](intellij_platform_extension_point_list.md)
- Section on [exploring module and plugin APIs](plugin_compatibility.md#exploring-module-and-plugin-apis).
- List of [notable](api_notable.md) and [incompatible](api_changes_list.md) API changes.

## Debugger Entry Points

The following techniques allow finding code responsible for specific features by adding breakpoints in places which are entry points to common IDE features.

> Make sure the sandbox IDE is run in the debug mode.

### Finding an intention action, quick fix, or other code modifying documents

<procedure>

1. Add a breakpoint at the beginning of [`DocumentImpl.changedUpdate()`](%gh-ic%/platform/core-impl/src/com/intellij/openapi/editor/impl/DocumentImpl.java).
2. In the sandbox IDE instance, invoke the intention, quick fix, or other feature modifying code.
3. The execution suspends at the breakpoint.
4. In the stacktrace, find the responsible class.

> This breakpoint will work in cases whenever a feature modifies a file text (not only direct changes in a document, but via PSI, actions, etc.).

</procedure>

### Finding code responsible for highlighting something in the editor

<procedure>

1. Add a breakpoint at the beginning of [`HighlightInfoHolder.add()`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/impl/analysis/HighlightInfoHolder.java).
2. In the sandbox IDE instance, modify a document in the editor, for example by adding a space next to the highlighted element.
3. The execution suspends at the breakpoint.
   > Depending on the number of added infos, adding a condition to the breakpoint may narrow down the debugger suspensions.
   > For example, add a condition for a description, severity, or other properties.
4. In the stacktrace, find the responsible class like inspection, annotator, highlighting pass, or other (usually its name is self-explanatory).

</procedure>

### Navigating to files in the editor from another editor, console, and other places

<procedure>

1. Add a breakpoint at the beginning of [`OpenFileDescriptor(Project, VirtualFile, CodeInsightContext, int, int, int, boolean)`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/OpenFileDescriptor.java)` (the constructor with statements initializing the class fields).
2. In the sandbox IDE instance, invoke the navigation feature.
3. The execution suspends at the breakpoint.
4. In the stacktrace, find the call responsible for triggering the navigation.

</procedure>

### Catching processes executed from the IDE

<procedure>

1. Add a breakpoint at the beginning of [`GeneralCommandLine(List<String>)`](%gh-ic%/platform/platform-util-io/src/com/intellij/execution/configurations/GeneralCommandLine.java) (constructor receiving the `command` list).
2. In the sandbox IDE instance, run a process.
3. The execution suspends at the breakpoint.
   > Depending on the executed process, there may be more processes involved, for example, executing a main Java method may involve running a code compilation process before executing the method.
   > The required process can be determined by checking the `command` list.
4. Depending on the needs, in the stacktrace find code responsible for building the process, setting process arguments, or other.

</procedure>

### Finding code responsible for opening a dialog

<procedure>

1. In the sandbox IDE instance, open a dialog.
2. Go back to the IDE and pause the program execution.
3. In the <control>Debugger</control> tool window, go to the <control>Threads & Variables</control> tab.
4. In the thread combo box (over the stacktrace), select the **AWT EventQueue** thread (filter the list by "EvenQueue").
5. In the stacktrace find code responsible for triggering the dialog.

</procedure>

### Finding code responsible for showing a popup

<procedure>

1. Add breakpoints at the beginning of [`LightweightHint`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/LightweightHint.java) and [`ActionPopupMenuImpl`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/ActionPopupMenuImpl.java) classes' constructors.
2. In the sandbox IDE instance, invoke the popup.
3. The execution suspends at the breakpoint.
4. In the stacktrace, find the responsible class.

</procedure>
