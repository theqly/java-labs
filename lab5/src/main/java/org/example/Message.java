package org.example;

import java.io.*;
import com.google.gson.Gson;

public class Message{
    //type 0 -> message, type 1 -> ping
    private final int type;
    private final String nickname;
    private final String content;

    public Message(String nickname, String content, int type){
        this.nickname = nickname;
        this.content = content;
        this.type = type;
    }

    String getNickname(){
        return nickname;
    }
    
    String getContent(){
        return content;
    }

    public int getType() {
        return type;
    }
}