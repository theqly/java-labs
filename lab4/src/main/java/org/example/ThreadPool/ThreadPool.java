package org.example.ThreadPool;


import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ThreadPool {
    private final Queue<Task> tasks;
    private final ArrayList<WorkerThread> workers;
    private final int numWorkers;
    private int delay;

    public ThreadPool(int numWorkers, int delay){
        this.tasks = new ConcurrentLinkedDeque<>();
        this.workers = new ArrayList<>();
        this.numWorkers = numWorkers;
        this.delay = delay;
        for(int i = 0; i < numWorkers; ++i){
            WorkerThread workerThread = new WorkerThread();
            workers.add(workerThread);
            workerThread.start();
        }
    }

    public synchronized void addTask(Task task){
        tasks.add(task);
        notifyAll();
    }

    public synchronized void stopWorking(){
        for(WorkerThread workerThread : workers) workerThread.stopWorking();
    }

    public int getTasksSize(){
        return tasks.size();
    }
    public void setDelay(int delay){
        this.delay = delay;
    }

    private class WorkerThread extends Thread{
        private boolean isWorking;

        @Override
        public void run(){
            isWorking = true;
            Task task;
            while(!Thread.currentThread().isInterrupted() && isWorking){
                synchronized (ThreadPool.this){
                    while(tasks.isEmpty()){
                        try{
                            ThreadPool.this.wait();
                        } catch (InterruptedException e){
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                    try{
                        Thread.sleep(delay);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    task = tasks.poll();
                }
                try{
                    if(task != null) task.run();
                } catch (RuntimeException e){
                    e.printStackTrace();
                }
            }
        }

        public void stopWorking(){
            isWorking = false;
        }
    }
}
