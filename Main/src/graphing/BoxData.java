package graphing;

import graphing.enums.AccessModifier;

abstract class BoxData {

   private String name;
   private AccessModifier accessModifier;
   private String dataType;

   public BoxData(String name, AccessModifier accessModifier, String dataType) {
      this.name = name;
      this.accessModifier = accessModifier;
      this.dataType = dataType;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public AccessModifier getAccessModifier() {
      return accessModifier;
   }

   public void setAccessModifier(AccessModifier accessModifier) {
      this.accessModifier = accessModifier;
   }

   public String getDataType() {
      return dataType;
   }

   public void setDataType(String dataType) {
      this.dataType = dataType;
   }

}
