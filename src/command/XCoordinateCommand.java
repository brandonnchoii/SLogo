package command;

import turtle.Turtle;

public class XCoordinateCommand extends Command{

    public XCoordinateCommand(){
        super();
    }

    @Override
    public double run(Turtle t) {
        return t.getTranslateX();
    }
}
