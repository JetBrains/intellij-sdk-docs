<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Version Control Systems

<link-summary>Overview of the Version Control Integration API allowing to implement a custom Version Control System support.</link-summary>

<tldr>

**Sample Plugins**: [OSS plugins providing VCS](https://jb.gg/ipe?extensions=com.intellij.vcs)

**VCS Extension Points**: [](intellij_platform_extension_point_list.md#version-control)

**Bundled VCS Plugins Extension Points**: [](intellij_community_plugins_extension_point_list.md#vcs-plugins)

</tldr>

This page gives an overview of the Version Control Integration API.

## Key Concepts

### `FilePath`

A [`FilePath`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/vcs/FilePath.java) represents a path to a file or directory on disk or in the VCS repository.
Unlike a [virtual file](virtual_file.md), a `FilePath` can represent a path to a file which doesn't exist on disk.
The main difference between a `FilePath` and a [`java.io.File`](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/io/File.html) is that a `FilePath` caches the `VirtualFile` corresponding to the path, so it can be retrieved without doing a VFS search.

To create instances of `FilePath`, the [`VcsContextFactory`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/actions/VcsContextFactory.java) API is used.

`FilePath` representing paths in a VCS repository, rather than local paths, are created using `VcsContextFactory.createFilePathOnNonLocal()`.
The `FilePath.isNonLocal()` method returns `true` for such files.

### `VcsRevisionNumber`

A [`VcsRevisionNumber`](%gh-ic%/platform/vcs-api/vcs-api-core/src/com/intellij/openapi/vcs/history/VcsRevisionNumber.java) represents a revision number of the file.
If the VCS stores revision numbers as simple integers, the standard [`VcsRevisionNumber`](%gh-ic%/platform/vcs-api/vcs-api-core/src/com/intellij/openapi/vcs/history/VcsRevisionNumber.java) `Int` implementation can be used.
If the VCS has a more complex format of revision numbers (like CVS, which uses a series of numbers delimited with dots), the plugin can provide a custom implementation.

### `ContentRevision`

A [`ContentRevision`](%gh-ic%/platform/vcs-api/vcs-api-core/src/com/intellij/openapi/vcs/changes/ContentRevision.java) represents a particular revision of a file, which exists either locally or in a VCS repository.
It has three main attributes:

* `FilePath` specifying the file of which this is a revision.
  If some version of the file exists locally, this should be a local path.
* [`VcsRevisionNumber`](%gh-ic%/platform/vcs-api/vcs-api-core/src/com/intellij/openapi/vcs/history/VcsRevisionNumber.java) specifying the revision number of the revision, or `VcsRevisionNumber.NULL` if the revision exists only locally.
* Content of the revision.

The content is returned as string, and the VCS plugin is responsible for converting the binary file content to correct encoding.
To detect the encoding automatically based on the IDE settings and the byte order mark, the method `CharsetToolkit.bytesToString()` can be used (this API is new in IDEA 7.0.2).
Revisions of binary files can also be represented as [`BinaryContentRevision`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/BinaryContentRevision.java).
For binary revisions, the result of `getContent()` is undefined, and `getBinaryContent()` can be used to retrieve the contents as a byte array.

A useful class which can be used to represent the current on-disk version of a particular file is [`CurrentContentRevision`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/CurrentContentRevision.java).

### `FileStatus`

A [`FileStatus`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/vcs/FileStatus.java) represents a status of a file in regard to VCS (unversioned, not changed, added, modified and so on).
It determines the color used to render the name of the file in the UI.

### `Change`

A [`Change`](%gh-ic%/platform/vcs-api/vcs-api-core/src/com/intellij/openapi/vcs/changes/Change.java) represents a single file operation (creation, modification, move/rename or deletion) from a VCS point of view.
A Change can represent either a modification which the user has performed locally and not yet committed, a committed modification, or some other type of modification (for example, a shelved change or a difference between two arbitrary revisions).

A `Change` essentially consists of two content revisions:

* before revision (`null` if the `Change` represents file creation)
* after revision (`null` if the `Change` represents file deletion)

A move or rename is represented by a `Change` where the before revision and the after revision have different file paths.
A custom file status can be specified for a `Change` if it represents a non-standard modification of the file (for example, a file which has been merged with conflicts).
If a custom file status has not been specified, the status is calculated automatically from the change type.

### `ChangeList`

A [`ChangeList`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/ChangeList.java) represents a named group of related changes.
There are two main kinds of changelists:

* [`LocalChangeList`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/LocalChangeList.java) represents a group of modifications done by a user locally.
  If the VCS also supports the concept of changelists (like Perforce does), the VCS plugin can synchronize the IDE's local changelist structure with that of the VCS.
  Otherwise, a local changelist is simply a subset of the files checked out or modified by the user.
* [`CommittedChangeList`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/versionBrowser/CommittedChangeList.java) represents a set of modifications checked in to the VCS repository.
  For VCSes which support atomic commit, every committed revision is represented by a `CommittedChangeList`.
  For VCSes which use per-file commit (like CVS), the plugin can use heuristics to group a sequence of individual file commits into a `CommittedChangeList`.

> The <control>Unversioned Files</control>, <control>Locally Deleted Files</control>, etc., nodes in the <control>Changes</control> view are not actually change lists, and files under those nodes are not represented by `ChangeList` objects.
>
{style="note"}

## Plugin Components

This section describes the different components which comprise a VCS integration plugin, roughly in the same order as they should be implemented.

### `AbstractVcs`

This is the main entry point for a VCS plugin, which is used by the IntelliJ Platform to retrieve all other services provided by the plugin.
Register `AbstractVcs` implementation in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.vcs"/></include> in <path>[plugin.xml](plugin_configuration_file.md)</path>, as shown in the following example:

```xml
<idea-plugin>
  ...
  <extensions defaultExtensionNs="com.intellij">
    <vcs name="svn" vcsClass="org.jetbrains.idea.svn.SvnVcs"/>
  </extensions>
</idea-plugin>
```

Here `name` is the unique name of the VCS (this must match the string returned by your implementation of `AbstractVcs.getName()`), and `vcsClass` is your implementation class.

### `ChangeProvider`

[`ChangeProvider`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/ChangeProvider.java) is responsible for tracking user changes to the working copy, and reporting these changes to the IntelliJ Platform core.
An implementation of this class is returned from [`AbstractVcs.getChangeProvider()`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/AbstractVcs.java).

The ChangeProvider works in tandem with
[`VcsDirtyScopeManager`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/VcsDirtyScopeManager.java)
.
It keeps track of the 'dirty scope': the set of files for which the VCS file status may be out of date.
Files are added to the dirty scope either when they are modified on disk, or when their VCS status is invalidated by an explicit call to
[`VcsDirtyScopeManager.fileDirty()`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/VcsDirtyScopeManager.java)
or
[`VcsDirtyScopeManager.dirDirtyRecursively()`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/VcsDirtyScopeManager.java).

After some files have been added to the dirty scope, the dirty scope is passed to `ChangeProvider.getChanges()`, along with a
[`ChangelistBuilder`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/ChangelistBuilder.java)
instance, which serves as a sink to which the `ChangeProvider` feeds the data about the changed files.
This processing happens asynchronously in a background thread.

The `ChangeProvider` can either iterate all files under the dirty scope using
[`VcsDirtyScope.iterate()`](%gh-ic%/platform/vcs-api/src/com/intellij/openapi/vcs/changes/VcsDirtyScope.java)
, or retrieve information about its contents using the `getDirtyFiles()` and `getDirtyDirectoriesRecursively()` methods.
If it is possible to retrieve the information about the local changes from the VCS in batch, it's strongly preferable to use the second method, as it scales much better for large working copies.

The `ChangeProvider` reports data to `ChangelistBuilder` using the following methods:

* `processChange()` is called for files which have been checked out (or modified if the VCS doesn't use an explicit checkout model), scheduled for addition or deletion, moved or renamed.
* `processUnversionedFile()` is called for files which exist on disk, but are not managed by the VCS, not scheduled for addition, and not ignored through <path>.cvsignore</path> or a similar mechanism.
* `processLocallyDeletedFile()` is called for files which exist in the VCS repository, but do not exist on disk and are not scheduled for deletion.
* `processIgnoredFile()` is called for files which are not managed by the VCS but are ignored through <path>.cvsignore</path> or a similar mechanism.
* `processSwitchedFile()` is called for files or directories for which the working copy corresponds to a different branch compared to the working copy of their parent directory.
  This can be called for the same files for which `processSwitchedFile()` has already been called.
