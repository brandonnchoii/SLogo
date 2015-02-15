#Design Document for SLogo
**Team 7: Megan Gutter, Brandon Choi, James Mosca, Thomas Bagley**

##Introduction
The goal of creating this project is essentially to create a basic IDE. In terms of UI, the goal is to create a user interface in which it is clear where commands are to be typed, and make it easy for the user to write new functions. The goal for the back end is to be able to correctly handle and execute function inputs. We want to add as little code as possible when we enable the back end to handle new, different types of commands. 

Our UI needs to be very flexible in terms of handling string input. The user is allowed to type whatever commands he/she wants. Once this input is handled on the back end, the WorldView window displays whatever output its told to. Also, new GUI features should be easy to add if needed. We can create this flexibility by having different objects for each of the windows of our front end(WorldView, CommandBox). UI flexibility will be tied to our ability to change what the user sees and how the user is able to interact with the program. 

The back end needs to be designed so it's very open for extension. We need to constantly be able to add new functionality so that we can handle different types of user input. Our back end needs to be able to correctly execute functions based on the original string input that the back end receives. 

The parser is the middle ground between the input and the function execution. It can handle any string input and throw an error if syntax is determined to be incorrect. No string input should break the program. 

##Overview


##User Interface


##Design Details


##API Example code


##Design Considerations 


##Team Responsibilities


