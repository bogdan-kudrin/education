import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Animation extends JLabel implements ActionListener, MouseListener {
	private Field game;
	private boolean stopped = false;
	private Timer timer;
	private final Color GREEN = new Color(0, 128, 0);
	private final Color GRAY = new Color(128, 128, 128);
	private double msPerFrame = 1000 / 8;
	private double cntTicks = 0;
	private int width = 25;
	private int height = 25;

	private final int minimumGridSize = 5;
	private final int timerDelay = (int)((double) 1000 / 32);

	public Animation(boolean makeStopped) {
		this.setBackground(Color.WHITE);

		addMouseListener(this);

		game = new Field();
		timer = new Timer((int) timerDelay, this);
		timer.setInitialDelay((int) timerDelay);
		if (!makeStopped) {
			timer.start();
		} else {
			stopped = true;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Dimension d = getSize();
		double gridX = (double) d.width / width, gridY = (double) d.height
				/ height;
		int x = (int) Math.floor(e.getX() / gridX);
		int y = (int) Math.floor(e.getY() / gridY);
		game.xorPoint(y, x);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (stopped)
			return;
		cntTicks++;
		update();
	}

	public void setFPS(double fps) {
		msPerFrame = 1000 / fps;
	}

	public void setWidth(int w) {
		width = w;
	}

	public void setHeight(int h) {
		height = h;
	}

	public void stop() {
		stopped = true;
		timer.stop();
	}

	public void start() {
		stopped = false;
		timer.start();
	}
	
	public boolean isStopped() {
		return stopped;
	}

	public void update() {
		while (cntTicks >= msPerFrame / timerDelay) {
			cntTicks -= msPerFrame / timerDelay;
			game.step();
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Dimension d = getSize();

		graphics.clearRect(0, 0, d.width, d.height);

		double gridX = (double) d.width / width, gridY = (double) d.height / height;

		graphics.setPaint(GREEN);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (game.getPoint(i, j)) {
					graphics.fill(new Ellipse2D.Double(gridX * (j + 0.1), gridY
							* (i + 0.1), gridX * 0.8, gridY * 0.8));
				}
			}
		}
		graphics.setPaint(GRAY);
		if (gridX > minimumGridSize && gridY > minimumGridSize) {
			for (int i = 1; i < width; i++) {
				graphics.draw(new Line2D.Double(i * gridX, 0, i * gridX,
						d.height));
			}
			for (int i = 1; i < height; i++) {
				graphics.draw(new Line2D.Double(0, i * gridY, d.width, i
						* gridY));
			}
		}
	}
}