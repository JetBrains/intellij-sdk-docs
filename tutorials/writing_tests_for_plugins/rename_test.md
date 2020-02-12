---
title: 6. Rename Test
---

This test verifies the Simple language in-place rename functionality, implemented in the [Reference Contributor](/tutorials/custom_language_support/reference_contributor.md) section of the Custom Language Support Tutorial, works as expected.

## 6.1. Define Input Test Data
Create the `RenameTestData.simple` properties file in the `testData` directory.

```bash
{% include /code_samples/simple_language_plugin/src/test/testData/RenameTestData.simple %}
```

Create the file `RenameTestData.java` in the `testData` directory.
This file contains one Simple language reference embedded in Java, with the [caret position](/basics/testing_plugins/test_project_and_testdata_directories.md#special-markup) placed just after a Simple language key.

```java
{% include /code_samples/simple_language_plugin/src/test/testData/RenameTestData.java %}
```

## 6.2. Create Output Test Data
Create the `RenameTestDataAfter.simple` properties file in the `testData` directory.
This file contains the expected outcome of the test.
Note the `website =` in `RenameTestData.simple` should be renamed to `websiteUrl =` by the test.

```bash
{% include /code_samples/simple_language_plugin/src/test/testData/RenameTestDataAfter.simple %}
```

## 6.3. Define a Test Method
Add the `testRename()` method to the `SimpleCodeInsightTest` class [previously defined](completion_test.md#define-a-test).
* Again, this method configures the test fixture by using the test files.
* The fixture then renames the Simple language element at the caret in `RenameTestData.java`.
* It then compares the input and output property files, ignoring whitespace.
 
```java
  public void testRename() {
    myFixture.configureByFiles("RenameTestData.java", "RenameTestData.simple");
    myFixture.renameElementAtCaret("websiteUrl");
    myFixture.checkResultByFile("RenameTestData.simple", "RenameTestDataAfter.simple", false);
  }
```

## 6.4. Run the Test
[Run](completion_test.md#run-the-test) the test and make sure it's green.
