---
title: 8. Find Usages Test
---

In this test we will check if the find usages provider, implemented in the
[Find Usages Provider](/tutorials/custom_language_support/find_usages_provider.md)
section of the
[Custom Language Support Tutorial](/tutorials/custom_language_support_tutorial.md),
works correctly.

### 8.1. Define test data

Create a file *FindUsagesTestData.simple*.

```bash
# You are reading the ".properties" entry.
! The exclamation mark can also mark text as comments.
<caret>website = http://en.wikipedia.org/

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

Create a file *FindUsagesTestData.java*.

```java
public class Test {
    public static void main(String[] args) {
        System.out.println("simple:website");
    }
}
```

### 8.2. Define a test method

```java
public void testFindUsages() {
    Collection<UsageInfo> usageInfos = myFixture.testFindUsages("FindUsagesTestData.simple", "FindUsagesTestData.java");
    assertEquals(1, usageInfos.size());
}
```

### 8.3. Run the test

Run the test and make sure it's green.

-----

[Previous](folding_test.md)
[Top](/tutorials/writing_tests_for_plugins.md)
[Next](commenter_test.md)
