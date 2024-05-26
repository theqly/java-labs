package org.example;

import org.example.AutoParts.Accessory;
import org.example.AutoParts.Auto;
import org.example.AutoParts.Body;
import org.example.AutoParts.Engine;
import org.example.Staff.Dealer;
import org.example.Staff.Supplier;
import org.example.Storage.Storage;
import org.example.ThreadPool.ThreadPool;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;

public class Factory{
    private final ArrayList<Dealer> dealers;
    private final ThreadPool workers;
    private final ProductionController productionController;
    private final ArrayList<Supplier<Body>> bodySuppliers;
    private final ArrayList<Supplier<Engine>> engineSuppliers;
    private final ArrayList<Supplier<Accessory>> accessorySuppliers;
    private final Storage<Auto> autoStorage;
    private final Storage<Body> bodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Accessory> accessoryStorage;
    private final int delay;
    private static final Properties properties = new Properties();

    public Factory(String config){
        delay = 1000;

        try{
            File file = new File(config);
            properties.load(new FileReader(file));
        } catch (IOException e){
            e.printStackTrace();
        }

        dealers = new ArrayList<>();
        workers = new ThreadPool(Integer.parseInt(properties.getProperty("Workers")), delay);
        bodySuppliers = new ArrayList<>();
        engineSuppliers = new ArrayList<>();
        accessorySuppliers = new ArrayList<>();

        autoStorage = new Storage<>(Integer.parseInt(properties.getProperty("AutoStorageSize")));
        bodyStorage = new Storage<>(Integer.parseInt(properties.getProperty("BodyStorageSize")));
        engineStorage = new Storage<>(Integer.parseInt(properties.getProperty("EngineStorageSize")));
        accessoryStorage = new Storage<>(Integer.parseInt(properties.getProperty("AccessoryStorageSize")));

        productionController = new ProductionController(autoStorage, bodyStorage, engineStorage, accessoryStorage, workers, delay);

        createStaff();
        startWork();
    }

    private void startWork(){
        for(Dealer dealer : dealers) dealer.start();
        for (Supplier<Body> bodySupplier : bodySuppliers) bodySupplier.start();
        for (Supplier<Engine> engineSupplier : engineSuppliers) engineSupplier.start();
        for (Supplier<Accessory> accessorySupplier : accessorySuppliers) accessorySupplier.start();
        productionController.start();
    }


    public void stopWork(){
        for(Dealer dealer : dealers) dealer.stopWorking();
        for (Supplier<Body> bodySupplier : bodySuppliers) bodySupplier.stopWorking();
        for (Supplier<Engine> engineSupplier : engineSuppliers) engineSupplier.stopWorking();
        for (Supplier<Accessory> accessorySupplier : accessorySuppliers) accessorySupplier.stopWorking();
        workers.stopWorking();
        productionController.stopWorking();
    }


    private void createStaff(){
        createDealers(Integer.parseInt(properties.getProperty("Dealers")));
        createBodySuppliers(Integer.parseInt(properties.getProperty("BodySuppliers")));
        createEngineSuppliers(Integer.parseInt(properties.getProperty("EngineSuppliers")));
        createAccessorySuppliers(Integer.parseInt(properties.getProperty("AccessorySuppliers")));
    }

    private void createDealers(int dealerCount){
        for (int i = 0; i < dealerCount; ++i) {
            Dealer dealer = new Dealer(autoStorage, i);
            dealer.setDelay(delay);
            dealers.add(dealer);
        }
    }
    private void createBodySuppliers(int bodySuppliersCount) {
        for (int i = 0; i < bodySuppliersCount; ++i) {
            Supplier<Body> supplier = new Supplier<>(bodyStorage, Body::new);
            supplier.setDelay(delay);
            bodySuppliers.add(supplier);
        }
    }

    private void createEngineSuppliers(int engineSuppliersCount) {
        for (int i = 0; i < engineSuppliersCount; ++i) {
            Supplier<Engine> supplier = new Supplier<>(engineStorage, Engine::new);
            supplier.setDelay(delay);
            engineSuppliers.add(supplier);
        }
    }


    private void createAccessorySuppliers(int accessorySuppliersCount) {
        for (int i = 0; i < accessorySuppliersCount; ++i) {
            Supplier<Accessory> supplier = new Supplier<>(accessoryStorage, Accessory::new);
            supplier.setDelay(delay);
            accessorySuppliers.add(supplier);
        }
    }

    public void setBodySuppliersDelay(int delay){
        for(Supplier<Body> bodySupplier : bodySuppliers) bodySupplier.setDelay(delay);
    }
    public void setEngineSuppliersDelay(int delay){
        for(Supplier<Engine> engineSupplier : engineSuppliers) engineSupplier.setDelay(delay);
    }
    public void setAccessorySuppliersDelay(int delay){
        for(Supplier<Accessory> accessorySupplier : accessorySuppliers) accessorySupplier.setDelay(delay);
    }
    public void setDealersDelay(int delay){
        for(Dealer dealer : dealers) dealer.setDelay(delay);
    }
    public void setWorkersDelay(int delay){
        workers.setDelay(delay);
    }

    public int getAccessoryStorageSize() {
        return accessoryStorage.getSize();
    }
    public int getEngineStorageSize() {
        return engineStorage.getSize();
    }
    public int getBodyStorageSize() {
        return bodyStorage.getSize();
    }
    public int getAutoStorageSize(){
        return autoStorage.getSize();
    }
}
