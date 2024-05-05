package org.example;

import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    private final int port;
    private final ArrayList<ClientThread> clients;
    private final LinkedList<Message> messageHistory;
    private final Gson gson;
    private final long timeToDisconnect = 30000;

    public Server(int port) {
        this.port = port;
        clients = new ArrayList<>();
        messageHistory = new LinkedList<>();
        gson = new Gson();
    }

    public static void main(String[] args) {
        int port = 8080;
        Server server = new Server(port);
        server.run();
    }

    public void run() {
        boolean running = true;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("*** Server is open on port " + port + " ***");
            TimeoutChecker timeoutChecker = new TimeoutChecker();
            timeoutChecker.start();
            while (running) {
                Socket socket = serverSocket.accept();
                System.out.println("*** New client connected: " + socket + " ***");

                ClientThread client = new ClientThread(socket);
                clients.add(client);
                sendHistoryToClient(client);
                client.start();
            }

            serverSocket.close();
            for (ClientThread client : clients) {
                client.close();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public synchronized void broadcast(Message message) {
        for (ClientThread client : clients) {
            try {
                client.sendMessage(message);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        addToMessageHistory(message);
    }

    private synchronized void removeClient(ClientThread client){
        broadcast(new Message("server", "client " + client.getSocket() + " disconnected", 0));
        System.out.println("*** Client disconnected: " + client.getSocket() + " ***");
        clients.remove(client);
        client.close();
    }

    private synchronized void addToMessageHistory(Message message) {
        messageHistory.addLast(message);
        if (messageHistory.size() > 10) {
            messageHistory.removeFirst();
        }
    }

    private synchronized void sendHistoryToClient(ClientThread client) {
        for (Message message : messageHistory) {
            try {
                client.sendMessage(message);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    class ClientThread extends Thread {
        private final Socket socket;
        private boolean isConnected;
        private long lastReceivedActivity;
        private ObjectInputStream in;
        private ObjectOutputStream out;

        public ClientThread(Socket socket) {
            this.socket = socket;
            isConnected = true;
            lastReceivedActivity = 0;
            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        @Override
        public void run() {
            while (isConnected) {
                try {
                    String json = (String) in.readObject();
                    Message message = gson.fromJson(json, Message.class);
                    if (message == null) break;
                    if (message.getType() == 0) {
                        lastReceivedActivity = System.currentTimeMillis();
                        broadcast(message);
                    } else if(message.getType() == 1){
                        lastReceivedActivity = System.currentTimeMillis();
                        sendMessage(message);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        }


        public boolean sendMessage(Message message) throws IOException {
            if(socket.isClosed()) return false;
            String json = gson.toJson(message);
            try{
                out.writeObject(json);
                out.flush();
            } catch (IOException e){

            }
            return true;
        }

        public Socket getSocket(){
            return socket;
        }

        public long getLastReceivedActivity() {
            return lastReceivedActivity;
        }

        public void close() {
            try {
                isConnected = false;
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    class TimeoutChecker extends Thread{
        boolean needToWork;
        public TimeoutChecker(){
            needToWork = true;
        }
        @Override
        public void run(){
            while (needToWork){
                for(Iterator<ClientThread> iterator = clients.iterator(); iterator.hasNext();){
                    ClientThread client = iterator.next();
                    if(System.currentTimeMillis() - client.getLastReceivedActivity() >= timeToDisconnect){
                        iterator.remove();
                        removeClient(client);
                    }
                }
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}