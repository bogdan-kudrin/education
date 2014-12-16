class Model {
    int size;
    int cellSize;
    int panelSize;
    boolean field[][];
    Model(int len) {
        size = len;
        cellSize = 500 / size;
        panelSize = size * cellSize;
        field = new boolean[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++)
                field[i][j] = false;
        }
    }

    public void doIteration() {
        boolean copy[][] = new boolean[size][size];
        int a, b, c, d, col;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                a = (i == 0) ? size - 1 : i - 1;
                b = (i == size - 1) ? 0 : i + 1;
                c = (j == 0) ? size - 1 : j - 1;
                d = (j == size - 1) ? 0 : j + 1;
                col = 0;
                if (field[a][c]) col++;
                if (field[a][j]) col++;
                if (field[a][d]) col++;
                if (field[i][c]) col++;
                if (field[i][d]) col++;
                if (field[b][c]) col++;
                if (field[b][j]) col++;
                if (field[b][d]) col++;
                if (!field[i][j]) {
                    copy[i][j] = (col == 3);
                }
                else {
                    copy[i][j] = (col == 3 || col == 2);
                }
            }
        }
        field = copy;
    }

    public void findAndChangeCell(int x, int y){
        int j = x / cellSize;
        int i = y / cellSize;
        field[i][j] = (!field[i][j]);
    }

    public boolean getCell(int i, int j){
        return field[i][j];
    }

    public int getSize() {
        return size;
    }

    public int getPanelSize() {
        return panelSize;
    }

    public int getCellSize(){
        return cellSize;
    }
}
