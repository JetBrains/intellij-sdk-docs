---
title: Surround With
---

In order to support the `Code | Surround With...` action, the plugin needs to register one or more implementations of the
[SurroundDescriptor](upsource:///platform/lang-api/src/com/intellij/lang/surroundWith/SurroundDescriptor.java)
interface in the `com.intellij.lang.surroundDescriptor` extension point.
Each of the surround descriptors defines a possible type of code fragment which can be surrounded - for example, one surround descriptor can handle surrounding expressions, and another can handle statements.
Each surround descriptor, in turn, contains an array of
[Surrounder](upsource:///platform/lang-api/src/com/intellij/lang/surroundWith/Surrounder.java)
objects, defining specific templates which can be used for surrounding the selected code fragment (for example, `Surround With if`, `Surround With for` and so on).

When the `Surround With...` action is invoked, the IDE queries all surround descriptors for the language until it finds one that returns a non-empty array from its `getElementsToSurround()` method.
Then it calls the
[Surrounder.isApplicable()](upsource:///platform/lang-api/src/com/intellij/lang/surroundWith/Surrounder.java)<!--#L46-->
method for each surrounder in that descriptor to check if the specific template is applicable in the current context.
Once the user selects a specific surrounder from the popup menu, the
[Surrounder.surroundElements()](upsource:///platform/lang-api/src/com/intellij/lang/surroundWith/Surrounder.java)<!--#L57-->
method is used to execute the surround action.
