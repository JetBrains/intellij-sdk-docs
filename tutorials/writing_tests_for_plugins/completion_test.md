---
title: 3. Completion Test
---

This test checks if code completion, implemented in the [Reference Contributor](/tutorials/custom_language_support/reference_contributor.md) section of the Custom Language Support Tutorial, works as expected.

## 3.1. Define Test Data
Create an input Simple file `DefaultTestData.simple` for the test.
```bash
{% include /code_samples/simple_language_plugin/src/test/testData/DefaultTestData.simple %}
```

Create an input java file `CompleteTestData.java` for the test.
```java
{% include /code_samples/simple_language_plugin/src/test/testData/CompleteTestData.java %}
```

## 3.2. Define a Test
Subclass `LightJavaCodeInsightFixtureTestCase` to create `SimpleCodeInsightTest`.
Override `getTestDataPath()`, and return the path from the root of this plugin module to the `testData` directory.
At this point only one test is defined, `testCompletion()`.
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

## 3.3. Run the test
As [before](parsing_test.md#run-the-test), run the test and make sure it's green.
