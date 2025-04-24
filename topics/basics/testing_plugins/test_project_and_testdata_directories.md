<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Test Project and Testdata Directories

<link-summary>Adding test data to test projects used during tests execution.</link-summary>

<include from="testing_plugins.md" element-id="testSamples"/>

<include from="tests_and_fixtures.md" element-id="testFrameworkDependencies"/>

The test fixture creates a *test project* environment.
Unless you customize the project creation, the test project will have one module with one source root called <path>src</path>.
The test project files exist either in a temporary directory or in an in-memory file system, depending on which implementation of [`TempDirTestFixture`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/fixtures/TempDirTestFixture.java) is used.

[`BasePlatformTestCase`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/fixtures/BasePlatformTestCase.java) uses an in-memory implementation; if you set up the test environment by calling `IdeaTestFixtureFactory.createCodeInsightFixture()`, you can specify the implementation to use.

> If your tests use the in-memory implementation, and you abort the execution of your tests, the persisted filesystem caches may get out of sync with the in-memory structures, and you may get spurious errors in your tests.
> If you get an unexpected error after a series of successful runs, **try rerunning the test**, and if that doesn't help, **delete the "system" subdirectory** in your [sandbox directory](ide_development_instance.md#the-development-instance-sandbox-directory).
>
{style="warning"}

## Testdata Files

In your plugin, you usually store the test data for your tests (such as files on which plugin features will be executed and expected output files) in the <path>testdata</path> directory.
This is just a directory under your plugin's content root, but not under a source root.
Files in <path>testdata</path> usually are not valid source code and must not be compiled.

To specify the location of <path>testdata</path>, you must override the `getTestDataPath()` method.
The default implementation assumes running as part of the IntelliJ Platform source tree and is not appropriate for third-party plugins.

> A widespread pattern in IntelliJ Platform tests is to use the test method's name being executed as the base for building the <path>testdata</path> file paths.
> This allows us to reuse most of the code between different test methods that test various aspects of the same feature, and this approach is also recommended for third-party plugin tests.
> The name of the test method can be retrieved using `UsefulTestCase.getTestName()`.
>
{style="note"}

> If your plugin builds on top of Java support, please see [](testing_faq.md#how-to-test-a-jvm-language) to set up your test environment to obtain the required _Mock JDK_ automatically.
>
{style="note"}

To copy files or directories from your <path>testdata</path> directory to the test project directory, you can use the `copyFileToProject()` and `copyDirectoryToProject()` methods from [`CodeInsightTestFixture`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/fixtures/CodeInsightTestFixture.java).

Most operations in plugin tests require a file open in the in-memory editor, in which highlighting, completion, and other operations will be performed.
The in-memory editor instance is returned by `CodeInsightTestFixture.getEditor()`.
To copy a file from the <path>testdata</path> directory to the test project directory and immediately open it in the editor, you can use the `CodeInsightTestFixture.configureByFile()` or `configureByFiles()` methods.
The latter copies multiple files to the test project directory and opens the *first* of them in the in-memory editor.

Alternatively, you can use one of the other methods, which take parameters annotated with [`@TestDataFile`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/TestDataFile.java).
These methods copy the specified files from the <path>testdata</path> directory to the test project directory, open the first of the specified files in the in-memory editor, and then perform the requested operation such as highlighting or code completion.

> The IDE supports smart navigation between test code and related test data file(s); see this [blog post](https://blog.jetbrains.com/platform/2017/10/improvements-in-testing-intellij-platform-plugins/) for more details.
>

### Special Markup

When a file is opened in the in-memory editor, special markup in the file content can specify the caret position or selection.

You can use one of the following markers:
* `<caret>` specifies the position where the caret should be placed.
* `<selection>` and `</selection>` specify the start and end of the selected text range.
* `<block>` and `</block>` specify the column selection's start and end points.
