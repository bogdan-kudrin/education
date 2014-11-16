/**
 * Created by Admin on 03.11.2014.
 */

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameJFrame extends JFrame {

    JDrawPanel MyJDrawPanel;

    public GameJFrame() {
        MyJDrawPanel = new JDrawPanel();//создаем панель

        MyJDrawPanel.addMouseListener(new MouseAdapter() {

            //метод обработки нажатия кнопки мыши (получаем координаты нажатия)
            public void mouseClicked(MouseEvent event) {
//                JOptionPane.showMessageDialog(null, event.getX());
//                JOptionPane.showMessageDialog(null, event.getY());
                int size = MyJDrawPanel.sizeGame;
                int x = (event.getX() - 2) / ((getWidth() - 20) / size);
                int y = (event.getY() - 30) / ((getHeight() - 68) / size);
                if (((event.getX() - 2) >= 0) && (x < size) && ((event.getY() - 30) >= 0) && (y < size)) {
                    MyJDrawPanel.changeCellValue(y, x);
                }
            }

        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(MyJDrawPanel);
        setSize(500, 500);
    }
}
