import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Паша on 15.11.2014.
 */
public class Controller {
    View view;
    Model model = new Model();

    ActionListener timerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.doIterate();
            view.repaint();
        }
    };

    Controller(View view){
        this.view=view;
        Timer timer= new Timer(1000,timerListener);
        timer.start();
    }

    public Model getModel()
    {return model;}
}
