package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws CalculatorException{
        Calculator calculator = new Calculator("in.txt");
        try {
            calculator.start();
        } catch (CalculatorException e){
            System.err.println(e.toString());
        }
    }
}