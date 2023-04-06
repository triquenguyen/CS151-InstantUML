package graphing.enums;

public enum Modality {

   ZERO_OR_ONE("0..1"), ONE("1"), ZERO_OR_MORE("0..*"),
   ONE_OR_MORE("1..*"), ZERO_TO_N("0..n"), ONE_TO_N("1..n");

   private String string;

   Modality(String string) {
      this.string = string;
   }

   @Override
   public String toString() {
      return string;
   }

}
