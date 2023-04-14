package actions;

import com.google.rpc.Help;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.BrowseFolderRunnable;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.ActionCallback;
import com.intellij.openapi.util.ClassExtension;
import com.intellij.ui.StatusPanel;
import org.apache.tools.ant.Project;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class HelloWorldAction extends AnAction {

   /*
   Classes in the API to look into:
   ActionCallback ac = new ActionCallback(); // Might usable
   ClassExtension<Class> ce = new ClassExtension<>("");
   VirtualFileWrapper
   VirtualFilePropertyEvent
    */
   @Override
   public void actionPerformed(@NotNull AnActionEvent e) {
      Messages.showInfoMessage("Hello World", "Info");
      ActionCallback ac = new ActionCallback(); // Might usable
      ClassExtension<Class> ce = new ClassExtension<>("");
      // VirtualFileWrapper, VirtualFilePropertyEvent

   }
}
