<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Introduction to Integration Tests

<primary-label ref="2023.2"/>

<link-summary>Walkthrough how to create the first integration tests.</link-summary>

> This page is part of the [](integration_tests.md) tutorial.

## Adding dependencies

Integration testing framework consists of two main components:

* **Starter**: Handles IDE configuration, test project setup, IDE startup, and output collection.
* **Driver**: Provides additional functionality like UI interaction and JMX calls of IDE's API.

The Starter framework exclusively supports JUnit 5, as it leverages JUnit 5's extensions and specialized listeners that aren't available in JUnit 4.

To create a new task - `integrationTest`, define new test source roots - `integrationTest`, and add required dependencies, update the `build.gradle.kts` file:

```kotlin
dependencies {
  intellijPlatform {
    //...
    testFramework(TestFrameworkType.Starter)
  }

sourceSets {
  create("integrationTest") {
    compileClasspath += sourceSets.main.get().output
    runtimeClasspath += sourceSets.main.get().output
  }
}

val integrationTestImplementation by configurations.getting {
  extendsFrom(configurations.testImplementation.get())
}

dependencies {
  integrationTestImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
  integrationTestImplementation("org.kodein.di:kodein-di-jvm:7.20.2")
  integrationTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.10.1")
}

val integrationTest = task<Test>("integrationTest") {
  val integrationTestSourceSet = sourceSets.getByName("integrationTest")
  testClassesDirs = integrationTestSourceSet.output.classesDirs
  classpath = integrationTestSourceSet.runtimeClasspath
  systemProperty("path.to.build.plugin", tasks.prepareSandbox.get().pluginDirectory.get().asFile)
  useJUnitPlatform()
  dependsOn(tasks.prepareSandbox)
}
```

The following dependencies are required:

* `testFramework(TestFrameworkType.Starter)` will add all required dependencies for writing integration tests - Starter and Driver frameworks.
* `org.kodein.di:kodein-di-jvm` is a dependency injection framework used by Starter for configuration.
* `org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm` is required for Starter framework which is implemented using Kotlin coroutines.

This configuration does the following:

* Imports test implementation dependencies to `integrationTest` implementation dependencies
* Defines new test source roots in <path>src/integrationTest</path>
* Creates a new task `integrationTest`
* Makes the test task depend on `prepareSandbox`, ensuring the plugin is built before tests run.
* To test a plugin, the Starter framework needs to know where to find the plugin distribution for installation in the IDE.
The `path.to.build.plugin` system property points to the plugin distribution file.
* Enables JUnit Platform for test execution.

For more details about configuring integration tests, please refer to [Gradle docs](https://docs.gradle.org/current/userguide/java_testing.html#sec:configuring_java_integration_tests).

> Note: Driver and UI components are in the experimental state and subject to API changes.
>
{style="note"}

## Creating the First Integration Test

Now that the configuration is complete, it's time to write the first integration test, which will:

* Start the IDE with the plugin installed.
* Wait for all background processes to complete.
* Perform a shutdown.

Create a new Kotlin file in `src/integrationTest/kotlin` with the following code:

```kotlin

class PluginTest {
  @Test
  fun simpleTestWithoutProject() {
    Starter.newContext(
      testName = "testExample",
      TestCase(IdeProductProvider.IC, projectInfo = NoProject)
        .withVersion("2024.3")
    ).apply {
      val pathToPlugin = System.getProperty("path.to.build.plugin")
      PluginConfigurator(this).installPluginFromFolder(File(pathToPlugin))
    }.runIdeWithDriver().useDriverAndCloseIde {
    }
  }
}
```

Let's break down each part of the test:

### 1. Context Creation

```kotlin
Starter.newContext(
  testName = "testExample",
  TestCase(IdeProductProvider.IC, projectInfo = NoProject
).withVersion("2024.3"))
```

The Context object stores IDE runtime configuration:

* IDE type (e.g., IntelliJ Community, PhpStorm, GoLand).
* IDE version (2024.3 in this example).
* Project configuration (using `NoProject` for this example).
* Custom VM options, paths, and SDK settings.

The `testName` parameter defines the folder name for test artifacts, which is useful when running multiple IDE instances in a single test.
The test case uses IntelliJ IDEA Community Edition version 2024.3, and starts the IDE without any project, so the welcome screen will be shown.

### 2. Plugin Installation

```kotlin
.apply {
  val pathToPlugin = System.getProperty("path.to.build.plugin")
  PluginConfigurator(this).installPluginFromFolder(File(pathToPlugin))
}
```

This step configures plugin installation using the plugin path defined in the Gradle configuration with the `path.to.build.plugin` system property.

> `PluginConfigurator` can install plugins from local paths or Marketplace.
>
{style="note"}

### 3. IDE Life Cycle Management

```kotlin
.runIdeWithDriver().useDriverAndCloseIde {
}
```

These two methods:

* Start the IDE instance (`runIdeWithDriver`).
* Shut down the IDE (`useDriverAndCloseIde`).

The empty lambda is used for IDE interactions (`useDriverAndCloseIde`).

> When the test is run for the first time, it may take longer than expected since it needs to download the IDE.
> Subsequent runs will be faster, using the cached IDE version.
>
{style="note"}

## Understanding the Test Architecture

The test can now be run and it should pass.

Integration tests operate across two separate processes:

* Test process:
    * Executes test code, sending commands to the IDE.
    * Manages the IDE life cycle.
    * Controls test flow and assertions.
* IDE process:
    * Listens and executes commands from the test process.

This dual-process architecture explains several key aspects of integration testing:

* Why debugging requires special considerations.
* The need for a communication protocol between test and IDE processes.
* Why a built plugin distribution is required.
* The origin of certain test-specific exceptions.

## Opening Projects in Tests

While starting the IDE with an empty project is useful, often it is required to use actual projects to verify real-world scenarios.
Let's modify the test to open a project.

The framework supports several ways to specify test projects:

* **GitHub projects**: `GitHubProject.fromGithub( branchName = "master", "JetBrains/ij-perf-report-aggregator" )`
* **Remote archives**: `RemoteArchiveProjectInfo("https://github.com/JetBrains/intellij-community/archive/master.zip")`
* **Local projects**: `LocalProjectInfo(Path("src/test/resources/test-projects/simple-project"))`

The following example shows how to open a GitHub project:

```kotlin
@Test
fun simpleTest() {
  Starter.newContext(
    "testExample",
    TestCase(
      IdeProductProvider.IC,
      GitHubProject.fromGithub(
        branchName = "master",
        repoRelativeUrl = "JetBrains/ij-perf-report-aggregator"
      )
    ).withVersion("2024.2")
  ).apply {
    val pathToPlugin = System.getProperty("path.to.build.plugin")
    PluginConfigurator(this).installPluginFromFolder(File(pathToPlugin))
  }.runIdeWithDriver().useDriverAndCloseIde {
    waitForIndicators(5.minutes)
  }
}
```

While simple, this test verifies a critical aspect: the plugin doesn't interfere with IDE startup.

> `waitForIndicators` inside the lambda ensures that the test waits till all indicators are gone before exiting the IDE.
>
{style="note"}

## Catching Exceptions from IDE

The test has one critical limitation: it won't detect exceptions or freezes occurring within the IDE process.
Let's understand why and how to fix this.

Due to the two-process architecture:

* Exceptions in the IDE process aren't automatically propagated to the test process.
* A bundled plugin collects exceptions from the IDE's `MessageBus`.
* These exceptions are stored in the error folder within the logs.
* The Starter framework collects exceptions from the IDE and pass them to the method `reportTestFailure` in object registered as `CIServer` in DI.

TeamCity reporting is used by default, falling back to `NoCIServer` for other environments.
However, this can be customized by using Kodein Dependency Injection.

The following example shows how to make tests fail when IDE exceptions occur:

```kotlin
init {
  di = DI {
    extend(di)
    bindSingleton<CIServer>(overrides = true) {
      object : CIServer by NoCIServer {
        override fun reportTestFailure(
          testName: String,
          message: String,
          details: String,
          linkToLogs: String?
        ) {
          fail { "$testName fails: $message. \n$details" }
        }
      }
    }
  }
}
```

The code above:

* Overrides a default `CIServer` object with a custom implementation.
* Provides `reportTestFailure` method which fails the test with detailed error information if any IDE exception is recorded during the test run.

This extensibility pattern can be applied to customize other aspects of the Starter framework as needed.

## Complete Example

Here's the complete test implementation that forms the foundation for future plugin testing:

```kotlin
class PluginTest {
  init {
    di = DI {
      extend(di)
      bindSingleton<CIServer>(overrides = true) {
        object : CIServer by NoCIServer {
          override fun reportTestFailure(
            testName: String,
            message: String,
            details: String,
            linkToLogs: String?
          ) {
            fail { "$testName fails: $message. \n$details" }
          }
        }
      }
    }
  }
  @Test
  fun simpleTest() {
    val result = Starter.newContext(
      "testExample",
      TestCase(
        IdeProductProvider.IC,
        GitHubProject.fromGithub(
          branchName = "master",
          repoRelativeUrl = "JetBrains/ij-perf-report-aggregator"
        )
      ).withVersion("2024.2")
    ).apply {
      val pathToPlugin = System.getProperty("path.to.build.plugin")
      PluginConfigurator(this).installPluginFromFolder(File(pathToPlugin))
    }.runIdeWithDriver().useDriverAndCloseIde {
      waitForIndicators(5.minutes)
    }
  }
}
```

This test provides a robust foundation for more elaborate tests by:

* Downloading and opening a real project.
* Starting the IDE with a plugin installed.
* Waiting for all background processes to complete.
* Monitoring for exceptions and freezes.
* Performing a shutdown.


