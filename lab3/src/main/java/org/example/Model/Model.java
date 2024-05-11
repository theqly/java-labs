package org.example.Model;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Model {
    private Field field;
    private Player player;
    private ArrayList<Coin> coins;
    private int score;
    private int endScore;
    private boolean isEnded;

    public Model() {
        field = new Field(500, 500);
        player = new Player(100, 100);
        field.initMap(new File("/home/whoistheql/programming/projects/java-labs/lab3/src/main/resources/level1.txt"));
        field.digEarth(player.getPositionX(), player.getPositionY(), player.getWidth(), player.getHeight());
        coins = new ArrayList<>();
        score = 0;
        isEnded = false;
        loadCoins(new File("/home/whoistheql/programming/projects/java-labs/lab3/src/main/resources/level1_coins.txt"));
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
        if(score == endScore) stop();
    }

    private void checkCollisions(){
        for(Iterator<Coin> iterator = coins.iterator(); iterator.hasNext(); ){
            Coin coin = iterator.next();
            if(player.getPositionY() + player.getHeight() <= coin.getPositionY()) continue;
            if(player.getPositionY() >= coin.getPositionY() + coin.getHeight()) continue;
            if(player.getPositionX() + player.getWidth() <= coin.getPositionX()) continue;
            if(player.getPositionX() >= coin.getPositionX() + coin.getWidth()) continue;

            coin.collect();
            score++;
            iterator.remove();
        }
    }
    private void loadCoins(File file){
        try {
            Scanner scanner = new Scanner(file);
            String line;
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                String[] tmp = line.split(" ");
                coins.add(new Coin(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])));
            }
            endScore = coins.toArray().length;
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

    private void stop(){
        isEnded = true;
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

    public boolean isEnded() {
        return isEnded;
    }
}
