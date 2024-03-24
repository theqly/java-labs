package org.example.Operations;

import org.example.CalculatorException;
import org.example.Context;
import org.example.Exceptions.ScriptExceptions.InsufficientStackSize;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddTest {
    @Test
    public void add_test() throws CalculatorException {
        Context context = new Context();
        Add add = new Add();
        context.getStack().push(5.0);
        context.getStack().push(5.0);
        String[] args = {"",""};
        add.execute(context, args);
        double res = context.getStack().peek();
        assertEquals(10.0, res, 0.01);
        assertThrows(InsufficientStackSize.class, () -> add.execute(context, args));
    }
}