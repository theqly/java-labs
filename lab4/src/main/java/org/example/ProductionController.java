package org.example;

import org.example.AutoParts.Accessory;
import org.example.AutoParts.Auto;
import org.example.AutoParts.Body;
import org.example.AutoParts.Engine;
import org.example.Storage.Storage;
import org.example.ThreadPool.Task;
import org.example.ThreadPool.ThreadPool;

public class ProductionController extends Thread{
    private final Storage<Auto> autoStorage;
    private final Storage<Body> bodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Accessory> accessoryStorage;
    private ThreadPool workers;
    private boolean isWorking;

    public ProductionController(Storage<Auto> autoStorage, Storage<Body> bodyStorage, Storage<Engine> engineStorage, Storage<Accessory> accessoryStorage, ThreadPool workers, int delay){
        this.autoStorage = autoStorage;
        this.bodyStorage = bodyStorage;
        this.engineStorage = engineStorage;
        this.accessoryStorage = accessoryStorage;
        this.workers = workers;
    }

    @Override
    public void run(){
        isWorking = true;
        try {
            while (!Thread.currentThread().isInterrupted() && isWorking){
                synchronized (autoStorage){
                    while(workers.getTasksSize() >= autoStorage.getFreeCapacity()){
                        autoStorage.wait();
                    }
                    int tasksToFull = autoStorage.getFreeCapacity() - workers.getTasksSize();
                    for(int i = 0; i < tasksToFull; ++i){
                        workers.addTask(new Task(bodyStorage, engineStorage, accessoryStorage, autoStorage));
                    }
                    autoStorage.notifyAll();
                }
            }
        } catch (InterruptedException e){
            e.printStackTrace();
            workers.stopWorking();
        }
    }

    public void stopWorking(){
        isWorking = false;
    }
}
