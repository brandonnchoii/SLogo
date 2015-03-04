package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class TellCommand extends LoopCommand{

    public TellCommand(){

    }

    public TellCommand(List<String> params){
        super(params);
    }
    @Override
    public double run(Turtle t) {
        if(parameters.contains(t.getID()))
            t.setActive(true);
        else
            t.setActive(false);
        return parameters.get(parameters.size()-1);
    }

    @Override
    protected void updateMap () {
    }

    @Override
    protected String makeString(Map<Integer, Turtle> turtles){
        String s = "";
        List<Integer> activeIDs = makeIDs();
        for (Turtle t: turtles.values()){
            if(activeIDs.contains(t.getID())){
                t.setActive(true);
                s += t.getID() + " ";
            }
            else
                t.setActive(false);
        }

        return s.trim();
    }

    private List<Integer> makeIDs(){
        List<Integer> ret = new ArrayList<>();
        String[] s = strParameters.get(1).split("\\p{Space}");
        for(String id: s)
            ret.add(Integer.parseInt(id));
        return ret;

    }
}

