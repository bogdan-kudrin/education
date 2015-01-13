import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.util.List;

/**
 * User: BKudrin
 * Date: 17.02.14
 * Time: 15:10
 */
public class Interpolator {

    public double interpolateLinear(Point3d[][][] points, double[][][] values, Point3d interpolationPoint){
        double[][][] basisPolinoms = new double[2][2][2];
        for (int n=0; n<2; n++){
            for (int m=0; m<2; m++){
                for (int p=0; p<2; p++){
                    basisPolinoms[n][m][p] = 1;
                }
            }
        }
        for (int n=0; n<2; n++){
            for (int m=0; m<2; m++){
                for (int p=0; p<2; p++){
                    for (int i=0; i<2; i++){
                        for (int j=0; j<2; j++){
                            for (int k=0; k<2; k++){
                                if (i != n && j!= m && k!=p){
                                basisPolinoms[n][m][p] *= ((interpolationPoint.getX()-points[i][j][k].getX())*(interpolationPoint.getY()-points[i][j][k].getY())*(interpolationPoint.getZ()-points[i][j][k].getZ()))/
                                        ((points[n][m][p].getX()-points[i][j][k].getX())*(points[n][m][p].getY()-points[i][j][k].getY())*(points[n][m][p].getZ()-points[i][j][k].getZ()));
                                }
                            }
                        }
                    }
                }
            }
        }
        double result = 0;
        for (int n=0; n<2; n++){
            for (int m=0; m<2; m++){
                for (int p=0; p<2; p++){
                    result += values[n][m][p]*basisPolinoms[n][m][p];
                }
            }
        }
        return result;
    }

    public double interpolateQuadric(double[] points, double[] values, double interpolationPoint){
        double[] basisPolinoms = new double[3];
        for (int p=0; p<3; p++){
            basisPolinoms[p] = 1;
        }
        for (int n=0; n<3; n++){
            for (int p=0; p<3; p++){
                if (n != p){
                    basisPolinoms[n] *= (interpolationPoint - points[p])/(points[n]-points[p]);
                }
            }
        }
        double result = 0;
        for (int p=0; p<3; p++){
            result += values[p]*basisPolinoms[p];
        }
        return result;
    }



}
