---
title: 7. Annotator
---

Annotator helps highlight and annotate any code based on specific rules.

### 7.1. Define an annotator

In this tutorial we will annotate usages of our properties within Java code.
Let's consider a literal which starts with *"simple:"* as a usage of our property.

```java
package com.simpleplugin;

import com.intellij.lang.annotation.*;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.simpleplugin.psi.SimpleProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SimpleAnnotator implements Annotator {
  @Override
  public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
    if (element instanceof PsiLiteralExpression) {
      PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
      String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;

      if (value != null && value.startsWith("simple" + ":")) {
        Project project = element.getProject();
        String key = value.substring(7);
        List<SimpleProperty> properties = SimpleUtil.findProperties(project, key);
        if (properties.size() == 1) {
          TextRange range = new TextRange(element.getTextRange().getStartOffset() + 8,
                                          element.getTextRange().getEndOffset() - 1);
          Annotation annotation = holder.createInfoAnnotation(range, null);
          annotation.setTextAttributes(DefaultLanguageHighlighterColors.LINE_COMMENT);
        } else if (properties.size() == 0) {
          TextRange range = new TextRange(element.getTextRange().getStartOffset() + 8,
                                          element.getTextRange().getEndOffset() - 1);
          holder.createErrorAnnotation(range, "Unresolved property");
        }
      }
    }
  }
}
```

### 7.2. Register the annotator

```xml
<annotator language="JAVA" implementationClass="com.simpleplugin.SimpleAnnotator"/>
```

### 7.3. Run the project

Let's define the following Java file and check if the IDE resolves a property.

```java
public class Test {
    public static void main(String[] args) {
        System.out.println("simple:website");
    }
}
```

![Annotator](img/annotator.png)

If we type an undefined property name, it will annotate the code with a error.

![Unresolved property](img/unresolved_property.png)

