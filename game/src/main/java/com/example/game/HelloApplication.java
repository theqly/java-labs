package com.example.game;

import com.example.game.Controller.Controller;
import com.example.game.Model.Model;
import com.example.game.View.ViewFX;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage){
        Model model = new Model();
        Controller controller = new Controller();
        ViewFX view = new ViewFX(model, controller);
        controller.setModel(model);

        Scene scene = new Scene(view, model.getField().getWidth(), model.getField().getHeight());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Digger");
        primaryStage.show();

        view.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}