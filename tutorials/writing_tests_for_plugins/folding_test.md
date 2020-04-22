---
title: 7. Folding Test
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This test verifies the Simple Language folding builder, implemented in the [Folding Builder](/tutorials/custom_language_support/folding_builder.md) section of the Custom Language Support Tutorial, works as expected.

> **NOTE** A folding builder must implement [`DumbAware`](upsource:///platform/core-api/src/com/intellij/openapi/project/DumbAware.java) to pass tests. See [Define a Folding Builder](/tutorials/custom_language_support/folding_builder.md#define-a-folding-builder) for more information.

## 7.1. Define Test Data
Create a file `FoldingTestData.java` in the `testData` directory.
This java file contains markup instructions for three different cases of code folding.

```java
{% include /code_samples/simple_language_plugin/src/test/testData/FoldingTestData.java %}
```

## 7.2. Define a Test
Add the `testFolding()` method to the `SimpleCodeInsightTest` class [previously defined](completion_test.md#define-a-test).
This test method reuses the `DefaultTestData.simple` properties file. 

```java
  public void testFolding() {
    myFixture.configureByFile("DefaultTestData.simple");
    myFixture.testFolding(getTestDataPath() + "/FoldingTestData.java");
  }
```

## 7.3. Run the Test
[Run](completion_test.md#run-the-test) the test and make sure it's green.
