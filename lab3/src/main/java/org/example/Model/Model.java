package org.example.Model;


import java.io.File;

public class Model {
    private Field field;
    private Player player;

    public Model() {
        field = new Field(500, 500);
        player = new Player();
        player.setPosition(100, 100);
        field.initMap(new File("/home/whoistheql/programming/projects/java-labs/lab3/src/main/resources/level1.txt"));
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
    }

    public Field getField() {
        return field;
    }

    public Player getPlayer() {
        return player;
    }
}
