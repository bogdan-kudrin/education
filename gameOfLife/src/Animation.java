import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

//Здорово, осталось только ввод живых клеток по клику сделать
public class Animation extends JLabel implements ActionListener {
    public Field game;
    public boolean stopped = false;
    Timer timer;
    final Color gr = new Color(0, 128, 0);
    //Лучше каждую переменную в своей строке писать
    int speedUp = 1;
    int width = 50;
    int height = 50;

    public Animation() {
        this.setBackground(Color.WHITE);

        game = new Field();
        timer = new Timer(40, this);
        timer.setInitialDelay(40);
        timer.start();
    }


    public void actionPerformed(ActionEvent e) {
        if (stopped) return;
        // После for ставят фигурные скобки, всегда
        for (int i = 0; i < Math.max(1, speedUp); i++) {
            game.step();
        }
        repaint();
    }

    public void setFPS(int fps) {
        //Всегда фигурные скобки в if/else
        //Или можно использовать тернарный оператор
        timer.setDelay(fps > 25 ? 40 : 1000 / fps);
        speedUp = fps / 25;
        timer.restart();
    }

    public void setWidth(int w) {
        width = height = w;
    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Dimension d = getSize();

        graphics.clearRect(0, 0, d.width, d.height);

        double w = width, h = height, grid_x = d.width / w, grid_y = d.height / h;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                //Можно просто так, сравнение с true избыточно
                if (game.f[i][j]) {
                    graphics.setPaint(gr);
                    graphics.fill(new Ellipse2D.Double(grid_x * (j + 0.1), grid_y * (i + 0.1), grid_x * 0.8, grid_y * 0.8));
                }
            }
        }
        graphics.setPaint(new Color(128, 128, 128));
        if (grid_x > 5 && grid_y > 5) {
            for (int i = 1; i < w; i++) {
                graphics.draw(new Line2D.Double(i * grid_x, 0, i * grid_x, d.height));
            }
            for (int i = 1; i < h; i++) {
                graphics.draw(new Line2D.Double(0, i * grid_y, d.width, i * grid_y));
            }
        }
    }
}