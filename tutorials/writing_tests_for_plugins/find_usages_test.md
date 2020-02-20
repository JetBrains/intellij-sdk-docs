---
title: 8. Find Usages Test
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This test ensures the find usages provider, implemented in the [Find Usages Provider](/tutorials/custom_language_support/find_usages_provider.md) section of the Custom Language Support Tutorial, works correctly.

## 8.1. Define the Test Data
Create the `FindUsagesTestData.simple` properties file in the `testData` directory.

```bash
{% include /code_samples/simple_language_plugin/src/test/testData/FindUsagesTestData.simple %}
```

Create the test file `FindUsagesTestData.java`, which contains one embedded Simple Language prefix and key.

```java
{% include /code_samples/simple_language_plugin/src/test/testData/FindUsagesTestData.java %}
```

## 8.2. Define a Test Method
Add the `testFindUsages()` method to the `SimpleCodeInsightTest` class [previously defined](completion_test.md#define-a-test).
This test verifies the find usage functionality will identify the "key with spaces".

```java
  public void testFindUsages() {
    Collection<UsageInfo> usageInfos = myFixture.testFindUsages("FindUsagesTestData.simple", "FindUsagesTestData.java");
    assertEquals(1, usageInfos.size());
  }
```

## 8.3. Run the Test
[Run](completion_test.md#run-the-test) the test and make sure it's green.
