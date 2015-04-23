---
layout: editable
title: Light and Heavy Tests
---

As mentioned above, plugin tests run in a real, rather than mocked, IntelliJ Platform environment, and use real implementations for most of the application and project components. Loading the project components for a project is a fairly expensive operation, and we want to avoid doing it for each test.
Because of that, we distinguish between *heavy*  tests, which create a new project for each test, and *light*  tests, which reuse a project from the previous test run when possible. Light and heavy tests use different base classes or fixture classes, as described below.

**Note:** Because of the performance difference, we recommend plugin developers to write light tests whenever possible.

The standard way of writing a light test is to extend the ```LightCodeInsightFixtureTestCase``` class (for tests that require the Java PSI or any related functionality) or ```LightPlatformCodeInsightFixtureTestCase``` (for tests that don't have any Java dependencies).

When writing a light test, you can specify the requirements for the project that you need to have in your test, such as the module type, the configured SDK, facets, libraries etc.
You do so by extending the ```LightProjectDescriptor``` class and returning your project descriptor from ```LightCodeInsightFixtureTestCase.getProjectDescriptor()```.
Before executing each test, the project will be reused if the test case returns the same project descriptor as the previous one, or recreated if the descriptor is different.

If you need to set up a multi-module project for your tests, you must write a heavy test. The setup code for a multi-module Java project looks something like that:


```java
final TestFixtureBuilder<IdeaProjectTestFixture> projectBuilder = IdeaTestFixtureFactory.getFixtureFactory().createFixtureBuilder(getName());

// repeat the following line for each module
final JavaModuleFixtureBuilder moduleFixtureBuilder = projectBuilder.addModule(JavaModuleFixtureBuilder.class);

myFixture = JavaTestFixtureFactory.getFixtureFactory().createCodeInsightFixture(projectBuilder.getFixture());
```