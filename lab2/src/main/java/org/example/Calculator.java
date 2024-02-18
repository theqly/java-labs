package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Calculator {
    public Calculator(String file){
        this.context = new Context();
        this.factory = new Factory();
        this.file = file;
    }

    public void start() throws IOException {
        if(Objects.equals(file, "System.in")){
            Scanner scanner = new Scanner(System.in);
            while (true){
                String line = scanner.nextLine();
                if(Objects.equals(line, "q")){
                    break;
                }
                execute(line);
            }
        } else {
            try(BufferedReader reader = new BufferedReader(new FileReader(file))){
                String line;
                while((line = reader.readLine()) != null){
                    execute(line);
                }
            }
        }
    }

    private void execute(String line){
        String[] splitLine = line.split("\\s+");
        String operationName = splitLine[0];
        String[] numbers = Arrays.copyOfRange(splitLine, 1, splitLine.length);
        try{
            Operation operation = factory.createOperation(operationName);
            operation.execute(context, numbers);
        } catch (Exception e){
            System.err.println("Executing ended with exception: " + e.getMessage());
        }
    }

    private Context context;
    private Factory factory;
    private String file;

}
