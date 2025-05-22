<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Code Inspections

<link-summary>Tutorial: implementing Inspections to highlight code in the editor.</link-summary>

<tldr>

**Product Help:** [Code Inspection](https://www.jetbrains.com/idea/webhelp/code-inspection.html), [Creating Custom Inspections](https://www.jetbrains.com/idea/help/creating-custom-inspections.html)

**UI Guidelines:** [](inspections.md)

</tldr>

The IntelliJ Platform provides tools designed for static code analysis called _code inspections_, which help the user maintain and clean up code without actually executing it.
Custom code inspections can be implemented as IntelliJ Platform plugins.
An example of the plugin approach is the [comparing_string_references_inspection](%gh-sdk-samples-master%/comparing_string_references_inspection) code sample.

See the [Inspections](inspections.md) topic in UI Guidelines on naming, writing description, and message texts for inspections.

## Creating an Inspection Plugin

The [comparing_string_references_inspection](%gh-sdk-samples-master%/comparing_string_references_inspection) code sample adds a new inspection to the <control>Java | Probable Bugs</control> group in the [Inspections list](https://www.jetbrains.com/help/idea/inspections-settings.html).
The inspection reports when the `==` or `!=` operator is used between String expressions.

It illustrates the components for a custom inspection plugin:

* Describing an [inspection](#plugin-configuration-file) in the plugin configuration file.
* Implementing a [local inspection class](#inspection-implementation-java-class) to inspect Java code in the editor.
* Creating a [visitor](#visitor-implementation-class) to traverse the PSI tree of the Java file being edited, inspecting for problematic syntax.
* Implementing a [quick fix](#quick-fix-implementation) class to correct syntax problems by altering the PSI tree as needed.
  Quick fixes are displayed to the user like [intentions](code_intentions.md).
* Writing an HTML [description](#inspection-description) of the inspection for display in the inspection preferences panel.
* Creating a [test](#inspection-test) for the implemented inspection and quick fix.

Although the code sample illustrates implementations of these components, it is often useful to see examples of inspections implemented in the [IntelliJ Community](%gh-ic%/README.md) code base.
To identify a given inspection's implementation classes, try to find an inspection [by name](explore_api.md#search-for-symbol-names) or [by UI texts](explore_api.md#search-by-ui-text).
Consider also searching for existing implementations in [IntelliJ Platform Explorer](https://jb.gg/ipe?extensions=com.intellij.localInspection).

## Creating an Inspection

The [comparing_string_references_inspection](%gh-sdk-samples-master%/comparing_string_references_inspection) code sample reports when the `==` or `!=` operators are used between String expressions.
The user can apply a quick fix to change `a==b` to `a.equals(b)`, or `a!=b` to `!a.equals(b)`.

The details of the `comparing_string_references_inspection` implementation illustrate the components of an inspection plugin.

### Plugin Configuration File

The `comparing_string_references_inspection` is described as a <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.localInspection"/></include> in the `comparing_string_references_inspection` plugin configuration ([`plugin.xml`](%gh-sdk-samples-master%/comparing_string_references_inspection/src/main/resources/META-INF/plugin.xml)) file.

There exist two types of inspection extensions:

* The <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.localInspection"/></include> is used for inspections that operate on one file at a time, and also operate "on-the-fly" as the user edits the file.
* The <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.globalInspection"/></include> is used for inspections that operate across multiple files, and the associated fix might, for example, refactor code between files.

The minimum inspection setup must declare the `implementationClass` and `language` attribute (unless the inspection works on any supported language).
As shown in the `comparing_string_references_inspection` plugin configuration file, other attributes can be defined in the `localInspection` element, either with or without localization.
In most cases, it is simplest to define the attributes in the plugin configuration file because the underlying parent classes handle most of the class responsibilities based on the configuration file description.

If required, inspections can define all the attribute information (except `implementationClass`) by overriding methods in the inspection implementation class (not recommended in general).

### Inspection Implementation Java Class

Inspection implementations for Java files, like [`ComparingStringReferencesInspection`](%gh-sdk-samples-master%/comparing_string_references_inspection/src/main/java/org/intellij/sdk/codeInspection/ComparingStringReferencesInspection.java), are often based on the Java class [`AbstractBaseJavaLocalInspectionTool`](%gh-ic%/java/java-analysis-api/src/com/intellij/codeInspection/AbstractBaseJavaLocalInspectionTool.java).
The [`AbstractBaseJavaLocalInspectionTool`](%gh-ic%/java/java-analysis-api/src/com/intellij/codeInspection/AbstractBaseJavaLocalInspectionTool.java) base class offers methods to inspect Java classes, fields, and methods.

More generally, `localInspection` types are based on the class [`LocalInspectionTool`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java).
Examining the class hierarchy for `LocalInspectionTool` shows that the IntelliJ Platform provides many child inspection classes for a variety of languages and frameworks.
One of these classes is a good basis for a new inspection implementation, but a bespoke implementation can also be based directly on `LocalInspectionTool`.

The primary responsibilities of the inspection implementation class are to provide:

* A `PsiElementVisitor` object to traverse the PSI tree of the file being inspected.
* A `LocalQuickFix` class to fix an identified problem (optional).
* An options panel to be displayed in the <control>Inspections</control> settings dialog (optional). See [](inspection_options.md) for more details.

The overridden `ComparingStringReferencesInspection` methods are discussed in the sections below.

### Visitor Implementation Class

The visitor class evaluates whether elements of the file's PSI tree are of interest to an inspection.

The `ComparingStringReferencesInspection.buildVisitor()` method creates an anonymous visitor class based on [`JavaElementVisitor`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/JavaElementVisitor.java) to traverse the PSI tree of the Java file being edited, inspecting for suspect syntax.
The anonymous class overrides `visitBinaryExpression()`, which checks if a `PsiBinaryExpression`'s operator is `==` or `!=`, and if both operand types are `String`.

### Quick Fix Implementation

The quick fix class acts much like an intention, allowing the user to invoke it on the `PsiElement` (or `TextRange`) highlighted by the inspection.

The `ComparingStringReferencesInspection` implementation uses the nested class `ReplaceWithEqualsQuickFix` to implement a quick fix based on [`LocalQuickFix`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/LocalQuickFix.java).
The `ReplaceWithEqualsQuickFix` class allows the user to change the use of `a == b` and `a != b` expression to `a.equals(b)` and `!a.equals(b)` respectively.

The heavy lifting is done in `ReplaceWithEqualsQuickFix.applyFix()`, which manipulates the PSI tree to convert the expressions.
The change to the PSI tree is accomplished by the usual approach to modification:

* Getting a `PsiElementFactory`.
* Creating a new `PsiMethodCallExpression`.
* Substituting the original left and right operands into the new `PsiMethodCallExpression`.
* Replacing the original binary expression with the `PsiMethodCallExpression`.

> In case of providing multiple quick fixes for a single element, their ordering is indeterministic due to performance reasons.
> It is possible to push specific items up or down by implementing
> [`HighPriorityAction`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/HighPriorityAction.java)
> or
> [`LowPriorityAction`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/LowPriorityAction.java)
> respectively.
>
{style="note"}

### Inspection Description

The inspection description is an HTML file.
The description is displayed in the upper right panel of the <control>Inspections</control> settings dialog when an inspection is selected from the list.

See the [Inspections](inspections.md) topic in UI Guidelines on important guidelines for writing the description.

Implicit in using [`LocalInspectionTool`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java) in the class hierarchy of the inspection implementation means following some conventions.

* The inspection description file is expected to be located under <path>\$RESOURCES_ROOT_DIRECTORY\$/inspectionDescriptions/</path>.
  If the inspection description file is to be located elsewhere, override `getDescriptionUrl()` in the inspection implementation class.
* The name of the description file is expected to be the inspection <path>\$SHORT_NAME\$.html</path> as provided by the inspection description, or the inspection implementation class.
  If a short name is not provided, the IntelliJ Platform computes one by removing `Inspection` suffix from the implementation class name.

> If a plugin project is multi-module, and it combines resources into a single JAR, make sure that all inspection description files have unique names or paths.
> Otherwise, only the last packed description file will exist in the distribution package.
>
{style="warning"}

> See the [](providing_translations.md#bundled-translations) section for information about how to provide inspection description translations in plugins.

#### Code Snippets
<primary-label ref="2023.2"/>

Using the following HTML structure, the description can embed code snippets that will be displayed with syntax highlighting:

```html
<p>
  The following code will be shown with syntax highlighting:
</p>
<pre>
  <code>
    // code snippet
  </code>
</pre>
```

The language will be set according to the [inspection's registration](#plugin-configuration-file) `language` attribute.
If required (e.g., when targeting [UAST](uast.md)), it can be specified explicitly via `<code lang="LanguageID">...</code>`.

#### Settings Link

To open related [settings](settings.md) directly from the inspection description, add a link with `settings://$CONFIGURABLE_ID$`, optionally followed by `?$SEARCH_STRING$` to pre-select UI element:

```html
See <em>Includes</em> tab in <a href="settings://fileTemplates">Settings | Editor | File and Code Templates</a> to configure.
```

### Inspection Test

> Please note that running the test requires setting system property `idea.home.path` in the `test` task configuration of the Gradle build script.
>
{style="note"}

The `comparing_string_references_inspection` code sample provides a test for the inspection.
See the [](testing_plugins.md) section for general information about plugin testing.

The `comparing_string_references_inspection` test is based on the [`UsefulTestCase`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/UsefulTestCase.java) class, part of the JUnit framework APIs.
This class handles much of the underlying boilerplate for tests.

By convention, the folder <path>test/testData/</path> contains the test files.
The folder contains pairs of files for each test using the name convention <path>∗.java</path> and <path>∗.after.java</path>, e.g., <path>Eq.java</path> / <path>Eq.after.java</path>.

The `comparing_string_references_inspection` tests run the inspection on the <path>∗.java</path> files, apply the quick fix, and compare the results with the respective <path>∗.after.java</path> files containing expected results.

## Running the Comparing String References Inspection Code Sample

The [comparing_string_references_inspection](%gh-sdk-samples-master%/comparing_string_references_inspection) code sample adds a new inspection to the <control>Java | Probable Bugs</control> group in the [Inspections](https://www.jetbrains.com/help/idea/inspections-settings.html) configuration.

See [](code_samples.md) on how to set up and run the plugin.

### How does it work?

The plugin inspects your code opened in the IntelliJ IDEA editor.
The plugin highlights the code fragments where two `String` expressions are compared by `==` or `!=` and proposes to replace this code fragment with `.equals()`:

![Comparing String References inspection highlighting and quick fix](comparing_references.png)
{width=680}

In this example, the `str1` and `str2` are variables of the String type.
Invoking <control>SDK: Use equals()</control> will result in transforming expression to the form visible in the [preview](code_intentions_preview.md) popup (code fragment on the right).
