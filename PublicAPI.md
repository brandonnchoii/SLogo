Public API

**Main**

java.lang.Object
	Main.java
	
public class Main 

The main function of the simulation

static void main(String[] args)
Runs the program

**User Interface**

java.lang.Object
	UserInterface.java
	
public class UserInterface

The UI holds a WorldController as well as various boxes to display buttons for settings and other functionalities

*Constructors*

public UserInterface()
Creates a new UserInterface.

*Methods*

void initialize()
Creates the UI

void refresh(String s)
Refreshes the view based on the World held in WorldController, implementing the commands signified by s.

**Turtle**

java.lang.Object
	javafx.scene.Node
		javafx.scene.image.ImageView
			Turtle.java'
			
Public class Turtle extends ImageView

This class represents the data implementation of a turtle, an actor in a 2-D environment. The implementation of turtle provided can retrieve its position, move, and turn. It can also set itself visible or hiding and draw its path. Finally, the turtle can execute commands and draw itself. This class could be extended to add a variety of functionalities by the addition of new fields and methods. For example, to implement 3-D coordinates, only one new field (z) and some adjustments to direction (to handle 3-D directionality) would be necessary. 

*Constructors*

Turtle()
Constructs a new Turtle with initial position (0,0), default image, currently drawing and visible

Turtle(int x, int y)
Constructs a new Turtle with initial position (x,y), default image, and currently drawing and visible

Turtle(int x, int y, Image i)
Constructs a new Turtle with initial position (x,y), and Image i

Turtle(int x, int y, boolean draw,boolean visible)
Constructs a new Turtle with initial position (x,y), Image I, drawing and visible set to the parameters

*Methods* 

int getX()
Returns the current x-position of the turtle

int getY()
Returns the current y-position of the turtle

void move(int px)
Moves the turtle px in its current direction

void rotate (double degrees)
Turns the turtle by degrees degrees

void setHeading(double degrees)
Turns the turtle to exactly degrees degrees

double getDirection()
Returns the direction the turtle is facing, in degrees. North/up is 0 degrees.

boolean isDrawing() 
Returns whether or not the turtle is set to draw its path. True indicates that the turtle will draw its path, false indicates that it will not.

void setDrawing(boolean b)
Sets the value of the pen field. True indicates that the turtle will draw its path, false indicates that it will not.

boolean isVisible()
Returns whether or not the turtle is visible. True – visible; false – not visible.

void setVisible(boolean b)
Returns whether or not the turtle is visible. True – visible; false – not visible.

void addCommand(Queue<Command> q)
Adds each element of q to the c to the turtle’s command queue

void act()
Executes all of the commands in the queue

void drawSelf(Group g)
Draws itself in g.

void setImage(Image i)
Sets the turtle’s Image to i

**WorldController**

Java.lang.Object
	WorldController.java
	
Public class WorldController

*Constructors*

public WorldController()
Creates a WorldController using the empty World constructor 

public WorldController(World world)
Creates a WorldController using world as the World

*Methods*

void update(String s)
Takes action based on the command entered (s)t and updates the view.

void clear()
Resets the screen

World getWorld()
Returns the world held in the WorldController

**World**

java.lang.Object
	World.java
	
Public abstract class World

This class represents a 2-D environment which holds a single turtle. The abstract nature of the class forces users to choose how the world handles situations in which the turtle goes off of the grid. This class could easily be extended to 3-D with one new field (z) and two methods (getZSize, setZSize). By changing the type of the stored objects to List<Turtle>, it would be easily possible to implement multiple turtles. The current subtypes planned are BoundedWorld, which will allow a turtle to sit outside the boundaries, and Unbounded World, which will treat the World as toroidal.

*Constructors*

public World()
Creates a new world with default size and a turtle at position (0,0)

public World(int xSize, int ySize)
Creates a new world of the specified size and a turtle at position (0,0)

public World (int xSize, int ySize, Turtle t)
Creates a new world of specified size and the Turtle provided

public World(int xSize, intySize, int turtleX, int turtleY)
Creates a new world of the specified size and a turtle at position (turtleX, turtleY)

*Methods*
int getXSize()
Returns the current horizontal size of the world

void setXSize(int x)
Sets the horizontal size of the world to x

int getYSize() 
Returns the current vertical size of the world

void setYSize(int y)
Sets the vertical size of the world

void listen(String s)
Processes the command in s.

abstract void fixPosition()
Takes action if the turtle’s position is outside of the grid. This action is determined by the subclass.

**CommandFactory**
java.lang.Object
CommandFactory.java

Public class CommandFactory

A factory class that creates a command with parameters, received from the parser. Based on the String given, it will know what type of command to create.

*Constructors*

CommandFactory()
Instantiates a new command factory

*Methods*

Command createCommand(String s)
Returns the command specified by s. If there are the wrong number of parameters, the command name is invalid, or a variable name is invalid, it will throw an exception.

**Command**

java.lang.Object
Command.java

public abstract class Command

This class provides a framework for a logo command. It holds the parameters of the argument and contains a method to execute the command. 

*Methods*
abstract double run()
Executes the command.

**Other Commands**
Each command will have its own class which will be an extension of Command.java. Other semi-general Command types (e.g. Math and Turtle) may come to exist as the commands need.


**ButtonFactory**

java.lang.Object
	ButtonFactory.java
	
public class ButtonFactory

*Constructors*

public ButtonFactory()
Creates a new ButtonFactory

*Methods*
public Button CreateButton(String s)
Creates a button that implements the commands with text s.

**Parser**

java.lang.Object
Parser.java

public class Parser

The parser will take in a string and detect each command, passing them to command factory. 

*Constructors*

Parser()
Instantiates a new parser

*Methods*
Queue< Command > parse(String s)
Returns a Queue of all Commands represented by s. 

