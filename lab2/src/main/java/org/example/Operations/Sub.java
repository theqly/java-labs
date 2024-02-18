package org.example.Operations;

import org.example.Context;
import org.example.Operation;

public class Sub implements Operation{
    @Override
    public void execute(Context context, String[] args) {
        if(context.getStack().size() < 2){
            throw new RuntimeException("Not enough args for operation -");
        }
        double value = context.getStack().pop() - context.getStack().pop();
        context.getStack().push(value);
    }
}
