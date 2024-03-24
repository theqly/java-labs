package org.example.Operations;

import org.example.CalculatorException;
import org.example.Context;
import org.example.Exceptions.ArithmeticExceptions.DivisionByZero;
import org.example.Exceptions.ScriptExceptions.InsufficientStackSize;
import org.example.Exceptions.ScriptExceptions.NotEnoughArgs;
import org.example.Operation;

public class Div implements Operation {
    @Override
    public void execute(Context context, String[] args) throws CalculatorException {
        if(context.getStack().size() < 2){
            throw new InsufficientStackSize();
        }
        double dividend = context.getStack().pop();
        double divider = context.getStack().pop();
        if(divider == 0.0){
            throw new DivisionByZero();
        }
        double value = dividend / divider;
        context.getStack().push(value);
    }
}
