---
title: 1. Tests Prerequisites
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This page discusses the steps to configure a plugin project for creating tests.

## 1.1. Create a Folder for Tests
Open the plugin project, and under the `src` directory create a separate folder `test`.
Under `test`, create the `java` folder for test source code, and the folder `testData` for test data files and reimport the Gradle project.

```text
└── src
    ├── main
    │   ├── java
    │   └── resources
    └── test
        ├── java
        └── testData
```

## 1.2. Set the Run Configuration Parameters
Because some of the tests use Java files as test data, the tests need to mock up the project SDK.
IntelliJ IDEA does everything automatically when the utility class [`LightJavaCodeInsightFixtureTestCase`](upsource:///java/testFramework/src/com/intellij/testFramework/fixtures/LightJavaCodeInsightFixtureTestCase.java) is used as the basis for the tests.

The system properties are defined in the `build.gradle` file using the snippet shown below.
The `/path/to/community/` is set to the absolute path to the root directory of the local intellij-community source on the machine running the tests.
For example, on macOS the `path/to/community/` might be `/Users/<user name>/Documents/<IJ community source root>/`

```groovy
  test {
    systemProperty "idea.home.path", "/path/to/community/"
  }
```
