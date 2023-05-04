package ui;

import com.intellij.openapi.ui.Messages;
import graphing.Box;
import graphing.Field;
import graphing.Method;
import graphing.UMLMap;
import javax.swing.*;
import graphing.Box.Edge;

import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.*;
import guru.nidi.graphviz.model.*;
import static guru.nidi.graphviz.attribute.Records.*;
import static guru.nidi.graphviz.model.Compass.*;
import static guru.nidi.graphviz.model.Factory.*;
import static guru.nidi.graphviz.attribute.Records.*;
import static guru.nidi.graphviz.model.Compass.*;
import guru.nidi.graphviz.model.Node;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UMLDiagramPanel extends JPanel {

   public MutableGraph g = mutGraph("example").setDirected(false);
   public Graph graph = graph("Diagram").directed();
   public Set<Node> graphNodeSet = new HashSet<>();
   private UMLMap map;

   public UMLDiagramPanel(UMLMap map) {
      this.map = map;

      for (Map.Entry<String, Box> entry : map.getEntrySet()) {
         Box fileClass = entry.getValue();
         Node graphNode = createNode(fileClass);
         graphNodeSet.add(graphNode);

         // Handle connection
         for (Edge edge : fileClass.getAdjList()) {
             graphNode.link(findNode(edge.getBox()));
         }
      }

      for (Node graphNode : graphNodeSet) {
         graph.with(graphNode);
      }

      try {
         Graphviz.fromGraph(graph).width(720).render(Format.PNG).toFile(new File("diagrams/diagram.png"));
      } catch (IOException e) {
         Messages.showInfoMessage(e.getMessage(), "Error");
      }

      /*
      * I will call this method from UMLMap.
      * Put code to generate the diagram and add the UML diagram here.  Last
      * line should call PluginToolWindowContentPanel.addDiagramPanel(this);
      * */

      PluginToolWindowContentPanel.addDiagramPanel(this);
   }

   public Node createNode(Box fileClass) {
      // Initiate CLass Name
      String className = fileClass.getBoxType() + "\n" + fileClass.getName();

      // Handle attributes
      StringBuilder attributes = new StringBuilder();

      for (Field attr : fileClass.getFields()) {
         attributes.append(attr.toString());
      }

      // Handle Methods
      StringBuilder methods = new StringBuilder();
      for (Method method : fileClass.getMethods()){
         methods.append(method.toString());
      }

      return node(className).with(Shape.BOX, Records.of(turn(rec(className), rec(attributes + "\n" + methods))));

   }

   public Node findNode(Box fileClass) {
      if (!graphNodeSet.contains(node(fileClass.getName()))) {
         return createNode(fileClass);
      } else {
         return node(fileClass.getName());
      }
   }

}
