<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 11. Find Usages Provider

<link-summary>Sample implementation of finding usages of Simple language properties.</link-summary>

<tldr>

**Reference**: [](find_usages.md)

**Code**: [`SimpleFindUsagesProvider`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleFindUsagesProvider.java)

**Testing**: [](find_usages_test.md)

</tldr>

<include from="language_and_filetype.md" element-id="custom_language_tutorial_header"></include>

A `FindUsagesProvider` uses a word scanner to build an index of words in every file.
A scanner breaks the text into words and defines the context for each word.

## Define a Find Usages Provider

The [`SimpleFindUsagesProvider`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleFindUsagesProvider.java) implements [`FindUsagesProvider`](%gh-ic%/platform/indexing-api/src/com/intellij/lang/findUsages/FindUsagesProvider.java).
Using the [`DefaultWordsScanner`](%gh-ic%/platform/indexing-impl/src/com/intellij/lang/cacheBuilder/DefaultWordsScanner.java) ensures the scanner implementation is thread-safe.
See the comments in `FindUsagesProvider` for more information.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleFindUsagesProvider.java" include-symbol="SimpleFindUsagesProvider"}

## Register the Find Usages Provider

The `SimpleFindUsagesProvider` implementation is registered with the IntelliJ Platform in the plugin configuration file using
the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.lang.findUsagesProvider"/></include>.

```xml
<extensions defaultExtensionNs="com.intellij">
  <lang.findUsagesProvider
      language="Simple"
      implementationClass="org.intellij.sdk.language.SimpleFindUsagesProvider"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](creating_plugin_project.md#running-a-plugin-with-the-runide-gradle-task) task.

The IDE now supports [Find Usages](https://www.jetbrains.com/help/idea/find-highlight-usages.html) for any property with a reference:

![Find Usages](find_usages.png)
