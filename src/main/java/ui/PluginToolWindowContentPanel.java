package ui;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.ui.SimpleToolWindowPanel;

/**
 * The main plugin Panel that holds the toolbar to add a new UML diagram
 */
public class PluginToolWindowContentPanel extends SimpleToolWindowPanel {

   private final ActionManager actionManager;
   private final ActionToolbar actionToolbar;

   public PluginToolWindowContentPanel() {
      super(true, true);

      // Manager allows components to be added to panel
      actionManager = ActionManager.getInstance();

      actionToolbar = actionManager.createActionToolbar(
              "toolbar",
              (ActionGroup) actionManager.getAction("UI.Toolbar"),
              true);
      setToolbar(actionToolbar.getComponent()); // Adds toolbar to panel

   }

}
