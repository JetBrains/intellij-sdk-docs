---
title: 3. Completion Test
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This test checks if the Simple Language code completion functionality, implemented in the [Reference Contributor](/tutorials/custom_language_support/reference_contributor.md) section of the Custom Language Support Tutorial, works as expected.

## 3.1. Define Test Data
Create the `DefaultTestData.simple` properties file in the `testData` directory.

```bash
{% include /code_samples/simple_language_plugin/src/test/testData/DefaultTestData.simple %}
```

Create a test input Java file `CompleteTestData.java` in the `testData` directory.
This file contains a Simple Language snippet within the Java.

```java
{% include /code_samples/simple_language_plugin/src/test/testData/CompleteTestData.java %}
```

## 3.2. Define a Test
Subclass [`LightJavaCodeInsightFixtureTestCase`](upsource:///java/testFramework/src/com/intellij/testFramework/fixtures/LightJavaCodeInsightFixtureTestCase.java) to create `SimpleCodeInsightTest`.
Override `getTestDataPath()`, and return the path from the root of this plugin module to the `testData` directory.

At this point only one test is defined in `SimpleCodeInsightTest`: `testCompletion()`.
This method:
* Configures the test using the two input files.
* Calls the basic completion functionality.
  Behind the scenes, this method call creates a list of possible elements to complete the embedded Simple Language reference.
* Checks the list of possible element names to ensure it contains all Simple Language completion possibilities.

```java
public class SimpleCodeInsightTest extends LightJavaCodeInsightFixtureTestCase {
  @Override
  protected String getTestDataPath() {
    return "src/test/testData";
  }

  public void testCompletion() {
    myFixture.configureByFiles("CompleteTestData.java", "DefaultTestData.simple");
    myFixture.complete(CompletionType.BASIC, 1);
    List<String> strings = myFixture.getLookupElementStrings();
    assertTrue(strings.containsAll(Arrays.asList("key with spaces", "language", "message", "tab", "website")));
    assertEquals(5, strings.size());
  }
}
```

## 3.3. Run the Test
Run the test by:
* Opening the **Gradle** Tool Window.
* Select the `simple_language_plugin`.
  You may need to reimport it as a Gradle project.
* Drill down under `simple_language_plugin` to *Tasks*, *verification*, *test* task.
* Run the *test* task.

The results are displayed in the **Run** Tool Window, and also written to the `simple_language_plugin/build/test-results/test/` directory.

If the **Run** Tool Window displays the error *Test events were not received*, do the following:
* In the **Gradle** Tool Window, drill down under `simple_language_plugin` to *Tasks*, *build*, *clean* task.
* Run the *clean* task, which deletes the `simple_language_plugin/build/` directory.
* Retry the test.