---
title: Incompatible Changes in IntelliJ Platform and Plugins API 2020.*
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<!--
Before documenting a breaking API change, please, make sure that the change cannot be avoided 
in an alternative way.

APIs marked with @ApiStatus.Experimental, @ApiStatus.Internal or @ApiStatus.ScheduledForRemoval don't need to be documented.

To document a new incompatible change, add a new line with the problem pattern
followed by a 2nd line with ": "-prefixed human-readable description and recommended fix/action.

The following problem patterns are supported:

<package name> package removed
<class name> class removed
<class name> class renamed to <new class name>

<class name>.<method name>(<human-readable parameters>) method removed
<class name>.<method name>(<human-readable parameters>) method return type changed from <before> to <after>
<class name>.<method name>(<human-readable parameters>) method parameter <type> removed
<class name>.<method name>(<human-readable parameters>) method parameter type changed from <before> to <after>
<class name>.<method name>(<human-readable parameters>) method visibility changed from <before> to <after>
<class name>.<method name>(<human-readable parameters>) method marked final
<class name> (class|interface) now (extends|implements) <class name> and inherits its final method <method name>(<human-readable parameters>)?
<class name> (class|interface) now (extends|implements) <class name> and inherits its abstract method <method name>(<human-readable parameters>)?
<class name>.<method name> method <parameter name> parameter marked @<class name>

<class name>(<human-readable parameters>) constructor removed
<class name>(<human-readable parameters>) constructor parameter <type> removed
<class name>(<human-readable parameters>) constructor parameter type changed from <before> to <after>
<class name>(<human-readable parameters>) constructor visibility changed from <before> to <after>

<class name>.<field name> field removed
<class name>.<field name> field type changed from <before> to <after>
<class name>.<field name> field visibility changed from <before> to <after>

<class name>.<method name>(<human-readable parameters>) marked abstract
<class name>.<method name>(<human-readable parameters>) abstract method added
<class name> class moved to package <package name>

<property name> property removed from resource bundle <bundle name>

where the placeholders must be enclosed in code quotes (`name`):

<class name> is a fully-qualified name of the class, e.g. `com.intellij.openapi.actionSystem.AnAction$InnerClass`.
<method name> is the exact method's name. Note that constructors have dedicated patterns.
<human-readable parameters> is a string representing parameters, which are not necessarily fully qualified. They do not affect the parser. For example, instead of (java.lang.Object, java.util.List, int) you are free to write (Object, List<String>, int)
<parameter name> is exact name of the method's parameter
<property name> is a full name of a property from .properties file, like `some.action.description`
<bundle name> is a fully qualified name of the property bundle, which includes its package, like `message.IdeBundle`

NOTE: If a code change you're trying to document doesn't match any of the above patterns, fill in a ticket in the YouTrack. 
An example of a ticket is https://youtrack.jetbrains.com/issue/PR-1218. Until supported, you may document the change as you prefer, and I will correct it later.

NOTE: You are allowed to prettify the pattern using links: [`org.example.Foo`](https://github.com/JetBrains/intellij-community/tree/master/)

NOTE: Entries not starting with code quotes (`name`) can be added to document non-code changes and will be skipped in API verification.
-->

Please see [Incompatible API Changes](/reference_guide/api_changes_list.md) on how to verify compatibility.

> **NOTE** Changes from API marked with [`org.jetbrains.annotations.ApiStatus.@Experimental/ScheduledForRemoval`](upsource:///platform/util/src/org/jetbrains/annotations/ApiStatus.java) are not listed here, as incompatible changes are to be expected.

# 2020.1

## Changes in IntelliJ Platform 2020.1

`com.intellij.compiler.ant` package removed
: 'Generate Ant build' functionality is removed from the IDE. Delete the code extending this or replace it with a dependency on the `generate-ant` plugin.

`org.jetbrains.jps.incremental.ModuleLevelBuilder.getCompilableFileExtensions` marked abstract
: Implement it in `ModuleLevelBuilder`'s implementation.

`com.intellij.codeInsight.TargetElementUtilBase` class removed
: Use `com.intellij.codeInsight.TargetElementUtil` instead.

`com.intellij.psi.stubs.PrebuiltStubsProviderBase` class now extends `com.intellij.index.PrebuiltIndexProvider` and inherits its abstract method `getIndexRoot()`
: Use `com.intellij.psi.stubs.PlatformPrebuiltStubsProviderBase` instead.

`com.intellij.psi.PsiElementVisitor.visitElement` method `PsiElement` parameter marked `@NotNull` 
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.psi.PsiElementVisitor.visitFile` method `PsiFile` parameter marked `@NotNull` 
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.psi.PsiElementVisitor.visitBinaryFile` method `PsiBinaryFile` parameter marked `@NotNull` 
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.psi.PsiElementVisitor.visitPlainTextFile` method `PsiPlainTextFile` parameter marked `@NotNull` 
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.psi.PsiElementVisitor.visitErrorElement` method `PsiErrorElement` parameter marked `@NotNull` 
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.psi.PsiElementVisitor.visitPlainText` method `PsiPlainText` parameter marked `@NotNull` 
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.psi.PsiElementVisitor.visitDirectory` method `PsiDirectory` parameter marked `@NotNull` 
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.psi.PsiElementVisitor.visitComment` method `PsiComment` parameter marked `@NotNull` 
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.psi.PsiElementVisitor.visitWhiteSpace` method `PsiWhiteSpace` parameter marked `@NotNull` 
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.psi.PsiElementVisitor.visitOuterLanguageElement` method `OuterLanguageElement` parameter marked `@NotNull` 
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.codeInspection.unused.ImplicitPropertyUsageProvider.isUsed` method `Property` parameter marked `@NotNull`
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.lang.ReadOnlyASTNode` class removed
: Use `com.intellij.testFramework.ReadOnlyLightVirtualFile`-based PSI instead.

Java code migrated to use `TYPE_USE` nullability annotations
: Due to some problems in Kotlin compiler existing Kotlin code might become incompilable in rare cases if a method written in Java returning an array and annotated as `@Nullable` or `@NotNull` is used or overridden.

`com.intellij.navigation.ChooseByNameContributorEx.processNames` method parameter type changed from `Processor<String>` to ``Processor<? extends String>``
: This may break source-compatibility with inheritors written in Kotlin.

`com.intellij.navigation.ChooseByNameContributorEx.processElementsWithName` method parameter type changed from `Processor<NavigationItem>` to ``Processor<? extends NavigationItem>``
: This may break source-compatibility with inheritors written in Kotlin.
