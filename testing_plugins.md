---
layout: editable
title: Testing Plugins
---


Before discussing the specific details of the IntelliJ IDEA plugin test framework, it's worth looking at the general approach that the IntelliJ IDEA team uses for testing the IDE code.
Our intention here is not so much to be prescriptive, and more to set the expectations we have good tools to support the approach that we use, and less good (or no) tools for approaches that we don't use.

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


*  [Tests and Fixtures](tests_and_fixtures.html)
*  [Light and Heavy Tests](light_and_heavy_tests.html)
*  [Test Project and Testdata Directories](test_project_and_testdata_directories.html)
*  [Writing Tests](writing_tests.html)


## Testing Highlighting

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



## Tutorial

Check out
[this](writing_tests_for_plugins.html)
step-by-step tutorial teaching how to write and run automated tests for your custom language plugin (source code included).

