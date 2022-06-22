[//]: # (title: Testing FAQ)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This page lists a number of common questions/issues and techniques useful for testing plugins.

## Useful Classes

- [`UsefulTestCase`](upsource:///platform/testFramework/src/com/intellij/testFramework/UsefulTestCase.java)
- [`PlatformTestUtil`](upsource:///platform/testFramework/src/com/intellij/testFramework/PlatformTestUtil.java)
- [`CodeInsightTestUtil`](upsource:///platform/testFramework/src/com/intellij/testFramework/fixtures/CodeInsightTestUtil.java)
- [`EditorTestUtil`](upsource:///platform/testFramework/src/com/intellij/testFramework/EditorTestUtil.java)
- [`PsiTestUtil`](upsource:///platform/testFramework/src/com/intellij/testFramework/PsiTestUtil.java)
- [`VfsTestUtil`](upsource:///platform/testFramework/src/com/intellij/testFramework/VfsTestUtil.java)
- [`IoTestUtil`](upsource:///platform/testFramework/src/com/intellij/openapi/util/io/IoTestUtil.java)

### UI

- [`ProjectViewTestUtil`](upsource:///platform/testFramework/src/com/intellij/testFramework/ProjectViewTestUtil.java)
- [`TestLookupElementPresentation`](upsource:///platform/testFramework/src/com/intellij/testFramework/fixtures/TestLookupElementPresentation.java)
- [`IconTestUtil`](upsource:///platform/testFramework/src/com/intellij/ui/IconTestUtil.java)
- [`TreeTestUtil`](upsource:///platform/testFramework/src/com/intellij/ui/tree/TreeTestUtil.java)

## Issues

### "No Tests Found" targeting 2021.3+

Please see [notes](https://plugins.jetbrains.com/docs/intellij/api-changes-list-2021.html#20213).

### How to avoid flaky tests?

Always call `super.tearDown()` inside `finally {..}` block of your test class to avoid leaks and side effects from previously run (failed) tests:

```java
void tearDown() {
  try {
    // test specific tear down calls
  }
  catch (Exception e) {
    addSuppressedException(e);
  }
  finally {
    super.tearDown();
  }
}
```

Avoid OS-specific assumptions (e.g., filesystem case-sensitivity, hardcoded separator instead of `java.io.File.separator`).

Use _ordered_ collections or [`UsefulTestCase.assertUnorderedCollection()`](upsource:///platform/testFramework/src/com/intellij/testFramework/UsefulTestCase.java).

Code deferring execution (e.g., via `Application.invokeLater()`) might not run during test execution (and possibly fails in production, too).
Use `Application.invokeLater(runnable, myProject.getDisposed()`.

### How to avoid test failure when using resources?

In some situations, added or changed files (e.g. XML DTDs provided by a plugin) are not refreshed in [](virtual_file_system.md).
In such cases, simply delete <path>test-system/caches</path> in your [sandbox directory](ide_development_instance.md#the-development-instance-sandbox-directory) and try again.

### How to enable DEBUG/TRACE logging?

Provide JVM system properties (Gradle: via `systemProperty` for `test` task) `idea.log.debug.categories` or `idea.log.trace.categories`, respectively.
Multiple categories can be set using a comma separated value list.

### How to get separate logs for failing tests?

Set system property `idea.split.test.logs` to `true` to generate separate test log files in <path>splitTestLogs</path> subdirectory for failing tests (WARN/ERROR level messages) (2021.3).

## Techniques

### How to mark test-only elements in production code?

Annotate with [`org.jetbrains.annotations.TestOnly`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/TestOnly.java), usages will be highlighted via inspection <control>JVM languages | Test-only usage in production code</control>.

### How to run tests for all files in a directory?

Use [`FileBasedTestCaseHelper`](upsource:///platform/testFramework/src/com/intellij/testFramework/FileBasedTestCaseHelper.java), please see its javadoc for instructions.

### How to modify setup on a per-test basis?

Use `UsefulTestCase.getTestName()` or create your own annotation(s) which can be checked via `UsefulTestCase.annotatedWith()`.

### How to run a performance test?

Use [`PlatformTestUtil.startPerformanceTest()`](upsource:///platform/testFramework/src/com/intellij/testFramework/PlatformTestUtil.java) to assert machine-adjusted metrics.

### How to dispatch pending UI events?

Use [`PlatformTestUtil.dispatchAllInvocationEventsInIdeEventQueue()`](upsource:///platform/testFramework/src/com/intellij/testFramework/PlatformTestUtil.java).

### How to disable stderr logging?

Use [`DefaultLogger.disableStderrDumping()`](upsource:///platform/util/src/com/intellij/openapi/diagnostic/DefaultLogger.java) passing `getTestRootDisposable()`.

### How to register a resource (DTD, XSD) temporarily?

Use [`ExternalResourceManagerExImpl.registerResourceTemporarily()`](upsource:///xml/xml-psi-impl/src/com/intellij/javaee/ExternalResourceManagerExImpl.java) passing `getTestRootDisposable()`.

### How to replace component/service in tests?

Provide dedicated test implementation via `testServiceImplementation` in [service declaration](plugin_services.md#declaring-a-service), or use [`ServiceContainerUtil`](upsource:///platform/testFramework/src/com/intellij/testFramework/ServiceContainerUtil.kt).

### How to replace extension points in tests?

Use [`ExtensionTestUtil`](upsource:///platform/testFramework/src/com/intellij/testFramework/ExtensionTestUtil.kt).

### How to wait for a specified amount of time?

If possible, use [](#how-to-wait-for-condition-with-timeout) approach. Otherwise, call `com.intellij.util.TimeoutUtil.sleep()`.

### How to wait for condition with timeout?

Use [`WaitFor`](upsource:///platform/util/src/com/intellij/util/WaitFor.java).

### How to test a JVM language?

Plugins supporting a JVM language may require JDK and language standard library to be set up in a test project, so that classes like `java.lang.String` can be correctly resolved during tests.
Tests extending [`LightJavaCodeInsightFixtureTestCase`](upsource:///java/testFramework/src/com/intellij/testFramework/fixtures/LightJavaCodeInsightFixtureTestCase.java) use one of the mock JDKs distributed with the [IntelliJ Community project](https://github.com/JetBrains/intellij-community) sources (notice <path>java/mockJDK-$JAVA_VERSION$</path> directories).
These JAR files are not available in plugin project dependencies, so the IntelliJ Community sources must be checked out to the machine running the tests, and sources' location must be provided to the test framework.
It's done by setting the `idea.home.path` system property to the absolute path of the checked-out sources in the `test` task configuration:


<tabs>
<tab title="Kotlin">

```kotlin
test {
  systemProperty("idea.home.path", "/path/to/intellij-community-sources")
}
```

</tab>
<tab title="Groovy">

```groovy
test {
  systemProperty "idea.home.path", "/path/to/intellij-community-sources"
}
```

</tab>
</tabs>


The default JDK version used by the test framework depends on the target platform version and is the latest supported version.
The easiest way to change the JDK version to a custom one is by overriding `LightJavaCodeInsightFixtureTestCase.getProjectDescriptor()` and using one of the predefined project descriptors in `LightJavaCodeInsightFixtureTestCase`.
If a project descriptor requires more customizations, its `getSdk()` method can use one of the [`IdeaTestUtil.getMockJdk*()`](upsource:///java/testFramework/src/com/intellij/testFramework/IdeaTestUtil.java) methods.

Sometimes, testing a JVM language requires adding standard or other libraries to a test project.
If a required library is available in the Maven repository, use [`MavenDependencyUtil`](upsource:///java/testFramework/src/com/intellij/testFramework/fixtures/MavenDependencyUtil.java), e.g.:

```java
MavenDependencyUtil.addFromMaven(model,
    "org.jetbrains.kotlin:kotlin-stdlib:1.6.10");
```

For [light tests](light_and_heavy_tests.md), use convenience method [`DefaultLightProjectDescriptor.withRepositoryLibrary()`](upsource:///java/testFramework/src/com/intellij/testFramework/fixtures/DefaultLightProjectDescriptor.java)
and [`JavaModuleFixtureBuilder.addMavenLibrary()`](upsource:///java/testFramework/src/com/intellij/testFramework/builders/JavaModuleFixtureBuilder.java) for [heavy tests](light_and_heavy_tests.md).

If a required library is an unpublished JAR file, use [`PsiTestUtil.addLibrary()`](upsource:///platform/testFramework/src/com/intellij/testFramework/PsiTestUtil.java) or `addProjectLibrary()` method and the JAR file path, e.g.:

```java
PsiTestUtil.addLibrary(model,
    "internal-library", getTestDataPath(), "internal-library-2.0.jar");
```

> If a topic you are interested in is not covered in the above sections, let us know via the "**Was this page helpful?**" feedback form below or [other channels](getting_help.md#problems-with-the-guide).
>
> Please be specific about the topics and reasons for adding them, and leave your email in case we need more details.
>
{type="note"}
