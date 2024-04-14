package org.example.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Field {
    private final int height;
    private final int width;
    private int[][] map;

    public Field(int width, int height){
        this.height = height;
        this.width = width;
        this.map = new int[height][width];
    }

    public void initMap(File file){
        try{
            Scanner scan = new Scanner(file);
            String line;
            for(int i = 0; i < height; ++i){
                line = scan.nextLine();
                String[] tmp = line.split(" ");
                for(int j = 0; j < tmp.length; ++j){
                    map[i][j] = Integer.parseInt(tmp[j]);
                }
            }
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean isWall(int x, int y){
        return map[x][y] == 1;
    }
    public void setPlayerPosition(int x, int y){
        map[x][y] = 2;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int[][] getMap() {
        return map;
    }
}
