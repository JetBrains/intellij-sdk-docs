---
layout: editable
title: 9. Completion Contributor
---


The easiest way to provide completion is to use a completion contributor.

### 1. Define a completion contributor

Let's provide custom completion for values in property files.

```java
package com.simpleplugin;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import com.simpleplugin.psi.SimpleTypes;
import org.jetbrains.annotations.NotNull;

public class SimpleCompletionContributor extends CompletionContributor {
    public SimpleCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(SimpleTypes.VALUE).withLanguage(SimpleLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("Hello"));
                    }
                }
        );
    }
}
```

### 2. Register the completion contributor

```xml
<completion.contributor language="Simple" implementationClass="com.simpleplugin.SimpleCompletionContributor"/>
```

### 3. Run the project

![Completion](img/completion.png)

[Previous](line_marker_provider.html)
[Top](cls_tutorial.html)
[Next](reference_contributor.html)



