<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Structure View

<link-summary>Implementing a custom language file structure view showing the file elements tree.</link-summary>

<tldr>

**Product Help:** [Source file structure](https://www.jetbrains.com/help/idea/viewing-structure-of-a-source-file.html)

</tldr>

The Structure View implementation used for a specific file type can be customized on many levels.
If a custom language plugin provides an implementation of the [`StructureView`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/structureView/StructureView.java) interface, it can completely replace the standard structure view implementation with a custom user interface component.
However, for most languages, this is not necessary, and the standard `StructureView` implementation provided by IntelliJ Platform can be reused.

> To modify an existing Structure View (e.g., add/filter nodes of builtin language support), use [`StructureViewExtension`](%gh-ic%/platform/structure-view-impl/src/com/intellij/ide/structureView/StructureViewExtension.java) registered
> in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.lang.structureViewExtension"/></include>.
>

The starting point for the structure view is the [`PsiStructureViewFactory`](%gh-ic%/platform/editor-ui-api/src/com/intellij/lang/PsiStructureViewFactory.java) interface, which is registered
in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.lang.psiStructureViewFactory"/></include>.

**Examples:**
- [`PsiStructureViewFactory`](%gh-ic%/plugins/properties/src/com/intellij/lang/properties/structureView/PropertiesStructureViewBuilderFactory.java) for [Properties language plugin](%gh-ic%/plugins/properties)
- [Custom Language Support Tutorial: Structure View](structure_view_factory.md)

To reuse the IntelliJ Platform implementation of the `StructureView`, the plugin returns a [`TreeBasedStructureViewBuilder`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/structureView/TreeBasedStructureViewBuilder.java) from its [`PsiStructureViewFactory.getStructureViewBuilder()`](%gh-ic%/platform/editor-ui-api/src/com/intellij/lang/PsiStructureViewFactory.java) method.
As the builder model, the plugin can specify a subclass of [`TextEditorBasedStructureViewModel`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/structureView/TextEditorBasedStructureViewModel.java), and by overriding methods of this subclass, it customizes the structure view for a specific language.

**Example:**
[`StructureViewModel`](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/structureView/PropertiesFileStructureViewModel.java) for [Properties language plugin](%gh-ic%/plugins/properties)

The main method to override is `getRoot()`, which returns the instance of a class implementing the [`StructureViewTreeElement`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/structureView/StructureViewTreeElement.java) interface.
There exists no standard implementation of this interface, so a plugin will need to implement it completely.

The structure view tree is usually built as a partial mirror of the PSI tree.
In the implementation of `StructureViewTreeElement.getChildren()`, the plugin can specify which of the child elements of a specific PSI tree node need to be represented as elements in the structure view.
Another important method is `getPresentation()`, which can be used to customize the text, attributes, and icon used to represent an element in the structure view.

The implementation of `StructureViewTreeElement.getChildren()` needs to be matched by `TextEditorBasedStructureViewModel.getSuitableClasses()`.
The latter method returns an array of `PsiElement`-derived classes, which can be shown as structure view elements.
It is used to select the Structure View item matching the cursor position when the structure view is first opened or when the <control>Autoscroll from source</control> option is enabled.

**Example:**
[`StructureViewTreeElement`](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/editor/PropertyStructureViewElement.java) for [Properties language plugin](%gh-ic%/plugins/properties)
