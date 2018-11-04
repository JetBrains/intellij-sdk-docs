package myToolWindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.*;
import com.intellij.ui.content.*;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Chursin
 * Date: Aug 25, 2010
 * Time: 2:09:00 PM
 */
public class MyToolWindowFactory implements ToolWindowFactory {
  // Create the tool window content.
  public void createToolWindowContent(Project project, ToolWindow toolWindow) {
    MyToolWindow myToolWindow = new MyToolWindow(toolWindow);
    ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
    Content content = contentFactory.createContent(myToolWindow.getContent(), "", false);
    toolWindow.getContentManager().addContent(content);
  }
}
