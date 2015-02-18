package factories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import command.Command;

public class CommandFactory {

    private Map<String, Double> variables;
    
    public CommandFactory() {
        variables = new HashMap<>();
    }
    
    public Command createCommand(String s){
    	List<Double> params = new ArrayList<Double>();
        String[] parts = s.split(" ");
        String command = parts[0];
        params = makeParams(parts);
        if (params.contains(null)){
        	throw new IllegalArgumentException("The parameters are invalid"); 
        }
   
        
        return null;
    }
    
    private List<Double> makeParams(String[] parts){
    	List<Double> params = new ArrayList<Double>();
    	 for (int i = 1; i < parts.length; i ++){
         	params.add(addParam(parts[i]));
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
    
    private boolean isVariable(String s){ //Check based on regex
    	return false;
    }
    
    private double readVariable(String s){
    	return variables.get(s);
    }
    
    public static void main(String[] args){
    	Map<String, Double> s = new HashMap<String, Double>();
    	s.put("S", 1.0);
    	System.out.print(Double.parseDouble("S"));
    }
}
