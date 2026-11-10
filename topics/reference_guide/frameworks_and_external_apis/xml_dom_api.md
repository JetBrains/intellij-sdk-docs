<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# XML DOM API

<link-summary>Implementing APIs for accessing XML models.</link-summary>

[//]: # (TODO content: DOM <=> PSI, Go To Symbol, editor gutter icon->DOM)

The _Document Object Model_ (DOM) in the IntelliJ Platform is an API for working with DTD- or Schema-based XML models.
It is intended for plugins that provide framework or server integrations, or a UI for editing XML files.
This page describes working with DOM (reading and writing tag content, attributes, and sub-tags) and connecting a UI to DOM for editing XML.

The reader is expected to be familiar with Java, Swing, the IntelliJ Platform XML PSI
(classes [`XmlTag`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/xml/XmlTag.java), [`XmlFile`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/xml/XmlFile.java), [`XmlTagValue`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/xml/XmlTagValue.java), etc.),
and IntelliJ Platform plugin development basics ([services](plugin_services.md), [file editors](editors.md)).

## XML PSI vs DOM

Working with XML through PSI usually means taking an `XmlFile`, getting its root tag, and then finding the required sub-tag by a path of tag names.
Typing these string names everywhere is tedious and error-prone.
Consider the following XML:

```xml
<root>
  <foo>
    <bar>42</bar>
    <bar>239</bar>
  </foo>
</root>
```

The goal is to read the content of the second `<bar>` element, namely "239".

Chained calls like the following are _not_ correct, because each call may return `null`:

```java
file.getDocument()
    .getRootTag()
    .findFirstSubTag("foo")
    .findSubTags("bar")[1]
    .getValue()
    .getTrimmedText();
```

Code that handles all `null` values looks like this:

```java
XmlFile file = ...;
XmlDocument document = file.getDocument();
if (document != null) {
  XmlTag rootTag = document.getRootTag();
  if (rootTag != null) {
    XmlTag foo = rootTag.findFirstSubTag("foo");
    if (foo != null) {
      XmlTag[] bars = foo.findSubTags("bar");
      if (bars.length > 1) {
        String s = bars[1].getValue().getTrimmedText();
        // do something
      }
    }
  }
}
```

DOM simplifies this code.
It only requires defining interfaces that extend [`DomElement`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomElement.java), for example:

```java
interface Root extends com.intellij.util.xml.DomElement {
  Foo getFoo();
}

interface Foo extends com.intellij.util.xml.DomElement {
  List<Bar> getBars();
}

interface Bar extends com.intellij.util.xml.DomElement {
  String getValue();
}
```

Next, create a [`DomFileDescription`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomFileDescription.java) implementation,
passing the root tag name and the root element interface to its constructor.
Register it in <path>[plugin.xml](plugin_configuration_file.md)</path>
using <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.dom.fileMetaData"/></include> and specify the `rootTagName` and `domVersion`/`stubVersion` attributes.

[`DomManager`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomManager.java) now provides the file element.
The following code gets the "239" value:

```java
DomManager manager = DomManager.getDomManager(project);
Root root = manager.getFileElement(file).getRootElement();
List<Bar> bars = root.getFoo().getBars();
if (bars.size() > 1) {
  String s = bars.get(1).getValue();
  // do something
}
```

A model is often used in several places, and re-creating it each time would be inefficient.
DOM caches the model, so subsequent calls to `DomManager.getFileElement()` return the same instance.
This makes it possible to obtain the root element once and reuse it.

This code is also safe from `NullPointerException`:
DOM guarantees that every method accessing a tag child returns a non-null element, even if the corresponding sub-tag doesn't exist.
Based on the interfaces, DOM generates the code for accessing the correct sub-tags and creating model elements at runtime.
Sub-tag names and element types are derived from method names, return types, and method annotations, if any.
In most cases, annotations can be omitted, as in the example above.
The following sections describe them and other ways of representing XML concepts such as tag content, attributes, and sub-tags.

## Building the Model

### Tag Content

XML PSI refers to tag content as _tag value_, and this page uses the same term.
To read and change a tag value, add a getter and a setter to the interface:

```java
String getValue();
void setValue(String s);
```

`getValue()` and `setValue()` are the default names of tag value accessors.
To use custom method names, annotate the methods with `@TagValue`:

```java
@TagValue
String getTagValue();

@TagValue
void setTagValue(String s);
```

These accessors work with `String` values, as XML tag content is always text.
A model can also operate with integers, booleans, enums, class names (represented as `PsiClass`), or Java types (`PsiType`).
In such cases, change the accessor types to the required ones.

#### Custom Value Types

For other types, annotate the accessor methods with `@Convert` and specify a class extending `Converter<T>`, where `T` is the custom type.
A converter converts values between `String` and `T`.
If a value can't be converted (for example, "foo" to `Integer`), the converter can return `null`.
The converter class must have a no-argument constructor.

For enum types, the default converter searches for enum constants with the names specified in XML.
Sometimes, XML values are not valid Java identifiers.
For example, the CMP version in EJB can be "1.x" or "2.x", which can't be used as enum constant names.
In such cases, let the enum implement `NamedEnum` and return the value matching the XML content from `getValue()`:

```java
enum CmpVersion implements NamedEnum {
  CmpVersion_1_X ("1.x"),
  CmpVersion_2_X ("2.x");

  private final String value;

  CmpVersion(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
```

Besides a value, an XML tag can have attributes and sub-tags.
However, many tags (according to DTD or Schema) contain only a value.
DOM represents such tags with [`GenericDomValue<T>`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/GenericDomValue.java):

```java
interface GenericDomValue<T> {
  T getValue();
  void setValue(T t);

  @TagValue
  String getStringValue();

  @TagValue
  void setStringValue(String s);
}
```

Specify a particular `T` when using this interface.
The `String` accessors are useful, for example, when `T` is [`PsiClass`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiClass.java),
and the UI needs to highlight invalid values:
`getStringValue()` returns the raw string from the XML file, and the converter provides the error message via `getErrorMessage()`.

### Attributes

Attribute values can be read and set, and they can have different types.
DOM represents attributes with [`GenericAttributeValue<T>`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/GenericAttributeValue.java), which extends `GenericDomValue<T>`:

```java
interface GenericAttributeValue<T> extends GenericDomValue<T> {
  XmlAttribute getXmlAttribute();
}
```

For example, an attribute named `some-class` with a value of type `PsiClass` is declared as follows:

```java
@Attribute("some-class")
GenericAttributeValue<PsiClass> getMyAttributeValue();
```

The returned value supports getting and setting the value, resolving the `PsiClass`, getting its `String` representation, etc.
If the method name matches the attribute name according to the name strategy (see below), the annotation can be omitted:

```java
GenericAttributeValue<PsiClass> getSomeClass();
```

[`DomNameStrategy`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomNameStrategy.java) specifies how to convert accessor names without the `get`, `set`, or `is` prefixes to XML element names.
Specify the strategy class in the `@NameStrategy` annotation on a DOM element interface.
All descendants and children of this interface use this strategy.
The default strategy is [`HyphenNameStrategy`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/HyphenNameStrategy.java), which delimits words with hyphens, as in the sample above.
Another common strategy is [`JavaNameStrategy`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/JavaNameStrategy.java), which follows the Java naming convention.
With it, the attribute name in the example would be `someClass`.

If the attribute type is not `PsiClass`, but a custom `T` requiring a converter, annotate the getter with `@Convert`.

The attribute getter never returns `null`, even if the attribute isn't specified in XML.
In this case, its `getValue()`, `getStringValue()`, and `getXmlAttribute()` methods return `null`, but the DOM element exists and is valid.
To remove an existing underlying attribute, call `DomElement.undefine()`:
the XML attribute is deleted, while the `GenericAttributeValue` remains valid.

### Children: Fixed Number

Tags often have at most one sub-tag with a given name,
for example, `<ejb-name>`, `<ejb-class>`, or `<cmp-field>` in tags defining entity EJBs.
To access such children, declare getters with a return type extending `DomElement`:

```java
GenericDomValue<String> getEjbName();
GenericDomValue<String> getEjbClass();
CmpField getCmpField();
```

To designate such children explicitly, use the `@SubTag` annotation.
Its `value` attribute specifies the tag name.
If it is not specified, the name is derived from the method name using the current name strategy.

For some sub-tags, their presence matters rather than their content, for example, `<unchecked>` in EJB method permissions:
if the sub-tag exists, permissions are unchecked; otherwise, they are checked.
Represent such sub-tags with a `GenericDomValue<Boolean>` child.
By default, its `getValue()` returns `true` if the tag value is "true", `false` if it is "false", and `null` otherwise.
To change this, specify `indicator = true` in the `@SubTag` annotation.
Then `getValue()` returns `true` if the tag exists, and `false` otherwise.

Some tags contain a fixed number (greater than one) of sub-tags with the same name.
For example, an EJB relation has two roles, each represented by an `<ejb-relationship-role>` tag.
Representing them as a collection would require checking the number of elements on every access.
Instead, DOM supports declaring a separate getter for each sub-tag, with its index:

```java
@SubTag(value = "ejb-relationship-role", index = 0)
EjbRelationshipRole getEjbRelationshipRole1();

@SubTag(value = "ejb-relationship-role", index = 1)
EjbRelationshipRole getEjbRelationshipRole2();
```

The first method returns the DOM element for the first `<ejb-relationship-role>` sub-tag, and the second method returns the element for the second one.
Such children are called _fixed-number_ children, as DTD or Schema defines a fixed number of sub-tags with the given name.
Most often, this number is 1; for EJB relations, it is 2.
Like attributes, fixed-number children exist regardless of whether the underlying tag exists.
To delete their tags, call `undefine()`.

Converters can be specified for `GenericDomValue` children in the same way as for attributes.

### Children: Collections

DTDs and Schemas often define children with the same tag name and no fixed upper limit of their number.
Accessors for such children return a `Collection` or `List` of a type extending `DomElement`.
To derive the tag name using the name strategy, use the plural form in the method name, for example:

```java
List<Entity> getEntities();
```

To specify the tag name explicitly, use the `@SubTagList` annotation.

Returned collections can't be modified directly.
To delete an element from a collection, call `undefine()` on it.
This removes the tag and makes the element invalid (`DomElement.isValid()` returns `false`).
This behavior differs from fixed-number children and attributes, which remain valid after `undefine()`.
Also, unlike them, collection children always have valid underlying XML tags.

DOM creates all elements internally, so it is not possible to add an existing DOM element to a collection.
Instead, declare methods in the parent element that add a child to the collection:

```java
// adds an element at the given index
Entity addEntity(int index);

// adds an element to the end of the collection
Entity addEntity();
```

Note the singular form of "Entity" in the method names, as these methods add a single element.
The returned element can then be modified: define its value, children, etc.

Another common case is a collection of tags with different names mixed in arbitrary order.
To work with it, define collection getters for all tag names in the mixed collection and an additional getter annotated with `@SubTagsList`:

```java
// <foo> elements
List<Foo> getFoos();

// <bar> elements
List<Bar> getBars();

// all <foo> and <bar> elements
@SubTagsList({ "foo", "bar" })
List<FooBar> getMergedListOfFoosAndBars();
```

The annotation is mandatory, as DOM can't derive several tag names from one method name.

To add elements to a mixed collection, declare an "add" method for each possible tag name:

```java
@SubTagsList(value = { "foo", "bar" }, tagName = "foo")
FooBar addFoo();

@SubTagsList(value = { "foo", "bar" }, tagName = "bar")
FooBar addBar(int index);
```

The `index` parameter in the last example is the index in the merged collection, not in the collection of `<bar>` tags.

### Dynamic Definition

To extend existing DOM models at runtime, implement `com.intellij.util.xml.reflect.DomExtender<T>`.
Register it in the `extenderClass` attribute of <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.dom.extender"/></include>, where `domClass` specifies the extended DOM class `T`.
[`DomExtensionsRegistrar`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/reflect/DomExtensionsRegistrar.java) provides methods to register dynamic attributes and children.

If the contributed elements depend on anything other than plain XML file content (framework version, libraries in the classpath, etc.), return `false` from `DomExtender.supportsStubs()`.

### Namespace Support

Annotate the DOM model with [`Namespace`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/Namespace.java)
and register the namespace key mapping via `DomFileDescription.registerNamespacePolicy()` in `DomFileDescription.initializeFileDescription()`.

### IDE Support

_Plugin DevKit_ supports the following features for working with DOM-related code:

* `DomElement` - provide implicit usages for all DOM-related methods defined in inheriting classes (to suppress the "unused method" warning)
* [`DomElementVisitor`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomElementVisitor.java) - provide implicit usages for all DOM-related visitor methods defined in inheriting classes (to suppress the "unused method" warning)

## Working with the DOM

### Class Choosers

A collection can contain same-named tags that have different structures or even different types in the DTD or Schema.
For example, JSF managed beans can be of three types, all extending `ManagedBean`:

* `MapEntriesBean`, if the `<managed-bean>` tag contains a `<map-entries>` sub-tag
* `ListEntriesBean`, if it contains a `<list-entries>` sub-tag
* `PropertyBean` otherwise

Each element of the list returned by `List<ManagedBean> getManagedBeans()` should implement the specific interface:
`MapEntriesBean`, `ListEntriesBean`, or `PropertyBean`.

To choose the interface that a DOM element implements depending on its tag, extend [`TypeChooser`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/TypeChooser.java):

```java
public abstract class TypeChooser {
  public abstract Type chooseType(XmlTag tag);
  public abstract void distinguishTag(XmlTag tag, Type aClass)
      throws IncorrectOperationException;
  public abstract Type[] getChooserTypes();
}
```

* `chooseType()` chooses the type (most often a class) for the given tag.
* `distinguishTag()` does the reverse: it modifies the tag so that the DOM element created from it later
  (for example, after the user reopens the project) implements the same interface, and no model data is lost.
* `getChooserTypes()` returns all types that `chooseType()` can return.

To register the `TypeChooser`, call `registerTypeChooser()` in the overridden `DomFileDescription.initializeFileDescription()` method.

### Useful Methods of `DomElement` and `DomManager`

#### PSI Connection

DOM is tightly connected to XML PSI.
`getXmlTag()` returns the underlying `XmlTag` instance, which can be `null` for fixed-number children and attributes.
`GenericAttributeValue` also provides `getXmlAttribute()`, and `getXmlElement()` returns the underlying XML element in the general case.
To get the DOM element for an XML PSI element, use `DomManager.getDomElement()`.

To create the underlying XML element for a DOM element that has none, call `ensureTagExists()`.
To delete it, call `undefine()`, which always deletes the underlying XML element (tag or attribute).
If the element was a collection child, the element and its entire subtree become invalid.

#### Tree Structure

`getParent()` returns the element's parent in the DOM tree.

`<T extends DomElement> T getParentOfType(Class<T> requiredClass, boolean strict)` returns the nearest ancestor of the given class.
If `strict` is `false` and the current DOM element is an instance of `requiredClass`, the method returns the element itself.

`getRoot()` returns the `DomFileElement`, which is the root of every DOM tree.

#### Validity

An element becomes invalid if it is deleted explicitly or due to external PSI changes.
Fixed-number children and attributes stay valid as long as possible, regardless of XML changes.
They become invalid only if their collection child ancestor is deleted.

Newly created DOM elements are always valid, so their `isValid()` methods return `true`.

Element validity is essential, as no methods except `isValid()` can be invoked on invalid elements.

#### DOM Reflection

DOM provides reflection, called _generic info_, which allows accessing children by tag names instead of calling getters.
See [`DomGenericInfo`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/reflect/DomGenericInfo.java) and the `getGenericInfo()` methods in `DomElement` and `DomManager` for more information.
`DomElement.getXmlElementName()` returns the name of the corresponding tag or attribute.

#### Presentation

<!-- TODO: using @Presentation -->

`DomElement.getPresentation()` returns an [`ElementPresentation`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ElementPresentation.java) instance,
which provides the presentable element type, name, and optionally an icon.
Presentations are obtained from presentation factory objects,
which must be registered in [`ElementPresentationManager`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ElementPresentationManager.java) as early as possible.
It is possible to specify the type name and icon for all elements of a class, as well as ways of getting the type name, icon, and presentable name for particular objects.
If not specified, the presentable name is taken from the object's method annotated with `@NameValue` that returns `String` or `GenericValue`.
If there is no such method, the presentable name is `null`.
For `DomElement`, the presentable name can also be obtained via `DomElement.getGenericInfo().getElementName()`.

#### Events

To receive notifications about every change in the DOM model, add a `DomEventListener` to `DomManager`.
DOM supports the following events: tag value changed, element defined/undefined/changed, and collection child added/removed.

#### Highlighting Annotations

DOM supports error checking and highlighting based on annotations added to DOM elements
(not to be confused with Java annotations).
Implement [`DomElementsAnnotator`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/highlighting/DomElementsAnnotator.java)
and return its instance from the overridden `DomFileDescription.createAnnotator()` method.
In `DomElementsAnnotator.annotate(DomElement element, DomElementsProblemsHolder annotator)`,
report all errors and warnings in the element's subtree via `DomElementsProblemsHolder.createProblem()`.

#### Automatic Highlighting (`BasicDomElementsInspection`)

An inspection extending `BasicDomElementsInspection` highlights the following errors automatically:

- `@Required` element missing or having empty text
- XML value that can't be converted by a `Converter`
- non-unique name where it must be unique

The last check requires annotating the name getter with `@NameValue`.
It uses the `DomFileDescription.getIdentityScope()` method to get the element defining the root scope in which the name must be unique.

To suppress spellchecking, annotate the DOM element with `@com.intellij.spellchecker.xml.NoSpellchecking`.

#### Required Children

A common error highlighting case is a missing required sub-tag or attribute.
DOM reports it automatically if the child getter is annotated with `@Required`.
For collection getters, this annotation means that the collection must not be empty (corresponding to `+` in DTD).
Also, creating a new element with required fixed-number or attribute children creates their tags or attributes in XML.

### Resolving

`GenericDomValue<T>` and its sub-interface `GenericAttributeValue<T>` accept any class as `T`.
For example, `GenericDomValue<PsiClass>` can be interpreted as a reference to a class.
In general, such a value can be considered a reference to an object of class `T`.
This is not very useful for strings or enums, but the idea applies to DOM elements.
XML often declares an object in one place and references it in a tag or attribute value elsewhere.
To model such a reference, declare a method like `GenericValue<MyDomElement> getMyDomElementReference()`,
and specify a converter that finds an instance of `MyDomElement` in the model with the name specified in the `GenericDomValue`.

DOM provides such a converter out of the box: [`DomResolveConverter`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomResolveConverter.java).
Reference getters don't need any annotations, as DOM resolves names automatically.
It searches elements by the name returned from the method annotated with `@NameValue`.
The `DomResolveConverter` constructor takes a parameter, so the class can't be referenced in the `@Convert` annotation, but its subclasses can.
To explicitly resolve a reference to a `DomElement` model-wide, use the `@Resolve` annotation parameterized with the target class.
The resolve scope is taken from `DomFileDescription.getResolveScope()`.

Auto-resolving in DOM also provides code insight in the XML editor: error highlighting, completion, Find Usages, and Rename refactoring.
Unresolved references are highlighted and completed.
To provide this code insight in a custom converter,
extend [`ResolvingConverter`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ResolvingConverter.java)
instead of [`Converter`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/Converter.java).
Its `getVariants()` method must return all targets the reference can resolve to,
similar to [`PsiReference`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReference.java).

To choose a `Converter` depending on other values (for example, in a sibling or parent element) or a runtime condition (for example, the presence or version of a library), use `WrappingConverter`.
See also `GenericDomValueConvertersRegistry` for managing an extensible registry of available converters.

### Mock and Stable Elements

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

Stable DOM elements also implement `StableElement`, which provides the following methods:

* `DomElement getWrappedElement()` - returns the current element to which all method calls are delegated.
* `void invalidate()` - makes the wrapped element invalid.
  The next method call causes the factory to create a new delegate.
* `void revalidate()` - calls the factory, and if it returns a new element (different from the currently wrapped one), invalidates the old element and adopts the new one.

### Visitor

DOM provides a visitor, `DomElementVisitor`, which is accepted by the `DomElement.accept()` and `acceptChildren()` methods.
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

### Implementation

A model sometimes needs functionality that isn't directly connected to XML, but relates to the program logic.
The DOM element interface is the most appropriate place for such functionality.

In the simplest case, a method returns the same value as another getter in this element or in one of its children.
Declare such a method and annotate it with `@PropertyAccessor`, specifying the path of property names (getter names without the `get` or `is` prefixes):

```java
GenericDomValue<String> getVeryLongName();

@PropertyAccessor("very-long-name")
GenericDomValue<String> getName();
```

In this case, the second method returns the same value as the first one.
With the "foo.bar.name" path in the annotation, DOM would call `getFoo().getBar().getName()` and return the result.
Such annotations are useful when extending an interface that is inconsistent with the model,
or when extracting a common super-interface from two model interfaces with differently named children that have the same meaning (for example, `<ejb-ref>` and `<ejb-local-ref>`).

More often, the model requires custom logic implemented in code.
Add the required methods to the interface, then create an abstract class implementing the interface,
and implement only the added methods that are not directly connected to the XML model.
The class must have a no-argument constructor.

Register the class using <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.dom.implementation"/></include>.
DOM then generates at runtime a class that implements the interface and extends the abstract class.

### Models Across Multiple Files

Many frameworks require a set of XML configuration files (_fileset_) to work as one model, so resolving and navigation work across all related DOM files.
Depending on the plugin, filesets can be provided implicitly (based on the existing framework setup in the project) or configured by the user (usually via a dedicated `Facet`).

Extend [`DomModelFactory`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/model/impl/DomModelFactory.java)
(or [`BaseDomModelFactory`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/model/impl/BaseDomModelFactory.java) for non-`Module` scope)
and provide an implementation of `DomModel`.
Usually, the `DomModel` implementation also provides search and utility methods.

### DOM Stubs

> Use stubs sparingly and only for heavily accessed parts of the DOM model, as they increase disk space usage and indexing time.
>
{style="note"}

DOM elements can be stubbed, so that costly access to XML PSI is not necessary
(see [](indexing_and_psi_stubs.md) for a similar feature for custom languages).
To stub performance-relevant elements, annotate their tag or attribute getters with `@com.intellij.util.xml.Stubbed`.
Set the `stubVersion` attribute of the <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dom.fileMetaData"/></include> extension,
and increase it whenever the usage of `@Stubbed` annotations changes in the DOM hierarchy to trigger rebuilding stubs during indexing.

## Building a DOM-Based GUI {collapsible="true"}
<primary-label ref="Deprecated"/>

> This API is unmaintained and will likely be removed in future versions.
>
{style="warning"}

### Forms

DOM-based forms support two operations: loading data from XML into the UI (_resetting_) and saving UI data to XML (_committing_).
[`Committable`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/Committable.java) declares the corresponding methods: `reset()` and `commit()`.
To structure forms into smaller parts, use [`CompositeCommittable`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/CompositeCommittable.java), which implements the Composite pattern.
The IDE invokes `commit()` and `reset()` automatically on editor tab switch or undo.
So it is enough to organize the whole Swing structure as a tree of `CompositeCommittable` objects.

DOM controls are special `Committable` implementations.
All of them implement `DomUIControl`.
They are not Swing components, but connectors between the DOM model and Swing components.
The DOM element is usually specified in the control's constructor.
The Swing component can be obtained in two ways:

* Ask the DOM control to create it.
* Call the control's `bind()` method to bind it to an existing Swing component of a matching type (depending on the type of the edited value).
  This approach is convenient for forms created, for example, in the IntelliJ IDEA GUI Designer.

After that, the Swing components are synchronized with DOM and highlight errors reported by `DomElementsAnnotator`.

To perform actions after a particular DOM control is committed (for example, to enable or disable some components or change their values),
call the control's `addCommitListener()` method and override `CommitListener.afterCommit()`.
This method is invoked inside the same write action as `commit()`, so any XML changes made in it are merged with the commit in the undo queue.

### Simple Controls

Simple controls edit `GenericDomValue` values: text, class names, enums, and booleans.
They take a `DomWrapper` object as a constructor parameter, which sets and gets values in the DOM model.

There are three main `DomWrapper` implementations:

* [`DomFixedWrapper<T>`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/DomFixedWrapper.java) redirects calls to `GenericDomValue<T>`.
* [`DomStringWrapper`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/DomStringWrapper.java) redirects calls to the string accessors of `GenericDomValue`.
* [`DomCollectionWrapper`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/DomCollectionWrapper.java) gets and sets values of the first element of the given `GenericDomValue` collection.

Some controls that contain a text field take an additional boolean constructor parameter, `commitOnEveryChange`.
Use it only in small dialogs, as committing on every change slows down the IDE significantly.

Most often, these controls are created by `DomUIFactory.createControl(GenericDomValue)`.
This method chooses the control to create using DOM reflection (`DomGenericInfo`, described above).
The following sections describe the simple controls for cases when they need to be created directly.

#### `BooleanControl`

Edits boolean values.
The control is bound to `JCheckBox`.

![BooleanControl](booleancontrol.gif)

#### `ComboControl`

The control is bound to a non-editable `JComboBox`, so it allows choosing a value from a limited set.
The set can be defined by an enum or by a `Factory<List<String>>` passed to the constructor, which can return any values (for example, a list of database names).
By default, invalid values (specified in XML, but missing in the list provided to the control) are displayed in red.
As combo boxes often use a custom `CellRenderer`, the control provides the `isValidValue(String)` method.
If it returns `false` for the rendered value, highlight the value in the custom renderer, or delegate to the default renderer.

![ComboControl](combocontrol.gif)

#### `BooleanEnumControl`

If there are only two alternatives, a checkbox can be more convenient than a combo box.
This control is bound to a checkbox, but instead of "true" and "false", it edits any two `String` values or two enum constants.
For enums, the `invertedOrder` parameter specifies which constant corresponds to the checked state.
By default, `invertedOrder` is `false`, so the first constant corresponds to the unchecked state, and the second one to the checked state.
If the parameter is `true`, the states are swapped.

### Editor-Based Controls

Editor-based controls use the IntelliJ Platform's `Editor` instead of the standard `JTextField`.
As `Editor` can't be instantiated directly through the Open API, these controls are bound to special `JPanel` inheritors,
and their `bind()` method adds the necessary content to the panels.

#### `TextControl`

Edits simple string values.
The control is bound to a `TextPanel` component.
If the control is bound to the `MultiLineTextPanel` inheritor, it displays a large editor.
If there is no space for a large editor, bind the control to a `BigTextPanel`.
It displays a text editor and a browse button that opens a dialog with a large editor for longer strings.

#### `PsiClassControl`

A one-line editor with a browse button that opens the standard class selection dialog.
The control accepts class names only.
It is bound to `PsiClassPanel`.

![PsiClassControl](psiclasscontrol.gif)

#### `PsiTypeControl`

Similar to `PsiClassControl`, but also accepts Java primitive types and arrays.
It is bound to `PsiTypePanel`.

### Collection Control

`DomCollectionControl<T>` is a table where each row represents one collection child, and `T` is the collection element type.
It requires the `DomElement` that is the parent of the collection, a description of the collection (sub-tag name or a `DomCollectionChildDescription` from DOM reflection), and a `ColumnInfo` array.
Pass them to the constructor, or override `createColumnInfos()` in a `DomCollectionControl` inheritor.

`ColumnInfo<Item, Aspect>` is an object-oriented way of working with the table model.
`Item` is the type of collection elements, and `Aspect` is the type of the column value: `String`, `PsiClass`, `Boolean`, etc.
A column info provides the column name, column class, value reading (`Aspect valueOf(Item)`), value writing (`setValue(Item item, Aspect aspect)`),
cell renderer (`getRenderer(Item)`), cell editability (`isCellEditable(Item)`), cell editor (`getEditor(Item)`), etc.

Many column infos are predefined, so creating a new one is rarely necessary.

If collection children are `GenericDomValue` objects, it is usually convenient to edit them directly in the table.
For this, use `StringColumnInfo`, `BooleanColumnInfo`, or the more generic `GenericValueColumnInfo`.
However, such collections are rare.

More commonly, a collection element is more complex and has several `GenericDomValue` children.
In this case, create a column for each child using `ChildGenericValueColumnInfo<T>`.
It requires a `DomFixedChildDescription` (from DOM reflection), a renderer, and an editor.

The main renderer options are `DefaultTableCellRenderer` and the IntelliJ Platform's `BooleanTableCellRenderer`.
Editors closely resemble simple DOM controls: `BooleanTableCellEditor`, `DefaultCellEditor(JTextField)`, `ComboTableCellEditor`, etc.
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

To add a custom popup menu to the control, call `DomTableView.installPopup()` after construction, and pass a `DefaultActionGroup` with the popup actions.

Tables support single or multiple (default) row selection.
To change this behavior, override `DomTableView.allowMultipleRowsSelection()`.

![CollectionControl](collectioncontrol.gif)

### UI Organization

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

## Sample Plugins

The following bundled open-source plugins make (heavy) use of DOM:

- [Android](%gh-ij-android%/README.md)
- [Ant](%gh-ic%/plugins/ant)
- [Plugin DevKit](%gh-ic%/plugins/devkit/devkit-core)
- [Maven](%gh-ic%/plugins/maven)

Explore third-party plugins using DOM on [IntelliJ Platform Explorer](https://jb.gg/ipe?extensions=com.intellij.dom.fileMetaData).
