<!-- Copyright 2000-2025 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

# Notable Changes in IntelliJ Platform and Plugins API 2019.*

<link-summary>List of known Notable API Changes in 2019.*</link-summary>

## 2019.3

### IntelliJ Platform 2019.3

`PlatformTestCase` renamed to `HeavyPlatformTestCase`
: Now reflects its "heavy test" characteristics (see [Light and Heavy Tests](light_and_heavy_tests.md)).

Support for transitive optional plugin dependencies
: [Optional <path>plugin.xml</path> configuration files](plugin_configuration_file.md#additional-plugin-configuration-files) can now specify [`<depends>`](plugin_configuration_file.md#idea-plugin__depends). [Issue](https://youtrack.jetbrains.com/issue/IDEA-209769)

Theme/Keymap plugins do not require restart
: (Un)Installing or enabling/disabling [Theme](themes_getting_started.md) or [Keymap](https://plugins.jetbrains.com/search?tags=Keymap) plugins doesn't require an IDE restart anymore.

Run task once (per project)
: Use `com.intellij.ide.util.RunOnceUtil` to run a task exactly once for application or per project.

Symbol completion in plain text editor (VCS Commit Message)
: Contribute symbol names (classes, methods, ...) via <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.completion.plainTextSymbol"/></include> ([`PlainTextSymbolCompletionContributor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/completion/PlainTextSymbolCompletionContributor.java)).

User-customizable date/time formatting
: Use `com.intellij.util.text.JBDateFormat#getFormatter()` to use configured format from <ui-path>Settings | Appearance & Behavior | System Settings | Date Formats</ui-path>.

### IntelliJ IDEA 2019.3

Unbundled plugins
: Several plugins (Heroku integration, RubyMotion support, Java Applets Support) for no longer actively maintained technology have been moved to a [separate repository](https://github.com/JetBrains/intellij-obsolete-plugins/).
If your plugin depends on them, users will need to install them from the [JetBrains Marketplace](https://plugins.jetbrains.com).

## 2019.2

### IntelliJ Platform 2019.2

Quickfixes for file-level notifications
: Consistent with other quickfixes, the menu now shows names of fixes, not names of problems themselves. [Issue](https://youtrack.jetbrains.com/issue/IDEA-216731)

Create HTML representation of code
: Use `com.intellij.openapi.editor.richcopy.HtmlSyntaxInfoUtil` to create Lexer-based highlighted code samples, e.g., for usage in documentation.

<ui-path>View | Appearance | Details in Tree Views</ui-path>
: Toggles showing additional details in UI (e.g. modification timestamp in Project View) see `UISettings.getShowInplaceComments()`.

New API for Editor Inlay Hints
: Allows a variety of presentations (incl. custom painting), mouse event handling, and exposing settings in <ui-path>Settings | Editor | Inlay Hints</ui-path>. See `com.intellij.codeInsight.hints.InlayHintsProvider`.

`com.intellij.openapi.vfs.AsyncFileListener`
: A non-blocking variant for `com.intellij.openapi.vfs.newvfs.BulkFileListener`.

`com.intellij.openapi.util.ColoredItem`
: Provides background color in all Trees, Lists and ComboBoxes.

`com.intellij.openapi.startup.StartupActivity` background variant
: Use new dedicated <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.backgroundPostStartupActivity"/></include> (see Javadoc for `StartupActivity#BACKGROUND_POST_STARTUP_ACTIVITY`).

`com.intellij.codeHighlighting.TextEditorHighlightingPassFactory` registration
: Use new dedicated <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.highlightingPassFactory"/></include>.

`com.intellij.openapi.fileTypes.FileTypeFactory` deprecated
: When registering a file type via file extension, pattern, or exact filename matching, use <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.fileType"/></include> instead (see [Sample](language_and_filetype.md#register-the-file-type)).

`@org.jetbrains.annotations.ApiStatus.NonExtendable`
: Indicates that the annotated API class, interface, or method must not get extended, implemented, or overridden by external plugins but can only be obtained or instantiated for classes and interfaces, or called for methods.

`@org.jetbrains.annotations.ApiStatus.OverrideOnly`
: Indicates that the annotated method is part of SPI (Service Provider Interface), which is intended to be only implemented or overridden but never called by external plugins.

`com.intellij.util.Query.forEach`
: Defaults to thread-safe to prevent problems with clients using unsynchronized collections.

`com.intellij.openapi.projectRoots.SdkType#getInvalidHomeMessage`
: Returns dedicated message when invalid SDK path was chosen (e.g., JRE instead of JDK).

### IntelliJ IDEA 2019.2

Java functionality extracted as a plugin
: If your plugin depends on Java, it must be specified now; see [](plugin_compatibility.md#java) for details.

Unbundled plugins
: Several plugins (J2ME, JsTestDriver, Struts 1.x) for no longer actively maintained technology have been moved to a [separate repository](https://github.com/JetBrains/intellij-obsolete-plugins/). If your plugin depends on them, users will need to install them from the [JetBrains Marketplace](https://plugins.jetbrains.com).

## 2019.1

### IntelliJ Platform 2019.1

`com.intellij.testFramework.InspectionTestCase` changed to light test
: Use dedicated `ProjectDescriptor` or rollback project setup changes in `tearDown()` (see [Light and Heavy Tests](light_and_heavy_tests.md)).

`@org.jetbrains.annotations.ApiStatus.AvailableSince`
: External annotations for the IntelliJ Platform are generated and attached to plugin projects automatically (replacing `@since` Javadoc).

`@org.jetbrains.annotations.ApiStatus.ScheduledForRemoval`
: External annotations for the IntelliJ Platform are generated and attached to plugin projects automatically. This allows highlighting of API, which has been removed in newer platform versions.

`@org.jetbrains.annotations.ApiStatus.Internal`
: Indicates that the annotated element must not be considered as a public API. Do not use outside of the IntelliJ Platform, see [](api_internal.md).

`PsiReferenceProvider` assert underlying element
: Assert references are created for the given underlying `PsiElement`. [Issue](https://youtrack.jetbrains.com/issue/IDEA-203954)

`CachedValue` more strict assertions
: Enabled in tests and EAP/internal mode, see [`CachedValueStabilityChecker`](%gh-ic%/platform/core-impl/src/com/intellij/util/CachedValueStabilityChecker.java) Javadoc.
