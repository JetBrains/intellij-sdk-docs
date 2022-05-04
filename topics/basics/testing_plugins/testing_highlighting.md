[//]: # (title: Testing Highlighting)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

When writing plugin tests, a common task is testing various kinds of highlighting (inspections, annotators, parser error highlighting, etc.).
The IntelliJ Platform provides a dedicated utility and markup format for this task.

To test the highlighting for the file currently loaded into the in-memory editor, invoke [`CodeInsightTestFixture.checkHighlighting()`](upsource:///platform/testFramework/src/com/intellij/testFramework/fixtures/CodeInsightTestFixture.java).
The parameters to the method specify which severities should be taken into account when comparing the results with the expected results: errors are always taken into account, whereas warnings, weak warnings, and infos are optional.
To ignore verifying additional highlighting, set parameter `ignoreExtraHighlighting` to `true`.

Alternatively, you can use `CodeInsightTestFixture.testHighlighting()`, which loads a [testdata file](test_project_and_testdata_directories.md) into the in-memory editor and highlights it as a single operation.

**Example**:
[Custom Language Support Tutorial: Testing Annotator](annotator_test.md)

### Inspections

If you need to test inspections, they must be enabled explicitly.
This is done by calling `CodeInsightTestFixture.enableInspections()` in the setup method of your test or directly in a test method, before the call to `CodeInsightTestFixture.checkHighlighting()`.

### Syntax Highlighting

To test syntax highlighting provided by [Lexer](implementing_lexer.md), use [`EditorTestUtil.testFileSyntaxHighlighting()`](upsource:///platform/testFramework/src/com/intellij/testFramework/EditorTestUtil.java).

## Expected Highlighting Results

The expected results of the highlighting are specified directly in the source file.
The platform supports an extensive XML-like markup language for this.
In its simplest form, the markup looks like this:

```xml
<warning descr="expected warning message">code to be highlighted</warning>
```

A more realistic example, embedded in Java test data to be highlighted:

```xml
public int <warning descr="The compareTo() method does not reference 'foo' which is referenced from equals(); inconsistency may result">compareTo</warning>(Simple other) {
  return 0;
}
```

### Severities
The tag name specifies the severity of the expected highlighting.
The following severities are supported:

* `<error>`
* `<warning>`
* `<weak_warning>`
* `<info>`
* `<inject>` for an [injected fragment](language_injection.md)
* `<symbolName>` for a marker that highlights an identifier according to its type
* any custom severity can be referenced by its name

### Optional Attributes

The tag can also have the following optional attributes.

**Message**
* `descr` expected (hardcoded) message associated with the highlighter (if not specified, any text will match). If the message contains a quotation mark, it can be escaped by putting two backslash characters before it.
* `bundleMsg` expected message from a message bundle in format `[bundleName#] bundleKey [|argument]...`
* `tooltip` expected tooltip message

**Visual**
* `textAttributesKey` expected [`TextAttributesKey`](upsource:///platform/core-api/src/com/intellij/openapi/editor/colors/TextAttributesKey.java) referenced by its `externalName`
* `foregroundColor`, `backgroundColor`, `effectColor` expected colors for the highlighting
* `effectType` expected effect type for the highlighting (see [`EffectType`](upsource:///platform/core-api/src/com/intellij/openapi/editor/markup/EffectType.java))
* `fontType` expected font style for the highlighting (`0` - normal, `1` - bold, `2` - italic, `3` - bold italic)

### Special Cases

*Nested* tags are supported:
```xml
<warning>warning_highlight<info>warning_and_info_highlight</info>warning_highlight</warning>
```

*Overlapping* tags (annotations) are currently **not supported** in the test framework (but displayed correctly in the editor, albeit this is not an officially supported scenario):
```xml
<warning>warning_highlight<info>warning-and_info_highlight</warning>info_highlight</info>
```
