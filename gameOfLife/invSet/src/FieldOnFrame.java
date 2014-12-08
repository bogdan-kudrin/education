import javax.swing.*;
import java.awt.*;

/**
 * Created by dan on 12/8/14.
 */
public class FieldOnFrame extends JPanel {
    private int height;
    private int width;
    private boolean[][] field;
    private int eps = 10;

    FieldOnFrame(int height, int width, boolean[][] field) {
        this.height = height;
        this.width = width;
        this.field = field;
    }

    public void paint(Graphics g) {
        for (int r = 0; r < height; ++r) {
            for (int c = 0; c < width; ++c) {
                g.setColor(field[r][c] ? Color.red : Color.cyan);
                g.drawRect(c + eps, r + eps, 1, 1);
            }
        }
    }
}
