<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 6. Rename Test

<link-summary>Implementing and running test for rename functionality implemented as a part of the Custom Language Support Tutorial.</link-summary>

<tldr>

**Tested Functionality**: [](reference_contributor.md)

</tldr>

<include from="tests_prerequisites.md" element-id="custom_language_testing_tutorial_header"></include>

## Define Input Test Data
Create the <path>RenameTestData.simple</path> properties file in the <path>testData</path> directory.

```properties
```
{src="simple_language_plugin/src/test/testData/RenameTestData.simple"}

Create the file <path>RenameTestData.java</path> in the <path>testData</path> directory.
This file contains one Simple Language reference embedded in Java, with the [caret position](test_project_and_testdata_directories.md#special-markup) placed just after a Simple Language key.

```java
```
{src="simple_language_plugin/src/test/testData/RenameTestData.java"}

## Create Output Test Data
Create the <path>RenameTestDataAfter.simple</path> file in the <path>testData</path> directory.
This file contains the expected outcome of the test.
Note the `website =` in <path>RenameTestData.simple</path> should be renamed to `websiteUrl =` by the test.

```properties
```
{src="simple_language_plugin/src/test/testData/RenameTestDataAfter.simple"}

## Define a Test Method
Add the `testRename()` method to the `SimpleCodeInsightTest` class [previously defined](completion_test.md#define-a-test).
* Again, this method configures the test fixture by using the test files.
* The fixture then renames the Simple Language element at the caret in <path>RenameTestData.java</path>.
* It then compares the input and output files, ignoring whitespace.

```java
```
{src="simple_language_plugin/src/test/java/org/intellij/sdk/language/SimpleCodeInsightTest.java" include-symbol="testRename"}


## Run the Test

<include from="custom_language_testing_snippets.md" element-id="runTests"/>
