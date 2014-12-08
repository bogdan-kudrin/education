import javax.swing.*;
import java.awt.*;

/**
 * Created by dan on 12/8/14.
 */
public class Controller {

    private Model model;
    private int height;
    private int width;
    private boolean[][] field;

    public Controller(double re, double im, int it) {
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        height = (int) (Math.min(sSize.getWidth(), sSize.getHeight()) * 0.95);
        width = height;
        model = new Model(re, im, it, height, width);
        field = model.getField();
        FieldOnFrame fieldOnFrame = new FieldOnFrame(height, width, field);
        JFrame frame = new JFrame();
        frame.getContentPane().add(fieldOnFrame);
        frame.setSize(sSize);
        frame.repaint();
        frame.setVisible(true);
    }
}
