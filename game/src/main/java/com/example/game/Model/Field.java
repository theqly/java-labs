package com.example.game.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Field {
    public static enum type{
        EARTH,
        VOID,

    }
    private final int height;
    private final int width;
    private type[][] map;

    public Field(int width, int height){
        this.height = height;
        this.width = width;
        this.map = new type[height][width];
    }

    public void initMap(File file){
        try{
            Scanner scan = new Scanner(file);
            String line;
            for(int i = 0; i < height; ++i){
                line = scan.nextLine();
                String[] tmp = line.split(" ");
                for(int j = 0; j < width; ++j){
                    switch (Integer.parseInt(tmp[j])){
                        case 0 -> map[i][j] = type.VOID;
                        case 1 -> map[i][j] = type.EARTH;
                    }
                }
            }
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public void digEarth(int x, int y, int dx, int dy){
        for(int i = y; i < y + dy; ++i){
            for(int j = x; j < x + dx; ++j){
                map[i][j] = type.VOID;
            }
        }
    }

    public type getType(int x, int y){
        return map[y][x];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public type[][] getMap() {
        return map;
    }
}
