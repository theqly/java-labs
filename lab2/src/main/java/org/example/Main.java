package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Calculator calculator = new Calculator("in.txt");
        calculator.start();
    }
}