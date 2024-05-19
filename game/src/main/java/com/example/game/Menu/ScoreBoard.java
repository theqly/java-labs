package com.example.game.Menu;

import com.example.game.Digger;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import com.example.game.Model.Record;

import java.util.List;

public class ScoreBoard extends VBox {
    public ScoreBoard(List<Record> records, Digger mainApp) {
        setAlignment(Pos.CENTER);
        setSpacing(10);

        HBox topBar = new HBox();
        topBar.setAlignment(Pos.TOP_LEFT);
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> mainApp.showMainMenu());
        topBar.getChildren().add(backButton);
        getChildren().add(topBar);

        Label title = new Label("High Scores");
        getChildren().add(title);

        for (Record record : records) {
            Label recordLabel = new Label(record.getNickname() + ": " + record.getTime() + "s");
            getChildren().add(recordLabel);
        }
    }
}
