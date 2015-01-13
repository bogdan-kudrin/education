import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * User: BKudrin
 * Date: 12.08.14
 * Time: 13:37
 */
public class Counter2 {
    static String pathToFile = "C:\\magneticFieldFiles\\inputTable.txt";
    static double[] lineDoubleParams = new double[13];

    static double distanceToPoint = 0;
    static double[] points = new double[3];
    static double[] values = new double[3];
    static double[] interpolationResults;
    static int interpolationResultsLength;

    public static int a = 10;
    public static int b = 20;
    public static int c = 30;
    public static int d = 40;
    public static int aColorBound;
    public static int bColorBound;
    public static int cColorBound;
    public static int dColorBound;

    public static void fillParams(int lineNumber){
        try{
            BufferedReader br = new BufferedReader(new FileReader(pathToFile));
            String line = br.readLine();
            int lineCounter = 1;

            while (line != null && (lineCounter < lineNumber)) {
                lineCounter++;
                line = br.readLine();
            }

            br.close();
            String[] lineStrParams = line.split("\\t");

            for (int i=0; i < 13; i++) {
                lineDoubleParams[i] = Double.parseDouble(lineStrParams[i]);
            }

            double point1X = lineDoubleParams[2];
            double point1Y = lineDoubleParams[3];
            double point1Z = lineDoubleParams[4];

            double point2X = lineDoubleParams[6];
            double point2Y = lineDoubleParams[7];
            double point2Z = lineDoubleParams[8];

            points[0] = 0;
            points[2] = Math.sqrt((point2X - point1X)*(point2X - point1X) + (point2Y - point1Y)*(point2Y - point1Y) + (point2Z - point1Z)*(point2Z - point1Z));
            points[1] = points[2]/2;

            values[0] = lineDoubleParams[9];
            values[2] = lineDoubleParams[10];
            values[1] = lineDoubleParams[11];

            interpolationResultsLength = (int) Math.round(points[1]);
            interpolationResults = new double[interpolationResultsLength];


            for (int i = 0; i < interpolationResultsLength; i++){
                interpolationResults[i] = interpolateQuadric(i);
            }

            for (int i = 0; i < interpolationResultsLength; i++){
                if (interpolationResults[i]>= a && interpolationResults[i+1]<a){
                    aColorBound = i;
                }  else if (interpolationResults[i]>= b && interpolationResults[i+1]<b){
                    bColorBound = i;
                }  else if (interpolationResults[i]>= c && interpolationResults[i+1]<c){
                    cColorBound = i;
                }  else if (interpolationResults[i]>= d && interpolationResults[i+1]<d){
                    dColorBound = i;
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static double interpolateQuadric(){
        return interpolateQuadric(points, values, distanceToPoint);
    }

    public static double interpolateQuadric(double distanceToPoint){
        return interpolateQuadric(points, values, distanceToPoint);
    }

    public static double interpolateQuadric(double[] points, double[] values, double distanceToPoint){
        double[] basisPolinoms = new double[3];
        for (int p=0; p<3; p++){
            basisPolinoms[p] = 1;
        }
        for (int n=0; n<3; n++){
            for (int p=0; p<3; p++){
                if (n != p){
                    basisPolinoms[n] *= (distanceToPoint - points[p])/(points[n]-points[p]);
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
