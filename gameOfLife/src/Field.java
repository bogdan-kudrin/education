/**
 * Created by alina on 17.12.14.
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
public class Field extends JPanel {

    public Model model;
    public boolean stopped;
    public Timer timer;

    private final ActionListener timerListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            model.nextStep();
            repaint();
        }
    };

    private final MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Dimension d = getSize();
            double gridX = (double) d.width / model.size, gridY = (double) d.height / model.size;
            int x = (int) Math.floor(e.getX() / gridX);
            int y = (int) Math.floor(e.getY() / gridY);
            model.field[y][x] = !model.field[y][x];
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    };

    public Field() {
        model = new Model();

        addMouseListener(mouseListener);

        timer = new Timer(100, timerListener);

        setPreferredSize(new Dimension(700, 700));
        this.setBackground(Color.WHITE);

        stopped = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Dimension dimension = getSize();

        int width = dimension.width, height = dimension.height;

        graphics.clearRect(0, 0, width, height);

        double gridX = (double) width / model.size, gridY = (double) height / model.size;

        graphics.setPaint(new Color(255, 0, 0));
        for (int i = 0; i < model.size; i++) {
            for (int j = 0; j < model.size; j++) {
                if (model.field[i][j]) {
                    graphics.fill(new Ellipse2D.Double(gridX * (j + 0.1), gridY * (i + 0.1), gridX * 0.8, gridY * 0.8));
                }
            }
        }
        graphics.setPaint(new Color(0, 0, 128));
        for (int i = 1; i < model.size; i++) {
            graphics.draw(new Line2D.Double(i * gridX, 0, i * gridX, height));
        }
        for (int i = 1; i < model.size; i++) {
            graphics.draw(new Line2D.Double(0, i * gridY, width, i * gridY));
        }
    }
}
