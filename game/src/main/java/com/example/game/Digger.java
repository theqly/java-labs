package com.example.game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Digger extends Application {
    public static void main(String[] args){
       launch(args);
    }

    @Override
    public void start(Stage stage){
        //Model model = new Model();
        //Controller controller = new Controller();
        //ViewFX view = new ViewFX(model, controller);
        //controller.setModel(model);
        StackPane root = new StackPane();
        stage.setScene(new Scene(root, 300, 250));
        stage.show();
    }

}