# Facet Basics [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Facet in IntelliJ SDK Docs][docs:facet_basics]*

## Quickstart

Facets extend base IDE features with additional frameworks support by providing additional libraries, dependencies,
technologies, and UI elements for configuring framework-specific settings.

Facet Basics represents configuration specific for a particular framework or technology, associated with a module.
SDK Facet is available to use in the `Project Settings > Facets` section and allows to specify any configuration
specified by the `FacetConfiguration` implementation - path to the SDK in this case.

## Structure

Facet Basics
plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Extension Points

| Name                     | Implementation Class                | Interface                  |
| ------------------------ | ----------------------------------- | -------------------------- |
| `com.intellij.facetType` | [DemoFacetType][file:DemoFacetType] | [FacetType][sdk:FacetType] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:facet_basics]: https://www.jetbrains.org/intellij/sdk/docs/reference_guide/project_model/facet.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html
[docs:gradle]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html

[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[file:DemoFacetType]: ./src/main/java/org/intellij/sdk/facet/DemoFacetType.java

[sdk:FacetType]: upsource:///platform/lang-api/src/com/intellij/facet/FacetType.java
