<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 4. Annotator Test

<link-summary>Implementing and running tests for the annotator implemented as a part of the Custom Language Support Tutorial.</link-summary>

<tldr>

**Tested Functionality**: [](annotator.md)

</tldr>

<include from="tests_prerequisites.md" element-id="custom_language_testing_tutorial_header"></include>

## Define Input Test Data
The <path>DefaultTestData.simple</path> file is reused for this test.

Create an input test file <path>AnnotatorTestData.java</path> in the <path>testData</path> directory.
This file contains two instances of Simple Language embedded in the Java code.
The first instance is a valid use of the `simple:` prefix followed by the Simple Language key `website`.
The second is a valid prefix but an invalid key `websit`, as noted by the test `<error>` [highlighting](testing_highlighting.md).

```java
public class Test {
  public static void main(String[] args) {
    System.out.println("simple:website");
    System.out.println("simple:<error descr="Unresolved property">websit</error>");
  }
}
```

> See how to [generate highlighting test data](testing_highlighting.md#generating-test-data).

## Define a Test Method
Add the `testAnnotator()` method to the `SimpleCodeInsightTest` class [previously defined](completion_test.md#define-a-test).
Again, this method configures the test fixture by using the test files.
It then calls the `checkHighlighting()` method to verify weak warnings.

```java
```
{src="simple_language_plugin/src/test/java/org/intellij/sdk/language/SimpleCodeInsightTest.java" include-symbol="testAnnotator"}

## Run the Test

<include from="custom_language_testing_snippets.md" element-id="runTests"/>
