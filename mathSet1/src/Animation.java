import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Animation extends JPanel implements ActionListener {
	static final int timerDelay = 1000;
	Model model;
	int[][] f = new int[Model.defaultHeight][Model.defaultWidth];
	double[][] color = new double[Model.defaultHeight][Model.defaultWidth];
	int cntSteps = 0;
	int iterateSteps = 1;
	Main.DynamicsType type = Main.DynamicsType.COMPLEX_NUMBERS;

	void setType(Main.DynamicsType s) {
		type = s;
		model = new Model(s);
		f = new int[model.height][model.width];
		color = new double[model.height][model.width];
		cleanField();
		updateField();
		model.nextStep();
		updateField();
	}
	
	void setProperties(double a, double b, int width, int height) {
		model = new Model(type, a, b, width, height);
		f = new int[model.height][model.width];
		color = new double[model.height][model.width];
		cleanField();
		updateField();
		model.nextStep();
		updateField();
	}
	
	void setIterateSteps(int newIterateSteps) {
		iterateSteps = newIterateSteps;
	}
	
	double getA() {
		return model.a;
	}
	
	double getB() {
		return model.b;
	}

	private void cleanField() {
		cntSteps = 0;
		for (int i = 0; i < model.height; i++) {
			for (int j = 0; j < model.width; j++) {
				f[i][j] = 0;
			}
		}
	}

	private void updateField() {
		cntSteps++;
		for (int i = 0; i < model.height; i++) {
			for (int j = 0; j < model.width; j++) {
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
		setType(Main.DynamicsType.COMPLEX_NUMBERS);
		Timer timer = new Timer(timerDelay, this);
		timer.start();
		repaint();
	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < iterateSteps; i++) {
			model.nextStep();
			updateField();
		}
		repaint();
	}

	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		Dimension d = getSize();
		graphics.clearRect(0, 0, d.width, d.height);
		double eps = 0.5;
		for (int i = 0; i < model.height; i++) {
			for (int j = 0; j < model.width; j++) {
				graphics.setPaint(new Color(0, (int) (color[model.height - i
						- 1][j]), 0));
				graphics.fill(new Rectangle2D.Double((double) j * d.width
						/ model.width - eps, (double) i * d.height
						/ model.height - eps, (double) d.width / model.width
						+ 2 * eps, (double) d.height / model.height + 2 * eps));
			}
		}
		Font font = graphics.getFont();
		int style = font.getStyle();
		String name = font.getName();
		graphics.setFont(new Font(name, style, 20));
		graphics.setPaint(new Color(255, 255, 255));
		graphics.drawString("Количество шагов: " + cntSteps, 10, 30);
	}
}