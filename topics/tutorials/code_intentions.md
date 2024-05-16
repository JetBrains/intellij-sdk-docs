<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Intentions

<link-summary>Intention actions allowing to fix code issues or transform the code to a different form.</link-summary>

<tldr>

**Product Help:** [Intention Actions](https://www.jetbrains.com/help/idea/intention-actions.html)

**UI Guidelines:** [](inspections.md)

</tldr>

This topic describes the [conditional_operator_intention](%gh-sdk-samples-master%/conditional_operator_intention), a sample plugin that adds a new [intention action](https://www.jetbrains.com/help/idea/intention-actions.html) to the IDE Intentions list.
In addition, the sample plugin contains a JUnit-based test.

## About Intention Actions

The IntelliJ Platform analyzes your code and helps handle situations that may result in errors.
When a possible problem is suspected, the IDE suggests an appropriate intention action, denoted with special icons.

See [Inspections](inspections.md) topic in UI Guidelines on naming, writing description, and message texts for inspections/intentions.

You can view a list of all available intention actions as well as enable/disable them using the [Intentions List](https://www.jetbrains.com/help/idea/intention-actions.html#intention-settings) in <ui-path>Settings | Editor | Intentions</ui-path>.

## Techniques Used

The [conditional_operator_intention](%gh-sdk-samples-master%/conditional_operator_intention) sample plugin illustrates the use of the following techniques:

- How to analyze a [PSI tree](psi_files.md).
- How to find a Java token of interest in the PSI tree.
- How to invoke a quick fix action for a token element under cursor using the [`PsiElementBaseIntentionAction`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/intention/PsiElementBaseIntentionAction.java) class.
- How to create a JUnit test for this plugin using the [`IdeaTestFixtureFactory`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/fixtures/IdeaTestFixtureFactory.java) class.

> In case of providing multiple intention actions for a single element, their ordering is indeterministic due to performance reasons.
> It is possible to push specific items up or down by implementing
> [`HighPriorityAction`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/HighPriorityAction.java)
> or
> [`LowPriorityAction`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/LowPriorityAction.java)
> respectively.
>
{style="note"}

## Sample Plugin

When launched, the sample plugin adds the <control>SDK: Convert ternary operator to if statement</control> item to the <control>SDK intentions</control> group in the <ui-path>Settings | Editor | Intentions</ui-path>.

#### Running the Plugin

See [Code Samples](code_samples.md) on how to set up and run the plugin.

#### How does it work?

The plugin analyzes symbols under the cursor in your code opened in the editor.
If the cursor is positioned on the `?` conditional operator, IntelliJ IDEA proposes to replace this conditional (ternary) operator with the "if-then-else" statement:

![Convert ternary operator intention popup](ternary_operator_intention.png)
{width=675}

Invoking <control>SDK: Convert ternary operator to if statement</control> intention action will result in transforming expression to the form visible in the [preview](code_intentions_preview.md) popup (code fragment on the right).

#### Testing the Plugin

> Please note that running the test requires setting system property `idea.home.path` in the `test` task configuration of the Gradle build script.
>
{style="note"}

The sample plugin contains the `ConditionalOperatorConverterTest` Java class and the test data in the <path>test/testData/</path> directory.
To perform the plugin test, run the `ConditionalOperatorConverterTest.testIntention()` method.
