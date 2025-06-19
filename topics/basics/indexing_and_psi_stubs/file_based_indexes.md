<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# File-Based Indexes

<link-summary>Introduction to file-based indexes allowing to store information about presence of some values in files, and accessing it by keys in a performant way.</link-summary>

File-based indexes are based on a [Map/Reduce architecture](https://en.wikipedia.org/wiki/MapReduce).
Each index has a specific type of key and a particular type of value.

The key is what's later used to retrieve data from the index.

*Example:* in the word index, the key is the word itself.

The value is arbitrary data, which is associated with the key in the index.

*Example:* in the word index, the value is a mask indicating in which context the word occurs (code, string literal, or comment).

In the simplest case, when one needs to know in what files some data is present, the value has type `Void` and is not stored in the index.

When the index implementation indexes a file, it receives a file's content and returns a map from the keys found in the file to the associated values.

When accessing an index, specify the key you're interested in and get back the list of files in which the key occurs, and the value associated with each file.

> In some cases, using [Gists](indexing_and_psi_stubs.md#gists) can be considered as an alternative.
>

## Implementing a File-Based Index

> A relatively simple file-based index implementation is the [UI Designer bound forms index](%gh-ic%/plugins/ui-designer/src/com/intellij/uiDesigner/binding/FormClassIndex.java), storing FQN of bound implementation class for [GUI Designer](https://www.jetbrains.com/help/idea/gui-designer-basics.html) <path>.form</path> files.
>

Each specific index implementation is a class extending [`FileBasedIndexExtension`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/FileBasedIndexExtension.java) registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.fileBasedIndex"/></include>.

An implementation of a file-based index consists of the following main parts:

* `getIndexer()` returns the [`DataIndexer`](%gh-ic%/platform/util/src/com/intellij/util/indexing/DataIndexer.java) implementation actually responsible for building a set of key/value pairs based on file content.
* `getKeyDescriptor()` returns the [`KeyDescriptor`](%gh-ic%/platform/util/src/com/intellij/util/io/KeyDescriptor.java) responsible for comparing keys and storing them in a serialized binary format.
   Probably the most commonly used implementation is [`EnumeratorStringDescriptor`](%gh-ic%/platform/util/src/com/intellij/util/io/EnumeratorStringDescriptor.java), which is designed for storing identifiers efficiently.
* `getValueExternalizer()` returns the [`DataExternalizer`](%gh-ic%/platform/util/src/com/intellij/util/io/DataExternalizer.java) responsible for storing values in a serialized binary format.
* `getInputFilter()` allows restricting the indexing only to a certain set of files.
  Consider using [`DefaultFileTypeSpecificInputFilter`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/DefaultFileTypeSpecificInputFilter.java).
* `getName()` returns a unique index ID.
  Consider using fully qualified index class name to not clash with other plugins defining index with the same ID, e.g.,&nbsp;`com.example.myplugin.indexing.MyIndex`.
* `getVersion()` returns the version of the index implementation.
  The index is automatically rebuilt if the current version differs from the version of the index implementation used to build it.

If there's no value to associate with the files (i.e., value type is `Void`), simplify the implementation by extending [`ScalarIndexExtension`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/ScalarIndexExtension.java).
In case of single value per file, extend from [`SingleEntryFileBasedIndexExtension`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/SingleEntryFileBasedIndexExtension.java).

Please see also [Improving indexing performance](indexing_and_psi_stubs.md#improving-indexing-performance).

> **Critical Implementation Notes**
>
> Value class must implement `equals()` and `hashCode()` properly, so a value deserialized from binary data should be equal to original one.
>
> The data returned by `DataIndexer.map()` must depend only on input data passed to the method, and must not depend on any external files.
> Otherwise, your index will not be correctly updated when the external data changes, and you will have stale data in your index.
>
> Please set system property `intellij.idea.indices.debug`/`intellij.idea.indices.debug.extra.sanity` to `true` to enable additional debugging assertions during development to assert correct index implementation.
>
{style="warning"}

## Accessing a File-Based Index

Access to file-based indexes is performed through the [`FileBasedIndex`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/FileBasedIndex.java) class.

> Please note index access is restricted during [dumb mode](indexing_and_psi_stubs.md#dumb-mode).
>
{style="note"}

The following primary operations are supported:

* `getAllKeys()` and `processAllKeys()` allow obtaining the list of all keys found in files, which are a part of the specified project.
  To optimize performance, consider returning `true` from `FileBasedIndexExtension.traceKeyHashToVirtualFileMapping()` (see its Javadoc for details).

> The returned data is guaranteed to contain all keys found in up-to-date project content, but may also include additional keys not currently found in the project.
>
{style="note"}

* `getValues()` allows to get all values associated with a specific key but not the files in which they were found.
* `getContainingFiles()` allows collecting all files in which a particular key was encountered.
* `processValues()` allows iterating through all files in which a specific key was encountered and accessing the associated values simultaneously.

### Nested Index Access

When accessing index data in nested calls (usually from multiple indexes), limitations might apply.

<tabs>

<tab title="2023.1 and later">

~~Nested index access is now possible.~~

**NOTE: Please do not use yet** This is known to cause problems under certain conditions, please watch this [issue](https://youtrack.jetbrains.com/issue/IJPL-265/Nested-index-lookups-still-leads-to-deadlocks).

</tab>

<tab title="2022.3 and earlier">

> Nested index access is forbidden as it might lead to a deadlock.
> Collect all necessary data from index _A_ first, then process results while accessing index _B_.
>
{style="warning"}

</tab>
</tabs>

## Standard Indexes

The IntelliJ Platform contains several standard file-based indexes.
The most useful indexes for plugin developers are:

### Word Index
Generally, the word index should be accessed indirectly by using helper methods of the [`PsiSearchHelper`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/PsiSearchHelper.java) class.

### File Name Index
[`FilenameIndex`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/FilenameIndex.java) provides a quick way to find all files matching a specific file name.

### File Type Index
[`FileTypeIndex`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/FileTypeIndex.java) serves a similar goal: it allows to find all files of a particular [`FileType`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileTypes/FileType.java) quickly.

## Additional Index Roots

To add additional files/directories to be indexed, implement [`IndexableSetContributor`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/IndexableSetContributor.java)
and register in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.indexedRootsProvider"/></include>.
