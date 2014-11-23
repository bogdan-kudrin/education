import javax.swing.*;        

import java.awt.*;
//import java.awt.event.*;


public class MyDrawPanel extends JPanel {    

int[][] drw;
int sizeX=1,sizeY=1,num;
public void paintComponent(Graphics g) {
  g.setColor(Color.PINK);
  Color cl;

  setBackground(Color.YELLOW);
  int x=0;
  int y=0;
  int n=0;
  for(x=0;x<num;x++){
	  for(y=0;y<num;y++){
		  
			  
			  n=drw[x][y]+1;
//			  cl=new Color(0,0,0);
			  cl=new Color((100*n-100)%256,(n*n+2439*n-2439)%256,(934*n-934)%256);
//			  cl=new Color(20*n%256,30*n%256,10*n%256);
			  g.setColor(cl);
			  g.fillRect(x*sizeX, y*sizeY, sizeX, sizeY);
		  
	  }
  }
}   
}