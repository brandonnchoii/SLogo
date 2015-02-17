February 17

##**TURTLE**

*Constructors* - This is updated with all of the current constructors

public Turtle() 

Creates a Turtle with default settings

public Turtle(Image i)

Creates a Turtle with Image i,otherwise default settings

public Turtle(int x, int y, Image i)

Creates a Turtle with position (x,y) and Image i, otherwise default settings
	
public Turtle(int x, int y, double dir, Image i)

Creates a Turtle with position (x,y), direction dir, and Image i, otherwise default settings

*Methods*

Removed isVisible and setVisible - Turtle already had these methods from javafx.scene.Node. This does not affect how other classes use it, as they can call these methods.

Added fixpos(int height, int width)

Adjusts the turtle if it is outside of the boundaries of a toroidal grid (Unbounded Grid). 

##**WORLD**

The major change in world is the change in field name from xSize & ySize to width and height. Methods are changed accordingly.


*Constructors*

public World() 

Creates a world with default settings

public World(int h, int w)

Creates a world with height h, width w, and a default Turtle and Parser

public World(int h, int w, Turtle t)

Creates a world with height h, width w, Turtle t, and a default parser

*Methods*

The getXSize(), setXSize(), getYSize(), and setYSize() methods have been changed to getWidth(), setWidth(), getHeight(), setHeight()c
