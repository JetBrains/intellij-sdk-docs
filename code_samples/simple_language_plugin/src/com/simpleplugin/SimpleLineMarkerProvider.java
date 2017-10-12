package com.simpleplugin;

import com.intellij.codeInsight.daemon.*;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.simpleplugin.psi.SimpleProperty;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SimpleLineMarkerProvider extends RelatedItemLineMarkerProvider {
  @Override
  protected void collectNavigationMarkers(@NotNull PsiElement element,
                                          Collection<? super RelatedItemLineMarkerInfo> result) {
    if (element instanceof PsiLiteralExpression) {
      PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
      String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
      if (value != null && value.startsWith("simple" + ":")) {
        Project project = element.getProject();
        final List<SimpleProperty> properties = SimpleUtil.findProperties(project, value.substring(7));
        if (properties.size() > 0) {
          NavigationGutterIconBuilder<PsiElement> builder =
              NavigationGutterIconBuilder.create(SimpleIcons.FILE).
                  setTargets(properties).
                  setTooltipText("Navigate to a simple property");
          result.add(builder.createLineMarkerInfo(element));
        }
      }
    }
  }
}
