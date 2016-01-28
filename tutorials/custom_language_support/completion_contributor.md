---
title: 9. Completion Contributor
---


The easiest way to provide completion is to use a completion contributor.

### 9.1. Define a completion contributor

Let's provide custom completion for values in property files.

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleCompletionContributor.java %}
```

### 9.2. Register the completion contributor

```xml
<completion.contributor language="Simple" implementationClass="com.simpleplugin.SimpleCompletionContributor"/>
```

### 9.3. Run the project

![Completion](img/completion.png)

[Previous](line_marker_provider.md)
[Top](/tutorials/custom_language_support_tutorial.md)
[Next](reference_contributor.md)



