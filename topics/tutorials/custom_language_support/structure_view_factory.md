<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 14. Structure View Factory

<link-summary>Sample implementation of a structure view factory adding a Simple language file structure in the Structure tool window.</link-summary>

<tldr>

**Reference**: [](structure_view.md)

**Code**: [`SimpleStructureViewFactory`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleStructureViewFactory.java),
[`SimpleStructureViewModel`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleStructureViewModel.java),
[`SimpleStructureViewElement`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleStructureViewElement.java)

</tldr>

<include from="language_and_filetype.md" element-id="custom_language_tutorial_header"></include>

The structure view can be customized for a specific file type.
Creating a structure view factory allows showing the structure of any file in the <control>Structure</control> tool window or by invoking <ui-path>Navigate | File Structure</ui-path> for easy navigation between items in the current editor.

## Define a Structure View Factory

The [`SimpleStructureViewFactory`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleStructureViewFactory.java)
implements [`PsiStructureViewFactory`](%gh-ic%/platform/editor-ui-api/src/com/intellij/lang/PsiStructureViewFactory.java).
The `getStructureViewBuilder()` implementation reuses the IntelliJ Platform class [`TreeBasedStructureViewBuilder`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/structureView/TreeBasedStructureViewBuilder.java).
At this point the project will not compile until `SimpleStructureViewModel` is [implemented below](#define-a-structure-view-model).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleStructureViewFactory.java" include-symbol="SimpleStructureViewFactory"}

## Define a Structure View Model

The [`SimpleStructureViewModel`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleStructureViewModel.java) is created by implementing [`StructureViewModel`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/structureView/StructureViewModel.java), which defines the model for data displayed in the standard structure view.
It also extends [`StructureViewModelBase`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/structureView/StructureViewModelBase.java), an implementation that links the model to a text editor.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleStructureViewModel.java" include-symbol="SimpleStructureViewModel"}

## Define a Structure View Element

The [`SimpleStructureViewElement`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleStructureViewElement.java)
implements [`StructureViewTreeElement`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/structureView/StructureViewTreeElement.java) and [`SortableTreeElement`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/util/treeView/smartTree/SortableTreeElement.java).
The `StructureViewTreeElement` represents an element in the Structure View tree model.
The `SortableTreeElement` represents an item in a smart tree that allows using text other than the presentable text as a key for alphabetic sorting.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleStructureViewElement.java" include-symbol="SimpleStructureViewElement"}

## Register the Structure View Factory

The `SimpleStructureViewFactory` implementation is registered with the IntelliJ Platform in the plugin configuration file using
the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.lang.structureViewExtension"/></include>.

```xml
<extensions defaultExtensionNs="com.intellij">
  <lang.psiStructureViewFactory
      language="Simple"
      implementationClass="org.intellij.sdk.language.SimpleStructureViewFactory"/>
</extensions>
```

## Run the Project

Run the project by using the Gradle [`runIde`](creating_plugin_project.md#running-a-plugin-with-the-runide-gradle-task) task.

Open the <path>test.simple</path> file and open <ui-path>View | Tool Windows | Structure</ui-path>.
The IDE now supports a structure view of the Simple Language:

![Structure View](structure_view.png)
