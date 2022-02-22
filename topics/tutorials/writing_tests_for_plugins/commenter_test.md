[//]: # (title: 9. Commenter Test)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="tests_prerequisites.md" include-id="custom_language_testing_tutorial_header"></include>

This test will check if the commenter, implemented in the [Commenter](commenter.md) section of the Custom Language Support Tutorial, works as expected.

## Define a Test Method
Add the `testCommenter()` method to the `SimpleCodeInsightTest` class [previously defined](completion_test.md#define-a-test).
This test constructs a Simple Language properties file containing one line, with the virtual caret positioned at the beginning of the line.
The test calls the commenter to insert a comment character at the caret, then verifies the results.
It again calls the line comment action to remove the comment character and verifies the results.

```java
```
{src="simple_language_plugin/src/test/java/org/intellij/sdk/language/SimpleCodeInsightTest.java" include-symbol="testCommenter"}

## Run the Test
[Run](parsing_test.md#run-the-test) the test and make sure it's green.
