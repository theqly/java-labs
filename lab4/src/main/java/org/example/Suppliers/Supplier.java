package org.example.Suppliers;

import org.example.AutoParts.AutoPart;

public abstract class Supplier<T extends AutoPart>{
    private int delay;
    public Supplier(int delay){
        this.delay = delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }
}
