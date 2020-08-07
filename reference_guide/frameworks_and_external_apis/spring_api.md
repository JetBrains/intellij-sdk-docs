---
title: Spring API
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Spring API allows 3rd party plugins to re-use, integrate with or extend existing Spring Framework support in IntelliJ IDEA.

A popular plugin using Spring API is [hybris integration](https://plugins.jetbrains.com/plugin/7525-hybris-integration).

To develop plugins you will need to use _IntelliJ IDEA Ultimate Edition_ version 13.1 or higher.

## Setting up IntelliJ Platform SDK

> **NOTE** This applies to [Plugin DevKit](/basics/getting_started/using_dev_kit.md) projects only. For [Gradle](/tutorials/build_system.md) projects, simply add dependency to bundled Spring plugin `com.intellij.spring`.

### New SDK
Please create an IntelliJ Platform SDK to include all minimum required files.
Then add `$IDEA_HOME$/plugins/Spring/lib/spring.jar` to its _classpath_ (_not_ to your plugin module's dependencies).

### Existing SDK
Follow these steps to modify existing IntelliJ Platform SDK:

* add to _classpath_ (_not_ to your plugin module's dependencies)
	* `$IDEA_HOME$/plugins/Spring/lib/spring-api.jar` _not in recent versions, merged into `spring.jar`_
	* `$IDEA_HOME$/plugins/Spring/lib/spring.jar`
* add to _sourcepath_
	* `$IDEA_HOME$/lib/src/src_spring-openapi.zip`

### General Notes
If you use other Spring functionality (e.g. Spring EL) in your plugin, make sure to add all required JARs to your IntelliJ Platform SDK classpath to make your plugin's tests work.


Please use only Spring-related functionality exposed in `spring-api.jar` (where sources are provided) in your plugin. Using any other "internal" (implementation) classes from Spring plugin itself (`spring.jar`) is _not_ supported.

### plugin.xml
Add `<depends>com.intellij.spring</depends>` to your `plugin.xml` to require "Spring Support" plugin to be activated. All available extension points are provided under `com.intellij.spring` prefix.
Note that "Spring Support" plugin itself has dependencies to a few other plugins which need to be enabled in your sandbox (see notifications on startup).

## Main Concepts
A Spring facet can be attached on a Module. (Nearly) All Spring functionality requires an existing and properly setup Spring facet.

Spring facets usually contain one more user-configured or automatically provided filesets, which group a set of Spring related configuration files (XML, Code, .properties or other configuration files).

A fileset usually corresponds to an actual application context configuration at runtime. Hierarchies can be modeled by depending on another fileset (possibly from  another module).

As an API-user, you will usually rather work with `SpringModel` (which is built on top of fileset(s)).

## API Updates
> **NOTE** 2017.3: `LocalXmlModel#setActiveProfiles` & `LocalAnnotationModel#setActiveProfiles` have been deprecated and will be removed in 2018.1.

> **NOTE** Starting with 2016.2, internal representation of bean _type_ has been changed from `PsiClass` to `PsiType`, please note deprecations.

> **NOTE** Some core classes have been changed in 14(.1), please see "_Version 14(.1)_" notes for info on how to replace existing API-calls.

## How Do I...

### Spring Setup
To check availability of Spring/Spring Facet etc. see `com.intellij.spring.model.utils.SpringCommonUtils`.

_2016.2_ See `com.intellij.spring.SpringLibraryUtil` to obtain information about exact version of Spring in use.

### Spring Model

#### Obtain Spring Model by File, PsiElement, ...
See `SpringManager#getSpringModel(s)...` and `com.intellij.spring.model.utils.SpringModelUtils`.

#### Contribute Implicit Model
See `com.intellij.spring.SpringModelProvider` to provide implicit filesets (e.g. provided by another framework in specific configuration file).

_Version 15_
See `com.intellij.spring.facet.SpringAutodetectedFileSet` for a convenient base class. Please note that autodetected filesets cannot be edited/modified by users in Spring facet.

#### Customize Implicit Models Configuration
_2017.1_ See `com.intellij.spring.facet.SpringFileSetEditorCustomization` to customize presentation and/or add extra settings/actions for specific autodetected filesets.

#### Contribute Implicit Beans
See `com.intellij.spring.model.jam.CustomComponentsDiscoverer` or `com.intellij.spring.model.SpringImplicitBeansProviderBase` to provide implicit (framework-specific) beans (e.g. "servletContext" by Spring MVC).

_Version 15_
`CustomComponentsDiscoverer` has been split into `com.intellij.spring.model.custom.CustomLocalComponentsDiscoverer` and `com.intellij.spring.model.custom.CustomModuleComponentsDiscoverer` respectively.

#### Contribute Custom Bean Scope
_Version 14_
See `com.intellij.spring.model.scope.SpringCustomBeanScope` to provide custom (e.g. framework specific) bean scopes.

#### Obtain/Create Spring Profiles
_Version 14.1_
`com.intellij.spring.profiles.SpringProfilesFactory`

### Beans

#### Search for Bean by Name
`com.intellij.spring.CommonSpringModel#findBeanByName`

_Version 14_: `com.intellij.spring.model.utils.SpringModelSearchers#findBean`

#### Search for Beans by Type
Choose one of `com.intellij.spring.CommonSpringModel#findBeansByPsiClassXXX` variants (please note deprecated methods).

_Version 14_: `com.intellij.spring.model.utils.SpringModelSearchers#findBeans`

_Version 16_: note deprecation of `SpringModelSearchParameters.BeanClass#withInheritors(GlobalSearchScope)`

#### Find out if Bean with Given Name/Type Exists
_Version 14_: `com.intellij.spring.model.utils.SpringModelSearchers#doesBeanExist` (please note deprecated methods)

#### Mark Bean as Infrastructure Bean
_Version 14_: implement `SpringInfrastructureBean`, such beans obtain special icon and can be filtered in various places in UI.

### XML Configuration
All support for XML-based Spring configuration files is provided via [DOM-API](xml_dom_api.md).

#### Add Support for Additional Spring Namespace
See EP `com.intellij.spring.dom.SpringCustomNamespaces`, registered namespace-key must match the one registered with your DOM elements via `@Namespace`.
Register available elements via standard `DomExtender<Beans>` EP or `com.intellij.spring.dom.SpringCustomNamespaces#registerExtensions` (Version 14).

Please pay attention to `getModelVersion` and `getStubVersion` (see javadoc).

#### Add Reference to Spring Bean in DomElement
Use the following template:

```java
@Convert(SpringBeanResolveConverter.class)
@RequiredBeanType("fqn.to.required.class") // optional
GenericAttributeValue<SpringBeanPointer> getMyAttributeName();
```


### Code Configuration

#### Add Reference to Spring Bean in JamElement
_Version 14_

```java
JamStringAttributeMeta.Single<SpringBeanPointer> ATTRIBUTE_META =
    JamAttributeMeta.singleString("attributeName",
        new SpringBeanReferenceJamConverter("fqn.to.required.class"));
```

#### @AliasFor
_Version 16_
See `com.intellij.spring.model.aliasFor.SpringAliasForUtils` to obtain corresponding `@AliasFor` JAM.

### spring.factories
_Version 15_
See `com.intellij.spring.spi.SpringSpiManager`.

### IDE Features

#### Add Inspections to Spring Validator
Add additional inspections (e.g. for custom namespace) to Spring Validator (*Settings|Compiler|Validation*) via EP `com.intellij.spring.SpringInspectionsRegistry$Contributor`.

#### Add Additional Files to Spring Validator
_Version 14.1_
Additional files to be processed by inspections registered with Spring Validator (e.g. specific `.properties` configuration files) can be registered via  `com.intellij.spring.SpringInspectionsRegistry$AdditionalFilesContributor`

#### Configure Spring Support for Other Frameworks
Use `com.intellij.spring.facet.SpringConfigurator` to provide "automatic" configuration when Spring facet is added via framework wizard.

#### UI/Presentation
Please do not reference bean icons from `SpringApiIcons` directly, but use `SpringPresentationProvider` to re-use unified icon/bean name. See `SpringBeansPsiElementCellRenderer` for popup/list renderer.


## Spring Boot
_2018.1_

Spring Boot API allows to extend/access Spring Boot specific support in the IDE.

> **WARNING** While we try to maintain compatibility, please be prepared for a less strict policy. 

### Setting up
Please perform these steps _additionally_ to setting up Spring API support (see [here](#setting-up-intellij-platform-sdk)):

* add to _classpath_ (_not_ to your plugin module's dependencies)
	* `$IDEA_HOME$/plugins/SpringBoot/lib/spring-boot.jar`
	* `$IDEA_HOME$/plugins/SpringBoot/lib/spring-boot-initializr.jar` (optional)
	* `$IDEA_HOME$/plugins/SpringBoot/lib/spring-boot-run.jar` (optional)
* add to _sourcepath_
	* `$IDEA_HOME$/lib/src/src_spring-boot-openapi.zip`

### plugin.xml
Add `<depends>com.intellij.spring.boot</depends>` to your `plugin.xml` to require "Spring Boot" plugin to be activated. All available extension points are provided under `com.intellij.spring.boot` prefix.

### Spring Boot Library
Use `com.intellij.spring.boot.library.SpringBootLibraryUtil` to query version and availability of common additional libraries.

### Custom Configuration Files Format
`com.intellij.spring.boot.model.SpringBootModelConfigFileContributor` allows to add support for custom config file formats.    
                                            
### Auto-Configuration Support
Existing `Condition` implementations can be simulated at design time in IDE via `com.intellij.spring.boot.model.autoconfigure.conditions.ConditionalContributor`.

Custom `@ConditionalOn...` annotations implementing `com.intellij.spring.boot.model.autoconfigure.conditions.jam.ConditionalOnJamElement` will be added into evaluation automatically.              

### Spring Initializr
requires `spring-boot-initializr.jar`

`com.intellij.spring.boot.initializr.SpringInitializrModuleBuilderPostTask` allows to perform custom setup steps after creation of module (e.g. setup integration with build system).

### Endpoint Tab
_2018.2_ - requires `spring-boot-run.jar`

Use EP `com.intellij.spring.boot.run.endpoint` to add custom actuator endpoint tabs. Any settings should be exposed in "Spring Boot" settings tab via `com.intellij.spring.boot.run.endpointTabConfigurable` EP.