<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- EP List Directory: /plugins/spring/ -->

# Spring API Extension Point and Listener List
<primary-label ref="IntelliJIDEA_Ultimate"/>

<link-summary>Overview of Extension Points and Listeners for Spring API.</link-summary>

<tldr>

**Product-Specific Plugin Development**: [IntelliJ IDEA Ultimate](idea_ultimate.md)

</tldr>

> Spring API Extension Points and Listeners are available in the Spring-related plugins,
> which are available in IntelliJ IDEA Ultimate only.
>
{style="note"}

55 Extension Points and 5 Listeners for Spring API

<include from="snippets.md" element-id="ep_list_legend"/>

## Spring

### Spring – Listeners

| Topic | Listener |
|-------|----------|
| [SpringBootEndpointsTabSettings#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.spring.boot.run.lifecycle.tabs.SpringBootEndpointsTabSettings.Listener)  | `Listener` |
| [SpringRepositoriesViewSettings#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.spring.data.commons.view.SpringRepositoriesViewSettings.Listener)  | `Listener` |
| [SpringFileSetService#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.spring.facet.SpringFileSetService.SpringFileSetListener)  | `SpringFileSetListener` |
| [SpringMvcViewSettings#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.spring.mvc.toolwindow.SpringMvcViewSettings.Listener)  | `Listener` |
| [SpringBeansViewSettings#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.spring.toolWindow.SpringBeansViewSettings.Listener)  | `Listener` |


### com.intellij.spring

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.autodetected.filesets](https://jb.gg/ipe?extensions=com.intellij.spring.autodetected.filesets) | `SpringAutodetectedFilesetsSearcher` |
| [com.intellij.spring.autodetected.models](https://jb.gg/ipe?extensions=com.intellij.spring.autodetected.models) | `SpringAutodetectedModelsSearcher` |
| [com.intellij.spring.beanClassLineMarker](https://jb.gg/ipe?extensions=com.intellij.spring.beanClassLineMarker) | `BeanClassLineMarker` |
| [com.intellij.spring.beans.stereotype](https://jb.gg/ipe?extensions=com.intellij.spring.beans.stereotype) | `SpringBeanStereotype` |
| [com.intellij.spring.componentScanExtender](https://jb.gg/ipe?extensions=com.intellij.spring.componentScanExtender) | `ComponentScanExtender` |
| [com.intellij.spring.conditionalEvaluatorProvider](https://jb.gg/ipe?extensions=com.intellij.spring.conditionalEvaluatorProvider) | `ConditionalEvaluatorProvider` |
| [com.intellij.spring.configSearcherScopeModifier](https://jb.gg/ipe?extensions=com.intellij.spring.configSearcherScopeModifier) | `ConfigSearcherScopeModifier` |
| [com.intellij.spring.configurator](https://jb.gg/ipe?extensions=com.intellij.spring.configurator) | `SpringConfigurator` |
| [com.intellij.spring.customBeanScope](https://jb.gg/ipe?extensions=com.intellij.spring.customBeanScope) | `SpringCustomBeanScope` |
| [com.intellij.spring.customConverterProvider](https://jb.gg/ipe?extensions=com.intellij.spring.customConverterProvider) | `Provider` |
| [com.intellij.spring.customLocalComponentsDiscoverer](https://jb.gg/ipe?extensions=com.intellij.spring.customLocalComponentsDiscoverer) | `CustomLocalComponentsDiscoverer` |
| [com.intellij.spring.customModuleComponentsDiscoverer](https://jb.gg/ipe?extensions=com.intellij.spring.customModuleComponentsDiscoverer) | `CustomModuleComponentsDiscoverer` |
| [com.intellij.spring.customNamespaces](https://jb.gg/ipe?extensions=com.intellij.spring.customNamespaces) | `SpringCustomNamespaces` |
| [com.intellij.spring.effective.types.provider](https://jb.gg/ipe?extensions=com.intellij.spring.effective.types.provider) | `SpringBeanEffectiveTypeProvider` |
| [com.intellij.spring.factoryMethodTypeHandler](https://jb.gg/ipe?extensions=com.intellij.spring.factoryMethodTypeHandler) | `CustomFactoryMethodTypeHandler` |
| [com.intellij.spring.fileSetEditorCustomization](https://jb.gg/ipe?extensions=com.intellij.spring.fileSetEditorCustomization) | `SpringFileSetEditorCustomization` |
| [com.intellij.spring.inspectionsRegistryAdditionalFilesContributor](https://jb.gg/ipe?extensions=com.intellij.spring.inspectionsRegistryAdditionalFilesContributor) | `AdditionalFilesContributor` |
| [com.intellij.spring.inspectionsRegistryContributor](https://jb.gg/ipe?extensions=com.intellij.spring.inspectionsRegistryContributor) | `Contributor` |
| [com.intellij.spring.jam.customMetaImplementation](https://jb.gg/ipe?extensions=com.intellij.spring.jam.customMetaImplementation) | `n/a` |
| [com.intellij.spring.localAnnotationModelDependentModelsProvider](https://jb.gg/ipe?extensions=com.intellij.spring.localAnnotationModelDependentModelsProvider) | `LocalAnnotationModelDependentModelsProvider` |
| [com.intellij.spring.localModelProducer](https://jb.gg/ipe?extensions=com.intellij.spring.localModelProducer) | `SpringLocalModelProducer` |
| [com.intellij.spring.placeholderReferenceResolver](https://jb.gg/ipe?extensions=com.intellij.spring.placeholderReferenceResolver) | `SpringPlaceholderReferenceResolver` |
| [com.intellij.spring.resourceTypeProvider](https://jb.gg/ipe?extensions=com.intellij.spring.resourceTypeProvider) | `SpringResourceTypeProvider` |
| [com.intellij.spring.scriptBeanPsiClassDiscoverer](https://jb.gg/ipe?extensions=com.intellij.spring.scriptBeanPsiClassDiscoverer) | `ScriptBeanPsiClassDiscoverer` |
| [com.intellij.spring.settingsProvider](https://jb.gg/ipe?extensions=com.intellij.spring.settingsProvider) | `SpringSettingsProvider` |
| [com.intellij.spring.testingAnnotationsProvider](https://jb.gg/ipe?extensions=com.intellij.spring.testingAnnotationsProvider) | `SpringTestingAnnotationsProvider` |
| [com.intellij.spring.testingImplicitContextsProvider](https://jb.gg/ipe?extensions=com.intellij.spring.testingImplicitContextsProvider) | `SpringTestingImplicitContextsProvider` |
| [com.intellij.spring.valueConverter](https://jb.gg/ipe?extensions=com.intellij.spring.valueConverter) | `SpringValueConvertersProvider` |

### com.intellij.spring.boot

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.boot.configFileDetector](https://jb.gg/ipe?extensions=com.intellij.spring.boot.configFileDetector) | `SpringBootConfigFileDetector` |
| [com.intellij.spring.boot.customHintReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.spring.boot.customHintReferenceProvider) | `SpringBootCustomHintReferenceProvider` |
| [com.intellij.spring.boot.languageSpecificBridge](https://jb.gg/ipe?extensions=com.intellij.spring.boot.languageSpecificBridge) | `SpringBootLanguageSpecificBridge` |
| [com.intellij.spring.boot.modelConditionalContributor](https://jb.gg/ipe?extensions=com.intellij.spring.boot.modelConditionalContributor) | `ConditionalContributor` |
| [com.intellij.spring.boot.modelConfigFileContributor](https://jb.gg/ipe?extensions=com.intellij.spring.boot.modelConfigFileContributor) | `SpringBootModelConfigFileContributor` |
| [com.intellij.spring.boot.modelConfigFileNameContributor](https://jb.gg/ipe?extensions=com.intellij.spring.boot.modelConfigFileNameContributor) | `SpringBootModelConfigFileNameContributor` |
| [com.intellij.spring.boot.modelExtender](https://jb.gg/ipe?extensions=com.intellij.spring.boot.modelExtender) | `SpringBootModelExtender` |
| [com.intellij.spring.boot.replacementTokenResolver](https://jb.gg/ipe?extensions=com.intellij.spring.boot.replacementTokenResolver) | `SpringBootReplacementTokenResolver` |

### com.intellij.spring.debugger

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.debugger.runConfigurationExtender](https://jb.gg/ipe?extensions=com.intellij.spring.debugger.runConfigurationExtender) | `SpringDebuggerRunConfigurationExtender` |
| [com.intellij.spring.debugger.sessionListener](https://jb.gg/ipe?extensions=com.intellij.spring.debugger.sessionListener) | `SpringDebuggerSessionListener` |

### com.intellij.spring.graphql

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.graphql.appPathProvider](https://jb.gg/ipe?extensions=com.intellij.spring.graphql.appPathProvider) | `GraphQLApplicationPathProvider` |

### com.intellij.spring.messaging

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.messaging.urlProvider](https://jb.gg/ipe?extensions=com.intellij.spring.messaging.urlProvider) | `SpringMessagingUrlProvider` |

### com.intellij.spring.mvc

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.mvc.applicationPathProvider](https://jb.gg/ipe?extensions=com.intellij.spring.mvc.applicationPathProvider) | `SpringApplicationPathProvider` |
| [com.intellij.spring.mvc.completion.controllerParamTypeProvider](https://jb.gg/ipe?extensions=com.intellij.spring.mvc.completion.controllerParamTypeProvider) | `ControllerParameterProvider` |
| [com.intellij.spring.mvc.mergingMvcRequestMappingLineMarkerProvider](https://jb.gg/ipe?extensions=com.intellij.spring.mvc.mergingMvcRequestMappingLineMarkerProvider) | `SpringMergingMvcRequestMappingLineMarkerProvider` |
| [com.intellij.spring.mvc.springEndpointsIconProvider](https://jb.gg/ipe?extensions=com.intellij.spring.mvc.springEndpointsIconProvider) | `SpringEndpointsIconProvider` |
| [com.intellij.spring.mvc.viewResolverFactory](https://jb.gg/ipe?extensions=com.intellij.spring.mvc.viewResolverFactory) | `ViewResolverFactory` |

### com.intellij.spring.security

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.security.rolesProvider](https://jb.gg/ipe?extensions=com.intellij.spring.security.rolesProvider) | `SpringSecurityRolesProvider` |

### intellij.spring.boot.mvc.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.boot.mvc.templateAvailabilityProvider](https://jb.gg/ipe?extensions=com.intellij.spring.boot.mvc.templateAvailabilityProvider) | `TemplateAvailabilityProvider` |

### intellij.spring.boot.run.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.boot.run.applicationUpdatePolicy](https://jb.gg/ipe?extensions=com.intellij.spring.boot.run.applicationUpdatePolicy) | `SpringBootApplicationUpdatePolicy` |
| [com.intellij.spring.boot.run.applicationUrlPathProviderFactory](https://jb.gg/ipe?extensions=com.intellij.spring.boot.run.applicationUrlPathProviderFactory) | `SpringBootApplicationUrlPathProviderFactory` |
| [com.intellij.spring.boot.run.endpoint](https://jb.gg/ipe?extensions=com.intellij.spring.boot.run.endpoint) ![Experimental][experimental] | `Endpoint` |
| [com.intellij.spring.boot.run.endpointTabConfigurable](https://jb.gg/ipe?extensions=com.intellij.spring.boot.run.endpointTabConfigurable) ![Project-Level][project-level] | `EndpointTabConfigurable` |
| [com.intellij.spring.boot.run.liveBeansPanelContent](https://jb.gg/ipe?extensions=com.intellij.spring.boot.run.liveBeansPanelContent) | `LiveBeansPanelContent` |

### intellij.spring.el.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.el.contexts](https://jb.gg/ipe?extensions=com.intellij.spring.el.contexts) | `SpringElContextsExtension` |
| [com.intellij.spring.el.injection.context](https://jb.gg/ipe?extensions=com.intellij.spring.el.injection.context) | `SpringElInjectionContext` |

### intellij.spring.graph.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.gutterDiagramActionProvider](https://jb.gg/ipe?extensions=com.intellij.spring.gutterDiagramActionProvider) | `SpringGutterDiagramActionProvider` |

[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square
