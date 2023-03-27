<!-- Copyright 2000-2023 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->
# Web Symbols

<link-summary>
Web Symbols framework simplifies web technology development by utilizing Symbol API and supporting custom syntaxes.
</link-summary>

> This API is available starting from 2022.3.1 and is currently in development and thus in an experimental state.
>
{style="warning"}

Web Symbols is a framework built on top of the platform's [Symbol API](symbols.md).
Its primary use is to reduce boilerplate when building support for web technologies,
however one can take advantage of it for any kind of technology.
Web Symbols API reduces work needed to provide reference resolution, find usages, documentation and rename refactoring.
It also provides an efficient JSON format (Web Types) for static symbol definitions.
Web Symbols core advantage, however, is the ability to evaluate symbol patterns.
Web frameworks tend to have custom syntaxes to enable various things,
e.g. through HTML attribute name (see Model Queries#example to see Vue directive syntax).
The pattern evaluator is able to recognize symbols in different sections of the name and provide reference resolution,
documentation, usage find.
It also supports rename refactoring for symbols originating from source code.
Another advantage is the flexibility of Web Symbols query.
It is very easy to contribute new symbols from various sources, which works perfectly for multi-sourced
(e.g. source code, library code, Web Types) applications.

In the following sub-pages you learn about implementing Web Symbols for your plugin and how you can
contribute Web Symbols by statically defining them through JSON schemas:

- [](websymbols_implementation.md)
- [](websymbols_static_symbols.md)
