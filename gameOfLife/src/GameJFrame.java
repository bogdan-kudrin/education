/**
 * Created by Admin on 03.11.2014.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class GameJFrame extends JFrame {
    int size; //размер игры
    JDrawPanel MyJDrawPanel; //панель для рисования

    //конструктор
    public GameJFrame(int size_) {
        size = size_;
        MyJDrawPanel = new JDrawPanel(size);//создаем панель

        //создаем слушатель и обработчик для события "Нажатие кнопки мыши"
        MyJDrawPanel.addMouseListener(new MouseAdapter() {

            //метод обработки нажатия кнопки мыши (получаем координаты нажатия)
            public void mouseClicked(MouseEvent event) {
//                JOptionPane.showMessageDialog(null, event.getX());
//                JOptionPane.showMessageDialog(null, event.getY());
                // вот тут размеры панели на 16 и 38 пикселей меньше, чем надо
                int x = (event.getX() - 2) / ((getWidth() - 20) / size);
                int y = (event.getY() - 30) / ((getHeight() - 68) / size);
                if (((event.getX() - 2) >= 0) && (x < size) && ((event.getY() - 30) >= 0) && (y < size)) {
                    MyJDrawPanel.ChangeZnach(y, x);
                }
            }

        });

        //обработка закрытия окна
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //привязываем панель к фрейму
        getContentPane().add(MyJDrawPanel);

        //устанавливаем начальный размер окна
        setSize(300, 300);
    }
}
