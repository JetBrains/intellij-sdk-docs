[//]: # (title: 10. Reference Test)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="tests_prerequisites.md" include-id="custom_language_testing_tutorial_header"></include>

This test checks if references functionality, implemented in the [Reference Contributor](reference_contributor.md) section of the Custom Language Support Tutorial, works as expected.

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
[Run](parsing_test.md#run-the-test) the test and make sure it's green.
