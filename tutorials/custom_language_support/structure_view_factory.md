---
title: 14. Structure View Factory
---

The [structure view](/reference_guide/custom_language_support/structure_view.md) in the IntelliJ Platform-based IDE can be customized for a specific file type, including Simple Language.
Creating a structure view factory allows showing the structure of any file in a **Structure** tool window for easy navigation between items.

* bullet list
{:toc}

## 14.1. Define a Structure View Factory
The structure view factory implements [`PsiStructureViewFactory`](upsource:///platform/editor-ui-api/src/com/intellij/lang/PsiStructureViewFactory.java).
The `getStructureViewBuilder()` implementation reuses the IntelliJ Platform class [`TreeBasedStructureViewBuilder`](upsource:///platform/editor-ui-api/src/com/intellij/ide/structureView/TreeBasedStructureViewBuilder.java).
At this point the project will not compile until `SimpleStructureViewModel` is [implemented below](#define-a-structure-view-model).
```java
{% include /code_samples/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleStructureViewFactory.java %}
```

## 14.2. Define a Structure View Model
The `SimpleStructureViewModel` is created by implementing [`StructureViewModel`](upsource:///platform/editor-ui-api/src/com/intellij/ide/structureView/StructureViewModel.java), which defines the model for data displayed in the standard structure view. 
It also extends [`StructureViewModelBase`](upsource:///platform/editor-ui-api/src/com/intellij/ide/structureView/StructureViewModelBase.java), an implementation that links the model to a text editor.
```java
{% include /code_samples/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleStructureViewModel.java %}
```

## 14.3. Define a Structure View Element
The `SimpleStructureViewElement` implements [`StructureViewTreeElement`](upsource:///platform/editor-ui-api/src/com/intellij/ide/structureView/StructureViewTreeElement.java) and [`SortableTreeElement`](upsource:///platform/editor-ui-api/src/com/intellij/ide/util/treeView/smartTree/SortableTreeElement.java).
The `StructureViewTreeElement` represents an element in the Structure View tree model.
The `SortableTreeElement` represents an item in a smart tree that allows using text other than the presentable text as a key for alphabetic sorting. 
```java
{% include /code_samples/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleStructureViewElement.java %}
```

## 14.4. Register the Structure View Factory
The `SimpleStructureViewFactory` implementation is registered with the IntelliJ Platform in the plugin configuration file using the `lang.psiStructureViewFactory` extension point.
```xml
  <extensions defaultExtensionNs="com.intellij">
    <lang.psiStructureViewFactory language="Simple" 
            implementationClass="org.intellij.sdk.language.SimpleStructureViewFactory"/>
  </extensions>
```

## 14.5. Run the Project
Rebuild the project, and run `simple_language_plugin` in a Development Instance.
Open the `test.simple` file and choose **View \| Tool Windows \| Structure**.
The IDE now supports a structure view of the Simple Language:

![Structure View](img/structure_view.png){:width="800px"}
