import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
 
public class Form extends JFrame {

	JFrame frame;
	JButton button, okbutton;
	JSpinner spinner;
	Timer timer;
	Panel panel;
	
	int size=30;

	public void OpenForm(){
		
		frame=new JFrame();
		button=new JButton("Запустить таймер");
		okbutton=new JButton("Изменить размеры поля");
		spinner=new JSpinner();
		spinner.setValue(30);
		panel=new Panel();
		MyDrawPanel DrawPanel=new MyDrawPanel();
		DrawPanel.drw=new int[size][size];
		DrawPanel.num=size;
//		LifeGame doIt=new LifeGame();

		LifeField mainField=new LifeField(size);
		DrawPanel.drw=mainField.field;
		panel.add(BorderLayout.WEST, button);
		panel.add(BorderLayout.SOUTH,spinner);
		panel.add(BorderLayout.EAST,okbutton);
		frame.getContentPane().add(BorderLayout.SOUTH,panel);

		frame.getContentPane().add(BorderLayout.CENTER, DrawPanel);
		timer=new Timer(100,new ActionListener(){
				public void actionPerformed(ActionEvent e){
					mainField.next();
					DrawPanel.drw=mainField.field;
					frame.repaint();
				}
		});
//		timer.start();
		okbutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if((Integer)spinner.getValue()>5){
					size=(Integer)spinner.getValue();
					DrawPanel.sizeX=frame.getWidth()/size;
					DrawPanel.sizeY=(frame.getHeight()-button.getHeight()-23)/size;
					DrawPanel.drw=new int[size][size];
					DrawPanel.num=size;
					mainField.field=new int[size][size];
					mainField.size=size;
					frame.repaint();
				}
			}
		});
		button.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
//				mainField.next();
//				DrawPanel.drw=mainField.field;
//				frame.repaint();

				if(timer.isRunning()) {
					timer.stop();
					frame.setTitle("Timer is stopped");
					button.setText("Запустить таймер");

				}
					
				
				else {
					timer.start();
					frame.setTitle("Timer is running");
					button.setText("Остановить таймер");
				}
				
			}
		});

		frame.addMouseListener(new MouseListener(){
			//BEGIN DO NOTHING
			public void mouseEntered(MouseEvent e){
				
			}
			public void mouseExited(MouseEvent e){
				
			}
			public void mouseReleased(MouseEvent e){
				
			}
			public void mousePressed(MouseEvent e){
				DrawPanel.drw[(e.getX()-4)/DrawPanel.sizeX][(e.getY()-23)/DrawPanel.sizeY]
				=1-DrawPanel.drw[(e.getX()-4)/DrawPanel.sizeX][(e.getY()-23)/DrawPanel.sizeY];
				frame.repaint();
				//frame.setTitle(e.getX()+" "+e.getY());
			}
			//END DO NOTHING
			public void mouseClicked(MouseEvent e){
				

			}
		});
		frame.addComponentListener(new ComponentListener(){

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				DrawPanel.sizeX=frame.getWidth()/size;
				DrawPanel.sizeY=(frame.getHeight()-button.getHeight()-23)/size;
				if(frame.getWidth()*1.0/frame.getHeight()>1.5) frame.setTitle("Клетки начинают выглядеть смешно");
				if(frame.getWidth()*1.0/frame.getHeight()>2) frame.setTitle("Да, я растягиваю квадратное поле на всю форму");
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		frame.setSize(500,500);
		DrawPanel.sizeX=frame.getWidth()/size;
		DrawPanel.sizeY=(frame.getHeight()-button.getHeight()-23)/size;
		frame.setVisible(true);
		
	}

}