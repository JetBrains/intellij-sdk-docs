<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- EP List Directory: /contrib/ -->

# Open Source Plugins Extension Point and Listener List

<link-summary>Overview of Extension Points and Listeners for OSS plugins bundled with IntelliJ IDEA Ultimate and other IDEs.</link-summary>

<tldr>

**Repository**: [intellij-plugins](https://github.com/JetBrains/intellij-plugins)

</tldr>

Overview of Extension Points and Listeners for OSS plugins bundled with [](idea_ultimate.md) and other IDEs.

<include from="snippets.md" element-id="ep_list_legend"/>

25 Extension Points and 9 Listeners

## IntelliJ Community Plugins

### IntelliJ Community Plugins - Listeners

| Topic | Listener |
|-------|----------|
| [ChangeListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.dts.settings.DtsSettings.ChangeListener)  ![Project-Level][project-level] | [`ChangeListener`](%gh-ij-plugins%/dts/src/com/intellij/dts/settings/DtsSettings.kt) |
| [FlexBuildConfigurationChangeListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.lang.javascript.flex.projectStructure.model.impl.FlexBuildConfigurationChangeListener)  | [`FlexBuildConfigurationChangeListener`](%gh-ij-plugins%/flex/src/com/intellij/lang/javascript/flex/projectStructure/model/impl/FlexBuildConfigurationChangeListener.java) |
| [SerialPortsListener.Companion#SERIAL_PORTS_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.plugins.serialmonitor.service.SerialPortsListener)  | [`SerialPortsListener`](%gh-ij-plugins%/serial-monitor/src/main/java/com/intellij/plugins/serialmonitor/service/SerialPortsListener.kt) |
| [PlatformioServiceKt#PLATFORMIO_UPDATES_TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.embedded.platformio.PlatformioUpdatesNotifier)  ![Project-Level][project-level] | [`PlatformioUpdatesNotifier`](%gh-ij-plugins%/platformio/src/com/jetbrains/cidr/cpp/embedded/platformio/PlatformioService.kt) |
| [PlatformioSettingsListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cpp.embedded.platformio.project.PlatformioSettingsListener)  | [`PlatformioSettingsListener`](%gh-ij-plugins%/platformio/src/com/jetbrains/cidr/cpp/embedded/platformio/project/PlatformioSettings.kt) |
| [DartAnalysisServerMessages#DART_ANALYSIS_TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.lang.dart.analyzer.DartAnalysisServerMessages.DartAnalysisNotifier)  | [`DartAnalysisNotifier`](%gh-ij-plugins%/Dart/src/com/jetbrains/lang/dart/analyzer/DartAnalysisServerMessages.java) |
| [PerforceSettings#OFFLINE_MODE_EXITED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [P4EnvHelper#P4_ENV_CHANGED](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.perforce.perforce.connections.P4EnvHelper.P4EnvListener)  ![Project-Level][project-level] | [`P4EnvListener`](%gh-ij-plugins%/PerforceIntegration/src/org/jetbrains/idea/perforce/perforce/connections/P4EnvHelper.java) |
| [FrameworkDefinitionListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.osmorc.settings.FrameworkDefinitionListener)  | [`FrameworkDefinitionListener`](%gh-ij-plugins%/osmorc/src/org/osmorc/settings/FrameworkDefinitionListener.java) |


### AngularJS

[`AngularJS`](%gh-ij-plugins%/Angular/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.angular2.configProvider](https://jb.gg/ipe?extensions=org.angular2.configProvider) | [`AngularConfigProvider`](%gh-ij-plugins%/Angular/src/org/angular2/cli/config/AngularConfigProvider.kt) |
| [org.angular2.entitiesSource](https://jb.gg/ipe?extensions=org.angular2.entitiesSource) | [`Angular2EntitiesSource`](%gh-ij-plugins%/Angular/src/org/angular2/entities/Angular2EntitiesSource.kt) |
| [org.angular2.frameworkHandler](https://jb.gg/ipe?extensions=org.angular2.frameworkHandler) ![Experimental][experimental] | [`Angular2FrameworkHandler`](%gh-ij-plugins%/Angular/src/org/angular2/entities/Angular2FrameworkHandler.kt) |
| [org.angular2.importsHandler](https://jb.gg/ipe?extensions=org.angular2.importsHandler) | [`Angular2ImportsHandler`](%gh-ij-plugins%/Angular/src/org/angular2/codeInsight/imports/Angular2ImportsHandler.kt) |
| [org.angular2.jsHandlersFactory](https://jb.gg/ipe?extensions=org.angular2.jsHandlersFactory) | [`Angular2JSHandlersFactory`](%gh-ij-plugins%/Angular/src/org/angular2/codeInsight/Angular2HandlersFactory.kt) |
| [org.angular2.templateScopesProvider](https://jb.gg/ipe?extensions=org.angular2.templateScopesProvider) | [`Angular2TemplateScopesProvider`](%gh-ij-plugins%/Angular/src/org/angular2/codeInsight/template/Angular2TemplateScopesProvider.kt) |

### com.intellij.dts

[`com.intellij.dts`](%gh-ij-plugins%/dts/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.clion.dtsUtil](https://jb.gg/ipe?extensions=com.intellij.clion.dtsUtil) | [`DtsCLionUtil`](%gh-ij-plugins%/dts/src/com/intellij/dts/clion/DtsCLionUtil.kt) |

### com.intellij.flex

[`com.intellij.flex`](%gh-ij-plugins%/flex/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.flex.breakpoint.type.provider](https://jb.gg/ipe?extensions=com.intellij.flex.breakpoint.type.provider) ![Non-Dynamic][non-dynamic] | [`BreakpointTypeProvider`](%gh-ij-plugins%/flex/src/com/intellij/lang/javascript/flex/debug/FlexBreakpointsHandler.java) |

### com.thoughtworks.gauge

[`com.thoughtworks.gauge`](%gh-ij-plugins%/gauge/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.thoughtworks.gauge.moduleImporter](https://jb.gg/ipe?extensions=com.thoughtworks.gauge.moduleImporter) | [`GaugeModuleImporter`](%gh-ij-plugins%/gauge/src/com/thoughtworks/gauge/wizard/GaugeModuleImporter.java) |

### Dart

[`Dart`](%gh-ij-plugins%/Dart/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Dart.completionExtension](https://jb.gg/ipe?extensions=Dart.completionExtension) | [`DartCompletionExtension`](%gh-ij-plugins%/Dart/src/com/jetbrains/lang/dart/ide/completion/DartCompletionExtension.java) |
| [Dart.completionTimerExtension](https://jb.gg/ipe?extensions=Dart.completionTimerExtension) | [`DartCompletionTimerExtension`](%gh-ij-plugins%/Dart/src/com/jetbrains/lang/dart/ide/completion/DartCompletionTimerExtension.java) |

### gherkin

[`gherkin`](%gh-ij-plugins%/cucumber/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.plugins.cucumber.injector.injectorExtensionPoint](https://jb.gg/ipe?extensions=org.jetbrains.plugins.cucumber.injector.injectorExtensionPoint) | [`GherkinInjectorExtensionPoint`](%gh-ij-plugins%/cucumber/src/org/jetbrains/plugins/cucumber/injector/GherkinInjectorExtensionPoint.java) |
| [org.jetbrains.plugins.cucumber.steps.cucumberJvmExtensionPoint](https://jb.gg/ipe?extensions=org.jetbrains.plugins.cucumber.steps.cucumberJvmExtensionPoint) | [`CucumberJvmExtensionPoint`](%gh-ij-plugins%/cucumber/src/org/jetbrains/plugins/cucumber/CucumberJvmExtensionPoint.java) |

### idea.plugin.protoeditor

[`idea.plugin.protoeditor`](%gh-ij-plugins%/protobuf/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.protobuf.codeImplementationSearcher](https://jb.gg/ipe?extensions=com.intellij.protobuf.codeImplementationSearcher) | [`PbCodeImplementationSearcher`](%gh-ij-plugins%/protobuf/protoeditor-core/src/com/intellij/protobuf/ide/gutter/PbGeneratedCodeConverterProvider.kt) |
| [com.intellij.protobuf.fileResolveProvider](https://jb.gg/ipe?extensions=com.intellij.protobuf.fileResolveProvider) ![Project-Level][project-level] | [`FileResolveProvider`](%gh-ij-plugins%/protobuf/protoeditor-core/src/com/intellij/protobuf/lang/resolve/FileResolveProvider.java) |
| [com.intellij.protobuf.generatedCodeConverterProvider](https://jb.gg/ipe?extensions=com.intellij.protobuf.generatedCodeConverterProvider) | [`PbGeneratedCodeConverterProvider`](%gh-ij-plugins%/protobuf/protoeditor-core/src/com/intellij/protobuf/ide/gutter/PbGeneratedCodeConverterProvider.kt) |
| [com.intellij.protobuf.nameGeneratorContributor](https://jb.gg/ipe?extensions=com.intellij.protobuf.nameGeneratorContributor) | [`NameGeneratorContributor`](%gh-ij-plugins%/protobuf/protoeditor-core/src/com/intellij/protobuf/lang/names/NameGeneratorContributor.java) |
| [com.intellij.protobuf.projectSettingsConfigurator](https://jb.gg/ipe?extensions=com.intellij.protobuf.projectSettingsConfigurator) ![Project-Level][project-level] | [`ProjectSettingsConfigurator`](%gh-ij-plugins%/protobuf/protoeditor-core/src/com/intellij/protobuf/ide/settings/ProjectSettingsConfigurator.java) |
| [com.intellij.protobuf.schemaProvider](https://jb.gg/ipe?extensions=com.intellij.protobuf.schemaProvider) ![Project-Level][project-level] | [`SchemaProvider`](%gh-ij-plugins%/protobuf/protoeditor-core/src/com/intellij/protobuf/lang/resolve/SchemaProvider.java) |

### intellij.prettierJS

[`intellij.prettierJS`](%gh-ij-plugins%/prettierJS/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.prettierjs.codeStyleInstaller](https://jb.gg/ipe?extensions=com.intellij.prettierjs.codeStyleInstaller) | [`PrettierCodeStyleInstaller`](%gh-ij-plugins%/prettierJS/src/com/intellij/prettierjs/codeStyle/PrettierCodeStyleInstaller.java) |

### name.kropp.intellij.makefile

[`name.kropp.intellij.makefile`](%gh-ij-plugins%/makefile/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.makefile.toolWindowStripeController](https://jb.gg/ipe?extensions=com.intellij.makefile.toolWindowStripeController) | [`MakefileToolWindowStripeController`](%gh-ij-plugins%/makefile/src/com/jetbrains/lang/makefile/toolWindow/MakefileToolWindowStripeController.kt) |

### org.jetbrains.plugins.vue

[`org.jetbrains.plugins.vue`](%gh-ij-plugins%/vuejs/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.vuejs.containerInfoProvider](https://jb.gg/ipe?extensions=com.intellij.vuejs.containerInfoProvider) | [`VueContainerInfoProvider`](%gh-ij-plugins%/vuejs/src/org/jetbrains/vuejs/model/source/VueContainerInfoProvider.kt) |
| [com.intellij.vuejs.templateScopesProvider](https://jb.gg/ipe?extensions=com.intellij.vuejs.templateScopesProvider) | [`VueTemplateScopesProvider`](%gh-ij-plugins%/vuejs/src/org/jetbrains/vuejs/codeInsight/template/VueTemplateScopesProvider.kt) |

### Osmorc

[`Osmorc`](%gh-ij-plugins%/osmorc/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Osmorc.frameworkIntegrator](https://jb.gg/ipe?extensions=Osmorc.frameworkIntegrator) | [`FrameworkIntegrator`](%gh-ij-plugins%/osmorc/src/org/osmorc/frameworkintegration/FrameworkIntegrator.java) |

### tslint

[`tslint`](%gh-ij-plugins%/tslint/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.tslint.configDetector](https://jb.gg/ipe?extensions=com.intellij.tslint.configDetector) | [`TsLintConfigDetector`](%gh-ij-plugins%/tslint/src/com/intellij/lang/javascript/linter/tslint/config/TsLintConfigDetector.java) |

[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square
