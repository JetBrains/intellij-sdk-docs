<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Run Configurations Tutorial

<link-summary>Implementing a custom run configuration tutorial.</link-summary>

<tldr>

**Product Help:** [Run/Debug Configuration](https://www.jetbrains.com/idea/help/run-debug-configuration.html)

</tldr>

This step-by-step guide shows how to register and implement a simple [run configuration](run_configurations.md).
Run configurations are used to run internal and external processes from within IntelliJ Platform based products.

The full implementation is available in the [code samples](%gh-sdk-samples-master%/run_configuration).

## Pre-Requirements

Create an empty plugin project.
See the [](creating_plugin_project.md) section for details.

## Implement a `ConfigurationType`

Implement [`ConfigurationType`](%gh-ic%/platform/execution/src/com/intellij/execution/configurations/ConfigurationType.java):

```java
```
{src="run_configuration/src/main/java/org/jetbrains/sdk/runConfiguration/DemoRunConfigurationType.java" include-symbol="DemoRunConfigurationType"}

## Register the `ConfigurationType`

Register implemented configuration type in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.configurationType"/></include> in the [plugin.xml](%gh-sdk-samples-master%/run_configuration/src/main/resources/META-INF/plugin.xml):

```xml
<extensions defaultExtensionNs="com.intellij">
  <configurationType
      implementation="org.jetbrains.sdk.runConfiguration.DemoRunConfigurationType"/>
</extensions>
```

## Implement a `ConfigurationFactory`

Implement a new [`ConfigurationFactory`](%gh-ic%/platform/execution/src/com/intellij/execution/configurations/ConfigurationFactory.java) through which custom run configurations will be created.

```java
```
{src="run_configuration/src/main/java/org/jetbrains/sdk/runConfiguration/DemoConfigurationFactory.java" include-symbol="DemoConfigurationFactory"}

Implement corresponding configuration options class extending [`RunConfigurationOptions`](%gh-ic%/platform/execution/src/com/intellij/execution/configurations/RunConfigurationOptions.kt) to store settings.

```java
```
{src="run_configuration/src/main/java/org/jetbrains/sdk/runConfiguration/DemoRunConfigurationOptions.java" include-symbol="DemoRunConfigurationOptions"}

## Implement a `RunConfiguration`

To make your changes visible from the UI, implement a new run configuration.

> In most of the cases it is sufficient derive a custom run configuration class from the [`RunConfigurationBase`](%gh-ic%/platform/execution/src/com/intellij/execution/configurations/RunConfigurationBase.java).
> If implementing specific settings externalization rules and I/O behaviour is required, use [`RunConfiguration`](%gh-ic%/platform/execution/src/com/intellij/execution/configurations/RunConfiguration.java) interface.
>
{style="note"}

```java
```
{src="run_configuration/src/main/java/org/jetbrains/sdk/runConfiguration/DemoRunConfiguration.java" include-symbol="DemoRunConfiguration"}

## Implement the `SettingsEditor`

```java
```
{src="run_configuration/src/main/java/org/jetbrains/sdk/runConfiguration/DemoSettingsEditor.java" include-symbol="DemoSettingsEditor"}

## Compile and Run the Plugin

<procedure>

1. [Execute](creating_plugin_project.md#executing-the-plugin) the plugin.
2. Go to <ui-path>Run \| Edit Configurations...</ui-path>, click to <control>Add</control> button (<control>+</control> icon), and select <control>Demo</control>.
3. In the <control>Script file</control> field provide the path to an example script (e.g. displaying "Hello world" message).
4. Click the <control>Apply</control> button and close the dialog.
5. In the run toolbar select created configuration and click the run button.

The script should be executed and its result should be displayed in the console.

</procedure>
