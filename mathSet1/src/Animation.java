import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Animation extends JPanel implements ActionListener {
	static final int timerDelay = 200;
	Model model;
	int[][] f = new int[Model.height][Model.width];
	double[][] color = new double[Model.height][Model.width];
	int cntSteps = 0;

	void setType(String s) {
		model = new Model(s);
		cleanField();
		updateField();
		model.nextStep();
		updateField();
	}

	private void cleanField() {
		cntSteps = 0;
		for (int i = 0; i < Model.height; i++) {
			for (int j = 0; j < Model.width; j++) {
				f[i][j] = 0;
			}
		}
	}

	private void updateField() {
		cntSteps++;
		for (int i = 0; i < Model.height; i++) {
			for (int j = 0; j < Model.width; j++) {
				if (model.f[i][j]) {
					f[i][j]++;
				}
				color[i][j] = 255 * Math.log(f[i][j] + 1)
						/ Math.log(cntSteps + 1);
			}
		}
	}

	public Animation() {
		this.setPreferredSize(new Dimension(700, 700));
		setType("ComplexNumbers");
		Timer timer = new Timer(timerDelay, this);
		timer.start();
		repaint();
	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 1; i++) {
			model.nextStep();
			updateField();
		}
		repaint();
	}

	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		Dimension d = getSize();
		graphics.clearRect(0, 0, d.width, d.height);
		double eps = 0.5;
		for (int i = 0; i < Model.height; i++) {
			for (int j = 0; j < Model.width; j++) {
				graphics.setPaint(new Color(0, (int) (color[Model.height - i
						- 1][j]), 0));
				graphics.fill(new Rectangle2D.Double((double) j * d.width
						/ Model.width - eps, (double) i * d.height
						/ Model.height - eps, (double) d.width / Model.width
						+ 2 * eps, (double) d.height / Model.height + 2 * eps));
			}
		}
		Font font = graphics.getFont();
		int size = font.getSize();
		int style = font.getStyle();
		String name = font.getName();
		System.out.println(name + " " + style + " " + size);
		graphics.setFont(new Font(name, style, 20));
		graphics.setPaint(new Color(255, 255, 255));
		graphics.drawString("Count of steps: " + cntSteps, 10, 30);
	}
}