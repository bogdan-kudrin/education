package gamelife;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
 
public class window extends JFrame {
	JFrame frame;
  private int step = 0;
  private JLabel countLabel;
  private JButton iterate;
  private JButton reboot;
  private JButton start;
  private JButton stop;
  private JTextField Size = new JTextField(5);
  private JButton Ready;
  JPanel Canvas;
  private int k, sizeX, sizeY;
  private String s;
  int size;
  field board;
  Timer timer;
  boolean flag1, flag2;
 
  public window(){
	super("Life game");
	frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    countLabel = new JLabel("Steps:" + step);
    iterate = new JButton("iterate!");
    reboot = new JButton("reboot!");
    start = new JButton("start!");
    stop = new JButton("stop!");   
    Ready = new JButton("Ready!");
 
    JPanel buttonsPanel = new JPanel(new FlowLayout()); 
    buttonsPanel.add(iterate);
    buttonsPanel.add(reboot);
    buttonsPanel.add(start);
    buttonsPanel.add(stop);
    buttonsPanel.add(Size);
    buttonsPanel.add(Ready);
    frame.add(Canvas = new JPanel() {
    	
      public void paintComponent(Graphics g) {
      super.paintComponent(g);
  	  g.setColor(Color.BLACK);
  	  setBackground(Color.WHITE);
  	  int x=0;
  	  int y=0;

  	  for(x=0;x<size;x++){
  	  for(y=0;y<size;y++){
  	  if(board.matrix[x][y])
  	  g.fillRect(x*sizeX, y*sizeY, sizeX, sizeY);
  	  }
  	  }
  	  } });
    frame.getContentPane().add(buttonsPanel, BorderLayout.PAGE_END);   
    frame.getContentPane().add(countLabel, BorderLayout.PAGE_START);
    frame.getContentPane().add(Canvas, BorderLayout.CENTER);
    initListeners();
    timer=new Timer(200,new ActionListener(){
		public void actionPerformed(ActionEvent e){
			board.iterate();
			step = step + 1;
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
	    board = new field(size);
	    sizeX=900/size;
	    sizeY=900/size;
	    frame.repaint();
  }
  private void initListeners() {
	     iterate.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {	    	   
	         step = step + 1; 
	         board.iterate();
	         updateStepCounter();
	         frame.repaint();
	       }
	     });
	     reboot.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		         step = 0; 
		         begin();
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
	     Canvas.addMouseListener(new MouseAdapter() {

	 	    @Override
	 	    public void mousePressed(MouseEvent me) {
	 	        board.setalive(me.getX() / sizeX + 1, me.getY() / sizeY + 1, true);
	 	        frame.repaint();
	 	    }

	 	});
	     Canvas.addMouseMotionListener(new MouseMotionAdapter() {

		 	    @Override
		 	    public void mouseDragged(MouseEvent me) {
		 	        board.setalive(me.getX() / sizeX + 1, me.getY() / sizeY + 1, true);
		 	        frame.repaint();
		 	    }

		 	});
  }
  private void updateStepCounter() {
	     countLabel.setText("Steps:" + step);
  }
  
}
