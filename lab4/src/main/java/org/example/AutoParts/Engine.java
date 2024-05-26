package org.example.AutoParts;

import java.util.concurrent.atomic.AtomicInteger;

public class Engine implements AutoPart{
    private static final AtomicInteger id_generator = new AtomicInteger(0);
    private final int id;

    public Engine(){
        this.id = id_generator.getAndIncrement();
    }

    public int getId(){
        return id;
    }
}
