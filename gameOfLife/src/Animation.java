import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

//Здорово, осталось только ввод живых клеток по клику сделать
public class Animation extends JLabel implements ActionListener, MouseListener {
    public Field game;
    public boolean stopped = false;
    Timer timer;
    final Color gr = new Color(0, 128, 0);
    //Лучше каждую переменную в своей строке писать
    double msPerFrame = 1000/8;
    double cntTicks = 0;
    int width = 25;
    int height = 25;

    public Animation() {
        this.setBackground(Color.WHITE);
        
        addMouseListener(this);

        game = new Field();
        timer = new Timer(125, this);
        timer.setInitialDelay(125);
        timer.start();
    }

    public void mouseClicked(MouseEvent e) {
    	Dimension d = getSize();
    	double grid_x = (double)d.width / width, grid_y = (double)d.height / height;
    	int x = (int)Math.floor((double)e.getX() / grid_x);
    	int y = (int)Math.floor((double)e.getY() / grid_y);
    	game.f[y][x] = game.f[y][x] ^ true;
    	repaint();
    	System.out.println(x);
    	System.out.println(y);
    	System.out.println(e.getX());
    	System.out.println(e.getX());
    }

    public void mouseReleased(MouseEvent e) {
    }
    
    public void mousePressed(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        if (stopped) return;
        cntTicks++;
        while (cntTicks >= msPerFrame / 125) {
        	cntTicks -= msPerFrame / 125;
        	game.step();
        }
        repaint();
    }

    public void setFPS(double fps) {
        //Всегда фигурные скобки в if/else
        //Или можно использовать тернарный оператор
    	msPerFrame = (double)1000/fps;
    	System.out.println(fps);
    }

    public void setWidth(int w) {
        width = height = w;
    }
    
    public void update() {
    	repaint();
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