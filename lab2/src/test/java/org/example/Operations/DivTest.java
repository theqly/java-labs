package org.example.Operations;

import org.example.CalculatorException;
import org.example.Context;
import org.example.Exceptions.ArithmeticExceptions.DivisionByZero;
import org.example.Exceptions.ScriptExceptions.InsufficientStackSize;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DivTest {
    @Test
    public void div_test() throws CalculatorException {
        Context context = new Context();
        Div div = new Div();
        context.getStack().push(5.0);
        context.getStack().push(5.0);
        String[] args = {"",""};
        div.execute(context, args);
        double res = context.getStack().pop();
        assertEquals(1.0, res, 0.01);
        context.getStack().push(0.0);
        context.getStack().push(10.0);
        assertThrows(DivisionByZero.class, () -> div.execute(context, args));
        assertThrows(InsufficientStackSize.class, () -> div.execute(context, args));
    }
}