<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# XML DOM API

<link-summary>Implementing APIs for accessing XML models.</link-summary>

[//]: # (TODO content: DOM <=> PSI, Go To Symbol, editor gutter icon->DOM)

This article is intended for plugin writers who create custom web server integrations, or some UI for easy XML editing.
It describes the *Document Object Model* (DOM) in IntelliJ Platform - an easy way to work with DTD or Schema-based XML models.
The following topics will be covered: working with DOM itself (reading/writing tags content, attributes, and subtags) and easy XML editing in the UI by connecting UI to DOM.

It's assumed that the reader is familiar with Java, Swing, IntelliJ Platform XML PSI (classes [`XmlTag`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/xml/XmlTag.java), [`XmlFile`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/xml/XmlFile.java), [`XmlTagValue`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/xml/XmlTagValue.java), etc.), IntelliJ Platform plugin development basics (application and project components, [file editors](editors.md)).

## XML PSI vs DOM

So, how to operate with XML from an IntelliJ Platform plugin?
Usually, one has to take `XmlFile`, get its root tag, and then find a required sub-tag by path.
The path consists of tag names, each of them a string.
Typing these everywhere is tedious and error-prone.
Consider the following XML:

```xml
<root>
  <foo>
    <bar>42</bar>
    <bar>239</bar>
  </foo>
</root>
```

The goal is to read the contents of the second bar element, namely, "239".

It's _not_ correct to create chained calls like

```java
file.getDocument()
    .getRootTag()
    .findFirstSubTag("foo")
    .findSubTags("bar")[1]
    .getValue()
    .getTrimmedText();
```

because each call here may return `null`.

So the code would probably look like this:

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

Looks awful, doesn't it?
But there's a better way to do the same thing.
It only requires extending a special interface - [`DomElement`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomElement.java).

For example, create several interfaces:

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

Next, create a [`DomFileDescription`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomFileDescription.java) class, pass to its constructor the root tag name and root element interface.
Register it in <path>[plugin.xml](plugin_configuration_file.md)</path>
using <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.dom.fileMetaData"/></include> and specify `rootTagName` and `domVersion`/`stubVersion` attributes.

[`DomManager`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomManager.java) now provides the file element.
To get the "239" value, only the following code is required:

```java
DomManager manager = DomManager.getDomManager(project);
Root root = manager.getFileElement(file).getRootElement();
List<Bar> bars = root.getFoo().getBars();
if (bars.size() > 1) {
  String s = bars.get(1).getValue();
  // do something
}
```

This looks a little nicer.
A model is often used in more than one place.
Re-creating the model is too inefficient, so it is cached, and any subsequent calls to `DomManager.getFileElement()` will return the same instance.
So, it is useful to invoke this method just once, and then keep everywhere only the obtained "root" object.
In this case, there is no need to repeat that scary first line, and the code will look even nicer.

It is also important to note that this scenario avoids potential `NullPointerException`: DOM guarantees that every method accessing a tag child will return a not-null element, even if the correspondingly-named sub-tag doesn't exist.
That may seem strange at first glance, but it appears to be rather convenient.
How does it work?
Simple.
Given those interfaces, DOM generates all the code for accessing correct subtags and creating model elements at runtime.
The sub-tag names and element types are taken from method names, return types and method annotations, if any.
In most cases annotations can be omitted, as in the example above, but this is discussed further in this article anyway.

The following sections explore more thoroughly what the DOM can do, and look at possible ways of representing various XML concepts such as tag content, attributes or sub-tags.
Later sections discuss basic methods for working with the model, as well as cover more advanced functionality.
The final section shows how to create a UI editor for DOM model elements.

## Building the Model

### Tag Content

In XML PSI, tag content is referred to as tag value, and this article does the same for consistency.
To read and change a tag value, add two methods (getter and setter) to the interface, like this:

```java
String getValue();
void setValue(String s);
```

These method names (`getValue` and `setValue`) are standard, and they are used for accessing tag values by default.
To use custom method names for the same goal, annotate these methods with `@TagValue`, for example:

```java
@TagValue
String getTagValue();

@TagValue
void setTagValue(String s);
```

These accessors work with `String` values.
This is natural, since XML represents a text format, and tag content is always text.
But sometimes a model operates with integers, booleans, enums, or even class names (they, of course, will be represented as `PsiClass`), and more generic Java types (`PsiType`).
In such cases, change the type in methods to the required one, and everything will keep working correctly.

#### Custom Value Types

For even more exotic types, tell DOM how to deal with them.
First, annotate the accessor methods with the `@Convert` annotation, and specify a custom class that should extend the `Converter<T>` class in the annotation.
Here `T` is the exotic type, while `Converter<T>` is a thing that knows how to convert values between `String` and `T`.
If the value cannot be converted (for example, "foo" is not convertible into `Integer`), the converter may return `null`.
Please also note that the implementation should have a no-argument constructor.

Consider an interesting case when `T` represents an enum value.
Usually, the converter just searches for enum elements with the names specified in XML.
But sometimes, enum element names need to match values that are not valid Java identifiers.
For example, the CMP version in EJB may be "1.x" or "2.x", but Java enum constants can't have such names.
For such cases, let the enum implement `NamedEnum` interface, and then use any names for the enum elements.
Now, just provide the `getValue()` implementation that will return the right value to match with XML contents, and voilà!
In the example, the code will look as follows:

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

As already mentioned, an XML tag may have lots of artifacts besides its value: there can be attributes, children, but rather often (e.g., according to DTD or Schema) it should have only the value.
Of course, such tags also need a DOM element to associate with.
DOM provides such an element:

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

So, specify a particular `T` when using this interface - and everything will work.
Methods that work with `String` are provided for many reasons.
For example, `T` is [`PsiClass`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiClass.java).
It would be useful to highlight invalid values in the UI.
To get the value to highlight (the string from the XML file), there is the `getStringValue()` method.
The error message will be taken from the converter via `getErrorMessage()`.

### Attributes

Attributes are also rather simple to deal with.
Their values can be read and set, and they can have different types.
So it's natural to create something like `GenericDomValue<T>` and then work as usual.
"Something like" will be an inheritor, as shown below:

```java
interface GenericAttributeValue<T> extends GenericDomValue<T> {
  XmlAttribute getXmlAttribute();
}
```

Consider an attribute named _some-class_ having a value of type `PsiClass`:

```java
@Attribute("some-class")
GenericAttributeValue<PsiClass> getMyAttributeValue();
```

That's all!
The returned value supports getting/setting values, resolving this `PsiClass`, getting its `String` representation, etc.
The name of the attribute will be taken from the method name (see next paragraph).
If the method is named in a special way, the annotation can even be omitted.
For example:

```java
GenericAttributeValue<PsiClass> getSomeClass();
```

The [`DomNameStrategy`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomNameStrategy.java) interface specifies how to convert accessor names to XML element names.
Or more precisely, not the full accessor names, but rather the names minus any "get", "set" or "is" prefixes.
The strategy class is specified in the `@NameStrategy` annotation in any DOM element interface.
Then any descendants and children of this interface will use this strategy.
The default strategy is [`HyphenNameStrategy`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/HyphenNameStrategy.java), where words are delimited by hyphens (see sample above).
Another common variant is [`JavaNameStrategy`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/JavaNameStrategy.java) that capitalizes the first letter of each word, as in Java's naming convention.
In the example, the attribute name would be "someClass".

If attribute doesn't define a `PsiClass`, but some other custom `T` that needs a converter, specify the `@Convert` annotation on the getter.

Please note that the attributes' getter method will never return `null`, even if the attribute isn't specified in XML.
Its `getValue()`, `getStringValue()` and `getXmlAttribute()` methods will return `null`, but the DOM interface instance will exist and be valid.
If the element has an underlying attribute, this can be easily fixed (surely, only if needed): just call the `undefine()` method (defined in `DomElement`), and the XML attribute disappears, while [`GenericAttributeValue`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/GenericAttributeValue.java) remains valid.

### Children: Fixed Number

Tags often have at most one sub-tag with the given name (e.g. `<ejb-name>`, `<ejb-class>` or `<cmp-field>` in tags defining entity EJBs).
To work with such children, provide getters for them.
These getters should have a return type that extends `DomElement`:

```java
GenericDomValue<String> getEjbName();
GenericDomValue<String> getEjbClass();
CmpField getCmpField();
```

There's also an annotation to designate such children explicitly: `@SubTag`.
Its "value" attribute contains a tag name.
If it is not specified, the name is implied from the method name using the current name strategy.

Sometimes it is the sub-tag's presence that means something, rather than its content - `<unchecked>` in EJB method permissions, for example.
If it exists, then permissions are unchecked, otherwise checked.
For such things one should create a special `GenericDomValue<Boolean>` child.
Usually its `getValue()` returns `true` if there's "true" in a tag value, `false` if there's "false" in a tag value, and `null` otherwise.
To change this, specify the attribute like `indicator=true` in the `@SubTag` annotation.
In this case, `getValue()` will return `true` if the tag exists and `false` otherwise.

Consider another interesting example inspired by EJB, where there is a relation that has two roles, each designating one relation end: first role and second role.
Both are represented by tags with the same values.
So, a collection of role elements could be used, but then every role access would require checking whether the collection has a sufficient number of elements.
But one of the main purposes of the DOM is to eliminate unnecessary checks.
So why not have a fixed (more than one) number of children with the same tag name?
DOM supports this:

```java
@SubTag(value = "ejb-relationship-role", index = 0)
EjbRelationshipRole getEjbRelationshipRole1();

@SubTag(value = "ejb-relationship-role", index = 1)
EjbRelationshipRole getEjbRelationshipRole2();
```

The first method will return the DOM element for the first subtag named `<ejb-relationship-role>`, and the second - for the second one.
Hence, the term "fixed number" for such children.
According to DTD or Schema, there should be a fixed number of subtags with the given name.
Most often this fixed number is 1; in the case with the relations it is 2.
Just like attributes, fixed-number children exist regardless of underlying tag existence.
To delete tags, use the same `undefine()` method.

For children of [`GenericDomValue`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/GenericDomValue.java) type, a converter can also be specified, just as for attributes.

### Children: Collections

One more common case in DTD and Schemas is when children have the same tag name and a non-fixed upper limit in count.
Their accessors differ from those of the fixed-number children in the following: the return result is `Collection` or `List` of a special type that extends `DomElement`, and to use name strategies, the method name must be in pluralized form.
For example, in the EJB there would be the following method:

```java
List<Entity> getEntities();
```

There's also an annotation `@SubTagList` that specifies the tag name explicitly.

Returned collections cannot be modified directly.
To delete an element from a collection, just call `undefine()` on this element.
The tag will then be removed, and an element will become invalid (`DomElement.isValid() == false`).
Note that this behavior differs from that of fixed-number children and attributes: they are always valid, even after `undefine()`.
Again, unlike those children types, collection children always have valid underlying XML tags.

Adding elements is a bit harder.
Since all DOM elements are created internally, it is not possible to just pass some existing DOM element to some method to add it to the collection.
Instead, the parent element adds a child to the collection.
In the example, it's done in the following way:

```java
Entity addEntity(int index);
```

which adds an element at the given index, or

```java
Entity addEntity();
```

which adds a new DOM element to the end of the collection.
Please note the singular tense of the word "Entity".
That's because this method deals with one `Entity` object, while the collection getter deals with potentially many entities.

Now, the returned value can be used as needed: modify it, define the tag's value, children, etc.

The last common case is also a collection, but one consisting of tags with different names that are arbitrarily mixed.
To work with it, define collection getters for all tag names within the mixed collection, and then define an additional specially annotated getter:

```java
// <foo> elements
List<Foo> getFoos();

// <bar> elements
List<Bar> getBars();

// all <foo> and <bar> elements
@SubTagsList({ "foo", "bar" })
List<FooBar> getMergedListOfFoosAndBars();
```

The annotation here is mandatory - DOM cannot guess several tag names from one method name.

To add elements to such mixed collections, create "add" methods for each possible tag name:

```java
@SubTagsList(value = { "foo", "bar" }, tagName = "foo")
FooBar addFoo();

@SubTagsList(value = { "foo", "bar" }, tagName = "bar")
FooBar addBar(int index);
```

The index parameter in the last example means the index in the merged collection, not in the collection of tags named "bar".

### Dynamic Definition

Existing DOM models can be extended at runtime by implementing `com.intellij.util.xml.reflect.DomExtender<T>`.
Register it in "extenderClass" attribute of <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.dom.extender"/></include>, where "domClass" specifies DOM class `<T>` to be extended.
[`DomExtensionsRegistrar`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/reflect/DomExtensionsRegistrar.java) provides various methods to register dynamic attributes and children.

If the contributed elements depend on anything other than plain XML file content (used framework version, libraries in classpath, ...), make sure to return `false` from `DomExtender.supportsStubs()`.

### Namespace Support

Annotate DOM model with [`Namespace`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/Namespace.java) and register namespace key mapping via `DomFileDescription.registerNamespacePolicy()` from `DomFileDescription.initializeFileDescription()`.

### IDE Support

_Plugin DevKit_ supports the following features for working with DOM related code:

* [`DomElement`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomElement.java) - provide implicit usages for all DOM-related methods defined in inheriting classes (to suppress "unused method" warning)
* [`DomElementVisitor`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomElementVisitor.java) - provide implicit usages for all DOM-related visitor methods defined in inheriting classes (to suppress "unused method" warning)

## Working with the DOM

### Class Choosers

It often happens that a collection contains same-named tags that may have different structure or even be represented by different types in the DTD or Schema.
As an example, JSF Managed Beans may be of three types.
If a `<managed-bean>` tag contains a `<map-entries>` sub-tag, then the Managed Bean type is `MapEntriesBean`.
If it contains a `<list-entries>` sub-tag, then the type is `ListEntriesBean`.
Otherwise, it's a `PropertyBean` (all three interfaces extend `ManagedBean`).
And `List<ManagedBean> getManagedBeans()` should return not only a list where all elements are instances of the `ManagedBean` interface, but a list where each element is of a certain type, i.e. `MapEntriesBean`, `ListEntriesBean`, or `PropertyBean`.

In such cases, one should decide which interface the DOM element should actually implement (according to the given tag).
This is achieved by extending the [`TypeChooser`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/TypeChooser.java) abstract class:

```java
public abstract class TypeChooser {
  public abstract Type chooseType(XmlTag tag);
  public abstract void distinguishTag(XmlTag tag, Type aClass)
      throws IncorrectOperationException;
  public abstract Type[] getChooserTypes();
}
```

Here, the first method (`chooseType()`) does exactly what it is named after (chooses the particular type, most often it's a class).
The second one (`distinguishTag()`) acts in reverse: it modifies a tag so that when the element is read from an XML file next time (for example, after the user has closed and opened the project again), the newly created DOM element will implement the same interface and no model data will be lost.
Finally, `getChooserTypes()` just returns all the types that could be returned by `chooseType()`.

To make the `TypeChooser` work, register it in the overridden `DomFileDescription.initializeFileDescription()` method by calling `registerTypeChooser()`.

### Useful Methods of `DomElement` and `DomManager`

#### PSI Connection

Of course, DOM is tightly connected to XML PSI, so there's always a way of getting the `XmlTag` instance (which can be `null` for fixed-number children and attributes) using the `getXmlTag()` method.
As mentioned above, `GenericAttributeValue` also has the `getXmlAttribute()` method.
In general case there is `getXmlElement()` method.
A DOM element can also be obtained by its underlying XML PSI element using the `DomManager.getDomElement()` method.

If a DOM element has no underlying XML element, it can be created by calling `ensureTagExists()`.
To delete a tag, use the already known `undefine()` method.
This method will always delete the underlying XML element (tag or attribute).
If the element was a collection's child, then neither it nor its entire subtree will be valid anymore.

#### Tree Structure

In every normal tree, there's always a possibility to walk up.
`DomElement` is no exception.
Method `getParent()` just returns element's parent in tree.

The method `<T extends DomElement> T getParentOfType(Class<T> requiredClass, boolean strict)` returns the tree ancestor of the given class.
If the standard _strict_ parameter is `false` and the current DOM element is an instance of _requiredClass_, the method returns the DOM element itself.

Finally, `getRoot()` will return the `DomFileElement`, which is the root of every DOM tree.

#### Validity

An element becomes invalid if it has been deleted explicitly or due to external PSI changes.
Fixed-number children and attributes are meant to stay valid as long as possible, no matter what happens with XML.
They can become invalid only if they have a collection tree ancestor that has been deleted.

Newly created DOM elements are always correct and valid, so their `isValid()` methods will return `true`.

Element validity is very important, since no methods can be invoked on invalid elements (except, of course, `isValid()` itself).

#### DOM Reflection

DOM also has a kind of reflection, called "Generic Info".
One would use it to be able to access children by tag names directly, instead of calling getter methods.
See [`DomGenericInfo`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/reflect/DomGenericInfo.java) interface and `getGenericInfo()` methods in `DomElement` and `DomManager` for more information.
There's also `DomElement.getXmlElementName()` method that returns the name of a corresponding tag or attribute.

#### Presentation

<!-- TODO: using @Presentation -->

`DomElement.getPresentation()` returns an instance of [`ElementPresentation`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ElementPresentation.java), an interface that knows presentable element type, name, and sometimes even its icon.
Presentations are actually obtained from presentation factory objects that, like `ClassChooser`s, should be registered in [`ElementPresentationManager`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ElementPresentationManager.java) as early as possible.
It is possible to specify type name and icon for all elements of some class, ways of getting type name, icon and presentable name for particular objects.
When not specified, presentable name is taken from the object itself, if it contains a method annotated with `@NameValue` annotation, that returns `String` or `GenericValue`.
If there's no such method, it will return `null`.
For `DomElement`, there's another way to get this presentable name:
`DomElement.getGenericInfo().getElementName()`.

#### Events

To be notified on every change in the DOM model, add `DomEventListener` to `DomManager`.
DOM supports the following events: tag value changed, element defined/undefined/changed, and collection child added/removed.

#### Highlighting Annotations

The DOM supports error checking and highlighting.
It's based on annotations added to the DOM element in a special place (don't confuse these annotations with the ones of Java 5 - they are very different).
Implement the [`DomElementsAnnotator`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/highlighting/DomElementsAnnotator.java) interface, override `DomFileDescription.createAnnotator()` method, and create this annotator there.
In `DomElementsAnnotator.annotate(DomElement element, DomElementsProblemsHolder annotator)`, report all errors and warnings in the element's subtree to the annotator (`DomElementsProblemsHolder.createProblem()`).
Return this annotator in the corresponding virtual method of the `DomFileDescription`.

#### Automatic Highlighting (`BasicDomElementsInspection`)

The following errors can be highlighted automatically by providing an instance of `BasicDomElementsInspection`:

- `@Required` element missing or having empty text
- XML value cannot be converted by some `Converter`
- name is not unique while it should be

The latter case requires specifying the name getter with `@NameValue` annotation.
The checking uses the `DomFileDescription.getIdentityScope()` method to get the element defining the root scope in which the name should be unique.

To suppress spellchecking annotate the DomElement with `@com.intellij.spellchecker.xml.NoSpellchecking`.

#### Required Children

There is a common case in error highlighting, when one needs to say, that some required sub-tag or attribute is missing.
DOM does this automatically if the getter for that child is annotated with the `@Required` annotation.
For collection children getters, this annotation will mean that the collection should be not empty (corresponding to '+' sign in DTD).
Also, creating a new element that has required fixed-number or attribute children creates their tags or attributes in XML as well.

### Resolving

Remember the interface `GenericDomValue<T>` and its sub-interface `GenericAttributeValue<T>`?
Remember, that ANY class may be passed as `T` - for example, `GenericDomValue<PsiClass>` can be interpreted as a reference to a class.
Then it can always be considered as a reference to an object of class `T`!
With strings or enums, this is not very useful, but the idea applies in another way.
Very often XML has such a structure that an object is declared at some place, and is referenced at some other place (more precisely, in a tag or attribute value).
So, to create a method like `GenericValue<MyDomElement> getMyDomElementReference()`, just specify a proper converter that will find an instance of `MyDomElement` in the model with the name specified in the `GenericDomValue`.

That's the core idea.
Since creating such converters is quite boring, DOM provides one out of the box.
Reference getters don't need to be annotated at all, as the name resolution will be made automatically.
Elements will be searched by name, and the name will be taken from the method annotated with `@NameValue`.
The converter used is [`DomResolveConverter`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/DomResolveConverter.java).
Its constructor takes a parameter, so it can't be referenced in `@Convert` annotation, but its subclasses (if any) - can.
To specify explicitly that a reference to `DomElement` should be resolved "model-wide", use the `@Resolve` annotation parameterized with the desired class.
The resolution scope will be taken from the `DomFileDescription.getResolveScope()`.

In addition to the above, auto-resolving in DOM also provides some features in the XML text editor: error highlighting, completion, Find Usages, Rename Refactoring...
Unresolved references will be highlighted, and even completed.
To create a custom converter with this code insight, extend not only the [`Converter`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/Converter.java) but [`ResolvingConverter`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ResolvingConverter.java).
It has one more method `getVariants()`, which must provide the collection consisting of all targets the reference may resolve to.
Those familiar with [`PsiReference`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReference.java) will recognize the similarities here.

To choose a `Converter` depending on other values (e.g. in a sibling/parent element) or any runtime condition (e.g. presence or version of a library), use `WrappingConverter`.
See also `GenericDomValueConvertersRegistry` for managing an extensible registry of available Converters to choose from.

### Mock and Stable Elements

DOM elements do not have to be tied to a physical file.
`DomManager.createMockElement()` creates a virtual element of a given class with the given module.
An element may be physical or not.
'Physical' here means that DOM will create a mock document for it, so <control>Undo</control> functionality is available if this document is passed to the right place in the file editor.

`DomElement.copyFrom()` copies information from one `DomElement` to another.
In fact, it just replaces XML tags, and all the old data is lost.
Nevertheless, the element's fixed-number children don't become invalid.
They only contain new tag values, attribute values, etc.
The tree is actually rather conservative.

The combination of `createMockElement()` and `copyFrom()` is useful for editing element contents in dialogs.
Create a mock copy of an element, work with it in the dialog and then, if the user doesn't cancel, copy the element back to the main model.
Since it's a common case, a special shortcut method has been created in `DomElement`, called `createMockCopy()`.

IntelliJ Platform's XML parser is incremental: changes in a text do not cause the whole file to be reparsed.
But keep in mind that this rule may sometimes not work correctly.
For example, DOM elements can unexpectedly become broken as a result of manual editing of the XML file (even if it didn't happen inside those elements).
If a file editor depends on such a broken element, this can lead to closing the tab, which isn't very nice from the user's point of view.
For example, suppose there is an entity bean named "SomeEntity".
The user opens an editor for it, then changes the tag name in the XML from entity to session, and then back to entity.
Of course, no DOM element can survive after such blasphemy.
But notwithstanding, the editor should stay open!
Well, there is a solution, and it's called `DomManager.createStableValue(Factory factory)`.
This method creates a DOM element that delegates all its functionality to some real element (returned from the factory parameter).
As soon as that real element becomes invalid, the factory is called once more, and if it returns something valid, it becomes the new delegate.
And so on...
In the example with EJB, the factory would once again look for an Entity Bean named "SomeEntity".

Stable DOM elements also implement the `StableElement` interface, which has the following methods:

* `DomElement getWrappedElement()` - just returns the current element to which all method calls are delegated;
* `void invalidate()` - makes the wrapped element invalid. Any following method call will cause the factory to create a new delegate;
* `void revalidate()` - calls the factory, and if it returns something new (i.e. not the same as the current wrapped element) invalidates the old value and adopts the new one.

### Visitor

Visitor is a very common design pattern.
DOM model also has a visitor, and it's called `DomElementVisitor`.
The `DomElement` interface has methods `accept()` and `acceptChildren()` that take this visitor as a parameter.
The `DomElementVisitor` interface itself may look surprising, since it has only one method: `visitDomElement(DomElement)`.
Where is the visitor pattern?
Where are all those methods with names like `visitT(T)` that are usually found in it?
There are no such methods, because the actual interfaces (T's) aren't known to anyone except the plugin developer.
But when instantiating the `DomElementVisitor` interface, add these `visitT()` methods there, and they will be called!
They can even be named just `visit()` with the parameter type specified, and everything will be fine.
For example, for two DOM element classes - `Foo` and `Bar` - the visitor may look like this:

```java
class MyVisitor implements DomElementVisitor {
  void visitDomElement(DomElement element) {}
  void visitFoo(Foo foo) {}
  void visitBar(Bar bar) {}
}
```

### Implementation

Sometimes the model needs to be extended with some functionality that isn't directly connected with XML, but relates to the program logic.
And the most appropriate place for this functionality is the DOM element interface.
What to do then?

The simplest case is adding a method to the interface that returns exactly what some other getter in this element (or in one of its children) returns.
Write this helper method and annotate it with the `@PropertyAccessor` annotation, specifying the path consisting of property names (getter names without the "get" or "is" prefixes).
For example:

```java
GenericDomValue<String> getVeryLongName();

@PropertyAccessor("very-long-name")
GenericDomValue<String> getName();
```

In this case, the second method will return just the same as the first one.
If there were "foo.bar.name" instead of "very-long-name" in the annotation, the system would actually call `getFoo().getBar().getName()` and return the result.
Such annotations are useful when extending some interface that is inconsistent with the model, or extracting a common super-interface from two model interfaces with differently named children that have the same sense (see `<ejb-ref>` and `<ejb-local-ref>`).

The case just described is simple, but rare.
More often, some logic really has to be incorporated into the model.
Then nothing except Java code helps.
And it will.
Add the desired methods to the interface, then create an abstract class implementing the interface, and implement there only the manually added methods that are not directly connected to the XML model.
Note that the class should have a constructor with no arguments.

Now, let DOM know to use this implementation every time it creates a model element implementing the necessary interface.
Register it using <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.dom.implementation"/></include> and DOM will generate at run-time the class that not only implements the needed interface, but also extends the abstract class.

### Models Across Multiple Files

Many frameworks require a set of XML configuration files ("fileset") to work as one model, so resolving/navigation works across all related DOM files.
Depending on implementation/plugin, providing filesets implicitly (using existing framework's setup in a project) or via user configuration (usually via dedicated `Facet`) can be achieved.

Extend [`DomModelFactory`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/model/impl/DomModelFactory.java) (or [`BaseDomModelFactory`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/model/impl/BaseDomModelFactory.java) for non-`Module` scope) and provide implementation of the `DomModel`.
Usually, the `DomModel` implementation also provides searcher/utility methods.

### DOM Stubs

> Please use it sparingly and only for heavily accessed parts in the DOM model, as it increases disk space usage/indexing run time.
>
{style="note"}

DOM elements can be stubbed, so (costly) access to XML/PSI is not necessary (see [Indexing and PSI Stubs](indexing_and_psi_stubs.md) for similar feature for custom languages).
Performance relevant elements, tag or attribute getters can simply be annotated with `@com.intellij.util.xml.Stubbed`.
Set and increase `stubVersion` of <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dom.fileMetaData"/></include> extension whenever `@Stubbed` annotations usage changes in the DOM hierarchy to trigger proper rebuilding of Stubs during indexing.

## Building a DOM-Based GUI {collapsible="true"}
<primary-label ref="Deprecated"/>

> This API is unmaintained and will likely be removed in future versions.
>
{style="warning"}

### Forms

All forms that deal with DOM are organized in a special way.
They support two main things: getting data from XML into the UI, and saving UI data to XML.
The former is called resetting, the latter - committing.
There's [`Committable`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/Committable.java) interface that has corresponding methods: `commit()` and `reset()`.
There's also a way of structuring forms into smaller parts, namely the Composite pattern: [`CompositeCommittable`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/CompositeCommittable.java).
Methods `commit()` and `reset()` are invoked automatically on editor tab switch or undo.
So it is enough to organize the whole Swing structure in a tree of `CompositeCommittable`, and the IDE will do all the hard work.

DOM controls are special descendants of `Committable`.
All of them implement `DomUIControl`.
Note that they are not Swing components - they are only a way of connecting DOM model and Swing components.
One end of the connection - the DOM element - is usually specified in the control's constructor.
The other end - Swing component - can be obtained in 2 ways.
The first is to ask DOM control to create it.
But that is rather inconvenient for creating the forms in, say, IntelliJ IDEA's GUI Designer.
In that case, use the second way: ask the control to `bind()` to an existing Swing component of a correct type (that depends on the type of the edited value).
After that, the Swing components will be synchronized with DOM.
They'll even highlight errors reported by `DomElementsAnnotator`.

Sometimes it is necessary to do some work (enable or disable some components, change their values) after a particular DOM control is committed.
Then define the `addCommitListener()` method of that DOM control and override the `CommitListener.afterCommit()` method.
This method will be invoked inside the same write action as the main `commit()`, so any changes made in this method to the XML will be merged with the `commit()` in the Undo queue.

### Simple Controls

Simple controls allow editing `GenericDomValue`: simple text, class names, enums and boolean values.
These controls take a special object as a constructor parameter.
This object should implement the `DomWrapper` interface that knows how to set/get values to/from a DOM model.

There are three major DomWrapper's: [`DomFixedWrapper<T>`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/DomFixedWrapper.java) redirecting calls to
[`GenericDomValue<T>`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/GenericDomValue.java),
[`DomStringWrapper`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/DomStringWrapper.java)
redirecting calls to string accessors of
[`GenericDomValue`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/GenericDomValue.java),
and [`DomCollectionWrapper`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/DomCollectionWrapper.java)
that gets/sets values of the first element of the given [`GenericDomValue`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/GenericDomValue.java) collection.
Some controls (those having a text field as part of itself) take an additional boolean constructor parameter - _commitOnEveryChange_, whose meaning is evident from the name.
Using it is not recommended anywhere except small dialogs, because committing on every change slows down the system significantly.

Most often these controls are created by `DomUIFactory.createControl(GenericDomValue)`.
This method understands which control to create by using DOM reflection (`DomGenericInfo`, described above).
But sometimes it is necessary to create the controls directly.
The following sections describe the simple controls more closely.

##### `BooleanControl`

It allows editing boolean values.
The control is bound to `JCheckBox`.

![BooleanControl](booleancontrol.gif)

##### `ComboControl`

The control is bound to a non-editable `JComboBox`, so it can be used to choose something from a limited set.
One case of such a limited set is enum.
Or it can be a constructor accepting a `Factory<List<String>>`, which can return any values (for example, a list of database names to choose from).
By default, the wrong values (written in XML, but not present in the list given to the control) are displayed in red.
Since it's common practice to specify custom `CellRenderer` for combo boxes, the control has the `isValidValue(String)` method.
If it returns `false` on the rendered value, highlight it in some way, to achieve the same result as the default renderer.
Or just delegate to that renderer in a custom way.

![ComboControl](combocontrol.gif)

##### `BooleanEnumControl`

Sometimes, when there are only 2 alternatives, it's convenient to use a checkbox instead of a combobox.
This control is designed specially for such cases.
While being (and being bound to) a checkbox, the control edits not just "true" or "false", but any two String values, or two enum elements.
In the last case, it has a boolean _invertedOrder_ parameter, to specify which element corresponds to the checked state.
By default, _invertedOrder_ is set to `false`, so the first element corresponds to the unchecked state, and the second - to the checked one.
If the parameter is set to `true`, the states will swap.

### Editor-Based Controls

Please note that editor-based controls are built on IntelliJ Platform's `Editor` instead of standard `JTextField`.
Since there's currently no way to instantiate Editor directly through the Open API, controls are bound to special `JPanel` inheritors, and their `bind()` method adds the necessary content to those panels.

##### `TextControl`

This control allows editing simple string values.
The control is bound to a `TextPanel` component.
There's also an inheritor of that panel - `MultiLineTextPanel`.
If a `StringControl` is bound to it, a big editor will appear on the screen.
In case there is no space for a big editor, bind it to a `BigTextPanel`.
Then it will be filled with a text editor, and the browse button will be added to open a dialog with the big editor for typing a longer string.

##### `PsiClassControl`

This is a one-line editor with a browse button that opens the standard class selection dialog.
The control accepts class names only.
It is bound to `PsiClassPanel`.

![PsiClassControl](psiclasscontrol.gif)

##### `PsiTypeControl`

This is almost the same as PsiClassControl, but allows entering not only class names, but also Java primitive types and even arrays.
It is bound to `PsiTypePanel`.

### Collection Control

There is a special table component where each row represents one collection child.
It's called `DomCollectionControl<T>`, where `T` is the collection element type.
To function properly, it needs `DomElement` (parent of the collection), some description of the collection (sub-tag name or a `DomCollectionChildDescription` from DOM reflection), and a `ColumnInfo` array.
This can be passed to the constructor, or can be created in a `DomCollectionControl` inheritor, in an overridden method `createColumnInfos()`.

What is a column info?
It's just a somewhat more comfortable way to work with the table model.
It uses Java 5 generics and is more object-oriented.
So, it's named `ColumnInfo<Item,Aspect>`, where `Item` is a type variable corresponding to the type of elements in the collection, and `Aspect` is a type variable corresponding to this particular column information type: `String`, `PsiClass`, `Boolean`, etc.
The basic things that a column knows are: column name, column class, reading value (Aspect `valueOf(Item)`), writing value (`setValue(Item item, Aspect aspect)`), cell renderer (`getRenderer(Item)`), cell "editability" (`isCellEditable(Item)`), cell editor (`getEditor(Item)`), etc.

There are a lot of predefined column infos, so creating a new one is rarely necessary.

First, if a collection child is a `GenericDomValue`, it's usually convenient to edit it directly in the table.
For this, use one of the following classes: `StringColumnInfo`, `BooleanColumnInfo`, or more generic `GenericValueColumnInfo`.
But such collections are encountered very rarely.

A more common case is when a collection element is more complex and has several `GenericDomValue` children.
Then one may create a column for each of those children.
The appropriate column info is `ChildGenericValueColumnInfo<T>`.
It requires a `DomFixedChildDescription` (one more thing from DOM reflection), a renderer and an editor - nothing else.
So, the main things left to customize are the renderer and the editor.

As for the renderer, there are two main choices: `DefaultTableCellRenderer`, and IntelliJ Platform's `BooleanTableCellRenderer`.
Editors are more complicated, but they closely resemble simple DOM controls.

`BooleanTableCellEditor`, `DefaultCellEditor(JTextField)`, `ComboTableCellEditor`, etc.
`DomUIFactory.createCellEditor()` will create any of them automatically (including the editor for `PsiClass`), so there is no need to think about which one to select every time.

Collection control is a complex control, so it's bound to a complex Swing component.
It's called [`DomTableView`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/DomTableView.java).
It has a toolbar (override `DomTableView.getToolbarPosition()` to customize its location), with Add and Delete buttons.
Optionally, specify custom addition actions in `DomCollectionControl.createAdditionActions()` (it's recommended to extend `ControlAddAction`).
If there is only one addition action, it will be invoked after pressing the Add button; if there are many, then a popup menu will be displayed.
To change the removal policy, override the `DomCollectionControl.doRemove(List<T>)` method.

The toolbar may also have an Edit button, if `DomCollectionControl.isEditable()` returns `true`.
To add a behavior to this button, override `DomCollectionControl.doEdit(T)`.
There can also be a Help button, if a non-null String _helpId_ parameter is passed while constructing the `DomTableView`.

If there are no items in the collection, `DomTableView` may display a special text (`DomTableView.getEmptyPaneText()`), instead of an empty table.

A custom popup menu can be added to the control.
Call the `DomTableView.installPopup()` method after construction, and pass a `DefaultActionGroup` with the popup actions.

Tables can have single or multiple (default) row selection.
To change this behavior, override `DomTableView.allowMultipleRowsSelection()`.

![CollectionControl](collectioncontrol.gif)

### UI Organization

The easiest way to create a DOM-based UI form is to extend the
[`BasicDomElementComponent`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/BasicDomElementComponent.java) class.
This requires passing some DOM element to the constructor.
Then bind an IntelliJ IDEA GUI Designer form to the subclass and design the form there.
When binding some controls to DOM UI, ensure that they have the right types.
Finally, create some DOM controls in class' constructor and bind them.
But controls can also be created and bound to the `DomElement`'s children - `GenericDomValue`'s automatically.

Just name the components properly and call the `bindProperties()` method in the constructor.
The field names should correspond to the getter names for the element's children.
They may also be prefixed with "my".
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

All the fields here are now bound to the controls in the GUI form.

Very often, a custom file editor is required.
Then, to use all the binding and undo functionality, it's suggested to inherit the
[`FileEditorProvider`](%gh-ic%/platform/analysis-api/src/com/intellij/openapi/fileEditor/FileEditorProvider.java)
from
[`PerspectiveFileEditorProvider`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/PerspectiveFileEditorProvider.java), create an instance of
[`DomFileEditor`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/DomFileEditor.java)
there, and pass a
[`BasicDomElementComponent`](%gh-ic%/xml/dom-openapi/src/com/intellij/util/xml/ui/BasicDomElementComponent.java).
To easily create an editor with a caption at the top, like in the EJB and JSF editors, use the static method `DomFileEditor.createDomFileEditor()`.
`DomFileEditor` automatically listens to all changes in the document corresponding to the given DOM element, and therefore refreshes the component on undo.
To listen to changes in additional documents, use the methods `addWatchedDocument()`, `removeWatchedDocument()`, `addWatchedElement()`, `removeWatchedElement()` in `DomFileEditor`.

## Sample Plugins

The following bundled open-source plugins make (heavy) use of DOM:

- [Android](%gh-ij-android%/README.md)
- [Ant](%gh-ic%/plugins/ant)
- [Plugin DevKit](%gh-ic%/plugins/devkit/devkit-core)
- [Maven](%gh-ic%/plugins/maven)

Explore 3rd party plugins using DOM on [IntelliJ Platform Explorer](https://jb.gg/ipe?extensions=com.intellij.dom.fileMetaData).
