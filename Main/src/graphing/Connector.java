package graphing;

import graphing.enums.ConnectorType;
import graphing.enums.Modality;

public class Connector {

   private ConnectorType type;
   private Modality from;
   private Modality to;

   public Connector() {
   }

   public Connector(ConnectorType type) {
      this.type = type;
   }

   public Modality getFrom() {
      return from;
   }

   public void setFrom(Modality from) {
      this.from = from;
   }

   public Modality getTo() {
      return to;
   }

   public void setTo(Modality to) {
      this.to = to;
   }
}
