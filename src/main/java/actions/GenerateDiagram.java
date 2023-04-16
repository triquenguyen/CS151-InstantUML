package actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import com.intellij.util.xml.ui.PsiClassPanel;
import org.jetbrains.annotations.NotNull;

public class GenerateDiagram extends AnAction {

   @Override
   public void actionPerformed(@NotNull AnActionEvent e) {
      Messages.showInfoMessage("Hello World", "Info");
   }
}
