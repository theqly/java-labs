package org.example.Controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.awt.event.KeyListener;

public class ControllerWrapper implements EventHandler<KeyEvent> {
    private KeyListener keyListener;

    public ControllerWrapper(KeyListener keyListener) {
        this.keyListener = keyListener;
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                keyListener.keyPressed(new java.awt.event.KeyEvent(null, java.awt.event.KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, java.awt.event.KeyEvent.VK_UP, 'W'));
                break;
            case DOWN:
                keyListener.keyPressed(new java.awt.event.KeyEvent(null, java.awt.event.KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, java.awt.event.KeyEvent.VK_DOWN, 'S'));
                break;
            case LEFT:
                keyListener.keyPressed(new java.awt.event.KeyEvent(null, java.awt.event.KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, java.awt.event.KeyEvent.VK_LEFT, 'A'));
                break;
            case RIGHT:
                keyListener.keyPressed(new java.awt.event.KeyEvent(null, java.awt.event.KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, java.awt.event.KeyEvent.VK_RIGHT, 'D'));
                break;
        }
    }
}
