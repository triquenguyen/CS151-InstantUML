# Project: InstantUML - Instantly generate your project UML
## Team 10 - Reece Kim, Trique Nguyen, Brian Pham
| Members | Proposal Contribution |
| ------- | --------------------- |
| Reece Kim | Came up with problem statements and provided solutions<br>Researched and analyzed the pros and cons of existing products on the market<br> Designed the overall functionalities of the project <br> Designed Class and Use Case Diagram|
| Trique Nguyen | Researched on developing a plugin for IntelliJ IDEA <br> Designed the plan and approach for the team to follow on <br> Came up with the initial tools used for the project <br> Designed the Sequence Diagram |
| Brian Pham | Designed the State Diagram <br> Stylized/Rewording of proposal report | 

| Members | Presentation Contribution |
| ------- | --------------------- |
| Reece Kim | Contribute to the presentation design in Canva <br> Explain the user flow |
| Trique Nguyen | Designed and animate presentation in Canva <br> Present the opening,discuss troblem statements and existing products |
| Brian Pham | Explain the tech stack / tools used in the project | 


| Members | Project Contribution |
| ------- | --------------------- |
| Reece Kim | Worked on the structure of the project. <br> Maintained the gradle.build file. <br> Maintained the plugin.xml file. <br> Coded everything except the part the build the displayed graph in DiagramPanel.java |
| Trique Nguyen | Researched and experimented  on GraphViz API <br> Implement GraphViz to generate the diagram <br> Display the diagram on the plugin user interface |
| Brian Pham | Overlook/Review code <br> Stylized/Check report <br> Updated needed Diagram(s) | 

## Survey on previous works and their problems/issues
- [IntelliJ Ultimate](https://www.jetbrains.com/idea/business/) [1]:
  - Comes with the paid version of IntelliJ for $16.90 per month
  - Smarter and interactive, you click on a package and it generates the diagram
  - Able to edit contents within the diagram
- [UML Generator](https://plugins.jetbrains.com/plugin/15124-uml-generator) (Existing IntelliJ plugin) [2]:
  - Can’t edit the contents within the boxes, can only delete the boxes
  - If there are enough boxes, then the connectors overlap with the boxes
  - Creates associations with DataTypes from the default Java library, which are not needed
- [Magic UML](https://github.com/xukmin/magicuml) [3]:
  - A GitHub project from 7 years ago
  - Does not have a GUI
  - Seems to have to input each .java file individually in order to use it

## Diagrams
| Diagram | Summary |
| ------- | --------------------- |
| [Class Diagram](./diagrams/Class%20Diagram.drawio.png) | The class diagram for the relationships within the project. |
| [State Diagram](./diagrams/State%20Diagram.png) | The state diagram for the process of the plugin when the user interacts with. |
| [Use Case Diagram](./diagrams/Use_Case_Diagram.drawio.png) | The use case diagram for how the user interacts with the plugin. | 
| [Sequence Diagram](./diagrams/SequenceDiagram.drawio.png)| The sequence diagram for how the objects/processes interact with each other in the plugin. |
<!-- Use if need images

[Class Diagram](./diagrams/Class%20Diagram.drawio.png)
<br>
![Class Diagram](./diagrams/Class%20Diagram.drawio.png)
--- 

[State Diagram](./diagrams/State%20Diagram.drawio.png)
<br>
![State Diagram](./diagrams/State%20Diagram.drawio.png)
---

[Use Case Diagram](./diagrams/Use_Case_Diagram.drawio.png)
<br>
![Use Case Diagram](./diagrams/Use_Case_Diagram.drawio.png)
---

[Sequence Diagram](./diagrams/SequenceDiagram.drawio.png)
<br>
![Sequence Diagram](./diagrams/SequenceDiagram.drawio.png)
---

-->
## Functionalities
- A free software/plugin, available for every Intellij type of user
- Improves productivity by allowing the user to see the high level relations between the classes directly in the IDE
- Easier usage of code selection in diagram generation (Ask if to leave out certain classes: public, protected, etc. of the diagram)
- Simple selection of package/code from the user to generate the diagram from.

## Operations
- Choosing package(s) to use for diagram generation
- Visualize/display relationships between classes
- Allow editing of diagram generation layout when choosing package*
- Allow zooming in/out for image viewing*
<br>
*Operations that are not implemented or is work in progress.

## Solutions
To deal with the problems that the previous works have, the plugin was built in consideration of the users' experience for convenience.
- Making the user not have to go through complex instructions to generate a diagram
- Easier instructions where user will be able to follow with very few problems along the way

## Instructions
Assuming that the user has already downloaded/updated their [IntelliJ](https://www.jetbrains.com/idea/)

### Setup
- Download/Clone/Import this whole git into your IntelliJ and build the project
- Ensure that project's Gradle settings are set to as is for the project
- Disable any unneeded plugins through (Help -> Find Action -> Plugins)

### Running Plugin
- Simply run the plugin through Gradle (runIde)
- This will display another IntelliJ window where a existing project should be opened
- On the sidebar, there will be a tab, "InstantUML", where upon clicking will show several button operations with text to help identify what does what
- Clicking "Generate Diagram" will prompt a display window of the project's package where you can choose which files are to be included in the diagram
- After generating, the image will be displayed in that very same sidebar window underneath the button operations.

## Snapshots
### Location of InstantUML Sidebar
![image](https://github.com/triquenguyen/CS151-InstantUML/assets/99629758/30f1b8fc-09d6-4164-ad14-7816dd8553b4)
### Preview generation of example package
![image](https://github.com/triquenguyen/CS151-InstantUML/assets/99629758/a5fb8cc5-074e-4a50-9e91-c4dd72f174b3)



