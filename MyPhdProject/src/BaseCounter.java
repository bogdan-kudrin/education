import javax.vecmath.Point2d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector2d;
import javax.vecmath.Vector3d;
import java.io.*;
import java.util.Date;

public class BaseCounter {
    String pathToOutputFile;
    int areaSizeX = 10;
    int areaSizeY = 10;
    int areaSizeZ = 10;
    int stepX = 1;
    int stepY = 1;
    int stepZ = 1;
    double scalefactorX = 1000.0/stepX;
    double scalefactorY = 1000.0/stepY;
    double scalefactorZ = 1000.0/stepZ;

    Vector3d[][][] fieldDistr;
    Vector3d[][][] interpolatedFieldDistr;
    Vector3d[][][] quadricInterpolatedFieldDistr;

    public Coil firstCoil = new Coil();
    public Coil secondCoil = new Coil();
    public Coil thirdCoil = new Coil();
    public Coil fourthCoil = new Coil();
    public Coil fifthCoil = new Coil();
    public Coil sixthCoil = new Coil();
    public boolean[] enabledCoils = {false, false, false, false, false, false};
    public Coil[] coils = {firstCoil, secondCoil, thirdCoil, fourthCoil, fifthCoil, sixthCoil};

    double max = 0;
    double interpolatedMax = 0;
    double quadricInterpolatedMax = 0;

     public void initField(){
        fieldDistr = new Vector3d[areaSizeX][areaSizeY][areaSizeZ];
        for (int i=0; i<areaSizeX; i+=1){
            for (int j=0; j<areaSizeY; j+=1){
                for (int k=0; k<areaSizeZ; k+=1){
                    fieldDistr[i][j][k] = new Vector3d();
                }
            }
        }
    }

    public void initInterpolatedField(){
        interpolatedFieldDistr = new Vector3d[areaSizeX*2 -1][areaSizeY*2 -1][areaSizeZ*2 -1];
        for (int i=0; i<areaSizeX*2 -1; i+=1){
            for (int j=0; j<areaSizeY*2 -1; j+=1){
                for (int k=0; k<areaSizeZ*2 -1; k+=1){
                    interpolatedFieldDistr[i][j][k] = new Vector3d();
                }
            }
        }
    }

    public void initQuaricInterpolatedField(){
        quadricInterpolatedFieldDistr = new Vector3d[areaSizeX][areaSizeY][areaSizeZ];
        for (int i=0; i<areaSizeX; i+=1){
            for (int j=0; j<areaSizeY; j+=1){
                for (int k=0; k<areaSizeZ; k+=1){
                    quadricInterpolatedFieldDistr[i][j][k] = new Vector3d();
                }
            }
        }
    }

    public void countFieldDistribution(){
        initField();
        for (int i=0; i< coils.length; i++){
            if (enabledCoils[i]){
                countCoilFieldDistr(coils[i]);
            }
        }
    }

    /*public void parallelCountFieldDistribution(){
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
    }*/

    public void countCoilFieldDistr(Coil coil){
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

    public void interpolateFieldDistrLinear(){
        initInterpolatedField();
        Interpolator interpolator = new Interpolator();
        for (int i=0; i<areaSizeX - 1; i+=1){
            for (int j=0; j<areaSizeY - 1; j+=1){
                for (int k=0; k<areaSizeZ - 1; k+=1){
                    //Point3d interpolationPoint = new Point3d((i + 0.5)/scalefactorX, (j + 0.5)/scalefactorY, (k + 0.5)/scalefactorZ);
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

                    for (int l=0; l<2; l+=1){
                        for (int m=0; m<2; m+=1){
                            for (int n=0; n<2; n+=1){
                                Point3d interpolationPoint = new Point3d(((double)i+(double)l*0.5)/scalefactorX, ((double)j+(double)m*0.5)/scalefactorY, ((double)k+(double)m*0.5)/scalefactorZ);
                                interpolatedFieldDistr[getIndex(i*2,l)][getIndex(j*2,m)][getIndex(k*2,n)].setX(interpolator.interpolateLinear(interpolationNodes, interpolationValuesX, interpolationPoint));
                                interpolatedFieldDistr[getIndex(i*2,l)][getIndex(j*2,m)][getIndex(k*2,n)].setY(interpolator.interpolateLinear(interpolationNodes, interpolationValuesY, interpolationPoint));
                                interpolatedFieldDistr[getIndex(i*2,l)][getIndex(j*2,m)][getIndex(k*2,n)].setZ(interpolator.interpolateLinear(interpolationNodes, interpolationValuesZ, interpolationPoint));
                            }
                        }

                    }


                }
            }

        }

    }

    public void interpolateFieldDistrQuadric(){
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

    public void countAndWriteFieldDistribution(){
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
                    "DIMENSIONS " + areaSizeX + " " + areaSizeY + " " + areaSizeZ + "\n" +
                    "ORIGIN 0 0 0\n" +
                    "SPACING " + stepX + " " + stepY + " " + stepZ + "\n" +
                    "POINT_DATA " + areaSizeX * areaSizeY * areaSizeZ + "\n" +
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

    public void interpolateAndWriteFieldDistribution(){
        try {
            File file = new File(pathToOutputFile.replaceAll("vtkField", "vtkFieldInterpolated"));
            file.getParentFile().mkdirs();
            BufferedWriter vtkFieldWriter = new BufferedWriter(new FileWriter(file));

            interpolateFieldDistrLinear();

            vtkFieldWriter.write("# vtk DataFile Version 2.0\n" +
                    "Cube example\n" +
                    "ASCII\n" +
                    "DATASET STRUCTURED_POINTS\n" +
                    "DIMENSIONS " + (areaSizeX*2 - 1) + " " + (areaSizeY*2 - 1) + " " + (areaSizeZ*2 - 1) + "\n" +
                    "ORIGIN 0 0 0\n" +
                    "SPACING " + stepX/2 + " " + stepY/2 + " " + stepZ/2 + "\n" +
                    "POINT_DATA " + (areaSizeX*2 - 1) * (areaSizeY*2 - 1) * (areaSizeZ*2 - 1) + "\n" +
                    "VECTORS vectors double\n");
            int f = -1;
            for (int k=0; k<(areaSizeZ*2 - 1); k+=1){
                for (int j=0; j<(areaSizeY*2 - 1); j+=1){
                    for (int i=0; i<(areaSizeX*2 - 1); i+=1){
                        Vector3d value = interpolatedFieldDistr[i][j][k];
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
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void interpolateQuadricAndWriteFieldDistribution(){
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
                    "DIMENSIONS " + areaSizeX + " " + areaSizeY + " " + areaSizeZ + "\n" +
                    "ORIGIN 0 0 0\n" +
                    "SPACING " + stepX + " " + stepY + " " + stepZ + "\n" +
                    "POINT_DATA " + areaSizeX * areaSizeY * areaSizeZ + "\n" +
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

    public int getIndex(int i, int j){
        return (j == 0 ? i : i + j);
    }

}
