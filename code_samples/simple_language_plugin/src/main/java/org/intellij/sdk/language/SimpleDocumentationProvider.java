package org.intellij.sdk.language;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.lang.documentation.DocumentationMarkup;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.*;
import org.apache.commons.lang3.tuple.Pair;
import org.intellij.sdk.language.psi.SimpleProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
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
   * Extracts the key, value, file and documentation comment of a Simple key/value entry and returns
   * a formatted representation of the information.
   */
  @Override
  public @Nullable String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {
    if (element instanceof SimpleProperty) {
      final String key = ((SimpleProperty) element).getKey();
      final String value = ((SimpleProperty) element).getValue();
      final String file = element.getContainingFile().getVirtualFile().getName();
      final String docComment = SimpleUtil.findDocumentationComment((SimpleProperty) element);

      return renderFullDoc(key, value, file, docComment);
    }
    return null;
  }

  /**
   * Provides the information in which file the Simple language key/value is defined.
   */
  @Override
  public @Nullable String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
    if (element instanceof SimpleProperty) {
      final String key = ((SimpleProperty) element).getKey();
      final String file = element.getContainingFile().getVirtualFile().getName();
      return "\"" + key + "\" in " + file;
    }
    return null;
  }

  /**
   * Provides documentation when a Simple Language element is hovered with the mouse.
   */
  @Override
  public @Nullable String generateHoverDoc(@NotNull PsiElement element, @Nullable PsiElement originalElement) {
      return generateDoc(element, originalElement);
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

  /**
   * Creates the formatted documentation using {@link DocumentationMarkup}. See the Java doc of
   * {@link com.intellij.lang.documentation.DocumentationProvider#generateDoc(PsiElement, PsiElement)} for more
   * information about building the layout.
   */
  private String renderFullDoc(String key, String value, String file, String docComment) {
    List<Pair<String, String>> values = new ArrayList<>();
    values.add(Pair.of("Key:", key));
    values.add(Pair.of("Value:", value));
    values.add(Pair.of("File:", file));
    values.add(Pair.of("Comment:", docComment));


    StringBuilder doc = new StringBuilder();
    doc.append(DocumentationMarkup.DEFINITION_START);
    doc.append("Simple Property");
    doc.append(DocumentationMarkup.DEFINITION_END);
    doc.append(DocumentationMarkup.CONTENT_START);
    doc.append(value);
    doc.append(DocumentationMarkup.CONTENT_END);
    doc.append(DocumentationMarkup.SECTIONS_START);
    for (Pair<String, String> p : values) {
      if (!p.getRight().isEmpty()) {
        doc.append(DocumentationMarkup.SECTION_HEADER_START);
        doc.append(p.getLeft());
        doc.append(DocumentationMarkup.SECTION_SEPARATOR);
        doc.append("<p>");
        doc.append(p.getRight());
        doc.append(DocumentationMarkup.SECTION_END);
      }
    }
    doc.append(DocumentationMarkup.SECTIONS_END);
    return doc.toString();
  }

 }
