package com.company;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;



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

class Model{
    int w=10, h=10, r=50;
    boolean model[][] = new boolean[h][w];
    Model(){
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++)
                model[i][j]=false;
        }

    }
    public void do_iteration(){
        boolean copy[][] = new boolean[h][w];
        int i;
        int j;
        int a; //i-1
        int b; //i+1
        int c; //j-1
        int d; //j+1
        int n;
        for(i=0;i<h;i++)
        {
            for(j=0;j<w;j++)
            {
                a=(i==0)? h-1 : i-1;
                b=(i==h-1)? 0 : i+1;
                c=(j==0)? w-1 : j-1;
                d=(j==w-1)? 0 : j+1;

                n=0;
                if(model[a][c]) n++;
                if(model[a][j]) n++;
                if(model[a][d]) n++;
                if(model[i][c]) n++;
                if(model[i][d]) n++;
                if(model[b][c]) n++;
                if(model[b][j]) n++;
                if(model[b][d]) n++;

                if(!model[i][j])
                {

                    copy[i][j]=(n==3);
                }
                else
                {
                    copy[i][j]=(n==3 || n==2);
                }
            }
        }
        model = copy;
    }
}

public class Main {
    public static void main(String[] args) {
        new Controller();
        // write your code here
    }
}

class Controller{
    Timer timer;
    Draw d = new Draw("Жизнь");
    ActionListener  timerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            d.bigpanel.m.do_iteration();
            d.repaint();
        }

    };
    ActionListener stopListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            d.littlepanel.start.setEnabled(true);
            d.bigpanel.pause=true;
        }
    };
    ActionListener startListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.restart();
            d.littlepanel.start.setEnabled(false);
            d.bigpanel.pause=false;
        }
    };
    Controller(){
        timer= new Timer(1000, timerListener);
        d.littlepanel.stop.addActionListener(stopListener);
        d.littlepanel.start.addActionListener(startListener);
        timer.start();
        timer.stop();
    }
}
