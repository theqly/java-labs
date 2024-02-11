package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

public class writer {
    public writer(String file){
        file_ = file;
    }

    public void write(Map<String, Integer> mp) throws Exception {
        //это ужас какой-то я честно признаю что мне помогал чат гпт
        int total_words = mp.values().stream().mapToInt(Integer::intValue).sum();
        try (BufferedWriter Writer = new BufferedWriter(new FileWriter(file_))) {
            for (Map.Entry<String, Integer> entry : mp.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).toArray(Map.Entry[]::new)) {
                try {
                    Writer.write(String.format("%s,%d,%.2f%%%n", entry.getKey(), entry.getValue(),(double) entry.getValue() / total_words * 100.0));
                } catch (Exception e){
                    e.printStackTrace(System.err);
                }
            }
            Writer.flush();
        } catch (Exception e){
            e.printStackTrace(System.err);
        }
    }

    private final String file_;
}
