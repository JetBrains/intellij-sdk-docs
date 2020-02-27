// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.maxOpenPrj;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Chursin
 * Date: Aug 13, 2010
 * Time: 3:50:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class MaxProject implements ProjectComponent {
  public MaxProject(Project project) {
  }

  public void initComponent() {
    // TODO: insert component initialization logic here
  }

  public void disposeComponent() {
    // TODO: insert component disposal logic here
  }

  @NotNull
  public String getComponentName() {
    return "MaxProject";
  }

  public void projectOpened() {
    // called when project is opened
    MyCounter commandCounter = ServiceManager.getService(MyCounter.class);

    if (commandCounter.increaseCounter() == -1) {
      final String errorMessage = String.format(
              "The maximum number of opened projects exceeds %d projects!", commandCounter.maxCount);
      Messages.showMessageDialog(errorMessage, "Error", Messages.getErrorIcon());
      ProjectManager pm = ProjectManager.getInstance();
      Project[] allProjects = pm.getOpenProjects();
      Project project = allProjects[allProjects.length - 1];
      pm.closeProject(project);
    }
  }


  public void projectClosed() {
    // called when project is being closed
    MyCounter commandCounter = ServiceManager.getService(MyCounter.class);
    commandCounter.decreaseCounter();
  }
}
