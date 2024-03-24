package org.example.Operations;

import org.example.CalculatorException;
import org.example.Context;
import org.example.Exceptions.ArithmeticExceptions.SqrtOfNegative;
import org.example.Exceptions.ScriptExceptions.StackIsEmpty;
import org.example.Operation;

public class Sqrt implements Operation {
    @Override
    public void execute(Context context, String[] args) throws CalculatorException {
        if(context.getStack().isEmpty()){
            throw new StackIsEmpty();
        }
        double tmp = context.getStack().pop();
        if(tmp < 0.0){
            throw new SqrtOfNegative();
        }
        double value = Math.sqrt(tmp);
        context.getStack().push(value);
    }
}
