[//]: # (title: Text Selection)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Extending/Shrinking Text Selection

Implementing [`ExtendWordSelectionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/ExtendWordSelectionHandler.java) and registering it as `com.intellij.extendWordSelectionHandler` EP in your <path>[plugin.xml](plugin_configuration_file.md)</path> allows you to provide additional text ranges to be used when extending or shrinking a selection.
Return `true` from `canSelect(PsiElement)` for the PSI elements that you want to provide additional text-ranges for.
The IntelliJ Platform will call `select(PsiElement, CharSequence, int, Editor)` for these elements where you can compute additional text ranges and return them as `List<TextRange>`.

See also:

- [](surround_with.md)
- [](additional_minor_features.md)

### Overview

The two actions [Extend Selection and Shrink Selection](https://www.jetbrains.com/help/idea/working-with-source-code.html#editor_code_selection) in IntelliJ Platform IDEs let you adjust selected text based on the structure of the source code.
This makes it easy to select not only expressions, blocks, and function definitions, but also code like whole lines or tags in JavaDoc comments.

When implementing a custom language, the IntelliJ Platform provides basic implementations of this EP, allowing you to select code based on your PSI structure and to select whole lines.
In many cases this is sufficient to provide a good user experience.
However, sometimes it's advantageous to provide additional regions that the user may wish to be able to select when extending or shrinking a selection.

This EP has two methods that need to be implemented:

1. `canSelect(PsiElement)` is called on each PSI element, starting from the element at the cursor and walking up each of its parents.
   Return `true` for a particular element to indicate that further text-ranges should be included for the PSI element.
2. `select(PsiElement, CharSequence, int, Editor)` returns the text-ranges within the PSI element of interest are calculated and returned.

### Example Use-Case

A possible use-case for custom language developers is a function call `f(a, b)` where the function call node has its two arguments as children.
If the cursor is located at argument `a`, extending the selection would first select argument `a` itself and in the next step grow to cover the whole function call.
However, you might want to select the list of all arguments as an intermediate step.
This can be achieved by implementing this EP in the following way:

1. Create a class that implements the `ExtendWordSelectionHandler` interface and register it as a `com.intellij.extendWordSelectionHandler` EP in your <path>plugin.xml</path>.
2. The `canSelect(PsiElement)` method should return `true` for the function call node.
   That indicates that `select(PsiElement, CharSequence, int, Editor)` will be called for the function-call node.
3. When the `select()` method is called, you can use the function call PSI element or the editor text to extract the text range that spans the arguments `a` and `b` and return it as `List<TextRange>` with one element.

### Further Insight and Debugging

Looking at other implementations can be an effective way to get a better understanding of how this EP works.
To get further insight into this EP, you may want to take a look at [`DocTagSelectioner`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/editorActions/wordSelection/DocTagSelectioner.java).
It provides the ability to select tag names like `@param` in JavaDoc comments.
Additionally, the [IntelliJ Platform Explorer](https://jb.gg/ipe?extensions=com.intellij.extendWordSelectionHandler) provides a list of open-source plugins with implementations of the `com.intellij.extendWordSelectionHandler` EP.

There are also some important places in the IntelliJ Platform to add breakpoints during debugging.
When _Extend Selection_ is called by the user, it is handled by [`SelectWordHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/SelectWordHandler.java).
The majority of the work, however, is then done inside [`SelectWordUtil`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/SelectWordUtil.java), where `processElement()` checks which implementations of this EP apply for the current PSI element.
If one of them returns `true` from its `canSelect()` method, the additional text ranges are extracted in the `askSelectioner()` function.
These places are good candidates to set breakpoints and investigate during debugging.
