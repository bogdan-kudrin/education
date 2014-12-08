/**
 * Created by dan on 11/17/14.
 */
public class Model {
    private  boolean [][] field;
    private  int height, width;

    public Model(int h, int w) {
        height = h;
        width = w;
        field = new boolean[height][width];
    }

    public boolean[][] getField() {
        return field;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private int countNeighbors(int row, int col) {
        int[] add = {-1, 0, 1};
        int ans = 0;
        for (int i = 0; i < add.length; ++i) {
            for (int j = 0; j < add.length; ++j) {
                int r = ((row + add[i]) % height + height) % height;
                int c = ((col + add[j]) % width + width) % width;
                if (row == 2 && col == 1) {
                    System.out.println("?" + r + " " + c + field[r][c]);
                }
                if (field[r][c]) ++ans;
            }
        }
        return ans;
    }
    public void makeIteration() {
        boolean[][] newField = new boolean[height][width];
        for (int r = 0; r < height; ++r) {
            for (int c = 0; c < width; ++c) {
                int numberOfNeighbors = countNeighbors(r, c);
                System.err.println("!" + r + " " + c + " " + numberOfNeighbors + " " + field[r][c]);
                if (field[r][c]) {
                    newField[r][c] = (3 <= numberOfNeighbors && numberOfNeighbors <= 4);
                }
                else {
                    newField[r][c] = (numberOfNeighbors == 3);
                }
            }
        }
        field = newField;
    }

    public void changeSquare(int r, int c) {
        field[r][c] = !field[r][c];
    }
}
