package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Reader {
    public Reader(String file){
        this.file = file;
        this.stat = new HashMap<>();
        this.totalWords = 0;
    }

    public void read() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for(String Word : line.split("\\P{Alnum}+")){
                    if(!Word.isEmpty()){
                        stat.compute(Word, (key, value) -> value == null ? 1 : value + 1);
                        totalWords++;
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace(System.err);
        }
    }

    public Map<String, Integer> getStat(){
        return stat;
    }
    public int getTotalWords(){
        return totalWords;
    }
    private final String file;
    private final Map<String, Integer> stat;
    private int totalWords;
}
