---
title: Incompatible Changes in IntelliJ Platform and Plugins API 2019.*
---

<!--

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
See the note on how to document new problems on the main page reference_guide/api_changes_list.md 
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 

-->

## Changes in IntelliJ Platform 2019.2
`java.org.java_websocket` package removed
: Java-WebSocket Library was removed. [Homepage](https://github.com/TooTallNate/Java-WebSocket)

`com.intellij.ide.SaveAndSyncHandlerImpl.refreshOpenFiles()` method removed
: Use `com.intellij.ide.SaveAndSyncHandler.refreshOpenFiles` instead.


## Changes in IntelliJ Platform 2019.1

`kotlinx.coroutines.experimental` package removed 
: Bundled Kotlin library is updated to 1.3 so the plugins must [migrate](https://blog.jetbrains.com/kotlin/2018/09/kotlin-1-3-rc-is-here-migrate-your-coroutines/) to the stable versions of coroutines.

`com.intellij.openapi.vcs.impl.ProjectLevelVcsManagerImpl(Project, FileStatusManager, FileIndexFacade, ProjectManager, DefaultVcsRootPolicy, VcsFileListenerContextHelper)` constructor removed 
: Use `com.intellij.openapi.vcs.impl.ProjectLevelVcsManagerImpl.<init>(Project, FileStatusManager, FileIndexFacade, ProjectManager, DefaultVcsRootPolicy)`.

`com.intellij.injected.editor.DocumentWindow.injectedToHost(int, boolean)` abstract method added
: Implement the method in `DocumentWindow` implementations.

`git4idea.rebase.GitCommitEditingAction.actionPerformed(AnActionEvent)` method marked final
: Implement `actionPerformedAfterChecks` instead of `actionPerformed`.

`git4idea.rebase.GitCommitEditingAction.actionPerformedAfterChecks(AnActionEvent)` abstract method added
: Implement `actionPerformedAfterChecks` instead of `actionPerformed`.

`com.intellij.util.lang.UrlClassLoader.loadPlatformLibrary` method removed
: Use `com.intellij.util.loader.NativeLibraryLoader.loadPlatformLibrary` instead.

`com.intellij.openapi.util.KeyedExtensionCollector.getExtensions()` method marked final
: Remove custom implementation.

## Changes in DataGrip and Database Tools plugin 2019.1

`com.intellij.sql.psi.SqlTokens.SQL_IDENT` field type changed from `com.intellij.sql.psi.impl.SqlTokenType` to `com.intellij.sql.psi.SqlTokenType`
: In most of the cases, it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type.
