#SLogo Addition

*Brandon Choi, Team 07*

##Estimation

I think that adding the new front end addition will take me about an hour and a half. I already previously implemented the changing of the turtle's image so I will now have to move the code around to create a UI component that performs the same function. I predict that I will have to add about three or four more classes in order to create the new view and integrate it with the rest of my code. I don't see this as too difficult of a task but it should take a few classes just to ensure proper design. I do think I will have to update the TopMenu class that I wrote in order to add this feature into the rest of the menu I already have. This way, I am just adding onto a feature I already made and I do not have to make an entirely new UI. The menu bar already holds the code I need to change the turtle image so I think that moving that code around to the image picker view should accomplish the task pretty easily.


##Review

It took me about forty five minutes to and hour to complete this new feature. The new classes I wrote are in the imagePicker package and are the ImagePicker and ImagePickerUnit classes. I almost got it completely right on the first try but had to work with the selected image's path due to invalid path errors when using getAbsolutePath(). Once I finished correcting the String path, the Turtle changed images correctly but my view did not. From there, I had to bind the imageview on my view to the same object property that the turtle was bound to. This way, changing my turtle image also changed the graphic on my new view box. To accomplish adding this new feature, I had to write the two new classes and update my TopMenu class as well as predicted above in Estimation. The main addition to the TopMenu class was the setUpImagePicker() method in which I call the image picker to display upon clicking the new menu item.

##Analysis

After VOOGASalad, I realized how poorly designed some portions of my SLogo were. Some parts of it such as the image binding was conceptually impressive but the actual implementation of them were very poorly planned out. A lot of my classes combined front and back end far too often and so the two portions could've been split up better as I did in VOOGA. Overall, looking at the TopMenu class, I realize that some of my classes were way too long. TopMenu was 300 lines long and was difficult to navigate through even though I had written the class myself. I should've made separate classes for different Menus and used TopMenu as more of a container of those components. 

If I were not familiar with the code at all, I think it would've taken me some time to figure out how exactly the image properties were binded to the back end turtle. I was able to successfully implement this feature quickly because I myself implemented the binding during the project. Therefore, I knew exactly how and where the binding was occuring between the front and back end image properties. I realize that I should've commented more areas of my code to specifically indicate where certain nodes were being created and what the overall class was accomplishing. 
