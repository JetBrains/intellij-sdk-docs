package com.github.novotnyr.mincssrel.shared

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.ui.Messages

internal class SharedAction : DumbAwareAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val message = project.service<SharedMessageService>().getMessage()
        Messages.showInfoMessage(message, "MinCSSRel - Shared Action")
    }
}