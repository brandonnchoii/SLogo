package command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import turtle.Turtle;


public class CommandFactory {

    private Map<String, Double> variables;
    private Map<String, String> functions;
    private String language;

    private ResourceBundle translationMap;
    private ResourceBundle paramMap;
    protected ResourceBundle syntax;


    private static final double DEFAULT_START = 1;

    public CommandFactory(String l)  {
        variables = new HashMap<>();
        language = l;
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
            Constructor<?> commandConstructor = commandClass.getConstructor(List.class, Map.class, Map.class);
            Command ret = (Command) commandConstructor.newInstance(parts, variables, functions);
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

 

    public void clearRepCount(){
        variables.remove(":repCount");
    }

    public void resetRepcount(){
        variables.put(":repcount", DEFAULT_START);
    }
    
    public static void main(String[] args){
        CommandFactory cf = new CommandFactory("English");
        List<String> parts = new ArrayList<String>();
        parts.add("sum");
        parts.add("50");
        parts.add("1");
        parts.add("2");
        
        Command c1 = cf.createCommand(parts);
        //Turtle t = new Turtle();
        //System.out.print(c1.run(t));
    }

}