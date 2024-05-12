package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Controller.Controller;
import org.example.Model.Model;
import org.example.View.View;
import org.example.View.ViewFX;

public class Digger extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        Model model = new Model();
        Controller controller = new Controller();
        ViewFX view = new ViewFX(model, controller);
        controller.setModel(model);
        Scene scene = new Scene(view);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}