<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Poly Symbols
<primary-label ref="2025.2"/>

<link-summary>
Poly Symbols framework provides a layer over Symbol API, which facilitates Symbols sharing between various languages and technologies.
</link-summary>

> This API is available starting from 2025.2 and is currently in development and thus in an experimental state.
>
{style="warning"}

> In versions 2022.3–2025.1 this API was known as Web Symbols.
>
{style="note"}

Poly Symbols is a framework built on top of the platform's [Symbol API](symbols.md). It provides a generic layer,
which allows sharing of Symbols between different languages and technologies. It also reduces work needed to
implement a new language or framework support with [Symbol API](symbols.md). In many cases only reference providers
and code completion providers need to be implemented on the platform side and symbol contributors on the Poly Symbol framework side.
After that most of the aspects of support based on reference resolution,
like find usages, documentation, or rename refactoring, will work out-of-the-box.

## Poly Symbol query executor

The main part of the framework is [`PolySymbolQueryExecutor`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryExecutor.kt),
which supports three simple queries: list symbols, get code completions, and match symbols with a name.
It uses symbols from scopes provided through [`PolySymbolQueryScopeContributor`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryScopeContributor.kt).
The list of scopes is built similarly to how code completion works. Various contributors
define patterns for `PsiElement`s for which they provide symbol scopes. For each instance of `PolySymbolQueryExecutor`,
the list of scopes is built anew depending on the provided PSI location. During query execution,
the executor gets symbols or completion items from each scope on the list to build the query results.
The results of the queries may be customized by [`PolySymbolQueryResultsCustomizer`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryResultsCustomizer.kt)
allowing for a very robust way to filter or alter the results of any query.

The `PolySymbolQueryExecutor` can be used in many places to provide symbols, but the main consumers of the API are
reference and code completion providers. The `PolySymbolQueryExecutor` simplifies reference and code completion providers
by delegating the actual symbol resolution to [`PolySymbolScope`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolScope.kt).
The executor provides a customization layer as well and abstracts language integration from symbol sources allowing for symbols to be provided from various sources
(e.g. source code, library code, or static symbol definitions, like Web Types)

## Symbol Patterns

`PolySymbolQueryExecutor` main advantage, however, is the ability to evaluate symbol patterns. Many frameworks or libraries
have custom syntaxes as a meta-language over regular language syntax. Examples for this are various web frameworks (e.g.
HTML attribute name microsyntax - see [Model Queries example](polysymbols_implementation.md#model-queries-example) for Vue directive syntax), but also message bundle keys,
or plugin extension points defined in XML. Using symbol patterns, it is relatively easy to define that micro syntax.
The pattern evaluator based on that information is able to recognize symbols making up a name of another symbol.

With none or minimal customizations required, for such symbols, the Poly Symbols framework provides: code completion, reference resolution,
documentation, navigation, semantic highlighting, occurrences highlighting, find usages, and rename refactoring. This is possible
because neither reference nor completion providers use `PolySymbolScope`s directly, but instead they depend on the query results
from the `PolySymbolQueryExecutor`. The executor can return a composite symbol for a reference provider, which then is split into name segments,
and each of the recognized symbols has its own reference range. For a code completion provider, on the other hand, it can
provide a set of completion items, which are results of evaluating all possible symbol names for the given patterns. Pattern evaluator
supports also multistaged completion, so it's possible to first complete a name prefix and then continue with completion of the rest
of the pattern.

## Summary

Overall, the Poly Symbols framework can serve as a base for a language support, or can work on a meta-level,
to support frameworks or libraries, which are giving additional meaning to the existing language features.

Currently, IDEs provide built-in integration for the following language features (see [](polysymbols_integration.md)):
- HTML: elements, attributes and attribute values
- CSS: properties, custom properties, functions, classes, pseudo-elements, pseudo-classes and parts
- JavaScript: string-literals, object properties, object literals, and symbols (in JavaScript and TypeScript)

The following subpages provide information on how to implement Poly Symbols for plugins and how to
define them statically through JSON schemas:
- [](polysymbols_implementation.md)
- [](polysymbols_web_types.md)

## PolyContext

Poly Symbols framework provides a convenient way to manage enablement of features through [`PolyContext` API](polysymbols_context.md).
