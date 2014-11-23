import java.math.*;
public class PointWithMath {
	double x,y;
	double a=0.28,b=-0.0113;
	public void f_1(){
		double newX,newY;
//		newX=1+y-a*x*x;
//		newY=b*x;
		newX=x*x-y*y+a;
		newY=2*x*y+b;
		x=newX;
		y=newY;
	}
	public void f_2(){
		double newX,newY;
		double a=1.2,b=0.4;
		newX=1+y-a*x*x;
		newY=b*x;
		x=newX;
		y=newY;
	}
	public void f_3(){
		double newX,newY;
		double a=1.01;
		newX=y;
		newY=a*y*(1-x);
		x=newX;
		y=newY;
	}
	public void f_4(){
		double newX,newY;
		double lambda=0.01,mu=10;
		newX=(1-lambda)*x+lambda*mu*y*(1-y);
		newY=(1-lambda)*y+lambda*mu*x*(1-x);
		x=newX;
		y=newY;
			
	}
	public PointWithMath(double x,double y){
		this.x=x;
		this.y=y;
	}
	public double absSquare(){
		return x*x+y*y;
	}

}
