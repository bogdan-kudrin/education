/**
 * Created by Admin on 08.10.2014.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DrawPanel extends JPanel {

    int n;

    Timer timer = new javax.swing.Timer( 500, new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            n++;
            repaint();
        }
    } );

    public DrawPanel(){
        n = 1;
        timer.start();
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();


        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(4));
        g2.setPaint(Color.black);
        //g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));

       // int n = 1000;
        Complex c = new Complex(0.377,-0.248);



        for ( int x = 0; x < getWidth(); x++)
            for (int y = 0; y < getHeight(); y ++){
                Complex z = new Complex((x - getWidth() / 2) / 75.0, (y - getHeight() / 2) / 75.0);
                int i;
                for ( i = 0; (i < n) && z.Proverka(); i++) {
                    z = z.Sqr();
                    z = z.Sum(c);
                }
                if (!z.Proverka()) {
                    int col = i % 255;
                    g2.setPaint(new Color(col,col,col));
                    g2.fill(new Rectangle2D.Double(x, y, 1, 1));
                }
            }



        g2.dispose();

    }


    }
