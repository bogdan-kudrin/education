/**
 * Created by Паша on 14.11.2014.
 */
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class View extends JFrame  {
    Controller controller = new Controller(this);
    //Не бойтесь делать отступы - тогда легче читать код
    BigPanel bigPanel= new BigPanel();
    //Имена переменных пишут camelCase'ом
    LittlePanel littlePanel = new LittlePanel();

    NorthPanel northPanel = new NorthPanel();

    EastPanel eastPanel = new EastPanel();

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
        controller.prepareForWork();
    }

    class BigPanel extends JPanel implements MouseListener {
        Model model;
        boolean pause = true;

        int width;
        int height;
        int cellSize;

        BigPanel() {
            super();
            addMouseListener(this);
            setPreferredSize(new Dimension(controller.getPanelWidth(), controller.getPanelHeight()));
            setModel(controller.getModel());
        }

         public void setModel(Model model){

             this.model=model;

             width = model.getWidth();
             height = model.getHeight();
             cellSize = model.getCellSize();
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
               controller.findAndChangeCell( evt.getX(),evt.getY());
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

    class LittlePanel extends JPanel {
        JButton start = new JButton("Старт");
        JButton stop = new JButton("Пауза");

        LittlePanel() {
            super();
            add(start);
            add(stop);
        }
    }


    class NorthPanel extends JPanel{
        JLabel x=new JLabel();
        JTextField width=new JTextField(3);
        JTextField height=new JTextField(3);
        JButton newField = new JButton("Новое поле");
        NorthPanel(){
            super();
            x.setText("X");
            add(width);
            add(x);
            add(height);
            add(newField);
        }
    }

    class EastPanel extends JPanel{
        //JLabel label = new JLabel();

        JSlider slider = new JSlider(JSlider.VERTICAL,10,100,10);

        ChangeListener sliderListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                controller.setSpeed(
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


