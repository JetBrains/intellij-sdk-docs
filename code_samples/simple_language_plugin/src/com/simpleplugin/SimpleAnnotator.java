package com.simpleplugin;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.simpleplugin.psi.SimpleProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.intellij.openapi.editor.DefaultLanguageHighlighterColors.LINE_COMMENT;
import static java.util.Objects.isNull;

public class SimpleAnnotator implements Annotator {
  @Override
  public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
    if (!(element instanceof PsiLiteralExpression)) return;

    PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
    String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;

    if (isNull(value) || !value.startsWith("simple:")) return;

    Project project = element.getProject();
    String key = value.substring(7);
    List<SimpleProperty> properties = SimpleUtil.findProperties(project, key);

    final int startOffset = element.getTextRange().getStartOffset() + 8;
    final int endOffset = element.getTextRange().getEndOffset() - 1;
    final TextRange range = new TextRange(startOffset, endOffset);

    if (properties.size() == 1) {
      holder.createInfoAnnotation(range, null).setTextAttributes(LINE_COMMENT);
    } else if (properties.size() == 0) {
      holder.createErrorAnnotation(range, "Unresolved property");
    }
  }
}