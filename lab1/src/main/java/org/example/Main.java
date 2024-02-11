package org.example;


import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java Main <input_file> <output_file>");
            return;
        }

        String in = args[0];
        String out = args[1];

        reader rd = new reader(in);
        writer wr = new writer(out);

        try {
            rd.read();
            wr.write(rd.get_stat());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
