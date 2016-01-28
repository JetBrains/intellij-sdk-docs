---
title: 6. Rename Test
---


In this test we will check if in-place rename, implemented in the
[Reference Contributor](/tutorials/custom_language_support/reference_contributor.md)
section of the
[Custom Language Support Tutorial](/tutorials/custom_language_support_tutorial.md), works as we expect.

### 6.1. Define input test data

Create a file *RenameTestData.simple*.

```bash
# You are reading the ".properties" entry.
! The exclamation mark can also mark text as comments.
website = http://en.wikipedia.org/

language = English
# The backslash below tells the application to continue reading
# the value onto the next line.
message = Welcome to \
          Wikipedia!
# Add spaces to the key
key\ with\ spaces = This is the value that could be looked up with the key "key with spaces".
# Unicode
tab : \u0009
```

Create a file *RenameTestData.java*.

```java
public class Test {
    public static void main(String[] args) {
        System.out.println("simple:website<caret>");
    }
}
```

### 6.2. Create output test data

Create a file *RenameTestDataAfter.simple*.

```bash
# You are reading the ".properties" entry.
! The exclamation mark can also mark text as comments.
websiteUrl = http://en.wikipedia.org/

language = English
# The backslash below tells the application to continue reading
# the value onto the next line.
message = Welcome to \
          Wikipedia!
# Add spaces to the key
key\ with\ spaces = This is the value that could be looked up with the key "key with spaces".
# Unicode
tab : \u0009
```

### 6.3. Define a test method

```java
public void testRename() {
    myFixture.configureByFiles("RenameTestData.java", "RenameTestData.simple");
    myFixture.renameElementAtCaret("websiteUrl");
    myFixture.checkResultByFile("RenameTestData.simple", "RenameTestDataAfter.simple", false);
}
```

### 6.4. Run the test

Run the test and make sure it's green.

[Previous](formatter_test.md)
[Top](/tutorials/writing_tests_for_plugins.md)
[Next](folding_test.md)


