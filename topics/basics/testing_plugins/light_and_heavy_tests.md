[//]: # (title: Light and Heavy Tests)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Plugin tests run in a real, rather than mocked, IntelliJ Platform environment and use real implementations for most application and project [services](plugin_services.md).

Loading and initializing all the project components and services for a project to run tests is a relatively expensive operation, and we want to avoid doing it for each test.
Dependently on the loading and execution time, we make a difference between *light* tests and *heavy* tests available in the IntelliJ Platform test framework:

* *Light* tests reuse a project from the previous test run when possible.
* *Heavy* tests create a new project for each test.

Light and heavy tests use different base classes or fixture classes, as described below.

> Because of the performance difference, we recommend plugin developers to write *light* tests whenever possible.
>
{type="note"}

## Light Tests

The standard way of writing a light test is to extend the following classes:

* [`BasePlatformTestCase`](upsource:///platform/testFramework/src/com/intellij/testFramework/fixtures/BasePlatformTestCase.java) (2019.2 and later) for tests that don't have any Java dependencies.
  For plugins using pre-2019.2 versions use [`LightPlatformCodeInsightFixtureTestCase`](upsource:///platform/testFramework/src/com/intellij/testFramework/fixtures/LightPlatformCodeInsightFixtureTestCase.java).
* [`LightJavaCodeInsightFixtureTestCase`](upsource:///java/testFramework/src/com/intellij/testFramework/fixtures/LightJavaCodeInsightFixtureTestCase.java) (2019.2 and later) for tests that require the Java PSI or any related functionality.
  For plugins using pre-2019.2 versions use [`LightCodeInsightFixtureTestCase`](upsource:///java/testFramework/src/com/intellij/testFramework/fixtures/LightCodeInsightFixtureTestCase.java).

When writing a light test, you can specify the project's requirements that you need to have in your test, such as the module type, the configured [SDK](sdk.md), [facets](facet.md), [libraries](library.md), etc.
You do so by extending the [`LightProjectDescriptor`](upsource:///platform/testFramework/src/com/intellij/testFramework/LightProjectDescriptor.java) class and returning your project descriptor (usually stored in `static final` field) from `getProjectDescriptor()`.

If your plugin builds on top of Java support, please see [](testing_faq.md#how-to-test-a-jvm-language) to set up your test environment to obtain the required _Mock JDK_ automatically.

Before executing each test, the project instance will be reused if the test case returns the same project descriptor as the previous one or recreated if the descriptor is different (`equals() = false`).

## Heavy Tests

> If you need to set up a multi-module project for your tests, you **must** write a heavy test.
>
{type="note"}

> In 2019.3, `PlatformTestCase` has been renamed to [`HeavyPlatformTestCase`](upsource:///platform/testFramework/src/com/intellij/testFramework/HeavyPlatformTestCase.java) reflecting its "heavy test" characteristics.
>
{type="note"}

The setup code for a multi-module Java project looks something like that:

```java
TestFixtureBuilder<IdeaProjectTestFixture> projectBuilder =
        IdeaTestFixtureFactory.getFixtureFactory().createFixtureBuilder(getName());

// Repeat the following line for each module
JavaModuleFixtureBuilder moduleFixtureBuilder =
        projectBuilder.addModule(JavaModuleFixtureBuilder.class);

myFixture = JavaTestFixtureFactory.getFixtureFactory()
        .createCodeInsightFixture(projectBuilder.getFixture());
```
