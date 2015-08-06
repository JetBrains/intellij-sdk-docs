---
title: Find Usages
---

The ```Find Usages``` action is a multi-step process, and each step of the process requires involvement from the custom language plugin.
The language plugin participates in the Find Usages process by registering an implementation of
[FindUsagesProvider](https://github.com/JetBrains/intellij-community/blob/master/platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java)
in the `com.intellij.lang.findUsagesProvider` extension point, and through the PSI implementation using
[PsiNamedElement](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiNamedElement.java)
and
[PsiReference](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiReference.java)
interfaces.

**Example**:
Implementation of
[FindUsagesProvider](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/findUsages/PropertiesFindUsagesProvider.java)
in
[Properties language plugin](https://github.com/JetBrains/intellij-community/tree/master/plugins/properties/)


The steps of the ```Find Usages``` action are the following:

*  Before the ```Find Usages``` action can be invoked, the IDE builds an index of words present in every file in the custom language.
   Using the
   [WordsScanner](https://github.com/JetBrains/intellij-community/blob/master/platform/indexing-api/src/com/intellij/lang/cacheBuilder/WordsScanner.java)
   implementation returned from
   [FindUsagesProvider.getWordsScanner()](https://github.com/JetBrains/intellij-community/blob/master/platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java),
   the contents of every file are loaded and passes it to the words scanner, along with the words consumer.
   The words scanner breaks the text into words, defines the context for each word (code, comments or literals) and passes the word to the consumer.
   The simplest way to implement the words scanner is to use the
   [DefaultWordsScanner](https://github.com/JetBrains/intellij-community/blob/master/platform/indexing-api/src/com/intellij/lang/cacheBuilder/DefaultWordsScanner.java)
   implementation, passing to it the sets of lexer token types which are treated as identifiers, literals and comments.
   The default words scanner will use the lexer to break the text into tokens, and will handle breaking the text of comment and literal tokens into individual words.

*  When the user invokes the Find Usages action, the IDE locates the PSI element the references to which will be searched.
   The PSI element at the cursor (the direct tree parent of the token at the cursor position) must be either a
   [PsiNamedElement](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiNamedElement.java)
   or a
   [PsiReference](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiReference.java)
   which resolves to a
   [PsiNamedElement](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiNamedElement.java).
   The word cache will be used to search for the text returned from the
   [PsiNamedElement.getName()](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiNamedElement.java)
   method.
   Also, if the text range of the
   [PsiNamedElement](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiNamedElement.java)
   includes some other text besides the identifier returned from `getName()` (for example, if the
   [PsiNamedElement](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiNamedElement.java)
   represents a JavaScript function and its text range includes the "`function`" keyword in addition to the name of the function), the method `getTextOffset()` must be overridden for the
   [PsiNamedElement](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiNamedElement.java),
   and must return the start offset of the name identifier within the text range of the element.

*  Once the element is located, the IDE calls
   [FindUsagesProvider.canFindUsagesFor()](https://github.com/JetBrains/intellij-community/blob/master/platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java)
   to ask the plugin if the Find Usages action is applicable to the specific element.

*  When showing the Find Usages dialog to the user,
   [FindUsagesProvider.getType()](https://github.com/JetBrains/intellij-community/blob/master/platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java)
   and
   [FindUsagesProvider.getDescriptiveName()](https://github.com/JetBrains/intellij-community/blob/master/platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java)
   are called to determine how the element should be presented to the user.

*  For every file containing the searched words, the IDE builds the PSI tree and recursively descends that tree.
   The text of each element is broken into words and then scanned.
   If the element was indexed as an identifier, every word is checked to be a
   [PsiReference](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiReference.java)
   resolving to the element the usages of which are searched.
   If the element was indexed as a comment or literal and the search in comments or literals is enabled, it checks if the word is equal to the name of the searched element.

*  After the usages are collected, results are shown in the usages pane.
The text shown for each found element is taken from the
[FindUsagesProvider.getNodeText()](https://github.com/JetBrains/intellij-community/blob/master/platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java)
method.

To have the title of the found element be correctly displayed in the title of the Find Usages toolwindow, you need to provide an implementation of the
[ElementDescriptionProvider](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/ElementDescriptionProvider.java)
interface.
The
[ElementDescriptionLocation](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/ElementDescriptionLocation.java)
passed to the provider in this case will be an instance of
[UsageViewLongNameLocation](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-impl/src/com/intellij/usageView/UsageViewLongNameLocation.java).

**Example:**
[ElementDescriptionProvider](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/com/intellij/lang/properties/PropertiesDescriptionProvider.java)
for
[Properties language plugin](https://github.com/JetBrains/intellij-community/tree/master/plugins/properties/)
