<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!--
EP List Directory:
  /community/java
  /community/json
  /community/jvm
  /community/plugins
  /community/python

There must be no top-level "Listeners" group, adjust ExtensionPointAnalyzerAction.Group accordingly.
-->

# IntelliJ Community Plugins Extension Point and Listener List

<link-summary>Overview of Extension Points and Listeners for IntelliJ Platform.</link-summary>

630 Extension Points and 70 Listeners for IntelliJ Community Plugins

<include from="snippets.topic" element-id="ep_list_legend"/>

## IntelliJ Community Plugins

### ByteCodeViewer

[`ByteCodeViewer`](%gh-ic%/plugins/ByteCodeViewer/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [ByteCodeViewer.classSearcher](https://jb.gg/ipe?extensions=ByteCodeViewer.classSearcher) | [`ClassSearcher`](%gh-ic%/plugins/ByteCodeViewer/src/com/intellij/byteCodeViewer/ClassSearcher.kt) |

### com.intellij.completion.evaluation

[`com.intellij.completion.evaluation`](%gh-ic%/plugins/evaluation-plugin/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.cce.apiCallExtractor](https://jb.gg/ipe?extensions=com.intellij.cce.apiCallExtractor) | [`ApiCallExtractorProvider`](%gh-ic%/plugins/evaluation-plugin/core/src/com/intellij/cce/metric/ApiCallExtractor.kt) |
| [com.intellij.cce.codeCompletionHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.cce.codeCompletionHandlerFactory) ![Project-Level][project-level] | [`CodeCompletionHandlerFactory`](%gh-ic%/plugins/evaluation-plugin/languages/src/com/intellij/cce/evaluation/CodeCompletionHandlerFactory.kt) |
| [com.intellij.cce.codeExecutionManager](https://jb.gg/ipe?extensions=com.intellij.cce.codeExecutionManager) | [`CodeExecutionManager`](%gh-ic%/plugins/evaluation-plugin/languages/src/com/intellij/cce/execution/manager/CodeExecutionManager.kt) |
| [com.intellij.cce.command](https://jb.gg/ipe?extensions=com.intellij.cce.command) | [`EvaluationCommandExtension`](%gh-ic%/plugins/evaluation-plugin/src/com/intellij/cce/commands/EvaluationCommandExtension.kt) |
| [com.intellij.cce.completionEvaluationVisitor](https://jb.gg/ipe?extensions=com.intellij.cce.completionEvaluationVisitor) | [`EvaluationVisitor`](%gh-ic%/plugins/evaluation-plugin/languages/src/com/intellij/cce/visitor/EvaluationVisitor.kt) |
| [com.intellij.cce.datasetActionProvider](https://jb.gg/ipe?extensions=com.intellij.cce.datasetActionProvider) | [`DatasetActionProvider`](%gh-ic%/plugins/evaluation-plugin/src/com/intellij/cce/ui/DatasetActionProvider.kt) |
| [com.intellij.cce.evaluableFeature](https://jb.gg/ipe?extensions=com.intellij.cce.evaluableFeature) | [`EvaluableFeature`](%gh-ic%/plugins/evaluation-plugin/src/com/intellij/cce/evaluable/EvaluableFeature.kt) |
| [com.intellij.cce.exposedApiExtractor](https://jb.gg/ipe?extensions=com.intellij.cce.exposedApiExtractor) | [`ExposedApiExtractor`](%gh-ic%/plugins/evaluation-plugin/core/src/com/intellij/cce/evaluable/ExposedApiExtractor.kt) |
| [com.intellij.cce.extraEvaluationStepProvider](https://jb.gg/ipe?extensions=com.intellij.cce.extraEvaluationStepProvider) | [`ExtraEvaluationStepProvider`](%gh-ic%/plugins/evaluation-plugin/src/com/intellij/cce/evaluation/step/ExtraEvaluationStepProvider.kt) |
| [com.intellij.cce.lineCompletionVisitorFactory](https://jb.gg/ipe?extensions=com.intellij.cce.lineCompletionVisitorFactory) | [`LineCompletionVisitorFactory`](%gh-ic%/plugins/evaluation-plugin/languages/src/com/intellij/cce/visitor/LineCompletionVisitorFactory.kt) |
| [com.intellij.cce.openProjectMethodProvider](https://jb.gg/ipe?extensions=com.intellij.cce.openProjectMethodProvider) | [`OpenProjectMethodProvider`](%gh-ic%/plugins/evaluation-plugin/languages/src/com/intellij/cce/actions/OpenProjectMethodProvider.kt) |
| [com.intellij.cce.setupSdkStep](https://jb.gg/ipe?extensions=com.intellij.cce.setupSdkStep) ![Project-Level][project-level] | [`SetupSdkStep`](%gh-ic%/plugins/evaluation-plugin/languages/src/com/intellij/cce/evaluation/SetupSdkStep.kt) |
| [com.intellij.cce.suggestionsProvider](https://jb.gg/ipe?extensions=com.intellij.cce.suggestionsProvider) ![Project-Level][project-level] | [`SuggestionsProvider`](%gh-ic%/plugins/evaluation-plugin/languages/src/com/intellij/cce/evaluation/SuggestionsProvider.kt) |

### com.intellij.completion.ml.ranking

[`com.intellij.completion.ml.ranking`](%gh-ic%/plugins/completion-ml-ranking/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.completion.ml.additionalContextFeatures](https://jb.gg/ipe?extensions=com.intellij.completion.ml.additionalContextFeatures) ![Internal][internal] | [`AdditionalContextFeatureProvider`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/sorting/AdditionalContextFeatureProvider.java) |
| [com.intellij.completion.ml.elementIdProvider](https://jb.gg/ipe?extensions=com.intellij.completion.ml.elementIdProvider) ![Internal][internal] | [`LookupElementIdProvider`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/util/LookupElementIdProvider.kt) |
| [com.intellij.completion.ml.experimentFetcher](https://jb.gg/ipe?extensions=com.intellij.completion.ml.experimentFetcher) | [`MLRankingExperimentFetcher`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/experiments/MLRankingExperimentFetcher.kt) |
| [com.intellij.completion.ml.featuresOverride](https://jb.gg/ipe?extensions=com.intellij.completion.ml.featuresOverride) ![Internal][internal] | [`RankingFeaturesOverrides`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/features/RankingFeaturesOverrides.kt) |
| [com.intellij.completion.ml.localModelProvider](https://jb.gg/ipe?extensions=com.intellij.completion.ml.localModelProvider) ![Internal][internal] | [`LocalZipModelProvider`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/ranker/local/LocalZipModelProvider.kt) |
| [com.intellij.completion.ml.lookupFeatures](https://jb.gg/ipe?extensions=com.intellij.completion.ml.lookupFeatures) ![Internal][internal] | [`LookupFeatureProvider`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/sorting/LookupFeatureProvider.java) |
| [com.intellij.completion.ml.ranking.features.policy](https://jb.gg/ipe?extensions=com.intellij.completion.ml.ranking.features.policy) ![Internal][internal] | [`CompletionFeaturesPolicy`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/features/CompletionFeaturesPolicy.kt) |
| [com.intellij.completion.ml.ranking.policy](https://jb.gg/ipe?extensions=com.intellij.completion.ml.ranking.policy) ![Experimental][experimental] | [`CompletionMLPolicy`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/CompletionMLPolicy.kt) |

### com.intellij.copyright

[`com.intellij.copyright`](%gh-ic%/plugins/copyright/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.copyright.updater](https://jb.gg/ipe?extensions=com.intellij.copyright.updater) | [`UpdateCopyrightsProvider`](%gh-ic%/plugins/copyright/src/com/maddyhome/idea/copyright/psi/UpdateCopyrightsProvider.java) |
| [com.intellij.copyright.variablesProvider](https://jb.gg/ipe?extensions=com.intellij.copyright.variablesProvider) | [`CopyrightVariablesProvider`](%gh-ic%/plugins/copyright/src/com/maddyhome/idea/copyright/pattern/CopyrightVariablesProvider.java) |

### com.intellij.java-i18n

[`com.intellij.java-i18n`](%gh-ic%/plugins/java-i18n/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.java-i18n.i18nizeHandlerProvider](https://jb.gg/ipe?extensions=com.intellij.java-i18n.i18nizeHandlerProvider) | [`I18nizeHandlerProvider`](%gh-ic%/plugins/java-i18n/src/com/intellij/codeInspection/i18n/I18nizeHandlerProvider.java) |
| [com.intellij.java-i18n.resourceBundleManager](https://jb.gg/ipe?extensions=com.intellij.java-i18n.resourceBundleManager) ![Project-Level][project-level] | [`ResourceBundleManager`](%gh-ic%/plugins/java-i18n/src/com/intellij/lang/properties/psi/ResourceBundleManager.java) |

### com.intellij.properties

[`com.intellij.properties`](%gh-ic%/plugins/properties/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.properties.alphaUnsortedInspectionSuppressor](https://jb.gg/ipe?extensions=com.intellij.properties.alphaUnsortedInspectionSuppressor) | [`AlphaUnsortedPropertiesFileInspectionSuppressor`](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/codeInspection/unsorted/AlphaUnsortedPropertiesFileInspectionSuppressor.java) |
| [com.intellij.properties.duplicatePropertyKeyAnnotationSuppressor](https://jb.gg/ipe?extensions=com.intellij.properties.duplicatePropertyKeyAnnotationSuppressor) | [`DuplicatePropertyKeyAnnotationSuppressor`](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/DuplicatePropertyKeyAnnotationSuppressor.java) |
| [com.intellij.properties.extendedUseScopeProvider](https://jb.gg/ipe?extensions=com.intellij.properties.extendedUseScopeProvider) ![Experimental][experimental] | [`ExtendedUseScopeProvider`](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/codeInspection/unused/ExtendedUseScopeProvider.java) |
| [com.intellij.properties.implicitPropertyUsageProvider](https://jb.gg/ipe?extensions=com.intellij.properties.implicitPropertyUsageProvider) | [`ImplicitPropertyUsageProvider`](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/codeInspection/unused/ImplicitPropertyUsageProvider.java) |
| [com.intellij.properties.spellcheckerMnemonicsTokenizer](https://jb.gg/ipe?extensions=com.intellij.properties.spellcheckerMnemonicsTokenizer) | [`MnemonicsTokenizer`](%gh-ic%/plugins/properties/src/com/intellij/lang/properties/spellchecker/MnemonicsTokenizer.java) |

### com.intellij.searcheverywhere.ml

[`com.intellij.searcheverywhere.ml`](%gh-ic%/plugins/search-everywhere-ml/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.searchEverywhereMl.itemSelectedListener](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereMl.itemSelectedListener) | [`SearchEverywhereItemSelectedListener`](%gh-ic%/plugins/search-everywhere-ml/src/com/intellij/searchEverywhereMl/SearchEverywhereItemSelectedListener.kt) |
| [com.intellij.searchEverywhereMl.rankingService](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereMl.rankingService) ![Internal][internal] | [`SearchEverywhereMlService`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereMlService.kt) |
| [com.intellij.searchEverywhereMl.searchEverywhereSessionPropertyProvider](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereMl.searchEverywhereSessionPropertyProvider) | [`SearchEverywhereSessionPropertyProvider`](%gh-ic%/plugins/search-everywhere-ml/src/com/intellij/searchEverywhereMl/SearchEverywhereSessionPropertyProvider.kt) |
| [com.intellij.searchEverywhereMl.textEmbeddingProvider](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereMl.textEmbeddingProvider) | [`TextEmbeddingProvider`](%gh-ic%/plugins/search-everywhere-ml/src/com/intellij/searchEverywhereMl/TextEmbeddingProvider.kt) |

### com.intellij.stats.completion

[`com.intellij.stats.completion`](%gh-ic%/plugins/stats-collector/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.stats.completion.policy](https://jb.gg/ipe?extensions=com.intellij.stats.completion.policy) ![Internal][internal] | [`CompletionStatsPolicy`](%gh-ic%/plugins/stats-collector/src/com/intellij/stats/completion/CompletionStatsPolicy.kt) |

### com.intellij.tasks

[`com.intellij.tasks`](%gh-ic%/plugins/tasks/tasks-core/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.tasks.commitPlaceholderProvider](https://jb.gg/ipe?extensions=com.intellij.tasks.commitPlaceholderProvider) | [`CommitPlaceholderProvider`](%gh-ic%/platform/tasks-platform-api/src/com/intellij/tasks/CommitPlaceholderProvider.java) |
| [com.intellij.tasks.dialogPanelProvider](https://jb.gg/ipe?extensions=com.intellij.tasks.dialogPanelProvider) ![Non-Dynamic][non-dynamic] | [`TaskDialogPanelProvider`](%gh-ic%/plugins/tasks/tasks-api/src/com/intellij/tasks/ui/TaskDialogPanelProvider.java) |

### com.intellij.turboComplete

[`com.intellij.turboComplete`](%gh-ic%/plugins/turboComplete/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.turboComplete.analysis.pipelineListener](https://jb.gg/ipe?extensions=com.intellij.turboComplete.analysis.pipelineListener) | [`PipelineListener`](%gh-ic%/plugins/turboComplete/src/com/intellij/turboComplete/analysis/PipelineListener.kt) |
| [com.intellij.turboComplete.features.kind.provider](https://jb.gg/ipe?extensions=com.intellij.turboComplete.features.kind.provider) | [`KindFeatureProvider`](%gh-ic%/plugins/turboComplete/src/com/intellij/turboComplete/features/kind/KindFeatureProvider.kt) |
| [com.intellij.turboComplete.kindCollector](https://jb.gg/ipe?extensions=com.intellij.turboComplete.kindCollector) ![Internal][internal] | [`KindCollector`](%gh-ic%/platform/ml-impl/src/com/intellij/platform/ml/impl/turboComplete/KindCollector.kt) |
| [com.intellij.turboComplete.suggestionGeneratorExecutorProvider](https://jb.gg/ipe?extensions=com.intellij.turboComplete.suggestionGeneratorExecutorProvider) ![Internal][internal] | [`SuggestionGeneratorExecutorProvider`](%gh-ic%/platform/ml-impl/src/com/intellij/platform/ml/impl/turboComplete/SuggestionGeneratorExecutorProvider.kt) |

### com.intellij.uiDesigner

[`com.intellij.uiDesigner`](%gh-ic%/plugins/ui-designer/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.paletteItemProvider](https://jb.gg/ipe?extensions=com.intellij.paletteItemProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`PaletteItemProvider`](%gh-ic%/plugins/ui-designer/src/com/intellij/ide/palette/PaletteItemProvider.java) |
| [com.intellij.uiDesigner.formInspectionTool](https://jb.gg/ipe?extensions=com.intellij.uiDesigner.formInspectionTool) ![Non-Dynamic][non-dynamic] | [`FormInspectionTool`](%gh-ic%/plugins/ui-designer/src/com/intellij/uiDesigner/inspections/FormInspectionTool.java) |

### com.jetbrains.filePrediction

[`com.jetbrains.filePrediction`](%gh-ic%/plugins/filePrediction/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.filePrediction.candidateProvider](https://jb.gg/ipe?extensions=com.intellij.filePrediction.candidateProvider) ![Internal][internal] | [`FilePredictionCandidateProvider`](%gh-ic%/plugins/filePrediction/src/com/intellij/filePrediction/candidates/FilePredictionCandidateProvider.kt) |
| [com.intellij.filePrediction.featureProvider](https://jb.gg/ipe?extensions=com.intellij.filePrediction.featureProvider) ![Internal][internal] | [`FilePredictionFeatureProvider`](%gh-ic%/plugins/filePrediction/src/com/intellij/filePrediction/features/FilePredictionFeatureProvider.kt) |
| [com.intellij.filePrediction.ml.model](https://jb.gg/ipe?extensions=com.intellij.filePrediction.ml.model) ![Internal][internal] | [`FilePredictionModelProvider`](%gh-ic%/plugins/filePrediction/src/com/intellij/filePrediction/predictor/model/FilePredictionModelProvider.kt) |
| [com.intellij.filePrediction.referencesProvider](https://jb.gg/ipe?extensions=com.intellij.filePrediction.referencesProvider) ![Internal][internal] | [`FileExternalReferencesProvider`](%gh-ic%/plugins/filePrediction/src/com/intellij/filePrediction/references/FilePredictionReferencesHelper.kt) |

### com.jetbrains.performancePlugin

[`com.jetbrains.performancePlugin`](%gh-ic%/plugins/performanceTesting/core/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.jetbrains.performancePlugin.commandProvider](https://jb.gg/ipe?extensions=com.jetbrains.performancePlugin.commandProvider) | [`CommandProvider`](%gh-ic%/plugins/performanceTesting/core/src/com/jetbrains/performancePlugin/CommandProvider.java) |
| [com.jetbrains.performancePlugin.playbackRunnerProvider](https://jb.gg/ipe?extensions=com.jetbrains.performancePlugin.playbackRunnerProvider) | [`PerformancePlaybackRunner`](%gh-ic%/plugins/performanceTesting/core/src/com/jetbrains/performancePlugin/PerformancePlaybackRunner.kt) |
| [com.jetbrains.performancePlugin.profiler](https://jb.gg/ipe?extensions=com.jetbrains.performancePlugin.profiler) | [`Profiler`](%gh-ic%/plugins/performanceTesting/core/src/com/jetbrains/performancePlugin/profilers/Profiler.kt) |
| [com.jetbrains.performancePlugin.runCallbackHandler](https://jb.gg/ipe?extensions=com.jetbrains.performancePlugin.runCallbackHandler) ![Non-Dynamic][non-dynamic] | [`RunCallbackHandler`](%gh-ic%/plugins/performanceTesting/core/src/com/jetbrains/performancePlugin/RunCallbackHandler.java) |
| [com.jetbrains.performancePlugin.snapshotOpener](https://jb.gg/ipe?extensions=com.jetbrains.performancePlugin.snapshotOpener) | [`SnapshotOpener`](%gh-ic%/plugins/performanceTesting/core/src/com/jetbrains/performancePlugin/profilers/SnapshotOpener.java) |

### Coverage

[`Coverage`](%gh-ic%/plugins/coverage/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.javaCoverageEngineExtension](https://jb.gg/ipe?extensions=com.intellij.javaCoverageEngineExtension) | [`JavaCoverageEngineExtension`](%gh-ic%/plugins/coverage/src/com/intellij/coverage/JavaCoverageEngineExtension.java) |

### DesignerCorePlugin.xml

[`DesignerCorePlugin.xml`](%gh-ic%/plugins/ui-designer-core/resources/META-INF/DesignerCorePlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Designer.customizations](https://jb.gg/ipe?extensions=Designer.customizations) ![Non-Dynamic][non-dynamic] | [`DesignerCustomizations`](%gh-ic%/plugins/ui-designer-core/src/com/intellij/designer/DesignerCustomizations.java) |

### intellij.dev.codeInsight.xml

[`intellij.dev.codeInsight.xml`](%gh-ic%/plugins/dev/intellij.dev.codeInsight/resources/intellij.dev.codeInsight.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.dev.lang.goodCodeRedVisitor](https://jb.gg/ipe?extensions=com.intellij.dev.lang.goodCodeRedVisitor) | [`GoodCodeRedVisitor`](%gh-ic%/plugins/dev/intellij.dev.codeInsight/src/internal/GoodCodeRedVisitor.java) |

### intellij.dev.psiViewer.xml

[`intellij.dev.psiViewer.xml`](%gh-ic%/plugins/dev/intellij.dev.psiViewer/resources/intellij.dev.psiViewer.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.dev.psiViewer.apiMethodsProvider](https://jb.gg/ipe?extensions=com.intellij.dev.psiViewer.apiMethodsProvider) | [`Provider`](%gh-ic%/plugins/dev/intellij.dev.psiViewer/src/properties/tree/nodes/apiMethods/PsiViewerApiMethod.kt) |
| [com.intellij.dev.psiViewer.extension](https://jb.gg/ipe?extensions=com.intellij.dev.psiViewer.extension) | [`PsiViewerExtension`](%gh-ic%/plugins/dev/intellij.dev.psiViewer/src/PsiViewerExtension.java) |
| [com.intellij.dev.psiViewer.propertyNodeAppender](https://jb.gg/ipe?extensions=com.intellij.dev.psiViewer.propertyNodeAppender) | [`PsiViewerPropertyNodeAppender`](%gh-ic%/plugins/dev/intellij.dev.psiViewer/src/properties/tree/PsiViewerPropertyNodeHolder.kt) |
| [com.intellij.dev.psiViewer.propertyNodeFactory](https://jb.gg/ipe?extensions=com.intellij.dev.psiViewer.propertyNodeFactory) | [`Factory`](%gh-ic%/plugins/dev/intellij.dev.psiViewer/src/properties/tree/PsiViewerPropertyNode.kt) |
| [com.intellij.dev.psiViewer.psiViewerActionEnabler](https://jb.gg/ipe?extensions=com.intellij.dev.psiViewer.psiViewerActionEnabler) | [`PsiViewerActionEnabler`](%gh-ic%/plugins/dev/intellij.dev.psiViewer/src/PsiViewerActionEnabler.kt) |

### intellij.devkit.core.xml

[`intellij.devkit.core.xml`](%gh-ic%/plugins/devkit/devkit-core/resources/intellij.devkit.core.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [DevKit.lang.addServiceAnnotationProvider](https://jb.gg/ipe?extensions=DevKit.lang.addServiceAnnotationProvider) ![Internal][internal] | [`AddServiceAnnotationProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/quickfix/ConvertToLightServiceFix.kt) |
| [DevKit.lang.appServiceAsStaticFinalFieldOrPropertyFixProvider](https://jb.gg/ipe?extensions=DevKit.lang.appServiceAsStaticFinalFieldOrPropertyFixProvider) ![Internal][internal] | [`AppServiceAsStaticFinalFieldOrPropertyFixProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/quickfix/AppServiceAsStaticFinalFieldOrPropertyFixProvider.kt) |
| [DevKit.lang.appServiceAsStaticFinalFieldOrPropertyVisitorProvider](https://jb.gg/ipe?extensions=DevKit.lang.appServiceAsStaticFinalFieldOrPropertyVisitorProvider) ![Internal][internal] | [`AppServiceAsStaticFinalFieldOrPropertyVisitorProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/ApplicationServiceAsStaticFinalFieldOrPropertyInspection.kt) |
| [DevKit.lang.cancellationCheckInLoopsFixProvider](https://jb.gg/ipe?extensions=DevKit.lang.cancellationCheckInLoopsFixProvider) ![Internal][internal] | [`CancellationCheckInLoopsFixProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/quickfix/CancellationCheckInLoopsFixProvider.kt) |
| [DevKit.lang.cancellationCheckProvider](https://jb.gg/ipe?extensions=DevKit.lang.cancellationCheckProvider) ![Internal][internal] | [`CancellationCheckProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/CancellationCheckProvider.kt) |
| [DevKit.lang.cancellationExceptionHandlingChecker](https://jb.gg/ipe?extensions=DevKit.lang.cancellationExceptionHandlingChecker) ![Internal][internal] | [`CancellationExceptionHandlingChecker`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/IncorrectCancellationExceptionHandlingInspection.kt) |
| [DevKit.lang.extensionClassShouldBeFinalErrorMessageProvider](https://jb.gg/ipe?extensions=DevKit.lang.extensionClassShouldBeFinalErrorMessageProvider) ![Internal][internal] | [`ErrorMessageProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/ExtensionClassShouldBeFinalErrorMessageProvider.kt) |
| [DevKit.lang.extensionClassShouldNotBePublicProvider](https://jb.gg/ipe?extensions=DevKit.lang.extensionClassShouldNotBePublicProvider) ![Internal][internal] | [`ExtensionClassShouldNotBePublicProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/ExtensionClassShouldNotBePublicProvider.kt) |
| [DevKit.lang.lightServiceMustBeFinalErrorMessageProvider](https://jb.gg/ipe?extensions=DevKit.lang.lightServiceMustBeFinalErrorMessageProvider) ![Internal][internal] | [`ErrorMessageProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/ExtensionClassShouldBeFinalErrorMessageProvider.kt) |
| [DevKit.lang.methodNameProvider](https://jb.gg/ipe?extensions=DevKit.lang.methodNameProvider) ![Internal][internal] | [`MethodNameProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/MethodNameProvider.kt) |
| [DevKit.lang.serviceLevelExtractor](https://jb.gg/ipe?extensions=DevKit.lang.serviceLevelExtractor) ![Internal][internal] | [`ServiceLevelExtractor`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/ServiceLevelExtractor.kt) |
| [DevKit.lang.staticInitializationInExtensionsVisitorProvider](https://jb.gg/ipe?extensions=DevKit.lang.staticInitializationInExtensionsVisitorProvider) ![Internal][internal] | [`StaticInitializationInExtensionsVisitorProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/StaticInitializationInExtensionsInspection.kt) |
| [DevKit.lang.uElementAsPsiCheckProvider](https://jb.gg/ipe?extensions=DevKit.lang.uElementAsPsiCheckProvider) ![Internal][internal] | [`UElementAsPsiCheckProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/UElementAsPsiCheckProvider.kt) |
| [DevKit.lang.visitorProviderForRBCInspection](https://jb.gg/ipe?extensions=DevKit.lang.visitorProviderForRBCInspection) ![Internal][internal] | [`VisitorProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/CallingMethodShouldBeRequiresBlockingContextInspection.kt) |

### intellij.ide.startup.importSettings.xml

[`intellij.ide.startup.importSettings.xml`](%gh-ic%/plugins/ide-startup/importSettings/resources/intellij.ide.startup.importSettings.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.startupWizardPages](https://jb.gg/ipe?extensions=com.intellij.startupWizardPages) | [`StartupWizardService`](%gh-ic%/plugins/ide-startup/importSettings/src/com/intellij/ide/startup/importSettings/data/StartupWizardService.kt) |
| [com.intellij.transferSettings.externalProjectImportChecker](https://jb.gg/ipe?extensions=com.intellij.transferSettings.externalProjectImportChecker) | [`ExternalProjectImportChecker`](%gh-ic%/plugins/ide-startup/importSettings/src/com/intellij/ide/startup/importSettings/transfer/ExternalProjectImportChecker.kt) |
| [com.intellij.transferSettings.thirdPartyProductSettingItem](https://jb.gg/ipe?extensions=com.intellij.transferSettings.thirdPartyProductSettingItem) | [`ThirdPartyProductSettingItemProvider`](%gh-ic%/plugins/ide-startup/importSettings/src/com/intellij/ide/startup/importSettings/transfer/ThirdPartyProductSettingItemProvider.kt) |
| [com.intellij.transferSettings.thirdPartyProductSettingsTransfer](https://jb.gg/ipe?extensions=com.intellij.transferSettings.thirdPartyProductSettingsTransfer) | [`ThirdPartyProductSettingsTransfer`](%gh-ic%/plugins/ide-startup/importSettings/src/com/intellij/ide/startup/importSettings/transfer/ThirdPartyProductSettingsTransfer.kt) |
| [com.intellij.transferSettings.vscode.pluginMapping](https://jb.gg/ipe?extensions=com.intellij.transferSettings.vscode.pluginMapping) | [`VSCodePluginMapping`](%gh-ic%/plugins/ide-startup/importSettings/src/com/intellij/ide/startup/importSettings/transfer/backend/providers/vscode/mappings/PluginMappings.kt) |

### intellij.performanceTesting.remoteDriver.xml

[`intellij.performanceTesting.remoteDriver.xml`](%gh-ic%/plugins/performanceTesting/remote-driver/resources/intellij.performanceTesting.remoteDriver.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.jetbrains.performancePlugin.remotedriver.textExtractorExtension](https://jb.gg/ipe?extensions=com.jetbrains.performancePlugin.remotedriver.textExtractorExtension) | [`TextExtractorExtension`](%gh-ic%/plugins/performanceTesting/remote-driver/src/com/jetbrains/performancePlugin/remotedriver/dataextractor/TextExtractorExtension.kt) |
| [com.jetbrains.performancePlugin.remotedriver.xpathDataModelExtension](https://jb.gg/ipe?extensions=com.jetbrains.performancePlugin.remotedriver.xpathDataModelExtension) | [`XpathDataModelExtension`](%gh-ic%/plugins/performanceTesting/remote-driver/src/com/jetbrains/performancePlugin/remotedriver/xpath/XpathDataModelExtension.kt) |

### intellij.platform.coverage.xml

[`intellij.platform.coverage.xml`](%gh-ic%/plugins/coverage-common/resources/intellij.platform.coverage.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.coverageEngine](https://jb.gg/ipe?extensions=com.intellij.coverageEngine) | [`CoverageEngine`](%gh-ic%/plugins/coverage-common/src/com/intellij/coverage/CoverageEngine.java) |
| [com.intellij.coverageModifiedFilesFilterFactory](https://jb.gg/ipe?extensions=com.intellij.coverageModifiedFilesFilterFactory) ![Internal][internal] | [`ModifiedFilesFilterFactory`](%gh-ic%/plugins/coverage-common/src/com/intellij/coverage/filters/ModifiedFilesFilterFactory.kt) |
| [com.intellij.coverageOptions](https://jb.gg/ipe?extensions=com.intellij.coverageOptions) ![Project-Level][project-level] | [`CoverageOptions`](%gh-ic%/plugins/coverage-common/src/com/intellij/coverage/CoverageOptions.java) |
| [com.intellij.coverageRunner](https://jb.gg/ipe?extensions=com.intellij.coverageRunner) | [`CoverageRunner`](%gh-ic%/plugins/coverage-common/src/com/intellij/coverage/CoverageRunner.java) |

### intellij.searchEverywhereMl.ranking.core.xml

[`intellij.searchEverywhereMl.ranking.core.xml`](%gh-ic%/plugins/search-everywhere-ml/ranking/core/resources/intellij.searchEverywhereMl.ranking.core.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.searcheverywhere.ml.rankingModelLoader](https://jb.gg/ipe?extensions=com.intellij.searcheverywhere.ml.rankingModelLoader) | [`SearchEverywhereMLRankingModelLoader`](%gh-ic%/plugins/search-everywhere-ml/ranking/core/src/com/intellij/searchEverywhereMl/ranking/core/model/SearchEverywhereMLRankingModelLoader.kt) |
| [com.intellij.searcheverywhere.ml.searchEverywhereElementFeaturesProvider](https://jb.gg/ipe?extensions=com.intellij.searcheverywhere.ml.searchEverywhereElementFeaturesProvider) ![Internal][internal] | [`SearchEverywhereElementFeaturesProvider`](%gh-ic%/plugins/search-everywhere-ml/ranking/core/src/com/intellij/searchEverywhereMl/ranking/core/features/SearchEverywhereElementFeaturesProvider.kt) |

### intellij.searchEverywhereMl.ranking.ext.xml

[`intellij.searchEverywhereMl.ranking.ext.xml`](%gh-ic%/plugins/search-everywhere-ml/ranking/ext/resources/intellij.searchEverywhereMl.ranking.ext.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.searchEverywhereMl.searchEverywhereElementKeyProvider](https://jb.gg/ipe?extensions=com.intellij.searchEverywhereMl.searchEverywhereElementKeyProvider) ![Internal][internal] | [`SearchEverywhereElementKeyProvider`](%gh-ic%/plugins/search-everywhere-ml/ranking/ext/src/com/intellij/searchEverywhereMl/ranking/ext/SearchEverywhereElementKeyProvider.kt) |

### intellij.toml.json.xml

[`intellij.toml.json.xml`](%gh-ic%/plugins/toml/json/src/main/resources/intellij.toml.json.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.toml.ide.json.tomlJsonSchemaCompletionFileFilter](https://jb.gg/ipe?extensions=org.toml.ide.json.tomlJsonSchemaCompletionFileFilter) | [`TomlJsonSchemaCompletionFileFilter`](%gh-ic%/plugins/toml/json/src/main/kotlin/org/toml/ide/json/TomlJsonSchemaCompletionFileFilter.kt) |

### intellij.yaml.backend.xml

[`intellij.yaml.backend.xml`](%gh-ic%/plugins/yaml/backend/resources/intellij.yaml.backend.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.yaml.customStructureViewFactory](https://jb.gg/ipe?extensions=com.intellij.yaml.customStructureViewFactory) | [`YAMLCustomStructureViewFactory`](%gh-ic%/plugins/yaml/backend/src/structureView/YAMLCustomStructureViewFactory.java) |
| [com.intellij.yaml.tagRecogniser](https://jb.gg/ipe?extensions=com.intellij.yaml.tagRecogniser) ![Experimental][experimental] | [`YamlTagRecogniser`](%gh-ic%/plugins/yaml/src/psi/YamlTagRecogniser.kt) |

### JUnit

[`JUnit`](%gh-ic%/plugins/junit/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.junitListener](https://jb.gg/ipe?extensions=com.intellij.junitListener) | [`IDEAJUnitListener`](%gh-ic%/java/java-runtime/src/com/intellij/rt/execution/junit/IDEAJUnitListener.java) |
| [com.intellij.testDiscoveryProducer](https://jb.gg/ipe?extensions=com.intellij.testDiscoveryProducer) | [`TestDiscoveryProducer`](%gh-ic%/java/execution/impl/src/com/intellij/execution/testDiscovery/TestDiscoveryProducer.java) |

### org.intellij.groovy

[`org.intellij.groovy`](%gh-ic%/plugins/groovy/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.newProjectWizard.groovy.buildSystem](https://jb.gg/ipe?extensions=com.intellij.newProjectWizard.groovy.buildSystem) | [`BuildSystemGroovyNewProjectWizard`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/config/wizard/BuildSystemGroovyNewProjectWizard.kt) |
| [org.intellij.groovy.applicabilityProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.applicabilityProvider) ![Experimental][experimental] | [`GroovyApplicabilityProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyApplicabilityProvider.java) |
| [org.intellij.groovy.astTransformationSupport](https://jb.gg/ipe?extensions=org.intellij.groovy.astTransformationSupport) ![DumbAware][dumb-aware] | [`AstTransformationSupport`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/transformations/AstTransformationSupport.java) |
| [org.intellij.groovy.callTypeCalculator](https://jb.gg/ipe?extensions=org.intellij.groovy.callTypeCalculator) ![Experimental][experimental] | [`GrCallTypeCalculator`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/typing/GrCallTypeCalculator.kt) |
| [org.intellij.groovy.classDescriptor](https://jb.gg/ipe?extensions=org.intellij.groovy.classDescriptor) | `n/a` |
| [org.intellij.groovy.closureCompleter](https://jb.gg/ipe?extensions=org.intellij.groovy.closureCompleter) | [`ClosureCompleter`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/lang/completion/ClosureCompleter.java) |
| [org.intellij.groovy.closureMissingMethodContributor](https://jb.gg/ipe?extensions=org.intellij.groovy.closureMissingMethodContributor) | [`ClosureMissingMethodContributor`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/ClosureMissingMethodContributor.java) |
| [org.intellij.groovy.completionCustomizer](https://jb.gg/ipe?extensions=org.intellij.groovy.completionCustomizer) ![Experimental][experimental] | [`GroovyCompletionCustomizer`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/lang/completion/api/GroovyCompletionCustomizer.kt) |
| [org.intellij.groovy.configSlurperSupport](https://jb.gg/ipe?extensions=org.intellij.groovy.configSlurperSupport) | [`ConfigSlurperSupport`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/configSlurper/ConfigSlurperSupport.java) |
| [org.intellij.groovy.convertToJava.customMethodInvocator](https://jb.gg/ipe?extensions=org.intellij.groovy.convertToJava.customMethodInvocator) | [`CustomMethodInvocator`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/refactoring/convertToJava/invocators/CustomMethodInvocator.java) |
| [org.intellij.groovy.customAnnotationChecker](https://jb.gg/ipe?extensions=org.intellij.groovy.customAnnotationChecker) | [`CustomAnnotationChecker`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/annotator/checkers/CustomAnnotationChecker.java) |
| [org.intellij.groovy.delegatesToProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.delegatesToProvider) | [`GrDelegatesToProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/delegatesTo/GrDelegatesToProvider.java) |
| [org.intellij.groovy.elementFilter](https://jb.gg/ipe?extensions=org.intellij.groovy.elementFilter) ![Experimental][experimental] | [`GroovyElementFilter`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/GroovyElementFilter.kt) |
| [org.intellij.groovy.expectedPackageNameProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.expectedPackageNameProvider) | [`ExpectedPackageNameProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/ExpectedPackageNameProvider.kt) |
| [org.intellij.groovy.expectedTypesContributor](https://jb.gg/ipe?extensions=org.intellij.groovy.expectedTypesContributor) | [`GroovyExpectedTypesContributor`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/expectedTypes/GroovyExpectedTypesContributor.java) |
| [org.intellij.groovy.gdslScriptProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.gdslScriptProvider) | [`GdslScriptProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/dsl/GdslScriptProvider.java) |
| [org.intellij.groovy.gdslTopLevelProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.gdslTopLevelProvider) ![Non-Dynamic][non-dynamic] | [`GdslMembersProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/dsl/dsltop/GdslMembersProvider.java) |
| [org.intellij.groovy.groovyFrameworkConfigNotification](https://jb.gg/ipe?extensions=org.intellij.groovy.groovyFrameworkConfigNotification) | [`GroovyFrameworkConfigNotification`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/annotator/GroovyFrameworkConfigNotification.java) |
| [org.intellij.groovy.groovySourceFolderDetector](https://jb.gg/ipe?extensions=org.intellij.groovy.groovySourceFolderDetector) | [`GroovySourceFolderDetector`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/actions/GroovySourceFolderDetector.java) |
| [org.intellij.groovy.importContributor](https://jb.gg/ipe?extensions=org.intellij.groovy.importContributor) | [`GrImportContributor`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/imports/GrImportContributor.java) |
| [org.intellij.groovy.inlayHintFilter](https://jb.gg/ipe?extensions=org.intellij.groovy.inlayHintFilter) | [`GroovyInlayHintFilter`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/editor/GroovyInlayHintFilter.java) |
| [org.intellij.groovy.inlineASTTransformationSupport](https://jb.gg/ipe?extensions=org.intellij.groovy.inlineASTTransformationSupport) ![Experimental][experimental] | [`GroovyInlineASTTransformationSupport`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/transformations/inline/GroovyInlineASTTransformationSupport.kt) |
| [org.intellij.groovy.inspectionDisabler](https://jb.gg/ipe?extensions=org.intellij.groovy.inspectionDisabler) | [`FileTypeInspectionDisabler`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/codeInspection/FileTypeInspectionDisabler.kt) |
| [org.intellij.groovy.mapContentProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.mapContentProvider) | [`GroovyMapContentProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyMapContentProvider.java) |
| [org.intellij.groovy.membersContributor](https://jb.gg/ipe?extensions=org.intellij.groovy.membersContributor) | [`NonCodeMembersContributor`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/NonCodeMembersContributor.java) |
| [org.intellij.groovy.methodComparator](https://jb.gg/ipe?extensions=org.intellij.groovy.methodComparator) | [`GrMethodComparator`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/GrMethodComparator.java) |
| [org.intellij.groovy.methodDescriptor](https://jb.gg/ipe?extensions=org.intellij.groovy.methodDescriptor) | `n/a` |
| [org.intellij.groovy.methodMayBeStaticInspectionFilter](https://jb.gg/ipe?extensions=org.intellij.groovy.methodMayBeStaticInspectionFilter) | [`GrMethodMayBeStaticInspectionFilter`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/codeInspection/declaration/GrMethodMayBeStaticInspectionFilter.java) |
| [org.intellij.groovy.namedArgumentProvider](https://jb.gg/ipe?extensions=org.intellij.groovy.namedArgumentProvider) | [`GroovyNamedArgumentProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyNamedArgumentProvider.java) |
| [org.intellij.groovy.overloadResolver](https://jb.gg/ipe?extensions=org.intellij.groovy.overloadResolver) | [`GroovyOverloadResolver`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/api/GroovyOverloadResolver.java) |
| [org.intellij.groovy.positionManagerDelegate](https://jb.gg/ipe?extensions=org.intellij.groovy.positionManagerDelegate) | [`ScriptPositionManagerHelper`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/extensions/debugger/ScriptPositionManagerHelper.java) |
| [org.intellij.groovy.psiEnhancerCategory](https://jb.gg/ipe?extensions=org.intellij.groovy.psiEnhancerCategory) ![Non-Dynamic][non-dynamic] | [`PsiEnhancerCategory`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/dsl/psi/PsiEnhancerCategory.java) |
| [org.intellij.groovy.referenceTypeEnhancer](https://jb.gg/ipe?extensions=org.intellij.groovy.referenceTypeEnhancer) | [`GrReferenceTypeEnhancer`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/typeEnhancers/GrReferenceTypeEnhancer.java) |
| [org.intellij.groovy.renameHelper](https://jb.gg/ipe?extensions=org.intellij.groovy.renameHelper) | [`GrRenameHelper`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/refactoring/rename/GrRenameHelper.java) |
| [org.intellij.groovy.scriptTypeDetector](https://jb.gg/ipe?extensions=org.intellij.groovy.scriptTypeDetector) | [`GroovyScriptTypeDetector`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyScriptTypeDetector.java) |
| [org.intellij.groovy.signatureHintProcessor](https://jb.gg/ipe?extensions=org.intellij.groovy.signatureHintProcessor) | [`SignatureHintProcessor`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/typeEnhancers/SignatureHintProcessor.java) |
| [org.intellij.groovy.typeAugmenter](https://jb.gg/ipe?extensions=org.intellij.groovy.typeAugmenter) | [`TypeAugmenter`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/dataFlow/types/TypeAugmenter.kt) |
| [org.intellij.groovy.typeCalculator](https://jb.gg/ipe?extensions=org.intellij.groovy.typeCalculator) | [`GrTypeCalculator`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/typing/GrTypeCalculator.java) |
| [org.intellij.groovy.typeConverter](https://jb.gg/ipe?extensions=org.intellij.groovy.typeConverter) | [`GrTypeConverter`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/typeEnhancers/GrTypeConverter.java) |
| [org.intellij.groovy.unresolvedHighlightFileFilter](https://jb.gg/ipe?extensions=org.intellij.groovy.unresolvedHighlightFileFilter) | [`GroovyUnresolvedHighlightFileFilter`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyUnresolvedHighlightFileFilter.java) |
| [org.intellij.groovy.unresolvedHighlightFilter](https://jb.gg/ipe?extensions=org.intellij.groovy.unresolvedHighlightFilter) | [`GroovyUnresolvedHighlightFilter`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyUnresolvedHighlightFilter.java) |
| [org.intellij.groovy.variableEnhancer](https://jb.gg/ipe?extensions=org.intellij.groovy.variableEnhancer) | [`GrVariableEnhancer`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/typeEnhancers/GrVariableEnhancer.java) |

### org.intellij.intelliLang

[`org.intellij.intelliLang`](%gh-ic%/plugins/IntelliLang/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.intellij.intelliLang.injectionConfig](https://jb.gg/ipe?extensions=org.intellij.intelliLang.injectionConfig) | `n/a` |
| [org.intellij.intelliLang.languageSupport](https://jb.gg/ipe?extensions=org.intellij.intelliLang.languageSupport) | [`LanguageInjectionSupport`](%gh-ic%/plugins/IntelliLang/src/org/intellij/plugins/intelliLang/inject/LanguageInjectionSupport.java) |

### org.jetbrains.idea.eclipse

[`org.jetbrains.idea.eclipse`](%gh-ic%/plugins/eclipse/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.idea.eclipse.natureImporter](https://jb.gg/ipe?extensions=org.jetbrains.idea.eclipse.natureImporter) | [`EclipseNatureImporter`](%gh-ic%/plugins/eclipse/src/org/jetbrains/idea/eclipse/importWizard/EclipseNatureImporter.java) |

### org.jetbrains.idea.reposearch

[`org.jetbrains.idea.reposearch`](%gh-ic%/plugins/repository-search/src/main/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.idea.reposearch.provider](https://jb.gg/ipe?extensions=org.jetbrains.idea.reposearch.provider) ![Experimental][experimental] | [`DependencySearchProvidersFactory`](%gh-ic%/plugins/repository-search/src/main/java/org/jetbrains/idea/reposearch/DependencySearchProvidersFactory.java) |

### org.jetbrains.platform.debugger.streams

[`org.jetbrains.platform.debugger.streams`](%gh-ic%/plugins/stream-debugger-core/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.platform.debugger.streams.librarySupport](https://jb.gg/ipe?extensions=org.jetbrains.platform.debugger.streams.librarySupport) | [`LibrarySupportProvider`](%gh-ic%/plugins/stream-debugger-core/src/com/intellij/debugger/streams/core/lib/LibrarySupportProvider.java) |

### org.jetbrains.plugins.textmate

[`org.jetbrains.plugins.textmate`](%gh-ic%/plugins/textmate/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.textmate.bundleProvider](https://jb.gg/ipe?extensions=com.intellij.textmate.bundleProvider) ![Non-Dynamic][non-dynamic] | [`TextMateBundleProvider`](%gh-ic%/plugins/textmate/src/org/jetbrains/plugins/textmate/api/TextMateBundleProvider.kt) |

### ru.adelf.idea.dotenv

[`ru.adelf.idea.dotenv`](%gh-ic%/plugins/env-files-support/src/main/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [ru.adelf.idea.dotenv.environmentVariablesProvider](https://jb.gg/ipe?extensions=ru.adelf.idea.dotenv.environmentVariablesProvider) | [`EnvironmentVariablesProvider`](%gh-ic%/plugins/env-files-support/src/main/java/ru/adelf/idea/dotenv/api/EnvironmentVariablesProvider.java) |
| [ru.adelf.idea.dotenv.environmentVariablesUsagesProvider](https://jb.gg/ipe?extensions=ru.adelf.idea.dotenv.environmentVariablesUsagesProvider) | [`EnvironmentVariablesUsagesProvider`](%gh-ic%/plugins/env-files-support/src/main/java/ru/adelf/idea/dotenv/api/EnvironmentVariablesUsagesProvider.java) |

### sh.xml

[`sh.xml`](%gh-ic%/plugins/sh/core/resources/META-INF/sh.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.runMarkerContributionAdditionalCondition](https://jb.gg/ipe?extensions=com.intellij.runMarkerContributionAdditionalCondition) | [`ShRunnerAdditionalCondition`](%gh-ic%/plugins/sh/core/src/com/intellij/sh/run/ShRunnerAdditionalCondition.java) |
| [com.intellij.shellOccurrencesHighlightingSuppressor](https://jb.gg/ipe?extensions=com.intellij.shellOccurrencesHighlightingSuppressor) | [`ShOccurrencesHighlightingSuppressor`](%gh-ic%/plugins/sh/core/src/com/intellij/sh/highlighting/ShOccurrencesHighlightingSuppressor.kt) |

### TestNG-J

[`TestNG-J`](%gh-ic%/plugins/testng/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.theoryinpractice.testng.listener](https://jb.gg/ipe?extensions=com.theoryinpractice.testng.listener) | [`IDEATestNGListener`](%gh-ic%/plugins/testng_rt/src/com/intellij/rt/testng/IDEATestNGListener.java) |

### XPathView

[`XPathView`](%gh-ic%/plugins/xpath/xpath-lang/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [XPathView.xpath.contextProviderExtension](https://jb.gg/ipe?extensions=XPathView.xpath.contextProviderExtension) | [`ContextProviderExtension`](%gh-ic%/plugins/xpath/xpath-lang/src/org/intellij/lang/xpath/context/ContextProviderExtension.java) |
| [XPathView.xpath.functionProvider](https://jb.gg/ipe?extensions=XPathView.xpath.functionProvider) | [`XPathFunctionProvider`](%gh-ic%/plugins/xpath/xpath-lang/src/org/intellij/lang/xpath/context/functions/XPathFunctionProvider.java) |
| [XPathView.xsltRunnerExtension](https://jb.gg/ipe?extensions=XPathView.xsltRunnerExtension) | [`XsltRunnerExtension`](%gh-ic%/plugins/xpath/xpath-lang/src/org/intellij/lang/xpath/xslt/run/XsltRunnerExtension.java) |



## Ant Plugin


### Ant Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [AntExecutionListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.lang.ant.config.execution.AntExecutionListener)  | [`AntExecutionListener`](%gh-ic%/plugins/ant/src/com/intellij/lang/ant/config/execution/AntExecutionListener.java) |


### AntSupport

[`AntSupport`](%gh-ic%/plugins/ant/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [AntSupport.AntMessageCustomizer](https://jb.gg/ipe?extensions=AntSupport.AntMessageCustomizer) | [`AntMessageCustomizer`](%gh-ic%/plugins/ant/src/com/intellij/lang/ant/config/execution/AntMessageCustomizer.java) |



## EditorConfig Plugin


### EditorConfig Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [EditorConfigSettings#EDITOR_CONFIG_ENABLED_TOPIC](https://jb.gg/ipe/listeners?topics=org.editorconfig.settings.EditorConfigListener)  | [`EditorConfigListener`](%gh-ic%/plugins/editorconfig/src/org/editorconfig/settings/EditorConfigListener.java) |


### org.editorconfig.editorconfigjetbrains

[`org.editorconfig.editorconfigjetbrains`](%gh-ic%/plugins/editorconfig/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [editorconfig.exportProvider](https://jb.gg/ipe?extensions=editorconfig.exportProvider) ![Non-Dynamic][non-dynamic] | [`EditorConfigExportProvider`](%gh-ic%/plugins/editorconfig/src/org/editorconfig/settings/EditorConfigExportProvider.java) |
| [editorconfig.optionDescriptorProvider](https://jb.gg/ipe?extensions=editorconfig.optionDescriptorProvider) ![Non-Dynamic][non-dynamic] | [`EditorConfigOptionDescriptorProvider`](%gh-ic%/plugins/editorconfig/src/org/editorconfig/language/extensions/EditorConfigOptionDescriptorProvider.kt) |



## Gradle Plugin


### Gradle Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [GradleSettingsListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.gradle.settings.GradleSettingsListener)  ![Project-Level][project-level] | [`GradleSettingsListener`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/settings/GradleSettingsListener.java) |
| [GradleUiListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.gradle.ui.GradleUiListener)  | [`GradleUiListener`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/ui/GradleUiListener.java) |


### com.intellij.gradle

[`com.intellij.gradle`](%gh-ic%/plugins/gradle/plugin-resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.plugins.gradle.autoReloadSettingsCollector](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.autoReloadSettingsCollector) ![Internal][internal] | [`GradleAutoReloadSettingsCollector`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/project/GradleAutoReloadSettingsCollector.kt) |
| [org.jetbrains.plugins.gradle.executionEnvironmentProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.executionEnvironmentProvider) | [`GradleExecutionEnvironmentProvider`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/execution/build/GradleExecutionEnvironmentProvider.java) |
| [org.jetbrains.plugins.gradle.executionHelperExtension](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.executionHelperExtension) ![Internal][internal] | [`GradleExecutionHelperExtension`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/project/GradleExecutionHelperExtension.kt) |
| [org.jetbrains.plugins.gradle.gradleJvmResolver](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.gradleJvmResolver) ![Experimental][experimental] | [`GradleJvmResolver`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/resolvers/GradleJvmResolver.kt) |
| [org.jetbrains.plugins.gradle.importCustomizer](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.importCustomizer) | [`GradleImportCustomizer`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/project/GradleImportCustomizer.java) |
| [org.jetbrains.plugins.gradle.issueChecker](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.issueChecker) | [`GradleIssueChecker`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/issue/GradleIssueChecker.kt) |
| [org.jetbrains.plugins.gradle.orderEnumerationHandlerFactory](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.orderEnumerationHandlerFactory) | [`FactoryImpl`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/execution/GradleOrderEnumeratorHandler.java) |
| [org.jetbrains.plugins.gradle.previewCustomizer](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.previewCustomizer) ![Experimental][experimental] | [`GradlePreviewCustomizer`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/project/GradlePreviewCustomizer.kt) |
| [org.jetbrains.plugins.gradle.projectResolve](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.projectResolve) | [`GradleProjectResolverExtension`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/project/GradleProjectResolverExtension.java) |
| [org.jetbrains.plugins.gradle.settingsControlProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.settingsControlProvider) | [`GradleSettingsControlProvider`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/settings/GradleSettingsControlProvider.java) |
| [org.jetbrains.plugins.gradle.syncContributor](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.syncContributor) ![Experimental][experimental] | [`GradleSyncContributor`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/syncAction/GradleSyncContributor.kt) |
| [org.jetbrains.plugins.gradle.targetEnvironmentAware](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.targetEnvironmentAware) ![Internal][internal] | [`GradleTargetEnvironmentAware`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/execution/target/GradleTargetEnvironmentAware.java) |
| [org.jetbrains.plugins.gradle.taskManager](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.taskManager) | [`GradleTaskManagerExtension`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/task/GradleTaskManagerExtension.java) |

### gradle-groovy-integration.xml

[`gradle-groovy-integration.xml`](%gh-ic%/plugins/gradle/java/resources/META-INF/gradle-groovy-integration.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.plugins.gradle.pluginDescriptions](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.pluginDescriptions) | [`GradlePluginDescriptionsExtension`](%gh-ic%/plugins/gradle/java/src/codeInsight/GradlePluginDescriptionsExtension.java) |
| [org.jetbrains.plugins.gradle.resolve.contributor](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.resolve.contributor) | [`GradleMethodContextContributor`](%gh-ic%/plugins/gradle/java/src/service/resolve/GradleMethodContextContributor.java) |

### org.jetbrains.plugins.gradle

[`org.jetbrains.plugins.gradle`](%gh-ic%/plugins/gradle/java/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.plugins.gradle.buildTasksProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.buildTasksProvider) | [`GradleBuildTasksProvider`](%gh-ic%/plugins/gradle/java/src/execution/build/GradleBuildTasksProvider.java) |
| [org.jetbrains.plugins.gradle.dslInspectionProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.dslInspectionProvider) ![Internal][internal] | [`GradleDslInspectionProvider`](%gh-ic%/plugins/gradle/java/src/codeInspection/GradleDslInspectionProvider.kt) |
| [org.jetbrains.plugins.gradle.externallyHandledExtensions](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.externallyHandledExtensions) ![Internal][internal] | [`GradleVersionCatalogHandler`](%gh-ic%/plugins/gradle/java/src/service/resolve/GradleVersionCatalogHandler.kt) |
| [org.jetbrains.plugins.gradle.frameworkSupport](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.frameworkSupport) | [`GradleFrameworkSupportProvider`](%gh-ic%/plugins/gradle/java/src/frameworkSupport/GradleFrameworkSupportProvider.java) |
| [org.jetbrains.plugins.gradle.initScriptGenerator](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.initScriptGenerator) ![Experimental][experimental] | [`GradleInitScriptGenerator`](%gh-ic%/plugins/gradle/java/src/execution/build/GradleInitScriptGenerator.kt) |
| [org.jetbrains.plugins.gradle.kotlinDslFrameworkSupport](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.kotlinDslFrameworkSupport) | [`KotlinDslGradleFrameworkSupportProvider`](%gh-ic%/plugins/gradle/java/src/frameworkSupport/KotlinDslGradleFrameworkSupportProvider.java) |
| [org.jetbrains.plugins.gradle.testTasksProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.gradle.testTasksProvider) | [`GradleTestTasksProvider`](%gh-ic%/plugins/gradle/java/src/execution/test/runner/GradleTestTasksProvider.java) |



## Grazie Plugin


### Grazie Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [GrazieStateLifecycleKt#CONFIG_STATE_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.grazie.ide.msg.GrazieStateLifecycle)  | [`GrazieStateLifecycle`](%gh-ic%/plugins/grazie/src/main/kotlin/com/intellij/grazie/ide/msg/GrazieStateLifecycle.kt) |


### tanvd.grazi

[`tanvd.grazi`](%gh-ic%/plugins/grazie/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.grazie.disableChecking](https://jb.gg/ipe?extensions=com.intellij.grazie.disableChecking) | `n/a` |
| [com.intellij.grazie.grammar.strategy](https://jb.gg/ipe?extensions=com.intellij.grazie.grammar.strategy) ![Deprecated][deprecated] | [`GrammarCheckingStrategy`](%gh-ic%/plugins/grazie/src/main/kotlin/com/intellij/grazie/grammar/strategy/GrammarCheckingStrategy.kt) |
| [com.intellij.grazie.problemFilter](https://jb.gg/ipe?extensions=com.intellij.grazie.problemFilter) | [`ProblemFilter`](%gh-ic%/plugins/grazie/src/main/kotlin/com/intellij/grazie/text/ProblemFilter.java) |
| [com.intellij.grazie.textChecker](https://jb.gg/ipe?extensions=com.intellij.grazie.textChecker) | [`TextChecker`](%gh-ic%/plugins/grazie/src/main/kotlin/com/intellij/grazie/text/TextChecker.java) |
| [com.intellij.grazie.textContentModificationTrackerProvider](https://jb.gg/ipe?extensions=com.intellij.grazie.textContentModificationTrackerProvider) | [`TextContentModificationTrackerProvider`](%gh-ic%/plugins/grazie/src/main/kotlin/com/intellij/grazie/text/TextContentModificationTrackerProvider.kt) |
| [com.intellij.grazie.textExtractor](https://jb.gg/ipe?extensions=com.intellij.grazie.textExtractor) | [`TextExtractor`](%gh-ic%/plugins/grazie/src/main/kotlin/com/intellij/grazie/text/TextExtractor.java) |



## IDE Features Trainer Plugin


### IDE Features Trainer Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [FeatureSuggestersManagerListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=training.featuresSuggester.FeatureSuggestersManagerListener)  | [`FeatureSuggestersManagerListener`](%gh-ic%/plugins/ide-features-trainer/src/training/featuresSuggester/FeatureSuggestersManagerListener.kt) |


### training

[`training`](%gh-ic%/plugins/ide-features-trainer/res/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [training.ifs.suggester](https://jb.gg/ipe?extensions=training.ifs.suggester) | [`FeatureSuggester`](%gh-ic%/plugins/ide-features-trainer/src/training/featuresSuggester/suggesters/FeatureSuggester.kt) |
| [training.ifs.suggesterSupport](https://jb.gg/ipe?extensions=training.ifs.suggesterSupport) | [`SuggesterSupport`](%gh-ic%/plugins/ide-features-trainer/src/training/featuresSuggester/SuggesterSupport.kt) |
| [training.ift.language.extension](https://jb.gg/ipe?extensions=training.ift.language.extension) | [`LangSupport`](%gh-ic%/plugins/ide-features-trainer/src/training/lang/LangSupport.kt) |
| [training.ift.learning.commonCourse](https://jb.gg/ipe?extensions=training.ift.learning.commonCourse) | [`LearningCourse`](%gh-ic%/plugins/ide-features-trainer/src/training/learn/course/LearningCourse.kt) |
| [training.ift.learning.course](https://jb.gg/ipe?extensions=training.ift.learning.course) | [`LearningCourseBase`](%gh-ic%/plugins/ide-features-trainer/src/training/learn/course/LearningCourseBase.kt) |
| [training.ift.newUsersOnboardingExperimentAccessor](https://jb.gg/ipe?extensions=training.ift.newUsersOnboardingExperimentAccessor) ![Internal][internal] | [`NewUsersOnboardingExperimentAccessor`](%gh-ic%/plugins/ide-features-trainer/src/training/learn/NewUsersOnboardingExperimentAccessor.kt) |



## Java Plugin


### Java Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [ExternalAnnotationsManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.ExternalAnnotationsListener)  ![Project-Level][project-level] | [`ExternalAnnotationsListener`](%gh-ic%/java/java-psi-api/src/com/intellij/codeInsight/ExternalAnnotationsListener.java) |
| [BuildManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.compiler.server.BuildManagerListener)  | [`BuildManagerListener`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/server/BuildManagerListener.java) |
| [CustomBuilderMessageHandler#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.compiler.server.CustomBuilderMessageHandler)  ![Project-Level][project-level] | [`CustomBuilderMessageHandler`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/server/CustomBuilderMessageHandler.java) |
| [PortableCachesLoadListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.compiler.server.PortableCachesLoadListener)  ![Internal][internal] ![Project-Level][project-level] | [`PortableCachesLoadListener`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/server/PortableCachesLoadListener.java) |
| [DebuggerActionListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.debugger.engine.DebuggerActionListener)  ![Internal][internal] ![Project-Level][project-level] | [`DebuggerActionListener`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/DebuggerActionListener.kt) |
| [DebuggerManagerListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.debugger.impl.DebuggerManagerListener)  ![Project-Level][project-level] | [`DebuggerManagerListener`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/impl/DebuggerManagerListener.java) |
| [StarterModuleProcessListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.ide.starters.StarterModuleProcessListener)  ![Project-Level][project-level] | [`StarterModuleProcessListener`](%gh-ic%/java/idea-ui/src/com/intellij/ide/starters/StarterModuleProcessListener.kt) |
| [CompilerTopics#COMPILATION_STATUS](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.compiler.CompilationStatusListener)  ![Project-Level][project-level] | [`CompilationStatusListener`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/CompilationStatusListener.java) |
| [ExcludedEntriesListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.compiler.options.ExcludedEntriesListener)  ![Project-Level][project-level] | [`ExcludedEntriesListener`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/options/ExcludedEntriesListener.java) |
| [LanguageLevelProjectExtension#LANGUAGE_LEVEL_CHANGED_TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.LanguageLevelProjectExtension.LanguageLevelChangeListener)  | [`LanguageLevelChangeListener`](%gh-ic%/java/java-frontback-psi-api/src/com/intellij/openapi/roots/LanguageLevelProjectExtension.java) |
| [VirtualFileJavaLanguageLevelListener#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.impl.VirtualFileJavaLanguageLevelListener)  ![Project-Level][project-level] | [`VirtualFileJavaLanguageLevelListener`](%gh-ic%/java/java-analysis-impl/src/com/intellij/openapi/roots/impl/VirtualFileJavaLanguageLevelListener.kt) |
| [ConfigurationErrors#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.ui.configuration.ConfigurationErrors)  | [`ConfigurationErrors`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/ConfigurationErrors.java) |
| [ArtifactManager#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.packaging.artifacts.ArtifactListener)  ![Project-Level][project-level] | [`ArtifactListener`](%gh-ic%/java/compiler/openapi/src/com/intellij/packaging/artifacts/ArtifactListener.java) |


### InspectionGadgets.xml

[`InspectionGadgets.xml`](%gh-ic%/java/java-impl/resources/META-INF/InspectionGadgets.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.implicit.resource.closer](https://jb.gg/ipe?extensions=com.intellij.implicit.resource.closer) | [`ImplicitResourceCloser`](%gh-ic%/java/java-analysis-impl/src/com/intellij/codeInspection/resources/ImplicitResourceCloser.java) |
| [com.intellij.naming.convention.class](https://jb.gg/ipe?extensions=com.intellij.naming.convention.class) | [`NamingConvention`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInspection/naming/NamingConvention.java) |
| [com.intellij.naming.convention.field](https://jb.gg/ipe?extensions=com.intellij.naming.convention.field) | [`NamingConvention`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInspection/naming/NamingConvention.java) |
| [com.intellij.naming.convention.method](https://jb.gg/ipe?extensions=com.intellij.naming.convention.method) | [`NamingConvention`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInspection/naming/NamingConvention.java) |

### intellij.java.frontback.impl.xml

[`intellij.java.frontback.impl.xml`](%gh-ic%/java/java-frontback-impl/resource/intellij.java.frontback.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.java.definitions](https://jb.gg/ipe?extensions=com.intellij.java.definitions) ![Experimental][experimental] | [`AbstractBasicJavaDefinitionService`](%gh-ic%/java/java-frontback-impl/src/com/intellij/codeInsight/definition/AbstractBasicJavaDefinitionService.java) |

### intellij.java.remoteServers.impl.xml

[`intellij.java.remoteServers.impl.xml`](%gh-ic%/java/remote-servers/impl/resources/intellij.java.remoteServers.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.remoteServer.moduleBuilderContribution](https://jb.gg/ipe?extensions=com.intellij.remoteServer.moduleBuilderContribution) ![Non-Dynamic][non-dynamic] | [`CloudModuleBuilderContributionFactory`](%gh-ic%/java/remote-servers/impl/src/com/intellij/remoteServer/impl/module/CloudModuleBuilderContributionFactory.java) |

### intellij.jvm.analysis.impl.xml

[`intellij.jvm.analysis.impl.xml`](%gh-ic%/jvm/jvm-analysis-impl/resources/intellij.jvm.analysis.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.codeInsight.blockingMethodChecker](https://jb.gg/ipe?extensions=com.intellij.codeInsight.blockingMethodChecker) | [`BlockingMethodChecker`](%gh-ic%/jvm/jvm-analysis-api/src/com/intellij/codeInspection/blockingCallsDetection/BlockingMethodChecker.java) |
| [com.intellij.codeInsight.nonBlockingContextChecker](https://jb.gg/ipe?extensions=com.intellij.codeInsight.nonBlockingContextChecker) | [`NonBlockingContextChecker`](%gh-ic%/jvm/jvm-analysis-api/src/com/intellij/codeInspection/blockingCallsDetection/NonBlockingContextChecker.java) |
| [com.intellij.codeInspection.sourceToSinkProvider](https://jb.gg/ipe?extensions=com.intellij.codeInspection.sourceToSinkProvider) | [`SourceToSinkProvider`](%gh-ic%/jvm/jvm-analysis-impl/src/com/intellij/codeInspection/sourceToSink/SourceToSinkLangugeProvider.kt) |

### java-debugger.xml

[`java-debugger.xml`](%gh-ic%/java/debugger/impl/resources/META-INF/java-debugger.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.debugger.additionalContextProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.additionalContextProvider) ![Internal][internal] | [`AdditionalContextProvider`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/evaluation/EvaluationContextWrapper.kt) |
| [com.intellij.debugger.asyncStackTraceProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.asyncStackTraceProvider) | [`AsyncStackTraceProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/AsyncStackTraceProvider.java) |
| [com.intellij.debugger.codeFragmentFactory](https://jb.gg/ipe?extensions=com.intellij.debugger.codeFragmentFactory) | [`CodeFragmentFactory`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/evaluation/CodeFragmentFactory.java) |
| [com.intellij.debugger.compoundRendererProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.compoundRendererProvider) | [`CompoundRendererProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/ui/tree/render/CompoundRendererProvider.java) |
| [com.intellij.debugger.creationStackTraceProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.creationStackTraceProvider) ![Internal][internal] | [`CreationStackTraceProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/CreationStackTraceProvider.kt) |
| [com.intellij.debugger.dfaAssistProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.dfaAssistProvider) | [`DfaAssistProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/dfaassist/DfaAssistProvider.java) |
| [com.intellij.debugger.dumpItemsProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.dumpItemsProvider) ![Internal][internal] | [`ThreadDumpItemsProviderFactory`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/impl/ThreadDumpItemsProviderFactory.kt) |
| [com.intellij.debugger.extraDebugNodesProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.extraDebugNodesProvider) ![Experimental][experimental] | [`ExtraDebugNodesProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/ui/tree/ExtraDebugNodesProvider.kt) |
| [com.intellij.debugger.extraSteppingFilter](https://jb.gg/ipe?extensions=com.intellij.debugger.extraSteppingFilter) | [`ExtraSteppingFilter`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/ExtraSteppingFilter.java) |
| [com.intellij.debugger.fieldVisibilityProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.fieldVisibilityProvider) | [`FieldVisibilityProvider`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/FieldVisibilityProvider.kt) |
| [com.intellij.debugger.frameExtraVarsProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.frameExtraVarsProvider) | [`FrameExtraVariablesProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/FrameExtraVariablesProvider.java) |
| [com.intellij.debugger.javaBreakpointHandlerFactory](https://jb.gg/ipe?extensions=com.intellij.debugger.javaBreakpointHandlerFactory) | [`JavaBreakpointHandlerFactory`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/JavaBreakpointHandlerFactory.java) |
| [com.intellij.debugger.javaDebugAware](https://jb.gg/ipe?extensions=com.intellij.debugger.javaDebugAware) | [`JavaDebugAware`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/JavaDebugAware.java) |
| [com.intellij.debugger.jdiHelperClassLoader](https://jb.gg/ipe?extensions=com.intellij.debugger.jdiHelperClassLoader) | [`JdiHelperClassLoader`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/impl/JdiHelperClassLoader.kt) |
| [com.intellij.debugger.jvmSmartStepIntoHandler](https://jb.gg/ipe?extensions=com.intellij.debugger.jvmSmartStepIntoHandler) | [`JvmSmartStepIntoHandler`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/actions/JvmSmartStepIntoHandler.java) |
| [com.intellij.debugger.jvmSteppingCommandProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.jvmSteppingCommandProvider) | [`JvmSteppingCommandProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/impl/JvmSteppingCommandProvider.java) |
| [com.intellij.debugger.nodeNameAdjuster](https://jb.gg/ipe?extensions=com.intellij.debugger.nodeNameAdjuster) | [`NodeDescriptorNameAdjuster`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/ui/tree/NodeDescriptorNameAdjuster.java) |
| [com.intellij.debugger.nodeRenderer](https://jb.gg/ipe?extensions=com.intellij.debugger.nodeRenderer) | [`NodeRenderer`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/ui/tree/render/NodeRenderer.java) |
| [com.intellij.debugger.positionManagerFactory](https://jb.gg/ipe?extensions=com.intellij.debugger.positionManagerFactory) | [`PositionManagerFactory`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/PositionManagerFactory.java) |
| [com.intellij.debugger.simplePropertyGetterProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.simplePropertyGetterProvider) | [`SimplePropertyGetterProvider`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/SimplePropertyGetterProvider.java) |
| [com.intellij.debugger.sourcePositionHighlighter](https://jb.gg/ipe?extensions=com.intellij.debugger.sourcePositionHighlighter) ![DumbAware][dumb-aware] | [`SourcePositionHighlighter`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/SourcePositionHighlighter.java) |
| [com.intellij.debugger.sourcePositionProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.sourcePositionProvider) | [`SourcePositionProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/SourcePositionProvider.kt) |
| [com.intellij.debugger.steppingListener](https://jb.gg/ipe?extensions=com.intellij.debugger.steppingListener) ![Internal][internal] | [`SteppingListener`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/SteppingListener.kt) |
| [com.intellij.debugger.syntheticProvider](https://jb.gg/ipe?extensions=com.intellij.debugger.syntheticProvider) | [`SyntheticTypeComponentProvider`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/SyntheticTypeComponentProvider.java) |
| [com.intellij.debuggerEditorTextProvider](https://jb.gg/ipe?extensions=com.intellij.debuggerEditorTextProvider) | [`EditorTextProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/impl/EditorTextProvider.java) |

### JavaAnalysisPlugin.xml

[`JavaAnalysisPlugin.xml`](%gh-ic%/java/java-analysis-impl/resources/META-INF/JavaAnalysisPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.allowedApiFilter](https://jb.gg/ipe?extensions=com.intellij.allowedApiFilter) | [`AllowedApiFilterExtension`](%gh-ic%/java/java-analysis-impl/src/com/intellij/psi/impl/AllowedApiFilterExtension.java) |
| [com.intellij.canBeFinal](https://jb.gg/ipe?extensions=com.intellij.canBeFinal) | [`CanBeFinalHandler`](%gh-ic%/java/java-analysis-impl/src/com/intellij/codeInspection/canBeFinal/CanBeFinalHandler.java) |
| [com.intellij.codeInsight.changeVariableTypeQuickFixProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.changeVariableTypeQuickFixProvider) | [`ChangeVariableTypeQuickFixProvider`](%gh-ic%/java/java-analysis-api/src/com/intellij/codeInsight/quickfix/ChangeVariableTypeQuickFixProvider.java) |
| [com.intellij.codeInsight.implicitSubclassProvider](https://jb.gg/ipe?extensions=com.intellij.codeInsight.implicitSubclassProvider) | [`ImplicitSubclassProvider`](%gh-ic%/java/java-analysis-impl/src/com/intellij/codeInspection/inheritance/ImplicitSubclassProvider.kt) |
| [com.intellij.deadCode](https://jb.gg/ipe?extensions=com.intellij.deadCode) | [`EntryPoint`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/reference/EntryPoint.java) |
| [com.intellij.injectedLanguageJavaReferenceSupplier](https://jb.gg/ipe?extensions=com.intellij.injectedLanguageJavaReferenceSupplier) ![Experimental][experimental] | `n/a` |
| [com.intellij.java.error.fix](https://jb.gg/ipe?extensions=com.intellij.java.error.fix) | [`CommonIntentionAction`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/CommonIntentionAction.java) |
| [com.intellij.javaLanguageLevelPusherCustomizer](https://jb.gg/ipe?extensions=com.intellij.javaLanguageLevelPusherCustomizer) ![Internal][internal] | [`JavaLanguageLevelPusherCustomizer`](%gh-ic%/java/java-analysis-impl/src/com/intellij/openapi/roots/impl/JavaLanguageLevelPusherCustomizer.java) |
| [com.intellij.lang.jvm.actions.jvmElementActionsFactory](https://jb.gg/ipe?extensions=com.intellij.lang.jvm.actions.jvmElementActionsFactory) | [`JvmElementActionsFactory`](%gh-ic%/java/java-analysis-api/src/com/intellij/lang/jvm/actions/JvmElementActionsFactory.kt) |
| [com.intellij.lang.jvm.annotationPackageSupport](https://jb.gg/ipe?extensions=com.intellij.lang.jvm.annotationPackageSupport) | [`AnnotationPackageSupport`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/annoPackages/AnnotationPackageSupport.java) |
| [com.intellij.lang.jvm.annotations.marker.suppressor](https://jb.gg/ipe?extensions=com.intellij.lang.jvm.annotations.marker.suppressor) | [`NonCodeAnnotationsMarkerSuppressor`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/NonCodeAnnotationsMarkerSuppressor.java) |
| [com.intellij.lang.jvm.bytecodeAnalysisSuppressor](https://jb.gg/ipe?extensions=com.intellij.lang.jvm.bytecodeAnalysisSuppressor) | [`BytecodeAnalysisSuppressor`](%gh-ic%/java/java-analysis-impl/src/com/intellij/codeInspection/bytecodeAnalysis/BytecodeAnalysisSuppressor.java) |
| [com.intellij.lang.jvm.ignoreAnnotationParamSupport](https://jb.gg/ipe?extensions=com.intellij.lang.jvm.ignoreAnnotationParamSupport) | [`IgnoreAnnotationParamSupport`](%gh-ic%/java/java-impl-inspections/src/com/intellij/codeInspection/DefaultAnnotationParamInspection.java) |
| [com.intellij.lang.jvm.ignoreVariableCanBeFinalSupport](https://jb.gg/ipe?extensions=com.intellij.lang.jvm.ignoreVariableCanBeFinalSupport) | [`IgnoreVariableCanBeFinalSupport`](%gh-ic%/java/java-analysis-impl/src/com/intellij/codeInspection/localCanBeFinal/IgnoreVariableCanBeFinalSupport.java) |
| [com.intellij.propertyAccessorDetector](https://jb.gg/ipe?extensions=com.intellij.propertyAccessorDetector) | [`PropertyAccessorDetector`](%gh-ic%/java/java-analysis-impl/src/com/intellij/psi/util/PropertyAccessorDetector.java) |
| [com.intellij.visibility](https://jb.gg/ipe?extensions=com.intellij.visibility) | [`VisibilityExtension`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInspection/visibility/VisibilityExtension.java) |

### JavaIndexingPlugin.xml

[`JavaIndexingPlugin.xml`](%gh-ic%/java/java-indexing-impl/resources/META-INF/JavaIndexingPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.allClassesSearch](https://jb.gg/ipe?extensions=com.intellij.allClassesSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.allOverridingMethodsSearch](https://jb.gg/ipe?extensions=com.intellij.allOverridingMethodsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.annotatedElementsSearch](https://jb.gg/ipe?extensions=com.intellij.annotatedElementsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.annotatedPackagesSearch](https://jb.gg/ipe?extensions=com.intellij.annotatedPackagesSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.classInheritorsSearch](https://jb.gg/ipe?extensions=com.intellij.classInheritorsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.classesWithAnnotatedMembersSearch](https://jb.gg/ipe?extensions=com.intellij.classesWithAnnotatedMembersSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.customPropertyScopeProvider](https://jb.gg/ipe?extensions=com.intellij.customPropertyScopeProvider) | [`CustomPropertyScopeProvider`](%gh-ic%/java/java-indexing-impl/src/com/intellij/psi/impl/search/CustomPropertyScopeProvider.java) |
| [com.intellij.directClassInheritorsSearch](https://jb.gg/ipe?extensions=com.intellij.directClassInheritorsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.functionalExpressionSearch](https://jb.gg/ipe?extensions=com.intellij.functionalExpressionSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.implicitClassSearch](https://jb.gg/ipe?extensions=com.intellij.implicitClassSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.implicitToStringSearch](https://jb.gg/ipe?extensions=com.intellij.implicitToStringSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.java.shortNamesCache](https://jb.gg/ipe?extensions=com.intellij.java.shortNamesCache) ![Project-Level][project-level] | [`PsiShortNamesCache`](%gh-ic%/java/java-indexing-api/src/com/intellij/psi/search/PsiShortNamesCache.java) |
| [com.intellij.java.staticMethodNamesCache](https://jb.gg/ipe?extensions=com.intellij.java.staticMethodNamesCache) ![Experimental][experimental] ![Project-Level][project-level] | [`JavaStaticMethodNameCache`](%gh-ic%/java/java-indexing-api/src/com/intellij/psi/search/JavaStaticMethodNameCache.java) |
| [com.intellij.javaModuleSearch](https://jb.gg/ipe?extensions=com.intellij.javaModuleSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.methodReferencesSearch](https://jb.gg/ipe?extensions=com.intellij.methodReferencesSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.overridingMethodsSearch](https://jb.gg/ipe?extensions=com.intellij.overridingMethodsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |

### JavaPlugin.xml

[`JavaPlugin.xml`](%gh-ic%/java/java-impl/resources/META-INF/JavaPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.OrderRootTypeUI](https://jb.gg/ipe?extensions=com.intellij.OrderRootTypeUI) | [`OrderRootTypeUIFactory`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/roots/ui/OrderRootTypeUIFactory.java) |
| [com.intellij.attachSourcesProvider](https://jb.gg/ipe?extensions=com.intellij.attachSourcesProvider) | [`AttachSourcesProvider`](%gh-ic%/java/openapi/src/com/intellij/codeInsight/AttachSourcesProvider.java) |
| [com.intellij.buildProcess.parametersProvider](https://jb.gg/ipe?extensions=com.intellij.buildProcess.parametersProvider) ![Project-Level][project-level] | [`BuildProcessParametersProvider`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/server/BuildProcessParametersProvider.java) |
| [com.intellij.canBeEmpty](https://jb.gg/ipe?extensions=com.intellij.canBeEmpty) | [`Condition`](%gh-ic%/platform/util/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.codeInsight.createFieldFromUsageHelper](https://jb.gg/ipe?extensions=com.intellij.codeInsight.createFieldFromUsageHelper) | [`CreateFieldFromUsageHelper`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/daemon/impl/quickfix/CreateFieldFromUsageHelper.java) |
| [com.intellij.codeInsight.externalLibraryResolver](https://jb.gg/ipe?extensions=com.intellij.codeInsight.externalLibraryResolver) | [`ExternalLibraryResolver`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/daemon/quickFix/ExternalLibraryResolver.java) |
| [com.intellij.compilableFileTypesProvider](https://jb.gg/ipe?extensions=com.intellij.compilableFileTypesProvider) ![Project-Level][project-level] | [`CompilableFileTypesProvider`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/CompilableFileTypesProvider.java) |
| [com.intellij.compileServer.plugin](https://jb.gg/ipe?extensions=com.intellij.compileServer.plugin) | `n/a` |
| [com.intellij.compiler](https://jb.gg/ipe?extensions=com.intellij.compiler) ![Deprecated][deprecated] ![Project-Level][project-level] | [`Compiler`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/Compiler.java) |
| [com.intellij.compiler.buildIssueContributor](https://jb.gg/ipe?extensions=com.intellij.compiler.buildIssueContributor) ![Experimental][experimental] | [`BuildIssueContributor`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/progress/BuildIssueContributor.java) |
| [com.intellij.compiler.buildTargetScopeProvider](https://jb.gg/ipe?extensions=com.intellij.compiler.buildTargetScopeProvider) | [`BuildTargetScopeProvider`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/impl/BuildTargetScopeProvider.java) |
| [com.intellij.compiler.inspectionValidator](https://jb.gg/ipe?extensions=com.intellij.compiler.inspectionValidator) ![Project-Level][project-level] | [`InspectionValidator`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/util/InspectionValidator.java) |
| [com.intellij.compiler.isUpToDateCheckConsumer](https://jb.gg/ipe?extensions=com.intellij.compiler.isUpToDateCheckConsumer) | [`IsUpToDateCheckConsumer`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/backwardRefs/IsUpToDateCheckConsumer.java) |
| [com.intellij.compiler.optionsManager](https://jb.gg/ipe?extensions=com.intellij.compiler.optionsManager) | [`CompilerOptionsFilter`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/options/CompilerOptionsFilter.java) |
| [com.intellij.compiler.task](https://jb.gg/ipe?extensions=com.intellij.compiler.task) ![Project-Level][project-level] | [`CompileTask`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/CompileTask.java) |
| [com.intellij.compiler.updateResourcesBuildContributor](https://jb.gg/ipe?extensions=com.intellij.compiler.updateResourcesBuildContributor) | [`UpdateResourcesBuildContributor`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/impl/UpdateResourcesBuildContributor.java) |
| [com.intellij.compilerFactory](https://jb.gg/ipe?extensions=com.intellij.compilerFactory) ![Deprecated][deprecated] ![Removal][removal] ![Project-Level][project-level] | [`CompilerFactory`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/CompilerFactory.java) |
| [com.intellij.configuration.ModuleStructureExtension](https://jb.gg/ipe?extensions=com.intellij.configuration.ModuleStructureExtension) | [`ModuleStructureExtension`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/projectRoot/ModuleStructureExtension.java) |
| [com.intellij.configuration.moduleStructureFilterExtension](https://jb.gg/ipe?extensions=com.intellij.configuration.moduleStructureFilterExtension) | [`ModuleStructureFilterExtension`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/projectRoot/ModuleStructureFilterExtension.java) |
| [com.intellij.constructorBodyGenerator](https://jb.gg/ipe?extensions=com.intellij.constructorBodyGenerator) ![Internal][internal] | [`ConstructorBodyGenerator`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/generation/ConstructorBodyGenerator.java) |
| [com.intellij.conversion.rule](https://jb.gg/ipe?extensions=com.intellij.conversion.rule) | [`TypeConversionRule`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/typeMigration/rules/TypeConversionRule.java) |
| [com.intellij.debuggerClassFilterProvider](https://jb.gg/ipe?extensions=com.intellij.debuggerClassFilterProvider) | [`DebuggerClassFilterProvider`](%gh-ic%/java/openapi/src/com/intellij/ui/classFilter/DebuggerClassFilterProvider.java) |
| [com.intellij.disableCompilationDependenciesResolutionTask](https://jb.gg/ipe?extensions=com.intellij.disableCompilationDependenciesResolutionTask) ![Experimental][experimental] | [`DisableCompilationDependenciesResolutionTask`](%gh-ic%/java/idea-ui/src/com/intellij/jarRepository/CompilationDependenciesResolutionTask.kt) |
| [com.intellij.documentationDelegateProvider](https://jb.gg/ipe?extensions=com.intellij.documentationDelegateProvider) | [`DocumentationDelegateProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/javadoc/DocumentationDelegateProvider.java) |
| [com.intellij.encapsulateFields.Helper](https://jb.gg/ipe?extensions=com.intellij.encapsulateFields.Helper) | [`EncapsulateFieldHelper`](%gh-ic%/java/openapi/src/com/intellij/refactoring/encapsulateFields/EncapsulateFieldHelper.java) |
| [com.intellij.exceptionFilter](https://jb.gg/ipe?extensions=com.intellij.exceptionFilter) | [`ExceptionFilterFactory`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/filters/ExceptionFilterFactory.java) |
| [com.intellij.execution.applicationRunLineMarkerHider](https://jb.gg/ipe?extensions=com.intellij.execution.applicationRunLineMarkerHider) ![DumbAware][dumb-aware] | [`ApplicationRunLineMarkerHider`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/ApplicationRunLineMarkerHider.java) |
| [com.intellij.externalAnnotationsArtifactsResolver](https://jb.gg/ipe?extensions=com.intellij.externalAnnotationsArtifactsResolver) | [`ExternalAnnotationsArtifactsResolver`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/ExternalAnnotationsArtifactsResolver.java) |
| [com.intellij.framework.type](https://jb.gg/ipe?extensions=com.intellij.framework.type) ![DumbAware][dumb-aware] | [`FrameworkTypeEx`](%gh-ic%/java/idea-ui/src/com/intellij/framework/FrameworkTypeEx.java) |
| [com.intellij.frameworkSupport](https://jb.gg/ipe?extensions=com.intellij.frameworkSupport) | [`FrameworkSupportProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/util/frameworkSupport/FrameworkSupportProvider.java) |
| [com.intellij.frameworkSupportCommunicator](https://jb.gg/ipe?extensions=com.intellij.frameworkSupportCommunicator) | [`FrameworkSupportCommunicator`](%gh-ic%/java/idea-ui/src/com/intellij/ide/util/newProjectWizard/impl/FrameworkSupportCommunicator.java) |
| [com.intellij.generateAccessorProvider](https://jb.gg/ipe?extensions=com.intellij.generateAccessorProvider) ![Obsolete][obsolete] | [`NotNullFunction`](%gh-ic%/platform/util/src/com/intellij/util/NotNullFunction.java) |
| [com.intellij.generation.toStringClassFilter](https://jb.gg/ipe?extensions=com.intellij.generation.toStringClassFilter) | [`GenerateToStringClassFilter`](%gh-ic%/java/java-impl/src/org/jetbrains/generate/tostring/GenerateToStringClassFilter.java) |
| [com.intellij.getterSetterProvider](https://jb.gg/ipe?extensions=com.intellij.getterSetterProvider) | [`GetterSetterPrototypeProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/generation/GetterSetterPrototypeProvider.java) |
| [com.intellij.gotoByName.defaultProvider.ignoreLanguage](https://jb.gg/ipe?extensions=com.intellij.gotoByName.defaultProvider.ignoreLanguage) ![Internal][internal] | `n/a` |
| [com.intellij.hierarchy.referenceProcessor](https://jb.gg/ipe?extensions=com.intellij.hierarchy.referenceProcessor) | [`CallReferenceProcessor`](%gh-ic%/java/openapi/src/com/intellij/ide/hierarchy/call/CallReferenceProcessor.java) |
| [com.intellij.jarRepositoryAuthenticationDataProvider](https://jb.gg/ipe?extensions=com.intellij.jarRepositoryAuthenticationDataProvider) ![Experimental][experimental] | [`JarRepositoryAuthenticationDataProvider`](%gh-ic%/java/idea-ui/src/com/intellij/jarRepository/JarRepositoryAuthenticationDataProvider.kt) |
| [com.intellij.java.changeSignature.converter](https://jb.gg/ipe?extensions=com.intellij.java.changeSignature.converter) ![Experimental][experimental] | [`JavaChangeInfoConverter`](%gh-ic%/java/java-impl/src/com/intellij/refactoring/changeSignature/JavaChangeInfoConverter.java) |
| [com.intellij.java.compiler](https://jb.gg/ipe?extensions=com.intellij.java.compiler) ![Project-Level][project-level] | [`BackendCompiler`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/impl/javaCompiler/BackendCompiler.java) |
| [com.intellij.java.consoleDecorator](https://jb.gg/ipe?extensions=com.intellij.java.consoleDecorator) ![Experimental][experimental] | [`JavaConsoleDecorator`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/application/JavaConsoleDecorator.java) |
| [com.intellij.java.effectively.final.fixer](https://jb.gg/ipe?extensions=com.intellij.java.effectively.final.fixer) ![Internal][internal] | [`EffectivelyFinalFixer`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/daemon/impl/quickfix/makefinal/EffectivelyFinalFixer.java) |
| [com.intellij.java.externalAnnotation](https://jb.gg/ipe?extensions=com.intellij.java.externalAnnotation) | [`AnnotationProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/externalAnnotation/AnnotationProvider.java) |
| [com.intellij.java.externalAnnotation.locationProvider](https://jb.gg/ipe?extensions=com.intellij.java.externalAnnotation.locationProvider) | [`AnnotationsLocationProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/externalAnnotation/location/AnnotationsLocationProvider.java) |
| [com.intellij.java.inspection.bulkMethodInfo](https://jb.gg/ipe?extensions=com.intellij.java.inspection.bulkMethodInfo) | [`BulkMethodInfoProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInspection/bulkOperation/BulkMethodInfoProvider.java) |
| [com.intellij.java.programPatcher](https://jb.gg/ipe?extensions=com.intellij.java.programPatcher) | [`JavaProgramPatcher`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/runners/JavaProgramPatcher.java) |
| [com.intellij.java.refactoring.chainCallExtractor](https://jb.gg/ipe?extensions=com.intellij.java.refactoring.chainCallExtractor) | [`ChainCallExtractor`](%gh-ic%/java/java-impl/src/com/intellij/refactoring/chainCall/ChainCallExtractor.java) |
| [com.intellij.javaDocNotNecessary](https://jb.gg/ipe?extensions=com.intellij.javaDocNotNecessary) | [`Condition`](%gh-ic%/platform/util/src/com/intellij/openapi/util/Condition.java) |
| [com.intellij.javaExpressionSurrounder](https://jb.gg/ipe?extensions=com.intellij.javaExpressionSurrounder) | [`JavaExpressionSurrounder`](%gh-ic%/java/openapi/src/com/intellij/codeInsight/generation/surroundWith/JavaExpressionSurrounder.java) |
| [com.intellij.jpsServerAuthExtension](https://jb.gg/ipe?extensions=com.intellij.jpsServerAuthExtension) | [`JpsServerAuthExtension`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/cache/client/JpsServerAuthExtension.kt) |
| [com.intellij.jreProvider](https://jb.gg/ipe?extensions=com.intellij.jreProvider) | [`JreProvider`](%gh-ic%/java/execution/impl/src/com/intellij/execution/ui/JreProvider.java) |
| [com.intellij.junitPatcher](https://jb.gg/ipe?extensions=com.intellij.junitPatcher) | [`JUnitPatcher`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/JUnitPatcher.java) |
| [com.intellij.junitRecognizer](https://jb.gg/ipe?extensions=com.intellij.junitRecognizer) | [`JUnitRecognizer`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/JUnitRecognizer.java) |
| [com.intellij.jvm.exceptionFilter](https://jb.gg/ipe?extensions=com.intellij.jvm.exceptionFilter) ![Experimental][experimental] | [`JvmExceptionOccurrenceFilter`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/filters/JvmExceptionOccurrenceFilter.java) |
| [com.intellij.jvm.logging](https://jb.gg/ipe?extensions=com.intellij.jvm.logging) ![Internal][internal] | [`JvmLogger`](%gh-ic%/java/java-impl/src/com/intellij/lang/logging/JvmLogger.kt) |
| [com.intellij.languageCompilerRefAdapter](https://jb.gg/ipe?extensions=com.intellij.languageCompilerRefAdapter) | [`LanguageCompilerRefAdapter`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/backwardRefs/LanguageCompilerRefAdapter.java) |
| [com.intellij.languageCompilerRefAdapter.directInheritorProvider](https://jb.gg/ipe?extensions=com.intellij.languageCompilerRefAdapter.directInheritorProvider) ![Project-Level][project-level] | [`DirectInheritorProvider`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/backwardRefs/DirectInheritorProvider.kt) |
| [com.intellij.library.dependencyScopeSuggester](https://jb.gg/ipe?extensions=com.intellij.library.dependencyScopeSuggester) | [`LibraryDependencyScopeSuggester`](%gh-ic%/java/java-impl/src/com/intellij/openapi/roots/LibraryDependencyScopeSuggester.java) |
| [com.intellij.library.javaSourceRootDetector](https://jb.gg/ipe?extensions=com.intellij.library.javaSourceRootDetector) | [`RootDetector`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/libraries/ui/RootDetector.java) |
| [com.intellij.methodImplementor](https://jb.gg/ipe?extensions=com.intellij.methodImplementor) | [`MethodImplementor`](%gh-ic%/java/openapi/src/com/intellij/codeInsight/MethodImplementor.java) |
| [com.intellij.moduleConfigurable](https://jb.gg/ipe?extensions=com.intellij.moduleConfigurable) ![Removal][removal] | [`ModuleConfigurable`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/projectRoot/ModuleConfigurable.java) |
| [com.intellij.newProjectWizard.java.buildSystem](https://jb.gg/ipe?extensions=com.intellij.newProjectWizard.java.buildSystem) | [`BuildSystemJavaNewProjectWizard`](%gh-ic%/java/idea-ui/src/com/intellij/ide/projectWizard/generators/BuildSystemJavaNewProjectWizard.kt) |
| [com.intellij.openapi.projectRoots.externalJavaConfigurationProvider](https://jb.gg/ipe?extensions=com.intellij.openapi.projectRoots.externalJavaConfigurationProvider) | [`ExternalJavaConfigurationProvider`](%gh-ic%/java/java-impl/src/com/intellij/openapi/projectRoots/impl/ExternalJavaConfigurationProvider.kt) |
| [com.intellij.overrideImplementsAnnotationsHandler](https://jb.gg/ipe?extensions=com.intellij.overrideImplementsAnnotationsHandler) | [`OverrideImplementsAnnotationsHandler`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/generation/OverrideImplementsAnnotationsHandler.java) |
| [com.intellij.packaging.artifactPropertiesProvider](https://jb.gg/ipe?extensions=com.intellij.packaging.artifactPropertiesProvider) | [`ArtifactPropertiesProvider`](%gh-ic%/java/compiler/openapi/src/com/intellij/packaging/artifacts/ArtifactPropertiesProvider.java) |
| [com.intellij.packaging.artifactType](https://jb.gg/ipe?extensions=com.intellij.packaging.artifactType) | [`ArtifactType`](%gh-ic%/java/compiler/openapi/src/com/intellij/packaging/artifacts/ArtifactType.java) |
| [com.intellij.packaging.elementType](https://jb.gg/ipe?extensions=com.intellij.packaging.elementType) | [`PackagingElementType`](%gh-ic%/java/compiler/openapi/src/com/intellij/packaging/elements/PackagingElementType.java) |
| [com.intellij.packaging.sourceItemFilter](https://jb.gg/ipe?extensions=com.intellij.packaging.sourceItemFilter) | [`PackagingSourceItemFilter`](%gh-ic%/java/compiler/openapi/src/com/intellij/packaging/ui/PackagingSourceItemFilter.java) |
| [com.intellij.packaging.sourceItemProvider](https://jb.gg/ipe?extensions=com.intellij.packaging.sourceItemProvider) | [`PackagingSourceItemsProvider`](%gh-ic%/java/compiler/openapi/src/com/intellij/packaging/ui/PackagingSourceItemsProvider.java) |
| [com.intellij.predefinedMigrationMapProvider](https://jb.gg/ipe?extensions=com.intellij.predefinedMigrationMapProvider) | [`PredefinedMigrationProvider`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/migration/PredefinedMigrationProvider.java) |
| [com.intellij.projectImportBuilder](https://jb.gg/ipe?extensions=com.intellij.projectImportBuilder) | [`ProjectImportBuilder`](%gh-ic%/java/idea-ui/src/com/intellij/projectImport/ProjectImportBuilder.java) |
| [com.intellij.projectImportProvider](https://jb.gg/ipe?extensions=com.intellij.projectImportProvider) | [`ProjectImportProvider`](%gh-ic%/java/idea-ui/src/com/intellij/projectImport/ProjectImportProvider.java) |
| [com.intellij.projectModelModifier](https://jb.gg/ipe?extensions=com.intellij.projectModelModifier) ![Project-Level][project-level] | [`JavaProjectModelModifier`](%gh-ic%/java/java-impl/src/com/intellij/openapi/roots/JavaProjectModelModifier.java) |
| [com.intellij.projectStructureConfigurableFilter](https://jb.gg/ipe?extensions=com.intellij.projectStructureConfigurableFilter) | [`ProjectStructureConfigurableFilter`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/ProjectStructureConfigurableFilter.java) |
| [com.intellij.projectStructureDetector](https://jb.gg/ipe?extensions=com.intellij.projectStructureDetector) | [`ProjectStructureDetector`](%gh-ic%/java/idea-ui/src/com/intellij/ide/util/projectWizard/importSources/ProjectStructureDetector.java) |
| [com.intellij.projectStructureValidator](https://jb.gg/ipe?extensions=com.intellij.projectStructureValidator) | [`ProjectStructureValidator`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/projectRoot/daemon/ProjectStructureValidator.java) |
| [com.intellij.projectWizard.projectCategory](https://jb.gg/ipe?extensions=com.intellij.projectWizard.projectCategory) | [`ProjectCategory`](%gh-ic%/java/idea-ui/src/com/intellij/ide/projectWizard/ProjectCategory.java) |
| [com.intellij.refactoring.introduceParameterMethodUsagesProcessor](https://jb.gg/ipe?extensions=com.intellij.refactoring.introduceParameterMethodUsagesProcessor) | [`IntroduceParameterMethodUsagesProcessor`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/introduceParameter/IntroduceParameterMethodUsagesProcessor.java) |
| [com.intellij.refactoring.moveAllClassesInFileHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveAllClassesInFileHandler) | [`MoveAllClassesInFileHandler`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/move/moveClassesOrPackages/MoveAllClassesInFileHandler.java) |
| [com.intellij.refactoring.moveClassHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveClassHandler) | [`MoveClassHandler`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/move/moveClassesOrPackages/MoveClassHandler.java) |
| [com.intellij.refactoring.moveClassToInnerHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveClassToInnerHandler) ![Internal][internal] | [`MoveClassToInnerHandler`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/move/moveClassesOrPackages/MoveClassToInnerHandler.java) |
| [com.intellij.refactoring.moveInnerClassUsagesHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveInnerClassUsagesHandler) | [`MoveInnerClassUsagesHandler`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/move/moveInner/MoveInnerClassUsagesHandler.java) |
| [com.intellij.refactoring.moveInnerHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveInnerHandler) | [`MoveInnerHandler`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/move/moveInner/MoveInnerHandler.java) |
| [com.intellij.refactoring.moveMemberHandler](https://jb.gg/ipe?extensions=com.intellij.refactoring.moveMemberHandler) | [`MoveMemberHandler`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/move/moveMembers/MoveMemberHandler.java) |
| [com.intellij.refactoring.overrideMethodProcessor](https://jb.gg/ipe?extensions=com.intellij.refactoring.overrideMethodProcessor) ![Internal][internal] | [`OverrideMethodsProcessor`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/OverrideMethodsProcessor.java) |
| [com.intellij.refactoring.pullUpHelperFactory](https://jb.gg/ipe?extensions=com.intellij.refactoring.pullUpHelperFactory) | [`PullUpHelperFactory`](%gh-ic%/java/openapi/src/com/intellij/refactoring/memberPullUp/PullUpHelperFactory.java) |
| [com.intellij.refactoring.safeDelete.JavaSafeDeleteDelegate](https://jb.gg/ipe?extensions=com.intellij.refactoring.safeDelete.JavaSafeDeleteDelegate) | [`JavaSafeDeleteDelegate`](%gh-ic%/java/openapi/src/com/intellij/refactoring/safeDelete/JavaSafeDeleteDelegate.java) |
| [com.intellij.repositoryLibrary](https://jb.gg/ipe?extensions=com.intellij.repositoryLibrary) | `n/a` |
| [com.intellij.runConfigurationExtension](https://jb.gg/ipe?extensions=com.intellij.runConfigurationExtension) | [`RunConfigurationExtension`](%gh-ic%/java/execution/impl/src/com/intellij/execution/RunConfigurationExtension.java) |
| [com.intellij.safeDelete.importSearcher](https://jb.gg/ipe?extensions=com.intellij.safeDelete.importSearcher) ![Internal][internal] | [`ImportSearcher`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/safeDelete/ImportSearcher.java) |
| [com.intellij.sdkEditorAdditionalOptionsProvider](https://jb.gg/ipe?extensions=com.intellij.sdkEditorAdditionalOptionsProvider) | [`SdkEditorAdditionalOptionsProvider`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/SdkEditorAdditionalOptionsProvider.java) |
| [com.intellij.starter.moduleImporter](https://jb.gg/ipe?extensions=com.intellij.starter.moduleImporter) | [`StarterModuleImporter`](%gh-ic%/java/idea-ui/src/com/intellij/ide/starters/StarterModuleImporter.kt) |
| [com.intellij.testGenerator](https://jb.gg/ipe?extensions=com.intellij.testGenerator) | [`TestGenerator`](%gh-ic%/java/java-impl/src/com/intellij/testIntegration/createTest/TestGenerator.java) |
| [com.intellij.unscrambleSupport](https://jb.gg/ipe?extensions=com.intellij.unscrambleSupport) | [`UnscrambleSupport`](%gh-ic%/java/openapi/src/com/intellij/unscramble/UnscrambleSupport.java) |
| [com.intellij.unusedDeclarationFixProvider](https://jb.gg/ipe?extensions=com.intellij.unusedDeclarationFixProvider) | [`UnusedDeclarationFixProvider`](%gh-ic%/java/java-analysis-api/src/com/intellij/codeInspection/reference/UnusedDeclarationFixProvider.java) |
| [com.intellij.variableTypeCalculator](https://jb.gg/ipe?extensions=com.intellij.variableTypeCalculator) | [`VariableTypeCalculator`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/template/macro/VariableTypeCalculator.java) |

### JavaPsiPlugin.xml

[`JavaPsiPlugin.xml`](%gh-ic%/java/java-psi-impl/resources/META-INF/JavaPsiPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.annotationSupport](https://jb.gg/ipe?extensions=com.intellij.annotationSupport) | [`PsiAnnotationSupport`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiAnnotationSupport.java) |
| [com.intellij.classTypePointerFactory](https://jb.gg/ipe?extensions=com.intellij.classTypePointerFactory) | [`ClassTypePointerFactory`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/ClassTypePointerFactory.java) |
| [com.intellij.codeStyle.ReferenceAdjuster](https://jb.gg/ipe?extensions=com.intellij.codeStyle.ReferenceAdjuster) | [`ReferenceAdjuster`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/codeStyle/ReferenceAdjuster.java) |
| [com.intellij.constantExpressionEvaluator](https://jb.gg/ipe?extensions=com.intellij.constantExpressionEvaluator) | [`ConstantExpressionEvaluator`](%gh-ic%/java/java-psi-impl/src/com/intellij/psi/impl/ConstantExpressionEvaluator.java) |
| [com.intellij.custom.exception.handler](https://jb.gg/ipe?extensions=com.intellij.custom.exception.handler) | [`CustomExceptionHandler`](%gh-ic%/java/java-psi-impl/src/com/intellij/codeInsight/CustomExceptionHandler.java) |
| [com.intellij.customJavadocTagProvider](https://jb.gg/ipe?extensions=com.intellij.customJavadocTagProvider) | [`CustomJavadocTagProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/javadoc/CustomJavadocTagProvider.java) |
| [com.intellij.deepestSuperMethodsSearch](https://jb.gg/ipe?extensions=com.intellij.deepestSuperMethodsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.expressionConverter](https://jb.gg/ipe?extensions=com.intellij.expressionConverter) | [`ExpressionConverter`](%gh-ic%/java/java-psi-impl/src/com/intellij/psi/impl/ExpressionConverter.java) |
| [com.intellij.generation.topLevelFactory](https://jb.gg/ipe?extensions=com.intellij.generation.topLevelFactory) | [`JVMElementFactoryProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/JVMElementFactoryProvider.java) |
| [com.intellij.importFilter](https://jb.gg/ipe?extensions=com.intellij.importFilter) | [`ImportFilter`](%gh-ic%/java/java-psi-api/src/com/intellij/codeInsight/ImportFilter.java) |
| [com.intellij.java.elementFinder](https://jb.gg/ipe?extensions=com.intellij.java.elementFinder) ![Project-Level][project-level] ![DumbAware][dumb-aware] | [`PsiElementFinder`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiElementFinder.java) |
| [com.intellij.java.languageFeatureProvider](https://jb.gg/ipe?extensions=com.intellij.java.languageFeatureProvider) | [`LanguageFeatureProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/pom/java/LanguageFeatureProvider.java) |
| [com.intellij.javaCompilerConfigurationProxy](https://jb.gg/ipe?extensions=com.intellij.javaCompilerConfigurationProxy) | [`JavaCompilerConfigurationProxy`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/JavaCompilerConfigurationProxy.java) |
| [com.intellij.javaMainMethodProvider](https://jb.gg/ipe?extensions=com.intellij.javaMainMethodProvider) ![DumbAware][dumb-aware] | [`JavaMainMethodProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/codeInsight/runner/JavaMainMethodProvider.java) |
| [com.intellij.javaModuleSystem](https://jb.gg/ipe?extensions=com.intellij.javaModuleSystem) | [`JavaModuleSystem`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/JavaModuleSystem.java) |
| [com.intellij.javadocTagInfo](https://jb.gg/ipe?extensions=com.intellij.javadocTagInfo) ![Project-Level][project-level] | [`JavadocTagInfo`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/javadoc/JavadocTagInfo.java) |
| [com.intellij.jvm.declarationSearcher](https://jb.gg/ipe?extensions=com.intellij.jvm.declarationSearcher) | [`JvmDeclarationSearcher`](%gh-ic%/java/java-psi-api/src/com/intellij/lang/jvm/source/JvmDeclarationSearcher.java) |
| [com.intellij.jvm.elementProvider](https://jb.gg/ipe?extensions=com.intellij.jvm.elementProvider) ![Project-Level][project-level] ![DumbAware][dumb-aware] | [`JvmElementProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/lang/jvm/facade/JvmElementProvider.java) |
| [com.intellij.lang.dumb.mode.supported](https://jb.gg/ipe?extensions=com.intellij.lang.dumb.mode.supported) ![Experimental][experimental] | [`JvmLanguageDumbAware`](%gh-ic%/java/java-psi-api/src/com/intellij/lang/jvm/JvmLanguageDumbAware.java) |
| [com.intellij.lang.inferredAnnotationProvider](https://jb.gg/ipe?extensions=com.intellij.lang.inferredAnnotationProvider) ![Project-Level][project-level] | [`InferredAnnotationProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/codeInsight/InferredAnnotationProvider.java) |
| [com.intellij.lang.jvm.multiReleaseSupport](https://jb.gg/ipe?extensions=com.intellij.lang.jvm.multiReleaseSupport) | [`JavaMultiReleaseModuleSupport`](%gh-ic%/java/java-psi-impl/src/com/intellij/psi/util/JavaMultiReleaseModuleSupport.java) |
| [com.intellij.lang.psiAugmentProvider](https://jb.gg/ipe?extensions=com.intellij.lang.psiAugmentProvider) ![DumbAware][dumb-aware] | [`PsiAugmentProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/augment/PsiAugmentProvider.java) |
| [com.intellij.psi.classFileDecompiler](https://jb.gg/ipe?extensions=com.intellij.psi.classFileDecompiler) | `Decompiler` |
| [com.intellij.psi.clsCustomNavigationPolicy](https://jb.gg/ipe?extensions=com.intellij.psi.clsCustomNavigationPolicy) | [`ClsCustomNavigationPolicy`](%gh-ic%/java/java-psi-impl/src/com/intellij/psi/impl/compiled/ClsCustomNavigationPolicy.java) |
| [com.intellij.superMethodsSearch](https://jb.gg/ipe?extensions=com.intellij.superMethodsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [com.intellij.testFramework](https://jb.gg/ipe?extensions=com.intellij.testFramework) ![DumbAware][dumb-aware] | [`TestFramework`](%gh-ic%/platform/core-api/src/com/intellij/testIntegration/TestFramework.java) |
| [org.jetbrains.uast.analysis.uastAnalysisPlugin](https://jb.gg/ipe?extensions=org.jetbrains.uast.analysis.uastAnalysisPlugin) ![Experimental][experimental] | [`UastAnalysisPlugin`](%gh-ic%/uast/uast-common/src/org/jetbrains/uast/analysis/UastAnalysisPlugin.kt) |
| [org.jetbrains.uast.evaluation.uastEvaluatorExtension](https://jb.gg/ipe?extensions=org.jetbrains.uast.evaluation.uastEvaluatorExtension) ![Experimental][experimental] | [`UEvaluatorExtension`](%gh-ic%/uast/uast-common/src/org/jetbrains/uast/evaluation/UEvaluatorExtension.kt) |
| [org.jetbrains.uast.generate.uastCodeGenerationPlugin](https://jb.gg/ipe?extensions=org.jetbrains.uast.generate.uastCodeGenerationPlugin) ![Experimental][experimental] | [`UastCodeGenerationPlugin`](%gh-ic%/uast/uast-common-ide/src/org/jetbrains/uast/generate/UastCodeGenerationPlugin.kt) |
| [org.jetbrains.uast.uastLanguagePlugin](https://jb.gg/ipe?extensions=org.jetbrains.uast.uastLanguagePlugin) | [`UastLanguagePlugin`](%gh-ic%/uast/uast-common/src/org/jetbrains/uast/UastLanguagePlugin.kt) |

### ManifestSupport.xml

[`ManifestSupport.xml`](%gh-ic%/java/manifest/resources/META-INF/ManifestSupport.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.manifest.parser.provider](https://jb.gg/ipe?extensions=com.intellij.manifest.parser.provider) | [`HeaderParserProvider`](%gh-ic%/java/manifest/src/org/jetbrains/lang/manifest/header/HeaderParserProvider.java) |

### UsageData.xml

[`UsageData.xml`](%gh-ic%/java/java-impl/resources/META-INF/UsageData.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.internal.statistic.libraryUsageImportProcessor](https://jb.gg/ipe?extensions=com.intellij.internal.statistic.libraryUsageImportProcessor) ![Internal][internal] | [`LibraryUsageImportProcessor`](%gh-ic%/java/java-impl/src/com/intellij/internal/statistic/libraryUsage/LibraryUsageImportProcessor.kt) |



## JSON Plugin


### JSON Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [JsonSchemaVfsListener#JSON_SCHEMA_CHANGED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [JsonSchemaVfsListener#JSON_DEPS_CHANGED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |


### intellij.json.split.xml

[`intellij.json.split.xml`](%gh-ic%/json/split/resources/intellij.json.split.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.json.jsonLiteralChecker](https://jb.gg/ipe?extensions=com.intellij.json.jsonLiteralChecker) | [`JsonLiteralChecker`](%gh-ic%/json/split/src/com/intellij/json/codeinsight/JsonLiteralChecker.java) |

### intellij.json.xml

[`intellij.json.xml`](%gh-ic%/json/resources/intellij.json.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [JavaScript.JsonSchema.ContentAwareSchemaFileProvider](https://jb.gg/ipe?extensions=JavaScript.JsonSchema.ContentAwareSchemaFileProvider) | [`ContentAwareJsonSchemaFileProvider`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/ContentAwareJsonSchemaFileProvider.java) |
| [JavaScript.JsonSchema.ProviderFactory](https://jb.gg/ipe?extensions=JavaScript.JsonSchema.ProviderFactory) ![DumbAware][dumb-aware] | [`JsonSchemaProviderFactory`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonSchemaProviderFactory.java) |
| [com.intellij.json.catalog.exclusion](https://jb.gg/ipe?extensions=com.intellij.json.catalog.exclusion) | [`JsonSchemaCatalogExclusion`](%gh-ic%/json/src/com/jetbrains/jsonSchema/remote/JsonSchemaCatalogExclusion.java) |
| [com.intellij.json.customStructureViewFactory](https://jb.gg/ipe?extensions=com.intellij.json.customStructureViewFactory) | [`JsonCustomStructureViewFactory`](%gh-ic%/json/src/com/intellij/json/structureView/JsonCustomStructureViewFactory.java) |
| [com.intellij.json.jsonLikePsiWalkerFactory](https://jb.gg/ipe?extensions=com.intellij.json.jsonLikePsiWalkerFactory) | [`JsonLikePsiWalkerFactory`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonLikePsiWalkerFactory.java) |
| [com.intellij.json.jsonSchemaCompletionCustomizer](https://jb.gg/ipe?extensions=com.intellij.json.jsonSchemaCompletionCustomizer) | [`JsonSchemaCompletionCustomizer`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonSchemaCompletionCustomizer.java) |
| [com.intellij.json.jsonSchemaEnabler](https://jb.gg/ipe?extensions=com.intellij.json.jsonSchemaEnabler) | [`JsonSchemaEnabler`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonSchemaEnabler.java) |
| [com.intellij.json.jsonSchemaGotoDeclarationSuppressor](https://jb.gg/ipe?extensions=com.intellij.json.jsonSchemaGotoDeclarationSuppressor) | [`JsonSchemaGotoDeclarationSuppressor`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonSchemaGotoDeclarationSuppressor.java) |
| [com.intellij.json.jsonSchemaNestedCompletionsTreeProvider](https://jb.gg/ipe?extensions=com.intellij.json.jsonSchemaNestedCompletionsTreeProvider) ![Experimental][experimental] | [`JsonSchemaNestedCompletionsTreeProvider`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonSchemaNestedCompletionsTreeProvider.kt) |
| [com.intellij.json.jsonSchemaQuickFixSuppressor](https://jb.gg/ipe?extensions=com.intellij.json.jsonSchemaQuickFixSuppressor) | [`JsonSchemaQuickFixSuppressor`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonSchemaQuickFixSuppressor.kt) |
| [com.intellij.json.jsonStandardComplianceProvider](https://jb.gg/ipe?extensions=com.intellij.json.jsonStandardComplianceProvider) | [`JsonStandardComplianceProvider`](%gh-ic%/json/src/com/intellij/json/codeinsight/JsonStandardComplianceProvider.java) |
| [com.intellij.json.jsonWidgetSuppressor](https://jb.gg/ipe?extensions=com.intellij.json.jsonWidgetSuppressor) | [`JsonWidgetSuppressor`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonWidgetSuppressor.java) |
| [com.intellij.json.shorthandValueHandler](https://jb.gg/ipe?extensions=com.intellij.json.shorthandValueHandler) | [`JsonSchemaShorthandValueHandler`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonSchemaShorthandValueHandler.kt) |



## Kotlin Plugin


### Kotlin Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [LibraryInfoListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.base.projectStructure.LibraryInfoListener)  ![Internal][internal] ![Project-Level][project-level] | [`LibraryInfoListener`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/LibraryInfoCache.kt) |
| [KotlinCompilerSettingsListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.compiler.configuration.KotlinCompilerSettingsListener)  ![Project-Level][project-level] | [`KotlinCompilerSettingsListener`](%gh-ic%/plugins/kotlin/base/compiler-configuration/src/org/jetbrains/kotlin/idea/compiler/configuration/BaseKotlinCompilerSettings.kt) |
| [KotlinBundledUsageDetector#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.macros.KotlinBundledUsageDetectorListener)  ![Project-Level][project-level] | [`KotlinBundledUsageDetectorListener`](%gh-ic%/plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/macros/KotlinBundledUsageDetectorListener.kt) |
| [KotlinRefactoringEventListener.Companion#EVENT_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.refactoring.KotlinRefactoringEventListener)  ![Deprecated][deprecated] | [`KotlinRefactoringEventListener`](%gh-ic%/plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/refactoring/RefactoringEventListenerEx.kt) |
| [KotlinRefactoringListener.Companion#EVENT_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.refactoring.KotlinRefactoringListener)  | [`KotlinRefactoringListener`](%gh-ic%/plugins/kotlin/refactorings/kotlin.refactorings.common/src/org/jetbrains/kotlin/idea/refactoring/KotlinRefactoringListener.kt) |
| [ScratchFileListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.scratch.ScratchFileListener)  ![Project-Level][project-level] | [`ScratchFileListener`](%gh-ic%/plugins/kotlin/jvm/src/org/jetbrains/kotlin/idea/scratch/ScratchFile.kt) |
| [KotlinCorruptedIndexListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.stubindex.resolve.KotlinCorruptedIndexListener)  ![Project-Level][project-level] | [`KotlinCorruptedIndexListener`](%gh-ic%/plugins/kotlin/base/analysis/src/org/jetbrains/kotlin/idea/stubindex/resolve/KotlinCorruptedIndexListener.kt) |


### completion-fe10.xml

[`completion-fe10.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/completion-fe10.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.completionInformationProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.completionInformationProvider) | [`CompletionInformationProvider`](%gh-ic%/plugins/kotlin/base/fe10/analysis/src/org/jetbrains/kotlin/idea/completion/CompletionInformationProvider.kt) |

### extensions.xml

[`extensions.xml`](%gh-ic%/plugins/kotlin/plugin/k2/resources/META-INF/extensions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.codeinsight.quickfix.registrar](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.codeinsight.quickfix.registrar) | [`KotlinQuickFixRegistrar`](%gh-ic%/plugins/kotlin/code-insight/api/src/org/jetbrains/kotlin/idea/codeinsight/api/applicators/fixes/KotlinQuickFixService.kt) |

### facets-base.xml

[`facets-base.xml`](%gh-ic%/plugins/kotlin/plugin/common/resources/META-INF/facets-base.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.facetConfigurationExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.facetConfigurationExtension) ![Non-Dynamic][non-dynamic] | [`KotlinFacetConfigurationExtension`](%gh-ic%/plugins/kotlin/base/facet/src/org/jetbrains/kotlin/idea/facet/KotlinFacetConfigurationExtension.kt) |
| [org.jetbrains.kotlin.versionInfoProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.versionInfoProvider) ![Non-Dynamic][non-dynamic] | [`KotlinVersionInfoProvider`](%gh-ic%/plugins/kotlin/base/facet/src/org/jetbrains/kotlin/idea/facet/KotlinVersionInfoProvider.kt) |

### file-types.xml

[`file-types.xml`](%gh-ic%/plugins/kotlin/plugin/common/resources/META-INF/file-types.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.binaryExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.binaryExtension) | [`KotlinBinaryExtension`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/KotlinBinaryExtension.kt) |

### highlighting-fe10.xml

[`highlighting-fe10.xml`](%gh-ic%/plugins/kotlin/highlighting/highlighting-k1/resources/META-INF/highlighting-fe10.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.highlighterExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.highlighterExtension) | [`KotlinHighlightingVisitorExtension`](%gh-ic%/plugins/kotlin/highlighting/highlighting-k1/src/org/jetbrains/kotlin/idea/highlighter/KotlinHighlightingVisitorExtension.kt) |

### jps.xml

[`jps.xml`](%gh-ic%/plugins/kotlin/plugin/common/resources/META-INF/jps.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.idePlatformKind](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idePlatformKind) ![Non-Dynamic][non-dynamic] | `IdePlatformKind` |
| [org.jetbrains.kotlin.idePlatformKindResolution](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idePlatformKindResolution) ![Non-Dynamic][non-dynamic] | [`IdePlatformKindResolution`](%gh-ic%/plugins/kotlin/base/fe10/analysis/src/org/jetbrains/kotlin/caches/resolve/IdePlatformKindResolution.kt) |
| [org.jetbrains.kotlin.idePlatformKindTooling](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idePlatformKindTooling) ![Non-Dynamic][non-dynamic] | [`IdePlatformKindTooling`](%gh-ic%/plugins/kotlin/base/code-insight/src/org/jetbrains/kotlin/idea/base/codeInsight/tooling/IdePlatformKindTooling.kt) |

### jvm-debugger.xml

[`jvm-debugger.xml`](%gh-ic%/plugins/kotlin/plugin/common/resources/META-INF/jvm-debugger.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.debugger.dexBytecodeInspector](https://jb.gg/ipe?extensions=com.intellij.debugger.dexBytecodeInspector) | [`DexBytecodeInspector`](%gh-ic%/plugins/kotlin/jvm-debugger/core/src/org/jetbrains/kotlin/idea/debugger/core/DexBytecodeInspector.kt) |
| [com.intellij.debugger.kotlinStackFrameValueContributor](https://jb.gg/ipe?extensions=com.intellij.debugger.kotlinStackFrameValueContributor) | [`KotlinStackFrameValueContributor`](%gh-ic%/plugins/kotlin/jvm-debugger/core/src/org/jetbrains/kotlin/idea/debugger/core/stackFrame/KotlinStackFrameValueContributor.kt) |
| [org.jetbrains.kotlin.idea.debugger.base.util.classNameCalculator](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.debugger.base.util.classNameCalculator) | [`ClassNameCalculator`](%gh-ic%/plugins/kotlin/jvm-debugger/base/util/src/org/jetbrains/kotlin/idea/debugger/base/util/ClassNameCalculator.kt) |

### kotlin-core-fe10.xml

[`kotlin-core-fe10.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/kotlin-core-fe10.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.classImportFilter](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.classImportFilter) | [`ClassImportFilter`](%gh-ic%/plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/util/ClassImportFilter.kt) |
| [org.jetbrains.kotlin.idea.caches.resolve.resolveOptimizingOptionsProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.caches.resolve.resolveOptimizingOptionsProvider) | [`ResolveOptimizingOptionsProvider`](%gh-ic%/plugins/kotlin/base/fe10/analysis/src/org/jetbrains/kotlin/idea/caches/resolve/ResolveOptimizingOptionsProvider.kt) |
| [org.jetbrains.kotlin.kotlinIndicesHelperExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.kotlinIndicesHelperExtension) ![Project-Level][project-level] | [`KotlinIndicesHelperExtension`](%gh-ic%/plugins/kotlin/base/fe10/analysis/src/org/jetbrains/kotlin/idea/core/extension/KotlinIndicesHelperExtension.kt) |
| [org.jetbrains.kotlin.quickFixContributor](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.quickFixContributor) | [`QuickFixContributor`](%gh-ic%/plugins/kotlin/base/fe10/code-insight/src/org/jetbrains/kotlin/idea/quickfix/QuickFixContributor.kt) |

### kotlin-core.xml

[`kotlin-core.xml`](%gh-ic%/plugins/kotlin/plugin/common/resources/META-INF/kotlin-core.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.kotlin.autoImportCallableWeigher](https://jb.gg/ipe?extensions=com.intellij.kotlin.autoImportCallableWeigher) ![Internal][internal] | [`KotlinAutoImportCallableWeigher`](%gh-ic%/plugins/kotlin/code-insight/api/src/org/jetbrains/kotlin/idea/codeinsight/api/classic/quickfixes/KotlinAutoImportCallableWeigher.kt) |
| [org.jetbrains.kotlin.analysis.additionalKDocResolutionProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.analysis.additionalKDocResolutionProvider) | `AdditionalKDocResolutionProvider` |
| [org.jetbrains.kotlin.buildSystemDependencyManager](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.buildSystemDependencyManager) ![Internal][internal] ![Project-Level][project-level] | [`KotlinBuildSystemDependencyManager`](%gh-ic%/plugins/kotlin/project-configuration/src/org/jetbrains/kotlin/idea/configuration/KotlinBuildSystemDependencyManager.kt) |
| [org.jetbrains.kotlin.buildSystemTypeDetector](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.buildSystemTypeDetector) | [`BuildSystemTypeDetector`](%gh-ic%/plugins/kotlin/base/facet/src/org/jetbrains/kotlin/idea/configuration/BuildSystemType.kt) |
| [org.jetbrains.kotlin.bundledFirCompilerPluginProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.bundledFirCompilerPluginProvider) ![Experimental][experimental] ![Internal][internal] | [`KotlinBundledFirCompilerPluginProvider`](%gh-ic%/plugins/kotlin/bundled-compiler-plugins-support/src/org/jetbrains/kotlin/idea/fir/extensions/KotlinBundledFirCompilerPluginProvider.kt) |
| [org.jetbrains.kotlin.experimentalFeature](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.experimentalFeature) | [`ExperimentalFeature`](%gh-ic%/plugins/kotlin/preferences/src/org/jetbrains/kotlin/idea/configuration/ExperimentalFeatures.kt) |
| [org.jetbrains.kotlin.facetValidatorCreator](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.facetValidatorCreator) ![Non-Dynamic][non-dynamic] | [`KotlinFacetValidatorCreator`](%gh-ic%/plugins/kotlin/base/compiler-configuration-ui/src/org/jetbrains/kotlin/idea/base/compilerPreferences/facet/KotlinFacetValidatorCreator.kt) |
| [org.jetbrains.kotlin.failedToDownloadJpsMavenArtifactSuggestedSolutionsContributor](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.failedToDownloadJpsMavenArtifactSuggestedSolutionsContributor) ![Internal][internal] ![Project-Level][project-level] | [`FailedToDownloadJpsMavenArtifactSuggestedSolutionsContributor`](%gh-ic%/plugins/kotlin/base/plugin/src/org/jetbrains/kotlin/idea/compiler/configuration/FailedToDownloadJpsMavenArtifactSuggestedSolutionsContributor.kt) |
| [org.jetbrains.kotlin.firCompilerPluginConfigurationProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.firCompilerPluginConfigurationProvider) ![Experimental][experimental] | [`KotlinFirCompilerPluginConfigurationForIdeProvider`](%gh-ic%/plugins/kotlin/bundled-compiler-plugins-support/src/org/jetbrains/kotlin/idea/fir/extensions/KotlinFirCompilerPluginConfigurationForIdeProvider.kt) |
| [org.jetbrains.kotlin.idea.base.platforms.targetPlatformDetector](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.base.platforms.targetPlatformDetector) ![Project-Level][project-level] | [`TargetPlatformDetector`](%gh-ic%/plugins/kotlin/base/facet/src/org/jetbrains/kotlin/idea/base/facet/platform/TargetPlatformDetector.kt) |
| [org.jetbrains.kotlin.idea.base.projectStructure.moduleInfoProviderExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.base.projectStructure.moduleInfoProviderExtension) ![Project-Level][project-level] | [`ModuleInfoProviderExtension`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/ModuleInfoProvider.kt) |
| [org.jetbrains.kotlin.idea.codeInsight.unambiguousImports](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.codeInsight.unambiguousImports) | [`KotlinAutoImportsFilter`](%gh-ic%/plugins/kotlin/frontend-independent/src/org/jetbrains/kotlin/idea/codeInsight/KotlinAutoImportsFilter.kt) |
| [org.jetbrains.kotlin.idea.testFrameworkProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.testFrameworkProvider) | [`KotlinTestFrameworkProvider`](%gh-ic%/plugins/kotlin/run-configurations/jvm/src/org/jetbrains/kotlin/idea/extensions/KotlinTestFrameworkProvider.kt) |
| [org.jetbrains.kotlin.j2kConverterExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.j2kConverterExtension) | [`J2kConverterExtension`](%gh-ic%/plugins/kotlin/j2k/shared/src/org/jetbrains/kotlin/j2k/J2kConverterExtension.kt) |
| [org.jetbrains.kotlin.j2kPostprocessorExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.j2kPostprocessorExtension) | [`J2kPostprocessorExtension`](%gh-ic%/plugins/kotlin/j2k/shared/src/org/jetbrains/kotlin/j2k/preAndPostprocessorExtension.kt) |
| [org.jetbrains.kotlin.j2kPreprocessorExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.j2kPreprocessorExtension) | [`J2kPreprocessorExtension`](%gh-ic%/plugins/kotlin/j2k/shared/src/org/jetbrains/kotlin/j2k/preAndPostprocessorExtension.kt) |
| [org.jetbrains.kotlin.kotlinInjectedFilesAnalysisProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.kotlinInjectedFilesAnalysisProvider) ![Internal][internal] | [`KotlinIdeInjectedFilesAnalysisPromoter`](%gh-ic%/plugins/kotlin/base/analysis/src/org/jetbrains/kotlin/idea/base/analysis/KotlinIdeInjectedFilesAnalysisPromoter.kt) |
| [org.jetbrains.kotlin.ktModuleFactory](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.ktModuleFactory) ![Internal][internal] | [`KaModuleFactory`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/ProjectStructureProviderIdeImpl.kt) |
| [org.jetbrains.kotlin.libraryVersionProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.libraryVersionProvider) ![Internal][internal] | [`KotlinLibraryVersionProvider`](%gh-ic%/plugins/kotlin/project-configuration/src/org/jetbrains/kotlin/idea/configuration/KotlinLibraryVersionProvider.kt) |
| [org.jetbrains.kotlin.newFileHook](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.newFileHook) ![Internal][internal] | [`NewKotlinFileHook`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/NewKotlinFileHook.kt) |
| [org.jetbrains.kotlin.pluginUpdateVerifier](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.pluginUpdateVerifier) | [`PluginUpdateVerifier`](%gh-ic%/plugins/kotlin/plugin-updater/src/org/jetbrains/kotlin/idea/update/PluginUpdateVerifier.kt) |
| [org.jetbrains.kotlin.projectConfigurator](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.projectConfigurator) | [`KotlinProjectConfigurator`](%gh-ic%/plugins/kotlin/project-configuration/src/org/jetbrains/kotlin/idea/configuration/KotlinProjectConfigurator.kt) |
| [org.jetbrains.kotlin.projectStructureInsightsProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.projectStructureInsightsProvider) ![Internal][internal] | [`ProjectStructureInsightsProvider`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/ProjectStructureProviderIdeImpl.kt) |
| [org.jetbrains.kotlin.smartEnterProcessorFixer](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.smartEnterProcessorFixer) | [`Fixer`](%gh-ic%/platform/lang-impl/src/com/intellij/lang/SmartEnterProcessorWithFixers.java) |
| [org.jetbrains.kotlin.supportAvailability](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.supportAvailability) | [`KotlinSupportAvailability`](%gh-ic%/plugins/kotlin/code-insight/utils/src/org/jetbrains/kotlin/idea/codeinsight/utils/KotlinSupportAvailability.kt) |

### kotlin.base.code-insight.minimal.xml

[`kotlin.base.code-insight.minimal.xml`](%gh-ic%/plugins/kotlin/base/code-insight/minimal/resource/kotlin.base.code-insight.minimal.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.smartEnterProcessorFixer](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.smartEnterProcessorFixer) | [`Fixer`](%gh-ic%/platform/lang-impl/src/com/intellij/lang/SmartEnterProcessorWithFixers.java) |

### kotlin.base.external-build-system.xml

[`kotlin.base.external-build-system.xml`](%gh-ic%/plugins/kotlin/base/external-build-system/resources/kotlin.base.external-build-system.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.idea.base.externalSystem.kotlinBuildSystemFacade](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.base.externalSystem.kotlinBuildSystemFacade) | [`KotlinBuildSystemFacade`](%gh-ic%/plugins/kotlin/base/external-build-system/src/org/jetbrains/kotlin/idea/base/externalSystem/KotlinBuildSystemFacade.kt) |

### kotlin.base.fir.project-structure.xml

[`kotlin.base.fir.project-structure.xml`](%gh-ic%/plugins/kotlin/base/fir/project-structure/resources/kotlin.base.fir.project-structure.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.k2KaModuleFactory](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.k2KaModuleFactory) ![Internal][internal] | [`K2KaModuleFactory`](%gh-ic%/plugins/kotlin/base/fir/project-structure/src/org/jetbrains/kotlin/idea/base/fir/projectStructure/K2KaModuleFactory.kt) |

### kotlin.gradle.code-insight-common.xml

[`kotlin.gradle.code-insight-common.xml`](%gh-ic%/plugins/kotlin/gradle/code-insight-common/resources/kotlin.gradle.code-insight-common.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.idea.gradleBuildScriptSupport](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.gradleBuildScriptSupport) | [`GradleBuildScriptSupport`](%gh-ic%/plugins/kotlin/gradle/code-insight-common/src/org/jetbrains/kotlin/idea/gradleCodeInsightCommon/GradleBuildScriptSupport.kt) |

### kotlin.gradle.gradle-java.xml

[`kotlin.gradle.gradle-java.xml`](%gh-ic%/plugins/kotlin/gradle/gradle-java/resources/kotlin.gradle.gradle-java.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.gradleProjectImportHandler](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.gradleProjectImportHandler) ![Project-Level][project-level] | [`GradleProjectImportHandler`](%gh-ic%/plugins/kotlin/gradle/gradle-java/src/org/jetbrains/kotlin/idea/gradleJava/configuration/KotlinGradleSourceSetDataService.kt) |
| [org.jetbrains.kotlin.idea.gradleJava.kotlinMultiplatformProducersProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.gradleJava.kotlinMultiplatformProducersProvider) | [`KotlinMultiplatformCommonProducersProvider`](%gh-ic%/plugins/kotlin/gradle/gradle-java/src/org/jetbrains/kotlin/idea/gradleJava/extensions/KotlinMultiplatformCommonProducersProvider.kt) |
| [org.jetbrains.kotlin.mppProjectResolve](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.mppProjectResolve) | [`KotlinMppGradleProjectResolverExtension`](%gh-ic%/plugins/kotlin/gradle/gradle-java/src/org/jetbrains/kotlin/idea/gradleJava/configuration/mpp/KotlinMppGradleProjectResolverExtension.kt) |

### kotlin.gradle.gradle-tooling.xml

[`kotlin.gradle.gradle-tooling.xml`](%gh-ic%/plugins/kotlin/gradle/gradle-tooling/resources/kotlin.gradle.gradle-tooling.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.idea.gradleTooling.serialization.IdeaKotlinSerializationContext](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.gradleTooling.serialization.IdeaKotlinSerializationContext) | `IdeaKotlinSerializationContext` |

### kotlin.gradle.gradle.xml

[`kotlin.gradle.gradle.xml`](%gh-ic%/plugins/kotlin/gradle/gradle/resources/kotlin.gradle.gradle.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.gradleModelFacade](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.gradleModelFacade) | [`KotlinGradleModelFacade`](%gh-ic%/plugins/kotlin/gradle/gradle/src/org/jetbrains/kotlin/idea/gradle/inspections/KotlinGradleModelFacade.java) |
| [org.jetbrains.kotlin.idea.extrasSerialization](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.extrasSerialization) | [`KotlinExtrasSerializationService`](%gh-ic%/plugins/kotlin/gradle/gradle/src/org/jetbrains/kotlin/idea/gradle/configuration/serialize/KotlinExtrasSerializationService.kt) |
| [org.jetbrains.kotlin.kpm.createRoots](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.kpm.createRoots) | [`ContentRootsCreator`](%gh-ic%/plugins/kotlin/gradle/gradle/src/org/jetbrains/kotlin/idea/gradle/configuration/kpm/ContentRootsCreator.kt) |
| [org.jetbrains.kotlin.kpm.moduleInitialize](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.kpm.moduleInitialize) | [`ModuleDataInitializer`](%gh-ic%/plugins/kotlin/gradle/gradle/src/org/jetbrains/kotlin/idea/gradle/configuration/kpm/ModuleDataInitializer.kt) |

### kotlin.highlighting.k2.xml

[`kotlin.highlighting.k2.xml`](%gh-ic%/plugins/kotlin/highlighting/highlighting-k2/resources/kotlin.highlighting.k2.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.callHighlighterExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.callHighlighterExtension) | [`KotlinCallHighlighterExtension`](%gh-ic%/plugins/kotlin/highlighting/highlighting-k2/src/org/jetbrains/kotlin/idea/highlighting/KotlinCallHighlighterExtension.kt) |

### kotlin.highlighting.shared.xml

[`kotlin.highlighting.shared.xml`](%gh-ic%/plugins/kotlin/highlighting/highlighting-shared/resources/kotlin.highlighting.shared.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.beforeResolveHighlightingVisitor](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.beforeResolveHighlightingVisitor) ![Internal][internal] | [`BeforeResolveHighlightingExtension`](%gh-ic%/plugins/kotlin/highlighting/highlighting-minimal/src/org/jetbrains/kotlin/idea/base/highlighting/BeforeResolveHighlightingExtension.kt) |

### kotlin.maven.xml

[`kotlin.maven.xml`](%gh-ic%/plugins/kotlin/maven/resources/kotlin.maven.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.mavenProjectImportHandler](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.mavenProjectImportHandler) ![Project-Level][project-level] | [`MavenProjectImportHandler`](%gh-ic%/plugins/kotlin/maven/src/org/jetbrains/kotlin/idea/maven/KotlinMavenImporter.kt) |

### kotlin.plugin.k2.xml

[`kotlin.plugin.k2.xml`](%gh-ic%/plugins/kotlin/plugin/k2/resources/kotlin.plugin.k2.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.resolveScopeEnlarger](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.resolveScopeEnlarger) ![Non-Dynamic][non-dynamic] | [`KotlinResolveScopeEnlarger`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/KotlinResolveScopeEnlarger.kt) |

### kotlin.project-wizard.idea.xml

[`kotlin.project-wizard.idea.xml`](%gh-ic%/plugins/kotlin/project-wizard/idea/resources/kotlin.project-wizard.idea.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.newProjectWizard.kotlin.buildSystem](https://jb.gg/ipe?extensions=com.intellij.newProjectWizard.kotlin.buildSystem) | [`BuildSystemKotlinNewProjectWizard`](%gh-ic%/plugins/kotlin/project-wizard/idea/src/org/jetbrains/kotlin/tools/projectWizard/BuildSystemKotlinNewProjectWizard.kt) |
| [org.jetbrains.kotlin.idea.androidSdkProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.androidSdkProvider) | [`AndroidSdkProvider`](%gh-ic%/plugins/kotlin/project-wizard/core/src/org/jetbrains/kotlin/tools/projectWizard/plugins/AndroidSdkProvider.kt) |
| [org.jetbrains.kotlin.idea.ideaWizardService](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.ideaWizardService) ![Project-Level][project-level] | [`IdeaWizardService`](%gh-ic%/plugins/kotlin/project-wizard/idea/src/org/jetbrains/kotlin/tools/projectWizard/wizard/service/IdeaWizardService.kt) |
| [org.jetbrains.kotlin.idea.projectTemplatesProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.projectTemplatesProvider) | [`ProjectTemplatesProvider`](%gh-ic%/plugins/kotlin/project-wizard/core/src/org/jetbrains/kotlin/tools/projectWizard/plugins/projectTemplates/ProjectTemplatesProvider.kt) |

### kotlin.refactorings.k2.xml

[`kotlin.refactorings.k2.xml`](%gh-ic%/plugins/kotlin/refactorings/kotlin.refactorings.k2/resources/kotlin.refactorings.k2.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.changeSignatureConflictFilter](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.changeSignatureConflictFilter) | [`KotlinChangeSignatureConflictFilter`](%gh-ic%/plugins/kotlin/refactorings/kotlin.refactorings.k2/src/org/jetbrains/kotlin/idea/k2/refactoring/changeSignature/KotlinChangeSignatureConflictFilter.kt) |
| [org.jetbrains.kotlin.extractFunctionDescriptorModifier](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.extractFunctionDescriptorModifier) | [`ExtractFunctionDescriptorModifier`](%gh-ic%/plugins/kotlin/refactorings/kotlin.refactorings.k2/src/org/jetbrains/kotlin/idea/k2/refactoring/extractFunction/ExtractFunctionDescriptorModifier.kt) |

### kotlin.searching.k2.xml

[`kotlin.searching.k2.xml`](%gh-ic%/plugins/kotlin/kotlin.searching/resources/kotlin.searching.k2.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.directKotlinClassInheritorsSearch](https://jb.gg/ipe?extensions=com.intellij.directKotlinClassInheritorsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |

### kotlinx-serialization.xml

[`kotlinx-serialization.xml`](%gh-ic%/plugins/kotlin/compiler-plugins/kotlinx-serialization/common/resources/META-INF/kotlinx-serialization.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.idea.compilerPlugin.kotlinxSerialization.kotlinSerializationEnabledChecker](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.compilerPlugin.kotlinxSerialization.kotlinSerializationEnabledChecker) | [`KotlinSerializationEnabledChecker`](%gh-ic%/plugins/kotlin/compiler-plugins/kotlinx-serialization/common/src/org/jetbrains/kotlin/idea/compilerPlugin/kotlinxSerialization/KotlinSerializationEnabledChecker.kt) |

### light-classes-fe10.xml

[`light-classes-fe10.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/light-classes-fe10.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.ultraLightClassModifierExtension](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.ultraLightClassModifierExtension) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `UltraLightClassModifierExtension` |

### org.jetbrains.kotlin

[`org.jetbrains.kotlin`](%gh-ic%/plugins/kotlin/plugin/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.supportsKotlinPluginMode](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.supportsKotlinPluginMode) | `n/a` |

### parcelize.xml

[`parcelize.xml`](%gh-ic%/plugins/kotlin/compiler-plugins/parcelize/common/resources/META-INF/parcelize.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.idea.compilerPlugin.parcelize.availabilityProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.idea.compilerPlugin.parcelize.availabilityProvider) ![Project-Level][project-level] | [`ParcelizeAvailabilityProvider`](%gh-ic%/plugins/kotlin/compiler-plugins/parcelize/common/src/org/jetbrains/kotlin/idea/compilerPlugin/parcelize/ParcelizeAvailability.kt) |

### refactorings-fe10.xml

[`refactorings-fe10.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/refactorings-fe10.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.additionalExtractableAnalyser](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.additionalExtractableAnalyser) | [`AdditionalExtractableAnalyser`](%gh-ic%/plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/refactoring/introduce/extractionEngine/AdditionalExtractableAnalyser.kt) |

### refactorings.xml

[`refactorings.xml`](%gh-ic%/plugins/kotlin/plugin/common/resources/META-INF/refactorings.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.foreignUsagesRenameProcessor](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.foreignUsagesRenameProcessor) | [`ForeignUsagesRenameProcessor`](%gh-ic%/plugins/kotlin/refactorings/kotlin.refactorings.common/src/org/jetbrains/kotlin/idea/refactoring/rename/ForeignUsagesRenameProcessor.kt) |
| [org.jetbrains.kotlin.postInsertDeclarationCallback](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.postInsertDeclarationCallback) | [`PostInsertDeclarationCallback`](%gh-ic%/plugins/kotlin/refactorings/kotlin.refactorings.common/src/org/jetbrains/kotlin/idea/refactoring/introduce/extractionEngine/PostInsertDeclarationCallback.kt) |
| [org.jetbrains.kotlin.renameHandler](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.renameHandler) | [`RenameHandler`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/RenameHandler.java) |

### resolution-fe10.xml

[`resolution-fe10.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/resolution-fe10.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.resolveScopeEnlarger](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.resolveScopeEnlarger) | [`KotlinResolveScopeEnlarger`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/KotlinResolveScopeEnlarger.kt) |

### scripting-base.xml

[`scripting-base.xml`](%gh-ic%/plugins/kotlin/plugin/common/resources/META-INF/scripting-base.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.ideScriptConfigurationControlFacade](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.ideScriptConfigurationControlFacade) ![Project-Level][project-level] | [`IdeScriptConfigurationControlFacade`](%gh-ic%/plugins/kotlin/scripting/src/kotlin/script/experimental/intellij/scriptConfigurationTools.kt) |
| [org.jetbrains.kotlin.scriptAdditionalIdeaDependenciesProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scriptAdditionalIdeaDependenciesProvider) ![Project-Level][project-level] | [`ScriptAdditionalIdeaDependenciesProvider`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/dependencies/ScriptAdditionalIdeaDependenciesProvider.kt) |
| [org.jetbrains.kotlin.scriptDiagnosticFixProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scriptDiagnosticFixProvider) | [`ScriptDiagnosticFixProvider`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/script/ScriptDiagnosticFixProvider.kt) |
| [org.jetbrains.kotlin.scripting.idea.listener](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scripting.idea.listener) ![Project-Level][project-level] | [`ScriptChangeListener`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/configuration/listener/ScriptChangeListener.kt) |
| [org.jetbrains.kotlin.scripting.idea.loader](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scripting.idea.loader) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ScriptConfigurationLoader`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/configuration/loader/ScriptConfigurationLoader.kt) |
| [org.jetbrains.kotlin.scripting.idea.scriptingSupport](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scripting.idea.scriptingSupport) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ScriptingSupport`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/configuration/ScriptingSupport.kt) |
| [org.jetbrains.kotlin.scripting.idea.settings.provider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scripting.idea.settings.provider) ![Project-Level][project-level] | [`ScriptingSupportSpecificSettingsProvider`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/script/configuration/ScriptingSupportSpecificSettingsProvider.kt) |

### scripting-k2.xml

[`scripting-k2.xml`](%gh-ic%/plugins/kotlin/plugin/k2/resources/META-INF/scripting-k2.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.ideScriptConfigurationControlFacade](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.ideScriptConfigurationControlFacade) ![Project-Level][project-level] | [`IdeScriptConfigurationControlFacade`](%gh-ic%/plugins/kotlin/scripting/src/kotlin/script/experimental/intellij/scriptConfigurationTools.kt) |
| [org.jetbrains.kotlin.kotlinScriptLazyResolveProhibitionCondition](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.kotlinScriptLazyResolveProhibitionCondition) ![Project-Level][project-level] | [`KotlinScriptLazyResolveProhibitionCondition`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/k2/KotlinScriptLazyResolveProhibitionCondition.kt) |
| [org.jetbrains.kotlin.scratchFileLanguageProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scratchFileLanguageProvider) | [`ScratchFileLanguageProvider`](%gh-ic%/plugins/kotlin/jvm/src/org/jetbrains/kotlin/idea/scratch/ScratchFileLanguageProvider.kt) |
| [org.jetbrains.kotlin.scriptAdditionalIdeaDependenciesProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scriptAdditionalIdeaDependenciesProvider) ![Project-Level][project-level] | [`ScriptAdditionalIdeaDependenciesProvider`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/dependencies/ScriptAdditionalIdeaDependenciesProvider.kt) |
| [org.jetbrains.kotlin.scriptConfigurationsSource](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scriptConfigurationsSource) ![Project-Level][project-level] | [`ScriptConfigurationsSource`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/k2/ScriptConfigurationsSource.kt) |
| [org.jetbrains.kotlin.scriptDefinitionsSource](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scriptDefinitionsSource) ![Project-Level][project-level] | `ScriptDefinitionsSource` |
| [org.jetbrains.kotlin.scriptDiagnosticFixProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scriptDiagnosticFixProvider) | [`ScriptDiagnosticFixProvider`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/script/ScriptDiagnosticFixProvider.kt) |
| [org.jetbrains.kotlin.scripting.definitions.scriptDefinitionProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scripting.definitions.scriptDefinitionProvider) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `ScriptDefinitionProvider` |
| [org.jetbrains.kotlin.scripting.idea.listener](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scripting.idea.listener) ![Project-Level][project-level] | [`ScriptChangeListener`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/configuration/listener/ScriptChangeListener.kt) |
| [org.jetbrains.kotlin.scripting.idea.loader](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scripting.idea.loader) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ScriptConfigurationLoader`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/configuration/loader/ScriptConfigurationLoader.kt) |
| [org.jetbrains.kotlin.scripting.idea.scriptingSupport](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scripting.idea.scriptingSupport) ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ScriptingSupport`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/configuration/ScriptingSupport.kt) |
| [org.jetbrains.kotlin.scripting.idea.settings.provider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scripting.idea.settings.provider) ![Project-Level][project-level] | [`ScriptingSupportSpecificSettingsProvider`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/script/configuration/ScriptingSupportSpecificSettingsProvider.kt) |

### scripting-support.xml

[`scripting-support.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/scripting-support.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.kotlin.scratchFileLanguageProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scratchFileLanguageProvider) | [`ScratchFileLanguageProvider`](%gh-ic%/plugins/kotlin/jvm/src/org/jetbrains/kotlin/idea/scratch/ScratchFileLanguageProvider.kt) |
| [org.jetbrains.kotlin.scriptDefinitionsProvider](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scriptDefinitionsProvider) ![Project-Level][project-level] | [`ScriptDefinitionsProvider`](%gh-ic%/plugins/kotlin/scripting/src/kotlin/script/experimental/intellij/scriptDefinitionProvider.kt) |
| [org.jetbrains.kotlin.scriptDefinitionsSource](https://jb.gg/ipe?extensions=org.jetbrains.kotlin.scriptDefinitionsSource) ![Project-Level][project-level] | `ScriptDefinitionsSource` |



## Markdown Plugin


### Markdown Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [ChangeListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.intellij.plugins.markdown.settings.MarkdownExtensionsSettings.ChangeListener)  ![Experimental][experimental] | [`ChangeListener`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/settings/MarkdownExtensionsSettings.kt) |
| [ChangeListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.intellij.plugins.markdown.settings.MarkdownPreviewSettings.ChangeListener)  | [`ChangeListener`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/settings/MarkdownPreviewSettings.kt) |
| [ChangeListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.intellij.plugins.markdown.settings.MarkdownSettings.ChangeListener)  ![Project-Level][project-level] | [`ChangeListener`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/settings/MarkdownSettings.kt) |


### intellij.markdown.fenceInjection.xml

[`intellij.markdown.fenceInjection.xml`](%gh-ic%/plugins/markdown/fenceInjection/src/main/resources/intellij.markdown.fenceInjection.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.intellij.plugins.markdown.fenceInjection.fenceSurroundingsProvider](https://jb.gg/ipe?extensions=org.intellij.plugins.markdown.fenceInjection.fenceSurroundingsProvider) ![Internal][internal] | [`FenceSurroundingsProvider`](%gh-ic%/plugins/markdown/fenceInjection/src/main/java/org/intellij/plugins/markdown/fenceInjection/FenceSurroundingsProvider.kt) |

### org.intellij.plugins.markdown

[`org.intellij.plugins.markdown`](%gh-ic%/plugins/markdown/core/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.intellij.markdown.additionalFenceLanguageSuggester](https://jb.gg/ipe?extensions=org.intellij.markdown.additionalFenceLanguageSuggester) ![Internal][internal] | [`AdditionalFenceLanguageSuggester`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/injection/aliases/AdditionalFenceLanguageSuggester.kt) |
| [org.intellij.markdown.browserPreviewExtensionProvider](https://jb.gg/ipe?extensions=org.intellij.markdown.browserPreviewExtensionProvider) | [`Provider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/extensions/MarkdownBrowserPreviewExtension.kt) |
| [org.intellij.markdown.fenceGeneratingProvider](https://jb.gg/ipe?extensions=org.intellij.markdown.fenceGeneratingProvider) ![Obsolete][obsolete] ![Internal][internal] | [`CodeFenceGeneratingProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/extensions/CodeFenceGeneratingProvider.kt) |
| [org.intellij.markdown.fenceLanguageProvider](https://jb.gg/ipe?extensions=org.intellij.markdown.fenceLanguageProvider) | [`CodeFenceLanguageProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/injection/CodeFenceLanguageProvider.java) |
| [org.intellij.markdown.flavourProvider](https://jb.gg/ipe?extensions=org.intellij.markdown.flavourProvider) ![Experimental][experimental] | [`MarkdownFlavourProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/lang/parser/MarkdownFlavourProvider.kt) |
| [org.intellij.markdown.html.panel.provider](https://jb.gg/ipe?extensions=org.intellij.markdown.html.panel.provider) | [`MarkdownHtmlPanelProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/ui/preview/MarkdownHtmlPanelProvider.java) |
| [org.intellij.markdown.markdownCompatibilityChecker](https://jb.gg/ipe?extensions=org.intellij.markdown.markdownCompatibilityChecker) | [`MarkdownCompatibilityChecker`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/lang/MarkdownCompatibilityChecker.kt) |
| [org.intellij.markdown.markdownExportProvider](https://jb.gg/ipe?extensions=org.intellij.markdown.markdownExportProvider) ![Experimental][experimental] | [`MarkdownExportProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/fileActions/export/MarkdownExportProvider.kt) |
| [org.intellij.markdown.markdownRunner](https://jb.gg/ipe?extensions=org.intellij.markdown.markdownRunner) | [`MarkdownRunner`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/extensions/jcef/commandRunner/MarkdownRunner.kt) |
| [org.intellij.markdown.previewStylesProvider](https://jb.gg/ipe?extensions=org.intellij.markdown.previewStylesProvider) ![Internal][internal] | [`MarkdownPreviewStylesProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/ui/preview/MarkdownPreviewStylesProvider.kt) |



## Maven Plugin


### Maven Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [MavenSystemIndicesManager#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.maven.indices.IndexChangeProgressListener)  | [`IndexChangeProgressListener`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/indices/MavenSystemIndicesManager.kt) |
| [MavenIndicesManager#INDEXER_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.maven.indices.MavenIndicesManager.MavenIndexerListener)  | [`MavenIndexerListener`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/indices/MavenIndicesManager.kt) |
| [MavenSearchIndex#INDEX_IS_BROKEN](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.maven.indices.MavenSearchIndex.IndexListener)  | [`IndexListener`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/indices/MavenSearchIndex.java) |
| [MavenImportListener#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.maven.project.MavenImportListener)  ![Project-Level][project-level] | [`MavenImportListener`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/project/MavenImportListener.java) |
| [MavenSyncListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.maven.project.MavenSyncListener)  | [`MavenSyncListener`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/project/MavenSyncListener.kt) |
| [MavenServerConnector#DOWNLOAD_LISTENER_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.maven.server.MavenServerDownloadListener)  | [`MavenServerDownloadListener`](%gh-ic%/plugins/maven-server-api/src/main/java/org/jetbrains/idea/maven/server/MavenServerDownloadListener.java) |


### groovy-support.xml

[`groovy-support.xml`](%gh-ic%/plugins/maven/src/main/resources/META-INF/groovy-support.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.idea.maven.importing.groovy.foldersConfiguratorContributor](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.importing.groovy.foldersConfiguratorContributor) | [`PluginContributor`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/plugins/groovy/GroovyPluginConfigurator.kt) |

### org.jetbrains.idea.maven

[`org.jetbrains.idea.maven`](%gh-ic%/plugins/maven/src/main/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.idea.maven.additional.importing.settings](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.additional.importing.settings) | [`AdditionalMavenImportingSettings`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/project/AdditionalMavenImportingSettings.java) |
| [org.jetbrains.idea.maven.archetypesProvider](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.archetypesProvider) | [`MavenArchetypesProvider`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/indices/MavenArchetypesProvider.java) |
| [org.jetbrains.idea.maven.artifactBuilder](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.artifactBuilder) | [`MavenArtifactBuilder`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/execution/build/MavenArtifactBuilder.java) |
| [org.jetbrains.idea.maven.compiler](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.compiler) ![Internal][internal] | [`MavenCompilerExtension`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/importing/MavenCompilerExtension.kt) |
| [org.jetbrains.idea.maven.executionEnvironmentProvider](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.executionEnvironmentProvider) | [`MavenExecutionEnvironmentProvider`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/execution/build/MavenExecutionEnvironmentProvider.java) |
| [org.jetbrains.idea.maven.importer](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.importer) ![Deprecated][deprecated] | [`MavenImporter`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/importing/MavenImporter.java) |
| [org.jetbrains.idea.maven.importing.afterImportConfigurator](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.importing.afterImportConfigurator) ![Experimental][experimental] | [`MavenAfterImportConfigurator`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/importing/MavenConfigurators.kt) |
| [org.jetbrains.idea.maven.importing.workspaceConfigurator](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.importing.workspaceConfigurator) ![Experimental][experimental] | [`MavenWorkspaceConfigurator`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/importing/MavenConfigurators.kt) |
| [org.jetbrains.idea.maven.log.import.parser](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.log.import.parser) ![Experimental][experimental] | [`MavenImportLoggedEventParser`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/externalSystemIntegration/output/importproject/MavenImportLoggedEventParser.java) |
| [org.jetbrains.idea.maven.log.parser](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.log.parser) ![Experimental][experimental] | [`MavenLoggedEventParser`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/externalSystemIntegration/output/MavenLoggedEventParser.java) |
| [org.jetbrains.idea.maven.log.spy.parser](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.log.spy.parser) ![Experimental][experimental] | [`MavenSpyLoggedEventParser`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/externalSystemIntegration/output/MavenSpyLoggedEventParser.java) |
| [org.jetbrains.idea.maven.manifestImporter](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.manifestImporter) | [`ManifestImporter`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/importing/ManifestImporter.java) |
| [org.jetbrains.idea.maven.mavenAdditionalHighlighter](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.mavenAdditionalHighlighter) ![Experimental][experimental] | [`MavenAdditionalHightligher`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/dom/MavenAdditionalHightligher.kt) |
| [org.jetbrains.idea.maven.mavenRemoteConnectionCreator](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.mavenRemoteConnectionCreator) | [`MavenRemoteConnectionCreator`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/execution/MavenRemoteConnectionCreator.kt) |
| [org.jetbrains.idea.maven.mavenServerSupportFactory](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.mavenServerSupportFactory) ![Internal][internal] | [`MavenRemoteProcessSupportFactory`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/server/MavenRemoteProcessSupportFactory.java) |
| [org.jetbrains.idea.maven.pluginDescriptor](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.pluginDescriptor) ![Internal][internal] | `n/a` |
| [org.jetbrains.idea.maven.projectResolutionContributor](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.projectResolutionContributor) ![Internal][internal] | [`MavenProjectResolutionContributor`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/project/MavenProjectResolver.kt) |
| [org.jetbrains.idea.maven.remotePathTransformerFactory](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.remotePathTransformerFactory) | [`RemotePathTransformerFactory`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/server/RemotePathTransformerFactory.java) |
| [org.jetbrains.idea.maven.repositoryProvider](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.repositoryProvider) | [`MavenRepositoryProvider`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/indices/MavenRepositoryProvider.java) |
| [org.jetbrains.idea.maven.targetConfigurationExtension](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.targetConfigurationExtension) ![Experimental][experimental] | [`TargetConfigurationMavenExtension`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/execution/target/TargetConfigurationMavenExtension.java) |
| [org.jetbrains.idea.maven.versionAwareMavenSupport](https://jb.gg/ipe?extensions=org.jetbrains.idea.maven.versionAwareMavenSupport) ![Internal][internal] | [`MavenVersionAwareSupportExtension`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/MavenVersionAwareSupportExtension.kt) |



## Python Plugin


### Python Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [HuggingFaceCacheUpdateListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.intellij.python.community.impl.huggingFace.cache.HuggingFaceCacheUpdateListener)  | [`HuggingFaceCacheUpdateListener`](%gh-ic%/python/huggingFace/src/com/intellij/python/community/impl/huggingFace/cache/HuggingFaceCacheUpdateListener.kt) |
| [PyFrameListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.python.debugger.PyFrameListener)  | [`PyFrameListener`](%gh-ic%/python/pydevSrc/src/com/jetbrains/python/debugger/PyFrameListener.kt) |
| [PyStackFrame#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.python.debugger.PyStackFrame.PyStackFrameRefreshedListener)  | [`PyStackFrameRefreshedListener`](%gh-ic%/python/src/com/jetbrains/python/debugger/PyStackFrame.java) |
| [PyPackageManager#PACKAGE_MANAGER_TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.python.packaging.PyPackageManager.Listener)  | [`Listener`](%gh-ic%/python/openapi/src/com/jetbrains/python/packaging/PyPackageManager.java) |
| [PythonPackageManager#PACKAGE_MANAGEMENT_TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.python.packaging.common.PythonPackageManagementListener)  ![Experimental][experimental] | [`PythonPackageManagementListener`](%gh-ic%/python/src/com/jetbrains/python/packaging/common/util.kt) |
| [PySdkListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=com.jetbrains.python.sdk.PySdkListener)  ![Internal][internal] | [`PySdkListener`](%gh-ic%/python/src/com/jetbrains/python/sdk/PySdkListener.kt) |


### intellij.pycharm.community.ide.impl.xml

[`intellij.pycharm.community.ide.impl.xml`](%gh-ic%/python/ide/impl/resources/intellij.pycharm.community.ide.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Pythonid.miscFileType](https://jb.gg/ipe?extensions=Pythonid.miscFileType) | [`MiscFileType`](%gh-ic%/python/ide/impl/src/com/intellij/pycharm/community/ide/impl/miscProject/MiscFileType.kt) |

### intellij.python.parser.xml

[`intellij.python.parser.xml`](%gh-ic%/python/python-parser/resources/intellij.python.parser.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Pythonid.dialectsTokenSetContributor](https://jb.gg/ipe?extensions=Pythonid.dialectsTokenSetContributor) | [`PythonDialectsTokenSetContributor`](%gh-ic%/python/python-parser/src/com/jetbrains/python/PythonDialectsTokenSetContributor.java) |

### intellij.python.syntax.xml

[`intellij.python.syntax.xml`](%gh-ic%/python/python-syntax/resources/intellij.python.syntax.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Pythonid.pyAnnotator](https://jb.gg/ipe?extensions=Pythonid.pyAnnotator) ![Experimental][experimental] | [`PyAnnotatorBase`](%gh-ic%/python/python-syntax-core/src/com/jetbrains/python/validation/PyAnnotatorBase.java) |

### PythonCore

[`PythonCore`](%gh-ic%/python/pluginCore/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Pythonid.PythonPackagingToolwindowActionProvider](https://jb.gg/ipe?extensions=Pythonid.PythonPackagingToolwindowActionProvider) | [`PythonPackagingToolwindowActionProvider`](%gh-ic%/python/src/com/jetbrains/python/packaging/toolwindow/actions/PythonPackagingToolwindowActionProvider.kt) |
| [Pythonid.breakpointHandler](https://jb.gg/ipe?extensions=Pythonid.breakpointHandler) | [`PyBreakpointHandlerFactory`](%gh-ic%/python/src/com/jetbrains/python/debugger/PyBreakpointHandlerFactory.java) |
| [Pythonid.condaSdkCustomizer](https://jb.gg/ipe?extensions=Pythonid.condaSdkCustomizer) ![Internal][internal] | [`PyCondaSdkCustomizer`](%gh-ic%/python/src/com/jetbrains/python/sdk/conda/PyCondaSdkCustomizer.kt) |
| [Pythonid.connectionCredentialsToTargetConfigurationConverter](https://jb.gg/ipe?extensions=Pythonid.connectionCredentialsToTargetConfigurationConverter) ![Internal][internal] | [`ConnectionCredentialsToTargetConfigurationConverter`](%gh-ic%/python/src/com/jetbrains/python/run/target/ConnectionCredentialsToTargetConfigurationConverter.kt) |
| [Pythonid.consoleOptionsProvider](https://jb.gg/ipe?extensions=Pythonid.consoleOptionsProvider) | [`PyConsoleOptionsProvider`](%gh-ic%/python/src/com/jetbrains/python/console/PyConsoleOptionsProvider.java) |
| [Pythonid.customProcessHandlerProvider](https://jb.gg/ipe?extensions=Pythonid.customProcessHandlerProvider) ![Internal][internal] | [`PyCustomProcessHandlerProvider`](%gh-ic%/python/src/com/jetbrains/python/run/PyCustomProcessHandlerProvider.kt) |
| [Pythonid.dataViewPanelFactory](https://jb.gg/ipe?extensions=Pythonid.dataViewPanelFactory) | [`PyDataViewPanelFactory`](%gh-ic%/python/src/com/jetbrains/python/debugger/containerview/PyDataViewPanelFactory.java) |
| [Pythonid.debugSessionFactory](https://jb.gg/ipe?extensions=Pythonid.debugSessionFactory) | [`PyDebugSessionFactory`](%gh-ic%/python/src/com/jetbrains/python/debugger/PyDebugSessionFactory.java) |
| [Pythonid.documentationLinkProvider](https://jb.gg/ipe?extensions=Pythonid.documentationLinkProvider) | [`PythonDocumentationLinkProvider`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/documentation/PythonDocumentationLinkProvider.java) |
| [Pythonid.interpreterTargetEnvironmentFactory](https://jb.gg/ipe?extensions=Pythonid.interpreterTargetEnvironmentFactory) ![Experimental][experimental] | [`PythonInterpreterTargetEnvironmentFactory`](%gh-ic%/python/src/com/jetbrains/python/run/PythonInterpreterTargetEnvironmentFactory.kt) |
| [Pythonid.magicLiteral](https://jb.gg/ipe?extensions=Pythonid.magicLiteral) ![Internal][internal] | [`PyMagicLiteralExtensionPoint`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/magicLiteral/PyMagicLiteralExtensionPoint.java) |
| [Pythonid.packageManagerProvider](https://jb.gg/ipe?extensions=Pythonid.packageManagerProvider) ![Experimental][experimental] | [`PyPackageManagerProvider`](%gh-ic%/python/src/com/jetbrains/python/packaging/PyCustomPackageManagers.kt) |
| [Pythonid.pep8ProblemSuppressor](https://jb.gg/ipe?extensions=Pythonid.pep8ProblemSuppressor) | [`Pep8ProblemSuppressor`](%gh-ic%/python/src/com/jetbrains/python/validation/Pep8ProblemSuppressor.java) |
| [Pythonid.projectSynchronizerProvider](https://jb.gg/ipe?extensions=Pythonid.projectSynchronizerProvider) ![Internal][internal] | [`PyProjectSynchronizerProvider`](%gh-ic%/python/src/com/jetbrains/python/remote/PyProjectSynchronizer.kt) |
| [Pythonid.pyAddSdkProvider](https://jb.gg/ipe?extensions=Pythonid.pyAddSdkProvider) | [`PyAddSdkProvider`](%gh-ic%/python/src/com/jetbrains/python/sdk/add/PyAddSdkProvider.kt) |
| [Pythonid.pyCustomSdkUiProvider](https://jb.gg/ipe?extensions=Pythonid.pyCustomSdkUiProvider) ![Internal][internal] | [`PyCustomSdkUiProvider`](%gh-ic%/python/src/com/jetbrains/python/sdk/PyCustomSdkUiProvider.java) |
| [Pythonid.pyPregeneratedSkeletonsProvider](https://jb.gg/ipe?extensions=Pythonid.pyPregeneratedSkeletonsProvider) | [`PyPregeneratedSkeletonsProvider`](%gh-ic%/python/src/com/jetbrains/python/sdk/skeletons/PyPregeneratedSkeletonsProvider.java) |
| [Pythonid.pyRootTypeProvider](https://jb.gg/ipe?extensions=Pythonid.pyRootTypeProvider) ![Internal][internal] | [`PyRootTypeProvider`](%gh-ic%/python/src/com/jetbrains/python/module/PyRootTypeProvider.java) |
| [Pythonid.pySdkProvider](https://jb.gg/ipe?extensions=Pythonid.pySdkProvider) ![Experimental][experimental] | [`PySdkProvider`](%gh-ic%/python/src/com/jetbrains/python/sdk/PySdkProvider.kt) |
| [Pythonid.pyTestConfigurationSelector](https://jb.gg/ipe?extensions=Pythonid.pyTestConfigurationSelector) | [`PyTestConfigurationSelector`](%gh-ic%/python/src/com/jetbrains/python/testing/PyTestConfigurationSelector.kt) |
| [Pythonid.pyTestFixtureExtension](https://jb.gg/ipe?extensions=Pythonid.pyTestFixtureExtension) | [`PyTestFixtureExtension`](%gh-ic%/python/src/com/jetbrains/python/testing/pyTestFixtures/PyTestFixtureExtension.kt) |
| [Pythonid.pythonCommandLineEnvironmentProvider](https://jb.gg/ipe?extensions=Pythonid.pythonCommandLineEnvironmentProvider) | [`PythonCommandLineEnvironmentProvider`](%gh-ic%/python/src/com/jetbrains/python/run/PythonCommandLineEnvironmentProvider.java) |
| [Pythonid.pythonCommandLineTargetEnvironmentProvider](https://jb.gg/ipe?extensions=Pythonid.pythonCommandLineTargetEnvironmentProvider) ![Experimental][experimental] ![Internal][internal] | [`PythonCommandLineTargetEnvironmentProvider`](%gh-ic%/python/src/com/jetbrains/python/run/target/PythonCommandLineTargetEnvironmentProvider.kt) |
| [Pythonid.pythonPackageManagerProvider](https://jb.gg/ipe?extensions=Pythonid.pythonPackageManagerProvider) ![Experimental][experimental] | [`PythonPackageManagerProvider`](%gh-ic%/python/src/com/jetbrains/python/packaging/management/PythonPackageManagerService.kt) |
| [Pythonid.pythonSdkComparator](https://jb.gg/ipe?extensions=Pythonid.pythonSdkComparator) ![Internal][internal] | [`PySdkComparator`](%gh-ic%/python/src/com/jetbrains/python/sdk/PySdkComparator.java) |
| [Pythonid.remoteConsoleProcessCreator](https://jb.gg/ipe?extensions=Pythonid.remoteConsoleProcessCreator) | [`PythonConsoleRemoteProcessCreator`](%gh-ic%/python/src/com/jetbrains/python/console/PythonConsoleRemoteProcessCreator.kt) |
| [Pythonid.remoteInterpreterManager](https://jb.gg/ipe?extensions=Pythonid.remoteInterpreterManager) | [`PythonRemoteInterpreterManager`](%gh-ic%/python/src/com/jetbrains/python/remote/PythonRemoteInterpreterManager.java) |
| [Pythonid.remoteProcessStarterManager](https://jb.gg/ipe?extensions=Pythonid.remoteProcessStarterManager) | [`PyRemoteProcessStarterManager`](%gh-ic%/python/src/com/jetbrains/python/run/PyRemoteProcessStarterManager.java) |
| [Pythonid.remoteSdkValidator](https://jb.gg/ipe?extensions=Pythonid.remoteSdkValidator) | [`PyRemoteSdkValidator`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/sdk/PyRemoteSdkValidator.kt) |
| [Pythonid.remoteSkeletonGeneratorFactory](https://jb.gg/ipe?extensions=Pythonid.remoteSkeletonGeneratorFactory) | [`PyRemoteSkeletonGeneratorFactory`](%gh-ic%/python/src/com/jetbrains/python/remote/PyRemoteSkeletonGeneratorFactory.java) |
| [Pythonid.runConfigurationEditorExtension](https://jb.gg/ipe?extensions=Pythonid.runConfigurationEditorExtension) ![Internal][internal] | [`PyRunConfigurationEditorExtension`](%gh-ic%/python/src/com/jetbrains/python/run/PyRunConfigurationEditorExtension.java) |
| [Pythonid.runConfigurationExtension](https://jb.gg/ipe?extensions=Pythonid.runConfigurationExtension) | [`PythonRunConfigurationExtension`](%gh-ic%/python/src/com/jetbrains/python/run/PythonRunConfigurationExtension.java) |
| [Pythonid.runnableScriptFilter](https://jb.gg/ipe?extensions=Pythonid.runnableScriptFilter) | [`RunnableScriptFilter`](%gh-ic%/python/src/com/jetbrains/python/run/RunnableScriptFilter.java) |
| [Pythonid.sshInterpreterManager](https://jb.gg/ipe?extensions=Pythonid.sshInterpreterManager) | [`PythonSshInterpreterManager`](%gh-ic%/python/src/com/jetbrains/python/remote/PythonSshInterpreterManager.java) |
| [Pythonid.systemPythonProvider](https://jb.gg/ipe?extensions=Pythonid.systemPythonProvider) | [`SystemPythonProvider`](%gh-ic%/python/services/system-python/src/com/intellij/python/community/services/systemPython/spi.kt) |
| [Pythonid.unresolvedReferenceQuickFixProvider](https://jb.gg/ipe?extensions=Pythonid.unresolvedReferenceQuickFixProvider) | [`PyUnresolvedReferenceQuickFixProvider`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/inspections/PyUnresolvedReferenceQuickFixProvider.java) |
| [com.jetbrains.python.configuration.pyIntegratedToolsTestPanelCustomizer](https://jb.gg/ipe?extensions=com.jetbrains.python.configuration.pyIntegratedToolsTestPanelCustomizer) | [`PyIntegratedToolsTestPanelCustomizer`](%gh-ic%/python/src/com/jetbrains/python/configuration/PyIntegratedToolsTestPanelCustomizer.kt) |
| [com.jetbrains.python.console.customizer](https://jb.gg/ipe?extensions=com.jetbrains.python.console.customizer) | [`PyConsoleCustomizer`](%gh-ic%/python/src/com/jetbrains/python/console/PyConsoleCustomizer.kt) |
| [com.jetbrains.python.console.executeCustomizer](https://jb.gg/ipe?extensions=com.jetbrains.python.console.executeCustomizer) ![Experimental][experimental] | [`PyExecuteConsoleCustomizer`](%gh-ic%/python/src/com/jetbrains/python/console/PyExecuteConsoleCustomizer.kt) |
| [com.jetbrains.python.console.pyConsoleOutputCustomizer](https://jb.gg/ipe?extensions=com.jetbrains.python.console.pyConsoleOutputCustomizer) ![Experimental][experimental] | [`PyConsoleOutputCustomizer`](%gh-ic%/python/src/com/jetbrains/python/console/PyConsoleOutputCustomizer.kt) |
| [com.jetbrains.python.console.runnerFactory](https://jb.gg/ipe?extensions=com.jetbrains.python.console.runnerFactory) | [`PythonConsoleRunnerFactory`](%gh-ic%/python/src/com/jetbrains/python/console/PythonConsoleRunnerFactory.java) |
| [com.jetbrains.python.debugger.numericContainerPopupCustomizer](https://jb.gg/ipe?extensions=com.jetbrains.python.debugger.numericContainerPopupCustomizer) | [`PyNumericContainerPopupCustomizer`](%gh-ic%/python/pydevSrc/src/com/jetbrains/python/debugger/pydev/tables/PyNumericContainerPopupCustomizer.kt) |
| [com.jetbrains.python.debugger.pyDebugAsyncioCustomizer](https://jb.gg/ipe?extensions=com.jetbrains.python.debugger.pyDebugAsyncioCustomizer) | [`PyDebugAsyncioCustomizer`](%gh-ic%/python/src/com/jetbrains/python/debugger/PyDebugAsyncioCustomizer.kt) |
| [com.jetbrains.python.testing.pyTestLineMarkerContributorCustomizer](https://jb.gg/ipe?extensions=com.jetbrains.python.testing.pyTestLineMarkerContributorCustomizer) | [`PyTestLineMarkerContributorCustomizer`](%gh-ic%/python/src/com/jetbrains/python/testing/PyTestLineMarkerContributor.kt) |

### PythonParser.xml

[`PythonParser.xml`](%gh-ic%/python/python-parser/resources/META-INF/PythonParser.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Pythonid.dialectsTokenSetContributor](https://jb.gg/ipe?extensions=Pythonid.dialectsTokenSetContributor) | [`PythonDialectsTokenSetContributor`](%gh-ic%/python/python-parser/src/com/jetbrains/python/PythonDialectsTokenSetContributor.java) |

### PythonPsi.xml

[`PythonPsi.xml`](%gh-ic%/python/python-psi-api/resources/META-INF/PythonPsi.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Pythonid.canonicalPathProvider](https://jb.gg/ipe?extensions=Pythonid.canonicalPathProvider) | [`PyCanonicalPathProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/resolve/PyCanonicalPathProvider.java) |
| [Pythonid.customPackageIdentifier](https://jb.gg/ipe?extensions=Pythonid.customPackageIdentifier) | [`PyCustomPackageIdentifier`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/PyCustomPackageIdentifier.java) |
| [Pythonid.importResolver](https://jb.gg/ipe?extensions=Pythonid.importResolver) | [`PyImportResolver`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/impl/PyImportResolver.java) |
| [Pythonid.inspectionExtension](https://jb.gg/ipe?extensions=Pythonid.inspectionExtension) | [`PyInspectionExtension`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/inspections/PyInspectionExtension.java) |
| [Pythonid.keywordArgumentProvider](https://jb.gg/ipe?extensions=Pythonid.keywordArgumentProvider) | [`PyKeywordArgumentProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/impl/PyKeywordArgumentProvider.java) |
| [Pythonid.knownDecoratorProvider](https://jb.gg/ipe?extensions=Pythonid.knownDecoratorProvider) | [`PyKnownDecoratorProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/PyKnownDecoratorProvider.java) |
| [Pythonid.pyClassInheritorsSearch](https://jb.gg/ipe?extensions=Pythonid.pyClassInheritorsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [Pythonid.pyClassMembersProvider](https://jb.gg/ipe?extensions=Pythonid.pyClassMembersProvider) | [`PyClassMembersProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/types/PyClassMembersProvider.java) |
| [Pythonid.pyModuleMembersProvider](https://jb.gg/ipe?extensions=Pythonid.pyModuleMembersProvider) | [`PyModuleMembersProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/types/PyModuleMembersProvider.java) |
| [Pythonid.pyOverridingMethodsSearch](https://jb.gg/ipe?extensions=Pythonid.pyOverridingMethodsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [Pythonid.pyReferenceResolveProvider](https://jb.gg/ipe?extensions=Pythonid.pyReferenceResolveProvider) | [`PyReferenceResolveProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/resolve/PyReferenceResolveProvider.java) |
| [Pythonid.pySuperMethodsSearch](https://jb.gg/ipe?extensions=Pythonid.pySuperMethodsSearch) | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| [Pythonid.pythonDocumentationQuickInfoProvider](https://jb.gg/ipe?extensions=Pythonid.pythonDocumentationQuickInfoProvider) | [`PythonDocumentationQuickInfoProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/documentation/PythonDocumentationQuickInfoProvider.java) |
| [Pythonid.resolveResultRater](https://jb.gg/ipe?extensions=Pythonid.resolveResultRater) | [`PyResolveResultRater`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/impl/PyResolveResultRater.java) |
| [Pythonid.statementEffectQuickFixProvider](https://jb.gg/ipe?extensions=Pythonid.statementEffectQuickFixProvider) | [`PyStatementEffectQuickFixProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/inspections/PyStatementEffectQuickFixProvider.java) |
| [Pythonid.thirdPartySdkDetector](https://jb.gg/ipe?extensions=Pythonid.thirdPartySdkDetector) ![Experimental][experimental] | [`PyThirdPartySdkDetector`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/resolve/PyThirdPartySdkDetector.java) |
| [Pythonid.typeHintProvider](https://jb.gg/ipe?extensions=Pythonid.typeHintProvider) ![Experimental][experimental] ![Internal][internal] | [`PyTypeHintProvider`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/codeInsight/typing/PyTypeHintProvider.kt) |
| [Pythonid.typeProvider](https://jb.gg/ipe?extensions=Pythonid.typeProvider) | [`PyTypeProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/impl/PyTypeProvider.java) |

### PythonPsiImpl.xml

[`PythonPsiImpl.xml`](%gh-ic%/python/python-psi-impl/resources/META-INF/PythonPsiImpl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Pythonid.customClassStubType](https://jb.gg/ipe?extensions=Pythonid.customClassStubType) ![Internal][internal] | [`PyCustomClassStubType`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/psi/impl/stubs/PyCustomClassStubType.java) |
| [Pythonid.customDecoratorStubType](https://jb.gg/ipe?extensions=Pythonid.customDecoratorStubType) ![Internal][internal] | [`PyCustomDecoratorStubType`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/psi/impl/stubs/PyCustomDecoratorStubType.kt) |
| [Pythonid.customTargetExpressionStubType](https://jb.gg/ipe?extensions=Pythonid.customTargetExpressionStubType) ![Internal][internal] | [`CustomTargetExpressionStubType`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/psi/impl/stubs/CustomTargetExpressionStubType.java) |
| [Pythonid.importCandidateProvider](https://jb.gg/ipe?extensions=Pythonid.importCandidateProvider) | [`PyImportCandidateProvider`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/codeInsight/imports/PyImportCandidateProvider.java) |
| [Pythonid.pyDataclassParametersProvider](https://jb.gg/ipe?extensions=Pythonid.pyDataclassParametersProvider) | [`PyDataclassParametersProvider`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/codeInsight/PyDataclasses.kt) |
| [Pythonid.pyReferenceCustomTargetChecker](https://jb.gg/ipe?extensions=Pythonid.pyReferenceCustomTargetChecker) | [`PyReferenceCustomTargetChecker`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/psi/impl/references/PyReferenceCustomTargetChecker.kt) |
| [Pythonid.pyiStubSuppressor](https://jb.gg/ipe?extensions=Pythonid.pyiStubSuppressor) ![Experimental][experimental] | [`PyiStubSuppressor`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/pyi/PyiStubSuppressor.java) |
| [Pythonid.typeCheckerExtension](https://jb.gg/ipe?extensions=Pythonid.typeCheckerExtension) ![Experimental][experimental] | [`PyTypeCheckerExtension`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/psi/types/PyTypeCheckerExtension.java) |
| [Pythonid.visitorFilter](https://jb.gg/ipe?extensions=Pythonid.visitorFilter) | [`PythonVisitorFilter`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/psi/PythonVisitorFilter.java) |
| [com.jetbrains.python.pythonHelpersLocator](https://jb.gg/ipe?extensions=com.jetbrains.python.pythonHelpersLocator) ![Internal][internal] | [`PythonHelpersLocator`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/PythonHelpersLocator.kt) |

### PythonSdk.xml

[`PythonSdk.xml`](%gh-ic%/python/python-sdk/resources/META-INF/PythonSdk.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Pythonid.projectSdkConfigurationExtension](https://jb.gg/ipe?extensions=Pythonid.projectSdkConfigurationExtension) ![Internal][internal] | [`PyProjectSdkConfigurationExtension`](%gh-ic%/python/python-sdk/src/com/jetbrains/python/sdk/configuration/PyProjectSdkConfigurationExtension.kt) |
| [Pythonid.pythonFlavorProvider](https://jb.gg/ipe?extensions=Pythonid.pythonFlavorProvider) | [`PythonFlavorProvider`](%gh-ic%/python/python-sdk/src/com/jetbrains/python/sdk/flavors/PythonFlavorProvider.java) |
| [Pythonid.pythonSdkFlavor](https://jb.gg/ipe?extensions=Pythonid.pythonSdkFlavor) | [`PythonSdkFlavor`](%gh-ic%/python/python-sdk/src/com/jetbrains/python/sdk/flavors/PythonSdkFlavor.java) |

### PythonSyntax.xml

[`PythonSyntax.xml`](%gh-ic%/python/python-syntax/resources/META-INF/PythonSyntax.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Pythonid.pyAnnotator](https://jb.gg/ipe?extensions=Pythonid.pyAnnotator) ![Experimental][experimental] | [`PyAnnotatorBase`](%gh-ic%/python/python-syntax-core/src/com/jetbrains/python/validation/PyAnnotatorBase.java) |



## Terminal Plugin


### Terminal Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [Constants#TERMINAL_COMMAND_HANDLER_TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.terminal.TerminalCommandHandlerCustomizer.TerminalCommandHandlerListener)  | [`TerminalCommandHandlerListener`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/TerminalCommandHandlerCustomizer.kt) |
| [BlockTerminalInitializationListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.terminal.block.BlockTerminalInitializationListener)  ![Internal][internal] ![Project-Level][project-level] | [`BlockTerminalInitializationListener`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/block/BlockTerminalInitializationListener.kt) |
| [CommandHistoryListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.terminal.block.history.CommandHistoryListener)  ![Internal][internal] ![Project-Level][project-level] | [`CommandHistoryListener`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/block/history/CommandHistoryListener.kt) |
| [CommandSearchListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.terminal.block.history.CommandSearchListener)  ![Internal][internal] ![Project-Level][project-level] | [`CommandSearchListener`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/block/history/CommandSearchListener.kt) |


### intellij.terminal.frontend.xml

[`intellij.terminal.frontend.xml`](%gh-ic%/plugins/terminal/frontend/resources/intellij.terminal.frontend.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [org.jetbrains.plugins.terminal.escapeHandler](https://jb.gg/ipe?extensions=org.jetbrains.plugins.terminal.escapeHandler) ![Experimental][experimental] | [`TerminalEscapeHandler`](%gh-ic%/plugins/terminal/frontend/src/com/intellij/terminal/frontend/action/TerminalEscapeAction.kt) |

### terminal.xml

[`terminal.xml`](%gh-ic%/plugins/terminal/resources/META-INF/terminal.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.terminal.shellCommandHandler](https://jb.gg/ipe?extensions=com.intellij.terminal.shellCommandHandler) | [`TerminalShellCommandHandler`](%gh-ic%/platform/execution-impl/src/com/intellij/terminal/TerminalShellCommandHandler.kt) |
| [org.jetbrains.plugins.terminal.commandHistoryProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.terminal.commandHistoryProvider) ![Internal][internal] | [`TerminalCommandHistoryProvider`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/block/history/TerminalCommandHistoryProvider.kt) |
| [org.jetbrains.plugins.terminal.commandSpecsProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.terminal.commandSpecsProvider) ![Experimental][experimental] | [`ShellCommandSpecsProvider`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/block/completion/spec/ShellCommandSpecsProvider.kt) |
| [org.jetbrains.plugins.terminal.exp.commandBlockHighlighterProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.terminal.exp.commandBlockHighlighterProvider) ![Internal][internal] | [`TerminalCommandBlockHighlighterProvider`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/block/output/highlighting/TerminalCommandBlockHighlighterProvider.kt) |
| [org.jetbrains.plugins.terminal.localTerminalCustomizer](https://jb.gg/ipe?extensions=org.jetbrains.plugins.terminal.localTerminalCustomizer) | [`LocalTerminalCustomizer`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/LocalTerminalCustomizer.java) |
| [org.jetbrains.plugins.terminal.openPredefinedTerminalProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.terminal.openPredefinedTerminalProvider) | [`OpenPredefinedTerminalActionProvider`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/ui/OpenPredefinedTerminalActionProvider.kt) |
| [org.jetbrains.plugins.terminal.promptCustomEnterHandler](https://jb.gg/ipe?extensions=org.jetbrains.plugins.terminal.promptCustomEnterHandler) ![Internal][internal] | [`TerminalPromptCustomEnterHandler`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/block/prompt/TerminalPromptCustomEnterHandler.kt) |
| [org.jetbrains.plugins.terminal.shellSupport](https://jb.gg/ipe?extensions=org.jetbrains.plugins.terminal.shellSupport) ![Experimental][experimental] | [`TerminalShellSupport`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/exp/completion/TerminalShellSupport.kt) |
| [org.jetbrains.plugins.terminal.terminalWidgetProvider](https://jb.gg/ipe?extensions=org.jetbrains.plugins.terminal.terminalWidgetProvider) ![Internal][internal] | [`TerminalWidgetProvider`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/TerminalWidgetProvider.kt) |



## VCS Plugins


### VCS Plugins – Listeners

| Topic | Listener |
|-------|----------|
| [SvnVcs#ROOTS_RELOADED](https://jb.gg/ipe/listeners?topics=com.intellij.util.Consumer)  ![Obsolete][obsolete] | [`Consumer`](%gh-ic%/platform/util/src/com/intellij/util/Consumer.java) |
| [GitBranchIncomingOutgoingManager#GIT_INCOMING_OUTGOING_CHANGED](https://jb.gg/ipe/listeners?topics=git4idea.branch.GitBranchIncomingOutgoingManager.GitIncomingOutgoingListener)  ![Project-Level][project-level] | [`GitIncomingOutgoingListener`](%gh-ic%/plugins/git4idea/src/git4idea/branch/GitBranchIncomingOutgoingManager.java) |
| [GitAuthenticationListener#GIT_AUTHENTICATION_SUCCESS](https://jb.gg/ipe/listeners?topics=git4idea.commands.GitAuthenticationListener)  | [`GitAuthenticationListener`](%gh-ic%/plugins/git4idea/src/git4idea/commands/GitAuthenticationListener.java) |
| [GitMergeCommitMessageChangedListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.commit.GitMergeCommitMessageChangedListener)  ![Project-Level][project-level] | [`GitMergeCommitMessageChangedListener`](%gh-ic%/plugins/git4idea/src/git4idea/commit/GitMergeCommitMessagePolicy.kt) |
| [GitExecutableManager#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.config.GitExecutableListener)  | [`GitExecutableListener`](%gh-ic%/plugins/git4idea/src/git4idea/config/GitExecutableListener.java) |
| [GitFetchInProgressListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.fetch.GitFetchInProgressListener)  ![Project-Level][project-level] | [`GitFetchInProgressListener`](%gh-ic%/plugins/git4idea/src/git4idea/fetch/GitFetchInProgressListener.kt) |
| [GitPushListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.push.GitPushListener)  | [`GitPushListener`](%gh-ic%/plugins/git4idea/src/git4idea/push/GitPushListener.kt) |
| [GitCommitTemplateListener#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.repo.GitCommitTemplateListener)  ![Project-Level][project-level] | [`GitCommitTemplateListener`](%gh-ic%/plugins/git4idea/src/git4idea/repo/GitCommitTemplateTracker.kt) |
| [GitConfigListener#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.repo.GitConfigListener)  ![Project-Level][project-level] | [`GitConfigListener`](%gh-ic%/plugins/git4idea/src/git4idea/repo/GitConfigListener.kt) |
| [GitRepository#GIT_REPO_CHANGE](https://jb.gg/ipe/listeners?topics=git4idea.repo.GitRepositoryChangeListener)  | [`GitRepositoryChangeListener`](%gh-ic%/plugins/git4idea/src/git4idea/repo/GitRepositoryChangeListener.java) |
| [GitRepository#GIT_REPO_STATE_CHANGE](https://jb.gg/ipe/listeners?topics=git4idea.repo.GitRepositoryStateChangeListener)  | [`GitRepositoryStateChangeListener`](%gh-ic%/plugins/git4idea/src/git4idea/repo/GitRepositoryStateChangeListener.kt) |
| [GitTagHolder#GIT_TAGS_LOADED](https://jb.gg/ipe/listeners?topics=git4idea.repo.GitTagLoaderListener)  | [`GitTagLoaderListener`](%gh-ic%/plugins/git4idea/src/git4idea/repo/GitTagHolder.kt) |
| [GitStashSettingsListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.stash.ui.GitStashSettingsListener)  | [`GitStashSettingsListener`](%gh-ic%/plugins/git4idea/src/git4idea/stash/ui/GitStashContentProvider.kt) |
| [GitRefreshListener#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.status.GitRefreshListener)  | [`GitRefreshListener`](%gh-ic%/plugins/git4idea/src/git4idea/status/GitRefreshListener.java) |
| [GitStagingAreaHolder#TOPIC](https://jb.gg/ipe/listeners?topics=git4idea.status.GitStagingAreaHolder.StagingAreaListener)  | [`StagingAreaListener`](%gh-ic%/plugins/git4idea/src/git4idea/status/GitStagingAreaHolder.java) |
| [SvnVcs#WC_CONVERTED](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  ![Project-Level][project-level] | `Runnable` |
| [RootsAndBranches#REFRESH_REQUEST](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  ![Project-Level][project-level] | `Runnable` |
| [Merger#COMMITTED_CHANGES_MERGED_STATE](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.svn.integrate.Merger.CommittedChangesMergedStateChanged)  ![Project-Level][project-level] | [`CommittedChangesMergedStateChanged`](%gh-ic%/plugins/svn4idea/src/org/jetbrains/idea/svn/integrate/Merger.java) |
| [SvnMergeInfoCache#SVN_MERGE_INFO_CACHE](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.svn.mergeinfo.SvnMergeInfoCache.SvnMergeInfoCacheListener)  ![Project-Level][project-level] | [`SvnMergeInfoCacheListener`](%gh-ic%/plugins/svn4idea/src/org/jetbrains/idea/svn/mergeinfo/SvnMergeInfoCache.java) |
| [GHPRDataOperationsListener.Companion#TOPIC](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.github.pullrequest.data.provider.GHPRDataOperationsListener)  | [`GHPRDataOperationsListener`](%gh-ic%/plugins/github/github-core/src/org/jetbrains/plugins/github/pullrequest/data/provider/GHPRDataOperationsListener.kt) |
| [HgVcs#REMOTE_TOPIC](https://jb.gg/ipe/listeners?topics=org.zmlx.hg4idea.HgUpdater)  ![Project-Level][project-level] | [`HgUpdater`](%gh-ic%/plugins/hg4idea/src/org/zmlx/hg4idea/HgUpdater.java) |
| [HgVcs#STATUS_TOPIC](https://jb.gg/ipe/listeners?topics=org.zmlx.hg4idea.HgUpdater)  ![Project-Level][project-level] | [`HgUpdater`](%gh-ic%/plugins/hg4idea/src/org/zmlx/hg4idea/HgUpdater.java) |
| [HgVcs#INCOMING_OUTGOING_CHECK_TOPIC](https://jb.gg/ipe/listeners?topics=org.zmlx.hg4idea.status.ui.HgWidgetUpdater)  ![Project-Level][project-level] | [`HgWidgetUpdater`](%gh-ic%/plugins/hg4idea/src/org/zmlx/hg4idea/status/ui/HgWidgetUpdater.java) |


### intellij.vcs.git.xml

[`intellij.vcs.git.xml`](%gh-ic%/plugins/git4idea/resources/intellij.vcs.git.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [Git4Idea.GitCheckinExplicitMovementProvider](https://jb.gg/ipe?extensions=Git4Idea.GitCheckinExplicitMovementProvider) | [`GitCheckinExplicitMovementProvider`](%gh-ic%/plugins/git4idea/src/git4idea/checkin/GitCheckinExplicitMovementProvider.java) |
| [Git4Idea.GitHttpAuthDataProvider](https://jb.gg/ipe?extensions=Git4Idea.GitHttpAuthDataProvider) | [`GitHttpAuthDataProvider`](%gh-ic%/plugins/git4idea/src/git4idea/remote/GitHttpAuthDataProvider.java) |
| [Git4Idea.gitAnnotationPerformanceListener](https://jb.gg/ipe?extensions=Git4Idea.gitAnnotationPerformanceListener) ![Internal][internal] | [`GitAnnotationPerformanceListener`](%gh-ic%/plugins/git4idea/src/git4idea/annotate/GitAnnotationPerformanceListener.kt) |
| [Git4Idea.gitCommitModeProvider](https://jb.gg/ipe?extensions=Git4Idea.gitCommitModeProvider) ![Internal][internal] | [`GitCommitModeProvider`](%gh-ic%/plugins/git4idea/src/git4idea/commit/GitCommitModeProvider.kt) |
| [Git4Idea.gitCurrentBranchPresenter](https://jb.gg/ipe?extensions=Git4Idea.gitCurrentBranchPresenter) | [`GitCurrentBranchPresenter`](%gh-ic%/plugins/git4idea/src/git4idea/ui/branch/GitCurrentBranchPresenter.kt) |
| [Git4Idea.gitFetchHandler](https://jb.gg/ipe?extensions=Git4Idea.gitFetchHandler) | [`GitFetchHandler`](%gh-ic%/plugins/git4idea/src/git4idea/fetch/GitFetchHandler.kt) |
| [Git4Idea.gitPostUpdateHandler](https://jb.gg/ipe?extensions=Git4Idea.gitPostUpdateHandler) | [`GitPostUpdateHandler`](%gh-ic%/plugins/git4idea/src/git4idea/update/GitPostUpdateHandler.kt) |
| [Git4Idea.gitProtectedBranchProvider](https://jb.gg/ipe?extensions=Git4Idea.gitProtectedBranchProvider) | [`GitProtectedBranchProvider`](%gh-ic%/plugins/git4idea/src/git4idea/config/GitProtectedBranchProvider.kt) |
| [Git4Idea.gitPushNotificationCustomizer](https://jb.gg/ipe?extensions=Git4Idea.gitPushNotificationCustomizer) ![Internal][internal] ![Project-Level][project-level] | [`GitPushNotificationCustomizer`](%gh-ic%/plugins/git4idea/src/git4idea/push/GitPushNotificationCustomizer.kt) |
| [Git4Idea.gitRawAnnotationProvider](https://jb.gg/ipe?extensions=Git4Idea.gitRawAnnotationProvider) ![Experimental][experimental] ![Project-Level][project-level] | [`GitRawAnnotationProvider`](%gh-ic%/plugins/git4idea/src/git4idea/annotate/GitAnnotationProvider.java) |
| [Git4Idea.gitRepositoryHostingService](https://jb.gg/ipe?extensions=Git4Idea.gitRepositoryHostingService) | [`GitRepositoryHostingService`](%gh-ic%/plugins/git4idea/src/git4idea/remote/GitRepositoryHostingService.java) |
| [Git4Idea.instantGitTokenProvider](https://jb.gg/ipe?extensions=Git4Idea.instantGitTokenProvider) ![Experimental][experimental] ![Internal][internal] | [`InstantGitTokenProvider`](%gh-ic%/plugins/git4idea/src/git4idea/instant/InstantGitTokenProvider.kt) |

### intellij.vcs.github.xml

[`intellij.vcs.github.xml`](%gh-ic%/plugins/github/github-core/resources/intellij.vcs.github.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.vcs.github.gistContentsCollector](https://jb.gg/ipe?extensions=com.intellij.vcs.github.gistContentsCollector) | [`GithubGistContentsCollector`](%gh-ic%/plugins/github/github-core/src/org/jetbrains/plugins/github/GithubGistContentsCollector.kt) |
| [intellij.vcs.github.aiReviewExtension](https://jb.gg/ipe?extensions=intellij.vcs.github.aiReviewExtension) ![Internal][internal] | [`GHPRAIReviewExtension`](%gh-ic%/plugins/github/github-core/src/org/jetbrains/plugins/github/ai/GHPRAIReviewExtension.kt) |
| [intellij.vcs.github.aiSummaryExtension](https://jb.gg/ipe?extensions=intellij.vcs.github.aiSummaryExtension) ![Internal][internal] | [`GHPRAISummaryExtension`](%gh-ic%/plugins/github/github-core/src/org/jetbrains/plugins/github/ai/GHPRAISummaryViewModel.kt) |
| [intellij.vcs.github.titleAndDescriptionGenerator](https://jb.gg/ipe?extensions=intellij.vcs.github.titleAndDescriptionGenerator) ![Internal][internal] | [`GHPRTitleAndDescriptionGeneratorExtension`](%gh-ic%/plugins/github/github-core/src/org/jetbrains/plugins/github/pullrequest/ui/toolwindow/create/GHPRCreateTitleAndDescriptionGenerationViewModel.kt) |

### intellij.vcs.gitlab.xml

[`intellij.vcs.gitlab.xml`](%gh-ic%/plugins/gitlab/gitlab-core/resources/intellij.vcs.gitlab.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [intellij.vcs.gitlab.titleGenerator](https://jb.gg/ipe?extensions=intellij.vcs.gitlab.titleGenerator) ![Internal][internal] | [`GitLabTitleGeneratorExtension`](%gh-ic%/plugins/gitlab/gitlab-core/src/org/jetbrains/plugins/gitlab/mergerequest/ui/create/model/GitLabMergeRequestCreateTitleGenerationViewModel.kt) |

### Subversion

[`Subversion`](%gh-ic%/plugins/svn4idea/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| [com.intellij.vcs.svn.mergerCommitMessage](https://jb.gg/ipe?extensions=com.intellij.vcs.svn.mergerCommitMessage) ![Project-Level][project-level] | [`MergerCommitMessage`](%gh-ic%/plugins/svn4idea/src/org/jetbrains/idea/svn/integrate/MergerCommitMessage.kt) |

[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square
