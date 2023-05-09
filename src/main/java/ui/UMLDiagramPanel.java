package ui;

import com.intellij.ide.ui.laf.LafManagerImpl;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.wm.*;
import com.intellij.openapi.wm.impl.ToolWindowManagerImpl;
import com.intellij.ui.content.ContentFactory;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import graphing.Box;
import graphing.Field;
import graphing.Method;
import graphing.UMLMap;
import listeners.ThemeInfo;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class UMLDiagramPanel extends JPanel {

   private UMLMap map;
   private mxGraph graph;
   private List<mxCell> cells;

   public UMLDiagramPanel(UMLMap map) {
      this.map = map;
      graph = new mxGraph();
      cells = new ArrayList<>();

      graph.getModel().beginUpdate();
      graph.setHtmlLabels(true);
      graph.setCellsResizable(true);
      graph.setAutoSizeCells(true);
      graph.setCellsSelectable(true);
      graph.setCellsEditable(false);
      graph.setModel(new mxGraphModel());

      try {

         // Add all boxes
         for (Box box : map.boxList()) {
            mxCell cell = newVertex(box); // Create new diagram box
            graph.addCell(cell);          // Add the box to graph
            graph.updateCellSize(cell);   // Update based on the contents
            box.setVisualVertex(cell);
         }

         // Add all connections
         for (Box box : map.boxList()) {
            for (Box.Edge edge : box.getPublicAdjList()) {
               mxCell connector = new mxCell();
               connector.setValue(null);
               connector.setStyle(edge.getConnector().getConnectorStyle());

               mxCell from = box.getVisualVertex();
               mxCell to = edge.getToBox().getVisualVertex();
               connector.setEdge(true);
               connector.setConnectable(true);

               connector.setSource(from);
               connector.setTarget(to);
               graph.addCell(connector);
            }
         }
      }
      finally {
         graph.getModel().endUpdate();
      }

      // Define the layout
      mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
      layout.execute(graph.getDefaultParent());

      // Wrap graph in component
      mxGraphComponent graphComponent = new mxGraphComponent(graph);
      graphComponent.getViewport().setBackground(Color.decode("#"
              + ThemeInfo.getBackGroundColor()));
      graphComponent.setConnectable(false);
      graphComponent.setDragEnabled(false);
      graphComponent.setToolTips(true);

      add(graphComponent);

      PluginToolWindowContentPanel.addDiagramPanel(this);
   }

   private mxCell newVertex(Box box) {

      StringBuilder value = new StringBuilder();

      // Set header
      value.append(String.format("<p style=\"margin:0px;margin-top:4px;" +
              "text-align:center;\"><b>%s</b></p>", box.getHeader()));
      value.append("<hr size=1/>");
      value.append(String.format("<p style=margin:0px;margin-left:4px;" +
              ">%s</p>", box.getAllFields()));
      value.append("<hr size=1/>");
      value.append(String.format("<p style=margin:0px;margin-left:4px;>%s</p>",
              box.getAllMethods()));

      mxCell result = new mxCell();
      result.setValue(value.toString());
      result.setGeometry(new mxGeometry(0, 0, 160, 90));
      result.setStyle(
              String.format("verticalAlign=top;align=left;overflow=fill;" +
                      "fontSize=12;" +
                      "html=1;whiteSpace=wrap;fillColor=%s;" +
                              "fontFamily=Helvetica;fontColor=black;",
                      "#ffffff"));
      result.setVertex(true);

      return result;
   }

   public void addToolWindow() {

/*      ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();

      ToolWindowManager manager = ToolWindowManager
              .getInstance(ProjectManager.getInstance().getDefaultProject());

      ToolWindow toolWindow = manager.registerToolWindow(
              new RegisterToolWindowTask("tool", ToolWindowAnchor.LEFT,
                      this, true, true,
                      true, "", contentFactory,
                      null));*/
   }


}
