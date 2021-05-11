[//]: # (title: DataGrip Extension Point List)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

11 Extension Points (EP) for DataGrip

 > Please use only EPs listed below; others are not recommended to be used by 3rd party plugins.
 >
 {type="warning"}
         
See [Extension Point List](extension_point_list.md) for IntelliJ Platform EPs.

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

## DataGrip

### DatabasePlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.database.artifactsConfig](https://jb.gg/ipe?extensions=com.intellij.database.artifactsConfig) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `n/a` | 
| [com.intellij.database.configValidator](https://jb.gg/ipe?extensions=com.intellij.database.configValidator) | `DatabaseConfigValidator` | 
| [com.intellij.database.connectionInterceptor](https://jb.gg/ipe?extensions=com.intellij.database.connectionInterceptor) ![Internal API](https://img.shields.io/badge/-Internal_API-red) | `DatabaseConnectionInterceptor` | 
| [com.intellij.database.dataSourceDetector](https://jb.gg/ipe?extensions=com.intellij.database.dataSourceDetector) | `DataSourceDetector` | 
| [com.intellij.database.driversConfig](https://jb.gg/ipe?extensions=com.intellij.database.driversConfig) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `n/a` | 
| [com.intellij.database.modelExternalData](https://jb.gg/ipe?extensions=com.intellij.database.modelExternalData) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `n/a` | 
| [com.intellij.database.modelRelationProvider](https://jb.gg/ipe?extensions=com.intellij.database.modelRelationProvider) | `ModelRelationProvider` | 
| [com.intellij.database.parameterPatternProvider](https://jb.gg/ipe?extensions=com.intellij.database.parameterPatternProvider) | `DatabaseParameterPatternProvider` | 
| [com.intellij.database.queryValidator](https://jb.gg/ipe?extensions=com.intellij.database.queryValidator) | `DbQueryValidator` | 
| [com.intellij.database.urlEditorInspector](https://jb.gg/ipe?extensions=com.intellij.database.urlEditorInspector) | `UrlEditorInspector` | 
| [com.intellij.database.urlParamEditorProvider](https://jb.gg/ipe?extensions=com.intellij.database.urlParamEditorProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `TypeDescriptorFactory` | 
