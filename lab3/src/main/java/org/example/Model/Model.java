package org.example.Model;


import java.io.File;

public class Model {
    private Field field;
    private Player player;
    private Coin coins[];
    private int score;

    public Model() {
        field = new Field(500, 500);
        player = new Player(100, 100);
        field.initMap(new File("/home/whoistheql/programming/projects/java-labs/lab3/src/main/resources/level1.txt"));
        field.digEarth(player.getPositionX(), player.getPositionY(), player.getWidth(), player.getHeight());
        coins = new Coin[10];
    }

    public void movePlayer(Player.direction direction){
        switch (direction){
            case UP -> player.setSpeed(0,-10);
            case LEFT -> player.setSpeed(-10, 0);
            case DOWN -> player.setSpeed(0, 10);
            case RIGHT -> player.setSpeed(10, 0);
        }
        player.move();
        player.resetSpeed();
        System.out.println("x:" + player.getPositionX() + "y: " + player.getPositionY());
        field.digEarth(player.getPositionX(), player.getPositionY(), player.getWidth(), player.getHeight());

    }

    private boolean isCollided(){
        for(Coin coin : coins){
            if(player.getPositionX() + player.getHeight() <= coin.getPositionX()) return false;
        }
        return true;
    }


    public Field getField() {
        return field;
    }

    public Player getPlayer() {
        return player;
    }
    public Coin[] getCoins(){
        return coins;
    }
}
