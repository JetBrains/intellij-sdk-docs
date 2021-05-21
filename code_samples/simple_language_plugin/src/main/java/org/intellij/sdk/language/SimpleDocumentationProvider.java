package org.intellij.sdk.language;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.lang.documentation.DocumentationMarkup;
import com.intellij.lang.documentation.DocumentationUtil;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.*;
import org.intellij.sdk.language.psi.SimpleProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SimpleDocumentationProvider extends AbstractDocumentationProvider {

  /**
   * For the Simple Language, we don't have online documentation. However, if your language provides
   * references pages online, URLs for the element can be returned here.
   */
  @Override
  public @Nullable List<String> getUrlFor(PsiElement element, PsiElement originalElement) {
    return null;
  }

  /**
   * Formats the key, value and documentation comment of a Simple key/value entry for showing
   * it in quick documentation.
   */
  @Override
  public @Nullable String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {
    if (element instanceof SimpleProperty) {
      final String key = ((SimpleProperty) element).getKey();
      final String value = ((SimpleProperty) element).getValue();
      final String file = element.getContainingFile().getVirtualFile().getName();
      final String docComment = SimpleUtil.findDocumentationComment((SimpleProperty) element);

      StringBuilder doc = new StringBuilder();
      doc.append(DocumentationMarkup.DEFINITION_START);
      DocumentationUtil.formatEntityName("File", file, doc);
      DocumentationUtil.formatEntityName("Key", key, doc);
      DocumentationUtil.formatEntityName("Value", value, doc);
      doc.append(DocumentationMarkup.DEFINITION_END);
      doc.append(DocumentationMarkup.CONTENT_START);
      doc.append(docComment);
      doc.append(DocumentationMarkup.CONTENT_END);
      return doc.toString();
    }
    return null;
  }

  /**
   * Provides documentation when a Simple Language element is hovered with the mouse.
   */
  @Override
  public @Nullable String generateHoverDoc(@NotNull PsiElement element, @Nullable PsiElement originalElement) {
    if (element instanceof SimpleProperty) {
      return generateDoc(element, originalElement);
    }
    return null;
  }

  /**
   * Returns the {@code SimpleProperty} element when quick documentation was called inside the autocompletion
   * popup.
   */
  @Override
  public @Nullable PsiElement getDocumentationElementForLookupItem(PsiManager psiManager, Object object, PsiElement element) {
    if (object instanceof SimpleProperty) {
      return (PsiElement) object;
    }
    return null;
  }

  /**
   * Extracts {@code SimpleProperty} element from usages in Java strings or Simple files
   */
  @Override
  public @Nullable PsiElement getCustomDocumentationElement(@NotNull Editor editor, @NotNull PsiFile file, @Nullable PsiElement contextElement, int targetOffset) {
    if (contextElement != null) {
      // In this part the SimpleProperty element is extracted from inside a Java string
      if (contextElement instanceof PsiJavaToken && ((PsiJavaToken) contextElement).getTokenType().equals(JavaTokenType.STRING_LITERAL)) {
        final PsiElement parent = contextElement.getParent();
        final PsiReference[] references = parent.getReferences();
        for (PsiReference ref : references) {
          if (ref instanceof SimpleReference) {
            final PsiElement property = ref.resolve();
            if (property instanceof SimpleProperty) {
              return property;
            }
          }
        }
      }
      // In this part the SimpleProperty element is extracted when inside a .simple file
      else if (contextElement.getContainingFile().getLanguage().equals(SimpleLanguage.INSTANCE)) {
        final PsiElement property = contextElement.getParent();
        if (property instanceof SimpleProperty) {
          return property;
        }
      }
    }
    return super.getCustomDocumentationElement(editor, file, contextElement, targetOffset);
  }
}
