/**
 * Created by Admin on 08.10.2014.
 */
import javax.swing.*;
public class JFrameClass extends JFrame {

    public JFrameClass(){
        super ("Фрактал");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        getContentPane().add( new DrawPanel());
    }
}
