---
title: Notable Changes in IntelliJ Platform and Plugins API 2020.*
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

We've published our roadmap for the IntelliJ Platform for 2020: [Part I](https://blog.jetbrains.com/idea/2019/12/intellij-platform-roadmap-for-2020/) [Part II](https://blog.jetbrains.com/idea/2020/01/intellij-based-ide-features-roadmap-for-2020/)

## 2020.2 

### Notable Changes in IntelliJ Platform 2020.2

Constructor Injection in `Configurable` forbidden
: Please obtain necessary components only when needed (logged as ERROR now). 

`VirtualFile` lookup via `Path`
: Added `VirtualFileManager.findFileByNioPath()`/`refreshAndFindFileByNioPath()`. See also `VirtualFile.toNioPath()`.

Tooltip descriptions for icons
: Register resource bundle via extension point `com.intellij.iconDescriptionBundle` to provide tooltips automatically for all `SimpleColoredComponent` renderers. 

Specify incompatibility with Module
: A plugin can [mark itself incompatible](/basics/getting_started/plugin_compatibility.md#declaring-incompatibility-with-module) if IDE contains specified module.

`com.intellij.openapi.editor.markup.MarkupModel` methods using `TextAttributesKey`
: To support on-the-fly Editor color scheme switching, change calls from methods taking `TextAttributes`.

Support for WebP images
: The platform now bundles support for images in [WebP](https://en.wikipedia.org/wiki/WebP) format.

FileType mapping via hashbang (`#!`)
: Specify `hashBangs` attribute in `com.intellij.fileType` extension point. [Issue](https://youtrack.jetbrains.com/issue/IDEA-175757)

Add information to About dialog
: Implement `com.intellij.ide.AboutPopupDescriptionProvider` and register in `com.intellij.aboutInfoProvider` extension point.

Unbundled plugins
: Several plugins (ASP, CFML, Flash/Flex, GWT, JBoss Seam Pageflow, JBoss Seam, JBoss jBPM, OSGi, Play Framework, Resin, Seam Navigation, Tapestry, Virgo/dmServer) for no longer actively maintained technology have been unbundled. 
If your plugin depends on them, users will need to install them from the [JetBrains Plugins Repository](https://plugins.jetbrains.com).

Previewing Intention/Quick Fix
: To support preview in intention popup, suitable `FileModifier` must be provided (default implementation `FileModifier.getFileModifierForPreview()` works for most cases). 
  
Delegate Run Anything/Terminal commands to IDE features
: Switch to matching IDE feature by implementing `com.intellij.terminal.TerminalShellCommandHandler` (extension point `com.intellij.terminal.shellCommandHandler`). [Blog post](https://blog.jetbrains.com/idea/2020/07/run-ide-features-from-the-terminal/)

Deprecating JavaFX in favor of JCEF
: We recommend switching to [JCEF](/reference_guide/jcef.md), please see [blog post](https://blog.jetbrains.com/platform/2020/07/javafx-and-jcef-in-the-intellij-platform/) for details.

ASM Library 8.0.1 
: Updated from 7.0.1.
               
Validating Lexer for editor highlighting
: Lexer is wrapped using `ValidatingLexerWrapper` to verify it generates continuous sequence of tokens and doesn't stall during iteration.

## 2020.1 

### Notable Changes in IntelliJ Platform 2020.1
Dynamic Plugins
: [Compatible plugins](/basics/plugin_structure/dynamic_plugins.md) can be installed, updated and uninstalled without requiring IDE restart.

[`com.intellij.openapi.application.TransactionGuard`](upsource:///platform/core-api/src/com/intellij/openapi/application/TransactionGuard.java) deprecated
: Usage is deprecated and can be replaced with `com.intellij.openapi.application.Application.invokeLater()` in most cases, please consult Javadoc for more details.

`RecursionManager.assertOnMissedCache()` enabled by default in tests
: Please see `RecursionManager.CachingPreventedException` Javadoc and [this issue](https://youtrack.jetbrains.com/issue/IDEA-228809) for details. 

`ResolveCache` using `IdempotenceChecker` in tests
: Reports when the same reference resolves to non-equivalent results in different threads, see [`IdempotenceChecker`](upsource:///platform/core-impl/src/com/intellij/util/IdempotenceChecker.java).

Refactoring dialog: builtin "Open in editor" option
: Set `addOpenInEditorCheckbox` constructor parameter to enable it in custom `RefactoringDialog` implementation.

Configurable status bar widgets
: Use extension point `com.intellij.statusBarWidgetFactory` to provide widgets that can be disabled or reordered. 

JCEF Support (_Experimental Feature_) 
: Allows [embedding](/reference_guide/jcef.md) Chromium-based browser in the IDE.

Override text presentation for actions depending on menu context 
: Set the [`<override-text>`](/basics/action_system.md#setting-the-override-text-element-for-an-action) element within the `<action>` declaration in `plugin.xml`.

Changes in Project Open/Import
: **Import from Existing Sources** has been removed from the Welcome Screen, leaving only **Open or Import**, which calls a different extension than the one previously used to contribute a wizard step to **Import from Existing Sources** (which is still available in the **File** menu).
To support **Open or Import**, a plugin must provide [`ProjectOpenProcessor`](upsource:///platform/platform-api/src/com/intellij/projectImport/ProjectOpenProcessor.java).
`ProjectOpenProcessor.canOpenProject()` should return `true` for the folder selected by the user only if it guarantees `doOpenProject()` can handle it.
If there are several matching processors, a simple choice dialog is shown.
If additional manual configuration is necessary, a modal dialog can be shown in `doOpenProject()` - however, it is highly recommended performing all setup automatically (like Maven and Gradle plugins do).

### Notable Changes in IntelliJ IDEA

EOL for JetBrains TFS Plugin
: Please use [Azure DevOps](https://plugins.jetbrains.com/plugin/7981-azure-devops) plugin instead, see [blog post](https://blog.jetbrains.com/idea/2020/01/end-of-support-for-tfs-2014-and-older/) for more details.

Unbundled plugins
: Several plugins (Cloud Foundry, Google App Engine) for no longer actively maintained technology have been unbundled. 
If your plugin depends on them, users will need to install them from the [JetBrains Plugins Repository](https://plugins.jetbrains.com).
