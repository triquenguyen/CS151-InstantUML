package ui;

import com.intellij.ide.ui.laf.LafManagerImpl;
import graphing.Box;
import graphing.Field;
import graphing.Method;
import graphing.UMLMap;
import javax.swing.*;

import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.*;
import guru.nidi.graphviz.model.*;

import static guru.nidi.graphviz.model.Factory.*;
import static guru.nidi.graphviz.attribute.Records.*;
import guru.nidi.graphviz.model.MutableNode;


import java.awt.image.BufferedImage;
import java.util.*;

public class UMLDiagramPanel extends JPanel {

   private MutableGraph mutableGraph;
   private HashMap<String, NodePair> graphNodes;
   private BufferedImage graphImage;
   private UMLMap map;

   public UMLDiagramPanel(UMLMap map) {
      this.map = map;
      mutableGraph = mutGraph("diagram").setDirected(true)
              .graphAttrs().add(Rank.dir(Rank.RankDir.TOP_TO_BOTTOM));
      graphNodes = new HashMap<>();

      // Add the nodes to the graph
      for (Map.Entry<String, Box> entry : map.getEntrySet()) {
         Box fileClass = entry.getValue();
         MutableNode graphNode = createNode(fileClass);
         mutableGraph.add(graphNode);

         graphNodes.put(entry.getKey(),
                 new NodePair(graphNode, entry.getValue()));
/*         for (Edge edge : fileClass.getAdjList())
            graphNode.linkTo(findNode(edge.getBox()));*/
      }

      // Add node links
      for (Map.Entry<String, NodePair> entry : graphNodes.entrySet()) {
         MutableNode graphNode = entry.getValue().vizNode;
         Box box = entry.getValue().box;

         for (Box.Edge edge : box.getAdjList()) {
            Link link = Link.to(graphNodes.get(edge.getBox().getName()).vizNode);
            mutableGraph.addLink(graphNode.addLink(link));
         }
            // graphNode.linkTo(graphNodes.get(edge.getBox().getName()).vizNode);
      }


/*      for (MutableNode graphNode : graphNodeSet)
         mutableGraph.add(graphNode);*/

      // Set the graph image
      graphImage = Graphviz.fromGraph(mutableGraph).width(1080)
              .render(Format.PNG).toImage();
      add(new JLabel(new ImageIcon(graphImage)));

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
              Records.of(turn(rec(className),
                      rec(attributes.toString())
                      , rec(methods.toString()))));
   }

   private class NodePair {

      private MutableNode vizNode;
      private Box box;

      public NodePair(MutableNode vizNode, Box box) {
         this.vizNode = vizNode;
         this.box = box;
      }
   }

/*   public MutableNode findNode(Box fileClass) {
      if (!graphNodeSet.contains(mutNode(fileClass.getName())))
         return createNode(fileClass);
      else
         return mutNode(fileClass.getName());
   }*/
}
