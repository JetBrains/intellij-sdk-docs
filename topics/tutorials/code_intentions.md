[//]: # (title: Intentions)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This topic describes the [conditional_operator_intention](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/conditional_operator_intention), a sample plugin that adds a new [intention action](https://www.jetbrains.com/help/idea/intention-actions.html) to the IDE Intentions list.
In addition, the sample plugin contains a JUnit-based test.

## About Intention Actions

The IntelliJ Platform analyzes your code and helps handle situations that may result in errors.
When a possible problem is suspected, the IDE suggests an appropriate intention action, denoted with special icons.
For more information, refer to [Intention Actions](https://www.jetbrains.com/help/idea/intention-actions.html) in the IntelliJ IDEA Web Help.

See [Inspections](https://jetbrains.design/intellij/text/inspections/) topic in the IntelliJ Platform UI Guidelines on naming, writing description, and message texts for inspections/intentions.

You can view a list of all available intention actions as well as enable/disable them using the [Intentions List](https://www.jetbrains.com/help/idea/intention-actions.html#intention-settings) in <menupath>Settings/Preferences | Editor | Intentions</menupath>.

## Techniques Used

The [conditional_operator_intention](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/conditional_operator_intention) sample plugin illustrates the use of the following techniques:

- How to analyze a [PSI tree](psi_files.md).
- How to find a Java token of interest in the PSI tree.
- How to invoke a quick fix action for a token element under cursor using the [`PsiElementBaseIntentionAction`](upsource:///platform/lang-api/src/com/intellij/codeInsight/intention/PsiElementBaseIntentionAction.java) class.
- How to create a JUnit test for this plugin using the [`IdeaTestFixtureFactory`](upsource:///platform/testFramework/src/com/intellij/testFramework/fixtures/IdeaTestFixtureFactory.java) class.

## Sample Plugin

When launched, this plugin adds the <control>Convert ternary operator if statement</control> item to the <control>Conditional Operator</control> node in the Intentions List:

![Intention settings dialog](IntentionsList.png)

#### Running the Plugin

See [Code Samples](code_samples.md) on how to set up and run the plugin.

#### How does it work?

The plugin analyzes symbols under the cursor in your code opened in the editor.
If the cursor is positioned on the `?` conditional operator, IntelliJ IDEA proposes to replace this conditional (ternary) operator with the "if-then-else" statement:

![Convert ternary operator intention popup](TernaryOperator.png)

In this example:

<compare>

```java
return (n>=0) ? n : -n;
```

```java
if ((n>=0)) {
  return n;
} else {
  return -n;
}
```
</compare>

#### Testing the Plugin

> Please note that running the test requires setting system property `idea.home.path` in the `test` task configuration of the Gradle build script.
>
{type="note"}

The sample plugin contains the `ConditionalOperatorConverterTest` Java class and the test data in the <path>test/testData/</path> directory.
To perform the plugin test, run the `ConditionalOperatorConverterTest.testIntention()` method.

For detailed information about testing and all related procedures, refer to [Testing](https://www.jetbrains.com/help/idea/performing-tests.html) in the IntelliJ IDEA Web Help.
