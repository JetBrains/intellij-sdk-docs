[//]: # (title: Code Inspections and Intentions)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<microformat>

**Product Help:** [Code inspections](https://www.jetbrains.com/help/idea/code-inspection.html), [Intention actions](https://www.jetbrains.com/help/idea/intention-actions.html)

</microformat>

### Inspections

The code inspections for custom languages use the same API as all other code inspections, based on the [`LocalInspectionTool`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java) class.

The functionality of [`LocalInspectionTool`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java) partially duplicates that of [Annotator](syntax_highlighting_and_error_highlighting.md#annotator).

The main differences are:
- supports batch analysis of code (through the <menupath>Code | Inspect Code...</menupath> action)
- the possibility to turn off the inspection (globally or by suppressing them on various levels)
- ability to configure the inspection options.

If none of that is required and the analysis only needs to run in the active editor, [Annotator](syntax_highlighting_and_error_highlighting.md#annotator) provides better performance (because it supports incremental analysis) and more flexibility for highlighting errors.

See [Inspections](https://jetbrains.design/intellij/text/inspections/) topic in the IntelliJ Platform UI Guidelines on naming, writing description, and message texts for inspections.

**Examples**:
- [Code Inspections Tutorial](code_inspections.md)
- A [simple inspection](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/codeInspection/TrailingSpacesInPropertyInspection.java) for [Properties language plugin](%gh-ic%/plugins/properties)

### Intentions

The code intentions for custom languages also use the standard API for intentions.
The intention classes need to implement the [`IntentionAction`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/IntentionAction.java) interface and are registered using the `com.intellij.intentionAction` extension point.

**Examples:**
- [Code Intentions Tutorial](code_intentions.md)
- A [simple intention action](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/intentions/control/SplitIfIntention.java) for Groovy
- [Custom Language Support Tutorial: Quick Fix](quick_fix.md)
