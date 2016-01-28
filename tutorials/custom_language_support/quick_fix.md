---
title: 18. Quick Fix
---


A quick fix allows to apply an automatic changes to the code via *⌥⏎*.

Let's add a quick fix which helps to define an unresolved property from its usage.

### 18.1. Update the element factory

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/psi/SimpleElementFactory.java %}
```

### 18.2. Define an intention action

The quick fix will create a property in the file chosen by user, and navigate to this property after creation.

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/CreatePropertyQuickFix.java %}
```

### 18.3. Update the annotator

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleAnnotator.java %}
```

### 18.4. Run the project

Now let's try to use a property which is not defined yet.

![Quick Fix](img/quick_fix.png)

[Previous](commenter.md)
[Top](/tutorials/custom_language_support_tutorial.md)

