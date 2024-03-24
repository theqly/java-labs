package org.example.Operations;

import org.example.CalculatorException;
import org.example.Context;
import org.example.Exceptions.ScriptExceptions.NotDefinedLetterPush;
import org.example.Exceptions.ScriptExceptions.NotEnoughArgs;
import org.junit.Test;

import javax.script.ScriptException;

import static org.junit.Assert.*;

public class PushTest {
    @Test
    public void push_test() throws CalculatorException {
        Context context = new Context();
        Push push = new Push();
        String[] args = {"16.0"};
        push.execute(context, args);
        double res = context.getStack().peek();
        assertEquals(16.0, res, 0.01);
        args[0] = "a";
        assertThrows(NotDefinedLetterPush.class, () -> push.execute(context,args));
        String[] a = {};
        assertThrows(NotEnoughArgs.class, () -> push.execute(context, a));
    }
}