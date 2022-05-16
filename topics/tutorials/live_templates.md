[//]: # (title: Live Templates)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

*Live Templates* are customizable rules that allow developers to abbreviate repetitive text patterns or surround code fragments with repetitive constructs in the editor.

When a user types the designated abbreviation followed by a configurable *expansion key* (usually `Tab`), the IDE transforms the preceding input sequence to its full-length output, and update the cursor position.

For example, consider a `for` loop.
Typically, the end user would need to type `for (int i = 0; i < 10; i++) {<Enter><Tab><Enter><Enter>}<Up>`.
This pattern may be shortened to `fori<Tab>` and the remaining contents will be expanded, leaving the following structure:

```java
for (int i = [|]; i < []; i++) {
  []
}
```

As the user completes each section of the `for` loop and presses `Tab`, the cursor advances to the next position in the editor.

Another use-case for live templates is surrounding the selected code with additional constructs.
When a user selects a code fragment and invokes the <menupath>Code | Surround With...</menupath> action and chooses the template from the list, the code is wrapped with the content defined in the template.

Consider the following Java method with the selected fragment:

```java
public void testMethod() {
  <selection>getActions()</selection>
}
```

Invoking the <menupath>Code | Surround With...</menupath> action and selecting the <control>Iterate Iterable or array</control> template would transform the code to:

```java
public void testMethod() {
  for (Action action : getActions()) {
    <cursor>
  }
}
```

> See the [](surround_with.md) section for the information on how to implement more advanced code surrounding.
>
{type="note"}

For more information about creating Custom Live Templates, refer to the [corresponding documentation](https://www.jetbrains.com/idea/help/creating-and-editing-live-templates.html).

These sections describe how to add Live Templates, and their associated building blocks, to plugins.
 * [Providing Live Templates](template_support.md)
 * [Creating New Functions for Live Templates](new_macros.md)
