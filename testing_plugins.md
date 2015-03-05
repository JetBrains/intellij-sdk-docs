---
title: Testing IntelliJ IDEA Plugins
---

<!--
INITIAL_SOURCE https://confluence.jetbrains.com/display/IDEADEV/Testing+IntelliJ+IDEA+Plugins
-->

# {{ page.title }}

### General Testing Approach

Before discussing the specific details of the IntelliJ IDEA plugin test framework, it's worth looking at the general approach that the IntelliJ IDEA team uses for testing the IDE code. Our intention here is not so much to be prescriptive, and more to set the expectations we have good tools to support the approach that we use, and less good (or no) tools for approaches that we don't use.

Most of the tests in the IntelliJ IDEA codebase are *model-level functional tests*. What this means is the following:

*  The tests run in a headless environment which uses real production implementations for the majority of components, except for a number of UI components.

*  The tests usually test a feature as a whole, rather than individual functions that comprise its implementation.

*  The tests do not test the Swing UI and work directly with the underlying model instead.

*  Most of the tests take a source file or a set of source files as input data, execute a feature, and then compare the output with expected results (which can be specified as another set of source files, as special markup in the input file, or directly in the test code).

The biggest benefit of this test approach is that tests are very stable and require very little maintenance once they have been written, no matter how much the underlying implementation is refactored or rewritten. In a product with 10+ years of lifetime that has gone through a large number of internal refactorings, we find that this benefit greatly outweighs the downsides of slower test execution and more difficult debugging of failures (compared to more isolated unit tests).

Another consequence of our testing approach is what our test framework does not provide:

*  We do not provide a recommended approach to mocking. We have a few tests in our codebase that use JMock, but in general we find it difficult to mock all of the interactions with IntelliJ IDEA components that your plugin class will need to have, and we recommend working with real components instead.

*  We do not provide a general-purpose framework for Swing UI testing. You can try using tools such as
[FEST](http://fest.easytesting.org/) or
[Sikuli](http://www.sikuli.org/)
for plugin UI testing, but we don't use either of them and cannot provide any guidelines for their use.
Internally, we use manual testing for testing our Swing UIs.

### Tests and Fixtures

The IntelliJ Platform testing infrastructure is not tied to any specific test framework.
In fact, the IntelliJ IDEA team uses both JUnit, TestNG and Cucumber for testing different parts of the project.
However, most of the tests are written using JUnit 3.

When writing your own tests, you have the choice between using a standard base class to perform the test set up for you and using a fixture class, which lets you perform the setup manually and does not tie you to a specific test framework.
With the former approach, you can use classes such as ```LightPlatformCodeInsightFixtureTestCase``` (despite being one of the longest and ugliest class names you'll run into in the IntelliJ IDEA API, it's actually the recommended approach for writing tests).
With the latter approach, you use the ```IdeaTestFixtureFactory``` class to create instances of fixtures for the test environment, and you need to call the fixture creation and setup methods from the test setup method used by your test framework.

### Light and Heavy Tests

As mentioned above, plugin tests run in a real, rather than mocked, IntelliJ IDEA environment, and use real implementations for most of the application and project components. Loading the project components for a project is a fairly expensive operation, and we want to avoid doing it for each test. Because of that, we distinguish between *heavy*  tests, which create a new project for each test, and *light*  tests, which reuse a project from the previous test run when possible. Light and heavy tests use different base classes or fixture classes, as described below.

(i) Because of the performance difference, we recommend plugin developers to write light tests whenever possible.

The standard way of writing a light test is to extend the ```LightCodeInsightFixtureTestCase``` class (for tests that require the Java PSI or any related functionality) or ```LightPlatformCodeInsightFixtureTestCase``` (for tests that don't have any Java dependencies).

When writing a light test, you can specify the requirements for the project that you need to have in your test, such as the module type, the configured SDK, facets, libraries etc. You do so by extending the ```LightProjectDescriptor``` class and returning your project descriptor from ```LightCodeInsightFixtureTestCase.getProjectDescriptor()```. Before executing each test, the project will be reused if the test case returns the same project descriptor as the previous one, or recreated if the descriptor is different.

If you need to set up a multi-module project for your tests, you must write a heavy test. The setup code for a multi-module Java project looks something like that:

```java
final TestFixtureBuilder<IdeaProjectTestFixture> projectBuilder = IdeaTestFixtureFactory.getFixtureFactory().createFixtureBuilder(getName());

// repeat the following line for each module
final JavaModuleFixtureBuilder moduleFixtureBuilder = projectBuilder.addModule(JavaModuleFixtureBuilder.class);

myFixture = JavaTestFixtureFactory.getFixtureFactory().createCodeInsightFixture(projectBuilder.getFixture());
```

### Test Project and Testdata Directories

The test fixture creates a *test project*  environment. Unless you customize the project creation, the test project will have one module with one source root called "```src```". The files for the test project physically exist either in a temporary directory or in an in-memory file system, depending on which implementation of ```TempDirTestFixture``` is used. ```LightPlatformCodeInsightFixtureTestCase``` uses an in-memory implementation; if you set up the test environment by calling ```IdeaTestFixtureFactory.createCodeInsightFixture```, you can specify the implementation to use.

Note that if your tests use the in-memory implementation, and you abort the execution of your tests, the persisted filesystem caches may get out of sync with the in-memory structures, and you may get spurious errors in your tests. In that case, you need to try running the test again, and if that doesn't help, delete the "system" subdirectory under the sandbox directory specified in the IntelliJ IDEA SDK settings.

In your plugin, you normally store the test data for your tests (such as files on which plugin features will be executed and expected output files) in the *testdata*  directory. This is just a directory under the content root of your plugin, but not under a source root (files in testdata are normally not valid source code and must not be compiled). To specify the location of testdata, you must override the ```LightPlatformCodeInsightFixtureTestCase.getTestDataPath()``` method (the default implementation assumes running as part of the IntelliJ IDEA source tree and is not appropriate for third-party plugins).

Note that a very common pattern in IntelliJ IDEA tests is to use the name of the test method being executed as the base for building the testdata file paths. This allows to reuse most of the code between different test methods that test different aspects of the same feature, and this approach is also recommended for third-party plugin tests. The name of the test method can be retrieved using ```UsefulTestCase.getTestName()```.

To copy files or directories from your testdata directory to the test project directory, you can use the ```copyFileToProject()``` and ```copyDirectoryToProject()``` methods in the ```CodeInsightTestFixture``` class.

Most operations in plugin tests require a file open in the in-memory editor, in which highlighting, completion and other operations will be performed. The in-memory editor instance is returned by ```CodeInsightTestFixture.getEditor()```. To copy a file from the testdata directory to the test project directory and immediately open it in the editor, you can use the ```CodeInsightTestFixture.configureByFile()``` or ```configureByFiles()``` methods. The latter copies multiple files to the test project directory and opens the *first*  of them in the in-memory editor.

Alternatively, you can use one of the other methods which take parameters annotated with ```@TestDataFile```. These methods copy the specified files from the testdata directory to the test project directory, open the first of the specified files in the in-memory editor, and then perform the requested operation such as highlighting or code completion.

When a file is opened in the in-memory editor, special markup in the file content can be used to specify the caret position or selection. You can use one of the following markers:

*  ```<caret>``` specifies the position where the caret should be placed;

*  ```<selection>``` and ```</selection>``` specify the start and end of the text range to be selected;

*  ```<block>``` and ```</block>``` specify the start and end points of the column selection.

### Writing Tests

In most cases, once you have the necessary files copied to the test project and loaded into the in-memory editor, writing the test itself involves invoking your plugin code and has few dependencies on the test framework. However, for many common cases the framework provides helper methods that can make testing easier:

*  ```type()``` simulates the typing of a character or string into the in-memory editor;

*  ```performEditorAction()``` simulates the execution of an action in the context of the in-memory editor;

*  ```complete()``` simulates the invocation of code completion and returns the list of lookup elements displayed in the completion list (or ```null``` if the completion had no suggestions or one suggestion which was auto-inserted);

*  ```findUsages()``` simulates the invocation of 'Find Usages' and returns the found usages;

*  ```findSingleIntention()``` in combination with ```launchAction()``` simulate the invocation of an intention action or inspection quickfix with the specified name;

*  ```renameElementAtCaret()``` or ```rename()``` simulate the execution of a rename refactoring.

To compare the results of executing the action with the expected results, you can use the ```checkResultByFile()``` method. The file with the expected results can also contain markup to specify the expected caret position or selected text range. If you're testing an action which modifies multiple files (a project-wide refactoring, for example), you can compare an entire directory under the test project with the expected output using ```PlatformTestUtil.assertDirectoriesEqual()```.

### Testing Highlighting

A common task when writing plugin tests is testing various kinds of highlighting (inspections, annotators, parser error highlighting etc.) The IntelliJ Platform provides a dedicated utility and markup format for this task.

To test the highlighting for the file currently loaded into the in-memory editor, you invoke the ```checkHighlighting()``` method. The parameters to the method specify which severities should be taken into account when comparing the results with the expected results: errors are always taken into account, whereas warnings, weak warnings and infos are optional. Alternatively, you can use the ```testHighlighting()``` method, which loads a testdata file into the in-memory editor and highlights it as a single operation.

If you need to test inspections (rather than generic highlighting provided by a highlighting lexer or annotator), you need to enable inspections that you're testing. This is done by calling ```CodeInsightTestFixture.enableInspections()``` in the setup method of your test or directly in a test method, before the call to checkHighlighting().

The expected results of the highlighting are specified directly in the source file. The platform supports an extensive XML-like markup language for this. In its simplest form, the markup looks like this:

```xml
<warning descr="expected error message">code to be highlighted</warning>
```

Or, as a more specific example:

```xml
public int <warning descr="The compareTo() method does not reference 'foo' which is referenced from equals(); inconsistency may result">compareTo</warning>(Simple other) {
    return 0;
}
```

The tag name specifies the severity of the expected highlighting. The following severities are supported:

*  ```<error>```

*  ```<warning>```

*  ```<weak_warning>```

*  ```<info>```

*  ```<inject>``` (for an injected fragment)

*  ```<symbolName>``` (for a marker that highlights an identifier according to its type)

*  any custom severity can be referenced by its name


The tag can also have the following optional attributes:

*  ```descr``` expected message associated with the highlighter (if not specified, any text will match; if the message contains a quotation mark, it can be escaped by putting two backslash characters before it)

*  ```foregroundColor```, ```backgroundColor```, ```effectColor``` *  expected colors for the highlighting

*  ```effectType``` expected effect type for the highlighting (see ```EffectType``` enum for possible values)

*  ```fontType``` expected font style for the highlighting (0 *  normal, 1 *  bold, 2 *  italic, 3 *  bold italic)



### Tutorial

Check out
[this](http://confluence.jetbrains.com/display/IntelliJIDEA/Writing+Tests+for+Plugins)
step-by-step tutorial teaching how to write and run automated tests for your custom language plugin (source code included).

