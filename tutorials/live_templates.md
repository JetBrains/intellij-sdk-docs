---
title: Live Templates
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

*Live Templates* are customizable rules that allow developers to abbreviate repetitive patterns of text in the editor. 
When a user types the designated abbreviation followed by a configurable *expansion key* (usually `Tab`), the IDE transforms the preceding input sequence to its full-length output, and update the cursor position. 

For example, consider a `for` loop. Typically, the end user would need to type `for (int i = 0; i < 10; i++) {<Enter><Tab><Enter><Enter>}<Up>`. 
This pattern may be shortened to `fori<Tab>` and the remaining contents will be expanded, leaving the following structure:

```java
for (int i = [|]; i < []; i++) {
    []
}
```
 
As the user completes each section of the `for` loop and presses `Tab`, the cursor advances to the next position in the editor. 
For more information about creating Custom Live Templates, refer to the [corresponding documentation](https://www.jetbrains.com/idea/help/creating-and-editing-live-templates.html). 

These sections describe how to add Live Templates, and their associated building blocks, to plugins.
 * [Adding Live Templates to a Plugin](live_templates/template_support.md)
 * [Creating New Functions for Live Templates](live_templates/new_macros.md)
 * Surround Templates
