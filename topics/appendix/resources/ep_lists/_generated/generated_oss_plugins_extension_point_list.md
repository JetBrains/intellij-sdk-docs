<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- GENERATED FILE, DO NOT EDIT -->
<!-- This file is generated with the SDK Docs Authoring Tools plugin ('Generate SDK Docs EP Lists' action) -->
<!-- Revision: b815cfdcaa594a8842ccf89e6ac2bf7615401920 -->

<!--
EP List Directories:
- /contrib

There must be no top-level "Listeners" group, adjust ExtensionPointAnalyzerAction.Group accordingly.
-->


<snippet id="content">

68 Extension Points and 10 Listeners

<include from="snippets.topic" element-id="ep_list_legend"/>

## IntelliJ Open Source Plugins

### AngularJS

[`AngularJS`](%gh-ij-plugins%/Angular/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.angular2.configProvider"/></include> | [`AngularConfigProvider`](%gh-ij-plugins%/Angular/src/org/angular2/cli/config/AngularConfigProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.angular2.entitiesSource"/></include> | [`Angular2EntitiesSource`](%gh-ij-plugins%/Angular/src/org/angular2/entities/Angular2EntitiesSource.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.angular2.frameworkHandler"/></include> ![Experimental][experimental] | [`Angular2FrameworkHandler`](%gh-ij-plugins%/Angular/src/org/angular2/entities/Angular2FrameworkHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.angular2.importsHandler"/></include> | [`Angular2ImportsHandler`](%gh-ij-plugins%/Angular/src/org/angular2/codeInsight/imports/Angular2ImportsHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.angular2.jsHandlersFactory"/></include> | [`Angular2JSHandlersFactory`](%gh-ij-plugins%/Angular/src/org/angular2/codeInsight/Angular2HandlersFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.angular2.templateScopesProvider"/></include> | [`Angular2TemplateScopesProvider`](%gh-ij-plugins%/Angular/src/org/angular2/codeInsight/template/Angular2TemplateScopesProvider.kt) |

### com.thoughtworks.gauge

[`com.thoughtworks.gauge`](%gh-ij-plugins%/gauge/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.thoughtworks.gauge.moduleImporter"/></include> | [`GaugeModuleImporter`](%gh-ij-plugins%/gauge/src/com/thoughtworks/gauge/wizard/GaugeModuleImporter.java) |

### gherkin

[`gherkin`](%gh-ij-plugins%/cucumber/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.cucumber.injector.injectorExtensionPoint"/></include> | [`GherkinInjectorExtensionPoint`](%gh-ij-plugins%/cucumber/src/org/jetbrains/plugins/cucumber/injector/GherkinInjectorExtensionPoint.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.cucumber.steps.cucumberJvmExtensionPoint"/></include> | [`CucumberJvmExtensionPoint`](%gh-ij-plugins%/cucumber/src/org/jetbrains/plugins/cucumber/CucumberJvmExtensionPoint.java) |

### idea.plugin.protoeditor

[`idea.plugin.protoeditor`](%gh-ij-plugins%/protobuf/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.protobuf.codeImplementationSearcher"/></include> | [`PbCodeImplementationSearcher`](%gh-ij-plugins%/protobuf/protoeditor-core/src/com/intellij/protobuf/ide/gutter/PbGeneratedCodeConverterProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.protobuf.fileResolveProvider"/></include> ![Project-Level][project-level] | [`FileResolveProvider`](%gh-ij-plugins%/protobuf/protoeditor-core/src/com/intellij/protobuf/lang/resolve/FileResolveProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.protobuf.generatedCodeConverterProvider"/></include> | [`PbGeneratedCodeConverterProvider`](%gh-ij-plugins%/protobuf/protoeditor-core/src/com/intellij/protobuf/ide/gutter/PbGeneratedCodeConverterProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.protobuf.nameGeneratorContributor"/></include> | [`NameGeneratorContributor`](%gh-ij-plugins%/protobuf/protoeditor-core/src/com/intellij/protobuf/lang/names/NameGeneratorContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.protobuf.projectSettingsConfigurator"/></include> ![Project-Level][project-level] | [`ProjectSettingsConfigurator`](%gh-ij-plugins%/protobuf/protoeditor-core/src/com/intellij/protobuf/ide/settings/ProjectSettingsConfigurator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.protobuf.schemaProvider"/></include> ![Project-Level][project-level] | [`SchemaProvider`](%gh-ij-plugins%/protobuf/protoeditor-core/src/com/intellij/protobuf/lang/resolve/SchemaProvider.java) |

### intellij.prettierJS

[`intellij.prettierJS`](%gh-ij-plugins%/prettierJS/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.prettierjs.codeStyleInstaller"/></include> | [`PrettierCodeStyleInstaller`](%gh-ij-plugins%/prettierJS/src/com/intellij/prettierjs/codeStyle/PrettierCodeStyleInstaller.java) |

### name.kropp.intellij.makefile

[`name.kropp.intellij.makefile`](%gh-ij-plugins%/makefile/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.makefile.toolWindowStripeController"/></include> | [`MakefileToolWindowStripeController`](%gh-ij-plugins%/makefile/src/com/jetbrains/lang/makefile/toolWindow/MakefileToolWindowStripeController.kt) |

### org.jetbrains.plugins.vue

[`org.jetbrains.plugins.vue`](%gh-ij-plugins%/vuejs/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vuejs.containerInfoProvider"/></include> | [`VueContainerInfoProvider`](%gh-ij-plugins%/vuejs/src/org/jetbrains/vuejs/model/source/VueContainerInfoProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vuejs.templateScopesProvider"/></include> | [`VueTemplateScopesProvider`](%gh-ij-plugins%/vuejs/src/org/jetbrains/vuejs/codeInsight/template/VueTemplateScopesProvider.kt) |

### tslint

[`tslint`](%gh-ij-plugins%/tslint/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.tslint.configDetector"/></include> | [`TsLintConfigDetector`](%gh-ij-plugins%/tslint/src/com/intellij/lang/javascript/linter/tslint/config/TsLintConfigDetector.java) |


## Dart Plugin

### Dart Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`DartAnalysisServerMessages#DART_ANALYSIS_TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.lang.dart.analyzer.DartAnalysisServerMessages.DartAnalysisNotifier)  | [`DartAnalysisNotifier`](%gh-ij-plugins%/Dart/src/com/jetbrains/lang/dart/analyzer/DartAnalysisServerMessages.java) |


### Dart

[`Dart`](%gh-ij-plugins%/Dart/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Dart.completionExtension"/></include> | [`DartCompletionExtension`](%gh-ij-plugins%/Dart/src/com/jetbrains/lang/dart/ide/completion/DartCompletionExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Dart.completionTimerExtension"/></include> | [`DartCompletionTimerExtension`](%gh-ij-plugins%/Dart/src/com/jetbrains/lang/dart/ide/completion/DartCompletionTimerExtension.java) |


## Devicetree Plugin

### Devicetree Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`ChangeListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.dts.settings.DtsSettings.ChangeListener)  ![Project-Level][project-level] | [`ChangeListener`](%gh-ij-plugins%/dts/src/com/intellij/dts/settings/DtsSettings.kt) |


### com.intellij.dts

[`com.intellij.dts`](%gh-ij-plugins%/dts/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.clion.dtsUtil"/></include> | [`DtsCLionUtil`](%gh-ij-plugins%/dts/src/com/intellij/dts/clion/DtsCLionUtil.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dts.settings.disabler"/></include> | [`DtsSettingsDisabler`](%gh-ij-plugins%/dts/src/com/intellij/dts/settings/DtsSettingsDisabler.kt) |

### dts-withCLion.xml

[`dts-withCLion.xml`](%gh-ij-plugins%/dts/resources/META-INF/dts-withCLion.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dts.cmake.configurationDataProvider"/></include> | [`DtsCMakeModelConfigurationDataProvider`](%gh-ij-plugins%/dts/src/com/intellij/dts/clion/impl/DtsCMakeModelConfigurationDataProvider.kt) |


## Flex Plugin

### Flex Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`FlexBuildConfigurationChangeListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.lang.javascript.flex.projectStructure.model.impl.FlexBuildConfigurationChangeListener)  | [`FlexBuildConfigurationChangeListener`](%gh-ij-plugins%/flex/src/com/intellij/lang/javascript/flex/projectStructure/model/impl/FlexBuildConfigurationChangeListener.java) |


### com.intellij.flex

[`com.intellij.flex`](%gh-ij-plugins%/flex/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.flex.breakpoint.type.provider"/></include> ![Non-Dynamic][non-dynamic] | [`BreakpointTypeProvider`](%gh-ij-plugins%/flex/src/com/intellij/lang/javascript/flex/debug/FlexBreakpointsHandler.java) |


## OSGi Plugin

### OSGi Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`FrameworkDefinitionListener#TOPIC`](https://jb.gg/ipe/listeners?topics=org.osmorc.settings.FrameworkDefinitionListener)  | [`FrameworkDefinitionListener`](%gh-ij-plugins%/osmorc/src/org/osmorc/settings/FrameworkDefinitionListener.java) |


### Osmorc

[`Osmorc`](%gh-ij-plugins%/osmorc/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Osmorc.frameworkIntegrator"/></include> | [`FrameworkIntegrator`](%gh-ij-plugins%/osmorc/src/org/osmorc/frameworkintegration/FrameworkIntegrator.java) |


## Perforce Helix Core

### Perforce Helix Core – Listeners

| Topic | Listener |
|-------|----------|
| [`PerforceSettings#OFFLINE_MODE_EXITED`](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [`P4ConfigListener#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.perforce.perforce.connections.P4ConfigListener)  ![Internal][internal] ![Project-Level][project-level] | [`P4ConfigListener`](%gh-ij-plugins%/PerforceIntegration/src/org/jetbrains/idea/perforce/perforce/connections/PerforceExternalConfigTracker.kt) |
| [`P4EnvHelper#P4_ENV_CHANGED`](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.perforce.perforce.connections.P4EnvHelper.P4EnvListener)  ![Project-Level][project-level] | [`P4EnvListener`](%gh-ij-plugins%/PerforceIntegration/src/org/jetbrains/idea/perforce/perforce/connections/P4EnvHelper.java) |


### PerforceDirectPlugin

[`PerforceDirectPlugin`](%gh-ij-plugins%/PerforceIntegration/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Perforce.P4ConnectionParametersProvider"/></include> ![Experimental][experimental] | [`P4ConnectionParametersProvider`](%gh-ij-plugins%/PerforceIntegration/src/org/jetbrains/idea/perforce/perforce/connections/P4ConnectionParametersProvider.kt) |


## PlatformIO for CLion Plugin

### PlatformIO for CLion Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`PlatformioServiceKt#PLATFORMIO_UPDATES_TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.embedded.platformio.PlatformioUpdatesNotifier)  ![Project-Level][project-level] | [`PlatformioUpdatesNotifier`](%gh-ij-plugins%/platformio/src/com/jetbrains/cidr/cpp/embedded/platformio/PlatformioService.kt) |
| [`PlatformioSettingsListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.embedded.platformio.project.PlatformioSettingsListener)  | [`PlatformioSettingsListener`](%gh-ij-plugins%/platformio/src/com/jetbrains/cidr/cpp/embedded/platformio/project/PlatformioSettings.kt) |



## Qodana Plugin

### intellij.qodana.coverage.xml

[`intellij.qodana.coverage.xml`](%gh-ij-plugins%/qodana/coverage/resources/intellij.qodana.coverage.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.qodana.coverage.cloudArtifactsProcessor"/></include> | [`CoverageCloudArtifactsProcessor`](%gh-ij-plugins%/qodana/coverage/src/org/jetbrains/qodana/staticAnalysis/inspections/coverage/CoverageCloudArtifactsProcessor.kt) |

### org.intellij.qodana

[`org.intellij.qodana`](%gh-ij-plugins%/qodana/core/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.azureCiConfigUpdateHandler"/></include> | [`AzureCIConfigHandler`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/extensions/ci/AzureCIConfigHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.bitbucketCiConfigUpdateHandler"/></include> | [`BitbucketCIConfigHandler`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/extensions/ci/BitbucketCIConfigHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.circleCiConfigUpdateHandler"/></include> | [`CircleCIConfigHandler`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/extensions/ci/CircleCIConfigHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.compiledInspectionKtsPostProcessorFactory"/></include> | [`CompiledInspectionKtsPostProcessorFactory`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/inspectionKts/InspectionKtsFileStatus.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.configUpdateHandler"/></include> | [`ConfigUpdateHandler`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/extensions/ConfigUpdateHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.contextMarginProvider"/></include> | [`ContextMarginProvider`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/staticAnalysis/sarif/ContextMarginProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.customPluginsForKtsClasspathProvider"/></include> | [`CustomPluginsForKtsClasspathProvider`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/inspectionKts/CustomPluginsForKtsClasspathProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.cyclomaticComplexityFileVisitor"/></include> | [`CyclomaticComplexityMetricFileVisitor`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/staticAnalysis/inspections/metrics/inspections/cyclomaticComplexity/CyclomaticComplexityMetricFileVisitor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.defaultQodanaYamlItemProvider"/></include> | [`QodanaYamlItemProvider`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/settings/QodanaYamlItem.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.dynamicInspectionsInitializer"/></include> | [`DynamicInspectionInitializer`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/inspectionKts/DynamicInspectionInitializer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.externalToolsConfigurationProvider"/></include> | [`ExternalToolsConfigurationProvider`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/staticAnalysis/inspections/runner/externalTools/ExternalToolsConfigurationProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.externalToolsProvider"/></include> | [`ExternalToolsProvider`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/staticAnalysis/inspections/runner/externalTools/ExternalToolsProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.githubCiConfigHandler"/></include> | [`GitHubCIConfigHandler`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/extensions/ci/GitHubCIConfigHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.gitlabCiConfigHandler"/></include> | [`GitLabCIConfigHandler`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/extensions/ci/GitLabCIConfigHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.globalOutputConsumer"/></include> ![Internal][internal] | [`GlobalOutputConsumer`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/staticAnalysis/inspections/runner/globalOutput/GlobalOutputConsumer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.highlightingListener"/></include> | [`QodanaHighlightingListener`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/highlight/QodanaHighlightingListener.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.inspectionKtsDefaultImportProvider"/></include> | [`InspectionKtsDefaultImportProvider`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/inspectionKts/imports.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.inspectionKtsExampleProvider"/></include> | [`Provider`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/inspectionKts/examples/InspectionKtsExample.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.inspectionKtsTemplateProvider"/></include> | [`Provider`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/inspectionKts/templates/InspectionKtsTemplate.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.inspectionProfileProvider"/></include> | [`QodanaInspectionProfileProvider`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/staticAnalysis/profile/QodanaInspectionProfileProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.jenkinsConfigHandler"/></include> | [`JenkinsConfigHandler`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/extensions/ci/JenkinsConfigHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.metricTable"/></include> | [`MetricTable`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/staticAnalysis/inspections/metrics/database/tables/MetricTable.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.metricsAggregator"/></include> | [`MetricAggregator`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/staticAnalysis/inspections/metrics/aggregators/MetricAggregator.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.problemsViewModuleSupport"/></include> | [`QodanaGroupByModuleSupport`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/ui/problemsView/QodanaGroupByModuleSupport.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.projectDescriber"/></include> | [`QodanaProjectDescriber`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/staticAnalysis/projectDescription/QodanaProjectDescriber.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.psiViewerSupport"/></include> | [`PsiViewerSupport`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/inspectionKts/ui/psi-viewer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.qodanaHighlightInfoComparator"/></include> | [`QodanaHighlightInfoComparator`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/extensions/QodanaHighlightInfoComparator.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.qodanaHighlightInfoTypeProvider"/></include> | [`QodanaHighlightInfoTypeProvider`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/extensions/QodanaHighlightInfoTypeProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.qodanaHighlightingSupportInfoProvider"/></include> | [`QodanaHighlightingSupportInfoProvider`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/extensions/QodanaHighlightingSupportInfoProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.qodanaScriptFactory"/></include> ![Internal][internal] | [`QodanaScriptFactory`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/staticAnalysis/script/QodanaScriptFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.quickFixesStrategyProvider"/></include> | [`QuickFixesStrategyProvider`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/staticAnalysis/sarif/QuickFixesStrategyProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.reportMetadataArtifact"/></include> | [`ReportMetadataArtifactProvider`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/report/LoadedReport.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.repositoryInfoProvider"/></include> | [`RepositoryInfoProvider`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/extensions/RepositoryInfoProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.repositoryRevisionProvider"/></include> | [`RepositoryRevisionProvider`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/extensions/RepositoryRevisionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.sarifReportContributor"/></include> | [`SarifReportContributor`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/staticAnalysis/sarif/SarifReportContributor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.setupCIProviderFactory"/></include> | [`SetupCIProviderFactory`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/ui/ci/SetupCIProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.vcsIgnoredFilesProvider"/></include> | [`VcsIgnoredFilesProvider`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/extensions/VcsIgnoredFilesProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.vcsRevisionProvider"/></include> | [`VcsRevisionProvider`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/vcs/VcsRevisionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.qodana.workflowExtension"/></include> ![Internal][internal] | [`QodanaWorkflowExtension`](%gh-ij-plugins%/qodana/core/src/org/jetbrains/qodana/staticAnalysis/workflow/QodanaWorkflowExtension.kt) |


## Serial Port Monitor Plugin

### Serial Port Monitor Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`SerialPortsListener.Companion#SERIAL_PORTS_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.plugins.serialmonitor.service.SerialPortsListener)  | [`SerialPortsListener`](%gh-ij-plugins%/serial-monitor/src/main/java/com/intellij/plugins/serialmonitor/service/SerialPortsListener.kt) |



[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square

</snippet>
