package org.example.Menu;

import org.example.Digger;

import org.example.Model.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScoreBoard extends JPanel {
    public ScoreBoard(List<org.example.Model.Record> records, Digger mainApp) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> mainApp.showMainMenu());
        add(backButton);
        add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel titleLabel = new JLabel("High Scores");
        add(titleLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));

        for (org.example.Model.Record record : records) {
            JLabel recordLabel = new JLabel(record.getNickname() + ": " + record.getTime() + "s");
            add(recordLabel);
            add(Box.createRigidArea(new Dimension(0, 5)));
        }
    }
}
