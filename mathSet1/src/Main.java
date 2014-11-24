import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;

public class Main extends JPanel {

    Model m;

    public Main() {
        this.setPreferredSize(new Dimension(700, 700));
        m = new Model();
        for (int i = 0; i < 500; i++) m.nextStep();
        repaint();
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Dimension d = getSize();
        double eps=0.5;
        for (int i=0; i<m.height; i++) {
            for (int j = 0; j < m.width; j++) {
                if (m.f[i][j]) {
                    g2.fill(new Rectangle2D.Double((double)j*d.width/m.width-eps, (double)i*d.height/m.height-eps,
                            (double)d.width/m.width+2*eps, (double)d.height/m.height+2*eps));
                }
            }
        }
    }

    public static void main(String s[]) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(new Main());
        f.setTitle("Dynamic systems");
        f.setBackground(Color.WHITE);
        f.setForeground(Color.WHITE);
        f.pack();

        f.setVisible(true);
    }
}