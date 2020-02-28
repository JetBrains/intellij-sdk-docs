// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.maxOpenPrj;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Listener to detect project open and close.
 * Depends on the org.intellij.sdk.maxOpenPrj.ProjectCountingService
 */
public class ProjectOpenCloseListener implements ProjectManagerListener, Disposable {

  /**
   * Invoked on project open.
   *
   * @param project opening project
   */
  @Override
  public void projectOpened(@NotNull Project project) {
    // Get the counting service
    ProjectCountingService projectCountingService = ServiceManager.getService(ProjectCountingService.class);
    // Increment the project count
    projectCountingService.incrProjectCount();
    // See if the total # of projects violates the limit.
    if (projectCountingService.projectLimitExceeded()) {
      System.out.println("\n\nOpen limit transition called for: " + project.toString());
      // Transitioned to outside the limit
      showBalloon("projectOpened", project, "Opening Project \"" + project.getName() + "\"", "The number of open projects exceeds the SDK plugin max_opened_projects limit.");
    }
  }

  /**
   * Invoked on project close.
   *
   * @param project closing project
   */
  @Override
  public void projectClosed(@NotNull Project project) {
    // Get the counting service
    ProjectCountingService projectCountingService = ServiceManager.getService(ProjectCountingService.class);
    // Was the count above the limit?
    boolean previouslyOverCount = projectCountingService.projectLimitExceeded();
    // Decrement the count because a project just closed
    projectCountingService.decrProjectCount();
    // See if the total # of projects no longer violates the limit.
    if (!projectCountingService.projectLimitExceeded() && previouslyOverCount) {
      System.out.println("\n\nCLOSED limit transition called for: " + project.toString());
      // Transitioned to within the limit.
      showBalloon("projectClosed", project, "\"" + project.getName() + "\" Has Been Closed", "The number of open projects is below the SDK plugin max_opened_projects limit.");
    }
  }

  /*
  CLOSED limit transition called for: Project (name=testKotlin, containerState=DISPOSE_IN_PROGRESS, componentStore=/Users/jhake/Documents/source/scratch/testKotlin)
  From: projectClosed - allProjects[2] = Project (name=conditional_operator_test, containerState=ACTIVE, componentStore=/Users/jhake/Documents/source/scratch/conditional_operator_test) , Open = true
  From: projectClosed - allProjects[1] = Project (name=foobar, containerState=ACTIVE, componentStore=/Users/jhake/Documents/source/scratch/foobar) , Open = true
  From: projectClosed - allProjects[0] = Project (name=SimpleTest, containerState=ACTIVE, componentStore=/Users/jhake/Documents/source/scratch/SimpleTest) , Open = true
   */
  private void showBalloon(String caller, Project project, String title, String message) {
    // Ensure the project is open. If not, use the next youngest one in the project list
    ProjectManager projectManager = ProjectManager.getInstance();
    Project[] allProjects = projectManager.getOpenProjects();
    Project validProject = allProjects[allProjects.length - 1];
    if (validProject == null) {
      return;
    }
    System.out.println(String.format("From: %s - validProject = %s", caller, validProject.toString()));

    // Verify the place to put the balloon
    final WindowManager manager = WindowManager.getInstance();
    final JFrame frame = manager.getFrame(validProject);
    JRootPane pane = frame.getRootPane();
    if (pane == null) {
      return;
    }

    // Construct and show the balloon
    JLabel component = new JLabel(message);
    final Balloon balloon = JBPopupFactory.getInstance().createBalloonBuilder(component)
            .setShadow(true)
            .setAnimationCycle(200)               // Was 0
            .setHideOnClickOutside(true)
            .setHideOnAction(false)
            .setFillColor(UIUtil.getControlColor())
            .setTitle(title)                          // Added
            .setFadeoutTime(5000)                     // Added
            .createBalloon();
    Disposer.register(validProject, balloon);
    balloon.showInCenterOf(pane);

//    final Balloon.Position position = QuickEditAction.getBalloonPosition(editor);
//    RelativePoint point = JBPopupFactory.getInstance().guessBestPopupLocation(editor);
//    if (position == Balloon.Position.above) {
//      final Point p = point.getPoint();
//      point = new RelativePoint(point.getComponent(), new Point(p.x, p.y - editor.getLineHeight()));
//    }

  }

  /**
   * Usually not invoked directly, see Disposable class javadoc.
   */
  @Override
  public void dispose() {
    // noop
  }
}
