package ui;

import com.intellij.ide.plugins.cl.PluginClassLoader;
import com.intellij.openapi.ui.Messages;
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
import org.slf4j.impl.StaticLoggerBinder;

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

   private MutableGraph g;
   private Graph graph;
   private Set<Node> graphNodeSet;
   private UMLMap map;

   public UMLDiagramPanel(UMLMap map) {
      this.map = map;
      g = mutGraph("example").setDirected(false);
      graph = graph("Diagram").directed();
      graphNodeSet = new HashSet<>();

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
         Graphviz.fromGraph(graph).width(720).render(Format.PNG)
                 .toFile(new File("diagrams/diagram.png"));
      } catch (IOException e) {
         Messages.showInfoMessage(e.getMessage(), "Error");
      }

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

      return node(className).with(Shape.BOX, Records.of(turn(rec(className),
              rec(attributes + "\n" + methods))));
   }

   public Node findNode(Box fileClass) {
      if (!graphNodeSet.contains(node(fileClass.getName()))) {
         return createNode(fileClass);
      } else {
         return node(fileClass.getName());
      }
   }

}
