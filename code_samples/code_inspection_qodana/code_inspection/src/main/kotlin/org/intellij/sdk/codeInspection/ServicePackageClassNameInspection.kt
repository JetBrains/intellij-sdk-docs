// Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.intellij.sdk.codeInspection

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import org.jetbrains.kotlin.idea.codeinsight.api.classic.inspections.AbstractKotlinInspection
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtVisitorVoid

class ServicePackageClassNameInspection : AbstractKotlinInspection() {
    override fun buildVisitor(
        holder: ProblemsHolder,
        isOnTheFly: Boolean
    ): PsiElementVisitor {
        return object : KtVisitorVoid() {
            override fun visitClass(klass: KtClass) {
                val classNamePsi = klass.nameIdentifier ?: return
                val classFqn = klass.fqName ?: return
                if (klass.packageLastComponent == "service" && !classFqn.asString().endsWith("Service")) {
                    holder.registerProblem(
                        classNamePsi,
                        "Class name in the 'service' package must have a 'Service' suffix",
                        ProblemHighlightType.GENERIC_ERROR_OR_WARNING
                    )
                }
            }
        }
    }

    private val KtClass.packageLastComponent: String
        get() = containingKtFile.packageFqName.shortName().asString()

}
