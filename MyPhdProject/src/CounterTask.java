import javax.vecmath.Point2d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector2d;
import javax.vecmath.Vector3d;

/**
 * User: BKudrin
 * Date: 16.04.13
 * Time: 9:16
 */
public class CounterTask implements Runnable {
    Vector3d[][][] workersFieldDistr;
    Coil coil;
    BaseCounter baseCounter = new BaseCounter();

    CounterTask(Vector3d[][][] workersFieldDistr, Coil coil){
        this.workersFieldDistr = workersFieldDistr;
        this.coil = coil;
        initField();
    }

    public void initField(){
        for (int i=0; i< baseCounter.areaSizeX; i+=1){
            for (int j=0; j< baseCounter.areaSizeY; j+=1){
                for (int k=0; k< baseCounter.areaSizeZ; k+=1){
                    workersFieldDistr[i][j][k] = new Vector3d();
                }
            }
        }
    }

    public void countCoilFieldDistr(){
        for (int i=0; i< baseCounter.areaSizeX; i+=1){
            for (int j=0; j< baseCounter.areaSizeY; j+=1){
                for (int k=0; k< baseCounter.areaSizeZ; k+=1){
                    Point3d globalPoint = new Point3d(i/ baseCounter.scalefactorX,j/ baseCounter.scalefactorY,k/ baseCounter.scalefactorZ);
                    Point3d localPoint = coil.toLocal(globalPoint);
                    Point2d polarPoint = coil.toPolar(localPoint);
                    Vector2d polarField = coil.countCoilB(polarPoint);
                    Vector3d localField = coil.fromPolar(polarField, localPoint);
                    Vector3d globalField = coil.toGlobal(localField);
                    if (globalField.length()<10)
                    {
                            workersFieldDistr[i][j][k].add(globalField);
                    }
                }
            }
        }
    }


    @Override
    public void run() {
        countCoilFieldDistr();
    }
}
