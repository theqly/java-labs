package org.example;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("not entered <input_file> <output_file>");
            return;
        }

        String in = args[0];
        String out = args[1];

        Reader reader = new Reader(in);
        Writer writer = new Writer(out);

        try {
            reader.read();
            writer.write(reader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
