<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Tests and Fixtures

<link-summary>Main approaches for implementing tests.</link-summary>

<include from="testing_plugins.md" element-id="testSamples"/>

The IntelliJ Platform testing infrastructure is not tied to any specific test framework.
In fact, the IntelliJ IDEA Team uses [JUnit](https://junit.org), [TestNG](https://testng.org), and [Cucumber](https://cucumber.io/) for testing different parts of the project.
However, most of the tests are written using JUnit 3.

When writing your tests, you have the choice between using a standard base class to perform the test set up for you and using a fixture class, which lets you perform the setup manually and does not tie you to a specific test framework.

<snippet id="testFrameworkDependencies">

> All required [test-framework dependencies](tools_intellij_platform_gradle_plugin_dependencies_extension.md#testing) must be declared explicitly.
>
{title="Configuring Test Frameworks (2024.2+)"}

</snippet>

With the former approach, you can use classes such as [`BasePlatformTestCase`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/fixtures/BasePlatformTestCase.java).

With the latter approach, you use the [`IdeaTestFixtureFactory`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/fixtures/IdeaTestFixtureFactory.java) class to create instances of fixtures for the test environment.
You need to call the fixture creation and setup methods from the test setup method used by your test framework.
