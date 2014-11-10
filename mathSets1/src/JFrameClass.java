/**
 * Created by Admin on 10.10.2014.
 */
import javax.swing.*;
public class JFrameClass extends JFrame {

    public JFrameClass(){
        super ("Henon");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        getContentPane().add( new DrawPanel());
    }
}
