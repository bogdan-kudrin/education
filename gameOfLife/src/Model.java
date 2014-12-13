/**
 * Created by notEphim on 11.12.2014.
 */
public class Model {
    int height;
    int width;
    int cellSize;
    int xBorderFields;
    int yBorderFields;
    boolean field[][];
    boolean nothingChanged = false;

    Model (int h, int w){

        width = w;
        height = h;
        field = new boolean [height][width];
        for (int i = 0 ; i < height ; ++i) {
            for (int j = 0; j < width; ++j) {
                field[i][j] = false;
            }
        }
    }

    public void iterate () {

        int neighbours[][] = new int[height][width];
        for (int i = 0 ; i < height ; ++i) {
            for (int j = 0 ; j < width ; ++j) {
                for (int di = -1 ; di < 2 ; ++di) {
                    for (int dj = -1 ; dj < 2 ; ++dj) {
                        if (di != 0 || dj != 0) {
                            int iCheck = (i + di + height) % height;
                            int jCheck = (j + dj + width) % width;
                            if (field[iCheck][jCheck])
                                ++neighbours[i][j];
                        }
                    }
                }
            }
        }
        nothingChanged = true;
        for (int i = 0 ; i < height ; ++i) {
            for (int j = 0 ; j < width ; ++j) {
                if (neighbours[i][j] == 3 && field[i][j] == false) {
                    nothingChanged = false;
                    field[i][j] = true;
                } else if ((neighbours[i][j] < 2 || neighbours[i][j] > 3) && field[i][j] == true) {
                    nothingChanged = false;
                    field[i][j] = false;
                }
            }
        }
    }

    public void clear () {
        for (int i = 0 ; i < height ; ++i) {
            for (int j = 0 ; j < width ; ++j) {
                field[i][j] = false;
            }
        }
    }

    public int rowOfTheCellWithPlanarCoordinates (int x, int y) {
        return (y - yBorderFields) / cellSize;
    }

    public int columnOfTheCellWithPlanarCoordinates (int x, int y) {
        return (x - xBorderFields) / cellSize;
    }

    public int xLeftUpperCornerOfTheCellWithCoordinates (int i, int j) {
        return j * cellSize + xBorderFields;
    }

    public int yLeftUpperCornerOfTheCellWithCoordinates (int i, int j) {
        return i * cellSize + yBorderFields;
    }

    public void changeTheCellWithCoordinates (int i, int j) {

        field[i][j] = !field[i][j];
    }

    public int getHeight () {
        return height;
    }

    public int getWidth () {
        return width;
    }

    public int getCellSize () {
        return cellSize;
    }

    public int getxBorderFields () {
        return xBorderFields;
    }

    public int getyBorderFields () {
        return yBorderFields;
    }

    public boolean getCellValue (int i, int j) {
        return field[i][j];
    }

    public void countCellSize (int xLimits, int yLimits) {
        cellSize = Math.min ((xLimits - 1) / width, (yLimits - 1) / height);
        xBorderFields = (xLimits - width * cellSize) / 2;
        yBorderFields = (yLimits - height * cellSize) / 2;
    }
}
