---
title: 4. Annotator Test
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This test checks if the Simple Language annotator functionality, implemented in the [Annotator](/tutorials/custom_language_support/annotator.md) section of the Custom Language Support Tutorial, works as expected.

## 4.1. Define Input Test Data
The `DefaultTestData.simple` properties file is reused for this test.

Create an input test file `AnnotatorTestData.java` in the `testData` directory.
This file contains two instances of Simple Language embedded in the Java code.
The first instance is a valid use of the `simple:` prefix followed by the Simple Language key `website`.
The second is a valid prefix but an invalid key, as noted by the test `<error>` [highlighting](/basics/testing_plugins/testing_highlighting.md).

```java
public class Test {
  public static void main(String[] args) {
    System.out.println("simple:website");
    System.out.println("simple:<error descr="Unresolved property">websit</error>");
  }
}
```

## 4.2. Define a Test Method
Add the `testAnnotator()` method to the `SimpleCodeInsightTest` class [previously defined](completion_test.md#define-a-test).
Again, this method configures the test fixture by using the test files.
It then calls the `checkHighlighting()` method to verify weak warnings.

```java
public void testAnnotator() {
    myFixture.configureByFiles("AnnotatorTestData.java", "DefaultTestData.simple");
    myFixture.checkHighlighting(false, false, true, true);
}
```

## 4.3. Run the Test
[Run](completion_test.md#run-the-test) the test and make sure it's green.
