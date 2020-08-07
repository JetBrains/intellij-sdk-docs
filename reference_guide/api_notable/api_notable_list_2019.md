---
title: Notable Changes in IntelliJ Platform and Plugins API 2019.*
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## 2019.3 

### Notable Changes in IntelliJ Platform 2019.3

`PlatformTestCase` renamed to `HeavyPlatformTestCase`
: Now reflects its "heavy test" characteristics (see [Light and Heavy Tests](/basics/testing_plugins/light_and_heavy_tests.md)).
                           
Support for transitive optional plugin dependencies
: Optional `plugin.xml` configuration files can now specify `<depends>`. [Issue](https://youtrack.jetbrains.com/issue/IDEA-209769)                           

Theme/Keymap plugins do not require restart
: (Un)Installing or enabling/disabling [Theme](/reference_guide/ui_themes/themes_intro.md) or [Keymap](https://plugins.jetbrains.com/search?tags=Keymap) plugins doesn't require an IDE restart anymore.
                 
Run task once (per project)
: Use `com.intellij.ide.util.RunOnceUtil` to run a task exactly once for application or per project.                 
                          
Symbol completion in plain text editor (VCS Commit Message)
: Contribute symbol names (classes, methods, ..) via `com.intellij.completion.plainTextSymbol` extension point (`com.intellij.codeInsight.completion.PlainTextSymbolCompletionContributor`).                         
                  
User-customizable date/time formatting
: Use `com.intellij.util.text.JBDateFormat#getFormatter()` to use configured format from `Settings | Appearance & Behavior | System Settings | Date Formats`.                  
                           
### Notable Changes in IntelliJ IDEA

Unbundled plugins
: Several plugins (Heroku integration, RubyMotion support, Java Applets Support) for no longer actively maintained technology have been moved to a [separate repository](https://github.com/JetBrains/intellij-obsolete-plugins/). 
If your plugin depends on them, users will need to install them from the [JetBrains Plugins Repository](https://plugins.jetbrains.com).
                           
                           
## 2019.2 

### Notable Changes in IntelliJ Platform 2019.2

Quickfixes for file-level notifications
: Consistent with other quickfixes, the menu now shows names of fixes, not names of problems themselves. [Issue](https://youtrack.jetbrains.com/issue/IDEA-216731)

Create HTML representation of code
: Use `com.intellij.openapi.editor.richcopy.HtmlSyntaxInfoUtil` to create Lexer-based highlighted code samples, e.g. for usage in documentation. 

View \| Appearance \| Description in Tree Views
: Toggles showing additional details in UI (e.g. modification timestamp in Project View) see `UISettings.getShowInplaceComments()`.

New API for Editor Inlay Hints
: Allows a variety of presentations (incl. custom painting), mouse event handling and exposing settings in _Editor \| Inlay Hints_. See `com.intellij.codeInsight.hints.InlayHintsProvider`.

`com.intellij.openapi.vfs.AsyncFileListener`
: A non-blocking variant for `com.intellij.openapi.vfs.newvfs.BulkFileListener`.

`com.intellij.openapi.util.ColoredItem`
: Provides background color in all Trees, Lists and ComboBoxes.

`com.intellij.openapi.startup.StartupActivity` background variant
: Use new dedicated extension point `com.intellij.backgroundPostStartupActivity` (see Javadoc for `StartupActivity#BACKGROUND_POST_STARTUP_ACTIVITY`).

`com.intellij.codeHighlighting.TextEditorHighlightingPassFactory` registration
: Use new dedicated extension point `com.intellij.highlightingPassFactory`.

`com.intellij.openapi.fileTypes.FileTypeFactory` deprecated
: When registering file type via file extension, pattern or exact file name matching, use extension point `com.intellij.fileType` instead (see [Sample](/tutorials/custom_language_support/language_and_filetype.md#b-register-file-type-20192-or-later)).

`@org.jetbrains.annotations.ApiStatus.NonExtendable`
: Indicates that the annotated API class, interface or method must not be extended, implemented or overridden by external plugins but can be only obtained or instantiated (for classes and interfaces), or called (for methods).

`@org.jetbrains.annotations.ApiStatus.OverrideOnly`
: Indicates that the annotated method is part of SPI (Service Provider Interface), which is intended to be only implemented or overridden but never called by external plugins.

`com.intellij.util.Query.forEach`
: Defaults to thread-safe to prevent problems with clients using unsynchronized collections.

`com.intellij.openapi.projectRoots.SdkType#getInvalidHomeMessage`
: Returns dedicated message when invalid SDK path was chosen (e.g., JRE instead of JDK). 


### Notable Changes in IntelliJ IDEA

Java functionality extracted as plugin
: If your plugin depends on Java, it must be specified now, please see [blog post](https://blog.jetbrains.com/platform/2019/06/java-functionality-extracted-as-a-plugin/) for details.

Unbundled plugins
: Several plugins (J2ME, JsTestDriver, Struts 1.x) for no longer actively maintained technology have been moved to a [separate repository](https://github.com/JetBrains/intellij-obsolete-plugins/). If your plugin depends on them, users will need to install them from the [JetBrains Plugins Repository](https://plugins.jetbrains.com).



## 2019.1 

### Notable Changes in IntelliJ Platform 2019.1

`com.intellij.testFramework.InspectionTestCase` changed to light test
: Use dedicated `ProjectDescriptor` or rollback project setup changes in `tearDown()` (see [Light and Heavy Tests](/basics/testing_plugins/light_and_heavy_tests.md)). 

`@org.jetbrains.annotations.ApiStatus.AvailableSince`
: External annotations for IntelliJ Platform are generated and attached to plugin projects automatically (replacing `@since` Javadoc).

`@org.jetbrains.annotations.ApiStatus.ScheduledForRemoval`
: External annotations for IntelliJ Platform are generated and attached to plugin projects automatically. This allows highlighting of API which has been removed in newer platform versions.

`@org.jetbrains.annotations.ApiStatus.Internal`
: Indicates that the annotated element must not be considered as a public API. Do not use outside of IntelliJ Platform. [Issue](https://youtrack.jetbrains.com/issue/IDEA-211175)

`PsiReferenceProvider` assert underlying element
: Assert references are created for the given underlying `PsiElement`. [Issue](https://youtrack.jetbrains.com/issue/IDEA-203954)

`CachedValue` more strict assertions
: Enabled in tests and EAP/internal mode, see [`CachedValueStabilityChecker`](upsource:///platform/core-impl/src/com/intellij/util/CachedValueStabilityChecker.java) Javadoc.
