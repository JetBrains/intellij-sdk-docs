<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Implementing Poly Symbols
<primary-label ref="2025.2"/>

<link-summary>Implementation details for the Poly Symbols API.</link-summary>

The core element of the framework is a [`PolySymbol`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/PolySymbol.kt).
It is identified through `name` and `qualifiedKind` properties.
The symbol has a very generic meaning and may represent a variable in some language, or an endpoint of some web server, or a file.

The symbol lifecycle is limited to a single read action.
To ensure its survival between read actions, use `PolySymbol.createPointer()` to create a symbol pointer.
Provided the symbol remains valid, dereferencing the pointer will return a new instance of the symbol.
It should be noted that during a write action, the symbol might not survive a PSI tree commit.
Therefore, creating a pointer prior to the commit and dereferencing it post-commit is advised.

Symbols, which share some common characteristics, should be grouped using the same `qualifiedKind`.
The `qualifiedKind` consists of a `namespace`, which roughly indicates a language or a framework the symbol belongs to, and a `kind`, which roughly indicates what the symbol's basic characteristics are.

Examples:
- a CSS property: `namespace: CSS`, `kind: properties`
- a Java class: `namespace: Java`, `kind: classes`
- a plugin extension: `namespace: ij-plugin`, `kind: extensions`

A Poly Symbol can originate from source code analysis, or it can be a symbol statically defined  through [Web Types](polysymbols_web_types.md) (JSON) or some other custom format.
In both cases, such a symbol can have some `source` defined.
Each symbol is treated by the framework the same, regardless of their origin.

Consumers of symbols should avoid casting the `PolySymbol` to some other specialized interface, as it prevents third party symbol providers from customizing symbols or providing additional symbols.

## General Properties

`PolySymbol` has a number of properties which are used across IDE features:

{style="full"}
`qualifiedKind`
: Describes which group of symbols (kind) within the particular language
or concept (namespace) the symbol belongs to.

`name`
: The name of the symbol. If the symbol does not have a pattern, the name will be used as-is for matching.

`origin`
: Specifies where this symbol comes from.
Besides descriptive information like framework, library, version, or default icon, it also provides an interface to load symbol types and icons.

`icon`
: An optional icon associated with the symbol, which is going to be used across the IDE.
If none is specified, a default icon of the `origin` will be used and if that’s not available, a default icon for symbol `namespace` and `kind`.

`priority`
: Symbols with higher priority will have precedence over those with lower priority when matching is performed.
Symbols with higher priority will also show higher on the completion list.

`apiStatus`
: Documents API status of the symbol. It is one of the sub-interfaces of [`PolySymbolApiStatus`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/PolySymbolApiStatus.kt):
`Stable`, `Experimental` or `Deprecated`.
Deprecated symbols are appropriately highlighted in the code editor, code completion, and quick documentation.

`modifiers`
: A set of symbol modifiers.
The framework contains constants for many modifiers known from various programming languages.
However, implementations are free to define other modifiers using `PolySymbolModifier.get`.

: When a match is performed over a sequence of symbols, use `PolySymbolMatchCustomizer` to customize
how modifiers from different symbols in the sequence are merged for the resulting `PolySymbolMatch` modifiers.

`psiContext`
: A `PsiElement`, which is a file or an element, which can be used to roughly locate the source of the symbol within a project to provide a context for loading additional information, like types.
If the symbol is
[`PsiSourcedPolySymbol`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/search/PsiSourcedPolySymbol.kt)
(see [](#psisourcedpolysymbol)), then `psiContext` is equal to `source`.

`presentation`
: Returns
[`TargetPresentation`](%gh-ic%/platform/core-api/src/com/intellij/platform/backend/presentation/TargetPresentation.kt)
used by
[`SearchTarget`](%gh-ic%/platform/lang-impl/src/com/intellij/find/usages/api/SearchTarget.kt)
and
[`RenameTarget`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/rename/api/RenameTarget.kt).
Default implementations of
[`PolySymbolRenameTarget`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/refactoring/PolySymbolRenameTarget.kt)
and
[`PolySymbolSearchTarget`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/search/PolySymbolSearchTarget.kt)
use the `presentation` property.

`searchTarget`
: Implement to provide usage search for the symbol.
In most cases the implementation would simply call `PolySymbolSearchTarget.create`.

: Symbol can also implement the `SearchTarget` interface directly and override its methods, in which case `PolySymbolSearchTarget` returned by `searchTarget` property is ignored.
If the returned target is not a `PolySymbolSearchTarget`, a dedicated `UsageSearcher` needs to be implemented to handle it.

`searchTarget`
: Implement to provide rename refactoring for the symbol.
In most cases the implementation would simply call `PolySymbolRenameTarget.create`.

: Symbol can also implement the `RenameTarget` interface directly and override its methods, in which case `PolySymbolRenameTarget` returned by `renameTarget` property is ignored.
If the returned target is not a `PolySymbolRenameTarget`, a dedicated `RenameUsageSearcher` needs to be implemented to handle it.

## Query Related Properties

The following properties are related to name matching and code completion queries:

`queryScope`
: When a pattern is being evaluated, matched symbols can provide additional scope for further resolution in the pattern.
By default, the `queryScope` returns the symbol itself if it implements the ` PolySymbolScope ` interface.

`extension`
: Specifies whether the symbol is an extension.
When matched along with a non-extension symbol, it can provide or override some properties of the symbol, or it can extend its scope contents.

## Methods
{#query-methods}

{style="full"}
`get(property: PolySymbolProperty<T>)`
: Accessor for various symbol properties. Plugins can use properties to provide additional information on the symbol.
All properties supported by IDEs are defined through `PROP_*` constants of the `PolySymbol` interface.
Check their documentation for further reference. To ensure that results are properly cast, use the
`PolySymbolProperty.tryCast` method for returned values.

`getDocumentationTarget(location: PsiElement?)`
: Used by the Poly Symbols framework to get a [`DocumentationTarget`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/DocumentationTarget.kt), which handles documentation rendering for the symbol.
The additional ` location ` parameter allows calculating more specific properties for the symbol documentation, like inferred generic parameters.

: By default, `PolySymbolDocumentationTarget.create` method should be used to build the documentation target for the symbol.
It allows for documentation to be further customized by [`PolySymbolDocumentationCustomizer`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/documentation/PolySymbolDocumentationCustomizer.kt)s.

`getNavigationTargets(project: Project)`
: Override to provide navigation targets for the symbol.
The `SymbolNavigationService` may be used to create navigation targets.

`isEquivalentTo(symbol: Symbol)`
: Returns true if two symbols are the same or equivalent for resolve purposes.

`matchContext(context: PolyContext)`
: Allows filtering out symbol from query results if the context is not matched.
By default, only the current symbol framework from the `origin` property is checked.

`createPointer()`
: Returns the pointer to the symbol, which can survive between read actions.
The dereferenced symbol should be valid, for example, any PSI-based properties should return valid `PsiElement`s.

`getModificationCount()`
: Symbols can be used in
[`CachedValue`](%gh-ic%/platform/core-api/src/com/intellij/psi/util/CachedValue.java)s
as dependencies.
If a symbol instance can mutate over time, it should properly implement this method.

## `PsiSourcedPolySymbol`

A symbol should implement
[`PsiSourcedPolySymbol`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/search/PsiSourcedPolySymbol.kt)
if its declaration is a regular `PsiElement`, for example, a variable or a declared type.
Once a symbol implements this interface, it can be searched and refactored together with the PSI element declaration.
In case a symbol is:
- a part of `PsiElement` (for instance, being part of a string literal)
- spans multiple PSI elements
- does not correlate one-to-one with a PSI element

contribution of a dedicated declaration provider instead of implementing this interface is recommended.

### Properties
{#psisourcedpolysymbol-properties}

{style="full"}
`source`
: The `PsiElement`, which is the symbol declaration.

## `PolySymbolWithPattern`

The [`PolySymbolWithPattern`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolWithPattern.kt) represents a symbol, which might consist of other symbols and provides a pattern,
which describes the relationship.

### Properties
{#polysymbolwithpattern-properties}

{style="full"}
`pattern`
: The pattern to match names against.
As a result of pattern matching, a [`PolySymbolMatch`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolMatch.kt) will be created.
A pattern may specify that a reference to other Poly Symbols is expected in some part of it.
For such places, appropriate segments with referenced Poly Symbols will be created, and navigation, validation, and refactoring support are available out-of-the-box.

## `CompositePolySymbol`

[`PolySymbolMatch`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolMatch.kt)
and some special symbols can have a name, which consists of other Poly Symbols.

### Properties
{#compositepolysymbol-properties}

{style="full"}
`nameSegments`
: List of
[`PolySymbolNameSegment`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/PolySymbolNameSegment.kt).
Each segment describes a range in the symbol name.
Segments can be built of other Poly Symbols and/or have related matching problems - missing the required part, unknown symbol name or be a duplicate of another segment.
See the [Model Queries Example](#model-queries-example) section for an example.

## `PolySymbolScope`

Each `PolySymbol` can contain other Poly Symbols, in which case it should implement `PolySymbolScope`.
For instance, an HTML element symbol would contain some HTML attribute symbols, or a JavaScript class symbol would contain field and method symbols.

When configuring queries, `PolySymbolScope`s contributed by [`PolySymbolQueryScopeContributor`](%gh-ic-master%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryScopeContributor.kt) for the given location are added to a `PolySymbolQueryStack`] to create an initial scope for symbol resolve.
During pattern matching with symbol sequences, all matched symbols' query scopes (`PolySymbol.queryScope`) are added to the stack allowing for extending scope matching.

### Methods
{#polysymbolscope-methods}

{style="full"}
`getSymbols()`
: Returns symbols within the scope.
If the provided `name` is `null`, no pattern evaluation will happen, and all symbols of a particular kind and from a particular namespace will be returned.

`getCodeCompletions()`
: Returns code completions for symbols within the scope.

`isExclusiveFor()`
: When scope is exclusive for a particular namespace and kind, resolve will not continue down the stack during pattern matching.

`createPointer()`
: Returns the pointer to the symbol scope, which can survive between read actions.
The dereferenced symbol scope should be valid.

`getModificationCount()`
: Symbol scopes are used in CachedValues as dependencies for query executors.
If a symbol scope instance can mutate over time, it should properly implement this method.

When implementing a scope containing many elements, an extension of
[`PolySymbolScopeWithCache`](%gh-ic-master%/platform/polySymbols/src/com/intellij/polySymbols/utils/PolySymbolScopeWithCache.kt) is advised.
This structure caches the list of symbols and uses an efficient cache mechanism to speed up queries.
On extension of this class, it's only necessary to override `initialize()` and provide parameters to the super constructor to specify the caching strategy for the results.

## Model Queries

Poly Symbols can contain patterns, which allow composing them from other Poly Symbols.
Such symbols should implement the [`PolySymbolWithPattern`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolWithPattern.kt) interface.

To find which symbols match available patterns, we need to make a match query.
One can also run a code completion query, which will produce a list of valid completions in the provided context,
or a symbol list query, which will list all available symbols.

To perform a query, create a
[`PolySymbolQueryExecutor`](%gh-ic-master%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryExecutor.kt)
using
[`PolySymbolQueryExecutorFactory`](%gh-ic-master%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryExecutorFactory.kt).
The query executor will be configured by all registered
[`PolySymbolQueryScopeContributor`](%gh-ic-master%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryScopeContributor.kt)
and
[`PolySymbolQueryConfigurator`](%gh-ic-master%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryConfigurator.kt)
extensions based on the provided PSI context.
Scope contributors will provide initial Poly Symbol scopes, and configurators will provider rules for calculating Poly Symbols context, and rules for symbol names conversion.

The result of the match query is a list of `PolySymbol`s.
Some of them might be
[`PolySymbolMatch`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolMatch.kt)es.
Such objects represent complex matches when patterns are used.
Poly Symbol Match has `nameSegments` property, which precisely describes how segments of the name relate to referenced Poly Symbols and whether there are any problems with resolution or the name itself.

When working with code completion, one can query for the list of code completions.
To properly calculate completions, a position in the current text under completion is required.
As a result, a list of
[`PolySymbolCodeCompletionItem`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/completion/PolySymbolCodeCompletionItem.kt)
will be provided.

### Example
{#model-queries-example}

Let's consider a Vue directive.

It is a special HTML attribute processed by the Vue framework at runtime or during compilation, which results in additional code being attached to the DOM element.
Its structure looks as follows:

![JavaScript Example Image](polysymbols_web_types.svg){ width="706"}

An example of how a Vue directive might be declared in Web Types is here.
Once a match query is run on `v-on:click.once.alt`, we will get a
[`PolySymbolMatch`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolMatch.kt)
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

To simplify resolution and make it less ambiguous, a segment to match is selected by taking everything up to static prefixes of the following patterns.
Thus, if we want to have symbol references and regular expressions in the pattern, they either have to terminate the pattern or must be followed by a static text.
A regular pattern static prefix is also considered a static text.

There are seven types of patterns:

1. String match: try to match an exact text, the match is case-sensitive.
2. Regular expression match: try to match a regular expression, the match can be case-insensitive.
3. Symbol reference placeholder: a symbol reference resolve will be attempted when this pattern is reached.
   A resolve will be made by the symbols provider from an enclosing complex pattern.
   If none of the symbols match the segment, the segment will have a [`MatchProblem.UNKNOWN_SYMBOL`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/PolySymbolNameSegment.kt) problem reported.
   The matched symbol might be a [`PolySymbolMatch`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolMatch.kt) itself, which allows for nesting patterns.
4. Pattern sequence: a sequence of patterns. If some patterns are not matched, an empty segment with `MatchProblem.MISSING_REQUIRED_PART` will be created.
5. Complex pattern: this pattern is called complex because it makes several things:
    - The provided patterns are treated as alternatives.
    - It can have symbols resolver, which is used by nested symbol reference placeholder patterns.
    - It allows adding an extra scope to resolve stack.
    - A complex pattern might be optional, in which case its absence is not reported as an error in an enclosing sequence or complex pattern.
    - The match can be repeated, and any duplicate segments might have a `MatchProblem.DUPLICATED` problem set.
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
[`PolyContext`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/context/PolyContext.kt)
is created using rules provided by [`PolySymbolQueryConfigurator`](%gh-ic-master%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryConfigurator.kt)s with the addition of custom
[`PolyContextProvider`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/context/PolyContextProvider.kt).
As a result, for each kind of context, there is at most a single name assigned.
`PolyContext` can also be used outside the
[`PolySymbolQueryExecutor`](%gh-ic-master%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryExecutor.kt)
as an efficient way to determine whether to enable or disable particular functionality in the IDE based on PSI or VFS context.

### Query stack

The stack is used as a scope for resolving symbols.
All scopes provided by
[`PolySymbolQueryConfigurator`](%gh-ic-master%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryConfigurator.kt)s
together with the list of additional scopes passed as arguments to the query create an initial query stack.
Each time a symbol is matched, the list returned by `queryScope` property is added to the stack for any subsequent matches further right the pattern.

## Declarations

To provide locations of declarations of Poly Symbols, which are not
[`PsiSourcedPolySymbol`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/search/PsiSourcedPolySymbol.kt)s,
a dedicated
[`PolySymbolDeclarationProvider`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/declarations/PolySymbolDeclarationProvider.kt)
should be registered.
It should return a list of
[`PolySymbolDeclaration`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/declarations/PolySymbolDeclaration.kt)s
in a particular `PsiElement` at a particular offset.
Symbols may implement the [`PolySymbolDeclaredInPsi`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/utils/PolySymbolDeclaredInPsi.kt)
interface to avoid boilerplate related to declaration building.

## References and Code Completion

Usually, it is enough to provide a [`PolySymbolQueryScopeContributor`](%gh-ic-master%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryScopeContributor.kt) for the [supported language features](polysymbols_integration.md#supported-language-features).
However, when implementing integration for a language feature, reference providers and code completions need to be implemented from scratch.

To provide references, a
[`PsiPolySymbolReferenceProvider`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/references/PsiPolySymbolReferenceProvider.kt)
should be registered.
If references resolve to a single `PolySymbol`, even if it may be a composite `PolySymbol`, the `getReferencedSymbol` method should be implemented.
If the symbol reference is offset within the `PsiElement`, for example, within a string literal, the `getReferencedSymbolNameOffset` should also be implemented.
If there are multiple symbol references within the `PsiElement` and it is not possible to use a [`PolySymbolWithPattern`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolWithPattern.kt) to represent that, `getOffsetsToReferencedSymbols` should be overridden instead.
The `PsiPolySymbolReferenceProvider` should use `PolySymbolQueryExecutor` to produce the list of the symbols at the given location.

Together with references, completion providers should also be implemented.
Due to the nature of Poly Symbol patterns, providers need to be implemented separately from reference contributions.
A regular `CompletionContributor` should be registered, which in turn should register a pattern for completion provider, which extends [`PolySymbolsCompletionProviderBase`](%gh-ic%/platform/polySymbols/backend/src/com/intellij/polySymbols/completion/PolySymbolsCompletionProviderBase.kt).

## Documentation, Navigation, Find Usages, and Rename Refactoring

These platform features are based on the reference resolve, so once the reference is resolved to a `PolySymbol`, it is up to implementations of the interface to provide particular support.
Override `getDocumentationTarget`, `getNavigationTargets` methods, or `searchTarget`, `renameTarget` properties to inform the platform about how it should use the symbol.
To customize symbols documentation generically, [`PolySymbolDocumentationCustomizer`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/documentation/PolySymbolDocumentationCustomizer.kt) should be implemented.

## Semantic highlighting

Poly Symbol framework supports semantic highlighting of symbols.
The simplest method is to return the name of `TextAttributesKey` from the `PolySymbol.get` method, when `PolySymbol.PROP_IJ_TEXT_ATTRIBUTES_KEY` property value is requested.
For a more generic approach, `PolySymbolHighlightingCustomizer` may be implemented.
