package org.example.Suppliers;

import org.example.AutoParts.Accessory;
import org.example.AutoParts.Body;

public class BodySupplier extends Supplier<Body>{
    public BodySupplier(int delay){
        super(delay);
    }

    public Body get(){
        try{
            Thread.sleep(getDelay());
        } catch (InterruptedException e){
            return null;
        }
        return new Body();
    }
}
