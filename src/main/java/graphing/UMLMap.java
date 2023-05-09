package graphing;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import graphing.enums.AccessModifier;
import graphing.enums.BoxType;
import graphing.enums.ConnectorType;
import ui.UMLDiagramPanel;

import java.util.*;

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

         // Add Nesting
         for (Box.Edge edge : current.getAdjList())
            edge.addNesting(map);

         // Check for generalization
         for (Iterator<Box.Edge> itr = current.getAdjList().iterator(); itr.hasNext(); ) {
            Box.Edge edge = itr.next();
            if (!edge.confirmGeneralization(map))
               itr.remove(); // Not in diagram
            else
               edge.getToBox().incrementInDegree(); // indeg++
         }

         // Add association
         for (Field field : current.getFields()) {
            String fieldID = field.getTypeID();
            if (map.containsKey(fieldID)
                    && !fieldID.equals(current.getDataTypeID())) {

               Box toBox = map.get(fieldID);
               toBox.incrementInDegree();
               current.addConnection(toBox,
                       new Connector(ConnectorType.ASSOCIATION));
            }
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
      box.setDataTypeID(clazz.getQualifiedName());

      // Check for nesting
      if (clazz.getContainingClass() != null) {
         box.addPotentialConnection(clazz.getContainingClass()
                 .getQualifiedName(), new Connector(ConnectorType.NESTED));
      }

      // set BoxType
      setBoxType(clazz, box);

      // set fields
      for (PsiField psiField : clazz.getFields()) {
         Field field = new Field(psiField.getName(),
                 findAccessModifier(psiField),
                 psiField.getType().getPresentableText(),
                 psiField.getType().getCanonicalText());
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

      // Check for potential generalization
      for (PsiClass type : clazz.getSupers())
         box.addPotentialConnection(type.getQualifiedName(),
                 new Connector(ConnectorType.GENERALIZATION));

      map.put(box.getDataTypeID(), box);
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

   public List<Box> boxList() {

      List<Box> list = new ArrayList<>();

      for (Map.Entry<String, Box> entry : map.entrySet())
         list.add(entry.getValue());

      return list;
   }

}
