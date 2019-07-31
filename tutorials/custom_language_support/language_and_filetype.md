---
title: 2. Language and File Type
---


### 2.1. Define a language

Note the case of the name of the language - `Simple`.

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleLanguage.java %}
```

### 2.2. Define an icon

Copy the
[icon](https://raw.githubusercontent.com/JetBrains/intellij-sdk-docs/master/code_samples/simple_language_plugin/src/com/simpleplugin/icons/jar-gray.png)
to **com.simpleplugin.icons** package.

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleIcons.java %}
```

### 2.3. Define a file type

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleFileType.java %}
```

### 2.4. Define a file type factory

> **NOTE** When targeting 2019.2 or later only, please see [2.5.B](#b-register-file-type-20192-or-later)

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleFileTypeFactory.java %}
```

### 2.5. Register the file type factory

In plugin.xml add:

```xml
<extensions defaultExtensionNs="com.intellij">
      <fileTypeFactory implementation="com.simpleplugin.SimpleFileTypeFactory"/>
</extensions>
```

### 2.5.B. Register file type (2019.2 or later)

When matching via file extension, pattern or exact file name, registration of file type should be done via `com.intellij.fileType` extension point instead of implementing dedicated `FileTypeFactory`. 

```xml
<extensions defaultExtensionNs="com.intellij">
    <fileType name="Simple file" implementationClass="com.simpleplugin.SimpleFileType" fieldName="INSTANCE" 
              language="Simple" extensions="simple"/>
</extensions>
```

### 2.6. Run the project

Create a file with extension *.simple*
and IntelliJ IDEA will automatically associate it with our language.

![File Type Factory](img/file_type_factory.png)
