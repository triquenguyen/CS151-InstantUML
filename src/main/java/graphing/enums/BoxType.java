package graphing.enums;

/**
 * The type of box from the options of Class, Interface, or enum.
 */
public enum BoxType {

   CLASS(""), INTERFACE("<<Interface>>"), ENUMERATION("<<Enumeration>>"), ABSTRACT_CLASS("<<Abstract>>");

   private String string;

   BoxType(String string) {
      this.string = string;
   }

   /**
    * @return   The String representation of the BoxType as would appear in the
    * UML diagram.
    */
   @Override
   public String toString() {
      return string;
   }

}
