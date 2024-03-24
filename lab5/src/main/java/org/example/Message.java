package org.example;

import java.io.*;

public class Message implements Serializable{
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
}