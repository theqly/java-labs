package org.example.Operations;

import org.example.Context;
import org.example.Operation;

public class Push implements Operation {
    @Override
    public void execute(Context context, String[] args){
        if(args.length == 0){
            throw new RuntimeException("no arguments for PUSH");
        }
        double value = context.getDefines().getOrDefault(args[0], -666.0);
        if(value == -666.0){
            try{
                value = Double.parseDouble(args[0]);
            } catch (Exception e){
                System.err.println("Executing ended with exception: " + e.getMessage());
            }
        }
        context.getStack().push(value);
    }
}
