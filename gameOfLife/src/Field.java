import java.util.LinkedList;

public class Field {

    public LinkedList<Cell> cells;
    int n;  // количество элементов на диагонали

    /**
     * Конструктор квадратного поля из клеток
     */
    public Field(int n) {
        this.n = n;
        int N = n * n; // Общее количество клеток
        cells = new LinkedList<>(); // список всех клеток на поле
        for (int i = 0; i < N; i++) {
            cells.add(new Cell());  // заполнение списка новыми клетками
        }
        int r, l;
        Cell cell;
        // определение соседей у клеток
        for (int i = N; i < 2 * N; i++) {
            cell = cells.get(i % N);
            if (i % n == 0) {
                l = 1;
            } else {
                l = 0;
            }
            if (i % n == n - 1) {
                r = 1;
            } else {
                r = 0;
            }
            cell.addNeighbors(cells.get((i + 1 - r * n) % N));
            cell.addNeighbors(cells.get((i + n + 1 - r * n) % N));
            cell.addNeighbors(cells.get((i - n + 1 - r * n) % N));
            cell.addNeighbors(cells.get((i - 1 + l * n) % N));
            cell.addNeighbors(cells.get((i + n - 1 + l * n) % N));
            cell.addNeighbors(cells.get((i - n - 1 + l * n) % N));
            cell.addNeighbors(cells.get((i + n) % N));
            cell.addNeighbors(cells.get((i - n) % N));
        }
    }
    /**
     * Проверка на живучесть всех клеток
    */
    public void verifyCells() {
        for (Cell cell : cells) {
            cell.verify();
        }
        for (Cell cell : cells) {
            cell.nextStep();
        }
    }

    public void setAlive(int i, int j, boolean b){
        cells.get(i + j * n).setAlive(b);
    }

}
