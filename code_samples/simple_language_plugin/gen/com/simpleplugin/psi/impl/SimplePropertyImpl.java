// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.*;
import com.simpleplugin.psi.*;
import org.jetbrains.annotations.NotNull;

public class SimplePropertyImpl extends SimpleNamedElementImpl implements SimpleProperty {

  public SimplePropertyImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleVisitor) ((SimpleVisitor) visitor).visitProperty(this);
    else super.accept(visitor);
  }

  public String getKey() {
    return SimplePsiImplUtil.getKey(this);
  }

  public String getValue() {
    return SimplePsiImplUtil.getValue(this);
  }

  public String getName() {
    return SimplePsiImplUtil.getName(this);
  }

  public PsiElement setName(String newName) {
    return SimplePsiImplUtil.setName(this, newName);
  }

  public PsiElement getNameIdentifier() {
    return SimplePsiImplUtil.getNameIdentifier(this);
  }

  public ItemPresentation getPresentation() {
    return SimplePsiImplUtil.getPresentation(this);
  }

}
