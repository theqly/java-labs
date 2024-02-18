package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Factory {
    public Factory(){
        this.operations = new HashMap<>();
        try {
            fillOperations();
        } catch (IOException e){
            System.err.println("Executing ended with exception: " + e.getMessage());
        }
    }

    public Operation createOperation(String operationName){
        Class<? extends Operation> operationClass = operations.get(operationName);
        if(operationClass == null){
            throw new RuntimeException("Cant find a class with name " + operationName);
        }
        try{
            return operationClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e){
            throw new RuntimeException("Executing ended with exception: " + e.getMessage());
        }
    }

    private void fillOperations() throws IOException{
        Properties properties = new Properties();
        try(InputStream inputStream = getClass().getResourceAsStream("/factory.properties")){
            properties.load(inputStream);
        }
        for(String operationName : properties.stringPropertyNames()){
            String className = properties.getProperty(operationName);
            try{
                Class<?> operationClass = Class.forName(className);
                if(Operation.class.isAssignableFrom(operationClass)){
                    operations.put(operationName, (Class<? extends Operation>) operationClass);
                } else {
                    throw new RuntimeException("Class " + operationName + " does not rebenok of Operation");
                }
            } catch (ClassNotFoundException e){
                System.err.println("Executing ended with exception: " + e.getMessage());
            }
        }
    }

    private Map<String, Class<? extends Operation>> operations;

}
