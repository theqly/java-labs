package org.example.Storage;

import java.util.LinkedList;
import java.util.Queue;

public class Storage<T> {
    private final int capacity;
    private final Queue<T> items;

    public Storage(int capacity){
        this.capacity = capacity;
        this.items  = new LinkedList<>();
    }

    /*public void put(T item){
        synchronized (items){
            while(items.size() >= capacity){
                try{
                    items.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            items.offer(item);
            items.notifyAll();
        }
    }

    public T take(){
        synchronized (items){
            while(items.isEmpty()){
                try{
                    items.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            T item = items.poll();
            items.notifyAll();
            return item;
        }
    }*/

    public synchronized void put(T item) {
        while (items.size() >= capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        items.add(item);
        notifyAll();
    }

    public synchronized T take() {
        while (items.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        T item = items.poll();
        notifyAll();
        return item;
    }

    public synchronized int getCapacity(){
        return capacity;
    }

    public synchronized int getSize(){
        return items.size();
    }
    public synchronized int getFreeCapacity(){
        return capacity - items.size();
    }
}
