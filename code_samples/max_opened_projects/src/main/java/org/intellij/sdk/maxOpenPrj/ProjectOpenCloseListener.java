// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.maxOpenPrj;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.CommonShortcuts;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import com.intellij.openapi.ui.Messages;
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
      // Transitioned to outside the limit
//      Messages.showMessageDialog(
//              "The number of open projects exceeds the SDK plugin max_opened_projects limit.",
//              "Opening Project \"" + project.getName() + "\"",
//              Messages.getErrorIcon());
      showBalloon(project, "Opening Project \"" + project.getName() + "\"", "The number of open projects exceeds the SDK plugin max_opened_projects limit.");
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
      // Transitioned from above the limit to within the limit.
//      Messages.showMessageDialog(
//              "The number of open projects does not exceed the SDK plugin max_opened_projects limit.",
//              "\"" + project.getName() + "\" Has Been Closed",
//              Messages.getErrorIcon());
      showBalloon(project, "\"" + project.getName() + "\" Has Been Closed", "The number of open projects is below the SDK plugin max_opened_projects limit.");
    }
  }


  private void showBalloon(Project project, String title, String message) {
    JLabel component = new JLabel(message);
    final Balloon balloon = JBPopupFactory.getInstance().createBalloonBuilder(component)
            .setShadow(true)
            .setAnimationCycle(200)               // Was 0
            .setHideOnClickOutside(true)
//            .setHideOnKeyOutside(true)
            .setHideOnAction(true)                    // was false
            .setFillColor(UIUtil.getControlColor())
            .setTitle(title)                          // Added
            .setFadeoutTime(5000)                     // Added
            .createBalloon();
//    DumbAwareAction.create(e -> balloon.hide())
//            .registerCustomShortcutSet(CommonShortcuts.ESCAPE, component);
    Disposer.register(project, balloon);
//    final Balloon.Position position = QuickEditAction.getBalloonPosition(editor);
//    RelativePoint point = JBPopupFactory.getInstance().guessBestPopupLocation(editor);
//    if (position == Balloon.Position.above) {
//      final Point p = point.getPoint();
//      point = new RelativePoint(point.getComponent(), new Point(p.x, p.y - editor.getLineHeight()));
//    }
    ProjectManager PM = ProjectManager.getInstance();
    Project[] AllProjects = PM.getOpenProjects();
    Project prevProject = AllProjects[AllProjects.length - 1];
    final WindowManager manager = WindowManager.getInstance();
    final JFrame frame = prevProject != null ? manager.getFrame(prevProject) : manager.findVisibleFrame();
    JRootPane pane = frame.getRootPane();
    balloon.showInCenterOf(pane);
  }

  /**
   * Usually not invoked directly, see Disposable class javadoc.
   */
  @Override
  public void dispose() {
    // noop
  }
}
