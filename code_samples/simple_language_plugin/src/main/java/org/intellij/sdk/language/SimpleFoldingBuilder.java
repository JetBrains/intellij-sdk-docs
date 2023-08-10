// Copyright 2000-2023 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.language;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import org.intellij.sdk.language.psi.SimpleProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SimpleFoldingBuilder extends FoldingBuilderEx implements DumbAware {

  @Override
  public FoldingDescriptor @NotNull [] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
    // Initialize the group of folding regions that will expand/collapse together.
    FoldingGroup group = FoldingGroup.newGroup(SimpleAnnotator.SIMPLE_PREFIX_STR);
    // Initialize the list of folding regions
    List<FoldingDescriptor> descriptors = new ArrayList<>();
    // Get a collection of the literal expressions in the document below root
    Collection<PsiLiteralExpression> literalExpressions =
            PsiTreeUtil.findChildrenOfType(root, PsiLiteralExpression.class);
    // Evaluate the collection
    for (final PsiLiteralExpression literalExpression : literalExpressions) {
      String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
      if (value != null && value.startsWith(SimpleAnnotator.SIMPLE_PREFIX_STR + SimpleAnnotator.SIMPLE_SEPARATOR_STR)) {
        Project project = literalExpression.getProject();
        String key = value.substring(
                SimpleAnnotator.SIMPLE_PREFIX_STR.length() + SimpleAnnotator.SIMPLE_SEPARATOR_STR.length()
        );
        // find SimpleProperty for the given key in the project
        SimpleProperty simpleProperty = ContainerUtil.getOnlyItem(SimpleUtil.findProperties(project, key));
        if (simpleProperty != null) {
          // Add a folding descriptor for the literal expression at this node.
          descriptors.add(new FoldingDescriptor(literalExpression.getNode(),
                  new TextRange(literalExpression.getTextRange().getStartOffset() + 1,
                          literalExpression.getTextRange().getEndOffset() - 1),
                  group, Collections.singleton(simpleProperty)));
        }
      }
    }
    return descriptors.toArray(FoldingDescriptor.EMPTY);
  }

  /**
   * Gets the Simple Language 'value' string corresponding to the 'key'
   *
   * @param node Node corresponding to PsiLiteralExpression containing a string in the format
   *             SIMPLE_PREFIX_STR + SIMPLE_SEPARATOR_STR + Key, where Key is
   *             defined by the Simple language file.
   */
  @Nullable
  @Override
  public String getPlaceholderText(@NotNull ASTNode node) {
    if (!(node.getPsi() instanceof PsiLiteralExpression nodeElement)) {
      return null ;
    }

    String key = ((String) nodeElement.getValue()).substring(
            SimpleAnnotator.SIMPLE_PREFIX_STR.length() + SimpleAnnotator.SIMPLE_SEPARATOR_STR.length()
    );

    SimpleProperty simpleProperty = ContainerUtil.getOnlyItem(SimpleUtil.findProperties(nodeElement.getProject(), key));
    if (simpleProperty == null) return StringUtil.THREE_DOTS;

    String propertyValue = simpleProperty.getValue();
    // IMPORTANT: keys can come with no values, so a test for null is needed
    // IMPORTANT: Convert embedded \n to backslash n, so that the string will look
    // like it has LF embedded in it and embedded " to escaped "
    return propertyValue == null ? StringUtil.THREE_DOTS : propertyValue.replaceAll("\n", "\\n").replaceAll("\"", "\\\\\"");
  }

  @Override
  public boolean isCollapsedByDefault(@NotNull ASTNode node) {
    return true;
  }

}
