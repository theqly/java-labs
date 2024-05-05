package org.example;

import java.io.*;
import com.google.gson.Gson;

public class Message{
    private final String nickname;
    private final String content;

    public Message(String nickname, String content){
        this.nickname = nickname;
        this.content = content;
    }

    String getNickname(){
        return nickname;
    }
    
    String getContent(){
        return content;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Message fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Message.class);
    }
}