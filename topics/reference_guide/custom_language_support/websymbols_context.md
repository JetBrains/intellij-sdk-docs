<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Web Symbols Context
<primary-label ref="2022.3"/>

<link-summary>How to use Web Symbols context detection to manage enablement of plugin features.</link-summary>

One of the important qualities of well-written plugins is the enablement of its features
only when needed. For instance, if a user does not have a particular web framework in their project,
HTML files should not contain that framework-specific assistance.

Web Symbols framework provides various ways to detect current context
and retrieve it through `WebSymbolsContext.get(kind, ...)` methods.

For a particular `kind` of Web Symbol context, there is only one `name` detected, and it can be `null` if
the context `kind` is not present in the location.

## Location - `PsiElement` vs `VirtualFile`

The location can either be a `PsiElement`, or a `VirtualFile` with a `Project`. If the location is `PsiElement`,
the PSI tree of the containing file might be checked by `WebSymbolsContextProvider`, for instance, for some imports.
If the location is a `VirtualFile`, the PSI tree will not be acquired, so the resulting context may differ
from the one acquired on a `PsiElement` location. This is expected. The `VirtualFile` location is used to,
amongst others, determine a language to parse the file, so it cannot use a PSI tree. It must also be fast,
since it's used during the indexing phase.

Usually, the coding assistance logic is working with `PsiElement`, so it should be provided as the location
at which context is checked. It is important, though, to remember that the language of the file might only
be substituted if a context is detected on `VirtualFile` level.

A good example of how context detection can be used is web frameworks, since at a particular location only
one of the frameworks can be used. Various `WebSymbolsContextProvider` extensions and context rules are used
to determine which of the frameworks (Vue, Angular, React, Astro, etc.) to enable at the particular location.
And each of the plugins calls `WebSymbolsContext.get("framework", ...)` to check if it's their framework,
which is enabled.

## `WebSymbolsContextProvider`

The most straightforward way to contribute context is to register a [`WebSymbolsContextProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/context/WebSymbolsContextProvider.kt)
through <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.webSymbols.context"/></include> and override one of `isEnabled()` methods
to detect a context of a particular name and kind, e.g.:

```xml
<idea-plugin>
  <extensions defaultExtensionNs="com.intellij">
    <webSymbols.context
        kind="stimulus-context" name="true"
        implementation="com.intellij.stimulus.context.StimulusContextProvider"/>
  </extensions>
</idea-plugin>
```

The `StimulusContextProvider` can, for instance, check for JavaScript imports in the file to see if some additional Stimulus support
should be present in the file. The result should be cached, as `WebSymbolsContextProvider` methods are called very often.

```kotlin
class StimulusContextProvider : WebSymbolsContextProvider {
  override fun isEnabled(file: PsiFile): Boolean {
    if (file is HtmlCompatibleFile) {
      return CachedValuesManager.getCachedValue(file) {
        CachedValueProvider.Result.create(hasStimulusImport(file), file)
      }
    }
    return super.isEnabled(file)
  }
}
```

The presence of the context can be checked as follows:

```kotlin
WebSymbolsContext.get("stimulus-context", psiElement) == "true"
```

`WebSymbolsContextProvider` can also prevent a context from being detected. In this case, the `isForbidden` method should be overridden.
To prevent any context of a particular kind, use `any` as a name, e.g.:

```xml
<idea-plugin>
  <extensions defaultExtensionNs="com.intellij">
    <webSymbols.context
        kind="framework" name="any"
        implementation="com.intellij.python.js.PyTemplatesWebContextBlocker"/>
  </extensions>
</idea-plugin>
```

In this example, the `PyTemplatesWebContextBlocker` can check if the project has any Python templating engine enabled in the
location and forbid the `framework` context to disable web frameworks support, like Angular or Vue, which conflict with e.g., Blade support.

## Context Rules

`WebSymbolsContextProvider` is straightforward to use, but it is not very efficient. When many providers look for similar information,
a lot of calculations are repeated, affecting the overall performance of the IDE. One of the examples is when providers look for the presence
of some package manager dependency (Node package, Ruby Gem, Maven or Gradle dependency, etc.). To optimize this lookup, context rules can be provided.

### Web Types with Context Rules

One of the ways to provide context rules is through a [Web Types](websymbols_web_types.md) file.
A top-level property `context-config` should be specified, e.g.:

```json
{
  "$schema": "https://json.schemastore.org/web-types",
  "name": "vue-store-contexts",
  "version": "0.0.0",
  "contexts-config": {
    "vue-store": {
      "vuex": {
        "enable-when": {
          "node-packages": [
            "vuex"
          ],
          "ide-libraries": [
            "vuex"
          ]
        }
      },
      "pinia": {
        "enable-when": {
          "node-packages": [
            "pinia"
          ]
        }
      }
    }
  }
}
```

The first level property under `contexts-config` is the name of context `kind` and the next level property is the context `name`
for which the `enable-when` and `disable-when` rules are provided.

In IDEs prior to 2024.2, only a deprecated syntax is supported, where the first level property under `contexts-config` is the context `name`
and the context kind needs to be specified through `kind` property, e.g.:

```json
{
  "$schema": "https://json.schemastore.org/web-types",
  "name": "vue-store-contexts",
  "version": "0.0.0",
  "contexts-config": {
    "vuex": {
      "kind": "vue-store",
      "enable-when": {
        "node-packages": [
          "vuex"
        ],
        "ide-libraries": [
          "vuex"
        ]
      }
    },
    "pinia": {
      "kind": "vue-store",
      "enable-when": {
        "node-packages": [
          "pinia"
        ]
      }
    }
  }
}
```

Supported `enabled-when` rules are:
- `file-extensions` - file extension without leading `.`
- `file-name-patterns` - file name regular expression pattern
- `ide-libraries` - name of a JavaScript library user preconfigured in the IDE
- `project-tool-executables` - name of a tool executable present in the project, i.e., in `node_modules/.bin`
- `node-packages` - name of a Node.js package dependency
- `ruby-gems` - name of a Ruby gem

For `disable-when` supported rules are:
- `file-extensions` - file extension without leading `.`
- `file-name-patterns` - file name regular expression pattern

#### Context Proximity

When rules are processed, for each rule match, a proximity score is calculated. A lower score means that the match
is closer to the location. For instance, `file-extensions` and `file-name-patterns` match have proximity `0.0`, which
means that it's a perfect match. `ide-libraries`, or `ruby-gems` have match of value `Double.MAX_VALUE`,
because they match at project or module level.

`node-packages` rule, on the other hand, takes into account the layout of the file system and importance of the dependency,
because it looks for `package.json` files in the directory hierarchy of a `VirtualFile` location.
If there is a `package.json` file in the same directory as the `VirtualFile` location, it will have base proximity score `0.0`,
if it's in a parent directory, it will have base score `1.0`, etc. Next, the dependency importance is added to the base score:
- `peerDependecies` - `0.1`
- `bundledDependencies` - `0.2`
- `dependencies` - `0.3`
- `optionalDependencies` - `0.4`
- `devDependencies` - `0.5`
- indirect dependencies found in `node_modules` - `0.6`

After all rules have been processed, a context `kind` gets assigned the `name` with the lowest proximity match.

#### Shipping Web Types

Web Types can be embedded with a plugin by pointing to the file via extension point:

```xml
<idea-plugin>
  <extensions defaultExtensionNs="com.intellij">
    <webSymbols.webTypes
        source="web-types/vue-store-contexts@0.0.0.web-types.json"
        enableByDefault="true"/>
  </extensions>
</idea-plugin>
```

Web Types are designed to correlate to package manager dependencies, so their name should correspond to a
package dependency. However, in this case, we only want to contribute some context rules, which do not
correspond to any dependency, so we can name the file as we want to. Currently, only Node Package Manager
is supported, and JavaScript plugin must be loaded for the extension point to work. In the future, support
for other package managers should be added and extension point should work regardless of the presence of JavaScript plugin.

If context rules are tied to the presence of some NPM dependency, use the dependency name and
choose the minimal version, for which the Web Types should be loaded.

If context rules should always be loaded, then any name (preferably not used by any dependency) should be used
for the dependency and the `enableByDefault` attribute set to `true`.

### `WebSymbolsContextRulesProvider`

Context rules can also be provided dynamically through [`WebSymbolsContextRulesProvider`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/context/WebSymbolsContextRulesProvider.kt).

To do that, register a [`WebSymbolsQueryConfigurator`](%gh-ic%/platform/webSymbols/src/com/intellij/webSymbols/query/WebSymbolsQueryConfigurator.kt)
through <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.webSymbols.queryConfigurator"/></include> and implement `getContextRulesProviders()`.
It is important that the results are stable, because any unexpected change in the rules will cause
rescanning of the project, dropping of all caches and restarting code analysis.

## `.ws-context` File
<primary-label ref="2024.1"/>

> The `.ws-context` file support is available since 2024.1.2.

The user can force a context with a `.ws-context` JSON file, e.g.:
```json
{
  "framework": "vue",
  "angular-template-syntax": "V_2",
  "src/**/app/**": {
    "framework": null,
    "app.component.html" : {
      "framework": "angular",
      "angular-template-syntax": "V_17"
    }
  },
  "src/**/p*-editor/*.html" : {
    "framework" : "angular"
  }
}
```

There are two types of properties supported in the file:
- `<context kind>` -> `<context name>`
- `<GLOB path>` -> context details object

The paths can be nested for simplicity. The last segment of the GLOB path is the file name pattern, which supports only `*` wildcard.
If the last segment is `**`, it matches all nested directories and files. Top level context properties are assumed to have `/**` pattern.

When choosing between different patterns matching the same file name, the following approach is taken:

1. Choose a pattern with the most path segments excluding `**` segments.
2. Choose a pattern which has a file name pattern (i.e., doesn't end with `**` or `/`).
3. Choose a pattern that was defined first.

## `WebSymbolsContextSourceProximityProvider`

`WebSymbolsContextSourceProximityProvider` allows providing evaluation logic for the context rules. It should be used when
working on integrating support for a package manager or a language which has a way to define global libraries. The provider should be registered
through <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.webSymbols.contextSourceProximityProvider"/></include>.

The provider has a single method to be overridden — `calculateProximity()`. When `sourceKind` parameter matches the provider requirements,
it should calculate the [proximity](#context-proximity) of each source provided by `sourceNames`. For instance, when supporting a package manager and
the method is called with `sourceKind` being a `PackageManagerDependency` with the appropriate name, the provider should calculate the [proximity](#context-proximity)
of each dependency provided through `sourceNames` parameter. The `Result` object contains also a `modificationTrackers` set field, which
is used to track when the cached results should be recalculated. It is crucial to have as few trackers as possible and refresh the cache as seldom as possible
because the results are used in every call to the `WebSymbolContext.get()` method.

