public class Field {
    public int size_h=100, size_w=100;
    public boolean[][] f=new boolean[size_h][size_w];

    public Field() {
        f[5][1]=f[5][2]=f[6][1]=f[6][2]=f[5][11]=f[6][11]=f[7][11]=f[4][12]=f[8][12]=f[3][13]=f[3][14]=f[9][13]=
                f[9][14]=f[6][15]=f[4][16]=f[8][16]=f[5][17]=f[6][17]=f[7][17]=f[6][18]=
                        f[3][21]=f[4][21]=f[5][21]=f[3][22]=f[4][22]=f[5][22]=f[2][23]=f[6][23]=f[2][25]=f[1][25]=
                                f[6][25]=f[7][25]=f[3][35]=f[3][36]=f[4][35]=f[4][36]=true;
    }

    public void step() {
        boolean[][] new_f=new boolean[size_h][size_w];
        for (int i = 0; i < size_h; i++) {
            for (int j = 0; j < size_w; j++) {
                int neighbours = 0;
                for (int di = -1; di <= 1; di++) {
                    for (int dj = -1; dj <= 1; dj++) {
                        neighbours += f[(i + di + size_h) % size_h][(j + dj + size_w) % size_w] ? 1 : 0;
                    }
                }
                neighbours -= f[i][j] ? 1 : 0;
                if (neighbours == 3 || (neighbours == 2 && f[i][j])) {
                    new_f[i][j] = true;
                }
            }
        }
        for (int i=0; i<size_w; i++) new_f[size_h-1][i]=false;
        f=new_f;
    }
}