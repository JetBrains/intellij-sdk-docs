<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 11. Documentation Test

<link-summary>Implementing and running test for documentation provider implemented as a part of the Custom Language Support Tutorial.</link-summary>

<tldr>

**Tested Functionality**: [](documentation_provider.md)

</tldr>

<include from="tests_prerequisites.md" element-id="custom_language_testing_tutorial_header"></include>

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

<include from="custom_language_testing_snippets.md" element-id="runTests"/>
