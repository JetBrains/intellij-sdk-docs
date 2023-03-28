<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 9. Commenter Test

<link-summary>Implementing and running test for the commenter implemented as a part of the Custom Language Support Tutorial.</link-summary>

<tldr>

**Tested Functionality**: [](commenter.md)

</tldr>

<include from="tests_prerequisites.md" element-id="custom_language_testing_tutorial_header"></include>

## Define a Test Method
Add the `testCommenter()` method to the `SimpleCodeInsightTest` class [previously defined](completion_test.md#define-a-test).
This test constructs a Simple Language properties file containing one line, with the virtual caret positioned at the beginning of the line.
The test calls the commenter to insert a comment character at the caret, then verifies the results.
It again calls the line comment action to remove the comment character and verifies the results.

```java
```
{src="simple_language_plugin/src/test/java/org/intellij/sdk/language/SimpleCodeInsightTest.java" include-symbol="testCommenter"}

## Run the Test

<include from="custom_language_testing_snippets.md" element-id="runTests"/>
