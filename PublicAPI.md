Public API

*Turtle*

java.lang.Object
	Turtle.java
	
**Public Class Turtle**

This class represents the data implementation of a turtle, an actor in a 2-D environment. The implementation of turtle provided can retrieve its position, move, and turn. It can also set itself visible or hiding and draw its path. This class could be extended to add a variety of functionalities by the addition of new fields and methods. For example, to implement 3-D coordinates, only one new field (z) and two new methods (getZ() and a new move method to include z) would be needed. 

**Constructors**
Turtle()
Constructs a new Turtle with initial position (0,0), currently drawing and visible

Turtle(int x, int y)
Constructs a new Turtle with initial position (x,y), currently drawing and visible

Turtle(int x, int y, boolean draw,boolean visible)
Constructs a new Turtle with initial position (x,y), with drawing and visible set to the parameters

**Methods** 
int getX()
Returns the current x-position of the turtle

int getY()
Returns the current y-position of the turtle

void move(int x, int y)
Moves the turtle to the coordinates specified by (x,y)

double getDirection()
Returns the direction the turtle is facing, in degrees. North/up is 0 degrees.

void setDirection(double d)
Sets the direction the turtle is facing, in degrees. North/up is 0 degrees. 

boolean isDrawing() 
Returns whether or not the turtle is set to draw its path. True indicates that the turtle will draw its path, false indicates that it will not.

void setDrawing(boolean b)
Sets the value of the pen field. True indicates that the turtle will draw its path, false indicates that it will not.

*World*

java.lang.Object
	World.java
	
**Public abstract class World**

This class represents a 2-D environment which holds a single turtle. The abstract nature of the class forces users to choose how the world handles situations in which the turtle goes off of the grid. This class could easily be extended to 3-D with one new field (z) and two methods (getZSize, setZSize). By changing the type of the stored objects to List<Turtle>, it would be easily possible to implement multiple turtles. 

**Constructors**

public World()
Creates a new world with default size and a turtle at position (0,0)

public World(int xSize, int ySize)
Creates a new world of the specified size and a turtle at position (0,0)

public World (int xSize, int ySize, Turtle t)
Creates a new world of specified size and the Turtle provided

public World(int xSize, intySize, int turtleX, int turtleY)
Creates a new world of the specified size and a turtle at position (turtleX, turtleY)

**Methods**

int getXSize()
Returns the current horizontal size of the world

void setXSize(int x)
Sets the horizontal size of the world to x

int getYSize() 
Returns the current vertical size of the world

void set YSize(int y)
Sets the vertical size of the world

Turtle getTurtle()
Returns the turtle currently stored in the world

abstract void fixPosition()
Takes action if the turtle’s position is outside of the grid. This action is determined by the subclass.

*WorldView*

Java.lang.Object
	WorldView.java
	
**Public class WorldView**

This class holds a world and has methods to display the world.

**Constructors**

public WorldView()
Creates a WorldView using the empty World constructor and a default Image for the Turtle

public WorldView(World world)
Creates a WorldView using world as the World and a default Image for the Turtle

public WorldView(World world, Image image)
Creates a WorldView using world as the world and image as the turtleImage.

**Methods**

void update()
Updates the view based on the positions and actions of the world

void clear()
Resets the screen

World getWorld()
Returns the world held in the WorldView

*Command*

java.lang.Object
	Command.java
	
**public abstract class Command**

This class provides a framework for a logo command. It holds the parameters of the argument and contains a method to execute the command. Immediate subclasses will be superclasses of individual commands, such as TurtleCommand and MathCommand.

**Methods**

abstract double run()
Executes the command.

*TurtleCommand*

java.lang.Object
	Command.java
		TurtleCommand.java
		
**public abstract class TurtleCommand()**

This command will have a turtle object it can manipulate as a field and the map of variables

*MathCommand*

java.lang.Object
	Command.java
		MathCommand.java
		
**public abstract class MathCommand()**
This command will have a map of the variables

*Other Commands*
Each command will have its own class which will be an extension of Command.java. Other semi-general Command types (e.g. Math and Turtle) may come to exist as the commands need.



