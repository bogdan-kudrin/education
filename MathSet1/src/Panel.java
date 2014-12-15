/**
 * Created by Admin on 10.10.2014.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;

class Panel extends JPanel {

    int iterationsCount = 1;

    Timer timer = new javax.swing.Timer(500, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            iterationsCount++;
            repaint();
            if (iterationsCount == 20){
                timer.stop();
            }
        }
    });

    public Panel() {
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();


        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(4));
        g2.setPaint(Color.black);

        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Point point = new Point((i - width / 2) / ((double) width / 4), (j - height / 2) / ((double) width / 4));;
                for (int iterations = 0; iterations < iterationsCount; iterations++){
                    point.transform();
                }
                if (point.outOfField()) {
                    g2.setPaint(Color.BLACK);
                    g2.fill(new Rectangle2D.Double(i, j, 1, 1));
                }

            }
        }

        g2.dispose();

    }


}

