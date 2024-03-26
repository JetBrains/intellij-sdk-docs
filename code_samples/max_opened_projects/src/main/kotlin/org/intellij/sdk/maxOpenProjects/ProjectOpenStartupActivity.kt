// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.intellij.sdk.maxOpenProjects

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.intellij.openapi.ui.Messages

/**
 * Invoked on opening a project.
 */
internal class ProjectOpenStartupActivity : ProjectActivity, DumbAware {
  override suspend fun execute(project: Project) {
    val application = ApplicationManager.getApplication()
    val projectCountingService = application.getService(ProjectCountingService::class.java)
    projectCountingService.increaseOpenProjectCount()
    if (projectCountingService.isOpenProjectsLimitExceeded) {
      application.invokeLater {
        Messages.showMessageDialog(
                project,
                "The number of open projects exceeded the limit while opening the '${project.name}' project.",
                "Open Projects Limit Exceeded",
                Messages.getInformationIcon()
        )
      }
    }
  }
}
