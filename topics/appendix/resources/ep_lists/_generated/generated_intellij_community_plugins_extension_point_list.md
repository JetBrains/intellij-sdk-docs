<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<!-- GENERATED FILE, DO NOT EDIT -->
<!-- This file is generated with the SDK Docs Authoring Tools plugin ('Generate SDK Docs EP Lists' action) -->
<!-- Revision: b815cfdcaa594a8842ccf89e6ac2bf7615401920 -->

<!--
EP List Directories:
- /community/java
- /community/json
- /community/jvm
- /community/plugins
- /community/python

There must be no top-level "Listeners" group, adjust ExtensionPointAnalyzerAction.Group accordingly.
-->


<snippet id="content">

629 Extension Points and 71 Listeners

<include from="snippets.topic" element-id="ep_list_legend"/>

## IntelliJ Community Plugins

### IntelliJ Community Plugins – Listeners

| Topic | Listener |
|-------|----------|
| [`CoverageLoadingListener#COVERAGE_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.coverage.CoverageLoadingListener)  ![Internal][internal] ![Project-Level][project-level] | [`CoverageLoadingListener`](%gh-ic%/plugins/coverage-common/src/com/intellij/coverage/CoverageLoaderListener.kt) |


### ByteCodeViewer

[`ByteCodeViewer`](%gh-ic%/plugins/ByteCodeViewer/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="ByteCodeViewer.classSearcher"/></include> | [`ClassSearcher`](%gh-ic%/plugins/ByteCodeViewer/src/com/intellij/byteCodeViewer/ClassSearcher.kt) |

### com.intellij.completion.evaluation

[`com.intellij.completion.evaluation`](%gh-ic%/plugins/evaluation-plugin/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cce.apiCallExtractor"/></include> | [`ApiCallExtractorProvider`](%gh-ic%/plugins/evaluation-plugin/core/src/com/intellij/cce/metric/ApiCallExtractor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cce.codeCompletionHandlerFactory"/></include> ![Project-Level][project-level] | [`CodeCompletionHandlerFactory`](%gh-ic%/plugins/evaluation-plugin/languages/src/com/intellij/cce/evaluation/CodeCompletionHandlerFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cce.codeExecutionManager"/></include> | [`CodeExecutionManager`](%gh-ic%/plugins/evaluation-plugin/languages/src/com/intellij/cce/execution/manager/CodeExecutionManager.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cce.command"/></include> | [`EvaluationCommandExtension`](%gh-ic%/plugins/evaluation-plugin/src/com/intellij/cce/commands/EvaluationCommandExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cce.completionEvaluationVisitor"/></include> | [`EvaluationVisitor`](%gh-ic%/plugins/evaluation-plugin/languages/src/com/intellij/cce/visitor/EvaluationVisitor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cce.datasetActionProvider"/></include> | [`DatasetActionProvider`](%gh-ic%/plugins/evaluation-plugin/src/com/intellij/cce/ui/DatasetActionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cce.evaluableFeature"/></include> | [`EvaluableFeature`](%gh-ic%/plugins/evaluation-plugin/src/com/intellij/cce/evaluable/EvaluableFeature.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cce.exposedApiExtractor"/></include> | [`ExposedApiExtractor`](%gh-ic%/plugins/evaluation-plugin/core/src/com/intellij/cce/evaluable/ExposedApiExtractor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cce.extraEvaluationStepProvider"/></include> | [`ExtraEvaluationStepProvider`](%gh-ic%/plugins/evaluation-plugin/src/com/intellij/cce/evaluation/step/ExtraEvaluationStepProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cce.lineCompletionVisitorFactory"/></include> | [`LineCompletionVisitorFactory`](%gh-ic%/plugins/evaluation-plugin/languages/src/com/intellij/cce/visitor/LineCompletionVisitorFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cce.openProjectMethodProvider"/></include> | [`OpenProjectMethodProvider`](%gh-ic%/plugins/evaluation-plugin/languages/src/com/intellij/cce/actions/OpenProjectMethodProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cce.setupSdkStep"/></include> ![Project-Level][project-level] | [`SetupSdkStep`](%gh-ic%/plugins/evaluation-plugin/languages/src/com/intellij/cce/evaluation/SetupSdkStep.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.cce.suggestionsProvider"/></include> ![Project-Level][project-level] | [`SuggestionsProvider`](%gh-ic%/plugins/evaluation-plugin/languages/src/com/intellij/cce/evaluation/SuggestionsProvider.kt) |

### com.intellij.completion.ml.ranking

[`com.intellij.completion.ml.ranking`](%gh-ic%/plugins/completion-ml-ranking/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.ml.additionalContextFeatures"/></include> ![Internal][internal] | [`AdditionalContextFeatureProvider`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/sorting/AdditionalContextFeatureProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.ml.elementIdProvider"/></include> ![Internal][internal] | [`LookupElementIdProvider`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/util/LookupElementIdProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.ml.experimentFetcher"/></include> | [`MLRankingExperimentFetcher`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/experiments/MLRankingExperimentFetcher.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.ml.featuresOverride"/></include> ![Internal][internal] | [`RankingFeaturesOverrides`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/features/RankingFeaturesOverrides.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.ml.localModelProvider"/></include> ![Internal][internal] | [`LocalZipModelProvider`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/ranker/local/LocalZipModelProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.ml.lookupFeatures"/></include> ![Internal][internal] | [`LookupFeatureProvider`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/sorting/LookupFeatureProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.ml.ranking.features.policy"/></include> ![Internal][internal] | [`CompletionFeaturesPolicy`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/features/CompletionFeaturesPolicy.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.completion.ml.ranking.policy"/></include> ![Experimental][experimental] | [`CompletionMLPolicy`](%gh-ic%/plugins/completion-ml-ranking/src/com/intellij/completion/ml/CompletionMLPolicy.kt) |

### com.intellij.copyright

[`com.intellij.copyright`](%gh-ic%/plugins/copyright/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.copyright.updater"/></include> | [`UpdateCopyrightsProvider`](%gh-ic%/plugins/copyright/src/com/maddyhome/idea/copyright/psi/UpdateCopyrightsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.copyright.variablesProvider"/></include> | [`CopyrightVariablesProvider`](%gh-ic%/plugins/copyright/src/com/maddyhome/idea/copyright/pattern/CopyrightVariablesProvider.java) |

### com.intellij.java-i18n

[`com.intellij.java-i18n`](%gh-ic%/plugins/java-i18n/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.java-i18n.i18nizeHandlerProvider"/></include> | [`I18nizeHandlerProvider`](%gh-ic%/plugins/java-i18n/src/com/intellij/codeInspection/i18n/I18nizeHandlerProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.java-i18n.resourceBundleManager"/></include> ![Project-Level][project-level] | [`ResourceBundleManager`](%gh-ic%/plugins/java-i18n/src/com/intellij/lang/properties/psi/ResourceBundleManager.java) |

### com.intellij.properties

[`com.intellij.properties`](%gh-ic%/plugins/properties/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.properties.alphaUnsortedInspectionSuppressor"/></include> | [`AlphaUnsortedPropertiesFileInspectionSuppressor`](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/codeInspection/unsorted/AlphaUnsortedPropertiesFileInspectionSuppressor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.properties.duplicatePropertyKeyAnnotationSuppressor"/></include> | [`DuplicatePropertyKeyAnnotationSuppressor`](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/DuplicatePropertyKeyAnnotationSuppressor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.properties.extendedUseScopeProvider"/></include> ![Experimental][experimental] | [`ExtendedUseScopeProvider`](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/codeInspection/unused/ExtendedUseScopeProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.properties.implicitPropertyUsageProvider"/></include> | [`ImplicitPropertyUsageProvider`](%gh-ic%/plugins/properties/properties-psi-impl/src/com/intellij/lang/properties/codeInspection/unused/ImplicitPropertyUsageProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.properties.spellcheckerMnemonicsTokenizer"/></include> | [`MnemonicsTokenizer`](%gh-ic%/plugins/properties/src/com/intellij/lang/properties/spellchecker/MnemonicsTokenizer.java) |

### com.intellij.searcheverywhere.ml

[`com.intellij.searcheverywhere.ml`](%gh-ic%/plugins/search-everywhere-ml/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searchEverywhereMl.itemSelectedListener"/></include> | [`SearchEverywhereItemSelectedListener`](%gh-ic%/plugins/search-everywhere-ml/src/com/intellij/searchEverywhereMl/SearchEverywhereItemSelectedListener.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searchEverywhereMl.rankingService"/></include> ![Internal][internal] | [`SearchEverywhereMlService`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereMlService.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searchEverywhereMl.searchEverywhereSessionPropertyProvider"/></include> | [`SearchEverywhereSessionPropertyProvider`](%gh-ic%/plugins/search-everywhere-ml/src/com/intellij/searchEverywhereMl/SearchEverywhereSessionPropertyProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searchEverywhereMl.textEmbeddingProvider"/></include> | [`TextEmbeddingProvider`](%gh-ic%/plugins/search-everywhere-ml/src/com/intellij/searchEverywhereMl/TextEmbeddingProvider.kt) |

### com.intellij.stats.completion

[`com.intellij.stats.completion`](%gh-ic%/plugins/stats-collector/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.stats.completion.policy"/></include> ![Internal][internal] | [`CompletionStatsPolicy`](%gh-ic%/plugins/stats-collector/src/com/intellij/stats/completion/CompletionStatsPolicy.kt) |

### com.intellij.tasks

[`com.intellij.tasks`](%gh-ic%/plugins/tasks/tasks-core/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.tasks.commitPlaceholderProvider"/></include> | [`CommitPlaceholderProvider`](%gh-ic%/platform/tasks-platform-api/src/com/intellij/tasks/CommitPlaceholderProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.tasks.dialogPanelProvider"/></include> ![Non-Dynamic][non-dynamic] | [`TaskDialogPanelProvider`](%gh-ic%/plugins/tasks/tasks-api/src/com/intellij/tasks/ui/TaskDialogPanelProvider.java) |

### com.intellij.turboComplete

[`com.intellij.turboComplete`](%gh-ic%/plugins/turboComplete/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.turboComplete.analysis.pipelineListener"/></include> | [`PipelineListener`](%gh-ic%/plugins/turboComplete/src/com/intellij/turboComplete/analysis/PipelineListener.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.turboComplete.features.kind.provider"/></include> | [`KindFeatureProvider`](%gh-ic%/plugins/turboComplete/src/com/intellij/turboComplete/features/kind/KindFeatureProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.turboComplete.kindCollector"/></include> ![Internal][internal] | [`KindCollector`](%gh-ic%/platform/ml-impl/src/com/intellij/platform/ml/impl/turboComplete/KindCollector.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.turboComplete.suggestionGeneratorExecutorProvider"/></include> ![Internal][internal] | [`SuggestionGeneratorExecutorProvider`](%gh-ic%/platform/ml-impl/src/com/intellij/platform/ml/impl/turboComplete/SuggestionGeneratorExecutorProvider.kt) |

### com.intellij.uiDesigner

[`com.intellij.uiDesigner`](%gh-ic%/plugins/ui-designer/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.paletteItemProvider"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`PaletteItemProvider`](%gh-ic%/plugins/ui-designer/src/com/intellij/ide/palette/PaletteItemProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.uiDesigner.formInspectionTool"/></include> ![Non-Dynamic][non-dynamic] | [`FormInspectionTool`](%gh-ic%/plugins/ui-designer/src/com/intellij/uiDesigner/inspections/FormInspectionTool.java) |

### com.jetbrains.filePrediction

[`com.jetbrains.filePrediction`](%gh-ic%/plugins/filePrediction/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.filePrediction.candidateProvider"/></include> ![Internal][internal] | [`FilePredictionCandidateProvider`](%gh-ic%/plugins/filePrediction/src/com/intellij/filePrediction/candidates/FilePredictionCandidateProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.filePrediction.featureProvider"/></include> ![Internal][internal] | [`FilePredictionFeatureProvider`](%gh-ic%/plugins/filePrediction/src/com/intellij/filePrediction/features/FilePredictionFeatureProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.filePrediction.ml.model"/></include> ![Internal][internal] | [`FilePredictionModelProvider`](%gh-ic%/plugins/filePrediction/src/com/intellij/filePrediction/predictor/model/FilePredictionModelProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.filePrediction.referencesProvider"/></include> ![Internal][internal] | [`FileExternalReferencesProvider`](%gh-ic%/plugins/filePrediction/src/com/intellij/filePrediction/references/FilePredictionReferencesHelper.kt) |

### com.jetbrains.performancePlugin

[`com.jetbrains.performancePlugin`](%gh-ic%/plugins/performanceTesting/core/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.performancePlugin.commandProvider"/></include> | [`CommandProvider`](%gh-ic%/plugins/performanceTesting/core/src/com/jetbrains/performancePlugin/CommandProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.performancePlugin.playbackRunnerProvider"/></include> | [`PerformancePlaybackRunner`](%gh-ic%/plugins/performanceTesting/core/src/com/jetbrains/performancePlugin/PerformancePlaybackRunner.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.performancePlugin.profiler"/></include> | [`Profiler`](%gh-ic%/plugins/performanceTesting/core/src/com/jetbrains/performancePlugin/profilers/Profiler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.performancePlugin.runCallbackHandler"/></include> ![Non-Dynamic][non-dynamic] | [`RunCallbackHandler`](%gh-ic%/plugins/performanceTesting/core/src/com/jetbrains/performancePlugin/RunCallbackHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.performancePlugin.snapshotOpener"/></include> | [`SnapshotOpener`](%gh-ic%/plugins/performanceTesting/core/src/com/jetbrains/performancePlugin/profilers/SnapshotOpener.java) |

### Coverage

[`Coverage`](%gh-ic%/plugins/coverage/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javaCoverageEngineExtension"/></include> | [`JavaCoverageEngineExtension`](%gh-ic%/plugins/coverage/src/com/intellij/coverage/JavaCoverageEngineExtension.java) |

### DesignerCorePlugin.xml

[`DesignerCorePlugin.xml`](%gh-ic%/plugins/ui-designer-core/resources/META-INF/DesignerCorePlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Designer.customizations"/></include> ![Non-Dynamic][non-dynamic] | [`DesignerCustomizations`](%gh-ic%/plugins/ui-designer-core/src/com/intellij/designer/DesignerCustomizations.java) |

### intellij.debugger.streams.core.xml

[`intellij.debugger.streams.core.xml`](%gh-ic%/plugins/stream-debugger-core/resources/intellij.debugger.streams.core.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.platform.debugger.streams.librarySupport"/></include> | [`LibrarySupportProvider`](%gh-ic%/plugins/stream-debugger-core/src/com/intellij/debugger/streams/core/lib/LibrarySupportProvider.java) |

### intellij.dev.codeInsight.xml

[`intellij.dev.codeInsight.xml`](%gh-ic%/plugins/dev/intellij.dev.codeInsight/resources/intellij.dev.codeInsight.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dev.lang.goodCodeRedVisitor"/></include> | [`GoodCodeRedVisitor`](%gh-ic%/plugins/dev/intellij.dev.codeInsight/src/internal/GoodCodeRedVisitor.java) |

### intellij.dev.psiViewer.xml

[`intellij.dev.psiViewer.xml`](%gh-ic%/plugins/dev/intellij.dev.psiViewer/resources/intellij.dev.psiViewer.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dev.psiViewer.apiMethodsProvider"/></include> | [`Provider`](%gh-ic%/plugins/dev/intellij.dev.psiViewer/src/properties/tree/nodes/apiMethods/PsiViewerApiMethod.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dev.psiViewer.extension"/></include> | [`PsiViewerExtension`](%gh-ic%/plugins/dev/intellij.dev.psiViewer/src/PsiViewerExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dev.psiViewer.propertyNodeAppender"/></include> | [`PsiViewerPropertyNodeAppender`](%gh-ic%/plugins/dev/intellij.dev.psiViewer/src/properties/tree/PsiViewerPropertyNodeHolder.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dev.psiViewer.propertyNodeFactory"/></include> | [`Factory`](%gh-ic%/plugins/dev/intellij.dev.psiViewer/src/properties/tree/PsiViewerPropertyNode.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.dev.psiViewer.psiViewerActionEnabler"/></include> | [`PsiViewerActionEnabler`](%gh-ic%/plugins/dev/intellij.dev.psiViewer/src/PsiViewerActionEnabler.kt) |

### intellij.devkit.core.xml

[`intellij.devkit.core.xml`](%gh-ic%/plugins/devkit/devkit-core/resources/intellij.devkit.core.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="DevKit.lang.addServiceAnnotationProvider"/></include> ![Internal][internal] | [`AddServiceAnnotationProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/quickfix/ConvertToLightServiceFix.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="DevKit.lang.appServiceAsStaticFinalFieldOrPropertyFixProvider"/></include> ![Internal][internal] | [`AppServiceAsStaticFinalFieldOrPropertyFixProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/quickfix/AppServiceAsStaticFinalFieldOrPropertyFixProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="DevKit.lang.appServiceAsStaticFinalFieldOrPropertyVisitorProvider"/></include> ![Internal][internal] | [`AppServiceAsStaticFinalFieldOrPropertyVisitorProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/ApplicationServiceAsStaticFinalFieldOrPropertyInspection.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="DevKit.lang.cancellationCheckInLoopsFixProvider"/></include> ![Internal][internal] | [`CancellationCheckInLoopsFixProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/quickfix/CancellationCheckInLoopsFixProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="DevKit.lang.cancellationCheckProvider"/></include> ![Internal][internal] | [`CancellationCheckProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/CancellationCheckProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="DevKit.lang.cancellationExceptionHandlingChecker"/></include> ![Internal][internal] | [`CancellationExceptionHandlingChecker`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/IncorrectCancellationExceptionHandlingInspection.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="DevKit.lang.extensionClassShouldBeFinalErrorMessageProvider"/></include> ![Internal][internal] | [`ErrorMessageProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/ExtensionClassShouldBeFinalErrorMessageProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="DevKit.lang.extensionClassShouldNotBePublicProvider"/></include> ![Internal][internal] | [`ExtensionClassShouldNotBePublicProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/ExtensionClassShouldNotBePublicProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="DevKit.lang.lightServiceMustBeFinalErrorMessageProvider"/></include> ![Internal][internal] | [`ErrorMessageProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/ExtensionClassShouldBeFinalErrorMessageProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="DevKit.lang.methodNameProvider"/></include> ![Internal][internal] | [`MethodNameProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/MethodNameProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="DevKit.lang.serviceLevelExtractor"/></include> ![Internal][internal] | [`ServiceLevelExtractor`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/ServiceLevelExtractor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="DevKit.lang.staticInitializationInExtensionsVisitorProvider"/></include> ![Internal][internal] | [`StaticInitializationInExtensionsVisitorProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/StaticInitializationInExtensionsInspection.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="DevKit.lang.uElementAsPsiCheckProvider"/></include> ![Internal][internal] | [`UElementAsPsiCheckProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/UElementAsPsiCheckProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="DevKit.lang.visitorProviderForRBCInspection"/></include> ![Internal][internal] | [`VisitorProvider`](%gh-ic%/plugins/devkit/devkit-core/src/inspections/CallingMethodShouldBeRequiresBlockingContextInspection.kt) |

### intellij.ide.startup.importSettings.xml

[`intellij.ide.startup.importSettings.xml`](%gh-ic%/plugins/ide-startup/importSettings/resources/intellij.ide.startup.importSettings.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.startupWizardPages"/></include> | [`StartupWizardService`](%gh-ic%/plugins/ide-startup/importSettings/src/com/intellij/ide/startup/importSettings/data/StartupWizardService.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.transferSettings.externalProjectImportChecker"/></include> | [`ExternalProjectImportChecker`](%gh-ic%/plugins/ide-startup/importSettings/src/com/intellij/ide/startup/importSettings/transfer/ExternalProjectImportChecker.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.transferSettings.thirdPartyProductSettingItem"/></include> | [`ThirdPartyProductSettingItemProvider`](%gh-ic%/plugins/ide-startup/importSettings/src/com/intellij/ide/startup/importSettings/transfer/ThirdPartyProductSettingItemProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.transferSettings.thirdPartyProductSettingsTransfer"/></include> | [`ThirdPartyProductSettingsTransfer`](%gh-ic%/plugins/ide-startup/importSettings/src/com/intellij/ide/startup/importSettings/transfer/ThirdPartyProductSettingsTransfer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.transferSettings.vscode.pluginMapping"/></include> | [`VSCodePluginMapping`](%gh-ic%/plugins/ide-startup/importSettings/src/com/intellij/ide/startup/importSettings/transfer/backend/providers/vscode/mappings/PluginMappings.kt) |

### intellij.performanceTesting.remoteDriver.xml

[`intellij.performanceTesting.remoteDriver.xml`](%gh-ic%/plugins/performanceTesting/remote-driver/resources/intellij.performanceTesting.remoteDriver.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.performancePlugin.remotedriver.textExtractorExtension"/></include> | [`TextExtractorExtension`](%gh-ic%/plugins/performanceTesting/remote-driver/src/com/jetbrains/performancePlugin/remotedriver/dataextractor/TextExtractorExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.performancePlugin.remotedriver.xpathDataModelExtension"/></include> | [`XpathDataModelExtension`](%gh-ic%/plugins/performanceTesting/remote-driver/src/com/jetbrains/performancePlugin/remotedriver/xpath/XpathDataModelExtension.kt) |

### intellij.platform.coverage.xml

[`intellij.platform.coverage.xml`](%gh-ic%/plugins/coverage-common/resources/intellij.platform.coverage.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.coverageEngine"/></include> | [`CoverageEngine`](%gh-ic%/plugins/coverage-common/src/com/intellij/coverage/CoverageEngine.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.coverageModifiedFilesFilterFactory"/></include> ![Internal][internal] | [`ModifiedFilesFilterFactory`](%gh-ic%/plugins/coverage-common/src/com/intellij/coverage/filters/ModifiedFilesFilterFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.coverageOptions"/></include> ![Project-Level][project-level] | [`CoverageOptions`](%gh-ic%/plugins/coverage-common/src/com/intellij/coverage/CoverageOptions.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.coverageRunner"/></include> | [`CoverageRunner`](%gh-ic%/plugins/coverage-common/src/com/intellij/coverage/CoverageRunner.java) |

### intellij.searchEverywhereMl.ranking.core.xml

[`intellij.searchEverywhereMl.ranking.core.xml`](%gh-ic%/plugins/search-everywhere-ml/ranking/core/resources/intellij.searchEverywhereMl.ranking.core.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searcheverywhere.ml.rankingModelLoader"/></include> | [`SearchEverywhereMLRankingModelLoader`](%gh-ic%/plugins/search-everywhere-ml/ranking/core/src/com/intellij/searchEverywhereMl/ranking/core/model/SearchEverywhereMLRankingModelLoader.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searcheverywhere.ml.searchEverywhereElementFeaturesProvider"/></include> ![Internal][internal] | [`SearchEverywhereElementFeaturesProvider`](%gh-ic%/plugins/search-everywhere-ml/ranking/core/src/com/intellij/searchEverywhereMl/ranking/core/features/SearchEverywhereElementFeaturesProvider.kt) |

### intellij.searchEverywhereMl.ranking.ext.xml

[`intellij.searchEverywhereMl.ranking.ext.xml`](%gh-ic%/plugins/search-everywhere-ml/ranking/ext/resources/intellij.searchEverywhereMl.ranking.ext.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.searchEverywhereMl.searchEverywhereElementKeyProvider"/></include> ![Internal][internal] | [`SearchEverywhereElementKeyProvider`](%gh-ic%/plugins/search-everywhere-ml/ranking/ext/src/com/intellij/searchEverywhereMl/ranking/ext/SearchEverywhereElementKeyProvider.kt) |

### intellij.toml.json.xml

[`intellij.toml.json.xml`](%gh-ic%/plugins/toml/json/src/main/resources/intellij.toml.json.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.toml.ide.json.tomlJsonSchemaCompletionFileFilter"/></include> | [`TomlJsonSchemaCompletionFileFilter`](%gh-ic%/plugins/toml/json/src/main/kotlin/org/toml/ide/json/TomlJsonSchemaCompletionFileFilter.kt) |

### intellij.yaml.backend.xml

[`intellij.yaml.backend.xml`](%gh-ic%/plugins/yaml/backend/resources/intellij.yaml.backend.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.yaml.customStructureViewFactory"/></include> | [`YAMLCustomStructureViewFactory`](%gh-ic%/plugins/yaml/backend/src/structureView/YAMLCustomStructureViewFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.yaml.tagRecogniser"/></include> ![Experimental][experimental] | [`YamlTagRecogniser`](%gh-ic%/plugins/yaml/src/psi/YamlTagRecogniser.kt) |

### JUnit

[`JUnit`](%gh-ic%/plugins/junit/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.junitListener"/></include> | [`IDEAJUnitListener`](%gh-ic%/java/java-runtime/src/com/intellij/rt/execution/junit/IDEAJUnitListener.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.testDiscoveryProducer"/></include> | [`TestDiscoveryProducer`](%gh-ic%/java/execution/impl/src/com/intellij/execution/testDiscovery/TestDiscoveryProducer.java) |

### org.intellij.groovy

[`org.intellij.groovy`](%gh-ic%/plugins/groovy/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.newProjectWizard.groovy.buildSystem"/></include> | [`BuildSystemGroovyNewProjectWizard`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/config/wizard/BuildSystemGroovyNewProjectWizard.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.applicabilityProvider"/></include> ![Experimental][experimental] | [`GroovyApplicabilityProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyApplicabilityProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.astTransformationSupport"/></include> ![DumbAware][dumb-aware] | [`AstTransformationSupport`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/transformations/AstTransformationSupport.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.callTypeCalculator"/></include> ![Experimental][experimental] | [`GrCallTypeCalculator`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/typing/GrCallTypeCalculator.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.classDescriptor"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.closureCompleter"/></include> | [`ClosureCompleter`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/lang/completion/ClosureCompleter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.closureMissingMethodContributor"/></include> | [`ClosureMissingMethodContributor`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/ClosureMissingMethodContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.completionCustomizer"/></include> ![Experimental][experimental] | [`GroovyCompletionCustomizer`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/lang/completion/api/GroovyCompletionCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.configSlurperSupport"/></include> | [`ConfigSlurperSupport`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/configSlurper/ConfigSlurperSupport.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.convertToJava.customMethodInvocator"/></include> | [`CustomMethodInvocator`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/refactoring/convertToJava/invocators/CustomMethodInvocator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.customAnnotationChecker"/></include> | [`CustomAnnotationChecker`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/annotator/checkers/CustomAnnotationChecker.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.delegatesToProvider"/></include> | [`GrDelegatesToProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/delegatesTo/GrDelegatesToProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.elementFilter"/></include> ![Experimental][experimental] | [`GroovyElementFilter`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/GroovyElementFilter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.expectedPackageNameProvider"/></include> | [`ExpectedPackageNameProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/ExpectedPackageNameProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.expectedTypesContributor"/></include> | [`GroovyExpectedTypesContributor`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/expectedTypes/GroovyExpectedTypesContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.gdslScriptProvider"/></include> | [`GdslScriptProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/dsl/GdslScriptProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.gdslTopLevelProvider"/></include> ![Non-Dynamic][non-dynamic] | [`GdslMembersProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/dsl/dsltop/GdslMembersProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.groovyFrameworkConfigNotification"/></include> | [`GroovyFrameworkConfigNotification`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/annotator/GroovyFrameworkConfigNotification.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.groovySourceFolderDetector"/></include> | [`GroovySourceFolderDetector`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/actions/GroovySourceFolderDetector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.importContributor"/></include> | [`GrImportContributor`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/imports/GrImportContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.inlayHintFilter"/></include> | [`GroovyInlayHintFilter`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/editor/GroovyInlayHintFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.inlineASTTransformationSupport"/></include> ![Experimental][experimental] | [`GroovyInlineASTTransformationSupport`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/transformations/inline/GroovyInlineASTTransformationSupport.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.inspectionDisabler"/></include> | [`FileTypeInspectionDisabler`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/codeInspection/FileTypeInspectionDisabler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.mapContentProvider"/></include> | [`GroovyMapContentProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyMapContentProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.membersContributor"/></include> | [`NonCodeMembersContributor`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/NonCodeMembersContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.methodComparator"/></include> | [`GrMethodComparator`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/GrMethodComparator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.methodDescriptor"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.methodMayBeStaticInspectionFilter"/></include> | [`GrMethodMayBeStaticInspectionFilter`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/codeInspection/declaration/GrMethodMayBeStaticInspectionFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.namedArgumentProvider"/></include> | [`GroovyNamedArgumentProvider`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyNamedArgumentProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.overloadResolver"/></include> | [`GroovyOverloadResolver`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/resolve/api/GroovyOverloadResolver.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.positionManagerDelegate"/></include> | [`ScriptPositionManagerHelper`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/extensions/debugger/ScriptPositionManagerHelper.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.psiEnhancerCategory"/></include> ![Non-Dynamic][non-dynamic] | [`PsiEnhancerCategory`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/dsl/psi/PsiEnhancerCategory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.referenceTypeEnhancer"/></include> | [`GrReferenceTypeEnhancer`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/typeEnhancers/GrReferenceTypeEnhancer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.renameHelper"/></include> | [`GrRenameHelper`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/refactoring/rename/GrRenameHelper.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.scriptTypeDetector"/></include> | [`GroovyScriptTypeDetector`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyScriptTypeDetector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.signatureHintProcessor"/></include> | [`SignatureHintProcessor`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/typeEnhancers/SignatureHintProcessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.typeAugmenter"/></include> | [`TypeAugmenter`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/dataFlow/types/TypeAugmenter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.typeCalculator"/></include> | [`GrTypeCalculator`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/typing/GrTypeCalculator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.typeConverter"/></include> | [`GrTypeConverter`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/typeEnhancers/GrTypeConverter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.unresolvedHighlightFileFilter"/></include> | [`GroovyUnresolvedHighlightFileFilter`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyUnresolvedHighlightFileFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.unresolvedHighlightFilter"/></include> | [`GroovyUnresolvedHighlightFilter`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/extensions/GroovyUnresolvedHighlightFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.groovy.variableEnhancer"/></include> | [`GrVariableEnhancer`](%gh-ic%/plugins/groovy/groovy-psi/src/org/jetbrains/plugins/groovy/lang/psi/typeEnhancers/GrVariableEnhancer.java) |

### org.intellij.intelliLang

[`org.intellij.intelliLang`](%gh-ic%/plugins/IntelliLang/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.intelliLang.injectionConfig"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.intelliLang.languageSupport"/></include> | [`LanguageInjectionSupport`](%gh-ic%/plugins/IntelliLang/src/org/intellij/plugins/intelliLang/inject/LanguageInjectionSupport.java) |

### org.jetbrains.idea.eclipse

[`org.jetbrains.idea.eclipse`](%gh-ic%/plugins/eclipse/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.eclipse.natureImporter"/></include> | [`EclipseNatureImporter`](%gh-ic%/plugins/eclipse/src/org/jetbrains/idea/eclipse/importWizard/EclipseNatureImporter.java) |

### org.jetbrains.idea.reposearch

[`org.jetbrains.idea.reposearch`](%gh-ic%/plugins/repository-search/src/main/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.reposearch.provider"/></include> ![Experimental][experimental] | [`DependencySearchProvidersFactory`](%gh-ic%/plugins/repository-search/src/main/java/org/jetbrains/idea/reposearch/DependencySearchProvidersFactory.java) |

### org.jetbrains.plugins.textmate

[`org.jetbrains.plugins.textmate`](%gh-ic%/plugins/textmate/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.textmate.bundleProvider"/></include> ![Non-Dynamic][non-dynamic] | [`TextMateBundleProvider`](%gh-ic%/plugins/textmate/src/org/jetbrains/plugins/textmate/api/TextMateBundleProvider.kt) |

### ru.adelf.idea.dotenv

[`ru.adelf.idea.dotenv`](%gh-ic%/plugins/env-files-support/src/main/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="ru.adelf.idea.dotenv.environmentVariablesProvider"/></include> | [`EnvironmentVariablesProvider`](%gh-ic%/plugins/env-files-support/src/main/java/ru/adelf/idea/dotenv/api/EnvironmentVariablesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="ru.adelf.idea.dotenv.environmentVariablesUsagesProvider"/></include> | [`EnvironmentVariablesUsagesProvider`](%gh-ic%/plugins/env-files-support/src/main/java/ru/adelf/idea/dotenv/api/EnvironmentVariablesUsagesProvider.java) |

### sh.xml

[`sh.xml`](%gh-ic%/plugins/sh/core/resources/META-INF/sh.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.runMarkerContributionAdditionalCondition"/></include> | [`ShRunnerAdditionalCondition`](%gh-ic%/plugins/sh/core/src/com/intellij/sh/run/ShRunnerAdditionalCondition.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.shellOccurrencesHighlightingSuppressor"/></include> | [`ShOccurrencesHighlightingSuppressor`](%gh-ic%/plugins/sh/core/src/com/intellij/sh/highlighting/ShOccurrencesHighlightingSuppressor.kt) |

### TestNG-J

[`TestNG-J`](%gh-ic%/plugins/testng/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.theoryinpractice.testng.listener"/></include> | [`IDEATestNGListener`](%gh-ic%/plugins/testng_rt/src/com/intellij/rt/testng/IDEATestNGListener.java) |

### XPathView

[`XPathView`](%gh-ic%/plugins/xpath/xpath-lang/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="XPathView.xpath.contextProviderExtension"/></include> | [`ContextProviderExtension`](%gh-ic%/plugins/xpath/xpath-lang/src/org/intellij/lang/xpath/context/ContextProviderExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="XPathView.xpath.functionProvider"/></include> | [`XPathFunctionProvider`](%gh-ic%/plugins/xpath/xpath-lang/src/org/intellij/lang/xpath/context/functions/XPathFunctionProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="XPathView.xsltRunnerExtension"/></include> | [`XsltRunnerExtension`](%gh-ic%/plugins/xpath/xpath-lang/src/org/intellij/lang/xpath/xslt/run/XsltRunnerExtension.java) |


## Ant Plugin

### Ant Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`AntExecutionListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.lang.ant.config.execution.AntExecutionListener)  | [`AntExecutionListener`](%gh-ic%/plugins/ant/src/com/intellij/lang/ant/config/execution/AntExecutionListener.java) |


### AntSupport

[`AntSupport`](%gh-ic%/plugins/ant/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="AntSupport.AntMessageCustomizer"/></include> | [`AntMessageCustomizer`](%gh-ic%/plugins/ant/src/com/intellij/lang/ant/config/execution/AntMessageCustomizer.java) |


## EditorConfig Plugin

### EditorConfig Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`EditorConfigSettings#EDITOR_CONFIG_ENABLED_TOPIC`](https://jb.gg/ipe/listeners?topics=org.editorconfig.settings.EditorConfigListener)  | [`EditorConfigListener`](%gh-ic%/plugins/editorconfig/src/org/editorconfig/settings/EditorConfigListener.java) |


### org.editorconfig.editorconfigjetbrains

[`org.editorconfig.editorconfigjetbrains`](%gh-ic%/plugins/editorconfig/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="editorconfig.exportProvider"/></include> ![Non-Dynamic][non-dynamic] | [`EditorConfigExportProvider`](%gh-ic%/plugins/editorconfig/src/org/editorconfig/settings/EditorConfigExportProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="editorconfig.optionDescriptorProvider"/></include> ![Non-Dynamic][non-dynamic] | [`EditorConfigOptionDescriptorProvider`](%gh-ic%/plugins/editorconfig/src/org/editorconfig/language/extensions/EditorConfigOptionDescriptorProvider.kt) |


## Gradle Plugin

### Gradle Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`GradleSettingsListener#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.gradle.settings.GradleSettingsListener)  ![Project-Level][project-level] | [`GradleSettingsListener`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/settings/GradleSettingsListener.java) |
| [`GradleUiListener#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.gradle.ui.GradleUiListener)  | [`GradleUiListener`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/ui/GradleUiListener.java) |


### com.intellij.gradle

[`com.intellij.gradle`](%gh-ic%/plugins/gradle/plugin-resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.autoReloadSettingsCollector"/></include> ![Internal][internal] | [`GradleAutoReloadSettingsCollector`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/project/GradleAutoReloadSettingsCollector.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.executionEnvironmentProvider"/></include> | [`GradleExecutionEnvironmentProvider`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/execution/build/GradleExecutionEnvironmentProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.executionHelperExtension"/></include> ![Internal][internal] | [`GradleExecutionHelperExtension`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/project/GradleExecutionHelperExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.gradleJvmResolver"/></include> ![Experimental][experimental] | [`GradleJvmResolver`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/resolvers/GradleJvmResolver.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.importCustomizer"/></include> | [`GradleImportCustomizer`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/project/GradleImportCustomizer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.issueChecker"/></include> | [`GradleIssueChecker`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/issue/GradleIssueChecker.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.orderEnumerationHandlerFactory"/></include> | [`FactoryImpl`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/execution/GradleOrderEnumeratorHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.previewCustomizer"/></include> ![Experimental][experimental] | [`GradlePreviewCustomizer`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/project/GradlePreviewCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.projectResolve"/></include> | [`GradleProjectResolverExtension`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/project/GradleProjectResolverExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.settingsControlProvider"/></include> | [`GradleSettingsControlProvider`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/settings/GradleSettingsControlProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.syncContributor"/></include> ![Experimental][experimental] | [`GradleSyncContributor`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/syncAction/GradleSyncContributor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.targetEnvironmentAware"/></include> ![Internal][internal] | [`GradleTargetEnvironmentAware`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/execution/target/GradleTargetEnvironmentAware.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.taskManager"/></include> | [`GradleTaskManagerExtension`](%gh-ic%/plugins/gradle/src/org/jetbrains/plugins/gradle/service/task/GradleTaskManagerExtension.java) |

### gradle-groovy-integration.xml

[`gradle-groovy-integration.xml`](%gh-ic%/plugins/gradle/java/resources/META-INF/gradle-groovy-integration.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.pluginDescriptions"/></include> | [`GradlePluginDescriptionsExtension`](%gh-ic%/plugins/gradle/java/src/codeInsight/GradlePluginDescriptionsExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.resolve.contributor"/></include> | [`GradleMethodContextContributor`](%gh-ic%/plugins/gradle/java/src/service/resolve/GradleMethodContextContributor.java) |

### org.jetbrains.plugins.gradle

[`org.jetbrains.plugins.gradle`](%gh-ic%/plugins/gradle/java/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.buildTasksProvider"/></include> | [`GradleBuildTasksProvider`](%gh-ic%/plugins/gradle/java/src/execution/build/GradleBuildTasksProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.dslInspectionProvider"/></include> ![Internal][internal] | [`GradleDslInspectionProvider`](%gh-ic%/plugins/gradle/java/src/codeInspection/GradleDslInspectionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.externallyHandledExtensions"/></include> ![Internal][internal] | [`GradleVersionCatalogHandler`](%gh-ic%/plugins/gradle/java/src/service/resolve/GradleVersionCatalogHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.frameworkSupport"/></include> | [`GradleFrameworkSupportProvider`](%gh-ic%/plugins/gradle/java/src/frameworkSupport/GradleFrameworkSupportProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.initScriptGenerator"/></include> ![Experimental][experimental] | [`GradleInitScriptGenerator`](%gh-ic%/plugins/gradle/java/src/execution/build/GradleInitScriptGenerator.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.kotlinDslFrameworkSupport"/></include> | [`KotlinDslGradleFrameworkSupportProvider`](%gh-ic%/plugins/gradle/java/src/frameworkSupport/KotlinDslGradleFrameworkSupportProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.gradle.testTasksProvider"/></include> | [`GradleTestTasksProvider`](%gh-ic%/plugins/gradle/java/src/execution/test/runner/GradleTestTasksProvider.java) |


## Grazie Plugin

### Grazie Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`GrazieStateLifecycleKt#CONFIG_STATE_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.grazie.ide.msg.GrazieStateLifecycle)  | [`GrazieStateLifecycle`](%gh-ic%/plugins/grazie/src/main/kotlin/com/intellij/grazie/ide/msg/GrazieStateLifecycle.kt) |


### tanvd.grazi

[`tanvd.grazi`](%gh-ic%/plugins/grazie/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.grazie.disableChecking"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.grazie.grammar.strategy"/></include> ![Deprecated][deprecated] | [`GrammarCheckingStrategy`](%gh-ic%/plugins/grazie/src/main/kotlin/com/intellij/grazie/grammar/strategy/GrammarCheckingStrategy.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.grazie.problemFilter"/></include> | [`ProblemFilter`](%gh-ic%/plugins/grazie/src/main/kotlin/com/intellij/grazie/text/ProblemFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.grazie.textChecker"/></include> | [`TextChecker`](%gh-ic%/plugins/grazie/src/main/kotlin/com/intellij/grazie/text/TextChecker.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.grazie.textContentModificationTrackerProvider"/></include> | [`TextContentModificationTrackerProvider`](%gh-ic%/plugins/grazie/src/main/kotlin/com/intellij/grazie/text/TextContentModificationTrackerProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.grazie.textExtractor"/></include> | [`TextExtractor`](%gh-ic%/plugins/grazie/src/main/kotlin/com/intellij/grazie/text/TextExtractor.java) |


## IDE Features Trainer Plugin

### IDE Features Trainer Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`FeatureSuggestersManagerListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=training.featuresSuggester.FeatureSuggestersManagerListener)  | [`FeatureSuggestersManagerListener`](%gh-ic%/plugins/ide-features-trainer/src/training/featuresSuggester/FeatureSuggestersManagerListener.kt) |


### training

[`training`](%gh-ic%/plugins/ide-features-trainer/res/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="training.ifs.suggester"/></include> | [`FeatureSuggester`](%gh-ic%/plugins/ide-features-trainer/src/training/featuresSuggester/suggesters/FeatureSuggester.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="training.ifs.suggesterSupport"/></include> | [`SuggesterSupport`](%gh-ic%/plugins/ide-features-trainer/src/training/featuresSuggester/SuggesterSupport.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="training.ift.language.extension"/></include> | [`LangSupport`](%gh-ic%/plugins/ide-features-trainer/src/training/lang/LangSupport.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="training.ift.learning.commonCourse"/></include> | [`LearningCourse`](%gh-ic%/plugins/ide-features-trainer/src/training/learn/course/LearningCourse.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="training.ift.learning.course"/></include> | [`LearningCourseBase`](%gh-ic%/plugins/ide-features-trainer/src/training/learn/course/LearningCourseBase.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="training.ift.newUsersOnboardingExperimentAccessor"/></include> ![Internal][internal] | [`NewUsersOnboardingExperimentAccessor`](%gh-ic%/plugins/ide-features-trainer/src/training/learn/NewUsersOnboardingExperimentAccessor.kt) |


## Java Plugin

### Java Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`ExternalAnnotationsManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.codeInsight.ExternalAnnotationsListener)  ![Project-Level][project-level] | [`ExternalAnnotationsListener`](%gh-ic%/java/java-psi-api/src/com/intellij/codeInsight/ExternalAnnotationsListener.java) |
| [`BuildManagerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.compiler.server.BuildManagerListener)  | [`BuildManagerListener`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/server/BuildManagerListener.java) |
| [`CustomBuilderMessageHandler#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.compiler.server.CustomBuilderMessageHandler)  ![Project-Level][project-level] | [`CustomBuilderMessageHandler`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/server/CustomBuilderMessageHandler.java) |
| [`PortableCachesLoadListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.compiler.server.PortableCachesLoadListener)  ![Internal][internal] ![Project-Level][project-level] | [`PortableCachesLoadListener`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/server/PortableCachesLoadListener.java) |
| [`DebuggerActionListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.debugger.engine.DebuggerActionListener)  ![Internal][internal] ![Project-Level][project-level] | [`DebuggerActionListener`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/DebuggerActionListener.kt) |
| [`DebuggerManagerListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.debugger.impl.DebuggerManagerListener)  ![Project-Level][project-level] | [`DebuggerManagerListener`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/impl/DebuggerManagerListener.java) |
| [`StarterModuleProcessListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.ide.starters.StarterModuleProcessListener)  ![Project-Level][project-level] | [`StarterModuleProcessListener`](%gh-ic%/java/idea-ui/src/com/intellij/ide/starters/StarterModuleProcessListener.kt) |
| [`CompilerTopics#COMPILATION_STATUS`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.compiler.CompilationStatusListener)  ![Project-Level][project-level] | [`CompilationStatusListener`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/CompilationStatusListener.java) |
| [`ExcludedEntriesListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.compiler.options.ExcludedEntriesListener)  ![Project-Level][project-level] | [`ExcludedEntriesListener`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/options/ExcludedEntriesListener.java) |
| [`LanguageLevelProjectExtension#LANGUAGE_LEVEL_CHANGED_TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.LanguageLevelProjectExtension.LanguageLevelChangeListener)  | [`LanguageLevelChangeListener`](%gh-ic%/java/java-frontback-psi-api/src/com/intellij/openapi/roots/LanguageLevelProjectExtension.java) |
| [`VirtualFileJavaLanguageLevelListener#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.impl.VirtualFileJavaLanguageLevelListener)  ![Project-Level][project-level] | [`VirtualFileJavaLanguageLevelListener`](%gh-ic%/java/java-analysis-impl/src/com/intellij/openapi/roots/impl/VirtualFileJavaLanguageLevelListener.kt) |
| [`ConfigurationErrors#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.openapi.roots.ui.configuration.ConfigurationErrors)  | [`ConfigurationErrors`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/ConfigurationErrors.java) |
| [`ArtifactManager#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.packaging.artifacts.ArtifactListener)  ![Project-Level][project-level] | [`ArtifactListener`](%gh-ic%/java/compiler/openapi/src/com/intellij/packaging/artifacts/ArtifactListener.java) |


### InspectionGadgets.xml

[`InspectionGadgets.xml`](%gh-ic%/java/java-impl/resources/META-INF/InspectionGadgets.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.implicit.resource.closer"/></include> | [`ImplicitResourceCloser`](%gh-ic%/java/java-analysis-impl/src/com/intellij/codeInspection/resources/ImplicitResourceCloser.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.naming.convention.class"/></include> | [`NamingConvention`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInspection/naming/NamingConvention.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.naming.convention.field"/></include> | [`NamingConvention`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInspection/naming/NamingConvention.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.naming.convention.method"/></include> | [`NamingConvention`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInspection/naming/NamingConvention.java) |

### intellij.java.frontback.impl.xml

[`intellij.java.frontback.impl.xml`](%gh-ic%/java/java-frontback-impl/resource/intellij.java.frontback.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.java.definitions"/></include> ![Experimental][experimental] | [`AbstractBasicJavaDefinitionService`](%gh-ic%/java/java-frontback-impl/src/com/intellij/codeInsight/definition/AbstractBasicJavaDefinitionService.java) |

### intellij.java.remoteServers.impl.xml

[`intellij.java.remoteServers.impl.xml`](%gh-ic%/java/remote-servers/impl/resources/intellij.java.remoteServers.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.remoteServer.moduleBuilderContribution"/></include> ![Non-Dynamic][non-dynamic] | [`CloudModuleBuilderContributionFactory`](%gh-ic%/java/remote-servers/impl/src/com/intellij/remoteServer/impl/module/CloudModuleBuilderContributionFactory.java) |

### intellij.jvm.analysis.impl.xml

[`intellij.jvm.analysis.impl.xml`](%gh-ic%/jvm/jvm-analysis-impl/resources/intellij.jvm.analysis.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.blockingMethodChecker"/></include> | [`BlockingMethodChecker`](%gh-ic%/jvm/jvm-analysis-api/src/com/intellij/codeInspection/blockingCallsDetection/BlockingMethodChecker.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.nonBlockingContextChecker"/></include> | [`NonBlockingContextChecker`](%gh-ic%/jvm/jvm-analysis-api/src/com/intellij/codeInspection/blockingCallsDetection/NonBlockingContextChecker.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInspection.sourceToSinkProvider"/></include> | [`SourceToSinkProvider`](%gh-ic%/jvm/jvm-analysis-impl/src/com/intellij/codeInspection/sourceToSink/SourceToSinkLangugeProvider.kt) |

### java-debugger.xml

[`java-debugger.xml`](%gh-ic%/java/debugger/impl/resources/META-INF/java-debugger.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.additionalContextProvider"/></include> ![Internal][internal] | [`AdditionalContextProvider`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/evaluation/EvaluationContextWrapper.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.asyncStackTraceProvider"/></include> | [`AsyncStackTraceProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/AsyncStackTraceProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.codeFragmentFactory"/></include> | [`CodeFragmentFactory`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/evaluation/CodeFragmentFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.compoundRendererProvider"/></include> | [`CompoundRendererProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/ui/tree/render/CompoundRendererProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.creationStackTraceProvider"/></include> ![Internal][internal] | [`CreationStackTraceProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/CreationStackTraceProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.dfaAssistProvider"/></include> | [`DfaAssistProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/dfaassist/DfaAssistProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.dumpItemsProvider"/></include> ![Internal][internal] | [`ThreadDumpItemsProviderFactory`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/impl/ThreadDumpItemsProviderFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.extraDebugNodesProvider"/></include> ![Experimental][experimental] | [`ExtraDebugNodesProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/ui/tree/ExtraDebugNodesProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.extraSteppingFilter"/></include> | [`ExtraSteppingFilter`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/ExtraSteppingFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.fieldVisibilityProvider"/></include> | [`FieldVisibilityProvider`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/FieldVisibilityProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.frameExtraVarsProvider"/></include> | [`FrameExtraVariablesProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/FrameExtraVariablesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.javaBreakpointHandlerFactory"/></include> | [`JavaBreakpointHandlerFactory`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/JavaBreakpointHandlerFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.javaDebugAware"/></include> | [`JavaDebugAware`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/JavaDebugAware.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.jdiHelperClassLoader"/></include> | [`JdiHelperClassLoader`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/impl/JdiHelperClassLoader.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.jvmSmartStepIntoHandler"/></include> | [`JvmSmartStepIntoHandler`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/actions/JvmSmartStepIntoHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.jvmSteppingCommandProvider"/></include> | [`JvmSteppingCommandProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/impl/JvmSteppingCommandProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.nodeNameAdjuster"/></include> | [`NodeDescriptorNameAdjuster`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/ui/tree/NodeDescriptorNameAdjuster.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.nodeRenderer"/></include> | [`NodeRenderer`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/ui/tree/render/NodeRenderer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.positionManagerFactory"/></include> | [`PositionManagerFactory`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/PositionManagerFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.simplePropertyGetterProvider"/></include> | [`SimplePropertyGetterProvider`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/SimplePropertyGetterProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.sourcePositionHighlighter"/></include> ![DumbAware][dumb-aware] | [`SourcePositionHighlighter`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/SourcePositionHighlighter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.sourcePositionProvider"/></include> | [`SourcePositionProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/SourcePositionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.steppingListener"/></include> ![Internal][internal] | [`SteppingListener`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/engine/SteppingListener.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.syntheticProvider"/></include> | [`SyntheticTypeComponentProvider`](%gh-ic%/java/debugger/openapi/src/com/intellij/debugger/engine/SyntheticTypeComponentProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debuggerEditorTextProvider"/></include> | [`EditorTextProvider`](%gh-ic%/java/debugger/impl/src/com/intellij/debugger/impl/EditorTextProvider.java) |

### JavaAnalysisPlugin.xml

[`JavaAnalysisPlugin.xml`](%gh-ic%/java/java-analysis-impl/resources/META-INF/JavaAnalysisPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.allowedApiFilter"/></include> | [`AllowedApiFilterExtension`](%gh-ic%/java/java-analysis-impl/src/com/intellij/psi/impl/AllowedApiFilterExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.canBeFinal"/></include> | [`CanBeFinalHandler`](%gh-ic%/java/java-analysis-impl/src/com/intellij/codeInspection/canBeFinal/CanBeFinalHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.changeVariableTypeQuickFixProvider"/></include> | [`ChangeVariableTypeQuickFixProvider`](%gh-ic%/java/java-analysis-api/src/com/intellij/codeInsight/quickfix/ChangeVariableTypeQuickFixProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.implicitSubclassProvider"/></include> | [`ImplicitSubclassProvider`](%gh-ic%/java/java-analysis-impl/src/com/intellij/codeInspection/inheritance/ImplicitSubclassProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.deadCode"/></include> | [`EntryPoint`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/reference/EntryPoint.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.injectedLanguageJavaReferenceSupplier"/></include> ![Experimental][experimental] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.java.error.fix"/></include> | [`CommonIntentionAction`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/intention/CommonIntentionAction.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javaLanguageLevelPusherCustomizer"/></include> ![Internal][internal] | [`JavaLanguageLevelPusherCustomizer`](%gh-ic%/java/java-analysis-impl/src/com/intellij/openapi/roots/impl/JavaLanguageLevelPusherCustomizer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.jvm.actions.jvmElementActionsFactory"/></include> | [`JvmElementActionsFactory`](%gh-ic%/java/java-analysis-api/src/com/intellij/lang/jvm/actions/JvmElementActionsFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.jvm.annotationPackageSupport"/></include> | [`AnnotationPackageSupport`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/annoPackages/AnnotationPackageSupport.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.jvm.annotations.marker.suppressor"/></include> | [`NonCodeAnnotationsMarkerSuppressor`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/NonCodeAnnotationsMarkerSuppressor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.jvm.bytecodeAnalysisSuppressor"/></include> | [`BytecodeAnalysisSuppressor`](%gh-ic%/java/java-analysis-impl/src/com/intellij/codeInspection/bytecodeAnalysis/BytecodeAnalysisSuppressor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.jvm.ignoreAnnotationParamSupport"/></include> | [`IgnoreAnnotationParamSupport`](%gh-ic%/java/java-impl-inspections/src/com/intellij/codeInspection/DefaultAnnotationParamInspection.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.jvm.ignoreVariableCanBeFinalSupport"/></include> | [`IgnoreVariableCanBeFinalSupport`](%gh-ic%/java/java-analysis-impl/src/com/intellij/codeInspection/localCanBeFinal/IgnoreVariableCanBeFinalSupport.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.propertyAccessorDetector"/></include> | [`PropertyAccessorDetector`](%gh-ic%/java/java-analysis-impl/src/com/intellij/psi/util/PropertyAccessorDetector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.visibility"/></include> | [`VisibilityExtension`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInspection/visibility/VisibilityExtension.java) |

### JavaIndexingPlugin.xml

[`JavaIndexingPlugin.xml`](%gh-ic%/java/java-indexing-impl/resources/META-INF/JavaIndexingPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.allClassesSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.allOverridingMethodsSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.annotatedElementsSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.annotatedPackagesSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.classInheritorsSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.classesWithAnnotatedMembersSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.customPropertyScopeProvider"/></include> | [`CustomPropertyScopeProvider`](%gh-ic%/java/java-indexing-impl/src/com/intellij/psi/impl/search/CustomPropertyScopeProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.directClassInheritorsSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.functionalExpressionSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.implicitClassSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.implicitToStringSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.java.shortNamesCache"/></include> ![Project-Level][project-level] | [`PsiShortNamesCache`](%gh-ic%/java/java-indexing-api/src/com/intellij/psi/search/PsiShortNamesCache.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.java.staticMethodNamesCache"/></include> ![Experimental][experimental] ![Project-Level][project-level] | [`JavaStaticMethodNameCache`](%gh-ic%/java/java-indexing-api/src/com/intellij/psi/search/JavaStaticMethodNameCache.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javaModuleSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.methodReferencesSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.overridingMethodsSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |

### JavaPlugin.xml

[`JavaPlugin.xml`](%gh-ic%/java/java-impl/resources/META-INF/JavaPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.OrderRootTypeUI"/></include> | [`OrderRootTypeUIFactory`](%gh-ic%/platform/lang-api/src/com/intellij/openapi/roots/ui/OrderRootTypeUIFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.attachSourcesProvider"/></include> | [`AttachSourcesProvider`](%gh-ic%/java/openapi/src/com/intellij/codeInsight/AttachSourcesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.buildProcess.parametersProvider"/></include> ![Project-Level][project-level] | [`BuildProcessParametersProvider`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/server/BuildProcessParametersProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.canBeEmpty"/></include> | [`Condition`](%gh-ic%/platform/util/src/com/intellij/openapi/util/Condition.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.createFieldFromUsageHelper"/></include> | [`CreateFieldFromUsageHelper`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/daemon/impl/quickfix/CreateFieldFromUsageHelper.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeInsight.externalLibraryResolver"/></include> | [`ExternalLibraryResolver`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/daemon/quickFix/ExternalLibraryResolver.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.compilableFileTypesProvider"/></include> ![Project-Level][project-level] | [`CompilableFileTypesProvider`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/CompilableFileTypesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.compileServer.plugin"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.compiler"/></include> ![Deprecated][deprecated] ![Project-Level][project-level] | [`Compiler`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/Compiler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.compiler.buildIssueContributor"/></include> ![Experimental][experimental] | [`BuildIssueContributor`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/progress/BuildIssueContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.compiler.buildTargetScopeProvider"/></include> | [`BuildTargetScopeProvider`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/impl/BuildTargetScopeProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.compiler.inspectionValidator"/></include> ![Project-Level][project-level] | [`InspectionValidator`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/util/InspectionValidator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.compiler.isUpToDateCheckConsumer"/></include> | [`IsUpToDateCheckConsumer`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/backwardRefs/IsUpToDateCheckConsumer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.compiler.optionsManager"/></include> | [`CompilerOptionsFilter`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/options/CompilerOptionsFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.compiler.task"/></include> ![Project-Level][project-level] | [`CompileTask`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/CompileTask.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.compiler.updateResourcesBuildContributor"/></include> | [`UpdateResourcesBuildContributor`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/impl/UpdateResourcesBuildContributor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.compilerFactory"/></include> ![Deprecated][deprecated] ![Removal][removal] ![Project-Level][project-level] | [`CompilerFactory`](%gh-ic%/java/compiler/openapi/src/com/intellij/openapi/compiler/CompilerFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.configuration.ModuleStructureExtension"/></include> | [`ModuleStructureExtension`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/projectRoot/ModuleStructureExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.configuration.moduleStructureFilterExtension"/></include> | [`ModuleStructureFilterExtension`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/projectRoot/ModuleStructureFilterExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.constructorBodyGenerator"/></include> ![Internal][internal] | [`ConstructorBodyGenerator`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/generation/ConstructorBodyGenerator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.conversion.rule"/></include> | [`TypeConversionRule`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/typeMigration/rules/TypeConversionRule.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debuggerClassFilterProvider"/></include> | [`DebuggerClassFilterProvider`](%gh-ic%/java/openapi/src/com/intellij/ui/classFilter/DebuggerClassFilterProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.disableCompilationDependenciesResolutionTask"/></include> ![Obsolete][obsolete] | [`DisableCompilationDependenciesResolutionTask`](%gh-ic%/java/idea-ui/src/com/intellij/jarRepository/CompilationDependenciesResolutionTask.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.documentationDelegateProvider"/></include> | [`DocumentationDelegateProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/javadoc/DocumentationDelegateProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.encapsulateFields.Helper"/></include> | [`EncapsulateFieldHelper`](%gh-ic%/java/openapi/src/com/intellij/refactoring/encapsulateFields/EncapsulateFieldHelper.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.exceptionFilter"/></include> | [`ExceptionFilterFactory`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/filters/ExceptionFilterFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.execution.applicationRunLineMarkerHider"/></include> ![DumbAware][dumb-aware] | [`ApplicationRunLineMarkerHider`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/ApplicationRunLineMarkerHider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.externalAnnotationsArtifactsResolver"/></include> | [`ExternalAnnotationsArtifactsResolver`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/ExternalAnnotationsArtifactsResolver.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.framework.type"/></include> ![DumbAware][dumb-aware] | [`FrameworkTypeEx`](%gh-ic%/java/idea-ui/src/com/intellij/framework/FrameworkTypeEx.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.frameworkSupport"/></include> | [`FrameworkSupportProvider`](%gh-ic%/platform/lang-api/src/com/intellij/ide/util/frameworkSupport/FrameworkSupportProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.frameworkSupportCommunicator"/></include> | [`FrameworkSupportCommunicator`](%gh-ic%/java/idea-ui/src/com/intellij/ide/util/newProjectWizard/impl/FrameworkSupportCommunicator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.generateAccessorProvider"/></include> ![Obsolete][obsolete] | [`NotNullFunction`](%gh-ic%/platform/util/src/com/intellij/util/NotNullFunction.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.generation.toStringClassFilter"/></include> | [`GenerateToStringClassFilter`](%gh-ic%/java/java-impl/src/org/jetbrains/generate/tostring/GenerateToStringClassFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.getterSetterProvider"/></include> | [`GetterSetterPrototypeProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/generation/GetterSetterPrototypeProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.gotoByName.defaultProvider.ignoreLanguage"/></include> ![Internal][internal] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.hierarchy.referenceProcessor"/></include> | [`CallReferenceProcessor`](%gh-ic%/java/openapi/src/com/intellij/ide/hierarchy/call/CallReferenceProcessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.jarRepositoryAuthenticationDataProvider"/></include> ![Experimental][experimental] | [`JarRepositoryAuthenticationDataProvider`](%gh-ic%/java/idea-ui/src/com/intellij/jarRepository/JarRepositoryAuthenticationDataProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.java.changeSignature.converter"/></include> ![Experimental][experimental] | [`JavaChangeInfoConverter`](%gh-ic%/java/java-impl/src/com/intellij/refactoring/changeSignature/JavaChangeInfoConverter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.java.compiler"/></include> ![Project-Level][project-level] | [`BackendCompiler`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/impl/javaCompiler/BackendCompiler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.java.consoleDecorator"/></include> ![Experimental][experimental] | [`JavaConsoleDecorator`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/application/JavaConsoleDecorator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.java.effectively.final.fixer"/></include> ![Internal][internal] | [`EffectivelyFinalFixer`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/daemon/impl/quickfix/makefinal/EffectivelyFinalFixer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.java.externalAnnotation"/></include> | [`AnnotationProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/externalAnnotation/AnnotationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.java.externalAnnotation.locationProvider"/></include> | [`AnnotationsLocationProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/externalAnnotation/location/AnnotationsLocationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.java.inspection.bulkMethodInfo"/></include> | [`BulkMethodInfoProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInspection/bulkOperation/BulkMethodInfoProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.java.programPatcher"/></include> | [`JavaProgramPatcher`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/runners/JavaProgramPatcher.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.java.refactoring.chainCallExtractor"/></include> | [`ChainCallExtractor`](%gh-ic%/java/java-impl/src/com/intellij/refactoring/chainCall/ChainCallExtractor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javaDocNotNecessary"/></include> | [`Condition`](%gh-ic%/platform/util/src/com/intellij/openapi/util/Condition.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javaExpressionSurrounder"/></include> | [`JavaExpressionSurrounder`](%gh-ic%/java/openapi/src/com/intellij/codeInsight/generation/surroundWith/JavaExpressionSurrounder.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.jpsServerAuthExtension"/></include> | [`JpsServerAuthExtension`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/cache/client/JpsServerAuthExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.jreProvider"/></include> | [`JreProvider`](%gh-ic%/java/execution/impl/src/com/intellij/execution/ui/JreProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.junitPatcher"/></include> | [`JUnitPatcher`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/JUnitPatcher.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.junitRecognizer"/></include> | [`JUnitRecognizer`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/JUnitRecognizer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.jvm.exceptionFilter"/></include> ![Experimental][experimental] | [`JvmExceptionOccurrenceFilter`](%gh-ic%/java/execution/openapi/src/com/intellij/execution/filters/JvmExceptionOccurrenceFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.jvm.logging"/></include> ![Internal][internal] | [`JvmLogger`](%gh-ic%/java/java-impl/src/com/intellij/lang/logging/JvmLogger.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.languageCompilerRefAdapter"/></include> | [`LanguageCompilerRefAdapter`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/backwardRefs/LanguageCompilerRefAdapter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.languageCompilerRefAdapter.directInheritorProvider"/></include> ![Project-Level][project-level] | [`DirectInheritorProvider`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/backwardRefs/DirectInheritorProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.library.dependencyScopeSuggester"/></include> | [`LibraryDependencyScopeSuggester`](%gh-ic%/java/java-impl/src/com/intellij/openapi/roots/LibraryDependencyScopeSuggester.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.library.javaSourceRootDetector"/></include> | [`RootDetector`](%gh-ic%/platform/lang-impl/src/com/intellij/openapi/roots/libraries/ui/RootDetector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.methodImplementor"/></include> | [`MethodImplementor`](%gh-ic%/java/openapi/src/com/intellij/codeInsight/MethodImplementor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.moduleConfigurable"/></include> ![Removal][removal] | [`ModuleConfigurable`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/projectRoot/ModuleConfigurable.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.newProjectWizard.java.buildSystem"/></include> | [`BuildSystemJavaNewProjectWizard`](%gh-ic%/java/idea-ui/src/com/intellij/ide/projectWizard/generators/BuildSystemJavaNewProjectWizard.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.openapi.projectRoots.externalJavaConfigurationProvider"/></include> | [`ExternalJavaConfigurationProvider`](%gh-ic%/java/java-impl/src/com/intellij/openapi/projectRoots/impl/ExternalJavaConfigurationProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.overrideImplementsAnnotationsHandler"/></include> | [`OverrideImplementsAnnotationsHandler`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/generation/OverrideImplementsAnnotationsHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.packaging.artifactPropertiesProvider"/></include> | [`ArtifactPropertiesProvider`](%gh-ic%/java/compiler/openapi/src/com/intellij/packaging/artifacts/ArtifactPropertiesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.packaging.artifactType"/></include> | [`ArtifactType`](%gh-ic%/java/compiler/openapi/src/com/intellij/packaging/artifacts/ArtifactType.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.packaging.elementType"/></include> | [`PackagingElementType`](%gh-ic%/java/compiler/openapi/src/com/intellij/packaging/elements/PackagingElementType.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.packaging.sourceItemFilter"/></include> | [`PackagingSourceItemFilter`](%gh-ic%/java/compiler/openapi/src/com/intellij/packaging/ui/PackagingSourceItemFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.packaging.sourceItemProvider"/></include> | [`PackagingSourceItemsProvider`](%gh-ic%/java/compiler/openapi/src/com/intellij/packaging/ui/PackagingSourceItemsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.predefinedMigrationMapProvider"/></include> | [`PredefinedMigrationProvider`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/migration/PredefinedMigrationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectImportBuilder"/></include> | [`ProjectImportBuilder`](%gh-ic%/java/idea-ui/src/com/intellij/projectImport/ProjectImportBuilder.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectImportProvider"/></include> | [`ProjectImportProvider`](%gh-ic%/java/idea-ui/src/com/intellij/projectImport/ProjectImportProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectModelModifier"/></include> ![Project-Level][project-level] | [`JavaProjectModelModifier`](%gh-ic%/java/java-impl/src/com/intellij/openapi/roots/JavaProjectModelModifier.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectStructureConfigurableFilter"/></include> | [`ProjectStructureConfigurableFilter`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/ProjectStructureConfigurableFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectStructureDetector"/></include> | [`ProjectStructureDetector`](%gh-ic%/java/idea-ui/src/com/intellij/ide/util/projectWizard/importSources/ProjectStructureDetector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectStructureValidator"/></include> | [`ProjectStructureValidator`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/roots/ui/configuration/projectRoot/daemon/ProjectStructureValidator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectWizard.projectCategory"/></include> | [`ProjectCategory`](%gh-ic%/java/idea-ui/src/com/intellij/ide/projectWizard/ProjectCategory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.introduceParameterMethodUsagesProcessor"/></include> | [`IntroduceParameterMethodUsagesProcessor`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/introduceParameter/IntroduceParameterMethodUsagesProcessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.moveAllClassesInFileHandler"/></include> | [`MoveAllClassesInFileHandler`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/move/moveClassesOrPackages/MoveAllClassesInFileHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.moveClassHandler"/></include> | [`MoveClassHandler`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/move/moveClassesOrPackages/MoveClassHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.moveClassToInnerHandler"/></include> ![Internal][internal] | [`MoveClassToInnerHandler`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/move/moveClassesOrPackages/MoveClassToInnerHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.moveInnerClassUsagesHandler"/></include> | [`MoveInnerClassUsagesHandler`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/move/moveInner/MoveInnerClassUsagesHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.moveInnerHandler"/></include> | [`MoveInnerHandler`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/move/moveInner/MoveInnerHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.moveMemberHandler"/></include> | [`MoveMemberHandler`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/move/moveMembers/MoveMemberHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.overrideMethodProcessor"/></include> ![Internal][internal] | [`OverrideMethodsProcessor`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/OverrideMethodsProcessor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.pullUpHelperFactory"/></include> | [`PullUpHelperFactory`](%gh-ic%/java/openapi/src/com/intellij/refactoring/memberPullUp/PullUpHelperFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.refactoring.safeDelete.JavaSafeDeleteDelegate"/></include> | [`JavaSafeDeleteDelegate`](%gh-ic%/java/openapi/src/com/intellij/refactoring/safeDelete/JavaSafeDeleteDelegate.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.repositoryLibrary"/></include> | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.runConfigurationExtension"/></include> | [`RunConfigurationExtension`](%gh-ic%/java/execution/impl/src/com/intellij/execution/RunConfigurationExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.safeDelete.importSearcher"/></include> ![Internal][internal] | [`ImportSearcher`](%gh-ic%/java/java-impl-refactorings/src/com/intellij/refactoring/safeDelete/ImportSearcher.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.sdkEditorAdditionalOptionsProvider"/></include> | [`SdkEditorAdditionalOptionsProvider`](%gh-ic%/java/idea-ui/src/com/intellij/openapi/SdkEditorAdditionalOptionsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.starter.moduleImporter"/></include> | [`StarterModuleImporter`](%gh-ic%/java/idea-ui/src/com/intellij/ide/starters/StarterModuleImporter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.testGenerator"/></include> | [`TestGenerator`](%gh-ic%/java/java-impl/src/com/intellij/testIntegration/createTest/TestGenerator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.unscrambleSupport"/></include> | [`UnscrambleSupport`](%gh-ic%/java/openapi/src/com/intellij/unscramble/UnscrambleSupport.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.unusedDeclarationFixProvider"/></include> | [`UnusedDeclarationFixProvider`](%gh-ic%/java/java-analysis-api/src/com/intellij/codeInspection/reference/UnusedDeclarationFixProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.variableTypeCalculator"/></include> | [`VariableTypeCalculator`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/template/macro/VariableTypeCalculator.java) |

### JavaPsiPlugin.xml

[`JavaPsiPlugin.xml`](%gh-ic%/java/java-psi-impl/resources/META-INF/JavaPsiPlugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.annotationSupport"/></include> | [`PsiAnnotationSupport`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiAnnotationSupport.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.classTypePointerFactory"/></include> | [`ClassTypePointerFactory`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/ClassTypePointerFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.codeStyle.ReferenceAdjuster"/></include> | [`ReferenceAdjuster`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/codeStyle/ReferenceAdjuster.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.constantExpressionEvaluator"/></include> | [`ConstantExpressionEvaluator`](%gh-ic%/java/java-psi-impl/src/com/intellij/psi/impl/ConstantExpressionEvaluator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.custom.exception.handler"/></include> | [`CustomExceptionHandler`](%gh-ic%/java/java-psi-impl/src/com/intellij/codeInsight/CustomExceptionHandler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.customJavadocTagProvider"/></include> | [`CustomJavadocTagProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/javadoc/CustomJavadocTagProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.deepestSuperMethodsSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.expressionConverter"/></include> | [`ExpressionConverter`](%gh-ic%/java/java-psi-impl/src/com/intellij/psi/impl/ExpressionConverter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.generation.topLevelFactory"/></include> | [`JVMElementFactoryProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/JVMElementFactoryProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.importFilter"/></include> | [`ImportFilter`](%gh-ic%/java/java-psi-api/src/com/intellij/codeInsight/ImportFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.java.elementFinder"/></include> ![Project-Level][project-level] ![DumbAware][dumb-aware] | [`PsiElementFinder`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiElementFinder.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.java.languageFeatureProvider"/></include> | [`LanguageFeatureProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/pom/java/LanguageFeatureProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javaCompilerConfigurationProxy"/></include> | [`JavaCompilerConfigurationProxy`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/JavaCompilerConfigurationProxy.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javaMainMethodProvider"/></include> ![DumbAware][dumb-aware] | [`JavaMainMethodProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/codeInsight/runner/JavaMainMethodProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javaModuleSystem"/></include> | [`JavaModuleSystem`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/JavaModuleSystem.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.javadocTagInfo"/></include> ![Project-Level][project-level] | [`JavadocTagInfo`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/javadoc/JavadocTagInfo.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.jvm.declarationSearcher"/></include> | [`JvmDeclarationSearcher`](%gh-ic%/java/java-psi-api/src/com/intellij/lang/jvm/source/JvmDeclarationSearcher.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.jvm.elementProvider"/></include> ![Project-Level][project-level] ![DumbAware][dumb-aware] | [`JvmElementProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/lang/jvm/facade/JvmElementProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.dumb.mode.supported"/></include> ![Experimental][experimental] | [`JvmLanguageDumbAware`](%gh-ic%/java/java-psi-api/src/com/intellij/lang/jvm/JvmLanguageDumbAware.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.inferredAnnotationProvider"/></include> ![Project-Level][project-level] | [`InferredAnnotationProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/codeInsight/InferredAnnotationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.jvm.multiReleaseSupport"/></include> | [`JavaMultiReleaseModuleSupport`](%gh-ic%/java/java-psi-impl/src/com/intellij/psi/util/JavaMultiReleaseModuleSupport.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.lang.psiAugmentProvider"/></include> ![DumbAware][dumb-aware] | [`PsiAugmentProvider`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/augment/PsiAugmentProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.psi.classFileDecompiler"/></include> | [`Decompiler`](%gh-ic%/java/java-frontback-psi-api/src/com/intellij/psi/compiled/ClassFileDecompilers.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.psi.clsCustomNavigationPolicy"/></include> | [`ClsCustomNavigationPolicy`](%gh-ic%/java/java-psi-impl/src/com/intellij/psi/impl/compiled/ClsCustomNavigationPolicy.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.superMethodsSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.testFramework"/></include> ![DumbAware][dumb-aware] | [`TestFramework`](%gh-ic%/platform/core-api/src/com/intellij/testIntegration/TestFramework.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.uast.analysis.uastAnalysisPlugin"/></include> ![Experimental][experimental] | [`UastAnalysisPlugin`](%gh-ic%/uast/uast-common/src/org/jetbrains/uast/analysis/UastAnalysisPlugin.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.uast.evaluation.uastEvaluatorExtension"/></include> ![Experimental][experimental] | [`UEvaluatorExtension`](%gh-ic%/uast/uast-common/src/org/jetbrains/uast/evaluation/UEvaluatorExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.uast.generate.uastCodeGenerationPlugin"/></include> ![Experimental][experimental] | [`UastCodeGenerationPlugin`](%gh-ic%/uast/uast-common-ide/src/org/jetbrains/uast/generate/UastCodeGenerationPlugin.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.uast.uastLanguagePlugin"/></include> | [`UastLanguagePlugin`](%gh-ic%/uast/uast-common/src/org/jetbrains/uast/UastLanguagePlugin.kt) |

### ManifestSupport.xml

[`ManifestSupport.xml`](%gh-ic%/java/manifest/resources/META-INF/ManifestSupport.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.manifest.parser.provider"/></include> | [`HeaderParserProvider`](%gh-ic%/java/manifest/src/org/jetbrains/lang/manifest/header/HeaderParserProvider.java) |

### UsageData.xml

[`UsageData.xml`](%gh-ic%/java/java-impl/resources/META-INF/UsageData.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.internal.statistic.libraryUsageImportProcessor"/></include> ![Internal][internal] | [`LibraryUsageImportProcessor`](%gh-ic%/java/java-impl/src/com/intellij/internal/statistic/libraryUsage/LibraryUsageImportProcessor.kt) |


## JSON Plugin

### JSON Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`JsonSchemaVfsListener#JSON_SCHEMA_CHANGED`](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |
| [`JsonSchemaVfsListener#JSON_DEPS_CHANGED`](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  | `Runnable` |


### intellij.json.split.xml

[`intellij.json.split.xml`](%gh-ic%/json/split/resources/intellij.json.split.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.json.jsonLiteralChecker"/></include> | [`JsonLiteralChecker`](%gh-ic%/json/split/src/com/intellij/json/codeinsight/JsonLiteralChecker.java) |

### intellij.json.xml

[`intellij.json.xml`](%gh-ic%/json/resources/intellij.json.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.JsonSchema.ContentAwareSchemaFileProvider"/></include> | [`ContentAwareJsonSchemaFileProvider`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/ContentAwareJsonSchemaFileProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="JavaScript.JsonSchema.ProviderFactory"/></include> ![DumbAware][dumb-aware] | [`JsonSchemaProviderFactory`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonSchemaProviderFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.json.catalog.exclusion"/></include> | [`JsonSchemaCatalogExclusion`](%gh-ic%/json/src/com/jetbrains/jsonSchema/remote/JsonSchemaCatalogExclusion.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.json.customStructureViewFactory"/></include> | [`JsonCustomStructureViewFactory`](%gh-ic%/json/src/com/intellij/json/structureView/JsonCustomStructureViewFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.json.jsonLikePsiWalkerFactory"/></include> | [`JsonLikePsiWalkerFactory`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonLikePsiWalkerFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.json.jsonSchemaCompletionCustomizer"/></include> | [`JsonSchemaCompletionCustomizer`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonSchemaCompletionCustomizer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.json.jsonSchemaEnabler"/></include> | [`JsonSchemaEnabler`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonSchemaEnabler.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.json.jsonSchemaGotoDeclarationSuppressor"/></include> | [`JsonSchemaGotoDeclarationSuppressor`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonSchemaGotoDeclarationSuppressor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.json.jsonSchemaNestedCompletionsTreeProvider"/></include> ![Experimental][experimental] | [`JsonSchemaNestedCompletionsTreeProvider`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonSchemaNestedCompletionsTreeProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.json.jsonSchemaQuickFixSuppressor"/></include> | [`JsonSchemaQuickFixSuppressor`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonSchemaQuickFixSuppressor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.json.jsonStandardComplianceProvider"/></include> | [`JsonStandardComplianceProvider`](%gh-ic%/json/src/com/intellij/json/codeinsight/JsonStandardComplianceProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.json.jsonWidgetSuppressor"/></include> | [`JsonWidgetSuppressor`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonWidgetSuppressor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.json.shorthandValueHandler"/></include> | [`JsonSchemaShorthandValueHandler`](%gh-ic%/json/src/com/jetbrains/jsonSchema/extension/JsonSchemaShorthandValueHandler.kt) |


## Kotlin Plugin

### Kotlin Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`LibraryInfoListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.base.projectStructure.LibraryInfoListener)  ![Internal][internal] ![Project-Level][project-level] | [`LibraryInfoListener`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/LibraryInfoCache.kt) |
| [`KotlinCompilerSettingsListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.compiler.configuration.KotlinCompilerSettingsListener)  ![Project-Level][project-level] | [`KotlinCompilerSettingsListener`](%gh-ic%/plugins/kotlin/base/compiler-configuration/src/org/jetbrains/kotlin/idea/compiler/configuration/BaseKotlinCompilerSettings.kt) |
| [`KotlinBundledUsageDetector#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.macros.KotlinBundledUsageDetectorListener)  ![Project-Level][project-level] | [`KotlinBundledUsageDetectorListener`](%gh-ic%/plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/macros/KotlinBundledUsageDetectorListener.kt) |
| [`KotlinRefactoringEventListener.Companion#EVENT_TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.refactoring.KotlinRefactoringEventListener)  ![Deprecated][deprecated] | [`KotlinRefactoringEventListener`](%gh-ic%/plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/refactoring/RefactoringEventListenerEx.kt) |
| [`KotlinRefactoringListener.Companion#EVENT_TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.refactoring.KotlinRefactoringListener)  | [`KotlinRefactoringListener`](%gh-ic%/plugins/kotlin/refactorings/kotlin.refactorings.common/src/org/jetbrains/kotlin/idea/refactoring/KotlinRefactoringListener.kt) |
| [`ScratchFileListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.scratch.ScratchFileListener)  ![Project-Level][project-level] | [`ScratchFileListener`](%gh-ic%/plugins/kotlin/jvm/src/org/jetbrains/kotlin/idea/scratch/ScratchFile.kt) |
| [`KotlinCorruptedIndexListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.kotlin.idea.stubindex.resolve.KotlinCorruptedIndexListener)  ![Project-Level][project-level] | [`KotlinCorruptedIndexListener`](%gh-ic%/plugins/kotlin/base/analysis/src/org/jetbrains/kotlin/idea/stubindex/resolve/KotlinCorruptedIndexListener.kt) |


### completion-fe10.xml

[`completion-fe10.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/completion-fe10.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.completionInformationProvider"/></include> | [`CompletionInformationProvider`](%gh-ic%/plugins/kotlin/base/fe10/analysis/src/org/jetbrains/kotlin/idea/completion/CompletionInformationProvider.kt) |

### extensions.xml

[`extensions.xml`](%gh-ic%/plugins/kotlin/plugin/k2/resources/META-INF/extensions.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.codeinsight.quickfix.registrar"/></include> | [`KotlinQuickFixRegistrar`](%gh-ic%/plugins/kotlin/code-insight/api/src/org/jetbrains/kotlin/idea/codeinsight/api/applicators/fixes/KotlinQuickFixService.kt) |

### facets-base.xml

[`facets-base.xml`](%gh-ic%/plugins/kotlin/plugin/common/resources/META-INF/facets-base.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.facetConfigurationExtension"/></include> ![Non-Dynamic][non-dynamic] | [`KotlinFacetConfigurationExtension`](%gh-ic%/plugins/kotlin/base/facet/src/org/jetbrains/kotlin/idea/facet/KotlinFacetConfigurationExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.versionInfoProvider"/></include> ![Non-Dynamic][non-dynamic] | [`KotlinVersionInfoProvider`](%gh-ic%/plugins/kotlin/base/facet/src/org/jetbrains/kotlin/idea/facet/KotlinVersionInfoProvider.kt) |

### file-types.xml

[`file-types.xml`](%gh-ic%/plugins/kotlin/plugin/common/resources/META-INF/file-types.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.binaryExtension"/></include> | [`KotlinBinaryExtension`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/KotlinBinaryExtension.kt) |

### highlighting-fe10.xml

[`highlighting-fe10.xml`](%gh-ic%/plugins/kotlin/highlighting/highlighting-k1/resources/META-INF/highlighting-fe10.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.highlighterExtension"/></include> | [`KotlinHighlightingVisitorExtension`](%gh-ic%/plugins/kotlin/highlighting/highlighting-k1/src/org/jetbrains/kotlin/idea/highlighter/KotlinHighlightingVisitorExtension.kt) |

### jps.xml

[`jps.xml`](%gh-ic%/plugins/kotlin/plugin/common/resources/META-INF/jps.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idePlatformKind"/></include> ![Non-Dynamic][non-dynamic] | `IdePlatformKind` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idePlatformKindResolution"/></include> ![Non-Dynamic][non-dynamic] | [`IdePlatformKindResolution`](%gh-ic%/plugins/kotlin/base/fe10/analysis/src/org/jetbrains/kotlin/caches/resolve/IdePlatformKindResolution.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idePlatformKindTooling"/></include> ![Non-Dynamic][non-dynamic] | [`IdePlatformKindTooling`](%gh-ic%/plugins/kotlin/base/code-insight/src/org/jetbrains/kotlin/idea/base/codeInsight/tooling/IdePlatformKindTooling.kt) |

### jvm-debugger.xml

[`jvm-debugger.xml`](%gh-ic%/plugins/kotlin/plugin/common/resources/META-INF/jvm-debugger.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.dexBytecodeInspector"/></include> | [`DexBytecodeInspector`](%gh-ic%/plugins/kotlin/jvm-debugger/core/src/org/jetbrains/kotlin/idea/debugger/core/DexBytecodeInspector.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.debugger.kotlinStackFrameValueContributor"/></include> | [`KotlinStackFrameValueContributor`](%gh-ic%/plugins/kotlin/jvm-debugger/core/src/org/jetbrains/kotlin/idea/debugger/core/stackFrame/KotlinStackFrameValueContributor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idea.debugger.base.util.classNameCalculator"/></include> | [`ClassNameCalculator`](%gh-ic%/plugins/kotlin/jvm-debugger/base/util/src/org/jetbrains/kotlin/idea/debugger/base/util/ClassNameCalculator.kt) |

### kotlin-core-fe10.xml

[`kotlin-core-fe10.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/kotlin-core-fe10.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.classImportFilter"/></include> | [`ClassImportFilter`](%gh-ic%/plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/util/ClassImportFilter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idea.caches.resolve.resolveOptimizingOptionsProvider"/></include> | [`ResolveOptimizingOptionsProvider`](%gh-ic%/plugins/kotlin/base/fe10/analysis/src/org/jetbrains/kotlin/idea/caches/resolve/ResolveOptimizingOptionsProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.kotlinIndicesHelperExtension"/></include> ![Project-Level][project-level] | [`KotlinIndicesHelperExtension`](%gh-ic%/plugins/kotlin/base/fe10/analysis/src/org/jetbrains/kotlin/idea/core/extension/KotlinIndicesHelperExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.quickFixContributor"/></include> | [`QuickFixContributor`](%gh-ic%/plugins/kotlin/base/fe10/code-insight/src/org/jetbrains/kotlin/idea/quickfix/QuickFixContributor.kt) |

### kotlin-core.xml

[`kotlin-core.xml`](%gh-ic%/plugins/kotlin/plugin/common/resources/META-INF/kotlin-core.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.kotlin.autoImportCallableWeigher"/></include> ![Internal][internal] | [`KotlinAutoImportCallableWeigher`](%gh-ic%/plugins/kotlin/code-insight/api/src/org/jetbrains/kotlin/idea/codeinsight/api/classic/quickfixes/KotlinAutoImportCallableWeigher.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.analysis.additionalKDocResolutionProvider"/></include> | `AdditionalKDocResolutionProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.buildSystemDependencyManager"/></include> ![Internal][internal] ![Project-Level][project-level] | [`KotlinBuildSystemDependencyManager`](%gh-ic%/plugins/kotlin/project-configuration/src/org/jetbrains/kotlin/idea/configuration/KotlinBuildSystemDependencyManager.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.buildSystemTypeDetector"/></include> | [`BuildSystemTypeDetector`](%gh-ic%/plugins/kotlin/base/facet/src/org/jetbrains/kotlin/idea/configuration/BuildSystemType.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.bundledFirCompilerPluginProvider"/></include> ![Experimental][experimental] ![Internal][internal] | [`KotlinBundledFirCompilerPluginProvider`](%gh-ic%/plugins/kotlin/bundled-compiler-plugins-support/src/org/jetbrains/kotlin/idea/fir/extensions/KotlinBundledFirCompilerPluginProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.experimentalFeature"/></include> | [`ExperimentalFeature`](%gh-ic%/plugins/kotlin/preferences/src/org/jetbrains/kotlin/idea/configuration/ExperimentalFeatures.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.facetValidatorCreator"/></include> ![Non-Dynamic][non-dynamic] | [`KotlinFacetValidatorCreator`](%gh-ic%/plugins/kotlin/base/compiler-configuration-ui/src/org/jetbrains/kotlin/idea/base/compilerPreferences/facet/KotlinFacetValidatorCreator.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.failedToDownloadJpsMavenArtifactSuggestedSolutionsContributor"/></include> ![Internal][internal] ![Project-Level][project-level] | [`FailedToDownloadJpsMavenArtifactSuggestedSolutionsContributor`](%gh-ic%/plugins/kotlin/base/plugin/src/org/jetbrains/kotlin/idea/compiler/configuration/FailedToDownloadJpsMavenArtifactSuggestedSolutionsContributor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.firCompilerPluginConfigurationProvider"/></include> ![Experimental][experimental] | [`KotlinFirCompilerPluginConfigurationForIdeProvider`](%gh-ic%/plugins/kotlin/bundled-compiler-plugins-support/src/org/jetbrains/kotlin/idea/fir/extensions/KotlinFirCompilerPluginConfigurationForIdeProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idea.base.platforms.targetPlatformDetector"/></include> ![Project-Level][project-level] | [`TargetPlatformDetector`](%gh-ic%/plugins/kotlin/base/facet/src/org/jetbrains/kotlin/idea/base/facet/platform/TargetPlatformDetector.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idea.base.projectStructure.moduleInfoProviderExtension"/></include> ![Project-Level][project-level] | [`ModuleInfoProviderExtension`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/ModuleInfoProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idea.codeInsight.unambiguousImports"/></include> | [`KotlinAutoImportsFilter`](%gh-ic%/plugins/kotlin/frontend-independent/src/org/jetbrains/kotlin/idea/codeInsight/KotlinAutoImportsFilter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idea.testFrameworkProvider"/></include> | [`KotlinTestFrameworkProvider`](%gh-ic%/plugins/kotlin/run-configurations/jvm/src/org/jetbrains/kotlin/idea/extensions/KotlinTestFrameworkProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.j2kConverterExtension"/></include> | [`J2kConverterExtension`](%gh-ic%/plugins/kotlin/j2k/shared/src/org/jetbrains/kotlin/j2k/J2kConverterExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.j2kPostprocessorExtension"/></include> | [`J2kPostprocessorExtension`](%gh-ic%/plugins/kotlin/j2k/shared/src/org/jetbrains/kotlin/j2k/preAndPostprocessorExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.j2kPreprocessorExtension"/></include> | [`J2kPreprocessorExtension`](%gh-ic%/plugins/kotlin/j2k/shared/src/org/jetbrains/kotlin/j2k/preAndPostprocessorExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.kotlinInjectedFilesAnalysisProvider"/></include> ![Internal][internal] | [`KotlinIdeInjectedFilesAnalysisPromoter`](%gh-ic%/plugins/kotlin/base/analysis/src/org/jetbrains/kotlin/idea/base/analysis/KotlinIdeInjectedFilesAnalysisPromoter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.ktModuleFactory"/></include> ![Internal][internal] | [`KaModuleFactory`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/ProjectStructureProviderIdeImpl.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.libraryVersionProvider"/></include> ![Internal][internal] | [`KotlinLibraryVersionProvider`](%gh-ic%/plugins/kotlin/project-configuration/src/org/jetbrains/kotlin/idea/configuration/KotlinLibraryVersionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.newFileHook"/></include> ![Internal][internal] | [`NewKotlinFileHook`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/NewKotlinFileHook.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.pluginUpdateVerifier"/></include> | [`PluginUpdateVerifier`](%gh-ic%/plugins/kotlin/plugin-updater/src/org/jetbrains/kotlin/idea/update/PluginUpdateVerifier.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.projectConfigurator"/></include> | [`KotlinProjectConfigurator`](%gh-ic%/plugins/kotlin/project-configuration/src/org/jetbrains/kotlin/idea/configuration/KotlinProjectConfigurator.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.projectStructureInsightsProvider"/></include> ![Internal][internal] | [`ProjectStructureInsightsProvider`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/ProjectStructureProviderIdeImpl.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.smartEnterProcessorFixer"/></include> | [`Fixer`](%gh-ic%/platform/lang-impl/src/com/intellij/lang/SmartEnterProcessorWithFixers.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.supportAvailability"/></include> | [`KotlinSupportAvailability`](%gh-ic%/plugins/kotlin/code-insight/utils/src/org/jetbrains/kotlin/idea/codeinsight/utils/KotlinSupportAvailability.kt) |

### kotlin.base.code-insight.minimal.xml

[`kotlin.base.code-insight.minimal.xml`](%gh-ic%/plugins/kotlin/base/code-insight/minimal/resource/kotlin.base.code-insight.minimal.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.smartEnterProcessorFixer"/></include> | [`Fixer`](%gh-ic%/platform/lang-impl/src/com/intellij/lang/SmartEnterProcessorWithFixers.java) |

### kotlin.base.external-build-system.xml

[`kotlin.base.external-build-system.xml`](%gh-ic%/plugins/kotlin/base/external-build-system/resources/kotlin.base.external-build-system.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idea.base.externalSystem.kotlinBuildSystemFacade"/></include> | [`KotlinBuildSystemFacade`](%gh-ic%/plugins/kotlin/base/external-build-system/src/org/jetbrains/kotlin/idea/base/externalSystem/KotlinBuildSystemFacade.kt) |

### kotlin.base.fir.project-structure.xml

[`kotlin.base.fir.project-structure.xml`](%gh-ic%/plugins/kotlin/base/fir/project-structure/resources/kotlin.base.fir.project-structure.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.k2KaModuleFactory"/></include> ![Internal][internal] | [`K2KaModuleFactory`](%gh-ic%/plugins/kotlin/base/fir/project-structure/src/org/jetbrains/kotlin/idea/base/fir/projectStructure/K2KaModuleFactory.kt) |

### kotlin.gradle.code-insight-common.xml

[`kotlin.gradle.code-insight-common.xml`](%gh-ic%/plugins/kotlin/gradle/code-insight-common/resources/kotlin.gradle.code-insight-common.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idea.gradleBuildScriptSupport"/></include> | [`GradleBuildScriptSupport`](%gh-ic%/plugins/kotlin/gradle/code-insight-common/src/org/jetbrains/kotlin/idea/gradleCodeInsightCommon/GradleBuildScriptSupport.kt) |

### kotlin.gradle.gradle-java.xml

[`kotlin.gradle.gradle-java.xml`](%gh-ic%/plugins/kotlin/gradle/gradle-java/resources/kotlin.gradle.gradle-java.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.gradleProjectImportHandler"/></include> ![Project-Level][project-level] | [`GradleProjectImportHandler`](%gh-ic%/plugins/kotlin/gradle/gradle-java/src/org/jetbrains/kotlin/idea/gradleJava/configuration/KotlinGradleSourceSetDataService.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idea.gradleJava.kotlinMultiplatformProducersProvider"/></include> | [`KotlinMultiplatformCommonProducersProvider`](%gh-ic%/plugins/kotlin/gradle/gradle-java/src/org/jetbrains/kotlin/idea/gradleJava/extensions/KotlinMultiplatformCommonProducersProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.mppProjectResolve"/></include> | [`KotlinMppGradleProjectResolverExtension`](%gh-ic%/plugins/kotlin/gradle/gradle-java/src/org/jetbrains/kotlin/idea/gradleJava/configuration/mpp/KotlinMppGradleProjectResolverExtension.kt) |

### kotlin.gradle.gradle-tooling.xml

[`kotlin.gradle.gradle-tooling.xml`](%gh-ic%/plugins/kotlin/gradle/gradle-tooling/resources/kotlin.gradle.gradle-tooling.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idea.gradleTooling.serialization.IdeaKotlinSerializationContext"/></include> | `IdeaKotlinSerializationContext` |

### kotlin.gradle.gradle.xml

[`kotlin.gradle.gradle.xml`](%gh-ic%/plugins/kotlin/gradle/gradle/resources/kotlin.gradle.gradle.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.gradleModelFacade"/></include> | [`KotlinGradleModelFacade`](%gh-ic%/plugins/kotlin/gradle/gradle/src/org/jetbrains/kotlin/idea/gradle/inspections/KotlinGradleModelFacade.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idea.extrasSerialization"/></include> | [`KotlinExtrasSerializationService`](%gh-ic%/plugins/kotlin/gradle/gradle/src/org/jetbrains/kotlin/idea/gradle/configuration/serialize/KotlinExtrasSerializationService.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.kpm.createRoots"/></include> | [`ContentRootsCreator`](%gh-ic%/plugins/kotlin/gradle/gradle/src/org/jetbrains/kotlin/idea/gradle/configuration/kpm/ContentRootsCreator.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.kpm.moduleInitialize"/></include> | [`ModuleDataInitializer`](%gh-ic%/plugins/kotlin/gradle/gradle/src/org/jetbrains/kotlin/idea/gradle/configuration/kpm/ModuleDataInitializer.kt) |

### kotlin.highlighting.k2.xml

[`kotlin.highlighting.k2.xml`](%gh-ic%/plugins/kotlin/highlighting/highlighting-k2/resources/kotlin.highlighting.k2.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.callHighlighterExtension"/></include> | [`KotlinCallHighlighterExtension`](%gh-ic%/plugins/kotlin/highlighting/highlighting-k2/src/org/jetbrains/kotlin/idea/highlighting/KotlinCallHighlighterExtension.kt) |

### kotlin.highlighting.shared.xml

[`kotlin.highlighting.shared.xml`](%gh-ic%/plugins/kotlin/highlighting/highlighting-shared/resources/kotlin.highlighting.shared.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.beforeResolveHighlightingVisitor"/></include> ![Internal][internal] | [`BeforeResolveHighlightingExtension`](%gh-ic%/plugins/kotlin/highlighting/highlighting-minimal/src/org/jetbrains/kotlin/idea/base/highlighting/BeforeResolveHighlightingExtension.kt) |

### kotlin.maven.xml

[`kotlin.maven.xml`](%gh-ic%/plugins/kotlin/maven/resources/kotlin.maven.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.mavenProjectImportHandler"/></include> ![Project-Level][project-level] | [`MavenProjectImportHandler`](%gh-ic%/plugins/kotlin/maven/src/org/jetbrains/kotlin/idea/maven/KotlinMavenImporter.kt) |

### kotlin.plugin.k2.xml

[`kotlin.plugin.k2.xml`](%gh-ic%/plugins/kotlin/plugin/k2/resources/kotlin.plugin.k2.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.resolveScopeEnlarger"/></include> ![Non-Dynamic][non-dynamic] | [`KotlinResolveScopeEnlarger`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/KotlinResolveScopeEnlarger.kt) |

### kotlin.project-wizard.idea.xml

[`kotlin.project-wizard.idea.xml`](%gh-ic%/plugins/kotlin/project-wizard/idea/resources/kotlin.project-wizard.idea.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.newProjectWizard.kotlin.buildSystem"/></include> | [`BuildSystemKotlinNewProjectWizard`](%gh-ic%/plugins/kotlin/project-wizard/idea/src/org/jetbrains/kotlin/tools/projectWizard/BuildSystemKotlinNewProjectWizard.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idea.androidSdkProvider"/></include> | [`AndroidSdkProvider`](%gh-ic%/plugins/kotlin/project-wizard/core/src/org/jetbrains/kotlin/tools/projectWizard/plugins/AndroidSdkProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idea.ideaWizardService"/></include> ![Project-Level][project-level] | [`IdeaWizardService`](%gh-ic%/plugins/kotlin/project-wizard/idea/src/org/jetbrains/kotlin/tools/projectWizard/wizard/service/IdeaWizardService.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idea.projectTemplatesProvider"/></include> | [`ProjectTemplatesProvider`](%gh-ic%/plugins/kotlin/project-wizard/core/src/org/jetbrains/kotlin/tools/projectWizard/plugins/projectTemplates/ProjectTemplatesProvider.kt) |

### kotlin.refactorings.k2.xml

[`kotlin.refactorings.k2.xml`](%gh-ic%/plugins/kotlin/refactorings/kotlin.refactorings.k2/resources/kotlin.refactorings.k2.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.changeSignatureConflictFilter"/></include> | [`KotlinChangeSignatureConflictFilter`](%gh-ic%/plugins/kotlin/refactorings/kotlin.refactorings.k2/src/org/jetbrains/kotlin/idea/k2/refactoring/changeSignature/KotlinChangeSignatureConflictFilter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.extractFunctionDescriptorModifier"/></include> | [`ExtractFunctionDescriptorModifier`](%gh-ic%/plugins/kotlin/refactorings/kotlin.refactorings.k2/src/org/jetbrains/kotlin/idea/k2/refactoring/extractFunction/ExtractFunctionDescriptorModifier.kt) |

### kotlin.searching.k2.xml

[`kotlin.searching.k2.xml`](%gh-ic%/plugins/kotlin/kotlin.searching/resources/kotlin.searching.k2.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.directKotlinClassInheritorsSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |

### kotlinx-serialization.xml

[`kotlinx-serialization.xml`](%gh-ic%/plugins/kotlin/compiler-plugins/kotlinx-serialization/common/resources/META-INF/kotlinx-serialization.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idea.compilerPlugin.kotlinxSerialization.kotlinSerializationEnabledChecker"/></include> | [`KotlinSerializationEnabledChecker`](%gh-ic%/plugins/kotlin/compiler-plugins/kotlinx-serialization/common/src/org/jetbrains/kotlin/idea/compilerPlugin/kotlinxSerialization/KotlinSerializationEnabledChecker.kt) |

### light-classes-fe10.xml

[`light-classes-fe10.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/light-classes-fe10.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.ultraLightClassModifierExtension"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `UltraLightClassModifierExtension` |

### org.jetbrains.kotlin

[`org.jetbrains.kotlin`](%gh-ic%/plugins/kotlin/plugin/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.supportsKotlinPluginMode"/></include> | `n/a` |

### parcelize.xml

[`parcelize.xml`](%gh-ic%/plugins/kotlin/compiler-plugins/parcelize/common/resources/META-INF/parcelize.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.idea.compilerPlugin.parcelize.availabilityProvider"/></include> ![Project-Level][project-level] | [`ParcelizeAvailabilityProvider`](%gh-ic%/plugins/kotlin/compiler-plugins/parcelize/common/src/org/jetbrains/kotlin/idea/compilerPlugin/parcelize/ParcelizeAvailability.kt) |

### refactorings-fe10.xml

[`refactorings-fe10.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/refactorings-fe10.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.additionalExtractableAnalyser"/></include> | [`AdditionalExtractableAnalyser`](%gh-ic%/plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/refactoring/introduce/extractionEngine/AdditionalExtractableAnalyser.kt) |

### refactorings.xml

[`refactorings.xml`](%gh-ic%/plugins/kotlin/plugin/common/resources/META-INF/refactorings.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.foreignUsagesRenameProcessor"/></include> | [`ForeignUsagesRenameProcessor`](%gh-ic%/plugins/kotlin/refactorings/kotlin.refactorings.common/src/org/jetbrains/kotlin/idea/refactoring/rename/ForeignUsagesRenameProcessor.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.postInsertDeclarationCallback"/></include> | [`PostInsertDeclarationCallback`](%gh-ic%/plugins/kotlin/refactorings/kotlin.refactorings.common/src/org/jetbrains/kotlin/idea/refactoring/introduce/extractionEngine/PostInsertDeclarationCallback.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.renameHandler"/></include> | [`RenameHandler`](%gh-ic%/platform/refactoring/src/com/intellij/refactoring/rename/RenameHandler.java) |

### resolution-fe10.xml

[`resolution-fe10.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/resolution-fe10.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.resolveScopeEnlarger"/></include> | [`KotlinResolveScopeEnlarger`](%gh-ic%/plugins/kotlin/base/project-structure/src/org/jetbrains/kotlin/idea/base/projectStructure/KotlinResolveScopeEnlarger.kt) |

### scripting-base.xml

[`scripting-base.xml`](%gh-ic%/plugins/kotlin/plugin/common/resources/META-INF/scripting-base.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.ideScriptConfigurationControlFacade"/></include> ![Project-Level][project-level] | [`IdeScriptConfigurationControlFacade`](%gh-ic%/plugins/kotlin/scripting/src/kotlin/script/experimental/intellij/scriptConfigurationTools.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scriptAdditionalIdeaDependenciesProvider"/></include> ![Project-Level][project-level] | [`ScriptAdditionalIdeaDependenciesProvider`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/dependencies/ScriptAdditionalIdeaDependenciesProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scriptDiagnosticFixProvider"/></include> | [`ScriptDiagnosticFixProvider`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/script/ScriptDiagnosticFixProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scripting.idea.listener"/></include> ![Project-Level][project-level] | [`ScriptChangeListener`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/configuration/listener/ScriptChangeListener.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scripting.idea.loader"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ScriptConfigurationLoader`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/configuration/loader/ScriptConfigurationLoader.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scripting.idea.scriptingSupport"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ScriptingSupport`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/configuration/ScriptingSupport.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scripting.idea.settings.provider"/></include> ![Project-Level][project-level] | [`ScriptingSupportSpecificSettingsProvider`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/script/configuration/ScriptingSupportSpecificSettingsProvider.kt) |

### scripting-k2.xml

[`scripting-k2.xml`](%gh-ic%/plugins/kotlin/plugin/k2/resources/META-INF/scripting-k2.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.ideScriptConfigurationControlFacade"/></include> ![Project-Level][project-level] | [`IdeScriptConfigurationControlFacade`](%gh-ic%/plugins/kotlin/scripting/src/kotlin/script/experimental/intellij/scriptConfigurationTools.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.kotlinScriptLazyResolveProhibitionCondition"/></include> ![Project-Level][project-level] | [`KotlinScriptLazyResolveProhibitionCondition`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/k2/KotlinScriptLazyResolveProhibitionCondition.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scratchFileLanguageProvider"/></include> | [`ScratchFileLanguageProvider`](%gh-ic%/plugins/kotlin/jvm/src/org/jetbrains/kotlin/idea/scratch/ScratchFileLanguageProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scriptAdditionalIdeaDependenciesProvider"/></include> ![Project-Level][project-level] | [`ScriptAdditionalIdeaDependenciesProvider`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/dependencies/ScriptAdditionalIdeaDependenciesProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scriptConfigurationsSource"/></include> ![Project-Level][project-level] | [`ScriptConfigurationsSource`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/k2/ScriptConfigurationsSource.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scriptDefinitionsSource"/></include> ![Project-Level][project-level] | `ScriptDefinitionsSource` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scriptDiagnosticFixProvider"/></include> | [`ScriptDiagnosticFixProvider`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/script/ScriptDiagnosticFixProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scripting.definitions.scriptDefinitionProvider"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | `ScriptDefinitionProvider` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scripting.idea.listener"/></include> ![Project-Level][project-level] | [`ScriptChangeListener`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/configuration/listener/ScriptChangeListener.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scripting.idea.loader"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ScriptConfigurationLoader`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/configuration/loader/ScriptConfigurationLoader.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scripting.idea.scriptingSupport"/></include> ![Non-Dynamic][non-dynamic] ![Project-Level][project-level] | [`ScriptingSupport`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/core/script/configuration/ScriptingSupport.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scripting.idea.settings.provider"/></include> ![Project-Level][project-level] | [`ScriptingSupportSpecificSettingsProvider`](%gh-ic%/plugins/kotlin/base/scripting/src/org/jetbrains/kotlin/idea/script/configuration/ScriptingSupportSpecificSettingsProvider.kt) |

### scripting-support.xml

[`scripting-support.xml`](%gh-ic%/plugins/kotlin/plugin/k1/resources/META-INF/scripting-support.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scratchFileLanguageProvider"/></include> | [`ScratchFileLanguageProvider`](%gh-ic%/plugins/kotlin/jvm/src/org/jetbrains/kotlin/idea/scratch/ScratchFileLanguageProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scriptDefinitionsProvider"/></include> ![Project-Level][project-level] | [`ScriptDefinitionsProvider`](%gh-ic%/plugins/kotlin/scripting/src/kotlin/script/experimental/intellij/scriptDefinitionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.kotlin.scriptDefinitionsSource"/></include> ![Project-Level][project-level] | `ScriptDefinitionsSource` |


## Markdown Plugin

### Markdown Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`ChangeListener#TOPIC`](https://jb.gg/ipe/listeners?topics=org.intellij.plugins.markdown.settings.MarkdownExtensionsSettings.ChangeListener)  ![Experimental][experimental] | [`ChangeListener`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/settings/MarkdownExtensionsSettings.kt) |
| [`ChangeListener#TOPIC`](https://jb.gg/ipe/listeners?topics=org.intellij.plugins.markdown.settings.MarkdownPreviewSettings.ChangeListener)  | [`ChangeListener`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/settings/MarkdownPreviewSettings.kt) |
| [`ChangeListener#TOPIC`](https://jb.gg/ipe/listeners?topics=org.intellij.plugins.markdown.settings.MarkdownSettings.ChangeListener)  ![Project-Level][project-level] | [`ChangeListener`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/settings/MarkdownSettings.kt) |


### intellij.markdown.fenceInjection.xml

[`intellij.markdown.fenceInjection.xml`](%gh-ic%/plugins/markdown/fenceInjection/src/main/resources/intellij.markdown.fenceInjection.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.plugins.markdown.fenceInjection.fenceSurroundingsProvider"/></include> ![Internal][internal] | [`FenceSurroundingsProvider`](%gh-ic%/plugins/markdown/fenceInjection/src/main/java/org/intellij/plugins/markdown/fenceInjection/FenceSurroundingsProvider.kt) |

### org.intellij.plugins.markdown

[`org.intellij.plugins.markdown`](%gh-ic%/plugins/markdown/core/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.markdown.additionalFenceLanguageSuggester"/></include> ![Internal][internal] | [`AdditionalFenceLanguageSuggester`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/injection/aliases/AdditionalFenceLanguageSuggester.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.markdown.browserPreviewExtensionProvider"/></include> | [`Provider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/extensions/MarkdownBrowserPreviewExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.markdown.fenceGeneratingProvider"/></include> ![Obsolete][obsolete] ![Internal][internal] | [`CodeFenceGeneratingProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/extensions/CodeFenceGeneratingProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.markdown.fenceLanguageProvider"/></include> | [`CodeFenceLanguageProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/injection/CodeFenceLanguageProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.markdown.flavourProvider"/></include> ![Experimental][experimental] | [`MarkdownFlavourProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/lang/parser/MarkdownFlavourProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.markdown.html.panel.provider"/></include> | [`MarkdownHtmlPanelProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/ui/preview/MarkdownHtmlPanelProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.markdown.markdownCompatibilityChecker"/></include> | [`MarkdownCompatibilityChecker`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/lang/MarkdownCompatibilityChecker.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.markdown.markdownExportProvider"/></include> ![Experimental][experimental] | [`MarkdownExportProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/fileActions/export/MarkdownExportProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.markdown.markdownRunner"/></include> | [`MarkdownRunner`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/extensions/jcef/commandRunner/MarkdownRunner.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.intellij.markdown.previewStylesProvider"/></include> ![Internal][internal] | [`MarkdownPreviewStylesProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/ui/preview/MarkdownPreviewStylesProvider.kt) |


## Maven Plugin

### Maven Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`MavenSystemIndicesManager#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.maven.indices.IndexChangeProgressListener)  | [`IndexChangeProgressListener`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/indices/MavenSystemIndicesManager.kt) |
| [`MavenIndicesManager#INDEXER_TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.maven.indices.MavenIndicesManager.MavenIndexerListener)  | [`MavenIndexerListener`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/indices/MavenIndicesManager.kt) |
| [`MavenSearchIndex#INDEX_IS_BROKEN`](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.maven.indices.MavenSearchIndex.IndexListener)  | [`IndexListener`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/indices/MavenSearchIndex.java) |
| [`MavenImportListener#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.maven.project.MavenImportListener)  ![Project-Level][project-level] | [`MavenImportListener`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/project/MavenImportListener.java) |
| [`MavenSyncListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.maven.project.MavenSyncListener)  | [`MavenSyncListener`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/project/MavenSyncListener.kt) |
| [`MavenServerConnector#DOWNLOAD_LISTENER_TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.maven.server.MavenServerDownloadListener)  | [`MavenServerDownloadListener`](%gh-ic%/plugins/maven-server-api/src/main/java/org/jetbrains/idea/maven/server/MavenServerDownloadListener.java) |


### groovy-support.xml

[`groovy-support.xml`](%gh-ic%/plugins/maven/src/main/resources/META-INF/groovy-support.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.importing.groovy.foldersConfiguratorContributor"/></include> | [`PluginContributor`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/plugins/groovy/GroovyPluginConfigurator.kt) |

### org.jetbrains.idea.maven

[`org.jetbrains.idea.maven`](%gh-ic%/plugins/maven/src/main/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.additional.importing.settings"/></include> | [`AdditionalMavenImportingSettings`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/project/AdditionalMavenImportingSettings.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.archetypesProvider"/></include> | [`MavenArchetypesProvider`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/indices/MavenArchetypesProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.artifactBuilder"/></include> | [`MavenArtifactBuilder`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/execution/build/MavenArtifactBuilder.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.compiler"/></include> ![Internal][internal] | [`MavenCompilerExtension`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/importing/MavenCompilerExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.executionEnvironmentProvider"/></include> | [`MavenExecutionEnvironmentProvider`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/execution/build/MavenExecutionEnvironmentProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.importer"/></include> ![Deprecated][deprecated] | [`MavenImporter`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/importing/MavenImporter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.importing.afterImportConfigurator"/></include> ![Experimental][experimental] | [`MavenAfterImportConfigurator`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/importing/MavenConfigurators.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.importing.workspaceConfigurator"/></include> ![Experimental][experimental] | [`MavenWorkspaceConfigurator`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/importing/MavenConfigurators.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.log.import.parser"/></include> ![Experimental][experimental] | [`MavenImportLoggedEventParser`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/externalSystemIntegration/output/importproject/MavenImportLoggedEventParser.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.log.parser"/></include> ![Experimental][experimental] | [`MavenLoggedEventParser`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/externalSystemIntegration/output/MavenLoggedEventParser.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.log.spy.parser"/></include> ![Experimental][experimental] | [`MavenSpyLoggedEventParser`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/externalSystemIntegration/output/MavenSpyLoggedEventParser.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.manifestImporter"/></include> | [`ManifestImporter`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/importing/ManifestImporter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.mavenAdditionalHighlighter"/></include> ![Experimental][experimental] | [`MavenAdditionalHightligher`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/dom/MavenAdditionalHightligher.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.mavenRemoteConnectionCreator"/></include> | [`MavenRemoteConnectionCreator`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/execution/MavenRemoteConnectionCreator.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.mavenServerSupportFactory"/></include> ![Internal][internal] | [`MavenRemoteProcessSupportFactory`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/server/MavenRemoteProcessSupportFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.pluginDescriptor"/></include> ![Internal][internal] | `n/a` |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.projectResolutionContributor"/></include> ![Internal][internal] | [`MavenProjectResolutionContributor`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/project/MavenProjectResolver.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.remotePathTransformerFactory"/></include> | [`RemotePathTransformerFactory`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/server/RemotePathTransformerFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.repositoryProvider"/></include> | [`MavenRepositoryProvider`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/indices/MavenRepositoryProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.targetConfigurationExtension"/></include> ![Experimental][experimental] | [`TargetConfigurationMavenExtension`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/execution/target/TargetConfigurationMavenExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.idea.maven.versionAwareMavenSupport"/></include> ![Internal][internal] | [`MavenVersionAwareSupportExtension`](%gh-ic%/plugins/maven/src/main/java/org/jetbrains/idea/maven/MavenVersionAwareSupportExtension.kt) |


## Python Plugin

### Python Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`HuggingFaceCacheUpdateListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.intellij.python.community.impl.huggingFace.cache.HuggingFaceCacheUpdateListener)  | [`HuggingFaceCacheUpdateListener`](%gh-ic%/python/huggingFace/src/com/intellij/python/community/impl/huggingFace/cache/HuggingFaceCacheUpdateListener.kt) |
| [`PyFrameListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.python.debugger.PyFrameListener)  | [`PyFrameListener`](%gh-ic%/python/pydevSrc/src/com/jetbrains/python/debugger/PyFrameListener.kt) |
| [`PyStackFrame#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.python.debugger.PyStackFrame.PyStackFrameRefreshedListener)  | [`PyStackFrameRefreshedListener`](%gh-ic%/python/src/com/jetbrains/python/debugger/PyStackFrame.java) |
| [`PyPackageManager#PACKAGE_MANAGER_TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.python.packaging.PyPackageManager.Listener)  | [`Listener`](%gh-ic%/python/openapi/src/com/jetbrains/python/packaging/PyPackageManager.java) |
| [`PythonPackageManager#PACKAGE_MANAGEMENT_TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.python.packaging.common.PythonPackageManagementListener)  ![Experimental][experimental] | [`PythonPackageManagementListener`](%gh-ic%/python/src/com/jetbrains/python/packaging/common/util.kt) |
| [`PySdkListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=com.jetbrains.python.sdk.PySdkListener)  ![Internal][internal] | [`PySdkListener`](%gh-ic%/python/src/com/jetbrains/python/sdk/PySdkListener.kt) |


### intellij.pycharm.community.ide.impl.xml

[`intellij.pycharm.community.ide.impl.xml`](%gh-ic%/python/ide/impl/resources/intellij.pycharm.community.ide.impl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.miscFileType"/></include> | [`MiscFileType`](%gh-ic%/python/ide/impl/src/com/intellij/pycharm/community/ide/impl/miscProject/MiscFileType.kt) |

### intellij.python.parser.xml

[`intellij.python.parser.xml`](%gh-ic%/python/python-parser/resources/intellij.python.parser.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.dialectsTokenSetContributor"/></include> | [`PythonDialectsTokenSetContributor`](%gh-ic%/python/python-parser/src/com/jetbrains/python/PythonDialectsTokenSetContributor.java) |

### intellij.python.syntax.xml

[`intellij.python.syntax.xml`](%gh-ic%/python/python-syntax/resources/intellij.python.syntax.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pyAnnotator"/></include> ![Experimental][experimental] | [`PyAnnotatorBase`](%gh-ic%/python/python-syntax-core/src/com/jetbrains/python/validation/PyAnnotatorBase.java) |

### PythonCore

[`PythonCore`](%gh-ic%/python/pluginCore/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.PythonPackagingToolwindowActionProvider"/></include> | [`PythonPackagingToolwindowActionProvider`](%gh-ic%/python/src/com/jetbrains/python/packaging/toolwindow/actions/PythonPackagingToolwindowActionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.breakpointHandler"/></include> | [`PyBreakpointHandlerFactory`](%gh-ic%/python/src/com/jetbrains/python/debugger/PyBreakpointHandlerFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.condaSdkCustomizer"/></include> ![Internal][internal] | [`PyCondaSdkCustomizer`](%gh-ic%/python/src/com/jetbrains/python/sdk/conda/PyCondaSdkCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.connectionCredentialsToTargetConfigurationConverter"/></include> ![Internal][internal] | [`ConnectionCredentialsToTargetConfigurationConverter`](%gh-ic%/python/src/com/jetbrains/python/run/target/ConnectionCredentialsToTargetConfigurationConverter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.consoleOptionsProvider"/></include> | [`PyConsoleOptionsProvider`](%gh-ic%/python/src/com/jetbrains/python/console/PyConsoleOptionsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.customProcessHandlerProvider"/></include> ![Internal][internal] | [`PyCustomProcessHandlerProvider`](%gh-ic%/python/src/com/jetbrains/python/run/PyCustomProcessHandlerProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.dataViewPanelFactory"/></include> | [`PyDataViewPanelFactory`](%gh-ic%/python/src/com/jetbrains/python/debugger/containerview/PyDataViewPanelFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.debugSessionFactory"/></include> | [`PyDebugSessionFactory`](%gh-ic%/python/src/com/jetbrains/python/debugger/PyDebugSessionFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.documentationLinkProvider"/></include> | [`PythonDocumentationLinkProvider`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/documentation/PythonDocumentationLinkProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.interpreterTargetEnvironmentFactory"/></include> ![Experimental][experimental] | [`PythonInterpreterTargetEnvironmentFactory`](%gh-ic%/python/src/com/jetbrains/python/run/PythonInterpreterTargetEnvironmentFactory.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.magicLiteral"/></include> ![Internal][internal] | [`PyMagicLiteralExtensionPoint`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/magicLiteral/PyMagicLiteralExtensionPoint.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.packageManagerProvider"/></include> ![Experimental][experimental] | [`PyPackageManagerProvider`](%gh-ic%/python/src/com/jetbrains/python/packaging/PyCustomPackageManagers.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pep8ProblemSuppressor"/></include> | [`Pep8ProblemSuppressor`](%gh-ic%/python/src/com/jetbrains/python/validation/Pep8ProblemSuppressor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.projectSynchronizerProvider"/></include> ![Internal][internal] | [`PyProjectSynchronizerProvider`](%gh-ic%/python/src/com/jetbrains/python/remote/PyProjectSynchronizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pyAddSdkProvider"/></include> | [`PyAddSdkProvider`](%gh-ic%/python/src/com/jetbrains/python/sdk/add/PyAddSdkProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pyCustomSdkUiProvider"/></include> ![Internal][internal] | [`PyCustomSdkUiProvider`](%gh-ic%/python/src/com/jetbrains/python/sdk/PyCustomSdkUiProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pyPregeneratedSkeletonsProvider"/></include> | [`PyPregeneratedSkeletonsProvider`](%gh-ic%/python/src/com/jetbrains/python/sdk/skeletons/PyPregeneratedSkeletonsProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pyRootTypeProvider"/></include> ![Internal][internal] | [`PyRootTypeProvider`](%gh-ic%/python/src/com/jetbrains/python/module/PyRootTypeProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pySdkProvider"/></include> ![Experimental][experimental] | [`PySdkProvider`](%gh-ic%/python/src/com/jetbrains/python/sdk/PySdkProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pyTestConfigurationSelector"/></include> | [`PyTestConfigurationSelector`](%gh-ic%/python/src/com/jetbrains/python/testing/PyTestConfigurationSelector.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pyTestFixtureExtension"/></include> | [`PyTestFixtureExtension`](%gh-ic%/python/src/com/jetbrains/python/testing/pyTestFixtures/PyTestFixtureExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pythonCommandLineEnvironmentProvider"/></include> | [`PythonCommandLineEnvironmentProvider`](%gh-ic%/python/src/com/jetbrains/python/run/PythonCommandLineEnvironmentProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pythonCommandLineTargetEnvironmentProvider"/></include> ![Experimental][experimental] ![Internal][internal] | [`PythonCommandLineTargetEnvironmentProvider`](%gh-ic%/python/src/com/jetbrains/python/run/target/PythonCommandLineTargetEnvironmentProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pythonPackageManagerProvider"/></include> ![Experimental][experimental] | [`PythonPackageManagerProvider`](%gh-ic%/python/src/com/jetbrains/python/packaging/management/PythonPackageManagerService.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pythonSdkComparator"/></include> ![Internal][internal] | [`PySdkComparator`](%gh-ic%/python/src/com/jetbrains/python/sdk/PySdkComparator.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.remoteConsoleProcessCreator"/></include> | [`PythonConsoleRemoteProcessCreator`](%gh-ic%/python/src/com/jetbrains/python/console/PythonConsoleRemoteProcessCreator.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.remoteInterpreterManager"/></include> | [`PythonRemoteInterpreterManager`](%gh-ic%/python/src/com/jetbrains/python/remote/PythonRemoteInterpreterManager.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.remoteProcessStarterManager"/></include> | [`PyRemoteProcessStarterManager`](%gh-ic%/python/src/com/jetbrains/python/run/PyRemoteProcessStarterManager.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.remoteSdkValidator"/></include> | [`PyRemoteSdkValidator`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/sdk/PyRemoteSdkValidator.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.remoteSkeletonGeneratorFactory"/></include> | [`PyRemoteSkeletonGeneratorFactory`](%gh-ic%/python/src/com/jetbrains/python/remote/PyRemoteSkeletonGeneratorFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.runConfigurationEditorExtension"/></include> ![Internal][internal] | [`PyRunConfigurationEditorExtension`](%gh-ic%/python/src/com/jetbrains/python/run/PyRunConfigurationEditorExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.runConfigurationExtension"/></include> | [`PythonRunConfigurationExtension`](%gh-ic%/python/src/com/jetbrains/python/run/PythonRunConfigurationExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.runnableScriptFilter"/></include> | [`RunnableScriptFilter`](%gh-ic%/python/src/com/jetbrains/python/run/RunnableScriptFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.sshInterpreterManager"/></include> | [`PythonSshInterpreterManager`](%gh-ic%/python/src/com/jetbrains/python/remote/PythonSshInterpreterManager.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.systemPythonProvider"/></include> | [`SystemPythonProvider`](%gh-ic%/python/services/system-python/src/com/intellij/python/community/services/systemPython/spi.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.unresolvedReferenceQuickFixProvider"/></include> | [`PyUnresolvedReferenceQuickFixProvider`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/inspections/PyUnresolvedReferenceQuickFixProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.python.configuration.pyIntegratedToolsTestPanelCustomizer"/></include> | [`PyIntegratedToolsTestPanelCustomizer`](%gh-ic%/python/src/com/jetbrains/python/configuration/PyIntegratedToolsTestPanelCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.python.console.customizer"/></include> | [`PyConsoleCustomizer`](%gh-ic%/python/src/com/jetbrains/python/console/PyConsoleCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.python.console.executeCustomizer"/></include> ![Experimental][experimental] | [`PyExecuteConsoleCustomizer`](%gh-ic%/python/src/com/jetbrains/python/console/PyExecuteConsoleCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.python.console.pyConsoleOutputCustomizer"/></include> ![Experimental][experimental] | [`PyConsoleOutputCustomizer`](%gh-ic%/python/src/com/jetbrains/python/console/PyConsoleOutputCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.python.console.runnerFactory"/></include> | [`PythonConsoleRunnerFactory`](%gh-ic%/python/src/com/jetbrains/python/console/PythonConsoleRunnerFactory.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.python.debugger.numericContainerPopupCustomizer"/></include> | [`PyNumericContainerPopupCustomizer`](%gh-ic%/python/pydevSrc/src/com/jetbrains/python/debugger/pydev/tables/PyNumericContainerPopupCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.python.debugger.pyDebugAsyncioCustomizer"/></include> | [`PyDebugAsyncioCustomizer`](%gh-ic%/python/src/com/jetbrains/python/debugger/PyDebugAsyncioCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.python.testing.pyTestLineMarkerContributorCustomizer"/></include> | [`PyTestLineMarkerContributorCustomizer`](%gh-ic%/python/src/com/jetbrains/python/testing/PyTestLineMarkerContributor.kt) |

### PythonParser.xml

[`PythonParser.xml`](%gh-ic%/python/python-parser/resources/META-INF/PythonParser.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.dialectsTokenSetContributor"/></include> | [`PythonDialectsTokenSetContributor`](%gh-ic%/python/python-parser/src/com/jetbrains/python/PythonDialectsTokenSetContributor.java) |

### PythonPsi.xml

[`PythonPsi.xml`](%gh-ic%/python/python-psi-api/resources/META-INF/PythonPsi.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.canonicalPathProvider"/></include> | [`PyCanonicalPathProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/resolve/PyCanonicalPathProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.customPackageIdentifier"/></include> | [`PyCustomPackageIdentifier`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/PyCustomPackageIdentifier.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.importResolver"/></include> | [`PyImportResolver`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/impl/PyImportResolver.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.inspectionExtension"/></include> | [`PyInspectionExtension`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/inspections/PyInspectionExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.keywordArgumentProvider"/></include> | [`PyKeywordArgumentProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/impl/PyKeywordArgumentProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.knownDecoratorProvider"/></include> | [`PyKnownDecoratorProvider`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/psi/PyKnownDecoratorProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pyClassInheritorsSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pyClassMembersProvider"/></include> | [`PyClassMembersProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/types/PyClassMembersProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pyModuleMembersProvider"/></include> | [`PyModuleMembersProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/types/PyModuleMembersProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pyOverridingMethodsSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pyReferenceResolveProvider"/></include> | [`PyReferenceResolveProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/resolve/PyReferenceResolveProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pySuperMethodsSearch"/></include> | [`QueryExecutor`](%gh-ic%/platform/core-api/src/com/intellij/util/QueryExecutor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pythonDocumentationQuickInfoProvider"/></include> | [`PythonDocumentationQuickInfoProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/documentation/PythonDocumentationQuickInfoProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.resolveResultRater"/></include> | [`PyResolveResultRater`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/impl/PyResolveResultRater.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.statementEffectQuickFixProvider"/></include> | [`PyStatementEffectQuickFixProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/inspections/PyStatementEffectQuickFixProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.thirdPartySdkDetector"/></include> ![Experimental][experimental] | [`PyThirdPartySdkDetector`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/resolve/PyThirdPartySdkDetector.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.typeHintProvider"/></include> ![Experimental][experimental] ![Internal][internal] | [`PyTypeHintProvider`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/codeInsight/typing/PyTypeHintProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.typeProvider"/></include> | [`PyTypeProvider`](%gh-ic%/python/python-psi-api/src/com/jetbrains/python/psi/impl/PyTypeProvider.java) |

### PythonPsiImpl.xml

[`PythonPsiImpl.xml`](%gh-ic%/python/python-psi-impl/resources/META-INF/PythonPsiImpl.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.customClassStubType"/></include> ![Internal][internal] | [`PyCustomClassStubType`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/psi/impl/stubs/PyCustomClassStubType.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.customDecoratorStubType"/></include> ![Internal][internal] | [`PyCustomDecoratorStubType`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/psi/impl/stubs/PyCustomDecoratorStubType.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.customTargetExpressionStubType"/></include> ![Internal][internal] | [`CustomTargetExpressionStubType`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/psi/impl/stubs/CustomTargetExpressionStubType.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.importCandidateProvider"/></include> | [`PyImportCandidateProvider`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/codeInsight/imports/PyImportCandidateProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pyDataclassParametersProvider"/></include> | [`PyDataclassParametersProvider`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/codeInsight/PyDataclasses.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pyReferenceCustomTargetChecker"/></include> | [`PyReferenceCustomTargetChecker`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/psi/impl/references/PyReferenceCustomTargetChecker.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pyiStubSuppressor"/></include> ![Experimental][experimental] | [`PyiStubSuppressor`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/pyi/PyiStubSuppressor.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.typeCheckerExtension"/></include> ![Experimental][experimental] | [`PyTypeCheckerExtension`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/psi/types/PyTypeCheckerExtension.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.visitorFilter"/></include> | [`PythonVisitorFilter`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/psi/PythonVisitorFilter.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.jetbrains.python.pythonHelpersLocator"/></include> ![Internal][internal] | [`PythonHelpersLocator`](%gh-ic%/python/python-psi-impl/src/com/jetbrains/python/PythonHelpersLocator.kt) |

### PythonSdk.xml

[`PythonSdk.xml`](%gh-ic%/python/python-sdk/resources/META-INF/PythonSdk.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.projectSdkConfigurationExtension"/></include> ![Internal][internal] | [`PyProjectSdkConfigurationExtension`](%gh-ic%/python/python-sdk/src/com/jetbrains/python/sdk/configuration/PyProjectSdkConfigurationExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pythonFlavorProvider"/></include> | [`PythonFlavorProvider`](%gh-ic%/python/python-sdk/src/com/jetbrains/python/sdk/flavors/PythonFlavorProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pythonSdkFlavor"/></include> | [`PythonSdkFlavor`](%gh-ic%/python/python-sdk/src/com/jetbrains/python/sdk/flavors/PythonSdkFlavor.java) |

### PythonSyntax.xml

[`PythonSyntax.xml`](%gh-ic%/python/python-syntax/resources/META-INF/PythonSyntax.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Pythonid.pyAnnotator"/></include> ![Experimental][experimental] | [`PyAnnotatorBase`](%gh-ic%/python/python-syntax-core/src/com/jetbrains/python/validation/PyAnnotatorBase.java) |


## Terminal Plugin

### Terminal Plugin – Listeners

| Topic | Listener |
|-------|----------|
| [`Constants#TERMINAL_COMMAND_HANDLER_TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.terminal.TerminalCommandHandlerCustomizer.TerminalCommandHandlerListener)  | [`TerminalCommandHandlerListener`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/TerminalCommandHandlerCustomizer.kt) |
| [`BlockTerminalInitializationListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.terminal.block.BlockTerminalInitializationListener)  ![Internal][internal] ![Project-Level][project-level] | [`BlockTerminalInitializationListener`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/block/BlockTerminalInitializationListener.kt) |
| [`CommandHistoryListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.terminal.block.history.CommandHistoryListener)  ![Internal][internal] ![Project-Level][project-level] | [`CommandHistoryListener`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/block/history/CommandHistoryListener.kt) |
| [`CommandSearchListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.terminal.block.history.CommandSearchListener)  ![Internal][internal] ![Project-Level][project-level] | [`CommandSearchListener`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/block/history/CommandSearchListener.kt) |


### intellij.terminal.frontend.xml

[`intellij.terminal.frontend.xml`](%gh-ic%/plugins/terminal/frontend/resources/intellij.terminal.frontend.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.terminal.escapeHandler"/></include> ![Experimental][experimental] | [`TerminalEscapeHandler`](%gh-ic%/plugins/terminal/frontend/src/com/intellij/terminal/frontend/action/TerminalEscapeAction.kt) |

### terminal.xml

[`terminal.xml`](%gh-ic%/plugins/terminal/resources/META-INF/terminal.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.terminal.shellCommandHandler"/></include> | [`TerminalShellCommandHandler`](%gh-ic%/platform/execution-impl/src/com/intellij/terminal/TerminalShellCommandHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.terminal.commandHistoryProvider"/></include> ![Internal][internal] | [`TerminalCommandHistoryProvider`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/block/history/TerminalCommandHistoryProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.terminal.commandSpecsProvider"/></include> ![Experimental][experimental] | [`ShellCommandSpecsProvider`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/block/completion/spec/ShellCommandSpecsProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.terminal.exp.commandBlockHighlighterProvider"/></include> ![Internal][internal] | [`TerminalCommandBlockHighlighterProvider`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/block/output/highlighting/TerminalCommandBlockHighlighterProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.terminal.localTerminalCustomizer"/></include> | [`LocalTerminalCustomizer`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/LocalTerminalCustomizer.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.terminal.openPredefinedTerminalProvider"/></include> | [`OpenPredefinedTerminalActionProvider`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/ui/OpenPredefinedTerminalActionProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.terminal.promptCustomEnterHandler"/></include> ![Internal][internal] | [`TerminalPromptCustomEnterHandler`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/block/prompt/TerminalPromptCustomEnterHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.terminal.shellSupport"/></include> ![Experimental][experimental] | [`TerminalShellSupport`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/exp/completion/TerminalShellSupport.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="org.jetbrains.plugins.terminal.terminalWidgetProvider"/></include> ![Internal][internal] | [`TerminalWidgetProvider`](%gh-ic%/plugins/terminal/src/org/jetbrains/plugins/terminal/TerminalWidgetProvider.kt) |


## VCS Plugins

### VCS Plugins – Listeners

| Topic | Listener |
|-------|----------|
| [`SvnVcs#ROOTS_RELOADED`](https://jb.gg/ipe/listeners?topics=com.intellij.util.Consumer)  ![Obsolete][obsolete] | [`Consumer`](%gh-ic%/platform/util/src/com/intellij/util/Consumer.java) |
| [`GitBranchIncomingOutgoingManager#GIT_INCOMING_OUTGOING_CHANGED`](https://jb.gg/ipe/listeners?topics=git4idea.branch.GitBranchIncomingOutgoingManager.GitIncomingOutgoingListener)  ![Project-Level][project-level] | [`GitIncomingOutgoingListener`](%gh-ic%/plugins/git4idea/src/git4idea/branch/GitBranchIncomingOutgoingManager.java) |
| [`GitAuthenticationListener#GIT_AUTHENTICATION_SUCCESS`](https://jb.gg/ipe/listeners?topics=git4idea.commands.GitAuthenticationListener)  | [`GitAuthenticationListener`](%gh-ic%/plugins/git4idea/src/git4idea/commands/GitAuthenticationListener.java) |
| [`GitMergeCommitMessageChangedListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=git4idea.commit.GitMergeCommitMessageChangedListener)  ![Project-Level][project-level] | [`GitMergeCommitMessageChangedListener`](%gh-ic%/plugins/git4idea/src/git4idea/commit/GitMergeCommitMessagePolicy.kt) |
| [`GitExecutableManager#TOPIC`](https://jb.gg/ipe/listeners?topics=git4idea.config.GitExecutableListener)  | [`GitExecutableListener`](%gh-ic%/plugins/git4idea/src/git4idea/config/GitExecutableListener.java) |
| [`GitFetchInProgressListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=git4idea.fetch.GitFetchInProgressListener)  ![Project-Level][project-level] | [`GitFetchInProgressListener`](%gh-ic%/plugins/git4idea/src/git4idea/fetch/GitFetchInProgressListener.kt) |
| [`GitPushListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=git4idea.push.GitPushListener)  | [`GitPushListener`](%gh-ic%/plugins/git4idea/src/git4idea/push/GitPushListener.kt) |
| [`GitCommitTemplateListener#TOPIC`](https://jb.gg/ipe/listeners?topics=git4idea.repo.GitCommitTemplateListener)  ![Project-Level][project-level] | [`GitCommitTemplateListener`](%gh-ic%/plugins/git4idea/src/git4idea/repo/GitCommitTemplateTracker.kt) |
| [`GitConfigListener#TOPIC`](https://jb.gg/ipe/listeners?topics=git4idea.repo.GitConfigListener)  ![Project-Level][project-level] | [`GitConfigListener`](%gh-ic%/plugins/git4idea/src/git4idea/repo/GitConfigListener.kt) |
| [`GitRepository#GIT_REPO_CHANGE`](https://jb.gg/ipe/listeners?topics=git4idea.repo.GitRepositoryChangeListener)  | [`GitRepositoryChangeListener`](%gh-ic%/plugins/git4idea/src/git4idea/repo/GitRepositoryChangeListener.java) |
| [`GitRepository#GIT_REPO_STATE_CHANGE`](https://jb.gg/ipe/listeners?topics=git4idea.repo.GitRepositoryStateChangeListener)  | [`GitRepositoryStateChangeListener`](%gh-ic%/plugins/git4idea/src/git4idea/repo/GitRepositoryStateChangeListener.kt) |
| [`GitTagHolder#GIT_TAGS_LOADED`](https://jb.gg/ipe/listeners?topics=git4idea.repo.GitTagLoaderListener)  | [`GitTagLoaderListener`](%gh-ic%/plugins/git4idea/src/git4idea/repo/GitTagHolder.kt) |
| [`GitStashSettingsListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=git4idea.stash.ui.GitStashSettingsListener)  | [`GitStashSettingsListener`](%gh-ic%/plugins/git4idea/src/git4idea/stash/ui/GitStashContentProvider.kt) |
| [`GitRefreshListener#TOPIC`](https://jb.gg/ipe/listeners?topics=git4idea.status.GitRefreshListener)  | [`GitRefreshListener`](%gh-ic%/plugins/git4idea/src/git4idea/status/GitRefreshListener.java) |
| [`GitStagingAreaHolder#TOPIC`](https://jb.gg/ipe/listeners?topics=git4idea.status.GitStagingAreaHolder.StagingAreaListener)  | [`StagingAreaListener`](%gh-ic%/plugins/git4idea/src/git4idea/status/GitStagingAreaHolder.java) |
| [`SvnVcs#WC_CONVERTED`](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  ![Project-Level][project-level] | `Runnable` |
| [`RootsAndBranches#REFRESH_REQUEST`](https://jb.gg/ipe/listeners?topics=java.lang.Runnable)  ![Project-Level][project-level] | `Runnable` |
| [`Merger#COMMITTED_CHANGES_MERGED_STATE`](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.svn.integrate.Merger.CommittedChangesMergedStateChanged)  ![Project-Level][project-level] | [`CommittedChangesMergedStateChanged`](%gh-ic%/plugins/svn4idea/src/org/jetbrains/idea/svn/integrate/Merger.java) |
| [`SvnMergeInfoCache#SVN_MERGE_INFO_CACHE`](https://jb.gg/ipe/listeners?topics=org.jetbrains.idea.svn.mergeinfo.SvnMergeInfoCache.SvnMergeInfoCacheListener)  ![Project-Level][project-level] | [`SvnMergeInfoCacheListener`](%gh-ic%/plugins/svn4idea/src/org/jetbrains/idea/svn/mergeinfo/SvnMergeInfoCache.java) |
| [`GHPRDataOperationsListener.Companion#TOPIC`](https://jb.gg/ipe/listeners?topics=org.jetbrains.plugins.github.pullrequest.data.provider.GHPRDataOperationsListener)  | [`GHPRDataOperationsListener`](%gh-ic%/plugins/github/github-core/src/org/jetbrains/plugins/github/pullrequest/data/provider/GHPRDataOperationsListener.kt) |
| [`HgVcs#REMOTE_TOPIC`](https://jb.gg/ipe/listeners?topics=org.zmlx.hg4idea.HgUpdater)  ![Project-Level][project-level] | [`HgUpdater`](%gh-ic%/plugins/hg4idea/src/org/zmlx/hg4idea/HgUpdater.java) |
| [`HgVcs#STATUS_TOPIC`](https://jb.gg/ipe/listeners?topics=org.zmlx.hg4idea.HgUpdater)  ![Project-Level][project-level] | [`HgUpdater`](%gh-ic%/plugins/hg4idea/src/org/zmlx/hg4idea/HgUpdater.java) |
| [`HgVcs#INCOMING_OUTGOING_CHECK_TOPIC`](https://jb.gg/ipe/listeners?topics=org.zmlx.hg4idea.status.ui.HgWidgetUpdater)  ![Project-Level][project-level] | [`HgWidgetUpdater`](%gh-ic%/plugins/hg4idea/src/org/zmlx/hg4idea/status/ui/HgWidgetUpdater.java) |


### intellij.vcs.git.xml

[`intellij.vcs.git.xml`](%gh-ic%/plugins/git4idea/resources/intellij.vcs.git.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Git4Idea.GitCheckinExplicitMovementProvider"/></include> | [`GitCheckinExplicitMovementProvider`](%gh-ic%/plugins/git4idea/src/git4idea/checkin/GitCheckinExplicitMovementProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Git4Idea.GitHttpAuthDataProvider"/></include> | [`GitHttpAuthDataProvider`](%gh-ic%/plugins/git4idea/src/git4idea/remote/GitHttpAuthDataProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Git4Idea.gitAnnotationPerformanceListener"/></include> ![Internal][internal] | [`GitAnnotationPerformanceListener`](%gh-ic%/plugins/git4idea/src/git4idea/annotate/GitAnnotationPerformanceListener.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Git4Idea.gitCurrentBranchPresenter"/></include> | [`GitCurrentBranchPresenter`](%gh-ic%/plugins/git4idea/src/git4idea/ui/branch/GitCurrentBranchPresenter.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Git4Idea.gitFetchHandler"/></include> | [`GitFetchHandler`](%gh-ic%/plugins/git4idea/src/git4idea/fetch/GitFetchHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Git4Idea.gitPostUpdateHandler"/></include> | [`GitPostUpdateHandler`](%gh-ic%/plugins/git4idea/src/git4idea/update/GitPostUpdateHandler.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Git4Idea.gitProtectedBranchProvider"/></include> | [`GitProtectedBranchProvider`](%gh-ic%/plugins/git4idea/src/git4idea/config/GitProtectedBranchProvider.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Git4Idea.gitPushNotificationCustomizer"/></include> ![Internal][internal] ![Project-Level][project-level] | [`GitPushNotificationCustomizer`](%gh-ic%/plugins/git4idea/src/git4idea/push/GitPushNotificationCustomizer.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Git4Idea.gitRawAnnotationProvider"/></include> ![Experimental][experimental] ![Project-Level][project-level] | [`GitRawAnnotationProvider`](%gh-ic%/plugins/git4idea/src/git4idea/annotate/GitAnnotationProvider.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Git4Idea.gitRepositoryHostingService"/></include> | [`GitRepositoryHostingService`](%gh-ic%/plugins/git4idea/src/git4idea/remote/GitRepositoryHostingService.java) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="Git4Idea.instantGitTokenProvider"/></include> ![Experimental][experimental] ![Internal][internal] | [`InstantGitTokenProvider`](%gh-ic%/plugins/git4idea/src/git4idea/instant/InstantGitTokenProvider.kt) |

### intellij.vcs.github.xml

[`intellij.vcs.github.xml`](%gh-ic%/plugins/github/github-core/resources/intellij.vcs.github.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.github.gistContentsCollector"/></include> | [`GithubGistContentsCollector`](%gh-ic%/plugins/github/github-core/src/org/jetbrains/plugins/github/GithubGistContentsCollector.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="intellij.vcs.github.aiReviewExtension"/></include> ![Internal][internal] | [`GHPRAIReviewExtension`](%gh-ic%/plugins/github/github-core/src/org/jetbrains/plugins/github/ai/GHPRAIReviewExtension.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="intellij.vcs.github.aiSummaryExtension"/></include> ![Internal][internal] | [`GHPRAISummaryExtension`](%gh-ic%/plugins/github/github-core/src/org/jetbrains/plugins/github/ai/GHPRAISummaryViewModel.kt) |
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="intellij.vcs.github.titleAndDescriptionGenerator"/></include> ![Internal][internal] | [`GHPRTitleAndDescriptionGeneratorExtension`](%gh-ic%/plugins/github/github-core/src/org/jetbrains/plugins/github/pullrequest/ui/toolwindow/create/GHPRCreateTitleAndDescriptionGenerationViewModel.kt) |

### intellij.vcs.gitlab.xml

[`intellij.vcs.gitlab.xml`](%gh-ic%/plugins/gitlab/gitlab-core/resources/intellij.vcs.gitlab.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="intellij.vcs.gitlab.titleGenerator"/></include> ![Internal][internal] | [`GitLabTitleGeneratorExtension`](%gh-ic%/plugins/gitlab/gitlab-core/src/org/jetbrains/plugins/gitlab/mergerequest/ui/create/model/GitLabMergeRequestCreateTitleGenerationViewModel.kt) |

### Subversion

[`Subversion`](%gh-ic%/plugins/svn4idea/resources/META-INF/plugin.xml)

| Extension Point | Implementation |
|-----------------|----------------|
| <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.vcs.svn.mergerCommitMessage"/></include> ![Project-Level][project-level] | [`MergerCommitMessage`](%gh-ic%/plugins/svn4idea/src/org/jetbrains/idea/svn/integrate/MergerCommitMessage.kt) |


[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square

</snippet>
