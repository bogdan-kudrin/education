import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Main extends JPanel implements ActionListener, ChangeListener {
    Animation GameOfLifeField;
    JButton button;
    JSlider fieldWidth, framesPerSecond;

    public Main() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setBackground(Color.WHITE);

        GameOfLifeField = new Animation();
        GameOfLifeField.setPreferredSize(new Dimension(900, 750));
        c.fill = GridBagConstraints.BOTH;
        c.weightx=1;
        c.weighty=1;
        add(GameOfLifeField, c);
        GameOfLifeField.setOpaque(true);

        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
        button = new JButton("Stop");
        button.setActionCommand("stop_start");
        button.addActionListener(this);
        right.setBackground(Color.WHITE);
        right.add(button);
        c.gridx = 1;
        c.weightx=0;
        c.weighty=0;
        c.fill=GridBagConstraints.NONE;
        c.anchor=GridBagConstraints.NORTH;
        add(right, c);

        framesPerSecond = new JSlider(JSlider.VERTICAL, 0, 20, 10);
        GameOfLifeField.setFPS((int)Math.sqrt(1<<10));
        framesPerSecond.addChangeListener(this);
        framesPerSecond.setMajorTickSpacing(5);
        framesPerSecond.setPreferredSize(new Dimension(50, 200));
        framesPerSecond.setMinimumSize(new Dimension(50, 200));
        framesPerSecond.setMaximumSize(new Dimension(50, 200));
        framesPerSecond.setMinorTickSpacing(1);
        framesPerSecond.setPaintTicks(true);
        framesPerSecond.setPaintLabels(true);
        right.add(framesPerSecond);

        fieldWidth = new JSlider(JSlider.VERTICAL, 0, 80, 50);
        fieldWidth.addChangeListener(this);
        fieldWidth.setMajorTickSpacing(20);
        fieldWidth.setMinorTickSpacing(5);
        fieldWidth.setPreferredSize(new Dimension(50, 200));
        fieldWidth.setMinimumSize(new Dimension(50, 200));
        fieldWidth.setMaximumSize(new Dimension(50, 200));
        fieldWidth.setPaintTicks(true);
        fieldWidth.setPaintLabels(true);
        right.add(fieldWidth);
        GameOfLifeField.setWidth(50);
    }


    public void actionPerformed(ActionEvent e) {
        if ("stop_start".equals(e.getActionCommand())) {
            GameOfLifeField.stopped^=true;
            button.setText(GameOfLifeField.stopped ? "Start" : "Stop");
        }
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            if (source.equals(framesPerSecond)) {
                int fps = source.getValue();
                GameOfLifeField.setFPS((int) Math.sqrt(1 << fps));
            } else if (source.equals(fieldWidth)) {
                int width = source.getValue();
                GameOfLifeField.setWidth(width);
            }
        }
    }


    public static void main(String s[]) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(new Main());
        f.setTitle("Conway's Game of Life");
        f.setBackground(Color.WHITE);
        f.setForeground(Color.WHITE);
        f.pack();

        f.setVisible(true);
    }
}