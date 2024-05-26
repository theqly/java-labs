package org.example.View;

import org.example.Controller.Controller;
import org.example.Controller.SliderController;
import org.example.Factory;

import java.util.*;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel{
    private final Factory factory;
    private final Controller controller;
    private JFrame frame;
    private java.util.Timer timer;
    private final int width = 1280;
    private final int height = 720;

    public View(Factory factory, Controller controller){
        this.factory = factory;
        this.controller = controller;
        this.timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 5, 5);
        initialize();
    }


    private void initialize() {
        frame = new JFrame("Factory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.addWindowListener(controller);
        frame.setLayout(new BorderLayout());

        frame.setResizable(false);

        frame.setSize(width, height);
        addSlider("Dealers Delay", SliderController.SliderType.dealerDelay);
        addSlider("Workers Delay", SliderController.SliderType.workersDelay);
        addSlider("Accessory Suppliers", SliderController.SliderType.accessorySupplierDelay);
        addSlider("Body Suppliers", SliderController.SliderType.bodySupplierDelay);
        addSlider("Engine Suppliers", SliderController.SliderType.engineSupplierDelay);

        frame.add(this, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawStorage(g, "Accessories", factory.getAccessoryStorageSize(), 0);
        drawStorage(g, "Engines", factory.getEngineStorageSize(), 1);
        drawStorage(g, "Bodies", factory.getBodyStorageSize(), 2);
        drawStorage(g, "Cars", factory.getBodyStorageSize(), 3);
    }

    private void drawStorage(Graphics g, String itemType, int amount, int line) {
        g.setColor(Color.BLACK);
        int rectX = width / 5 * 3;
        int rectY = 15 + (height - 40) / 4 * line;
        g.fillRect(rectX, rectY, 150, 80);
        g.setColor((Color.WHITE));
        g.setFont(new Font("Arial", Font.BOLD, 14));
        String string = itemType + ": " +amount;
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(string);
        int textHeight = fm.getHeight();
        int x = rectX + (150 - textWidth) / 2;
        int y = rectY + (80 - textHeight) / 2 + fm.getAscent();
        g.drawString(string, x, y);
    }

    private void addSlider(String name, SliderController.SliderType ID) {
        JSlider slider = createSlider(ID);
        JLabel label = new JLabel(name);

        GridLayout layout = new GridLayout(5, 2, 15, 15);
        setLayout(layout);
        add(slider);
        add(label);
    }

    private JSlider createSlider(SliderController.SliderType ID) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 20, 1);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(2);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.addChangeListener(new SliderController(factory, ID));
        return slider;
    }
}