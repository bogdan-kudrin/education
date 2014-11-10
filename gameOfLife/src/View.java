import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Паша on 10.11.2014.
 */

class View extends JFrame {
    //Не бойтесь делать отступы - тогда легче читать код
    BigPanel bigpanel = new BigPanel();
    //Имена переменных пишут camelCase'ом
    LittlePanel littlePanel = new LittlePanel(bigpanel.panelWidth);

    NorthPanel northPanel = new NorthPanel(bigpanel.panelWidth);
    View(String s) {
        super(s);
        setSize(bigpanel.panelWidth, bigpanel.panelHeight + 100);
        setLayout(new BorderLayout());
        this.add(bigpanel, BorderLayout.CENTER);
        this.add(littlePanel, BorderLayout.SOUTH);
        this.add(northPanel, BorderLayout.NORTH);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void globalRepaint() {
        repaint();
    }

    class BigPanel extends JPanel implements MouseListener {
        Model model = new Model(10,10);
        boolean pause = true;
        int panelWidth;
        int panelHeight;

        BigPanel() {
            super();
            panelWidth = model.width * model.cellSize + 100;
            panelHeight = model.height * model.cellSize + 100;
            setSize(panelWidth, panelHeight);
            addMouseListener(this);
        }

        public void paint(Graphics g) {

            g.setColor(Color.black);
            for (int i = 0; i < model.width + 1; i++) {
                g.drawLine(model.cellSize * i + 50, 50, model.cellSize * i + 50, model.cellSize * model.height + 50);
            }
            for (int i = 0; i < model.height + 1; i++) {
                g.drawLine(50, model.cellSize * i + 50, model.cellSize * model.width + 50, model.cellSize * i + 50);
            }

            for (int i = 0; i < model.height; i++) {
                for (int j = 0; j < model.width; j++) {
                    //Можно использовать тернарный оператор вместо простого if/else
                    g.setColor(model.field[i][j] ? Color.green : Color.white);
                    g.fillRect(model.cellSize * j + 51, model.cellSize * i + 51, model.cellSize - 2, model.cellSize - 2);

                }
            }
        }

        public void mousePressed(MouseEvent evt) {
            if (pause) {
                int x = evt.getX();
                int y = evt.getY();
                if (x > 50 & x < panelWidth - 50 & y > 50 & y < panelHeight - 50) {
                    int j = (x - 50) / model.cellSize;
                    int i = (y - 50) / model.cellSize;
                    model.field[i][j] = (!model.field[i][j]);
                    globalRepaint();
                }
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

        LittlePanel(int width) {
            super();
            setSize(width, 50);
            add(start);
            add(stop);
        }
    }
    class NorthPanel extends JPanel{
        JLabel x=new JLabel();
        JTextField width=new JTextField(3);
        JTextField height=new JTextField(3);
        JButton newField = new JButton("Новое поле");
        NorthPanel(int w){
            super();
            setSize(w, 50);
            x.setText("X");
            add(width);
            add(x);
            add(height);
            add(newField);
        }
    }
}

