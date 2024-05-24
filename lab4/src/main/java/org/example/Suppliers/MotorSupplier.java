package org.example.Suppliers;

import org.example.AutoParts.Accessory;
import org.example.AutoParts.Motor;

public class MotorSupplier extends Supplier<Motor>{
    public MotorSupplier(int delay){
        super(delay);
    }

    public Motor get(){
        try{
            Thread.sleep(getDelay());
        } catch (InterruptedException e){
            return null;
        }
        return new Motor();
    }
}
