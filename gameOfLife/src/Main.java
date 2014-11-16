import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Main extends JPanel implements ActionListener, ChangeListener {
    // Названия полей в классе всегда с маленькой буквы
    Animation gameOfLifeField;
    JButton button;
    JSlider fieldWidth;
    JSlider framesPerSecond;

    public Main() {
        setLayout(new GridBagLayout());
        // Избегайте, пожалуйста идентификаторов в один символ
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setBackground(Color.WHITE);

        gameOfLifeField = new Animation();
        gameOfLifeField.setPreferredSize(new Dimension(900, 750));
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        add(gameOfLifeField, gridBagConstraints);
        gameOfLifeField.setOpaque(true);

        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
        button = new JButton("Stop");
        button.setActionCommand("stop_start");
        button.addActionListener(this);
        right.setBackground(Color.WHITE);
        right.add(button);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        add(right, gridBagConstraints);

        framesPerSecond = new JSlider(JSlider.VERTICAL, 0, 6, 3);
        framesPerSecond.addChangeListener(this);
        framesPerSecond.setMajorTickSpacing(5);
        framesPerSecond.setPreferredSize(new Dimension(50, 200));
        framesPerSecond.setMinimumSize(new Dimension(50, 200));
        framesPerSecond.setMaximumSize(new Dimension(50, 200));
        framesPerSecond.setMinorTickSpacing(1);
        framesPerSecond.setPaintTicks(true);
        framesPerSecond.setPaintLabels(true);
        right.add(framesPerSecond);

        fieldWidth = new JSlider(JSlider.VERTICAL, 0, 80, 25);
        fieldWidth.addChangeListener(this);
        fieldWidth.setMajorTickSpacing(20);
        fieldWidth.setMinorTickSpacing(5);
        fieldWidth.setPreferredSize(new Dimension(50, 200));
        fieldWidth.setMinimumSize(new Dimension(50, 200));
        fieldWidth.setMaximumSize(new Dimension(50, 200));
        fieldWidth.setPaintTicks(true);
        fieldWidth.setPaintLabels(true);
        right.add(fieldWidth);
        gameOfLifeField.setWidth(50);
    }


    public void actionPerformed(ActionEvent e) {
        if ("stop_start".equals(e.getActionCommand())) {
            gameOfLifeField.stopped ^= true;
            button.setText(gameOfLifeField.stopped ? "Start" : "Stop");
        }
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            if (source.equals(framesPerSecond)) {
                int fps = source.getValue();
                System.out.println(fps);
                gameOfLifeField.setFPS(Math.pow(2, fps));
            } else if (source.equals(fieldWidth)) {
                int width = source.getValue();
                gameOfLifeField.setWidth(width);
            }
        }
        gameOfLifeField.update();
    }
    
    public static void createAndShowGUI() {
        JFrame frame = new JFrame();
        // С JFrame среда разработки ругается
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new Main());
        frame.setTitle("Conway's Game of Life");
        frame.setBackground(Color.WHITE);
        frame.setForeground(Color.WHITE);
        frame.pack();

        frame.setVisible(true);
    }


    public static void main(String s[]) {
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            createAndShowGUI();
	        }
    	});
    }
}