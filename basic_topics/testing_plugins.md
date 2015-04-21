---
layout: editable
title: Testing Plugins
---


Before discussing the specific details of the IntelliJ IDEA plugin test framework, it's worth looking at the general approach that the IntelliJ IDEA team uses for testing the IDE code.
Our intention here is not so much to be prescriptive, and more to set the expectations we have good tools to support the approach that we use, and less good (or no) tools for approaches that we don't use.

Most of the tests in the IntelliJ IDEA codebase are *model-level functional tests*. What this means is the following:

*  The tests run in a headless environment which uses real production implementations for the majority of components, except for a number of UI components.

*  The tests usually test a feature as a whole, rather than individual functions that comprise its implementation.

*  The tests do not test the Swing UI and work directly with the underlying model instead.

*  Most of the tests take a source file or a set of source files as input data, execute a feature, and then compare the output with expected results (which can be specified as another set of source files, as special markup in the input file, or directly in the test code).

The biggest benefit of this test approach is that tests are very stable and require very little maintenance once they have been written, no matter how much the underlying implementation is refactored or rewritten.
In a product with 10+ years of lifetime that has gone through a large number of internal refactorings, we find that this benefit greatly outweighs the downsides of slower test execution and more difficult debugging of failures (compared to more isolated unit tests).

Another consequence of our testing approach is what our test framework does not provide:

*  We do not provide a recommended approach to mocking.
We have a few tests in our codebase that use JMock, but in general we find it difficult to mock all of the interactions with IntelliJ IDEA components that your plugin class will need to have, and we recommend working with real components instead.

*  We do not provide a general-purpose framework for Swing UI testing. You can try using tools such as
[FEST](http://fest.easytesting.org/) or
[Sikuli](http://www.sikuli.org/)
for plugin UI testing, but we don't use either of them and cannot provide any guidelines for their use.
Internally, we use manual testing for testing our Swing UIs.


*  [Tests and Fixtures](basic_topics/testing_plugins/tests_and_fixtures.html)
*  [Light and Heavy Tests](basic_topics/testing_plugins/light_and_heavy_tests.html)
*  [Test Project and Testdata Directories](basic_topics/testing_plugins/test_project_and_testdata_directories.html)
*  [Writing Tests](basic_topics/testing_plugins/writing_tests.html)
*  [Testing Highlighting](basic_topics/testing_plugins/testing_highlighting.html)


Check out
[this step-by-step tutorial](writing_tests_for_plugins.html)
teaching how to write and run automated tests for your custom language plugin (source code included).

