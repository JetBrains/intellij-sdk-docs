[//]: # (title: 3. Completion Test)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="tests_prerequisites.md" include-id="custom_language_testing_tutorial_header"></include>

This test checks if the Simple Language code completion functionality, implemented in the [Reference Contributor](reference_contributor.md) section of the Custom Language Support Tutorial, works as expected.

## Define Test Data
Create the <path>DefaultTestData.simple</path> file in the <path>testData</path> directory.

```bash
```
{src="simple_language_plugin/src/test/testData/DefaultTestData.simple"}

Create a test input Java file <path>CompleteTestData.java</path> in the <path>testData</path> directory.
This file contains a Simple Language reference within the Java code at `<caret>`.

```java
```
{src="simple_language_plugin/src/test/testData/CompleteTestData.java"}

## Define a Test
Subclass [`LightJavaCodeInsightFixtureTestCase`](upsource:///java/testFramework/src/com/intellij/testFramework/fixtures/LightJavaCodeInsightFixtureTestCase.java) to create `SimpleCodeInsightTest`.
Override `getTestDataPath()`, and return the path from the root of this plugin module to the <path>testData</path> directory.

```java
```
{src="simple_language_plugin/src/test/java/org/intellij/sdk/language/SimpleCodeInsightTest.java" include-symbol="getTestDataPath"}


At this point only one test is defined in `SimpleCodeInsightTest`: `testCompletion()`.
This method:
* Configures the test using the two input files.
* Calls the basic completion functionality.
  Behind the scenes, this method call creates a list of possible elements to complete the embedded Simple Language reference.
* Checks the list of returned lookup strings to ensure it matches the completion variants provided by the reference.

```java
```
{src="simple_language_plugin/src/test/java/org/intellij/sdk/language/SimpleCodeInsightTest.java" include-symbol="testCompletion"}


A number of related methods exist in `CodeInsightTestFixture` for testing completion and lookup elements, e.g., when testing completion variants and requiring only one testdata file `CodeInsightTestFixture.testCompletionVariants()`.

## Run the Test
[Run](parsing_test.md#run-the-test) the test and make sure it's green.
