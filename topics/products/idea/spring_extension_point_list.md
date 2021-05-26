[//]: # (title: Spring API Extension Point List)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

48 Extension Points (EP) for Spring API

See [Plugin Extensions](plugin_extensions.md) on how to declare extensions in your plugin.
  
**Extension Point** searches for usages inside existing implementations of open-source IntelliJ Platform plugins via [IntelliJ Platform Explorer](https://jb.gg/ipe).

**Implementation** is related EP class. 

#### Note Legend

| Icon | Description | Details |
|---|---|---|
| ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | Non-Dynamic Extension Point | Installation/update of plugin requires restart ([Dynamic Plugins](dynamic_plugins.md)) |
| ![Experimental API](https://img.shields.io/badge/-Experimental_API-red) | Experimental API | Implementation annotated with [`@ApiStatus.Experimental`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/ApiStatus.java), API might be altered or removed without prior notice |
| ![Internal API](https://img.shields.io/badge/-Internal_API-red) | Internal API | Implementation annotated with [`@ApiStatus.Internal`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/ApiStatus.java), should not be used by 3rd party |
| ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | Project-Level Extension Point | Declared with `area="IDEA_PROJECT"`, can have `Project` as constructor parameter |

## Spring

### com.intellij.spring
com.intellij.spring

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.beanPointerPanelContent](https://jb.gg/ipe?extensions=com.intellij.spring.beanPointerPanelContent) | `SpringBeanPointerPanelContent` | 
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
| [com.intellij.spring.modelProvider](https://jb.gg/ipe?extensions=com.intellij.spring.modelProvider) | `SpringModelProvider` | 
| [com.intellij.spring.resourceTypeProvider](https://jb.gg/ipe?extensions=com.intellij.spring.resourceTypeProvider) | `SpringResourceTypeProvider` | 
| [com.intellij.spring.scriptBeanPsiClassDiscoverer](https://jb.gg/ipe?extensions=com.intellij.spring.scriptBeanPsiClassDiscoverer) | `ScriptBeanPsiClassDiscoverer` | 
| [com.intellij.spring.testingAnnotationsProvider](https://jb.gg/ipe?extensions=com.intellij.spring.testingAnnotationsProvider) | `SpringTestingAnnotationsProvider` | 
| [com.intellij.spring.testingImplicitContextsProvider](https://jb.gg/ipe?extensions=com.intellij.spring.testingImplicitContextsProvider) | `SpringTestingImplicitContextsProvider` | 
| [com.intellij.spring.toolWindowContent](https://jb.gg/ipe?extensions=com.intellij.spring.toolWindowContent) | `SpringToolWindowContentProvider` | 
| [com.intellij.spring.valueConverter](https://jb.gg/ipe?extensions=com.intellij.spring.valueConverter) | `SpringValueConvertersProvider` | 

### com.intellij.spring.boot
com.intellij.spring.boot

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.boot.applicationUpdatePolicy](https://jb.gg/ipe?extensions=com.intellij.spring.boot.applicationUpdatePolicy) | `SpringBootApplicationUpdatePolicy` | 
| [com.intellij.spring.boot.customHintReferenceProvider](https://jb.gg/ipe?extensions=com.intellij.spring.boot.customHintReferenceProvider) | `SpringBootCustomHintReferenceProvider` | 
| [com.intellij.spring.boot.modelConditionalContributor](https://jb.gg/ipe?extensions=com.intellij.spring.boot.modelConditionalContributor) | `ConditionalContributor` | 
| [com.intellij.spring.boot.modelConfigFileContributor](https://jb.gg/ipe?extensions=com.intellij.spring.boot.modelConfigFileContributor) | `SpringBootModelConfigFileContributor` | 
| [com.intellij.spring.boot.modelConfigFileNameContributor](https://jb.gg/ipe?extensions=com.intellij.spring.boot.modelConfigFileNameContributor) | `SpringBootModelConfigFileNameContributor` | 
| [com.intellij.spring.boot.modelExtender](https://jb.gg/ipe?extensions=com.intellij.spring.boot.modelExtender) | `SpringBootModelExtender` | 
| [com.intellij.spring.boot.placeholderReferenceResolver](https://jb.gg/ipe?extensions=com.intellij.spring.boot.placeholderReferenceResolver) | `SpringBootPlaceholderReferenceResolver` | 
| [com.intellij.spring.boot.replacementTokenResolver](https://jb.gg/ipe?extensions=com.intellij.spring.boot.replacementTokenResolver) | `SpringBootReplacementTokenResolver` | 
| [com.intellij.spring.boot.run.applicationUrlPathProviderFactory](https://jb.gg/ipe?extensions=com.intellij.spring.boot.run.applicationUrlPathProviderFactory) | `SpringBootApplicationUrlPathProviderFactory` | 
| [com.intellij.spring.boot.run.endpoint](https://jb.gg/ipe?extensions=com.intellij.spring.boot.run.endpoint) ![Experimental API](https://img.shields.io/badge/-Experimental_API-red) | `Endpoint` | 
| [com.intellij.spring.boot.run.endpointTabConfigurable](https://jb.gg/ipe?extensions=com.intellij.spring.boot.run.endpointTabConfigurable) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `EndpointTabConfigurable` | 
| [com.intellij.spring.boot.run.liveBeansPanelContent](https://jb.gg/ipe?extensions=com.intellij.spring.boot.run.liveBeansPanelContent) | `LiveBeansPanelContent` | 

### com.intellij.spring.integration
com.intellij.spring.integration

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.integration.security.accessAttributeContributor](https://jb.gg/ipe?extensions=com.intellij.spring.integration.security.accessAttributeContributor) | `AccessAttributeContributor` | 

### com.intellij.spring.messaging
com.intellij.spring.messaging

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.messaging.urlProvider](https://jb.gg/ipe?extensions=com.intellij.spring.messaging.urlProvider) | `SpringMessagingUrlProvider` | 

### com.intellij.spring.mvc
com.intellij.spring.mvc

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.mvc.applicationPortProvider](https://jb.gg/ipe?extensions=com.intellij.spring.mvc.applicationPortProvider) | `SpringApplicationPortProvider` | 
| [com.intellij.spring.mvc.mergingMvcRequestMappingLineMarkerProvider](https://jb.gg/ipe?extensions=com.intellij.spring.mvc.mergingMvcRequestMappingLineMarkerProvider) | `SpringMergingMvcRequestMappingLineMarkerProvider` | 
| [com.intellij.spring.mvc.viewResolverFactory](https://jb.gg/ipe?extensions=com.intellij.spring.mvc.viewResolverFactory) | `ViewResolverFactory` | 

### com.intellij.spring.security
com.intellij.spring.security

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.security.rolesProvider](https://jb.gg/ipe?extensions=com.intellij.spring.security.rolesProvider) | `SpringSecurityRolesProvider` | 

### com.intellij.spring.webflow
com.intellij.spring.webflow

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.webflow.securedAttributesContributor](https://jb.gg/ipe?extensions=com.intellij.spring.webflow.securedAttributesContributor) | `ExtendableSecuredAttributesContributor` | 

### spring-boot-mvc.xml
spring-boot-mvc.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.boot.mvc.templateAvailabilityProvider](https://jb.gg/ipe?extensions=com.intellij.spring.boot.mvc.templateAvailabilityProvider) | `TemplateAvailabilityProvider` | 

### spring-diagram-integration.xml
spring-diagram-integration.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.gutterDiagramActionProvider](https://jb.gg/ipe?extensions=com.intellij.spring.gutterDiagramActionProvider) | `SpringGutterDiagramActionProvider` | 

### spring-el.xml
spring-el.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.spring.el.contexts](https://jb.gg/ipe?extensions=com.intellij.spring.el.contexts) | `SpringElContextsExtension` | 
| [com.intellij.spring.el.injection.context](https://jb.gg/ipe?extensions=com.intellij.spring.el.injection.context) | `SpringElInjectionContext` | 
