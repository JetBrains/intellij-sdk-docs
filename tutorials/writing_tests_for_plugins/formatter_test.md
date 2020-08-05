---
title: 5. Formatter Test
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This test checks if the Simple Language formatter, implemented in the [Formatter](/tutorials/custom_language_support/formatter.md) section of the Custom Language Support Tutorial, works as expected.

## 5.1. Define Test Data
Create the `FormatterTestData.simple` properties file in the `testData` directory.

```bash
{% include /code_samples/simple_language_plugin/src/test/testData/FormatterTestData.simple %}
```

## 5.2. Define a Test Method
Add the `testFormatter()` method to the `SimpleCodeInsightTest` class [previously defined](completion_test.md#define-a-test).
* Again, this method configures the test fixture by using the test file.
* The code style Simple Language settings for spaces and blank lines are set.
* The file is then formatted according to the settings.
* The formatted file is compared to the expected results in the benchmark file `DefaultTestData.simple`.

```java
  public void testFormatter() {
    myFixture.configureByFile("FormatterTestData.simple");
    CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_AROUND_ASSIGNMENT_OPERATORS = true;
    CodeStyle.getLanguageSettings(myFixture.getFile()).KEEP_BLANK_LINES_IN_CODE = 2;
    WriteCommandAction.writeCommandAction(getProject()).run(() -> {
      CodeStyleManager.getInstance(getProject()).reformatText(myFixture.getFile(),
                                   ContainerUtil.newArrayList(myFixture.getFile().getTextRange()));
    });
    myFixture.checkResultByFile("DefaultTestData.simple");
  }
```

## 5.3. Run the Test
[Run](completion_test.md#run-the-test) the test and make sure it's green.

> **TIP** See also [`FormatterTestCase`](upsource:///platform/testFramework/src/com/intellij/psi/formatter/FormatterTestCase.java) as convenient base class.
