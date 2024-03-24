package org.example;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Objects;

public class Server {
    private final int port;
    private static ArrayList<ClientThread> clients;

    public Server(int port){
        this.port = port;
        clients = new ArrayList<ClientThread>();
    }

    public static void main(String[] args){
        int port = 8080;
        Server server = new Server(port);
        server.run();
    }

    public void run(){
        boolean running = true;
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("*** Server is open on port " + port + " ***");
            while (running){
                Socket socket = serverSocket.accept();
                System.out.println("*** New client connected: " + socket + " ***");
                if(!running) break;
                ClientThread client = new ClientThread(socket);
                clients.add(client);
                client.start();
            }

            serverSocket.close();
            for(ClientThread client : clients){
                try {
                    client.close();
                } catch (IOException e){
                    System.out.println(e.getMessage());
                }
            }

        } catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
    public synchronized static void broadcast(String message){
        for(ClientThread client : clients){
            try{
                client.sendMessage(message);
            } catch (IOException e){
                    System.out.println(e.getMessage());
            }
        }
    }
}