<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 7. Folding Test

<link-summary>Implementing and running test for folding builder implemented as a part of the Custom Language Support Tutorial.</link-summary>

<tldr>

**Tested Functionality**: [](folding_builder.md)

</tldr>

<include from="tests_prerequisites.md" element-id="custom_language_testing_tutorial_header"></include>

> A folding builder must implement [`DumbAware`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/DumbAware.java) to pass tests.
> See [](folding_builder.md#define-a-folding-builder) for more information.
>
{style="note"}

## Define Test Data
Create a file <path>FoldingTestData.java</path> in the <path>testData</path> directory.
This java file contains markup instructions for three different cases of code folding.

```java
```
{src="simple_language_plugin/src/test/testData/FoldingTestData.java"}

## Define a Test
Add the `testFolding()` method to the `SimpleCodeInsightTest` class [previously defined](completion_test.md#define-a-test).
This test method reuses the <path>DefaultTestData.simple</path> Simple file.

```java
```
{src="simple_language_plugin/src/test/java/org/intellij/sdk/language/SimpleCodeInsightTest.java" include-symbol="testFolding"}


## Run the Test

<include from="custom_language_testing_snippets.md" element-id="runTests"/>
