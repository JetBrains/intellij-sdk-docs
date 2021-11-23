// This is a generated file. Not intended for manual editing.
package org.intellij.sdk.language.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.intellij.sdk.language.psi.SimpleProperty;
import org.intellij.sdk.language.psi.SimpleVisitor;
import org.jetbrains.annotations.NotNull;

public class SimplePropertyImpl extends SimpleNamedElementImpl implements SimpleProperty {

  public SimplePropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SimpleVisitor visitor) {
    visitor.visitProperty(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimpleVisitor) accept((SimpleVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public String getKey() {
    return SimplePsiImplUtil.getKey(this);
  }

  @Override
  public String getValue() {
    return SimplePsiImplUtil.getValue(this);
  }

  @Override
  public String getName() {
    return SimplePsiImplUtil.getName(this);
  }

  @Override
  public PsiElement setName(@NotNull String newName) {
    return SimplePsiImplUtil.setName(this, newName);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return SimplePsiImplUtil.getNameIdentifier(this);
  }

  @Override
  public ItemPresentation getPresentation() {
    return SimplePsiImplUtil.getPresentation(this);
  }

}
