---
title: Execution
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The standard execution of a run action goes through the following steps:

* The user selects a *run configuration* (for example, by choosing one from the run configurations combobox) and an *executor*  (for example, by pressing a toolbar button created by the executor).
* The *program runner* that will actually execute the process is selected, by polling all registered program runners and asking whether they can run the specified run profile with the specified executor ID.
* The [`ExecutionEnvironment`](upsource:///platform/lang-api/src/com/intellij/execution/runners/ExecutionEnvironment.java) object is created. This object aggregates all the settings required to execute the process, as well as the selected [`ProgramRunner`](upsource:///platform/lang-api/src/com/intellij/execution/runners/ProgramRunner.java).
* `ProgramRunner.execute()` is called, receiving the executor and the execution environment.

Implementations of `ProgramRunner.execute()` go through the following steps to execute the process:

* `RunProfile.getState()` method is called to create a [`RunProfileState`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/RunProfileState.java) object, describing a process about to be started. At this stage, the command line parameters, environment variables and other information required to start the process is initialized.
* `RunProfileState.execute()` is called. It starts the process, attaches a `ProcessHandler` to its input and output streams, creates a console to display the process output, and returns an [`ExecutionResult`](upsource:///platform/lang-api/src/com/intellij/execution/ExecutionResult.java) object aggregating the console and the process handler.
* The `RunContentBuilder` object is created and invoked to display the execution console in a tab of the Run or Debug tool window.

## Executor

The [`Executor`](upsource:///platform/lang-api/src/com/intellij/execution/Executor.java) interface describes a specific way of executing any possible run configuration.

The three default executors provided by the *IntelliJ Platform* by default are _Run_, _Debug_ and _Run with Coverage_.  Each executor gets its own toolbar button, which starts the selected run configuration using this executor, and its own context menu item for starting a configuration using this executor.

As a plugin developer, you normally don't need to implement the `Executor` interface. However, it can be useful, for example, if you're implementing a profiler integration and want to provide the possibility to execute any configuration with profiling.

## Running a Process

The `RunProfileState` interface comes up in every run configuration implementation as the return value `RunProfile.getState()`. It describes a process which is ready to be started and holds the information like the command line, current working directory, and environment variables for the process to be started. (The existence of `RunProfileState` as a separate step in the execution flow allows run configuration extensions and other components to patch the configuration and to modify the parameters before it gets executed.)

The standard base class used as implementation of `RunProfileState` is [`CommandLineState`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/CommandLineState.java). It contains the logic for putting together a running process and a console into an [`ExecutionResult`](upsource:///platform/lang-api/src/com/intellij/execution/ExecutionResult.java), but doesn't know anything how the process is actually started. For starting the process, it's best to use the [`GeneralCommandLine`](upsource:///platform/platform-util-io/src/com/intellij/execution/configurations/GeneralCommandLine.java) class, which takes care of setting up the command line parameters and executing the process.

Alternatively, if the process you need to run is a JVM-based one, you can use the [`JavaCommandLineState`](upsource:///java/execution/openapi/src/com/intellij/execution/configurations/JavaCommandLineState.java) base class. It knows about the command line parameters of the JVM and can take care of details like calculating the classpath for the JVM.

To monitor the execution of a process and capture its output, the [`OSProcessHandler`](upsource:///platform/platform-util-io/src/com/intellij/execution/process/OSProcessHandler.java) class is normally used. Once you've created an instance of `OSProcessHandler` from either a command line or a Process object, you need to call the `startNotify()` method to start capturing its output. You may also want to attach a [`ProcessTerminatedListener`](upsource:///platform/platform-api/src/com/intellij/execution/process/ProcessTerminatedListener.java) to the `OSProcessHandler`, so that the exit status of the process will be displayed in the console.

## Displaying Process Output

If you're using `CommandLineState`, a console view will be automatically created and attached to the output of the process. Alternatively, you can arrange this yourself:

* `TextConsoleBuilderFactory.createBuilder(project).getConsole()` creates a [`ConsoleView`](upsource:///platform/lang-api/src/com/intellij/execution/ui/ConsoleView.java) instance
* `ConsoleView.attachToProcess()` attaches it to the output of a process.

If the process you're running uses ANSI escape codes to color its output, the [`ColoredProcessHandler`](upsource:///platform/platform-impl/src/com/intellij/execution/process/ColoredProcessHandler.java) class will parse it and display the colors in the IntelliJ console.

Console [filters](upsource:///platform/lang-api/src/com/intellij/execution/filters/Filter.java) allow you to convert certain strings found in the process output to clickable hyperlinks. To attach a filter to the console, use `CommandLineState.addConsoleFilters()` or, if you're creating a console manually, `TextConsoleBuilder.addFilter()`.

Two common filter implementations you may want to reuse are [`RegexpFilter`](upsource:///platform/lang-api/src/com/intellij/execution/filters/RegexpFilter.java) and [`UrlFilter`](upsource:///platform/lang-api/src/com/intellij/execution/filters/UrlFilter.java).

## Starting a Run Configuration from Code

If you have an existing run configuration that you need to execute, the easiest way to do so is to use [`ProgramRunnerUtil.executeConfiguration()`](upsource:///platform/execution-impl/src/com/intellij/execution/ProgramRunnerUtil.java). The method takes a [`Project`](upsource:///platform/core-api/src/com/intellij/openapi/project/Project.java), a [`RunnerAndConfigurationSettings`](upsource:///platform/lang-api/src/com/intellij/execution/RunnerAndConfigurationSettings.java), as well as an [`Executor`](upsource:///platform/lang-api/src/com/intellij/execution/Executor.java). To get the `RunnerAndConfigurationSettings` for an existing configuration, you can use, for example, `RunManager.getConfigurationSettings(ConfigurationType)`. As the last parameter, you normally pass either `DefaultRunExecutor.getRunExecutorInstance()` or `DefaultDebugExecutor.getDebugExecutorInstance()`.
