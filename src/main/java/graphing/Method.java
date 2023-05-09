package graphing;

import graphing.enums.AccessModifier;

/**
 * A representation of a method as would be seen in a UML class diagram Box.
 */
public class Method extends BoxData {

   Method(String name, AccessModifier accessModifier, String dataType) {
      super(name, accessModifier, dataType);
   }

   /**
    * @return    The String representation of a method as it would be seen in a
    *            UML class diagram.
    */
   @Override
   public String toString() {
      return String.format("%s %s:%s()<br>", getAccessModifier().toString(),
              getName(), getDataType());
   }

}
