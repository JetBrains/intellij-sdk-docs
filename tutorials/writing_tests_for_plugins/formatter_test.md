---
title: 5. Formatter Test
---

In this test we will check if the formatter, implemented in the
[Formatter](formatter.html)
section of the
[Custom Language Support Tutorial](cls_tutorial.html)
works as we expect.

### 5.1. Define test data

Create a file *FormatterTestData.simple*.

```bash
# You are reading the ".properties" entry.
! The exclamation mark can also mark text as comments.
website=http://en.wikipedia.org/

language= English
# The backslash below tells the application to continue reading
# the value onto the next line.
message  =  Welcome to \
          Wikipedia!
# Add spaces to the key
key\ with\ spaces = This is the value that could be looked up with the key "key with spaces".
# Unicode
tab :\u0009
```

### 5.2. Define a test method

```java
public void testFormatter() {
    myFixture.configureByFiles("FormatterTestData.simple");
    CodeStyleSettingsManager.getSettings(getProject()).SPACE_AROUND_ASSIGNMENT_OPERATORS = true;
    ApplicationManager.getApplication().runWriteAction(new Runnable() {
        @Override
        public void run() {
            CodeStyleManager.getInstance(getProject()).reformat(myFixture.getFile());
        }
    });
    myFixture.checkResultByFile("DefaultTestData.simple");
}
```

### 5.3. Run the test

Run the test and make sure it's green.

-----

[Previous](annotator_test.html)
[Top](../writing_tests_for_plugins.html)
[Next](rename_test.html)
