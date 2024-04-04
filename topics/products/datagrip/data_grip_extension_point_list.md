<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- EP List Directory: /dbe/ -->

# DataGrip Extension Point and Listener List

<link-summary>Overview of Extension Points and Listeners for DataGrip.</link-summary>

75 Extension Points and 28 Listeners for DataGrip

See [](extension_point_list.md) for IntelliJ Platform.

<include from="snippets.md" element-id="ep_list_legend"/>

## DataGrip

### DataGrip - Listeners

| Topic | Listener |
|-------|----------|
| [JdbcDriverManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.console.JdbcDriverManager.Listener)  | `Listener` |
| [DatabaseSessionManager#topic](https://jb.gg/ipe/listeners?topics=com.intellij.database.console.session.DatabaseSessionManagerListener)  | `DatabaseSessionManagerListener` |
| [DatabaseSession.Companion#topic](https://jb.gg/ipe/listeners?topics=com.intellij.database.console.session.DatabaseSessionStateListener)  | `DatabaseSessionStateListener` |
| [CsvFormatsSettings#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.csv.CsvFormatsSettings.Listener)  | `Listener` |
| [AbstractDataSource#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.dataSource.AbstractDataSource.Listener)  | `Listener` |
| [DataSourceModelStorage#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.dataSource.DataSourceModelStorage.Listener)  | `Listener` |
| [DataSourceStorage#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.dataSource.DataSourceStorage.Listener)  | `Listener` |
| [DatabaseConnectionManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.dataSource.DatabaseConnectionManager.Listener)  | `Listener` |
| [DatabaseArtifactManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.dataSource.artifacts.DatabaseArtifactManager.ArtifactListener)  | `ArtifactListener` |
| [DataSourceTestConnectionManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.dataSource.ui.DataSourceTestConnectionManager.Listener)  | `Listener` |
| [DatabaseTopics#AUDIT_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.datagrid.DataAuditor)  | `DataAuditor` |
| [DatabaseTopics#RESPONSE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.datagrid.DataConsumer)  | `DataConsumer` |
| [DataGrid#ACTIVE_GRID_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.datagrid.DataGrid.ActiveGridListener)  | `ActiveGridListener` |
| [DataGridListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.datagrid.DataGridListener)  | `DataGridListener` |
| [DatabaseTopics#REQUEST_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.datagrid.DataProducer)  | `DataProducer` |
| [ImportHead#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.dbimport.ImportHead.Listener)  | `Listener` |
| [DbImportDialog#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.dbimport.editor.DbImportDialog.Listener)  | `Listener` |
| [ScriptGeneratorDiagnosticListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.dialects.base.generator.ScriptGeneratorDiagnosticListener)  | `ScriptGeneratorDiagnosticListener` |
| [DatabaseModelLoader#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.introspection.DatabaseModelLoader.Listener)  | `Listener` |
| [DataSourceManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.psi.DataSourceManager.Listener)  | `Listener` |
| [DbPsiFacade#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.psi.DbPsiFacade.Listener)  | `Listener` |
| [DataGridAppearanceSettings#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.settings.DataGridAppearanceSettings.Listener)  | `Listener` |
| [DataGridSettings#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.settings.DataGridSettings.Listener)  | `Listener` |
| [DatabaseColorManager#COLOR_CHANGE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.view.DatabaseColorManager.ColorChangeListener)  | `ColorChangeListener` |
| [DatabaseConfigEditor#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.database.view.ui.DatabaseConfigEditor.Listener)  | `Listener` |
| [SqlRoutineIndex#topic](https://jb.gg/ipe/listeners?topics=com.intellij.sql.SqlRoutineIndex.SqlRoutineIndexListener)  | `SqlRoutineIndexListener` |
| [DatabaseSettings#TOPIC](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [DatabaseViewOptions#TOPIC](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |


### DatabaseConnectivity.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.database.configValidator](https://jb.gg/ipe?extensions=com.intellij.database.configValidator) | `DatabaseConfigValidator` |
| [com.intellij.database.dataConsumer](https://jb.gg/ipe?extensions=com.intellij.database.dataConsumer) | `DataConsumer` |
| [com.intellij.database.dataProducer](https://jb.gg/ipe?extensions=com.intellij.database.dataProducer) | `DataProducer` |
| [com.intellij.database.errorProvider](https://jb.gg/ipe?extensions=com.intellij.database.errorProvider) ![Non-Dynamic][non-dynamic] | `ConsoleErrorProviderFactory` |
| [com.intellij.database.gridHelper](https://jb.gg/ipe?extensions=com.intellij.database.gridHelper) | `CoreGridHelper` |
| [com.intellij.database.jdbcHelper](https://jb.gg/ipe?extensions=com.intellij.database.jdbcHelper) | `JdbcHelper` |
| [com.intellij.database.jdbcMetadataWrapper](https://jb.gg/ipe?extensions=com.intellij.database.jdbcMetadataWrapper) | `MDFactory` |
| [com.intellij.database.jdbcSourceLoader](https://jb.gg/ipe?extensions=com.intellij.database.jdbcSourceLoader) | `JdbcSourceLoader` |
| [com.intellij.database.objectEditorFactory](https://jb.gg/ipe?extensions=com.intellij.database.objectEditorFactory) ![Non-Dynamic][non-dynamic] | `DbObjectEditorFactory` |
| [com.intellij.database.queryParametersProvider](https://jb.gg/ipe?extensions=com.intellij.database.queryParametersProvider) | `QueryParametersProvider` |
| [com.intellij.database.queryValidator](https://jb.gg/ipe?extensions=com.intellij.database.queryValidator) | `DbQueryValidator` |
| [com.intellij.database.selectInProvider](https://jb.gg/ipe?extensions=com.intellij.database.selectInProvider) | `Extension` |
| [com.intellij.database.toDatabaseScriptTranslator](https://jb.gg/ipe?extensions=com.intellij.database.toDatabaseScriptTranslator) | `ToDatabaseScriptTranslator` |

### DatabaseCore.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.database.addToHSet](https://jb.gg/ipe?extensions=com.intellij.database.addToHSet) | `n/a` |
| [com.intellij.database.artifactsConfig](https://jb.gg/ipe?extensions=com.intellij.database.artifactsConfig) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.database.connectionInterceptor](https://jb.gg/ipe?extensions=com.intellij.database.connectionInterceptor) ![Experimental][experimental] | `DatabaseConnectionInterceptor` |
| [com.intellij.database.consoleProvider](https://jb.gg/ipe?extensions=com.intellij.database.consoleProvider) | `PersistenceConsoleProvider` |
| [com.intellij.database.consoleRunContextParametersTuner](https://jb.gg/ipe?extensions=com.intellij.database.consoleRunContextParametersTuner) | `ConsoleRunContextParametersTuner` |
| [com.intellij.database.dataAuditor](https://jb.gg/ipe?extensions=com.intellij.database.dataAuditor) | `DataAuditor` |
| [com.intellij.database.dataImporter](https://jb.gg/ipe?extensions=com.intellij.database.dataImporter) | `ImportManager` |
| [com.intellij.database.dataSourceDetector](https://jb.gg/ipe?extensions=com.intellij.database.dataSourceDetector) | `DataSourceDetector` |
| [com.intellij.database.dataSourceManager](https://jb.gg/ipe?extensions=com.intellij.database.dataSourceManager) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `DataSourceManager` |
| [com.intellij.database.dbms](https://jb.gg/ipe?extensions=com.intellij.database.dbms) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.database.definitionProvider](https://jb.gg/ipe?extensions=com.intellij.database.definitionProvider) | `DefinitionProvider` |
| [com.intellij.database.dmlHelper](https://jb.gg/ipe?extensions=com.intellij.database.dmlHelper) | `DmlHelper` |
| [com.intellij.database.domainRegistry](https://jb.gg/ipe?extensions=com.intellij.database.domainRegistry) | `DomainRegistry` |
| [com.intellij.database.driversConfig](https://jb.gg/ipe?extensions=com.intellij.database.driversConfig) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.database.errorHandler](https://jb.gg/ipe?extensions=com.intellij.database.errorHandler) | `DatabaseErrorHandler` |
| [com.intellij.database.executionEnvironmentHelper](https://jb.gg/ipe?extensions=com.intellij.database.executionEnvironmentHelper) | `ExecutionEnvironmentHelper` |
| [com.intellij.database.explainPlanProvider](https://jb.gg/ipe?extensions=com.intellij.database.explainPlanProvider) | `ExplainPlanProvider` |
| [com.intellij.database.extensionFallback](https://jb.gg/ipe?extensions=com.intellij.database.extensionFallback) | `n/a` |
| [com.intellij.database.geoHelper](https://jb.gg/ipe?extensions=com.intellij.database.geoHelper) | `GeoHelper` |
| [com.intellij.database.gridColumnsManagerFactory](https://jb.gg/ipe?extensions=com.intellij.database.gridColumnsManagerFactory) | `GridColumnsManagerFactory` |
| [com.intellij.database.hookUpHelper](https://jb.gg/ipe?extensions=com.intellij.database.hookUpHelper) | `HookUpHelper` |
| [com.intellij.database.introspector](https://jb.gg/ipe?extensions=com.intellij.database.introspector) ![Internal][internal] | `Factory` |
| [com.intellij.database.introspectorStatsProvider](https://jb.gg/ipe?extensions=com.intellij.database.introspectorStatsProvider) | `DBIntrospectorStatsProvider` |
| [com.intellij.database.linkedDataSourceHelper](https://jb.gg/ipe?extensions=com.intellij.database.linkedDataSourceHelper) | `LinkedDataSourceHelper` |
| [com.intellij.database.modelExternalData](https://jb.gg/ipe?extensions=com.intellij.database.modelExternalData) ![Non-Dynamic][non-dynamic] | `n/a` |
| [com.intellij.database.modelFacade](https://jb.gg/ipe?extensions=com.intellij.database.modelFacade) ![Non-Dynamic][non-dynamic] | `ModelFacade` |
| [com.intellij.database.modelRelationProvider](https://jb.gg/ipe?extensions=com.intellij.database.modelRelationProvider) | `ModelRelationProvider` |
| [com.intellij.database.namingService](https://jb.gg/ipe?extensions=com.intellij.database.namingService) | `Provider` |
| [com.intellij.database.optionProvider](https://jb.gg/ipe?extensions=com.intellij.database.optionProvider) | `DbOptionProvider` |
| [com.intellij.database.parameterPatternProvider](https://jb.gg/ipe?extensions=com.intellij.database.parameterPatternProvider) | `DatabaseParameterPatternProvider` |
| [com.intellij.database.processParamProvider](https://jb.gg/ipe?extensions=com.intellij.database.processParamProvider) | `ConsoleConfigurationParamProvider` |
| [com.intellij.database.routineExecutionHelper](https://jb.gg/ipe?extensions=com.intellij.database.routineExecutionHelper) | `RoutineExecutionHelper` |
| [com.intellij.database.scriptGenerator](https://jb.gg/ipe?extensions=com.intellij.database.scriptGenerator) | `ScriptGenerator` |
| [com.intellij.database.sqlObjectBuilder](https://jb.gg/ipe?extensions=com.intellij.database.sqlObjectBuilder) | `SqlObjectBuilder` |
| [com.intellij.database.synchronizeHandler](https://jb.gg/ipe?extensions=com.intellij.database.synchronizeHandler) | `SynchronizeHandler` |
| [com.intellij.database.typeSystem](https://jb.gg/ipe?extensions=com.intellij.database.typeSystem) | `DasTypeSystem` |
| [com.intellij.database.urlEditorInspector](https://jb.gg/ipe?extensions=com.intellij.database.urlEditorInspector) | `UrlEditorInspector` |
| [com.intellij.database.urlParamEditorProvider](https://jb.gg/ipe?extensions=com.intellij.database.urlParamEditorProvider) ![Non-Dynamic][non-dynamic] | `TypeDescriptorFactory` |
| [com.intellij.database.virtualFileDataSourceProvider](https://jb.gg/ipe?extensions=com.intellij.database.virtualFileDataSourceProvider) | `VirtualFileDataSourceProvider` |

### DatabasePlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.database.cli.runTargetProvider](https://jb.gg/ipe?extensions=com.intellij.database.cli.runTargetProvider) | `CliRunTargetProvider` |
| [com.intellij.database.databaseViewStructureExtension](https://jb.gg/ipe?extensions=com.intellij.database.databaseViewStructureExtension) | `DvStructureExtension` |
| [com.intellij.database.debuggerFacade](https://jb.gg/ipe?extensions=com.intellij.database.debuggerFacade) | `SqlDebuggerFacade` |
| [com.intellij.database.predicatesHelper](https://jb.gg/ipe?extensions=com.intellij.database.predicatesHelper) | `PredicatesHelper` |
| [com.intellij.database.runConsoleAvailable](https://jb.gg/ipe?extensions=com.intellij.database.runConsoleAvailable) | `RunQueryIntentionActionAvailable` |
| [com.intellij.database.schemaDiffCustomization](https://jb.gg/ipe?extensions=com.intellij.database.schemaDiffCustomization) | `SchemaDiffCustomization` |
| [com.intellij.database.urlParamEditorUiProvider](https://jb.gg/ipe?extensions=com.intellij.database.urlParamEditorUiProvider) ![Non-Dynamic][non-dynamic] | `TypeDescriptorUiFactory` |

### intellij.grid.core.impl

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.database.datagrid.extractorsHelper](https://jb.gg/ipe?extensions=com.intellij.database.datagrid.extractorsHelper) | `ExtractorsHelper` |
| [com.intellij.database.datagrid.formatterCreatorProvider](https://jb.gg/ipe?extensions=com.intellij.database.datagrid.formatterCreatorProvider) | `FormatterCreatorProvider` |
| [com.intellij.database.datagrid.objectNormalizerProvider](https://jb.gg/ipe?extensions=com.intellij.database.datagrid.objectNormalizerProvider) | `ObjectNormalizerProvider` |

### intellij.grid.impl

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.database.datagrid.valueEditorTab](https://jb.gg/ipe?extensions=com.intellij.database.datagrid.valueEditorTab) | `ValueEditorTab` |

### mongo.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.database.mongo.resolveHelper](https://jb.gg/ipe?extensions=com.intellij.database.mongo.resolveHelper) | `MongoJSResolveHelper` |

### SqlPluginCore.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.database.dialect](https://jb.gg/ipe?extensions=com.intellij.database.dialect) ![Non-Dynamic][non-dynamic] | `DatabaseDialect` |
| [com.intellij.sql.dataSourceProvider](https://jb.gg/ipe?extensions=com.intellij.sql.dataSourceProvider) | `DataSourceProvider` |
| [com.intellij.sql.dialect](https://jb.gg/ipe?extensions=com.intellij.sql.dialect) ![Non-Dynamic][non-dynamic] | `SqlLanguageDialect` |
| [com.intellij.sql.dialectCodeStyleProvider](https://jb.gg/ipe?extensions=com.intellij.sql.dialectCodeStyleProvider) | `SqlDialectCodeStyleProvider` |
| [com.intellij.sql.evaluationHelper](https://jb.gg/ipe?extensions=com.intellij.sql.evaluationHelper) | `EvaluationHelper` |
| [com.intellij.sql.executionFlowAnalyzerProvider](https://jb.gg/ipe?extensions=com.intellij.sql.executionFlowAnalyzerProvider) | `ExecutionFlowAnalyzerProvider` |
| [com.intellij.sql.formatterHelper](https://jb.gg/ipe?extensions=com.intellij.sql.formatterHelper) | `SqlFormatterHelper` |
| [com.intellij.sql.inspectionSuppressorDelegate](https://jb.gg/ipe?extensions=com.intellij.sql.inspectionSuppressorDelegate) | `SqlInspectionSuppressorDelegate` |
| [com.intellij.sql.membersHelper](https://jb.gg/ipe?extensions=com.intellij.sql.membersHelper) | `SqlMembersHelper` |
| [com.intellij.sql.navigationHelper](https://jb.gg/ipe?extensions=com.intellij.sql.navigationHelper) | `NavigationHelper` |
| [com.intellij.sql.resolveExtension](https://jb.gg/ipe?extensions=com.intellij.sql.resolveExtension) | `SqlResolveExtension` |

[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square
