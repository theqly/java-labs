package org.example.Operations;

import org.example.CalculatorException;
import org.example.Context;
import org.example.Exceptions.ScriptException;
import org.example.Exceptions.ScriptExceptions.NotEnoughArgs;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefineTest {
    @Test
    public void define_test() throws CalculatorException {
        Context context = new Context();
        Define define = new Define();
        String[] args1 = {"a", "16.0"};
        define.execute(context, args1);
        Push push = new Push();
        String[] args2 = {"a", "b"};
        push.execute(context, args2);
        double res = context.getStack().peek();
        assertEquals(16.0, res, 0.01);
        String[] args3 = {"bu"};
        assertThrows(NotEnoughArgs.class, () -> define.execute(context, args3));
        assertThrows(ScriptException.class, () -> define.execute(context, args2));
    }
}