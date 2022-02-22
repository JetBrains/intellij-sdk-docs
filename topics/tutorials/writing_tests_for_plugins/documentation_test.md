[//]: # (title: 11. Documentation Test)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="tests_prerequisites.md" include-id="custom_language_testing_tutorial_header"></include>

This test will check if the documentation provider, implemented in the [Documentation](documentation_provider.md) section of the Custom Language Support Tutorial, works as expected.

## Define a Test Method
Add the `testDocumentation()` method to the `SimpleCodeInsightTest` class [previously defined](completion_test.md#define-a-test).
This test uses the files <path>DocumentationTestData.java</path> and <path>DocumentationTestData.simple</path> from the <path>testData</path> directory
to construct a test project and opens the first one in the editor with the virtual caret positioned at the usage of the Simple Language property.

Using the `DocumentationManager`, it checks if a target element for documentation is found, retrieves the documentation provider for the
Simple Language and creates the documentation string for the target element.
Finally, the documentation string is verified against the expected output.

```java
```
{src="simple_language_plugin/src/test/java/org/intellij/sdk/language/SimpleCodeInsightTest.java" include-symbol="testDocumentation"}

## Run the Test
[Run](parsing_test.md#run-the-test) the test and make sure it's green.
