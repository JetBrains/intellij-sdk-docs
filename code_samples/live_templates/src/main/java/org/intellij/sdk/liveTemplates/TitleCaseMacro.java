// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.intellij.sdk.liveTemplates;

import com.intellij.codeInsight.template.*;
import com.intellij.codeInsight.template.macro.MacroBase;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;

final class TitleCaseMacro extends MacroBase {

  public TitleCaseMacro() {
    super("titleCase", "titleCase(String)");
  }

  /**
   * Strictly to uphold contract for constructors in base class.
   */
  private TitleCaseMacro(String name, String description) {
    super(name, description);
  }

  @Override
  protected Result calculateResult(Expression @NotNull [] params, ExpressionContext context, boolean quick) {
    // Retrieve the text from the macro or selection, if any is available.
    String text = getTextResult(params, context, true);
    if (text == null) {
      return null;
    }
    if (!text.isEmpty()) {
      // Capitalize the start of every word
      text = StringUtil.toTitleCase(text);
    }
    return new TextResult(text);
  }

  @Override
  public boolean isAcceptableInContext(TemplateContextType context) {
    // Might want to be less restrictive in future
    return (context instanceof MarkdownContext);
  }

}
