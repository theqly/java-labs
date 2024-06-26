package org.example.AutoParts;

import java.util.concurrent.atomic.AtomicInteger;

public class Body implements AutoPart{
    private static final AtomicInteger id_generator = new AtomicInteger(0);
    private final int id;

    public Body(){
        this.id = id_generator.getAndIncrement();
    }

    public int getId(){
        return id;
    }
}
