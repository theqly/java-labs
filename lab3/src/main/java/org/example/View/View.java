package org.example.View;

import org.example.Controller.Controller;
import org.example.Model.Field;
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
        frame.setSize(model.getField().getWidth(), model.getField().getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.addKeyListener(controller);
        this.setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Field field = model.getField();
        for(int y = 0; y < field.getHeight(); ++y){
            for(int x = 0; x < field.getWidth(); ++x){
                if(field.getType(x, y) == Field.type.VOID){
                    g.setColor(new Color(120, 95, 63));
                } else {
                    g.setColor(new Color(222, 172, 128));
                }
                g.fillRect(x, y, 1, 1);
            }
        }
        g.setColor(new Color(181, 193, 142));
        g.fillRect(model.getPlayer().getPositionX(), model.getPlayer().getPositionY(), 20, 20);
    }

}