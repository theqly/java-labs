package org.example.Model;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Model {
    private Field field;
    private Player player;
    private ArrayList<Coin> coins;
    private int score;

    public Model() {
        field = new Field(500, 500);
        player = new Player(100, 100);
        field.initMap(new File("/home/theqly/programming/projects/java-labs/lab3/src/main/resources/level1.txt"));
        field.digEarth(player.getPositionX(), player.getPositionY(), player.getWidth(), player.getHeight());
        coins = new ArrayList<>();
        loadCoins();
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
        update();
    }

    private void update(){
        field.digEarth(player.getPositionX(), player.getPositionY(), player.getWidth(), player.getHeight());
        checkCollisions();
        if(score == 10) stop();
    }

    private void checkCollisions(){
        for(Iterator<Coin> iterator = coins.iterator(); iterator.hasNext(); ){
            Coin coin = iterator.next();
            if(player.getPositionX() + player.getHeight() <= coin.getPositionX()) continue;
            if(player.getPositionY() >= coin.getPositionY() + coin.getHeight()) continue;
            if(player.getPositionX() + player.getWidth() <= coin.getPositionX()) continue;
            if(player.getPositionX() >= coin.getPositionX() + coin.getWidth()) continue;

            coin.collect();
            score++;
            iterator.remove();
        }
    }
    private void loadCoins(){
        for(int i = 0; i < 10; ++i){
            coins.add(new Coin(i * 10 + i, i * 10 + i));
        }
    }

    private void stop(){
        System.out.println("You won!");
    }
    public Field getField() {
        return field;
    }

    public Player getPlayer() {
        return player;
    }
    public ArrayList<Coin> getCoins(){
        return coins;
    }

}
