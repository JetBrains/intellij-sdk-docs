---
title: 17. Commenter
---

A commenter allows user to comment the code at the cursor or selected code automatically via corresponding actions.

### 17.1. Define a commenter

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleCommenter.java %}
```

### 17.2. Register the commenter

```xml
<lang.commenter language="Simple" implementationClass="com.simpleplugin.SimpleCommenter"/>
```

### 17.3. Run the project

![Commenter](img/commenter.png)
