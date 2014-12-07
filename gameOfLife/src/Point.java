/**
 * Created by Света on 07.12.2014.
 */


class Point
{
    double x, y;
    int a=-2, b=2, c=-2, d=2;

    Point(int i,int j){
        x=(double)i*(b-a)/500+a;
        y=(double)j*(d-c)/500+c;
    }

    public void Step ()
    {
        double k=1.4;
        double t=0.3;
        double p=x;
        double q=y;
        x=1+q-k*p*p;
        y=t*p;
    }

    public boolean Run ()
    {
        for (int s=0; s<50; s++) {
            Step();
        }

        return (a<x&b>x&c<y&d>y);
    }


}
