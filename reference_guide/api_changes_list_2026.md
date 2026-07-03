<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Incompatible Changes in IntelliJ Platform and Plugins API 2026.*

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

<link-summary>List of known Breaking API Changes in 2026.*</link-summary>

<include from="snippets.topic" element-id="apiChangesHeader"/>

<include from="snippets.topic" element-id="apiChangesJavaVersion"/>

<include from="snippets.topic" element-id="gradlePluginVersion"/>

## 2026.3

### IntelliJ Platform 2026.3

#### Kotlin UI DSL 1.0 Removal

Kotlin UI DSL Version 1 (the `com.intellij.ui.layout` DSL entry points) has been completely removed.
Any access to the removed classes will lead to compilation or runtime errors.
The migration should be done according to the [Kotlin UI DSL Version 2 migration guide](kotlin_ui_dsl.md#migration-to-version-2).

`com.intellij.ui.layout.BaseBuilder` class removed
: [Migrate to Kotlin UI DSL Version 2](kotlin_ui_dsl.md#migration-to-version-2).

`com.intellij.ui.layout.CCFlags` class removed
: [Migrate to Kotlin UI DSL Version 2](kotlin_ui_dsl.md#migration-to-version-2).

`com.intellij.ui.layout.Cell` class removed
: [Migrate to Kotlin UI DSL Version 2](kotlin_ui_dsl.md#migration-to-version-2).

`com.intellij.ui.layout.CellBuilder` class removed
: [Migrate to Kotlin UI DSL Version 2](kotlin_ui_dsl.md#migration-to-version-2).

`com.intellij.ui.layout.CellMarker` class removed
: [Migrate to Kotlin UI DSL Version 2](kotlin_ui_dsl.md#migration-to-version-2).

`com.intellij.ui.layout.LCFlags` class removed
: [Migrate to Kotlin UI DSL Version 2](kotlin_ui_dsl.md#migration-to-version-2).

`com.intellij.ui.layout.LayoutBuilder` class removed
: [Migrate to Kotlin UI DSL Version 2](kotlin_ui_dsl.md#migration-to-version-2).

`com.intellij.ui.layout.LayoutBuilderImpl` class removed
: [Migrate to Kotlin UI DSL Version 2](kotlin_ui_dsl.md#migration-to-version-2).

`com.intellij.ui.layout.LayoutImplKt` class removed
: [Migrate to Kotlin UI DSL Version 2](kotlin_ui_dsl.md#migration-to-version-2).

`com.intellij.ui.layout.LayoutKt` class removed
: [Migrate to Kotlin UI DSL Version 2](kotlin_ui_dsl.md#migration-to-version-2).

`com.intellij.ui.layout.Row` class removed
: [Migrate to Kotlin UI DSL Version 2](kotlin_ui_dsl.md#migration-to-version-2).

`com.intellij.ui.layout.RowBuilder` class removed
: [Migrate to Kotlin UI DSL Version 2](kotlin_ui_dsl.md#migration-to-version-2).

## 2026.2

### IntelliJ Platform 2026.2

#### PolySymbols 2026.2

`com.intellij.polySymbols.search.PsiSourcedPolySymbol` class renamed to `com.intellij.polySymbols.search.PsiLinkedPolySymbol`
: Use [`PsiLinkedPolySymbol`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/search/PsiLinkedPolySymbol.kt) instead.
Additionally, the `source` property was renamed to `linkedElement`.

`com.intellij.polySymbols.utils.PsiSourcedPolySymbolDelegate` class renamed to `com.intellij.polySymbols.utils.PsiLinkedPolySymbolDelegate`
: Use [`PsiLinkedPolySymbolDelegate`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/utils/PsiLinkedPolySymbolDelegate.kt) instead.

`com.intellij.polySymbols.refactoring.PsiSourcedPolySymbolRefactoringHelper` class renamed to `com.intellij.polySymbols.refactoring.PsiLinkedPolySymbolRefactoringHelper`
: Use [`PsiLinkedPolySymbolRefactoringHelper`](%gh-ic%/platform/polySymbols/backend/src/com/intellij/polySymbols/refactoring/PsiLinkedPolySymbolRefactoringHelper.kt) instead.

`com.intellij.polySymbols.refactoring.PsiSourcedPolySymbolRenameHandler` class renamed to `com.intellij.polySymbols.refactoring.PsiLinkedPolySymbolRenameHandler`
: Use [`PsiLinkedPolySymbolRenameHandler`](%gh-ic%/platform/polySymbols/backend/src/com/intellij/polySymbols/refactoring/PsiLinkedPolySymbolRenameHandler.kt) instead.

`com.intellij.polySymbols.search.PsiSourcedPolySymbolProvider` class renamed to `com.intellij.polySymbols.search.PsiLinkedPolySymbolProvider`
: Use [`PsiLinkedPolySymbolProvider`](%gh-ic%/platform/polySymbols/backend/src/com/intellij/polySymbols/search/PsiLinkedPolySymbolProvider.kt) instead.

`com.intellij.polySymbols.search.PsiSourcedPolySymbolReference` class renamed to `com.intellij.polySymbols.search.PsiLinkedPolySymbolReference`
: Use [`PsiLinkedPolySymbolReference`](%gh-ic%/platform/polySymbols/backend/src/com/intellij/polySymbols/search/PsiLinkedPolySymbolReference.kt) instead.

`com.intellij.polySymbols.search.PsiSourcedPolySymbolReferenceSearcher` class renamed to `com.intellij.polySymbols.search.PsiLinkedPolySymbolReferenceSearcher`
: Use [`PsiLinkedPolySymbolReferenceSearcher`](%gh-ic%/platform/polySymbols/backend/src/com/intellij/polySymbols/search/PsiLinkedPolySymbolReferenceSearcher.kt) instead.

`com.intellij.polySymbols.search.PsiSourcedPolySymbolRequestResultProcessor` class renamed to `com.intellij.polySymbols.search.PsiLinkedPolySymbolRequestResultProcessor`
: Use [`PsiLinkedPolySymbolRequestResultProcessor`](%gh-ic%/platform/polySymbols/backend/src/com/intellij/polySymbols/search/PsiLinkedPolySymbolRequestResultProcessor.kt) instead.

### Kotlin Plugin 2026.2

#### K1 Plugin Removal

K1 Plugin was completely removed from the distribution.
Any access to its classes or to the classes of the K1 Compiler Frontend would lead to runtime errors.
The migration should be done according to the [migration guide](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.asJava.classes.KtLightClassForSourceDeclaration` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.asJava.classes.KtLightClassImpl` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.asJava.classes.KtUltraLightClass` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.asJava.elements.KtLightMemberImpl` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.asJava.elements.KtLightPsiLiteral` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.actions.generate.KotlinGenerateMemberActionBase` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.base.projectStructure.moduleInfo.ModuleSourceInfo` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.base.utils.fqname.FqNameUtilKt` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.caches.project.MultiplatformUtilKt` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.caches.resolve.util.JavaResolutionUtils` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.codeInsight.DescriptorToSourceUtilsIde` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.codeInsight.KtFunctionPsiElementCellRenderer` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.codeInsight.ReferenceVariantsHelper` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.codeInsight.postfix.KtPostfixTemplateProviderKt` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.core.ShortenReferences` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.core.UtilsKt` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.core.overrideImplement.GenerateMembersHandler` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.core.overrideImplement.GenerateMembersHandler.Companion` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.core.overrideImplement.ImplementMembersHandler` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.core.overrideImplement.OverrideMemberChooserObject` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.gradleJava.compilerPlugin.AbstractCompilerPluginGradleImportHandler` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.imports.ImportsUtils` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.inspections.IntentionBasedInspectionKt` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.quickfix.KotlinIntentionActionsFactory` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.quickfix.KotlinQuickFixAction` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.quickfix.KotlinSingleIntentionActionFactory` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.quickfix.QuickFixContributor` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.quickfix.QuickFixes` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.quickfix.createFromUsage.CreateFromUsageFixBase` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.refactoring.KotlinRefactoringUtilKt` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.refactoring.changeSignature.ChangeSignatureUtilsKt` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.refactoring.changeSignature.KotlinChangeInfo` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.refactoring.changeSignature.KotlinChangeSignatureData` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.refactoring.changeSignature.KotlinMethodDescriptor` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.refactoring.memberInfo.MemberInfoUtilsKt` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.refactoring.safeDelete.KotlinSafeDeleteProcessor` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.search.usagesSearch.SearchHelpersKt` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.search.usagesSearch.UtilsKt` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.util.CallTypeAndReceiver` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.util.IdeDescriptorRenderers` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.util.ImportInsertHelper` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.util.ImportInsertHelper.Companion` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.util.ModifierListModifactorKt` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.util.TypeUtils` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.j2k.UtilsKt` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.resolve.lazy.BodyResolveMode` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.util.DescriptorUtilsKt` class removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.asJava.KotlinAsJavaSupport.getLightClass(KtClassOrObject)` method removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.caches.resolve.ResolutionUtils.analyze$default(KtElement, BodyResolveMode, int, Object)` method removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.caches.resolve.ResolutionUtils.analyze(KtElement, BodyResolveMode)` method removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.caches.resolve.ResolutionUtils.getResolutionFacade(KtElement)` method removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.caches.resolve.ResolutionUtils.resolveImportReference(KtFile, FqName)` method removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.caches.resolve.ResolutionUtils.resolveToCall$default(KtElement, BodyResolveMode, int, Object)` method removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.caches.resolve.ResolutionUtils.resolveToDescriptorIfAny$default(KtClassOrObject, BodyResolveMode, int, Object)` method removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.caches.resolve.ResolutionUtils.resolveToDescriptorIfAny$default(KtDeclaration, BodyResolveMode, int, Object)` method removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.caches.resolve.ResolutionUtils.resolveToDescriptorIfAny$default(KtNamedFunction, BodyResolveMode, int, Object)` method removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.caches.resolve.ResolutionUtils.resolveToDescriptorIfAny$default(KtProperty, BodyResolveMode, int, Object)` method removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.caches.resolve.ResolutionUtils.resolveToDescriptorIfAny(KtDeclaration, BodyResolveMode)` method removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.idea.util.CommentSaver.restore$default(CommentSaver, PsiElement, boolean, int, Object)` method removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.js.descriptorUtils.DescriptorUtilsKt.getJetTypeFqName(KotlinType, boolean)` method removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

`org.jetbrains.kotlin.js.descriptorUtils.DescriptorUtilsKt.getNameIfStandardType(KotlinType)` method removed
: [Migrate to K2 (Analysis API)](https://kotlin.github.io/analysis-api/migrating-from-k1.html).

### CLion 2026.2

The C/C++ language engine based on `com.intellij.cidr.lang` (CLion Classic) is no longer bundled with CLion.
CLion Nova, based on the ReSharper C++ engine, is now the default and the only bundled C/C++ engine.
The classic APIs remain available via the [C/C++ Language Support via Classic Engine](https://plugins.jetbrains.com/plugin/32355-c-c--language-support-via-classic-engine) plugin (ID `com.intellij.cidr.lang`).
Plugins depending on these APIs must declare a dependency on the Marketplace plugin using [`plugin("com.intellij.cidr.lang", "<version>")`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#non-bundled-plugin).
See [C/C++ Language Engine: Nova and Classic](clion.md#language-engine) for setup details and CLion Nova API replacements.

`com.jetbrains.cidr.lang.OCFileType` class removed
: The Classic C/C++ engine is no longer bundled. Depend on the [C/C++ Language Support via Classic Engine](https://plugins.jetbrains.com/plugin/32355-c-c--language-support-via-classic-engine) plugin to keep using it, or migrate to `com.jetbrains.rider.cpp.fileType.CppFileType` (CLion Nova).

`com.jetbrains.cidr.lang.OCLanguage` class removed
: The Classic C/C++ engine is no longer bundled. Depend on the [C/C++ Language Support via Classic Engine](https://plugins.jetbrains.com/plugin/32355-c-c--language-support-via-classic-engine) plugin to keep using it, or migrate to `com.jetbrains.rider.cpp.fileType.CppLanguage` (CLion Nova).

`com.jetbrains.cidr.lang.psi.OCFile` class removed
: Depend on the [C/C++ Language Support via Classic Engine](https://plugins.jetbrains.com/plugin/32355-c-c--language-support-via-classic-engine) plugin to keep using it, or use `com.jetbrains.rider.cpp.fileType.psi.CppFile` (CLion Nova) for file-level access only.

CLion Nova has no frontend PSI, so the remaining `com.jetbrains.cidr.lang` classes — PSI, symbols, types, and resolve/references — have no replacement; depend on the Classic Engine plugin to keep using them.

## 2026.1

### IntelliJ Platform 2026.1

AWT input event handlers no longer run under write-intent lock
:
Custom input handlers (`KeyListener`, `MouseAdapter`, etc.) no longer execute under the [write-intent lock](threading_model.md#read-write-lock).
If PSI or other write-intent-protected data needs to be accessed in these handlers, explicitly acquire a read action using [`ReadAction.nonBlocking().submit()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/ReadAction.java) or coroutine-based equivalents such as [`readAction {}`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/coroutines.kt).

#### Language Server Protocol (LSP) 2026.1.4

In 2026.1.4, key LSP classes were [renamed](language_server_protocol.md#lsp-api-refactoring).
`LspServerManager` was renamed to `LspClientManager` and the service is registered only under the new interface.
Plugins retrieving `LspServerManager` via `project.service<LspServerManager>()` or similar methods will get null.
Use `LspClientManager.getInstance(project)` instead, or `LspServerManager.getInstance(project)` to support versions before 2026.4.1.

#### PolySymbols 2026.1

`com.intellij.polySymbols.PolySymbol.getOrigin()` method removed
: The property was confusing and in many cases was not used. Its functionality is replaced by:
: - `framework` property - provide `com.intellij.polySymbols.html.HtmlFrameworkSymbolsSupport.HtmlFrameworkIdProperty`
value through `PolySymbol.Property` mechanism
: - `defaultIcon` property - implement `icon` property and return `true` for `PolySymbol.DocHideIconProperty` through `PolySymbol.Property` mechanism
: - `typeSupport` property - provide `com.intellij.polySymbols.utils.PolySymbolTypeSupport.TypeSupportProperty`
value through `PolySymbol.Property` mechanism

`com.intellij.polySymbols.PolySymbolQualifiedKind` class renamed to `com.intellij.polySymbols.PolySymbolKind`
: additionally `name` property was renamed to `kindName` and `qualifiedKind` properties and parameters across the package
were renamed to `kind`

The following classes no longer implement [`PsiModificationTracker`](%gh-ic%/platform/core-api/src/com/intellij/psi/util/PsiModificationTracker.java) (this requirement was confusing):
- [`PolySymbolScope`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolScope.kt)
- [`PolySymbolQueryExecutor`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryExecutor.kt)
- [`PolySymbolQueryResultsCustomizer`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryResultsCustomizer.kt)

Implementing `PsiModificationTracker` was replaced by `modificationTracker` property for the following classes:
- [`PolyContextRulesProvider`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/context/PolyContextRulesProvider.kt)
- [`PolySymbolNamesProvider`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolNamesProvider.kt)
- [`PolySymbolNameConversionRulesProvider`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolNameConversionRulesProvider.kt)

### Java Plugin 2026.1

The Java plugin has been split into several modules with their own classloaders.
This shouldn't affect plugins that use standard `<depends>com.intellij.java</depends>` dependency.
If a plugin depends on specific Java plugin modules directly, the dependencies should be updated to reference the new module names.
