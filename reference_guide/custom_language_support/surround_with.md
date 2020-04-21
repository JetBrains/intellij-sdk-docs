---
title: Surround With
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

To support the _Surround With_ action, the plugin needs to register one or more implementations of the
[`SurroundDescriptor`](upsource:///platform/lang-api/src/com/intellij/lang/surroundWith/SurroundDescriptor.java)
interface in the `com.intellij.lang.surroundDescriptor` extension point.
Each of the surround descriptors defines a possible type of code fragment which can be surrounded - for example, one surround descriptor can handle surrounding expressions, and another can handle statements.
Each surround descriptor, in turn, contains an array of
[`Surrounder`](upsource:///platform/lang-api/src/com/intellij/lang/surroundWith/Surrounder.java)
objects, defining specific templates which can be used for surrounding the selected code fragment (for example, _Surround With if_, _Surround With for_, and so on).

When the _Surround With_ action is invoked, the IDE queries all surround descriptors for the language until it finds one that returns a non-empty array from its `getElementsToSurround()` method.
Then it calls the
[`Surrounder.isApplicable()`](upsource:///platform/lang-api/src/com/intellij/lang/surroundWith/Surrounder.java)
method for each surrounder in that descriptor to check if the specific template is applicable in the current context.
Once the user selects a specific surrounder from the popup menu, the
[`Surrounder.surroundElements()`](upsource:///platform/lang-api/src/com/intellij/lang/surroundWith/Surrounder.java)
method is used to execute the surround action.

**Example:**
[`SurroundDescriptor`](upsource:///plugins/groovy/src/org/jetbrains/plugins/groovy/lang/surroundWith/GroovySurroundDescriptor.java)
for Groovy plugin