import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Паша on 10.11.2014.
 */
public class Controller {
    View draw = new View("MathSets1");
    ActionListener timerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
         draw.model.doIterate();
            draw.repaint();
        }
    };
    Timer timer= new Timer(1000,timerListener);
    Controller(){timer.start();}
}
