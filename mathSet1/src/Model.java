public class Model
{
    public static final int width = 700, height = 700;
    public static final double x1 = -3, x2 = 3, y1 = -3, y2 = 3;
    public static final double a = 2.2, b = 0.4;
    public boolean[][] f=new boolean[height][width];

    public boolean isInRegion(double x, double y) {
        if (x*x+y*y<=8) return true;
        else return false;
    }

    public double jToX(int j) {
        return (double)j*((double)(x2-x1)/width)+x1;
    }

    public double iToY(int i) {
        return (double)i*((double)(y2-y1)/height)+y1;
    }

    public int XtoJ(double x) {
        return (int)((x-x1)*width/(x2-x1));
    }
    public int YtoI(double y) {
        return (int)((y-y1)*height/(y2-y1));
    }

    public double getNextX(double x, double y) {
        return (1+y-a*x*x);
    }

    public double getNextY(double x, double y) {
        return b*y;
    }

    public int getNextI(int i, int j) {
        return YtoI(getNextY(jToX(j), iToY(i)));

    }

    public int getNextJ(int i, int j) {
        return XtoJ(getNextX(jToX(j), iToY(i)));
    }

    public Model() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isInRegion(jToX(j), iToY(i))) f[i][j]=true;
            }
        }
    }

    public void nextStep() {
        boolean[][] new_f=new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (getNextI(i, j)>=0 && getNextI(i, j) < height && getNextJ(i, j)>=0 && getNextJ(i, j) < width &&
                        f[getNextI(i, j)][getNextJ(i, j)] && f[i][j]) new_f[i][j]=true;
            }
        }
        f = new_f;
    }
}
