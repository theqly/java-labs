package org.example.Operations;

import org.example.Context;
import org.example.Operation;

public class Sqrt implements Operation {
    @Override
    public void execute(Context context, String[] args) {
        if(context.getStack().isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
        double value = Math.sqrt(context.getStack().pop());
        context.getStack().push(value);
    }
}
