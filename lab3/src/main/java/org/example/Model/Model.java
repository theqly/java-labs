package org.example.Model;


import java.io.File;

public class Model {
    private Field field;
    private Player player;

    public Model() {
        field = new Field(500, 500);
        player = new Player();
        player.setPosition(0, 0);
        field.setPlayerPosition(0, 0);
        field.initMap(new File("/home/whoistheql/programming/projects/java-labs/lab3/src/main/resources/level1.txt"));
    }

    public void movePlayer(int direction){
        switch (direction){
            case 0 -> player.setSpeed(10,0);
            case 1 -> player.setSpeed(-10, 0);
            case 2 -> player.setSpeed(0, 10);
            case 3 -> player.setSpeed(0, -10);
        }
        player.move();
        player.resetSpeed();
        field.setPlayerPosition(player.getPositionX(), player.getPositionY());
        System.out.println(player.getPositionX() + player.getPositionY());
    }

    public Field getField() {
        return field;
    }

    public Player getPlayer() {
        return player;
    }
}
