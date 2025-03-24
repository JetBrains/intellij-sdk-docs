<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 2. Language and File Type

<link-summary>Sample implementation of Simple language and file type definitions.</link-summary>

<tldr>

**Reference**: [](registering_file_type.md)

**Code**: [`SimpleLanguage`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleLanguage.java),
[`SimpleIcons`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleIcons.java),
[`SimpleFileType`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleFileType.java)

</tldr>

<snippet id="custom_language_tutorial_header">

> This page is part of multi-step [](custom_language_support_tutorial.md).
> All previous steps must be executed in sequence for the code to work.

</snippet>

The IntelliJ Platform determines file type by examining the name of a file.
Each language has [Language](%gh-ic%/platform/core-api/src/com/intellij/lang/Language.java) and [LanguageFileType](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileTypes/LanguageFileType.java) objects defining the language.
Register the `LanguageFileType` with the IntelliJ Platform in the plugin configuration file.

## Define the Language

The language implemented in this tutorial is named "Simple" - note the case of the name.
The [`SimpleLanguage`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleLanguage.java) class is defined in the `org.intellij.sdk.language` package of the `simple_language_plugin` code sample:

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleLanguage.java" include-symbol="SimpleLanguage"}

## Define an Icon

The [icon](%gh-sdk-samples-master%/simple_language_plugin/src/main/resources/icons/jar-gray.png) for the Simple Language is defined by the
[`SimpleIcons`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleIcons.java) class.
Please see [](icons.md) for details on how to define and use icons.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleIcons.java" include-symbol="SimpleIcons"}

## Define a File Type

The [`SimpleFileType`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleFileType.java) is defined by subclassing [`LanguageFileType`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileTypes/LanguageFileType.java):

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleFileType.java" include-symbol="SimpleFileType"}

## Register the File Type

The Simple Language file type is registered via the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.fileType"/></include> in <path>[plugin.xml](plugin_configuration_file.md)</path> and registered with <path>*.simple</path> extension:

```xml
<extensions defaultExtensionNs="com.intellij">
  <fileType
      name="Simple File"
      implementationClass="org.intellij.sdk.language.SimpleFileType"
      fieldName="INSTANCE"
      language="Simple"
      extensions="simple"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](creating_plugin_project.md#running-a-plugin-with-the-runide-gradle-task) task.

Create an empty file with the extension <path>.simple</path>, and IntelliJ IDEA automatically associates it with our language.
Note the appearance of the Simple Language file icon next to the <path>test.simple</path> file in the <control>Project</control> Tool Window, and the editor tab for the file.

![File Type Factory](file_type_factory.png){width="800"}
