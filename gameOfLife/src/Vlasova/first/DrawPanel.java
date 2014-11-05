/**
 * Created by Admin on 10.10.2014.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

class DrawPanel extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();


        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(4));
        g2.setPaint(Color.black);
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));

        int n = 1000;

        for ( int x = 0; x < getWidth(); x++)
            for (int y = 0; y < getHeight(); y ++){
                int i;
                double x0 = (x - getWidth() / 2)/ 75.0;
                double y0 = (y - getHeight() / 2) / 75.0;
                for ( i = 0; (i < n) && (Math.abs(x0) <= 2) &&(Math.abs(y0) <= 2) ; i++)
                {
                    double temp = y0;
                    y0 = 0.3 * x0;
                    x0 = 1 + temp / 0.7 - 1.4*x0*x0;

                }
                if ((Math.abs(x0) > 2) ||(Math.abs(y0) > 2) ) {
                    int col = 255 - i % 255;
                    g2.setPaint(new Color(col,col,col));
                    g2.fill(new Rectangle2D.Double(x, y, 1, 1));
                }
            }



        g2.dispose();

    }


}

