package command;

import turtle.Turtle;

public class YCoordinateCommand extends Command{

    public YCoordinateCommand(){
        super();
    }

    @Override
    public double run(Turtle t) {
        return t.getTranslateY();
    }
}
