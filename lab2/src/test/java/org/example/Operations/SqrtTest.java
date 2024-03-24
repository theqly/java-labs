package org.example.Operations;

import org.example.CalculatorException;
import org.example.Context;
import org.example.Exceptions.ArithmeticExceptions.SqrtOfNegative;
import org.example.Exceptions.ScriptExceptions.StackIsEmpty;
import org.junit.Test;

import static org.junit.Assert.*;

public class SqrtTest {
    @Test
    public void sqrt_test() throws CalculatorException {
        Context context = new Context();
        Sqrt sqrt = new Sqrt();
        context.getStack().push(16.0);
        String[] args = {"",""};
        sqrt.execute(context, args);
        double res = context.getStack().peek();
        assertEquals(4.0, res, 0.01);
        context.getStack().pop();
        assertThrows(StackIsEmpty.class, () -> sqrt.execute(context, args));
        context.getStack().push(-16.0);
        assertThrows(SqrtOfNegative.class, () -> sqrt.execute(context, args));

    }
}