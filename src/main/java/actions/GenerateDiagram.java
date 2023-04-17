package actions;

import com.intellij.ide.ui.laf.IntelliJLookAndFeelInfo;
import com.intellij.internal.statistic.eventLog.validator.IntellijSensitiveDataValidator;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.testFramework.JavaPsiTestCase;
import com.intellij.util.PsiIconUtil;
import com.intellij.util.xml.ui.PsiClassPanel;
import org.jetbrains.annotations.NotNull;

public class GenerateDiagram extends AnAction {

   @Override
   public void actionPerformed(@NotNull AnActionEvent e) {

      PsiTreeUtil tree = new PsiTreeUtil();

      PsiClassPanel panel = new PsiClassPanel();



      Messages.showInfoMessage(panel.toString(), "Info");
   }
}
