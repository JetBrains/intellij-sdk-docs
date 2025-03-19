<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Declarations and References
<primary-label ref="2020.3"/>

<link-summary>Overview of symbol declarations and references.</link-summary>

> This API is currently in development and thus in an experimental state.
>
{style="warning"}

## Declarations

Each [symbol](symbols.md) may be declared in zero or more places, for example:

- a C# partial class is a symbol with several declarations;
- a property key is a symbol possibly declared in several files simultaneously;
- a Java local variable is a symbol with a single declaration;
- and a file is a symbol without declarations; it has only references.

Declarations in PSI elements are implementations of
[`PsiSymbolDeclaration`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/PsiSymbolDeclaration.java).

To report a declaration in a PSI element, either:

- implement and register
  [`PsiSymbolDeclarationProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/PsiSymbolDeclarationProvider.java)
  in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.psi.declarationProvider"/></include>
- implement `PsiSymbolDeclaration` directly in the `PsiElement`.

## References

References from PSI elements are implementations of
[`PsiSymbolReference`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/PsiSymbolReference.java).

The main method of `PsiSymbolReference` is `resolveReference()`, which returns the collection of symbols to which the reference points,
plus additional data.
If it is not possible to resolve the reference, for example, if it points to an undefined class, an empty collection gets returned.
A counterpart to the `resolveReference()` method is `PsiSymbolReference.resolvesTo()`,
which checks if the reference resolves to the specified symbol.
This method can be implemented to walk the tree only if the element's text is equal to the reference's text.

For convenience, if the reference can possibly be resolved to a single symbol without additional data, then it can extend from
[`SingleTargetReference`](%gh-ic%/platform/core-api/src/com/intellij/model/SingleTargetReference.java).

### Own References

Own references are the references found in PSI elements, which are considered as references by the language.

**Example:**
PSI element representing `x` in `x * 2` Java expression has an Own reference to a local Java variable, e.g., `var x = 42`,
because this is a reference from Java language point of view, and Java language support uses it, e.g., for code analysis.

To provide Own references by the `PsiElement`, implement
[`PsiElement.getOwnReferences()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiElement.java) in the `PsiElement`.
For a single reference, use `Collections.singletonList()`.

### External References

External references are the references that are not considered as references by the host language.
The language support should not rely on their existence/absence, because they might be contributed by other plugins.

**Example:**
PSI element representing `"users.txt"` in `new File("users.txt")` Java expression is a string literal from Java language point of view,
but there is a plugin which _knows_ that this literal references a file name, and provides such a reference.

External references might be contributed to PSI elements
that implement [`PsiExternalReferenceHost`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/PsiExternalReferenceHost.java).
To allow other plugins to contribute references of `PsiElement`, implement `PsiExternalReferenceHost` in the `PsiElement`.
To contribute an External reference to the existing `PsiExternalReferenceHost`, implement and register
[`PsiSymbolReferenceProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/PsiSymbolReferenceProvider.java).

### Implicit References

Implicit references are the references that should be part of the mechanism to obtain a target by a reference,
without the inverse ability to search or rename such references by a target.

**Example:**
`var` keyword in `var x = new Person()` Java declaration has an Implicit reference, because it doesn't make sense to obtain the reference by the target class.

At the same time, it's possible:

- to navigate to the class by <shortcut>Ctrl-Click</shortcut> on `var`;
- to start a refactoring (e.g., rename) from the class targeted by this reference;
- to view documentation of the class targeted by this reference.

To provide an Implicit reference, implement and register
[`ImplicitReferenceProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/ImplicitReferenceProvider.java)
in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.psi.implicitReferenceProvider"/></include>.
