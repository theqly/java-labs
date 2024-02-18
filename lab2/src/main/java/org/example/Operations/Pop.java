package org.example.Operations;

import org.example.Context;
import org.example.Operation;

public class Pop implements Operation {
    @Override
    public void execute(Context context, String[] args){
        if(context.getStack().isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
        context.getStack().pop();
    }
}
