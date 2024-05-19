package com.example.game.View;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import com.example.game.Controller.Controller;
import com.example.game.Model.*;

import java.util.Objects;
import java.util.Optional;

public class ViewFX extends BorderPane {
    private Model model;
    private AnimationTimer animationTimer;
    private long lastState;

    public ViewFX(Model model, Controller controller) {
        this.model = model;

        Canvas canvas = new Canvas(model.getField().getWidth(), model.getField().getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        setCenter(canvas);

        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long curState = model.getState();
                if (lastState != curState) {
                    if (model.isEnded()) {
                        draw(gc);
                        animationTimer.stop();
                        Platform.runLater(() -> showEndGameDialog());
                    } else {
                        draw(gc);
                    }
                    lastState = curState;
                }
            }
        };
        animationTimer.start();

        setFocusTraversable(true);
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(controller);

        canvas.setOnMouseClicked(event -> canvas.requestFocus());

        Platform.runLater(canvas::requestFocus);
    }

    private void draw(GraphicsContext gc) {
        Field field = model.getField();

        for (int y = 0; y < field.getHeight(); ++y) {
            for (int x = 0; x < field.getWidth(); ++x) {
                switch (field.getType(x, y)) {
                    case VOID:
                        gc.setFill(Color.rgb(100, 100, 100));
                        break;
                    case EARTH:
                        gc.setFill(Color.rgb(200, 200, 200));
                        break;
                }
                gc.fillRect(x, y, 1, 1);
            }
        }

        gc.setFill(Color.YELLOW);
        for (Coin coin : model.getCoins()) {
            gc.fillRect(coin.getPositionX(), coin.getPositionY(), 10, 10);
        }

        gc.setFill(Color.BLACK);
        for (Ghost ghost : model.getGhosts()) {
            gc.fillRect(ghost.getPositionX(), ghost.getPositionY(), 20, 20);
        }

        gc.setFill(Color.rgb(181, 193, 142));
        gc.fillRect(model.getPlayer().getPositionX(), model.getPlayer().getPositionY(), 20, 20);

        String timeText = "Time: " + model.getTime();
        double x = 10;
        double y = 20;

        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        gc.strokeText(timeText, x, y);

        gc.setFill(Color.BLACK);
        gc.fillText(timeText, x, y);
    }

    public void showEndGameDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game Over");
        if(model.getPlayer().isAlive()) alert.setHeaderText("You won! Your time is " + model.getTime());
        else alert.setHeaderText("You lose!");
        alert.setContentText("Do you want to quit the game?");
        ButtonType quitButton = new ButtonType("Quit");
        alert.getButtonTypes().setAll(quitButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == quitButton) {
            Platform.runLater(() -> {
                Platform.exit();
                System.exit(0);
            });
        }
    }

}
