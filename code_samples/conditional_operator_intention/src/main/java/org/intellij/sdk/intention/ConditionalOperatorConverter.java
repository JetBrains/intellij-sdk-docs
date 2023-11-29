// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.intellij.sdk.intention;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Implements an intention action to replace a ternary statement with if-then-else.
 */
@NonNls
final class ConditionalOperatorConverter extends PsiElementBaseIntentionAction implements IntentionAction {

  /**
   * Checks whether this intention is available at the caret offset in file - the caret must sit just before a "?"
   * character in a ternary statement. If this condition is met, this intention's entry is shown in the available
   * intentions list.
   *
   * <p>Note: this method must do its checks quickly and return.</p>
   *
   * @param project a reference to the Project object being edited.
   * @param editor  a reference to the object editing the project source
   * @param element a reference to the PSI element currently under the caret
   * @return {@code true} if the caret is in a literal string element, so this functionality should be added to the
   * intention menu or {@code false} for all other types of caret positions
   */
  public boolean isAvailable(@NotNull Project project, Editor editor, @Nullable PsiElement element) {
    // Quick sanity check
    if (element == null) {
      return false;
    }

    // Is this a token of type representing a "?" character?
    if (element instanceof PsiJavaToken token) {
      if (token.getTokenType() != JavaTokenType.QUEST) {
        return false;
      }
      // Is this token part of a fully formed conditional, i.e. a ternary?
      if (token.getParent() instanceof PsiConditionalExpression conditionalExpression) {
        // Satisfies all criteria; call back invoke method
        return conditionalExpression.getThenExpression() != null && conditionalExpression.getElseExpression() != null;
      }
      return false;
    }
    return false;
  }

  /**
   * Modifies the PSI to change a ternary expression to an if-then-else statement.
   * If the ternary is part of a declaration, the declaration is separated and moved above the if-then-else statement.
   * Called when user selects this intention action from the available intentions list.
   *
   * @param project a reference to the Project object being edited.
   * @param editor  a reference to the object editing the project source
   * @param element a reference to the PSI element currently under the caret
   * @throws IncorrectOperationException Thrown by underlying (PSI model) write action context
   *                                     when manipulation of the PSI tree fails.
   */
  public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element)
      throws IncorrectOperationException {
    // Get the factory for making new PsiElements, and the code style manager to format new statements
    PsiElementFactory factory = JavaPsiFacade.getInstance(project).getElementFactory();
    CodeStyleManager codeStylist = CodeStyleManager.getInstance(project);

    // Get the parent of the "?" element in the ternary statement to find the conditional expression that contains it
    PsiConditionalExpression conditionalExpression =
        PsiTreeUtil.getParentOfType(element, PsiConditionalExpression.class, false);
    if (conditionalExpression == null) {
      return;
    }
    // Verify the conditional expression exists and has two outcomes in the ternary statement.
    PsiExpression thenExpression = conditionalExpression.getThenExpression();
    PsiExpression elseExpression = conditionalExpression.getElseExpression();
    if (thenExpression == null || elseExpression == null) {
      return;
    }

    // Keep searching up the PSI Tree in case the ternary is part of a FOR statement.
    PsiElement originalStatement = PsiTreeUtil.getParentOfType(conditionalExpression, PsiStatement.class, false);
    while (originalStatement instanceof PsiForStatement) {
      originalStatement = PsiTreeUtil.getParentOfType(originalStatement, PsiStatement.class, true);
    }
    if (originalStatement == null) {
      return;
    }

    // If the original statement is a declaration based on a ternary operator,
    // split the declaration and assignment
    if (originalStatement instanceof PsiDeclarationStatement declaration) {
      // Find the local variable within the declaration statement
      PsiElement[] declaredElements = declaration.getDeclaredElements();
      PsiLocalVariable variable = null;
      for (PsiElement declaredElement : declaredElements) {
        if (declaredElement instanceof PsiLocalVariable &&
            PsiTreeUtil.isAncestor(declaredElement, conditionalExpression, true)) {
          variable = (PsiLocalVariable) declaredElement;
          break;
        }
      }
      if (variable == null) {
        return;
      }

      // Ensure that the variable declaration is not combined with other declarations, and add a mark
      variable.normalizeDeclaration();
      Object marker = new Object();
      PsiTreeUtil.mark(conditionalExpression, marker);

      // Create a new expression to declare the local variable
      PsiExpressionStatement statement =
          (PsiExpressionStatement) factory.createStatementFromText(variable.getName() + " = 0;", null);
      statement = (PsiExpressionStatement) codeStylist.reformat(statement);

      // Replace initializer with the ternary expression, making an assignment statement using the ternary
      PsiExpression rExpression = ((PsiAssignmentExpression) statement.getExpression()).getRExpression();
      PsiExpression variableInitializer = variable.getInitializer();
      if (rExpression == null || variableInitializer == null) {
        return;
      }
      rExpression.replace(variableInitializer);

      // Remove the initializer portion of the local variable statement,
      // making it a declaration statement with no initializer
      variableInitializer.delete();

      // Get the grandparent of the local var declaration, and add the new declaration just beneath it
      PsiElement variableParent = variable.getParent();
      originalStatement = variableParent.getParent().addAfter(statement, variableParent);
      conditionalExpression = (PsiConditionalExpression) PsiTreeUtil.releaseMark(originalStatement, marker);
    }
    if (conditionalExpression == null) {
      return;
    }

    // Create an IF statement from a string with placeholder elements.
    // This will replace the ternary statement
    PsiIfStatement newIfStmt = (PsiIfStatement) factory.createStatementFromText("if (true) {a=b;} else {c=d;}", null);
    newIfStmt = (PsiIfStatement) codeStylist.reformat(newIfStmt);

    // Replace the conditional expression with the one from the original ternary expression
    PsiReferenceExpression condition = (PsiReferenceExpression) conditionalExpression.getCondition().copy();
    PsiExpression newIfStmtCondition = newIfStmt.getCondition();
    if (newIfStmtCondition == null) {
      return;
    }
    newIfStmtCondition.replace(condition);

    // Begin building the assignment string for the THEN and ELSE clauses using the
    // parent of the ternary conditional expression
    PsiAssignmentExpression assignmentExpression =
        PsiTreeUtil.getParentOfType(conditionalExpression, PsiAssignmentExpression.class, false);
    if (assignmentExpression == null) {
      return;
    }
    // Get the contents of the assignment expression up to the start of the ternary expression
    String exprFrag = assignmentExpression.getLExpression().getText()
        + assignmentExpression.getOperationSign().getText();

    // Build the THEN statement string for the new IF statement,
    // make a PsiExpressionStatement from the string, and switch the placeholder
    String thenStr = exprFrag + thenExpression.getText() + ";";
    PsiExpressionStatement thenStmt = (PsiExpressionStatement) factory.createStatementFromText(thenStr, null);
    PsiBlockStatement thenBranch = (PsiBlockStatement) newIfStmt.getThenBranch();
    if (thenBranch == null) {
      return;
    }
    thenBranch.getCodeBlock().getStatements()[0].replace(thenStmt);

    // Build the ELSE statement string for the new IF statement,
    // make a PsiExpressionStatement from the string, and switch the placeholder
    String elseStr = exprFrag + elseExpression.getText() + ";";
    PsiExpressionStatement elseStmt = (PsiExpressionStatement) factory.createStatementFromText(elseStr, null);
    PsiBlockStatement elseBranch = (PsiBlockStatement) newIfStmt.getElseBranch();
    if (elseBranch == null) {
      return;
    }
    elseBranch.getCodeBlock().getStatements()[0].replace(elseStmt);

    // Replace the entire original statement with the new IF
    originalStatement.replace(newIfStmt);
  }

  /**
   * If this action is applicable, returns the text to be shown in the list of intention actions available.
   */
  @NotNull
  public String getText() {
    return getFamilyName();
  }

  /**
   * Returns text for name of this family of intentions.
   * It is used to externalize "auto-show" state of intentions.
   * It is also the directory name for the descriptions.
   *
   * @return the intention family name.
   */
  @NotNull
  public String getFamilyName() {
    return "SDK: Convert ternary operator to if statement";
  }

}
