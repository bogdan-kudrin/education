import javax.vecmath.Point2d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector2d;
import javax.vecmath.Vector3d;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class PointCounter {

    public Point3d globalPoint = new Point3d();
    public Vector3d pointDistribution = new Vector3d();
    BaseCounter baseCounter;

    public PointCounter(BaseCounter baseCounter) {
        this.baseCounter = baseCounter;
    }

    public void countCoilDistributionInPoint(Coil coil) {
        Point3d localPoint = coil.toLocal(globalPoint);
        Point2d polarPoint = coil.toPolar(localPoint);
        Vector2d polarField = coil.countCoilB(polarPoint);
        Vector3d localField = coil.fromPolar(polarField, localPoint);
        pointDistribution.add(coil.toGlobal(localField));
    }

    public void countCoilDistributionInPoint() {
        pointDistribution = new Vector3d();
        for (int i = 0; i < baseCounter.coils.length; i++) {
            if (baseCounter.enabledCoils[i]) {
                countCoilDistributionInPoint(baseCounter.coils[i]);
            }
        }
    }
}
