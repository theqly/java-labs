package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Server {
    private final int port;
    private final ArrayList<ClientThread> clients;
    private final LinkedList<String> messageHistory;
    private final Gson gson;

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
            while (running) {
                Socket socket = serverSocket.accept();
                System.out.println("*** New client connected: " + socket + " ***");
                if (!running) break;
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

    public synchronized void broadcast(String message) {
        for (ClientThread client : clients) {
            try {
                if(!client.sendMessage(message)){
                    removeClient(client);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        addToMessageHistory(message);
    }

    private synchronized void removeClient(ClientThread client){
        clients.remove(client);
        client.close();
        System.out.println("Client disconnected");
    }

    private synchronized void addToMessageHistory(String message) {
        messageHistory.addLast(message);
        if (messageHistory.size() > 10) {
            messageHistory.removeFirst();
        }
    }

    private synchronized void sendHistoryToClient(ClientThread client) {
        for (String message : messageHistory) {
            try {
                client.sendMessage(message);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    class ClientThread extends Thread {
        private final Socket socket;
        private ObjectInputStream in;
        private ObjectOutputStream out;

        private Timer timer;

        public ClientThread(Socket socket) {
            this.socket = socket;
            timer = new Timer();
            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                broadcast("*** New client connected: " + socket + " ***");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    //String message;
                    Message message = (Message) in.readObject();
                    if (message == null) break;
                    broadcast(message.getNickname() + ": " + message.getContent());
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        }

        public boolean sendMessage(String message) throws IOException {
            if(!socket.isConnected()){
                timer.scheduleAtFixedRate(new TimerTask() {
                    int secondsAway = 0;
                    @Override
                    public void run() {
                        secondsAway++;
                        if(socket.isConnected()) timer.cancel();
                        if(secondsAway >= 3 && !socket.isConnected()){
                            timer.cancel();
                            close();
                        }
                    }
                }, 0, 1000);
                return false;
            }
            try{
                out.writeObject(message);
                out.flush();
            } catch (IOException e){

            }
            return true;
        }

        public void close() {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}