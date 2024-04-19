package org.example;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Gson gson;
    private static ObjectInputStream in;
    private ObjectOutputStream out;
    private final String server;
    private Socket socket;
    private final int port;
    private final String nickname;
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
            //System.out.println("Enter your message: ");
            String message = scanner.nextLine();
            try{
                client.sendMessage(new Message(client.getNickname(), message));
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        //scanner.close();
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
    }
    public void sendMessage(Message message) throws IOException {
        String json = gson.toJson(message);
        out.writeObject(json);
        out.flush();
    }

    public String getNickname(){
        return nickname;
    }

    public String getMessage() throws IOException, ClassNotFoundException {
        return (String)in.readObject();
    }

    class ListenerThread extends Thread{
        @Override
        public void run(){
            while (true){
                try{
                    String message = getMessage();
                    //System.out.println("Received message: ");
                    System.out.println(message);
                } catch (IOException | ClassNotFoundException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}