<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Syntax and Error Highlighting

<link-summary>Highlighting syntax and semantic code errors on multiple levels.</link-summary>

<tldr>

**Product Help**: [Colors and fonts](https://www.jetbrains.com/help/idea/configuring-colors-and-fonts.html)

**UI Guidelines**: [](inspections.md)

</tldr>

The syntax and error highlighting are performed on multiple levels: [](#lexer), [](#parser), and [](#annotator)/[](#external-annotator).

## Text Attributes Key

How a particular range of text should be highlighted is defined via [`TextAttributesKey`](%gh-ic%/platform/core-api/src/com/intellij/openapi/editor/colors/TextAttributesKey.java).
An instance of this class is created for every distinct type of item that should be highlighted (keyword, number, string literal, etc.).

The `TextAttributesKey` defines the default attributes applied to items of the corresponding type (for example, keywords are bold, numbers are blue, string literals are bold and green).
Highlighting from multiple `TextAttributesKey` items can be layered — for example, one key may define an item's boldness and another one its color.

> To inspect applied `TextAttributesKey`(s) in the editor for the element at the caret, use <ui-path>Jump to Colors and Fonts</ui-path> action.
>
> The underlying `TextAttributeKey`'s external name for items in <ui-path>Settings | Editor | Color Scheme</ui-path> can be inspected using [UI Inspector](internal_ui_inspector.md#inspecting-settings).
>
{title="Looking up existing TextAttributeKey"}

## Color Settings

The mapping of the `TextAttributesKey` to specific attributes used in an editor is defined by the [`EditorColorsScheme`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/colors/EditorColorsScheme.java) class.
It can be configured by the user via <ui-path>Settings | Editor | Color Scheme</ui-path> by providing an implementation of [`ColorSettingsPage`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/colors/ColorSettingsPage.java).

To look up the external name for a setting in the IDE, use [UI Inspector](internal_ui_inspector.md#inspecting-settings).

The <ui-path>File | Export | Files or Selection to HTML</ui-path> feature uses the same syntax highlighting mechanism as the editor.
Thus, it will work automatically for custom languages that provide a syntax highlighter.

**Examples:**

- [`ColorSettingsPage`](%gh-ic%/plugins/properties/src/com/intellij/lang/properties/PropertiesColorsPage.java) for [Properties language plugin](%gh-ic%/plugins/properties)
- [`DebuggerColorsPage`](%gh-ic%/platform/xdebugger-impl/ui/src/com/intellij/xdebugger/impl/ui/DebuggerColorsPage.java) and its [`com.intellij.colorSettings` declaration](%gh-ic-master%/platform/xdebugger-impl/ui/resources/intellij.platform.debugger.impl.ui.xml) with a localized name
- [Custom Language Support Tutorial: Color Settings Page](syntax_highlighter_and_color_settings_page.md)

> See the note about Language Defaults and support for additional color schemes in [](color_scheme_management.md).
>
{style="note"}

### Registering a Color Settings Page

The extension point to use depends on the IntelliJ Platform version the plugin targets.

<tabs>
<tab title="2026.3+">

Register the color settings page in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.colorSettings"/></include>:

```xml
<extensions defaultExtensionNs="com.intellij">
  <colorSettings
      implementation="com.example.MyColorSettingsPage"
      displayName="My Language"/>
</extensions>
```

For a localized page name, use the `key` and `bundle` attributes instead of `displayName`:

```xml
<extensions defaultExtensionNs="com.intellij">
  <colorSettings
      implementation="com.example.MyColorSettingsPage"
      bundle="messages.MyBundle"
      key="color.settings.my.language.name"/>
</extensions>
```

The page class still implements `ColorSettingsPage`.
The values in the declaration must match the values that the page class returns, because the IDE uses the declaration in the Settings tree and the class on the page itself.

The [`ColorSettingsPageEP`](%gh-ic-master%/platform/platform-impl/src/com/intellij/application/options/colors/ColorSettingsPageEP.java) bean supports the following attributes:

| Attribute          | Required    | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
|--------------------|-------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `implementation`   | **yes**     | FQN of the `ColorSettingsPage` implementation.                                                                                                                                                                                                                                                                                                                                                                                                              |
| `displayName`      | **yes** (1) | <p>The non-localized page name. It must match `getDisplayName()`.</p><p>For a localized name, omit `displayName` and use `key` and `bundle`.</p>                                                                                                                                                                                                                                                                                                            |
| `key` and `bundle` | **yes** (1) | <p>The [localization](internationalization.md#message-bundles) key and bundle for the page name. The resolved name must match `getDisplayName()`.</p><p>If `bundle` is omitted, the plugin's [`<resource-bundle>`](plugin_configuration_file.md#idea-plugin__resource-bundle) is used.</p>                                                                                                                                                                  |
| `pageId`           | no          | <p>The ID of the page. The default is the `implementation` value, which is also what `getId()` returns by default.</p><p>Specify it only if the page class overrides `getId()`, and use the same value.</p><p>The common [`id`](plugin_configuration_file.md#idea-plugin__extensions__-) attribute of extensions does not set the page ID.</p>                                                                                                              |
| `priority`         | no          | <p>The group of the page in the Color Scheme tree, as a [`DisplayPriority`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/DisplayPriority.java) value, for example, `COMMON_SETTINGS`. The default is `LANGUAGE_SETTINGS`.</p><p>Specify it if the page class implements [`DisplayPrioritySortable`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/DisplayPrioritySortable.java), and use the value that `getPriority()` returns.</p> |
| `keyLanguageIn`    | no          | <p>A comma-separated list of [platform prefixes](%gh-ic%/platform/core-api/src/com/intellij/util/PlatformUtils.java) of the products in which the page belongs to the `KEY_LANGUAGE_SETTINGS` group, for example, `keyLanguageIn="WebStorm,PhpStorm"`. In all other products, the page uses the `priority` value.</p><p>Use it if `getPriority()` returns a different value depending on the product.</p>                                                   |
| `groupWeight`      | no          | <p>The order of the page within its `priority` group. Pages with a higher weight come first. Pages with equal weight are sorted by name. The default is 0.</p><p>Specify it if the page class implements `DisplayPrioritySortable.getWeight()`, and use the same value.</p>                                                                                                                                                                                 |
| `beta`             | no          | `true` adds a <control>Beta</control> label next to the page name in the Settings tree.                                                                                                                                                                                                                                                                                                                                                                     |

(1) Either `displayName` or `key` and `bundle` must be specified, depending on whether the page name is localized.
If the declaration states no name, the IDE reports an error.

</tab>

<tab title="Earlier versions">

Register the color settings page in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.colorSettingsPage"/></include>:

```xml
<extensions defaultExtensionNs="com.intellij">
  <colorSettingsPage
      implementation="com.example.MyColorSettingsPage"/>
</extensions>
```

> The `com.intellij.colorSettingsPage` extension point is deprecated since 2026.3 but still works.
> If the plugin's [`since-build`](plugin_configuration_file.md#idea-plugin__idea-version) is 263 or later, use `com.intellij.colorSettings` instead.
>
> A plugin that also supports earlier versions can register the page class with both extension points.
> 2026.3 and later use the `com.intellij.colorSettings` declaration and ignore the `com.intellij.colorSettingsPage` one.
> Earlier versions ignore the unknown `com.intellij.colorSettings` declaration, but the plugin then can't be loaded or unloaded [without a restart](dynamic_plugins.md) in them.
> If that matters, register only `com.intellij.colorSettingsPage` until the plugin drops support for versions earlier than 2026.3.
>
{style="warning" title="Deprecated extension point"}

</tab>
</tabs>

## Lexer

The first syntax highlighting level is based on the lexer output and is provided through the [`SyntaxHighlighter`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighter.java) interface.
The syntax highlighter returns the `TextAttributesKey` instances for each token type, which needs special highlighting.
For highlighting lexer errors use [`HighlighterColors.BAD_CHARACTER`](%gh-ic%/platform/core-api/src/com/intellij/openapi/editor/HighlighterColors.java).

**Examples:**

- [`SyntaxHighlighter`](%gh-ic%/plugins/properties/properties-psi-api/src/com/intellij/lang/properties/PropertiesHighlighter.java) implementation for [Properties language plugin](%gh-ic%/plugins/properties)
- [Custom Language Support Tutorial: Syntax Highlighter](syntax_highlighter_and_color_settings_page.md)

> Use [`HtmlSyntaxInfoUtil`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/editor/richcopy/HtmlSyntaxInfoUtil.java) to create Lexer-based highlighted code samples, e.g., for usage in documentation.
>
{title="Creating highlighted code sample"}

### Semantic Highlighting

[Semantic highlighting](https://www.jetbrains.com/help/idea/configuring-colors-and-fonts.html#semantic-highlighting) provides an additional coloring layer to improve the visual distinction of several related items (e.g., method parameters, local variables).

Register [`RainbowVisitor`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/RainbowVisitor.java)
in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.highlightVisitor"/></include>.
[](#color-settings) must implement [`RainbowColorSettingsPage`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/colors/RainbowColorSettingsPage.java) in addition.

## Parser

The second level of error highlighting happens during parsing.
If, according to the grammar of the language, a particular sequence of tokens is invalid, the [`PsiBuilder.error()`](%gh-ic%/platform/core-api/src/com/intellij/lang/PsiBuilder.java) method can highlight the invalid tokens and display an error message showing why they are not valid.

See [](syntax_errors.md) on how to programmatically suppress these errors in certain contexts.

## Annotator

The third level of highlighting is performed through the [`Annotator`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/annotation/Annotator.java) interface.
A plugin can register one or more annotators in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.annotator"/></include>, and these annotators are called during the background highlighting pass to process the elements in the custom language's PSI tree.
Attribute `language` should be set to the Language ID where this annotator applies to.
If highlighting data requires invoking external tools, use an [](#external-annotator) instead.

Annotators can analyze not only the syntax, but also the semantics using PSI, and thus can provide much more complex syntax and error highlighting logic.
The annotator can also provide quick fixes to problems it detects.
When the file is changed, the annotator is called incrementally to process only changed elements in the PSI tree.

Annotators not requiring information from [indexes](indexing_and_psi_stubs.md) can be marked [dumb aware](indexing_and_psi_stubs.md#DumbAwareAPI) to work during indexing (e.g., for additional [syntax highlighting](#syntax)). (2023.1+)

> See also [Code Inspections](code_inspections_and_intentions.md) which offer more fine-grained control and some additional features.
>
{style="note"}

### Errors/Warning

See the [](inspections.md) topic in UI Guidelines on how to write message texts for highlighting/quick fixes.

To highlight a region of a text as a warning or error:

```java
holder.newAnnotation(HighlightSeverity.WARNING, "Invalid code") // or HighlightSeverity.ERROR
    .withFix(new MyFix(psiElement))
    .create();
```

### Syntax

To apply additional syntax highlighting:

```java
holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
    .range(rangeToHighlight)
    .textAttributes(MyHighlighter.EXTRA_HIGHLIGHT_ATTRIBUTE)
    .create();
```

**Examples:**

- [`Annotator`](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/PropertiesAnnotator.java) for [Properties language plugin](%gh-ic%/plugins/properties)
- [Custom Language Support Tutorial: Annotator](annotator.md)

## External Annotator

If the custom language employs external tools for validating files in the language (for example, using the Xerces library for XML schema validation),
it can provide an implementation of the [`ExternalAnnotator`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/annotation/ExternalAnnotator.java) interface and register
it in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.externalAnnotator"/></include> (`language` attribute must be specified).

The `ExternalAnnotator` highlighting has the lowest priority and is invoked only after all other background processing has completed.
It uses the same [`AnnotationHolder`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/annotation/AnnotationHolder.java) interface for converting the output of the external tool into editor highlighting.

To skip running specific `ExternalAnnotator` for a given file, register an [`ExternalAnnotatorsFilter`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/ExternalAnnotatorsFilter.java) extension
in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.daemon.externalAnnotatorsFilter"/></include>.

To enable running `ExternalAnnotator` during indexing in [dumb mode](indexing_and_psi_stubs.md#dumb-mode), it can be marked [dumb aware](indexing_and_psi_stubs.md#DumbAwareAPI) (2023.3).

## Controlling Highlighting

Existing highlighting can be suppressed programmatically in certain contexts, see [](controlling_highlighting.md).

To force re-highlighting all open or specific file(s) (e.g., after changing plugin-specific settings), use
[`DaemonCodeAnalyzer.restart()`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/daemon/DaemonCodeAnalyzer.java).

## Order of Running Highlighting
<primary-label ref="2024.1"/>

[Inspections](code_inspections_and_intentions.md) and [annotators](#annotator) do not run sequentially on each `PsiElement` anymore.
Instead, they're run in parallel on all relevant PSI independently with the following consequences.

**Independent Annotators**

[Annotators](#annotator) are run independent of each other: if an annotator found an error, it no longer stops annotating the `PsiElement`'s parents.
Effectively, there is "more" highlighting now.

**Highlight Range**

Producing highlights must be done as close as possible for the relevant `PsiElement`.
For example, instead of

```text
annotate(PsiFile) {
  <<highlight all relevant identifiers>>
}
```

this approach should be used:

```text
annotate(PsiIdentifier) {
  <<highlight this identifier if it's relevant>>
}
```

The latter version:
- performs faster highlighting – it doesn't have to wait until all other identifiers are visited
- removes outdated highlights faster – right after the identifier was visited and the annotator didn't produce a highlighting anymore

