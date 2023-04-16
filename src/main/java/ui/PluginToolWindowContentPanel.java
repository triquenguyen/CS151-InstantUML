package ui;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.impl.ActionMenu;
import com.intellij.openapi.ui.SimpleToolWindowPanel;

import java.util.LinkedList;

public class PluginToolWindowContentPanel extends SimpleToolWindowPanel {

   private final ActionManager actionManager;
   private final ActionToolbar actionToolbar;
   private static LinkedList<UMLDiagramPanel> diagramPanels;

   public PluginToolWindowContentPanel() {
      super(true, true);

      actionManager = ActionManager.getInstance();

      actionToolbar = actionManager.createActionToolbar(
              "toolbar",
              (ActionGroup) actionManager.getAction("UI.Toolbar"),
              true);
      setToolbar(actionToolbar.getComponent());



   }

}
