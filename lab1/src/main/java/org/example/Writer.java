package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map;

public class Writer {
    public Writer(String file){
        this.file = file;
    }

    public void write(Reader reader) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, Integer> entry : reader.getStat().entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).toArray(Map.Entry[]::new)) {
                try {
                    writer.write(String.format("%s,%d,%.2f%%%n", entry.getKey(), entry.getValue(),(double) entry.getValue() / reader.getTotalWords() * 100.0));
                } catch (Exception e){
                    e.printStackTrace(System.err);
                }
            }
            writer.flush();
        } catch (Exception e){
            e.printStackTrace(System.err);
        }
    }

    private final String file;
}
