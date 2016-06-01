---
title: Tests and Fixtures
---

The *IntelliJ Platform* testing infrastructure is not tied to any specific test framework. In fact, the IntelliJ IDEA Team uses JUnit, TestNG and Cucumber for testing different parts of the project. However, most of the tests are written using JUnit 3.

When writing your own tests, you have the choice between using a standard base class to perform the test set up for you and using a fixture class, which lets you perform the setup manually and does not tie you to a specific test framework.

With the former approach, you can use classes such as [`LightPlatformCodeInsightFixtureTestCase`](upsource:///platform/testFramework/src/com/intellij/testFramework/fixtures/LightPlatformCodeInsightFixtureTestCase.java) (despite being one of the longest and ugliest class names you'll run into in the *IntelliJ Platform* API, it's actually the recommended approach for writing tests).

With the latter approach, you use the [`IdeaTestFixtureFactory`](upsource:///platform/testFramework/src/com/intellij/testFramework/fixtures/IdeaTestFixtureFactory.java) class to create instances of fixtures for the test environment, and you need to call the fixture creation and setup methods from the test setup method used by your test framework.
