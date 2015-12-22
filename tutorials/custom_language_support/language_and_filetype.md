---
title: 2. Language and File Type
---


### 2.1. Define a language

{% include_code simple_language_plugin/src/com/simpleplugin/Simple.bnf lang:java %}

### 2.2. Define an icon

Copy the
[icon](https://raw.githubusercontent.com/cheptsov/SimplePlugin/master/src/com/simpleplugin/icons/jar-gray.png)
to **com.simpleplugin.icons** package.

{% include_code simple_language_plugin/src/com/simpleplugin/SimpleIcons.java %}

### 2.3. Define a file type

{% include_code simple_language_plugin/src/com/simpleplugin/SimpleFileType.java %}

### 2.4. Define a file type factory

{% include_code simple_language_plugin/src/com/simpleplugin/SimpleFileTypeFactory.java %}

### 2.5. Register the file type factory

```xml
      <fileTypeFactory implementation="com.simpleplugin.SimpleFileTypeFactory"/>
```

### 2.6. Run the project

Create a file with extension *.simple*
and IntelliJ IDEA will automatically associate it with our language.

![File Type Factory](img/file_type_factory.png)

[Previous](prerequisites.md)
[Top](/tutorials/custom_language_support_tutorial.md)
[Next](grammar_and_parser.md)
