package com.example.game.Model;

public class Ghost extends GameObject{
    private int[] speed;
    public Ghost(int x, int y){
        super(x, y, 20, 20);
        speed = new int[2];
    }

    public void setSpeed(int dx, int dy){
        speed[0] = dx;
        speed[1] = dy;
    }

    public void resetSpeed(){
        speed[0] = speed[1] = 0;
    }

    public void move(){
        positionX += speed[0];
        positionY += speed[1];
    }


}
