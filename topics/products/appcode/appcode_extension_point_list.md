[//]: # (title: AppCode Extension Point List)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

20 Extension Points (EP) for AppCode

See [Extension Point List](extension_point_list.md) for IntelliJ Platform EPs.

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
        
## AppCode
                
### AppCodeSwiftPlugin.xml
AppCodeSwiftPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [appcode.swift.sourceModuleProducer](https://jb.gg/ipe?extensions=appcode.swift.sourceModuleProducer) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `AppcodeSourceModuleProducer` | 

### CocoaCommonPlugin.xml
CocoaCommonPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.cocoa.xcodeProjectFileProvider](https://jb.gg/ipe?extensions=cidr.cocoa.xcodeProjectFileProvider) | `XcodeProjectFileProvider` | 

### CocoaPlugin.xml
CocoaPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.cocoa.documentation.search.candidates.helper](https://jb.gg/ipe?extensions=cidr.cocoa.documentation.search.candidates.helper) | `XcodeDocumentationCandidateBasedSearchHelper` | 
| [cidr.cocoa.documentation.search.usr.provider](https://jb.gg/ipe?extensions=cidr.cocoa.documentation.search.usr.provider) | `XcodeDocumentationUsrProvider` | 

### com.intellij.appcode
com.intellij.appcode

| Extension Point | Implementation |
|-----------------|----------------|
| [appcode.runConfigurationExtension](https://jb.gg/ipe?extensions=appcode.runConfigurationExtension) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `AppCodeRunConfigurationExtension` | 
| [appcode.xcodeExternalBuildProvider](https://jb.gg/ipe?extensions=appcode.xcodeExternalBuildProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `XcodeExternalBuildProvider` | 
| [appcode.xcodeTemplatePathsProvider](https://jb.gg/ipe?extensions=appcode.xcodeTemplatePathsProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `XcodeTemplatePathsProvider` | 

### SwiftLanguageInternalPlugin.xml
SwiftLanguageInternalPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [swift.sdkInfo.extractor](https://jb.gg/ipe?extensions=swift.sdkInfo.extractor) | `SwiftOCResolveConfigurationSdkInfoExtractor` | 

### SwiftPlugin.xml
SwiftPlugin.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [cidr.lang.swiftCustomIncludePathProvider](https://jb.gg/ipe?extensions=cidr.lang.swiftCustomIncludePathProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `SwiftCustomIncludePathProvider` | 
| [cidr.lang.swiftSourceModuleProvider](https://jb.gg/ipe?extensions=cidr.lang.swiftSourceModuleProvider) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `SwiftSourceModuleProvider` | 
| [cidr.lang.swiftTypeInheritorsSearch](https://jb.gg/ipe?extensions=cidr.lang.swiftTypeInheritorsSearch) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | [`QueryExecutor`](upsource:///platform/core-api/src/com/intellij/util/QueryExecutor.java) | 
| [swift.kotlinNative](https://jb.gg/ipe?extensions=swift.kotlinNative) ![Non-Dynamic](https://img.shields.io/badge/-Non--Dynamic-orange) | `KotlinNativeExtensionPoint` | 
| [swift.lang.sourceKit.compileArgumentsCollector](https://jb.gg/ipe?extensions=swift.lang.sourceKit.compileArgumentsCollector) | `SwiftSourceKitCompileArgumentsCollector` | 
| [swift.lang.sourceKit.dataGenerator](https://jb.gg/ipe?extensions=swift.lang.sourceKit.dataGenerator) ![Project-Level](https://img.shields.io/badge/-Project--Level-yellow) | `SourceKitDataGenerator` | 
| [swift.lang.sourceKit.declarationLocationValidator](https://jb.gg/ipe?extensions=swift.lang.sourceKit.declarationLocationValidator) | `SourceKitDeclarationLocationValidator` | 
| [swift.lang.swiftSupportProvider](https://jb.gg/ipe?extensions=swift.lang.swiftSupportProvider) | `SwiftSupportProvider` | 

### SwiftPMCommon.xml
SwiftPMCommon.xml

| Extension Point | Implementation |
|-----------------|----------------|
| [swift.packageManager.appleSdk.filter](https://jb.gg/ipe?extensions=swift.packageManager.appleSdk.filter) | `SwiftPackageLoadedAppleSdkFilter` | 
| [swift.packageManager.environmentConfigurator](https://jb.gg/ipe?extensions=swift.packageManager.environmentConfigurator) | `SwiftPackageManagerEnvironmentConfigurator` | 
| [swift.packageManager.modulemapsCollector](https://jb.gg/ipe?extensions=swift.packageManager.modulemapsCollector) | `SwiftPackageModuleMapsCollector` | 
| [swift.packageManager.systemModuleResolver](https://jb.gg/ipe?extensions=swift.packageManager.systemModuleResolver) | `SwiftPackageManagerSystemModuleResolver` | 
