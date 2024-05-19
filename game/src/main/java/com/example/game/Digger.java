package com.example.game;

import com.example.game.Controller.Controller;
import com.example.game.Model.Model;
import com.example.game.Model.Record;
import com.example.game.View.ViewFX;
import com.example.game.Menu.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Digger extends Application {
    private Stage primaryStage;
    private Scene menuScene;
    private Scene gameScene;
    private Model model;
    private ViewFX view;
    Controller controller;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("My Game");

        showMainMenu();
    }

    public void showMainMenu() {
        Menu menu = new Menu(this);
        menuScene = new Scene(menu, 500, 500);
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    public void startGame() {
        TextInputDialog dialog = new TextInputDialog("Player");
        dialog.setTitle("Player Nickname");
        dialog.setHeaderText("Enter your Nickname:");
        dialog.setContentText("Nickname:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            model = new Model();
            model.setNickname(result.get());
            controller = new Controller();
            controller.setModel(model);
            view = new ViewFX(model, controller);
            gameScene = new Scene(view, model.getField().getWidth(), model.getField().getHeight());
            primaryStage.setScene(gameScene);
            primaryStage.show();
            view.requestFocus();
            model.setStartTime();
        }
    }

    public void showScoreBoard() {
        List<Record> recordsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("/home/whoistheql/programming/projects/java-labs/game/src/main/resources/score_board.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(": ");
                recordsList.add(new Record(parts[0], Double.parseDouble(parts[1])));
            }
        } catch (IOException e) {
            System.out.println("Error loading records: " + e.getMessage());
        }
        ScoreBoard scoreBoard = new ScoreBoard(recordsList, this);
        Scene scoreBoardScene = new Scene(scoreBoard, 500, 500);
        primaryStage.setScene(scoreBoardScene);
        primaryStage.show();
    }

    public void showAbout() {
        About about = new About(this);
        Scene aboutScene = new Scene(about, 500, 500);
        primaryStage.setScene(aboutScene);
        primaryStage.show();
    }

    public void quit() {
        primaryStage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}