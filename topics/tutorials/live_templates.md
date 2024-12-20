<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Live Templates

<link-summary>Live templates support overview.</link-summary>

<tldr>

**Product Help:** [Live templates](https://www.jetbrains.com/help/idea/using-live-templates.html)

</tldr>

*Live Templates* are customizable rules that allow developers to abbreviate repetitive text patterns or surround code fragments with repetitive constructs in the editor.

When a user types the designated abbreviation followed by a configurable *expansion key* (usually <shortcut>Tab</shortcut>), the IDE transforms the preceding input sequence to its full-length output, and update the cursor position.

For example, consider a Java `for` loop.
Typically, the end user would need to type `for (int i = 0; i < 10; i++) {<Enter><Tab><Enter><Enter>}<Up>`.
This pattern may be shortened to `fori<Tab>` and the remaining contents will be expanded, leaving the following structure:

```java
for (int i = [|]; i < []; i++) {
  []
}
```

As the user completes each section of the `for` loop and presses `Tab`, the cursor advances to the next position in the editor.

Another use-case for live templates is surrounding the selected code with additional constructs.
When a user selects a code fragment and invokes the <ui-path>Code | Surround With...</ui-path> action and chooses the template from the list, the code is wrapped with the content defined in the template.

Consider the following Java method with the selected fragment inside `<selection>`:

```java
public void testMethod() {
  <selection>getActions()</selection>
}
```

Invoking the <ui-path>Code | Surround With...</ui-path> action and selecting the <control>Iterate Iterable or array</control> template would transform the code to:

```java
public void testMethod() {
  for (Action action : getActions()) {
    <cursor>
  }
}
```

> See the [](surround_with.md) section for the information on how to implement more advanced code surrounding.
>
{style="note"}

For more information about creating Custom Live Templates, refer to the [corresponding documentation](https://www.jetbrains.com/idea/help/creating-and-editing-live-templates.html).

These sections describe how to add Live Templates, and their associated building blocks, to plugins.
 * [](providing_live_templates.md)
   * [](live_templates_configuration_file.md)
 * [](creating_live_template_functions.md)
