package graphing;

import graphing.enums.AccessModifier;

/**
 * A representation of a field in a UML diagram Box.
 */
public class Field extends BoxData {

   Field(String name, AccessModifier accessModifier, String dataType) {
      super(name, accessModifier, dataType);
   }

   /**
    * @return    The string representation of a field as it would be seen
    *            in a UMl class diagram.
    */
   @Override
   public String toString() {
      return String.format("%s %s: %s", getAccessModifier().toString(),
              getName(), getDataType());
   }

}
