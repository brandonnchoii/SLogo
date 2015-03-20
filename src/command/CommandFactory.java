//This entire file is part of my masterpiece.
//Thomas Bagley

package command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;

/**
 * CommandFactory.java
 * @author Thomas Bagley
 * Takes a List of Strings representing a command and its parameters and returns an executable command
 */
public class CommandFactory {

    private ObservableMap<String, Double> variables;
    private ObservableMap<String, String> functions;
    private String language;

    private ResourceBundle translationMap;
    private ResourceBundle paramMap;
    protected ResourceBundle syntax;
        
    private List<ObjectProperty> bindings;
    private ObservableList<Color> colors;

    private static final String LANGUAGE_RESOURCE = "resources.language/";
    private static final String PARAMETER_RESOURCE = "resources.parameters/";
    private static final String COMMAND_EXCEPTION = "Invalid Command";
    private static final String VARIABLE = "Variable";
    private static final String VARIABLE_EXCEPTION = "Illegal Variable Name";
    private static final double DEFAULT_START = 1;
    
    /**
     * The constructor for Command Factory
     * @param l The language for commands
     * @param var An ObservableMap to store the variables currently recognized by the program
     * @param functions2 An ObservableMap to store the user-defined functions currently recognized by the program
     * @param bind A List of ObjectProperties for the User Interface (Never fully implemented)
     * @param color A List of Colors for the User Interface (Never fully implemented)
     */
    public CommandFactory(String l, ObservableMap<String, Double> var, ObservableMap<String, String> functions2, List<ObjectProperty> bind, ObservableList<Color> color)  {
        variables = var;
        functions = functions2;
        bindings = bind;
        language = l;
        colors = color;
        setTranslationMap();
        paramMap = ResourceBundle.getBundle(PARAMETER_RESOURCE + "numParameters");
        syntax = ResourceBundle.getBundle(LANGUAGE_RESOURCE + "Syntax");
    }
    
    /**
     * Called when the language is updated so that commands are recognized in the new language
     */
    private void setTranslationMap(){
        translationMap = ResourceBundle.getBundle(LANGUAGE_RESOURCE + language);
    }
    
    /**
     * The core functionality of the program - takes a list of Strings and returns a Command
     * @param parts A list representing first the command and then its parameters
     * @return The executable command called by the user (or throws an exception)
     */
    public Command createCommand(List<String> parts){
        String command = parts.get(0);
        String cmd = translateCommand(command.toLowerCase());
        Command ret = reflectCMD(cmd, parts);
        return ret;
    }
    
    /**
     * Takes the English string representing the command and the parameters, and returns the appropriate command
     * @param cmd The command's English representation
     * @param parts The list of the command and its parameters, processed in command
     * @return The appropriate command with correct parameters and properties (or throws an exception)
     */
    private Command reflectCMD(String cmd, List<String> parts){
        try {
            Class<?> commandClass = Class.forName("command." + cmd + "Command");
            Constructor<?> commandConstructor = commandClass.getConstructor(List.class, ObservableMap.class, ObservableMap.class, List.class, ObservableList.class);
            Command ret = (Command) commandConstructor.newInstance(parts, variables, functions, bindings, colors);
            return ret;

        } 
        catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(COMMAND_EXCEPTION);
        }


    }

    /**
     * Takes a string in any language and translates it to its English representation
     * @param s The String representing the command
     * @return The String for the English representation
     */
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

    /**
     * Returns the number of parameters for a given command type
     * @param s The English string representing the command
     * @return The number of parameters for that command
     */
    public int getNumParameters(String s){
        return Integer.parseInt(paramMap.getString(translateCommand(s)));
    }

    /**
     * Updates the language in which the CommandFactory reads commands
     * @param l The new language
     */
    public void setLanguage(String l) {
        language = l;
        setTranslationMap();
    }
    
    /**
     * Adds or updates a variable in the map
     * @param s The variable name (must start with ":")
     * @param d
     */
    public void updateVariable(String s, Double d){
        if(s.matches(syntax.getString(VARIABLE)))
            variables.put(s,d);
        else
            throw new IllegalArgumentException(VARIABLE_EXCEPTION);
       
    }

    /**
     * Resets the value of the variable repcount, used to track the number of runs for general commands and the repeat command.
     */
    public void resetRepcount(){
        variables.put(":repcount", DEFAULT_START);
    }
    
}