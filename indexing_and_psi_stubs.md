---
title: Indexing and PSI Stubs
---

<!--
INITIAL_SOURCE https://confluence.jetbrains.com/display/IDEADEV/Indexing+and+PSI+Stubs+in+IntelliJ+IDEA
-->

# {{ page.title }}


## Introduction

The indexing framework provides a quick way to locate certain elements (for example, files containing a certain word or methods with a particular name) in large code bases. Plugin developers can use the existing indexes built by IntelliJ IDEA itself, as well as build and use their own indexes.

It supports two main types of indexes: *file-based indexes*  and *stub indexes*. File-based indexes are built directly over the content of files, and stub indexes are built over serialized *stub trees*. A stub tree for a source file is a subset of its PSI tree which contains only externally visible declarations and is serialized in a compact binary format. Querying a file-based index gets you the set of files matching a certain condition, and querying a stub index gets you the set of matching PSI elements. Therefore, custom language plugin developers should typically use stub indexes in their plugin implementations.

## File-based Indexes

File-based indexes are based on a map/reduce architecture. Each index has a certain type of key and a certain type of value. The key is what's later used to retrieve data from the index; for example, in the word index the key is the word itself. The value is arbitrary data which is associated with the key in the index; for example, in the word index the value is a mask indicating in which context the word occurs (code, string literal or comment). In the simplest case (when we only need to know in what files some data occurs), the value has type Void and is not stored in the index.

When the index implementation indexes a file, it receives the content of a file and returns a map from the keys found in the file to the associated values. When the index is accessed, you specify the key that you're interested in and get back the list of files in which the key occurs and the value associated with each file.

### Implementing a File-based Index

A fairly simple file-based index implementation is the [UI Designer bound forms index](https://github.com/JetBrains/intellij-community/blob/master/plugins/ui-designer/src/com/intellij/uiDesigner/binding/FormClassIndex.java). You can refer to it as an example to understand this discussion better.

Each specific index implementation is a class extending [FileBasedIndexExtension](https://github.com/JetBrains/intellij-community/blob/master/platform/indexing-api/src/com/intellij/util/indexing/FileBasedIndexExtension.java),
registered in the <fileBasedIndex>  extension point.
The implementation contains of the following main parts:

*  `getIndexer()` returns the indexer class, which is responsible for actually building a set of key/value pairs based on the file content.

*  `getKeyDescriptor()` returns the key descriptor, which is responsible for comparing the keys and storing them in a serialized binary format. Probably the most commonly used KeyDescriptor implementation is `EnumeratorStringDescriptor`  implementation, designed for storing identifiers in an efficient way.

*  `getValueExternalizer()` returns the value serializer, which takes care of storing values in a serialized binary format.

*  `getInputFilter()`  allows to restrict the indexing only to a certain set of files.

*  `getVersion()`  returns the version of the index implementation. The index is automatically rebuilt if the current version differs from the version of the index implementation used to build the index.

If you don't need to associate any value with the files (i.e. your value type is Void), you can simplify the implementation by using [ScalarIndexExtension](https://github.com/JetBrains/intellij-community/blob/master/platform/indexing-impl/src/com/intellij/util/indexing/ScalarIndexExtension.java) as the base class.

Note that the data returned by ```DataIndexer.map()``` must depend only on input data passed to the method, and must not depend on any external files. Otherwise your index will not be correctly updated when the external data changes, and you will have stale data in your index.

### Accessing a File-based Index

Access to file-based indexes is performed through the *[FileBasedIndex](https://github.com/JetBrains/intellij-community/blob/master/platform/indexing-api/src/com/intellij/util/indexing/FileBasedIndex.java)*  class. The following primary operations are supported:

*  `getAllKeys()`  and `processAllKeys()`  allow to obtain the list of all keys found in files which are part of the specified project. Note that the returned data is guaranteed to contain all keys found in up-to-date project content, but may also contain additional keys not currently found in the project.

*  `getValues()` allows to obtain all values associated with a specific key (but not the files in which they were found);

*  `getContainingFiles()` allows to obtain all files in which a specific key was encountered;

*  `processValues()` allows to iterate though all files in which a specific key was encountered and to access the associated values at the same time.

### Standard Indexes

A number of the standard file-based indexes contained in the IntelliJ Platform are often useful for plugin developers. The first of them is the above-mentioned *word index*. This should generally accessed not directly, but using the helper methods in the *[PsiSearchHelper](https://github.com/JetBrains/intellij-community/blob/master/platform/indexing-api/src/com/intellij/psi/search/PsiSearchHelper.java)*  class.

The second is [FilenameIndex](https://github.com/JetBrains/intellij-community/blob/master/platform/indexing-impl/src/com/intellij/psi/search/FilenameIndex.java).
It provides a quick way to find all files matching a certain file name.
[FileTypeIndex](https://github.com/JetBrains/intellij-community/blob/master/platform/indexing-impl/src/com/intellij/psi/search/FileTypeIndex.java)
serves a similar goal: it allows to quickly find all files of a certain file type.

## Stub Trees

As mentioned above, a stub tree is a subset of the PSI tree for a file which is stored in a compact serialized binary format. Actually the PSI tree for a file can be backed either by the AST (built by parsing the text of the file) or by the stub tree (deserialized from disk); switching between the two is transparent. The stub tree contains only a subset of the nodes (typically only the nodes that are needed to resolve the declarations contained in this file from external files). Trying to access any node which is not part of the stub tree, or to perform any operation which cannot be satisfied by the stub tree (such as accessing the text of a PSI element), causes the file to be parsed and the PSI to switch to AST backing.

Each stub in the stub tree is simply a bean class with no behavior, which stores a subset of the state of the corresponding PSI element (for example, its name, modifier flags like public or final, etc.) The stub also holds a pointer to its parent in the tree and a list of its children stubs.

To support stubs for your custom language, you first need to decide which of the elements of your PSI tree should be stored as stubs. Typically you need to have stubs for things like methods or fields, which are visible from other files, and don't need to have stubs for things like statements or local variables, which are not visible externally.

For each element type that you want to store in the stub tree, you need to perform the following steps:

*  Define an interface for the stub, derived from the `StubElement` interface ([example](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-api/src/com/intellij/lang/properties/psi/PropertyStub.java)).

*  Provide an implementation for the interface ([example](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/psi/impl/PropertyStubImpl.java)).

*  Make sure that the interface for the PSI element extends `StubBasedPsiElement` parameterized by the type of the stub interface ([example](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-api/src/com/intellij/lang/properties/psi/Property.java)).

*  Make sure that the implementation class for the PSI element extends `StubBasedPsiElementBase` parameterized by the type of the stub interface ([example](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/com/intellij/lang/properties/psi/impl/PropertyImpl.java#L45)). Provide both a constructor that accepts an ASTNode and a constructor which accepts a stub.

*  Create a class which implements `IStubElementType` and is parameterized with the stub interface and the actual PSI element interface ([example](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/parsing/PropertyStubElementType.java)). Implement the createPsi() and createStub() methods for creating PSI from a stub and vice versa. Implement the serialize() and deserialize() methods for storing the data in a binary stream.

*  Use the class implementing `IStubElementType` as the element type constant when parsing ([example](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/parsing/PropertiesElementTypes.java))

*  Make sure that all methods in the PSI element interface access the stub data rather than the PSI tree when appropriate ([example: Property.getKey() implementation](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/psi/impl/PropertyImpl.java#L95))

The following steps need to be performed only once for each language that supports stubs:

*  Change the file element type for your language (the element type that you return from ```ParserDefinition.getFileNodeType()```) to a class that extends IStubFileElementType.

*  In your plugin.xml, define the ```<stubElementTypeHolder>``` extension and specify the interface which contains the `IElementType` constants used by your language's parser ([example](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/META-INF/plugin.xml#L55)).

For serializing string data in stubs (such as element names), it's recommended to use ```StubOutputStream.writeName()``` and ```StubInputStream.readName()``` methods. These methods ensure that each unique identifier is stored only once in the data stream, and thus reduce the size of the serialized stub tree data.

If you need to change the stored binary format for the stubs (for example, if you want to store some additional data or some new elements), make sure that you advance the stub version returned from `IStubFileElementType.getStubVersion()` for your language. This will cause the stubs and stub indices to be rebuilt, and will avoid mismatches between the stored data format and the code trying to load it.

By default, if a PSI element extends `StubBasedPsiElement`, all elements of that type will be stored in the stub tree. If you need more precise control over which elements are stored, override `IStubElementType.shouldCreateStub()` and return `false` for elements which should not be included in the stub tree. Note that the exclusion is not recursive: if some elements of the element for which you returned false are also stub-based PSI elements, they will be included in the stub tree.

It's essential to make sure that all information stored in the stub tree depends only on the contents of the file for which stubs are being built, and does not depend on any external files. Otherwise the stub tree will not be rebuilt when an external dependency changes, and you will have stale and incorrect data in the stub tree.

## Stub Indexes

When building the stub tree, you can at the same time put some data about the stub elements into a number of indexes, which then can be used to find the PSI elements by the corresponding key. Unlike file-based indexes, stub indexes do not support storing custom data as values; the value is always a PSI element. Keys in stub indexes are normally strings (such as class names); other data types are also supported if desired.

A stub index is a class which extends [AbstractStubIndex](https://github.com/JetBrains/intellij-community/blob/master/platform/indexing-api/src/com/intellij/psi/stubs/AbstractStubIndex.java). In the most common case, when the key type is String, you use a more specific base class, namely [StringStubIndexExtension](https://github.com/JetBrains/intellij-community/blob/master/platform/indexing-api/src/com/intellij/psi/stubs/StringStubIndexExtension.java).
Stub index implementation classes are registered in the ```<stubIndex>``` extension point.

To put data into an index, you implement the method ```IStubElementType.indexStub()``` ([example: JavaClassElementType.indexStub()](https://github.com/JetBrains/intellij-community/blob/master/java/java-psi-impl/src/com/intellij/psi/impl/java/stubs/JavaClassElementType.java#L189)). This method accepts an ```IndexSink``` as a parameter, and puts in the index ID and the key for each index in which the element should be stored.

To access the data from an index, the following two methods are used:

*  `AbstractStubIndex.getAllKeys()` returns the list all keys in the specified index for the specified project (for example, the list of all class names found in the project);

*  `AbstractStubIndex.get()` returns the collection of PSI elements corresponding to a certain key (for example, classes with the specified short name) in the specified scope.

### Related Forum Discussions

*  [Lifecycle of stub creation](http://devnet.jetbrains.com/message/5485343)

