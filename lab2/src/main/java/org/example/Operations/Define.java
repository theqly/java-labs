package org.example.Operations;

import org.example.Context;
import org.example.Operation;

public class Define implements Operation {
    @Override
    public void execute(Context context, String[] args){
        if(args.length < 2){
            throw new RuntimeException("Not enough arguments for DEFINE");
        }
        try{
            context.getDefines().put(args[0], Double.parseDouble(args[1]));
        } catch (Exception e){
            System.err.println("Executing ended with exception: " + e.getMessage());
        }
    }
}
