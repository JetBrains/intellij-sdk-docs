---
title: 16. Code Style Setting
---

Code style settings allow defining formatting options. A code style settings provider will create an instance of the settings, and also create an options page in order to edit them. In this example, we'll create a page that uses the default language code style settings, customised by a language code style settings provider.

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
