<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Light and Heavy Tests

<link-summary>Introduction to light tests reusing a single project for multiple tests, and heavy tests creating a new project for each test.</link-summary>

<include from="testing_plugins.md" element-id="testSamples"/>

<include from="tests_and_fixtures.md" element-id="testFrameworkDependencies"/>

Plugin tests run in a real, rather than mocked, IntelliJ Platform environment and use real implementations for most application and project [services](plugin_services.md).

Loading and initializing all the project components and services for a project to run tests is a relatively expensive operation, and it is desired to avoid doing it for each test.
Dependently on the loading and execution time, we make a difference between *light* tests and *heavy* tests available in the IntelliJ Platform test framework:

* *Light* tests reuse a project from the previous test run when possible.
* *Heavy* tests create a new project for each test.

Light and heavy tests use different base classes or fixture classes, as described below.

> Because of the performance difference, we recommend plugin developers to write *light* tests whenever possible.
>
{style="note"}

## Light Tests

The standard way of writing a light test is to extend one of the following classes:

<tabs>
<tab title="Default">

Use [`LightPlatformTestCase`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/LightPlatformTestCase.java)
or [`BasePlatformTestCase`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/fixtures/BasePlatformTestCase.java)
for tests that don't have any dependency on Java functionality.

**Examples:**
- [`JavaCopyrightTest`](%gh-ic%/java/java-tests/testSrc/com/intellij/copyright/JavaCopyrightTest.kt)
- [`HtmlDocumentationTest`](%gh-ic%/xml/tests/src/com/intellij/html/HtmlDocumentationTest.java)
- [`AcceptWordAsCorrectTest`](%gh-ic%/spellchecker/testSrc/com/intellij/spellchecker/inspector/AcceptWordAsCorrectTest.java)

</tab>

<tab title="Plugins using Java PSI">

> An explicit dependency on `TestFramework.Plugin.Java` is required, see [test-framework dependencies](tools_intellij_platform_gradle_plugin_dependencies_extension.md#testing).
>
{title="Java Test Framework (2024.2+)"}

For tests that require the [Java PSI](idea.md#java-plugin) or related functionality:
- [`LightJavaCodeInsightFixtureTestCase`](%gh-ic%/java/testFramework/src/com/intellij/testFramework/fixtures/LightJavaCodeInsightFixtureTestCase.java) for JUnit 3
- [`LightJavaCodeInsightFixtureTestCase4`](%gh-ic%/java/testFramework/src/com/intellij/testFramework/fixtures/LightJavaCodeInsightFixtureTestCase4.kt) for JUnit 4 (2021.1 and later)
- [`LightJavaCodeInsightFixtureTestCase5`](%gh-ic%/java/testFramework/src/com/intellij/testFramework/fixtures/LightJavaCodeInsightFixtureTestCase5.kt) for JUnit 5 (2021.1 and later)

**Examples:**
- [`PatternValidatorTest`](%gh-ic%/plugins/IntelliLang/IntelliLang-tests/test/org/intellij/plugins/intelliLang/pattern/PatternValidatorTest.java) (JUnit 3)
- [`JavaCtrlMouseTest`](%gh-ic%/java/java-tests/testSrc/com/intellij/java/codeInsight/javadoc/JavaCtrlMouseTest.kt) (JUnit 4)
- [`MissingJavadocHighlightingTest`](%gh-ic%/java/java-tests/testSrc/com/intellij/java/codeInsight/daemon/MissingJavadocHighlightingTest.java) (JUnit 5)

> See [](testing_faq.md#how-to-test-a-jvm-language) on how to set up your test environment to obtain the required _Mock JDK_ automatically.

</tab>
</tabs>

### LightProjectDescriptor

When writing a light test, it is possible to specify the requirements of the project used in test, such as the module type, the configured [SDK](sdk.md), [facets](facet.md), [libraries](library.md), etc.
It is done by extending the [`LightProjectDescriptor`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/LightProjectDescriptor.java) class and returning the project descriptor (usually stored in `static final` field) from `getProjectDescriptor()`.

Before executing each test, the project instance will be reused if the test case returns the same project descriptor as the previous one or recreated if the descriptor is different (`equals() = false`).

When [testing JVM languages](testing_faq.md#how-to-test-a-jvm-language), see also [`DefaultLightProjectDescriptor`](%gh-ic%/java/testFramework/src/com/intellij/testFramework/fixtures/DefaultLightProjectDescriptor.java).

## Heavy Tests

The standard way of writing a heavy test is to extend [`HeavyPlatformTestCase`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/HeavyPlatformTestCase.java).

**Examples:**
- [`ModuleDeleteProviderTest`](%gh-ic%/java/java-tests/testSrc/com/intellij/openapi/roots/ui/configuration/actions/ModuleDeleteProviderTest.java)
- [`FacetTypeUnloadingTest`](%gh-ic%/java/idea-ui/testSrc/com/intellij/facet/FacetTypeUnloadingTest.kt)
- [`SourceFolderManagerTest`](%gh-ic%/platform/external-system-impl/testSrc/com/intellij/openapi/externalSystem/service/project/manage/SourceFolderManagerTest.kt)

### Setting Up a Multi-Module Project

If a test requires a multi-module project, using a heavy test is **required**.
The following code snippet presents a multi-module Java project setup:

```java
TestFixtureBuilder<IdeaProjectTestFixture> projectBuilder =
    IdeaTestFixtureFactory.getFixtureFactory().createFixtureBuilder(getName());

// fixture must be created before adding modules:
myFixture = JavaTestFixtureFactory.getFixtureFactory()
    .createCodeInsightFixture(projectBuilder.getFixture());

// add and configure modules:
JavaModuleFixtureBuilder<?> builder1 =
    projectBuilder.addModule(JavaModuleFixtureBuilder.class);
// optionally, configure the module, e.g.:
// builder1.setLanguageLevel(...);
// builder1.addJdk(...);

JavaModuleFixtureBuilder<?> builder2 =
    projectBuilder.addModule(JavaModuleFixtureBuilder.class);
// configure another module...
```
