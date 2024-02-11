package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class reader {
    public reader(String file){
        file_ = file;
        stat = new HashMap<>();
    }

    public void read() throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(file_))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for(String Word : line.split("[^\\p{L}\\p{N}]+")){
                    if(!Word.isEmpty()){
                        stat.put(Word, stat.getOrDefault(Word, 0) + 1);
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace(System.err);
        }
    }

    public Map<String, Integer> get_stat(){
        return stat;
    }

    private String file_;
    private Map<String, Integer> stat;
}
