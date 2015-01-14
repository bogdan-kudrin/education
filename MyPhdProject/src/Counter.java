import javax.vecmath.Point2d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector2d;
import javax.vecmath.Vector3d;
import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class Counter{
    static String pathToOutputFile;
    static int areaSizeX = 10;
    static int areaSizeY = 10;
    static int areaSizeZ = 10;
    static int stepX = 1;
    static int stepY = 1;
    static int stepZ = 1;
    static  double scalefactorX = 1000.0/stepX;
    static  double scalefactorY = 1000.0/stepY;
    static  double scalefactorZ = 1000.0/stepZ;

    static Vector3d[][][] fieldDistr;
    static Vector3d[][][] interpolatedFieldDistr;
    static Vector3d[][][] quadricInterpolatedFieldDistr;

    public static Coil firstCoil = new Coil();
    public static Coil secondCoil = new Coil();
    public static Coil thirdCoil = new Coil();
    public static Coil fourthCoil = new Coil();
    public static Coil fifthCoil = new Coil();
    public static Coil sixthCoil = new Coil();
    public static boolean[] enabledCoils = {false, false, false, false, false, false};
    public static Coil[] coils = {fifthCoil, secondCoil, thirdCoil, fourthCoil, fifthCoil, sixthCoil};

    public static Point3d globalPoint = new Point3d();
    public static Vector3d pointDistribution = new Vector3d();

    static double max = 0;
    static double interpolatedMax = 0;
    static double quadricInterpolatedMax = 0;

     static public void initField(){
        fieldDistr = new Vector3d[areaSizeX][areaSizeY][areaSizeZ];
        for (int i=0; i<areaSizeX; i+=1){
            for (int j=0; j<areaSizeY; j+=1){
                for (int k=0; k<areaSizeZ; k+=1){
                    fieldDistr[i][j][k] = new Vector3d();
                }
            }
        }
    }

    static public void initInterpolatedField(){
        interpolatedFieldDistr = new Vector3d[areaSizeX][areaSizeY][areaSizeZ];
        for (int i=0; i<areaSizeX; i+=1){
            for (int j=0; j<areaSizeY; j+=1){
                for (int k=0; k<areaSizeZ; k+=1){
                    interpolatedFieldDistr[i][j][k] = new Vector3d();
                }
            }
        }
    }

    static public void initQuaricInterpolatedField(){
        quadricInterpolatedFieldDistr = new Vector3d[areaSizeX][areaSizeY][areaSizeZ];
        for (int i=0; i<areaSizeX; i+=1){
            for (int j=0; j<areaSizeY; j+=1){
                for (int k=0; k<areaSizeZ; k+=1){
                    quadricInterpolatedFieldDistr[i][j][k] = new Vector3d();
                }
            }
        }
    }

    static public void countFieldDistribution(){
        initField();
        for (int i=0; i<coils.length; i++){
            if (enabledCoils[i]){
                countCoilFieldDistr(coils[i]);
            }
        }
    }

    public void parallelCountFieldDistribution(){
        Runnable task1 = new CounterTask(fieldDistr, firstCoil);
        Thread worker1 = new Thread(task1);
        if (enabledCoils[0]){
            worker1.start();
        }
        Runnable task2 = new CounterTask(fieldDistr, secondCoil);
        Thread worker2 = new Thread(task2);
        if (enabledCoils[1]){
            worker2.start();
        }

        while (worker1.isAlive() || worker2.isAlive()){

        }
    }

    public static void countCoilDistributionInPoint(Coil coil){
        Point3d localPoint = coil.toLocal(globalPoint);
        Point2d polarPoint = coil.toPolar(localPoint);
        Vector2d polarField = coil.countCoilB(polarPoint);
        Vector3d localField = coil.fromPolar(polarField, localPoint);
        pointDistribution.add(coil.toGlobal(localField));
    }

    public static void countCoilDistributionInPoint(){
        pointDistribution = new Vector3d();
        for (int i=0; i<coils.length; i++){
            if (enabledCoils[i]){
                countCoilDistributionInPoint(coils[i]);
            }
        }
    }

    static public void countCoilFieldDistr(Coil coil){
        for (int i=0; i<areaSizeX; i+=1){
            for (int j=0; j<areaSizeY; j+=1){
                for (int k=0; k<areaSizeZ; k+=1){
                    Point3d globalPoint = new Point3d(i/scalefactorX, j/scalefactorY, k/scalefactorZ);
                    Point3d localPoint = coil.toLocal(globalPoint);
                    Point2d polarPoint = coil.toPolar(localPoint);
                    Vector2d polarField = coil.countCoilB(polarPoint);
                    Vector3d localField = coil.fromPolar(polarField, localPoint);
                    Vector3d globalField = coil.toGlobal(localField);
                    if (globalField.length()<10)
                    {
                            fieldDistr[i][j][k].add(globalField);
                    }
                }
            }
        }
    }

    static public void interpolateFieldDistrLinear(){
        initInterpolatedField();
        Interpolator interpolator = new Interpolator();
        for (int i=0; i<areaSizeX - 1; i+=1){
            for (int j=0; j<areaSizeY - 1; j+=1){
                for (int k=0; k<areaSizeZ - 1; k+=1){
                    Point3d interpolationPoint = new Point3d((i + 0.5)/scalefactorX, (j + 0.5)/scalefactorY, (k + 0.5)/scalefactorZ);
                    Point3d[][][] interpolationNodes = new Point3d[2][2][2];
                    double[][][] interpolationValuesX = new double[2][2][2];
                    double[][][] interpolationValuesY = new double[2][2][2];
                    double[][][] interpolationValuesZ = new double[2][2][2];
                    for (int l=0; l<2; l+=1){
                        for (int m=0; m<2; m+=1){
                            for (int n=0; n<2; n+=1){
                                interpolationNodes[l][m][n] = new Point3d(getIndex(i,l)/scalefactorX, getIndex(j,m)/scalefactorY, getIndex(k,n)/scalefactorZ);
                                interpolationValuesX[l][m][n] = fieldDistr[getIndex(i,l)][getIndex(j,m)][getIndex(k,n)].getX();
                                interpolationValuesY[l][m][n] = fieldDistr[getIndex(i,l)][getIndex(j,m)][getIndex(k,n)].getY();
                                interpolationValuesZ[l][m][n] = fieldDistr[getIndex(i,l)][getIndex(j,m)][getIndex(k,n)].getZ();
                            }
                        }

                    }

                    interpolatedFieldDistr[i][j][k].setX(interpolator.interpolateLinear(interpolationNodes, interpolationValuesX, interpolationPoint));
                    interpolatedFieldDistr[i][j][k].setY(interpolator.interpolateLinear(interpolationNodes, interpolationValuesY, interpolationPoint));
                    interpolatedFieldDistr[i][j][k].setZ(interpolator.interpolateLinear(interpolationNodes, interpolationValuesZ, interpolationPoint));
                }
            }

        }

    }

    static public void interpolateFieldDistrQuadric(){
        initQuaricInterpolatedField();
        Interpolator interpolator = new Interpolator();
        double interpolationPoint = new Vector3d(0.5/scalefactorX, 0.5/scalefactorY, 0.5/scalefactorZ).length();
        double [] interpolationNodes = new double[3];
        double[] interpolationValuesX = new double[3];
        double[] interpolationValuesY = new double[3];
        double[] interpolationValuesZ = new double[3];
        interpolationNodes[0] = 0;
        interpolationNodes[1] = new Vector3d(1/scalefactorX, 1/scalefactorY, 1/scalefactorZ).length();
        interpolationNodes[2] = new Vector3d(2/scalefactorX, 2/scalefactorY, 2/scalefactorZ).length();
        for (int i=0; i<areaSizeX - 2; i+=1){
            for (int j=0; j<areaSizeY - 2; j+=1){
                for (int k=0; k<areaSizeZ - 2; k+=1){
                    for (int n=0; n<3; n+=1){
                        interpolationValuesX[n] = fieldDistr[i+n][j+n][k+n].getX();
                        interpolationValuesY[n] = fieldDistr[i+n][j+n][k+n].getY();
                        interpolationValuesZ[n] = fieldDistr[i+n][j+n][k+n].getZ();
                    }
                    quadricInterpolatedFieldDistr[i][j][k].setX(interpolator.interpolateQuadric(interpolationNodes, interpolationValuesX, interpolationPoint));
                    quadricInterpolatedFieldDistr[i][j][k].setY(interpolator.interpolateQuadric(interpolationNodes, interpolationValuesY, interpolationPoint));
                    quadricInterpolatedFieldDistr[i][j][k].setZ(interpolator.interpolateQuadric(interpolationNodes, interpolationValuesZ, interpolationPoint));
                }
            }

        }

    }

    public static void countAndWriteFieldDistribution(){
        Date startDate = new Date();
        try {
            File file = new File(pathToOutputFile);
            file.getParentFile().mkdirs();
            BufferedWriter vtkFieldWriter = new BufferedWriter(new FileWriter(file));

            countFieldDistribution();
            vtkFieldWriter.write("# vtk DataFile Version 2.0\n" +
                    "Cube example\n" +
                    "ASCII\n" +
                    "DATASET STRUCTURED_POINTS\n" +
                    "DIMENSIONS " + Counter.areaSizeX + " " + Counter.areaSizeY + " " + Counter.areaSizeZ + "\n" +
                    "ORIGIN 0 0 0\n" +
                    "SPACING " + Counter.stepX + " " + Counter.stepY + " " + Counter.stepZ + "\n" +
                    "POINT_DATA " + Counter.areaSizeX * Counter.areaSizeY * Counter.areaSizeZ + "\n" +
                    "VECTORS vectors double\n");
            int f = -1;
            for (int k=0; k<areaSizeZ; k+=1){
                for (int j=0; j<areaSizeY; j+=1){
                    for (int i=0; i<areaSizeX; i+=1){
                        Vector3d value = fieldDistr[i][j][k];
                        if (value.length() > max){
                            max = value.length();
                        }
                        f++;
                        if (f==5) {
                            f=0;
                            vtkFieldWriter.write("\n");
                        }
                        vtkFieldWriter.write(value.x + " " +
                                value.y + " " +
                                value.z + '\u0009');
                    }
                }
            }

            vtkFieldWriter.close();
            Date stopDate = new Date();
            Long diff = stopDate.getTime() - startDate.getTime();
            System.out.println(diff/1000 + " sec");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void interpolateAndWriteFieldDistribution(){
        Date startDate = new Date();
        try {
            File file = new File(pathToOutputFile.replaceAll("vtkField", "vtkFieldInterpolated"));
            file.getParentFile().mkdirs();
            BufferedWriter vtkFieldWriter = new BufferedWriter(new FileWriter(file));

            interpolateFieldDistrLinear();

            vtkFieldWriter.write("# vtk DataFile Version 2.0\n" +
                    "Cube example\n" +
                    "ASCII\n" +
                    "DATASET STRUCTURED_POINTS\n" +
                    "DIMENSIONS " + Counter.areaSizeX + " " + Counter.areaSizeY + " " + Counter.areaSizeZ + "\n" +
                    "ORIGIN 0 0 0\n" +
                    "SPACING " + Counter.stepX + " " + Counter.stepY + " " + Counter.stepZ + "\n" +
                    "POINT_DATA " + Counter.areaSizeX * Counter.areaSizeY * Counter.areaSizeZ + "\n" +
                    "VECTORS vectors double\n");
            int f = -1;
            for (int k=0; k<areaSizeZ; k+=1){
                for (int j=0; j<areaSizeY; j+=1){
                    for (int i=0; i<areaSizeX; i+=1){
                        Vector3d value = interpolatedFieldDistr[i][j][k];
                        if (value.length() > interpolatedMax){
                            interpolatedMax = value.length();
                        }
                        f++;
                        if (f==5) {
                            f=0;
                            vtkFieldWriter.write("\n");
                        }
                        vtkFieldWriter.write(value.x + " " +
                                value.y + " " +
                                value.z + '\u0009');

                    }
                }
            }

            vtkFieldWriter.close();
            Date stopDate = new Date();
            Long timeDiff = stopDate.getTime() - startDate.getTime();
            System.out.println(timeDiff/1000 + " sec");
            System.out.println(interpolatedMax/max);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void interpolateQuadricAndWriteFieldDistribution(){
        Date startDate = new Date();
        try {
            File file = new File(pathToOutputFile.replaceAll("vtkField", "vtkFieldInterpolatedQuadric"));
            file.getParentFile().mkdirs();
            BufferedWriter vtkFieldWriter = new BufferedWriter(new FileWriter(file));

            interpolateFieldDistrQuadric();

            vtkFieldWriter.write("# vtk DataFile Version 2.0\n" +
                    "Cube example\n" +
                    "ASCII\n" +
                    "DATASET STRUCTURED_POINTS\n" +
                    "DIMENSIONS " + Counter.areaSizeX + " " + Counter.areaSizeY + " " + Counter.areaSizeZ + "\n" +
                    "ORIGIN 0 0 0\n" +
                    "SPACING " + Counter.stepX + " " + Counter.stepY + " " + Counter.stepZ + "\n" +
                    "POINT_DATA " + Counter.areaSizeX * Counter.areaSizeY * Counter.areaSizeZ + "\n" +
                    "VECTORS vectors double\n");
            int f = -1;
            for (int k=0; k<areaSizeZ; k+=1){
                for (int j=0; j<areaSizeY; j+=1){
                    for (int i=0; i<areaSizeX; i+=1){
                        Vector3d value = quadricInterpolatedFieldDistr[i][j][k];
                        if (value.length() > quadricInterpolatedMax){
                            quadricInterpolatedMax = value.length();
                        }
                        f++;
                        if (f==5) {
                            f=0;
                            vtkFieldWriter.write("\n");
                        }
                        vtkFieldWriter.write(value.x + " " +
                                value.y + " " +
                                value.z + '\u0009');

                    }
                }
            }

            vtkFieldWriter.close();
            Date stopDate = new Date();
            Long timeDiff = stopDate.getTime() - startDate.getTime();
            System.out.println(timeDiff/1000 + " sec");
            System.out.println(quadricInterpolatedMax/max);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static int getIndex(int i, int j){
        return (j == 0 ? i : i + j);
    }


}
