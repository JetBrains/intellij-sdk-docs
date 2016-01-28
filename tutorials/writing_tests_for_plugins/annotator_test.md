---
title: 4. Annotator Test
---

In this test we will check if the annotator, implemented in the
[Annotator](/tutorials/custom_language_support/annotator.md) section
of the
[Custom Language Support Tutorial](/tutorials/custom_language_support_tutorial.md)
works as we expect.

### 4.1. Define test data

Create a file *AnnotatorTestData.java*.

```java
public class Test {
    public static void main(String[] args) {
        System.out.println("simple:website");
        System.out.println("simple:<error descr="Unresolved property">websit"</error>);
    }
}
```

### 4.2. Define a test method

```java
public void testAnnotator() {
    myFixture.configureByFiles("AnnotatorTestData.java", "DefaultTestData.simple");
    myFixture.checkHighlighting(false, false, true);
}
```

### 4.3. Run the test

Run the test and make sure it's green.

-----

[Previous](completion_test.md)
[Top](/tutorials/writing_tests_for_plugins.md)
[Next](formatter_test.md)

