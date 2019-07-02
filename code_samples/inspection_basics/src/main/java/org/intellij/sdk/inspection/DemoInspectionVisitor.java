// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.inspection;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiPlainTextFile;

/**
 * @author Anna Bulenkova
 */
public class DemoInspectionVisitor extends PsiElementVisitor {
  @Override
  public void visitElement(PsiElement element) {
    super.visitElement(element);
  }

  @Override
  public void visitPlainTextFile(PsiPlainTextFile file) {
    super.visitPlainTextFile(file);
  }
}
