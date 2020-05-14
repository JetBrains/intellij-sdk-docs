---
title: Code Inspections and Intentions
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

### Inspections

The code inspections for custom languages use the same API as all other code inspections, based on the
[`LocalInspectionTool`](upsource:///platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java)
class.

The functionality of
[`LocalInspectionTool`](upsource:///platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java)
partially duplicates that of
[Annotator](syntax_highlighting_and_error_highlighting.md#annotator).

The main differences are:
- supports batch analysis of code (through the **Analyze \| Inspect Code...** action)
- the possibility to turn off the inspection (globally or by suppressing them on various levels) 
- ability to configure the inspection options.

If none of that is required and the analysis only needs to run in the active editor,
[Annotator](syntax_highlighting_and_error_highlighting.md#annotator)
provides better performance (because of its support for incremental analysis) and more flexibility for highlighting errors.

**Examples**:
- [Code Inspections Tutorial](/tutorials/code_inspections.md)
- A [simple inspection](upsource:///plugins/properties/properties-psi-impl/src/com/intellij/codeInspection/TrailingSpacesInPropertyInspection.java) for [Properties language plugin](upsource:///plugins/properties/)


### Intentions

The code intentions for custom languages also use the regular API for intentions.
The intention classes need to implement the
[`IntentionAction`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/intention/IntentionAction.java)
interface and are registered using the `com.intellij.intentionAction` extension point.

**Examples:**
- [Code Intentions Tutorial](/tutorials/code_intentions.md)
- A [simple intention action](upsource:///plugins/groovy/src/org/jetbrains/plugins/groovy/intentions/control/SplitIfIntention.java) for Groovy
- [Custom Language Support Tutorial: Quick Fix](/tutorials/custom_language_support/quick_fix.md)
