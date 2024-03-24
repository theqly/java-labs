package org.example.Operations;

import org.example.CalculatorException;
import org.example.Context;
import org.example.Exceptions.ScriptExceptions.StackIsEmpty;
import org.example.Operation;

public class Print implements Operation {
    @Override
    public void execute(Context context, String[] args) throws CalculatorException {
        if(context.getStack().isEmpty()){
            throw new StackIsEmpty();
        }
        System.out.println(context.getStack().peek());
    }
}
