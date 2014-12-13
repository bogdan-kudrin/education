
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class View extends JFrame {
    Space space = new Space();
    Buttons buttons = new Buttons();

    View(String title) {
        super(title);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.add(space, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
        pack();

        timer = new Timer(125, timerListener);

        timer.start();
        timer.stop();
    }

    Timer timer;

    ActionListener timerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            space.model.iterate();
            if (space.model.nothingChanged) {
                space.pause();
            }
            else {
                repaint();
            }
        }
    };

    class Space extends JPanel implements MouseListener {
        int xLimits = 987, yLimits = 610;
        Model model = new Model(100, 100);
        boolean paused = true;

        Space() {
            super();
            setPreferredSize(new Dimension(xLimits, yLimits));
            addMouseListener(this);
            model.countCellSize(xLimits, yLimits);
        }

        public void paint(Graphics g) {

            super.paintComponent(g);
            for (int i = 0; i < model.getHeight(); ++i) {
                for (int j = 0; j < model.getWidth(); ++j) {
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

            if (paused) {
                int i = model.rowOfTheCellWithPlanarCoordinates(e.getX(), e.getY());
                int j = model.columnOfTheCellWithPlanarCoordinates(e.getX(), e.getY());
                model.changeTheCellWithCoordinates(i, j);
                repaint();
            }
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

        public void pause () {
            space.paused = true;
            timer.stop ();
            buttons.start.setEnabled(true);
            buttons.stop.setEnabled(false);
            buttons.clear.setEnabled(true);
            buttons.step.setEnabled(true);
        }
    }

    class Buttons extends JPanel {
        JButton start = new JButton("Go!");
        JButton step = new JButton("Step");
        JButton stop = new JButton("Stop");
        JButton clear = new JButton("Clear");

        ActionListener startListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                space.paused = false;
                timer.restart ();
                start.setEnabled(false);
                stop.setEnabled(true);
                clear.setEnabled(false);
                step.setEnabled(false);
            }
        };

        ActionListener stopListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                space.pause();
            }
        };

        ActionListener clearListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                space.model.clear();
                space.repaint();
            }
        };

        ActionListener stepListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                space.model.iterate();
                space.repaint();
            }
        };

        Buttons () {
            super ();
            add(start);
            add(step);
            add (stop);
            add (clear);
            stop.setEnabled(false);

            stop.addActionListener(stopListener);
            start.addActionListener(startListener);
            clear.addActionListener(clearListener);
            step.addActionListener(stepListener);
        }
    }
}
