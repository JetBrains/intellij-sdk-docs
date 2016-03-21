import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class MarkdownContext extends TemplateContextType {
  protected MarkdownContext() {
    super("MARKDOWN", "Markdown");
  }

  @Override
  public boolean isInContext(@NotNull PsiFile file, int offset) {
    return file.getName().endsWith(".md");
  }
}
