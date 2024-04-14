package org.example.View;

import org.example.Controller.Controller;
import org.example.Model.Model;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel{
    private Model model;
    private Controller controller;
    private JFrame frame;
    public View(Model model, Controller controller){
        this.model = model;
        this.controller = controller;
        frame = new JFrame("Digger");
        initialize();
    }


    private void initialize() {
        frame.add(this);
        frame.setSize(new Dimension(model.getField().getHeight(), model.getField().getWidth()));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(model.getField().getHeight(), model.getField().getWidth()));
        frame.addKeyListener(controller);
        frame.setFocusable(true);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int pix = 50;
        for(int x = 0; x < model.getField().getWidth(); x += pix){
            for(int y = 0; y < model.getField().getHeight(); y += 20){
                if(model.getField().getMap()[x][y] == 0){
                    g.setColor(Color.BLACK);
                    g.fillRect(x*pix, y*pix, pix, pix);
                } else {
                    g.setColor(Color.WHITE);
                    g.fillRect(pix, y, pix, pix);
                }

            }
        }
    }

}