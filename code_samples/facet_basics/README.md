# Facet Basics [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Facet in IntelliJ SDK Docs][docs:facet_basics]*

## Quickstart

Facets extend base IDE features with additional frameworks support by providing additional libraries, dependencies, technologies, and UI elements for configuring framework-specific settings.

Facet Basics represents configuration specific for a particular framework or technology, associated with a module.
SDK Facet is available to use in the `Project Settings > Facets` section.
It allows us to specify any configuration specified by the `FacetConfiguration` implementation - path to the SDK in this case.

### Extension Points

| Name                     | Implementation                      | Extension Point Class |
| ------------------------ | ----------------------------------- | --------------------- |
| `com.intellij.facetType` | [DemoFacetType][file:DemoFacetType] | `FacetType`           |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:facet_basics]: https://www.jetbrains.org/intellij/sdk/docs/reference_guide/project_model/facet.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html

[file:DemoFacetType]: ./src/main/java/org/intellij/sdk/facet/DemoFacetType.java

