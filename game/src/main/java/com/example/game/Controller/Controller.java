package com.example.game.Controller;

import com.example.game.Model.*;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Controller implements EventHandler<KeyEvent> {
    private Model model;

    public void setModel(Model model){
        this.model = model;
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case W -> model.movePlayer(Player.direction.UP);
            case A -> model.movePlayer(Player.direction.LEFT);
            case S -> model.movePlayer(Player.direction.DOWN);
            case D -> model.movePlayer(Player.direction.RIGHT);
        }
    }
}
