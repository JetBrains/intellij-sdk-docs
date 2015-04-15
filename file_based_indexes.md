---
layout: editable
title: File-based Indexes
---

File-based indexes are based on a map/reduce architecture.
Each index has a certain type of key and a certain type of value.
The key is what's later used to retrieve data from the index; for example, in the word index the key is the word itself. The value is arbitrary data which is associated with the key in the index; for example, in the word index the value is a mask indicating in which context the word occurs (code, string literal or comment). In the simplest case (when we only need to know in what files some data occurs), the value has type Void and is not stored in the index.

When the index implementation indexes a file, it receives the content of a file and returns a map from the keys found in the file to the associated values. When the index is accessed, you specify the key that you're interested in and get back the list of files in which the key occurs and the value associated with each file.

### Implementing a File-based Index

A fairly simple file-based index implementation is the [UI Designer bound forms index](https://github.com/JetBrains/intellij-community/blob/master/plugins/ui-designer/src/com/intellij/uiDesigner/binding/FormClassIndex.java). You can refer to it as an example to understand this discussion better.

Each specific index implementation is a class extending [FileBasedIndexExtension](https://github.com/JetBrains/intellij-community/blob/master/platform/indexing-api/src/com/intellij/util/indexing/FileBasedIndexExtension.java),
registered in the <fileBasedIndex>  extension point.
The implementation contains of the following main parts:

*  `getIndexer()` returns the indexer class, which is responsible for actually building a set of key/value pairs based on the file content.

*  `getKeyDescriptor()` returns the key descriptor, which is responsible for comparing the keys and storing them in a serialized binary format. Probably the most commonly used `KeyDescriptor` implementation is `EnumeratorStringDescriptor`  implementation, designed for storing identifiers in an efficient way.

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