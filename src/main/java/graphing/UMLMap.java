package graphing;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import ui.UMLDiagramPanel;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.lang.reflect.*;

/**
 * Stores data from Java classes.  Uses javap command to collect data from the
 * "out" folder in IntelliJ.  Holds the data in an outgoing graphical
 *  representation.
 */
public class UMLMap {

   private HashMap<Box, ArrayList<Node>> map; // Outgoing map


   public UMLMap(PsiJavaFileImpl[] classes) {
      map = new HashMap<>();
   }

   /**
    * Populates UMLMap with data from the file.
    */
   public void populateMap(PsiJavaFileImpl[] classes) {



      new UMLDiagramPanel(this);
   }

   /**
    * Populates a box based off of data collected from one execution of a
    * javap -p command. Also adds non-Association Connections.
    *
    * @param reader
    * @return
    */
   private Box populateBox(BufferedReader reader) {


      return null;
   }

   /**
    * @param box   A Box that is added to the map
    */
   private void addBox(Box box) {
      if (!map.containsKey(box))
         map.put(box, new ArrayList<>());
      else
         throw new IllegalArgumentException("Box already in Map");
   }

   /**
    * Adds a connection from one box to another
    *
    * @param from         The start of the connection
    * @param connection   The kind of connection
    * @param to           Where the connection ends
    */
   private void addConnection(Box from, Connector connection, Box to) {
      map.get(from).add(new Node(to, connection));
   }

   /**
    * @return   The Entry set for the internal map.
    */
   public Set<Map.Entry<Box, ArrayList<Node>>> getEntrySet() {
      return map.entrySet();
   }

   /**
    * Stores the connections to other Boxes in the graph
    */
   private class Node {

      private Box box;
      private Connector connector;

      /**
       * @param box        A box already existing in the map
       * @param connector  The connection that the from node has to the to node
       */
      public Node(Box box, Connector connector) {
         this.connector = connector;
      }
   }
}
