---
title: Incompatible Changes in IntelliJ Platform and Plugins API 2017.*
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<!--
See the note on how to document new problems on the main page reference_guide/api_changes_list.md 
-->

Please see [Incompatible API Changes](/reference_guide/api_changes_list.md) on how to verify compatibility.

> **NOTE** Changes from API marked with `org.jetbrains.annotations.ApiStatus.@Experimental`/`ScheduledForRemoval` are not listed here, as incompatible changes are to be expected.

## 2017.3 

### Changes in IntelliJ Platform 2017.3

`com.intellij.internal.statistic.AbstractApplicationUsagesCollector` class removed
: This class isn't supposed to be used in regular plugins. Override `com.intellij.internal.statistic.AbstractProjectsUsagesCollector` instead if you're developing an IDE with its own statistics services. 
 
`com.intellij.internal.statistic.UsagesCollector.doPersistProjectUsages` method removed 
: This method isn't supposed to be used in regular plugins. There is no need to call this method anymore.
 
`org.apache.sanselan` package removed
: Use classes from `org.apache.commons.imaging` instead.
 
`com.intellij.psi.MultiplePsiFilesPerDocumentFileViewProvider.getLanguages` abstract method added 
: Implementation of this class are supposed to have several languages so you need to implement this method explicitly and return them all. 
  
`org.jetbrains.kotlin.idea.configuration.KotlinProjectConfigurator.changeCoroutineConfiguration` abstract method added 
: You need to implement this method and add the logic for updating the configuration in your build system. 
  
`org.jetbrains.kotlin.idea.configuration.KotlinProjectConfigurator.updateLanguageVersion` abstract method added 
: You need to implement this method and add the logic for updating the configuration in your build system.

`org.jetbrains.kotlin.idea.configuration.KotlinProjectConfigurator.addLibraryDependency` abstract method added
: You need to implement this method and add the logic for updating the configuration in your build system.

### Changes in DataGrip and Database Tools Plugin 2017.3

`com.intellij.database.dataSource.DataSourceManager` class removed
: Use `com.intellij.database.psi.DbPsiFacade` instead.

`com.intellij.database.dataSource.DataSourceManagerEx` class removed
: Use `com.intellij.database.psi.DbPsiFacade` instead.

`com.intellij.database.dataSource.DataSource` class removed
: Use `com.intellij.database.psi.DbDataSource` and `com.intellij.database.model.DatabaseSystem` instead.

`com.intellij.database.psi.DbDataSource.getModel` method return type changed from `DbElement`-based model to `DasObject`-based model
: Use `com.intellij.database.psi.DbPsiFacade.findElement` to get the corresponding `com.intellij.database.psi.DbElement` wrapper when needed.

`com.intellij.database.view.DatabaseView.getTreeBuilder` method removed
: Use `LangDataKeys.PSI_ELEMENT_ARRAY.get(event)` to get Database view selection.

### Changes in PhpStorm and PHP Plugin 2017.3

`com.jetbrains.php.lang.psi.elements.Function.getReturnType()` method return type changed from `PsiElement` to `PhpReturnType`
: Before method had been returning a `com.jetbrains.php.lang.psi.elements.ClassReference`. Now method returns `com.jetbrains.php.lang.psi.elements.PhpReturnType`. Method `ReturnType.getClassReference()` can be used if you need just a `ClassReference`. If you need to get the `PhpType`, use `com.jetbrains.php.lang.psi.elements.Function.getReturnType.getType()` method instead.