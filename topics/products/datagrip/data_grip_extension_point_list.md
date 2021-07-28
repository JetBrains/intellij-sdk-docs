[//]: # (title: DataGrip Extension Point List)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

12 Extension Points (EP) for DataGrip

 > Please use only EPs listed below; others are not recommended being used by 3rd party plugins.
 >
 {type="warning"}
         
See [Extension Point List](extension_point_list.md) for IntelliJ Platform EPs.

<include src="extension_point_list.md" include-id="ep_list_legend"></include>

## DataGrip

### DatabasePlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.database.artifactsConfig](https://jb.gg/ipe?extensions=com.intellij.database.artifactsConfig) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `n/a` | 
| [com.intellij.database.configValidator](https://jb.gg/ipe?extensions=com.intellij.database.configValidator) | `DatabaseConfigValidator` | 
| [com.intellij.database.connectionInterceptor](https://jb.gg/ipe?extensions=com.intellij.database.connectionInterceptor) ![Internal API][internal] | `DatabaseConnectionInterceptor` |
| [com.intellij.database.databaseViewStructureExtension](https://jb.gg/ipe?extensions=com.intellij.database.databaseViewStructureExtension) | `DvStructureExtension` | 
| [com.intellij.database.dataSourceDetector](https://jb.gg/ipe?extensions=com.intellij.database.dataSourceDetector) | `DataSourceDetector` |
| [com.intellij.database.driversConfig](https://jb.gg/ipe?extensions=com.intellij.database.driversConfig) ![Non-Dynamic][non-dynamic] | `n/a` | 
| [com.intellij.database.modelExternalData](https://jb.gg/ipe?extensions=com.intellij.database.modelExternalData) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.database.modelRelationProvider](https://jb.gg/ipe?extensions=com.intellij.database.modelRelationProvider) | `ModelRelationProvider` |
| [com.intellij.database.parameterPatternProvider](https://jb.gg/ipe?extensions=com.intellij.database.parameterPatternProvider) | `DatabaseParameterPatternProvider` 
| [com.intellij.database.queryValidator](https://jb.gg/ipe?extensions=com.intellij.database.queryValidator) | `DbQueryValidator` | 
| [com.intellij.database.urlEditorInspector](https://jb.gg/ipe?extensions=com.intellij.database.urlEditorInspector) | `UrlEditorInspector` | 
| [com.intellij.database.urlParamEditorProvider](https://jb.gg/ipe?extensions=com.intellij.database.urlParamEditorProvider) ![Non-Dynamic][non-dynamic] | `TypeDescriptorFactory` |

[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange
[experimental]: https://img.shields.io/badge/-Experimental_API-red
[internal]: https://img.shields.io/badge/-Internal_API-red
[project-level]: https://img.shields.io/badge/-Project--Level-yellow
