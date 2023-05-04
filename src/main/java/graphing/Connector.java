package graphing;

import graphing.enums.ConnectorType;
import graphing.enums.Modality;

/**
 * A representation of a Connector which connects the boxes in a UML class
 * diagram.  Stores the ConnectorType and the Modality of the connector in
 * the case that it represents a kind of Association.
 */
public class Connector {

   private ConnectorType type;
   private Modality from;
   private Modality to;

   /**
    * @param type    The ConnectorType of the Connector.
    */
   public Connector(ConnectorType type) {
      this.type = type;
   }

   /**
    * @return    The modality of the origin
    */
   public Modality getFrom() {
      return from;
   }

   /**
    * Sets the Modality of the origin
    */
   public void setFrom(Modality from) {
      this.from = from;
   }

   /**
    * @return    The modality of the Data that it goes to.
    */
   public Modality getTo() {
      return to;
   }

   /**
    * Sets the modality of the box that it leads to.
    */
   public void setTo(Modality to) {
      this.to = to;
   }
}
