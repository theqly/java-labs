package org.example.AutoParts;

public abstract class AutoPart {
    private final int id;
    private static int counter;
    public AutoPart(){
        id = counter++;
    }

    public int getId(){
        return id;
    }
}
