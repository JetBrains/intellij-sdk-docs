<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Navigation

<link-summary>Implementing navigation to resolved symbols or other PSI elements.</link-summary>
<primary-label ref="2020.3"/>

> This API is currently in development and thus in experimental state.
>
{style="warning"}

The <ui-path>Navigate | Declaration or Usages</ui-path> action is performed in several steps.

## Direct Navigation

Direct navigation is the navigation from `PsiElement` to another `PsiElement`,
such as navigation from `break` keyword to the end of a loop in Java, without showing any popups.

To provide `PsiElement` for direct navigation, implement and register
[`DirectNavigationProvider`](%gh-ic%/platform/core-api/src/com/intellij/navigation/DirectNavigationProvider.java)
in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.lang.directNavigationProvider"/></include>.

## Symbol Navigation

If there is no Direct navigation available under the caret, then the IntelliJ Platform proceeds with `Symbol` navigation.
In this step the IntelliJ Platform computes the navigation targets based on target symbols,
which it obtains by resolving a [reference](declarations_and_references.md#references).
If there are several target symbols or several navigation targets defined for a symbol,
then the IDE shows the navigation popup to ask the user to choose where to go.

The [`NavigationTarget`](%gh-ic%/platform/core-api/src/com/intellij/platform/backend/navigation/NavigationTarget.java)
is essentially a pair of a `Navigatable` and
a [`TargetPresentation`](%gh-ic%/platform/core-api/src/com/intellij/platform/backend/presentation/TargetPresentation.kt)
instances (where to go and what to show in the popup).

To provide navigation targets by a `Symbol`, either:
- implement and register
  [`SymbolNavigationProvider`](%gh-ic%/platform/core-api/src/com/intellij/navigation/SymbolNavigationProvider.java)
  in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.symbolNavigation"/></include>;
- or implement
  [`NavigatableSymbol`](%gh-ic%/platform/core-api/src/com/intellij/navigation/NavigatableSymbol.java)
  in the `Symbol`.

## Showing Usages

If there are no navigation targets available, then the IntelliJ Platform starts finding usages of the target symbol
obtained by resolving a [reference](declarations_and_references.md#references)
or from a [declaration](declarations_and_references.md#declarations).

> Existing implementations of the mentioned extension points can be found on the [IntelliJ Platform Explorer](explore_api.md#search-the-intellij-platform-explorer).
>
{style="note"}
