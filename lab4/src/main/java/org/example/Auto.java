package org.example;

import org.example.AutoParts.*;

public class Auto {
    private final Body body;
    private final Motor motor;
    private final Accessory accessory;
    private final int id;
    private static int counter;

    public Auto(Body body, Motor motor, Accessory accessory){
        this.body = body;
        this.motor = motor;
        this.accessory = accessory;
        id = counter++;
    }

    public String getInfo(){
        return String.format("Auto: %d (Body: %d, Motor %d, Accessory: %d)", id, body.getId(), motor.getId(), accessory.getId());
    }
}
