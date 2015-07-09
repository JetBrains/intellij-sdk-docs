---
title: 14. Structure View Factory
---


A structure view factory allows to show the structure of any file in a *Structure* tool window for easy navigation between items.

### 14.1. Define a structure view factory

```java
package com.simpleplugin;

import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.lang.PsiStructureViewFactory;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleStructureViewFactory implements PsiStructureViewFactory {
    @Nullable
    @Override
    public StructureViewBuilder getStructureViewBuilder(final PsiFile psiFile) {
        return new TreeBasedStructureViewBuilder() {
            @NotNull
            @Override
            public StructureViewModel createStructureViewModel() {
                return new SimpleStructureViewModel(psiFile);
            }
        };
    }
}
```

### 14.2. Define a structure view model

```java
package com.simpleplugin;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.psi.PsiFile;
import com.simpleplugin.psi.SimpleFile;
import org.jetbrains.annotations.NotNull;

public class SimpleStructureViewModel extends StructureViewModelBase implements
        StructureViewModel.ElementInfoProvider {
    public SimpleStructureViewModel(PsiFile psiFile) {
        super(psiFile, new SimpleStructureViewElement(psiFile));
    }

    @NotNull
    public Sorter[] getSorters() {
        return new Sorter[] {Sorter.ALPHA_SORTER};
    }


    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
        return false;
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement element) {
        return element instanceof SimpleFile;
    }
}
```

### 14.3. Define a structure view element

```java
package com.simpleplugin;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.simpleplugin.psi.SimpleFile;
import com.simpleplugin.psi.SimpleProperty;

import java.util.ArrayList;
import java.util.List;

public class SimpleStructureViewElement implements StructureViewTreeElement, SortableTreeElement {
    private PsiElement element;

    public SimpleStructureViewElement(PsiElement element) {
        this.element = element;
    }

    @Override
    public Object getValue() {
        return element;
    }

    @Override
    public void navigate(boolean requestFocus) {
        if (element instanceof NavigationItem) {
            ((NavigationItem) element).navigate(requestFocus);
        }
    }

    @Override
    public boolean canNavigate() {
        return element instanceof NavigationItem &&
                ((NavigationItem)element).canNavigate();
    }

    @Override
    public boolean canNavigateToSource() {
        return element instanceof NavigationItem &&
                ((NavigationItem)element).canNavigateToSource();
    }

    @Override
    public String getAlphaSortKey() {
        return element instanceof PsiNamedElement ? ((PsiNamedElement) element).getName() : null;
    }

    @Override
    public ItemPresentation getPresentation() {
        return element instanceof NavigationItem ?
                ((NavigationItem) element).getPresentation() : null;
    }

    @Override
    public TreeElement[] getChildren() {
        if (element instanceof SimpleFile) {
            SimpleProperty[] properties = PsiTreeUtil.getChildrenOfType(element, SimpleProperty.class);
            List<TreeElement> treeElements = new ArrayList<TreeElement>(properties.length);
            for (SimpleProperty property : properties) {
                treeElements.add(new SimpleStructureViewElement(property));
            }
            return treeElements.toArray(new TreeElement[treeElements.size()]);
        } else {
            return EMPTY_ARRAY;
        }
    }
}
```

### 14.4. Register the structure view factory

```xml
<lang.psiStructureViewFactory language="Simple" implementationClass="com.simpleplugin.SimpleStructureViewFactory"/>
```

### 14.5. Run the project

![Structure View](img/structure_view.png)

[Previous](go_to_symbol_contributor.html)
[Top](../custom_language_support.html)
[Next](formatter.html)
