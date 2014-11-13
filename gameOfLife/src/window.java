package gamelife;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.*;

import javax.swing.*;
 
public class window extends JFrame {
	JFrame frame;
  private int step = 0;
  private int lifes = 0;
  private int n = 0;
  private int w, h;
  private int longestlife = 0;
  private double averagelife = 0;
  private JLabel countLabel;
  private JButton iterate;
  private JButton reboot;
  private JButton start;
  private JButton stop;
  private JTextField Size = new JTextField(5);
  private JButton Ready;
  private int k;
  private String s;
  int size;
  field board;
  MyDrawPanel DrawPanel = new MyDrawPanel();
  Timer timer;
  boolean flag1, flag2;
  boolean[][] previous;
 
  public window(){
	super("Life game");
	//setSize(700, 800);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame = new JFrame();
    countLabel = new JLabel("Steps:" + step + "      Lifes:" + lifes + "      Longest Life:" + longestlife + "      Average Life:" + averagelife);
    iterate = new JButton("iterate!");
    reboot = new JButton("reboot!");
    start = new JButton("start!");
    stop = new JButton("stop!");   
    Ready = new JButton("Ready!");
 
    JPanel buttonsPanel = new JPanel(new FlowLayout()); 
    frame.getContentPane().add(BorderLayout.NORTH, countLabel);
    buttonsPanel.add(iterate);
    buttonsPanel.add(reboot);
    buttonsPanel.add(start);
    buttonsPanel.add(stop);
    buttonsPanel.add(Size);
    buttonsPanel.add(Ready);
    frame.getContentPane().add(BorderLayout.CENTER, DrawPanel);
    frame.getContentPane().add(BorderLayout.SOUTH, buttonsPanel);
    initListeners();
    timer=new Timer(200,new ActionListener(){
		public void actionPerformed(ActionEvent e){
			board.iterate();
			/**
			flag1 = false;
			flag2 = false;
			for(int i=0; i<size; i++){
				for(int j=0; j<size; j++){
				if(DrawPanel.drw[i][j]!=board.matrix[i][j]){			
				flag1=true;
				}
				}
			}
			for(int i=0; i<size; i++){
				for(int j=0; j<size; j++){
				if(previous[i][j]!=board.matrix[i][j]){			
				flag2=true;
				}
				}
			}
			if((!flag1)||(!flag2)){
				board = new field(size);
				lifes+=1;
				if(n>longestlife){
					longestlife=n;
				}
				averagelife=(double)step/lifes;
				n=0;
			}
			*/
			step = step + 1;
			n+=1;
			previous=DrawPanel.drw;
			DrawPanel.drw=board.matrix;
			updateStepCounter();
			frame.repaint();
		}
    });
  }
  public int ReadSize(){
		s = Size.getText();
		k = Integer.parseInt(s);
		return k;
	}
  public void begin(){
	  previous = new boolean[size][size];
	    for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				previous[i][j]=false;
			}
	    }
	    board = new field(size);
	    DrawPanel.drw=new boolean[size][size];
	    DrawPanel.num=size;
	    DrawPanel.drw=board.matrix;
	    DrawPanel.sizeX=900/size;
	    DrawPanel.sizeY=900/size;
	    frame.repaint();
  }
  private void initListeners() {
	     iterate.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	         step = step + 1; 
	         n+=1;
	         board.iterate();
	         DrawPanel.drw=board.matrix;
	         updateStepCounter();
	         frame.repaint();
	       }
	     });
	     reboot.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		         step = 0; 
		         lifes=0;
		         n=0;
		         longestlife=0;
		         board = new field(size);
		         DrawPanel.drw=board.matrix;
		         updateStepCounter();
		         frame.repaint();
		       }
		     });
	     start.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	  timer.start();
		       }
		     });
	     stop.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		         timer.stop();
		       }
		     });
	     Ready.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {	    	   
		    	   size = ReadSize();
		    	   begin();
		       }
		     });
	     addMouseListener(new MouseAdapter() {

	            @Override
	            public void mousePressed(MouseEvent me) {
	                w = getWidth() / size;
	                h = getHeight() / size;
	                board.setalive(me.getX() / w, me.getY() / h, true);
	                DrawPanel.drw=board.matrix;
	                frame.repaint();
	            }

	        });
  }
  private void updateStepCounter() {
	     countLabel.setText("Steps:" + step + "      Lifes:" + lifes + "      Longest Life:" + longestlife + "      Average Life:" + averagelife);
  }
}
