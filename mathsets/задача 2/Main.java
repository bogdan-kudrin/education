package com.company;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Main {

    public static void main(String[] args) {
       SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Draw("Ы");
            }
        });
        // write your code here
    }
}
class Point{
    double x,y;
    int ax=0, bx=1, ay=0, by=1;
    Point(double i,double j){
        x=i*(bx-ax)/1000+ax;
        y=j*(by-ay)/1000+ay;
    }
    public void do_iterate(){
        double a=
                2
                //2.21
               // 2.27
                ;
        double x1=y;
        double y1=a*y*(1-x);
        x=x1;
        y=y1;
    }
}
class Controller{
    public boolean check(Point p){
        for(int k=0; k<50; k++){
            p.do_iterate();
        }
        return (p.x>=p.ax & p.x<=p.bx & p.y>=p.ay & p.y<=p.by);
    }
}
class Model{
    Controller c= new Controller();
    boolean[][] picture = new boolean[1000][1000];
    Model(){
        for(int i=0;i<1000; i++){
            for(int j=0;j<1000; j++){
                Point p = new Point(i,j);
                picture[i][j]=c.check(p);
            }
        }
    }
}
class Draw extends JFrame{
    Model m = new Model();
    Draw(String s){
        super(s);
        setSize(1100,1100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void paint(Graphics g){

        for(int i=0;i<1000; i++){
            for(int j=0;j<1000; j++){
                if(m.picture[i][j]) {g.setColor(Color.black);}
                else { g.setColor(Color.green);}
                g.drawLine(i+50,j+50,i+50,j+50);
            }
        }
    }
}