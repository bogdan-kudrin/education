/**
 * Created by Паша on 10.11.2014.
 */
class Point{
    double x,y;
    int ax=-2, bx=2, ay=-2, by=2;
    Point(double i,double j,double width,double height){
        x=i*(bx-ax)/width+ax;
        y=j*(by-ay)/height+ay;
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