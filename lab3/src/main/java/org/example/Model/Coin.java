package org.example.Model;

public class Coin {
    private int positionX;
    private int positionY;
    private int width;
    private int height;
    private boolean isCollected;
    public Coin(int x, int y){
        positionX = x;
        positionY = y;
        width = 10;
        height = 10;
        isCollected = false;
    }

    public void collect(){
        isCollected = true;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public boolean isCollected() {
        return isCollected;
    }
}
