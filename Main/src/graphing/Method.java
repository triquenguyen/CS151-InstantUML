package graphing;

import graphing.enums.AccessModifier;

public class Method extends BoxData {

   Method(String name, AccessModifier accessModifier, String dataType) {
      super(name, accessModifier, dataType);
   }

   @Override
   public String toString() {
      return String.format("%s %s: %s()", getAccessModifier().toString(),
              getName(), getDataType());
   }

}
