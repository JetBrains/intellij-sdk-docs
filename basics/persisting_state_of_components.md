---
title: Persisting State of Components
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The *IntelliJ Platform* provides an API that allows components or services to persist their state between restarts of the IDE. You can use either a simple API to persist a few values or persist the state of more complicated components using the [`PersistentStateComponent`](upsource:///platform/projectModel-api/src/com/intellij/openapi/components/PersistentStateComponent.java) interface.

> **WARNING** If you need to persist sensitive data like passwords, please see [Persisting Sensitive Data](persisting_sensitive_data.md).

## Using PropertiesComponent for Simple Non-Roamable Persistence

If the plugin needs to persist just a few simple values, the easiest way to do so is to use the [`com.intellij.ide.util.PropertiesComponent`](upsource:///platform/core-api/src/com/intellij/ide/util/PropertiesComponent.java) service. It can be used for saving both application-level values and project-level values (stored in the workspace file). Roaming is disabled for `PropertiesComponent`, so use it only for temporary, non-roamable properties.

Use the `PropertiesComponent.getInstance()` method for storing application-level values, and the `PropertiesComponent.getInstance(Project)` method for storing project-level values.

Since all plugins share the same namespace, it is highly recommended to prefix key names (e.g., using plugin ID `com.myplugin.myCustomSetting`).

## Using PersistentStateComponent

The [`com.intellij.openapi.components.PersistentStateComponent`](upsource:///platform/projectModel-api/src/com/intellij/openapi/components/PersistentStateComponent.java) interface gives you the most flexibility for defining the values to be persisted, their format, and storage location.

To use it:
- mark a [service](plugin_structure/plugin_services.md) as implementing the `PersistentStateComponent` interface
- define the state class
- specify the storage location using `@com.intellij.openapi.components.State`

Note that instances of extensions cannot persist their state by implementing `PersistentStateComponent`. If your extension needs to have a persistent state, you need to define a separate service responsible for managing that state.

### Implementing the PersistentStateComponent Interface

The implementation of `PersistentStateComponent` needs to be parameterized with the type of the state class. The state class can either be a separate JavaBean class, or the class implementing `PersistentStateComponent` itself.

In the former case, the instance of the state class is typically stored as a field in the `PersistentStateComponent` class:

```java
@State(...)
class MyService implements PersistentStateComponent<MyService.State> {
  
  static class State {
    public String value;
  }

  State myState;

  public State getState() {
    return myState;
  }

  public void loadState(State state) {
    myState = state;
  }
}
```

In the latter case, you can use the following pattern to implement `getState()` and `loadState()` methods:

```java
@State(...)
class MyService implements PersistentStateComponent<MyService> {
  public String stateValue;

  public MyService getState() {
    return this;
  }

  public void loadState(MyService state) {
    XmlSerializerUtil.copyBean(state, this);
  }
}
```

### Implementing the State Class

The implementation of `PersistentStateComponent` works by serializing public fields, [annotated](upsource:///platform/util/src/com/intellij/util/xmlb/annotations) private fields (see also [Customizing the XML format of persisted values](#customizing-the-xml-format-of-persisted-values)), and bean properties into an XML format. 

To exclude a public field or bean property from serialization, annotate the field or getter with `@com.intellij.util.xmlb.annotations.Transient`.

Note that the state class must have a default constructor. It should return the default state of the component (one used if there is nothing persisted in the XML files yet).

State class should have an `equals()` method, but if it is not implemented, state objects are compared by fields. When using Kotlin, use [Data Classes](https://kotlinlang.org/docs/reference/data-classes.html).


The following types of values can be persisted:

* numbers (both primitive types, such as `int`, and boxed types, such as `Integer`)
* booleans
* strings
* collections
* maps
* enums

For other types, extend [`com.intellij.util.xmlb.Converter`](upsource:///platform/util/src/com/intellij/util/xmlb/Converter.java):

```java
class LocalDateTimeConverter extends Converter<LocalDateTime> {
  public LocalDateTime fromString(String value) {
    final long epochMilli = Long.parseLong(value);
    final ZoneId zoneId = ZoneId.systemDefault();
    return Instant.ofEpochMilli(epochMilli).atZone(zoneId).toLocalDateTime();
  }

  public String toString(LocalDateTime value) {
    final ZoneId zoneId = ZoneId.systemDefault();
    final long toEpochMilli = value.atZone(zoneId).toInstant().toEpochMilli();
    return Long.toString(toEpochMilli);
  }
}
```

Define the converter above in `@com.intellij.util.xmlb.annotations.OptionTag` or `@com.intellij.util.xmlb.annotations.Attribute`:

```java
class State {
  @OptionTag(converter = LocalDateTimeConverter.class)
  public LocalDateTime dateTime;
}
```


### Defining the Storage Location

To specify where exactly the persisted values are stored, add `@State` annotation to the `PersistentStateComponent` class. 

It has the following fields:
* `name` (required) — specifies the name of the state (name of the root tag in XML).
* `storages` — one or more of `@com.intellij.openapi.components.Storage` annotations to specify the storage locations. Optional for project-level values — standard project file is used in this case.
* `reloadable` (optional) — if set to false, complete project (or application) reload is required when the XML file is changed externally and the state has changed.

The simplest ways of specifying the `@Storage` annotation are as follows (since 2016.x; for previous versions, please see [old version](https://github.com/JetBrains/intellij-sdk-docs/blob/5dcb02991cf828a7d4680d125ce56b4c10234146/basics/persisting_state_of_components.md) of this document):

* `@Storage("yourName.xml")` If a component is project-level — for `.ipr` based projects standard project file is used automatically - no need to specify anything.

* `@Storage(StoragePathMacros.WORKSPACE_FILE)` for values stored in the workspace file.

By specifying a different value for the `value` parameter (`file` before 2016.x), the state is persisted in a different file. 

> **NOTE** For application-level components it is strongly recommended to use a custom file, using of `other.xml` is deprecated.

The `roamingType` parameter of the `@Storage` annotation specifies the roaming type when the Settings Repository plugin is used.

## Customizing the XML Format of Persisted Values

> **NOTE** Please consider using annotation parameters only to achieve backward compatibility. Otherwise, please feel free to file issues about serialization cosmetics.

If you want to use the default bean serialization but need to customize the storage format in XML (for example, for compatibility with previous versions of your plugin or externally defined XML formats), you can use the `@Tag`, `@Attribute`, `@Property`, `@MapAnnotation`, `@AbstractCollection` annotations. 

Please see `com.intellij.util.xmlb.annotations`'s [`package.html`](upsource:///platform/util/src/com/intellij/util/xmlb/annotations/package.html) for more information.

If the state you need to serialize doesn't map cleanly to a JavaBean, you can use `org.jdom.Element` as the state class. In that case, you can use the `getState()` method to build an XML element with an arbitrary structure, which then is saved directly in the state XML file. In the `loadState()` method, you can deserialize the JDOM element tree using any custom logic. Please note this is not recommended and should be avoided whenever possible.

## Migrating Persisted Values

If the underlying persistence model or storage format has changed, a [`ConverterProvider`](upsource:///platform/lang-impl/src/com/intellij/conversion/ConverterProvider.java) can provide 
[`ProjectConverter`](upsource:///platform/lang-impl/src/com/intellij/conversion/ProjectConverter.java) whose `getAdditionalAffectedFiles()` method returns affected files to migrate and performs programmatic migration of stored values.

## Persistent Component Lifecycle

The `loadState()` method is called after the component has been created (only if there is some non-default state persisted for the component), and after the XML file with the persisted state is changed externally (for example, if the project file was updated from the version control system). In the latter case, the component is responsible for updating the UI and other related components according to the changed state.

The `getState()` method is called every time the settings are saved (for example, on frame deactivation or when closing the IDE). If the state returned from `getState()` is equal to the default state (obtained by creating the state class with a default constructor), nothing is persisted in the XML. Otherwise, the returned state is serialized in XML and stored.

## Legacy API (JDOMExternalizable)

Older components use the [`JDOMExternalizable`](upsource:///platform/util/src/com/intellij/openapi/util/JDOMExternalizable.java) interface for persisting state. It uses the `readExternal()` method for reading the state from a JDOM element, and `writeExternal()` to write the state to it.

Implementations can store the state in attributes and sub-elements manually, or use the [`DefaultJDOMExternalizer`](upsource:///platform/util/src/com/intellij/openapi/util/DefaultJDOMExternalizer.java) class to store the values of all public fields automatically.

Components save their state in the following files:

* Project-level: project (`.ipr`) file. However, if the workspace option in the `plugin.xml` file is set to `true`, then workspace (`.iws`) file is used instead.
* Module-level: module (`.iml`) file.
