import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class Line extends Line2D{
	private Point point1;
	private Point point2;
	public Line (){
		point1 = new Point();
		point2 = new Point();
	}
	public Line (double x1, double y1, double x2, double y2) {
		point1 = new Point(x1, y1);
		point2 = new Point(x2, y2);
	}
	@Override
	public Rectangle2D getBounds2D() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point2D getP1() {
		return point1;
	}

	@Override
	public Point2D getP2() {
		return point2;
	}

	@Override
	public double getX1() {
		return point1.getX();
	}

	@Override
	public double getX2() {
		return point2.getX();
	}

	@Override
	public double getY1() {
		return point1.getY();
	}

	@Override
	public double getY2() {
		return point2.getY();
	}

	@Override
	public void setLine(double x1, double y1, double x2, double y2) {
		point1.setLocation(x1, y1);
		point2.setLocation(x2, y2);
	}

}
