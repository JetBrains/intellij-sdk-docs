---
layout: editable
title: Custom Language Support
---


*IntelliJ Platform* is a powerful platform for building development tools targeting *any* language.
Most of IDE features consist of language-independent and language-specific parts, and you can support a particular feature for your language with a small amount of effort:
you just need to implement the language-specific part, and the language-independent part is provided for you by the platform.

This part of the documentation will explain the main concepts of the *Language API* and will guide you through the sequence of steps which are usually required to develop a custom language plugin.
You can obtain additional information about the *Language API* from the JavaDoc comments for the *Language API* classes and from the source code of the Properties language support, which is part of the
[IntelliJ IDEA Community Edition](https://github.com/JetBrains/intellij-community)
source code.


If you prefer a full example to the detailed description offered on this page, please check out a step-by-step tutorial how to define custom language support on example of ".properties" files:
[Custom Language Support Tutorial](cls_tutorial.html)

Providing custom language support includes the following major steps:

* [Registering File Type](registering_file_type.html)
* [Implementing Lexer](implementing_lexer.html)
* [Implementing Parser and PSI](implementing_parser_and_psi.html)
* [Syntax Highlighting and Error Highlighting](syntax_highlighting_and_error_highlighting.html)
* [References and Resolve](references_and_resolve.html)
* [Code Completion](code_completion.html)
* [Find Usages](find_usages.html)
* [Rename Refactoring](rename_refactoring.html)
* [Safe Delete Refactoring](safe_delete_refactoring.html)
* [Code Formatter](code_formatting.html)


## Code Inspections and Intentions

The code inspections for custom languages use the same API as all other code inspections, based on the
[LocalInspectionTool](https://github.com/JetBrains/intellij-community/blob/master/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java)
class.

The functionality of
[LocalInspectionTool](https://github.com/JetBrains/intellij-community/blob/master/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java)
partially duplicates that of
[Annotator](https://github.com/JetBrains/intellij-community/blob/master/platform/analysis-api/src/com/intellij/lang/annotation/Annotator.java).
The main differences are that
[LocalInspectionTool](https://github.com/JetBrains/intellij-community/blob/master/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionTool.java)
supports batch analysis of code (through the **Analyze \| Inspect Code...** action), the possibility to turn off the inspection (globally or by suppressing them on various levels) and to configure the inspection options.
If none of that is required and the analysis only needs to run in the active editor,
[Annotator](https://github.com/JetBrains/intellij-community/blob/master/platform/analysis-api/src/com/intellij/lang/annotation/Annotator.java)
provides better performance (because of its support for incremental analysis) and more flexibility for highlighting errors.

**Example**:
A
[simple inspection](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/codeInspection/TrailingSpacesInPropertyInspection.java)
for
[Properties language plugin](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/)


The code intentions for custom languages also use the regular API for intentions.
The intention classes need to implement the
[IntentionAction](https://github.com/JetBrains/intellij-community/blob/master/platform/analysis-api/src/com/intellij/codeInsight/intention/IntentionAction.java)
interface and to be registered using the `<intentionAction>` bean in your *plugin.xml*.

**Example:**
A
[simple intention action](https://github.com/JetBrains/intellij-community/blob/master/plugins/groovy/src/org/jetbrains/plugins/groovy/intentions/control/SplitIfIntention.java)
for Groovy




## Structure View

The Structure View implementation used for a specific file type can be customized on many levels.
If a custom language plugin provides an implementation of the
[StructureView](https://github.com/JetBrains/intellij-community/blob/master/platform/structure-view-api/src/com/intellij/ide/structureView/StructureView.java)
interface, it can completely replace the standard structure view implementation with a custom user interface component.
However, for most languages this is not necessary, and the standard
[StructureView](https://github.com/JetBrains/intellij-community/blob/master/platform/structure-view-api/src/com/intellij/ide/structureView/StructureView.java)
implementation provided by *IntelliJ Platform* can be reused.

The starting point for the structure view is the
[PsiStructureViewFactory](https://github.com/JetBrains/intellij-community/blob/master/platform/structure-view-api/src/com/intellij/lang/PsiStructureViewFactory.java)
interface, which is registered in the `com.intellij.lang.psiStructureViewFactory` extension point.

**Example:**
[PsiStructureViewFactory](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/com/intellij/lang/properties/structureView/PropertiesStructureViewBuilderFactory.java)
for .properties files




To reuse the *IntelliJ Platform* implementation of the
[StructureView](https://github.com/JetBrains/intellij-community/blob/master/platform/structure-view-api/src/com/intellij/ide/structureView/StructureView.java),
the plugin returns a
[TreeBasedStructureViewBuilder](https://github.com/JetBrains/intellij-community/blob/master/platform/structure-view-api/src/com/intellij/ide/structureView/TreeBasedStructureViewBuilder.java)
from its
[PsiStructureViewFactory.getStructureViewBuilder()](https://github.com/JetBrains/intellij-community/blob/master/platform/structure-view-api/src/com/intellij/lang/PsiStructureViewFactory.java#L35)
method.
As the model for the builder, the plugin can specify a subclass of
[TextEditorBasedStructureViewModel](https://github.com/JetBrains/intellij-community/blob/master/platform/structure-view-api/src/com/intellij/ide/structureView/TextEditorBasedStructureViewModel.java),
and by overriding methods of this subclass it customizes the structure view for a specific language.

**Example**:
[StructureViewModel](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/structureView/PropertiesFileStructureViewModel.java)
for .properties files


The main method to override is `getRoot()`, which returns the instance of a class implementing the
[StructureViewTreeElement](https://github.com/JetBrains/intellij-community/blob/master/platform/structure-view-api/src/com/intellij/ide/structureView/StructureViewTreeElement.java)
interface.
There exists no  standard implementation of this interface, so a plugin will need to implement it completely.

The structure view tree is usually built as a partial mirror of the PSI tree.
In the implementation of
`StructureViewTreeElement.getChildren()`,
the plugin can specify which of the child elements of a specific PSI tree node need to be represented as elements in the structure view. 
Another important method is `getPresentation()`, which can be used to customize the text, attributes and icon used to represent an element in the structure view.

The implementation of `StructureViewTreeElement.getChildren()` needs to be matched by `TextEditorBasedStructureViewModel.getSuitableClasses()`. 
The latter method returns an array of `PsiElement`\-derived classes which can be shown as structure view elements, and is used to select the Structure View item matching the cursor position when the structure view is first opened or when the ```Autoscroll from source``` option is used.

**Example:** 
[StructureViewElement](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/structureView/PropertiesStructureViewElement.java) 
for a 
[Properties language plugin](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/)




## Surround With

In order to support the ```Code | Surround With...``` action, the plugin needs to register one or more implementations of the 
[SurroundDescriptor](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/lang/surroundWith/SurroundDescriptor.java) 
interface in the `com.intellij.lang.surroundDescriptor` extension point. 
Each of the surround descriptors defines a possible type of code fragment which can be surrounded - for example, one surround descriptor can handle surrounding expressions, and another can handle statements. 
Each surround descriptor, in turn, contains an array of 
[Surrounder](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/lang/surroundWith/Surrounder.java) 
objects, defining specific templates which can be used for surrounding the selected code fragment (for example, ```Surround With if```, ```Surround With for``` and so on).

When the ```Surround With...``` action is invoked, the IDE queries all surround descriptors for the language until it finds one that returns a non-empty array from its `getElementsToSurround()` method. 
Then it calls the 
[Surrounder.isApplicable()](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/lang/surroundWith/Surrounder.java#L46) 
method for each surrounder in that descriptor to check if the specific template is applicable in the current context. 
Once the user selects a specific surrounder from the popup menu, the 
[Surrounder.surroundElements()](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/lang/surroundWith/Surrounder.java#L57)
method is used to execute the surround action.

## Go to Class and Go to Symbol

A custom language plugin can provide its own items to be included in the lists shown when the user chooses the ```Go to | Class...``` or ```Go to | Symbol...``` action. 
In order to do so, the plugin must provide implementations for the 
[ChooseByNameContributor](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/navigation/ChooseByNameContributor.java) 
interface (separate implementations need to be provided for ```Go to Class``` and ```Go to Symbol```), and register them in the `com.intellij.gotoClassContributor` and `com.intellij.gotoSymbolContributor` extension points.

Each contributor needs to be able to return a complete list of names to show in the list for a specified project, which will then be filtered by the IDE according to the text typed by the user in the dialog. 
For each name in that list, the contributor needs to provide a list of 
[NavigationItem](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/navigation/NavigationItem.java) 
instances (typically 
[PsiElement](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiElement.java)
), which specify the destinations to jump to when a specific name is selected from the list.

## Documentation

To provide different kinds of documentation support (tooltips on **Ctrl-hover**, quick documentation popup etc.), the plugin needs to provide an implementation of the 
[DocumentationProvider](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/lang/documentation/DocumentationProvider.java) 
interface and register it in the `lang.documentationProvider` extension point. 
A standard base class for such implementations is available in the class 
[AbstractDocumentationProvider](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/lang/documentation/AbstractDocumentationProvider.java).

**Example**: 
[DocumentationProvider](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/com/intellij/lang/properties/PropertiesDocumentationProvider.java) 
for 
[Properties language plugin](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/)


The `getQuickNavigateInfo()` method returns the text to be displayed when the user holds the mouse over an element with ```Ctrl``` pressed.


## Additional Minor Features

In order to implement *brace matching*, once the syntax highlighting lexer has been implemented, all that is required is to implement the 
[PairedBraceMatcher](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/lang/PairedBraceMatcher.java) 
interface and to return an array of brace pairs (
[BracePair](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/lang/BracePair.java)
) for the language. 
Each brace pair specifies the characters for the opening and closing braces and the lexer token types for these characters. 
(In principle, it is possible to return multi-character tokens, like "begin" and "end", as the start and end tokens of a brace pair. 
The IDE will match such braces, but the highlighting for such braces will not be fully correct.)

Certain types of braces can be marked as structural. 
Structural braces have higher priority than regular braces: they are matched with each other even if there are unmatched braces of other types between them, and an opening non-structural braces is not matched with a closing one if one of them is inside a pair of matched structural braces and another is outside.

The *code folding* is controlled by the plugin through the 
[FoldingBuilder](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lang/folding/FoldingBuilder.java) 
interface. 
The interface returns the list of text ranges which are foldable (as an array of 
[FoldingDescriptor](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lang/folding/FoldingDescriptor.java) 
objects), the replacement text which is shown for each range when it is folded, and the default state of each folding region (folded or unfolded).

The *Comment Code* feature is controlled through the 
[Commenter](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lang/Commenter.java) 
interface. 
The interface can return the prefix for the line comment, and the prefix and suffix for the block comment, if such features are supported by the language.

**Example:** 
[Commenter](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/PropertiesCommenter.java) 
for [Properties language plugin](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/)


To support smart/semantic *Join Lines* see
[JoinLinesHandlerDelegate](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/codeInsight/editorActions/JoinLinesHandlerDelegate.java).

*Smart Enter* (e.g. autocomplete missing semicolon/parentheses) can be provided via 
[SmartEnterProcessor](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/codeInsight/editorActions/smartEnter/SmartEnterProcessor.java).

*Naming suggestions* for Rename Refactoring can be provided via 
[NameSuggestionProvider](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/refactoring/rename/NameSuggestionProvider.java).

*Semantic highlight usages* (e.g. exit points) can be achieved using 
[HighlightUsagesHandlerFactory](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-impl/src/com/intellij/codeInsight/highlighting/HighlightUsagesHandlerFactory.java).

*View\|Parameter Info* is provided via 
[ParameterInfoHandler](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/lang/parameterInfo/ParameterInfoHandler.java) 
(extension point `codeInsight.parameterInfo`).

The *To Do view* is supported automatically if the plugin provides a correct implementation of the 
[ParserDefinition.getCommentTokens()](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lang/ParserDefinition.java#L79) 
method.

The *View \| Context Info* feature is supported for custom languages since IntelliJ IDEA 10.5. 
In order for it to work, you need to have a structure view implementation based on a 
[TreeBasedStructureViewBuilder](https://github.com/JetBrains/intellij-community/blob/master/platform/structure-view-api/src/com/intellij/ide/structureView/TreeBasedStructureViewBuilder.java), 
and additionally to provide an implementation of 
[DeclarationRangeHandler](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/codeInsight/hint/DeclarationRangeHandler.java) 
for your language and to register it in the `declarationRangeHandler` extension point.

*Spellchecking* can be provided via EP `spellchecker.support` (
[SpellcheckingStrategy](https://github.com/JetBrains/intellij-community/spellchecker/src/com/intellij/spellchecker/tokenizer/SpellcheckingStrategy.java)
) where you can return 
[Tokenizer](https://github.com/JetBrains/intellij-community/spellchecker/src/com/intellij/spellchecker/tokenizer/Tokenizer.java) 
to use, possibly depending on the passed in 
[PsiElement](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiElement.java) 
(or `EMPTY_TOKENIZER` for no spellchecking).

New in 13: user-configurable *reference injections* can be provided via `referenceInjector` extension point (
[ReferenceInjector](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/psi/injection/ReferenceInjector.java)
) (IntelliLang plugin required).

Please ask questions or suggest missing topics in [plugin development forum](http://devnet.jetbrains.com/community/idea/open_api_and_plugin_development).