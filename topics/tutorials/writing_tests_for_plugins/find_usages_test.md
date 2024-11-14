<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 8. Find Usages Test

<link-summary>Implementing and running test for finding usages functionality implemented as a part of the Custom Language Support Tutorial.</link-summary>

<tldr>

**Tested Functionality**: [](find_usages_provider.md)

</tldr>

<include from="tests_prerequisites.md" element-id="custom_language_testing_tutorial_header"></include>

## Define the Test Data
Create the <path>FindUsagesTestData.simple</path> file in the <path>testData</path> directory.

```properties
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

<include from="custom_language_testing_snippets.md" element-id="runTests"/>
