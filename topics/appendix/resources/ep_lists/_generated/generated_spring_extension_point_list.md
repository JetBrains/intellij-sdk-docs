<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- GENERATED FILE, DO NOT EDIT -->
<!-- This file is generated with the SDK Docs Authoring Tools plugin ('Generate SDK Docs EP Lists' action) -->
<!-- Revision: b815cfdcaa594a8842ccf89e6ac2bf7615401920 -->

<!--
EP List Directories:
- /plugins/spring
-->


<snippet id="content">

58 Extension Points and 6 Listeners

<include from="snippets.topic" element-id="ep_list_legend"/>

## Spring

### Spring – Listeners

| Topic | Listener |
|-------|----------|
| [`SpringFileSetService#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.spring.facet.SpringFileSetService.SpringFileSetListener)  | `SpringFileSetListener` |
| [`SpringBeansViewSettings#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.spring.toolWindow.SpringBeansViewSettings.Listener)  | `Listener` |


### com.intellij.spring

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.autodetected.filesets"/></include> | `SpringAutodetectedFilesetsSearcher` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.autodetected.models"/></include> | `SpringAutodetectedModelsSearcher` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.beanClassLineMarker"/></include> | `BeanClassLineMarker` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.beans.stereotype"/></include> | `SpringBeanStereotype` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.componentScanExtender"/></include> | `ComponentScanExtender` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.conditionalEvaluatorProvider"/></include> | `ConditionalEvaluatorProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.configSearcherScopeModifier"/></include> | `ConfigSearcherScopeModifier` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.configurator"/></include> | `SpringConfigurator` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.customBeanScope"/></include> | `SpringCustomBeanScope` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.customConverterProvider"/></include> | `Provider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.customLocalComponentsDiscoverer"/></include> | `CustomLocalComponentsDiscoverer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.customModuleComponentsDiscoverer"/></include> | `CustomModuleComponentsDiscoverer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.customNamespaces"/></include> | `SpringCustomNamespaces` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.effective.types.provider"/></include> | `SpringBeanEffectiveTypeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.factoryMethodTypeHandler"/></include> | `CustomFactoryMethodTypeHandler` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.fileSetEditorCustomization"/></include> | `SpringFileSetEditorCustomization` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.inspectionsRegistryAdditionalFilesContributor"/></include> | `AdditionalFilesContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.inspectionsRegistryContributor"/></include> | `Contributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.jam.customMetaImplementation"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.localAnnotationModelDependentModelsProvider"/></include> | `LocalAnnotationModelDependentModelsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.localModelProducer"/></include> | `SpringLocalModelProducer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.placeholderReferenceResolver"/></include> | `SpringPlaceholderReferenceResolver` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.resourceTypeProvider"/></include> | `SpringResourceTypeProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.scriptBeanPsiClassDiscoverer"/></include> | `ScriptBeanPsiClassDiscoverer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.settingsProvider"/></include> | `SpringSettingsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.springTemplateProvider"/></include> | `SpringTemplateProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.testingAnnotationsProvider"/></include> | `SpringTestingAnnotationsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.testingImplicitContextsProvider"/></include> | `SpringTestingImplicitContextsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.valueConverter"/></include> | `SpringValueConvertersProvider` |

### intellij.spring.el.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.el.contexts"/></include> | `SpringElContextsExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.el.injection.context"/></include> | `SpringElInjectionContext` |

### intellij.spring.graph.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.gutterDiagramActionProvider"/></include> | `SpringGutterDiagramActionProvider` |


## Spring Boot Plugin

### Spring Boot Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`SpringBootEndpointsTabSettings#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.spring.boot.run.lifecycle.tabs.SpringBootEndpointsTabSettings.Listener)  | `Listener` |


### com.intellij.spring.boot

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.boot.configFileDetector"/></include> | `SpringBootConfigFileDetector` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.boot.customHintReferenceProvider"/></include> | `SpringBootCustomHintReferenceProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.boot.languageSpecificBridge"/></include> | `SpringBootLanguageSpecificBridge` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.boot.modelConditionalContributor"/></include> | `ConditionalContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.boot.modelConfigFileContributor"/></include> | `SpringBootModelConfigFileContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.boot.modelConfigFileNameContributor"/></include> | `SpringBootModelConfigFileNameContributor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.boot.modelExtender"/></include> | `SpringBootModelExtender` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.boot.replacementTokenResolver"/></include> | `SpringBootReplacementTokenResolver` |

### intellij.spring.boot.mvc.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.boot.mvc.templateAvailabilityProvider"/></include> | `TemplateAvailabilityProvider` |

### intellij.spring.boot.run.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.boot.run.applicationUpdatePolicy"/></include> | `SpringBootApplicationUpdatePolicy` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.boot.run.applicationUrlPathProviderFactory"/></include> | `SpringBootApplicationUrlPathProviderFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.boot.run.endpoint"/></include> ![Experimental][experimental] | `Endpoint` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.boot.run.endpointTabConfigurable"/></include> ![Project-Level][project-level] | `EndpointTabConfigurable` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.boot.run.liveBeansPanelContent"/></include> | `LiveBeansPanelContent` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.boot.run.runtimeApplicationProvider"/></include> | `SpringBootRuntimeApplicationProvider` |


## Spring Data Plugin

### Spring Data Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`SpringRepositoriesViewSettings#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.spring.data.commons.view.SpringRepositoriesViewSettings.Listener)  | `Listener` |



## Spring Debugger Plugin

### Spring Debugger Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`SpringDebugModelListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.spring.debugger.model.SpringDebugModelListener)  | `SpringDebugModelListener` |


### com.intellij.spring.debugger

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.debugger.configCodeVisionProvider"/></include> ![Internal][internal] | `SpringConfigFileCodeVisionInfoProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.debugger.runConfigurationExtender"/></include> | `SpringDebuggerRunConfigurationExtender` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.debugger.sessionListener"/></include> | `SpringDebuggerSessionListener` |


## Spring GraphQL Plugin

### com.intellij.spring.graphql

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.graphql.appPathProvider"/></include> | `GraphQLApplicationPathProvider` |


## Spring Messaging Plugin

### com.intellij.spring.messaging

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.messaging.urlProvider"/></include> | `SpringMessagingUrlProvider` |


## Spring Security Plugin

### com.intellij.spring.security

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.security.rolesProvider"/></include> | `SpringSecurityRolesProvider` |


## Spring Web Plugin

### Spring Web Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`SpringMvcViewSettings#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.spring.mvc.toolwindow.SpringMvcViewSettings.Listener)  | `Listener` |


### com.intellij.spring.mvc

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.mvc.applicationPathProvider"/></include> | `SpringApplicationPathProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.mvc.completion.controllerParamTypeProvider"/></include> | `ControllerParameterProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.mvc.mergingMvcRequestMappingLineMarkerProvider"/></include> | `SpringMergingMvcRequestMappingLineMarkerProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.mvc.springEndpointsIconProvider"/></include> | `SpringEndpointsIconProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.spring.mvc.viewResolverFactory"/></include> | `ViewResolverFactory` |


[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square

</snippet>
