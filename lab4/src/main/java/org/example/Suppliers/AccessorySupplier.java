package org.example.Suppliers;

import org.example.AutoParts.Accessory;
import org.example.AutoParts.AutoPart;

public class AccessorySupplier extends Supplier<Accessory>{
    public AccessorySupplier(int delay){
        super(delay);
    }

    public Accessory get(){
        try{
            Thread.sleep(getDelay());
        } catch (InterruptedException e){
            return null;
        }
        return new Accessory();
    }
}
