package org.example;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    private Gson gson;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private final String server;
    private Socket socket;
    private final int port;
    private final String nickname;
    private long lastReceived;
    private final long timeTiDisconnect = 30000;
    public Client(String server, int port, String nickname){
        this.server = server;
        this.port = port;
        this.nickname = nickname;
    }

    public static void main(String[] args){
        int port = 8080;
        String serverAddress = "localhost";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your nickname: ");
        String nick = scanner.nextLine();

        Client client = new Client(serverAddress, port, nick);
        client.start();
        while (true){
            if(client.getSocket().isClosed()) break;
            String message = scanner.nextLine();
            try{
                client.sendMessage(new Message(client.getNickname(), message, 0));
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    public void start(){
        gson = new Gson();
        try{
            socket = new Socket(server, port);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Connected on " + socket.getInetAddress() + ":" + port);
        try{
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        ListenerThread listenerThread = new ListenerThread();
        listenerThread.start();
        PingThread pingThread = new PingThread();
        pingThread.start();
    }


    public void sendMessage(Message message) throws IOException {
        String json = gson.toJson(message);
        out.writeObject(json);
        out.flush();
    }

    public String getNickname(){
        return nickname;
    }

    public Socket getSocket() {
        return socket;
    }

    public Message getMessage() throws IOException, ClassNotFoundException {
        if(socket.isClosed()) return null;
        if(socket.isInputShutdown()) return null;
        if(socket.isOutputShutdown()) return null;
        String json = (String)in.readObject();
        if (json == null) return null;
        return gson.fromJson(json, Message.class);
    }

    public void stop() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e){

        }
    }

    class ListenerThread extends Thread{
        @Override
        public void run(){
            while (!Thread.interrupted()){
                try{
                    Message message = getMessage();
                    if (message == null) break;
                    if (message.getType() == 0);
                    System.out.println(message.getNickname() + ": " + message.getContent());
                    lastReceived = System.currentTimeMillis();
                } catch (IOException | ClassNotFoundException e){
                    System.out.println("*** Socket unavailable " + e + " ***");
                    break;
                }
            }
        }
    }

    class PingThread extends Thread{
        @Override
        public void run(){
            while (!Thread.interrupted()){
                try {
                    sendMessage(new Message(nickname, "ping", 1));
                    sleep(3000);
                    if(System.currentTimeMillis() - lastReceived >= timeTiDisconnect) {
                        System.out.println("*** Server not available ***");
                        Client.this.stop();
                        break;
                    }
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}