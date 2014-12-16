import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class View extends JFrame {
    Model model = new Model(8);
    BigPanel bigPanel= new BigPanel();
    SouthPanel southPanel = new SouthPanel();
    NorthPanel northPanel = new NorthPanel();
    Timer timer;

    ActionListener timerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.doIteration();
            repaint();
        }
    };

    View(String s) {
        super(s);
        setLayout(new BorderLayout());
        this.add(bigPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(northPanel, BorderLayout.NORTH);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        timer= new Timer(1000, timerListener);
        timer.start();
        timer.stop();
    }

    public void globalRepaint() {
        repaint();
    }

    class BigPanel extends JPanel implements MouseListener {
        boolean pause = true;
        int size;
        int cellSize;

        BigPanel() {
            super();
            addMouseListener(this);
            setModel();
        }

        public void setModel(){
            size = model.getSize();
            cellSize = model.getCellSize();
            setPreferredSize(new Dimension(model.getPanelSize(), model.getPanelSize()));
        }

        public void paint(Graphics g) {
            g.setColor(Color.black);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    g.setColor(model.getCell(i,j) ? Color.red : Color.white);
                    g.fillRect(cellSize * j, cellSize * i, cellSize, cellSize);
                }
            }
        }

        public void mousePressed(MouseEvent evt) {
            if (pause) {
                model.findAndChangeCell( evt.getX(),evt.getY());
                globalRepaint();
            }
        }

        public void mouseEntered(MouseEvent evt) {
        }

        public void mouseExited(MouseEvent evt) {
        }

        public void mouseClicked(MouseEvent evt) {
        }

        public void mouseReleased(MouseEvent evt) {
        }
    }

    public  void pause() {
        timer.stop();
        northPanel.start.setEnabled(true);
        bigPanel.pause = true;
    }

    class NorthPanel extends JPanel {
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");

        ActionListener startListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.restart();
                start.setEnabled(false);
                bigPanel.pause = false;
            }
        };

        ActionListener stopListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause();
            }
        };

        NorthPanel() {
            super();
            add(start);
            add(stop);
            stop.addActionListener(stopListener);
            start.addActionListener(startListener);
        }
    }

    class SouthPanel extends JPanel{
        JButton newField = new JButton("New field");
        JTextField textField = new JTextField(3);

        ActionListener newFieldListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = 0;
                try {
                    size = Integer.valueOf(textField.getText());
                }
                catch (NumberFormatException e1) {
                    return;
                }
                pause();
                model=new Model(size);
                bigPanel.setModel();
                pack();
                globalRepaint();
            }
        };

        SouthPanel() {
            super();
            add(newField);
            add(textField);
            newField.addActionListener(newFieldListener);
        }
    }


}
