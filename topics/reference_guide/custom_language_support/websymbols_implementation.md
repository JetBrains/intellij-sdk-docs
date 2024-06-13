<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Implementing Web Symbols
<primary-label ref="2022.3"/>

<link-summary>Implementation details for the Web Symbols API.</link-summary>

The core element of the framework is a
[`WebSymbol`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/WebSymbol.kt),
representing an entity in the Web Symbols model.
This symbol is characterized by `namespace`, `kind` and `name` properties, with its lifecycle encapsulated
within a single read action.
To ensure its survival between read actions, use `WebSymbol.createPointer()` to create a symbol pointer.
Provided the symbol remains valid, dereferencing the pointer will return a new instance of the symbol.
It should be noted that during a write action, the symbol might not survive a PSI tree commit.
Therefore, creating a pointer prior to the commit and dereferencing it post-commit is advised.

The property `namespace` describes which language or concept (not tied to a particular language) the symbol belongs to,
and `kind` describes which group of symbols within that particular language or concept it belongs to.
Examples:

- a CSS property: `namespace: CSS`, `kind: properties`
- a Java class: `namespace: Java`, `kind: classes`
- a plugin extension: `namespace: ij-plugin`, `kind: extensions`

A Web Symbol can originate from source code analysis, or it can be a symbol statically defined
through [Web Types](websymbols_web_types.md) (JSON) or some other custom format.
In both cases, such a symbol can have some `source` defined.
Each symbol is treated by the framework the same, regardless of their origin.

## General Properties

`WebSymbol` has a number of properties which are used across IDE features:

{style="full"}
`namespace`
: Describes which language or concept the symbol belongs to.

`kind`
: Describes which group of symbols within the particular language or concept (namespace) the symbol belongs to.
The kind should be plural in form, e.g. "attributes".

`name`
: The name of the symbol. If the symbol does not have a pattern, the name will be used as-is for matching.

`origin`
: Specifies where this symbol comes from.
Besides descriptive information like framework, library, version, or default icon,
it also provides an interface to load symbol types and icons.

`icon`
: An optional icon associated with the symbol, which is going to be used across the IDE.
If none is specified, a default icon of the `origin` will be used and if that’s not available, a default icon for symbol `namespace` and `kind`.

`priority`
: Symbols with higher priority will have precedence over those with lower priority, when matching is performed.
Symbols with higher priority will also show higher on the completion list.

`proximity`
: Provides an additional way to sort symbols in the code completion list within a particular priority.
The value must be a non-negative integer, and the higher proximity, the higher the symbol would be on the list.

`apiStatus`
: *Since 2023.2 - replaces `deprecated` and `experimental` properties*
: Documents API status of the symbol. It is one of the sub-interfaces of [`WebSymbolApiStatus`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/WebSymbolApiStatus.kt):
   `Stable`, `Experimental` or `Deprecated`. Deprecated symbols are appropriately highlighted in the code editor, code completion, and quick documentation.

`deprecated`
: *Removed in 2023.2 - replaced with `apiStatus` property*
: Documents, whether the symbol is deprecated. Deprecated symbols are appropriately highlighted in the code editor,
code completion, and quick documentation.

`experimental`
: *Removed in 2023.2 - replaced with `apiStatus` property*
: Documents, whether the symbol is considered an experimental feature and should be used with caution and might be
removed or its API altered in the future.

`required`
: Whether this symbol is required.
What "is required" means depends on the symbol.
For instance, for an HTML attribute, it would mean that the attribute is required to be present for the particular HTML element.
For JavaScript property, it would mean that it is not optional, so it cannot be `undefined`.

`defaultValue`
: If the symbol represents some property, variable, or anything that can hold a value, this property documents what is the default value.

`type`
: The type of the symbol. The type can be interpreted only within the context of symbol origin and in regard to its namespace and kind.
The type may be a language type, coming from e.g., Java or TypeScript, or it may be any arbitrary value.
Usually a type would be associated with symbols, which can hold a value, or represent some language symbol, like class, method, etc.

`psiContext`
: A PsiElement, which is a file or an element, which can be used to roughly locate the source of the symbol within a project to provide
a context for loading additional information, like types.
If the symbol is
[`PsiSourcedWebSymbol`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/PsiSourcedWebSymbol.kt)
(see [](#psisourcedwebsymbol)), then `psiContext` is equal to `source`.

`properties`
: Various symbol properties. There should be no assumption on the type of properties.
Properties can be used by plugins to provide additional information on the symbol.
See [Web Types Special Properties](websymbols_web_types.md#special-properties) section for reference to the custom properties supported by IDEs.

`presentation`
: Returns
[`TargetPresentation`](%gh-ic%/platform/core-api/src/com/intellij/platform/backend/presentation/TargetPresentation.kt)
used by
[`SearchTarget`](%gh-ic%/platform/lang-impl/src/com/intellij/find/usages/api/SearchTarget.kt)
and
[`RenameTarget`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/rename/api/RenameTarget.kt).
Default implementations of
[`WebSymbolRenameTarget`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/refactoring/WebSymbolRenameTarget.kt)
and
[`WebSymbolSearchTarget`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/search/WebSymbolSearchTarget.kt)
use the `presentation` property.

## Documentation Properties

The following properties handle generation of
[Quick Doc](https://www.jetbrains.com/help/idea/viewing-reference-information.html#inline-quick-documentation)
in the IDE:

{style="full"}
`description`
: An optional text, which describes the symbol's purpose and usage.
It is rendered in the documentation popup or view.

`descriptionSections`
: Additional sections, to be rendered in the symbols’ documentation.
Each section should have a name, but the contents are optional.

`docUrl`
: An optional URL to a website with detailed symbol's documentation

`documentation`
: *Removed in 2023.1.1 - replaced by `createDocumentation()`*
: An interface holding information required to render documentation for the symbol.
To customize symbols documentation, one can override the method, or implement[`WebSymbolDocumentationCustomizer`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/documentation/WebSymbolDocumentationCustomizer.kt).
[`WebSymbolDocumentation`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/documentation/WebSymbolDocumentation.kt)
interface provides builder methods for customizing the documentation.
`with*` methods return a copy of the documentation with customized fields.

## Query Related Properties

The following properties are related to name matching and code completion queries:

{style="full"}
`pattern`
: The pattern to match names against.
As a result of pattern matching, a [`WebSymbolMatch`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolMatch.kt) will be created.
A pattern may specify that a reference to other Web Symbols is expected in some part of it.
For such places, appropriate segments with referenced Web Symbols will be created and navigation, validation, and refactoring support are available out-of-the-box.

`queryScope`
: When a pattern is being evaluated, matched symbols can provide additional scope for further resolution in the pattern.
By default, the `queryScope` returns the symbol itself

`virtual`
: Some symbols represent only a framework syntax, which does not translate to a particular symbol in the runtime.
For instance, a Vue directive, which needs to be prefixed with `v-` will result in some special code generated,
but as such is not a real HTML attribute. This distinction allows us to ignore such symbols when looking for references.

`abstract`
: Some symbols may have a lot in common with each other, and one can use abstract symbols as their super symbol.
For performance reasons, only statically defined symbols ([](websymbols_web_types.md),
[Custom Elements Manifest](https://github.com/webcomponents/custom-elements-manifest))
can inherit from other statically defined symbols.
For dynamically defined symbols, regular class inheritance should be used.

`extension`
: Specifies whether the symbol is an extension.
When matched along with a non-extension symbol, it can provide or override some properties of the symbol, or it can extend its scope contents.

## HTML support

`attributeValue`
: A special property to support symbols representing HTML attributes.
It can specify the kind (plain, expression, no-value), type (boolean, number, string, enum, complex, of-match),
whether an attribute value is required, a default value, and the result type of value expression in the appropriate language.
If `COMPLEX` type is set, the value of `langType` will be used and if `OF_MATCH`, the type of the `symbol` will be used.
When merging information from several segments in the WebSymbolMatch, first non-null property values take precedence.
By default - when properties are `null` - attribute value is of plain type and is required.

## Methods
{#query-methods}

{style="full"}
`createPointer()`
: Returns the pointer to the symbol, which can survive between read actions.
The dereferenced symbol should be valid, e.g., any PSI-based properties should return valid `PsiElement`s.

`getModificationCount()`
: Symbols can be used in
[`CachedValue`](%gh-ic%/platform/core-api/src/com/intellij/psi/util/CachedValue.java)s
as dependencies.
If a symbol instance can mutate over time, it should properly implement this method.

`isEquivalentTo()`
: Returns true if two symbols are the same or equivalent for resolve purposes.

`adjustNameForRefactoring()`
: Web Symbols can have various naming conventions.
This method is used by the framework to determine a new name for a symbol based on its occurrence.

`getDocumentationTarget()`
: *Since 2023.1.1*
: Used by the Web Symbols framework to get a [`DocumentationTarget`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/DocumentationTarget.kt), which handles documentation
rendering for the symbol.
The default implementation will use `createDocumentation()` to render the documentation.

`createDocumentation()`
: *Since 2023.1.1 - replaces `documentation` property*
: Returns [`WebSymbolDocumentation`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/documentation/WebSymbolDocumentation.kt) -
an interface holding information required to render documentation for the symbol.
By default, its contents are built from the available Web Symbol information. To customize symbols documentation, one can override the method, or implement
[`WebSymbolDocumentationCustomizer`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/documentation/WebSymbolDocumentationCustomizer.kt).
:
[`WebSymbolDocumentation`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/documentation/WebSymbolDocumentation.kt)
interface provides builder methods for customizing the documentation.
`with*` methods return a copy of the documentation with customized fields.


## `PsiSourcedWebSymbol`

A symbol should implement
[`PsiSourcedWebSymbol`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/PsiSourcedWebSymbol.kt)
if its declaration is a regular `PsiElement`, e.g., a variable or a declared type.
Once a symbol implements this interface, it can be searched and refactored together with the PSI element declaration.
In case a symbol is part of a `PsiElement` (for instance, being part of a string literal),
spans multiple PSI elements, or does not correlate one-to-one with a PSI element,
contribution of a dedicated declaration provider instead of implementing this interface is recommended.

### Properties
{#psisourcedwebsymbol-properties}

{style="full"}
`source`
: The `PsiElement`, which is the symbol declaration.

## `CompositeWebSymbol`

[`WebSymbolMatch`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolMatch.kt)
and some special symbols can have a name, which consists of other Web Symbols.

### Properties
{#compositewebsymbol-properties}

{style="full"}
`nameSegments`
: List of
[`WebSymbolNameSegment`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/WebSymbolNameSegment.kt).
Each segment describes a range in the symbol name.
Segments can be built of other Web Symbols and/or have related matching problems - missing required part,
unknown symbol name or be a duplicate of another segment.
See the [Model Queries Example](#model-queries-example) section for an example.

## Web Symbols Scope

Web Symbols are contained within a loose model built from Web Symbols scopes, each time anew for a particular context.
Each Web Symbol is also a
[`WebSymbolsScope`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/WebSymbolsScope.kt)
and it can contain other Web Symbols.
For instance, an HTML element symbol would contain some HTML attributes symbols, or a JavaScript class symbol would contain field and method symbols.
When configuring queries, Web Symbols scopes are added to the list to create an initial scope for symbols resolve.

### Methods
{#websymbolsscope-methods}

{style="full"}
`getSymbols()`
: Returns symbols within the scope. If provided `name` is `null`, no pattern evaluation will happen and all symbols of a particular
kind and from a particular namespace will be returned.

`getCodeCompletions()`
: Returns code completions for symbols within the scope.

`isExclusiveFor()`
: When scope is exclusive for a particular namespace and kind, resolve will not continue down the stack during pattern matching.

`createPointer()`
: Returns the pointer to the symbol scope, which can survive between read actions. The dereferenced symbol scope should be valid.

`getModificationCount()`
: Symbol scopes are used in CachedValues as dependencies for query executors.
If a symbol scope instance can mutate over time, it should properly implement this method.

When implementing a scope containing many elements, an extension of
[`WebSymbolsScopeWithCache`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/WebSymbolsScopeWithCache.kt) is advised.
This structure caches the list of symbols and uses an efficient cache mechanism to speed up queries.
On extension of this class, it's only necessary to override `initialize()` and provide parameters to
the super constructor to specify the caching strategy for the results.

## Model Queries

Web Symbols can contain patterns, which allow to compose them from other Web Symbols.
To find which symbols match available patterns, we need to make a match query.
One can also run a code completion query, which will produce a list of valid completions in the provided context.

To perform a query, create a
[`WebSymbolsQueryExecutor`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryExecutor.kt)
using
[`WebSymbolsQueryExecutorFactory`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryExecutorFactory.kt).
The query executor will be configured by all the registered
[`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt)'s
based on the provided PSI context.
Configurators will provide initial Web Symbol scopes, rules for calculating Web Symbols context, and rules for symbol names conversion.

The result of the match query is a list of WebSymbols.
Some of them might be
[`WebSymbolMatch`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolMatch.kt)es.
Such objects represent complex matches when patterns are used.
Web Symbol Match has `nameSegments` property, which precisely describes how segments of the name relate to referenced Web Symbols and
whether there are any problems with resolution or the name itself.

When working with code completion, one can query for the list of code completions.
To properly calculate completions, a position in the current text under completion is required.
As a result, a list of
[`WebSymbolCodeCompletionItem`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/completion/WebSymbolCodeCompletionItem.kt)
will be provided.

### Example
{#model-queries-example}

Let’s take a Vue directive as an example.
It is a special HTML attribute processed by the Vue framework in runtime or during compile,
which results in additional code being attached to the DOM element.
Its structure looks as follows:

![JavaScript Example Image](websymbols_web_types.svg){ width="706"}

An example of how Vue directive might be declared in Web Types is here.
Once a match query is run on `v-on:click.once.alt`, we will get a
[`WebSymbolMatch`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolMatch.kt)
with the following segments:

1. `v-`: Vue directive pattern symbol
2. `on`: Vue `on` directive
3. `:`
4. `click`: DOM `click` event symbol
5. `.`
6. `once`: Vue `on` directive `once` modifier
7. `alt`: Vue `on` directive `alt` modifier

### Patterns

Usually one would create such elements using Web Types, but sometimes there might be a need to do that programmatically.

To simplify resolution and make it less ambiguous,
a segment to match is selected by taking everything up to static prefixes of the following patterns.
Thus, if we want to have symbol references and regular expressions in the pattern,
they either have to terminate the pattern or must be followed by a static text.
A regular pattern static prefix is also considered a static text.

There are seven types of patterns:

1. String match: try to match an exact text, the match is case-sensitive
2. Regular expression match: try to match a regular expression, the match can be case-insensitive
3. Symbol reference placeholder: a symbol reference resolve will be attempted when this pattern is reached.
   A resolve will be made by the symbols provider from an enclosing complex pattern.
   If none of the symbols match the segment, the segment will have `UNKNOWN_SYMBOL` problem reported.
   The matched symbol might be a WebSymbolMatch itself, which allows for nesting patterns.
4. Pattern sequence: a sequence of patterns. If some patterns are not matched, an empty segment with `MISSING_REQUIRED_PART` will be created.
5. Complex pattern: this pattern is called complex, because it makes several things:
    - The provided patterns are treated as alternatives.
    - It can have symbols resolver, which is used by nested symbol reference placeholder patterns.
    - It allows adding an additional scope to resolve stack
    - A complex pattern might be optional, in which case its absence is not reported as an error in an enclosing sequence or complex pattern
    - The match can be repeated, and any duplicate segments might have `DUPLICATED` problem set
    - It can override proximity and priority, which by default is based on priority and proximity of matched symbols.

   {style="alpha-lower"}
6. Completion auto popup: a special pattern, which works only in code completion queries.
   It delimits the place where when creating code completion items, pattern evaluation should be stopped and `...` added.
   Selecting such items will result in adding the prefix part, and then another code completion popup will open.
   The pattern can be sticky, which means that the prefix will be shown in the nested code completion list.
7. Single symbol reference (*since 2023.2*): try to match text against the symbol name, but put a reference to another element.

### Query Context

When performing queries, some symbols should be excluded and others included in particular contexts.
For instance, if we have an Angular project, none of the Vue components should be available.
[`WebSymbolsContext`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/context/WebSymbolsContext.kt)
is created using rules provided by `WebSymbolsQueryConfigurator`s with the addition of custom
[`WebSymbolsContextProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/context/WebSymbolsContextProvider.kt).
As a result, for each kind of context, there is at most a single name assigned.
`WebSymbolsContext` can also be used outside the
[`WebSymbolsQueryExecutor`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryExecutor.kt)
as an efficient way to determine whether to enable or disable particular functionality in the IDE based on PSI or VFS context.

### Query stack

The stack is used as a scope for resolving symbols.
All scopes provided by
[`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt)s
together with the list of additional scopes passed as arguments to the query create an initial query stack.
Each time a symbol is matched, the list returned by `queryScope` property is added to the stack for any subsequent matches further right the pattern.

## Declarations, References, Search, Refactoring

To provide locations of declarations of Web Symbols, which are not
[`PsiSourcedWebSymbol`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/PsiSourcedWebSymbol.kt)s,
a dedicated
[`WebSymbolDeclarationProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/declarations/WebSymbolDeclarationProvider.kt)
should be registered.
It should return a list of
[`WebSymbolDeclaration`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/declarations/WebSymbolDeclaration.kt)s
in a particular `PsiElement` at a particular offset.

Similarly, to provide references, a
[`PsiSymbolReferenceProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/PsiSymbolReferenceProvider.java)
should be registered.
It should return
[`WebSymbolReference`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/references/WebSymbolReference.kt)
objects from `PsiSymbolReferenceProvider.getReferences()`.

To support search/finding usages, Web Symbol needs to implement
[`SearchTarget`](%gh-ic%/platform/lang-impl/src/com/intellij/find/usages/api/SearchTarget.kt)
or a
[`WebSymbolSearchTarget`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/search/WebSymbolSearchTarget.kt)
needs to be provided for it through a
[`SymbolSearchTargetFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/find/usages/symbol/SymbolSearchTargetFactory.java).

To support name refactoring, the
[`RenameTarget`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/rename/api/RenameTarget.kt)
interface needs to be implemented,
or a
[`WebSymbolRenameTarget`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/refactoring/WebSymbolRenameTarget.kt)
needs to be provided for it through a
[`SymbolRenameTargetFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/rename/symbol/SymbolRenameTargetFactory.java).
