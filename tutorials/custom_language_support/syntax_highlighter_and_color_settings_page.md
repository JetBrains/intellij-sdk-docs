---
title: 5. Syntax Highlighter and Color Settings Page
---


### 5.1. Define a syntax highlighter

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleSyntaxHighlighter.java %}
```

### 5.2. Define a syntax highlighter factory

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleSyntaxHighlighterFactory.java %}
```

### 5.3. Register the syntax highlighter factory

```xml
<lang.syntaxHighlighterFactory key="Simple" implementationClass="com.simpleplugin.SimpleSyntaxHighlighterFactory"/>
```

### 5.4. Run the project

![Syntax highlighter](img/syntax_highlighter.png)

### 5.5. Define a color settings page

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleColorSettingsPage.java %}
```

### 5.6. Register the color settings page

```xml
<colorSettingsPage implementation="com.simpleplugin.SimpleColorSettingsPage"/>
```

### 5.7. Run the project

![Color Settings Page](img/color_settings_page.png)

-----------

[Previous](lexer_and_parser_definition.md)
[Top](/tutorials/custom_language_support_tutorial.md)
[Next](psi_helper_and_utilities.md)


