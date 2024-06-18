package org.example.AutoParts;

import java.util.concurrent.atomic.AtomicInteger;

public class Auto {
    private final Body body;
    private final Engine engine;
    private final Accessory accessory;
    private final static AtomicInteger id_generator = new AtomicInteger(0);
    private final int id;

    public Auto(Body body, Engine engine, Accessory accessory){
        this.body = body;
        this.engine = engine;
        this.accessory = accessory;
        this.id = id_generator.getAndIncrement();
    }

    public String getInfo(){
        return String.format("Auto: %d (Body: %d, Engine: %d, Accessory: %d)", this.id, body.getId(), engine.getId(), accessory.getId());
    }
}
