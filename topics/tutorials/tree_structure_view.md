<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Modifying Project View Structure

<link-summary>Modifying Project View Tutorial.</link-summary>

This tutorial is meant to illustrate how the project tree structure view appearance can be modified programmatically.

This topic describes the [treeStructureProvider](%gh-sdk-samples-master%/tree_structure_provider) sample plugin.

The steps below show how to filter out and keep visible only text files and directories in the Project View Panel.

Other use cases include:
- grouping/nesting related entries, e.g., [GUI Designer](https://www.jetbrains.com/help/idea/gui-designer-basics.html) <path>.form</path> file and related bound class ([`FormMergerTreeStructureProvider`](%gh-ic%/plugins/ui-designer/src/com/intellij/uiDesigner/projectView/FormMergerTreeStructureProvider.java)).
- provide additional "nested" nodes, e.g., contents of custom archive file

## Implement Custom `TreeStructureProvider`

To provide custom Structure View behaviour, implement [`TreeStructureProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/projectView/TreeStructureProvider.java) with the nodes filtering logic in `modify()` method.
If access to indexes is not required, it can be marked [dumb aware](indexing_and_psi_stubs.md#DumbAwareAPI).

The example below shows how to filter out all the Project View nodes except those which correspond to text files and directories.

```java
```
{src="tree_structure_provider/src/main/java/org/intellij/sdk/treeStructureProvider/TextOnlyTreeStructureProvider.java" include-symbol="TextOnlyTreeStructureProvider"}

## Register Custom `TreeStructureProvider`

Register the implementation in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.treeStructureProvider"/></include> to the [plugin.xml](%gh-sdk-samples-master%/tree_structure_provider/src/main/resources/META-INF/plugin.xml)

```xml
<extensions defaultExtensionNs="com.intellij">
  <treeStructureProvider implementation="org.intellij.sdk.treeStructureProvider.TextOnlyTreeStructureProvider"/>
</extensions>
```

## Compile and Run the Plugin

See [Code Samples](code_samples.md) on how to set up and run the plugin.

After going through the steps described above, you can see only text files and directories belonging to a project in the Project View.

![Text Files](text_only.png)
