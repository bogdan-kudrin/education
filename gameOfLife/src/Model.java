/**
 * Created by alina on 17.12.14.
 */
public class Model {
    public int size = 30;
    boolean[][] field;

    public Model() {
        field = new boolean[size][size];
    }

    public Model(int newSize) {
        size = newSize;
        field = new boolean[size][size];
    }

    public void nextStep() {
        boolean[][] new_field = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j]) {
                    int res = 0;
                    for (int ii = -1; ii < 2; ii++) {
                        for (int jj = -1; jj < 2; jj++) {
                            if (field[(i + ii + size) % size][(j + jj + size) % size] && (ii != 0 || jj != 0))
                                res++;
                        }
                    }
                    if (res == 2 || res == 3) {
                        new_field[i][j] = true;
                    }
                } else {
                    int res = 0;
                    for (int ii = -1; ii < 2; ii++) {
                        for (int jj = -1; jj < 2; jj++) {
                            if (field[(i + ii + size) % size][(j + jj + size) % size] && (ii != 0 || jj != 0))
                                res++;
                        }
                    }
                    if (res == 3) {
                        new_field[i][j] = true;
                    }
                }
            }
        }
        field = new_field;
    }

}
