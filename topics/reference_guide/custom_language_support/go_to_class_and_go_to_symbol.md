[//]: # (title: Go to Class and Go to Symbol)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

A custom language plugin can provide its items to be included in the lists shown when the user chooses the <menupath>Navigate | Class</menupath> or <menupath>Navigate | Symbol</menupath> action.

Provide implementations of [`ChooseByNameContributor`](upsource:///platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) interface (separate implementations need to be provided for <control>Class</control> and <control>Symbol</control>, respectively), and register them in the `com.intellij.gotoClassContributor` and `com.intellij.gotoSymbolContributor` extension points.

> Please consider implementing [`ChooseByNameContributorEx`](upsource:///platform/lang-impl/src/com/intellij/navigation/ChooseByNameContributorEx.java) for better performance.
>
{type="tip"}

Each contributor must return a complete list of names to show in the list for a specified project, which the IDE will then filter according to the text typed by the user in the dialog.
Using [File-based or Stub indices](indexing_and_psi_stubs.md) to obtain matching candidates is highly recommended to improve performance.

For each name in that list, the contributor needs to provide a list of [`NavigationItem`](upsource:///platform/core-api/src/com/intellij/navigation/NavigationItem.java) instances (typically [`PsiElement`](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java)), which specify the destinations to jump to when a specific item is selected from the list.

**Example:**
- [Custom Language Support Tutorial: Go To Symbol Contributor](go_to_symbol_contributor.md)
