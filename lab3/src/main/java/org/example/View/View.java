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
        for(int x = 0; x < model.getField().getHeight(); ++x){
            for(int y = 0; y < model.getField().getWidth(); ++y){
                if(model.getField().getType(x, y) == 0){
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, 1, 1);
                } else {
                    g.setColor(Color.WHITE);
                    g.fillRect(x, y, 1, 1);
                }

            }
        }
    }

}