// This is a generated file. Not intended for manual editing.
package com.simpleplugin.psi;

import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

public class SimpleVisitor extends PsiElementVisitor {

  public void visitProperty(@NotNull SimpleProperty o) {
    visitNamedElement(o);
  }

  public void visitNamedElement(@NotNull SimpleNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
