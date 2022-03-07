[//]: # (title: 7. Folding Test)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="tests_prerequisites.md" include-id="custom_language_testing_tutorial_header"></include>

This test verifies the Simple Language folding builder, implemented in the [Folding Builder](folding_builder.md) section of the Custom Language Support Tutorial, works as expected.

> A folding builder must implement [`DumbAware`](upsource:///platform/core-api/src/com/intellij/openapi/project/DumbAware.java) to pass tests.
> See [Define a Folding Builder](folding_builder.md#define-a-folding-builder) for more information.
>
{type="note"}

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
[Run](parsing_test.md#run-the-test) the test and make sure it's green.
