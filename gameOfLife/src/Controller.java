import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Паша on 10.11.2014.
 */
class Controller {
    Timer timer;
    //Избегайте имен в один символ
    View draw = new View("Жизнь");
    ActionListener timerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            draw.bigpanel.model.doIteration();
            draw.repaint();
        }

    };
    ActionListener stopListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            draw.littlePanel.start.setEnabled(true);
            draw.bigpanel.pause = true;
        }
    };
    ActionListener startListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.restart();
            draw.littlePanel.start.setEnabled(false);
            draw.bigpanel.pause = false;
        }
    };

    Controller() {
        timer = new Timer(1000, timerListener);
        draw.littlePanel.stop.addActionListener(stopListener);
        draw.littlePanel.start.addActionListener(startListener);
        timer.start();
        timer.stop();
    }
}
