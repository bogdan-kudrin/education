
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class View extends JFrame {
    Space space = new Space ();

    View (String title) {
        super(title);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout ());

        this.add (space, BorderLayout.CENTER);
        pack ();

        timer = new Timer (1000, timerListener);

        timer.start ();
        timer.stop ();
    }

    Timer timer;

    ActionListener timerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            space.model.iterate();
            repaint();
        }
    };
}

class Space extends JPanel implements MouseListener{
    int xLimits = 987, yLimits = 610;
    Model model = new Model (10, 10);

    Space () {
        super ();
        setPreferredSize(new Dimension(xLimits, yLimits));
        addMouseListener(this);
        model.countCellSize(xLimits, yLimits);
    }
    public void paint (Graphics g){

        super.paintComponent(g);
        for (int i = 0 ; i < model.getHeight() ; ++i) {
            for (int j = 0 ; j < model.getWidth() ; ++j) {
                g.setColor(model.getCellValue(i, j) ? Color.green : Color.white);
                g.fillRect(model.xLeftUpperCornerOfTheCellWithCoordinates(i, j),
                        model.yLeftUpperCornerOfTheCellWithCoordinates(i, j),
                        model.getCellSize(), model.getCellSize());
                g.setColor(Color.black);
                g.drawRect(model.getxBorderFields() + j * model.getCellSize(),
                        model.getyBorderFields() + i * model.getCellSize(),
                        model.getCellSize(), model.getCellSize());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int i = model.rowOfTheCellWithPlanarCoordinates(e.getX(), e.getY());
        int j = model.columnOfTheCellWithPlanarCoordinates(e.getX(), e.getY());
        model.changeTheCellWithCoordinates(i, j);
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
