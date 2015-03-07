package command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import turtle.Turtle;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;


public class CommandFactory {

    private ObservableMap<String, Double> variables;
    private ObservableMap<String, String> functions;
    private List<ObjectProperty> bindings;
    private ObservableList<Color> colors;
    private String language;

    private ResourceBundle translationMap;
    private ResourceBundle paramMap;
    protected ResourceBundle syntax;


    private static final double DEFAULT_START = 1;

    public CommandFactory(String l, ObservableMap<String, Double> var, ObservableMap<String, String> functions2, List<ObjectProperty> bind, ObservableList<Color> color)  {
        variables = var;
        functions = functions2;
        bindings = bind;
        language = l;
        colors = color;
        setTranslationMap();
        paramMap = ResourceBundle.getBundle("resources.parameters/numParameters");
        syntax = ResourceBundle.getBundle("resources.languages/Syntax");
    }

    private void setTranslationMap(){
        translationMap = ResourceBundle.getBundle("resources.languages/" + language);
    }

    public Command createCommand(List<String> parts){
        String command = parts.get(0);
        String cmd = translateCommand(command.toLowerCase());
        Command ret = reflectCMD(cmd, parts);
        return ret;
    }

    private Command reflectCMD(String cmd, List<String> parts){
        try {
            Class<?> commandClass = Class.forName("command." + cmd + "Command");
            Constructor<?> commandConstructor = commandClass.getConstructor(List.class, ObservableMap.class, ObservableMap.class, List.class, ObservableList.class);
            Command ret = (Command) commandConstructor.newInstance(parts, variables, functions, bindings, colors);
            return ret;

        } 
        catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid Command");
        }


    }

    private String translateCommand(String s){
        for(String key: translationMap.keySet()){
            String val = translationMap.getString(key);
            for (String sub: val.split(","))
                if(sub.equals(s))
                    return key;
            for(String sub: val.split("\\|"))
                if(sub.equals(s))
                    return key;
        }

        throw new IllegalArgumentException("Illegal Command");
    }

    public int getNumParameters(String s){
        return Integer.parseInt(paramMap.getString(translateCommand(s)));
    }

    public void setLanguage(String l) {
        language = l;
        setTranslationMap();
    }

    public void updateVariable(String s, Double d){
        variables.put(s,d);
    }


    public void clearRepCount(){
        variables.remove(":repCount");
    }

    public void resetRepcount(){
        variables.put(":repcount", DEFAULT_START);
    }
    
    public static void main(String[] args){
        CommandFactory cf = new CommandFactory("English", null, null, null, null);
        List<String> a = new ArrayList<>();
        a.add("dotimes");
        a.add(":x 2");
        Command c = cf.createCommand(a);
        //System.out.print(c.run(new Turtle()));
    }
    

}