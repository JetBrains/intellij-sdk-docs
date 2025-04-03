<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Testing Highlighting

<link-summary>Testing highlighting the code highlighted with various APIs.</link-summary>

<include from="testing_plugins.md" element-id="testSamples"/>

<include from="tests_and_fixtures.md" element-id="testFrameworkDependencies"/>

When writing plugin tests, a common task is testing various kinds of highlighting (inspections, annotators, parser error highlighting, etc.).
The IntelliJ Platform provides a dedicated utility and markup format for this task.

To test the highlighting for the file currently loaded into the in-memory editor, invoke [`CodeInsightTestFixture.checkHighlighting()`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/fixtures/CodeInsightTestFixture.java).
The parameters to the method specify which severities should be taken into account when comparing the results with the expected results: errors are always taken into account, whereas warnings, weak warnings, and infos are optional.
To ignore verifying additional highlighting, set parameter `ignoreExtraHighlighting` to `true`.

Alternatively, you can use `CodeInsightTestFixture.testHighlighting()`, which loads a [testdata file](test_project_and_testdata_directories.md) into the in-memory editor and highlights it as a single operation.

**Example:**
[Custom Language Support Tutorial: Testing Annotator](annotator_test.md)

### Inspections

If you need to test inspections, they must be enabled explicitly.
This is done by calling `CodeInsightTestFixture.enableInspections()` in the setup method of your test or directly in a test method, before the call to `CodeInsightTestFixture.checkHighlighting()`.

### Syntax Highlighting

To test syntax highlighting provided by [Lexer](implementing_lexer.md), use [`EditorTestUtil.testFileSyntaxHighlighting()`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/EditorTestUtil.java).

This method takes the tested file and the answer file describing expected highlighting information for each token.
The answer file format is as follows:
```
token_value
    EXPECTED_TEXT_ATTRIBUTE_KEY => FALLBACK_KEY
string␣value
    MY_TEXT_ATTRIBUTE_KEY => MY_FALLBACK_KEY => DEFAULT_STRING
```

It starts with token value, which is the actual token value from the tested file (space characters in token value are replaced with `␣`).
It is followed by indented list of text attribute keys, starting with the key defined for the given token, followed by fallback keys separated by ` => `.

Creating an answer file from scratch would be cumbersome process, so it is recommended to provide only the tested file and executing the test.
Test will fail and the expected answer file will be generated.
Review the generated file carefully, and provide fixes to the syntax highlighter and the answer file if needed.

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
* `descr` expected message associated with the highlighter (if not specified, any text will match). If the message contains a quotation mark, it can be escaped by putting two backslash characters before it.
* `tooltip` expected tooltip message

**Visual**
* `textAttributesKey` expected [`TextAttributesKey`](%gh-ic%/platform/core-api/src/com/intellij/openapi/editor/colors/TextAttributesKey.java) referenced by its `externalName`
* `foregroundColor`, `backgroundColor`, `effectColor` expected colors for the highlighting
* `effectType` expected effect type for the highlighting (see [`EffectType`](%gh-ic%/platform/core-api/src/com/intellij/openapi/editor/markup/EffectType.java))
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

### Generating Test Data

To generate an expected highlighting result file for a test:

1. Make sure that Plugin DevKit plugin is installed and [internal mode is enabled](enabling_internal.md).
2. [Create a Scratch file](https://www.jetbrains.com/help/idea/scratches.html#create-scratch-file) for the tested language.
3. Write code causing tested highlighting.
4. Invoke the <ui-path>Tools | Internal Actions | DevKit | Toggle Expected Highlighting Markup</ui-path> action.
