import javax.media.j3d.Transform3D;
import javax.vecmath.*;

/**
 * User: BKudrin
 * Date: 22.11.12
 * Time: 17:05
 */
public class Coil {

    double externalRadius = 0.058;
    double wireThickness = 0.0016;
    double innerRadius = 0.018;
    double height = 0.034;
    double horizontalLoops = 25;
    double verticalLoops = 20;
    double mu = 0.0000004;
    double electricCurrent = 3;
    double c = mu*electricCurrent/(2*Math.PI);
    final static Vector3d crossVector = new Vector3d(Math.random(),Math.random(),Math.random());
    Vector3d xAxis = new Vector3d(1,0,0);
    Vector3d yAxis = new Vector3d(0,1,0);
    Vector3d zAxis = new Vector3d(0,0,1);
    Point3d coilZero = new Point3d(0,0,0);
    Matrix3d toGlobalMatrix = new Matrix3d();
    Matrix3d toLocalMatrix = new Matrix3d();

    public Coil(){
        toGlobalMatrix.setColumn(0, this.xAxis);
        toGlobalMatrix.setColumn(1, this.yAxis);
        toGlobalMatrix.setColumn(2, this.zAxis);
        toLocalMatrix.invert(toGlobalMatrix);
    }

    public Coil(Point3d coilZero, Vector3d zAxis, double externalRadius, double height, double electricCurrent, double verticalLoops, double horizontalLoops){
        this.coilZero = coilZero;
        this.zAxis = zAxis;
        this.zAxis.normalize();
        this.xAxis.cross(this.zAxis, crossVector);
        this.xAxis.normalize();
        this.yAxis.cross(this.zAxis,xAxis);
        this.yAxis.normalize();
        toGlobalMatrix.setColumn(0, this.xAxis);
        toGlobalMatrix.setColumn(1, this.yAxis);
        toGlobalMatrix.setColumn(2, this.zAxis);
        toLocalMatrix.invert(toGlobalMatrix);

        this.externalRadius = externalRadius;
        this.height = height;
        this.electricCurrent = electricCurrent;
        this.verticalLoops = verticalLoops;
        this.horizontalLoops = horizontalLoops;
        this.wireThickness = height /verticalLoops;
        this.innerRadius = externalRadius - horizontalLoops*wireThickness;

    }

    public Point3d toLocal (Point3d point){
        Point3d result = new Point3d();
        Transform3D transform = new Transform3D();
        transform.set(toLocalMatrix);
        point.sub(coilZero);
        transform.transform(point, result);
        return result;
    }

    public Point2d toPolar (Point3d point){
        Point2d result = new Point2d();
        result.setX(Math.sqrt(point.getX()*point.getX()+point.getY()*point.getY()));
        result.setY(point.getZ());
        return result;
    }

    public Vector3d fromPolar (Vector2d vector, Point3d point){
        Vector3d result = new Vector3d();
        double roOfPoint = Math.sqrt(point.getX()*point.getX()+point.getY()*point.getY());
        double scale = vector.getX()/roOfPoint;
        result.setX(point.getX()*scale);
        result.setY(point.getY()*scale);
        result.setZ(vector.getY());
        return result;
    }

    public Vector3d toGlobal (Vector3d vector){
        Vector3d result = new Vector3d();
        Transform3D transform = new Transform3D();
        transform.set(toGlobalMatrix);
        transform.transform(vector, result);
        return result;
    }

    public Vector2d countCoilB(Point2d point){
        Vector2d result = new Vector2d();
        for (double h = 0; h<= height; h+=wireThickness){
            for (double R = innerRadius; R<= externalRadius; R+=wireThickness){
                result.add(countLoopB(new Point2d(point.getX(), point.getY()-h), R));
            }
        }
        return result;
    }

    public Vector2d countLoopB(Point2d point, double R){
        double ro = point.getX();
        double z = point.getY();
        double RSquare = R*R;
        double roSquare = ro*ro;
        double twoRro = 2*R*ro;
        double roPlusRSquare = RSquare + twoRro + roSquare;
        double rMinusRoSquare = RSquare - twoRro + roSquare;
        double zSquare = z*z;
        double kSquare = 4*ro*R/(roPlusRSquare + zSquare);
        double sqrt = Math.sqrt(roPlusRSquare+zSquare);
        double Bro = (I.eE(kSquare)*(RSquare + roSquare + zSquare)/(rMinusRoSquare+zSquare)-I.eK(kSquare))
                *c*z/(ro*sqrt);
        double Bz = (I.eE(kSquare)*(RSquare - roSquare - zSquare)/(rMinusRoSquare+zSquare)+I.eK(kSquare))
                *c/sqrt;
        Vector2d result = new Vector2d(Bro,Bz);
        return result;
    }
}
