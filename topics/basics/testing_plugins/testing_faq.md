[//]: # (title: Testing FAQ)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This page lists a number of common questions/issues and techniques useful for testing plugins.

## Useful Classes

- [`UsefulTestCase`](upsource:///platform/testFramework/src/com/intellij/testFramework/UsefulTestCase.java)
- [`PlatformTestUtil`](upsource:///platform/testFramework/src/com/intellij/testFramework/PlatformTestUtil.java)
- [`CodeInsightTestUtil`](upsource:///platform/testFramework/src/com/intellij/testFramework/fixtures/CodeInsightTestUtil.java)
- [`EditorTestUtil`](upsource:///platform/testFramework/src/com/intellij/testFramework/EditorTestUtil.java)
- [`PsiTestUtil`](upsource:///platform/testFramework/src/com/intellij/testFramework/PsiTestUtil.java)
- [`VfsTestUtil`](upsource:///platform/testFramework/src/com/intellij/testFramework/VfsTestUtil.java)
- [`ProjectViewTestUtil`](upsource:///platform/testFramework/src/com/intellij/testFramework/ProjectViewTestUtil.java)
- [`TestLookupElementPresentation`](upsource:///platform/testFramework/src/com/intellij/testFramework/fixtures/TestLookupElementPresentation.java)

## Issues

### How to avoid blinking tests?

Always call `super.tearDown()` inside `finally {..}` block of your test class to avoid leaks and side-effects from previously run (failed) tests.

Avoid OS-specific assumptions (e.g., filesystem case-sensitivity, hardcoded separator instead of `java.io.File.separator`).

Use _ordered_ collections or [`UsefulTestCase.assertUnorderedCollection()`](upsource:///platform/testFramework/src/com/intellij/testFramework/UsefulTestCase.java).

Code deferring execution (e.g., via `Application.invokeLater()`) might not run during test execution (and possibly fails in production, too). Use `invokeLater(runnable, myProject.getDisposed()`.

### How to avoid test failure when using resources?

In some situations, added or changed files (e.g. DTDs provided by plugin) are not refreshed in VFS. In such cases, simply delete <path>test-system/caches</path> in your [sandbox directory](ide_development_instance.md#the-development-instance-sandbox-directory) and try again.

### How to enable DEBUG/TRACE logging?

Set system properties `idea.log.debug.categories` or `idea.log.trace.categories`, respectively.

### How to get separate logs for failing tests?

Set system property `idea.split.test.logs` to `true` to generate separate test log files in `splitTestLogs` subdirectory for failing tests (WARN/ERROR level messages) (2021.3).

## Techniques

### How to mark test-only elements in production code?

Annotate with [`org.jetbrains.annotations.TestOnly`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/TestOnly.java), usages will be highlighted via inspection <control>JVM languages | Test-only usage in production code</control>.

### How to run tests for all files in a directory?

Use [`FileBasedTestCaseHelper`](upsource:///platform/testFramework/src/com/intellij/testFramework/FileBasedTestCaseHelper.java), please see its javadoc for instructions.

### How to modify setup on per-test basis?

Use `UsefulTestCase.getTestName()` or create your own annotation(s) which can be checked via `UsefulTestCase.annotatedWith()`.

### How to run performance test?

Use [`PlatformTestUtil.startPerformanceTest()`](upsource:///platform/testFramework/src/com/intellij/testFramework/PlatformTestUtil.java) to assert machine-adjusted metrics.

### How to dispatch pending UI events?

Use [`PlatformTestUtil.dispatchAllInvocationEventsInIdeEventQueue()`](upsource:///platform/testFramework/src/com/intellij/testFramework/PlatformTestUtil.java).

### How to disable stderr logging?

Use [`DefaultLogger.disableStderrDumping()`](upsource:///platform/util/src/com/intellij/openapi/diagnostic/DefaultLogger.java) passing `getTestRootDisposable()`.

### How to register a resource (DTD, XSD) temporarily?

Use [`ExternalResourceManagerExImpl.registerResourceTemporarily()`](upsource:///xml/xml-psi-impl/src/com/intellij/javaee/ExternalResourceManagerExImpl.java) passing `getTestRootDisposable()`.

### How to replace component/service in tests?

Provide `testServiceImplementation` for service declaration in <path>plugin.xml</path>, or use [`ServiceContainerUtil`](upsource:///platform/testFramework/src/com/intellij/testFramework/ServiceContainerUtil.kt).

### How to replace extension points in tests?

Use [`ExtensionTestUtil`](upsource:///platform/testFramework/src/com/intellij/testFramework/ExtensionTestUtil.kt).

### How to wait for specified amount of time?

Use `com.intellij.util.TimeoutUtil.sleep()`.

### JVM: How to add Maven dependencies?

Use [`MavenDependencyUtil`](upsource:///java/testFramework/src/com/intellij/testFramework/fixtures/MavenDependencyUtil.java).
