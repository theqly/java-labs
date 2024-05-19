package org.example.Menu;

import org.example.Digger;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    public Menu(Digger mainApp) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton playButton = new JButton("Play");
        playButton.addActionListener(e -> mainApp.startGame());

        JButton scoreBoardButton = new JButton("Score Board");
        scoreBoardButton.addActionListener(e -> mainApp.showScoreBoard());

        JButton aboutButton = new JButton("About");
        aboutButton.addActionListener(e -> mainApp.showAbout());

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> mainApp.quit());

        add(playButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(scoreBoardButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(aboutButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(quitButton);
    }
}
