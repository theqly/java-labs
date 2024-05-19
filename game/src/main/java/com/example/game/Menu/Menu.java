package com.example.game.Menu;

import com.example.game.Digger;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Menu extends VBox {
    public Menu(Digger mainApp) {
        setAlignment(Pos.CENTER);
        setSpacing(10);

        Button playButton = new Button("Play");
        playButton.setOnAction(e -> mainApp.startGame());

        Button scoreBoardButton = new Button("Score Board");
        scoreBoardButton.setOnAction(e -> mainApp.showScoreBoard());

        Button aboutButton = new Button("About");
        aboutButton.setOnAction(e -> mainApp.showAbout());

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> mainApp.quit());

        getChildren().addAll(playButton, scoreBoardButton, aboutButton, quitButton);
    }
}
