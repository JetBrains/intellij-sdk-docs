<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Surround With

<link-summary>Surrounding code fragments with various constructs.</link-summary>

<tldr>

**Product Help:** [Surround code fragments](https://www.jetbrains.com/help/idea/surrounding-blocks-of-code-with-language-constructs.html)

</tldr>

To support the _Surround With_ action, the plugin needs to register one or more implementations of the [`SurroundDescriptor`](%gh-ic%/platform/lang-api/src/com/intellij/lang/surroundWith/SurroundDescriptor.java) interface
in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.lang.surroundDescriptor"/></include>.
Each of the surround descriptors defines a possible type of code fragment that can be surrounded - for example, one surround descriptor can handle surrounding expressions, and another can handle statements.
Each surround descriptor, in turn, contains an array of [`Surrounder`](%gh-ic%/platform/lang-api/src/com/intellij/lang/surroundWith/Surrounder.java) objects, defining specific templates which can be used for surrounding the selected code fragment (for example, _Surround With if_, _Surround With for_, and so on).

When the <ui-path>Code | Surround With...</ui-path> action is invoked, the IDE queries all surround descriptors for the language until it finds one that returns a non-empty array from its `getElementsToSurround()` method.
Then it calls the [`Surrounder.isApplicable()`](%gh-ic%/platform/lang-api/src/com/intellij/lang/surroundWith/Surrounder.java) method for each surrounder in that descriptor to check if the specific template is applicable in the current context.
Once the user selects a specific surrounder from the popup menu, the [`Surrounder.surroundElements()`](%gh-ic%/platform/lang-api/src/com/intellij/lang/surroundWith/Surrounder.java) method is used to execute the surround action.

**Example:**
[`SurroundDescriptor`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/lang/surroundWith/GroovySurroundDescriptor.java) for Groovy plugin

> See the [](live_templates.md) and [](advanced_postfix_templates.md#surround-postfix-templates) sections for information on how to support code surrounding with other IDE features.
>
{style="note"}
