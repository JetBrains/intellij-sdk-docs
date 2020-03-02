// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.utils;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.wm.IdeFocusManager;
import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.wm.ex.WindowManagerEx;
import com.intellij.openapi.wm.impl.WindowManagerImpl;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * This is just a utility class to help communicate the state of this plugin using Popups
 *
 * @see <a href="https://www.jetbrains.org/intellij/sdk/docs/user_interface_components/popups.html">Popup documentation</a>
 */
public class SdkBalloonHelper {

  public static SdkBalloonHelper getSdkBalloonHelper() {
    return new SdkBalloonHelper();
  }

  /**
   * This method constructs a Balloon-type popup and displays it in the middle of a Project window.
   *
   * @param project The Project to host the Balloon.
   * @param title   A short description of what the Balloon conveys
   * @param message Detailed information to display in HTML format
   */
  public void showBalloon(@Nullable Project project, @NotNull String title, @NotNull String message) {

    // Create a component to hold the message in HTML format
    JLabel component = new JLabel("<html>" + message + "</html>");

    // Construct the balloon using the component
    final Balloon balloon = JBPopupFactory.getInstance().createBalloonBuilder(component)
            .setShadow(true)
            .setHideOnClickOutside(true)
            .setHideOnAction(false)
            .setFillColor(UIUtil.getControlColor())
            .setTitle(title)
            .setFadeoutTime(6000)
            .createBalloon();

    // Ensure the project is open. If not, find a visible Project to display the balloon.
    Project displayProject = findValidProject(project);
    if (displayProject == null) return;

    // Get the UI element to display the balloon
    JRootPane pane = getProjectRootPane(displayProject);
    if (pane == null) return;

    // This is a special case for the message when closing a project.
    // The closed project is no longer visible, so the next...
    // Consequently register it for disposal with the project that will display it
//    Disposer.register(displayProject, balloon);

    // Show the balloon in the middle of the project's window - it will disappear per the animation
    balloon.showInCenterOf(pane);
  }

  /**
   * This function verifies that the provided Project is still open.
   * If the Project is not open (closed, or in some state of disposal,)
   * the function tries to find the next most-recently opened Project.
   *
   * @param dodgyProject The Project to be verified as open
   * @return dodgyProject if it is verified as open,
   * Or the last Project listed in ProjectManager's list of open Projects.
   * Or null if no other Projects are open. (Edge case when IDE is closing.)
   */
  @Nullable
  private Project findValidProject(@Nullable Project dodgyProject) {
    Project validProject = dodgyProject;
    if ((dodgyProject == null) || !dodgyProject.isOpen()) {
      // Find the next most-recently opened Project that is still open.
      IdeFocusManager focusManager = IdeFocusManager.getGlobalInstance();
      IdeFrame ideFrameFromFocusMgr = focusManager.getLastFocusedFrame();
      Project focusMgrProject = ideFrameFromFocusMgr.getProject();
      System.out.println("\n\nFocus Mgr last focused frame -> project: " + focusMgrProject.toString() + "\n");


      final WindowManager windowManager = WindowManagerImpl.getInstance();
      IdeFrame[] allIdeFrames = windowManager.getAllProjectFrames();
      for (int i=0; i<allIdeFrames.length; i++) {
        System.out.println(String.format("Win Mgr IdeFrames[%d] -> project: %s", i, allIdeFrames[i].getProject().toString()));
      }

      String outcome = String.format("\n\nUsing Win Mgr getIdeFrame(null): ");
      String focusProject;
      IdeFrame focusFrame = windowManager.getIdeFrame(null);
      if (focusFrame != null) {
        focusProject = String.format("%s", focusFrame.getProject().toString());
      } else {
        focusProject = String.format("No focused project found");
      }
      System.out.println(outcome + focusProject);

      outcome = String.format("\n\nUsing Win Mgr findFrameFor(null): ");
      focusFrame = ((WindowManagerImpl) windowManager).findFrameFor(null);
      if (focusFrame != null) {
        focusProject = String.format("%s", focusFrame.getProject().toString());
      } else {
        focusProject = String.format("No focused project found");
      }
      System.out.println(outcome + focusProject);

      ProjectManager projectManager = ProjectManager.getInstance();
      Project[] allProjects = projectManager.getOpenProjects();
      validProject = allProjects.length > 0 ? allProjects[allProjects.length - 1] : null;
      System.out.println("\n\nProjMgr allProjects[last]: " + validProject.toString());
    }
    return validProject;
  }

  /**
   * This function gets the JRootPane for an open Project
   *
   * @param project The open Project
   * @return A valid JRootPane for the Project
   * Otherwise null
   */
  @Nullable
  private JRootPane getProjectRootPane(@Nullable Project project) {
    JRootPane projectPane = null;
    if ((project != null) && project.isOpen()) {
      // Get the frame for the project, then the JRootPane
      final WindowManager manager = WindowManager.getInstance();
      final JFrame frame = manager.getFrame(project);
      projectPane = frame != null ? frame.getRootPane() : null;
    }
    return projectPane;
  }

}
