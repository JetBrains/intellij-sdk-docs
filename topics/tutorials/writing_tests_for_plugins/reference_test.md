[//]: # (title: 10. Reference Test)

<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This test checks if references functionality, implemented in the [Reference Contributor](reference_contributor.md) section of the Custom Language Support Tutorial, works as expected.

## Define Test Data
This test reuses the Simple Language properties file `DefaultTestData.simple`.

Create the test file `ReferenceTestData.java` in the `testData` directory.
This file has one Simple Language prefix and key, with the caret placed after the key.

```java
```
{src="simple_language_plugin/src/test/testData/ReferenceTestData.java"}

## Define a Test Method
Add the ` testReference()` method to the `SimpleCodeInsightTest` class [previously defined](completion_test.md#define-a-test).
This test is configured by the test files.
The fixture gets the `PsiReference` at the caret position, and then asserts the resolved `SimpleProperty.value()` with the known value of that key.

```java
  public void testReference() {
    PsiReference referenceAtCaret = 
            myFixture.getReferenceAtCaretPositionWithAssertion("ReferenceTestData.java", "DefaultTestData.simple");
    final SimpleProperty resolvedSimpleProperty = assertInstanceOf(referenceAtCaret.resolve(), SimpleProperty.class);
    assertEquals("https://en.wikipedia.org/", resolvedSimpleProperty.getValue());
  }
```

## Run the Test
[Run](completion_test.md#run-the-test) the test and make sure it's green.