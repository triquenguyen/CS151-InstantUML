package ui;

import graphing.Box;
import graphing.Field;
import graphing.Method;
import graphing.UMLMap;
import javax.swing.*;
import graphing.Box.Edge;

import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.*;
import guru.nidi.graphviz.model.*;

import static guru.nidi.graphviz.model.Factory.*;
import static guru.nidi.graphviz.attribute.Records.*;
import guru.nidi.graphviz.model.MutableNode;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UMLDiagramPanel extends JPanel {

   private MutableGraph mutableGraph;
   private Set<MutableNode> graphNodeSet;
   private BufferedImage graphImage;
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
      ArrayList<String> mList = new ArrayList<>();
      for (Method method : fileClass.getMethods()) {
         methods.append(String.format("%s%n", method));
      }

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
