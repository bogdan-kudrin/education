import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by dan on 11/17/14.
 */
public class FieldOnFrame extends JPanel
                          implements MouseListener {

    private boolean[][] field;
    private int squareSize;
    private int height;
    private int width;
    private int eps;

    FieldOnFrame(int height, int width, boolean[][] field, int squareSize) {
        super();
        this.squareSize = squareSize;
        this.height = height;
        this.width = width;
        this.field = field;
        this.eps = squareSize / 2;
        addMouseListener(this);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }



    public void paint(Graphics g) {
        for (int r = 0; r < height; ++r) {
            for (int c = 0; c < width; ++c) {
                g.setColor(field[r][c] ? Color.red : Color.darkGray);
                g.fillRect(c * squareSize + eps, r * squareSize + eps, squareSize, squareSize);
                g.setColor(Color.black);
                g.drawRect(c * squareSize + eps, r * squareSize + eps, squareSize, squareSize);
            }
        }
    }


    public void applyChanges(int height, int width, boolean[][] field) {
        this.height = height;
        this.width = width;
        this.field = field;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int x = mouseEvent.getX() - eps;
        int y = mouseEvent.getY() - eps;
        Controller.changeSquare(y / squareSize, x / squareSize);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    public void changeSquare(int r, int c) {
        field[r][c] = !field[r][c];
        repaint();
    }
}
