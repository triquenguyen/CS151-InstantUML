package graphing.enums;

/**
 * Constants representing the Modality of UML diagram Association
 */
public enum Modality {

   ZERO_OR_ONE("0..1"), ONE("1"), ZERO_OR_MORE("0..*"),
   ONE_OR_MORE("1..*"), ZERO_TO_N("0..n"), ONE_TO_N("1..n");

   private String string;

   Modality(String string) {
      this.string = string;
   }

   /**
    * @return    The String representation of the kinds of modality that would
    *            appear in a UML diagram.
    */
   @Override
   public String toString() {
      return string;
   }

}
