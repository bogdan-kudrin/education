import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
 
public class Form extends JFrame {

	JFrame frame;
	JButton button;
	Timer timer;
	int iterations=0;
	int maxiterations=300;
	boolean[][] used;
	
	
	
	int size=800;

	public void OpenForm(){
		frame=new JFrame();
		button=new JButton("!");
		MyDrawPanel DrawPanel=new MyDrawPanel();
		DrawPanel.drw=new int[size][size];
		DrawPanel.num=size;


		frame.getContentPane().add(BorderLayout.SOUTH, button);
		frame.getContentPane().add(BorderLayout.CENTER, DrawPanel);



		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
//				mainField.next();
//				DrawPanel.drw=mainField.field;
//				frame.repaint();
				button.setText("!!");
			}
		});
		PointWithMath[][] points=new PointWithMath[size+10][size+10];
		used=new boolean[size+10][size+10];
		for(int i=-size/2;i<size/2;i++)
			for(int j=-size/2;j<size/2;j++){
				points[i+size/2][j+size/2]=new PointWithMath(4.0*i/size,4.0*j/size);
			}
			
		
		timer=new Timer(100,new ActionListener(){

			@Override
			//I don't know what does this thing do
			
			public void actionPerformed(ActionEvent e) {
				iterations++;
				if(iterations>maxiterations) timer.stop();
				for(int i=-size/2;i<size/2;i++)
					for(int j=-size/2;j<size/2;j++)
						if(!used[i+size/2][j+size/2]){
							int ishifted=i+size/2;
							int jshifted=j+size/2;
							points[ishifted][jshifted].f_1();
							if(points[ishifted][jshifted].absSquare()>100){
								DrawPanel.drw[ishifted][jshifted]=maxiterations-iterations;
								used[ishifted][jshifted]=true;
							}
							
						
					}
				frame.repaint();
				frame.setTitle(iterations+ " ");
			}
			
		});
		timer.start();
/*		int iterations=300;
		for(int i=-size/2;i<size/2;i++)
			for(int j=-size/2;j<size/2;j++){
				PointWithMath point=new PointWithMath(4.0*i/size,4.0*j/size);
				boolean draw=true;
				for(int k=0;k<iterations;k++){
					point.f_1();
					if(point.absSquare()>100) {
						DrawPanel.drw[i+size/2][j+size/2]=(iterations-k);
						k=iterations;
						draw=false;
						
					}
				}
				if(draw) DrawPanel.drw[i+size/2][j+size/2]=1;
			}*/
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		frame.setSize(800,800);
		frame.setVisible(true);
		
	}

}