package graphing.enums;

/**
 * Constants which represent the AccessModifiers of java fields and methods
 */
public enum AccessModifier {

   PUBLIC("+"), PROTECTED("#"), PRIVATE("-"), DEFAULT("~");

   private String string;

   /**
    * @param string    The Access modifier as represented on the UML diagram.
    */
   AccessModifier(String string) {
      this.string = string;
   }

   /**
    * @return  The String representation of the modifier as it would appear in
    * the UML diagram.
    */
   @Override
   public String toString() {
      return string;
   }

}
