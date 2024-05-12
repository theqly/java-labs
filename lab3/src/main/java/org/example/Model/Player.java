package org.example.Model;

public class Player extends GameObject{

    public static enum direction{
        UP,
        LEFT,
        DOWN,
        RIGHT,

    }
    private final int[] speed;
    private boolean isAlive;

    public Player(int x, int y){
        super(x, y, 20, 20);
        speed = new int[2];
        isAlive = true;
    }

    public void setPosition(int x, int y){
        positionX = x;
        positionY = y;
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

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
