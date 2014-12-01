import java.awt.BorderLayout;


import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
public class JFrame2 {
	public void openframe2 (final int width, final int height){
		final JFrame frame = new JFrame ();
		frame.setLayout(new BorderLayout());
		//frame.setLocationRelativeTo(null);
	    frame.setSize(800, 600);
		JPanel panelup = new JPanel();
		//JButton button = new JButton("button");
		GraphicPanel graphicpanel = new GraphicPanel();
		panelup.setLayout(new GridBagLayout());
		//panelup.add(button,new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets (0, 0, 0, 0), 0, 0));
		panelup.setBackground(new Color(0, 0, 0));
		//panelup.add(graphicpanel, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets (0, 0, 0, 0), 0, 0));
		
		
		 frame.setVisible(true);
	
		 final Controller myController = new Controller ();
		 final JPanel[][] table = new JPanel[width][height];
		 final Model model = new Model(width, height);
		 JPanel[] setka = new JPanel[width];
		 JPanel[] setka2 = new JPanel[height];
		 model.revitalize(0, 1, 1);
		 model.revitalize(1, 2, 1);
		 model.revitalize(2, 0, 1);
		 model.revitalize(2, 1, 1);
		 model.revitalize(2, 2, 1);
		 
		 
		 
		 class TimerActionListener implements ActionListener{
			 public void actionPerformed(ActionEvent	event){
				 myController.iterate(model);
			 
				 for (int i = 0; i < width; i++) 
					 for (int j = 0; j < height; j++){
						 if (model.table[i][j] == 1) table[i][j].setBackground(Color.BLACK);
						 else table[i][j].setBackground(Color.WHITE);
					
					 }
			 }
	 }

		 
		 
		 
		 
		 
		 
		 final Timer timer = new Timer(100, new TimerActionListener());
		 model.revitalize(0, 1, 1);
		 model.revitalize(1, 2, 1);
		 model.revitalize(2, 0, 1);
		 model.revitalize(2, 1, 1);
		 model.revitalize(2, 2, 1);
		 
		 
		 
		 
		 
		 JPanel paneldown = new JPanel();
		 frame.add(paneldown, BorderLayout.SOUTH);
		 final JButton start = new JButton("Stop");
		 class startActionListener implements ActionListener{
			private Timer timer1 = null;
			 @Override
			public void actionPerformed(ActionEvent event) {
				if (start.getText() == "Stop"){
					start.setText("Start");
					timer.stop();
				}
				else {
					start.setText("Stop");
					timer.start();
				}
			}
			
		}
		start.addActionListener(new startActionListener());
		paneldown.add(start); 
		 
		 
		 class tableMousListener implements MouseListener{
			 private int x;
			 private int y;
			 tableMousListener(int x1,int y1){
				 x = x1;
				 y = y1;
			 }
			@Override
			public void mouseClicked(MouseEvent arg0) {
				table[x][y].setBackground(Color.BLACK);
				model.revitalize(x, y, 1);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			 
			 
		 }
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++){
					table[i][j] = new JPanel();
				}
				setka[i] = new JPanel ();
				setka[i].setBackground(Color.BLACK);
		 }
		 
		 for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++){
					table[i][j].addMouseListener(new tableMousListener(i, j));		 
				}
		 }
		 for (int j = 0; j < height; j++){
			 setka2[j] = new JPanel ();
			 setka2[j].setBackground(Color.BLACK);
		 }
		 for (int i = 0; i < width; i++) 
				for (int j = 0; j < height; j++){
					panelup.add(table[i][j], new GridBagConstraints(i*100, j*100, 99, 99, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets (0, 0, 0, 0), 20, 20));
				}
		 for (int i = 0; i < width; i++){
			 panelup.add(setka[i], new GridBagConstraints(i*100 + 99, 0, 99, height*100, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets (0, 0, 0, 0), -7, -7));
		 }
		 for (int j = 0; j < height; j++){
			 panelup.add(setka2[j], new GridBagConstraints(0, j*100 + 99, width*100, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets (0, 0, 0, 0), -7, -7));
		 }
		frame.add(panelup, BorderLayout.NORTH);
		 timer.start();
		 frame.pack();
	}
	
	
	
}
