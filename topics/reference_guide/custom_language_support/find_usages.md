<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Find Usages

<link-summary>Introduction to implementing Find Usages action in custom languages.</link-summary>

<tldr>

**Product Help:** [Find Usages](https://www.jetbrains.com/help/idea/find-highlight-usages.html)

</tldr>

The _Find Usages_ action is a multi-step process, and each step of the process requires involvement from the custom language plugin.

The language plugin participates in the Find Usages process by registering an implementation of [`FindUsagesProvider`](%gh-ic%/platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java) in
the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.lang.findUsagesProvider"/></include>, and through the PSI implementation using [`PsiNamedElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiNamedElement.java) and [`PsiReference`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReference.java) interfaces.

> In cases like function parameters and local variables, consider overriding  [`PsiElement.getUseScope()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiElement.java) to return a narrower scope.
> For instance, returning the scope of the nearest function definition can significantly reduce the number of files that need to be parsed and references that need to be resolved when renaming such elements.

The steps of the _Find Usages_ action are the following:
* Before the _Find Usages_ action can be invoked, the IDE builds an index of words present in every file in the custom language.
  Using the [`WordsScanner`](%gh-ic%/platform/indexing-api/src/com/intellij/lang/cacheBuilder/WordsScanner.java) implementation returned from [`FindUsagesProvider.getWordsScanner()`](%gh-ic%/platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java), the contents of every file are loaded and passes it to the words scanner, along with the words consumer.
  The words scanner breaks the text into words, defines the context for each word (code, comments, or literals), and passes the word to the consumer.
  The simplest way to implement the words scanner is to use the [`DefaultWordsScanner`](%gh-ic%/platform/indexing-impl/src/com/intellij/lang/cacheBuilder/DefaultWordsScanner.java) implementation, passing to it the sets of lexer token types which are treated as identifiers, literals, and comments.
  The default words scanner will use the lexer to break the text into tokens and handle breaking the text of the comment and literal tokens into individual words.
* When the user invokes the _Find Usages_ action, the IDE locates the PSI element the references to be searched.
  The PSI element at the cursor (the direct tree parent of the token at the cursor position) must be either a [`PsiNamedElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiNamedElement.java) or a [`PsiReference`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReference.java) which resolves to a [`PsiNamedElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiNamedElement.java).
  The word cache will be used to search for the text returned from the [`PsiNamedElement.getName()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiNamedElement.java) method.
  Also, if the text range of the [`PsiNamedElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiNamedElement.java) includes some other text besides the identifier returned from `getName()` (for example, if the [`PsiNamedElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiNamedElement.java) represents a JavaScript function and its text range includes the "`function`" keyword in addition to the name of the function), the method `getTextOffset()` must be overridden for the [`PsiNamedElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiNamedElement.java), and must return the start offset of the name identifier within the text range of the element.
* Once the element is located, the IDE calls [`FindUsagesProvider.canFindUsagesFor()`](%gh-ic%/platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java) to ask the plugin if the _Find Usages_ action applies to the specific element.
* When showing the _Find Usages_ dialog to the user, [`FindUsagesProvider.getType()`](%gh-ic%/platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java) and [`FindUsagesProvider.getDescriptiveName()`](%gh-ic%/platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java) are called to determine how the element should be presented to the user.
* For every file containing the searched words, the IDE builds the PSI tree and recursively descends it.
  The text of each element is broken into words and then scanned.
  If the element was indexed as an identifier, every word is checked to be a [`PsiReference`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiReference.java) resolving to the element the usages of which are searched.
  If the element was indexed as a comment or literal and the search in comments or literals is enabled, it checks if the word is equal to the searched element's name.
* After the usages are collected, results are shown in the usages pane.
  The text shown for each found element is taken from the [`FindUsagesProvider.getNodeText()`](%gh-ic%/platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java) method.
  To group results by type, implement [`UsageTypeProvider`](%gh-ic%/platform/usageView-impl/src/com/intellij/usages/impl/rules/UsageTypeProvider.java) and register
  in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.usageTypeProvider"/></include> to provide custom or predefined [`UsageType`](%gh-ic%/platform/usageView/src/com/intellij/usages/impl/rules/UsageType.java).

**Examples:**
- Implementation of [`FindUsagesProvider`](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/findUsages/PropertiesFindUsagesProvider.java) in [Properties language plugin](%gh-ic%/plugins/properties)
- [Custom Language Support Tutorial: Find Usages](find_usages_provider.md)

### Grouping Results

To have the title of the found element be correctly displayed in the title of the Find Usages tool window, you need to provide an implementation of the [`ElementDescriptionProvider`](%gh-ic%/platform/core-api/src/com/intellij/psi/ElementDescriptionProvider.java) interface.
The [`ElementDescriptionLocation`](%gh-ic%/platform/core-api/src/com/intellij/psi/ElementDescriptionLocation.java) passed to the provider in this case will be an instance of [`UsageViewLongNameLocation`](%gh-ic%/platform/usageView/src/com/intellij/usageView/UsageViewLongNameLocation.java).

**Example:**
[`ElementDescriptionProvider`](%gh-ic%/plugins/properties/src/com/intellij/lang/properties/PropertiesDescriptionProvider.java) for [Properties language plugin](%gh-ic%/plugins/properties)
