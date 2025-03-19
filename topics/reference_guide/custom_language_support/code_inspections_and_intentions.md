<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Code Inspections and Intentions

<link-summary>Introduction to analyzing the code and providing quick fixes for the found issues.</link-summary>

<tldr>

**Product Help:** [Code inspections](https://www.jetbrains.com/help/idea/code-inspection.html), [Intention actions](https://www.jetbrains.com/help/idea/intention-actions.html)

**UI Guidelines:** [](inspections.md)

</tldr>

Code inspections analyze code for potential issues, such as errors, inefficiencies, or code smells.
Code intentions suggest improvements and refactorings to enhance readability and maintainability.

While inspections focus on detecting problems, intentions provide optional enhancements to improve code structure.
Inspections are run on a full PSI tree and report found problems, while intention actions are run only on elements under the cursor.

## Inspections

The code inspections for custom languages use the same API as all other code inspections, based on the [`LocalInspectionTool`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java) class.

The functionality of [`LocalInspectionTool`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java) partially duplicates that of [Annotator](syntax_highlighting_and_error_highlighting.md#annotator).

The main differences are:
- supports batch analysis of code (through the <ui-path>Code | Inspect Code...</ui-path> action)
- the possibility to turn off the inspection (globally or by suppressing them on various levels)
- ability to configure the inspection options.

If none of that is required and the analysis only needs to run in the active editor, [Annotator](syntax_highlighting_and_error_highlighting.md#annotator) provides better performance (because it supports incremental analysis) and more flexibility for highlighting errors.

**Examples:**
- [Code Inspections Tutorial](code_inspections.md)
- A [simple inspection](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/codeInspection/TrailingSpacesInPropertyInspection.java) for the [Properties language plugin](%gh-ic%/plugins/properties)

> See changes in the [order of running highlighting](syntax_highlighting_and_error_highlighting.md#order-of-running-highlighting) introduced in 2024.1.

### Inspections Performance
<primary-label ref="2023.3"/>

A [custom language plugin](custom_language_support.md) providing many inspections (>100) can register the default [`PsiElementVisitor`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiElementVisitor.java)
for its language in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.inspection.basicVisitor"/></include> to optimize processing.

## Intentions

The code intentions for custom languages also use the standard API for intentions.
The intention classes need to implement the [`IntentionAction`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/IntentionAction.java) interface
and are registered using the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.intentionAction"/></include>.

**Examples:**
- [Code Intentions Tutorial](code_intentions.md)
- A [simple intention action](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/intentions/control/SplitIfIntention.java) for Groovy
- [Custom Language Support Tutorial: Quick Fix](quick_fix.md)
