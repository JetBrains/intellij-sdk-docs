// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.inspection;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiPlainTextFile;
import org.jetbrains.annotations.NotNull;

public class DemoInspectionVisitor extends PsiElementVisitor {
  @Override
  public void visitElement(@NotNull PsiElement element) {
    super.visitElement(element);
  }

  @Override
  public void visitPlainTextFile(@NotNull PsiPlainTextFile file) {
    super.visitPlainTextFile(file);
  }
}
