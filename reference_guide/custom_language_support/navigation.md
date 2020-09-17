---
title: Navigation
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

> **WARNING** This API is available starting from 2020.3 and currently in development and thus in experimental state.

The _Go to Declaration or Usages_ action is performed in several steps.

## Direct Navigation

Direct navigation is the navigation from `PsiElement` to another `PsiElement`, 
such as navigation from `break` keyword to the end of a loop in Java, without showing any popups.

To provide `PsiElement` for direct navigation, implement and register 
[`DirectNavigationProvider`](upsource:///platform/core-api/src/com/intellij/navigation/DirectNavigationProvider.java).


## Symbol Navigation

If there is no direct navigation available under the caret, then the platform proceeds with `Symbol` navigation.

After obtaining the target symbols by resolving a [reference](declarations_and_references.md#references), 
the IntelliJ Platform computes the navigation targets. 
If there are several target symbols or several navigation targets defined for a symbol, 
then the IDE shows the navigation popup to ask the user to choose where to go.

The [`NavigationTarget`](upsource:///platform/core-api/src/com/intellij/navigation/NavigationTarget.java)
is essentially a pair of a `Navigatable` and 
a [`TargetPopupPresentation`](upsource:///platform/core-api/src/com/intellij/navigation/TargetPopupPresentation.java) 
instances (where to go and what to show in the popup).

To provide navigation targets by a `Symbol`, either:
- implement and register 
  [`SymbolNavigationProvider`](upsource:///platform/core-api/src/com/intellij/navigation/SymbolNavigationProvider.java);
- or implement 
  [`NavigatableSymbol`](upsource:///platform/core-api/src/com/intellij/navigation/NavigatableSymbol.java)
  in the `Symbol`.


## Showing Usages

If there are no navigation targets available, then the IntelliJ Platform starts finding usages of the target symbol 
obtained by resolving a [reference](declarations_and_references.md#references) 
or from a [declaration](declarations_and_references.md#declarations). 
