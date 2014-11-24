import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;

public class Main extends JPanel implements ActionListener {

	private static final int timerDelay = 200;
    private Model model;
	private int[][] f = new int[Model.height][Model.width];
	private double[][] color = new double[Model.height][Model.width];
	private int cntSteps = 0;

	private void updateField() {
    	cntSteps++;
        for (int i=0; i<Model.height; i++) {
            for (int j = 0; j < Model.width; j++) {
                if (model.f[i][j]) {
                	f[i][j]++;
                }
                color[i][j] = 255*Math.log(f[i][j]+1)/Math.log(cntSteps+1);
            }
        }
	}
    public Main() {
        this.setPreferredSize(new Dimension(700, 700));
        model = new Model();
        updateField();
        Timer timer  = new Timer(timerDelay, this);
        timer.start();
        repaint();
    }
    
    public void actionPerformed(ActionEvent e) {
    	model.nextStep();
        updateField();
    	repaint();
    }

    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        Dimension d = getSize();
        graphics.clearRect(0, 0, d.width, d.height);
        double eps=0.5; 
        for (int i=0; i<Model.height; i++) {
            for (int j = 0; j < Model.width; j++) {
            		graphics.setPaint(new Color(0, (int)(color[Model.height - i - 1][j]), 0));
                    graphics.fill(new Rectangle2D.Double((double)j*d.width/Model.width-eps, (double)i*d.height/Model.height-eps,
                            (double)d.width/Model.width+2*eps, (double)d.height/Model.height+2*eps));
            }
        }
    }

	public static void createAndShowGUI() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(new Main());
        f.setTitle("Dynamic systems");
        f.setBackground(Color.BLACK);
        f.setForeground(Color.BLACK);
        f.pack();

        f.setVisible(true);
	}

	public static void main(String s[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}
}