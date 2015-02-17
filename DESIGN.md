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

The main component of our back end code is our World class. It holds an instance of the Turtle that is being moved and acted upon, a Parser that takes in the user's input and eventually sends out the appropriate Command(s), as well as the determined dimensions of the world. 

The Turtle class is one of our most important classes as this is the character that is acted upon by the user's inputs. It contains a boolean pen that indicates whether it is drawing or not, a boolean visible, its coordinates in x and y, its degree of direction, a Queue of Commands that it can interate through and perform, as well as the Image that will represent it on the front end. 

The Parser and CommandFactory 


*Controller* </br>
Relevant classes: WorldController

WorldController acts as the medium between the UserInterface (front end) and World and its necessary classes (back end).


*View / Front End* </br>
Relevant classes: UserInterface




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
