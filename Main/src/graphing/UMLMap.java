package graphing;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Stores data from Java classes.  Uses javap command to collect data from the
 * "out" folder in IntelliJ.  Holds the data in an outgoing graphical
 *  representation.
 */
public class UMLMap {

   private HashMap<Box, ArrayList<Node>> map; // Outgoing map.
   private File classesFile;

   /**
    * default constructor for UMLMap
    */
   public UMLMap() {
      map = new HashMap<>();
      classesFile = null;
   }

   /**
    * @param classesFile    The File that contains
    */
   public UMLMap(File classesFile) {
      map = new HashMap<>();
      this.classesFile = classesFile;
   }

   /**
    * Populates UMLMap with data from the file.
    */
   public void populateMap() {

      // Initialize Process builder for javap -p commands
      ProcessBuilder builder = new ProcessBuilder();

      // array of .class files in the out folder
      File[] files = classesFile.listFiles();
      builder.directory(classesFile);

      for (File file : files) {

         // TODO add an if condition that continues if class isn't one of the
         // TODO ones selected by user.

         // Commands to get the method and variable signatures
         builder.command("cmd.exe", "/c", "javap -p " + file.getName());

         // Start and run the process for each .class file
         Process p = null;
         try {
            p = builder.start(); // Convert to builder into a process
         }
         catch (IOException ioException) {
            ioException.getStackTrace();
         }

         // Set up Buffered reader to read class, field, and method signatures
         BufferedReader reader =
                 new BufferedReader(new InputStreamReader(p.getInputStream()));
         Box box = populateBox(reader); // Populate a box


      }

      /*
      TODO After the Map has been population add the Association connections
      by looking at the Field in the Box's Map.
       */

   }

   /**
    * Populates a box based off of data collected from one execution of a
    * javap -p command. Also adds non-Association Connections.
    *
    * @param reader
    * @return
    */
   private Box populateBox(BufferedReader reader) {

      String line = "";
      String[] s;

      // TODO Read the second line to determine the BoxType

      // Collect the Field and Method data to put in the map
      while (line != null) {

         // TODO read the


      }

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
