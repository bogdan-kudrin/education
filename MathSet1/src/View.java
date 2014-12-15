import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Пользователь on 15.12.2014.
 */
public class View extends JFrame {

    MyPanel myPanel = new MyPanel();
    Model model = new Model();

    ActionListener timerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
          model.iterate();
            repaint();
        }
    };

    Timer timer = new Timer(1000,timerListener);

    View(String s) {
        super(s);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(myPanel);
        pack();
        timer.start();
    }


    class MyPanel extends JPanel {

        MyPanel() {
            super();
            setVisible(true);
            setPreferredSize(new Dimension(800, 800));
        }

        public void paint(Graphics g) {
            for (int i = 0; i < 200; i++) {
                for (int j = 0; j < 200; j++) {
                    g.setColor(new Color(model.getColor(i, j), 0, 0));
                    g.fillRect(i * 4, j * 4, 4, 4);
                }
            }
        }
    }
}
