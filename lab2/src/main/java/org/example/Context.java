package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Context {
    public Context(){
        this.stack = new Stack<>();
        this.defines = new HashMap<>();
    }

    public Stack<Double> getStack(){
        return stack;
    }

    public Map<String, Double> getDefines(){
        return defines;
    }

    private Stack<Double> stack;
    private Map<String, Double> defines;
}
