<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Frameworks

<link-summary>Tutorial on implementing custom framework types.</link-summary>

The following tutorial shows how to support a custom framework type for a project and make this framework type embedded in a [project wizard](new_project_wizard.md) as a UI component.
The examples in this tutorial rely heavily on the [framework_basics](%gh-sdk-samples-master%/framework_basics) code sample.

> Note that this feature requires a [dependency](plugin_dependencies.md) on the [](idea.md#java-plugin).
>
{style="warning"}

## Creating a New Framework

In order to make a custom framework available and configurable for a project, the [`FrameworkTypeEx`](%gh-ic%/java/idea-ui/src/com/intellij/framework/FrameworkTypeEx.java) class needs to be extended, in this example to make the [DemoFramework](%gh-sdk-samples-master%/framework_basics/src/main/java/org/intellij/sdk/framework/DemoFramework.java) class.

```java
final class DemoFramework extends FrameworkTypeEx {
}
```

## Registering Framework

The newly created framework class should be registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.framework.type"/></include> in [`plugin.xml`](%gh-sdk-samples-master%/framework_basics/src/main/resources/META-INF/plugin.xml) configuration file:

```xml
<extensions defaultExtensionNs="com.intellij">
  <framework.type
      implementation="org.intellij.sdk.framework.DemoFramework"/>
</extensions>
```

## Setting up Mandatory Attributes

The framework component should have a unique name passed as a string literal to the constructor.
It is best if this is the FQN name of the class:

```java
final class DemoFramework extends FrameworkTypeEx {

  public static final String FRAMEWORK_ID =
      "org.intellij.sdk.framework.DemoFramework";

  DemoFramework() {
    super(FRAMEWORK_ID);
  }
}
```

The *Presentable name* and *icon* define the appearance of visual components related to the framework:

```java
final class DemoFramework extends FrameworkTypeEx {
  @NotNull
  @Override
  public String getPresentableName() {
    return "SDK Demo Framework";
  }

  @NotNull
  @Override
  public Icon getIcon() {
    return SdkIcons.Sdk_default_icon;
  }
}
```

## Creating Provider for Enabling Framework Support

To make the framework set up available while executing the steps to create a project, the
`DemoFramework.createProvider()` method must be implemented to return an object of type [`FrameworkSupportInModuleConfigurable`](%gh-ic%/java/idea-ui/src/com/intellij/framework/addSupport/FrameworkSupportInModuleConfigurable.java), which adds the framework to a module.
In this example the framework is added to any [`ModuleType`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/module/ModuleType.java) without checking, which is usually not the case.

```java
@NotNull
@Override
public FrameworkSupportInModuleProvider createProvider() {
  return new FrameworkSupportInModuleProvider() {
    @NotNull
    @Override
    public FrameworkTypeEx getFrameworkType() {
      return DemoFramework.this;
    }

    @NotNull
    @Override
    public FrameworkSupportInModuleConfigurable createConfigurable(
        @NotNull FrameworkSupportModel model) {
      return new FrameworkSupportInModuleConfigurable() {

        @Override
        public JComponent createComponent() {
          return new JCheckBox("SDK Extra Option");
        }

        @Override
        public void addSupport(@NotNull Module module,
                               @NotNull ModifiableRootModel model,
                               @NotNull ModifiableModelsProvider provider) {
          // This is the place to set up a library, generate a specific file,
          // and actually add framework support to a module.
        }
      };
    }

    @Override
    public boolean isEnabledForModuleType(@NotNull ModuleType type) {
      return true;
    }
  };
}
```
## Compile and Run the Plugin

See [Code Samples](code_samples.md) on how to set up and run the plugin.

An extra option for configuring the newly created Demo custom framework should be available in the Project Wizard:

![Custom Framework Support](custom_framework.png)
