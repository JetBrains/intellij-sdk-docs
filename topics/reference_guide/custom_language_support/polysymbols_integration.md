<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Poly Symbols Integration with Language Features
<primary-label ref="2025.2"/>

<link-summary>How to integrate your Poly Symbols with a particular language feature.</link-summary>

IDEs provide built-in support for Poly Symbols integration with the main language features of HTML, CSS, JavaScript, and TypeScript.
Contribution of static symbols can be achieved through Web Types or Custom Elements Manifest.
The dynamic symbols, as a result of source code analysis, should be contributed through [`PolySymbolQueryScopeContributor`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryScopeContributor.kt).

The concept of contributing symbols is similar to how code completion works.
Platform runs multiple code completion contributors to build a list of available items for a particular place in the code.
Later on,the list is displayed to the user.
Query executor, on the other hand, uses a list of contributed [`PolySymbolScope`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolScope.kt)s at a given location to either list all available symbols, get code completions, or match a name against the list of available symbols.

The implementations should register query scope providers by calling the `registrar` parameter methods.
The registrar works as a builder, but each call creates a new instance, so you can reuse build stages, for example:

```kotlin
registrar
  .inFile(AstroFileImpl::class.java)
  .inContext { it.framework == AstroFramework.ID }
  .apply {
    // Default scopes
    forAnyPsiLocationInFile()
      .contributeScopeProvider {
        mutableListOf(
          AstroFrontmatterScope(it.containingFile as AstroFile),
          AstroAvailableComponentsScope(it.project)
        )
      }

    // AstroStyleDefineVarsScope
    forPsiLocation(CssElement::class.java)
      .contributeScopeProvider { location ->
        location.parentOfType<XmlTag>()
          ?.takeIf {
            it.name.equals(HtmlUtil.STYLE_TAG_NAME, ignoreCase = true)
          }
          ?.let { listOf(AstroStyleDefineVarsScope(it)) }
        ?: emptyList()
      }
  }
```

The order in which the builder methods are called does not affect the final performance.
Framework reorders conditions in the best way to efficiently match contributors with locations in the code.

### A Symbol Referencing Pattern

A common pattern for contributing symbols is to use a reference pattern to map from one kind of symbols to the other.
Let's say that we can produce a list of symbols representing message bundle file property keys.
Such symbols could have a qualified kind named: `message-bundle/properties`.
Let's say that these message bundle properties can be used as string literal parameters to some Java method calls.
A scope contributor would provide a scope containing all the `properties` symbols and a symbol with a reference pattern, which would map from `message-bundle/properties` to the ` java/string-literals ` kind.
A convenient way of defining such a symbol is to use the `ReferencingPolySymbol` class.

As a result, we can say that in a particular place in the code, all provided message bundle properties should be treated as possible string literal values.
Such an approach allows symbol contributors to abstract from the actual implementation details of registering completion providers or reference providers in a particular language and focus on building the symbol model.

## Supported Language Features

### HTML

#### Elements
{#html-elements}

**Namespace**: `html`

**Kind**: `elements`

Poly Symbols representing available HTML elements.
HTML element symbols can be contributed statically or dynamically (through [`PolySymbolQueryScopeContributor`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryScopeContributor.kt)).
The statically contributed symbols are available globally, depending on the context setting of the contributing Web Types file.

For dynamic contributions, the contributors should be registered with the following `PsiElement` classes:
- [`HtmlTag`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/html/HtmlTag.java) - Poly Symbols should represent available HTML elements.
  The HTML tag's actual name should not be taken into account when building the scope.
- `CssElement` - Poly Symbols should represent available HTML elements within a particular CSS file.

The matched Poly Symbol tag is taken as a scope for matching HTML attributes of the tag.

#### Attributes
{#html-attributes}

**Namespace**: `html`

**Kind**: `attributes`

Poly Symbols representing available HTML attributes.
HTML attribute symbols can be contributed statically or dynamically (through [`PolySymbolQueryScopeContributor`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryScopeContributor.kt)).
The statically contributed symbols are available globally, depending on the context setting of the contributing Web Types file.
If the containing tag is matched to a Poly Symbol, it is added to the scope for attribute matching.

For dynamic contributions, the contributors should be registered with the following `PsiElement` classes:
- [`XmlAttribute`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/xml/XmlAttribute.java) - Poly Symbols should represent available HTML attributes for matching.
  The HTML attribute's actual name should not be taken into account when building the scope.
  However, the parent HTML tag and other attributes can be taken into account when building the scope.
- [`HtmlTag`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/html/HtmlTag.java) - Poly Symbols should represent available HTML attributes for matching.
  The HTML tag and other attributes can be taken into account when building the scope.

Dedicated support for `PolySymbol` modifiers:
- `required` - if present, a warning will be shown if the attribute is missing.
  Does not apply to `virtual` attributes.
- `optional` - during `PolySymbolMatch` modifiers merge, only one of `required` and `optional` modifiers will be present in the final set.
  The `optional` modifier may be used to force the attribute to be not `required`
- `virtual` - specifies virtual attributes used by web frameworks.
  Usually they are used during template compilation and missing from the final DOM.

Dedicated support for `PolySymbol` `get` properties:
- `PROP_HTML_ATTRIBUTE_VALUE` - A special property to support symbols representing HTML attributes.
  It can specify the kind (`plain`, `expression`, `no-value`), type (`boolean`, `number`, `string`, `enum`, `complex`, `of-match`), whether an attribute value is required, a default value, and the result type of value expression in the appropriate language.
  If the `COMPLEX` type is set, the value of `langType` will be used, and if `OF_MATCH`, the type of the `symbol` will be used.
  When merging information from several segments in the PolySymbolMatch, the first non-null property values take precedence.
  By default - when properties are `null` - the attribute value is of plain type and is required.

### CSS

#### Properties
{#css-properties}

**Namespace**: `css`

**Kind**: `properties`

Poly Symbols representing available CSS properties. [Custom CSS properties (variables)](https://developer.mozilla.org/en-US/docs/Web/CSS/Using_CSS_custom_properties) names should be prefixed with `--`.
CSS properties can be contributed statically or dynamically (through [`PolySymbolQueryScopeContributor`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryScopeContributor.kt)).
The statically contributed symbols are available globally, depending on the context setting of the contributing Web Types file.

Within a CSS file, the additional scope for matching properties is built from Poly Symbols matching HTML element names from terminal selectors from the enclosing ruleset.

Within an HTML element `style` attribute value, an additional scope is built from Poly Symbols matching the enclosing HTML element.

Scope for custom properties (variables) references (arguments for [`var()`](https://developer.mozilla.org/en-US/docs/Web/CSS/var) function) is built the same way
as for properties.
Only properties with names starting with `--` are taken into account.

For dynamic contributions, the contributors should be registered with the following `PsiElement` classes:
- `CssDeclaration` - Poly Symbols should represent available CSS properties for matching.
  The CSS declaration's actual name should not be taken into account when building the scope.

#### Pseudo-elements
{#css-pseudo-elements}

**Namespace**: `css`

**Kind**: `pseudo-elements`

Poly Symbols representing available [CSS pseudo-elements](https://developer.mozilla.org/en-US/docs/Web/CSS/Pseudo-elements).
Symbols names *should not* be prefixed with `::`.
CSS pseudo-elements can be contributed statically or dynamically (through [`PolySymbolQueryScopeContributor`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryScopeContributor.kt)).
The statically contributed symbols are available globally, depending on the context setting of the contributing Web Types file.

The additional scope for matching pseudo-elements is built from Poly Symbols matching HTML element name preceding the pseudo-element keyword.

For dynamic contributions, the contributors should be registered with the following `PsiElement` classes:
- `CssPseudoSelector` - Poly Symbols should represent available CSS pseudo-elements for matching.
  The CSS pseudo-element actual name should not be taken into account when building the scope.

Alternatively, `CSS_PSEUDO_ELEMENTS_SYMBOL_QUERY_PATTERNS` constant may be used as the pattern.

Dedicated support for `PolySymbol` `get` properties:
- `PROP_CSS_ARGUMENTS` - `true` if pseudo-element keyword accepts arguments.

#### Pseudo-classes
{#css-pseudo-classes}

**Namespace**: `css`

**Kind**: `pseudo-classes`

Poly Symbols representing available [CSS pseudo-classes](https://developer.mozilla.org/en-US/docs/Web/CSS/Pseudo-classes).
Symbols names *should not* be prefixed with `:`.
CSS pseudo-classes can be contributed statically or dynamically (through [`PolySymbolQueryScopeContributor`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryScopeContributor.kt)).
The statically contributed symbols are available globally, depending on the context setting of the contributing Web Types file.

The additional scope for matching pseudo-classes is built from Poly Symbols matching HTML element name preceding the pseudo-class keyword.

For dynamic contributions, the contributors should be registered with the following `PsiElement` classes:
- `CssPseudoSelector` - Poly Symbols should represent available CSS pseudo-classes for matching.
  The CSS pseudo-class actual name should not be taken into account when building the scope.

Alternatively, `CSS_PSEUDO_CLASSES_SYMBOL_QUERY_PATTERNS` constant may be used as the pattern.

Dedicated support for `PolySymbol` `get` properties:
- `PROP_CSS_ARGUMENTS` - `true` if pseudo-element keyword accepts arguments.

#### Functions
{#css-functions}

**Namespace**: `css`

**Kind**: `functions`

Poly Symbols representing available [CSS functions](https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Functions).
CSS functions can be contributed statically or dynamically (through [`PolySymbolQueryScopeContributor`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryScopeContributor.kt)).
The statically contributed symbols are available globally, depending on the context setting of the contributing Web Types file.

The additional scope for matching functions is built from Poly Symbols matching the CSS property name, the value of which is being calculated.

For dynamic contributions, the contributors should be registered with the following `PsiElement` classes:
- `CssFunction` - Poly Symbols should represent available CSS functions for matching.
  The CSS function actual name should not be taken into account when building the scope.

Alternatively, `CSS_FUNCTIONS_SYMBOL_QUERY_PATTERNS` constant may be used as the pattern.

#### Classes
{#css-classes}

**Namespace**: `css`

**Kind**: `classes`

Poly Symbols representing available CSS classes.
Symbols names *should not* be prefixed with `.`.
CSS classes can be contributed statically or dynamically (through [`PolySymbolQueryScopeContributor`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryScopeContributor.kt)).
The statically contributed symbols are available globally, depending on the context setting of the contributing Web Types file.

Within a CSS file, an additional scope for matching classes is built from Poly Symbols matching the HTML element name preceding the class keyword.

Within HTML attribute `class`, the additional scope for matching classes is built from Poly Symbols matching the enclosing HTML element name.

For dynamic contributions, the contributors should be registered with the following `PsiElement` classes:
- `CssClass` - Poly Symbols should represent available CSS classes for matching.
  The CSS class actual name should not be taken into account when building the scope.
- [`XmlAttributeValue`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/xml/XmlAttributeValue.java) - Poly Symbols should represent available CSS classes for matching within the attribute value.

Alternatively, `CSS_CLASSES_SYMBOL_QUERY_PATTERNS` constant may be used as the pattern.

#### Parts
{#css-parts}

**Namespace**: `css`

**Kind**: `parts`

Poly Symbols representing available HTML element parts for matching with [CSS `::part`](https://developer.mozilla.org/en-US/docs/Web/CSS/::part) pseudo-element.
CSS parts can be contributed statically or dynamically (through [`PolySymbolQueryScopeContributor`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryScopeContributor.kt)).
The statically contributed symbols are available globally, depending on the context setting of the contributing Web Types file.

Within a CSS file, an additional scope for matching classes is built from Poly Symbols matching HTML element name preceding the `::part` keyword.

For dynamic contributions, the contributors should be registered with the following `PsiElement` classes:
- `CssTermImpl` - Poly Symbols should represent available CSS parts for matching.
  The CSS part's actual name should not be taken into account when building the scope.

Alternatively, `CSS_PARTS_SYMBOL_QUERY_PATTERNS` constant may be used as the pattern.

### JavaScript

#### String Literals
{#js-string-literals}

**Namespace**: `js`

**Kind**: `string-literals`

Poly Symbols representing JavaScript or TypeScript string literals available in a particular location.
Only dynamically contributed string literal symbols (through [`PolySymbolQueryScopeContributor`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryScopeContributor.kt)) have built-in support.

For dynamic contributions, the contributors should be registered with the following `PsiElement` classes:
- a `JSLiteralExpression`
- an unqualified `JSReferenceExpression`, which parent is *not* `JSIndexedPropertyAccessExpression`, `JSCallExpression` or `JSProperty`

Alternatively, `JS_STRING_LITERALS_SYMBOL_QUERY_PATTERNS` constant may be used as the pattern.

#### Properties
{#js-properties}

**Namespace**: `js`

**Kind**: `properties`

Poly Symbols represent properties of an object, which is a result of a JavaScript or TypeScript expression.
Only dynamically contributed properties symbols (through [`PolySymbolQueryScopeContributor`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryScopeContributor.kt)) have built-in support.

For dynamic contributions, the contributors should be registered with the following `PsiElement` classes:
- `JSObjectLiteralExpression` - Poly Symbols should represent expected properties
- `JSExpression` - Poly Symbols should represent available properties the expression's result.
  Parent expression is `JSReferenceExpression` or `JSIndexedPropertyAccessExpression`.

Alternatively, `JS_PROPERTIES_SYMBOL_QUERY_PATTERNS` constant may be used as the pattern.

Dedicated support for `PolySymbol` modifiers:
- `required` - the JavaScript property is treated as non-optional
- `readonly` - the JavaScript property is treated as read-only

Dedicated support for `PolySymbol` `get` properties:
- `PROP_JS_TYPE` - the type will be used in JavaScript type evaluator as the type of the property

#### Symbols
{#js-symbols}

**Namespace**: `js`

**Kind**: `symbols`

Poly Symbols representing JavaScript or TypeScript symbols available for resolve of an unqualified JavaScript reference expression.
Only dynamically contributed symbols (through [`PolySymbolQueryScopeContributor`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryScopeContributor.kt)) have built-in support.

For dynamic contributions, the contributors should be registered with the following `PsiElement` classes:
- an unqualified `JSReferenceExpression` - Poly Symbols should represent possible symbols to which JavaScript reference could resolve.
  The reference to the actual name should not be taken into account when building the scope.

Alternatively, `JS_SYMBOLS_SYMBOL_QUERY_PATTERNS` constant may be used as the pattern.

Dedicated support for `PolySymbol` `get` properties:
- `PROP_JS_TYPE` - the type will be used in JavaScript type evaluator as the type of the symbol
- `PROP_JS_SYMBOL_KIND` - the kind of the symbol, one of [`JsSymbolSymbolKind`](%gh-ic%/platform/polySymbols/src-web/com/intellij/polySymbols/js/JsSymbolSymbolKind.kt) enum values.
  The kind will be used to render the appropriate icon in the code completion popup

  *Integration with unqualified reference resolution is not available in TypeScript code*

#### DOM Events
{#js-events}
**Namespace**: `js`

**Kind**: `events`

Poly Symbols representing available DOM events.
DOM events can be contributed statically or dynamically (through [`PolySymbolQueryScopeContributor`](%gh-ic%/platform/polySymbols/src/com/intellij/polySymbols/query/PolySymbolQueryScopeContributor.kt)).
The statically contributed symbols are available globally, depending on the context setting of the contributing Web Types file.

The additional scope for matching DOM events is built from Poly Symbols matching enclosing HTML element name.

For dynamic contributions, the contributors should be registered with the following `PsiElement` classes:
- [`HtmlTag`](%gh-ic%/xml/xml-psi-api/src/com/intellij/psi/html/HtmlTag.java) - Poly Symbols should represent available DOM events for matching.
