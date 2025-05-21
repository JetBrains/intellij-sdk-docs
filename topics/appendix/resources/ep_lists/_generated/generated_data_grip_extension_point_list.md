<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- GENERATED FILE, DO NOT EDIT -->
<!-- This file is generated with the SDK Docs Authoring Tools plugin ('Generate SDK Docs EP Lists' action) -->
<!-- Revision: b815cfdcaa594a8842ccf89e6ac2bf7615401920 -->

<!--
EP List Directories:
- /dbe
-->


<snippet id="content">

77 Extension Points and 23 Listeners

<include from="snippets.topic" element-id="ep_list_legend"/>

## DataGrip

### DataGrip – Listeners

| Topic | Listener |
|-------|----------|
| [`JdbcDriverManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.console.JdbcDriverManager.Listener)  | `Listener` |
| [`DatabaseSessionManager#topic`](https://jb.gg/ipe/listeners?topics=com.intellij.database.console.session.DatabaseSessionManagerListener)  | `DatabaseSessionManagerListener` |
| [`DatabaseSession.Companion#topic`](https://jb.gg/ipe/listeners?topics=com.intellij.database.console.session.DatabaseSessionStateListener)  | `DatabaseSessionStateListener` |
| [`AbstractDataSource#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.dataSource.AbstractDataSource.Listener)  | `Listener` |
| [`DataSourceModelStorage#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.dataSource.DataSourceModelStorage.Listener)  | `Listener` |
| [`DataSourceStorage#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.dataSource.DataSourceStorage.Listener)  | `Listener` |
| [`DatabaseConnectionManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.dataSource.DatabaseConnectionManager.Listener)  | `Listener` |
| [`DatabaseArtifactManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.dataSource.artifacts.DatabaseArtifactManager.ArtifactListener)  | `ArtifactListener` |
| [`DataSourceTestConnectionManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.dataSource.ui.DataSourceTestConnectionManager.Listener)  | `Listener` |
| [`DatabaseTopics#AUDIT_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.datagrid.DataAuditor)  | `DataAuditor` |
| [`DatabaseTopics#RESPONSE_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.datagrid.DataConsumer)  | [`DataConsumer`](%gh-ic%/grid/core-impl/src/datagrid/DataConsumer.java) |
| [`DatabaseTopics#REQUEST_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.datagrid.DataProducer)  | [`DataProducer`](%gh-ic%/grid/core-impl/src/datagrid/DataProducer.java) |
| [`ImportHead#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.dbimport.ImportHead.Listener)  | `Listener` |
| [`DbImportDialog#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.dbimport.editor.DbImportDialog.Listener)  | `Listener` |
| [`ScriptGeneratorDiagnosticListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.dialects.base.generator.ScriptGeneratorDiagnosticListener)  | `ScriptGeneratorDiagnosticListener` |
| [`DatabaseModelLoader#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.introspection.DatabaseModelLoader.Listener)  | `Listener` |
| [`DataSourceManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.psi.DataSourceManager.Listener)  | `Listener` |
| [`DbPsiFacade#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.psi.DbPsiFacade.Listener)  | `Listener` |
| [`DatabaseColorManager#COLOR_CHANGE_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.view.DatabaseColorManager.ColorChangeListener)  | `ColorChangeListener` |
| [`DatabaseConfigEditor#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.database.view.ui.DatabaseConfigEditor.Listener)  | `Listener` |
| [`SqlRoutineIndex#topic`](https://jb.gg/ipe/listeners?topics=com.intellij.sql.SqlRoutineIndex.SqlRoutineIndexListener)  | `SqlRoutineIndexListener` |
| [`DatabaseSettings#TOPIC`](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [`DatabaseViewOptions#TOPIC`](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |


### DatabaseConnectivity.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.artifactRepositoriesProvider"/></include> | `ArtifactRepositoriesProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.configValidator"/></include> | `DatabaseConfigValidator` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.dataConsumer"/></include> | [`DataConsumer`](%gh-ic%/grid/core-impl/src/datagrid/DataConsumer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.dataProducer"/></include> | [`DataProducer`](%gh-ic%/grid/core-impl/src/datagrid/DataProducer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.errorProvider"/></include> ![Non-Dynamic][non-dynamic] | `ConsoleErrorProviderFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.gridHelper"/></include> | [`CoreGridHelper`](%gh-ic%/grid/core-impl/src/datagrid/CoreGridHelper.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.jdbcHelper"/></include> | `JdbcHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.jdbcMetadataWrapper"/></include> | `MDFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.jdbcSourceLoader"/></include> | `JdbcSourceLoader` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.objectEditorFactory"/></include> ![Non-Dynamic][non-dynamic] | `DbmsObjectEditorFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.objectEditorModelFactory"/></include> ![Non-Dynamic][non-dynamic] | `DbmsObjectEditorModelFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.queryParametersProvider"/></include> | `QueryParametersProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.queryValidator"/></include> | `DbQueryValidator` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.selectInProvider"/></include> | `Extension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.toDatabaseScriptTranslator"/></include> | `ToDatabaseScriptTranslator` |

### DatabaseCore.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.addToHSet"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.artifactsConfig"/></include> ![Non-Dynamic][non-dynamic] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.connectionInterceptor"/></include> ![Experimental][experimental] | `DatabaseConnectionInterceptor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.consoleProvider"/></include> | `PersistenceConsoleProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.consoleRunContextParametersTuner"/></include> | `ConsoleRunContextParametersTuner` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.dataAuditor"/></include> | `DataAuditor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.dataImporter"/></include> | `ImportManager` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.dataSourceDetector"/></include> | `DataSourceDetector` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.dataSourceManager"/></include> ![Project-Level][project-level] | `DataSourceManager` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.dbms"/></include> ![Non-Dynamic][non-dynamic] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.definitionProvider"/></include> | `DefinitionProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.dmlHelper"/></include> | `DmlHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.domainRegistry"/></include> | `DomainRegistry` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.driversConfig"/></include> ![Non-Dynamic][non-dynamic] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.errorHandler"/></include> | `DatabaseErrorHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.executionEnvironmentHelper"/></include> | `ExecutionEnvironmentHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.explainPlanProvider"/></include> | `ExplainPlanProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.extensionFallback"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.geoHelper"/></include> | `GeoHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.gridColumnsManagerFactory"/></include> | `GridColumnsManagerFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.hookUpHelper"/></include> | `HookUpHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.introspector"/></include> ![Internal][internal] | `Factory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.introspectorStatsProvider"/></include> | `DBIntrospectorStatsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.linkedDataSourceHelper"/></include> | `LinkedDataSourceHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.modelExternalData"/></include> ![Non-Dynamic][non-dynamic] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.modelFacade"/></include> ![Non-Dynamic][non-dynamic] | `ModelFacade` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.modelRelationProvider"/></include> | `ModelRelationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.namingService"/></include> | `Provider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.optionProvider"/></include> | `DbOptionProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.parameterPatternProvider"/></include> | `DatabaseParameterPatternProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.processParamProvider"/></include> | `ConsoleConfigurationParamProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.routineExecutionHelper"/></include> | `RoutineExecutionHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.runtimeErrorFixProvider"/></include> | [`RuntimeErrorActionProvider`](%gh-ic%/grid/core-impl/src/connection/throwable/info/RuntimeErrorActionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.scriptGenerator"/></include> | `ScriptGenerator` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.sqlObjectBuilder"/></include> | `SqlObjectBuilder` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.synchronizeHandler"/></include> | `SynchronizeHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.typeSystem"/></include> | `DasTypeSystem` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.urlEditorInspector"/></include> | `UrlEditorInspector` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.urlParamEditorProvider"/></include> ![Non-Dynamic][non-dynamic] | `TypeDescriptorFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.virtualFileDataSourceProvider"/></include> | `VirtualFileDataSourceProvider` |

### DatabasePlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.activeConnectionInfoProvider"/></include> ![Internal][internal] | `DbActiveConnectionInfoProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.cli.runTargetProvider"/></include> | `CliRunTargetProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.databaseViewStructureExtension"/></include> | `DvStructureExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.debuggerFacade"/></include> | `SqlDebuggerFacade` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.predicatesHelper"/></include> | `PredicatesHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.runConsoleAvailable"/></include> | `RunQueryIntentionActionAvailable` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.schemaDiffCustomization"/></include> | `SchemaDiffCustomization` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.urlParamEditorUiProvider"/></include> ![Non-Dynamic][non-dynamic] | `TypeDescriptorUiFactory` |

### intellij.grid.scripting.impl.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.grid.scripting.ivyLocalRepository"/></include> | `IvyLocalRepository` |

### mongo.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.mongo.resolveHelper"/></include> | `MongoJSResolveHelper` |

### SqlPluginCore.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.dialect"/></include> ![Non-Dynamic][non-dynamic] | `DatabaseDialect` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.database.sqlEffectAnalyzer"/></include> ![Non-Dynamic][non-dynamic] | `SqlAffectAnalyzer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.sql.dataSourceProvider"/></include> | `DataSourceProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.sql.dialect"/></include> ![Non-Dynamic][non-dynamic] | `SqlLanguageDialect` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.sql.dialectCodeStyleProvider"/></include> | `SqlDialectCodeStyleProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.sql.evaluationHelper"/></include> | `EvaluationHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.sql.executionFlowAnalyzerProvider"/></include> | `ExecutionFlowAnalyzerProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.sql.formatterHelper"/></include> | `SqlFormatterHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.sql.inspectionSuppressorDelegate"/></include> | `SqlInspectionSuppressorDelegate` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.sql.membersHelper"/></include> | `SqlMembersHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.sql.navigationHelper"/></include> | `NavigationHelper` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.sql.resolveExtension"/></include> | `SqlResolveExtension` |


[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square

</snippet>
