package org.example.ThreadPool;

import org.example.AutoParts.*;
import org.example.Storage.Storage;

public class Task implements Runnable{
    private final Storage<Body> bodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Auto> autoStorage;

    public Task(Storage<Body> bodyStorage, Storage<Engine> engineStorage,
                      Storage<Accessory> accessoryStorage, Storage<Auto> autoStorage) {
        this.bodyStorage = bodyStorage;
        this.engineStorage = engineStorage;
        this.accessoryStorage = accessoryStorage;
        this.autoStorage = autoStorage;
    }

    @Override
    public void run() {
        try {
            Body body;
            Engine engine;
            Accessory accessory;

            synchronized (bodyStorage) {
                while (bodyStorage.getSize() == 0) {
                    bodyStorage.wait();
                }
                body = bodyStorage.take();
            }

            synchronized (engineStorage) {
                while (engineStorage.getSize() == 0) {
                    engineStorage.wait();
                }
                engine = engineStorage.take();
            }

            synchronized (accessoryStorage) {
                while (accessoryStorage.getSize() == 0) {
                    accessoryStorage.wait();
                }
                accessory = accessoryStorage.take();
            }

            Auto auto = new Auto(body, engine, accessory);
            synchronized (autoStorage) {
                while (autoStorage.getSize() >= autoStorage.getCapacity()) {
                    autoStorage.wait();
                }
                autoStorage.put(auto);
                autoStorage.notifyAll();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
