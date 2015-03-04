package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class AskCommand extends LoopCommand{

    public AskCommand(){

    }

    public AskCommand(List<String> params, Map<String, Double> variables){
        super(params, variables);
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
    
    protected ArrayList<Double> createParameters(){
        return new ArrayList<Double>();
    }
    @Override
    protected String makeString(Map<Integer, Turtle> turtles){
        String s = "";
        List<Integer> activeIDs = makeIDs();
        for (Turtle t: turtles.values())
            if(activeIDs.contains(t.getID()))
                s += t.getID() + " ";

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