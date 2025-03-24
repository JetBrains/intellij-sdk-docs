<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Web Types
<primary-label ref="2022.3"/>

<link-summary>Web Types - contributing statically defined Web Symbols through JSONs.</link-summary>

Web Types is a JSON metadata format, which provides an easy way to contribute statically defined Web Symbols.
The JSON Web Types detailed schema can be accessed by
[following this link](https://github.com/JetBrains/web-types/blob/master/schema/web-types.json).
The format is open source and IDE-agnostic by itself.
However, currently it is being actively used mostly by JetBrains IDEs.

Originally, it was created to facilitate the contribution of statically defined symbols for the
[Vue](https://vuejs.org/) framework, which may explain the presence of some deprecated properties in the schema.

A simple Web Types file looks as follows, where this file defines a `my-element` HTML element with a `foo` attribute:

```JSON
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
          "doc-url": "https://example.com/docs/my-element",
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

## File Structure

The Web Types file should, at least, contain `name`, `version` and `contributions` properties.
It should also include `$schema` property which can be either
`https://raw.githubusercontent.com/JetBrains/web-types/master/schema/web-types.json`
or
`http://json.schemastore.org/web-types`.
The schema contains detailed documentation for all the JSON entities.

Directly under `contributions` property are listed namespaces with their contributions.
Currently only `html`, `css` or `js` namespaces are allowed.
However, in the future this limitation will be lifted to support Web Types for other technologies.

The `namespace` object contains symbol kind names listed as properties.
Some symbol kinds are predefined and directly supported by IDE (see [](#direct-support) for reference).
The kind of symbol should be related to its role.
For instance, a Vue directive should be of the kind `vue-directives`.
Each framework should define a set of custom symbol kinds if needed.
Reference for the most important symbol kinds defined by frameworks supported by IDEs is below.

Each symbol kind name property of a namespace object is an array of symbol contributions.
A symbol contribution should have at least a name.
Contributions in addition to standard properties can define sub-contributions and custom properties.
A custom property is a JSON property, whose value is of a simple type (string, number or boolean),
or is an array of simple types.
If a contribution’s JSON property’s value is an object value or an array of objects, it is treated as a list of sub-contributions.
Such contributions will be assigned to the same namespace as the parent contributions.
To use a different namespace for sub-contributions, nest symbol kind JSON property name under a `js`, `css` or `html` property, e.g.:

```JSON
{
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
}
```

In the example below, Web Types contributes information that the `my-element` HTML element supports a custom CSS property `--bg-color`.
The `attributes` are implicitly under the `html` namespace.
To contribute a `foo` attribute, one could also write it in longer form:

```JSON
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

Each Web Types contribution is represented in the Web Symbols framework by a
[`PsiSourcedWebSymbol`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/PsiSourcedWebSymbol.kt)
object.
All the Web Types contributions are mapped one-to-one, and custom properties are accessible through `properties` property.

## Including Web Types

Web Types can currently be discovered by the IDE in the following ways:

{style="full"}
NPM
: The IDE will automatically discover any Web Types shipped with the NPM library and specified in the
`web-types` property of `package.json`.
The property accepts a string or an array of strings with relative paths to Web Types files shipped with the package.

Local Project
: In your JavaScript projects in `package.json` files, you can specify `web-types` property similarly to the NPM package.
The property accepts a string or an array of strings with relative paths to Web Types files within the project.

IDE Plugin
: You can ship Web Types JSON with your IDE plugin.
To point an IDE to its location, use <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.webSymbols.webTypes"/></include> and pass the file location in `source` attribute value.
With `enableByDefault` attribute, you can choose whether the Web Types file should be contributed to Web Symbols scope by default,
or only if an NPM package with the same name is present in the project.

## Special Properties

{style="full"}
`inject-language`
: Supported by `html/elements` and `html/attributes`, allows to inject the specified language into HTML element text or HTML attribute value.

`doc-hide-pattern`
: If a symbol uses a RegEx pattern, usually it will be displayed in a documentation popup section "pattern".
Setting this property to `true` hides that section.

`hide-from-completion`
: By default, all symbols show up in code completion.
Setting this property to `true` prevents a symbol from showing up in the code completion.

## Symbol Kinds

Web Types files are used internally by IDEs and tools to define rules for frameworks.
Following is the reference for symbol kinds used by framework support.

### Direct Support

IDEs provide direct support for the following symbol kinds:
- `html/elements`
- `html/attributes`
- `css/properties`
- `css/pseudo-classes`
- `css/pseudo-elements`
- `css/functions`
- `css/classes`
- `css/parts` (*since 2023.2*)

Prior to 2023.1, IDEs were required to have JavaScript plugin installed for the support to work.

### Angular

Angular plugin Web Types are available
[here](%gh-ij-plugins%/Angular/resources/web-types)
for reference.
Any Web Types file targeting only Angular support should have `framework` property set to `angular`.
Highlights: `js/ng-custom-events` contribute symbols with patterns for any custom events supported by Angular `EventManager`s, e.g.:

```JSON
{
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
        }
      ]
    }
  ]
}
```

### Vue

Vue plugin Web Types are available
[here](%gh-ij-plugins%/vuejs/resources/web-types)
for reference.
Any Web Types file targeting only Vue support should have `framework` property set to `vue`.
Highlights:

{style="full"}
`/html/vue-components`
: Use `/html/vue-components` to contribute Vue components.

`/html/vue-directives`
: Use `/html/vue-directives` to contribute Vue directives.
Use `attribute-value` property to specify the type of value expression. E.g.:
```JSON
{
    "attribute-value": {
      "type": "boolean",
      "required": true
    }
}
```

`/html/vue-file-top-elements`
: Use `/html/vue-file-top-elements` to contribute any custom top-level elements available in Vue Single Component File

A Vue `/html/vue-components` contribution supports:

{style="full"}
`/html/props`
: Use `/html/props` to contribute Vue component props, e.g:
```JSON
{
    "props": [{
      "name": "appear",
      "description": "Whether to apply transition on initial render.",
      "type": "boolean",
      "default": "false"
    }]
}
```

`/html/slots`
: Use `/html/slots` to contribute Vue component slots.
For scoped slots, use `vue-properties` to provide a list of scoped slot properties.
Example:
```JSON
{
    "slots": [{
      "name": "img",
      "description": "Expects the [v-img](/components/images) component.",
      "doc-url": "https://vuetifyjs.com/en/api/v-app-bar/#slots",
      "vue-properties": [
        {
          "name": "props",
          "type": "{ height: string, src: string | srcObject }"
        }
      ]
    }]
}
```

`/js/events`
: Use `/js/events` to contribute Vue component events, e.g.:
```JSON
{
    "js": {
      "events": [
        {
          "name": "input",
          "description": "The updated bound model"
        }
      ]
    }
}
```

`html/vue-model`
: Use `html/vue-model` to contribute settings for Vue model directive. E.g.:
```JSON
{
    "vue-model": {
      "prop": "show",
      "event": "input"
    }
}
```


A Vue `/html/vue-directives` contribution supports:

{style="full"}
`/html/argument`
: Use `/html/argument` as a Vue directive argument. E.g.:
```JSON
{
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
}
```

`/html/modifiers`
: Use `/html/modifiers` as a Vue directive modifier. E.g.:
```JSON
{
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
}
```


### Web Components

For Lit support, install it in your Node project as a `devDependency` `@web-types/lit`.
Web Types are available
[here](https://github.com/JetBrains/web-types/blob/master/packages/lit/lit%402.0.0/lit.web-types.json)
for reference.

Web Components should use:

- `/html/attributes`: for attributes available in HTML
- `/js/properties`: for Web Component DOM class properties
- `/js/events`: for Web Component events

Example Web Component:

```JSON
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
          "doc-url": "https://example.com/docs/cool-component",
          "attributes": [
            {
              "name": "color",
              "description": "Choose color for coolness",
              "default": "blue",
              "required": false,
              "doc-url": "https://example.com/docs/cool-component#attrs",
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
