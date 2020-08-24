---
title: Incompatible Changes in IntelliJ Platform and Plugins API 2018.*
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<!--

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
See the note on how to document new problems on the main page reference_guide/api_changes_list.md 
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 

-->

Please see [Incompatible API Changes](/reference_guide/api_changes_list.md) on how to verify compatibility.

> **NOTE** Changes from API marked with `org.jetbrains.annotations.ApiStatus.@Experimental`/`ScheduledForRemoval` are not listed here, as incompatible changes are to be expected.

## 2018.3 

### Changes in IntelliJ Platform 2018.3

`com.intellij.openapi.externalSystem.action.ExternalSystemAction.isEnabled` method `AnActionEvent` parameter marked `@NotNull`
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.openapi.externalSystem.action.ExternalSystemAction.isVisible` method `AnActionEvent` parameter marked `@NotNull`
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.openapi.actionSystem.AnAction.actionPerformed` method `AnActionEvent` parameter marked `@NotNull`
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.openapi.actionSystem.DataContext.getData` method `dataId` parameter marked `@NotNull`
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.openapi.actionSystem.ToggleAction.isSelected` method `AnActionEvent` parameter marked `@NotNull`
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.openapi.actionSystem.ToggleAction.setSelected` method `AnActionEvent` parameter marked `@NotNull`
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.openapi.actionSystem.DataProvider.getData` method `dataId` parameter marked `@NotNull`
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.psi.meta.PsiMetaData.getDependences` method removed
: Use `com.intellij.psi.meta.PsiMetaData.getDependencies` instead.


## 2018.2
 
### Changes in IntelliJ Platform 2018.2

`com.intellij.util.Query.forEach` method parameter type changed from `Processor<Result>` to `Processor<? super Result>`
: Update `Query` implementations accordingly.

`com.intellij.util.Query.forEachAsync` method parameter type changed from `Processor<Result>` to `Processor<? super Result>`
: Update `Query` implementations accordingly.

`com.intellij.util.QueryExecutor.execute` method parameter type changed from `Processor<Result>` to `Processor<? super Result>`
: Update `QueryExecutor` implementations accordingly.

`com.intellij.openapi.application.QueryExecutorBase.processQuery` method parameter type changed from `Processor<Result>` to `Processor<? super Result>`
: Update `QueryExecutorBase` inheritors accordingly.

`com.intellij.util.InstanceofQuery` class type parameter `T` added
: Set this parameter at call sites to base query type.

`com.intellij.task.ProjectTaskManager.build` method parameter type changed from `Artifact[]` to `ProjectModelBuildableElement[]` 
: In most of the cases, it's enough to recompile the code.

`com.intellij.task.ProjectTaskManager.rebuild` method parameter type changed from `Artifact[]` to `ProjectModelBuildableElement[]` 
: In most of the cases, it's enough to recompile the code.

`org.apache.batik.script.InterpreterFactory.createInterpreter` abstract method added 
: Update `InterpreterFactory` implementations accordingly.


## 2018.1 

### Changes in IntelliJ Platform 2018.1

`com.google.common.base.Objects.ToStringHelper` class removed
: Use `com.google.common.base.MoreObjects.ToStringHelper` instead.

`com.google.common.util.concurrent.Futures.get` method removed
: Use `com.google.common.util.concurrent.Futures.getChecked` instead.

[`org.tmatesoft.svn`](https://svnkit.com/index.html) package removed 
: Use classes from `org.jetbrains.idea.svn` instead.

`org.jetbrains.idea.svn.svnkit.SvnKitManager` class removed
: Use `org.jetbrains.idea.svn.api.ClientFactory` instead.

`clojure` package removed 
: Clojure 1.8 jars are removed from IntelliJ Ultimate package, provide your own jars if needed.

`com.intellij.ide.structureView.newStructureView.StructureViewComponent.getTreeBuilder` method removed 
: Use `queueUpdate` and `getTree` instead.

### Changes in PhpStorm and PHP Plugin 2018.1

`com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.DOC_COMMENT` field type changed from `com.intellij.psi.tree.ILazyParseableElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocCommentElementType` 
: In most of the cases, it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type.

`com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.phpDocSpecialTag` field type changed from `com.intellij.psi.tree.ILazyParseableElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocMethodTagElementType`
: In most of the cases, it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type.

`com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.phpDocTag` field type changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocTagElementType` 
: In most of the cases, it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type.

`com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.phpDocReturn` field type changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocTagElementType`
: In most of the cases, it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type.

`com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.phpDocMixin` field type changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocTagElementType`
: In most of the cases, it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type.

`com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.phpDocThrows` field type changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocTagElementType`
: In most of the cases, it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type.

`com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.phpDocParam` field type changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocTagElementType`
: In most of the cases, it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type.

`com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.phpDocInlineTag` field type changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocTagElementType`
: In most of the cases, it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type.

`com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.phpDocMethod` field type changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocMethodElementType`
: In most of the cases, it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type.

`com.jetbrains.php.lang.documentation.phpdoc.parser.PhpDocElementTypes.phpDocProperty` field type changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocElementType` to `com.jetbrains.php.lang.documentation.phpdoc.psi.stubs.PhpDocPropertyElementType`
: In most of the cases, it's enough to recompile the code. It may also be needed to check that the code doesn't rely on the field's type.

`com.jetbrains.php.lang.documentation.phpdoc.parser.tags.PhpDocTagParser.getElementType` method return type changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.PhpDocElementType` to `com.intellij.psi.tree.IElementType`
: In most of the cases, it's enough to recompile the code. It's also necessary to change the return type of subclasses.

`com.jetbrains.php.lang.documentation.phpdoc.psi.impl.PhpDocCommentImpl` superclass changed from `com.intellij.psi.impl.source.tree.LazyParseablePsiElement` to `com.jetbrains.php.lang.psi.elements.impl.PhpPsiElementImpl`
: It's necessary to check that the code doesn't rely on the superclass.

`com.jetbrains.php.lang.documentation.phpdoc.psi.impl.tags.PhpDocTagImpl` superclass changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.impl.PhpDocPsiElementImpl` to `com.jetbrains.php.lang.psi.elements.impl.PhpTypedElementImpl`
: It's necessary to check that the code doesn't rely on the superclass. The change also affects all the inheritors of `PhpDocTagImpl`.

`com.jetbrains.php.lang.documentation.phpdoc.psi.impl.tags.PhpDocMethodTagImpl` superclass changed from `com.intellij.psi.impl.source.tree.LazyParseablePsiElement` to `com.jetbrains.php.lang.documentation.phpdoc.psi.impl.tags.PhpDocTagImpl`
: It's necessary to check that the code doesn't rely on the superclass.

`com.jetbrains.php.lang.documentation.phpdoc.psi.impl.tags.PhpDocMethodImpl` superclass changed from `com.jetbrains.php.lang.documentation.phpdoc.psi.impl.PhpDocPsiElementImpl` to `com.jetbrains.php.lang.psi.elements.impl.PhpNamedElementImpl`
: It's necessary to check that the code doesn't rely on the superclass.

