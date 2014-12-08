/**
 * Created by dan on 12/1/14.
 */
public class Model {

    private Complex c;
    private double r;
    private int maxIter;

    private int height;
    private int width;
    private boolean[][] field;

    private double xStep;
    private double yStep;

    public Model(double re, double im, int it, int height, int width) {
        c = new Complex(re, im);
        r = calcR(c);
        maxIter = it;
        this.height = height;
        this.width = width;
        xStep = 2 * r / width;
        yStep = 2 * r / height;
        field = new boolean[height][width];
        double y = -this.r;
        for (int r = 0; r < height; ++r){
            double x = -this.r;
            for (int c = 0; c < width; ++c) {
                Complex z = new Complex(x, y);
                field[r][c] = IsInInvSet(z);
                x += xStep;
            }
            y += yStep;
        }
    }

    private boolean IsInInvSet(Complex z) {
        for (int i = 0; i <= maxIter; ++i) {
            if (!isInside(z)) {
                return false;
            }
            z = nextIteration(z);
        }
        //System.err.println(z.getRe() + " " + z.getIm());
        return true;
    }

    private Complex nextIteration(Complex z) {
        return (z.multiply(z)).add(c);
    }

    private boolean isInside(Complex z) {
        //System.err.println(z.getRe() + " " + z.getIm() + "i " + z.abs());
        return z.abs() <= r;
    }

    private double calcR(Complex c) {
        return (1 + Math.sqrt(1 + 4 * c.abs())) / 2;
    }


    public boolean[][] getField() {
        return field;
    }
}
