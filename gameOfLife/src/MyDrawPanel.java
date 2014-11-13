package gamelife;

import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

public class MyDrawPanel extends JPanel { 

boolean[][] drw;
int sizeX,sizeY,num;

public void paintComponent(Graphics g) {
g.setColor(Color.BLACK);
setBackground(Color.WHITE);
int x=0;
int y=0;

for(x=0;x<num;x++){
for(y=0;y<num;y++){
if(drw[x][y])
g.fillRect(x*sizeX, y*sizeY, sizeX, sizeY);
}
}
} 
}
