<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# External Builder API and Plugins
<primary-label ref="IntelliJIDEA"/>

<link-summary>Working with External Builder API.</link-summary>

> Adding JPS support to your plugin requires Java plugin to be present for it to work.
> Please see [Plugin Dependencies](plugin_dependencies.md) on how to set up your plugin with required dependency.
>
{style="note"}

### External Build Process Workflow

When the user invokes an action that involves executing an external build (Make, Build Artifacts, etc.), the following steps happen:

* Before-compile tasks are performed in the IDE process.
* Some source generation tasks that depend on the PSI (e.g., UI designer form to source compilation) are executed in the IDE process.
* [`BuildTargetScopeProvider`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/impl/BuildTargetScopeProvider.java) extensions are called to calculate the scope of the external build (the set of build targets to compile based on the target module to make and the known set of changes).
* The external build process is spawned (or an existing build process background process is reused).
* The external build process loads the project model (<path>.idea</path>, <path>.iml</path> files, and so on), represented by a [`JpsModel`](%gh-ic%/jps/model-api/src/org/jetbrains/jps/model/JpsModel.java) instance.
* The full tree of targets to build is calculated based on each build target's dependencies to be compiled.
* For each target, the set of builders capable of building this target is calculated.
* For every target and every builder, the `build()` method is called.
  This can happen in parallel if the "Compile independent modules in parallel" option is enabled in the settings.
  For module-level builders, the order of invoking builders for a single target is determined by their category; for other builders, the order is undefined.
* Caches to record the state of the compilation are saved.
* Compilation messages reported through the [`CompileContext`](%gh-ic%/jps/jps-builders/src/org/jetbrains/jps/incremental/CompileContext.java) API are transmitted to the IDE process and displayed in the UI (in the *Messages* view).
* Post-compile tasks are executed in the IDE process.

### Incremental Build

To support incremental build, the build process uses several caches which are persisted between build invocations.
Even if your compiler doesn't support incremental build, you still need to report correct information so that incremental build works correctly for other compilers.

* [`SourceToOutputMapping`](%gh-ic%/jps/jps-builders/src/org/jetbrains/jps/builders/storage/SourceToOutputMapping.java) is a many-to-many relationship between source files and output files ("which source files were used to produce the specified output file").
  It's filled by calls to `BuildOutputConsumer.registerOutputFile()` and `ModuleLevelBuilder.OutputConsumer.registerOutputFile()`.

The IDE monitors the project content changes and uses the information from those caches to generate the set of dirty and deleted files for every compilation. (Dirty files need to be recompiled, and deleted files need to have their output deleted).
A builder can also report additional files as dirty (e.g., if a method is deleted, the builder can report the classes using this method as dirty.) A module-level builder can add some files to the dirty scope; if this happens, and if the builder returns `ADDITIONAL_PASS_REQUIRED` from its `build()` method, another round of builder execution for the same module chunk will be started with the new dirty scope.

A builder may also want to have its custom caches to store additional information to support the partial recompilation of a target (e.g., the dependencies between Java files in a module).
To store this data, you can either store arbitrary files in the directory returned from `BuildDataManager.getDataPaths().getTargetDataRoot()` or use a higher-level API: `BuildDataManager.getStorage()`

To pass custom data between the invocation of the same builder between multiple targets, you can use `CompileContext.getUserData()` and `CompileContext.putUserData()`.

### Services and Extensions in External Builder

The external builder process uses the standard Java [services](https://docs.oracle.com/en/java/javase/24/docs/api/java.base/java/util/ServiceLoader.html) mechanism to support plugins.
There are several service interfaces (e.g. [`BuilderService`](%gh-ic%/jps/jps-builders/src/org/jetbrains/jps/incremental/BuilderService.java)) which can be implemented in plugins to extend the builder functionality.
An implementation of a service needs to be registered by creating the <path>META-INF/services/\$service-interface-fqn\$</path> file containing the implementation class's qualified name.
E.g. `BuilderService` implementations are registered in <path>META-INF/services/org.jetbrains.jps.incremental.BuilderService</path> file.
These files don't have extensions, so you need to map corresponding patterns to text files in IDE settings.

### Registering a Plugin for External Builder

Sources of a plugin for External Builder should be put in a separate module.
By convention, such a module has a name '...-jps-plugin', and its sources are placed under the <path>jps-plugin</path> directory in the main plugin directory.
Use <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.compileServer.plugin"/></include> to add the plugin to the classpath of the external build process, the plugin JAR should be named <path>\$JPS_module_name\$.jar</path>. <ui-path>Build | Prepare Plugin Module for Deployment</ui-path> action will automatically pack the 'jps-plugin' part to a separate JAR accordingly.

### Debugging a Plugin for External Builder

**If your test IDE is IntelliJ IDEA 16.0 or newer**

Switch on "Debug Build Process" toggle action (available via 'Find Action') in the test IDE.
After that, every time compilation is run, the build process will wait for debugger connection on some (random) port and will show the port number in the status bar.
In a working copy of IDE, a "Remote" run configuration should be created and pointed to this port.
Suppose you often need to debug external builders and want to reuse the created "Remote" run configuration.
In that case, you may fix the port number by adding the following VM option to the plugin run configuration:

```
-Dcompiler.process.debug.port=<port-number>
```

**If your test IDE is IntelliJ IDEA 15.0 or older**

Start IDE with your plugin with the following VM option:

```
-Dcompiler.process.debug.port=<port-number>
```

After that, every time compilation is run in the test IDE, the build process will wait for debugger connection on this port and then proceed.  In a working copy of IDE, a "Remote" run configuration should be created and pointed to this port.
Specifying port "-1" will disable debugging mode.

### Profiling External Build Process

The build process has built-in self-CPU-profiling capabilities.

<procedure title="Enabling CPU profiling for build process">

1. _2023.2+_ Install [YourKit Profiler for IDE Performance Testing](https://plugins.jetbrains.com/plugin/20892-yourkit-profiler-for-ide-performance-testing) plugin
2. Copy <path>\$IDE_HOME\$/lib/yjp-controller-api-redist.jar</path> and <path>\$IDE_HOME\$/bin/yjpagent.*</path> files to <path>\$IDE_SYSTEM_DIR\$/compile-server</path>
3. In <ui-path>Settings | Build, Execution, Deployment | Compiler | Java Compiler</ui-path> add `-Dprofiling.mode=true` in <control>Additional command line parameters</control>
4. Make sure <control>Build project automatically</control> in <ui-path>Settings | Build, Execution, Deployment | Compiler</ui-path> is disabled

</procedure>

After this, every build process run should result in a CPU snapshot stored in <path>\$USER_HOME\$/Snapshots</path> directory.
Snapshots are named like <path>ExternalBuild\-\$DATE\$.snapshot</path>.

Specifying `-Dprofiling.mode=false` will turn profiling off.
Please capture a couple of snapshots for the situations you believe the build should work much faster than it does.

Please create an issue in the issue tracker and attach generated <path>*.snapshot</path> files to it or upload them as [described here](https://intellij-support.jetbrains.com/hc/en-us/articles/206869619) and specify links in the issue.
Please also provide details about the memory and other VM settings for the build process you were using.

### Accessing External Build Process' Logs

The log file is located under the directory:

```
<ide-system-directory>/log/build-log
```

There, both <path>build-log.log</path> and <path>build-log.properties</path> files can be found.
The <path>build-log.properties</path> is a log4j configuration file, where the log level and desired logging categories can be adjusted.
This file contains logging from all build sessions, including those from the auto-make.

In IntelliJ Platform versions before version 14.1, log4j configuration was stored in <path>build-log.xml</path>.

### Accessing Project Model and Configuration from External Build

The project model in the External Build process is provided by JPS (*JetBrains Project System*).
A project is represented by [`JpsProject`](%gh-ic%/jps/model-api/src/org/jetbrains/jps/model/JpsProject.java), a module by [`JpsModule`](%gh-ic%/jps/model-api/src/org/jetbrains/jps/model/JpsProject.java), and so on.
Suppose your compiler depends on something that isn't added to the model yet (e.g., some facet settings).
In that case, you need to extend the JPS model (use `JpsOsmorcModuleExtension` as a reference implementation) and provide an implementation of [`JpsModelSerializerExtension`](%gh-ic%/jps/model-serialization/src/org/jetbrains/jps/model/serialization/JpsModelSerializerExtension.java) to load the configuration from project files.

#### Implementing Builder

If your compiler isn't involved in the compilation of an existing [`BuildTarget`](%gh-ic%/jps/jps-builders/src/org/jetbrains/jps/builders/BuildTarget.java), you need to create a new implementation of `BuildTarget` and `BuildTargetType`.
Also, register an implementation of [`BuildTargetScopeProvider`](%gh-ic%/java/compiler/impl/src/com/intellij/compiler/impl/BuildTargetScopeProvider.java) extension on the IDE side to add required targets to the build scope.
The builder implementation should extend either [`TargetBuilder`](%gh-ic%/jps/jps-builders/src/org/jetbrains/jps/incremental/TargetBuilder.java) or [`ModuleLevelBuilder`](%gh-ic%/jps/jps-builders/src/org/jetbrains/jps/incremental/ModuleLevelBuilder.java) class and should be created using [`BuilderService`](%gh-ic%/jps/jps-builders/src/org/jetbrains/jps/incremental/BuilderService.java) extension.
