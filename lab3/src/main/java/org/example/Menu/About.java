package org.example.Menu;

import org.example.Digger;

import javax.swing.*;
import java.awt.*;

public class About extends JPanel {
    public About(Digger mainApp) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> mainApp.showMainMenu());
        add(backButton);
        add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel aboutLabel = new JLabel("Game created by Artem Gaan");
        add(aboutLabel);
    }
}
