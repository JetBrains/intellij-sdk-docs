[//]: # (title: 8. Find Usages Test)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="tests_prerequisites.md" include-id="custom_language_testing_tutorial_header"></include>

This test ensures the find usages provider, implemented in the [Find Usages Provider](find_usages_provider.md) section of the Custom Language Support Tutorial, works correctly.

## Define the Test Data
Create the <path>FindUsagesTestData.simple</path> file in the <path>testData</path> directory.

```bash
```
{src="simple_language_plugin/src/test/testData/FindUsagesTestData.simple"}

Create the test file <path>FindUsagesTestData.java</path>, which contains one embedded Simple Language prefix and key.

```java
```
{src="simple_language_plugin/src/test/testData/FindUsagesTestData.java"}

## Define a Test Method
Add the `testFindUsages()` method to the `SimpleCodeInsightTest` class [previously defined](completion_test.md#define-a-test).
This test verifies the find usage functionality will identify the "key with spaces".

```java
```
{src="simple_language_plugin/src/test/java/org/intellij/sdk/language/SimpleCodeInsightTest.java" include-symbol="testFindUsages"}


## Run the Test
[Run](parsing_test.md#run-the-test) the test and make sure it's green.
