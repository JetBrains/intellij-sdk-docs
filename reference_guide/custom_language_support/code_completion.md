---
title: Code Completion
---

There are two main types of code completion that can be provided by custom language plugins: reference completion and contributor-based completion.

Reference completion is easier to implement, but supports only the basic completion action.
Contributor-based completion provides more features, supports all three completion types (basic, smart and class name) and can be used, for example, to implement keyword completion.

### Reference Completion

To fill the completion list, the IDE calls
[PsiReference.getVariants()](upsource:///platform/core-api/src/com/intellij/psi/PsiReference.java)
either on the reference at the caret location or on a dummy reference that would be placed at the caret.
This method needs to return an array of objects containing either strings,
[PsiElement](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java)
instances or instances of the
[LookupElement](upsource:///platform/lang-api/src/com/intellij/codeInsight/lookup/LookupElement.java)
class.
If a
[PsiElement](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java)
instance is returned in the array, the completion list shows the icon for the element.

The most common way to implement `getVariants()` is to use the same function for walking up the tree as in
[PsiReference.resolve()](upsource:///platform/core-api/src/com/intellij/psi/PsiReference.java),
and a different implementation of
[PsiScopeProcessor](upsource:///platform/core-api/src/com/intellij/psi/scope/PsiScopeProcessor.java)
which collects all declarations passed to its `processDeclarations()` method and returns them as an array for filling the completion list.

### Contributor-based Completion

Implementing the
[CompletionContributor](upsource:///platform/lang-api/src/com/intellij/codeInsight/completion/CompletionContributor.java)
interface gives you the greatest control over the operation of code completion for your language.
Note that the JavaDoc of that class contains a detailed FAQ for implementing code completion.

The core scenario of using
[CompletionContributor](upsource:///platform/lang-api/src/com/intellij/codeInsight/completion/CompletionContributor.java)
consists of calling the `extend()` method and passing in the *pattern* specifying the context in which this completion variant is applicable, as well as a *completion provider* which generates the items to show in the completion list.

**Example**:
[CompletionContributor](https://github.com/JetBrains/intellij-plugins/blob/master/osmorc/src/org/osmorc/manifest/completion/OsgiManifestCompletionContributor.java)
for completing keywords in MANIFEST.MF files.


Items shown in the completion list are represented by instances of the
[LookupElement](upsource:///platform/lang-api/src/com/intellij/codeInsight/lookup/LookupElement.java)
interface.
These instances are normally created through the
[LookupElementBuilder](upsource:///platform/lang-api/src/com/intellij/codeInsight/lookup/LookupElementBuilder.java)
class.
For every lookup element, you can specify the following attributes:

* Text, tail text and type text. Tail text is shown next to the main item text, is not used for prefix matching, and can be used, for example, to show the parameter list of the method. Type text is shown right-aligned in the lookup list and can be used to show the return type or containing class of a method, for example.
* Icon
* Text attributes (bold, strikeout etc.)
* Insert handler. The insert handler is a callback which is called when the item is selected, and can be used to perform additional modifications of the text (for example, to put in the parentheses for a method call)
