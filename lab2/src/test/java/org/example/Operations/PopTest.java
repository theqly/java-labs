package org.example.Operations;

import org.example.CalculatorException;
import org.example.Context;
import org.example.Exceptions.ScriptExceptions.StackIsEmpty;
import org.junit.Test;

import static org.junit.Assert.*;

public class PopTest {
    @Test
    public void pop_test() throws CalculatorException {
        Context context = new Context();
        Pop pop = new Pop();
        context.getStack().push(5.0);
        String[] args = {"",""};
        pop.execute(context, args);
        assertEquals(context.getStack().size(), 0);
        assertThrows(StackIsEmpty.class, () -> pop.execute(context, args));
    }
}