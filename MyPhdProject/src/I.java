import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class I {
    public static final ArrayList<Double> ellipticK = scanData(
            I.class.getResourceAsStream("interpolationData/EllipticK.txt"));
    public static ArrayList<Double> ellipticE = scanData(
            I.class.getResourceAsStream("interpolationData/EllipticE.txt"));
    public static ArrayList<Double> diffK = scanData(
            I.class.getResourceAsStream("interpolationData/DiffK.txt"));
    public static ArrayList<Double> diffE = scanData(
            I.class.getResourceAsStream("interpolationData/DiffE.txt"));

    public static ArrayList<Double> scanData(InputStream inputStream) {
        ArrayList<Double> result = new ArrayList<Double>();
        Scanner scanner = new Scanner(inputStream);
        try {
            while (scanner.hasNextLine()){
                result.add(Double.parseDouble(scanner.nextLine()));
            }
        } finally{
            scanner.close();
        }
        return result;
    }

    public static double eK(double kSquare) {
        double dk = (kSquare * 50);
        int k = (int) dk;
        double rk = (double) k;
        return ellipticK.get(k) + (kSquare -(rk/50)) * diffK.get(k) * 0.01;
    }

    public static double eE(double kSquare)
    {
        double dk = (kSquare * 50);
        int k = (int) dk;
        double rk = (double) k;
        return ellipticE.get(k) - (kSquare - (rk/50.0)) * diffE.get(k) * 0.01;

    }
}
