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


## Rename Refactoring

The operation of the Rename refactoring is quite similar to that of Find Usages.
It uses the same rules for locating the element to be renamed, and the same index of words for locating the files which may have references to the element being renamed.

When the rename refactoring is performed, the method
[PsiNamedElement.setName()](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiNamedElement.java)
is called for the renamed element, and
[PsiReference.handleElementRename()](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiReference.java)
is called for all references to the renamed element.
Both of these methods perform basically the same action: replace the underlying AST node of the PSI element with the node containing the new text entered by the user.
Creating a fully correct AST node from scratch is quite difficult.
Thus, surprisingly, the easiest way to get the replacement node is to create a dummy file in the custom language so that it would contain the necessary node in its parse tree, build the parse tree and extract the necessary node from it.

**Example:**
[setName()](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/psi/impl/PropertyImpl.java#L58)
implementation for a
[Properties language plugin](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/)


Another interface related to the Rename refactoring is
[NamesValidator](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/lang/refactoring/NamesValidator.java).
This interface allows a plugin to check if the name entered by the user in the ```Rename``` dialog is a valid identifier (and not a keyword) according to the custom language rules.
If an implementation of this interface is not provided by the plugin, Java rules for validating identifiers are used.
Implementations of
[NamesValidator](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/lang/refactoring/NamesValidator.java)
are registered in the `com.intellij.lang.namesValidator` extension point.

**Example**:
[NamesValidator](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/com/intellij/lang/properties/PropertiesNamesValidator.java)
for
[Properties language plugin](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/)


Further customization of the Rename refactoring processing is possible on multiple levels.
Providing a custom implementation of the
[RenameHandler](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/refactoring/rename/RenameHandler.java)
interface allows you to entirely replace the UI and workflow of the rename refactoring, and also to support renaming something which is not a
[PsiElement](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiElement.java)
at all.

**Example**:
[RenameHandler](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/com/intellij/lang/properties/refactoring/rename/ResourceBundleFromEditorRenameHandler.java)
for renaming a resource bundle


If you're fine with the standard UI but need to extend the default logic of renaming, you can provide an implementation of the
[RenamePsiElementProcessor](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-impl/src/com/intellij/refactoring/rename/RenamePsiElementProcessor.java)
interface.
This allows you to:

*  Rename an element different from the one on which the action was invoked (a super method, for example)

*  Rename multiple elements at once (if their names are linked according to the logic of your language)

*  Check for name conflicts (existing names etc.)

*  Customize how search for code references or text references is performed

*  etc.

**Example**:
[RenamePsiElementProcessor](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/com/intellij/lang/properties/refactoring/rename/RenamePropertyProcessor.java)
for renaming a property in
[Properties plugin language](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/)


## Safe Delete Refactoring

The ```Safe Delete``` refactoring also builds on the same ```Find Usages``` framework as ```Rename```.
In addition to that, in order to support ```Safe Delete```, a plugin needs to implement two things:

*  The
   [RefactoringSupportProvider](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/lang/refactoring/RefactoringSupportProvider.java)
   interface, registered in the `com.intellij.lang.refactoringSupport` extension point, and the `isSafeDeleteAvailable()` method, which checks if the ```Safe Delete``` refactoring is available for a specific PSI element

*  The
   [PsiElement.delete()](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiElement.java#L371)
   method for the
   [PsiElement](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/psi/PsiElement.java)
   subclasses for which ```Safe Delete``` is available.
   Deleting PSI elements is implemented by deleting the underlying AST nodes from the AST tree (which, in turn, causes the text ranges corresponding to the AST nodes to be deleted from the document).

**Example:**
[delete()](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/psi/impl/PropertyImpl.java#L363)
implementation for a
[Property language plugin](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/)




If needed, it's possible to further customize how Safe Delete is performed for a particular type of element (how references are searched, etc).
This is done by implementing the `SafeDeleteProcessorDelegate` interface.

**Example**:
[SafeDeleteProcessorDelegate](https://github.com/JetBrains/intellij-community/blob/master/plugins/properties/src/com/intellij/lang/properties/refactoring/PropertiesFilesSafeDeleteProcessor.java)
implementation for a .properties file




## Code Formatter

The IntelliJ Platform includes a powerful framework for implementing custom language formatters.
In this framework, the plugin specifies the *constraints* on the spacing between different syntax elements, and the formatting engine, provided by the IDE, calculates the smallest number of whitespace modifications that need to be performed on the file to make it match the constraints.

The process of formatting a file or a file fragment consists of the following main steps:

*  The _formatting model builder_ (
   [FormattingModelBuilder](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/formatting/FormattingModelBuilder.java)
   ), implemented by the plugin, provides a formatting model (
   [FormattingModel](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/formatting/FormattingModel.java)
   ) for the document to be formatted

*  The formatting model is requested to build the structure of the file as applies to formatting, as a tree of _blocks_ (
   [Block](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/formatting/Block.java)
   ) with associated indent, wrap, alignment and spacing settings.

*  The formatting engine calculates the sequence of whitespace characters (spaces, tabs and/or line breaks) that needs to be placed at every block boundary, based on the formatting model provided by the plugin.

*  The formatting model is requested to insert the calculated whitespace characters at necessary positions in the file.

The structure of blocks is usually built in such a way that it mirrors the PSI structure of the file - for example, in Java code, the top-level formatting block covers the entire file, its children cover individual classes in the file, blocks on the next level cover methods inside classes, and so on. The formatter modifies only the characters between blocks, and the tree of blocks must be built in such a way that the bottom-level blocks cover all non-whitespace characters in the file: otherwise the characters between blocks may be deleted by the formatter.

If the formatting operation does not affect the entire file (for example, if the formatter is called to format the pasted block of text), a complete tree of blocks is not built - rather, only blocks for the text range covered by the formatting operation and their parents are built.

For every block, the plugin specifies the following properties:

*  The _spacing_ (
   [Spacing](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/formatting/Spacing.java)
   ) specifies what spaces or line breaks are inserted between the specified children of the block.
   The spacing object specifies the minimum and maximum number of spaces that must be placed between the specified child blocks, the minimum number of line breaks to place there, and whether the existing line breaks and blank lines should be preserved.
   The formatting model can also specify that the spacing between the specified blocks may not be modified by the formatter.

*  The _indent_ specifies how the block is indented relative to its parent block.
   There are different modes of indenting defined by factory methods in the Indent class.
   The most commonly used are the none indent (which means the child block is not indented), the regular indent (the child block is indented by the number of spaces specified in the **Project Code Style \| General \| Indent** setting) and the continuation indent (based on **Project Code Style \| General \| Continuation Indent** setting).
   If the formatting model does not specify an indent, the "continuation without first" mode is used, which means that the first block in a sequence of blocks with that type is not indented and the following blocks are indented with a continuation indent.

*  The _wrap_ (
   [Wrap](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/formatting/Wrap.java)
   ) specifies whether the content of the block is wrapped to the next line.
   Wrapping is performed by inserting a line break before the block content.
   The plugin can specify that a particular block is never wrapped, always wrapped, or wrapped only if it exceeds the right margin.

*  The _alignment_ (
   [Alignment](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/formatting/Alignment.java)
   ) specifies which blocks should be aligned with each other.
   If two blocks with the alignment property set to the same object instance are placed in different lines, and if the second block is the first non-whitespace block in its line, the formatter inserts white spaces before the second block so that it starts from the same column as the first one.

For each of these properties, a number of special use settings exists, which are described in the JavaDoc comments for the respective classes.
See also
[SpacingBuilder](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/formatting/SpacingBuilder.java)
which aids in building rule-based configuration.

An important special case in using the formatter is the smart indent performed when the user presses the ```Enter``` key in a source code file.
To determine the indent for the new line, the formatter engine calls the method `getChildAttributes()` on either the block immediately before the caret or the parent of that block, depending on the return value of the `isIncomplete()` method for the block before the caret.
If the block before the cursor is incomplete (contains elements that the user will probably type but has not yet typed, like a closing parenthesis of the parameter list or the trailing semicolon of a statement), `getChildAttributes()` is called on the block before the caret; otherwise, it's called on the parent block.

**New in IntelliJ IDEA 13**:
Code formatting can be suppressed per region via [special comments](http://youtrack.jetbrains.com/issue/IDEA-56995#comment=27-605969).

### Code Style Settings

To specify the default indent size for the language provided by your plugin, and to allow the user to configure the tab size and indent size you need to implement the
[FileTypeIndentOptionsProvider](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/psi/codeStyle/FileTypeIndentOptionsProvider.java)
interface and to register the implementation in the `fileTypeIndentOptionsProvider` extension point.
The return value of `createIndentOptions()` determines the default indent size.

### Rearranger

**New in 12:**

Allows custom languages to provide user-configurable arrangement/grouping rules for element types supported by language plugin.
Rules can be refined via modifiers and name, ordering can be applied additionally.
Please see
[Rearranger](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/psi/codeStyle/arrangement/Rearranger.java)
and related for JavaDoc.

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