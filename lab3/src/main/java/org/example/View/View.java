package org.example.View;

import org.example.Controller.Controller;
import org.example.Model.Coin;
import org.example.Model.Field;
import org.example.Model.Ghost;
import org.example.Model.Model;
import java.util.*;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel{
    private Model model;
    private Controller controller;
    private JFrame frame;
    private java.util.Timer timer;
    private long lastState;
    public View(Model model, Controller controller){
        this.model = model;
        this.controller = controller;
        this.timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long curState = model.getState();
                if(lastState != model.getState()) {
                    if(model.isEnded()){
                        if(model.getPlayer().isAlive()) showEndGameDialog("won");
                        else showEndGameDialog("lose");
                    } else repaint();
                    lastState = curState;
                }
            }
        }, 30, 30);
        setMinimumSize(new Dimension(model.getField().getWidth(), model.getField().getHeight()));
        initialize();
    }


    private void initialize() {
        frame = new JFrame("Digger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.addKeyListener(controller);
        frame.setLayout(new BorderLayout());

        int fieldWidth = model.getField().getWidth();
        int fieldHeight = model.getField().getHeight();

        frame.setResizable(false);
        frame.add(this, BorderLayout.CENTER);
        frame.setVisible(true);

        Insets insets = frame.getInsets();
        int borderX = insets.left + insets.right;
        int borderY = insets.top + insets.bottom;

        frame.setSize(fieldWidth + borderX, fieldHeight + borderY);
        frame.setMinimumSize(new Dimension(fieldWidth + borderX, fieldHeight + borderY));
    }

    public void showEndGameDialog(String condition) {
        Object[] options = {"quit"};
        int choice = JOptionPane.showOptionDialog(frame,
                "Do you want to quit the game?",
                "You " + condition + "!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Field field = model.getField();

        for(int y = 0; y < field.getHeight(); ++y){
            for(int x = 0; x < field.getWidth(); ++x){
                switch (field.getType(x, y)){
                    case VOID -> g.setColor(new Color(100, 100, 100));
                    case EARTH -> g.setColor(new Color(200, 200 ,200));
                }
                g.fillRect(x, y, 1, 1);
            }
        }

        g.setColor(Color.YELLOW);
        for (Coin coin : model.getCoins()){
            g.fillRect(coin.getPositionX(), coin.getPositionY(), 10, 10);
        }

        g.setColor(Color.black);
        for (Ghost ghost : model.getGhosts()){
            g.fillRect(ghost.getPositionX(), ghost.getPositionY(), 20, 20);
        }

        g.setColor(new Color(181, 193, 142));
        g.fillRect(model.getPlayer().getPositionX(), model.getPlayer().getPositionY(), 20, 20);
    }

}