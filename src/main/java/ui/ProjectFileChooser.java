package ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import com.intellij.ui.CheckboxTree;
import com.intellij.ui.CheckedTreeNode;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.ui.TreeSpeedSearch;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.util.PlatformIcons;
import com.intellij.util.ui.JBUI;
import graphing.UMLMap;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import java.awt.*;
import java.util.HashMap;

/**
 * Generates a CheckBox tree in which allows the user to select which project
 * .java files are wanted in the UML diagram.
 */
public class ProjectFileChooser extends DialogWrapper {

   private Project project;
   private PsiManager manager;
   private CheckboxTree tree;
   private HashMap<String, CheckedTreeNode> packages;
   private CheckedTreeNode root;

   /**
    * Creates the UI including the project file tree
    *
    * @param project    The user's project that they are currently viewing
    */
   public ProjectFileChooser(Project project) {
      super(project, true);

      setSize(600, 600);  // Set size
      setTitle("Choose Files");       // Top text
      this.project = project;         // Set the class's project
      root = new CheckedTreeNode("(Packages)");
      manager = PsiManager.getInstance(project);
      packages = new HashMap<>(); // Initialize to hold package names

      init();  // Initialize the panel, calls createCenterPanel()
      show();  // Project file chooser
   }

   /**
    * Overridden from DialogueWrapper class.  Create the panel that holds the
    * CheckBox tree.
    *
    * @return    The center panel that hold the CheckBox tree
    */
   @Override
   protected @Nullable JComponent createCenterPanel() {

      // Initialize panel and set layout
      JPanel panel = new JPanel();
      panel.setLayout(new BorderLayout());

      // Set tree icons and root
      tree = new CheckboxTree(new CheckboxTree.CheckboxTreeCellRenderer() {
         @Override
         public void customizeRenderer(JTree tree, Object value,
                                       boolean selected, boolean expanded,
                                       boolean leaf, int row, boolean hasFocus) {

            CheckedTreeNode node = (CheckedTreeNode) value;

            if (value instanceof ClassNode) {
               getTextRenderer().setIcon(PlatformIcons.CLASS_ICON);
               getTextRenderer().append(value.toString());
            }
            else if (node.getParent() == root) {
               getTextRenderer().setIcon(PlatformIcons.PACKAGE_ICON);
               getTextRenderer().append(value.toString());
            }
            else {
               getTextRenderer().setIcon(PlatformIcons.FILE_ICON);
               getTextRenderer().append(value.toString());
            }
         }}, root);
      tree.setRootVisible(true);
      // tree.setModel(new DefaultTreeModel(root));

      LoadProjectFiles();

      // Put tree into a scroll pane and add to main JPanel
      JScrollPane scrollPane = ScrollPaneFactory.createScrollPane(tree);
      scrollPane.setPreferredSize(JBUI.size(500, 300));

      panel.add(scrollPane, BorderLayout.CENTER);

      return panel;
   }

   /**
    * First part of the method that loads .java files into checkboxes.  Inputs
    * the children under the main project.
    */
   private void LoadProjectFiles() {
      // Get the first children of files
      LoadProjectFiles(project.getProjectFile().getParent()
              .getParent().getChildren());
   }

   /**
    * The recursive method that grabs the .java files from the project.
    *
    * @param vFiles    The child files of a directory
    */
   private void LoadProjectFiles(VirtualFile[] vFiles) {

      if (vFiles == null) // base case
         return;

      // Add java files and packages
      for (VirtualFile file : vFiles) {

         PsiFile pFile = manager.findFile(file);

         if (pFile instanceof PsiJavaFileImpl) {

            // cast to java file and create new java class node
            PsiJavaFileImpl javaFile = (PsiJavaFileImpl)pFile;
            ClassNode classNode = new ClassNode(javaFile.getName(), javaFile);
            CheckedTreeNode packageNode;

            if (packages.containsKey(javaFile.getPackageName())) {
               // Add package node to HashMap
               packageNode = packages.get(javaFile.getPackageName());
            }
            else
               // Add package node to tree
               packageNode = addPackageNode(javaFile.getPackageName());

            // Add class node to UI tree
            packageNode.add(classNode);
         }
         else
            LoadProjectFiles(file.getChildren()); // recurse into directory

      }
   }

   /**
    * Adds a package node to the tree and HashMap
    *
    * @param name    The name of the package
    * @return        The package node
    */
   private CheckedTreeNode addPackageNode(String name) {
      CheckedTreeNode packageNode = new CheckedTreeNode(name);
      root.add(packageNode);
      packages.put(name, packageNode);
      return packageNode;
   }

   /**
    * Sends the PsiJava files to a UMLMap
    */
   @Override
   protected void doOKAction() {
      // Get ClassNodes
      ClassNode[] nodes = tree.getSelectedNodes(ClassNode.class,
              ClassNode.class::isInstance);

      // Get classes from nodes
      PsiJavaFileImpl[] classes = new PsiJavaFileImpl[nodes.length];
      for (int i = 0; i < nodes.length; i++)
         classes[i] = nodes[i].classFile;
      new UMLMap(classes); // Send the classes to a UMl Map
   }

   /**
    * Holds a CheckedTree node that hold a PsiClass data
    */
   private class ClassNode extends CheckedTreeNode {

      private PsiJavaFileImpl classFile;

      private ClassNode(String name, PsiJavaFileImpl classFile) {
         super(name);
         this.classFile = classFile;
      }
   }
}

