---
layout: editable
title: PSI Helpers and Utilities
---

<!--
INITIAL_SOURCE https://confluence.jetbrains.com/display/IntelliJIDEA/PSI+Helpers+and+Utilities
-->



### 1. Define helper methods for generated PSI elements

If we want to have custom methods in PSI classes we need to define them separately and ask Grammar-Kit to embed them into generated code.

Let's define an utility class with these helper methods.

```java
package com.simpleplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.simpleplugin.psi.SimpleProperty;
import com.simpleplugin.psi.SimpleTypes;

public class SimplePsiImplUtil {
    public static String getKey(SimpleProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(SimpleTypes.KEY);
        if (keyNode != null) {
            return keyNode.getText();
        } else {
            return null;
        }
    }

    public static String getValue(SimpleProperty element) {
        ASTNode valueNode = element.getNode().findChildByType(SimpleTypes.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }
}
```

### 2. Update grammar and regenerate the parser

Now we tell to use this utility class in the grammar file via *stubParserClass* attribute.

To tell which methods for which PSI classes must be used we specify methods for particular rule.

```java
{
  parserClass="com.simpleplugin.parser.SimpleParser"

  extends="com.intellij.extapi.psi.ASTWrapperPsiElement"

  psiClassPrefix="Simple"
  psiImplClassSuffix="Impl"
  psiPackage="com.simpleplugin.psi"
  psiImplPackage="com.simpleplugin.psi.impl"

  elementTypeHolderClass="com.simpleplugin.psi.SimpleTypes"
  elementTypeClass="com.simpleplugin.psi.SimpleElementType"
  tokenTypeClass="com.simpleplugin.psi.SimpleTokenType"

  psiImplUtilClass="com.simpleplugin.psi.impl.SimplePsiImplUtil"
}

simpleFile ::= item_*

private item_ ::= (property|COMMENT|CRLF)

property ::= (KEY? SEPARATOR VALUE?) | KEY {methods=[getKey getValue]}
```

After we made our changes to the grammar we can regenerate the parser and PSI classes.

### 3. Define an utility to search properties

Now we need an utility class to search PSI elements for defined properties over the project.
We will use this utility later when implementing code assistance.

```java
package com.simpleplugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import com.simpleplugin.psi.SimpleFile;
import com.simpleplugin.psi.SimpleProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SimpleUtil {
    public static List<SimpleProperty> findProperties(Project project, String key) {
        List<SimpleProperty> result = null;
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, SimpleFileType.INSTANCE,
                GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            SimpleFile simpleFile = (SimpleFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                SimpleProperty[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, SimpleProperty.class);
                if (properties != null) {
                    for (SimpleProperty property : properties) {
                        if (key.equals(property.getKey())) {
                            if (result == null) {
                                result = new ArrayList<SimpleProperty>();
                            }
                            result.add(property);
                        }
                    }
                }
            }
        }
        return result != null ? result : Collections.<SimpleProperty>emptyList();
    }

    public static List<SimpleProperty> findProperties(Project project) {
        List<SimpleProperty> result = new ArrayList<SimpleProperty>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, SimpleFileType.INSTANCE,
                GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            SimpleFile simpleFile = (SimpleFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                SimpleProperty[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, SimpleProperty.class);
                if (properties != null) {
                    Collections.addAll(result, properties);
                }
            }
        }
        return result;
    }
}
```

----------------
[Previous](syntax_highlighter_and_color_settings_page.html)
[Top](cls_tutorial.html)
[Next](annotator.html)

