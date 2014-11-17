import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class View extends JFrame {
    Model model=new Model(10,10);
    //Не бойтесь делать отступы - тогда легче читать код
    BigPanel bigPanel= new BigPanel();
    //Имена переменных пишут camelCase'ом
    LittlePanel littlePanel = new LittlePanel();

    NorthPanel northPanel = new NorthPanel();

    EastPanel eastPanel = new EastPanel();

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
        this.add(littlePanel, BorderLayout.SOUTH);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(eastPanel,BorderLayout.EAST);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        timer= new Timer(1000, timerListener);

        timer.start();
        timer.stop();
    }
    public void globalRepaint(){
        repaint();
    }

    class BigPanel extends JPanel implements MouseListener {
        boolean pause = true;

        int width;
        int height;
        int cellSize;

        BigPanel() {
            super();
            addMouseListener(this);
            setModel();
        }

        public void setModel(){

            width = model.getWidth();
            height = model.getHeight();
            cellSize = model.getCellSize();
            setPreferredSize(new Dimension(model.getPanelWidth(), model.getPanelHeight()));
        }


        public void paint(Graphics g) {
            g.setColor(Color.black);
            for (int i = 0; i < width + 1; i++) {
                g.drawLine(cellSize * i + 50, 50, cellSize * i + 50, cellSize *height + 50);
            }
            for (int i = 0; i < height + 1; i++) {
                g.drawLine(50,cellSize * i + 50, cellSize * width + 50, cellSize * i + 50);
            }

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    //Можно использовать тернарный оператор вместо простого if/else
                    g.setColor(model.getCell(i,j) ? Color.green : Color.white);
                    g.fillRect(cellSize * j + 51, cellSize * i + 51,cellSize - 2, cellSize - 2);

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

    public  void pause(){
        timer.stop();
        littlePanel.start.setEnabled(true);
        bigPanel.pause = true;
    }

    public void setSpeed(int speed) {
        timer.setDelay(10000/speed);
    }


    class LittlePanel extends JPanel {
        JButton start = new JButton("Старт");
        JButton stop = new JButton("Пауза");

        ActionListener startListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.restart();
                littlePanel.start.setEnabled(false);
                bigPanel.pause = false;
            }
        };

        ActionListener stopListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause();
            }
        };

        LittlePanel() {
            super();
            add(start);
            add(stop);

            stop.addActionListener(stopListener);
            start.addActionListener(startListener);
        }
    }


    class NorthPanel extends JPanel{
        JLabel x=new JLabel();
        JTextField width=new JTextField(3);
        JTextField height=new JTextField(3);
        JButton newField = new JButton("Новое поле");

        ActionListener newFieldListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int width=0,height=0;

                try {
                    width = Integer.valueOf(
                            northPanel.width.getText()
                    );
                }catch (NumberFormatException e1) {return;}

                try {
                    height = Integer.valueOf(
                            northPanel.height.getText()
                    );
                }catch (NumberFormatException e1) {return;}

                pause();

                model=new Model(width,height);

                bigPanel.setModel();

                pack();
                globalRepaint();
            }
        };

        NorthPanel(){
            super();
            x.setText("X");
            add(width);
            add(x);
            add(height);
            add(newField);

            newField.addActionListener(newFieldListener);
        }
    }

    class EastPanel extends JPanel{

        JSlider slider = new JSlider(JSlider.VERTICAL,10,100,10);

        ChangeListener sliderListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setSpeed(
                        slider.getValue()
                );
            }
        };
        EastPanel(){
            super();

            add(slider);
            slider.addChangeListener(sliderListener);
        }
    }
}


