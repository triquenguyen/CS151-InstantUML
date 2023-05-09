package graphing;

import graphing.enums.AccessModifier;

/**
 * A representation of a field in a UML diagram Box.
 */
public class Field extends BoxData {

   private String typeID;

   Field(String name, AccessModifier accessModifier, String dataType,
         String typeID) {
      super(name, accessModifier, dataType);
      this.typeID = typeID;
   }

   String getTypeID() {
      return typeID;
   }

   /**
    * @return    The string representation of a field as it would be seen
    *            in a UMl class diagram.
    */
   @Override
   public String toString() {
      return String.format("%s %s:%s<br>", getAccessModifier().toString(),
              getName(), getDataType());
   }

}
