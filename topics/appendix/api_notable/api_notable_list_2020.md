<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Notable Changes in IntelliJ Platform and Plugins API 2020.*

<link-summary>List of known Notable API Changes in 2020.*</link-summary>

We've published our roadmap for the IntelliJ Platform for 2020: [Part I](https://blog.jetbrains.com/idea/2019/12/intellij-platform-roadmap-for-2020/) [Part II](https://blog.jetbrains.com/idea/2020/01/intellij-based-ide-features-roadmap-for-2020/)

## 2020.3

### IntelliJ Platform 2020.3

IntelliJ Platform migrated to Java 11
: Using Java 11 is now required, please see [blog post](https://blog.jetbrains.com/platform/2020/09/intellij-project-migrates-to-java-11/).

Internal changes in Project Model
: Internal changes related to a significant redesign of the representation of project models have been made, please see [blog post](https://blog.jetbrains.com/platform/2020/10/new-implementation-of-project-model-interfaces-in-2020-3/) for details. This shouldn't affect any plugins using the IntelliJ API properly and which don't access internal classes.

Reparsing of `IReparseableLeafElementType`
: For elements whose `IElementType` implements this interface, platform attempts reparse when a modification is made right before or after the leaf element preventing reparsing the whole file.

Generating HTML fragments
: Use [`HtmlBuilder`](%gh-ic%/platform/util/src/com/intellij/openapi/util/text/HtmlBuilder.java) for generating formatted content, e.g., for [Documentation](documentation.md).

Extensible HTML Lexer/Parser
: Implement [`HtmlEmbeddedContentSupport`](%gh-ic%/xml/xml-parser/src/com/intellij/html/embedding/HtmlEmbeddedContentSupport.kt) and register in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.html.embeddedContentSupport"/></include> to embed arbitrary tokens into any tag or attribute.
Please note that old API from `com.intellij.lexer.BaseHtmlLexer` is no longer working.

Action System
: New features in [](action_system.md): `<override-text>` works now for [`<group>`](plugin_configuration_file.md#idea-plugin__actions__group) as well, [`<synonym>`](plugin_configuration_file.md#idea-plugin__actions__action__synonym) provides alternative names when searching for actions, and groups can be excluded from search results.

Welcome Screen customization
: To provide additional custom tabs, implement [`WelcomeTabFactory`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/wm/WelcomeTabFactory.java) and register in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.welcomeTabFactory"/></include>.

File Type association with the IDE
: To control file type association with the IDE in the operating system, implement [`OSFileIdeAssociation`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileTypes/OSFileIdeAssociation.java).

Reader Mode customization
: Implement [`ReaderModeProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/codeInsight/actions/ReaderModeProvider.kt) and register in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.readerModeProvider"/></include> to apply custom settings for files rendered in reader mode. Provide `com.intellij.codeInsight.actions.ReaderModeMatcher` to disable Reader Mode for particular set of files.

Text Editor customization
: Implement [`TextEditorCustomizer`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/fileEditor/impl/text/TextEditorCustomizer.kt) and register in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.textEditorCustomizer"/></include> to customize created editors.<br/>
**Note: `TextEditorCustomizer` has been marked internal since 2024.2.**

### JavaScript Plugin 2020.3

Published Javascript Testframework
: This allows using existing test base classes, see [WebStorm Plugin Development](webstorm.md#javascript-test-framework) page for details.

### CLion/AppCode 2020.3

Required changes in project setup
: When targeting 2020.3, please see this [migration guide](https://blog.jetbrains.com/clion/2020/12/migration-guide-for-plugins-2020-3/).


## 2020.2

### IntelliJ Platform 2020.2

Constructor Injection in `Configurable` forbidden
: Please obtain necessary components only when needed (logged as ERROR now).

`VirtualFile` lookup via `Path`
: Added `VirtualFileManager.findFileByNioPath()`/`refreshAndFindFileByNioPath()`. See also `VirtualFile.toNioPath()`.

Tooltip descriptions for icons
: Register resource bundle via <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.iconDescriptionBundle"/></include> to provide tooltips automatically for all [`SimpleColoredComponent`](%gh-ic%/platform/platform-api/src/com/intellij/ui/SimpleColoredComponent.java) renderers.

Specify incompatibility with Module
: A plugin can [mark itself incompatible](plugin_compatibility.md#declaring-incompatibility-with-module) if IDE contains specified module.

`com.intellij.openapi.editor.markup.MarkupModel` methods using `TextAttributesKey`
: To support on-the-fly Editor color scheme switching, change calls from methods taking `TextAttributes`.

Support for WebP images
: The platform now bundles support for images in [WebP](https://en.wikipedia.org/wiki/WebP) format.

FileType mapping via hashbang (`#!`)
: Specify `hashBangs` attribute in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.fileType"/></include>. [Issue](https://youtrack.jetbrains.com/issue/IDEA-175757)

Add information to About dialog
: Implement [`AboutPopupDescriptionProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/AboutPopupDescriptionProvider.kt) and register in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.aboutPopupDescriptionProvider"/></include>.

Previewing Intention/Quick Fix
: To support preview in intention popup, suitable [`FileModifier`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/FileModifier.java) must be provided (default implementation `FileModifier.getFileModifierForPreview()` works for most cases).

Delegate Run Anything/Terminal commands to IDE features
: Switch to matching IDE feature by implementing [`TerminalShellCommandHandler`](%gh-ic%/platform/execution-impl/src/com/intellij/terminal/TerminalShellCommandHandler.kt) (<include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.terminal.shellCommandHandler"/></include>). [Blog post](https://blog.jetbrains.com/idea/2020/07/run-ide-features-from-the-terminal/)

Deprecating JavaFX in favor of JCEF
: We recommend switching to [JCEF](embedded_browser_jcef.md), please see [blog post](https://blog.jetbrains.com/platform/2020/07/javafx-and-jcef-in-the-intellij-platform/) for details.

ASM Library 8.0.1
: Updated from 7.0.1.

Validating Lexer for editor highlighting
: Lexer is wrapped using [`ValidatingLexerWrapper`](%gh-ic%/platform/editor-ui-ex/src/com/intellij/openapi/editor/ex/util/ValidatingLexerWrapper.java) to verify it generates a continuous sequence of tokens and doesn't stall during iteration.

### IntelliJ IDEA 2020.2

Unbundled plugins
: Several plugins (ASP, CFML, Flash/Flex, GWT, JBoss Seam Pageflow, JBoss Seam, JBoss jBPM, OSGi, Play Framework, Resin, Seam Navigation, Tapestry, Virgo/dmServer) for no longer actively maintained technology have been unbundled. If your plugin depends on them, users will need to install them from the [JetBrains Marketplace](https://plugins.jetbrains.com).

## 2020.1

### IntelliJ Platform 2020.1

Dynamic Plugins
: [Compatible plugins](dynamic_plugins.md) can be installed, updated and uninstalled without requiring IDE restart.

[`com.intellij.openapi.application.TransactionGuard`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/TransactionGuard.java) deprecated
: Usage is deprecated and can be replaced with `com.intellij.openapi.application.Application.invokeLater()` in most cases, please consult Javadoc for more details.

`RecursionManager.assertOnMissedCache()` enabled by default in tests
: Please see [`RecursionManager.CachingPreventedException`](%gh-ic%/platform/util/src/com/intellij/openapi/util/RecursionManager.java) Javadoc and [this issue](https://youtrack.jetbrains.com/issue/IDEA-228809) for details.

`ResolveCache` using `IdempotenceChecker` in tests
: Reports when the same reference resolves non-equivalent results in different threads, see [`IdempotenceChecker`](%gh-ic%/platform/core-impl/src/com/intellij/util/IdempotenceChecker.java).

Refactoring dialog: builtin "Open in editor" option
: Set `addOpenInEditorCheckbox` constructor parameter to enable it in custom `RefactoringDialog` implementation.

Configurable status bar widgets
: Use <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.statusBarWidgetFactory"/></include> to provide widgets that can be disabled or reordered, see [](status_bar_widgets.md).

JCEF Support (_Experimental Feature_)
: Allows [embedding](embedded_browser_jcef.md) Chromium-based browser in the IDE.

Override text presentation for actions depending on menu context
: Set the [`<override-text>`](plugin_configuration_file.md#idea-plugin__actions__action__override-text) element within the [`<action>`](plugin_configuration_file.md#idea-plugin__actions__action) declaration in <path>[plugin.xml](plugin_configuration_file.md)</path>.

Changes in Project Open/Import
: **Import from Existing Sources** has been removed from the Welcome Screen, leaving only **Open or Import**, which calls a different extension than the one previously used to contribute a wizard step to **Import from Existing Sources** (which is still available in the <control>File</control> menu). To support **Open or Import**, a plugin must provide [`ProjectOpenProcessor`](%gh-ic%/platform/platform-api/src/com/intellij/projectImport/ProjectOpenProcessor.kt).
`ProjectOpenProcessor.canOpenProject()` should return `true` for the folder selected by the user only if it guarantees `doOpenProject()` can handle it. If there are several matching processors, a simple chooser dialog is shown. If additional manual configuration is necessary, a modal dialog can be shown in `doOpenProject()` - however, it is highly recommended performing all setup automatically (like Maven and Gradle plugins do).

### IntelliJ IDEA 2020.1

EOL for JetBrains TFS Plugin
: Please use [Azure DevOps](https://plugins.jetbrains.com/plugin/7981-azure-devops) plugin instead, see [blog post](https://blog.jetbrains.com/idea/2020/01/end-of-support-for-tfs-2014-and-older/) for more details.

Unbundled plugins
: Several plugins (Cloud Foundry, Google App Engine) for no longer actively maintained technology have been unbundled. If your plugin depends on them, users will need to install them from the [JetBrains Marketplace](https://plugins.jetbrains.com).
