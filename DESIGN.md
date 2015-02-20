#Design Document for SLogo
**Team 7: Megan Gutter, Brandon Choi, James Mosca, Thomas Bagley**

##Introduction
The goal of creating this project is essentially to create a basic IDE. In terms of UI, the goal is to create a user interface in which it is clear where commands are to be typed, and make it easy for the user to write new functions. The goal for the back end is to be able to correctly handle and execute function inputs. We want to add as little code as possible when we enable the back end to handle new, different types of commands. 

Our UI needs to be very flexible in terms of handling string input. The user is allowed to type whatever commands he/she wants. Once this input is handled on the back end, the WorldView window displays whatever output its told to. Also, new GUI features should be easy to add if needed. We can create this flexibility by having different objects for each of the windows of our front end(WorldView, CommandBox). UI flexibility will be tied to our ability to change what the user sees and how the user is able to interact with the program. 

The back end needs to be designed so it's very open for extension. We need to constantly be able to add new functionality so that we can handle different types of user input. Our back end needs to be able to correctly execute functions based on the original string input that the back end receives. 

Our World class takes on the role of the middle ground between the input and the visualization of the input. It can handle any string input and an error is thrown if syntax is determined to be incorrect. The World class also plays a role in the back end of our program. The parser is called by World in order to handle its string input. World isn't explicitly a controller class, because it takes on back end roles, but it performs a similar function by passing things between front end and back end. 

##Overview

Reference our UML design [HERE](https://github.com/duke-compsci308-spring2015/slogo_team07/blob/master/UML%20Overview.jpg)

Our program utilizes the classic model-view-controller architecture as follows: 

*Model / Back End* <br>
Relevant classes: World (and subclasses), Turtle, Parser, CommandFactory, Command (and subclasses), ButtonFactory

The main component of our back end code is our World class. It holds an instance of the Turtle that is being moved and acted upon, a Parser that takes in the user's input and eventually sends out the appropriate Command(s), as well as the determined dimensions of the world. It is an abstract class and can be extended to be bounded or unbounded depending on the desired wrap around functionality. the two pairs of get and set methods allow the dimensions of the world to be changed dynamically. The listen() method is a crucial method that will call Turtle to execute all the commands in its queue of commands. This is essentially where all the movements, changes in direction, drawing, and other significant changes of the back end components in the program will occur. 

The Turtle class is one of our most important classes as this is the character that is acted upon by the user's inputs. It contains a boolean pen that indicates whether it is drawing or not, a boolean visible, its coordinates in x and y, its degree of direction, a Queue of Commands that it can interate through and perform, as well as the Image that will represent it on the front end. We designed the Turtle in a way to minimize the data sharing between classes in terms of variables. Instead, we wanted to design our program so that entire Objects are passed around OR that all objects perform the functions that would otherwise require any of their fields. Therefore rather than passing specific coordinates to update for a Forward command, we instead call move() from the Turtle class to minimize the sharing of data. Though get and set methods still exist, we hope to eventually be able to get rid of these and perform all the necessary functions with maximum encapsulation.

The Parser takes in the inputted String and utilizes CommandFactory to return all the appropriate commands in a Queue so that the Turtle can act on them in order. Command is an abstract class that will be extended to all specific Command subclasses such as Forward. The HashMap<String, Double> variables will be used to store any and all variables inputted by the user so that the program can reference them whenever needed. The ButtonFactory class is what creates clickable versions of the past commands the user put in. Therefore, once the specific Command(s) is created, the ButtonFactory will initialize a Button to represent those actions and send it the UserInterface to become available for the user. The Buttons would be added to mySideBar.


*Controller* <br>
Relevant classes: WorldController

WorldController acts as the medium between the UserInterface (front end) and World and its necessary classes (back end). WorldController's clear() method clears the entire screen by erasing all the drawings. The getWorld() method returns the World with all of its new components to demonstrate movement, drawings, or any other visual changes that the back end is meant to represent. The update() method will be a simple method that utilizes the two previously mentioned helper methods.  


*View / Front End* <br>
Relevant classes: UserInterface

The HBox CommandField is the text input where the user can type in commands he or she wants the Turtle to perform. The VBox mySideBar will contain the link to the HTML help page as well as the buttons representing past actions that are now easily clickable in order to repeat the respective previous actions. myWorldController is the instance of WorldController and will act as the middle ground between the UserInterface's front end and the rest of the code's back end. It's initialize() method creates all three of its field variables and components in order to get the program started. The refresh method gets the most up to date World and 'draws' it on the user's view. 

*Basic Overiew of a single command running:* <br>
Say the program is started. Main is called and UserInterface creates the necessary World, Turtle, and WorldController instances as well as its visual components such as commandField. The user inputs forward 50 and clicks run. This is sent to the WorldController, which sends it the its World. The World utilizes Parser and CommandFactory to produce the appropriate Forward command subclass. This is now sent to be created as a Button to be used in the future if the user chooses to do so. At the same time, the World now has one command that it gives the Turtle to add to its queue and perform. The updated Turtle is now available in the updated World which is sent back to the UI through WorldController's getWorld method. The appropriate changes are now visualized on the screen for the user to see.

##User Interface
At the most basic level there are two components to the [user interface](https://github.com/duke-compsci308-spring2015/slogo_team07/blob/master/UIDiagram.jpg). There is a space that allows the user to write Logo code, and there is a window which displays the results of running the code. The user can interact only with the space for writing code. First, there is a text box/field that allows the user to type actual code. The user can select custom settings such as line color, turtle image, line thickness, and background display color, and language. There are also options available that allow users to select recent commands, user-defined commands, and currently-saved variables available. These are features available for user convenience. 

Error reporting is given in the form of a popup dialog box on screen. One way in which an error is generated is by attempting to run invalid code. This is defined as attempting to run code with invalid syntax, such that our parser can't make the data into a command. Invalid code can be achieved by typing out incorrect code or running code that might have been saved for later use by the user. Attempting to run the program when there is no code would be another example of invalid code. An error message is shown relaying this to the user. 

##Design Details

###Main

Main runs UserInterface.

###UserInterface

The UserInterface will contain the visuals and how the user interacts with them. The command field will allow the users to enter commands to the turtle interactively and virtually displays the results of the turtle executing commands. When a button is pressed, the refresh() method will change the turtle based on the commands. The buttons held in this class allow the user to set a background color, choose a turtle image, and specify a pen color. There will be a field that shows commands previously run in the environment and variables currently available in the environment, as well as user defined commands. Users can also choose which language and access a help page. This class interacts with the WorldController. The resources needed will be the languages and the help page.
* void initialize()
* void refresh()

###WorldController

The WorldController will be the controller of the MVC format. It keeps the model and view separate, getting information from the view to give to the model and vice versa. Holds the World.
* void update(String s)
  * When an input string is detected, calls all the methods in world to parse and execute command
  * Sends information to the visualizer 
* void clear()
* World getWorld()

###World (abstract)

The World connects the Parser, and therefore the Commands, with the Turtle. This is how the commands are passed to the turtle to execute. This class can be extended to create any type of world, like finite or wrap-around.
* int getXSize()
* int getYSize()
* void setXSize(int x)
* void setYSize(int y)
* abstract void fixPosition()
  * To be implemented in the UnboundedWorld, corrects position of turtle for wrap-around 
* void listen(String s)
  * Calls parse on the s through the parser
  * Passes command to the turtle
  * Calls act on the turtle

###BoundedWorld and UnboundedWorld

These classes extend the abstract World class for the different possible edge types.

###Parser

This class will parse the input strings into an implementable format. The Parser will pass the parsed string to the CommandFactory to generate the correct command. The Parser will return to World a queue of Commands to implement. 
*	Queue<Command> parse(String s)

###CommandFactory

We will create the correct type of Command based on the parsed string that was received from the Parser. This is where we will handle errors that may result from the user’s commands, resulting in a pop up box with the error. Holds a map of variables that the user has entered. 

###Command (interface)

The Command interface will be implemented by all the command classes. This is an interface because each command executes different instructions, so there are no methods in common and there will be no use for this to be an abstract class. The purpose of this class is to create a framework from which the same method can be called by the Turtle to perform any command. New commands can be created easily by extending this interface. 
* abstract double run()

###ButtonFactory

Dynamically creates new choices for the user and sends them to be displayed, with regards to the features to show previously run commands, variables, and user-defined commands. 
* Button createButton()

###Turtle
The Turtle is responsible for holding its current position and orientation and being able to move based on the instructions from the queue of Commands given to it by the World. The Turtle’s attributes can be changed here as well, like its image or pen color. 
* void drawSelf(Group g)
* boolean isDrawin()
* boolean isVisible()
* void setDrawing(boolean b)
* void setVisible(boolean b)
* int getX()
* int getY()
* void act()
  * Performs Commands from queue of Commands 
* void move(int pixels) 
* void rotate(double degrees)
* void setHeading(double degrees)
* void addCommands(Queue<Command> commands)



##API Example code

Reference [this](https://github.com/duke-compsci308-spring2015/slogo_team07/blob/master/API.md)

The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail.


For convenience, set s = ‘fd 50’


UI.refresh(s) calls WorldController.update(s) which calls World.listen(s) which calls Parser.parse(s) which returns a one-element Queue of a new ForwardCommand with parameter list equal to {50} which is passed to Turtle.addCommand(s). World.listen(s) also calls Turtle.act(), which processes the command. UI.refresh(s) then references WorldController.getWorld() which returns the information and is updated by the UI.


##Design Considerations 

We discussed the use of factories for commands and buttons in order to maximize convenience and cleanliness in code. We also weighed the pros and cons on which class should act as our controller in MVC design. We eventually settled on WorldController because it already had access to both the UI as well as the World, which acts as our main component in the back end. We were concerned about how our program would store variables and throw exceptions if needed but settled on putting the hash map of variables in our command factory so that when initializing our commands, any faulty parameter data types would allow us to display a pop-up indiciating an error in our user's input. One significant change from our initial design of the program was our amount of get and set methods. After our readings, we decided on attempting to minimize the number of gets and sets so that there was the least amount of data sharing and a lot of encapsulation in our classes and objects. Finally, we discussed whether to utilize an abstract class or an interface for our Command class. We settled on an abstract class because the functionality of CommandFactory required us to be able to instantiate a general Command. Moreover, while an interface allows multiple behaviors to be implemented on one object, an abstract class demonstrates more of a direct inheritance. A lot of our design considerations and choices changed after class readings on interfaces and reconsiderations on possible code smells. By the end of it, we were really satisified with how our overall design turned out and believe the use of abstractions and our streamlined MVC design will make Sprint 3 much easier.


##Team Responsibilities

Front end artists: James Mosca, Brandon Choi
Back end artists: Thomas Bagley, Megan Gutter

Because of how complicated the back end is, the front end artists will definitely be contributing to back end as well depending how much help they need and on what they need help on. 

James: <br>
Primary: UserInterface, drawing functionality<br>
Secondary: ImageView components<br>

Brandon: </br>
Primary: UserInterface, WorldController, Main<br>
Secondary: drawing functionality<br>

Thomas: </br>
Primary: Turtle, Command(s), CommandFactory<br>
Secondary: ButtonFactory, Parser, World<br>

Megan: </br>
Primary: Parser, World<br>
Secondary: Command(s)<br>

We plan on meeting as often as possible in order to pair program. Since we now have a bigger team than last project, we will also meet with our respective front or back end partner and code and collaborate with them as well. We have established a messaging system for communication so that we can let each other know of any questions, concerns, or updates. In order to merge and change the master branch, everyone must first verbally or through GitHub comments approve the pull request and its changes. We hope to meet often in order to prevent merge errors in the future and so that everyone is aware of all components of our model-view-controller design.

##Parser Recitation Discussion
So our current parser implementation design basically consists of recursively making a tree and then running through elements of the tree in a specific order in order to execute commands. Tony from cellsociety is taking a similar but different approach. They solve the problem by having types, each instance of which is a "token". So 50 is constant, x is a variable, etc. Then they use an eval function that knows how to handle each token, and it just evals the next token until the whole expression is evaluated. I do like this design. I could see us potentially implementing this type system.
