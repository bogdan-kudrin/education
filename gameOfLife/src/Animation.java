import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Animation extends JLabel implements ActionListener {
    public Field game;
    public boolean stopped = false;
    Timer timer;
    final Color gr = new Color(0, 128, 0);
    int speedUp=1, width=50, height=50;

    public Animation() {
        this.setBackground(Color.WHITE);

        game = new Field();
        timer = new Timer(40, this);
        timer.setInitialDelay(40);
        timer.start();
    }


    public void actionPerformed(ActionEvent e) {
        if (stopped) return;
        for (int i=0; i<Math.max(1, speedUp); i++) game.step();
        repaint();
    }

    public void setFPS(int fps) {
        if (fps>25) timer.setDelay(40);
        else timer.setDelay(1000/fps);
        speedUp=fps/25;
        timer.restart();
    }

    public void setWidth(int w) {
        width=height=w;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Dimension d = getSize();
        
        g2.clearRect(0,  0,  d.width, d.height);

        double w = width, h = height, grid_x = d.width / w, grid_y = d.height / h;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (game.f[i][j] == true) {
                    g2.setPaint(gr);
                    g2.fill(new Ellipse2D.Double(grid_x * (j + 0.1), grid_y * (i + 0.1), grid_x * 0.8, grid_y * 0.8));
                }
            }
        }
        g2.setPaint(new Color(128, 128, 128));
        if (grid_x > 5 && grid_y > 5) {
            for (int i = 1; i < w; i++) {
                g2.draw(new Line2D.Double(i * grid_x, 0, i * grid_x, d.height));
            }
            for (int i = 1; i < h; i++) {
                g2.draw(new Line2D.Double(0, i * grid_y, d.width, i * grid_y));
            }
        }
    }
}