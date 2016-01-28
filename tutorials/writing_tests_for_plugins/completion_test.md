---
title: 3. Completion Test
---


In this test we will check if code completion, implemented in the [Reference Contributor](/tutorials/custom_language_support/reference_contributor.md) section of the [Custom Language Support Tutorial](/tutorials/custom_language_support_tutorial.md) works as we expect.

### 3.1. Define test data

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
{% include /code_samples/simple_language_plugin/testData/CompleteTestData.java %}
```

### 3.2. Define a test

```java
{% include /code_samples/simple_language_plugin/tests/com/simpleplugin/SimpleCodeInsightTest.java %}
```

### 3.3. Run the test

Run the test and make sure it's green.

-----

[Previous](parsing_test.md)
[Top](/tutorials/writing_tests_for_plugins.md)
[Next](annotator_test.md)

