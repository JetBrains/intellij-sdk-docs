// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.utils;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

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
   * Adds the Balloon to the Project's Disposer
   *
   * @param project The Project to host the Balloon.
   * @param title   A short description of what the Balloon conveys
   * @param message Detailed information
   */
  public void showBalloon(@Nullable Project project, @NotNull String title, @NotNull String message) {
    // Ensure the project is open. If not, use the next youngest one to show the balloon
    Project displayProject = findValidProject(project);
    if (displayProject == null) return;

    // Verify the place to put the balloon
    JRootPane pane = getProjectRootPane(displayProject);
    if (pane == null) return;

    // Construct the balloon
    JLabel component = new JLabel("<html>" + message + "</html>");
    final Balloon balloon = JBPopupFactory.getInstance().createBalloonBuilder(component)
            .setShadow(true)
            .setHideOnClickOutside(true)
            .setHideOnAction(false)
            .setFillColor(UIUtil.getControlColor())
            .setTitle(title)
            .setFadeoutTime(6000)
            .createBalloon();

    // Register it for disposal with the project that will display it
    Disposer.register(displayProject, balloon);

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
    if ((validProject == null) || !validProject.isOpen()) {
      // Find the next most-recently opened Project that is still open.
      ProjectManager projectManager = ProjectManager.getInstance();
      Project[] allProjects = projectManager.getOpenProjects();
      validProject = allProjects.length > 0 ? allProjects[allProjects.length - 1] : null;
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
