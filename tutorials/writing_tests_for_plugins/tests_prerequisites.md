---
title: 1. Tests Prerequisites
---

This page discusses the steps to configure a plugin project for creating tests.

## 1.1. Create a Folder for Tests
Open the plugin project and under the `src` directory create a separate folder `test`.
Under `test`, create the `java` folder for test source code, and the folder `testData` for test resources.
```text
└── src
    ├── main
    │   ├── java
    │   └── resources
    └── test
        ├── java
        └── testData
```
Mark the `java` folder as a test source root via the context menu `Mark Directory As`  &rarr; `Test Source Root`.
Similarly, mark the `testData` folder as a test resource root via the context menu `Mark Directory As`  &rarr; `Test Resources Root`.

## 1.2. Set the Run Configuration Parameters
Since some of the tests use Java files as test data, the tests need to mock up the project SDK.
IntelliJ IDEA does everything automatically when we use the utility class [`LightJavaCodeInsightFixtureTestCase`](upsource:///java/testFramework/src/com/intellij/testFramework/fixtures/LightJavaCodeInsightFixtureTestCase.java) as the basis for our tests.

The system properties are defined in the `build.gradle` file using the snippet shown below.
The "/path/to/community/" is set to the absolute path to the root directory of the local intellij-community source on the machine running the tests.
```groovy
  test {
    systemProperty "idea.home.path", "/path/to/community/"
  }
```
