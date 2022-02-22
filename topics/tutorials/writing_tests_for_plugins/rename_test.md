[//]: # (title: 6. Rename Test)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="tests_prerequisites.md" include-id="custom_language_testing_tutorial_header"></include>

This test verifies the Simple Language in-place rename functionality, implemented in the [Reference Contributor](reference_contributor.md) section of the Custom Language Support Tutorial, works as expected.

## Define Input Test Data
Create the <path>RenameTestData.simple</path> properties file in the <path>testData</path> directory.

```bash
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

```bash
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
[Run](parsing_test.md#run-the-test) the test and make sure it's green.
