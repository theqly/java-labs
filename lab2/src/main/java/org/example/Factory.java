package org.example;

import org.example.Exceptions.ScriptException;
import org.example.Exceptions.SeparateExceptions.CantCreateNewInstanceException;
import org.example.Exceptions.SeparateExceptions.ClassInFactoryNotFoundException;
import org.example.Exceptions.SeparateExceptions.FactoryCastException;
import org.example.Exceptions.FileExceptions.FactoryConfigFileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Factory {
    public Factory(){
        this.operations = new HashMap<>();
        fillOperations();
    }

    public Operation createOperation(String operationName){
        Class<? extends Operation> operationClass = operations.get(operationName);
        if(operationClass == null){
            throw new ClassInFactoryNotFoundException();
        }
        try{
            return operationClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e){
            throw new CantCreateNewInstanceException();
        }
    }

    private void fillOperations(){
        Properties properties = new Properties();
        try{
            InputStream inputStream = getClass().getResourceAsStream("/factory.properties");
            properties.load(inputStream);
        } catch (Exception e){
            throw new FactoryConfigFileNotFoundException();
        }

        for(String operationName : properties.stringPropertyNames()){
            String className = properties.getProperty(operationName);
            try{
                Class<?> operationClass = Class.forName(className);
                if(Operation.class.isAssignableFrom(operationClass)){
                    operations.put(operationName, (Class<? extends Operation>) operationClass);
                } else {
                    throw new FactoryCastException();
                }
            } catch (ClassNotFoundException e){
                throw new ClassInFactoryNotFoundException();
            }
        }
    }

    private Map<String, Class<? extends Operation>> operations;

}
