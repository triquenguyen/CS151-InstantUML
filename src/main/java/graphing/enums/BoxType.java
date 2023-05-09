package graphing.enums;

/**
 * The type of box from the options of Class, Interface, or enum.
 */
public enum BoxType {

   CLASS(""), INTERFACE("&lt;&lt;Interface&gt;&gt;<br>"),
   ENUMERATION("&lt;&lt;Enumeration&gt;&gt;<br>"),
   ABSTRACT("&lt;&lt;Abstract&gt;&gt;<br>");

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
