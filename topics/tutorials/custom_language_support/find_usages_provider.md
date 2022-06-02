[//]: # (title: 11. Find Usages Provider)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="language_and_filetype.md" include-id="custom_language_tutorial_header"></include>

A `FindUsagesProvider` uses a word scanner to build an index of words in every file.
A scanner breaks the text into words and defines the context for each word.

**Reference**: [](find_usages.md)

## Define a Find Usages Provider

The `SimpleFindUsagesProvider` implements [`FindUsagesProvider`](upsource:///platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java).
Using the [`DefaultWordsScanner`](upsource:///platform/indexing-api/src/com/intellij/lang/cacheBuilder/DefaultWordsScanner.java) ensures the scanner implementation is thread-safe.
See the comments in `FindUsagesProvider` for more information.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleFindUsagesProvider.java"}

## Register the Find Usages Provider

The `SimpleFindUsagesProvider` implementation is registered with the IntelliJ Platform in the plugin configuration file using the `com.intellij.lang.findUsagesProvider` extension point.

```xml
<extensions defaultExtensionNs="com.intellij">
  <lang.findUsagesProvider
      language="Simple"
      implementationClass="org.intellij.sdk.language.SimpleFindUsagesProvider"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](gradle_prerequisites.md#running-a-simple-gradle-based-intellij-platform-plugin) task.

The IDE now supports [Find Usages](https://www.jetbrains.com/help/idea/find-highlight-usages.html) for any property with a reference:

![Find Usages](find_usages.png)
