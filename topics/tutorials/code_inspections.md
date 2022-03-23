[//]: # (title: Code Inspections)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The IntelliJ Platform provides tools designed for static code analysis called _code inspections_, which help the user maintain and clean up code without actually executing it.
Custom code inspections can be implemented as IntelliJ Platform plugins.
Examples of the plugin approach are the IntelliJ Platform SDK code samples [inspection_basics](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/inspection_basics) and [comparing_references_inspection](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/comparing_references_inspection).
In addition, the comparing_references_inspection code sample demonstrates implementing a unit test.

You can also create custom inspections through the IntelliJ IDEA user interface.
See [Code Inspection](https://www.jetbrains.com/idea/webhelp/code-inspection.html) and [Creating Custom Inspections](https://www.jetbrains.com/idea/help/creating-custom-inspections.html) for more information.

See [Inspections](https://jetbrains.design/intellij/text/inspections/) topic in the IntelliJ Platform UI Guidelines on naming, writing description, and message texts for inspections.

## Creating an Inspection Plugin

The [comparing_references_inspection](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/comparing_references_inspection) code sample adds a new inspection to the <control>Java | Probable Bugs</control> group in the [Inspections list](https://www.jetbrains.com/help/idea/inspections-settings.html).
The inspection reports when the `==` or `!=` operator is used between Java expressions of reference types.

It illustrates the components for a custom inspection plugin:
* Describing an [inspection](#plugin-configuration-file) in the plugin configuration file.
* Implementing a [local inspection class](#inspection-implementation-java-class) to inspect Java code in the editor.
* Creating a [visitor](#visitor-implementation-class) to traverse the PSI tree of the Java file being edited, inspecting for problematic syntax.
* Implementing a [quick fix](#quick-fix-implementation) class to correct syntax problems by altering the PSI tree as needed.
  Quick fixes are displayed to the user like [intentions](code_intentions.md).
* Implementing an [inspection preferences panel](#inspection-preferences-panel) to display information about the inspection.
* Writing an HTML [description](#inspection-description) of the inspection for display in the inspection preferences panel.
* Optionally, create a [unit test](#inspection-unit-test) for the plugin.

Although the IntelliJ Platform SDK code samples illustrate implementations of these components, it is often useful to see examples of inspections implemented in the _intellij_community_ code base.
This process can help find inspection descriptions and implementations based on what is visible in the IDE UI.
The overall approach works for inspections aimed at other languages as well.
* Find an existing inspection that is similar to the one you want to implement in the <menupath>Settings/Preferences | Editor | Inspections</menupath> panel.
  Note the display name of the inspection.
  For example, the Java/Probable Bugs inspection <control>Object comparison using '==', instead of 'equals()'</control> is very similar to `comparing_references_inspection`.
* Use the display name text as the [target for a search](https://www.jetbrains.com/help/idea/finding-and-replacing-text-in-project.html) within the _intellij_community_ project.
  This will identify a bundle file if the display name is localized.
  If it is not localized, the search finds either the plugin configuration (<path>plugin.xml</path>) file where it is an attribute in the inspection description, or the implementation where it is provided by an overridden method.
* In the case of localization, copy the key from the bundle file identified by the search.
  * Use the key text as the target for a search within the _intellij_community_ project.
    This search locates the plugin configuration file that describes the inspection.
  * From the inspection description entry, find the `implementationClass` attribute value.
* Use the `implementationClass` text as the [target of a class search](https://www.jetbrains.com/help/idea/searching-everywhere.html#Searching_Everywhere.xml) in the _intellij_community_ codebase to find the implementation.

See also [](explore_api.md) for more information and strategies.

## Creating an Inspection

The [comparing_references_inspection](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/comparing_references_inspection) code sample reports when the `==` or `!=` operators are used between Java expressions of reference types.
The user can apply a quick fix to change `a==b` to `a.equals(b)`, or `a!=b` to `!a.equals(b)`.

The details of the `comparing_references_inspection` implementation illustrate the components of an inspection plugin.

### Plugin Configuration File

The `comparing_references_inspection` is described as a `com.intellij.localInspection` extension point in the `comparing_references_inspection` plugin configuration ([`plugin.xml`](https://github.com/JetBrains/intellij-sdk-code-samples/blob/main/comparing_references_inspection/src/main/resources/META-INF/plugin.xml)) file.

There exist two types of inspection extensions:
* The `com.intellij.localInspection` extension point is used for inspections that operate on one file at a time, and also operate "on-the-fly" as the user edits the file.
* The `com.intellij.globalInspection` extension point is used for inspections that operate across multiple files, and the associated fix might, for example, refactor code between files.

The minimum inspection description must contain the `implementationClass` attribute.
As shown in the `comparing_references_inspection` plugin configuration file, other attributes can be defined in the `localInspection` element, either with or without localization.
In most cases, it is simplest to define the attributes in the plugin configuration file because the underlying parent classes handle most of the class responsibilities based on the configuration file description.
Note that some attributes are not displayed to the user, so they are never localized.

If required, inspections can define all of the attribute information (except `implementationClass`) by overriding methods in the inspection implementation class (not recommended in general).

### Inspection Implementation Java Class

Inspection implementations for Java files, like [`ComparingReferencesInspection`](https://github.com/JetBrains/intellij-sdk-code-samples/blob/main/comparing_references_inspection/src/main/java/org/intellij/sdk/codeInspection/ComparingReferencesInspection.java), are often based on the Java class [`AbstractBaseJavaLocalInspectionTool`](upsource:///java/java-analysis-api/src/com/intellij/codeInspection/AbstractBaseJavaLocalInspectionTool.java).
The `AbstractBaseJavaLocalInspectionTool` implementation class offers methods to inspect Java classes, fields, and methods.

More generally, `localInspection` types are based on the class [`LocalInspectionTool`](upsource:///platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java).
Examining the class hierarchy for `LocalInspectionTool` shows that the IntelliJ Platform provides many child inspection classes for a variety of languages and frameworks.
One of these classes is a good basis for a new inspection implementation, but a bespoke implementation can also be based directly on `LocalInspectionTool`.

The primary responsibilities of the inspection implementation class are to provide:
* A `PsiElementVisitor` object to traverse the PSI tree of the file being inspected.
* A `LocalQuickFix` class to fix an identified problem.
* A `JPanel` to be displayed in the <control>Inspections</control> settings dialog.

The `ComparingReferencesInspection` class defines two `String` fields:
* `QUICK_FIX_NAME` defines the string users see when prompted to apply the quick fix.
* `CHECKED_CLASSES` holds a list of class names of interest to the inspection.

The overridden `ComparingReferencesInspection` methods are discussed in the sections below.

### Visitor Implementation Class

The visitor class evaluates whether elements of the file's PSI tree are of interest to an inspection.

The `ComparingReferencesInspection.buildVisitor()` method creates an anonymous visitor class based on [`JavaElementVisitor`](upsource:///java/java-psi-api/src/com/intellij/psi/JavaElementVisitor.java) to traverse the PSI tree of the Java file being edited, inspecting for suspect syntax.
The anonymous class overrides three methods in particular.
* `visitReferenceExpression()` to prevent any duplicate visitation of reference-type expressions.
* `visitBinaryExpression()`, which does all the heavy lifting.
  It is called to evaluate a `PsiBinaryExpression`, and it checks to see if the operands are `==` or `!=`, and if the operands are classes relevant to this inspection.
* `isCheckedType()` evaluates the `PsiType` of the operands to determine if they are of interest to this inspection.

### Quick Fix Implementation

The quick fix class acts much like an intention, allowing the user to invoke it on the `PsiElement` (or `TextRange`) highlighted by the inspection.

The `ComparingReferencesInspection` implementation uses the nested class `CriQuickFix` to implement a quick fix based on [`LocalQuickFix`](upsource:///platform/analysis-api/src/com/intellij/codeInspection/LocalQuickFix.java).
The `CriQuickFix` class gives a user the option to change the use of `a == b` and `a != b` expression to `a.equals(b)` and `!a.equals(b)` respectively.

The heavy lifting is done in `CriQuickFix.applyFix()`, which manipulates the PSI tree to convert the expressions.
The change to the PSI tree is accomplished by the usual approach to modification:
* Getting a `PsiElementFactory`.
* Creating a new `PsiMethodCallExpression`.
* Substituting the original left and right operands into the new `PsiMethodCallExpression`.
* Replacing the original binary expression with the `PsiMethodCallExpression`.

### Inspection Preferences Panel

The inspection preferences panel is used to display information and provide additional options for the inspection.

The panel created by `ComparingReferencesInspection.createOptionsPanel()` just defines a single `JTextField` to display in a `JPanel`.
This `JPanel` gets added to the <control>Inspections</control> settings dialog when the inspection is selected.
The `JTextField` allows editing of the `CHECKED_CLASSES` field while displayed in the panel.

Note that the IntelliJ Platform provides most of the UI displayed in the <control>Inspections</control> panel.

### Inspection Description

The inspection description is an HTML file.
The description is displayed in the upper right panel of the <control>Inspections</control> settings dialog when an inspection is selected from the list.

Implicit in using [`LocalInspectionTool`](upsource:///platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java) in the class hierarchy of the inspection implementation means following some conventions.
* The inspection description file is expected to be located under <path>$RESOURCES_ROOT_DIRECTORY$/inspectionDescriptions/</path>.
  If the inspection description file is to be located elsewhere, override `getDescriptionUrl()` in the inspection implementation class.
* The name of the description file is expected to be the inspection <path>$SHORT_NAME$.html</path> as provided by the inspection description, or the inspection implementation class.
  If a short name is not provided by the plugin, the IntelliJ Platform computes one by removing `Inspection` suffix from the implementation class name.

> To open related [settings](settings.md) directly from the inspection description, add a link with `settings://$CONFIGURABLE_ID$`, optionally followed by `?$SEARCH_STRING$` to pre-select UI element:
>
> `See <em>Includes</em> tab in <a href="settings://fileTemplates">Settings/Preferences | Editor | File and Code Templates</a> to configure.`
>
{type="tip"}

### Inspection Unit Test

> Please note that running the test requires setting system property `idea.home.path` in the `test` task configuration of the Gradle build script.
>
{type="note"}

The `comparing_references_inspection` code sample provides a unit test for the inspection.
See the [](testing_plugins.md) section for general information about plugin testing.

The `comparing_references_inspection` test is based on the [`UsefulTestCase`](upsource:///platform/testFramework/src/com/intellij/testFramework/UsefulTestCase.java) class, part of the JUnit framework APIs.
This class handles much of the underlying boilerplate for tests.

By convention, the folder <path>test/testData/</path> contains the test files.
The folder contains pairs of files for each test using the name convention <path>∗.java</path> and <path>∗.after.java</path>.

In the case of `comparing_references_inspection` the test files are <path>Eq.java</path> / <path>Eq.after.java</path>, and <path>Neq.java</path> / <path>Neq.after.java</path>.

The `comparing_references_inspection` tests run the inspection on the <path>∗.java</path> files, implement the quick fix, and compare the results with the respective <path>∗.after.java</path> files containing expected result.

## Running the Comparing References Inspection Code Sample

The [comparing_references_inspection](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/comparing_references_inspection) code sample adds a new inspection to the <control>Java | Probable Bugs</control> group in the [Inspections List](https://www.jetbrains.com/help/idea/inspections-settings.html).

See [](code_samples.md) on how to set up and run the plugin.

### Configuring the Plugin

Once the plugin is launched, you can set the plugin options.
You can specify the Java classes to participate in the code inspection and the severity level of the found probable bugs.

On the main menu, open the <menupath>Settings/Preferences | Editor | Inspections</menupath> dialog.
In the list of the IntelliJ IDEA <control>Java</control> inspections, expand the <control>Probable bugs</control> node, and then click <control>SDK: '==' or '!=' instead of 'equals()'</control>.

![Comparing References inspection options](comparingReferences_options.png)

Under <control>Options</control>, you can specify the following plugin settings:
* From the <control>Severity</control> list, select the severity level of probable bugs the plugin finds such as <control>Warning</control>, <control>Error</control>, etc.
* In the text box under <control>Severity</control>, specify the semicolon separated list of Java classes to participate in this code inspection.
* When finished, click <control>OK</control>.

### How does it work?

The plugin inspects your code opened in the IntelliJ IDEA editor.
The plugin highlights the code fragments where two variables of the reference type are separated by `==` or `!=` and proposes to replace this code fragment with `.equals()`:

![Comparing References inspection highlighting and quick fix](comparingReferences.png)

In this example, the `str1` and `str2` are variables of the String type.
Invoking <control>SDK: Use equals()</control> replaces:

<compare>

```java
return (str1 == str2);
```

```java
return (str1.equals(str2));
```
</compare>
