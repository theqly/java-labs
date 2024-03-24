package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
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
        String nickname = scanner.nextLine();

        Client client = new Client(serverAddress, port, nickname);
        client.start();
        while (true){
            //System.out.println("Enter your message: ");
            String message = scanner.nextLine();
            try{
                client.sendMessage(new Message(nickname, message));
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        //scanner.close();
    }

    public void start(){
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
        out.writeObject(message);
    }
    public static String getMessage() throws IOException, ClassNotFoundException {
        return (String)in.readObject();
    }
}