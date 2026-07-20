<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# IntelliJ IDE Starter Changelog

<link-summary>Notable changes and fixes in the IntelliJ IDE Starter framework by release.</link-summary>

## 2026.2

* Examples were moved to a separate GitHub repository: [ide-starter-examples](%gh-starter-examples-master%).
* Source code was moved to the community code base.
  Most of the relevant modules can be found [here](%gh-ic-master%/tools).

### Breaking Changes {#breaking-changes-2026-2}
* To use the default `IdeInfo` of a certain IDE, add the
  `testImplementation("com.jetbrains.intellij.tools:ide-starter-product-idea-ultimate:LATEST-EAP-SNAPSHOT")` dependency.
* To make tests work, add `jvmArgs("--add-opens=java.base/sun.nio.fs=ALL-UNNAMED")` as done in the examples.
* IntelliJ IDEA Community Edition test cases were dropped as it was merged with IntelliJ IDEA Ultimate in 2025.3.
* Update to `jvmToolchain(25)` and `kotlin("jvm") version "2.3.0"` to be able to run recent IDE versions.

## 2025.1

### Improvements {#improvements-2025-1}

* New method `com.intellij.ide.starter.models.VMOptions.configureLoggers` to configure loggers.
* ScreenRecorder is disabled for Wayland since it causes a system dialog.
* `runIde` now accepts `ExecOutputRedirect` as a parameter to redirect output of the IDE process.
* New event type `IdeBeforeRunIdeProcessEvent` which can be used to subscribe right before IDE start.

### Fixes {#fixes-2025-1}

* `ExistingIdeInstaller` was fixed on Windows.
* Various improvements for Driver and split mode.
* Fix Rider downloading.
* Freezes are reported only from the errors folder to avoid false positives.

### Breaking Changes {#breaking-changes-2025-1}
* `getListOfIndexingMetrics()` now returns `IndexingMetric`. Use the following code to convert to a standard `Metric`.

```kotlin
val indexingMetrics = extractIndexingMetrics(results).getListOfIndexingMetrics().map {
  when (it) {
    is IndexingMetric.Duration -> PerformanceMetrics.newDuration(it.name, it.durationMillis)
    is IndexingMetric.Counter -> PerformanceMetrics.newCounter(it.name, it.value)
  }
}
```

## 2024.3

### Improvements {#improvements-2024-3}

* Parsing of 2Gb <path>opentelemetry.json</path> now requires 3x less heap size.
* Reworked and unified metrics collection. There is a `MetricsCollector` interface with the main implementations
  `StarterTelemetrySpanCollector` and
  `StarterTelemetryJsonMeterCollector` for collecting spans and meters respectively.
* It is possible to customize `PublicIdeDownloader` by overriding the `mapDownloadLink` method.
* `TimeoutAnalyzer` class that infers the reason of a hanging test.
* Implementation of Split mode which can be run using the env variable `REMOTE_DEV_RUN=true`.

### Fixes {#fixes-2024-3}
* Environment variables are not filtered out on Linux.

### Breaking Changes {#breaking-changes-2024-3}

* Method `com.intellij.tools.ide.metrics.collector.starter.collector.getMetricsFromSpanAndChildren` was removed.
  Use `StarterTelemetrySpanCollector(spanFilter).collect(ideStartResult.runContext)` instead.
* In `com.intellij.tools.ide.metrics.collector.metrics.PerformanceMetrics.Metric` the field `value` is now `Int` instead of `Long`.
* `com.intellij.tools.ide.metrics.collector.publishing.IJPerfMetricsDto` was simplified to include only the required data:
    * `buildInfo` is no longer needed for the `create` method.
    * Fields `productCode`, `tcInfo`, `os`, `osFamily`, `runtime`, `branch` were removed.
    * New value `mode` was added to distinguish, for example, `split` and `monolith`.
* Kotlin 2.0 is required.

## 2024.2

### Improvements {#improvements-2024-2}

* A new protocol to communicate with an IDE is introduced — Driver.
  See the [Driver README](%gh-ic%/tools/intellij.tools.ide.starter.driver/README.md) for more details.
