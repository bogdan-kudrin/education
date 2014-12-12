/*
*В общем, я пока смог сделать только кликанье на клетки и одновременную с ним работу игры в жизнь.
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
