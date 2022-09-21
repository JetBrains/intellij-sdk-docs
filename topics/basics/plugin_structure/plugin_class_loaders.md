[//]: # (title: Class Loaders)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

A separate class loader is used to load the classes of each plugin.
This allows each plugin to use a different library version, even if the same library is used by the IDE itself or by another plugin.

## Bundled Libraries

[Third-Party Software and Licenses](https://www.jetbrains.com/legal/third-party-software/) lists all bundled libraries and their versions for each product.

## Overriding IDE Dependencies

Gradle 7 introduced `implementation` scope, replacing `compile` scope.
For this setup, to use project defined dependency instead of the bundled IDE version, add the following snippet to your Gradle build script:

<tabs>
<tab title="Kotlin">

```kotlin
configurations.all {
  resolutionStrategy.sortArtifacts(ResolutionStrategy.SortOrder.DEPENDENCY_FIRST)
}
```

</tab>
<tab title="Groovy">

```groovy
configurations.all {
  resolutionStrategy.sortArtifacts(ResolutionStrategy.SortOrder.DEPENDENCY_FIRST)
}
```

</tab>
</tabs>

## Classes from Plugin Dependencies

By default, the main IDE class loader loads classes that are not found in the plugin class loader.
However, in the <path>[plugin.xml](plugin_configuration_file.md)</path> file, you may use the [`<depends>`](plugin_configuration_file.md#idea-plugin__depends) element to specify that a [plugin depends](plugin_dependencies.md) on one or more other plugins.
In this case, the class loaders of those plugins will be used for classes not found in the current plugin.
This allows a plugin to reference classes from other plugins.

## Using ServiceLoader

Some libraries use [`ServiceLoader`](https://docs.oracle.com/javase/8/docs/api/index.html?java/util/ServiceLoader.html) to detect and load implementations.
For this to work in a plugin, the context class loader must be set to the plugin's classloader and restored afterwards with the original one around initialization code:

```java
Thread currentThread = Thread.currentThread();
ClassLoader originalClassLoader = currentThread.getContextClassLoader();
ClassLoader pluginClassLoader = this.getClass().getClassLoader();
try {
  currentThread.setContextClassLoader(pluginClassLoader);
  // code working with ServiceLoader here
} finally {
  currentThread.setContextClassLoader(originalClassLoader);
}
```
