Slogo Addition – Back End
Estimation:
	I will at least need to add two new commands, each of which is its own class, plus modify and/or add subclasses to World. It will likely also require updates to the primary controller class and the properties files to translate entered commands into reflectable names. I would expect that this will take about an hour.

Review: 
	It took me about 45 minutes to complete this. I did not get it right on the first try as I forgot how some of the data in the program was held. I needed to update the following files:
	Turtle.java: Because we had only one turtle, the turtle was able to hold the property of whether or not it was bounded. Even in a multiple-turtle setting, this could be an interesting implementation. 
	World.java: To check if the turtle was bounded and, if so, to fix its position. Also made non-abstract.
	BoundedWorld.java: This class was deleted because it was not fully implemented and was causing issues in the new boundaries.
	UnboundedWorld.java:  This class was deleted because it was not fully implemented and was causing issues in the new boundaries.
	WorldController.java: Now instantiates instances of World instead of (Un)BoundedWorld. Removed imports for (Un)BoundedWorld
	English.properties: To add the fence and window commands to the list of legal commands
	NumParameters.properties: To add the fence and window commands with 0 parameters so that information could be read.

Analysis:
	The code dependencies required to make this work were problematic. In order to change information about the world, I had to store it in the turtle – which is only passable because we only had one turtle. This is because each command takes a turtle as a parameter and does not have access to the world. However, the position checking had to be done from world, because only world knew its boundaries. 
	If I was not familiar with the code, it would have been incredibly difficult to implement this feature, as I would likely not have understood what I needed to change in world vs. what I needed to change in turtle. A more logical control flow would have made this much easier. 

**NOTE: The World itself is not the same size as the pane. The fence command restricts the turtle to the size of the World, not the pane.**
