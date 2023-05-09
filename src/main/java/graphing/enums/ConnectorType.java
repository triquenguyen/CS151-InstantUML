package graphing.enums;

import com.mxgraph.view.mxStylesheet;

import java.util.HashMap;
import java.util.Map;

/**
 * The kinds of Connectors for the UML diagram.
 */
public enum ConnectorType {

   ASSOCIATION("startArrow=open;endArrow=;html=1;rounded=0;startFill=1;" +
           "strokeWidth=1;startSize=13;endSize=4;endFill=black;label=;"),
   GENERALIZATION("endArrow=block;html=1;rounded=0;endFill=0;" +
           "strokeWidth=1;endSize=13;startSize=4;label=;startFill=#ffffff"),
   NESTED("endArrow=oval;rounded=0;html=1;endFill=0;strokeWidth=1;" +
           "endSize=11;startSize=4;label=;");

   private String style;

   ConnectorType(String style) {
      this.style = style;
   }

   public String getStyle() {
      return style;
   }
}
