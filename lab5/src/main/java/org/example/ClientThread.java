package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread extends Thread{
    private final Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String nickname;

    public ClientThread(Socket socket){
        this.socket = socket;
        try{
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            //nickname = (String) in.readObject();
            Server.broadcast("*** New client connected: " + socket + " ***");

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run(){
        while(true){
            try{
                Message message = (Message) in.readObject();
                Server.broadcast(message.getContent());
            } catch (IOException | ClassNotFoundException e){
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        out.writeObject(message);
        out.flush();
    }

    public String getNickname(){
        return nickname;
    }
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
