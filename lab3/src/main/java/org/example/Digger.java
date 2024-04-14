package org.example;

import org.example.Controller.Controller;
import org.example.Model.Model;
import org.example.View.View;

public class Digger {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller();
        View view = new View(model, controller);
        controller.setModel(model);
        controller.setView(view);
    }
}