// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.intellij.sdk.codeInspection;

import com.intellij.codeInspection.AbstractBaseJavaLocalInspectionTool;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTypesUtil;
import org.jetbrains.annotations.NotNull;

/**
 * Implements an inspection to detect when String references are compared using 'a==b' or 'a!=b'.
 * The quick fix converts these comparisons to 'a.equals(b) or '!a.equals(b)' respectively.
 */
final class ComparingStringReferencesInspection extends AbstractBaseJavaLocalInspectionTool {

  private final ReplaceWithEqualsQuickFix myQuickFix = new ReplaceWithEqualsQuickFix();

  /**
   * This method is overridden to provide a custom visitor
   * that inspects expressions with relational operators '==' and '!='.
   * The visitor must not be recursive and must be thread-safe.
   *
   * @param holder     object for the visitor to register problems found
   * @param isOnTheFly true if inspection was run in non-batch mode
   * @return non-null visitor for this inspection
   */
  @NotNull
  @Override
  public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder holder, boolean isOnTheFly) {
    return new JavaElementVisitor() {

      /**
       * Evaluate binary PSI expressions to see if they contain relational operators '==' and '!=',
       * AND they are of String type.
       * The evaluation ignores expressions comparing an object to null.
       * IF these criteria are met, register the problem in the ProblemsHolder.
       *
       * @param expression The binary expression to be evaluated.
       */
      @Override
      public void visitBinaryExpression(@NotNull PsiBinaryExpression expression) {
        super.visitBinaryExpression(expression);
        IElementType opSign = expression.getOperationTokenType();
        if (opSign == JavaTokenType.EQEQ || opSign == JavaTokenType.NE) {
          // The binary expression is the correct type for this inspection
          PsiExpression lOperand = expression.getLOperand();
          PsiExpression rOperand = expression.getROperand();
          if (rOperand == null || isNullLiteral(lOperand) || isNullLiteral(rOperand)) {
            return;
          }
          // Nothing is compared to null, now check the types being compared
          if (isStringType(lOperand) || isStringType(rOperand)) {
            // Identified an expression with potential problems, register problem with the quick fix object
            holder.registerProblem(expression,
                InspectionBundle.message("inspection.comparing.string.references.problem.descriptor"),
                myQuickFix);
          }
        }
      }

      private boolean isStringType(PsiExpression operand) {
        PsiClass psiClass = PsiTypesUtil.getPsiClass(operand.getType());
        if (psiClass == null) {
          return false;
        }

        return "java.lang.String".equals(psiClass.getQualifiedName());
      }

      private static boolean isNullLiteral(PsiExpression expression) {
        return expression instanceof PsiLiteralExpression &&
            ((PsiLiteralExpression) expression).getValue() == null;
      }
    };
  }

  /**
   * This class provides a solution to inspection problem expressions by manipulating the PSI tree to use 'a.equals(b)'
   * instead of '==' or '!='.
   */
  private static class ReplaceWithEqualsQuickFix implements LocalQuickFix {

    /**
     * Returns a partially localized string for the quick fix intention.
     * Used by the test code for this plugin.
     *
     * @return Quick fix short name.
     */
    @NotNull
    @Override
    public String getName() {
      return InspectionBundle.message("inspection.comparing.string.references.use.quickfix");
    }

    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
      // binaryExpression holds a PSI expression of the form "x == y",
      // which needs to be replaced with "x.equals(y)"
      PsiBinaryExpression binaryExpression = (PsiBinaryExpression) descriptor.getPsiElement();
      IElementType opSign = binaryExpression.getOperationTokenType();
      PsiExpression lExpr = binaryExpression.getLOperand();
      PsiExpression rExpr = binaryExpression.getROperand();
      if (rExpr == null) {
        return;
      }
      // Step 1: Create a replacement fragment from text, with "a" and "b" as placeholders
      PsiElementFactory factory = JavaPsiFacade.getInstance(project).getElementFactory();
      PsiMethodCallExpression equalsCall =
          (PsiMethodCallExpression) factory.createExpressionFromText("a.equals(b)", null);
      // Step 2: Replace "a" and "b" with elements from the original file
      PsiExpression qualifierExpression =
          equalsCall.getMethodExpression().getQualifierExpression();
      assert qualifierExpression != null;
      qualifierExpression.replace(lExpr);
      equalsCall.getArgumentList().getExpressions()[0].replace(rExpr);
      // Step 3: Replace a larger element in the original file with the replacement tree
      PsiExpression result = (PsiExpression) binaryExpression.replace(equalsCall);

      // Steps 4-6 needed only for negation
      if (opSign == JavaTokenType.NE) {
        // Step 4: Create a replacement fragment with negation and negated operand placeholder
        PsiPrefixExpression negation =
            (PsiPrefixExpression) factory.createExpressionFromText("!a", null);
        PsiExpression operand = negation.getOperand();
        assert operand != null;
        // Step 5: Replace operand placeholder with the actual expression
        operand.replace(result);
        // Step 6: Replace the result with the negated expression
        result.replace(negation);
      }
    }

    @NotNull
    public String getFamilyName() {
      return getName();
    }

  }

}
