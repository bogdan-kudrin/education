package mathset;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Window extends JFrame{
	JFrame frame;
	private JButton Start;
	private JButton Stop;
	private JButton SetParametres;
	private JPanel Canvas;
	private JTextField aParam = new JTextField(5);
	private JTextField bParam = new JTextField(5);
	String s;
	Model field;
	Color[] shades = new Color[30];
	boolean flag;
	Timer timer;
	double a, b;
	int steps;
	
	public Window(){
		super("Henon map");
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Start = new JButton("Start");
		Stop = new JButton("Stop");
		SetParametres = new JButton("Set Parametres");
		JPanel ButtonsPanel = new JPanel(new FlowLayout());
		ButtonsPanel.add(Start);
		ButtonsPanel.add(Stop);
		ButtonsPanel.add(SetParametres);
		ButtonsPanel.add(aParam);
		ButtonsPanel.add(bParam);
		flag=false;
		for(int i=0; i<30; i++){
			shades[i]=new Color(8*i, 0, 240-8*i);
		}
		frame.add(Canvas = new JPanel() {
	    	
		      public void paintComponent(Graphics g) {
		      super.paintComponent(g);
		  	  setBackground(Color.WHITE);
		  	  int x=0;
		  	  int y=0;

		  	  for(x=0;x<1000;x++){
		  	  for(y=0;y<1000;y++){
		  	  if(flag){
		  	  g.setColor(shades[field.matrix[x][y][0]]);
		  	  g.fillRect(x, y, 1, 1);
		  	  }
		  	  }
		  	  }
		  	  } });
		frame.getContentPane().add(ButtonsPanel, BorderLayout.PAGE_START);   
	    frame.getContentPane().add(Canvas, BorderLayout.CENTER);
	    initListeners();
	    timer=new Timer(200, new ActionListener(){
	    	@Override
			public void actionPerformed(ActionEvent e){
	    		if(steps<29){
				field.iterate();
				frame.repaint();
				steps++;
	    		}
			}
	    });
	}
	public void ReadParametres(){
		s=aParam.getText();
		a=Double.parseDouble(s);
		s=bParam.getText();
		b=Double.parseDouble(s);
	}
	public void begin(){
		field = new Model(a, b);
		frame.repaint();
		flag=true;
		steps=0;
	}
	
	private void initListeners(){
		Start.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	  timer.start();
		       }
		     });
	     Stop.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		         timer.stop();
		       }
		     });
	     SetParametres.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {	    	   
		    	   ReadParametres();
		    	   begin();
		       }
		     });
	}
}
