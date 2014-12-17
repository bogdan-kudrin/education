import java.util.LinkedList;

public class Cell {

    private LinkedList<Cell> neighbors; // Соседи клетки (другие 8 клеток)
    private boolean alive; // признак жизни
    private boolean nextState;


    /**
     *  Конструктор клетки
     */
    public Cell() {
        alive = false; // изначальна клетка мертва
        nextState = false; // её следующее состояние - смерть
        neighbors = new LinkedList<>(); // строчка, которая должна быть в коде. Инициализация списка соседей

    }

    /**
     * Добавляем клетку в соседи
     */
    public void addNeighbors(Cell cell) {
        if (neighbors.size() < 8) { // делаем проверку на количество соседей на всякий случай
            neighbors.add(cell);
        }
    }

    /**
    * Проверка жизнедеятельности клетки
    */
    public void verify() {
        int i = 0;
        // цикл по всем элементам списка neighbors, на каждом шаге cell - очередной сосед

        for (Cell cell : neighbors) {

            if (cell.isAlive()) {
                i++;
            }
        }
        nextState = alive;

        if (alive   ) {
            if (i < 2 || i > 3) {
                nextState = false;
            }
        } else {
            if (i == 3) {
                nextState = true;
            }
        }
    }

    public void nextStep(){
        alive = nextState;
    }

    /**
     * Стандартный аксессор к полю alive
     * return жива ли клетка
     */
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
