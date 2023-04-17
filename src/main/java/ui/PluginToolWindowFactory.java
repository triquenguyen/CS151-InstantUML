package ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Referenced from: https://github.com/alessandro-caldonazzi/UMLGenerator/blob/master/src/com/jakutenshi/projects/umlplugin/ui/UMLPluginToolWindowFactory.java
 */
public class PluginToolWindowFactory implements ToolWindowFactory {

   private ToolWindow pluginToolWindow;
   private PluginToolWindowContentPanel panel;

   @Override
   public void createToolWindowContent(@NotNull Project project,
                                       @NotNull ToolWindow toolWindow) {
      panel = new PluginToolWindowContentPanel();
      pluginToolWindow = toolWindow;
      ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
      Content content = contentFactory.createContent(panel, "", true);
      toolWindow.getContentManager().addContent(content);

   }
}
