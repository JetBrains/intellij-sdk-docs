<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- GENERATED FILE, DO NOT EDIT -->
<!-- This file is generated with the SDK Docs Authoring Tools plugin ('Generate SDK Docs EP Lists' action) -->
<!-- Revision: b815cfdcaa594a8842ccf89e6ac2bf7615401920 -->

<!--
EP List Directories:
- /goland
-->


<snippet id="content">

17 Extension Points and 6 Listeners

<include from="snippets.topic" element-id="ep_list_legend"/>

## GoLand

### GoLand – Listeners

| Topic | Listener |
|-------|----------|
| [`GoLibrariesService#LIBRARIES_TOPIC`](https://jb.gg/ipe/listeners?topics=com.goide.project.GoLibrariesService.LibrariesListener)  | `LibrariesListener` |
| [`GoModuleSettings#BUILD_TARGET_TOPIC`](https://jb.gg/ipe/listeners?topics=com.goide.project.GoModuleSettings.BuildTargetListener)  ![Project-Level][project-level] | `BuildTargetListener` |
| [`GoModuleSettings#GO_SUPPORT_TOPIC`](https://jb.gg/ipe/listeners?topics=com.goide.project.GoModuleSettings.GoSupportListener)  ![Project-Level][project-level] | `GoSupportListener` |
| [`GoProjectLifecycleListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.goide.project.GoProjectLifecycleListener)  | `GoProjectLifecycleListener` |
| [`VgoUpdateProgressManager#UPDATE_PROGRESS_TOPIC`](https://jb.gg/ipe/listeners?topics=com.goide.vgo.VgoStatusTracker.VgoUpdateProgressManager.VgoModuleUpdateProgressListener)  ![Project-Level][project-level] | `VgoModuleUpdateProgressListener` |
| [`VgoProjectSettings#VGO_INTEGRATION_TOPIC`](https://jb.gg/ipe/listeners?topics=com.goide.vgo.configuration.VgoProjectSettings.IntegrationListener)  | `IntegrationListener` |


### goland.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.goide.dlv.positionConverterFactory"/></include> | `DlvPositionConverterFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.goide.documentation.packageVersionProvider"/></include> | `GoDocumentationPackageVersionProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.goide.execution.defaultTargetEnvironmentProvider"/></include> | `DefaultTargetEnvironmentProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.goide.executorExtension"/></include> | `GoExecutorExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.goide.highlighting.errorAnnotatorSuppressor"/></include> | `GoErrorAnnotatorSuppressor` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.goide.importResolver"/></include> | `GoImportResolver` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.goide.importsFilter"/></include> | `GoImportsFilter` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.goide.packageFactory"/></include> | `GoPackageFactory` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.goide.runConfigurationExtension"/></include> | `GoRunConfigurationExtension` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.goide.sdk.sdkVetoer"/></include> | `GoBasedSdkVetoer` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.goide.sdk.targetSdkVersionProvider"/></include> | `GoTargetSdkVersionProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.goide.sdkProvider"/></include> | `GoSdkProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.goide.support"/></include> | `GoLangSupport` |

### openapi.xml

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.goide.documentation.additionalDocumentationProvider"/></include> | `GoAdditionalDocumentationProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.goide.importPathsProvider"/></include> | `GoImportPathsProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.goide.imports.weigher"/></include> | `GoImportsWeigher` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.goide.rootsProvider"/></include> | `GoRootsProvider` |


[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square

</snippet>
