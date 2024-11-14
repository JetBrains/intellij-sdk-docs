<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 5. Formatter Test

<link-summary>Implementing and running test for the formatter implemented as a part of the Custom Language Support Tutorial.</link-summary>

<tldr>

**Tested Functionality**: [](formatter.md)

</tldr>

<include from="tests_prerequisites.md" element-id="custom_language_testing_tutorial_header"></include>

See also [`FormatterTestCase`](%gh-ic%/platform/testFramework/src/com/intellij/psi/formatter/FormatterTestCase.java) as convenient base class.

## Define Test Data
Create the <path>FormatterTestData.simple</path> file in the <path>testData</path> directory.

```properties
```
{src="simple_language_plugin/src/test/testData/FormatterTestData.simple"}

## Define a Test Method
Add the `testFormatter()` method to the `SimpleCodeInsightTest` class [previously defined](completion_test.md#define-a-test).
* Again, this method configures the test fixture by using the test file.
* The code style Simple Language settings for spaces and blank lines are set.
* The file is then formatted according to the settings.
* The formatted file is compared to the expected results in the benchmark file <path>DefaultTestData.simple</path>.

```java
```
{src="simple_language_plugin/src/test/java/org/intellij/sdk/language/SimpleCodeInsightTest.java" include-symbol="testFormatter"}

## Run the Test

<include from="custom_language_testing_snippets.md" element-id="runTests"/>
