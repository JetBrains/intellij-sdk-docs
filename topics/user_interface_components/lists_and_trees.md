<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# List and Tree Controls

<link-summary>Creating list and tree components consistent with IntelliJ Platform UI components.</link-summary>

### `JBList` and `Tree`

Whenever you would normally use a standard [Swing `JList`](https://docs.oracle.com/en/java/javase/24/docs/api/java.desktop/javax/swing/JList.html) component,
it's recommended to use the [`JBList`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/JBList.java) class as drop-in replacement.
`JBList` supports the following additional features on top of `JList`:

* Drawing a tooltip with complete text of an item if the item doesn't fit into the list box width.
* Drawing a gray text message in the middle of the list box when it contains no items.
  The text can be customized by calling `getEmptyText().setText()`.
* Drawing a busy icon in the top right corner of the list box to indicate that a background operation is being performed.
  This can be enabled by calling `setPaintBusy()`.

Similarly, the [`Tree`](%gh-ic%/platform/platform-api/src/com/intellij/ui/treeStructure/Tree.java) class provides a replacement for the standard
[Swing `JTree`](https://docs.oracle.com/en/java/javase/24/docs/api/java.desktop/javax/swing/JTree.html) class.
In addition to the features of [`JBList`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/JBList.java), it supports wide selection painting (Mac style) and auto-scroll on drag & drop.

### `ColoredListCellRenderer` and `ColoredTreeCellRenderer`

When you need to customize the presentation of items in a list box or a tree, it's recommended to use the [`ColoredListCellRenderer`](%gh-ic%/platform/platform-api/src/com/intellij/ui/ColoredListCellRenderer.java) or [`ColoredTreeCellRenderer`](%gh-ic%/platform/platform-api/src/com/intellij/ui/ColoredTreeCellRenderer.java) classes as the cell renderer.
These classes allow you to compose the presentation out of multiple text fragments with different attributes by calling `append()` and to set an optional icon for the item by calling `setIcon()`.
The renderer automatically takes care of setting the correct text color for selected items and of many other platform-specific rendering details.

### `ListSpeedSearch` and `TreeSpeedSearch`

To facilitate keyboard-based selection of items in a list box or a tree, you can install a speed search handler on it using the [`ListSpeedSearch`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/ListSpeedSearch.java) and [`TreeSpeedSearch`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/TreeSpeedSearch.java).
This can be done simply by calling `new ListSpeedSearch(list)` or `new TreeSpeedSearch(tree)`.
To customize the text which is used to locate the element, override the `getElementText()` method.
Alternatively, you can pass a function to convert items to strings.
A function needs to be passed as `elementTextDelegate` to the [`ListSpeedSearch`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/ListSpeedSearch.java) constructor or as `toString` to the [`TreeSpeedSearch`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/TreeSpeedSearch.java) constructor.

### `ToolbarDecorator`

A very common task in plugin development is showing a list or a tree where the user is allowed to add, remove, edit or reorder the items.
The implementation of this task is greatly facilitated by the [`ToolbarDecorator`](%gh-ic%/platform/platform-api/src/com/intellij/ui/ToolbarDecorator.java) class.
This class provides a toolbar with actions on items and automatically enables drag & drop reordering of items in list boxes if supported by the underlying list model.
The position of the toolbar above or below the list depends on the platform under which the IDE is running.

To use a toolbar decorator:

* If you need to support removing and reordering of items in a list box, make sure the model of your list implements the [`EditableModel`](%gh-ic%/platform/util/ui/src/com/intellij/util/ui/EditableModel.java) interface.
  [`CollectionListModel`](%gh-ic%/platform/util/ui/src/com/intellij/ui/CollectionListModel.java) is a handy model class that implements this interface.
* Call [`ToolbarDecorator.createDecorator()`](%gh-ic%/platform/platform-api/src/com/intellij/ui/ToolbarDecorator.java) to create a decorator instance.
* If you need to support adding and/or removing items, call `setAddAction()` and/or `setRemoveAction()`.
* If you need other buttons in additional to the standard ones, call `addExtraAction()` or `setActionGroup()`.
* Call `createPanel()` and add the component it returns to your panel.

<!--
### AbstractTreeBuilder and AbstractTreeStructure
TODO link to tutorial
-->
