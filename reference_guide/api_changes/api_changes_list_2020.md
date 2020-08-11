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

> **NOTE** Changes from API marked with `org.jetbrains.annotations.ApiStatus.@Experimental`/`ScheduledForRemoval` are not listed here, as incompatible changes are to be expected.

## 2020.3

### Changes in IntelliJ Platform 2020.3
                 
`com.intellij.openapi.application.NonBlockingReadAction.finishOnUiThread` method parameter type changed from ``Consumer<T>`` to ``Consumer<? super T>``
: This may break source-compatibility with inheritors written in Kotlin.


## 2020.2

### Changes in IntelliJ Platform 2020.2

Support for JavaFX deprecated
: Plugins should migrate to [JCEF](/reference_guide/jcef.md). Alternatively, add an explicit dependency on [JavaFX Runtime for Plugins](https://plugins.jetbrains.com/plugin/14250-javafx-runtime-for-plugins). 

`com.intellij.psi.util.PsiTreeUtil.processElements(element, processor)` method parameter type changed from `PsiElementProcessor` to `PsiElementProcessor<PsiElement>`
: This may break source-compatibility with clients that pass more specific processor. Passing more specific processor was illegal before as well because the `processElements` passes every descendant `PsiElement` to the processor regardless of its type. However, this worked with some poorly written clients, e.g. `PsiElementProcessor.CollectFilteredElements` and `PsiElementProcessor.FindFilteredElement` (both deprecated now). To simplify the migration, a new three-arg `processElements(element, elementClass, processor)` is introduced that actually filters by element class, so in most cases, the simplest migration would be to add a wanted element class as a second argument. However, it's advised to use `SyntaxTraverser` API instead, which is more rich and flexible.

`com.maddyhome.idea.copyright.util.FileTypeUtil.getFileTypeByName(String)` method removed
: This was an internal utility method not intended for use in plugins. Use `FileTypeManager.getInstance().findFileTypeByName()` instead.

`javassist` package removed
: [Javassist](https://github.com/jboss-javassist/javassist) library was removed, bundle it with your plugin instead.

`com.intellij.compiler.backwardRefs.LanguageCompilerRefAdapter.INSTANCES` field removed
: This field leaked instances of plugin's extensions on plugin unloading. Use `com.intellij.compiler.backwardRefs.LanguageCompilerRefAdapter#EP_NAME.getExtensionList()` directly instead.  

`groovy.util.AntBuilder` class removed
: Add `org.codehaus.groovy:groovy-ant` dependency.

`groovy.util.GroovyTestCase` class removed
: Add `org.codehaus.groovy:groovy-test` dependency.

`groovy.util.GroovyTestSuite` class removed
: Add `org.codehaus.groovy:groovy-test` dependency.

`groovy.json.internal` package removed
: Use classes from `org.apache.groovy.json.internal` package.

`com.intellij.openapi.externalSystem.service.execution.TaskCompletionProvider(Project, ProjectSystemId, TextAccessor, Options)` constructor parameter type changed from `groovyjarjarcommonscli.Options` to `org.apache.commons.cli.Options`
: Update inheritors accordingly.

`org.jetbrains.plugins.gradle.service.execution.cmd.GradleCommandLineOptionsProvider.getSupportedOptions()` method return type changed from `groovyjarjarcommonscli.Options` to `org.apache.commons.cli.Options`
: Update call sites accordingly.

`com.intellij.openapi.editor.markup.MarkupModel.addLineHighlighter(TextAttributesKey, int, int)` abstract method added
: Use it instead of `MarkupModel.addLineHighlighter(int, int, TextAttributes)`

`com.intellij.openapi.editor.markup.MarkupModel.addRangeHighlighter(TextAttributesKey, int, int, int, HighlighterTargetArea)` abstract method added
: Use it instead of `MarkupModel.addRangeHighlighter(int, int, int, TextAttributes, HighlighterTargetArea)`

`com.intellij.codeInsight.daemon.LineMarkerProvider.getLineMarkerInfo` method return type changed from ``LineMarkerInfo`` to ``LineMarkerInfo<?>``
: This may break source-compatibility with inheritors written in Kotlin.

`com.intellij.codeInsight.daemon.LineMarkerProvider.collectSlowLineMarkers` method parameter type changed from ``List<PsiElement>`` to ``List<? extends PsiElement>``
: This may break source-compatibility with inheritors written in Kotlin.

`com.intellij.codeInsight.daemon.LineMarkerProvider.collectSlowLineMarkers` method parameter type changed from ``List<PsiElement>`` to ``Collection<? super LineMarkerInfo<?>>``
: This may break source-compatibility with inheritors written in Kotlin.

`com.intellij.util.indexing.FileBasedIndex.FileTypeSpecificInputFilter.registerFileTypesUsedForIndexing` method parameter type changed from ``Consumer<FileType>`` to ``Consumer<? super FileType>``
: This may break source-compatibility with inheritors written in Kotlin.

`com.intellij.psi.impl.include.FileIncludeProvider.registerFileTypesUsedForIndexing` method parameter type changed from ``Consumer<FileType>`` to ``Consumer<? super FileType>``
: This may break source-compatibility with inheritors written in Kotlin.

`com.intellij.codeInsight.highlighting.HighlightUsagesHandlerBase.selectTargets` method parameter type changed from ``List<T>`` to ``List<? extends T>``
: This may break source-compatibility with inheritors written in Kotlin.

`com.intellij.codeInsight.highlighting.HighlightUsagesHandlerBase.selectTargets` method parameter type changed from ``Consumer<List<? extends T>>`` to ``Consumer<? super List<? extends T>>``
: This may break source-compatibility with inheritors written in Kotlin.

`com.intellij.codeInsight.highlighting.HighlightUsagesHandlerBase.computeUsages` method parameter type changed from ``List<T>`` to ``List<? extends T>``
: This may break source-compatibility with inheritors written in Kotlin.

`com.intellij.pom.java.LanguageLevel.JDK_13_PREVIEW` field removed
: Please remove plugin code supporting Java 13 language level features. IntelliJ IDEA supports preview features of the latest Java release as well as one upcoming release (if available). 


#### VCS
  
`com.intellij.diff.util.DiffUserDataKeysEx.REVISION_INFO` field removed
: Use `com.intellij.diff.DiffVcsDataKeys.REVISION_INFO` instead. 

`com.intellij.codeInsight.actions.FormatChangedTextUtil.getChangedElements(Project, Change[], Function)` method removed
: Use `com.intellij.codeInsight.actions.VcsFacadeImpl.getVcsInstance().getChangedElements(...)` instead. 

### Changes in GitHub Plugin 2020.2

`org.jetbrains.plugins.github.util.LazyCancellableBackgroundProcessValue(ProgressManager)` constructor removed
: Required for more tight control of task scheduling. Use `org.jetbrains.plugins.github.util.LazyCancellableBackgroundProcessValue.Companion#create(ProgressManager, (ProgressIndicator) -> T)` instead of subclassing

`org.jetbrains.plugins.github.util.LazyCancellableBackgroundProcessValue.compute(ProgressIndicator)` method return type changed from `T` to `CompletableFuture<T>`
: Required for more tight control of task scheduling. Use `org.jetbrains.plugins.github.util.LazyCancellableBackgroundProcessValue.Companion#create(ProgressManager, (ProgressIndicator) -> T)` instead of subclassing

`org.jetbrains.plugins.github.pullrequest.ui.GHCompletableFutureLoadingModel()` constructor removed
: Model was made disposable and it is now required to pass parent disposable in constructor

`org.jetbrains.plugins.github.util.GithubGitHelper.getPossibleRemoteUrlCoordinates()` method removed
: Use `org.jetbrains.plugins.github.util.GHProjectRepositoriesManager.getKnownRepositories()` instead

### Changes in Groovy Plugin 2020.2

`org.jetbrains.plugins.groovy.formatter.AlignmentProvider.addPair` method parameter type changed from `Boolean` to `boolean`
: Please adjust/recompile the code.


### Changes in Java EE Plugins 2020.2

Java EE plugins split
: Plugin `com.intellij.javaee` _Java EE: EJB, JPA, Servlets_ has been split to: 
- `com.inteellij.javaee` _Java EE Platform_ - main plugin other JavaEE/Jakarta plugins depend on
- `com.intellij.javaee.app.servers.integration` _Java EE: Application Servers Integration_
- `com.intellij.javaee.ejb` _Java EE: Enterprise Java Beans (EJB)_
- `com.intellij.javaee.jpa` _Java EE: JPA_
- `com.intellij.javaee.web` _Java EE: Web/Servlets_


### Changes in JavaScript Plugin 2020.2

`com.intellij.lang.javascript.linter.jslint` package removed
: JSLint functionality has been unbundled and moved to a separate plugin. [Issue](https://youtrack.jetbrains.com/issue/WEB-44511)


### Changes in PhpStorm and PHP Plugin 2020.2

Added Union Types Support
: Please see [PhpStorm Breaking Changes](/products/phpstorm/php_open_api_breaking_changes.md).



## 2020.1

### Changes in IntelliJ Platform 2020.1

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

Images module functionality (package `org.intellij.images.*`) extracted to plugin
: The dependency [must be declared](/basics/plugin_structure/plugin_dependencies.md) explicitly now:
  * Add `<depends>com.intellij.platform.images</depends>` in `plugin.xml`
  * Add to `build.gradle`:
    
    ```groovy
    intellij {
      plugins = ['platform-images']
    }
    ```

### Changes in Python Plugin 2020.1

`com.jetbrains.python.psi.PyCallExpression.PyMarkedCallee` class removed
: Use `com.jetbrains.python.psi.types.PyCallableType` instead.

`com.jetbrains.python.psi.PyCallExpression.multiResolveCallee` method return type changed from `List<PyMarkedCallee>` to `List<PyCallableType>`
: Use `com.jetbrains.python.psi.types.PyCallableType` instead of `com.jetbrains.python.psi.PyCallExpression.PyMarkedCallee`.
