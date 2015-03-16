---
title: List and Tree Controls
---

<!--
INITIAL_SOURCE https://confluence.jetbrains.com/display/IDEADEV/IntelliJ+IDEA+List+and+Tree+Controls
-->

# {{ page.title }}

### JBList and Tree

Whenever you would normally use a standard Swing JList component, it's recommended to use the ```JBList``` class as drop-in replacement.
JBList supports the following additional features on top of JList:

*  Drawing a tooltip with complete text of an item if the item doesn't fit into the list box width

*  Drawing a gray text message in the middle of the list box when it contains no items (the text can be customized by calling ```getEmptyText().setText()```)

*  Drawing a busy icon in the top right corner of the list box to indicate that a background operation is being performed (enabled by calling ```setPaintBusy()```)

Similarly, the ```com.intellij.ui.treeStructure.Tree``` class provides a replacement for the standard JTree class. In addition to the features of JBList, it supports wide selection painting (Mac style) and auto-scroll on drag & drop.

### ColoredListCellRenderer and ColoredTreeCellRenderer

When you need to customize the presentation of items in a list box or a tree, it's recommended to use the ```ColoredListCellRenderer``` or ```ColoredTreeCellRenderer``` classes as the cell renderer. These classes allow you to compose the presentation out of multiple text fragments with different attributes (by calling ```append()```) and to set an optional icon for the item (by calling ```setIcon```). The renderer automatically takes care of setting the correct text color for selected items and of many other platform-specific rendering details.

### ListSpeedSearch and TreeSpeedSearch

To facilitate keyboard-based selection of items in a list box or a tree, you can install a speed search handler on it. This can be done simply by calling ```new ListSpeedSeach(list)``` or ```new TreeSpeedSearch(tree)```. If you need to customize the text which is used to locate the element, you can override the ```getElementText()``` method. Alternatively, you can pass a function to convert items to strings (as ```elementTextDelegate``` to the ListSpeedSearch constructor or as ```toString``` to the TreeSpeedSearch constructor).

### ToolbarDecorator

A very common task in plugin development is showing a list or a tree where the user is allowed to add, remove, edit or reorder the items. The implementation of this task is greatly facilitated by the ```ToolbarDecorator``` class. This class provides a toolbar with actions on items and automatically enables drag & drop reordering of items in list boxes if supported by the underlying list model. The position of the toolbar (above or below the list) depends on the platform under which IntelliJ IDEA is running.

To use a toolbar decorator:

*  If you need to support removing and reordering of items in a list box, make sure the model of your list implements the ```EditableModel``` interface. ```CollectionListModel``` is a handy model class that implements this interface.

*  Call ```ToolbarDecorator.createDecorator``` to create a decorator instance.

*  If you need to support adding and/or removing items, call ```setAddAction()``` and/or ```setRemoveAction()```.

*  If you need other buttons in additional to the standard ones, call ```addExtraAction()``` or ```setActionGroup()```.

*  Call ```createPanel()``` and add the component it returns to your panel.

### AbstractTreeBuilder and AbstractTreeStructure

TBD

