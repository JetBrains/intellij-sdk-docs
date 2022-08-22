// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.intellij.sdk.language;

import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.spellchecker.inspections.PlainTextSplitter;
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy;
import com.intellij.spellchecker.tokenizer.TokenConsumer;
import com.intellij.spellchecker.tokenizer.Tokenizer;
import org.intellij.sdk.language.psi.SimpleProperty;
import org.jetbrains.annotations.NotNull;

public class SimpleSpellcheckingStrategy extends SpellcheckingStrategy {

    @Override
    public @NotNull Tokenizer<?> getTokenizer(PsiElement element) {
        if (element instanceof PsiComment) {
            return myCommentTokenizer;
        }

        if (element instanceof SimpleProperty) {
            return new SimplePropertyTokenizer();
        }

        return EMPTY_TOKENIZER;
    }

    private static class SimplePropertyTokenizer extends Tokenizer<SimpleProperty> {
        public void tokenize(@NotNull SimpleProperty element, TokenConsumer consumer) {
            String text = element.getText();
            if (element.getNode() != null && !text.isEmpty()) {
                consumer.consumeToken(element, PlainTextSplitter.getInstance());
            }
        }
    }

}
