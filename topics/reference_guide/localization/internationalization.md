<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Internationalization

<link-summary>Tips about implementing message bundles, organizing translations, etc.</link-summary>

To enable IntelliJ-based IDEs and plugins for _National Language Support (NLS)_, all `String` instances in the code can be split into three categories:
- **NLS strings** - strings which are shown to users in the UI: texts in dialogs, menu items, descriptions of inspections, error messages, etc.
- **non-NLS strings** - strings which are used internally and aren't shown in UI: attributes in configuration files, keys in indices and caches, etc.
- **NLS-safe strings** - strings which don't need to be localized but can be shown in UI: strings written by users (e.g., parts of program code, file names, URLs, etc.), names of frameworks (in some cases names of frameworks may be translated, e.g., if they consist of multiple words), etc.

By default, a string is considered as non-NLS.

## NLS Annotations

There is a set of NLS-related annotations, which can be used for annotating strings in the IDE or plugin code.
Annotating strings enables inspecting NLS string content correctness.

For example, if an API method parameter is annotated as an NLS string, any hardcoded string passed as a value will be reported and extracting it to a message bundle will be proposed as a quick fix.
Another example is inspecting whether a given string value is properly capitalized for the usage context.

> Internationalization-related inspections can be enabled in <ui-path>Settings | Editor | Inspections</ui-path> in the following groups (some inspections require enabling _Java Internationalization_ bundled plugin):
> - <ui-path>Java | Internationalization</ui-path>
> - <ui-path>Java | Properties files</ui-path>
> - <ui-path>Properties files</ui-path>

Consider using the following annotations:
- [`@Nls`](%gh-java-annotations%/common/src/main/java/org/jetbrains/annotations/Nls.java) - for NLS strings.
  The `capitalization` attribute allows to specify required capitalization.
- [`@NonNls`](%gh-java-annotations%/common/src/main/java/org/jetbrains/annotations/NonNls.java) - for non-NLS strings
- [`@NlsSafe`](%gh-ic%/platform/util/base/src/com/intellij/openapi/util/NlsSafe.java) - for NLS-safe strings

### NLS Context Annotations

NLS context annotations are semantic annotations describing the context where the annotated strings are intended to be used.
For example, [`@InspectionMessage`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/util/InspectionMessage.java) should be used for strings displayed as messages reported by inspections.

NLS context annotations must be annotated with `@Nls` and they can define:
- capitalization requirement - via `@Nls.capitalization` attribute
- [`@NlsContext`](%gh-ic%/platform/util/src/com/intellij/openapi/util/NlsContext.java) - specifies default prefix and suffix for property keys, which will be suggested by the <control>I18nize hardcoded string literal</control> quick fix provided by <ui-path>Java | Internationalization | Hardcoded strings</ui-path> inspection

The IntelliJ Platform provides NLS context annotations, including:
- general contexts: [`NlsContexts`](%gh-ic%/platform/util/src/com/intellij/openapi/util/NlsContexts.java) nested annotations
- action contexts: [`NlsActions`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/util/NlsActions.java) nested annotations
- miscellaneous contexts:
  [`@InspectionMessage`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/util/InspectionMessage.java),
  [`@IntentionFamilyName`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/util/IntentionFamilyName.java),
  [`@IntentionName`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/util/IntentionName.java),
  [`@GutterName`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/daemon/GutterName.java),
  [`@TooltipTitle`](%gh-ic%/platform/platform-api/src/com/intellij/ide/TooltipTitle.java)

To find all available annotations, search for `@NlsContext` usages in the [intellij-community](%gh-ic%/README.md) source code.

If the provided set of NLS context annotations are not sufficient, create custom annotations.

### A Single NLS Category Restriction

It is important not to use the same string instance for both NLS and non-NLS categories.
If it is required to use some class as a key in configuration files and present it in the UI, create two separate methods (`getId`, `getDisplayName`) even if they return the same value in the default locale.

### Avoiding Non-NLS Strings in Message Bundle

To enable localization, all NLS strings must be placed in [resource bundle](#message-bundles) files.
It is important to avoid adding non-NLS strings to a resource bundle.
At best, this will add unnecessary work for translators, but it is also quite possible that if such a string is translated, it could break some features.

## Message Bundles

All NLS strings from a module should be added to a <path>*.properties</path> file.
A standard location of message files in JAR is <path>/messages/\*.properties</path>.
In [Gradle-based plugin](developing_plugins.md#gradle-plugin) project sources, message files are located in <path>\$MODULE_ROOT\$/src/main/resources/messages/\*.properties</path>.

> A standard convention for naming a message bundle properties file is <path>*Bundle.properties</path>.
>
> If a plugin project is multi-module, and it combines resources into a single JAR, make sure that all bundle files have unique names or paths.
> Otherwise, only the last packed bundle file will exist in the distribution package.
>
{style="warning"}

A corresponding [bundle class](#message-bundle-class) should be used to access the strings from the code.

> Enable <control>Plugin DevKit | Plugin descriptor | Plugin.xml i18n verification</control> inspection for reporting hardcoded texts in [plugin descriptor files](plugin_configuration_file.md).

### Message Bundle Class

The recommended approach to create a bundle class is to delegate getting messages to [`DynamicBundle`](%gh-ic%/platform/core-api/src/com/intellij/DynamicBundle.java), e.g.:

<tabs group="languages">

<tab title="Kotlin" group-key="kotlin">

```kotlin
@NonNls
private const val BUNDLE = "messages.ExampleBundle"

internal object ExampleBundle {
  private val INSTANCE = DynamicBundle(ExampleBundle::class.java, BUNDLE)

  fun message(
      key: @PropertyKey(resourceBundle = BUNDLE) String,
      vararg params: Any
  ): @Nls String {
    return INSTANCE.getMessage(key, *params)
  }

  fun lazyMessage(
      @PropertyKey(resourceBundle = BUNDLE) key: String,
      vararg params: Any
  ): Supplier<@Nls String> {
    return INSTANCE.getLazyMessage(key, *params)
  }
}
```
</tab>
<tab title="Java" group-key="java">

```java
final class ExampleBundle {
  @NonNls
  private static final String BUNDLE = "messages.ExampleBundle";
  private static final DynamicBundle INSTANCE =
      new DynamicBundle(ExampleBundle.class, BUNDLE);

  private ExampleBundle() {}

  public static @NotNull @Nls String message(
      @NotNull @PropertyKey(resourceBundle = BUNDLE) String key,
      Object @NotNull ... params
  ) {
    return INSTANCE.getMessage(key, params);
  }

  public static Supplier<@Nls String> lazyMessage(
      @NotNull @PropertyKey(resourceBundle = BUNDLE) String key,
      Object @NotNull ... params
  ) {
    return INSTANCE.getLazyMessage(key, params);
  }
}
```
</tab>

</tabs>

Do not extend `DynamicBundle` in bundle classes.

Annotating message key parameter with [`@PropertyKey`](%gh-java-annotations%/common/src/main/java/org/jetbrains/annotations/PropertyKey.java) adds the IDE support in the client code, e.g., reporting unresolved message keys.

### Moving Strings to Message Bundles

> Make sure the encoding for bundle files is set to UTF-8 in <ui-path>Settings | Editor | File Encodings | Properties Files (*.properties)</ui-path>.

IntelliJ IDEA provides inspections with fixes which help with moving strings to message bundles, e.g. <ui-path>Editor | Inspections | Java | Internationalization | Hardcoded strings</ui-path> for Java and Kotlin code.

It is possible to move multiple hardcoded strings to a message bundle in batch mode.

<procedure title="Moving Hardcoded Strings to a Message Bundle">

1. Invoke the <ui-path>Code | Analyse Code | Run Inspection by Name...</ui-path> action.
2. Select the <control>Hardcoded strings</control> inspection.
3. Select the inspection scope and run the inspection.
4. In the <control>Problems</control> tool window, select the items to internationalize.
5. Invoke the <control>I18nize hardcoded string literal</control> quick fix.
6. Provide the message bundle file and resource bundle expression.
7. Review the internationalized messages. It is possible to delete items and adjust their keys.
8. Click the <control>OK</control> button.

</procedure>

## Internationalization Tips

### Property Keys

It is important to specify a prefix for the property key describing the context in which UI string is used, especially for short strings.

For example, `set=Set` property is hard to translate as the message usage context is unclear.
It is required to find usages of such a message to properly translate it, which is a very time-consuming process.
Also, other developers may reuse the same property for a different meaning of the word, and after that it will be impossible to translate it correctly.

The simplest way to specify the prefix is annotating the parameter with one of [`@NlsContext` annotations](#nls-context-annotations).
It will cause the IDE to generate prefix and suffix automatically when the string is moved to a message bundle.

### Using `&` in Messages

The `&` symbol in message bundles is used to specify [mnemonic](mnemonics.md) characters for buttons and labels.
To use `&` in a value, escape it by a backslash (note that you also need to escape the backslash symbol):
```
section.title=Drag \\\& Drop
```

### Long Values

To wrap a long value in a message bundle, put a backslash at the end of the line, and continue the value on the next line with an indent.
Starting spaces on the next line are ignored, so if a space character is required before the word in the next line, add a space before the backslash:
```
key=very, very long \
  description
```

In the above example, `message("key")` evaluates to `very, very long description`.

Note that the backslash at the end of a line doesn't add a line break into the resulting value.
For line breaks, use `\n`.

### Avoid Programmatic String Transformations

They include but are not limited to:
- capitalization (e.g., "usage" and "Usage")
- pluralization (e.g., "child" and "children")
- grammatical casing ("das Projekt" and "dem Projekt" - "the project" in German used for different grammatical cases: "the project" and "of the project" accordingly)
- gender-based modification (e.g., "nuevo" and "nueva" - "new" in Spanish used for a masculine and feminine subject accordingly)

Such transformations make the incorrect assumption about:
- existence of a transformation in another language (e.g., there might be no casing in some language)
- rules of transformation in another language (e.g., capitalization rules may differ)
- context of the transformed string (e.g., the same word might be used in different contexts)

In all above cases, it is better to put the result string into the bundle.
Example:

<table>
    <tr>
        <td width="50%">
            <b>Wrong</b>
        </td>
        <td>
            <b>Correct</b>
        </td>
    </tr>
    <tr>
        <td width="50%">
            Message bundle:
            <code-block>
              term.node=node
              ‎
            </code-block>
            Code:
            <code-block lang="java">
              message("term.node")
              pluralize(message("term.node"))
            </code-block>
        </td>
        <td>
            Message bundle:
            <code-block>
              term.node=node
              term.nodes=nodes
            </code-block>
            Code:
            <code-block lang="java">
              message("term.node")
              message("term.nodes")
            </code-block>
        </td>
    </tr>
</table>

### Internationalization of String Concatenations

#### Concatenation of Localized Strings and User Input

If an NLS string is not a simple literal but obtained as a concatenation of several values, always use [MessageFormat](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/text/MessageFormat.html) pattern to extract it to a message bundle.

For example, to internationalize `"Delete Class " + className` add the following property:
```
title.delete.class=Delete Class {0}
```
and access it with `message("title.delete.class", className)` in the client code.

Patterns should be used even if the changing part of the string is the last or the first element in the concatenation, because this may change when the message is translated to a language with a different grammar.

> Note that single quotes are used for escaping in `MessageFormat` patterns, and to use `'` character it is required to double it: `''`.
>
> For example, below are the correct sentences:
> ```
> # single quote in the value below is not escaped,
> # because MessageFormat pattern is not used:
> checkbox.text.do.not.show.again=Don't show again
>
> # single quote in the value below is escaped,
> # because MessageFormat pattern is used (notice "{0}"):
> error.message.file.does.not.exist=File {0} doesn''t exist
> ```
>
{title="Escaping Single Quotes in MessageFormat Patterns"}

#### Avoid Composing NLS Strings from Parts

If it is required to include a dynamically obtained string in a message, it is necessary to use message patterns as described above.
However, in other cases, it is better to avoid composing NLS strings from smaller parts stored in separate properties:
- the concatenation of words in the sentence incorrectly assumes that the translated sentence will have the same words in the same order and form in other languages
- it is harder to properly translate messages

The following example won't be translated correctly into the languages which use word cases:

English messages:
```
dialog.title.add.0=Add {0}
dialog.title.edit.0=Edit {0}
dialog.title.name.0.1={0} ''{1}''
concept.library=Library
concept.dependency=Dependency
```

Polish translations:
```
dialog.title.add.0=Dodaj {0}
dialog.title.add.0=Edytuj {0}
concept.library=Bibilioteka
concept.dependency=Zależność
```

Correct result:
```kotlin
message("dialog.title.name.0.1", message("concept.library"), "foo")
// Biblioteka 'foo'
```

Wrong result:
```kotlin
message("dialog.title.add.0", message("concept.library"))
// Dodaj Biblioteka
// wrong case and capitalization (correct: "Dodaj bibliotekę")
```

If several localized strings (non-user input) are used to concatenate the string, then the following techniques can be used (in order of preference):
1. Consider reworking the UI to avoid the string concatenation (consult the UX expert if your organization has any, or check [IntelliJ Platform UI Guidelines](ui_guidelines_welcome.topic)).
   - Put string parts into different UI elements.
   - Remove a UI element which shows the concatenated string.
2. Rephrase the string.
   - Use a generic term:
     `"Select " + term + " to preview"` where term is `"usage"` or `"occurrence"` or `"match"` → `"Select result to preview"`.
   - Don't use the term at all:
     `"Preview " + term` → `"Preview"`

     The UI might still need a slight rework to be clear what will be previewed (consult the UX expert if possible).
   - In some cases, it might be correct to use the sentence concatenation:
     ```
     guidelines.browser.tab.name={0} | IntelliJ Platform UI Guidelines
     google.docs.browser.tab.name={0} - Google Docs
     ```
3. Provide the whole string for each context and term pair:
   ```
   dialog.title.add.library=Add Library
   dialog.title.edit.library=Edit Library
   dialog.title.name.library.0=Library ''{0}''
   dialog.title.add.dependency=Add Dependency
   dialog.title.edit.dependency=Edit Dependency
   dialog.title.name.dependency.0=Dependency ''{0}''
   ```
   This approach is the least preferred:
   - Given X terms and Y contexts, it will result in X*Y strings.
     It is acceptable to have several strings for small X and Y.
     For more cases, the translations may become unmaintainable.
   - If plugins provide terms via an extension point, the extension point API would require a change to provide full strings.

#### Messages Depending on Numbers

Sometimes it is required to change a message depending on a number, for example, to pluralize a noun or use a proper form for a verb.
In that case, use ChoiceFormat patterns.

It starts with an index of the argument which will be used for choosing a variant, then the `choice` word follows,
and the rest specifies pairs of numbers and corresponding values separated by `|`.

For example, for pattern:
```
title.selected.files=Selected {0,choice,1#File|2#Files}
```
the expression `message("title.selected.files", count)` will evaluate to:
- `"Selected File"` if `count` is `1` or less
- `"Selected Files"` if `count` is `2` or greater

The number before `#` characters does not specify the exact value but start of the range.
The corresponding variant will be used if the argument is greater or equal to the number in this clause and less than the number in the next clause.
For example, for pattern:
```
label.selected.files={0,choice,0#No files are|1#One file is|2#A few files are|10#Many files are} selected
```
the expression `message("label.selected.files", 6)` will evaluate to `"A few files are selected"`.

If an argument is referred in one of the choice's variants, it becomes a message pattern itself, and this **adds additional layer of escaping of single quotes**, so to add `'` character in such a variant, add: `''''`.
For example, below is the correct sentence:
```
warning.message={0,choice,1#One person doesn''t|2#{0} people don''''t} like MessageFormat
```

In some cases, the ordinal format can be useful.
It adds an appropriate ending to numbers, so it turns into `1st`, `2nd`, `3rd`, `10th`, and so on:
```
parameter.cast.fix=Cast {0,number,ordinal} parameter to {1}
```

### Formatting Non-Textual Values

Different countries and languages display numbers, dates and times, durations, file sizes, and other values, in a localized way.
The IntelliJ Platform provides some utils to display them properly.

#### Date and Time

- [`DateFormatUtil`](%gh-ic%/platform/platform-api/src/com/intellij/util/text/DateFormatUtil.java) ([IDE's locale](providing_translations.md#getting-the-current-locale-programmatically) is supported since 2024.1)
- [`NlsMessages.formatDateLong()`](%gh-ic%/platform/ide-core-impl/src/com/intellij/ide/nls/NlsMessages.java)

#### Durations

- [`NlsMessages.formatDuration()`](%gh-ic%/platform/ide-core-impl/src/com/intellij/ide/nls/NlsMessages.java)

#### File Sizes

- [`Formats.formatFileSize()`](%gh-ic%/platform/util/base/src/com/intellij/openapi/util/text/Formats.java) (the units are not localized and the default JVM locale is used instead of the [IDE's locale](providing_translations.md#getting-the-current-locale-programmatically))

<include from="snippets.topic" element-id="missingContent"/>
