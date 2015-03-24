---
title: Completion Test
---

<!--
INITIAL_SOURCE https://confluence.jetbrains.com/display/IntelliJIDEA/Completion+Test
-->

# {{ page.title }}

In this test we will check if code completion, implemented in the
[Reference Contributor Tutorial](reference_contributor.html),
works as we expect.

### 1. Define test data

Create a file *DefaultTestData.simple*.

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

Create one more file *CompleteTestData.java*.

```java
public class Test {
    public static void main(String[] args) {
        System.out.println("simple:<caret>");
    }
}
```

### 2. Define a test

```java
package com.simpleplugin;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;

import java.util.Arrays;
import java.util.List;

public class SimpleCodeInsightTest extends LightCodeInsightFixtureTestCase {
    @Override
    protected String getTestDataPath() {
        return "../../SimplePlugin/testData";
    }

    public void testCompletion() {
        myFixture.configureByFiles("CompleteTestData.java", "DefaultTestData.simple");
        myFixture.complete(CompletionType.BASIC, 1);
        List<String> strings = myFixture.getLookupElementStrings();
        assertTrue(strings.containsAll(Arrays.asList("key\\ with\\ spaces", "language", "message", "tab", "website")));
        assertEquals(5, strings.size());
    }
}
```

### 3. Run the test

Run the test and make sure it's green.

-----

[Previous](parsing_test.html) [Top](writing_tests_for_plugins.html) [Next](annotator_test.html)

