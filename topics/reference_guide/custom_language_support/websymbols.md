<!-- Copyright 2000-2023 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->
# Web Symbols

<link-summary>
Web Symbols framework simplifies web technology development by utilizing Symbol API and supporting custom syntaxes.
</link-summary>

## Web Symbol

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

The core element of the framework is a
[`WebSymbol`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/WebSymbol.kt),
which represents an entity in the Web Symbols model.
It is described through `namespace`, `kind` and `name` properties. Its lifecycle is a single read action.
If you need it to survive between read actions, use `WebSymbol.createPointer()` to create a symbol pointer.
If the symbol is still valid, dereferencing the pointer will return a new instance of the symbol.

The property `namespace` describes which language or concept the symbol belongs to,
and `kind` describes which group of symbols within that particular language or concept it belongs to.
E.g. a CSS property would have: `namespace == "CSS"` and `kind == "properties"`, a Java class would have:
`namespace == "Java"` and `kind == "classes"`.
The namespace can also represent a concept, which is not tied to a particular language.
For instance a plugin extension could be represented with `namespace == "ij-plugin"` and `kind == "extensions"`.

A Web Symbol can originate from source code analysis or it can be a symbol statically defined through Web Types (JSON) or some other, custom format.
In both cases such a symbol can have some `source` defined.
Each symbol is treated by the framework the same, regardless of their origin.

### General Properties

WebSymbol has a number of properties, which are used across IDE features:

{style="full"}
namespace
: describes which language or concept the symbol belongs to.

kind
: describes which group of symbols within the particular language or concept (namespace) the symbol belongs to.
The kind should be plural in form, e.g. "attributes".

name
: the name of the symbol. If the symbol does not have a pattern, the name will be used as-is for matching.

origin
: specifies where this symbol comes from. Besides descriptive information like framework, library, version or default icon,
it also provides an interface to load symbol types and icons.

icon
: an optional icon associated with the symbol, which is going to be used across the IDE.
If none is specified, a default icon of the `origin` will be used and if that’s not available, a default icon for symbol `namespace` and `kind`.

priority
: symbols with higher priority will have precedence over those with lower priority, when matching is performed.
Symbols with higher priority will also show higher on the completion list.

proximity
: provides additional way to sort symbols in code completion list within a particular priority.
The value must be a non-negative integer and the higher proximity, the higher the symbol would be on the list.

deprecated
: documents, whether the symbol is deprecated. Deprecated symbols are appropriately highlighted in the code editor,
code completion and quick documentation.

experimental
: documents, whether the symbol is considered an experimental feature and should be used with caution and might be
removed or its API altered in the future.

required
: whether this symbol is required. What "is required" means, depends on the symbol.
For instance, for an HTML attribute it would mean that the attribute is required to be present for the particular HTML element.

defaultValue
: if the symbol represents some property, variable or anything that can hold a value, this property documents what is the default value.

attributeValue
: a special property to support symbols representing HTML attributes.
It can specify the kind (plain, expression, no-value), type (boolean, number, string, enum, complex, of-match),
whether an attribute value is required, a default value and the result type of value expression in the appropriate language.
If `COMPLEX` type is set, the value of `langType` will be used and if `OF_MATCH`, the type of the `symbol` will be used.
When merging information from several segments in the WebSymbolMatch, first non-null property values take precedence.
By default - when properties are `null` - attribute value is of plain type and is required.

type
: the type of the symbol. The type can be interpreted only within the context of symbol origin and with regards to its namespace and kind.
The type may be a language type, coming from e.g. Java or TypeScript, or it may be any arbitrary value.
Usually a type would be associated with symbols, which can hold a value, or represent some language symbol, like class, method, etc.

psiContext
: a PsiElement, which is a file or an element, which can be used to roughly locate the source of the symbol within a project to provide
a context for loading additional information, like types.
If the symbol is
[`PsiSourcedWebSymbol`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/PsiSourcedWebSymbol.kt)
(see [](#psisourcedwebsymbol)), then `psiContext` is equal to `source`.

properties
: various symbol properties. There should be no assumption on the type of properties.
Properties can be used by plugins to provide additional information on the symbol.
See Web Types/Properties section for reference on the custom properties supported by IDEs.

presentation
: returns
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

### Documentation Properties

Following properties handle generation of Quick Doc in the IDE:

{style="full"}
description
: an optional text, which describes the symbol purpose and usage. It is rendered in the documentation popup or view.

descriptionSections
: additional sections, to be rendered in the symbols’ documentation. Each section should have a name, but the contents are optional.

docUrl
: an optional URL to a website with detailed symbol's documentation

documentation
: an interface holding information required to render documentation for the symbol. To customize symbols documentation,
one can override the method, or implement
[`WebSymbolDocumentationCustomizer`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/documentation/WebSymbolDocumentationCustomizer.kt).
[`WebSymbolDocumentation`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/documentation/WebSymbolDocumentation.kt)
interface provides builder methods for customizing the documentation.
`with*` methods return a copy of the documentation with customized fields.

### Query Related Properties

Following properties are related to name matching and code completion queries:

{style="full"}
pattern
: the pattern to match names against. As a result of pattern matching a
[`WebSymbolMatch`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolMatch.kt)
will be created.
A pattern may specify that a reference to other Web Symbols is expected in some part of it.
For such places, appropriate segments with referenced Web Symbols will be created and navigation,
validation and refactoring support is available out-of-the-box.

queryScope
: when pattern is being evaluated, matched symbols can provide additional scope for further resolution in the pattern.
By default the `queryScope` returns the symbol itself

virtual
: some symbols represent only a framework syntax, which does not translate to a particular symbol in the runtime.
For instance a Vue directive, which needs to be prefixed with `v-` will result in some special code generated,
but as such is not a real HTML attribute. This distinction allows us to ignore such symbols when looking for references.

abstract
: some symbols may have a lot in common with each other and one can use abstract symbols as their super symbol,
currently only Web Types symbols can inherit from others.

extension
: specifies whether the symbol is an extension. When matched along with a non-extension symbol it can provide or
override some of the properties of the symbol, or it can extend its scope contents.

### Methods
{#query-methods}

{style="full" sorted="asc"}
createPointer()
: returns the pointer to the symbol, which can survive between read actions. The dereferenced symbol should be valid,
  i.e. any PSI based properties should return valid PsiElements.

getModificationCount()
: symbols can be used in
[`CachedValue`](%gh-ic%/platform/core-api/src/com/intellij/psi/util/CachedValue.java)s
as dependencies. If a symbol instance can mutate over the time, it should properly implement this method.

isEquivalentTo()
: returns true if two symbols are the same or equivalent for resolve purposes.

adjustNameForRefactoring(): Web Symbols can have various naming conventions.
  This method is used by the framework to determine a new name for a symbol based on its occurrence.

## PsiSourcedWebSymbol

Your symbol should implement
[`PsiSourcedWebSymbol`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/PsiSourcedWebSymbol.kt)
if its declaration is a regular `PsiElement`, e.g. a variable or a declared type.
Once a symbol implements this interface it can be searched and refactored together with the PSI element declaration.
If your symbol is part of a string, or spans multiple PSI elements, or does not relate 1-1 with a PSI element,
instead of implementing this interface you should contribute dedicated declaration and reference providers.

### Properties
{#psisourcedwebsymbol-properties}

{style="full"}
source
: the `PsiElement`, which is the symbol declaration

## CompositeWebSymbol

[`WebSymbolMatch`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolMatch.kt)
and some special symbols can have a name, which consists of other Web Symbols.

### Properties
{#compositewebsymbol-properties}

{style="full"}
nameSegments
: list of
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

{style="full"}
getSymbols()
: returns symbols within the scope. If provided `name` is `null`, no pattern evaluation will happen and all symbols of a particular
kind and from particular namespace will be returned.

getCodeCompletions()
: returns code completions for symbols within the scope.

isExclusiveFor()
: when scope is exclusive for a particular namespace and kind, resolve will not continue down the stack during pattern matching.

createPointer()
: returns the pointer to the symbol scope, which can survive between read actions. The dereferenced symbol scope should be valid.

getModificationCount()
: symbol scopes are used in CachedValues as dependencies for query executors.
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
or
a
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

## Web Types

Web Types is a JSON metadata format, which provides an easy way to contribute statically defined Web Symbols.
The JSON Web Types detailed schema can be accessed by
[following this link](https://github.com/JetBrains/web-types/blob/master/schema/web-types.json).
The format is open source and IDE-agnostic by itself, however currently it is being actively used mostly by JetBrains IDEs.

It was originally created to facilitate the contribution of statically defined symbols for the
[Vue](https://vuejs.org/) framework and therefore you might notice some deprecated properties in the schema.

A simple Web Types file looks as follows, where this file defines an `my-element` HTML element with a `foo` attribute.:

```
{
  "$schema": "https://raw.githubusercontent.com/JetBrains/web-types/master/schema/web-types.json",
  "name": "example",
  "version": "0.0.1",
  "description-markup": "markdown",
  "contributions": {
    "html": {
      "elements": [
        {
          "name": "my-element",
          "description": "A custom HTML element",
          "doc-url": "https://my-lib/docs/my-element",
          "attributes": [
            {
              "name": "foo",
              "description": "A custom attribute of `my-element`"
            }
          ]
        }
      ]
    }
  }
}
```
{lang="JSON" collapsible="true" default-state="collapsed" collapsed-title-line-number="2"}

### File Structure

The Web Types file should, at minimum, contain `name`, `version` and `contributions` properties.
It is should also include `$schema` property which can be either
`https://raw.githubusercontent.com/JetBrains/web-types/master/schema/web-types.json`
or
`http://json.schemastore.org/web-types`.
The schema contains detailed documentation for all of the JSON entities.

Directly under `contributions` property are listed namespaces with their contributions.
Currently only `html`, `css` or `js` namespaces are allowed, however in the future this limitation
will be lifted to support Web Types for other technologies.

The `namespace` object contains symbol kind names listed as properties.
Some symbol kinds are predefined and directly supported by IDE (see Symbol Kinds/Supported for reference).
The kind of a symbol should relate to its role.
For instance a Vue directive should be of the kind `vue-directives`.
Each framework should define a set of custom symbol kinds if needed.
Reference for the most important symbol kinds defined by frameworks supported by IDEs is below.

Each symbol kind name property of a namespace object is an array of symbol contributions.
A symbol contribution should have at least a name.
Contributions in addition to standard properties can define sub-contributions and custom properties.
A custom property is a JSON property, whose value is of a simple type (string, number or boolean),
or is an array of simple types.
If a contribution’s JSON property’s value is an object value or an array of objects, it is treated as a list of sub-contributions.
Such contributions will be assigned to the same namespace as the parent contributions.
To use a different namespace for sub-contributions, nest symbol kind JSON property name under a `js`, `css` or `html` property. E.g:

```
"contributions": {
 "html": {
   "elements": [
     {
       "name": "my-element",
       "description": "A custom HTML element",
       "attributes": [
         {
           "name": "foo",
           "description": "A custom HTML attribute of `my-element`"
         }
       ],
       "css": {
         "properties": [
           {
             "name": "--bg-color",
             "description": "Background color of my-element"
           }
         ]
       }
     }
   ]
 }
}
```
{lang="JSON" collapsible="true" default-state="collapsed"}

In the example below, Web Types contributes information that the `my-element` HTML element supports a custom CSS property `--bg-color`.
The `attributes` are implicitly under the `html` namespace.
To contribute `foo` attribute one could also write it in longer form:

```
{
  "name": "my-element",
  "description": "A custom HTML element",
  "html": {
    "attributes": [
      {
        "name": "foo",
        "description": "A custom attribute of `my-element`"
      }
    ]
  }
}
```
{lang="JSON" collapsible="true" default-state="collapsed" collapsed-title-line-number="2"}

Each Web Types contribution is represented in the Web Symbols framework by a
[`PsiSourcedWebSymbol`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/PsiSourcedWebSymbol.kt)
object.
All of the Web Types contributions are mapped 1-1 and custom properties are accessible through `properties` property.

### Including Web Types

Web Types can currently be discovered by the IDE in following ways:

{style="full"}
NPM
: The IDE will automatically discover any Web Types shipped with the NPM library and specified in the
`web-types` property of `package.json`.
The property accepts a string or an array of strings with relative paths to Web Types files shipped with the package.

Local Project
: In your JavaScript projects in `package.json` files you can specify `web-types` property similarly to the NPM package.
The property accepts a string or an array of strings with relative paths to Web Types files within the project.

IDE Plugin
: You can ship Web Types JSON with your IDE plugin.
To point an IDE to its location use `com.intellij.webSymbols.webTypes` extension point and pass the file location in `source` attribute value.
With `enableByDefault` attribute you can choose whether the Web Types file should be contributed to Web Symbols scope by default,
or only if an NPM package with the same name is present in the project.

### Special Properties

{style="full"}
inject-language
: supported by `html/elements` and `html/attributes`, allows to inject the specified language into HTML element text or HTML attribute value.

doc-hide-pattern
: if a symbol uses a RegEx pattern, usually it will be displayed in a documentation popup section "pattern". Setting this property to `true` hides that section.

hide-from-completion
: by default all symbols show up in code completion. Setting this property to `true` prevents a symbol from showing up in the code completion.

### Symbol Kinds

Web Types files are used internally by IDEs and tooling to define rules for frameworks.
Following is the reference for symbol kinds used by framework support.

#### Direct Support

In version 2022.3 IDEs with JavaScript plugin installed provide direct support for following symbol kinds:

- `html/elements`
- `html/attributes`
- `css/properties`
- `css/pseudo-classes`
- `css/pseudo-elements`
- `css/functions`
- `css/classes`

#### Angular

Angular plugin Web Types are available
[here](https://github.com/JetBrains/intellij-plugins/tree/master/AngularJS/resources/web-types)
for reference.
Any Web Types file targeting only Angular support should have `framework` property set to `angular`.
Highlights: `js/ng-custom-events` contribute symbols with patterns for any custom events supported by Angular `EventManager`s, e.g.:

```
"ng-custom-events": [
 {
   "name": "Custom modifiers for declarative events handling",
   "priority": "normal",
   "pattern": {
     "template": [
       {
         "items": {
           "path": "/js/events",
           "includeVirtual": false
         }
       },
       {
         "items": "ng-event-plugins-modifiers",
         "template": [
           ".",
           "#...",
           "#item:modifiers"
         ],
         "priority": "high",
         "repeat": true,
         "unique": true,
         "required": false
       }
     ]
   },
   "ng-event-plugins-modifiers": [
     {
       "name": "prevent"
     },
     {
       "name": "stop"
     },
     ...
   ]
 }
]
```
{lang="JSON" collapsible="true" default-state="collapsed"}

#### Vue

Vue plugin Web Types are available here for reference.
Any Web Types file targeting only Vue support should have `framework` property set to `vue`.
Highlights:

{style="full"}
/html/vue-components
: Use `/html/vue-components` to contribute Vue components.

/html/vue-directives
: Use `/html/vue-directives` to contribute Vue directives.
Use `attribute-value` property to specify the type of value expression. E.g.:
```
"attribute-value": {
  "type": "boolean",
  "required": true
}
```
{lang="JSON" collapsible="true" default-state="collapsed"}

/html/vue-file-top-elements
: Use `/html/vue-file-top-elements` to contribute any custom top-level elements available in Vue Single Component File

A Vue `/html/vue-components` contribution supports:

{style="full"}
/html/props
: Use `/html/props` to contribute Vue component props, e.g:
```
"props": [
 {
  "name": "appear",
  "description": "Whether to apply transition on initial render.",
  "type": "boolean",
  "default": "false"
 }
]
```
{lang="JSON" collapsible="true" default-state="collapsed"}

/html/slots
: Use `/html/slots` to contribute Vue component slots.
For scoped slots use `vue-properties` to provide list of scoped slot properties.
Example:
```
"slots": [
 {
   "name": "img",
   "description": "Expects the [v-img](/components/images) component.",
   "doc-url": "https://www.vuetifyjs.com/api/v-app-bar#slots",
   "vue-properties": [
     {
       "name": "props",
       "type": "{ height: string, src: string | srcObject }"
     }
   ]
 }
]
```
{lang="JSON" collapsible="true" default-state="collapsed"}

/js/events
: Use `/js/events` to contribute Vue component events, e.g.:
```
"js": {
 "events": [
   {
     "name": "input",
     "description": "The updated bound model"
   }
 ]
}
```
{lang="JSON" collapsible="true" default-state="collapsed"}

html/vue-model
: Use `html/vue-model` to contribute settings for Vue model directive. E.g.:
```
"vue-model": {
  "prop": "show",
  "event": "input"
}
```
{lang="JSON" collapsible="true" default-state="collapsed"}


A Vue `/html/vue-directives` contribution supports:

{style="full"}
/html/argument
: Use `/html/argument` as a Vue directive argument. E.g.:
```
"argument": {
 "name": "attribute or property name",
 "description": "Optional attribute or property name",
 "pattern": {
   "items": [
     {
       "path": "/html/attributes",
       "includeVirtual": false
     }
   ]
 }
}
```
{lang="JSON" collapsible="true" default-state="collapsed"}


/html/modifiers
: Use `/html/modifiers` as a Vue directive modifier. E.g.:
```
"modifiers": [
 {
   "name": "body",
   "description": "Make the mask append to the body element",
   "type": "boolean"
 },
 {
   "name": "fullscreen",
   "type": "boolean"
 }
]
```
{lang="JSON" collapsible="true" default-state="collapsed"}


#### Web Components

For Lit support, install into your Node project as a devDependency `@web-types/lit`.
Web Types are available here for reference.
Web Components should use:

- `/html/attributes`: for attributes available in HTML
- `/js/properties`: for Web Component DOM class properties
- `/js/events`: for Web Component events

Example Web Component:

```
{
  "$schema": "https://raw.githubusercontent.com/JetBrains/web-types/master/schema/web-types.json",
  "name": "Cool library",
  "version": "1.0.0",
  "js-types-syntax": "typescript",
  "description-markup": "markdown",
  "contributions": {
    "html": {
      "elements": [
        {
          "name": "cool-component",
          "description": "Use the cool component to make your website more attractive.",
          "doc-url": "https://www.cool-lib.com/docs/cool-component",
          "attributes": [
            {
              "name": "color",
              "description": "Choose color for coolness",
              "default": "blue",
              "required": false,
              "doc-url": "https://www.cool-lib.com/docs/cool-component#attrs",
              "value": {
                "type": "string"
              }
            }
          ],
          "slots": [
            {
              "name": "container"
            }
          ],
          "events": [
            {
              "name": "color:changed",
              "description": "Emitted when color changes"
            }
          ],
          "js": {
            "properties": [
              {
                "name": "color",
                "type": "string",
                "default": "blue"
              }
            ]
          },
          "css": {
            "properties": [
              {
                "name": "--cool-degree"
              }
            ]
          }
        }
      ]
    }
  }
}
```
{lang="JSON" collapsible="true" default-state="collapsed" collapsed-title-line-number="3"}