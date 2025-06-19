<!-- Copyright 2000-2025 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

# Additional Minor Features

<link-summary>Additional minor features for custom languages.</link-summary>

A number of commonly used minor features are listed in the following format:

_EP: `fully.qualified.extensionPointName`_ — Extension Point Name (must be specified in <path>[plugin.xml](plugin_configuration_file.md)</path>). Click to browse usages in existing implementations of open-source plugins.

_`com.extensionPoint.class`_ _description text_ — Extension Point class/interface to provide functionality

> See also [](intellij_platform_extension_point_list.md#langextensionpointsxml) to discover more Language-related Extension Points
> as well as the general guide [](explore_api.md).
>
{title="Locating more Language EPs"}

## Editing

### Brace Matching

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.braceMatcher"/></include>

[`PairedBraceMatcher`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/PairedBraceMatcher.java)
returns an array of brace pairs ([`BracePair`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/BracePair.java)) specifying the characters for the opening and closing braces and the lexer token types for these characters.
(In principle, it is possible to return multi-character tokens, like "begin" and "end", as the start and end tokens of a brace pair.
The IDE will match such braces, but the highlighting for such braces will not be entirely correct.)

Certain types of braces can be marked as structural.
Structural braces have higher priority than regular braces: they are matched with each other even if there are unmatched braces of different types between them.
An opening non-structural brace is not matched with a closing one if one of them is inside a pair of matched structural braces and another is outside.
See also [](#recognizing-complex-multi-block-expressions).

#### "Heavy" Brace Matching

<primary-label ref="2022.3"/>

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.heavyBracesHighlighter"/></include>

If the brace matching is "too heavy" and should not be executed in [EDT](threading_model.md), implement
[`HeavyBraceHighlighter`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/HeavyBraceHighlighter.java).

### Quote Handling

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.quoteHandler"/></include>

To support the <control>Insert pair quote</control> feature, provide [`QuoteHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/QuoteHandler.java).
In most cases, [`SimpleTokenSetQuoteHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/SimpleTokenSetQuoteHandler.java) base implementation will be suitable.

### Comment Code

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.commenter"/></include>

[`Commenter`](%gh-ic%/platform/core-api/src/com/intellij/lang/Commenter.java) returns the prefix for the line comment, and the prefix and suffix for the block comment if supported by the language.
For more complex logic, use [`SelfManagingCommenter`](%gh-ic%/platform/core-api/src/com/intellij/codeInsight/generation/SelfManagingCommenter.java).

- [Custom Language Support Tutorial: Commenter](commenter.md)

### Code Folding

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.foldingBuilder"/></include>

[`FoldingBuilder`](%gh-ic%/platform/core-api/src/com/intellij/lang/folding/FoldingBuilder.java) returns the list of foldable text ranges (as an array of [`FoldingDescriptor`](%gh-ic%/platform/core-api/src/com/intellij/lang/folding/FoldingDescriptor.java) objects), the replacement text which is shown for each range when it is folded, and the default state of each folding region (folded or unfolded).

- [Custom Language Support Tutorial: Folding Builder](folding_builder.md)

### Join Lines

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.joinLinesHandler"/></include>

[`JoinLinesHandlerDelegate`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/JoinLinesHandlerDelegate.java) allows extending support smart/semantic <ui-path>Edit | Join Lines</ui-path> (e.g., String literal split on multiple lines).

### Smart Enter

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.smartEnterProcessor"/></include>

[`SmartEnterProcessor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/smartEnter/SmartEnterProcessor.java) handles <ui-path>Edit | Complete Statement</ui-path> (e.g., autocomplete missing semicolon/parentheses).

### Move Element Left/Right

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.moveLeftRightHandler"/></include>

Return children of the given element from [`MoveElementLeftRightHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/moveLeftRight/MoveElementLeftRightHandler.java) for <ui-path>Code | Move Element Left|Right</ui-path>, e.g., method call parameters.
Alternatively, implement [`PsiListLikeElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiListLikeElement.java) in the PSI element.

### Move Statements Up and Down in the Editor

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.statementUpDownMover"/></include>

[`StatementUpDownMover`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover.java)
allows for customizing the behavior of moving statements up and down.
This can be used to keep code syntactically correct when moving code in the editor, e.g., when moving
a variable declaration.

### Splitting and Joining List Constructs

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.listSplitJoinContext"/></include>

[`ListSplitJoinContext`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/editor/actions/lists/ListSplitJoinContext.kt)
needs to be implemented to define the exact behavior of splitting and joining list-like constructs in a language.
The UI will show implementations of this EP as an
[intention action](https://www.jetbrains.com/help/idea/intention-actions.html)
at appropriate locations.
Developers can use the abstract classes in
[`DefaultListSplitJoinContext`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/editor/actions/lists/DefaultListSplitJoinContext.kt)
for their implementation.

- [`XmlAttributesSplitJoinContext`](%gh-ic%/xml/impl/src/com/intellij/codeInsight/intentions/XmlAttributesSplitJoinContext.kt)

### Recognizing Complex Multi-Block Expressions

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeBlockSupportHandler"/></include>

[`CodeBlockSupportHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/CodeBlockSupportHandler.java)
allows providing text ranges for more complex code blocks like, e.g., in `if`/`elsif`/`else` blocks.
It is used to highlight markers and keywords if one is under the cursor, and for navigation to the beginning/end of blocks.
See also [](#brace-matching).

## Highlighting

### Reference Injection

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.referenceInjector"/></include>

[`ReferenceInjector`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/injection/ReferenceInjector.java) allows users to inject pre-defined references (e.g., "Encoding", "File Reference") into `PsiLanguageInjectionHost` elements (IntelliLang plugin required).

### Prevent Error Highlighting of Files

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.problemHighlightFilter"/></include>, <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.problemFileHighlightFilter"/></include>

[`ProblemHighlightFilter`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/daemon/ProblemHighlightFilter.java) and
the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.problemFileHighlightFilter"/></include> (which implements
[`Condition<VirtualFile>`](%gh-ic%/platform/util/src/com/intellij/openapi/util/Condition.java))
are used to filter out files that should not be error-highlighted because they are, e.g., outside
the current project scope.
Note that these filters should be permissive and only prevent highlighting for files that are absolutely
known to be outside the scope.

### Semantic Highlight Usages

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.highlightUsagesHandlerFactory"/></include>

[`HighlightUsagesHandlerFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/HighlightUsagesHandlerFactory.java) allows highlighting e.g., Exit Points or Exceptions.
Can be [](indexing_and_psi_stubs.md#DumbAwareAPI) (2024.3+).

## Navigation

### Context Info

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.declarationRangeHandler"/></include>

[`DeclarationRangeHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hint/DeclarationRangeHandler.java) provides <ui-path>View | Context Info</ui-path> for custom languages with structure view implementation based on a [`TreeBasedStructureViewBuilder`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/structureView/TreeBasedStructureViewBuilder.java).

### Breadcrumbs

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.breadcrumbsInfoProvider"/></include>

[`BreadcrumbsProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ui/breadcrumbs/BreadcrumbsProvider.java)
allows for language-specific [breadcrumbs](https://www.jetbrains.com/help/idea/navigating-through-the-source-code.html#editor_breadcrumbs).
[Sticky Lines](https://www.jetbrains.com/help/idea/sticky-lines.html) feature also uses this data.

## IDE Integration

### TODO View

[`ParserDefinition.getCommentTokens()`](%gh-ic%/platform/core-api/src/com/intellij/lang/ParserDefinition.java) must return the set of tokens treated as comments to populate the <control>TODO</control> tool window.

### Color Preview/Chooser

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.colorProvider"/></include>

[`ElementColorProvider`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/editor/ElementColorProvider.java) renders a gutter icon for an element containing color information.

### File Includes

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.include.provider"/></include>

[`FileIncludeProvider`](%gh-ic%/platform/lang-api/src/com/intellij/psi/impl/include/FileIncludeProvider.java) provides information about _include_ statements resolving to files (e.g., `<xi:include>` in XML).
Including/included files can then be obtained via [`FileIncludeManager`](%gh-ic%/platform/lang-api/src/com/intellij/psi/impl/include/FileIncludeManager.java).

### Plain Text Completion

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.plainTextSymbol"/></include>

[`PlainTextSymbolCompletionContributor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/completion/PlainTextSymbolCompletionContributor.java)
provides a simple way to extract lookup elements from a file so that users have completion available
in, e.g., plain text editors like VCS commit messages.

### Reader Mode

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.readerModeMatcher"/></include>

[`ReaderModeMatcher`](%gh-ic%/platform/editor-ui-api/src/com/intellij/codeInsight/actions/ReaderModeMatcher.kt)
provides a way to decide if files are shown in the correct mode: reader mode vs. normal editor mode.
Please see
[the documentation](https://www.jetbrains.com/help/idea/reader-mode.html)
to get familiar with reader mode.

### Provide Fully Qualified Names (FQN) for Elements

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.qualifiedNameProvider"/></include>

[`QualifiedNameProvider`](%gh-ic%/platform/refactoring/src/com/intellij/ide/actions/QualifiedNameProvider.java)
provides features like copying and pasting references of FQN for, e.g., classes, functions, or methods.
Therefore, the `QualifiedNameProvider` implementation needs to provide logic to convert from and to FQN.

### Customized "Copy Path"

Extend from [`CopyPathProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/CopyPathProvider.kt)
[action](action_system.md) (or `DumbAwareCopyPathProvider` if [no indexes are needed](indexing_and_psi_stubs.md#DumbAwareAPI))
and return a custom (language-specific) path from `getPathToElement()`.
Register the action with the popup menu group `<add-to-group group-id="CopyReferencePopupGroup"/>`.

- [`CopyRepositoryRootPathProvider`](%gh-ic%/plugins/git4idea/src/git4idea/actions/CopyRepositoryRootPathProvider.kt)

### Label Files as Test Files

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.testSourcesFilter"/></include>

[`TestSourcesFilter`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/TestSourcesFilter.java)
allows for telling the IDE that a file is a test file; even it's not located in a directory marked as
test root.
This can be used in situations where test files are located next to source files.
If these files can be distinguished either by filename or content from source files, implementing this
EP will mark them as test files for the IDE.

## Refactoring

### Naming Suggestions

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.nameSuggestionProvider"/></include>

[`NameSuggestionProvider`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/NameSuggestionProvider.java) provides name suggestions for the given element, e.g., for [Rename refactoring](rename_refactoring.md).

### Suggesting Rename and Change Signature Refactorings

EP: <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.suggestedRefactoringSupport"/></include>

[`SuggestedRefactoringSupport`](%gh-ic%/platform/lang-api/src/com/intellij/refactoring/suggested/SuggestedRefactoringSupport.kt)
provides functionality for suggesting rename and change signature refactorings for custom languages.

<include from="snippets.topic" element-id="missingContent"/>
