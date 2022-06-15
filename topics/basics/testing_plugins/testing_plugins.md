[//]: # (title: Testing Overview)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

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
Still, in general, we find it difficult to mock all of the interactions with IntelliJ Platform components that your plugin class will need to have.
We recommend working with real components instead.
See also [](testing_faq.md#how-to-replace-componentservice-in-tests) and [](testing_faq.md#how-to-replace-extension-points-in-tests).

### UI Tests

Please see the dedicated [intellij-ui-test-robot](https://github.com/JetBrains/intellij-ui-test-robot) library.
It is fully integrated with Gradle-based setup via [`runIdeForUiTests`](tools_gradle_intellij_plugin.md#runideforuitests-task) task.

Please do not use <path>platform/testGuiFramework</path> it is reserved for internal use.

## Topics

> Check out [this step-by-step tutorial](writing_tests_for_plugins.md) teaching how to write and run automated tests for your custom language plugin.
> Also, code samples
> [comparing_references_inspection](https://github.com/JetBrains/intellij-sdk-docs/tree/main/code_samples/comparing_references_inspection)
> and [conditional_operator_intention](https://github.com/JetBrains/intellij-sdk-docs/tree/main/code_samples/conditional_operator_intention) demonstrate using tests.
>
{type="note"}

* [](tests_and_fixtures.md)
* [](light_and_heavy_tests.md)
* [](test_project_and_testdata_directories.md)
* [](writing_tests.md)
* [](testing_highlighting.md)
* [](testing_faq.md)

> If a topic you are interested in is not covered in the above sections, let us know via the "**Was this page helpful?**" feedback form below or [other channels](getting_help.md#problems-with-the-guide).
>
> Please be specific about the topics and reasons for adding them, and leave your email in case we need more details.
>
{type="note"}
