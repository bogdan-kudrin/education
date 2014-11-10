/**
 * Created by Admin on 03.11.2014.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;

public class JDrawPanel extends JPanel  {

    iteration One;
    JButton submit = new JButton("Шаг");
    JButton submit1 = new JButton("Начать игру");


    //конструктор панели
    public JDrawPanel (int size) {

        //говорим, что будем задавать координаты кнопок вручную
        setLayout(null);
        One = new iteration(size);


        submit1.setBounds(60, 5, 180, 20);
        submit.setBounds(110, 5, 80, 20);
        add(submit);
        add(submit1);
        submit.setVisible(false);//кнопка "Шаг" появится только после задания живых клеток

        //создаем обработчик нажатия на кнопки
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                One.Done();
                repaint(); //вызов перерисовки окна

            }
        });

        submit1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                submit.setVisible(true);
                submit1.setVisible(false);

            }
        });
    }

    //меняем значение клетки
    public void ChangeZnach(int x, int y) {
        One.ChangeZnach(x, y);
        repaint();
    }

    //метод, который вызывается при перерисовке окна
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g.create();
        int Wd = (getWidth() - 4) / One.size();
        int Hg = (getHeight() - 30) / One.size();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON );
        g2.setStroke(new BasicStroke(1));

        g2.setPaint(new GradientPaint(0, 0, Color.YELLOW, getWidth(), getHeight(), Color.RED));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));

        g2.setPaint(Color.BLUE);
        g2.translate(2, 30);

        for (int i = 0; i <= One.size(); i++){

            g2.drawLine(0, i*Hg, getWidth(), i*Hg);
            g2.drawLine(i*Wd, 0, i*Wd, getHeight());
        }

        for (int i = 0; i < One.size(); i++){
            for (int j = 0; j < One.size(); j++){
                if (One.getZnach(i,j)){
                    g2.fillRect(j * Wd + Wd / 5, i * Hg + Hg / 5, Wd - Wd /5 * 2, Hg - Hg / 5 * 2 );
                }
            }
        }

        g2.dispose();

    }







}
