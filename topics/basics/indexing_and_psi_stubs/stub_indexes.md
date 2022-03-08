[//]: # (title: Stub Indexes)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Stub Trees

A stub tree is a subset of the PSI tree for a file; it is stored in a compact serialized binary format.
The PSI tree for a file can be backed either by the AST (built by parsing the file) or by the stub tree deserialized from disk.
Switching between the two is transparent.

The stub tree contains only a subset of the nodes.
Typically, it contains only the nodes needed to resolve the declarations contained in this file from external files.
Trying to access any node that is not part of the stub tree or perform any operation that cannot be satisfied by the stub tree, e.g., accessing the text of a PSI element, causes file parsing to switch from the PSI to AST backing.

Each stub in the stub tree is simply a bean class with no behavior.
A stub stores a subset of the corresponding PSI element's state, like the element's name, modifier flags like public or final, etc.
The stub also holds a pointer to its parent in the tree and a list of its children's stubs.

To support stubs for your custom language, you first need to decide which of your PSI tree elements should be stored as stubs.
Typically, you need to have stubs for things like methods or fields visible from other files.
You usually don't need to have stubs for things like statements or local variables, which are not visible externally.

### Implementation

The following steps need to be performed only once for each language that supports stubs:

* Change the file element type for your language (the element type that you return from `ParserDefinition.getFileNodeType()`) to a class that extends [`IStubFileElementType`](upsource:///platform/core-impl/src/com/intellij/psi/tree/IStubFileElementType.java).
* In your <path>plugin.xml</path>, define the `com.intellij.stubElementTypeHolder` extension and specify the interface which contains the `IElementType` constants used by your language's parser as well as `externalIdPrefix` if possible ([example](upsource:///plugins/properties/src/META-INF/plugin.xml)).

For each element type that you want to store in the stub tree, you need to perform the following steps:

* Define an interface for the stub, derived from the [`StubElement`](upsource:///platform/core-api/src/com/intellij/psi/stubs/StubElement.java) interface ([example](upsource:///plugins/properties/properties-psi-api/src/com/intellij/lang/properties/psi/PropertyStub.java)).
* Provide an implementation for the interface ([example](upsource:///plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/psi/impl/PropertyStubImpl.java)).
* Make sure the interface for the PSI element extends [`StubBasedPsiElement`](upsource:///platform/core-api/src/com/intellij/psi/StubBasedPsiElement.java) parameterized by the type of the stub interface ([example](upsource:///plugins/properties/properties-psi-api/src/com/intellij/lang/properties/psi/Property.java)).
* Make sure the implementation class for the PSI element extends [`StubBasedPsiElementBase`](upsource:///platform/core-impl/src/com/intellij/extapi/psi/StubBasedPsiElementBase.java) parameterized by the type of the stub interface ([example](upsource:///plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/psi/impl/PropertyImpl.java)).
  Provide both a constructor that accepts an `ASTNode` and a constructor that accepts a stub.
* Create a class that implements `IStubElementType` and is parameterized with the stub interface and the actual PSI element interface ([example](upsource:///plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/parsing/PropertyStubElementType.java)).
  Implement the `createPsi()` and `createStub()` methods for creating PSI from a stub and vice versa.
  Implement the `serialize()` and `deserialize()` methods for storing the data in a binary stream.
* Use the class implementing `IStubElementType` as the element type constant when parsing ([example](upsource:///plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/parsing/PropertiesElementTypes.java)).
* Make sure all methods in the PSI element interface access the stub data rather than the PSI tree when appropriate ([example: `Property.getKey()` implementation](upsource:///plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/psi/impl/PropertyImpl.java)).

> If you use [Grammar-Kit](https://github.com/JetBrains/Grammar-Kit) to generate your language PSI, see the [Stub indices support](https://github.com/JetBrains/Grammar-Kit/blob/master/HOWTO.md#35-stub-indices-support) section for instructions on integrating your grammar with stubs.
>
{type="note"}

By default, if a PSI element extends `StubBasedPsiElement`, all elements of that type will be stored in the stub tree.
If you need more precise control over which elements are stored, override `IStubElementType.shouldCreateStub()` and return `false` for elements that should not be included in the stub tree.
The exclusion is not recursive: if some elements of the element for which you returned false are also stub-based PSI elements, they will be included in the stub tree.


### Serializing Data

For serializing string data, e.g. element names, in stubs, we recommend to use `StubOutputStream.writeName()` and `StubInputStream.readName()` methods.
These methods ensure that each unique identifier is stored only once in the data stream.
This reduces the size of the serialized stub tree data.
See also [`DataInputOutputUtil`](upsource:///platform/util/src/com/intellij/util/io/DataInputOutputUtil.java).

If you need to change the stored binary format for the stubs (for example, if you want to store some additional data or some new elements), make sure you advance the stub version returned from `IStubFileElementType.getStubVersion()` for your language.
This will cause the stubs and [stub indices](#stub-indexes) to be rebuilt, and will avoid mismatches between the stored data format, and the code trying to load it.

It's essential to ensure that all information stored in the stub tree depends only on the contents of the file for which stubs are being built, and does not depend on any external files.
Otherwise, the stub tree will not be rebuilt when external dependency changes, and you will have stale and incorrect data in the stub tree.

> Please see also [Improving indexing performance](indexing_and_psi_stubs.md#improving-indexing-performance).
>
{type="tip"}

## Stub Indexes

When building the stub tree, you can, at the same time, put some data about the stub elements into a number of indexes, which then can be used to find the PSI elements by the corresponding key.
Unlike file-based indexes, stub indexes do not support storing custom data as values; the value is always a PSI element.
Keys in stub indexes are typically strings (such as class names); other data types are also supported if desired.

A stub index is a class which extends [`AbstractStubIndex`](upsource:///platform/indexing-api/src/com/intellij/psi/stubs/AbstractStubIndex.java).
In the most common case, when the key type is `String`, you use a more specific base class, namely [`StringStubIndexExtension`](upsource:///platform/indexing-api/src/com/intellij/psi/stubs/StringStubIndexExtension.java).
Stub index implementation classes are registered in the `com.intellij.stubIndex` extension point.

To put data into an index, you implement the method `IStubElementType.indexStub()` ([example: `JavaClassElementType.indexStub()`](upsource:///java/java-psi-impl/src/com/intellij/psi/impl/java/stubs/JavaClassElementType.java)).
This method accepts an `IndexSink` as a parameter and puts in the index ID and the key for each index in which the element should be stored.

To access the data from an index, the following two methods are used:

* `AbstractStubIndex.getAllKeys()` returns the list of all keys in the specified index for the specified project (for example, the list of all class names found in the project).
* `AbstractStubIndex.get()` returns the collection of PSI elements corresponding to a certain key (for example, classes with the specified short name) in the specified scope.

## Related Forum Discussions

* [Lifecycle of stub creation](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206121959-Lifecycle-of-stub-creation/comments/206143885)
