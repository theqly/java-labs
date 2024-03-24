package org.example.Operations;

import org.example.CalculatorException;
import org.example.Context;
import org.example.Exceptions.ScriptExceptions.StackIsEmpty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class PrintTest {

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
    public void print_test() throws CalculatorException {
        Context context = new Context();
        Print print = new Print();
        context.getStack().push(5.0);
        String[] args = {"",""};
        print.execute(context, args);
        assertEquals("5.0", outputStream.toString().trim());
        context.getStack().pop();
        assertThrows(StackIsEmpty.class, () -> print.execute(context, args));
    }
}