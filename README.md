# <p style="text-align: center;">InstantUML <br>A UML Generator Plugin For IntelliJ IDEA</br></p> 
## <p style="text-align: center;">Project Proposal</p>
### <p style="text-align: center;">Group 10 - Trique Nguyen, Reece Kim, Brian Pham</p>
### <p style="text-align: center;">CS 151: Object Oriented Design</p>
### <p style="text-align: center;">Professor Gaikwad</p>

# Problem Statement
- UML design generators today are either impractical and not up to date or locked behind some paywall; The key here is to fix this issue by attempting to update that process while being free and open-source
- Previous/Current Works:  
  - IntelliJ Ultimate [1]:  
    - Comes with the paid version of IntelliJ for $16.90 per month
    - Smarter and interactive, you click on a package and it generates the diagram
    - Able to edit contents within the diagram 
  - UML Generator (Existing IntelliJ plugin) [2]:
    - Can’t edit the contents within the boxes, can only delete the boxes
    - If there are enough boxes, then the connectors overlap with the boxes
    - Creates associations with DataTypes from the default Java library, which are not needed  
  - Magic UML [3]:  
    - A GitHub project from 7 years ago  
    - Does not have a GUI  
    - Seems to have to input each ```.java``` file individually in order to use it
- Users should be able to use this product to generate their java code into a UML design of their choosing on their operating systems (Windows/Linux/MacOS). 

# High-level Description	
- Final product: a plugin in IntelliJ IDEA
- Plan and approach
  - Research on the similar products currently found in the market, find their disadvantages and figure out to improve it in our product
  - Research on how to develop a plugin for IntelliJ on their SDK platform (or draw.io) 
  - Designing diagrams (Use case, class,state, etc.)
  - Develop the plugin based on the designed diagrams 
  - Develop testing strategy, to ensure the product runs smoothly and gets debugged on time before the official release

# Funtionality
- A free software/plugin, available for every Intellij type of user
- Improves productivity by allowing the user to see the high level relations between the classes directly in the IDE
- Easier usage of code selection in diagram generation (Ask if to leave out certain classes: public, protected, etc. of the diagram)  
- Simple selection of package/code from the user to generate the diagram from.

# Operations
- Choosing package(s) to use for diagram generation
- Visualize/display relationships between classes
- Allow editing of diagram generation layout when choosing package 
- Selection of code to be used in diagram(s) (getters, setters, etc.)






