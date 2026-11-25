<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# DOM-Based GUI

<primary-label ref="Deprecated"/>

<link-summary>Building Swing forms and file editors bound to DOM models.</link-summary>

> This API is unmaintained and will likely be removed in future versions.
>
{style="warning"}

The DOM UI API connects Swing components to [DOM models](defining_dom_model.md) for editing XML files in forms and custom [file editors](editors.md).
The reader is expected to be familiar with Swing.

## Forms

DOM-based forms support two operations: loading data from XML into the UI (_resetting_) and saving UI data to XML (_committing_).
[`Committable`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/Committable.java) declares the corresponding methods: `reset()` and `commit()`.
To structure forms into smaller parts, use [`CompositeCommittable`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/CompositeCommittable.java), which implements the Composite pattern.
The IDE invokes `commit()` and `reset()` automatically on editor tab switch or undo.
So it is enough to organize the whole Swing structure as a tree of `CompositeCommittable` objects.

DOM controls are special `Committable` implementations.
All of them implement [`DomUIControl`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/DomUIControl.java).
They are not Swing components, but connectors between the DOM model and Swing components.
The DOM element is usually specified in the control's constructor.
The Swing component can be obtained in two ways:

* Ask the DOM control to create it.
* Call the control's `bind()` method to bind it to an existing Swing component of a matching type (depending on the type of the edited value).
  This approach is convenient for forms created, for example, in the IntelliJ IDEA GUI Designer.

After that, the Swing components are synchronized with DOM and highlight errors reported by [`DomElementsAnnotator`](dom_highlighting_and_resolving.md#highlighting-annotations).

To perform actions after a particular DOM control is committed (for example, to enable or disable some components or change their values),
call the control's `addCommitListener()` method and override [`CommitListener.afterCommit()`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/CommitListener.java).
This method is invoked inside the same write action as `commit()`, so any XML changes made in it are merged with the commit in the undo queue.

## Simple Controls

Simple controls edit [`GenericDomValue`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/GenericDomValue.java) values: text, class names, enums, and booleans.
They take a [`DomWrapper`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/DomWrapper.java) object as a constructor parameter, which sets and gets values in the DOM model.

There are three main `DomWrapper` implementations:

* [`DomFixedWrapper<T>`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/DomFixedWrapper.java) redirects calls to `GenericDomValue<T>`.
* [`DomStringWrapper`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/DomStringWrapper.java) redirects calls to the string accessors of `GenericDomValue`.
* [`DomCollectionWrapper`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/DomCollectionWrapper.java) gets and sets values of the first element of the given `GenericDomValue` collection.

Some controls that contain a text field take an additional boolean constructor parameter, `commitOnEveryChange`.
Use it only in small dialogs, as committing on every change slows down the IDE significantly.

Most often, these controls are created by [`DomUIFactory.createControl(GenericDomValue)`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/DomUIFactory.java).
This method chooses the control to create using [DOM reflection](working_with_dom_elements.md#dom-reflection).
The following sections describe the simple controls for cases when they need to be created directly.

### `BooleanControl`

[`BooleanControl`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/BooleanControl.java) edits boolean values.
The control is bound to `JCheckBox`.

![BooleanControl](booleancontrol.gif)

### `ComboControl`

[`ComboControl`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/ComboControl.java) is bound to a non-editable `JComboBox`, so it allows choosing a value from a limited set.
The set can be defined by an enum or by a `Factory<List<String>>` passed to the constructor, which can return any values (for example, a list of database names).
By default, invalid values (specified in XML, but missing in the list provided to the control) are displayed in red.
As combo boxes often use a custom `CellRenderer`, the control provides the `isValidValue(String)` method.
If it returns `false` for the rendered value, highlight the value in the custom renderer, or delegate to the default renderer.

![ComboControl](combocontrol.gif)

### `BooleanEnumControl`

If there are only two alternatives, a checkbox can be more convenient than a combo box.
[`BooleanEnumControl`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/BooleanEnumControl.java) is bound to a checkbox, but instead of "true" and "false", it edits any two `String` values or two enum constants.
For enums, the `invertedOrder` parameter specifies which constant corresponds to the checked state.
By default, `invertedOrder` is `false`, so the first constant corresponds to the unchecked state, and the second one to the checked state.
If the parameter is `true`, the states are swapped.

## Editor-Based Controls

Editor-based controls use the IntelliJ Platform's [`Editor`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/Editor.java) instead of the standard `JTextField`.
As `Editor` can't be instantiated directly through the Open API, these controls are bound to special `JPanel` inheritors,
and their `bind()` method adds the necessary content to the panels.

### `TextControl`

[`TextControl`](%gh-ic%/xml/dom-impl/src/com/intellij/util/xml/ui/TextControl.java) edits simple string values.
The control is bound to a [`TextPanel`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/TextPanel.java) component.
If the control is bound to the [`MultiLineTextPanel`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/MultiLineTextPanel.java) inheritor, it displays a large editor.
If there is no space for a large editor, bind the control to a [`BigTextPanel`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/BigTextPanel.java).
It displays a text editor and a browse button that opens a dialog with a large editor for longer strings.

### `PsiClassControl`

[`PsiClassControl`](%gh-ic%/java/xml.dom.impl/src/com/intellij/util/xml/ui/PsiClassControl.java) is a one-line editor with a browse button that opens the standard class selection dialog.
The control accepts class names only.
It is bound to [`PsiClassPanel`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/PsiClassPanel.java).

![PsiClassControl](psiclasscontrol.gif)

### `PsiTypeControl`

[`PsiTypeControl`](%gh-ic%/java/xml.dom.impl/src/com/intellij/util/xml/ui/PsiTypeControl.java) is similar to `PsiClassControl`, but also accepts Java primitive types and arrays.
It is bound to [`PsiTypePanel`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/PsiTypePanel.java).

## Collection Control

[`DomCollectionControl<T>`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/DomCollectionControl.java) is a table where each row represents one collection child, and `T` is the collection element type.
It requires the [`DomElement`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomElement.java) that is the parent of the collection, a description of the collection (sub-tag name or a [`DomCollectionChildDescription`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/reflect/DomCollectionChildDescription.java) from DOM reflection), and a [`ColumnInfo`](%gh-ic%/platform/platform-api/src/com/intellij/util/ui/ColumnInfo.java) array.
Pass them to the constructor, or override `createColumnInfos()` in a `DomCollectionControl` inheritor.

`ColumnInfo<Item, Aspect>` is an object-oriented way of working with the table model.
`Item` is the type of collection elements, and `Aspect` is the type of the column value: `String`, [`PsiClass`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiClass.java), `Boolean`, etc.
A column info provides the column name, column class, value reading (`Aspect valueOf(Item)`), value writing (`setValue(Item item, Aspect aspect)`),
cell renderer (`getRenderer(Item)`), cell editability (`isCellEditable(Item)`), cell editor (`getEditor(Item)`), etc.

Many column infos are predefined, so creating a new one is rarely necessary.

If collection children are `GenericDomValue` objects, it is usually convenient to edit them directly in the table.
For this, use [`StringColumnInfo`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/StringColumnInfo.java), [`BooleanColumnInfo`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/BooleanColumnInfo.java), or the more generic [`GenericValueColumnInfo`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/GenericValueColumnInfo.java).
However, such collections are rare.

More commonly, a collection element is more complex and has several `GenericDomValue` children.
In this case, create a column for each child using [`ChildGenericValueColumnInfo<T>`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/ChildGenericValueColumnInfo.java).
It requires a [`DomFixedChildDescription`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/reflect/DomFixedChildDescription.java) (from DOM reflection), a renderer, and an editor.

The main renderer options are `DefaultTableCellRenderer` and the IntelliJ Platform's [`BooleanTableCellRenderer`](%gh-ic%/platform/core-ui/src/ui/BooleanTableCellRenderer.java).
Editors closely resemble simple DOM controls: [`BooleanTableCellEditor`](%gh-ic%/platform/util/ui/src/com/intellij/ui/BooleanTableCellEditor.java), `DefaultCellEditor(JTextField)`, [`ComboTableCellEditor`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/ComboTableCellEditor.java), etc.
`DomUIFactory.createCellEditor()` creates the appropriate editor automatically (including the editor for `PsiClass`).

The collection control is bound to [`DomTableView`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/DomTableView.java).
It has a toolbar with the <control>Add</control> and <control>Delete</control> buttons.
To customize the toolbar location, override `DomTableView.getToolbarPosition()`.
Optionally, provide custom addition actions in `DomCollectionControl.createAdditionActions()` (extending `ControlAddAction` is recommended).
If there is only one addition action, the <control>Add</control> button invokes it; if there are several, the button displays a popup menu.
To change the removal behavior, override the `DomCollectionControl.doRemove(List<T>)` method.

If `DomCollectionControl.isEditable()` returns `true`, the toolbar also has the <control>Edit</control> button.
To implement its behavior, override `DomCollectionControl.doEdit(T)`.
The toolbar also has the <control>Help</control> button if a non-null `helpId` parameter is passed to the `DomTableView` constructor.

If the collection is empty, `DomTableView` can display a text returned by `DomTableView.getEmptyPaneText()` instead of an empty table.

To add a custom popup menu to the control, call `DomTableView.installPopup()` after construction, and pass a [`DefaultActionGroup`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/actionSystem/DefaultActionGroup.java) with the popup actions.

Tables support single or multiple (default) row selection.
To change this behavior, override `DomTableView.allowMultipleRowsSelection()`.

![CollectionControl](collectioncontrol.gif)

## UI Organization

To create a DOM-based UI form, extend
[`BasicDomElementComponent`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/BasicDomElementComponent.java),
passing a DOM element to its constructor.
Then bind an IntelliJ IDEA GUI Designer form to the subclass and design the form there.
Ensure that the components bound to DOM controls have the right types.
Finally, create DOM controls in the class constructor and bind them to the components.

Alternatively, DOM can create controls for the `GenericDomValue` children of the `DomElement` and bind them automatically.
Name the component fields after the getters of the element's children, optionally with the `my` prefix, and call `bindProperties()` in the constructor.
Consider the following DOM interface:

```java
public interface Converter extends DomElement {
  GenericDomValue<String> getConverterId();
  GenericDomValue<PsiClass> getConverterClass();
}
```

In this case, the UI form class can look like this:

```java
public class ConverterComponent extends BasicDomElementComponent<Converter> {
  private JPanel myRootPane;
  private TextPanel myConverterId;
  private PsiClassPanel myConverterClass;

  public ConverterComponent(Converter domElement) {
    super(domElement);
    bindProperties();
  }
}
```

All fields in this class are bound to the controls in the GUI form.

To provide a custom file editor that supports binding and undo,
implement [`FileEditorProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorProvider.java)
by extending [`PerspectiveFileEditorProvider`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/PerspectiveFileEditorProvider.java).
In the provider, create a [`DomFileEditor`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/DomFileEditor.java) instance and pass a `BasicDomElementComponent` to it.
To create an editor with a caption at the top, like in the EJB and JSF editors, use the static `DomFileEditor.createDomFileEditor()` method.
`DomFileEditor` listens to all changes in the document corresponding to the given DOM element and refreshes the component on undo.
To listen to changes in other documents, use the `addWatchedDocument()`, `removeWatchedDocument()`, `addWatchedElement()`, and `removeWatchedElement()` methods of `DomFileEditor`.
