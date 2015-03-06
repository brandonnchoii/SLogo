package command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class CommandFactory {

    private Map<String, Double> variables;
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
            Constructor<?> commandConstructor = commandClass.getConstructor(List.class, Map.class);
            Command ret = (Command) commandConstructor.newInstance(parts, variables);
            return ret;

        } 
        catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
            return new ForCommand();
            //throw new IllegalArgumentException("The command is invalid");
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

}