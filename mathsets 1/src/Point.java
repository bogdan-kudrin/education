/**
 * Created by Паша on 15.11.2014.
 */
class Point{
    double x,y;

    //это для первой, третеё и четвёртой задачи
    int minX=-2, maxX=2, minY=-2, maxY=2;

    //это для второй
    //int minX=0, maxX=1, minY=0, maxY=1;


    Point(double i,double j,double width,double height){
        x=i*(maxX-minX)/width+minX;
        y=j*(maxY-minY)/height+minY;
    }

    public double getX()
    {return(x-minX);}

    public double getY()
    {return(y-minY);}

    public double getDistanceX()
    {return (maxX-minX);}

    public double getDistanceY()
    {return(maxY-minY);

    }
    // это 1-е отображение

    public void doIterate(){
        double a=1.4, b=0.3;
        double x1=1+y-a*x*x;
        double y1=b*x;
        x=x1;
        y=y1;
    }

    //это 2-е

    //public void doIterate(){
    //    double a=
    //            2
    //            //2.21
    //            // 2.27
    //            ;
    //    double x1=y;
    //    double y1=a*y*(1-x);
    //    x=x1;
    //    y=y1;
    //}


//это 3-е

    // public void doIterate(){
    //    double c1=0, c2=0 ;
    //     double x1=x*x-y*y+c1*c1-c2*c2;
    //     double y1=2*x*y+2*c1*c2;
    //     x=x1;
    //     y=y1;
    // }

    //это 4-е

    // public void doIterate(){
    //     double lambda=0.2, miu=4.11;
    //     double x1=(1-lambda)*x+lambda*miu*y*(1-y);
    //     double y1=(1-lambda)*y+lambda*miu*x*(1-x);
    //     x=x1;
    //     y=y1;
    // }
}