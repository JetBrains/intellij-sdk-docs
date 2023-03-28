<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 10. Reference Test

<link-summary>Implementing and running tests for resolving elements implemented as a part of the Custom Language Support Tutorial.</link-summary>

<tldr>

**Tested Functionality**: [](reference_contributor.md)

</tldr>

<include from="tests_prerequisites.md" element-id="custom_language_testing_tutorial_header"></include>

## Define Test Data
This test reuses the Simple Language file <path>DefaultTestData.simple</path>.

Create the test file <path>ReferenceTestData.java</path> in the <path>testData</path> directory.
This file has one Simple Language prefix and key, with the caret placed after the key.

```java
```
{src="simple_language_plugin/src/test/testData/ReferenceTestData.java"}

## Define a Test Method
Add the `testReference()` method to the `SimpleCodeInsightTest` class [previously defined](completion_test.md#define-a-test).
This test is configured by the test files.
The fixture gets the `PsiReference` at the caret position, and then asserts the resolved `SimpleProperty.value()` with the known value of that key.

```java
```
{src="simple_language_plugin/src/test/java/org/intellij/sdk/language/SimpleCodeInsightTest.java" include-symbol="testReference"}

## Run the Test

<include from="custom_language_testing_snippets.md" element-id="runTests"/>
