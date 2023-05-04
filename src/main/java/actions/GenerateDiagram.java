package actions;

import com.intellij.moduleDependencies.DependenciesAnalyzeManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.PsiManagerImpl;
import com.intellij.psi.util.PsiClassUtil;
import com.intellij.testFramework.LightVirtualFile;
import com.thoughtworks.qdox.library.ClassLoaderLibrary;
import io.github.classgraph.ClassGraphClassLoader;
import org.apache.tools.ant.taskdefs.Classloader;
import org.apache.tools.ant.taskdefs.Java;
import org.jetbrains.annotations.NotNull;
import org.jf.dexlib2.analysis.ClassPath;
import org.objenesis.instantiator.sun.SunReflectionFactorySerializationInstantiator;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CyclicBarrier;

/**
 * makes the UML diagram
 */
public class GenerateDiagram extends AnAction {

   @Override
   public void actionPerformed(@NotNull AnActionEvent e) {

   }


}
