import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
public class JFrame1 {
	private int getwidth;
	private int getheight;
	private int maxwidth = 30;
	private int maxheight = 30;
	public int Getwidt (){
		return getwidth;
	}
	public int Getheight (){
		return getheight;
	}
	public void Setwidth(int width){
		getwidth = width;		
	}
	public void Setheight(int height){
		getheight = height;		
	}
	
	public void openframe1 (){
		final JFrame frame = new JFrame("Enter size.");
		JPanel panelup = new JPanel();
		JPanel paneldown = new JPanel();
		final JLabel verdict = new JLabel(" ");
		JLabel widthlabel = new JLabel("Width");
		JLabel heightlabel = new JLabel("Height");
		JLabel maxsize = new JLabel("Max size width = 30, height = 30");
		JButton ok = new JButton("OK");
		final JTextField width = new JTextField(15);
		final JTextField height = new JTextField(15);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    frame.setSize(400, 200);
	    frame.setMinimumSize(new Dimension(300, 0));
	    frame.setLayout(new BorderLayout());
	    frame.setVisible(true);
	    panelup.setSize(new Dimension(300, 150));
	    panelup.setBackground(new Color(255, 255, 255));
	    paneldown.setSize(new Dimension(300, 50));
	    paneldown.setBackground(new Color(255, 255, 255));
	    panelup.setLayout(new GridBagLayout());
	    panelup.add(width, new GridBagConstraints(1, 3, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets (0, 0, 0, 0), 0, 0));
	    panelup.add(verdict, new GridBagConstraints(1, 1, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets (0, 0, 0, 0), 0, 0));
	    panelup.add(widthlabel, new GridBagConstraints(0, 3, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets (0, 0, 0, 0), 0, 0));
	    panelup.add(height, new GridBagConstraints(1, 4, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets (0, 0, 0, 0), 0, 0));
	    panelup.add(heightlabel, new GridBagConstraints(0, 4, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets (0, 0, 0, 0), 0, 0));
	    panelup.add(maxsize, new GridBagConstraints(0, 2, 2, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets (0, 0, 0, 0), 0, 0));
	    paneldown.add(ok);
	    
	    class okActionListener implements ActionListener{
	    	private int width1;
	    	private int height1;
			public void actionPerformed(ActionEvent event) {
				width1 = Integer.valueOf(width.getText());
				height1 = Integer.valueOf(height.getText());
				if ((width1 > 0) && (width1 <= maxwidth)) {
					if ((height1 > 0) && (height1 <= maxheight)){
						getwidth = width1;
						getheight = height1;
					    frame.setVisible(false);
					}
					else verdict.setText("Height velue out of range!");
					
				}
				else {
					if ((height1 > 0) && (height1 <= maxheight))
						verdict.setText("Width velue out of range!");
					else
						verdict.setText("Height and Width value's out of range!");
				}
			}    	
	    }

	    ok.addActionListener(new okActionListener());
	    frame.add(panelup, BorderLayout.NORTH);
	    frame.add(paneldown, BorderLayout.SOUTH);
	    frame.pack();
	    



	    
	}
}
