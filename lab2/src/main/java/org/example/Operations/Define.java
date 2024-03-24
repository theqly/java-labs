package org.example.Operations;

import org.example.CalculatorException;
import org.example.Context;
import org.example.Exceptions.ScriptException;
import org.example.Exceptions.ScriptExceptions.NotEnoughArgs;
import org.example.Operation;

public class Define implements Operation {
    @Override
    public void execute(Context context, String[] args) throws CalculatorException {
        if(args.length < 2){
            throw new NotEnoughArgs();
        }
        try{
            context.getDefines().put(args[0], Double.parseDouble(args[1]));
        } catch (Exception e){
            throw new ScriptException();
        }
    }
}
