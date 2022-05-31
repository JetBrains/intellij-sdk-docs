[//]: # (title: DataGrip Extension Point and Listener List)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

63 Extension Points (EP) and 17 Listeners for DataGrip

See [](extension_point_list.md) for IntelliJ Platform.

<include src="extension_point_list.md" include-id="ep_list_legend"></include>

## DataGrip

### DataGrip - Listeners

| Topic | Listener |
|-------|----------|
| [DatabaseSessionManager#topic](https://jb.gg/ipe/listeners?topics=com.intellij.database.console.session.DatabaseSessionManagerListener)  | `DatabaseSessionManagerListener` |
| [AbstractDataSource#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.dataSource.AbstractDataSource.Listener)  | `Listener` |
| [DataSourceStorageCore#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.dataSource.DataSourceStorageCore.Listener)  | `Listener` |
| [DatabaseConnectionManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.dataSource.DatabaseConnectionManager.Listener)  | `Listener` |
| [DatabaseArtifactManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.dataSource.artifacts.DatabaseArtifactManager.ArtifactListener)  | `ArtifactListener` |
| [DatabaseTopics#AUDIT_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.datagrid.DataAuditor)  | `DataAuditor` |
| [DatabaseTopics#RESPONSE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.datagrid.DataConsumer)  | `DataConsumer` |
| [DataGrid#ACTIVE_GRID_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.datagrid.DataGrid.ActiveGridListener)  | `ActiveGridListener` |
| [DataGridListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.datagrid.DataGridListener)  | `DataGridListener` |
| [DatabaseTopics#REQUEST_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.datagrid.DataProducer)  | `DataProducer` |
| [ScriptGeneratorDiagnosticListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.dialects.base.generator.ScriptGeneratorDiagnosticListener)  | `ScriptGeneratorDiagnosticListener` |
| [DataSourceManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.psi.DataSourceManager.Listener)  | `Listener` |
| [DbPsiFacade#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.psi.DbPsiFacade.Listener)  | `Listener` |
| [DatabaseSettings#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.settings.DatabaseSettings.Listener)  | `Listener` |
| [DatabaseColorManager#COLOR_CHANGE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.view.DatabaseColorManager.ColorChangeListener)  | `ColorChangeListener` |
| [SqlRoutineIndex#topic](https://jb.gg/ipe/listeners?topics=com.intellij.sql.SqlRoutineIndex.SqlRoutineIndexListener)  | `SqlRoutineIndexListener` |
| [DatabaseViewOptions#TOPIC](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |


### DatabaseConnectivity.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.database.addToHSet](https://jb.gg/ipe?extensions=com.intellij.database.addToHSet) | `n/a` |
| [com.intellij.database.artifactsConfig](https://jb.gg/ipe?extensions=com.intellij.database.artifactsConfig) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.database.configValidator](https://jb.gg/ipe?extensions=com.intellij.database.configValidator) | `DatabaseConfigValidator` |
| [com.intellij.database.connectionInterceptor](https://jb.gg/ipe?extensions=com.intellij.database.connectionInterceptor) ![Internal API][internal] | `DatabaseConnectionInterceptor` |
| [com.intellij.database.consoleProvider](https://jb.gg/ipe?extensions=com.intellij.database.consoleProvider) | `PersistenceConsoleProvider` |
| [com.intellij.database.dataAuditor](https://jb.gg/ipe?extensions=com.intellij.database.dataAuditor) | `DataAuditor` |
| [com.intellij.database.dataConsumer](https://jb.gg/ipe?extensions=com.intellij.database.dataConsumer) | `DataConsumer` |
| [com.intellij.database.dataImporter](https://jb.gg/ipe?extensions=com.intellij.database.dataImporter) | `ImportManager` |
| [com.intellij.database.dataProducer](https://jb.gg/ipe?extensions=com.intellij.database.dataProducer) | `DataProducer` |
| [com.intellij.database.dataSourceDetector](https://jb.gg/ipe?extensions=com.intellij.database.dataSourceDetector) | `DataSourceDetector` |
| [com.intellij.database.dataSourceManager](https://jb.gg/ipe?extensions=com.intellij.database.dataSourceManager) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `DataSourceManager` |
| [com.intellij.database.dbms](https://jb.gg/ipe?extensions=com.intellij.database.dbms) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.database.definitionProvider](https://jb.gg/ipe?extensions=com.intellij.database.definitionProvider) | `DefinitionProvider` |
| [com.intellij.database.dmlHelper](https://jb.gg/ipe?extensions=com.intellij.database.dmlHelper) | `DmlHelper` |
| [com.intellij.database.domainRegistry](https://jb.gg/ipe?extensions=com.intellij.database.domainRegistry) | `DomainRegistry` |
| [com.intellij.database.driversConfig](https://jb.gg/ipe?extensions=com.intellij.database.driversConfig) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.database.errorHandler](https://jb.gg/ipe?extensions=com.intellij.database.errorHandler) | `DatabaseErrorHandler` |
| [com.intellij.database.errorProvider](https://jb.gg/ipe?extensions=com.intellij.database.errorProvider) ![Non-Dynamic][non-dynamic] | `ConsoleErrorProviderFactory` |
| [com.intellij.database.executionEnvironmentHelper](https://jb.gg/ipe?extensions=com.intellij.database.executionEnvironmentHelper) | `ExecutionEnvironmentHelper` |
| [com.intellij.database.explainPlanProvider](https://jb.gg/ipe?extensions=com.intellij.database.explainPlanProvider) | `ExplainPlanProvider` |
| [com.intellij.database.extensionFallback](https://jb.gg/ipe?extensions=com.intellij.database.extensionFallback) | `n/a` |
| [com.intellij.database.geoHelper](https://jb.gg/ipe?extensions=com.intellij.database.geoHelper) | `GeoHelper` |
| [com.intellij.database.gridHelper](https://jb.gg/ipe?extensions=com.intellij.database.gridHelper) | `GridHelper` |
| [com.intellij.database.introspector](https://jb.gg/ipe?extensions=com.intellij.database.introspector) ![Internal API][internal] | `Factory` |
| [com.intellij.database.jdbcHelper](https://jb.gg/ipe?extensions=com.intellij.database.jdbcHelper) | `JdbcHelper` |
| [com.intellij.database.jdbcSourceLoader](https://jb.gg/ipe?extensions=com.intellij.database.jdbcSourceLoader) | `JdbcSourceLoader` |
| [com.intellij.database.modelExternalData](https://jb.gg/ipe?extensions=com.intellij.database.modelExternalData) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.database.modelFacade](https://jb.gg/ipe?extensions=com.intellij.database.modelFacade) ![Non-Dynamic][non-dynamic] | `ModelFacade` |
| [com.intellij.database.modelRelationProvider](https://jb.gg/ipe?extensions=com.intellij.database.modelRelationProvider) | `ModelRelationProvider` |
| [com.intellij.database.namingService](https://jb.gg/ipe?extensions=com.intellij.database.namingService) | `Provider` |
| [com.intellij.database.objectEditorFactory](https://jb.gg/ipe?extensions=com.intellij.database.objectEditorFactory) ![Non-Dynamic][non-dynamic] | `DbObjectEditorFactory` |
| [com.intellij.database.optionProvider](https://jb.gg/ipe?extensions=com.intellij.database.optionProvider) | `DbOptionProvider` |
| [com.intellij.database.parameterPatternProvider](https://jb.gg/ipe?extensions=com.intellij.database.parameterPatternProvider) | `DatabaseParameterPatternProvider` |
| [com.intellij.database.processParamProvider](https://jb.gg/ipe?extensions=com.intellij.database.processParamProvider) | `ParamProvider` |
| [com.intellij.database.queryParametersProvider](https://jb.gg/ipe?extensions=com.intellij.database.queryParametersProvider) | `QueryParametersProvider` |
| [com.intellij.database.queryValidator](https://jb.gg/ipe?extensions=com.intellij.database.queryValidator) | `DbQueryValidator` |
| [com.intellij.database.routineExecutionHelper](https://jb.gg/ipe?extensions=com.intellij.database.routineExecutionHelper) | `RoutineExecutionHelper` |
| [com.intellij.database.scriptGenerator](https://jb.gg/ipe?extensions=com.intellij.database.scriptGenerator) | `ScriptGenerator` |
| [com.intellij.database.selectInProvider](https://jb.gg/ipe?extensions=com.intellij.database.selectInProvider) | `Extension` |
| [com.intellij.database.sqlObjectBuilder](https://jb.gg/ipe?extensions=com.intellij.database.sqlObjectBuilder) | `SqlObjectBuilder` |
| [com.intellij.database.synchronizeHandler](https://jb.gg/ipe?extensions=com.intellij.database.synchronizeHandler) | `SynchronizeHandler` |
| [com.intellij.database.toDatabaseScriptTranslator](https://jb.gg/ipe?extensions=com.intellij.database.toDatabaseScriptTranslator) | `ToDatabaseScriptTranslator` |
| [com.intellij.database.typeService](https://jb.gg/ipe?extensions=com.intellij.database.typeService) | `DasTypeService` |
| [com.intellij.database.urlEditorInspector](https://jb.gg/ipe?extensions=com.intellij.database.urlEditorInspector) | `UrlEditorInspector` |
| [com.intellij.database.urlParamEditorProvider](https://jb.gg/ipe?extensions=com.intellij.database.urlParamEditorProvider) ![Non-Dynamic][non-dynamic] | `TypeDescriptorFactory` |
| [com.intellij.database.virtualFileDataSourceProvider](https://jb.gg/ipe?extensions=com.intellij.database.virtualFileDataSourceProvider) | `VirtualFileDataSourceProvider` |

### DatabasePlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.database.databaseViewStructureExtension](https://jb.gg/ipe?extensions=com.intellij.database.databaseViewStructureExtension) | `DvStructureExtension` |
| [com.intellij.database.databaseViewStructureProvider](https://jb.gg/ipe?extensions=com.intellij.database.databaseViewStructureProvider) | `DatabaseViewStructureProvider` |
| [com.intellij.database.debuggerFacade](https://jb.gg/ipe?extensions=com.intellij.database.debuggerFacade) | `SqlDebuggerFacade` |
| [com.intellij.database.gridColumnsManagerFactory](https://jb.gg/ipe?extensions=com.intellij.database.gridColumnsManagerFactory) | `GridColumnsManagerFactory` |
| [com.intellij.database.predicatesHelper](https://jb.gg/ipe?extensions=com.intellij.database.predicatesHelper) | `PredicatesHelper` |
| [com.intellij.database.schemaDiffCustomization](https://jb.gg/ipe?extensions=com.intellij.database.schemaDiffCustomization) | `SchemaDiffCustomization` |

### mongo.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.database.mongo.resolveHelper](https://jb.gg/ipe?extensions=com.intellij.database.mongo.resolveHelper) | `MongoJSResolveHelper` |

### SqlPluginCore.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.database.dialect](https://jb.gg/ipe?extensions=com.intellij.database.dialect) ![Non-Dynamic][non-dynamic] | `DatabaseDialect` |
| [com.intellij.sql.dialect](https://jb.gg/ipe?extensions=com.intellij.sql.dialect) ![Non-Dynamic][non-dynamic] | `SqlLanguageDialect` |
| [com.intellij.sql.dialectCodeStyleProvider](https://jb.gg/ipe?extensions=com.intellij.sql.dialectCodeStyleProvider) | `SqlDialectCodeStyleProvider` |
| [com.intellij.sql.evaluationHelper](https://jb.gg/ipe?extensions=com.intellij.sql.evaluationHelper) | `EvaluationHelper` |
| [com.intellij.sql.executionFlowAnalyzerProvider](https://jb.gg/ipe?extensions=com.intellij.sql.executionFlowAnalyzerProvider) | `ExecutionFlowAnalyzerProvider` |
| [com.intellij.sql.formatterHelper](https://jb.gg/ipe?extensions=com.intellij.sql.formatterHelper) | `SqlFormatterHelper` |
| [com.intellij.sql.membersHelper](https://jb.gg/ipe?extensions=com.intellij.sql.membersHelper) | `SqlMembersHelper` |
| [com.intellij.sql.navigationHelper](https://jb.gg/ipe?extensions=com.intellij.sql.navigationHelper) | `NavigationHelper` |
| [com.intellij.sql.resolveExtension](https://jb.gg/ipe?extensions=com.intellij.sql.resolveExtension) | `SqlResolveExtension` |
| [com.intellij.sql.typeSystem](https://jb.gg/ipe?extensions=com.intellij.sql.typeSystem) | `SqlTypeSystem` |

[experimental]: https://img.shields.io/badge/-Experimental_API-red?style=flat-square
[internal]: https://img.shields.io/badge/-Internal_API-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
