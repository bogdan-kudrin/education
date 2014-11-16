import java.awt.*;
import java.awt.event.*;


import javax.swing.*;
 
public class Form extends JFrame {

	JFrame frame;
	JButton button;
	Timer timer;
	
	int size=30;
	public void OpenForm(){
		frame=new JFrame();
		button=new JButton("!");
		MyDrawPanel DrawPanel=new MyDrawPanel();
		DrawPanel.drw=new int[size][size];
		DrawPanel.num=size;
		LifeGame doIt=new LifeGame();

		LifeField mainField=doIt.fieldWithGlider(size,0,0);
		DrawPanel.drw=mainField.field;
		frame.getContentPane().add(BorderLayout.SOUTH, button);
		frame.getContentPane().add(BorderLayout.CENTER, DrawPanel);

		timer=new Timer(100,new ActionListener(){
				public void actionPerformed(ActionEvent e){
					mainField.next();
					DrawPanel.drw=mainField.field;
					frame.repaint();
				}
		});
		timer.start();
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
//				mainField.next();
//				DrawPanel.drw=mainField.field;
//				frame.repaint();
				button.setText("!");
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		frame.setSize(500,500);
		frame.setVisible(true);
		
	}

}