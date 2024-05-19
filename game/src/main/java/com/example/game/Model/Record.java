package com.example.game.Model;

public class Record implements Comparable<Record> {
    private String nickname;
    private double time;

    public Record(String nickname, double time) {
        this.nickname = nickname;
        this.time = time;
    }

    public String getNickname() {
        return nickname;
    }

    public double getTime() {
        return time;
    }

    @Override
    public int compareTo(Record other) {
        return Double.compare(this.time, other.time);
    }
}
