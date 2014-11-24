public class Model
{
    public static final int width = 500, height = 500;
    public static final double a = 2.2, b = 0.1;
    public boolean[][] f=new boolean[height][width];

    public boolean isInRegion(double x, double y) {
        if (x*x+y*y<=1.5) return true;
        else return false;
    }

    public double jToX(int j) {
        return (double)j*((double)4/width)-2;
    }

    public double iToY(int i) {
        return (double)i*((double)4/height)-2;
    }

    public int XtoJ(double x) {
        return (int)((x+2)*width/4);
    }
    public int YtoI(double y) {
        return (int)((y+2)*height/4);
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
