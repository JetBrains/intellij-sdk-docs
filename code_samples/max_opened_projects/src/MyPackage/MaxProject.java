package MyPackage;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.*;
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
    MyCounter CommandCounter = ServiceManager.getService(MyCounter.class);

    if (CommandCounter.IncreaseCounter() == -1) {
      Messages.showMessageDialog(
          "The maximum number of opened projects exceeds " + String.valueOf(CommandCounter.MaxCount) +
          " projects!", "Error", Messages.getErrorIcon());
      ProjectManager PM = ProjectManager.getInstance();
      Project[] AllProjects = PM.getOpenProjects();
      Project project = AllProjects[AllProjects.length - 1];
      PM.closeProject(project);
    }
  }


  public void projectClosed() {
    // called when project is being closed
    MyCounter CommandCounter = ServiceManager.getService(MyCounter.class);
    CommandCounter.DecreaseCounter();
  }
}
