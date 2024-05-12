package org.example.Model;

public class GameObject {
    protected int positionX;
    protected int positionY;
    protected int width;
    protected int height;
    public GameObject(int x, int y, int width, int height){
        positionX = x;
        positionY = y;
        this.width = width;
        this.height = height;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
