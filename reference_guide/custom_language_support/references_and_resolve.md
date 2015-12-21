---
title: References and Resolve
---

One of the most important and tricky parts in implementing a custom language PSI is resolving references.
Resolving references gives users the ability to navigate from a PSI element usage (accessing a variable, calling a method and so on) to the declaration of that element (the variable's definition, a method declaration and so on).
This feature is needed in order to support the `Go to Declaration` action invoked by **Ctrl-B** and **Ctrl-Click**, and it is a prerequisite for implementing the `Find Usages` action, the `Rename` refactoring and code completion.

All PSI elements which work as references (for which the `Go to Declaration` action applies) need to implement the
[PsiElement.getReference()](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java)
method and to return a
[PsiReference](upsource:///platform/core-api/src/com/intellij/psi/PsiReference.java)
implementation from that method.
The
[PsiReference](upsource:///platform/core-api/src/com/intellij/psi/PsiReference.java)
interface can be implemented by the same class as
[PsiElement](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java),
or by a different class. An element can also contain multiple references (for example, a string literal can contain multiple substrings which are valid full-qualified class names), in which case it can implement
[PsiElement.getReferences()](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java)
and return the references as an array.

The main method of the
[PsiReference](upsource:///platform/core-api/src/com/intellij/psi/PsiReference.java)
interface is `resolve()`, which returns the element to which the reference points, or `null` if it was not possible to resolve the reference to a valid element (for example, should it point to an undefined class).
A counterpart to this method is `isReferenceTo()`, which checks if the reference resolves to the specified element.
The latter method can be implemented by calling `resolve()` and comparing the result with the passed PSI element, but additional optimizations are possible (for example, performing the tree walk only if the element text is equal to the text of the reference).

**Example**:
[Reference](upsource:///plugins/properties/src/com/intellij/lang/properties/ResourceBundleReference.java)
to a ResourceBundle in the
[Properties language plugin](upsource:///plugins/properties)


There's a set of interfaces which can be used as a base for implementing resolve support, namely the
[PsiScopeProcessor](upsource:///platform/core-api/src/com/intellij/psi/scope/PsiScopeProcessor.java) interface and the
[PsiElement.processDeclarations()](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java)
method.
These interfaces have a number of extra complexities which are not necessary for most custom languages (like support for substituting Java generics types), but they are required if the custom language can have references to Java code.
If Java interoperability is not required, the plugin can forgo the standard interfaces and provide its own, different implementation of resolve.

The implementation of resolve based on the standard helper classes contains of the following components:

*  A class implementing the
   [PsiScopeProcessor](upsource:///platform/core-api/src/com/intellij/psi/scope/PsiScopeProcessor.java)
   interface which gathers the possible declarations for the reference and stops the resolve process when it has successfully completed.
   The main method which needs to be implemented is `execute()`, which is called to process every declaration encountered during the resolve, and returns `true` if the resolve needs to be continued or `false` if the declaration has been found.
   The methods `getHint()` and `handleEvent()` are used for internal optimizations and can be left empty in the
   [PsiScopeProcessor](upsource:///platform/core-api/src/com/intellij/psi/scope/PsiScopeProcessor.java)
   implementations for custom languages.

*  A function which walks the PSI tree up from the reference location until the resolve has successfully completed or until the end of the resolve scope has been reached.
   If the target of the reference is located in a different file, the file can be located, for example, using
   [FilenameIndex.getFilesByName()](upsource:///platform/indexing-impl/src/com/intellij/psi/search/FilenameIndex.java)
   (if the file name is known) or by iterating through all custom language files in the project (`iterateContent()` in the
   [FileIndex](upsource:///platform/indexing-impl/src/com/intellij/psi/search/FilenameIndex.java)
   interface obtained from
   [ProjectRootManager.getFileIndex()](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ProjectRootManager.java)
   ).

*  The individual PSI elements, on which the `processDeclarations()` method is called during the PSI tree walk.
   If a PSI element is a declaration, it passes itself to the `execute()` method of the
   [PsiScopeProcessor](upsource:///platform/core-api/src/com/intellij/psi/scope/PsiScopeProcessor.java)
   passed to it.
   Also, if necessary according to the language scoping rules, a PSI element can pass the
   [PsiScopeProcessor](upsource:///platform/core-api/src/com/intellij/psi/scope/PsiScopeProcessor.java)
   to its child elements.

An extension of the
[PsiReference](upsource:///platform/core-api/src/com/intellij/psi/PsiReference.java)
interface, which allows a reference to resolve to multiple targets, is the
[PsiPolyVariantReference](upsource:///platform/core-api/src/com/intellij/psi/PsiPolyVariantReference.java)
interface.
The targets to which the reference resolves are returned from the `multiResolve()` method.
The `Go to Declaration` action for such references allows the user to choose a navigation target.
The implementation of `multiResolve()` can be also based on
[PsiScopeProcessor](upsource:///platform/core-api/src/com/intellij/psi/scope/PsiScopeProcessor.java),
and can collect all valid targets for the reference instead of stopping when the first valid target is found.

The `Quick Definition Lookup` action is based on the same mechanism as `Go to Declaration`, so it becomes automatically available for all references that can be resolved by the language plugin.
