---
title: 10. Reference Test
---


In this test we will check if references, implemented in the
[Reference Contributor](reference_contributor.html)
section of the
[Custom Language Support Tutorial](cls_tutorial.html),
works as we expect.

### 10.1. Define test data

Create a file *ReferenceTestData.java*.

```java
public class Test {
    public static void main(String[] args) {
        System.out.println("simple:website<caret>");
    }
}
```

### 10.2. Define a test method

```java
public void testReference() {
    myFixture.configureByFiles("ReferenceTestData.java", "DefaultTestData.simple");
    PsiElement element = myFixture.getFile().findElementAt(myFixture.getCaretOffset()).getParent();
    assertEquals("http://en.wikipedia.org/", ((SimpleProperty) element.getReferences()[0].resolve()).getValue());
}
```

### 10.3. Run the test

Run the test and make sure it's green.

-----

[Previous](commenter_test.html)
[Top](../writing_tests_for_plugins.html)





