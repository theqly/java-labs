package com.example.game.Menu;

import com.example.game.Digger;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class About extends VBox {
    public About(Digger mainApp){
        setAlignment(Pos.CENTER);
        setSpacing(10);

        HBox topBar = new HBox();
        topBar.setAlignment(Pos.TOP_LEFT);
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> mainApp.showMainMenu());
        topBar.getChildren().add(backButton);
        getChildren().add(topBar);

        Label text = new Label("Game created by Artem Gaan");
        getChildren().add(text);
    }
}
