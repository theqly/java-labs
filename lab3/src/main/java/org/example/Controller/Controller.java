package org.example.Controller;

import org.example.Model.*;
import org.example.View.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    private Model model;

    public void setModel(Model model){
        this.model = model;
    }


    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()){
            case KeyEvent.VK_W -> model.movePlayer(Player.direction.UP);
            case KeyEvent.VK_A -> model.movePlayer(Player.direction.LEFT);
            case KeyEvent.VK_S -> model.movePlayer(Player.direction.DOWN);
            case KeyEvent.VK_D -> model.movePlayer(Player.direction.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

}
