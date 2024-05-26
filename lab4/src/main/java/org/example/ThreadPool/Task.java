package org.example.ThreadPool;

import org.example.AutoParts.*;
import org.example.Storage.Storage;

public class Task{
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

    public void doTask(){
        Body body = bodyStorage.take();
        Engine engine = engineStorage.take();
        Accessory accessory = accessoryStorage.take();

        Auto auto = new Auto(body, engine, accessory);
        autoStorage.put(auto);
    }
}
