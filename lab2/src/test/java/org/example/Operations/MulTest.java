package org.example.Operations;

import org.example.CalculatorException;
import org.example.Context;
import org.example.Exceptions.ScriptExceptions.InsufficientStackSize;
import org.junit.Test;

import static org.junit.Assert.*;

public class MulTest {
    @Test
    public void mul_test() throws CalculatorException {
        Context context = new Context();
        Mul mul = new Mul();
        context.getStack().push(5.0);
        context.getStack().push(5.0);
        String[] args = {"",""};
        mul.execute(context, args);
        double res = context.getStack().peek();
        assertEquals(25.0, res, 0.01);
        assertThrows(InsufficientStackSize.class, () -> mul.execute(context, args));
    }
}