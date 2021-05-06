[//]: # (title: GoLand Extension Point List)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

14 Extension Points (EP) for GoLand

See [Plugin Extensions](plugin_extensions.md) on how to declare extensions in your plugin.
  
**Extension Point** searches for usages inside existing implementations of open-source IntelliJ Platform plugins via [IntelliJ Platform Explorer](https://jb.gg/ipe).

**Implementation** is related EP class. 

#### Note Legend

| Icon | Description | Details |
|---|---|---|
| ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | Non-Dynamic Extension Point | Installation/update of plugin requires restart ([Dynamic Plugins](dynamic_plugins.md)) |
| ![Experimental API](https://img.shields.io/badge/-Experimental_API-red) | Experimental API | Implementation annotated with [`@ApiStatus.Experimental`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/ApiStatus.java), API might be altered or removed without prior notice |
| ![Internal API](https://img.shields.io/badge/-Internal_API-red) | Internal API | Implementation annotated with [`@ApiStatus.Internal`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/ApiStatus.java), should not be used by 3rd party |
| ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | Project-Level Extension Point | Declared with `area="IDEA_PROJECT"`, can have `Project` as constructor parameter |

## GoLand

### goland.xml
goland.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.goide.dlv.positionConverterFactory](https://jb.gg/ipe?extensions=com.goide.dlv.positionConverterFactory) | `DlvPositionConverterFactory` | 
| [com.goide.documentation.packageVersionProvider](https://jb.gg/ipe?extensions=com.goide.documentation.packageVersionProvider) | `GoDocumentationPackageVersionProvider` | 
| [com.goide.executorExtension](https://jb.gg/ipe?extensions=com.goide.executorExtension) | `GoExecutorExtension` | 
| [com.goide.externalToolIntegrator](https://jb.gg/ipe?extensions=com.goide.externalToolIntegrator) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `GoExternalToolIntegrator` | 
| [com.goide.highlighting.errorAnnotatorSuppressor](https://jb.gg/ipe?extensions=com.goide.highlighting.errorAnnotatorSuppressor) | `GoErrorAnnotatorSuppressor` | 
| [com.goide.importPathsProvider](https://jb.gg/ipe?extensions=com.goide.importPathsProvider) | `GoImportPathsProvider` | 
| [com.goide.importResolver](https://jb.gg/ipe?extensions=com.goide.importResolver) | `GoImportResolver` | 
| [com.goide.imports.weigher](https://jb.gg/ipe?extensions=com.goide.imports.weigher) | `GoImportsWeigher` | 
| [com.goide.importsFilter](https://jb.gg/ipe?extensions=com.goide.importsFilter) | `GoImportsFilter` | 
| [com.goide.packageFactory](https://jb.gg/ipe?extensions=com.goide.packageFactory) | `GoPackageFactory` | 
| [com.goide.rootsProvider](https://jb.gg/ipe?extensions=com.goide.rootsProvider) | `GoRootsProvider` | 
| [com.goide.runConfigurationExtension](https://jb.gg/ipe?extensions=com.goide.runConfigurationExtension) | `GoRunConfigurationExtension` | 
| [com.goide.sdkProvider](https://jb.gg/ipe?extensions=com.goide.sdkProvider) | `GoSdkProvider` | 
| [com.goide.support](https://jb.gg/ipe?extensions=com.goide.support) | `GoLangSupport` | 

