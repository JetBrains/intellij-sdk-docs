<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Recipes

<link-summary>Recipes for solving particular tasks with IntelliJ Platform Gradle Plugin</link-summary>

## Run custom task with customized sandbox location

To create a custom task with the sandbox directory specified outside of the default <path>build/idea-sandbox/[TYPE]-[VERSION]/</path> location, pass the new location to its `prepareSandboxTask` sandbox producer configuration:

```kotlin
val runWithCustomSandbox by intellijPlatformTesting.runIde.registering {
  // ...

  prepareSandboxTask {
    sandboxDirectory = project.layout.buildDirectory.dir("custom-sandbox")
    sandboxSuffix = ""
  }
}
```

This will result in the following sandbox structure:

```
build/
├── custom-sandbox
│   ├── config
│   ├── log
│   ├── plugins
│   └── system
...
```
