package actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import ui.ProjectFileChooser;

/**
 * When + button is pressed, calls ProjectFileChooser that allows user to
 * choose the .java files in the UMl diagram
 */
public class ChooseFiles extends AnAction {

   @Override
   public void actionPerformed(@NotNull AnActionEvent e) {
      new ProjectFileChooser(e.getProject());
   }
}

