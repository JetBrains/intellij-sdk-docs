[//]: # (title: Testing Plugins)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Most of the tests in the IntelliJ Platform codebase are *model level functional tests*.
What this means is the following:

* The tests run in a headless environment that uses real production implementations for most components, except for many UI components.
* The tests usually test a feature as a whole, rather than individual functions that comprise its implementation.
* The tests do not test the Swing UI and work directly with the underlying model instead.
* Most of the tests take a source file or a set of source files as input data, execute a feature, and then compare the output with expected results.
  Results can be specified as another set of source files, special markup in the input file, or directly in the test code.

The most significant benefit of this test approach is that tests are very stable and require very little maintenance once written, no matter how much the underlying implementation is refactored or rewritten.

In a product with 15+ years of a lifetime that has gone through a large number of internal refactorings, we find that this benefit dramatically outweighs the downsides of slower test execution and more difficult debugging of failures being compared to more isolated unit tests.

### Mocks

Another consequence of our testing approach is that we do not provide a recommended approach to mocking.
We have a few tests in our codebase that use JMock.
Still, in general, we find it difficult to mock all of the interactions with IntelliJ Platform components that your plugin class will need to have.
We recommend working with real components instead.

### UI Tests

Please see dedicated [intellij-ui-test-robot](https://github.com/JetBrains/intellij-ui-test-robot) library.
It is fully integrated with Gradle-based setup via `runIdeForUiTests` task.

Please do not use _platform/testGuiFramework_; it is reserved for internal use.

## Topics
* [Tests and Fixtures](tests_and_fixtures.md)
* [Light and Heavy Tests](light_and_heavy_tests.md)
* [Test Project and Testdata Directories](test_project_and_testdata_directories.md)
* [Writing Tests](writing_tests.md)
* [Testing Highlighting](testing_highlighting.md)

  > Check out [this step-by-step tutorial](writing_tests_for_plugins.md) teaching how to write and run automated tests for your custom language plugin (source code included).
  > 
 {type="tip"}