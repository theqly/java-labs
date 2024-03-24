package org.example.Operations;

import org.example.CalculatorException;
import org.example.Context;
import org.example.Exceptions.ScriptException;
import org.example.Exceptions.ScriptExceptions.NotDefinedLetterPush;
import org.example.Exceptions.ScriptExceptions.NotEnoughArgs;
import org.example.Operation;

public class Push implements Operation {
    @Override
    public void execute(Context context, String[] args) throws CalculatorException {
        if(args.length == 0){
            throw new NotEnoughArgs();
        }
        double value;
        try{
            value = Double.parseDouble(args[0]);
        } catch (NumberFormatException e){
            if(context.getDefines().containsKey(args[0])){
                value = context.getDefines().get(args[0]);
            } else {
                throw new NotDefinedLetterPush();
            }
        }
        context.getStack().push(value);
    }
}
