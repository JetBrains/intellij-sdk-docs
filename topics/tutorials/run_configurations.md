[//]: # (title: Run Configurations Tutorial)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

These series of steps show how to register and implement a simple Run Configuration.
Run Configurations are used to run internal and external processes from within IntelliJ Platform based products.
To get familiar with the concept of a Run Configuration refer [Run/Debug Configuration](https://www.jetbrains.com/idea/help/run-debug-configuration.html) section of [IntelliJ IDEA Web Help](https://www.jetbrains.com/idea/help/intellij-idea.html)

Consider the **runConfiguration** sample plugin available in the [code samples](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/run_configuration).
See [Code Samples](code_samples.md) on how to set up and run the plugin.

## Pre-Requirements

Create an empty plugin project as described in [Creating a Plugin Project](gradle_build_system.md).

## Register a New ConfigurationType

Add new `com.intellij.configurationType` extension to the [plugin.xml](https://github.com/JetBrains/intellij-sdk-code-samples/blob/main/run_configuration/src/main/resources/META-INF/plugin.xml)

```xml
<extensions defaultExtensionNs="com.intellij">
  <configurationType
      implementation="org.jetbrains.sdk.runConfiguration.DemoRunConfigurationType"/>
</extensions>
```

## Implement ConfigurationType

Implement [`ConfigurationType`](upsource:///platform/execution/src/com/intellij/execution/configurations/ConfigurationType.java)  interface registered in the Step 1.

```java
```
{src="run_configuration/src/main/java/org/jetbrains/sdk/runConfiguration/DemoRunConfigurationType.java"}

## Implement a ConfigurationFactory

Implement a new [`ConfigurationFactory`](upsource:///platform/execution/src/com/intellij/execution/configurations/ConfigurationFactory.java) through which custom run configurations will be created.

```java
```
{src="run_configuration/src/main/java/org/jetbrains/sdk/runConfiguration/DemoConfigurationFactory.java"}

Implement corresponding configuration options class extending [`RunConfigurationOptions`](upsource:///platform/execution/src/com/intellij/execution/configurations/RunConfigurationOptions.kt) to store settings.

```java
```
{src="run_configuration/src/main/java/org/jetbrains/sdk/runConfiguration/DemoRunConfigurationOptions.java"}

## Implement a Run Configuration

To make your changes visible from the UI, implement a new Run Configuration.

**Note:** In most of the cases you can derive a custom Run Configuration class from the [`RunConfigurationBase`](upsource:///platform/execution/src/com/intellij/execution/configurations/RunConfigurationBase.java).
If you need to implement specific settings externalization rules and I/O behaviour, use [`RunConfiguration`](upsource:///platform/execution/src/com/intellij/execution/configurations/RunConfiguration.java) interface.

```java
```
{src="run_configuration/src/main/java/org/jetbrains/sdk/runConfiguration/DemoRunConfiguration.java"}

## Create and Implement Run Configuration UI Form

Make sure _UI Designer_ plugin is [enabled](https://www.jetbrains.com/help/idea/managing-plugins.html).

Create a new [UI form](https://www.jetbrains.com/help/idea/designing-gui-major-steps.html) that defines, how an inner part of the new Run Configuration should look like.

Default Run Configuration will be looking like this:

![Default Run Configuration Look](ui_form.png)

## Bind the UI Form

The UI Form should be bound with a Java class responsible for handling UI components logic.

```java
```
{src="run_configuration/src/main/java/org/jetbrains/sdk/runConfiguration/DemoSettingsEditor.java"}

## Compile and Run the Plugin

Refer to [Running and Debugging a Plugin](gradle_prerequisites.md#executing-the-plugin).

After going through the steps described above you can create a custom Run Configuration from your plugin.

![New Run Configuration Type](new_run_configuration.png)
