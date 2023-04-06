package graphing;

import graphing.enums.AccessModifier;
import graphing.enums.BoxType;

import java.util.List;

/**
 * Holds data representing a box in a UML diagram.  Can be of type class,
 * interface, or enum.
 */
public class Box {

   private BoxType boxType;
   private boolean isAbstract;
   private List<Field> fields;
   private List<Method> methods;

   /**
    * Default constructor for Box
    */
   public Box() {
      this.boxType = null;
      this.isAbstract = false;
      this.fields = null;
      this.methods = null;
   }

   /**
    * Public constructor taking in all possible parameters for the Box
    *
    * @param boxType      Whether the box is a class, interface, or enum
    * @param isAbstract   If the box is abstract
    * @param fields       The variable fields in the box
    * @param methods      The methods in the box
    */
   public Box(BoxType boxType, boolean isAbstract,
              List<Field> fields, List<Method> methods) {
      this.boxType = boxType;
      this.isAbstract = isAbstract;
      this.fields = fields;
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
    * @return   boolean for if the Box is abstract.
    */
   public boolean isAbstract() {
      return isAbstract;
   }

   /**
    * Setter for the abstract box.
    */
   public void setAbstract(boolean anAbstract) {
      isAbstract = anAbstract;
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

   /**
    * Adds one method to the method.
    */
   public void addMethod(Method method) {
      methods.add(method);
   }
}
