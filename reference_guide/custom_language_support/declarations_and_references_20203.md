---
title: Declarations and References
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

> **WARNING** This API is currently in development and thus in experimental state

## Declarations

Each symbol may be declared in zero or more places, for example:
- JVM package is a symbol with several declarations (split packages);
- C# partial class is a symbol with several declarations;
- property key is a symbol declared in several files simultaneously;
- Java local variable is a symbol with a single declaration;
- file is a symbol without declarations, it has only references.

Declarations are implementations of
[`SymbolDeclaration`](upsource:///platform/core-api/src/com/intellij/model/SymbolDeclaration.java). 
Declarations in PSI elements are implementations of
[`PsiSymbolDeclaration`](upsource:///platform/core-api/src/com/intellij/model/psi/PsiSymbolDeclaration.java).

To report a declaration in a PSI element either:
- implement and register 
  [`PsiSymbolDeclarationProvider`](upsource:///platform/core-api/src/com/intellij/model/psi/PsiSymbolDeclarationProvider.java);
- implement `PsiSymbolDeclaration` directly in the `PsiElement`.


## References

References are implementations of 
[`SymbolReference`](upsource:///platform/core-api/src/com/intellij/model/SymbolReference.java) interface. 
References from PSI elements are implementations of 
[`PsiSymbolReference`](upsource:///platform/core-api/src/com/intellij/model/psi/PsiSymbolReference.java) interface.

The main method of the `SymbolReference` interface is `resolveReference()`, which returns the collection of results 
(symbols to which the reference points plus additional data), or an empty collection if it was not possible to resolve the reference 
(for example, should it point to an undefined class). A counterpart to the `resolveReference()` method is `resolvesTo()`, 
which checks if the reference resolves to the specified element. The `resolvesTo()` method can be implemented 
to perform the tree walk only if the element text is equal to the text of the reference.

For convenience, if the reference can possibly be resolved:
- with a single result, then it might be extended from 
[`SingleResultReference`](upsource:///platform/core-api/src/com/intellij/model/SingleResultReference.java);
- to a single symbol without additional data, then it might be extended from 
[`SingleTargetReference`](upsource:///platform/core-api/src/com/intellij/model/SingleTargetReference.java);
- to multiple symbols without additional data, then 
[`SymbolResolveResult#fromSymbol()`](upsource:///platform/core-api/src/com/intellij/model/SymbolResolveResult.java) might be used.


### Own references

Own references are the references found in PSI elements which are considered as references by the language.

To provide own references by the `PsiElement` implement 
[`PsiElement#getOwnReferences()`](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java) in the `PsiElement`.
If the element contains a single reference the `Collections#singletonList()` may be used.

**Example:**
PSI element representing `x` in `x * 2` Java expression has an own reference to a local Java variable, e.g. `var x = 42`, 
because this is a reference from Java language point of view, and Java language support uses it e.g. for code analysis.


### External references

External references are the references which are not considered as references by the host language. 
The language support should not rely on their existence/absence, because they might be contributed by other plugins.

External references might be contributed to PSI elements
which implement [`PsiExternalReferenceHost`](upsource:///platform/core-api/src/com/intellij/model/psi/PsiExternalReferenceHost.java). 
To allow other plugins to contribute the references op the `PsiElement` implement `PsiExternalReferenceHost` in the `PsiElement`. 
To contribute an external reference to the existing `PsiExternalReferenceHost` implement and register 
[`PsiSymbolReferenceProvider`](upsource:///platform/core-api/src/com/intellij/model/psi/PsiSymbolReferenceProvider.java).

**Example:**
PSI element representing `"users.txt"` in `new File("users.txt")` Java expression is a string literal from Java language point of view, 
but there is a plugin which _knows_ that this literal references a file name, and provides such reference.


### Implicit references

Implicit references are the references which should be part of the mechanism to obtain a target by a reference, 
without the inverse ability to search or rename such references by a target.

To provide an implicit reference implement and register 
[`ImplicitReferenceProvider`](upsource:///platform/core-api/src/com/intellij/model/psi/ImplicitReferenceProvider.java).

**Example:**
`var` keyword in `var x = new Person()` Java declaration has an implicit reference, 
because it doesn't make sense to obtain the reference by the target class. At the same time it's possible:  
- to navigate to the class by ctrl-clicking `var`; 
- to start a refactoring (e.g. rename) from the class targeted by this reference;
- to view documentation of the class targeted by this reference.
