package org.example.View;

import org.example.Controller.Controller;
import org.example.Controller.SliderController;
import org.example.Factory;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class View extends JPanel {
    private final Factory factory;
    private final Controller controller;
    private JFrame frame;
    private Timer timer;
    private JSlider bodySupplierDelaySlider;
    private JSlider engineSupplierDelaySlider;
    private JSlider accessorySupplierDelaySlider;
    private JSlider dealerDelaySlider;
    private JSlider workersDelaySlider;

    private JTextArea bodyStorageInfo;
    private JTextArea engineStorageInfo;
    private JTextArea accessoryStorageInfo;
    private JTextArea autoStorageInfo;

    public View(Factory factory, Controller controller) {
        this.factory = factory;
        this.controller = controller;
        initialize();
        startTimer();
    }

    private void initialize() {
        frame = new JFrame("Factory");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.addWindowListener(controller);
        frame.setLayout(new GridLayout(3, 1));

        JPanel sliderPanel = new JPanel(new GridLayout(5, 2));
        JPanel infoPanel = new JPanel(new GridLayout(1, 4));

        bodySupplierDelaySlider = createSlider(SliderController.SliderType.bodySupplierDelay);
        engineSupplierDelaySlider = createSlider(SliderController.SliderType.engineSupplierDelay);
        accessorySupplierDelaySlider = createSlider(SliderController.SliderType.accessorySupplierDelay);
        dealerDelaySlider = createSlider(SliderController.SliderType.dealerDelay);
        workersDelaySlider = createSlider(SliderController.SliderType.workersDelay);

        sliderPanel.add(new JLabel("Body Supplier Delay:"));
        sliderPanel.add(bodySupplierDelaySlider);
        sliderPanel.add(new JLabel("Engine Supplier Delay:"));
        sliderPanel.add(engineSupplierDelaySlider);
        sliderPanel.add(new JLabel("Accessory Supplier Delay:"));
        sliderPanel.add(accessorySupplierDelaySlider);
        sliderPanel.add(new JLabel("Dealer Delay:"));
        sliderPanel.add(dealerDelaySlider);
        sliderPanel.add(new JLabel("Worker Delay:"));
        sliderPanel.add(workersDelaySlider);

        bodyStorageInfo = new JTextArea();
        engineStorageInfo = new JTextArea();
        accessoryStorageInfo = new JTextArea();
        autoStorageInfo = new JTextArea();

        bodyStorageInfo.setEditable(false);
        engineStorageInfo.setEditable(false);
        accessoryStorageInfo.setEditable(false);
        autoStorageInfo.setEditable(false);

        infoPanel.add(createStoragePanel("Body Storage", bodyStorageInfo));
        infoPanel.add(createStoragePanel("Engine Storage", engineStorageInfo));
        infoPanel.add(createStoragePanel("Accessory Storage", accessoryStorageInfo));
        infoPanel.add(createStoragePanel("Auto Storage", autoStorageInfo));

        frame.add(sliderPanel);
        frame.add(infoPanel);

        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateStorageInfo(
                        factory.getBodyStorageSize(),
                        factory.getEngineStorageSize(),
                        factory.getAccessoryStorageSize(),
                        factory.getAutoStorageSize()
                );
            }
        }, 0, 5);  // Update every second
    }

    public void updateStorageInfo(int bodyInfo, int engineInfo, int accessoryInfo, int autoInfo) {
        bodyStorageInfo.setText("Body Storage: " + bodyInfo);
        engineStorageInfo.setText("Engine Storage: " + engineInfo);
        accessoryStorageInfo.setText("Accessory Storage: " + accessoryInfo);
        autoStorageInfo.setText("Auto Storage: " + autoInfo);
    }

    private JSlider createSlider(SliderController.SliderType type) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 1);
        slider.addChangeListener(new SliderController(factory, type));
        slider.setMajorTickSpacing(2);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        return slider;
    }

    private JPanel createStoragePanel(String title, JTextArea textArea) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(title), BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        return panel;
    }
}
