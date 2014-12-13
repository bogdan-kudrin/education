/*
*Сейчас это игра на поле 100х100 с возможностью остановить, запустить и сделать шаг
*/

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new View ("Game of Life");
            }
        });
    }
}
