package org.example.Model;

public class Player {

    public static enum direction{
        UP,
        LEFT,
        DOWN,
        RIGHT,

    }
    private int positionX;
    private int positionY;
    private final int[] speed;
    private final int width;
    private final int height;

    public Player(int x, int y){
        positionX = x;
        positionY = y;
        speed = new int[2];
        width = 20;
        height = 20;
    }

    public int getPositionX(){
        return positionX;
    }

    public int getPositionY(){
        return positionY;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
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
        if(positionX < 0) positionX = 0;
        positionY += speed[1];
        if(positionY < 0) positionY = 0;
    }
}
