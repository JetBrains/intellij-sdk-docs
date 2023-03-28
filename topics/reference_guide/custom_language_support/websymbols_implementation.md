<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Implementing Web Symbols

<link-summary>
Implementation details for the Web Symbols API.
</link-summary>

The core element of the framework is a
[`WebSymbol`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/WebSymbol.kt),
which represents an entity in the Web Symbols model.
It is described through `namespace`, `kind` and `name` properties. Its lifecycle is a single read action.
If you need it to survive between read actions, use `WebSymbol.createPointer()` to create a symbol pointer.
If the symbol is still valid, dereferencing the pointer will return a new instance of the symbol.

The property `namespace` describes which language or concept (not tied to a particular language) the symbol belongs to,
and `kind` describes which group of symbols within that particular language or concept it belongs to.
Examples:
- a CSS property: `namespace: CSS`, `kind: properties`
- a Java class: `namespace: Java`, `kind: classes`
- a plugin extension: `namespace: ij-plugin`, `kind: extensions`

A Web Symbol can originate from source code analysis, or it can be a symbol statically defined
through Web Types (JSON) or some other, custom format (see [](websymbols_static_symbols.md)).
In both cases such a symbol can have some `source` defined.
Each symbol is treated by the framework the same, regardless of their origin.

## General Properties

WebSymbol has a number of properties, which are used across IDE features:

{style="narrow"}
namespace
: Describes which language or concept the symbol belongs to.

kind
: Describes which group of symbols within the particular language or concept (namespace) the symbol belongs to.
The kind should be plural in form, e.g. "attributes".

name
: The name of the symbol. If the symbol does not have a pattern, the name will be used as-is for matching.

origin
: Specifies where this symbol comes from. Besides descriptive information like framework, library, version or default icon,
it also provides an interface to load symbol types and icons.

icon
: An optional icon associated with the symbol, which is going to be used across the IDE.
If none is specified, a default icon of the `origin` will be used and if that’s not available, a default icon for symbol `namespace` and `kind`.

priority
: Symbols with higher priority will have precedence over those with lower priority, when matching is performed.
Symbols with higher priority will also show higher on the completion list.

proximity
: Provides additional way to sort symbols in code completion list within a particular priority.
The value must be a non-negative integer and the higher proximity, the higher the symbol would be on the list.

deprecated
: Documents, whether the symbol is deprecated. Deprecated symbols are appropriately highlighted in the code editor,
code completion and quick documentation.

experimental
: Documents, whether the symbol is considered an experimental feature and should be used with caution and might be
removed or its API altered in the future.

required
: Whether this symbol is required. What "is required" means, depends on the symbol.
For instance, for an HTML attribute it would mean that the attribute is required to be present for the particular HTML element.

defaultValue
: If the symbol represents some property, variable or anything that can hold a value, this property documents what is the default value.

attributeValue
: A special property to support symbols representing HTML attributes.
It can specify the kind (plain, expression, no-value), type (boolean, number, string, enum, complex, of-match),
whether an attribute value is required, a default value and the result type of value expression in the appropriate language.
If `COMPLEX` type is set, the value of `langType` will be used and if `OF_MATCH`, the type of the `symbol` will be used.
When merging information from several segments in the WebSymbolMatch, first non-null property values take precedence.
By default - when properties are `null` - attribute value is of plain type and is required.

type
: The type of the symbol. The type can be interpreted only within the context of symbol origin and with regards to its namespace and kind.
The type may be a language type, coming from e.g. Java or TypeScript, or it may be any arbitrary value.
Usually a type would be associated with symbols, which can hold a value, or represent some language symbol, like class, method, etc.

psiContext
: A PsiElement, which is a file or an element, which can be used to roughly locate the source of the symbol within a project to provide
a context for loading additional information, like types.
If the symbol is
[`PsiSourcedWebSymbol`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/PsiSourcedWebSymbol.kt)
(see [](#psisourcedwebsymbol)), then `psiContext` is equal to `source`.

properties
: Various symbol properties. There should be no assumption on the type of properties.
Properties can be used by plugins to provide additional information on the symbol.
See Web Types/Properties section for reference on the custom properties supported by IDEs.

presentation
: Returns
[`TargetPresentation`](%gh-ic%/platform/core-api/src/com/intellij/navigation/TargetPresentation.kt)
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

Following properties handle generation of
[Quick Doc](https://www.jetbrains.com/help/idea/viewing-reference-information.html#inline-quick-documentation)
in the IDE:

{style="narrow"}
description
: An optional text, which describes the symbol purpose and usage. It is rendered in the documentation popup or view.

descriptionSections
: Additional sections, to be rendered in the symbols’ documentation. Each section should have a name, but the contents are optional.

docUrl
: An optional URL to a website with detailed symbol's documentation

documentation
: An interface holding information required to render documentation for the symbol. To customize symbols documentation,
one can override the method, or implement
[`WebSymbolDocumentationCustomizer`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/documentation/WebSymbolDocumentationCustomizer.kt).
[`WebSymbolDocumentation`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/documentation/WebSymbolDocumentation.kt)
interface provides builder methods for customizing the documentation.
`with*` methods return a copy of the documentation with customized fields.

## Query Related Properties

Following properties are related to name matching and code completion queries:

{style="narrow"}
pattern
: The pattern to match names against. As a result of pattern matching a
[`WebSymbolMatch`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolMatch.kt)
will be created.
A pattern may specify that a reference to other Web Symbols is expected in some part of it.
For such places, appropriate segments with referenced Web Symbols will be created and navigation,
validation and refactoring support is available out-of-the-box.

queryScope
: When pattern is being evaluated, matched symbols can provide additional scope for further resolution in the pattern.
By default, the `queryScope` returns the symbol itself

virtual
: Some symbols represent only a framework syntax, which does not translate to a particular symbol in the runtime.
For instance a Vue directive, which needs to be prefixed with `v-` will result in some special code generated,
but as such is not a real HTML attribute. This distinction allows us to ignore such symbols when looking for references.

abstract
: Some symbols may have a lot in common with each other and one can use abstract symbols as their super symbol,
currently only Web Types symbols can inherit from others.

extension
: Specifies whether the symbol is an extension. When matched along with a non-extension symbol it can provide or
override some of the properties of the symbol, or it can extend its scope contents.

## Methods
{#query-methods}

{style="narrow"}
createPointer()
: Returns the pointer to the symbol, which can survive between read actions. The dereferenced symbol should be valid,
  i.e. any PSI based properties should return valid PsiElements.

getModificationCount()
: Symbols can be used in
[`CachedValue`](%gh-ic%/platform/core-api/src/com/intellij/psi/util/CachedValue.java)s
as dependencies. If a symbol instance can mutate over the time, it should properly implement this method.

isEquivalentTo()
: Returns true if two symbols are the same or equivalent for resolve purposes.

adjustNameForRefactoring(): Web Symbols can have various naming conventions.
  This method is used by the framework to determine a new name for a symbol based on its occurrence.

## PsiSourcedWebSymbol

A symbol should implement
[`PsiSourcedWebSymbol`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/PsiSourcedWebSymbol.kt)
if its declaration is a regular `PsiElement`, e.g. a variable or a declared type.
Once a symbol implements this interface it can be searched and refactored together with the PSI element declaration.
If your symbol is part of a string, or spans multiple PSI elements, or does not relate 1-1 with a PSI element,
instead of implementing this interface you should contribute dedicated declaration and reference providers.

### Properties
{#psisourcedwebsymbol-properties}

{style="narrow"}
source
: The `PsiElement`, which is the symbol declaration.

## CompositeWebSymbol

[`WebSymbolMatch`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolMatch.kt)
and some special symbols can have a name, which consists of other Web Symbols.

### Properties
{#compositewebsymbol-properties}

{style="narrow"}
nameSegments
: List of
[`WebSymbolNameSegment`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/WebSymbolNameSegment.kt).
Each segment describes a range in the symbol name.
Segments can be built of other Web Symbols and/or have related matching problems - missing required part,
unknown symbol name or be a duplicate of another segment. See Model Queries/Example section for an example.

## Web Symbols Scope
Web Symbols are contained within a loose model built from Web Symbols scopes, each time anew for a particular context.
Each Web Symbol is also a
[`WebSymbolsScope`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/WebSymbolsScope.kt)
and it can contain other Web Symbols.
For instance an HTML element symbol would contain some HTML attributes symbols, or a JavaScript class symbol would contain fields and methods symbols.
When configuring queries, Web Symbols scope are added to the list to create an initial scope for symbols resolve.

### Methods
{#websymbolsscope-methods}

{style="narrow"}
getSymbols()
: Returns symbols within the scope. If provided `name` is `null`, no pattern evaluation will happen and all symbols of a particular
kind and from particular namespace will be returned.

getCodeCompletions()
: Returns code completions for symbols within the scope.

isExclusiveFor()
: When scope is exclusive for a particular namespace and kind, resolve will not continue down the stack during pattern matching.

createPointer()
: Returns the pointer to the symbol scope, which can survive between read actions. The dereferenced symbol scope should be valid.

getModificationCount()
: Symbol scopes are used in CachedValues as dependencies for query executors.
If a symbol scope instance can mutate over the time, it should properly implement this method.

When implementing a scope, which contains many elements you should extend
[`WebSymbolsScopeWithCache`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/WebSymbolsScopeWithCache.kt),
which cache the list of symbols and uses efficient cache to speed up queries.
When extending the class, you only need to override the `initialize` method and provide parameters to the super constructor to specify how results should be cached.

## Model Queries

Web Symbols can contain patterns, which allow to compose them from other Web Symbols.
To find which symbols match available patterns we need to make a match query.
One can also run code completion query, which will produce a list of valid completions in the provided context.

To perform a query create a
[`WebSymbolsQueryExecutor`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryExecutor.kt)
using
[`WebSymbolsQueryExecutorFactory`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryExecutorFactory.kt).
The query executor will be configured by all of the registered
[`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt)'s
based on the provided PSI context.
Configurators will provide initial Web Symbol scopes, rules for calculating Web Symbols context and rules for symbol names conversion.

The result of the match query is a list of WebSymbols.
Some of them might be
[`WebSymbolMatch`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolMatch.kt)es.
Such objects represent complex matches, when patterns are used.
Web Symbol Match has `nameSegments` property, which precisely describes how segments of the name relate to referenced Web Symbols and
whether there are any problems with resolution or the name itself.

When working with code completion, one can query for the list of code completions.
To properly calculate completions, a position in the current text under completion is required.
As a result, a list of
[`WebSymbolCodeCompletionItem`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/completion/WebSymbolCodeCompletionItem.kt)
will be provided.

### Example

Let’s take a Vue directive as an example.
It is a special HTML attribute processed by Vue framework in runtime or during compile,
which results in additional code being attached to the DOM element.
It’s structure looks as follows:

![JavaScript Example Image](websymbols_web_types.svg){ width="706"}

An example of how Vue directive might be declared in Web Types is here.
Once a match query is run on `v-on:click.once.alt`, we will get a
[`WebSymbolMatch`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolMatch.kt)
with following segments:

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
a segment to match is selected by taking everything up to prefixes of following patterns.
Thus, if we want to have symbol references and regular expressions in the pattern,
they either have to terminate the pattern or must be followed by a static text.
A regular pattern static prefix is also considered a static text.

There are 6 types of patterns:

1. String match: try to match an exact text, the match is case sensitive
2. Regular expression match: try match a regular expression, the match can be case insensitive
3. Symbol reference placeholder: a symbol reference resolve will be attempted when this pattern is reached.
   A resolve will be made by the symbols provider from an enclosing complex pattern.
   If none of the symbols match the segment, the segment will have `UNKNOWN_SYMBOL` problem reported.
   The matched symbol might be a WebSymbolMatch itself, which allows for nesting patterns.
4. Pattern sequence: a sequence of patterns. If some of the patterns are not matched, an empty segment with `MISSING_REQUIRED_PART` will be created.
5. Complex pattern: this pattern is called complex, because it makes several things:
    - The provided patterns are treated as alternatives.
    - It can have symbols resolver, which is used by nested symbol reference placeholder patterns.
    - It allows to add an additional scope to resolve stack
    - A complex pattern might be optional, in which case its absence is not reported as an error in enclosing sequence or complex pattern
    - The match can be repeated, and any duplicate segments might have `DUPLICATED` problem set
    - It can override proximity and priority, which by default is based on priority and proximity of matched symbols.

    {style="alpha-lower"}
6. Completion auto popup: a special pattern, which works only in code completion queries.
   It delimits the place, where when creating code completion items, pattern evaluation should be stopped and `...` added.
   Selecting such items will result in adding the prefix part and then another code completion popup will open.
   The pattern can be sticky, which means that the prefix will be shown in the nested code completion list.

### Query Context

When performing queries, some symbols should be excluded and others included in particular contexts.
For instance, if we have an Angular project, none of the Vue components should be available.
[`WebSymbolsContext`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/context/WebSymbolsContext.kt)
is created using rules provided by WebSymbolsQueryConfigurators with addition of custom
[`WebSymbolsContextProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/context/WebSymbolsContextProvider.kt).
As a result, for each kind of context there is at most a single name assigned.
`WebSymbolsContext` can also be used outside of the
[`WebSymbolsQueryExecutor`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryExecutor.kt)
as an efficient way to determine whether to enable or disable particular functionality in the IDE based on PSI or VFS context.

### Query stack

The stack is used as a scope for resolving symbols.
All scopes provided by
[`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt)s
together with the list of additional scopes passed as arguments to the query create an initial query stack.
Each time a symbol is matched, the list returned by `queryScope` property is added to the stack for any subsequent matches further up the pattern.

## Declarations, References, Search, Refactoring

To provide locations of declarations of Web Symbols, which are not
[`PsiSourcedWebSymbol`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/PsiSourcedWebSymbol.kt)s,
a dedicated
[`WebSymbolDeclarationProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/declarations/WebSymbolDeclarationProvider.kt)
should be registered.
It should return a list of
[`WebSymbolDeclaration`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/declarations/WebSymbolDeclaration.kt)s
in a particular `PsiElement` at a particular offset.

Similarly, to provide references a
[`PsiSymbolReferenceProvider`](%gh-ic%/platform/core-api/src/com/intellij/model/psi/PsiSymbolReferenceProvider.java)
should be registered.
It should return
[`WebSymbolReference`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/references/WebSymbolReference.kt)
objects from `PsiSymbolReferenceProvider.getReferences()`.

To support search (aka find usages) Web Symbol needs to implement
[`SearchTarget`](%gh-ic%/platform/lang-impl/src/com/intellij/find/usages/api/SearchTarget.kt)
or a
[`WebSymbolSearchTarget`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/search/WebSymbolSearchTarget.kt)
needs to be provided for it through a
[`SymbolSearchTargetFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/find/usages/symbol/SymbolSearchTargetFactory.java).

To support name refactoring, the
[`RenameTarget`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/rename/api/RenameTarget.kt).
interface needs to be implemented,
or a
[`WebSymbolRenameTarget`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/refactoring/WebSymbolRenameTarget.kt)
needs to be provided for it through a
[`SymbolRenameTargetFactory`](%gh-ic%/platform/lang-impl/src/com/intellij/refactoring/rename/symbol/SymbolRenameTargetFactory.java).
