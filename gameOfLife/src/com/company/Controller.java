package com.company;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Паша on 10.11.2014.
 */
class Controller{
    Timer timer;
    Draw d = new Draw("Жизнь");
    ActionListener timerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            d.bigpanel.m.do_iteration();
            d.repaint();
        }

    };
    ActionListener stopListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            d.littlepanel.start.setEnabled(true);
            d.bigpanel.pause=true;
        }
    };
    ActionListener startListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.restart();
            d.littlepanel.start.setEnabled(false);
            d.bigpanel.pause=false;
        }
    };
    Controller(){
        timer= new Timer(1000, timerListener);
        d.littlepanel.stop.addActionListener(stopListener);
        d.littlepanel.start.addActionListener(startListener);
        timer.start();
        timer.stop();
    }
}
