package graphing;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import graphing.enums.AccessModifier;
import graphing.enums.BoxType;
import graphing.enums.ConnectorType;
import ui.UMLDiagramPanel;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.lang.reflect.*;

/**
 * Stores data from Java classes.  Uses javap command to collect data from the
 * "out" folder in IntelliJ.  Holds the data in an outgoing graphical
 *  representation.
 */
public class UMLMap {

   private HashMap<String, Box> map; // Outgoing map


   public UMLMap(PsiJavaFileImpl[] classes) {
      map = new HashMap<>();
      populateMap(classes);
      new UMLDiagramPanel(this);
   }

   /**
    * Populates UMLMap with data from the file.
    */
   private void populateMap(PsiJavaFileImpl[] classes) {
      addBoxes(classes); // Add boxes to diagram
      addConnections();  // Add other Connectors
   }

   private void addConnections() {

      for (Map.Entry<String, Box> entry : map.entrySet()) {

         Box current = entry.getValue();

         // Add generalization
         for (String className : current.getPotentialConnections())
            if (map.containsKey(className)) {
               Box toBox = map.get(className);
               toBox.incrementInDegree();
               current.addConnection(toBox,
                       new Connector(ConnectorType.GENERALIZATION));
            }

         // Add association
         for (Field field : current.getFields())
            if (map.containsKey(field.getDataType())) {
               Box toBox = map.get(field.getDataType());
               toBox.incrementInDegree();
               current.addConnection(toBox,
                       new Connector(ConnectorType.ASSOCIATION));
            }

      }
   }

   private void addBoxes(PsiJavaFileImpl[] classes) {
      for (PsiJavaFileImpl classFile : classes)
         addClasses(classFile.getClasses());
   }

   private void addClasses(PsiClass[] psiClasses) {

      if (psiClasses == null)
         return;

      for (PsiClass clazz : psiClasses) {
         addClass(clazz);
         addClasses(clazz.getInnerClasses());
      }
   }

   private void addClass(PsiClass clazz) {

      Box box = new Box();
      box.setName(clazz.getName());

      // Check for nesting
      if (clazz.getContainingClass() != null) {
         box.addConnection(map.get(clazz.getContainingClass().getName()),
                 new Connector(ConnectorType.NESTED));
      }

      // set BoxType
      setBoxType(clazz, box);

      // set fields
      for (PsiField psiField : clazz.getFields()) {
         Field field = new Field(psiField.getName(),
                 findAccessModifier(psiField),
                 psiField.getType().getPresentableText());
         box.addField(field);
      }

      // set methods
      for (PsiMethod psiMethod : clazz.getMethods()) {
         if (psiMethod.isConstructor())
            continue;
         Method method = new Method(psiMethod.getName(),
                 findAccessModifier(psiMethod),
                 psiMethod.getReturnType().getPresentableText());
         box.addMethod(method);
      }

      // Check for potential connections
      for (PsiClassType type : clazz.getSuperTypes())
         box.addPotentialConnection(type.getClassName());

      map.put(box.getName(), box);
   }


   private AccessModifier findAccessModifier(PsiMember field) {
      if (field.getModifierList().hasModifierProperty("public"))
         return AccessModifier.PUBLIC;
      else if (field.getModifierList().hasModifierProperty("default"))
         return AccessModifier.DEFAULT;
      else if (field.getModifierList().hasModifierProperty("private"))
         return AccessModifier.PRIVATE;
      else
         return AccessModifier.PROTECTED;
   }

   private void setBoxType(PsiClass clazz, Box box) {
      if (clazz.isInterface())
         box.setBoxType(BoxType.INTERFACE);
      else if (clazz.isEnum())
         box.setBoxType(BoxType.ENUMERATION);
      else if (clazz.hasModifierProperty("abstract"))
         box.setBoxType(BoxType.ABSTRACT);
      else
         box.setBoxType(BoxType.CLASS);
   }

   /**
    * @return   The Entry set for the internal map.
    */
   public Set<Map.Entry<String, Box>> getEntrySet() {
      return map.entrySet();
   }
}
