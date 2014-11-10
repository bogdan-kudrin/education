/**
 * Created by Admin on 22.09.2014.
 */
public class Table {
    private int size;
    private boolean table[][];


    public Table() {
        size = 1;
        table = new boolean[1][1];
        table[0][0] = false;
    }

    public Table(int size) {
        // _size Лучше так:
        this.size = size;
        table = new boolean[this.size][this.size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = false;
            }
        }
    }

    public Table(int size, boolean array[][]) {
        this.size = size;
        table = new boolean[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                table[i][j] = array[i][j];
            }
        }
    }

    public void setCellValue(int k, int l, boolean f) {
        if ((k >= 0) && (l >= 0) && (k < size) && (l < size))
            table[k][l] = f;
    }

    //Обычно public геттеры как раз возвращают private переменную класса
    //Тут правильно
    public int getSize() {
        return size;
    }

    public boolean[][] getTable() {
        boolean copy[][] = new boolean[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; j++) {
                copy[i][j] = table[i][j];
            }
        }
        return copy;
    }

    //А тут геттер не очень логичен
    public boolean getOne(int k, int l) {
        //Всегда if/else оборачивать в фигурные скобки
        if ((k >= 0) && (l >= 0) && (k < size) && (l < size)) {
            return table[k][l];
        } else {
            throw new RuntimeException("Выход за границы массива");
        }
    }

    public boolean getNeighbour(int k, int l, int i) {
        switch (i) {
            case 1:
                return table[(size + k - 1) % size][(size + l - 1) % size];
            case 2:
                return table[(size + k) % size][(size + l - 1) % size];
            case 3:
                return table[(size + k + 1) % size][(size + l - 1) % size];
            case 4:
                return table[(size + k - 1) % size][(size + l) % size];
            case 5:
                return table[(size + k + 1) % size][(size + l) % size];
            case 6:
                return table[(size + k - 1) % size][(size + l + 1) % size];
            case 7:
                return table[(size + k) % size][(size + l + 1) % size];
            case 8:
                return table[(size + k + 1) % size][(size + l + 1) % size];
            default:
                return false;
        }

    }
}
