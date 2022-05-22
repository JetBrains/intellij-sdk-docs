// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.language;

import com.intellij.icons.AllIcons;
import com.intellij.ide.navigationToolbar.StructureAwareNavBarModelExtension;
import com.intellij.lang.Language;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.PsiCommentImpl;
import com.intellij.psi.impl.source.tree.TreeElement;
import org.intellij.sdk.language.psi.SimpleFile;
import org.intellij.sdk.language.psi.SimpleProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;

public class SimpleStructureAwareNavbar extends StructureAwareNavBarModelExtension {
    @NotNull
    @Override
    protected Language getLanguage() {
        return SimpleLanguage.INSTANCE;
    }

    @Override
    public @Nullable String getPresentableText(Object object) {
        if (object instanceof SimpleFile) {
            return ((SimpleFile) object).getName();
        }
        if (object instanceof SimpleProperty) {
            return ((SimpleProperty) object).getName();
        }

        return null;
    }

    @Override
    @Nullable
    public Icon getIcon(Object object) {
        if (object instanceof SimpleProperty) {
            return AllIcons.Nodes.Property;
        }

        return null;
    }

    @Override
    public @Nullable PsiElement adjustElement(@NotNull PsiElement psiElement) {
        if (psiElement instanceof PsiComment) {
            if (isCommentAttachedToProperty(psiElement)) {
                return ((PsiCommentImpl) psiElement).getTreeNext().getTreeNext().getPsi();
            }
            return psiElement.getContainingFile();
        }
        return super.adjustElement(psiElement);
    }

    private boolean isCommentAttachedToProperty(PsiElement psiElement) {
        if (psiElement instanceof PsiComment) {
            TreeElement next = ((PsiCommentImpl) psiElement).getTreeNext();
            if (next == null) {
                return false;
            }
            TreeElement afterNext = next.getTreeNext();
            if (afterNext == null) {
                return false;
            }

            PsiElement afterNextPsi = afterNext.getPsi();
            return afterNextPsi instanceof SimpleProperty;
        }
        return false;
    }
}
