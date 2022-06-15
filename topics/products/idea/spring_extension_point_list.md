[//]: # (title: Spring API Extension Point and Listener List)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

47 Extension Points and 5 Listeners for Spring API

<include src="extension_point_list.md" include-id="ep_list_legend"></include>

## Spring

### Spring - Listeners

| Topic                                                                                                                                                          | Listener                |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------|
| [SpringBootEndpointsTabSettings#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.spring.boot.run.lifecycle.tabs.SpringBootEndpointsTabSettings.Listener) | `Listener`              |
| [SpringRepositoriesViewSettings#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.spring.data.commons.view.SpringRepositoriesViewSettings.Listener)       | `Listener`              |
| [SpringFileSetService#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.spring.facet.SpringFileSetService.SpringFileSetListener)                          | `SpringFileSetListener` |
| [SpringMvcViewSettings#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.spring.mvc.toolwindow.SpringMvcViewSettings.Listener)                            | `Listener`              |
| [SpringBeansViewSettings#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.spring.toolWindow.SpringBeansViewSettings.Listener)                            | `Listener`              |

### com.intellij.spring

| Extension Point                                                                                                                                                     | Implementation                                |
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------|
| [com.intellij.spring.beanPointerPanelContent](https://jb.gg/ipe?extensions=com.intellij.spring.beanPointerPanelContent)                                             | `SpringBeanPointerPanelContent`               |
| [com.intellij.spring.componentScanExtender](https://jb.gg/ipe?extensions=com.intellij.spring.componentScanExtender)                                                 | `ComponentScanExtender`                       |
| [com.intellij.spring.conditionalEvaluatorProvider](https://jb.gg/ipe?extensions=com.intellij.spring.conditionalEvaluatorProvider)                                   | `ConditionalEvaluatorProvider`                |
| [com.intellij.spring.configSearcherScopeModifier](https://jb.gg/ipe?extensions=com.intellij.spring.configSearcherScopeModifier)                                     | `ConfigSearcherScopeModifier`                 |
| [com.intellij.spring.configurator](https://jb.gg/ipe?extensions=com.intellij.spring.configurator)                                                                   | `SpringConfigurator`                          |
| [com.intellij.spring.customBeanScope](https://jb.gg/ipe?extensions=com.intellij.spring.customBeanScope)                                                             | `SpringCustomBeanScope`                       |
| [com.intellij.spring.customConverterProvider](https://jb.gg/ipe?extensions=com.intellij.spring.customConverterProvider)                                             | `Provider`                                    |
| [com.intellij.spring.customLocalComponentsDiscoverer](https://jb.gg/ipe?extensions=com.intellij.spring.customLocalComponentsDiscoverer)                             | `CustomLocalComponentsDiscoverer`             |
| [com.intellij.spring.customModuleComponentsDiscoverer](https://jb.gg/ipe?extensions=com.intellij.spring.customModuleComponentsDiscoverer)                           | `CustomModuleComponentsDiscoverer`            |
| [com.intellij.spring.customNamespaces](https://jb.gg/ipe?extensions=com.intellij.spring.customNamespaces)                                                           | `SpringCustomNamespaces`                      |
| [com.intellij.spring.effective.types.provider](https://jb.gg/ipe?extensions=com.intellij.spring.effective.types.provider)                                           | `SpringBeanEffectiveTypeProvider`             |
| [com.intellij.spring.factoryMethodTypeHandler](https://jb.gg/ipe?extensions=com.intellij.spring.factoryMethodTypeHandler)                                           | `CustomFactoryMethodTypeHandler`              |
| [com.intellij.spring.fileSetEditorCustomization](https://jb.gg/ipe?extensions=com.intellij.spring.fileSetEditorCustomization)                                       | `SpringFileSetEditorCustomization`            |
| [com.intellij.spring.inspectionsRegistryAdditionalFilesContributor](https://jb.gg/ipe?extensions=com.intellij.spring.inspectionsRegistryAdditionalFilesContributor) | `AdditionalFilesContributor`                  |
| [com.intellij.spring.inspectionsRegistryContributor](https://jb.gg/ipe?extensions=com.intellij.spring.inspectionsRegistryContributor)                               | `Contributor`                                 |
| [com.intellij.spring.jam.customMetaImplementation](https://jb.gg/ipe?extensions=com.intellij.spring.jam.customMetaImplementation)                                   | `n/a`                                         |
| [com.intellij.spring.localAnnotationModelDependentModelsProvider](https://jb.gg/ipe?extensions=com.intellij.spring.localAnnotationModelDependentModelsProvider)     | `LocalAnnotationModelDependentModelsProvider` |
| [com.intellij.spring.localModelProducer](https://jb.gg/ipe?extensions=com.intellij.spring.localModelProducer)                                                       | `SpringLocalModelProducer`                    |
| [com.intellij.spring.modelProvider](https://jb.gg/ipe?extensions=com.intellij.spring.modelProvider)                                                                 | `SpringModelProvider`                         |
| [com.intellij.spring.placeholderReferenceResolver](https://jb.gg/ipe?extensions=com.intellij.spring.placeholderReferenceResolver)                                   | `SpringPlaceholderReferenceResolver`          |
| [com.intellij.spring.resourceTypeProvider](https://jb.gg/ipe?extensions=com.intellij.spring.resourceTypeProvider)                                                   | `SpringResourceTypeProvider`                  |
| [com.intellij.spring.scriptBeanPsiClassDiscoverer](https://jb.gg/ipe?extensions=com.intellij.spring.scriptBeanPsiClassDiscoverer)                                   | `ScriptBeanPsiClassDiscoverer`                |
| [com.intellij.spring.testingAnnotationsProvider](https://jb.gg/ipe?extensions=com.intellij.spring.testingAnnotationsProvider)                                       | `SpringTestingAnnotationsProvider`            |
| [com.intellij.spring.testingImplicitContextsProvider](https://jb.gg/ipe?extensions=com.intellij.spring.testingImplicitContextsProvider)                             | `SpringTestingImplicitContextsProvider`       |
| [com.intellij.spring.toolWindowContent](https://jb.gg/ipe?extensions=com.intellij.spring.toolWindowContent)                                                         | `SpringToolWindowContentProvider`             |
| [com.intellij.spring.valueConverter](https://jb.gg/ipe?extensions=com.intellij.spring.valueConverter)                                                               | `SpringValueConvertersProvider`               |

### com.intellij.spring.boot

| Extension Point                                                                                                                                 | Implementation                             |
|-------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------|
| [com.intellij.spring.boot.customHintReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.spring.boot.customHintReferenceProvider)       | `SpringBootCustomHintReferenceProvider`    |
| [com.intellij.spring.boot.modelConditionalContributor](https://jb.gg/ipe?extensions=com.intellij.spring.boot.modelConditionalContributor)       | `ConditionalContributor`                   |
| [com.intellij.spring.boot.modelConfigFileContributor](https://jb.gg/ipe?extensions=com.intellij.spring.boot.modelConfigFileContributor)         | `SpringBootModelConfigFileContributor`     |
| [com.intellij.spring.boot.modelConfigFileNameContributor](https://jb.gg/ipe?extensions=com.intellij.spring.boot.modelConfigFileNameContributor) | `SpringBootModelConfigFileNameContributor` |
| [com.intellij.spring.boot.modelExtender](https://jb.gg/ipe?extensions=com.intellij.spring.boot.modelExtender)                                   | `SpringBootModelExtender`                  |
| [com.intellij.spring.boot.replacementTokenResolver](https://jb.gg/ipe?extensions=com.intellij.spring.boot.replacementTokenResolver)             | `SpringBootReplacementTokenResolver`       |

### com.intellij.spring.boot.initializr

| Extension Point                                                                                                                                     | Implementation                |
|-----------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------|
| [com.intellij.spring.boot.initializr.sharedIndexesProvider](https://jb.gg/ipe?extensions=com.intellij.spring.boot.initializr.sharedIndexesProvider) | `SpringSharedIndexesProvider` |

### com.intellij.spring.messaging

| Extension Point                                                                                                     | Implementation               |
|---------------------------------------------------------------------------------------------------------------------|------------------------------|
| [com.intellij.spring.messaging.urlProvider](https://jb.gg/ipe?extensions=com.intellij.spring.messaging.urlProvider) | `SpringMessagingUrlProvider` |

### com.intellij.spring.mvc

| Extension Point                                                                                                                                                       | Implementation                                     |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------|
| [com.intellij.spring.mvc.applicationPortProvider](https://jb.gg/ipe?extensions=com.intellij.spring.mvc.applicationPortProvider)                                       | `SpringApplicationPortProvider`                    |
| [com.intellij.spring.mvc.mergingMvcRequestMappingLineMarkerProvider](https://jb.gg/ipe?extensions=com.intellij.spring.mvc.mergingMvcRequestMappingLineMarkerProvider) | `SpringMergingMvcRequestMappingLineMarkerProvider` |
| [com.intellij.spring.mvc.viewResolverFactory](https://jb.gg/ipe?extensions=com.intellij.spring.mvc.viewResolverFactory)                                               | `ViewResolverFactory`                              |

### com.intellij.spring.security

| Extension Point                                                                                                       | Implementation                |
|-----------------------------------------------------------------------------------------------------------------------|-------------------------------|
| [com.intellij.spring.security.rolesProvider](https://jb.gg/ipe?extensions=com.intellij.spring.security.rolesProvider) | `SpringSecurityRolesProvider` |

### intellij.spring.boot.core.mvc.xml

| Extension Point                                                                                                                                     | Implementation                 |
|-----------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------|
| [com.intellij.spring.boot.mvc.templateAvailabilityProvider](https://jb.gg/ipe?extensions=com.intellij.spring.boot.mvc.templateAvailabilityProvider) | `TemplateAvailabilityProvider` |

### intellij.spring.boot.run.xml

| Extension Point                                                                                                                                                           | Implementation                                |
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------|
| [com.intellij.spring.boot.run.applicationUpdatePolicy](https://jb.gg/ipe?extensions=com.intellij.spring.boot.run.applicationUpdatePolicy)                                 | `SpringBootApplicationUpdatePolicy`           |
| [com.intellij.spring.boot.run.applicationUrlPathProviderFactory](https://jb.gg/ipe?extensions=com.intellij.spring.boot.run.applicationUrlPathProviderFactory)             | `SpringBootApplicationUrlPathProviderFactory` |
| [com.intellij.spring.boot.run.endpoint](https://jb.gg/ipe?extensions=com.intellij.spring.boot.run.endpoint) ![Experimental API][experimental]                             | `Endpoint`                                    |
| [com.intellij.spring.boot.run.endpointTabConfigurable](https://jb.gg/ipe?extensions=com.intellij.spring.boot.run.endpointTabConfigurable) ![Project-Level][project-level] | `EndpointTabConfigurable`                     |
| [com.intellij.spring.boot.run.liveBeansPanelContent](https://jb.gg/ipe?extensions=com.intellij.spring.boot.run.liveBeansPanelContent)                                     | `LiveBeansPanelContent`                       |

### intellij.spring.el.xml

| Extension Point                                                                                                   | Implementation              |
|-------------------------------------------------------------------------------------------------------------------|-----------------------------|
| [com.intellij.spring.el.contexts](https://jb.gg/ipe?extensions=com.intellij.spring.el.contexts)                   | `SpringElContextsExtension` |
| [com.intellij.spring.el.injection.context](https://jb.gg/ipe?extensions=com.intellij.spring.el.injection.context) | `SpringElInjectionContext`  |

### intellij.spring.graph.xml

| Extension Point                                                                                                                 | Implementation                      |
|---------------------------------------------------------------------------------------------------------------------------------|-------------------------------------|
| [com.intellij.spring.gutterDiagramActionProvider](https://jb.gg/ipe?extensions=com.intellij.spring.gutterDiagramActionProvider) | `SpringGutterDiagramActionProvider` |

[experimental]: https://img.shields.io/badge/-Experimental_API-red?style=flat-square
[internal]: https://img.shields.io/badge/-Internal_API-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
