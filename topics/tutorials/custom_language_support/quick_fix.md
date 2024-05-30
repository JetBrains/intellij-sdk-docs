<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 19. Quick Fix

<link-summary>Sample implementation a quick fix adding a missing Simple language property referenced in a Java file.</link-summary>

<include from="language_and_filetype.md" element-id="custom_language_tutorial_header"></include>

<tldr>

**Reference**: [](code_inspections_and_intentions.md)

**Code**: [`SimpleElementFactory`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/SimpleElementFactory.java),
[`SimpleCreatePropertyQuickFix`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleCreatePropertyQuickFix.java),
[`SimpleAnnotator`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleAnnotator.java)

</tldr>

A quick fix for a custom language supports the IntelliJ Platform-based IDE feature [Intention Actions](https://www.jetbrains.com/help/idea/intention-actions.html#apply-intention-actions).
For the Simple language, this tutorial adds a quick fix that helps to define an unresolved property from its usage.

## Update the Element Factory
The [`SimpleElementFactory`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/SimpleElementFactory.java) is updated to include two new methods to support the user choice of creating a new property for the Simple Language quick fix.
The new `createCRLF()` method supports adding a newline to the end of the [`test.simple`](lexer_and_parser_definition.md#run-the-project) file before adding a new property.
A new overload of `createProperty()` creates a new `key`-`value` pair for Simple Language.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/SimpleElementFactory.java" include-symbol="SimpleElementFactory"}

## Define an Intention Action
The [`SimpleCreatePropertyQuickFix`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleCreatePropertyQuickFix.java) creates a property in the file chosen by the user - in this case, a Java file containing a `prefix:key` - and navigate to this property after creation.
Under the hood, `SimpleCreatePropertyQuickFix` is an Intention Action.
For a more in-depth example of an Intention Action, see [`conditional_operator_intention`](%gh-sdk-samples-master%/conditional_operator_intention).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleCreatePropertyQuickFix.java" include-symbol="SimpleCreatePropertyQuickFix"}

## Update the Annotator
When a `badProperty` annotation is created, the `badProperty.registerFix()` method in
[`SimpleAnnotator`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleAnnotator.java) is called.
This method call registers the `SimpleCreatePropertyQuickFix` as the Intention Action for the IntelliJ Platform to use to correct the problem.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleAnnotator.java" include-symbol="SimpleAnnotator"}

## Run the Project
Run the project by using the Gradle [`runIde`](creating_plugin_project.md#running-a-plugin-with-the-runide-gradle-task) task.
Open the test [Java file](annotator.md#run-the-project).

To test `SimpleCreatePropertyQuickFix`, change `simple:website` to `simple:website.url`.
The key `website.url` is highlighted by `SimpleAnnotator` as an invalid key, as shown below.
Choose "Create Property".

![Quick Fix](quick_fix.png){width="800"}

The IDE opens the <path>test.simple</path> file and adds `website.url` as a new key.
Add the new value `jetbrains.com` for the new `website.url` key.

![New Property](new_property.png)

Now switch back to the Java file; the new key is highlighted as valid.
