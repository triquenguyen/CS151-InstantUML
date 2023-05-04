package ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

/**
 * The main class that deals with the panel and tool window of the plugin.
 * Can be considered to be the control
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
