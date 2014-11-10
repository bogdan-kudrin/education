package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Паша on 10.11.2014.
 */

class Draw  extends JFrame {
    BigPanel bigpanel=new BigPanel();
    LittlePanel littlepanel=new LittlePanel(bigpanel.w);
    Draw(String s) {
        super(s);
        setSize(bigpanel.w, bigpanel.h+50);
        setLayout(new BorderLayout());
        this.add(bigpanel,BorderLayout.CENTER);
        this.add(littlepanel,BorderLayout.SOUTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public  void globalRepaint(){repaint();}
    class BigPanel extends JPanel implements MouseListener
    { Model m = new Model();
        boolean pause=true;
        int w, h;
        BigPanel(){
            super();
            w=m.w*m.r+100;
            h=m.h*m.r+100;
            setSize(w,h);
            addMouseListener(this);
        }
        public void paint(Graphics g) {

            g.setColor(Color.black);
            for (int i = 0; i < m.w+1; i++) {
                g.drawLine(m.r * i + 50, 50, m.r * i + 50, m.r*m.h+50);
            }
            for (int i = 0; i < m.h+1; i++) {
                g.drawLine(50, m.r * i + 50, m.r*m.w+50, m.r * i + 50);
            }


            for (int i = 0; i < m.h; i++)
            {
                for (int j = 0; j < m.w; j++) {
                    if (m.model[i][j]) {
                        g.setColor(Color.green);
                    }
                    else{ g.setColor(Color.white);}
                    g.fillRect(m.r * j + 51, m.r * i + 51, m.r-2, m.r-2);

                }
            }
        }
        public void mousePressed(MouseEvent evt){
            if(pause){
                int x=evt.getX();
                int y=evt.getY();
                if(x>50 & x<w-50 & y>50 & y<h-50){
                    int j=(x-50)/m.r;
                    int i=(y-50)/m.r;
                    m.model[i][j]=(!m.model[i][j]);
                    globalRepaint();
                }
            }
        }
        public void mouseEntered(MouseEvent evt) { }
        public void mouseExited(MouseEvent evt) { }
        public void mouseClicked(MouseEvent evt) { }
        public void mouseReleased(MouseEvent evt) { }
    }
    class LittlePanel extends JPanel{
        JButton start=new JButton("Старт");
        JButton stop=new JButton("Пауза");
        LittlePanel(int w){
            super();
            setSize(w,50);
            add(start);
            add(stop);
        }
    }
}
