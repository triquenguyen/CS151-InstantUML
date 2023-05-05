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
import guru.nidi.graphviz.model.MutableNode;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UMLDiagramPanel extends JPanel {

//   private MutableGraph g;
   private Graph graph;

   private MutableGraph mutableGraph;
   private Set<MutableNode> graphNodeSet;

   private ArrayList<LinkSource> linkList;
   private UMLMap map;

   public UMLDiagramPanel(UMLMap map) {
      this.map = map;
      mutableGraph = mutGraph("diagram").setDirected(false)
              .graphAttrs().add(Rank.dir(Rank.RankDir.TOP_TO_BOTTOM));
      linkList = new ArrayList<>();
      graphNodeSet = new HashSet<>();


      for (Map.Entry<String, Box> entry : map.getEntrySet()) {
         Box fileClass = entry.getValue();
         MutableNode graphNode = findNode(fileClass);
         graphNodeSet.add(graphNode);

         if (fileClass.getAdjList() != null) {
            for (Edge edge : fileClass.getAdjList()) {
               graphNode.linkTo(findNode(edge.getBox()));
            }
         }
      }

      for (MutableNode graphNode : graphNodeSet) {
         mutableGraph.add(graphNode);
      }

      try {
         Graphviz.fromGraph(mutableGraph).width(1080).render(Format.PNG)
                 .toFile(new File("diagrams/diagram.png"));
      } catch (IOException e) {
         Messages.showInfoMessage(e.getMessage(), "Error");
      }

      PluginToolWindowContentPanel.addDiagramPanel(this);
   }

   public MutableNode createNode(Box fileClass) {
      // Initiate CLass Name
      String className = fileClass.getBoxType() + "\n" + fileClass.getName();

      // Handle attributes
      StringBuilder attributes = new StringBuilder();
      for (Field attr : fileClass.getFields())
         attributes.append(String.format("%s%n", attr));

      // Handle Methods
      StringBuilder methods = new StringBuilder();
      for (Method method : fileClass.getMethods())
         methods.append(String.format("%s%n", method));

      return mutNode(className).add(Font.size(24), Shape.BOX,
              Records.of(turn(rec(className), rec(attributes.toString())
                      , rec(methods.toString()))));
   }

   public MutableNode findNode(Box fileClass) {
      if (!graphNodeSet.contains(mutNode(fileClass.getName()))) {
         return createNode(fileClass);
      } else {
         return mutNode(fileClass.getName());
      }
   }
}
