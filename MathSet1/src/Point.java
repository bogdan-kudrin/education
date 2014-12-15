/**
 * Created by Пользователь on 15.12.2014.
 */
public class Point {

    double x, y,a=1.4, b=0.3;

    Point(double i, double j){

    x=(-2+i/50);
    y=(-2+j/50);
    }

    public void iterate(){
        double x1=1+y-a*x*x;
        y=b*x;
        x=x1;
    }

    public int getI(){
        double i=(x+2)*50;
        return ((int)i);
    }

    public int getJ(){
        double j=(y+2)*50;
        return ((int)j);
    }
}
