/**
 * Created by alina on 17.12.14.
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class MainPanel extends JPanel {
    Field graphics;
    JTextField sizeField;

    private final ActionListener startListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            graphics.stopped = true;
            graphics.timer.start();
        }
    };
    private final ActionListener stopListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            graphics.stopped = false;
            graphics.timer.stop();
        }
    };
    private final ActionListener changeSizeListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            graphics.stopped = false;
            graphics.timer.stop();
            graphics.model = new Model(Integer.parseInt(sizeField.getText()));
            graphics.repaint();
        }
    };
    public MainPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JButton startButton = new JButton("Старт");
        JButton stopButton = new JButton("Стоп");
        sizeField = new JTextField(5);
        JButton changeSize = new JButton("Изменить размер поля");

        startButton.addActionListener(startListener);
        stopButton.addActionListener(stopListener);
        changeSize.addActionListener(changeSizeListener);

        add(startButton);
        add(stopButton);
        add(sizeField);
        add(changeSize);
        graphics = new Field();
        add(graphics);
    }
}
