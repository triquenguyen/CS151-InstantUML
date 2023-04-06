package graphing.enums;

public enum BoxType {

   CLASS(""), INTERFACE("<<Interface>>"), ENUMERATION("<<Enumeration>>");

   private String string;

   BoxType(String string) {
      this.string = string;
   }

   @Override
   public String toString() {
      return string;
   }

}
