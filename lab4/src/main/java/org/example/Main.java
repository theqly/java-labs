package org.example;

import org.example.Controller.Controller;
import org.example.View.View;

public class Main {
    public static void main(String[] args) {
        Factory factory = new Factory("config.properties");
        Controller controller = new Controller(factory);
        View view = new View(factory, controller);
    }
}