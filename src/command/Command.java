package command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import turtle.Turtle;

public abstract class Command {

    private static final String DEFAULT_START = "1";
    private static final String DEFAULT_END = "2";
    private static final String DEFAULT_INCR = "1";
    private static final String DEFAULT_VAR = ":repcount";
    protected List<String> strParameters;
    protected List<Double> parameters;
    protected Map<String, Double> variables;
    protected boolean loop;
    protected Map<String, String> commandValues; 
    private ResourceBundle syntax;

    public Command(){
        strParameters = new ArrayList<String>();
        checkParams();
        loop = false;
        createCommandValues();
    }

    public Command(List<String> params, Map<String, Double> variableMap) {
        strParameters = params;
        loop = false;
        syntax = ResourceBundle.getBundle("resources.languages/Syntax");
        createCommandValues();
        parameters = makeParameters();
        
       

    }
    
    protected List<Double> makeParameters(){
        List<Double> params = new ArrayList<Double>();
        for (int i = 1; i < strParameters.size(); i ++){
            params.add(addParam(strParameters.get(i)));
        }

        return params;
    }
    
    private Double addParam(String s){
        if(isVariable(s))
            return readVariable(s);
        else
            try{
                return Double.parseDouble(s);
            }
        catch(NumberFormatException e){
            return null;
        }
    }

    private boolean isVariable(String s){ 
        return s.matches(syntax.getString("Variable"));
    }

    private double readVariable(String s){
        Double d = variables.get(s);
        if(d != null)
            return variables.get(s);
        else
            throw new IllegalArgumentException("Illegal variable name");
    }

    public void updateVariable(String varName, double val){
        variables.put(varName,val);
    }

    protected void createCommandValues(){
        commandValues = new HashMap<>();
        commandValues.put("loopStart", DEFAULT_START);
        commandValues.put("loopEnd", DEFAULT_END);
        commandValues.put("loopIncrement", DEFAULT_INCR);
        commandValues.put("loopVariable", DEFAULT_VAR);
        commandValues.put("ifStatement", DEFAULT_START);
    }

    public abstract double run(Turtle t);

    public boolean isLoop(){
        return loop;
    }

    private void checkParams(){
        if (parameters.contains(null)){
            throw new IllegalArgumentException("The parameters are invalid"); 
        }
    }

    public Map<String, String> getCommandValues(Map<Integer,Turtle> turtles){
        commandValues.put("turtlesToAct", makeString(turtles));
        return commandValues;
    }

    protected String makeString(Map<Integer, Turtle> turtles){
        String s = "";
        for (Turtle t: turtles.values())
            if(t.isActive())
                s += t.getID() + " ";
        return s.trim();
    }

}       
