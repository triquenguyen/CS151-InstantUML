package ui;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTabbedPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.tabs.TabInfo;
import org.apache.tools.ant.Project;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The main plugin Panel that holds the toolbar to add a new UML diagram
 */
public class PluginToolWindowContentPanel extends SimpleToolWindowPanel {

   private final ActionManager actionManager;
   private final ActionToolbar actionToolbar;
   private static ArrayList<UMLDiagramPanel> diagramPanels = new ArrayList<>();
   private static JBTabbedPane tabs = new JBTabbedPane();


   public PluginToolWindowContentPanel() {
      super(true, true);

      // Manager allows components to be added to panel
      actionManager = ActionManager.getInstance();

      actionToolbar = actionManager.createActionToolbar(
              "toolbar",
              (ActionGroup) actionManager.getAction("UI.Toolbar"),
              true);
      setToolbar(actionToolbar.getComponent()); // Adds toolbar to panel

      setContent(tabs);
      setVisible(true);
   }

   public static void addDiagramPanel(UMLDiagramPanel diagramPanel) {
      diagramPanels.add(diagramPanel);
      JScrollPane scrollPane = new JScrollPane(diagramPanel);
      tabs.addTab("tab", scrollPane);
   }

}
