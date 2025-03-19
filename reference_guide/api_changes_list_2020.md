<!-- Copyright 2000-2024 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

# Incompatible Changes in IntelliJ Platform and Plugins API 2020.*

<!--
Before documenting a breaking API change, please make sure that the change cannot be avoided in an alternative way.

APIs marked with @Deprecated(forRemoval=true), @ApiStatus.Experimental, @ApiStatus.Internal/IntellijInternalApi, or @ApiStatus.ScheduledForRemoval don't need to be documented.

To document a new incompatible change, add a new line with the problem pattern followed by a 2nd line with ": "-prefixed human-readable description
and recommended fix/action (REQUIRED, please write full sentence ending with '.', see existing entries as reference).
Non-platform changes must be grouped under relevant section for plugin.

The following problem patterns are supported and must be followed EXACTLY (e.g., no '#' instead of '.'):

<package name> package removed

<class name> class removed
<class name> class renamed to <new class name>
<class name> class moved to package <package name>

<class name>.<method name>(<human-readable parameters>) marked abstract
<class name>.<method name>(<human-readable parameters>) abstract method added
<class name>.<method name>(<human-readable parameters>) method removed
<class name>.<method name>(<human-readable parameters>) method moved to the superclass
<class name>.<method name>(<human-readable parameters>) method return type changed from <before> to <after>
<class name>.<method name>(<human-readable parameters>) method visibility changed from <before> to <after>
<class name>.<method name>(<human-readable parameters>) method marked final
<class name>.<method name>(<human-readable parameters>) method parameter <type> removed
<class name>.<method name>(<human-readable parameters>) method parameter type changed from <before> to <after>
<class name>.<method name> method <parameter name> parameter marked @<class name>
<class name> (class|interface) now (extends|implements) <class name> and inherits its final method <method name>(<human-readable parameters>)?
<class name> (class|interface) now (extends|implements) <class name> and inherits its abstract method <method name>(<human-readable parameters>)?
<class name> class now interface

<class name>(<human-readable parameters>) constructor removed
<class name>(<human-readable parameters>) constructor parameter <type> removed
<class name>(<human-readable parameters>) constructor parameter type changed from <before> to <after>
<class name>(<human-readable parameters>) constructor visibility changed from <before> to <after>

<class name>.<field name> field removed
<class name>.<field name> field moved to the superclass
<class name>.<field name> field type changed from <before> to <after>
<class name>.<field name> field visibility changed from <before> to <after>

<property name> property removed from resource bundle <bundle name>

Where the placeholders must be enclosed in code quotes (`name`):

<class name> is a fully-qualified name of the class, e.g. `com.intellij.openapi.actionSystem.AnAction$InnerClass`.
<method name> is the exact method's name. Note that constructors have dedicated patterns.
<human-readable parameters> is a string representing parameters, which are not necessarily fully qualified. They do not affect the parser. For example, instead of (java.lang.Object, java.util.List, int) you are free to write (Object, List<String>, int)
<parameter name> is exact name of the method's parameter
<property name> is a full name of a property from .properties file, like `some.action.description`
<bundle name> is a fully qualified name of the property bundle, which includes its package, like `message.IdeBundle`

NOTE: If a code change you're trying to document doesn't match any of the above patterns, please ask in #plugins-verifier

NOTE: You are allowed to prettify the pattern using links: [`org.example.Foo`](https://github.com/JetBrains/intellij-community/tree/master/)

NOTE: Entries not starting with code quotes (`name`) can be added to document non-code changes and will be skipped in API verification.
-->

<link-summary>List of known Breaking API Changes in 2020.*</link-summary>

<include from="snippets.topic" element-id="apiChangesHeader"/>

<include from="snippets.topic" element-id="apiChangesJavaVersion"/>

## 2020.3

### IntelliJ Platform 2020.3

`com.intellij.openapi.application.NonBlockingReadAction.finishOnUiThread` method parameter type changed from ``Consumer<T>`` to ``Consumer<? super T>``
: This may break source-compatibility with inheritors written in Kotlin.

`com.intellij.openapi.diagnostic.ErrorReportSubmitter.submit` method parameter type changed from ``Consumer<SubmittedReportInfo>`` to ``Consumer<? super SubmittedReportInfo>``
: This may break source-compatibility with inheritors written in Kotlin.

`com.intellij.execution.ui.ConsoleView.attachToProcess` method `ProcessHandler` parameter marked `@NotNull`
: This may break source-compatibility with inheritors written in Kotlin if they declare parameter type as nullable.

`com.intellij.util.indexing.FileContentImpl(VirtualFile, byte[])` constructor removed
: Constructors of `FileContentImpl` were replaced with factory methods, use `FileContentImpl#createByContent(VirtualFile, byte[])`.

`com.intellij.spellchecker.quickfixes.ChangeTo(String)` constructor removed
: Replaced with `ChangeTo(String, PsiElement, TextRange)`.

`com.intellij.spellchecker.tokenizer.SpellcheckingStrategy.getDefaultRegularFixes(boolean, String, PsiElement)` method removed
: Replaced with `SpellcheckingStrategy.getDefaultRegularFixes(boolean, String, PsiElement, TextRange)`.

`com.intellij.psi.stubs.IStubElementType.createStub` method parameter type changed from `StubElement` to ``StubElement<?>``
: This may break source-compatibility with inheritors written in Kotlin.

`com.intellij.execution.application.ApplicationConfiguration.isSwingInspectorEnabled()` method removed
: The Swing Inspector functionality has been removed from the product.

`com.intellij.execution.application.ApplicationConfiguration.setSwingInspectorEnabled(boolean)` method removed
: The Swing Inspector functionality has been removed from the product.

`show.swing.inspector` property removed from resource bundle `messages.ExecutionBundle`
: The Swing Inspector functionality has been removed from the product.

`show.swing.inspector.disabled` property removed from resource bundle `messages.ExecutionBundle`
: The Swing Inspector functionality has been removed from the product.

### Java Plugin 2020.3

The PSI structure of multi-dimensional arrays in Java source files changed (see `com.intellij.psi.PsiTypeElement`)
: Now the children are flattened: brackets for all the dimensions are direct children of the `PsiTypeElement` that represent the multi-dimensional array. This change doesn't break source or binary compatibility but may produce behavioral changes in the code that traverses the tree of Java source files.

The `com.intellij.psi.PsiAnnotation.getOwner` method now returns `PsiType` instead of `PsiTypeElement` for type annotations in Java source files
: This change supports identifying whether a type annotation is attached to an inner class or a particular dimension of a multi-dimensional array.
This change doesn't break source or binary compatibility but may produce behavioral changes for callers.

### PhpStorm and PHP Plugin 2020.3

Added PHP 8 support
: See [Breaking Changes in PhpStorm 2020.3](php_open_api_breaking_changes_203.md).

### Python Plugin 2020.3

All parameters in `com.jetbrains.python.psi.PyElementVisitor` marked `@NotNull`
: This may break source-compatibility with inheritors written in Kotlin.

`com.jetbrains.python.parsing.ParsingContext(SyntaxTreeBuilder, LanguageLevel, StatementParsing.FUTURE)` method parameter `StatementParsing.FUTURE` removed
: It is no longer used in parsing.

`com.jetbrains.python.parsing.StatementParsing(ParsingContext, StatementParsing.FUTURE)` method parameter `StatementParsing.FUTURE` removed
: It is no longer used in parsing.

`com.jetbrains.python.parsing.StatementParsing.FUTURE` class removed
: Use `com.jetbrains.python.psi.FutureFeature` instead.

`com.jetbrains.python.sdk.PythonSdkUpdater.updateOrShowError(Sdk, SdkModificator, Project, Component)` method parameter `SdkModificator` removed
: It was not processed carefully, it should be enough to pass editable SDK instead.

`python.sdk.interpreter.field.is.empty` property removed from resource bundle `messages.PyBundle`
: Use `python.sdk.field.is.empty` from `messages.PySdkBundle` instead.

`base.interpreter` property removed from resource bundle `messages.PyBundle`
: Use `python.venv.base.label` from `messages.PySdkBundle` instead.

`interpreter` property removed from resource bundle `messages.PyBundle`
: Use `python.interpreter.label` from `messages.PySdkBundle` instead.

`com.jetbrains.python.psi.LanguageLevel.hasWithStatement()` method removed
: It is `true` for all supported python versions.

### CLion/AppCode 2020.3

Required changes in project setup
: When targeting 2020.3, please see this [migration guide](https://blog.jetbrains.com/clion/2020/12/migration-guide-for-plugins-2020-3/).

## 2020.2

### IntelliJ Platform 2020.2

Support for JavaFX deprecated
: Plugins should migrate to [JCEF](embedded_browser_jcef.md). Alternatively, add an explicit dependency on [JavaFX Runtime for Plugins](https://plugins.jetbrains.com/plugin/14250-javafx-runtime-for-plugins).

`com.intellij.psi.util.PsiTreeUtil.processElements(element, processor)` method parameter type changed from `PsiElementProcessor` to `PsiElementProcessor<PsiElement>`
: This may break source-compatibility with clients that pass a more specific processor. Passing a more specific processor was illegal before because the `processElements` passes every descendant `PsiElement` to the processor regardless of its type. However, this worked with some poorly written clients, e.g. `PsiElementProcessor.CollectFilteredElements` and `PsiElementProcessor.FindFilteredElement` (both deprecated now). To simplify the migration, a new three-arg `processElements(element, elementClass, processor)` is introduced that filters by element class. In most cases, the simplest migration would be to add a wanted element class as a second argument. However, it's advised to use `SyntaxTraverser` API instead, which is more rich and flexible.

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
: Use it instead of `MarkupModel.addLineHighlighter(int, int, TextAttributes)`.

`com.intellij.openapi.editor.markup.MarkupModel.addRangeHighlighter(TextAttributesKey, int, int, int, HighlighterTargetArea)` abstract method added
: Use it instead of `MarkupModel.addRangeHighlighter(int, int, int, TextAttributes, HighlighterTargetArea)`.

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
: Please remove the plugin code supporting Java 13 language level features.
IntelliJ IDEA supports preview features of the latest Java release and one upcoming release (if available).

#### VCS

`com.intellij.diff.util.DiffUserDataKeysEx.REVISION_INFO` field removed
: Use `com.intellij.diff.DiffVcsDataKeys.REVISION_INFO` instead.

`com.intellij.codeInsight.actions.FormatChangedTextUtil.getChangedElements(Project, Change[], Function)` method removed
: Use `com.intellij.codeInsight.actions.VcsFacadeImpl.getVcsInstance().getChangedElements(...)` instead.

### GitHub Plugin 2020.2

`org.jetbrains.plugins.github.util.LazyCancellableBackgroundProcessValue(ProgressManager)` constructor removed
: Required for more tight control of task scheduling. Use `org.jetbrains.plugins.github.util.LazyCancellableBackgroundProcessValue.Companion#create(ProgressManager, (ProgressIndicator) -> T)` instead of subclassing.

`org.jetbrains.plugins.github.util.LazyCancellableBackgroundProcessValue.compute(ProgressIndicator)` method return type changed from `T` to `CompletableFuture<T>`
: Required for more tight control of task scheduling. Use `org.jetbrains.plugins.github.util.LazyCancellableBackgroundProcessValue.Companion#create(ProgressManager, (ProgressIndicator) -> T)` instead of subclassing.

`org.jetbrains.plugins.github.pullrequest.ui.GHCompletableFutureLoadingModel()` constructor removed
: Model was made disposable and it is now required to pass parent disposable in constructor.

`org.jetbrains.plugins.github.util.GithubGitHelper.getPossibleRemoteUrlCoordinates()` method removed
: Use `org.jetbrains.plugins.github.util.GHProjectRepositoriesManager.getKnownRepositories()` instead.

### Groovy Plugin 2020.2

`org.jetbrains.plugins.groovy.formatter.AlignmentProvider.addPair` method parameter type changed from `Boolean` to `boolean`
: Please adjust/recompile the code.

### Java EE Plugins 2020.2

Java EE plugins split
: Plugin `com.intellij.javaee` _Java EE: EJB, JPA, Servlets_ has been split to:
- `com.inteellij.javaee` _Java EE Platform_ - main plugin other JavaEE/Jakarta plugins depend on
- `com.intellij.javaee.app.servers.integration` _Java EE: Application Servers Integration_
- `com.intellij.javaee.ejb` _Java EE: Enterprise Java Beans (EJB)_
- `com.intellij.javaee.jpa` _Java EE: JPA_
- `com.intellij.javaee.web` _Java EE: Web/Servlets_

### JavaScript Plugin 2020.2

`com.intellij.lang.javascript.linter.jslint` package removed
: JSLint functionality has been unbundled and moved to a separate plugin. [Issue](https://youtrack.jetbrains.com/issue/WEB-44511)

### PhpStorm and PHP Plugin 2020.2

Added Union Types Support
: See [Breaking Changes in PhpStorm 2020.2](php_open_api_breaking_changes_202.md).

### Kotlin Plugin 1.4

`org.jetbrains.kotlin.idea.core.formatter.KotlinCodeStyleSettings.PACKAGES_TO_USE_STAR_IMPORTS` field type changed from `PackageEntryTable` to `KotlinPackageEntryTable`
: This change was required to implement import layout order for Kotlin. `KotlinPackageEntryTable` can be used in the same manner as `PackageEntryTable`.

### Python Plugin 2020.2

`com.jetbrains.python.PythonDialectsTokenSetProvider.INSTANCE` field removed
: `PythonDialectsTokenSetProvider` became an application service, use `PythonDialectsTokenSetProvider.getInstance()` instead.

`com.jetbrains.python.psi.PyUtil.getLanguageLevelForVirtualFile(Project, VirtualFile)` method removed
: Use `PythonLanguageLevelPusher.getLanguageLevelForVirtualFile(Project, VirtualFile)` instead.

## 2020.1

### IntelliJ Platform 2020.1

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
: In rare cases existing Kotlin code might become uncompilable due to some problems in the Kotlin compiler: if a method is used or overridden, and is written in Java, and returns an array annotated as `@Nullable` or `@NotNull`.

`com.intellij.navigation.ChooseByNameContributorEx.processNames` method parameter type changed from `Processor<String>` to ``Processor<? extends String>``
: This may break source-compatibility with inheritors written in Kotlin.

`com.intellij.navigation.ChooseByNameContributorEx.processElementsWithName` method parameter type changed from `Processor<NavigationItem>` to ``Processor<? extends NavigationItem>``
: This may break source-compatibility with inheritors written in Kotlin.

Images module functionality (package `org.intellij.images.*`) extracted to plugin
: The dependency [must be declared](plugin_dependencies.md) explicitly now by the [`<depends>`](plugin_configuration_file.md#idea-plugin__depends) element:
  * Add `<depends>com.intellij.platform.images</depends>` in <path>[plugin.xml](plugin_configuration_file.md)</path>
  * Add to <path>build.gradle</path>:

    ```groovy
    intellij {
      plugins = ['platform-images']
    }
    ```
  * If your plugin depends on other plugins using `com.intellij.platform.images` (e.g., CSS), please make sure to use `gradle-intellij-plugin` >=0.4.19

### Python Plugin 2020.1

`com.jetbrains.python.psi.PyCallExpression.PyMarkedCallee` class removed
: Use `com.jetbrains.python.psi.types.PyCallableType` instead.

`com.jetbrains.python.psi.PyCallExpression.multiResolveCallee` method return type changed from `List<PyMarkedCallee>` to `List<PyCallableType>`
: Use `com.jetbrains.python.psi.types.PyCallableType` instead of `com.jetbrains.python.psi.PyCallExpression.PyMarkedCallee`.
