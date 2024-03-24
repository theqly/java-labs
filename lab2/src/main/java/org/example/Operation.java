package org.example;

public interface Operation {
    public void execute(Context context, String[] args) throws CalculatorException;
}
