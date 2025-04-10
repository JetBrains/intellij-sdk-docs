<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Writing Tests

<link-summary>Introduction to implementing tests.</link-summary>

<include from="testing_plugins.md" element-id="testSamples"/>

<include from="tests_and_fixtures.md" element-id="testFrameworkDependencies"/>

In most cases, once you have the necessary files copied to the test project and loaded into the in-memory editor, writing the test itself involves invoking your plugin code and has few dependencies on the test framework.

However, for many common cases, the framework provides helper methods that can make testing easier:
* `type()` simulates the typing of a character or string into the in-memory editor.
* `performEditorAction()` simulates the execution of an action in the in-memory editor context.
* `complete()` simulates code completion invocation and returns the list of lookup elements displayed in the completion list (or `null` if the completion had no suggestions or one suggestion which was auto-inserted).
* `findUsages()` simulates the invocation of _Find Usages_ and returns the found usages.
* `findSingleIntention()` in combination with `launchAction()` simulate the invocation of an intention action or inspection quick fix with the specified name.
* `renameElementAtCaret()` or `rename()` simulate the execution of a rename refactoring.

To compare the results of executing the action with the expected results, you can use the `checkResultByFile()` method.
The file with the expected results can also contain [markup](test_project_and_testdata_directories.md#special-markup) to specify the expected caret position or selected text range.
Suppose you're testing an action that modifies multiple files (a project-wide refactoring, for example). In that case, you can compare an entire directory under the test project with the expected output using `PlatformTestUtil.assertDirectoriesEqual()`.

See [Useful Classes](testing_faq.md#useful-classes) for other common testing functionality.
