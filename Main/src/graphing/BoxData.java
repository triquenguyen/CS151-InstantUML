package graphing;

import graphing.enums.AccessModifier;

/**
 * An abstract class for the data within the box.  Is extended by Method and
 * Field classes.
 */
abstract class BoxData {

   private String name;
   private AccessModifier accessModifier;
   private String dataType;

   /**
    * Constructor which sets all of the Box
    *
    * @param name             The name of the data
    * @param accessModifier   The access modifier of the variable
    * @param dataType         The DataType of the Data
    */
   protected BoxData(String name, AccessModifier accessModifier, String dataType) {
      this.name = name;
      this.accessModifier = accessModifier;
      this.dataType = dataType;
   }

   /**
    * @return    The name of the data
    */
   public String getName() {
      return name;
   }

   /**
    * Sets the name of the data
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * @return    The type of access modifier applied to the data.
    */
   public AccessModifier getAccessModifier() {
      return accessModifier;
   }

   /**
    * Sets the access modifier of the data.
    */
   public void setAccessModifier(AccessModifier accessModifier) {
      this.accessModifier = accessModifier;
   }

   /**
    * @return    The datatype of the data.
    */
   public String getDataType() {
      return dataType;
   }

   /**
    * Sets the dataType of the data.
    */
   public void setDataType(String dataType) {
      this.dataType = dataType;
   }

}
