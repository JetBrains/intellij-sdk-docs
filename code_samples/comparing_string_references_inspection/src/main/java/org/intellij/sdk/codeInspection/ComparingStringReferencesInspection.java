// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.intellij.sdk.codeInspection;

import com.intellij.codeInspection.AbstractBaseJavaLocalInspectionTool;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.siyeh.ig.psiutils.ExpressionUtils.isNullLiteral;

/**
 * Implements an inspection to detect when String references are compared using 'a==b' or 'a!=b'.
 * The quick fix converts these comparisons to 'a.equals(b) or '!a.equals(b)' respectively.
 */
public class ComparingStringReferencesInspection extends AbstractBaseJavaLocalInspectionTool {

  private final ReplaceWithEqualsQuickFix myQuickFix = new ReplaceWithEqualsQuickFix();

  /**
   * This method is overridden to provide a custom visitor
   * that inspects expressions with relational operators '==' and '!='.
   * The visitor must not be recursive and must be thread-safe.
   *
   * @param holder     object for the visitor to register problems found
   * @param isOnTheFly true if inspection was run in non-batch mode
   * @return non-null visitor for this inspection
   * @see JavaElementVisitor
   */
  @NotNull
  @Override
  public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder holder, boolean isOnTheFly) {
    return new JavaElementVisitor() {

      /**
       * Evaluate binary psi expressions to see if they contain relational operators '==' and '!=',
       * AND they are of String type.
       * The evaluation ignores expressions comparing an object to null.
       * IF these criteria are met, register the problem in the ProblemsHolder.
       *
       * @param expression The binary expression to be evaluated.
       */
      @Override
      public void visitBinaryExpression(PsiBinaryExpression expression) {
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
        PsiType type = operand.getType();
        if (!(type instanceof PsiClassType)) {
          return false;
        }
        PsiClass resolvedType = ((PsiClassType) type).resolve();
        if (resolvedType == null) {
          return false;
        }
        return "java.lang.String".equals(resolvedType.getQualifiedName());
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

    /**
     * This method manipulates the PSI tree to replace 'a==b' with 'a.equals(b)' or 'a!=b' with '!a.equals(b)'.
     *
     * @param project    The project that contains the file being edited.
     * @param descriptor A problem found by this inspection.
     */
    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
      PsiBinaryExpression binaryExpression = (PsiBinaryExpression) descriptor.getPsiElement();
      IElementType opSign = binaryExpression.getOperationTokenType();
      PsiExpression lExpr = binaryExpression.getLOperand();
      PsiExpression rExpr = binaryExpression.getROperand();
      if (rExpr == null) {
        return;
      }

      PsiElementFactory factory = JavaPsiFacade.getInstance(project).getElementFactory();
      PsiMethodCallExpression equalsCall =
              (PsiMethodCallExpression) factory.createExpressionFromText("a.equals(b)", null);

      equalsCall.getMethodExpression().getQualifierExpression().replace(lExpr);
      equalsCall.getArgumentList().getExpressions()[0].replace(rExpr);

      PsiExpression result = (PsiExpression) binaryExpression.replace(equalsCall);

      if (opSign == JavaTokenType.NE) {
        PsiPrefixExpression negation = (PsiPrefixExpression) factory.createExpressionFromText("!a", null);
        negation.getOperand().replace(result);
        result.replace(negation);
      }
    }

    @NotNull
    public String getFamilyName() {
      return getName();
    }

  }

}
