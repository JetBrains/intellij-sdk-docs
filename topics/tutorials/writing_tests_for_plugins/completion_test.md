<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 3. Completion Test

<tldr>

**Tested Functionality**: [](reference_contributor.md)

</tldr>

<include from="tests_prerequisites.md" element-id="custom_language_testing_tutorial_header"></include>

## Define Test Data
Create the <path>DefaultTestData.simple</path> file in the <path>testData</path> directory.
This file contains test Simple language properties that will be completed in a test Java file (see the `Test` class later in this section).

```properties
```
{src="simple_language_plugin/src/test/testData/DefaultTestData.simple"}

Create a test input Java file <path>CompleteTestData.java</path> in the <path>testData</path> directory.
This file contains a Simple Language reference within the Java code at `<caret>` [special marker](test_project_and_testdata_directories.md#special-markup), which denotes the caret position to use in the test.

```java
```
{src="simple_language_plugin/src/test/testData/CompleteTestData.java"}

## Define a Test
Subclass [`LightJavaCodeInsightFixtureTestCase`](%gh-ic%/java/testFramework/src/com/intellij/testFramework/fixtures/LightJavaCodeInsightFixtureTestCase.java) to create `SimpleCodeInsightTest`.
Override `getTestDataPath()`, and return the path from the root of this plugin module to the <path>testData</path> directory.

```java
```
{src="simple_language_plugin/src/test/java/org/intellij/sdk/language/SimpleCodeInsightTest.java" include-symbol="getTestDataPath"}


At this point, only one test is defined in `SimpleCodeInsightTest`: `testCompletion()`.
This method:
* Configures the test using the two input files.
* Calls the basic completion functionality.
  Behind the scenes, this method call creates a list of possible elements to complete the embedded Simple Language reference.
* Checks the list of returned lookup strings to ensure it matches the completion variants provided by the reference.

```java
```
{src="simple_language_plugin/src/test/java/org/intellij/sdk/language/SimpleCodeInsightTest.java" include-symbol="testCompletion"}


A number of related methods exist in [`CodeInsightTestFixture`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/fixtures/CodeInsightTestFixture.java) for testing completion and lookup elements, e.g., when testing completion variants and requiring only one testdata file `CodeInsightTestFixture.testCompletionVariants()`.

## Run the Test

<include from="custom_language_testing_snippets.md" element-id="runTests"/>
