<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Go to Class and Go to Symbol

<link-summary>"Go to Class/Symbol" contributors allowing for quick searching and navigating to classes and symbols.</link-summary>

<tldr>

**Product Help:** [Searching Everywhere](https://www.jetbrains.com/help/idea/searching-everywhere.html)

</tldr>

A custom language plugin can provide its items to be included in the lists shown when the user chooses the <ui-path>Navigate | Class</ui-path> or <ui-path>Navigate | Symbol</ui-path> action.

Provide implementations of [`ChooseByNameContributorEx`](%gh-ic%/platform/lang-impl/src/com/intellij/navigation/ChooseByNameContributorEx.java) interface (separate implementations need to be provided for <control>Class</control> and <control>Symbol</control>, respectively), and register them
in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.gotoClassContributor"/></include> or <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.gotoSymbolContributor"/></include>.

Each `ChooseByNameContributorEx` implementation must provide the following methods:
* `processNames(@NotNull Processor<? super String> processor, @NotNull GlobalSearchScope scope, @Nullable IdFilter filter)`

  Feeds the processor with a complete list of names available in a specified scope, which the IDE will then filter according to the text typed by the user in the dialog.
  Using [](indexing_and_psi_stubs.md) to obtain matching candidates is highly recommended to improve performance.
* `processElementsWithName(String name, Processor<? super NavigationItem> processor, FindSymbolParameters parameters)`

  Feeds the processor with a list of [`NavigationItem`](%gh-ic%/platform/core-api/src/com/intellij/navigation/NavigationItem.java) instances (typically [`PsiElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiElement.java)) matching the given name and parameters.
  Processed `NavigationItem`s specify the destinations to jump to when a specific item is selected from the list.

**Example:**
- [Custom Language Support Tutorial: Go To Symbol Contributor](go_to_symbol_contributor.md)
