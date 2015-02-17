#Design Document for SLogo
**Team 7: Megan Gutter, Brandon Choi, James Mosca, Thomas Bagley**

##Introduction
The goal of creating this project is essentially to create a basic IDE. In terms of UI, the goal is to create a user interface in which it is clear where commands are to be typed, and make it easy for the user to write new functions. The goal for the back end is to be able to correctly handle and execute function inputs. We want to add as little code as possible when we enable the back end to handle new, different types of commands. 

Our UI needs to be very flexible in terms of handling string input. The user is allowed to type whatever commands he/she wants. Once this input is handled on the back end, the WorldView window displays whatever output its told to. Also, new GUI features should be easy to add if needed. We can create this flexibility by having different objects for each of the windows of our front end(WorldView, CommandBox). UI flexibility will be tied to our ability to change what the user sees and how the user is able to interact with the program. 

The back end needs to be designed so it's very open for extension. We need to constantly be able to add new functionality so that we can handle different types of user input. Our back end needs to be able to correctly execute functions based on the original string input that the back end receives. 

Our World class takes on the role of the middle ground between the input and the visualization of the input. It can handle any string input and an error is thrown if syntax is determined to be incorrect. The World class also plays a role in the back end of our program. The parser is called by World in order to handle its string input. World isn't explicitly a controller class, because it takes on back end roles, but it performs a similar function by passing things between front end and back end. 

##Overview


##User Interface
At the most basic level there are two components to the user interface. There is a space that allows the user to write Logo code, and there is a window which displays the results of running the code. The user can interact only with the space for writing code. First, there is a text box/field that allows the user to type actual code. The user can select custom settings such as line color, turtle image, line thickness, and background display color, and language. There are also options available that allow users to select recent commands, user-defined commands, and currently-saved variables available. These are features available for user convenience. 

Error reporting is given in the form of a popup dialog box on screen. One way in which an error is generated is by attempting to run invalid code. This is defined as attempting to run code with invalid syntax, such that our parser can't make the data into a command. Invalid code can be achieved by typing out incorrect code or running code that might have been saved for later use by the user. Attempting to run the program when there is no code would be another example of invalid code. An error message is shown relaying this to the user. 

##Design Details


##API Example code


##Design Considerations 


##Team Responsibilities


