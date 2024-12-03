<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Testing Overview

<link-summary>Introduction to testing plugins.</link-summary>

Most of the tests in the IntelliJ Platform codebase are *model-level functional tests*.
What this means is the following:

* The tests run in a headless environment that uses real production implementations for most components, except for many UI components.
* The tests usually test a feature as a whole rather than individual functions that comprise its implementation.
* The tests do not test the Swing UI and work directly with the underlying model instead (see also [](#ui-tests)).
* Most tests take a source file or a set of source files as [input data](test_project_and_testdata_directories.md), execute a feature, and compare the output with expected results.
  Results can be specified as another set of source files, [special markup](testing_highlighting.md) in the input file, or directly in the test code.

The most significant benefit of this test approach is that tests are very stable and require very little maintenance once written, no matter how much the underlying implementation is refactored or rewritten.

In a product with 20+ years of a lifetime that has gone through many internal refactorings, we find that this benefit dramatically outweighs the downsides of slower test execution and more difficult debugging of failures compared to more isolated unit tests.

### Mocks

Another consequence of our testing approach is that we do not provide a recommended approach to mocking.
We have a few tests in our codebase that use JMock.
Still, in general, we find it difficult to mock all the interactions with IntelliJ Platform components that your plugin class will need to have.
We recommend working with real components instead.
See also [](testing_faq.md#how-to-replace-componentservice-in-tests) and [](testing_faq.md#how-to-replace-extension-points-in-tests).

### UI Tests

UI tests using the dedicated [intellij-ui-test-robot](https://github.com/JetBrains/intellij-ui-test-robot) library can be run via Gradle tasks:

<tabs>
<tab title="IntelliJ Platform Gradle Plugin (2.x)">

Use [`testIdeUi`](tools_intellij_platform_gradle_plugin_tasks.md#testIdeUi) task.

</tab>
<tab title="Gradle IntelliJ Plugin (1.x)">

Use [`runIdeForUiTests`](tools_gradle_intellij_plugin.md#tasks-runideforuitests) task.

</tab>
</tabs>

Do not use <path>platform/testGuiFramework</path>, as it is reserved for internal use.

<video src="https://www.youtube.com/watch?v=UJexzfG01Qo"/>

_The IntelliJ Platform integration testing framework, used internally for performance, functional, and UI tests, is now accessible to help streamline and enhance your plugin testing experience._

## Topics

<snippet id="testSamples">

> Check out [this step-by-step tutorial](writing_tests_for_plugins.md) teaching how to write and run automated tests for your custom language plugin.
> Also, code samples
> [comparing_string_references_inspection](%gh-sdk-samples-master%/comparing_string_references_inspection)
> and [conditional_operator_intention](%gh-sdk-samples-master%/conditional_operator_intention) demonstrate using tests.
>
{style="note"}

</snippet>

* [](tests_and_fixtures.md)
* [](light_and_heavy_tests.md)
* [](test_project_and_testdata_directories.md)
* [](writing_tests.md)
* [](testing_highlighting.md)
* [](testing_faq.md)

<include from="snippets.md" element-id="missingContent"/>
