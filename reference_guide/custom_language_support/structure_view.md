---
title: Structure View
---

The Structure View implementation used for a specific file type can be customized on many levels.
If a custom language plugin provides an implementation of the
[StructureView](upsource:///platform/structure-view-api/src/com/intellij/ide/structureView/StructureView.java)
interface, it can completely replace the standard structure view implementation with a custom user interface component.
However, for most languages this is not necessary, and the standard
[StructureView](upsource:///platform/structure-view-api/src/com/intellij/ide/structureView/StructureView.java)
implementation provided by *IntelliJ Platform* can be reused.

The starting point for the structure view is the
[PsiStructureViewFactory](upsource:///platform/structure-view-api/src/com/intellij/lang/PsiStructureViewFactory.java)
interface, which is registered in the `com.intellij.lang.psiStructureViewFactory` extension point.

**Example:**
[PsiStructureViewFactory](upsource:///plugins/properties/src/com/intellij/lang/properties/structureView/PropertiesStructureViewBuilderFactory.java)
for
[Properties language plugin](upsource:///plugins/properties)


To reuse the *IntelliJ Platform* implementation of the
[StructureView](upsource:///platform/structure-view-api/src/com/intellij/ide/structureView/StructureView.java),
the plugin returns a
[TreeBasedStructureViewBuilder](upsource:///platform/structure-view-api/src/com/intellij/ide/structureView/TreeBasedStructureViewBuilder.java)
from its
[PsiStructureViewFactory.getStructureViewBuilder()](upsource:///platform/structure-view-api/src/com/intellij/lang/PsiStructureViewFactory.java)<!--#L35-->
method.
As the model for the builder, the plugin can specify a subclass of
[TextEditorBasedStructureViewModel](upsource:///platform/structure-view-api/src/com/intellij/ide/structureView/TextEditorBasedStructureViewModel.java),
and by overriding methods of this subclass it customizes the structure view for a specific language.

**Example**:
[StructureViewModel](upsource:///plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/structureView/PropertiesFileStructureViewModel.java)
for
[Properties language plugin](upsource:///plugins/properties)


The main method to override is `getRoot()`, which returns the instance of a class implementing the
[StructureViewTreeElement](upsource:///platform/structure-view-api/src/com/intellij/ide/structureView/StructureViewTreeElement.java)
interface.
There exists no  standard implementation of this interface, so a plugin will need to implement it completely.

The structure view tree is usually built as a partial mirror of the PSI tree.
In the implementation of
`StructureViewTreeElement.getChildren()`,
the plugin can specify which of the child elements of a specific PSI tree node need to be represented as elements in the structure view.
Another important method is `getPresentation()`, which can be used to customize the text, attributes and icon used to represent an element in the structure view.

The implementation of `StructureViewTreeElement.getChildren()` needs to be matched by `TextEditorBasedStructureViewModel.getSuitableClasses()`.
The latter method returns an array of `PsiElement`\-derived classes which can be shown as structure view elements, and is used to select the Structure View item matching the cursor position when the structure view is first opened or when the `Autoscroll from source` option is used.

**Example:**
[StructureViewElement](upsource:///plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/structureView/PropertiesStructureViewElement.java)
for
[Properties language plugin](upsource:///plugins/properties/)
