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
| `kotlinx.coroutines.experimental` package removed | Bundled Kotlin library is updated to 1.3 so the plugins must to [migrate](https://blog.jetbrains.com/kotlin/2018/09/kotlin-1-3-rc-is-here-migrate-your-coroutines/) to the stable versions of coroutines |
