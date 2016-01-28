---
title: Indexing and PSI Stubs
---


The indexing framework provides a quick way to locate certain elements, e.g. files containing a certain word or methods with a particular name, in large code bases. Plugin developers can use the existing indexes built by the IDE itself, as well as build and use their own indexes.

It supports two main types of indexes:

* [File-based indexes](/basics/indexing_and_psi_stubs/file_based_indexes.md)
* [Stub indexes](/basics/indexing_and_psi_stubs/stub_indexes.md)

File-based indexes are built directly over the content of files. Stub indexes are built over serialized *stub trees*. A stub tree for a source file is a subset of its PSI tree which contains only externally visible declarations and is serialized in a compact binary format.

Querying a file-based index gets you the set of files matching a certain condition. Querying a stub index gets you the set of matching PSI elements. Therefore, custom language plugin developers should typically use stub indexes in their plugin implementations.
