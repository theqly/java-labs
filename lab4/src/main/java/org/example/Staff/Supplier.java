package org.example.Staff;

import org.example.AutoParts.AutoPart;
import org.example.Storage.Storage;

public class Supplier<T extends AutoPart> extends Thread{
    private final Storage<T> storage;
    private int delay;
    private java.util.function.Supplier<T> supplier;
    private boolean isWorking;

    public Supplier(Storage<T> storage, java.util.function.Supplier<T> supplier){
        this.storage = storage;
        this.supplier = supplier;
        this.isWorking = true;
    }

    @Override
    public void run(){
        try {
            while (!Thread.currentThread().isInterrupted() && isWorking) {
                Thread.sleep(delay);
                T item = supplier.get();
                storage.put(item);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stopWorking(){
        isWorking = false;
    }
    public void setDelay(int delay){
        this.delay = delay;
    }

}
