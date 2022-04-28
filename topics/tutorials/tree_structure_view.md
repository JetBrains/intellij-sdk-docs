[//]: # (title: Modifying Project View Structure)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This tutorial is meant to illustrate how the project tree structure view appearance can be modified programmatically.

This topic describes the [treeStructureProvider](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/tree_structure_provider) sample plugin.

The steps below show how to filter out and keep visible only text files and directories in the Project View Panel.

Other use cases include:
- grouping/nesting related entries, e.g., [GUI Designer](https://www.jetbrains.com/help/idea/gui-designer-basics.html) <path>.form</path> file and related bound class ([`FormMergerTreeStructureProvider`](upsource:///plugins/ui-designer/src/com/intellij/uiDesigner/projectView/FormMergerTreeStructureProvider.java)).
- provide additional "nested" nodes, e.g., contents of custom archive file

## Register Custom TreeStructure Provider

Add new `com.intellij.treeStructureProvider` extension to the [plugin.xml](https://github.com/JetBrains/intellij-sdk-code-samples/blob/main/tree_structure_provider/src/main/resources/META-INF/plugin.xml)

```xml
<extensions defaultExtensionNs="com.intellij">
  <treeStructureProvider implementation="org.intellij.sdk.treeStructureProvider.TextOnlyTreeStructureProvider"/>
</extensions>
```

## Implement Custom TreeStructureProvider

To provide custom Structure View behaviour, implement [`TreeStructureProvider`](upsource:///platform/editor-ui-api/src/com/intellij/ide/projectView/TreeStructureProvider.java) with the nodes filtering logic in `modify()` method.
The example below shows how to filter out all the Project View nodes except those which correspond to text files and directories.

```java
```
{src="tree_structure_provider/src/main/java/org/intellij/sdk/treeStructureProvider/TextOnlyTreeStructureProvider.java"}

## Compile and Run the Plugin

See [Code Samples](code_samples.md) on how to set up and run the plugin.

After going through the steps described above, you can see only text files and directories belonging to a project in the Project View.

![Text Files](text_only.png)
