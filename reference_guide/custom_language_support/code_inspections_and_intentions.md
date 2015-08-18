---
title: Code Inspections and Intentions
---

The code inspections for custom languages use the same API as all other code inspections, based on the
[LocalInspectionTool](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java)
class.

The functionality of
[LocalInspectionTool](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java)
partially duplicates that of
[Annotator](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/platform/analysis-api/src/com/intellij/lang/annotation/Annotator.java).
The main differences are that
[LocalInspectionTool](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java)
supports batch analysis of code (through the **Analyze \| Inspect Code...** action), the possibility to turn off the inspection (globally or by suppressing them on various levels) and to configure the inspection options.
If none of that is required and the analysis only needs to run in the active editor,
[Annotator](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/platform/analysis-api/src/com/intellij/lang/annotation/Annotator.java)
provides better performance (because of its support for incremental analysis) and more flexibility for highlighting errors.

**Example**:
A
[simple inspection](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/plugins/properties/properties-psi-impl/src/com/intellij/codeInspection/TrailingSpacesInPropertyInspection.java)
for
[Properties language plugin](https://github.com/JetBrains/intellij-community/tree/master/plugins/properties/)


The code intentions for custom languages also use the regular API for intentions.
The intention classes need to implement the
[IntentionAction](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/platform/analysis-api/src/com/intellij/codeInsight/intention/IntentionAction.java)
interface and to be registered using the `<intentionAction>` bean in your *plugin.xml*.

**Example:**
A
[simple intention action](https://upsource.jetbrains.com/idea-community/file/1731d054af4ca27aa827c03929e27eeb0e6a8366/plugins/groovy/src/org/jetbrains/plugins/groovy/intentions/control/SplitIfIntention.java)
for Groovy
