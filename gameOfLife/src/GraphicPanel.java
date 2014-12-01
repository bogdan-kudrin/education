import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class GraphicPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Line line;
	GraphicPanel () {
		line = new Line (100, 20, 10, 150);
	}
	@Override
	public void paintComponent (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;	
		g2.draw(line);
	}
	

}
