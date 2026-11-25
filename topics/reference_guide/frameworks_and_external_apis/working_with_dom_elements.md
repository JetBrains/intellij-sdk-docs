<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Working with DOM Elements

<link-summary>Accessing the XML PSI, structure, validity, and other runtime aspects of DOM elements, and combining several DOM files into one model.</link-summary>

This page describes the most useful methods of [`DomElement`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomElement.java) and [`DomManager`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomManager.java) for working with a [defined DOM model](defining_dom_model.md) at runtime.

## PSI Connection

DOM is tightly connected to XML PSI.
`DomElement.getXmlTag()` returns the underlying [`XmlTag`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/xml/XmlTag.java) instance, which can be `null` for fixed-number children and attributes.
[`GenericAttributeValue`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/GenericAttributeValue.java) also provides `getXmlAttribute()`, and `getXmlElement()` returns the underlying XML element in the general case.
To get the DOM element for an XML PSI element, use `DomManager.getDomElement()`.

To create the underlying XML element for a DOM element that has none, call `ensureTagExists()`.
To delete it, call `undefine()`, which always deletes the underlying XML element (tag or attribute).
If the element was a collection child, the element and its entire subtree become invalid.

## Tree Structure

`getParent()` returns the element's parent in the DOM tree.

`<T extends DomElement> T getParentOfType(Class<T> requiredClass, boolean strict)` returns the nearest ancestor of the given class.
If `strict` is `false` and the current DOM element is an instance of `requiredClass`, the method returns the element itself.

`getRoot()` returns the [`DomFileElement`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomFileElement.java), which is the root of every DOM tree.

## Validity

An element becomes invalid if it is deleted explicitly or due to external PSI changes.
Fixed-number children and attributes stay valid as long as possible, regardless of XML changes.
They become invalid only if their collection child ancestor is deleted.

Newly created DOM elements are always valid, so their `isValid()` methods return `true`.

Element validity is essential, as no methods except `isValid()` can be invoked on invalid elements.

## DOM Reflection

DOM provides reflection, called _generic info_, which allows accessing children by tag names instead of calling getters.
See [`DomGenericInfo`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/reflect/DomGenericInfo.java) and the `getGenericInfo()` methods in `DomElement` and `DomManager` for more information.
`DomElement.getXmlElementName()` returns the name of the corresponding tag or attribute.

## Presentation

`DomElement.getPresentation()` returns an [`ElementPresentation`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ElementPresentation.java) instance,
which provides the presentable element type, name, and optionally an icon.
Presentations are obtained from presentation factory objects,
which must be registered in [`ElementPresentationManager`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ElementPresentationManager.java) as early as possible.
It is possible to specify the type name and icon for all elements of a class, as well as ways of getting the type name, icon, and presentable name for particular objects.
If not specified, the presentable name is taken from the object's method annotated with [`@NameValue`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/NameValue.java) that returns `String` or [`GenericValue`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/GenericValue.java).
If there is no such method, the presentable name is `null`.
For `DomElement`, the presentable name can also be obtained via `DomElement.getGenericInfo().getElementName()`.

## Events

To receive notifications about every change in the DOM model, add a [`DomEventListener`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomEventListener.java) to `DomManager`.
DOM supports the following events: tag value changed, element defined/undefined/changed, and collection child added/removed.

## Visitor

DOM provides a visitor, [`DomElementVisitor`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomElementVisitor.java), which is accepted by the `DomElement.accept()` and `acceptChildren()` methods.
`DomElementVisitor` declares only one method: `visitDomElement(DomElement)`.
It doesn't declare `visitT(T)` methods for specific types, as only the plugin knows its DOM interfaces.
Instead, declare such methods in the visitor implementation, and DOM calls them.
The methods can also be named `visit()`, with only the parameter type specified.
For example, a visitor for the `Foo` and `Bar` DOM element classes can look like this:

```java
class MyVisitor implements DomElementVisitor {
  void visitDomElement(DomElement element) {}
  void visitFoo(Foo foo) {}
  void visitBar(Bar bar) {}
}
```

## Mock and Stable Elements

DOM elements do not have to be tied to a physical file.
`DomManager.createMockElement()` creates a virtual element of the given class for the given module.
The element can be physical or not.
For a physical element, DOM creates a mock document, so <control>Undo</control> is available if the document is passed to the file editor.

`DomElement.copyFrom()` copies data from one `DomElement` to another by replacing its XML tags, so all old data is lost.
However, the element's fixed-number children don't become invalid: they only get new tag values, attribute values, etc.

Combining `createMockElement()` and `copyFrom()` is useful for editing element contents in dialogs:
create a mock copy of an element, edit it in the dialog, and if the user confirms the changes, copy it back to the main model.
`DomElement.createMockCopy()` is a shortcut for creating such a copy.

The IntelliJ Platform's XML parser is incremental: changes in the text don't cause the whole file to be reparsed.
However, DOM elements can still become invalid as a result of manual edits of the XML file, even outside these elements.
If a file editor depends on such an element, its tab may be closed, which disrupts the user.
For example, the user opens an editor for an entity bean named "SomeEntity", then changes the tag name in XML from `entity` to `session` and back to `entity`.
No DOM element survives such a change, but the editor should stay open.
To handle this, use `DomManager.createStableValue(Factory factory)`.
It creates a DOM element that delegates all calls to a real element returned by the factory.
When the real element becomes invalid, DOM calls the factory again, and if it returns a valid element, the element becomes the new delegate.
In the EJB example, the factory would look up the entity bean named "SomeEntity" again.

Stable DOM elements also implement [`StableElement`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/StableElement.java), which provides the following methods:

* `DomElement getWrappedElement()` - returns the current element to which all method calls are delegated.
* `void invalidate()` - makes the wrapped element invalid.
  The next method call causes the factory to create a new delegate.
* `void revalidate()` - calls the factory, and if it returns a new element (different from the currently wrapped one), invalidates the old element and adopts the new one.

## Models Across Multiple Files

Many frameworks require a set of XML configuration files (_fileset_) to work as one model, so resolving and navigation work across all related DOM files.
Depending on the plugin, filesets can be provided implicitly (based on the existing framework setup in the project) or configured by the user (usually via a dedicated [`Facet`](%gh-ic%/platform/lang-core/src/com/intellij/facet/Facet.java)).

Extend [`DomModelFactory`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/model/impl/DomModelFactory.java)
(or [`BaseDomModelFactory`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/model/impl/BaseDomModelFactory.java) for non-[`Module`](%gh-ic%/platform/core-api/src/com/intellij/openapi/module/Module.java) scope)
and provide an implementation of [`DomModel`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/model/DomModel.java).
Usually, the `DomModel` implementation also provides search and utility methods.
