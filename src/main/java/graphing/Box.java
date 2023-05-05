package graphing;

import graphing.enums.AccessModifier;
import graphing.enums.BoxType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Holds data representing a box in a UML diagram.  Can be of type class,
 * interface, or enum.
 */
public class Box {

   private String name;
   private BoxType boxType;
   private List<Field> fields;
   private List<Method> methods;
   private ArrayList<Edge> adjList; // outGoing
   private int inDegree;
   private LinkedList<String> potentialConnections;

   /**
    * Default constructor for Box
    */
   public Box() {
      this.boxType = null;
      this.fields = new ArrayList<>();
      this.methods = new ArrayList<>();
      inDegree = 0;
      adjList = new ArrayList<>();
      potentialConnections = new LinkedList<>();
   }

   /**
    * Public constructor taking in all possible parameters for the Box
    *
    * @param boxType      Whether the box is a class, interface, or enum
    * @param fields       The variable fields in the box
    * @param methods      The methods in the box
    */
   public Box(BoxType boxType,
              List<Field> fields, List<Method> methods) {
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
   public void setBoxType(BoxType boxType) {
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
   public void setFields(List<Field> fields) {
      this.fields = fields;
   }

   /**
    * Adds one field to the field list.
    *
    * @param field
    */
   public void addField(Field field) {
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
   public void setMethods(List<Method> methods) {
      this.methods = methods;
   }

   void incrementInDegree() {inDegree++;}

   void decrementInDegree() {inDegree--;}


   /**
    * Adds one method to the method.
    */
   public void addMethod(Method method) {
      methods.add(method);
   }

   void addPotentialConnection(String className) {
      potentialConnections.add(className);
   }

   LinkedList<String> getPotentialConnections() {
      return potentialConnections;
   }

   void addConnection(Box box, Connector connection) {
      adjList.add(new Edge(box, connection));
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public ArrayList<Edge> getAdjList() {
      return adjList;
   }

   /**
    * Stores the connections to other Boxes in the graph
    */
   public class Edge {

      private Box box;
      private Connector connector;

      /**
       * @param box        A box already existing in the map
       * @param connector  The connection that the from node has to the to node
       */
      public Edge(Box box, Connector connector) {
         this.box = box;
         this.connector = connector;
      }

      public Box getBox() {
         return box;
      }

      public void setBox(Box box) {
         this.box = box;
      }

      public Connector getConnector() {
         return connector;
      }

      public void setConnector(Connector connector) {
         this.connector = connector;
      }
   }
}
