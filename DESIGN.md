#Design Document for SLogo
**Team 7: Megan Gutter, Brandon Choi, James Mosca, Thomas Bagley**

##Introduction
The goal of creating this project is essentially to create a basic IDE. In terms of UI, the goal is to create a user interface in which it is clear where commands are to be typed, and make it easy for the user to write new functions. The goal for the back end is to be able to correctly handle and execute function inputs. We want to add as little code as possible when we enable the back end to handle new, different types of commands. 

Our UI needs to be very flexible in terms of handling string input. The user is allowed to type whatever commands he/she wants. Once this input is handled on the back end, the WorldView window displays whatever output its told to. Also, new GUI features should be easy to add if needed. We can create this flexibility by having different objects for each of the windows of our front end(WorldView, CommandBox). UI flexibility will be tied to our ability to change what the user sees and how the user is able to interact with the program. 

The back end needs to be designed so it's very open for extension. We need to constantly be able to add new functionality so that we can handle different types of user input. Our back end needs to be able to correctly execute functions based on the original string input that the back end receives. 

Our "executer" is the middle ground between the input and the drawing being displayed on screen. It can handle any string input and throw an error if syntax is determined to be incorrect. Otherwise the string is passed to the back end of the program. No string input should break the program. 

##Overview

Our program utilizes the classic model-view-controller architecture as follows: 

*Model / Back End* </br>
Relevant classes: World (and subclasses), Turtle, Parser, CommandFactory, Command (and subclasses), ButtonFactory

The main component of our back end code is our World class. It holds an instance of the Turtle that is being moved and acted upon, a Parser that takes in the user's input and eventually sends out the appropriate Command(s), as well as the determined dimensions of the world. It is an abstract class and can be extended to be bounded or unbounded depending on the desired wrap around functionality. the two pairs of get and set methods allow the dimensions of the world to be changed dynamically. The listen() method is a crucial method that will call Turtle to execute all the commands in its queue of commands. This is essentially where all the movements, changes in direction, drawing, and other significant changes of the back end components in the program will occur. 

The Turtle class is one of our most important classes as this is the character that is acted upon by the user's inputs. It contains a boolean pen that indicates whether it is drawing or not, a boolean visible, its coordinates in x and y, its degree of direction, a Queue of Commands that it can interate through and perform, as well as the Image that will represent it on the front end. We designed the Turtle in a way to minimize the data sharing between classes in terms of variables. Instead, we wanted to design our program so that entire Objects are passed around OR that all objects perform the functions that would otherwise require any of their fields. Therefore rather than passing specific coordinates to update for a Forward command, we instead call move() from the Turtle class to minimize the sharing of data. Though get and set methods still exist, we hope to eventually be able to get rid of these and perform all the necessary functions with maximum encapsulation.

The Parser takes in the inputted String and utilizes CommandFactory to return all the appropriate commands in a Queue so that the Turtle can act on them in order. Command is an abstract class that will be extended to all specific Command subclasses such as Forward. The HashMap<String, Double> variables will be used to store any and all variables inputted by the user so that the program can reference them whenever needed. The ButtonFactory class is what creates clickable versions of the past commands the user put in. Therefore, once the specific Command(s) is created, the ButtonFactory will initialize a Button to represent those actions and send it the UserInterface to become available for the user. The Buttons would be added to mySideBar.


*Controller* </br>
Relevant classes: WorldController

WorldController acts as the medium between the UserInterface (front end) and World and its necessary classes (back end). WorldController's clear() method clears the entire screen by erasing all the drawings. The getWorld() method returns the World with all of its new components to demonstrate movement, drawings, or any other visual changes that the back end is meant to represent. The update() method will be a simple method that utilizes the two previously mentioned helper methods.  


*View / Front End* </br>
Relevant classes: UserInterface

The HBox CommandField is the text input where the user can type in commands he or she wants the Turtle to perform. The VBox mySideBar will contain the link to the HTML help page as well as the buttons representing past actions that are now easily clickable in order to repeat the respective previous actions. myWorldController is the instance of WorldController and will act as the middle ground between the UserInterface's front end and the rest of the code's back end. It's initialize() method creates all three of its field variables and components in order to get the program started. The refresh method gets the most up to date World and 'draws' it on the user's view. 

*Basic Overiew of a single command running:* </br>
Say the program is started. Main is called and UserInterface creates the necessary World, Turtle, and WorldController instances as well as its visual components such as commandField. The user inputs forward 50 and clicks run. This is sent to the WorldController, which sends it the its World. The World utilizes Parser and CommandFactory to produce the appropriate Forward command subclass. This is now sent to be created as a Button to be used in the future if the user chooses to do so. At the same time, the World now has one command that it gives the Turtle to add to its queue and perform. The updated Turtle is now available in the updated World which is sent back to the UI through WorldController's getWorld method. The appropriate changes are now visualized on the screen for the user to see.

##User Interface
At the most basic level there are two components to the user interface. There is a space that allows the user to write Logo code, and there is a window which displays the results of running the code. The user can interact only with the space for writing code. First, there is a text box/field that allows the user to type actual code. The user can select custom settings such as line color, turtle image, line thickness, and background display color, and language. There are also options available that allow users to select recent commands, user-defined commands, and currently-saved variables available. These are features available for user convenience. 

Error reporting is given in the form of a popup dialog box on screen. One way in which an error is generated is by attempting to run invalid code. This is defined as attempting to run code with invalid syntax, such that our parser can't make the data into a command. Invalid code can be achieved by typing out incorrect code or running code that might have been saved for later use by the user. Attempting to run the program when there is no code would be another example of invalid code. An error message is shown relaying this to the user. 

##Design Details


##API Example code


##Design Considerations 


##Team Responsibilities

Front end artists: James Mosca, Brandon Choi
Back end artists: Thomas Bagley, Megan Gutter

Because of how complicated the back end is, the front end artists will definitely be contributing to back end as well depending how much help they need and on what they need help on. 

James: </br>
Primary: UserInterface, drawing functionality</br>
Secondary: ImageView components</br>

Brandon: </br>
Primary: UserInterface, WorldController, Main</br>
Secondary: drawing functionality</br>

Thomas: </br>
Primary: Turtle, Command(s), CommandFactory</br>
Secondary: ButtonFactory, Parser, World</br>

Megan: </br>
Primary: Parser, World</br>
Secondary: Command(s)</br>

We plan on meeting as often as possible in order to pair program. Since we now have a bigger team than last project, we will also meet with our respective front or back end partner and code and collaborate with them as well. We have established a messaging system for communication so that we can let each other know of any questions, concerns, or updates. In order to merge and change the master branch, everyone must first verbally or through GitHub comments approve the pull request and its changes. We hope to meet often in order to prevent merge errors in the future and so that everyone is aware of all components of our model-view-controller design.


