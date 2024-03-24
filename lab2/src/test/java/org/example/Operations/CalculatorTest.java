package org.example.Operations;

import org.example.Calculator;
import org.example.CalculatorException;
import org.example.Context;
import org.example.Exceptions.ScriptExceptions.StackIsEmpty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CalculatorTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void restoreStream() {
        System.setOut(originalOut);
    }

    @Test
    public void calculator_test() throws CalculatorException {
        Calculator calculator = new Calculator("src/test/resources/test.txt");
        try {
            calculator.start();
        } catch (CalculatorException e){
            System.err.println(e.getMessage());
        }
        assertEquals("12.0", outputStream.toString().trim());
    }
}
