package graphing;

import graphing.enums.AccessModifier;

public class Field extends BoxData {

   Field(String name, AccessModifier accessModifier, String dataType) {
      super(name, accessModifier, dataType);
   }

   @Override
   public String toString() {
      return String.format("%s %s: %s", getAccessModifier().toString(),
              getName(), getDataType());
   }

}
