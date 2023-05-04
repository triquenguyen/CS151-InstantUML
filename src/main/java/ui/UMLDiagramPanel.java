package ui;

import graphing.Box;
import graphing.UMLMap;
import javax.swing.*;
import graphing.UMLMap.Node;

import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.*;
import guru.nidi.graphviz.model.*;
import static guru.nidi.graphviz.attribute.Records.*;
import static guru.nidi.graphviz.model.Compass.*;
import static guru.nidi.graphviz.model.Factory.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class UMLDiagramPanel extends JPanel {

   public MutableGraph g = mutGraph("example").setDirected(false);

   private UMLMap map;

   public UMLDiagramPanel(UMLMap map) throws Exception {
      this.map = map;

      ArrayList<guru.nidi.graphviz.model.Node> graphNodeList = new ArrayList<>();

      for (Map.Entry<graphing.Box, ArrayList<Node>> entry : map.getEntrySet()) {
         Box fileClass = entry.getKey();
         ArrayList<Node> attributes = entry.getValue();
         int index = 0;

         guru.nidi.graphviz.model.Node graphNode = node(fileClass.getName())
                 .with(Shape.BOX, Label.html("<b>fileClass.getName()</b>"));

         graphNodeList.add(graphNode);
      }

      for (guru.nidi.graphviz.model.Node graphNode : graphNodeList) {
         g.add(graphNode);
      }

      Graphviz.fromGraph(g).width(200).render(Format.PNG).toFile(new File("diagrams/diagram.png"));

      /*
      * I will call this method from UMLMap.
      * Put code to generate the diagram and add the UML diagram here.  Last
      * line should call PluginToolWindowContentPanel.addDiagramPanel(this);
      * */
      PluginToolWindowContentPanel.addDiagramPanel(this);
   }

}
