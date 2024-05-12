package org.example.View;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import org.example.Controller.Controller;
import org.example.Controller.ControllerWrapper;
import org.example.Model.Coin;
import org.example.Model.Field;
import org.example.Model.Ghost;
import org.example.Model.Model;

import java.util.Optional;

public class ViewFX extends BorderPane {
    private Model model;
    private Controller controller;
    private AnimationTimer animationTimer;
    private long lastState;

    public ViewFX(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;

        Canvas canvas = new Canvas(model.getField().getWidth(), model.getField().getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        setCenter(canvas);

        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long curState = model.getState();
                if (lastState != model.getState()) {
                    if (model.isEnded()) {
                        if (model.getPlayer().isAlive()) {
                            showEndGameDialog("won");
                        } else {
                            showEndGameDialog("lose");
                        }
                    } else {
                        draw(gc);
                    }
                    lastState = curState;
                }
            }
        };
        animationTimer.start();

        setFocusTraversable(true);
        canvas.setOnKeyPressed(new ControllerWrapper(controller));
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
    }

    public void showEndGameDialog(String condition) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("You " + condition + "!");
        alert.setContentText("Do you want to quit the game?");
        ButtonType quitButton = new ButtonType("Quit");
        ButtonType continueButton = new ButtonType("Continue");
        alert.getButtonTypes().setAll(quitButton, continueButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == quitButton) {
            System.exit(0);
        }
    }
}
