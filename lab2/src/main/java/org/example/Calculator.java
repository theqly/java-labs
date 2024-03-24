package org.example;

import org.example.Exceptions.FileExceptions.CantCreateBuffReaderException;
import org.example.Exceptions.FileExceptions.LoggerConfigFileNotFoundException;
import org.example.Exceptions.FileExceptions.ScriptFileNotFoundException;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Calculator {
    public Calculator(String file){
        this.context = new Context();
        logger.log(Level.INFO, "Created context");
        this.factory = new Factory();
        logger.log(Level.INFO, "Created factory");
        this.file = file;
    }

    public void start() throws CalculatorException {
        Reader reader;
        if (Objects.equals(file, "System.in")) {
            reader = new InputStreamReader(System.in);
            logger.log(Level.INFO, "Created InputStreamReader");
        } else {
            try{
                reader = new FileReader(file);
            } catch (FileNotFoundException e){
                logger.log(Level.INFO, "Catch exception", e);
                throw new ScriptFileNotFoundException();
            }
            logger.log(Level.INFO, "Created FileReader by " + file);
        }

        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (Objects.equals(line, "q")) {
                    break;
                }
                execute(line);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Catch exception", e);
            throw new CantCreateBuffReaderException();
        }
    }

    private void execute(String line) throws CalculatorException {
        String[] splitLine = line.split("\\s+");
        String operationName = splitLine[0];
        String[] numbers = Arrays.copyOfRange(splitLine, 1, splitLine.length);
        Operation operation = factory.createOperation(operationName);
        operation.execute(context, numbers);
        logger.log(Level.INFO, "Executed operation " + operationName);
    }

    static {
        try(FileInputStream config = new FileInputStream("src/test/resources/logging.properties")){
            LogManager.getLogManager().readConfiguration(config);
            logger = Logger.getLogger(Calculator.class.getName());
        } catch (Exception e){
            throw new LoggerConfigFileNotFoundException();
        }
    }

    static Logger logger;
    private final Context context;
    private final Factory factory;
    private final String file;

}
