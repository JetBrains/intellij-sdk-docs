---
layout: editable
title: 13. Go To Symbol Contributor
---

*A go to symbol contributor helps user to navigate to any PSI element by it's name.*

### 1. Define helper method for generated PSI elements

To specify how a PSI element looks like in the *Go To Symbol* popup window, *Structure* tool window or another components, it should implement *getPresentation* method.

This means we need to define this method in our utility *com.simpleplugin.parser.SimplePsiImplUtil* and regenerate the parser and PSI classes.

```java
public static ItemPresentation getPresentation(final SimpleProperty element) {
    return new ItemPresentation() {
        @Nullable
        @Override
        public String getPresentableText() {
            return element.getKey();
        }

        @Nullable
        @Override
        public String getLocationString() {
            return element.getContainingFile().getName();
        }

        @Nullable
        @Override
        public Icon getIcon(boolean unused) {
            return SimpleIcons.FILE;
        }
    };
}
```

### 2. Update grammar and regenerate the parser

```java
property ::= (KEY? SEPARATOR VALUE?) | KEY {mixin="com.simpleplugin.psi.impl.SimpleNamedElementImpl"
  implements="com.simpleplugin.psi.SimpleNamedElement" methods=[getKey getValue getName setName getNameIdentifier getPresentation]}
```

### 3. Define a go to symbol contributor

```java
package com.simpleplugin;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.simpleplugin.psi.SimpleProperty;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SimpleChooseByNameContributor implements ChooseByNameContributor {
    @NotNull
    @Override
    public String[] getNames(Project project, boolean includeNonProjectItems) {
        List<SimpleProperty> properties = SimpleUtil.findProperties(project);
        List<String> names = new ArrayList<String>(properties.size());
        for (SimpleProperty property : properties) {
            if (property.getKey() != null && property.getKey().length() > 0) {
                names.add(property.getKey());
            }
        }
        return names.toArray(new String[names.size()]);
    }

    @NotNull
    @Override
    public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
        // todo include non project items
        List<SimpleProperty> properties = SimpleUtil.findProperties(project, name);
        return properties.toArray(new NavigationItem[properties.size()]);
    }
}
```

### 4. Register the go to symbol contributor

```xml
<gotoSymbolContributor implementation="com.simpleplugin.SimpleChooseByNameContributor"/>
```

### 5. Run the project

Now we can navigate to a property definition by name pattern via *⌥⌘⇧N* shortcut.

![Go To Symbol](img/go_to_symbol.png)

[Previous](folding_builder.html)
[Top](cls_tutorial.html)
[Next](structure_view_factory)
