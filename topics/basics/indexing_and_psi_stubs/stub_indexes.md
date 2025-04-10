<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Stub Indexes

<link-summary>Creating stub indexes containing PSI tree fragments, which allow searching for PSI elements without parsing files.</link-summary>

## Stub Trees

A stub tree is a subset of the PSI tree for a file; it is stored in a compact serialized binary format.
The PSI tree for a file can be backed either by the AST (built by parsing the file) or by the stub tree deserialized from disk.
Switching between the two is transparent.

The stub tree contains only a subset of the nodes.
Typically, it contains only the nodes needed to resolve the declarations contained in this file from external files.
Trying to access any node that is not part of the stub tree or perform any operation that cannot be satisfied by the stub tree, e.g., accessing the text of a PSI element, causes file parsing to switch from the stub to AST backing.

Each stub in the stub tree is simply a bean class with no behavior.
A stub stores a subset of the corresponding PSI element's state, like the element's name, modifier flags like public or final, etc.
The stub also holds a pointer to its parent in the tree and a list of its children's stubs.

To support stubs for a custom language, first decide which of the PSI tree elements should be stored as stubs.
Typically, stubs are needed for things like methods or fields visible from other files.
Usually there is no need to have stubs for things like statements or local variables, which are not visible externally.

### Implementation

> When using [Grammar-Kit](https://github.com/JetBrains/Grammar-Kit) to generate the language PSI, see the [Stub indexes support](https://github.com/JetBrains/Grammar-Kit/blob/master/HOWTO.md#35-stub-indices-support) section for instructions on integrating the grammar with stubs.
>
{style="note"}

<procedure id="stubs-setup" title="Stubs Setup">

The following steps need to be performed only once for each language that supports stubs:

1. Change the file element type for the language (the element type returned from `ParserDefinition.getFileNodeType()`) to a class that extends [`IStubFileElementType`](%gh-ic%/platform/core-impl/src/com/intellij/psi/tree/IStubFileElementType.java) and override its `getExternalId()` method (see also following item).
2. In the <path>[plugin.xml](plugin_configuration_file.md)</path>, define the <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.stubElementTypeHolder"/></include> extension and specify the interface which contains the `IElementType` constants used by the language's parser.

   Define the common `externalIdPrefix` to be used for all stub element types (see [](#adding-stub-elements)).
   See [`StubElementTypeHolderEP`](%gh-ic%/platform/core-api/src/com/intellij/psi/stubs/StubElementTypeHolderEP.java) docs for important requirements.

**Examples:**
- [`JavaStubElementTypes`](%gh-ic%/java/java-psi-impl/src/com/intellij/psi/impl/java/stubs/JavaStubElementTypes.java) registered in [`JavaPsiPlugin.xml`](%gh-ic%/java/java-psi-impl/resources/META-INF/JavaPsiPlugin.xml)
- see [`Angular2MetadataElementTypes`](%gh-ij-plugins%/Angular/src/org/angular2/entities/metadata/Angular2MetadataElementTypes.kt) for Kotlin sample

</procedure>

<procedure id="adding-stub-elements" title="Adding Stub Elements">

For each element type that needs to be stored in the stub tree, perform the following steps:

1. Define an interface for the stub, derived from the [`StubElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/stubs/StubElement.java) interface ([example](%gh-ic%/plugins/properties/properties-psi-api/src/com/intellij/lang/properties/psi/PropertyStub.java)).
2. Provide an implementation for the interface ([example](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/psi/impl/PropertyStubImpl.java)).
3. Make sure the interface for the PSI element extends [`StubBasedPsiElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/StubBasedPsiElement.java) parameterized by the type of the stub interface ([example](%gh-ic%/plugins/properties/properties-psi-api/src/com/intellij/lang/properties/psi/Property.java)).
4. Make sure the implementation class for the PSI element extends [`StubBasedPsiElementBase`](%gh-ic%/platform/core-impl/src/com/intellij/extapi/psi/StubBasedPsiElementBase.java) parameterized by the type of the stub interface ([example](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/psi/impl/PropertyImpl.java)).
   Provide both a constructor that accepts an `ASTNode` and a constructor that accepts a stub.
5. Create a class that implements [`IStubElementType`](%gh-ic%/platform/core-api/src/com/intellij/psi/stubs/IStubElementType.java) and is parameterized with the stub interface and the actual PSI element interface ([example](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/parsing/PropertyStubElementType.java)).
   Implement the `createPsi()` and `createStub()` methods for creating PSI from a stub and vice versa.
   Implement the `serialize()` and `deserialize()` methods for storing the data in a binary stream.

   Override `getExternalId()` according to common used `externalIdPrefix` for the language (see [](#stubs-setup)).

   For always-leaf stub nodes return `true` from `isAlwaysLeaf()` (2023.3).
   "Container" stubs that do not serialize any data of their own may implement [`EmptyStubSerializer`](%gh-ic%/platform/core-api/src/com/intellij/psi/stubs/EmptyStubSerializer.java) to optimize storage (2023.3).
6. Use the class implementing `IStubElementType` as the element type constant when parsing ([example](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/parsing/PropertiesElementTypes.java)).
7. Make sure all methods in the PSI element interface access the stub data rather than the PSI tree when appropriate ([example: `Property.getKey()` implementation](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/psi/impl/PropertyImpl.java)).

</procedure>

By default, if a PSI element extends `StubBasedPsiElement`, all elements of that type will be stored in the stub tree.
To have more precise control over which elements are stored, override `IStubElementType.shouldCreateStub()` and return `false` for elements that should not be included in the stub tree.
The exclusion is not recursive: if some elements of the element returning `false` are also stub-based PSI elements, they will be included in the stub tree.


### Serializing Data

For serializing string data in stubs, e.g. element names, use [`StubOutputStream`](%gh-ic%/platform/core-api/src/com/intellij/psi/stubs/StubOutputStream.java) `writeName()` and `readName()`.
These methods ensure that each unique identifier is stored only once in the data stream.
This reduces the size of the serialized stub tree data.
See also [`DataInputOutputUtil`](%gh-ic%/platform/util/src/com/intellij/util/io/DataInputOutputUtil.java).

To change the stored binary format for the stubs (for example, to store some additional data or some new elements), make sure to advance the stub version returned from `IStubFileElementType.getStubVersion()` for the language.
This will cause the stubs and [](#stub-indexes) to be rebuilt, and will avoid mismatches between the stored data format and the code trying to load it.

It is critical to ensure that all information stored in the stub tree depends only on the contents of the file for which stubs are being built, and does not depend on any external files or any other data.
Otherwise, the stub tree will not be rebuilt when external dependencies change, leading to stale and incorrect data in the stub tree.

> Please see also [](indexing_and_psi_stubs.md#improving-indexing-performance).
>

## Stub Indexes

When building the stub tree, the plugin can, at the same time, put some data about the stub elements into a number of indexes, which then can be used to find the PSI elements by the corresponding key.
Unlike file-based indexes, stub indexes do not support storing custom data as values; the value is always a PSI element.
Keys in stub indexes are typically strings (such as class names); other data types are also supported if desired.

A stub index is a class which extends [`AbstractStubIndex`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/stubs/AbstractStubIndex.java).
In the most common case, when the key type is `String`, use a more specific base class, namely [`StringStubIndexExtension`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/stubs/StringStubIndexExtension.java).
Stub index implementation classes are registered in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.stubIndex"/></include>.

To put data into an index, implement `IStubElementType.indexStub()` ([example: `JavaClassElementType.indexStub()`](%gh-ic%/java/java-psi-impl/src/com/intellij/psi/impl/java/stubs/JavaClassElementType.java)).
This method accepts an [`IndexSink`](%gh-ic%/platform/core-api/src/com/intellij/psi/stubs/IndexSink.java) as a parameter and puts in the index ID and the key for each index in which the element should be stored.

### Accessing Stub Indexes

To access the data from an index, the following instance methods are used on the singleton instance managed by the implementation:

#### Keys

`AbstractStubIndex.getAllKeys()/processAllKeys()` returns the list of all keys (processes all keys) in the index for the specified project (for example, the list of all class names found in the project).

> The returned keys may return stale/out-of-date data.
> See [](#elements) to obtain/verify actual existing elements for the given key in a given scope
> (for example, when iterating all keys to collect relevant completion variants).
>
{style="warning" title="Stale Data"}

#### Elements

[`StubIndex.getElements()`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/stubs/StubIndex.java) returns the collection of PSI elements corresponding to a certain key (for example, classes with the specified short name) in the specified scope.

**Example:** [`JavaAnnotationIndex`](%gh-ic%/java/java-indexing-impl/src/com/intellij/psi/impl/java/stubs/index/JavaAnnotationIndex.java)

## Related Forum Discussions

* [Lifecycle of stub creation](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206121959-Lifecycle-of-stub-creation/comments/206143885)
