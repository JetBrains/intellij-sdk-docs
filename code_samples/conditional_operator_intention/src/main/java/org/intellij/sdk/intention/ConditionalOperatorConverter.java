// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.intention;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.*;

/**
 * Implements an intention action to replace a ternary statement with if-then-else
 */
@NonNls
public class ConditionalOperatorConverter extends PsiElementBaseIntentionAction implements IntentionAction {

  /**
   * If this action is applicable, returns the text to be shown in the list of
   * intention actions available.
   */
  @NotNull
  public String getText() {
    return "SDK Convert ternary operator to if statement";
  }


  /**
   * Returns text for name of this family of intentions. It is used to externalize
   * "auto-show" state of intentions.
   * It is also the directory name for the descriptions.
   *
   * @return  the intention family name.
   */
  @NotNull
  public String getFamilyName() {
    return "ConditionalOperatorIntention";
  }


  /**
   * Checks whether this intention is available at the caret offset in file - the caret
   * must sit just before a "?" character in a ternary statement. If this condition is met,
   * this intention's entry is shown in the available intentions list.
   *
   * Note: this method must do its checks quickly and return.
   *
   * @param project a reference to the Project object being edited.
   * @param editor  a reference to the object editing the project source
   * @param element a reference to the PSI element currently under the caret
   * @return
   * <ul>
   * <li> true if the caret is in a literal string element, so this functionality
   * should be added to the intention menu.</li>
   * <li> false for all other types of caret positions</li>
   * </ul>
   */
  public boolean isAvailable(@NotNull Project project, Editor editor, @Nullable PsiElement element) {

    // Quick sanity check
    if (element == null) return false;

    // Is this a token of type representing a "?" character?
    if (element instanceof PsiJavaToken) {
      final PsiJavaToken token = (PsiJavaToken) element;
      if (token.getTokenType() != JavaTokenType.QUEST) return false;
      // Is this token part of a fully formed conditional, i.e. a ternary?
      if (token.getParent() instanceof PsiConditionalExpression) {
        final PsiConditionalExpression conditionalExpression = (PsiConditionalExpression) token.getParent();
        return conditionalExpression.getThenExpression() != null
                && conditionalExpression.getElseExpression() != null;// Satisfies all criteria; call back invoke method
      }
      return false;
    }
    return false;
  }

  /**
   * Modifies the Psi to change a ternary expression to an if-then-else statement.
   * If the ternary is part of a declaration, the declaration is separated and
   * moved above the if-then-else statement. Called when user selects this intention action
   * from the available intentions list.
   *
   *   @param  project   a reference to the Project object being edited.
   *   @param  editor    a reference to the object editing the project source
   *   @param  element   a reference to the PSI element currently under the caret
   *   @throws IncorrectOperationException Thrown by underlying (Psi model) write action context
   *   when manipulation of the psi tree fails.
   *   @see ConditionalOperatorConverter#startInWriteAction()
   */
  public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement element) throws IncorrectOperationException {

    // Get the factory for making new PsiElements, and the code style manager to format new statements
    final PsiElementFactory factory = JavaPsiFacade.getInstance(project).getElementFactory();
    final CodeStyleManager codeStylist = CodeStyleManager.getInstance(project);

    // Get the parent of the "?" element in the ternary statement to find the conditional expression that contains it
    PsiConditionalExpression conditionalExpression = PsiTreeUtil.getParentOfType(element, PsiConditionalExpression.class, false);
    // Verify the conditional expression exists and has two outcomes in the ternary statement.
    if (conditionalExpression == null) return;
    if (conditionalExpression.getThenExpression() == null || conditionalExpression.getElseExpression() == null) return;

    // Keep searching up the Psi Tree in case the ternary is part of a FOR statement.
    PsiElement originalStatement = PsiTreeUtil.getParentOfType(conditionalExpression, PsiStatement.class, false);
    while (originalStatement instanceof PsiForStatement) {
      originalStatement = PsiTreeUtil.getParentOfType(originalStatement, PsiStatement.class, true);
    }
    if (originalStatement == null) return;

    // If the original statement is a declaration based on a ternary operator,
    // split the declaration and assignment
    if (originalStatement instanceof PsiDeclarationStatement) {
      final PsiDeclarationStatement declaration = (PsiDeclarationStatement) originalStatement;

      // Find the local variable within the declaration statement
      final PsiElement[] declaredElements = declaration.getDeclaredElements();
      PsiLocalVariable variable = null;
      for (PsiElement declaredElement : declaredElements) {
        if (declaredElement instanceof PsiLocalVariable &&
            PsiTreeUtil.isAncestor(declaredElement, conditionalExpression, true)) {
          variable = (PsiLocalVariable) declaredElement;
          break;
        }
      }
      if (variable == null) return;

      // Ensure that the variable declaration is not combined with other declarations, and add a mark
      variable.normalizeDeclaration();
      final Object marker = new Object();
      PsiTreeUtil.mark(conditionalExpression, marker);

      // Create a new expression to declare the local variable
      PsiExpressionStatement statement =
          (PsiExpressionStatement) factory.createStatementFromText(variable.getName() + " = 0;", null);
      statement = (PsiExpressionStatement) codeStylist.reformat(statement);

      // Replace initializer with the ternary expression, making an assignment statement using the ternary
      ((PsiAssignmentExpression) statement.getExpression()).getRExpression().replace(variable.getInitializer());

      // Remove the initializer portion of the local variable statement,
      // making it a declaration statement with no initializer
      variable.getInitializer().delete();

      // Get the grandparent of the local var declaration, and add the new declaration just beneath it
      final PsiElement variableParent = variable.getParent();
      originalStatement = variableParent.getParent().addAfter(statement, variableParent);
      conditionalExpression = (PsiConditionalExpression) PsiTreeUtil.releaseMark(originalStatement, marker);
    }

    // Create an IF statement from a string with placeholder elements.
    // This will replace the ternary statement
    PsiIfStatement newIfStmt = (PsiIfStatement) factory.createStatementFromText("if (true) {a=b;} else {c=d;}",null);
    newIfStmt = (PsiIfStatement) codeStylist.reformat(newIfStmt);

    // Replace the conditional expression with the one from the original ternary expression
    final PsiReferenceExpression condition = (PsiReferenceExpression) conditionalExpression.getCondition().copy();
    newIfStmt.getCondition().replace(condition);

    // Begin building the assignment string for the THEN and ELSE clauses using the
    // parent of the ternary conditional expression
    PsiAssignmentExpression assignmentExpression = PsiTreeUtil.getParentOfType(conditionalExpression, PsiAssignmentExpression.class, false);
    // Get the contents of the assignment expression up to the start of the ternary expression
    String exprFrag = assignmentExpression.getLExpression().getText() + assignmentExpression.getOperationSign().getText() ;

    // Build the THEN statement string for the new IF statement,
    // make a PsiExpressionStatement from the string, and switch the placeholder
    String thenStr = exprFrag + conditionalExpression.getThenExpression().getText() + ";" ;
    PsiExpressionStatement thenStmt = (PsiExpressionStatement) factory.createStatementFromText(thenStr, null);
    ( (PsiBlockStatement) newIfStmt.getThenBranch() ).getCodeBlock().getStatements()[0].replace(thenStmt);

    // Build the ELSE statement string for the new IF statement,
    // make a PsiExpressionStatement from the string, and switch the placeholder
    String elseStr = exprFrag + conditionalExpression.getElseExpression().getText() + ";" ;
    PsiExpressionStatement elseStmt = (PsiExpressionStatement) factory.createStatementFromText(elseStr, null);
    ( (PsiBlockStatement) newIfStmt.getElseBranch() ).getCodeBlock().getStatements()[0].replace(elseStmt);

    // Replace the entire original statement with the new IF
    newIfStmt = (PsiIfStatement) originalStatement.replace(newIfStmt);

  }

  /**
   * Indicates this intention action expects the Psi framework to provide the write action
   * context for any changes.
   *
   * @return <ul>
   * <li> true if the intention requires a write action context to be provided</li>
   * <li> false if this intention action will start a write action</li>
   * </ul>
   */
  public boolean startInWriteAction() {return true;}


}

