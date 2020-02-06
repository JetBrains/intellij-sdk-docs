// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.intellij.sdk.language;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import com.intellij.sdk.language.psi.SimpleProperty;
import org.jetbrains.annotations.*;

public class SimpleRefactoringSupportProvider extends RefactoringSupportProvider {
  @Override
  public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement elementToRename, @Nullable PsiElement context) {
    return (elementToRename instanceof SimpleProperty);
  }
}

/*
2020-01-10 21:59:36,392 [  74521]   WARN - name.RenamePsiElementProcessor - org.jetbrains.kotlin.idea.refactoring.rename.RenameKotlinTypeParameterProcessor overrides deprecated findReferences(..).
Override findReferences(PsiElement, SearchScope, boolean) instead.
*/