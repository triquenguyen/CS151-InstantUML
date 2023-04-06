package graphing.enums;

public enum AccessModifier {

   PUBLIC("+"), PROTECTED("#"), PRIVATE("-"), DEFAULT("~");

   private String string;

   AccessModifier(String string) {
      this.string = string;
   }

   @Override
   public String toString() {
      return string;
   }

}
