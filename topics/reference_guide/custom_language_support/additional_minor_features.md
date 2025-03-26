<!-- Copyright 2000-2025 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

# Additional Minor Features

<link-summary>Additional minor features for custom languages.</link-summary>

A number of commonly used minor features are listed in the following format:

_EP: `fully.qualified.extensionPointName`_ — Extension Point Name (must be specified in <path>[plugin.xml](plugin_configuration_file.md)</path>)

_`com.extensionPoint.class`_ _description text_ — Extension Point class/interface to provide functionality

_- Sample 1_ - Sample implementation

> See also [](intellij_platform_extension_point_list.md#langextensionpointsxml) to discover more Language-related Extension Points as well as general guide [](explore_api.md).
>
{title="Locating more Language EPs"}

### Brace Matching

EP: `com.intellij.lang.braceMatcher`

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

If the brace matching is "too heavy" and should not be executed in [EDT](threading_model.md), implement
[`HeavyBraceHighlighter`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/HeavyBraceHighlighter.java)
and register in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.heavyBracesHighlighter"/></include>.

### Quote Handling

EP: `com.intellij.lang.quoteHandler`

To support <control>Insert pair quote</control> feature, provide [`QuoteHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/QuoteHandler.java).
In most cases, [`SimpleTokenSetQuoteHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/editorActions/SimpleTokenSetQuoteHandler.java) base implementation will be suitable.

### Comment Code

EP: `com.intellij.lang.commenter`

[`Commenter`](%gh-ic%/platform/core-api/src/com/intellij/lang/Commenter.java) returns the prefix for the line comment, and the prefix and suffix for the block comment if supported by the language.
For more complex logic, use [`SelfManagingCommenter`](%gh-ic%/platform/core-api/src/com/intellij/codeInsight/generation/SelfManagingCommenter.java).

- [`Commenter`](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/PropertiesCommenter.java) for [Properties language plugin](%gh-ic%/plugins/properties)
- [Custom Language Support Tutorial: Commenter](commenter.md)

### Code Folding

EP: `com.intellij.lang.foldingBuilder`

[`FoldingBuilder`](%gh-ic%/platform/core-api/src/com/intellij/lang/folding/FoldingBuilder.java) returns the list of foldable text ranges (as an array of [`FoldingDescriptor`](%gh-ic%/platform/core-api/src/com/intellij/lang/folding/FoldingDescriptor.java) objects), the replacement text which is shown for each range when it is folded, and the default state of each folding region (folded or unfolded).

- [Custom Language Support Tutorial: Folding Builder](folding_builder.md)

### Join Lines

EP: `com.intellij.joinLinesHandler`

[`JoinLinesHandlerDelegate`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/JoinLinesHandlerDelegate.java) allows extending support smart/semantic <ui-path>Edit | Join Lines</ui-path> (e.g., String literal split on multiple lines).

### Smart Enter

EP: `com.intellij.lang.smartEnterProcessor`

[`SmartEnterProcessor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/smartEnter/SmartEnterProcessor.java) handles <ui-path>Edit | Complete Statement</ui-path> (e.g., autocomplete missing semicolon/parentheses).

### Move Element Left/Right

EP: `com.intellij.moveLeftRightHandler`

Return children of given element from [`MoveElementLeftRightHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/moveLeftRight/MoveElementLeftRightHandler.java) for <ui-path>Code | Move Element Left|Right</ui-path>, e.g., method call parameters.
Alternatively, implement [`PsiListLikeElement`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiListLikeElement.java) in PSI element.

### Naming Suggestions

EP: `com.intellij.nameSuggestionProvider`

[`NameSuggestionProvider`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/NameSuggestionProvider.java) provides name suggestions for the given element, e.g., for Rename refactoring.

### Semantic Highlight Usages

EP: `com.intellij.highlightUsagesHandlerFactory`

[`HighlightUsagesHandlerFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/HighlightUsagesHandlerFactory.java) allows highlighting e.g., Exit Points or Exceptions.
Can be [](indexing_and_psi_stubs.md#DumbAwareAPI) (2024.3+).

### TODO View

[`ParserDefinition.getCommentTokens()`](%gh-ic%/platform/core-api/src/com/intellij/lang/ParserDefinition.java) must return the set of tokens treated as comments to populate the <control>TODO</control> tool window.

### Context Info

EP: `com.intellij.declarationRangeHandler`

[`DeclarationRangeHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hint/DeclarationRangeHandler.java) provides <ui-path>View | Context Info</ui-path> for custom languages with structure view implementation based on a [`TreeBasedStructureViewBuilder`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ide/structureView/TreeBasedStructureViewBuilder.java).

### Spellchecking

Moved to [](spell_checking.md).

### Reference Injection

EP: `com.intellij.referenceInjector`

[`ReferenceInjector`](%gh-ic%/platform/analysis-api/src/com/intellij/psi/injection/ReferenceInjector.java) allows users to inject pre-defined references (e.g., "Encoding", "File Reference") into `PsiLanguageInjectionHost` elements (IntelliLang plugin required).

### Color Preview/Chooser

EP: `com.intellij.colorProvider`

[`ElementColorProvider`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/editor/ElementColorProvider.java) renders gutter icon for an element containing color information.

### File Includes

EP: `com.intellij.include.provider`

[`FileIncludeProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/psi/impl/include/FileIncludeProvider.java) provides information about _include_ statements resolving to files (e.g., `<xi:include>` in XML).
Including/included files can then be obtained via [`FileIncludeManager`](%gh-ic%/platform/lang-api/src/com/intellij/psi/impl/include/FileIncludeManager.java).

### Recognizing Complex Multi-Block Expressions

EP: `com.intellij.codeBlockSupportHandler`

[`CodeBlockSupportHandler`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/highlighting/CodeBlockSupportHandler.java)
allows providing text ranges for more complex code blocks like, e.g., in `if`/`elsif`/`else` blocks.
It is used to highlight markers and keywords if one is under the cursor, and for navigation to the beginning/end of blocks.
See also [](#brace-matching).

### Breadcrumbs

EP: `com.intellij.breadcrumbsInfoProvider`

[`BreadcrumbsProvider`](%gh-ic%/platform/editor-ui-api/src/com/intellij/ui/breadcrumbs/BreadcrumbsProvider.java)
allows for language-specific [breadcrumbs](https://www.jetbrains.com/help/idea/navigating-through-the-source-code.html#editor_breadcrumbs).
[Sticky Lines](https://www.jetbrains.com/help/idea/sticky-lines.html) feature also uses this data.

- [`GroovyBreadcrumbsInfoProvider`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/editor/GroovyBreadcrumbsInfoProvider.kt)

### Plain Text Completion

EP: `com.intelllij.completion.plainTextSymbol`

[`PlainTextSymbolCompletionContributor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/completion/PlainTextSymbolCompletionContributor.java)
provides a simple way to extract lookup elements from a file so that users have completion available
in, e.g., plain text editors like VCS commit messages.

### Splitting and Joining List Constructs

EP: `com.intellij.listSplitJoinContext`

[`ListSplitJoinContext`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/editor/actions/lists/ListSplitJoinContext.kt)
needs to be implemented to define the exact behavior of splitting and joining list-like constructs
in a language.
The UI will show implementations of this EP as an
[intention action](https://www.jetbrains.com/help/idea/intention-actions.html)
at appropriate locations.
Developers can use the abstract classes in
[`DefaultListSplitJoinContext`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/editor/actions/lists/DefaultListSplitJoinContext.kt)
for their implementation.

- [`XmlAttributesSplitJoinContext`](%gh-ic%/xml/impl/src/com/intellij/codeInsight/intentions/XmlAttributesSplitJoinContext.kt)

### Suggesting Rename and Change Signature Refactorings

EP: `com.intellij.suggestedRefactoringSupport`

[`SuggestedRefactoringSupport`](%gh-ic%/platform/lang-api/src/com/intellij/refactoring/suggested/SuggestedRefactoringSupport.kt)
provides functionality for suggesting rename and change signature refactorings for custom languages.

- [`KotlinSuggestedRefactoringSupport`](%gh-ic%/plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/refactoring/suggested/KotlinSuggestedRefactoringSupport.kt)

### Reader Mode

EP: `com.intellij.readerModeMatcher`

[`ReaderModeMatcher`](%gh-ic%/platform/editor-ui-api/src/com/intellij/codeInsight/actions/ReaderModeMatcher.kt)
provides a way to decide if files are shown in the correct mode: reader mode vs. normal editor mode.
Please see
[the documentation](https://www.jetbrains.com/help/idea/reader-mode.html)
to get familiar with reader mode.

### Background Colors for Editors and Project View

EP: `com.intellij.editorTabColorProvider`

[`EditorTabColorProvider`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/fileEditor/impl/EditorTabColorProvider.java)
allows for the modification of the background colors for specific files.
If access to indexes is not required, it can be marked [dumb aware](indexing_and_psi_stubs.md#DumbAwareAPI).

### Custom Names and Tooltips for Editor Tabs

EP: `com.intellij.editorTabTitleProvider`

[`EditorTabTitleProvider`](%gh-ic%/platform/ide-core-impl/src/com/intellij/openapi/fileEditor/impl/EditorTabTitleProvider.kt)
allows for specifying custom names and tooltips displayed in the title of editor tabs.
If access to indexes is not required, it can be marked [dumb aware](indexing_and_psi_stubs.md#DumbAwareAPI).

Please see, e.g.,
[`GradleEditorTabTitleProvider`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/util/GradleEditorTabTitleProvider.kt)
which shows how the project name is added to the editor tab for Gradle files.

### Prevent Error Highlighting of Files

EP: `com.intellij.problemHighlightFilter`, `com.intellij.problemFileHighlightFilter`

[`ProblemHighlightFilter`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/daemon/ProblemHighlightFilter.java) and
the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.problemFileHighlightFilter"/></include> (which implements
[`Condition<VirtualFile>`](%gh-ic%/platform/util/src/com/intellij/openapi/util/Condition.java))
are used to filter out files that should not be error-highlighted because they are, e.g., outside
the current project scope.
Note that these filters should be permissive and only prevent highlighting for files that are absolutely
known to be outside the scope.

- [`JavaProblemHighlightFilter`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/daemon/JavaProblemHighlightFilter.java)
- [`PyProblemFileHighlightFilter`](%gh-ic%/python/src/com/jetbrains/python/codeInsight/PyProblemFileHighlightFilter.java)

### Provide Fully Qualified Names (FQN) for Elements

EP: `com.intellij.ide.actions.QualifiedNameProvider`

[`QualifiedNameProvider`](%gh-ic%/platform/refactoring/src/com/intellij/ide/actions/QualifiedNameProvider.java)
provides features like copying and pasting references of FQN for, e.g., classes, functions, or methods.
Therefore, the `QualifiedNameProvider` implementation needs to provide logic to convert from and to
FQN.

- [`PyQualifiedNameProvider`](%gh-ic%/python/src/com/jetbrains/python/actions/PyQualifiedNameProvider.java)

### Label Files as Test Files

EP: `com.intellij.openapi.roots.TestSourcesFilter`

[`TestSourcesFilter`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/TestSourcesFilter.java)
allows for telling the IDE that a file is a test file, even it's not located in a directory marked as
test root.
This can be used in situations where test files are located next to source files.
If these files can be distinguished either by filename or content from source files, implementing this
EP will mark them as test files for the IDE.

### Move Statements Up and Down in the Editor

EP: `com.intellij.statementUpDownMover`

[`StatementUpDownMover`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover.java)
allows for customizing the behavior of moving statements up and down.
This can be used to keep code syntactically correct when moving code in the editor, e.g. when moving
a variable declaration.

- [`DeclarationMover`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/editorActions/moveUpDown/DeclarationMover.java)


<include from="snippets.topic" element-id="missingContent"/>
