<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Integration Tests: Starter Configuration

<primary-label ref="2023.2"/>

<link-summary>Walkthrough how to customize and configure the Starter framework behavior.</link-summary>

> This page is part of the [](integration_tests.md) tutorial.

For introduction and setting up dependencies, refer to [](integration_tests_intro.md).

## How to Override/Modify Default Starter Behavior

Any behavior initialized through the `Kodein` DI framework can be modified or extended.
To do so, refer to the
[DI container initialization](%gh-ic%/tools/intellij.tools.ide.starter/src/com/intellij/ide/starter/di/diContainer.kt).

For example, create a custom implementation of `com.intellij.ide.starter.ci.CIServer` and provide it through DI.
Make sure to use the same `Kodein` version specified in the Starter project's `build.gradle`.

Example:

```kotlin
di = DI {
      extend(di)
      bindSingleton<CIServer>(overrides = true) { YourImplementationOfCI() }
}
```

## Debugging the Test

> If the `debugger.auto.attach.from.console` registry key is enabled,
> the test can be run under the debugger in IntelliJ IDEA, and attachment happens automatically.
>
{style="tip"}

Since the IDE runs as a separate process from the test, the test cannot be debugged directly.
To debug a test, connect remotely to the IDE instance.

General debugging workflow:

1. Create a run configuration for <control>Remote JVM Debug</control>:
  - <control>Debugger mode</control>: <control>Attach to Remote JVM</control>
  - <control>Host</control>: `localhost`
  - <control>Port</control>: `5005`
  - <control>Command line arguments for remote JVM</control>: `-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005`
2. Run the test.
3. The required option will be added automatically.

After seeing the console prompt to connect remotely to port 5005, run the created run configuration.

## Using JUnit5 Extensions to Modify Starter Behavior

For JUnit5, several extensions provide a convenient way to set configuration variables.
A list of extensions is available in the
[config package](%gh-ic%/tools/intellij.tools.ide.starter.junit5/src/com/intellij/ide/starter/junit5/config).

Example:

```kotlin
@ExtendWith(EnableClassFileVerification::class)
@ExtendWith(UseLatestDownloadedIdeBuild::class)
class ClassWithTest {
...
}
```

Environment variables that tweak Starter behavior may also be useful.
They are located in `com.intellij.ide.starter.config.StarterConfigurationStorage`.

## Downloading Custom Releases

By default, when `useEAP()` or `useRelease()` methods are called,
IDE installers will be downloaded from JetBrains' public hosting.
If no version is specified, the latest version will be used.
However, a specific version can be specified if needed.

## How to Specify Another URL for IDE Downloading

1. Override the default `IdeDownloader` with `IdeByLinkDownloader`.
   This downloader uses the `downloadURI` field from `IdeInfo`.

```kotlin
init {
  di = DI {
    extend(di)
    bindSingleton<IdeDownloader>(overrides = true) { IdeByLinkDownloader }
  }
}
```

2. Create a custom `IdeInfo` by taking the predefined IntelliJ IDEA Ultimate configuration and overriding only the download URL.

```kotlin
Starter.newContext(
  testName = "custom-ide-download-url",
  testCase = TestCase(
    IdeProductProvider.IU.copy(
      downloadURI = URI("https://example.com/idea-IU-installer.dmg")
    ),
    GitHubProject.fromGithub(
      branchName = "master",
      repoRelativeUrl = "jitpack/gradle-simple.git"
    )
  )
)
```

`IdeProductProvider.IU.copy(downloadURI = URI("https://example.com/idea-IU-installer.dmg"))`
is the key part of this example.
It starts with the standard IntelliJ IDEA Ultimate configuration and changes only the `downloadURI` field,
so Starter will download the IDE from the specified URL.

This example uses `IdeProductProvider.IU` directly, so no additional `IdeInfo.IdeaUltimate` setup is required here.

## Modifying VM Options

There are two ways to modify the VM options.
One is on `IDETestContext`, and the other is on `IDERunContext`.
The first one is used to modify VM options for the whole context that can be reused between runs.
The second is used to modify VM options for the current run only.

## Performance Testing/Metrics Collection

<!-- TODO: replace the two module README links below with links to specific code fragments (colleague to fill in). -->

Out of the box, Starter can collect OpenTelemetry metrics using the
[`intellij.tools.ide.metrics.collector.starter`](%gh-ic%/tools/intellij.tools.ide.metrics.collector.starter#readme)
module.

For a more general approach to OpenTelemetry metrics collection (without Starter), see the
[`intellij.tools.ide.metrics.collector`](%gh-ic%/tools/intellij.tools.ide.metrics.collector#readme)
module.

Unit tests can also be run as benchmark tests via
[`Benchmark.newBenchmark()`](%gh-ic%/tools/intellij.tools.ide.metrics.benchmark/src/com/intellij/tools/ide/metrics/benchmark/Benchmark.java).
See [examples of usages in IntelliJ repo](https://github.com/search?q=repo%3AJetBrains%2Fintellij-community%20Benchmark.newBenchmark&type=code){ignore-vars="true"}.

More details can be found in
[`BenchmarkTestInfo.start()`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/BenchmarkTestInfo.java),
[`BenchmarkTestInfo.startAsSubtest()`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/BenchmarkTestInfo.java)
and
[`BenchmarkTestInfoImpl.withMetricsCollector()`](%gh-ic%/tools/intellij.tools.ide.metrics.benchmark/src/com/intellij/tools/ide/metrics/benchmark/BenchmarkTestInfoImpl.java).
