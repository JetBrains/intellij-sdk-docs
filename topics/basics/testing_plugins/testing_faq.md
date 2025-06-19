<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Testing FAQ

<web-summary>
Common FAQ, techniques, and issues for testing plugins. Writing automated tests, avoid flaky tests, debug, and handle common scenarios.
</web-summary>

<link-summary>Common questions and issues for testing plugins.</link-summary>

This page lists a number of common questions/issues and techniques useful for testing plugins.

<include from="testing_plugins.md" element-id="testSamples"/>

## Useful Classes

- [`UsefulTestCase`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/UsefulTestCase.java)
- [`PlatformTestUtil`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/PlatformTestUtil.java)
- [`CodeInsightTestUtil`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/fixtures/CodeInsightTestUtil.java)
- [`EditorTestUtil`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/EditorTestUtil.java)
- [`PsiTestUtil`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/PsiTestUtil.java)
- [`VfsTestUtil`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/VfsTestUtil.java)
- [`IoTestUtil`](%gh-ic%/platform/testFramework/src/com/intellij/openapi/util/io/IoTestUtil.java)
- [`IndexingTestUtil`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/IndexingTestUtil.kt)
- [`LeakHunter`](%gh-ic%/platform/testFramework/common/src/LeakHunter.java)

### UI

See [](integration_tests_intro.md) for UI integration tests.

- [`ProjectViewTestUtil`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/ProjectViewTestUtil.java)
- [`TestLookupElementPresentation`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/fixtures/TestLookupElementPresentation.java)
- [`IconTestUtil`](%gh-ic%/platform/testFramework/src/com/intellij/ui/IconTestUtil.java)
- [`TreeTestUtil`](%gh-ic%/platform/testFramework/src/com/intellij/ui/tree/TreeTestUtil.java)
- [`EdtTestUtil`](%gh-ic%/platform/testFramework/common/src/EdtTestUtil.java)

## Issues

### Unresolved test-framework dependencies
<primary-label ref="2024.2"/>

<include from="tests_and_fixtures.md" element-id="testFrameworkDependencies"/>

### "No Tests Found" targeting 2021.3+
<primary-label ref="2021.3"/>

See [notes](api_changes_list_2021.md#20213).

### How to avoid flaky tests?

Always call `super.tearDown()` inside the `finally {}` block of your test class to avoid leaks and side effects from previously run (failed) tests:

<tabs>
<tab title="Java" group-key="java">

```java
protected void tearDown() throws Exception {
  try {
    // test specific tear-down calls
  } catch (Exception e) {
    addSuppressedException(e);
  } finally {
    super.tearDown();
  }
}
```
</tab>

<tab title="Kotlin" group-key="kotlin">

```kotlin
override fun tearDown() {
  try {
    // test specific tear-down calls
  } catch (e: Throwable) {
    addSuppressedException(e)
  } finally {
    super.tearDown()
  }
}
```
</tab>
</tabs>

Avoid OS-specific assumptions (e.g., filesystem case-sensitivity, hardcoded separator instead of `java.io.File.separator`, default encoding, line endings).

Use _ordered_ collections or [`UsefulTestCase.assertUnorderedCollection()`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/UsefulTestCase.java).

Code-deferring execution (e.g., via `Application.invokeLater()`) might not run during test execution (and possibly fails in production, too).
Use `Application.invokeLater(runnable, myProject.getDisposed())`.

When targeting 2024.2 or later, see also [](#how-to-handle-projectactivity).

### How to avoid test failure when using resources?

In some situations, added or changed files (e.g. XML DTDs provided by a plugin) are not refreshed in [](virtual_file_system.md).
In such cases, delete the <path>test-system/caches</path> folder in your [sandbox directory](ide_development_instance.md#the-development-instance-sandbox-directory) and try again.

### How to enable DEBUG/TRACE logging?

Provide JVM system properties `idea.log.debug.categories` or `idea.log.trace.categories` to specify logger category name, respectively.
Multiple categories can be set using a comma-separated value list.

**Sample** Set DEBUG level for categories `com.my.plugin.ui` and `com.my.plugin.backend`:

<tabs group="gradle">
<tab title="Kotlin" group-key="kotlin">

```kotlin
tasks {
  test {
    systemProperty("idea.log.debug.categories", "com.my.plugin.ui,com.my.plugin.backend")
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
test {
  systemProperty("idea.log.debug.categories", "com.my.plugin.ui,com.my.plugin.backend")
}
```

</tab>
</tabs>

### How to get separate logs for failing tests?
<primary-label ref="2021.3"/>

Set system property `idea.split.test.logs` to `true` to generate separate test log files in the <path>splitTestLogs</path> subdirectory for failing tests (WARN/ERROR level messages).

## Techniques

### How to mark test-only elements in production code?

Annotate with [`@TestOnly`](%gh-java-annotations%/common/src/main/java/org/jetbrains/annotations/TestOnly.java), usages will be highlighted via inspection <control>JVM languages | Test-only usage in production code</control>.

To mark members whose visibility is higher than necessary to be used from tests, use [`@VisibleForTesting`](%gh-java-annotations%/common/src/main/java/org/jetbrains/annotations/VisibleForTesting.java)

### How to run tests for all files in a directory?

Use [`FileBasedTestCaseHelper`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/FileBasedTestCaseHelper.java), please see its Javadoc for instructions.

### How to modify setup on a per-test basis?

Use `UsefulTestCase.getTestName()` or create your own annotation which can be checked via `UsefulTestCase.annotatedWith()`.

### How to run a performance test?

Use [`PlatformTestUtil.startPerformanceTest()`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/PlatformTestUtil.java) to assert machine-adjusted metrics.

### How to dispatch pending UI events?

Use [`PlatformTestUtil.dispatchAllInvocationEventsInIdeEventQueue()`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/PlatformTestUtil.java).

### How to disable stderr logging?

Use [`DefaultLogger.disableStderrDumping()`](%gh-ic%/platform/util/src/com/intellij/openapi/diagnostic/DefaultLogger.java) passing `getTestRootDisposable()`.

### How to register a resource (DTD, XSD) temporarily?

Use [`ExternalResourceManagerExImpl.registerResourceTemporarily()`](%gh-ic%/xml/xml-psi-impl/src/com/intellij/javaee/ExternalResourceManagerExImpl.kt) passing `getTestRootDisposable()`.

### How to replace a component/service in tests?

Provide dedicated test implementation via `testServiceImplementation` in [service declaration](plugin_services.md#declaring-a-service), or use [`ServiceContainerUtil`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/ServiceContainerUtil.kt).

### How to replace extension points in tests?

Use [`ExtensionTestUtil`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/ExtensionTestUtil.kt).

### How to wait for a specified amount of time?

If possible, use the [](#how-to-wait-for-condition-with-timeout) approach. Otherwise, call `com.intellij.util.TimeoutUtil.sleep()`.

### How to wait for condition with timeout?

Use [`WaitFor`](%gh-ic%/platform/util/src/com/intellij/util/WaitFor.java).

### How to test a JVM language?
<primary-label ref="IntelliJIDEA"/>

Plugins supporting a JVM language may require JDK and language standard library to be set up in a test project, so that classes like `java.lang.String` can be correctly resolved during tests.
Tests extending [`LightJavaCodeInsightFixtureTestCase`](%gh-ic%/java/testFramework/src/com/intellij/testFramework/fixtures/LightJavaCodeInsightFixtureTestCase.java) use one of the mock JDKs in the [Java plugin](%gh-ic%/java) sources (notice <path>mockJDK-\$JAVA_VERSION\$</path> directories).
These JAR files are not available in plugin project dependencies, so the IntelliJ Community sources must be checked out to the machine running the tests, and sources' location must be provided to the test framework.
It's done by setting the `idea.home.path` system property to the absolute path of the checked-out sources in the `test` task configuration:


<tabs group="gradle">
<tab title="Kotlin" group-key="kotlin">

```kotlin
test {
  systemProperty("idea.home.path", "/path/to/intellij-community-sources")
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
test {
  systemProperty "idea.home.path", "/path/to/intellij-community-sources"
}
```

</tab>
</tabs>


The default JDK version used by the test framework depends on the target platform version and is the latest supported version.
The easiest way to change the JDK version to a custom one is by overriding `LightJavaCodeInsightFixtureTestCase.getProjectDescriptor()` and using one of the predefined project descriptors in `LightJavaCodeInsightFixtureTestCase`.
If a project descriptor requires more customizations, its `getSdk()` method can use one of the [`IdeaTestUtil.getMockJdk*()`](%gh-ic%/java/testFramework/shared/src/com/intellij/testFramework/IdeaTestUtil.java) methods.

Sometimes, testing a JVM language requires adding standard or other libraries to a test project.
If a required library is available in the Maven repository, use [`MavenDependencyUtil`](%gh-ic%/java/testFramework/shared/src/com/intellij/testFramework/fixtures/MavenDependencyUtil.java), e.g.:

```java
MavenDependencyUtil.addFromMaven(model,
    "org.jetbrains.kotlin:kotlin-stdlib:1.6.10");
```

For [light tests](light_and_heavy_tests.md), use convenience method [`DefaultLightProjectDescriptor.withRepositoryLibrary()`](%gh-ic%/java/testFramework/src/com/intellij/testFramework/fixtures/DefaultLightProjectDescriptor.java)
and [`JavaModuleFixtureBuilder.addMavenLibrary()`](%gh-ic%/java/testFramework/src/com/intellij/testFramework/builders/JavaModuleFixtureBuilder.java) for [heavy tests](light_and_heavy_tests.md).

If a required library is an unpublished JAR file, use [`PsiTestUtil.addLibrary()`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/PsiTestUtil.java) or `addProjectLibrary()` method and the JAR file path, e.g.:

```java
PsiTestUtil.addLibrary(model,
    "internal-library", getTestDataPath(), "internal-library-2.0.jar");
```

### How to handle `ProjectActivity`?
<primary-label ref="2024.2"/>

[`ProjectActivity`](%gh-ic%/platform/core-api/src/com/intellij/openapi/startup/StartupActivity.kt) is no longer awaited when opening a project in tests.
If tests depend on some job done in `ProjectActivity` (e.g., automatic project re-import), implement a dedicated [event/listener](messaging_infrastructure.md) and wait for it explicitly.
As a workaround, use [`StartupActivityTestUtil.waitForProjectActivitiesToComplete()`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/StartupActivityTestUtil.kt).

### How to handle indexing?
<primary-label ref="2024.2"/>

Indexing is now run asynchronously in a background thread.
Use [`IndexingTestUtil.waitUntilIndexesAreReady()/suspendUntilIndexesAreReady()`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/IndexingTestUtil.kt)
to wait for fully populated indexes.

<include from="snippets.topic" element-id="missingContent"/>
