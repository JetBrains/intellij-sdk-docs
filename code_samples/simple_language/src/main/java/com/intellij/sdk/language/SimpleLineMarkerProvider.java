package com.intellij.sdk.language;

import com.intellij.codeInsight.daemon.*;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.java.PsiJavaTokenImpl;
import com.intellij.sdk.language.psi.SimpleProperty;
import org.jetbrains.annotations.NotNull;
import static com.intellij.sdk.language.SimpleAnnotator.*;

import java.util.*;

public class SimpleLineMarkerProvider extends RelatedItemLineMarkerProvider {
  @Override
  protected void collectNavigationMarkers( @NotNull PsiElement element,
                                           @NotNull Collection< ? super RelatedItemLineMarkerInfo > result ) {
    // This must be an element with a literal expression as a parent
    if ( !(element instanceof PsiJavaTokenImpl) || !(element.getParent() instanceof PsiLiteralExpression) ) return;
    
    // The literal expression must start with the Simple language literal expression
    PsiLiteralExpression literalExpression = (PsiLiteralExpression) element.getParent();
    String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
    if ( ( value == null ) || !value.startsWith( SIMPLE_PREFIX_STR + SIMPLE_SEPARATOR_STR ) ) return;

    // Get the Simple language property usage
    Project project = element.getProject();
    String possibleProperties = value.substring( SIMPLE_PREFIX_STR.length()+SIMPLE_SEPARATOR_STR.length() );
    final List< SimpleProperty > properties = SimpleUtil.findProperties( project, possibleProperties );
    if ( properties.size() > 0 ) {
      // Add the property to a collection of line marker info
      NavigationGutterIconBuilder< PsiElement > builder =
            NavigationGutterIconBuilder.create( SimpleIcons.FILE )
                                       .setTargets( properties )
                                       .setTooltipText( "Navigate to Simple language property" );
      result.add( builder.createLineMarkerInfo( element ) );
    }
  }

}