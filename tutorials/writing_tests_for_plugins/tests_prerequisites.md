---
title: 1. Tests Prerequisites
---


### 1.1. Create a folder for tests

Open the project with the plugin and create a separate folder "tests".
Mark the folder as a test source root via the context menu `Mark Directory As`  &rarr; `Test Source Root`.

### 1.2. Create a folder for test data

In our tests we will use test data, so we need one more folder *"testData"* to store these files.

### 1.3. Run Configuration Parameters

Since some of our tests will use Java files as test data, we need to mock up the project SDK.
IntelliJ IDEA does everything automatically when we use the special utility class
[LightCodeInsightFixtureTestCase](upsource:///java/testFramework/src/com/intellij/testFramework/fixtures/LightCodeInsightFixtureTestCase.java).

All we need to do is point the run configuration's working directory to the root of the [IntelliJ IDEA Community Edition sources](upsource:///README.md) (via `idea.home.path`) and enable the following VM options:

```
-ea -Xbootclasspath/p:../out/classes/production/boot -XX:+HeapDumpOnOutOfMemoryError -Xmx512m -XX:MaxPermSize=320m 
-Didea.system.path=../test-system -Didea.home.path=../ -Didea.config.path=../test-config -Didea.test.group=ALL_EXCLUDE_DEFINED
```       

If you're using Gradle, system properties must be passed to forked JVM via `build.gradle`:
```
test {
  systemProperty "idea.home.path", "/path/to/community/"
}
```

**Note**:

  >  Keep in mind that we have changed the _working directory_, so all the paths in tests extended from
  >  [LightCodeInsightFixtureTestCase](upsource:///java/testFramework/src/com/intellij/testFramework/fixtures/LightCodeInsightFixtureTestCase.java)
  >  will use relative path to the _source root_ of IntelliJ IDEA Community Edition.
