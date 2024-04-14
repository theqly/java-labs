package org.example.Model;

public class Player {
    /* X is 0 and going down, Y is 1 and going right */
    private final int[] position;
    private final int[] speed;

    public Player(){
        position = new int[2];
        speed = new int[2];
    }

    public int getPositionX(){
        return position[0];
    }

    public int getPositionY(){
        return position[1];
    }

    public void setPosition(int x, int y){
        position[0] = x;
        position[1] = y;
    }

    public void setSpeed(int dx, int dy){
        speed[0] = dx;
        speed[1] = dy;
    }

    public void resetSpeed(){
        speed[0] = speed[1] = 0;
    }

    public void move(){
        position[0] += speed[0];
        if(position[0] < 0) position[0] = 0;
        position[1] += speed[1];
        if(position[1] < 0) position[1] = 0;
    }
}
