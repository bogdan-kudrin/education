/**
 * Created by alina on 17.12.14.
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class Main extends JFrame {
    public Main() {
        super("Dynamic systems");
        createAndShowGUI();
    }
    public void createAndShowGUI() {
        MainPanel panel = new MainPanel();
        add(panel);
        pack();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);
    }
    public static void main(String s[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Main();
            }
        });
    }
}
