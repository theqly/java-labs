package org.example.Controller;

import org.example.Model.*;
import org.example.View.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    private Model model;
    private View view;

    public void setModel(Model model){
        this.model = model;
    }

    public void setView(View view){
        this.view = view;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()){
            case KeyEvent.VK_W -> model.movePlayer(0);
            case KeyEvent.VK_S -> model.movePlayer(1);
            case KeyEvent.VK_A -> model.movePlayer(2);
            case KeyEvent.VK_D -> model.movePlayer(3);
        }
        System.out.println("key pressed\n");
        view.repaint();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

}
