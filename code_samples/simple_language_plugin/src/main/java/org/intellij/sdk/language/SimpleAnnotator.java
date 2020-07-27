// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.language;

import com.intellij.lang.annotation.AnnotationBuilder;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import org.intellij.sdk.language.psi.SimpleProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.intellij.lang.annotation.HighlightSeverity.ERROR;
import static com.intellij.lang.annotation.HighlightSeverity.INFORMATION;


public class SimpleAnnotator implements Annotator {
  // Define strings for the Simple language prefix - used for annotations, line markers, etc.
  public static final String SIMPLE_PREFIX_STR = "simple";
  public static final String SIMPLE_SEPARATOR_STR = ":";

  @Override
  public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
    // Ensure the Psi Element is an expression
    if (!(element instanceof PsiLiteralExpression)) return;

    // Ensure the Psi element contains a string that starts with the key and separator
    PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
    String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
    if ((value == null) || !value.startsWith(SIMPLE_PREFIX_STR + SIMPLE_SEPARATOR_STR)) return;

    // Define the text ranges (start is inclusive, end is exclusive)
    // "simple:key"
    //  01234567890
    TextRange prefixRange = TextRange.from(element.getTextRange().getStartOffset(), SIMPLE_PREFIX_STR.length() + 1);
    TextRange separatorRange = TextRange.from(prefixRange.getEndOffset(), SIMPLE_SEPARATOR_STR.length());
    TextRange keyRange = new TextRange(separatorRange.getEndOffset(), element.getTextRange().getEndOffset() - 1);

    // Get the list of properties from the Project
    String possibleProperties = value.substring(SIMPLE_PREFIX_STR.length() + SIMPLE_SEPARATOR_STR.length());
    Project project = element.getProject();
    List<SimpleProperty> properties = SimpleUtil.findProperties(project, possibleProperties);

    // Set the annotations using the text ranges - Normally there would be one range, set by the element itself.
    holder.newAnnotation(INFORMATION, "").range(prefixRange).textAttributes(DefaultLanguageHighlighterColors.KEYWORD).create();
    holder.newAnnotation(INFORMATION, "").range(separatorRange).textAttributes(SimpleSyntaxHighlighter.SEPARATOR).create();
    if (properties.isEmpty()) {
      // No well-formed property found following the key-separator
      AnnotationBuilder builder = holder.newAnnotation(ERROR, "Unresolved property").range(keyRange);
      // Force the text attributes to Simple syntax bad character
      builder.textAttributes(SimpleSyntaxHighlighter.BAD_CHARACTER);
      // ** Tutorial step 18.3 - Add a quick fix for the string containing possible properties
      builder.withFix(new SimpleCreatePropertyQuickFix(possibleProperties));
      // Finish creating new annotation
      builder.create();
    } else {
      // Found at least one property, force the text attributes to Simple syntax value character
      holder.newAnnotation(INFORMATION, "").range(keyRange).textAttributes(SimpleSyntaxHighlighter.VALUE).create();
    }
  }

}
