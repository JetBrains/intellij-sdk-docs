---
title: Live Templates
---

*Live templates* in IntelliJ IDEA are customizable rules that allow developers to abbreviate repetitive patterns of text in the editor. When a user types the designated abbreviation followed by a configurable *expansion key* (usually `Tab`), the IDE will transform the preceding input sequence to its full-length output, and update the cursor position. For example, consider a `for` loop. Typically, the end user would need to type `for(int i = 0; i < 10; i++) {<Enter><Tab><Enter><Enter>}<Up>`. This pattern may be shortened to `fori<Tab>` and the remaining contents will be expanded, leaving the following structure:

```
for(int i = [|]; i < []; i++) {
    []
}
```
 
 As the user completes each section of the `for` loop and presses `Tab`, the cursor will advance to the next position in the editor. For more information about creating your own Custom Live Templates, you may refer to the [corresponding documentation](https://www.jetbrains.com/idea/help/creating-and-editing-live-templates.html). In this tutorial, we will illustrate how to add default Custom Live Templates to an IntelliJ Platform plugin, and assign valid contexts where these templates can take on added functionality based on the surrounding code and file type. We will discuss how to export existing live templates, and bundle them within a plugin to give plugin users added typing efficiency when using a custom language.
 
 *  [1. Create a new Live Template](live_templates/template_support.md)