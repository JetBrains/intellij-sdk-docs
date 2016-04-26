---
title: File-based Indexes
---

File-based indexes are based on a Map/Reduce architecture. Each index has a certain type of key and a certain type of value.

The key is what's later used to retrieve data from the index.

*Example:* in the word index the key is the word itself.

The value is arbitrary data which is associated with the key in the index.

*Example:* in the word index the value is a mask indicating in which context the word occurs (code, string literal or comment).

In the simplest case, when we only need to know in what files some data occurs, the value has type `Void` and is not stored in the index.

When the index implementation indexes a file, it receives the content of a file and returns a map from the keys found in the file to the associated values.

When you access the index, you specify the key that you're interested in and get back the list of files in which the key occurs and the value associated with each file.

## Implementing a file based index

A fairly simple file-based index implementation is the [UI Designer bound forms index](upsource:///plugins/ui-designer/src/com/intellij/uiDesigner/binding/FormClassIndex.java). Refer to it as an example to understand this topic better.

Each specific index implementation is a class extending [FileBasedIndexExtension](upsource:///platform/indexing-api/src/com/intellij/util/indexing/FileBasedIndexExtension.java). A file based index should be registered in the `<fileBasedIndex>` extension point.

The implementation of a file based contains of the following main parts:

* `getIndexer()` returns the indexer class, which is is responsible for actually building a set of key/value pairs based on the file content.
* `getKeyDescriptor()` returns the key descriptor, which is responsible for comparing the keys and storing them in a serialized binary format.

   Probably the most commonly used [`KeyDescriptor`](upsource:///platform/util/src/com/intellij/util/io/KeyDescriptor.java) implementation is [`EnumeratorStringDescriptor`](upsource:///platform/util/src/com/intellij/util/io/EnumeratorStringDescriptor.java) which is designed for storing identifiers in an efficient way.
* `getValueExternalizer()` returns the value serializer, which takes care of storing values in a serialized binary format.
* `getInputFilter()` allows to restrict the indexing only to a certain set of files.
* `getVersion()` returns the version of the index implementation. The index is automatically rebuilt if the current version differs from the version of the index implementation used to build the index.

If you don't need to associate any value with the files (i.e. your value type is Void), you can simplify the implementation by using [ScalarIndexExtension](upsource:///platform/indexing-impl/src/com/intellij/util/indexing/ScalarIndexExtension.java) as the base class.

> **Note** The data returned by `DataIndexer.map()` must depend only on input data passed to the method, and must not depend on any external files. Otherwise your index will not be correctly updated when the external data changes, and you will have stale data in your index.

> **Note** Please see `com.intellij.util.indexing.DebugAssertions` on how to enable additional debugging assertions during development to assert correct index implementation.

## Accessing a file based index

Access to file based indexes is performed through the [FileBasedIndex](upsource:///platform/indexing-api/src/com/intellij/util/indexing/FileBasedIndex.java) class.

The following primary operations are supported:

* `getAllKeys()` and `processAllKeys()` allow to obtain the list of all keys found in files which are part of the specified project.

> **Note** The returned data is guaranteed to contain all keys found in up-to-date project content, but may also contain additional keys not currently found in the project.

* `getValues()` allows to obtain all values associated with a specific key but not the files in which they were found.
* `getContainingFiles()` allows to obtain all files in which a specific key was encountered.
* `processValues()` allows to iterate though all files in which a specific key was encountered and to access the associated values at the same time.

> **Note** Nested index access is forbidden as it might lead to deadlock. Collect all necessary data from index A first, then process results while accessing index B.

## Standard indexes

The *IntelliJ Platform* contains a number of the standard file-based indexes. The most useful indexes for plugin developers are:

* Word index
* File name index

Generally, the word index should be accessed indirectly, but using the helper methods in the [`PsiSearchHelper`](upsource:///platform/indexing-api/src/com/intellij/psi/search/PsiSearchHelper.java) class.

The second index is [`FilenameIndex`](upsource:///platform/indexing-impl/src/com/intellij/psi/search/FilenameIndex.java). It provides a quick way to find all files matching a certain file name. [`FileTypeIndex`](upsource:///platform/indexing-impl/src/com/intellij/psi/search/FileTypeIndex.java) serves a similar goal: it allows to quickly find all files of a certain file type.
