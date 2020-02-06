// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.intellij.sdk.language;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import com.intellij.sdk.language.psi.SimpleTypes;
import org.jetbrains.annotations.NotNull;

public class SimpleCompletionContributor extends CompletionContributor {
  public SimpleCompletionContributor() {
    // Register the completion providers
    extend( CompletionType.BASIC,
            PlatformPatterns.psiElement(SimpleTypes.VALUE).withLanguage(SimpleLanguage.INSTANCE),
            new CompletionProvider<CompletionParameters>() {
                  // Define candidate completions
                  public void addCompletions(@NotNull CompletionParameters parameters,
                                             ProcessingContext context,
                                             @NotNull CompletionResultSet resultSet) {
                    // Create a completion independent of context for Simple language
                    resultSet.addElement(LookupElementBuilder.create("Hello"));
                  }
            }
    );
  }
}
