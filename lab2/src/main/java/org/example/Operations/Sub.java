package org.example.Operations;

import org.example.CalculatorException;
import org.example.Context;
import org.example.Exceptions.ScriptExceptions.InsufficientStackSize;
import org.example.Operation;

public class Sub implements Operation{
    @Override
    public void execute(Context context, String[] args) throws CalculatorException {
        if(context.getStack().size() < 2){
            throw new InsufficientStackSize();
        }
        double value = context.getStack().pop() - context.getStack().pop();
        context.getStack().push(value);
    }
}
