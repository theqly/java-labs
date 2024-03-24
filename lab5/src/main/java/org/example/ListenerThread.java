package org.example;

import java.io.IOException;

public class ListenerThread extends Thread{
    @Override
    public void run(){
        while (true){
            try{
                String message = Client.getMessage();
                //System.out.println("Received message: ");
                System.out.println(message);
            } catch (IOException | ClassNotFoundException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
