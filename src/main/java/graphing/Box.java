package graphing;

import com.mxgraph.model.mxCell;
import graphing.enums.BoxType;
import graphing.enums.ConnectorType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Holds data representing a box in a UML diagram.  Can be of type class,
 * interface, or enum.
 */
public class Box {

   private String name;
   private String dataTypeID;
   private BoxType boxType;
   private List<Field> fields;
   private List<Method> methods;
   private LinkedList<Edge> adjList; // outGoing
   private mxCell visualVertex;
   private int inDegree;

   /**
    * Default constructor for Box
    */
   public Box() {
      this.boxType = null;
      this.fields = new ArrayList<>();
      this.methods = new ArrayList<>();
      inDegree = 0;
      adjList = new LinkedList<>();
   }

   /**
    * Public constructor taking in all possible parameters for the Box
    *
    * @param boxType      Whether the box is a class, interface, or enum
    * @param fields       The variable fields in the box
    * @param methods      The methods in the box
    */
   public Box(BoxType boxType,
              List<Field> fields, List<Method> methods, String name) {
      this.boxType = boxType;
      this.fields = fields;
      inDegree = 0;
      this.methods = methods;
   }

   /**
    * Getter for boxType
    */
   public BoxType getBoxType() {
      return boxType;
   }

   /**
    * Setter for boxType
    */
   void setBoxType(BoxType boxType) {
      this.boxType = boxType;
   }


   /**
    * @return    A list of the Box's fields.
    */
   public List<Field> getFields() {
      return fields;
   }

   /**
    * A setter which sets the list of fields.
    *
    * @param fields    A list of Fields
    */
   void setFields(List<Field> fields) {
      this.fields = fields;
   }

   /**
    * Adds one field to the field list.
    *
    * @param field
    */
   void addField(Field field) {
      fields.add(field);
   }

   /**
    * @return    A list of the Box's methods.
    */
   public List<Method> getMethods() {
      return methods;
   }

   /**
    * A setter which sets the list of methods.
    *
    * @param methods    A list of Methods.
    */
   void setMethods(List<Method> methods) {
      this.methods = methods;
   }

   void incrementInDegree() {inDegree++;}

   void decrementInDegree() {inDegree--;}


   /**
    * Adds one method to the method.
    */
   void addMethod(Method method) {
      methods.add(method);
   }

   void addConnection(Box box, Connector connection) {
      adjList.add(new Edge(box, connection));
   }

   /**
    * Intended for generalization
    */
   void addPotentialConnection(String boxID, Connector nestOrGen) {
      adjList.add(new Edge(boxID, nestOrGen));
   }

   String getName() {
      return name;
   }

   void setName(String name) {
      this.name = name;
   }

   void setDataTypeID(String dataTypeID) {
      this.dataTypeID = dataTypeID;
   }

   String getDataTypeID() {
      return dataTypeID;
   }

   LinkedList<Edge> getAdjList() {
      return adjList;
   }

   public List<Edge> getPublicAdjList() {
      List<Edge> result = adjList;
      return result;
   }

   public String getAllFields() {
      StringBuilder result = new StringBuilder();
      for (Field field : fields)
         result.append(field.toString());
      return result.toString();
   }

   public String getAllMethods() {
      StringBuilder result = new StringBuilder();
      for (Method method : methods)
         result.append(method.toString());
      return result.toString();
   }

   public String getHeader() {
      return String.format("%s%s", boxType.toString(), name);
   }

   public mxCell getVisualVertex() {
      return visualVertex;
   }

   public void setVisualVertex(mxCell visualVertex) {
      this.visualVertex = visualVertex;
   }

   /**
    * Stores the connections to other Boxes in the graph
    */
   public class Edge {

      private String toID;
      private Box toBox;
      private Connector connector;

      /**
       * @param box        A box already existing in the map
       * @param connector  The connection that the from node has to the to node
       */
      Edge(Box box, Connector connector) {
         this.toBox = box;
         this.connector = connector;
      }

      Edge(String toID, Connector connector) {
         this.toID = toID;
         this.connector = connector;
      }

      boolean confirmGeneralization(HashMap<String, Box> map) {
         if (map.containsKey(toID)) {
            toBox = map.get(toID);
            return true;
         }
         return false;
      }

      boolean addNesting(HashMap<String, Box> map) {
         if (connector.equals(ConnectorType.NESTED)) {
            toBox = map.get(toID);
            return true;
         }
         return false;
      }
      public Box getToBox() {
         return toBox;
      }

      public Connector getConnector() {
         return connector;
      }

      void setConnector(Connector connector) {
         this.connector = connector;
      }
   }
}
