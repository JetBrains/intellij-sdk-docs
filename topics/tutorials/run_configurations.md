[//]: # (title: Run Configurations)

<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

These series of steps show how to register and implement a simple Run Configuration.
Run Configurations are used to run internal and external processes from within IntelliJ Platform based products.
To get familiar with the concept of a Run Configuration refer [Run/Debug Configuration](https://www.jetbrains.com/idea/help/run-debug-configuration.html) section of  [IntelliJ IDEA Web Help](https://www.jetbrains.com/idea/help/intellij-idea.html)

## Pre-Requirements

Create an empty plugin project as described in [Creating a Plugin Project](getting_started.md).

## Register a New ConfigurationType

Add a new `configurationType` extension to the [plugin.xml](https://github.com/JetBrains/intellij-sdk-code-samples/blob/main/run_configuration/src/main/resources/META-INF/plugin.xml)

```xml
<extensions defaultExtensionNs="com.intellij">
  <configurationType implementation="org.jetbrains.sdk.runConfiguration.DemoRunConfigurationType"/>
</extensions>
```

## Implement ConfigurationType

Implement the new configurationType that was registered in the previous step. The standard way to do this is to implement the [`ConfigurationType`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/ConfigurationType.java) interface within the IntelliJ API, but the IntelliJ platform now offers the [`SimpleConfigurationType`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/runConfigurationType.kt) class that you can extend to set up your new configurationType even easier. (Although the `SimpleConfigurationType` class is written in Kotlin, it is fully interoperable with Java too)

```kotlin
class DemoRunConfigurationType : SimpleConfigurationType(
    id = "DemoRunConfiguration",
    name = "Demo",
    icon = NotNullLazyValue.createValue { AllIcons.General.Information }
) {
  override fun createTemplateConfiguration(project: Project): RunConfiguration {
    return DemoRunConfig(project, this, "Demo")
  }
}
```

The `createTemplateConfiguration()` method should return an instance of your run configuration which you will set up in the next step.

>  The `id` field is very important and should not be changed after your first release! If you change it, any configurations your users have created will become unrecognized after they download a plugin update since IntelliJ uses the `id` to determine the configuration's type from xml.
>
{type="warning"}

By default, `SimpleConfigurationType` will set up a factory for your run configurations automatically. If your plugin requires more than one factory for your configurationType, you should extend the [`ConfigurationTypeBase`]() class instead and call the `addFactory()` method in the constructor as many times as needed. Your configuration factories will need to be declared separately and should extend [`ConfigurationFactory`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/ConfigurationFactory.java). You can refer to the [run configuration code sample](https://github.com/JetBrains/intellij-sdk-docs/blob/main/code_samples/run_configuration/src/main/java/org/jetbrains/sdk/runConfiguration/DemoRunConfigurationType.java) for an example of this.

The following is a sample implementation of [`ConfigurationFactory`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/ConfigurationFactory.java):

```kotlin
class DemoRunConfigFactory(configurationType: DemoRunConfigType) : ConfigurationFactory(configurationType) {
    override fun createTemplateConfiguration(project: Project): RunConfiguration {
        return DemoRunConfig(project, this, "Demo")
    }

    override fun getId() = "Demo"
}
```

## Implement a Run Configuration

To make your changes visible from the UI, implement a new Run Configuration.

**Note:** In most cases, your custom Run Configuration class can just extend [`RunConfigurationBase`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/RunConfigurationBase.java).
If you need to implement specific settings-externalization rules and I/O behaviour, use the [`RunConfiguration`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/RunConfiguration.java) interface instead.

```java
public class DemoRunConfiguration extends RunConfigurationBase {
    protected DemoRunConfiguration(Project project, ConfigurationFactory factory, String name) {
        super(project, factory, name);
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new DemoSettingsEditor();
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {

    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment executionEnvironment) throws ExecutionException {
        return null;
    }
}
```

## Create and Implement Run Configuration UI Form

Make sure _UI Designer_ plugin is [enabled](https://www.jetbrains.com/help/idea/managing-plugins.html).

Create a new  [UI form](https://www.jetbrains.com/help/idea/designing-gui-major-steps.html)  that defines, how an inner part of the new Run Configuration should look like.

Default Run Configuration will be looking like this:

![Default Run Configuration Look](ui_form.png)

## Bind the UI Form

The UI Form should be bound with a Java class responsible for handling UI components logic.

```java
public class DemoSettingsEditor extends SettingsEditor<DemoRunConfiguration> {
    private JPanel myPanel;
    private LabeledComponent<ComponentWithBrowseButton> myMainClass;

    @Override
    protected void resetEditorFrom(DemoRunConfiguration demoRunConfiguration) {

    }

    @Override
    protected void applyEditorTo(DemoRunConfiguration demoRunConfiguration) throws ConfigurationException {

    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return myPanel;
    }

    private void createUIComponents() {
        myMainClass = new LabeledComponent<ComponentWithBrowseButton>();
        myMainClass.setComponent(new TextFieldWithBrowseButton());
    }
}
```

## Compile and Run the Plugin

Refer to [Running and Debugging a Plugin](running_and_debugging_a_plugin.md).

After going through the steps described above you can create a custom Run Configuration from your plugin.

![New Run Configuration Type](new_run_configuration.png)
