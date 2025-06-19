<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- NO LONGER UPDATED ====================================================== -->

<snippet id="content">

# AppCode Extension Point and Listener List
<primary-label ref="Deprecated"/>

<link-summary>Overview of Extension Points and Listeners for AppCode.</link-summary>

<tldr>

**Product-Specific Plugin Development**: [AppCode](app_code.md)

</tldr>

> See [](intellij_platform_extension_point_list.md) for IntelliJ Platform.

<include from="app_code.md" element-id="appCodeSunset"/>

34 Extension Points and 6 Listeners for AppCode 2022.3

<include from="snippets.topic" element-id="ep_list_legend"/>

## AppCode

### AppCode – Listeners


| Topic | Listener |
|-------|----------|
| [CocoaPodsUtils#GEM_TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cocoapods.CocoaPodsUtils.GemListener)  | `GemListener` |
| [CocoaPodsUtils#PODS_TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.cocoapods.CocoaPodsUtils.PodsListener)  | `PodsListener` |
| [AMDeviceManager#DEVICE_LISTENER_TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.execution.deviceSupport.AMDeviceListener)  | `AMDeviceListener` |
| [XcodeProjectTestListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.xcode.model.XcodeProjectTestListener)  | `XcodeProjectTestListener` |
| [XcodeIsBrokenListener.Companion#XCODE_IS_BROKEN_TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.cidr.xcode.refresh.XcodeIsBrokenListener)  | `XcodeIsBrokenListener` |
| [SwiftPackageManagerSettingsListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.swift.swiftpm.SwiftPackageManagerSettingsListener)  | `SwiftPackageManagerSettingsListener` |


### AppCodeCorePlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [appcode.additionalRootsProvider](https://jb.gg/ipe?extensions=appcode.additionalRootsProvider) ![Non-Dynamic][non-dynamic] | `XcodeMetaDataAdditionalRootsProvider` |
| [appcode.attachDebuggerProvider](https://jb.gg/ipe?extensions=appcode.attachDebuggerProvider) ![Non-Dynamic][non-dynamic] | `AppCodeAttachDebuggerExtension` |
| [appcode.projectNameUpdateVeto](https://jb.gg/ipe?extensions=appcode.projectNameUpdateVeto) ![Non-Dynamic][non-dynamic] | `XcodeProjectNameUpdateVeto` |
| [appcode.projectRootNodeDelegateProvider](https://jb.gg/ipe?extensions=appcode.projectRootNodeDelegateProvider) ![Non-Dynamic][non-dynamic] | `AppCodeProjectRootNodeDelegateProvider` |
| [appcode.rootsInfoPostProcessor](https://jb.gg/ipe?extensions=appcode.rootsInfoPostProcessor) ![Non-Dynamic][non-dynamic] | `XcodeRootsInfoPostProcessor` |
| [appcode.runConfigurationExtension](https://jb.gg/ipe?extensions=appcode.runConfigurationExtension) ![Non-Dynamic][non-dynamic] | `AppCodeRunConfigurationExtension` |
| [appcode.xcodeExternalBuildProvider](https://jb.gg/ipe?extensions=appcode.xcodeExternalBuildProvider) ![Non-Dynamic][non-dynamic] | `XcodeExternalBuildProvider` |
| [appcode.xcodeTemplatePathsProvider](https://jb.gg/ipe?extensions=appcode.xcodeTemplatePathsProvider) ![Non-Dynamic][non-dynamic] | `XcodeTemplatePathsProvider` |
| [appcode.xcodeTemplatesProvider](https://jb.gg/ipe?extensions=appcode.xcodeTemplatesProvider) | `XcodeTemplatesProvider` |

### CocoaCommonPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [appcode.breakpointHandlersProvider](https://jb.gg/ipe?extensions=appcode.breakpointHandlersProvider) ![Non-Dynamic][non-dynamic] | `IPhoneBreakpointHandlersProvider` |
| [appcode.lldbInitializerProvider](https://jb.gg/ipe?extensions=appcode.lldbInitializerProvider) ![Non-Dynamic][non-dynamic] | `LLDBInitializerProvider` |
| [cidr.cocoa.xcodeProjectFileProvider](https://jb.gg/ipe?extensions=cidr.cocoa.xcodeProjectFileProvider) | `XcodeProjectFileProvider` |

### CocoaPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.cocoa.documentation.search.candidates.helper](https://jb.gg/ipe?extensions=cidr.cocoa.documentation.search.candidates.helper) | `XcodeDocumentationCandidateBasedSearchHelper` |
| [cidr.cocoa.documentation.search.usr.provider](https://jb.gg/ipe?extensions=cidr.cocoa.documentation.search.usr.provider) | `XcodeDocumentationUsrProvider` |

### SwiftLanguageInternalPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.lang.swiftTypeInheritorsSearch](https://jb.gg/ipe?extensions=cidr.lang.swiftTypeInheritorsSearch) ![Non-Dynamic][non-dynamic] | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [swift.lang.libraryModuleImportRestriction](https://jb.gg/ipe?extensions=swift.lang.libraryModuleImportRestriction) | `SwiftLibraryModuleImportRestriction` |
| [swift.sdkInfo.extractor](https://jb.gg/ipe?extensions=swift.sdkInfo.extractor) | `OCResolveConfigurationSdkInfoExtractor` |
| [swift.sourcekit.blacklistedModulesProvider](https://jb.gg/ipe?extensions=swift.sourcekit.blacklistedModulesProvider) ![Non-Dynamic][non-dynamic] | `SourceKitBlacklistedModulesProvider` |
| [swift.sourcekit.dependenciesLoader](https://jb.gg/ipe?extensions=swift.sourcekit.dependenciesLoader) | `SourceKitPlatformBinaryDependenciesLoader` |

### SwiftPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.lang.swiftCustomIncludePathProvider](https://jb.gg/ipe?extensions=cidr.lang.swiftCustomIncludePathProvider) ![Non-Dynamic][non-dynamic] | `SwiftCustomIncludePathProvider` |
| [cidr.lang.swiftSourceModuleProvider](https://jb.gg/ipe?extensions=cidr.lang.swiftSourceModuleProvider) ![Non-Dynamic][non-dynamic] | `SwiftSourceModuleProvider` |
| [swift.kotlinNative](https://jb.gg/ipe?extensions=swift.kotlinNative) ![Non-Dynamic][non-dynamic] | `KotlinNativeExtensionPoint` |
| [swift.lang.sourceKit.compileArgumentsCollector](https://jb.gg/ipe?extensions=swift.lang.sourceKit.compileArgumentsCollector) | `SwiftSourceKitCompileArgumentsCollector` |
| [swift.lang.sourceKit.dataGenerator](https://jb.gg/ipe?extensions=swift.lang.sourceKit.dataGenerator) ![Project-Level][project-level] | `SourceKitDataGenerator` |
| [swift.lang.sourceKit.declarationLocationValidator](https://jb.gg/ipe?extensions=swift.lang.sourceKit.declarationLocationValidator) | `SourceKitDeclarationLocationValidator` |
| [swift.lang.sourceKitFixExtension](https://jb.gg/ipe?extensions=swift.lang.sourceKitFixExtension) | `SwiftSourceKitFixExtension` |
| [swift.lang.swiftSupportProvider](https://jb.gg/ipe?extensions=swift.lang.swiftSupportProvider) | `SwiftSupportProvider` |

### SwiftPMCommon.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [swift.packageManager.appleSdk.filter](https://jb.gg/ipe?extensions=swift.packageManager.appleSdk.filter) | `SwiftPackageLoadedAppleSdkFilter` |
| [swift.packageManager.environmentConfigurator](https://jb.gg/ipe?extensions=swift.packageManager.environmentConfigurator) | `SwiftPackageManagerEnvironmentConfigurator` |
| [swift.packageManager.launcher](https://jb.gg/ipe?extensions=swift.packageManager.launcher) | `SwiftPackageManagerConfigurationLauncher` |
| [swift.packageManager.modulemapsCollector](https://jb.gg/ipe?extensions=swift.packageManager.modulemapsCollector) | `SwiftPackageModuleMapsCollector` |
| [swift.packageManager.systemModuleResolver](https://jb.gg/ipe?extensions=swift.packageManager.systemModuleResolver) | `SwiftPackageManagerSystemModuleResolver` |

### SwiftTestsExtension.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [test.cidr.OCCodeInsightDelegate](https://jb.gg/ipe?extensions=test.cidr.OCCodeInsightDelegate) ![Non-Dynamic][non-dynamic] | `OCCodeInsightDelegate` |

### XcodeModelCorePlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [xcode.pbxReferenceBuildSettingsProvider](https://jb.gg/ipe?extensions=xcode.pbxReferenceBuildSettingsProvider) ![Non-Dynamic][non-dynamic] | `PBXReferenceBuildSettingProvider` |

[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square

</snippet>
