package org.example.Staff;

import org.example.AutoParts.Auto;
import org.example.Storage.Storage;

public class Dealer extends Thread{
    private final Storage<Auto> storage;
    private int delay;
    private int id;
    private boolean isWorking;
    public Dealer(Storage<Auto> storage, int id){
        this.storage = storage;
        this.id = id;
    }

    @Override
    public void run(){
        isWorking = true;
        double startTime = (double) System.currentTimeMillis() / 1000;
        try {
            while(!Thread.currentThread().isInterrupted() && isWorking){
                Thread.sleep(delay);
                Auto auto = storage.take();
                double curTime = (double) System.currentTimeMillis() / 1000 - startTime;
                System.out.println(curTime + " Dealer: " + id + " " + auto.getInfo());
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
