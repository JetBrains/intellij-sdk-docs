---
title: 11. Find Usages Provider
---

A find usage provider uses a word scanner to build an index of words present in every file.
A scanner breaks the text into words, defines the context for each word and passes it to the find usage provider.

### 11.1. Define a find usages provider

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleFindUsagesProvider.java %}
```

### 11.2. Register the find usages provider

```xml
<lang.findUsagesProvider language="Simple" implementationClass="com.simpleplugin.SimpleFindUsagesProvider"/>
```

### 11.3. Run the project

Now we can call *Find Usages* for any property with a reference.

![Find Usages](img/find_usages.png)

[Previous](reference_contributor.md)
[Top](/tutorials/custom_language_support_tutorial.md)
[Next](folding_builder.md)
