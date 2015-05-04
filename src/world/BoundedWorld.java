package world;

import java.io.IOException;
import java.util.List;
import command.Command;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import turtle.Turtle;
import javafx.beans.property.ObjectProperty;


public class BoundedWorld extends World {

    public BoundedWorld (ObservableMap<String, Double> variables,
                         ObservableMap<String, String> savedCommands,
                         List<ObjectProperty> bindings, ObservableList<Color> colors)
        throws IOException {
        super(variables, savedCommands, bindings, colors);
    }

    public BoundedWorld (ObservableMap<String, Double> variables,
                         ObservableMap<String, String> functions,
                         List<ObjectProperty> bindings, int h, int w,
                         ObservableList<Color> colors) throws IOException {
        super(h, w, variables, functions, bindings, colors);
    }

    public BoundedWorld (int h, int w, Turtle t, String l,
                         ObservableMap<String, Double> variables,
                         ObservableMap<String, String> functions,
                         List<ObjectProperty> bindings, ObservableList<Color> colors)
        throws IOException {
        super(h, w, t, l, variables, functions, bindings, colors);
    }

    @Override
    public void fixPosition (int turtleID) {
        Turtle turtle = myTurtles.get(turtleID);
        Point2D goal = turtle.getGoal();

        if (turtle.getBoundaryType() == 3) {
            System.out.println(goal.getX());
            System.out.println("height = " + height);
            if (goal.getX() >= width / 2) {
                turtle.moveTo(width / 2, goal.getY());
            }
            else if (goal.getX() <= -width / 2) {
                turtle.moveTo(-width / 2, goal.getY());
            }
            else if (turtle.getY() >= height / 2) {
                turtle.moveTo(goal.getY(), height / 2);
            }
            else if (turtle.getX() <= -height / 2) {
                turtle.moveTo(goal.getY(), -height / 2);
            }
        }
    }

    @Override
    protected String runCommand(Command command, int turtleID) {
        String param;
        try {
                param = myTurtles.get(turtleID).act(command);
                fixPosition(turtleID);
        } catch (Exception e) {
                myTurtles.put(turtleID, new Turtle(bindings, TURTLE_DEFAULT,
                                turtleID));
                param = myTurtles.get(turtleID).act(command);
        }
        return param;
}
}
