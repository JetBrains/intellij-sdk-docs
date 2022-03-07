[//]: # (title: 1. Tests Prerequisites)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<chunk id="custom_language_testing_tutorial_header">

> This page is part of multi step [](writing_tests_for_plugins.md) tutorial.
{type="tip"}

</chunk>

This page discusses the steps to configure a plugin project for creating tests.

## Create a Folder for Tests
Open the plugin project and create a separate folder named <path>test</path> under the <path>src</path> directory.
Under <path>test</path>, create the <path>java</path> folder for test source code, and the folder <path>testData</path> for test data files and reimport the Gradle project.

```text
└── src
    ├── main
    │   ├── java
    │   └── resources
    └── test
        ├── java
        └── testData
```

## Set the Run Configuration Parameters

Because some tests use Java files as test data, the tests need to mock up the project's SDK.
See the [](testing_faq.md#how-to-test-a-jvm-language) section for details.
