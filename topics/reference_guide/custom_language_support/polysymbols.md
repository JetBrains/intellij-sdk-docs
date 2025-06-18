<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Poly Symbols
<primary-label ref="2025.2"/>

<link-summary>
Poly Symbols framework simplifies web technology development by utilizing Symbol API and supporting custom syntaxes.
</link-summary>

> This API is available starting from 2025.2 and is currently in development and thus in an experimental state.
>
{style="warning"}

Poly Symbols is a framework built on top of the platform's [Symbol API](symbols.md).
Its primary use is to reduce boilerplate when building support for web technologies,
however one can take advantage of it for any kind of technology.

Poly Symbols API reduces work needed to provide reference resolution, find usages, documentation and rename refactoring.
It also provides an efficient JSON format (Web Types) for static symbol definitions.
Poly Symbols core advantage, however, is the ability to evaluate symbol patterns.

Web frameworks tend to have custom syntaxes to enable various things,
e.g. through HTML attribute name (see [Model Queries example](polysymbols_implementation.md#model-queries-example) to see Vue directive syntax).
The pattern evaluator is able to recognize symbols in different sections of the element name and provide reference resolution,
documentation, usage occurrence, etc.
It also supports rename refactoring for symbols originating from source code.
Another advantage is the flexibility of Poly Symbols query.
It is very easy to contribute new symbols from various sources, which works perfectly for multi-sourced
(e.g. source code, library code, Web Types) applications.

Poly Symbols API is not designed to create a support for new languages, but to rather work on meta
level, to support frameworks or libraries, which are giving additional meaning to the existing language features.
Currently, IDEs provide built-in integration for following language features (see [](polysymbols_integration.md)):
- HTML: elements, attributes and attribute values
- CSS: properties, custom properties, functions, classes, pseudo-elements, pseudo-classes and parts
- JavaScript: string-literals, object properties, object literals and symbols (in JavaScript)

There's also the option to write integration for other languages.

Poly Symbols framework provides also a convenient way to manage enablement of features through [`PolyContext` API](polysymbols_context.md).

The following sub-pages provide information on how to implement Poly Symbols for plugins and how to
define them statically through JSON schemas:
- [](polysymbols_implementation.md)
- [](polysymbols_web_types.md)
