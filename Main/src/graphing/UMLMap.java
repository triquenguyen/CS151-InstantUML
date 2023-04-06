package graphing;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class UMLMap {

   private HashMap<Box, ArrayList<Node>> map;
   private File classesFile;

   public UMLMap() {
      map = new HashMap<>();
      classesFile = null;
   }

   public UMLMap(File classesFile) {
      map = new HashMap<>();
      this.classesFile = classesFile;
   }

   public void populateMap() {

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
    * @return  The path to the files which the data are collected from.
    */
   public File getFile() {
      return classesFile;
   }

   /**
    * @param classesFile  File path which data can be collected from
    */
   public void setFile(File classesFile) {
      this.classesFile = classesFile;
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
