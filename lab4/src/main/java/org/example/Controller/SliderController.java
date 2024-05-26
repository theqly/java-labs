package org.example.Controller;

import org.example.Factory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderController implements ChangeListener {

    public static enum SliderType{
        dealerDelay,
        workersDelay,
        bodySupplierDelay,
        engineSupplierDelay,
        accessorySupplierDelay,
    }
    private final SliderType type;
    private final Factory factory;
    public SliderController(Factory factory, SliderType type){
        this.factory = factory;
        this.type = type;
    }

    @Override
    public void stateChanged(ChangeEvent e){
        int delay = ((JSlider)e.getSource()).getValue();
        switch (type){
            case dealerDelay -> factory.setDealersDelay(delay * 1000);
            case workersDelay -> factory.setWorkersDelay(delay * 1000);
            case bodySupplierDelay -> factory.setBodySuppliersDelay(delay * 1000);
            case engineSupplierDelay -> factory.setEngineSuppliersDelay(delay * 1000);
            case accessorySupplierDelay -> factory.setAccessorySuppliersDelay(delay * 1000);
        }
    }
}
