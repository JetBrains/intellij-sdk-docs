---
title: 14. Structure View Factory
---


A structure view factory allows to show the structure of any file in a *Structure* tool window for easy navigation between items.

### 14.1. Define a structure view factory

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleStructureViewFactory.java %}
```

### 14.2. Define a structure view model

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleStructureViewModel.java %}
```

### 14.3. Define a structure view element

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleStructureViewElement.java %}
```

### 14.4. Register the structure view factory

```xml
<lang.psiStructureViewFactory language="Simple" implementationClass="com.simpleplugin.SimpleStructureViewFactory"/>
```

### 14.5. Run the project

![Structure View](img/structure_view.png)

[Previous](go_to_symbol_contributor.md)
[Top](/tutorials/custom_language_support_tutorial.md)
[Next](formatter.md)
