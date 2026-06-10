package com.github.novotnyr.mincssrel.css

import com.intellij.lang.css.CSSLanguage
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.EDT
import com.intellij.openapi.application.readAction
import com.intellij.openapi.progress.currentThreadCoroutineScope
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.css.CssFile
import com.intellij.psi.css.CssFileType
import com.intellij.util.concurrency.annotations.RequiresReadLock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.intellij.lang.annotations.Language

@Language("CSS")
private const val SAMPLE_STYLESHEET = """
p {
  margin-top: 100px;
  margin-bottom: 100px;
  margin-right: 150px;
  margin-left: 80px;
}

body {
  font-family: sans-serif;
}

h1 {
  font-size: 2.5em;
}    
"""

class CssAction : DumbAwareAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        currentThreadCoroutineScope().launch {
            val selectorNames = readAction {
                project.createSampleCssPsiFile()?.getSelectorNames() ?: emptyList()
            }
            val selectorsMessage = selectorNames.joinToString(", ")
            withContext(Dispatchers.EDT) {
                // As of 2026.1, showInfoMessage uses read-write lock.
                // Such locks are prohibited on Dispatchers.UI.
                Messages.showInfoMessage(selectorsMessage, "CSS Selector List")
            }
        }
    }

    private fun Project.createSampleCssPsiFile(): CssFile? {
        val psiFile = PsiFileFactory
            .getInstance(this)
            .createFileFromText(CSSLanguage.INSTANCE, SAMPLE_STYLESHEET)
        return psiFile as? CssFile
    }

    @RequiresReadLock
    private fun CssFile.getSelectorNames() = stylesheet.rulesetList.rulesets.flatMap {
        it.selectors.toList()
    }.map {
        it.presentableText
    }
}