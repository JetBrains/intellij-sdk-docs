---
title: Feature Extractor
---

Products based on the *IntelliJ Platform* may advertise a plugin for installation if it supports an additional feature from the list:
* Configuration Type
* Facet Type
* File Extensions Type
* Module Type
* Artifact Type

For it, there is a tool called [`feature extractor`](https://github.com/JetBrains/intellij-plugin-verifier/tree/master/intellij-feature-extractor/), which statically analyzes bytecode of a plugin and tries to extract values that are passed to IntelliJ API. 
However, if values are dynamically evaluated, *the extractor* may return incomplete results. If you cannot find your plugin in [the list of features](https://plugins.jetbrains.com/feature/), you can make your code easier for the analyses or ask us to manually add missed features.

## Configuration Type

When you want IDEs to show that your plugin provides to *make run configuration*, you need to implement [ConfigurationType](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/execution/configurations/ConfigurationType.java) and implement `getId()`. The feature extractor analyzes value of `getId()`.

The example of an advertisement for plugins which support *D Run Configuration*:

![Configuration Type of Feature](img/feature_extractor_configuration.png)

Refer to [Run Configurations](/basics/run_configurations.md) to get more information about how to declare this feature in your plugin.

## Facet Type

To introduce this feature you should extend [FacetType](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/facet/FacetType.java) and pass `stringId` to its constructor. Value of `stringId` parameter will be analyzed by *the feature extractor*.

The example of plugins suggestion for *jangaroo facet*:

![Facet Type of Feature](img/feature_extractor_facet.png)

Refer to [Facet](/reference_guide/project_model/facet.md) for additional information.

## File Extensions Type

The plugin can support specific file extensions. When there is a file with this extension in IDEs the hint will be shown to users to install your plugin.
You should extend [FileTypeFactory](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/fileTypes/FileTypeFactory.java) and feed supported file extensions in *createFileTypes(FileTypeConsumer)*. Values that get fed to *FileTypeConsumer's* methods are analyzed.

The example of an advertisement for plugins which support *.d extension:

![File Extensions Type of Feature](img/feature_extractor_extensions.png)

Refer to [Registering a File Type](/reference_guide/custom_language_support/registering_file_type.md) to provide this feature in the plugin.

## Module Type

If you want IDEs to advertise that your plugin can allow creating a specific module you should extend [ModuleType](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-api/src/com/intellij/openapi/module/ModuleType.java) and pass `id` parameter to its constructor.

Look [Module](/reference_guide/project_model/module.md) and [Supporting Module Types](/tutorials/project_wizard/module_types.md) for more information about this type of features.


## Artifact Type

To add this feature you should extend [ArtifactType](https://github.com/JetBrains/intellij-community/blob/master/java/compiler/openapi/src/com/intellij/packaging/artifacts/ArtifactType.java) and pass `id` parameter to its constructor. Value of `id` parameter passed to *ModuleType's* constructor is analyzed by *the extractor*.

The example of suggestion to install plugins which support a necessary *artifact*:

![Artifact Type of Feature](img/feature_extractor_artifacts.png)
