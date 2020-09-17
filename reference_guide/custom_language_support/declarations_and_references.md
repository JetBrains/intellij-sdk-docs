---
title: Declarations and References
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

> **WARNING** This API is available starting from 2020.3 and currently in development and thus in experimental state.

## Declarations

Each [symbol](symbols.md) may be declared in zero or more places, for example:
- a JVM package is a symbol with several declarations (split packages);
- a C# partial class is a symbol with several declarations;
- a property key is a symbol possibly declared in several files simultaneously;
- a Java local variable is a symbol with a single declaration;
- and a file is a symbol without declarations; it has only references.

Declarations are implementations of
[`SymbolDeclaration`](upsource:///platform/core-api/src/com/intellij/model/SymbolDeclaration.java). 
Declarations in PSI elements are implementations of
[`PsiSymbolDeclaration`](upsource:///platform/core-api/src/com/intellij/model/psi/PsiSymbolDeclaration.java).

To report a declaration in a PSI element, either:
- implement and register 
  [`PsiSymbolDeclarationProvider`](upsource:///platform/core-api/src/com/intellij/model/psi/PsiSymbolDeclarationProvider.java);
- or implement `PsiSymbolDeclaration` directly in the `PsiElement`.


## References

References are implementations of 
[`SymbolReference`](upsource:///platform/core-api/src/com/intellij/model/SymbolReference.java) interface. 
References from PSI elements are implementations of 
[`PsiSymbolReference`](upsource:///platform/core-api/src/com/intellij/model/psi/PsiSymbolReference.java) interface.

The main method of `SymbolReference` is `resolveReference()`, which returns the collection of symbols to which the reference points, plus additional data.
If it is not possible to resolve the reference, for example, if it points to an undefined class, an empty collection gets returned.  
A counterpart to the `resolveReference()` method is `SymbolReference.resolvesTo()`, which checks if the reference resolves to the specified element. 
This method can be implemented to walk the tree only if the element's text is equal to the reference's text.

For convenience, if the reference can possibly be resolved:
- with a single result, then it might be extended from 
[`SingleResultReference`](upsource:///platform/core-api/src/com/intellij/model/SingleResultReference.java);
- to a single symbol without additional data, then it might be extended from 
[`SingleTargetReference`](upsource:///platform/core-api/src/com/intellij/model/SingleTargetReference.java);
- to multiple symbols without additional data, then 
[`SymbolResolveResult.fromSymbol()`](upsource:///platform/core-api/src/com/intellij/model/SymbolResolveResult.java) might be used.


### Own references

Own references are the references found in PSI elements, which are considered as references by the language.

**Example:**
PSI element representing `x` in `x * 2` Java expression has an Own reference to a local Java variable, e.g., `var x = 42`,
because this is a reference from Java language point of view, and Java language support uses it, e.g., for code analysis.

To provide Own references by the `PsiElement`, implement 
[`PsiElement.getOwnReferences()`](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java) in the `PsiElement`.
If the element contains a single reference, `Collections.singletonList()` can be used


### External References

External references are the references which are not considered as references by the host language. 
The language support should not rely on their existence/absence, because they might be contributed by other plugins.

**Example:**
PSI element representing `"users.txt"` in `new File("users.txt")` Java expression is a string literal from Java language point of view,
but there is a plugin which _knows_ that this literal references a file name, and provides such reference.

External references might be contributed to PSI elements
that implement [`PsiExternalReferenceHost`](upsource:///platform/core-api/src/com/intellij/model/psi/PsiExternalReferenceHost.java). 
To allow other plugins to contribute references of `PsiElement`, implement `PsiExternalReferenceHost` in the `PsiElement`. 
To contribute an External reference to the existing `PsiExternalReferenceHost`, implement and register 
[`PsiSymbolReferenceProvider`](upsource:///platform/core-api/src/com/intellij/model/psi/PsiSymbolReferenceProvider.java).


### Implicit References

Implicit references are the references which should be part of the mechanism to obtain a target by a reference, 
without the inverse ability to search or rename such references by a target.

**Example:**
`var` keyword in `var x = new Person()` Java declaration has an Implicit reference,
because it doesn't make sense to obtain the reference by the target class. At the same time it's possible:
- to navigate to the class by ctrl-clicking `var`;
- to start a refactoring (e.g., rename) from the class targeted by this reference;
- to view documentation of the class targeted by this reference.

To provide an Implicit reference, implement and register 
[`ImplicitReferenceProvider`](upsource:///platform/core-api/src/com/intellij/model/psi/ImplicitReferenceProvider.java).
