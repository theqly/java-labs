package org.example.AutoParts;

import java.util.concurrent.atomic.AtomicInteger;

public class Accessory implements AutoPart{
    private static final AtomicInteger id_generator = new AtomicInteger(0);
    private final int id;

    public Accessory(){
        this.id = id_generator.getAndIncrement();
    }

    public int getId(){
        return id;
    }
}
