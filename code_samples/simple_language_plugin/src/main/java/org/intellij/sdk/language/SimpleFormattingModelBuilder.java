// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.language;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.intellij.sdk.language.psi.SimpleTypes;
import org.jetbrains.annotations.*;

public class SimpleFormattingModelBuilder implements FormattingModelBuilder {
  @NotNull
  @Override
  public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
    return FormattingModelProvider
                 .createFormattingModelForPsiFile(element.getContainingFile(),
                                                  new SimpleBlock(element.getNode(),
                                                                  Wrap.createWrap(WrapType.NONE, false),
                                                                  Alignment.createAlignment(),
                                                                  createSpaceBuilder(settings)),
                                                  settings);
  }
  
  private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
    return new SpacingBuilder(settings, SimpleLanguage.INSTANCE)
                 .around(SimpleTypes.SEPARATOR)
                 .spaceIf(settings.getCommonSettings(SimpleLanguage.INSTANCE.getID()).SPACE_AROUND_ASSIGNMENT_OPERATORS)
                 .before(SimpleTypes.PROPERTY)
                 .none();
  }
  
  @Nullable
  @Override
  public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
    return null;
  }
}
