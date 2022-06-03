[//]: # (title: 2. Language and File Type)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<chunk id="custom_language_tutorial_header">

> This page is part of multi step [](custom_language_support_tutorial.md).
>
{type="tip"}

</chunk>

The IntelliJ Platform determines file type by examining the name of a file.
Each language has [Language](upsource:///platform/core-api/src/com/intellij/lang/Language.java) and [LanguageFileType](upsource:///platform/core-api/src/com/intellij/openapi/fileTypes/LanguageFileType.java) objects defining the language.
Register the `LanguageFileType` with the IntelliJ Platform in the plugin configuration file.

**Reference**: [](registering_file_type.md)

## Define the Language

The language implemented in this tutorial is named "Simple" - note the case of the name.
The `SimpleLanguage` class is defined in the `org.intellij.sdk.language` package of the `simple_language_plugin` code sample:

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleLanguage.java"}

## Define an Icon

The [icon](https://github.com/JetBrains/intellij-sdk-code-samples/blob/main/simple_language_plugin/src/main/resources/icons/jar-gray.png) for the Simple Language is defined by the `SimpleIcons` class.
Please see [](work_with_icons_and_images.md) for details on how to define and use icons.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleIcons.java"}

## Define a FileType

The Simple Language file type is defined by subclassing [`LanguageFileType`](upsource:///platform/core-api/src/com/intellij/openapi/fileTypes/LanguageFileType.java):

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleFileType.java"}

## Register the FileType

<tabs>

<tab title="2019.2 and later">

Direct registration is possible - no `FileTypeFactory` is required.

Instead, the file type is registered via the `com.intellij.fileType` extension point in <path>plugin.xml</path> and registered with <path>*.simple</path> extension:

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

</tab>

<tab title="Pre-2019.2">

### Define a FileType Factory

First, define `SimpleFileTypeFactory` as a subclass of [`FileTypeFactory`](upsource:///platform/ide-core/src/com/intellij/openapi/fileTypes/FileTypeFactory.java).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleFileTypeFactory.java"}

### Register the FileType Factory

The `SimpleFileTypeFactory` is registered using the `com.intellij.fileTypeFactory` extension point in <path>plugin.xml</path>.

```xml
<extensions defaultExtensionNs="com.intellij">
  <fileTypeFactory
      implementation="org.intellij.sdk.language.SimpleFileTypeFactory"/>
</extensions>
```

</tab>

</tabs>

## Run the Project

Run the plugin by using the Gradle [`runIde`](gradle_prerequisites.md#running-a-simple-gradle-based-intellij-platform-plugin) task.

Create an empty file with the extension <path>.simple</path>, and IntelliJ IDEA automatically associates it with our language.
Note the appearance of the Simple Language file icon next to the <path>test.simple</path> file in the **Project Tool Window**, and the editor tab for the file.

![File Type Factory](file_type_factory.png){width="800"}
