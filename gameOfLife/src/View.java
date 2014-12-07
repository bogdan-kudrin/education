/**
 * Created by Света on 07.12.2014.
 */
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;

public class View extends JFrame {

        Module F= new Module();
        public View (){
            super("Running points");
            setSize(600, 600);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public void paint(Graphics g)
        {
            for(int i=0; i<500; i++)
                for (int j=0; j<500; j++) {
                    if ( F.A[i][j]) g.setColor(Color.blue);
                    else g.setColor(Color.pink);
                    g.drawLine(i + 50, j + 50, i + 50, j + 50);
                }
        }

    }

