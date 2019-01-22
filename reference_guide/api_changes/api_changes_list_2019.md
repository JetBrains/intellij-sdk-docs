---
title: Incompatible Changes in IntelliJ Platform and Plugins API 2019.*
---

<!--
See the note on how to document new problems on the main page reference_guide/api_changes_list.md 
-->

<style>
  table {
    width:100%;
  }
  th, tr, td {
    width:50%;
  }
</style>

## Changes in IntelliJ Platform 2019.1

|  Change | How to deal with it |
|---------|---------------------|
| `kotlinx.coroutines.experimental` package removed | Bundled Kotlin library is updated to 1.3 so the plugins must [migrate](https://blog.jetbrains.com/kotlin/2018/09/kotlin-1-3-rc-is-here-migrate-your-coroutines/) to the stable versions of coroutines. |
| The constructor `com.intellij.openapi.vcs.impl.ProjectLevelVcsManagerImpl.<init>(Project, FileStatusManager, FileIndexFacade, ProjectManager, DefaultVcsRootPolicy, VcsFileListenerContextHelper)` has been removed | Use `com.intellij.openapi.vcs.impl.ProjectLevelVcsManagerImpl.<init>(Project, FileStatusManager, FileIndexFacade, ProjectManager, DefaultVcsRootPolicy)` |

## Changes in DataGrip and Database Tools plugin 2019.1

|  Change | How to deal with it |
|---------|---------------------|
| `com.intellij.sql.psi.SqlTokens.SQL_IDENT` field type changed from `com.intellij.sql.psi.impl.SqlTokenType` to `com.intellij.sql.psi.SqlTokenType` | In most of the cases it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type. |
