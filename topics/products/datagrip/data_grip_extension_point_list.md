[//]: # (title: DataGrip Extension Point List)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

12 Extension Points (EP) and 13 Listeners for DataGrip

 > Please use only Extension Points and Listeners listed below; others are not recommended to be used by 3rd party plugins.
 >
 {type="warning"}

See [Extension Point List](extension_point_list.md) for IntelliJ Platform EPs.

<include src="extension_point_list.md" include-id="ep_list_legend"></include>

## DataGrip

### DataGrip - Listeners

| Topic | Listener |
|-------|----------|
| `com.intellij.database.dataSource.DataSourceStorage#TOPIC`| `com.intellij.database.dataSource.DataSourceStorage.Listener` |
| `com.intellij.database.dataSource.DatabaseArtifactManager#TOPIC`| `com.intellij.database.dataSource.DatabaseArtifactManager.ArtifactListener` |
| `com.intellij.database.dataSource.DatabaseConnectionManager#TOPIC`| `com.intellij.database.dataSource.DatabaseConnectionManager.Listener` |
| `com.intellij.database.DatabaseTopics#AUDIT_TOPIC`| `com.intellij.database.datagrid.DataAuditor` |
| `com.intellij.database.DatabaseTopics#RESPONSE_TOPIC`| `com.intellij.database.datagrid.DataConsumer` |
| `com.intellij.database.datagrid.DataGridListener#TOPIC`| `com.intellij.database.datagrid.DataGridListener` |
| `com.intellij.database.DatabaseTopics#REQUEST_TOPIC`| `com.intellij.database.datagrid.DataProducer` |
| `com.intellij.database.psi.DataSourceManager#TOPIC`| `com.intellij.database.psi.DataSourceManager.Listener` |
| `com.intellij.database.psi.DbPsiFacade#TOPIC`| `com.intellij.database.psi.DbPsiFacade.Listener` |
| `com.intellij.database.settings.DatabaseSettings#TOPIC`| `com.intellij.database.settings.DatabaseSettings.Listener` |
| `com.intellij.database.view.DatabaseColorManager#COLOR_CHANGE_TOPIC`| `com.intellij.database.view.DatabaseColorManager.ColorChangeListener` |
| `com.intellij.database.view.DatabaseViewOptions#TOPIC`| `java.lang.Runnable` |

### DatabasePlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.database.artifactsConfig](https://jb.gg/ipe?extensions=com.intellij.database.artifactsConfig) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.database.configValidator](https://jb.gg/ipe?extensions=com.intellij.database.configValidator) | `DatabaseConfigValidator` |
| [com.intellij.database.connectionInterceptor](https://jb.gg/ipe?extensions=com.intellij.database.connectionInterceptor) ![Internal API][internal] | `DatabaseConnectionInterceptor` |
| [com.intellij.database.databaseViewStructureExtension](https://jb.gg/ipe?extensions=com.intellij.database.databaseViewStructureExtension) | `DvStructureExtension` |
| [com.intellij.database.dataSourceDetector](https://jb.gg/ipe?extensions=com.intellij.database.dataSourceDetector) | `DataSourceDetector` |
| [com.intellij.database.driversConfig](https://jb.gg/ipe?extensions=com.intellij.database.driversConfig) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.database.modelExternalData](https://jb.gg/ipe?extensions=com.intellij.database.modelExternalData) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.database.modelRelationProvider](https://jb.gg/ipe?extensions=com.intellij.database.modelRelationProvider) | `ModelRelationProvider` |
| [com.intellij.database.parameterPatternProvider](https://jb.gg/ipe?extensions=com.intellij.database.parameterPatternProvider) | `DatabaseParameterPatternProvider` |
| [com.intellij.database.queryValidator](https://jb.gg/ipe?extensions=com.intellij.database.queryValidator) | `DbQueryValidator` |
| [com.intellij.database.urlEditorInspector](https://jb.gg/ipe?extensions=com.intellij.database.urlEditorInspector) | `UrlEditorInspector` |
| [com.intellij.database.urlParamEditorProvider](https://jb.gg/ipe?extensions=com.intellij.database.urlParamEditorProvider) ![Non-Dynamic][non-dynamic] | `TypeDescriptorFactory` |

[experimental]: https://img.shields.io/badge/-Experimental_API-red?style=flat-square
[internal]: https://img.shields.io/badge/-Internal_API-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
