<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Web Symbols Integration with Language Features
<primary-label ref="2022.3"/>

<link-summary>How to integrate your Web Symbols with a particular language feature.</link-summary>

IDEs provide built-in support for Web Symbols integration with main language features of HTML, CSS, and JavaScript.
Contribution of static symbols can be achieved through Web Types or Custom Elements Manifest,
or by creating and registering a [`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt) extension.

The implementation of [`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt) requires overriding `getScope()`.
This process involves the creation of a `WebSymbolScope` list,
depending on the provided `PsiElement` (`element` parameter) and `WebSymbolContext` (`context` parameter).
The following list of supported language features outlines the types of `PsiElement`s that can be expected
for each supported language feature.

## Supported Language Features

### HTML

#### Elements
{#html-elements}

**Namespace**: `html`

**Kind**: `elements`

Web Symbols representing available HTML elements.
HTML element symbols can be contributed statically or dynamically (through
[`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt)).
The statically contributed symbols are available globally, depending on the context setting of the contributing Web Types file.

For dynamic contributions, the `WebSymbolsQueryConfigurator.getScope()`'s `element` parameter can be:
- [`HtmlTag`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/html/HtmlTag.java) - Web Symbols should represent available HTML elements. The HTML tag's actual name should not be taken into account when building the scope.
- `CssElement` - Web Symbols should represent available HTML elements within a particular CSS file.

The matched Web Symbol is taken as a scope for matching HTML attributes of the tag.

#### Attributes
{#html-attributes}

**Namespace**: `html`

**Kind**: `attributes`

Web Symbols representing available HTML attributes.
HTML attribute symbols can be contributed statically or dynamically (through [`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt)).
The statically contributed symbols are available globally, depending on the context setting of the contributing Web Types file.
If the containing tag is matched to a Web Symbol, it is added to the scope for attribute matching.

For dynamic contributions, the `WebSymbolsQueryConfigurator.getScope()`'s `element` parameter can be:
- [`XmlAttribute`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/xml/XmlAttribute.java) - Web Symbols should represent available HTML attributes for matching. The HTML attribute actual name should not be taken into account when building the scope. However, the parent HTML tag and other attributes can be taken into account when building the scope.
- [`HtmlTag`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/html/HtmlTag.java) - Web Symbols should represent available HTML attributes for matching. The HTML tag and other attributes can be taken into account when building the scope.

Dedicated support for `WebSymbol` interface properties:
- `required` - if `true`, a warning will be shown if the attribute is missing. Does not apply to `virtual` attributes.
- `default` - the default value of the attribute, if `attributeValue.default` is `null`.
- `attributeValue` - provides information about the attribute value, see [`attributeValue`](websymbols_implementation.md#html-support) reference.

### CSS

#### Properties
{#css-properties}

**Namespace**: `css`

**Kind**: `properties`

Web Symbols representing available CSS properties. [Custom CSS properties (variables)](https://developer.mozilla.org/en-US/docs/Web/CSS/Using_CSS_custom_properties) names should be prefixed with `--`.
CSS properties can be contributed statically or dynamically (through [`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt)).
The statically contributed symbols are available globally, depending on the context setting of the contributing Web Types file.

Within a CSS file, the additional scope for matching properties is built from Web Symbols matching HTML element names from terminal selectors from the enclosing ruleset.

Within an HTML element `style` attribute value, an additional scope is built from Web Symbols matching the enclosing HTML element.

Scope for custom properties (variables) references (arguments for [`var()`](https://developer.mozilla.org/en-US/docs/Web/CSS/var) function) is built the same way
as for properties. Only properties with names starting with `--` are taken into account.

For dynamic contributions, the `WebSymbolsQueryConfigurator.getScope()`'s `element` parameter can be:
- `CssDeclaration` - Web Symbols should represent available CSS properties for matching. The CSS declaration actual name should not be taken into account when building the scope.

#### Pseudo-elements
{#css-pseudo-elements}

**Namespace**: `css`

**Kind**: `pseudo-elements`

Web Symbols representing available [CSS pseudo-elements](https://developer.mozilla.org/en-US/docs/Web/CSS/Pseudo-elements).
Symbols names *should not* be prefixed with `::`.
CSS pseudo-elements can be contributed statically or dynamically (through [`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt)).
The statically contributed symbols are available globally, depending on the context setting of the contributing Web Types file.

The additional scope for matching pseudo-elements is built from Web Symbols matching HTML element name preceding the pseudo-element keyword.

For dynamic contributions, the `WebSymbolsQueryConfigurator.getScope()`'s `element` parameter can be:
- `CssPseudoSelector` - Web Symbols should represent available CSS pseudo-elements for matching. The CSS pseudo-element actual name should not be taken into account when building the scope.

Dedicated support for `WebSymbol` interface properties:
- `properties[WebSymbol.PROP_ARGUMENTS]` - `true` if pseudo-element keyword accepts arguments.

#### Pseudo-classes
{#css-pseudo-classes}

**Namespace**: `css`

**Kind**: `pseudo-classes`

Web Symbols representing available [CSS pseudo-classes](https://developer.mozilla.org/en-US/docs/Web/CSS/Pseudo-classes).
Symbols names *should not* be prefixed with `:`.
CSS pseudo-classes can be contributed statically or dynamically (through [`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt)).
The statically contributed symbols are available globally, depending on the context setting of the contributing Web Types file.

The additional scope for matching pseudo-classes is built from Web Symbols matching HTML element name preceding the pseudo-class keyword.

For dynamic contributions, the `WebSymbolsQueryConfigurator.getScope()`'s `element` parameter can be:
- `CssPseudoSelector` - Web Symbols should represent available CSS pseudo-classes for matching. The CSS pseudo-class actual name should not be taken into account when building the scope.

Dedicated support for `WebSymbol` interface properties:
- `properties[WebSymbol.PROP_ARGUMENTS]` - `true` if pseudo-class keyword accepts arguments.

#### Functions
{#css-functions}

**Namespace**: `css`

**Kind**: `functions`

Web Symbols representing available [CSS functions](https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Functions).
CSS functions can be contributed statically or dynamically (through [`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt)).
The statically contributed symbols are available globally, depending on the context setting of the contributing Web Types file.

The additional scope for matching functions is built from Web Symbols matching the CSS property name, the value of which is being calculated.

For dynamic contributions, the `WebSymbolsQueryConfigurator.getScope()`'s `element` parameter can be:
- `CssFunction` - Web Symbols should represent available CSS functions for matching. The CSS function actual name should not be taken into account when building the scope.

#### Classes
{#css-classes}

**Namespace**: `css`

**Kind**: `classes`

Web Symbols representing available CSS classes.
Symbols names *should not* be prefixed with `.`.
CSS classes can be contributed statically or dynamically (through [`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt)).
The statically contributed symbols are available globally, depending on the context setting of the contributing Web Types file.

Within CSS file, an additional scope for matching classes is built from Web Symbols matching HTML element name preceding the class keyword.

Within HTML attribute `class` the additional scope for matching classes is built from Web Symbols matching the enclosing HTML element name.

For dynamic contributions, the `WebSymbolsQueryConfigurator.getScope()`'s `element` parameter can be:
- `CssClass` - Web Symbols should represent available CSS classes for matching. The CSS class actual name should not be taken into account when building the scope.
- [`XmlAttributeValue`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/xml/XmlAttributeValue.java) - Web Symbols should represent available CSS classes for matching within the attribute value.

#### Parts
{#css-parts}

<primary-label ref="2023.2"/>

**Namespace**: `css`

**Kind**: `parts`

Web Symbols representing available HTML element parts for matching with [CSS `::part`](https://developer.mozilla.org/en-US/docs/Web/CSS/::part) pseudo-element.
CSS parts can be contributed statically or dynamically (through [`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt)).
The statically contributed symbols are available globally, depending on the context setting of the contributing Web Types file.

Within CSS file, an additional scope for matching classes is built from Web Symbols matching HTML element name preceding the `::part` keyword.

For dynamic contributions, the `WebSymbolsQueryConfigurator.getScope()`'s `element` parameter can be:
- `CssTermImpl` - Web Symbols should represent available CSS parts for matching. The CSS part's actual name should not be taken into account when building the scope.


### JavaScript

#### String Literals
{#js-string-literals}

<primary-label ref="2023.2"/>

**Namespace**: `js`

**Kind**: `string-literals`

Web Symbols representing JavaScript or TypeScript string literals available in a particular location.
Only dynamically contributed string literal symbols (through [`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt)) have built-in support.

The `WebSymbolsQueryConfigurator.getScope()`'s `element` parameter can be:
- a `JSLiteralExpression`
- an unqualified `JSReferenceExpression`,
  which parent is *not* `JSIndexedPropertyAccessExpression`, `JSCallExpression` or `JSProperty`

#### Properties
{#js-properties}

<primary-label ref="2023.2"/>

**Namespace**: `js`

**Kind**: `properties`

Web Symbols represent properties of an object, which is a result of a JavaScript or TypeScript expression.
Only dynamically contributed properties symbols (through [`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt)) have built-in support.

The `WebSymbolsQueryConfigurator.getScope()`'s `element` parameter can be:
  - `JSObjectLiteralExpression` - Web Symbols should represent expected properties
  - `JSExpression` - Web Symbols should represent available properties the expression's result.
    Parent expression is `JSReferenceExpression` or `JSIndexedPropertyAccessExpression`.

Dedicated support for `WebSymbol` interface properties:
  - `type` - if the type is `JSType`, it will be used in JavaScript type evaluator as the type of the property
  - `required` - the JavaScript property is treated as non-optional
  - `properties[WebSymbol.PROP_READ_ONLY]` - the JavaScript property is treated as read-only

#### Symbols
{#js-symbols}

<primary-label ref="2023.2"/>

**Namespace**: `js`

**Kind**: `symbols`

Web Symbols representing JavaScript symbols available for resolve of an unqualified JavaScript reference expression.
Only dynamically contributed symbols (through [`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt)) have built-in support.

The `WebSymbolsQueryConfigurator.getScope()`'s `element` parameter can be:
- an unqualified `JSReferenceExpression` - Web Symbols should represent possible symbols to which JavaScript reference could resolve. The reference to actual name should not be taken into account when building the scope.

Dedicated support for `WebSymbol` interface properties:
- `type` - if the type is `JSType`, it will be used in JavaScript type evaluator as the type of the symbol
- `properties[WebSymbol.PROP_KIND]` - the kind of the symbol, one of [`WebSymbolJsKind`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/js/WebSymbolJsKind.kt) enum values. The kind will be used to render the appropriate icon in the code completion popup

  *Integration with unqualified reference resolution is not available in TypeScript code*

#### DOM Events
{#js-events}
**Namespace**: `js`

**Kind**: `events`

Web Symbols representing available DOM events.
DOM events can be contributed statically or dynamically (through [`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt)).
The statically contributed symbols are available globally, depending on the context setting of the contributing Web Types file.

The additional scope for matching DOM events is built from Web Symbols matching enclosing HTML element name.

For dynamic contributions, the `WebSymbolsQueryConfigurator.getScope()`'s `element` parameter can be:
- [`HtmlTag`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/html/HtmlTag.java) - Web Symbols should represent available DOM events for matching.


## Using Patterns for Domain-Specific Mappings

When a language feature, such as an HTML element, is capable of representing an entity from a framework,
like a Vue component, it may be beneficial to use a custom symbol kind with a domain-specific name (for example, `vue-components`),
and establish a mapping from this to the language feature via a pattern:

```json
{
  "contributions": {
    "html": {
      "elements": [
        {
          "name": "Vue component",
          "pattern": {
            "items": "/html/vue-components"
          },
          "attributes": [
            {
              "name": "Component property",
              "pattern": {
                "or": [
                  {
                    "items": "props",
                    "priority": "highest"
                  }
                ]
              },
              "value": {
                "type": "of-match"
              }
            }
          ]
        }
      ]
    }
  }
}
```

Note that in this example, attributes are produced from `html/props` symbols.
For Vue component, a set of named values, which can be provided to the component instance are called `props`.
Now, our Vue component definition can have a more domain-specific look:

```json
{
  "contributions": {
    "html": {
      "vue-components": [
        {
          "name": "MyVueComponent",
          "description": "This is the component you always needed in your application",
          "props": [
            {
              "name": "listen-to",
              "type": "string | HTMLElement | Document | Window | (() => HTMLElement)",
              "description": "The scrolling element to listen to.",
              "default": "document"
            }
          ]
        }
      ]
    }
  }
}
```
