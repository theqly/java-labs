package org.example.Operations;

import org.example.CalculatorException;
import org.example.Context;
import org.example.Exceptions.ScriptExceptions.InsufficientStackSize;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubTest {
    @Test
    public void sub_test() throws CalculatorException {
        Context context = new Context();
        Sub sub = new Sub();
        context.getStack().push(5.0);
        context.getStack().push(5.0);
        String[] args = {"",""};
        sub.execute(context, args);
        double res = context.getStack().peek();
        assertEquals(0.0, res, 0.01);
        assertThrows(InsufficientStackSize.class, () -> sub.execute(context, args));
    }
}