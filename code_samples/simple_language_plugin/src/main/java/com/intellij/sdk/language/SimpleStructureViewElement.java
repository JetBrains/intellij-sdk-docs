// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.intellij.sdk.language;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.sdk.language.psi.SimpleFile;
import com.intellij.sdk.language.psi.SimpleProperty;
import com.intellij.sdk.language.psi.impl.SimplePropertyImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SimpleStructureViewElement implements StructureViewTreeElement, SortableTreeElement {
  private NavigatablePsiElement element;
  
  public SimpleStructureViewElement(NavigatablePsiElement element) {
    this.element = element;
  }
  
  @Override
  public Object getValue() {
    return element;
  }
  
  @Override
  public void navigate(boolean requestFocus) {
    element.navigate(requestFocus);
  }
  
  @Override
  public boolean canNavigate() {
    return element.canNavigate();
  }
  
  @Override
  public boolean canNavigateToSource() {
    return element.canNavigateToSource();
  }
  
  @NotNull
  @Override
  public String getAlphaSortKey() {
    String name = element.getName();
    return name != null ? name : "";
  }
  
  @NotNull
  @Override
  public ItemPresentation getPresentation() {
    ItemPresentation presentation = element.getPresentation();
    return presentation != null ? presentation : new PresentationData();
  }
  
  @Override
  public TreeElement[] getChildren() {
    if (element instanceof SimpleFile) {
      SimpleProperty[] properties = PsiTreeUtil.getChildrenOfType(element, SimpleProperty.class);
      List<TreeElement> treeElements = new ArrayList<TreeElement>(properties.length);
      for (SimpleProperty property : properties) {
        treeElements.add(new SimpleStructureViewElement((SimplePropertyImpl) property));
      }
      return treeElements.toArray(new TreeElement[treeElements.size()]);
    } else {
      return EMPTY_ARRAY;
    }
  }
}