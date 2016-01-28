---
title: 16. Code Style Setting
---

### 16.1. Define code style settings

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleCodeStyleSettings.java %}
```

### 16.2. Define code style settings provider

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleCodeStyleSettingsProvider.java %}
```

### 16.3. Register the code style settings provider

```xml
<codeStyleSettingsProvider implementation="com.simpleplugin.SimpleCodeStyleSettingsProvider"/>
```

### 16.4. Define language code style settings provider

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleLanguageCodeStyleSettingsProvider.java %}
```

### 16.5. Register the language code style settings provider

```xml
<langCodeStyleSettingsProvider implementation="com.simpleplugin.SimpleLanguageCodeStyleSettingsProvider"/>
```

### 16.6. Run the project

![Code Style Settings](img/code_style_settings.png)

[Previous](formatter.md)
[Top](/tutorials/custom_language_support_tutorial.md)
[Next](commenter.md)
