package org.example.Model;


import java.io.*;
import java.util.*;

public class Model {
    private Field field;
    private Player player;
    private int playerSpeed = 5;
    private ArrayList<Coin> coins;
    private ArrayList<Ghost> ghosts;
    private int ghostSpeed = 1;
    private long startTime;
    private long endTime;
    private Timer ghostTimer;
    private long state;
    private boolean isEnded;
    private List<Record> records;
    private String nickname = "player";

    public Model() {
        field = new Field(500, 500);
        player = new Player(436, 0);
        field.initMap(new File("/home/whoistheql/programming/projects/java-labs/lab3/src/main/resources/map.txt"));
        field.digEarth(player.getPositionX(), player.getPositionY(), player.getWidth(), player.getHeight());
        coins = new ArrayList<>();
        ghosts = new ArrayList<>();
        ghostTimer = new Timer();
        isEnded = false;
        loadCoins(new File("/home/whoistheql/programming/projects/java-labs/lab3/src/main/resources/coins.txt"));
        loadGhosts();
        records = loadRecords(new File("/home/whoistheql/programming/projects/java-labs/lab3/src/main/resources/score_board.txt"));
        startTime = System.currentTimeMillis();
    }

    public void movePlayer(Player.direction direction){
        switch (direction){
            case UP -> player.setSpeed(0,-playerSpeed);
            case LEFT -> player.setSpeed(-playerSpeed, 0);
            case DOWN -> player.setSpeed(0, playerSpeed);
            case RIGHT -> player.setSpeed(playerSpeed, 0);
        }
        player.move();
        player.resetSpeed();
        checkPlayerPosition();
        update();
    }
    public void moveGhosts(){
        for(Ghost ghost : ghosts){
            int y, x;
            ArrayList<Player.direction> movement = new ArrayList<>();
            movement.add(Player.direction.UP);
            movement.add(Player.direction.LEFT);
            movement.add(Player.direction.DOWN);
            movement.add(Player.direction.RIGHT);
            for(y = ghost.getPositionY() - 1; y > ghost.getPositionY() - 1 - ghostSpeed; --y){
                for(x = ghost.getPositionX(); x < ghost.getPositionX() + ghost.getWidth(); ++x) {
                    if(y < 0 || x < 0 || y >= field.getHeight() || x >= field.getWidth()){
                        movement.remove(Player.direction.UP);
                        break;
                    }
                    if (field.getType(x, y) == Field.type.EARTH) movement.remove(Player.direction.UP);
                }
            }

            for(x = ghost.getPositionX() - 1; x > ghost.getPositionX() - 1 - ghostSpeed; --x){
                for(y = ghost.getPositionY(); y < ghost.getPositionY() + ghost.getHeight(); ++y){
                    if(y < 0 || x < 0 || y >= field.getHeight() || x >= field.getWidth()){
                        movement.remove(Player.direction.LEFT);
                        break;
                    }
                    if(field.getType(x, y) == Field.type.EARTH) movement.remove(Player.direction.LEFT);
                }
            }

            for(y = ghost.getPositionY() + ghost.getHeight() + 1; y < ghost.getPositionY() + ghost.getHeight() + 1 + ghostSpeed; ++y){
                for(x = ghost.getPositionX(); x < ghost.getPositionX() + ghost.getWidth(); ++x){
                    if(y < 0 || x < 0 || y >= field.getHeight() || x >= field.getWidth()){
                        movement.remove(Player.direction.DOWN);
                        break;
                    }
                    if(field.getType(x, y) == Field.type.EARTH) movement.remove(Player.direction.DOWN);
                }
            }

            for(x = ghost.getPositionX() + ghost.getWidth() + 1; x < ghost.getPositionX() + ghost.getWidth() + 1 + ghostSpeed; ++x){
                for(y = ghost.getPositionY(); y < ghost.getPositionY() + ghost.getHeight(); ++y){
                    if(y < 0 || x < 0 || y >= field.getHeight() || x >= field.getWidth()){
                        movement.remove(Player.direction.RIGHT);
                        break;
                    }
                    if(field.getType(x, y) == Field.type.EARTH) movement.remove(Player.direction.RIGHT);
                }
            }
            Random random = new Random(System.currentTimeMillis());
            int i = random.nextInt(movement.size());

            switch (movement.get(i)){
                case UP -> ghost.setSpeed(0,-ghostSpeed);
                case LEFT -> ghost.setSpeed(-ghostSpeed, 0);
                case DOWN -> ghost.setSpeed(0, ghostSpeed);
                case RIGHT -> ghost.setSpeed(ghostSpeed, 0);
            }

            ghost.move();
            ghost.resetSpeed();
        }
    }

    private void update(){
        if(!isEnded){
            field.digEarth(player.getPositionX(), player.getPositionY(), player.getWidth(), player.getHeight());
            checkCoinsCollisions();
            state++;
            if(coins.isEmpty()) stop();
        }
    }

    private void checkCoinsCollisions(){
        coins.removeIf(coin -> checkCollisions(player, coin));
    }

    private void checkGhostsCollisions(){
        for(Ghost ghost : ghosts){
            if(checkCollisions(player, ghost)) player.setAlive(false);
        }
    }

    private void checkPlayerPosition(){
        int posX = player.getPositionX();
        int posY = player.getPositionY();
        int pWidth = player.getWidth();
        int pHeight = player.getHeight();
        int fWidth = field.getWidth();
        int fHeight = field.getHeight();

        if(posX < 0) player.setPosition(0, posY);
        if(posY < 0) player.setPosition(posX, 0);
        if(posX + pWidth > fWidth) player.setPosition(fWidth - pWidth, posY);
        if(posY+ pHeight > fHeight) player.setPosition(posX, fHeight - pHeight);
    }

    private boolean checkCollisions(GameObject object1, GameObject object2){
        if(object1.getPositionY() + object1.getHeight() <= object2.getPositionY()) return false;
        if(object1.getPositionY() >= object2.getPositionY() + object2.getHeight()) return false;
        if(object1.getPositionX() + object1.getWidth() <= object2.getPositionX()) return false;
        if(object1.getPositionX() >= object2.getPositionX() + object2.getWidth()) return false;
        return true;
    }

    private void saveRecord(double time){
        records.add(new Record(nickname, time));
        Collections.sort(records);
        writeRecords(new File("/home/whoistheql/programming/projects/java-labs/lab3/src/main/resources/score_board.txt"));
    }

    private void writeRecords(File file){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Record record : records) {
                bw.write(record.getNickname() + ": " + record.getTime() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing records: " + e.getMessage());
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

        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

    private void loadGhosts(){
        ghosts.add(new Ghost(100, 400));
        ghosts.add(new Ghost(200, 400));
        ghosts.add(new Ghost(300, 400));
        ghostTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                moveGhosts();
                checkGhostsCollisions();
                if(!player.isAlive()) stop();
                state++;
            }
        }, 5, 5);
    }

    private List<Record> loadRecords(File file) {
        List<Record> recordsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(": ");
                recordsList.add(new Record(parts[0], Double.parseDouble(parts[1])));
            }
        } catch (IOException e) {
            System.out.println("Error loading records: " + e.getMessage());
        }
        return recordsList;
    }

    private void stop(){
        endTime = System.currentTimeMillis() - startTime;
        isEnded = true;
        if(player.isAlive()) saveRecord((double) endTime / 1000);

        if(ghostTimer != null){
            ghostTimer.cancel();
        }
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setStartTime(){
        startTime = System.currentTimeMillis();
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

    public ArrayList<Ghost> getGhosts() {
        return ghosts;
    }

    public long getState() {
        return state;
    }

    public double getTime(){
        if(isEnded) return (double) endTime / 1000;
        return (double) (System.currentTimeMillis() - startTime) / 1000;
    }

    public List<Record> getRecords() {
        return records;
    }

    public boolean isEnded() {
        return isEnded;
    }
}
