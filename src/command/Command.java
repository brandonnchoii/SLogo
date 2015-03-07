package command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;
import turtle.Turtle;

public abstract class Command {

    private static final String DEFAULT_START = "1";
    private static final String DEFAULT_END = "1";
    private static final String DEFAULT_INCR = "1";
    private static final String DEFAULT_VAR = ":repcount";
    protected List<String> strParameters;
    protected List<Double> parameters;
    protected Map<String, Double> variables;
    protected Map<String, String> functions;
    protected List<ObjectProperty> bindings;
    protected ObservableList<Color> colors;
    protected boolean loop;
    protected Map<String, String> commandValues; 
    protected ResourceBundle syntax;

    public Command(List<String> params, ObservableMap<String, Double> variableMap, ObservableMap<String, String> func, List<ObjectProperty> bind, ObservableList<Color> color) {
        strParameters = params;
        loop = false;
        syntax = ResourceBundle.getBundle("resources.languages/Syntax");
        createCommandValues();
        parameters = makeParameters();
        functions = func;
        bindings = bind;
        colors = color;
    }

    protected List<Double> makeParameters(){
        List<Double> params = new ArrayList<Double>();
        for (int i = 1; i < strParameters.size(); i ++){
            params.add(addParam(strParameters.get(i)));
        }
        checkParams(params);
        return params;
    }

    protected Double addParam(String s){
        if(isVariable(s)){
            return readVariable(s);
        }
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

    protected double readVariable(String s){
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

    private void checkParams(List<Double> params){
     
        if (params.contains(null)){
            throw new IllegalArgumentException("Parameters are invalid"); 
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

    public Map<String, Double> updateVariables(){
        return variables;
    }

    public Map<String, String> updateFunctions(){
        return functions;
    }

}       
