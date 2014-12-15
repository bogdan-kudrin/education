/**
 * Created by Admin on 10.10.2014.
 */
import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public static int size = 500;

    public Frame(){
        super ("Отображение Хенона");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Panel panel = new Panel();
        panel.setPreferredSize(new Dimension(size, size));
        getContentPane().add(panel);
        pack();
    }
}
