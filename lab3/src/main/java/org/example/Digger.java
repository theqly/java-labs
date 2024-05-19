package org.example;

import org.example.Controller.Controller;
import org.example.Model.*;
import org.example.View.View;
import org.example.Menu.*;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Digger extends JFrame {
    private JPanel currentPanel;
    private Model model;
    private View view;
    private Controller controller;

    public Digger() {
        setTitle("Digger");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        showMainMenu();
        setVisible(true);
    }

    public void showMainMenu() {
        currentPanel = new org.example.Menu.Menu(this);
        setContentPane(currentPanel);
        revalidate();
        repaint();
    }

    public void startGame() {
        String nickname = JOptionPane.showInputDialog(this, "Enter your Nickname:", "Player Nickname", JOptionPane.PLAIN_MESSAGE);
        if (nickname != null && !nickname.trim().isEmpty()) {
            model = new Model();
            model.setNickname(nickname);
            controller = new Controller();
            controller.setModel(model);
            view = new View(model, controller);
            model.setStartTime();
        }
    }

    public void showScoreBoard() {
        List<org.example.Model.Record> recordsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("/home/whoistheql/programming/projects/java-labs/lab3/src/main/resources/score_board.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(": ");
                recordsList.add(new org.example.Model.Record(parts[0], Double.parseDouble(parts[1])));
            }
        } catch (IOException e) {
            System.out.println("Error loading records: " + e.getMessage());
        }
        currentPanel = new ScoreBoard(recordsList, this);
        setContentPane(currentPanel);
        revalidate();
        repaint();
    }

    public void showAbout() {
        currentPanel = new About(this);
        setContentPane(currentPanel);
        revalidate();
        repaint();
    }

    public void quit() {
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Digger::new);
    }
}
