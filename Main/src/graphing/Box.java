package graphing;

import graphing.enums.AccessModifier;
import graphing.enums.BoxType;

import java.util.List;

public class Box {

   private AccessModifier accessModifier;
   private BoxType boxType;
   private boolean isAbstract;
   private List<Field> fields;
   private List<Method> methods;

   public Box(AccessModifier accessModifier, BoxType boxType, boolean isAbstract,
              List<Field> fields, List<Method> methods) {
      this.accessModifier = accessModifier;
      this.boxType = boxType;
      this.isAbstract = isAbstract;
      this.fields = fields;
      this.methods = methods;
   }

   public AccessModifier getAccessModifier() {
      return accessModifier;
   }

   public void setAccessModifier(AccessModifier accessModifier) {
      this.accessModifier = accessModifier;
   }

   public BoxType getBoxType() {
      return boxType;
   }

   public void setBoxType(BoxType boxType) {
      this.boxType = boxType;
   }

   public boolean isAbstract() {
      return isAbstract;
   }

   public void setAbstract(boolean anAbstract) {
      isAbstract = anAbstract;
   }

   public List<Field> getFields() {
      return fields;
   }

   public void setFields(List<Field> fields) {
      this.fields = fields;
   }

   public List<Method> getMethods() {
      return methods;
   }

   public void setMethods(List<Method> methods) {
      this.methods = methods;
   }
}
