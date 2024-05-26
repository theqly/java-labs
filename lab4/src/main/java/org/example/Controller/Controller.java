package org.example.Controller;

import org.example.Factory;

import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Controller extends WindowAdapter {
    private Factory factory;
    public Controller(Factory factory){
        this.factory = factory;
    }

    @Override
    public void windowClosing(WindowEvent e){
        factory.stopWork();
    }
}
