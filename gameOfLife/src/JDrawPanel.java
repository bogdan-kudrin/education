/**
 * Created by Admin on 03.11.2014.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;

public class JDrawPanel extends JPanel {

    //Имена переменных с маленькой буквы
    Iteration one;
    JButton stepButton = new JButton("Шаг");
    JButton startButton = new JButton("Начать игру");

    public JDrawPanel(int size) {

        setLayout(null);
        one = new Iteration(size);


        startButton.setBounds(60, 5, 180, 20);
        stepButton.setBounds(110, 5, 80, 20);
        add(stepButton);
        add(startButton);
        stepButton.setVisible(false);

        stepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                one.done();
                repaint(); //вызов перерисовки окна

            }
        });

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                stepButton.setVisible(true);
                startButton.setVisible(false);

            }
        });
    }

    public void changeCellValue(int x, int y) {
        one.сhangeCellValue(x, y);
        repaint();
    }

    //метод, который вызывается при перерисовке окна
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        int Wd = (getWidth() - 4) / one.size();
        int Hg = (getHeight() - 30) / one.size();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(1));

        g2.setPaint(new GradientPaint(0, 0, Color.YELLOW, getWidth(), getHeight(), Color.RED));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));

        g2.setPaint(Color.BLUE);
        g2.translate(2, 30);

        for (int i = 0; i <= one.size(); i++) {

            g2.drawLine(0, i * Hg, getWidth(), i * Hg);
            g2.drawLine(i * Wd, 0, i * Wd, getHeight());
        }

        for (int i = 0; i < one.size(); i++) {
            for (int j = 0; j < one.size(); j++) {
                if (one.getCellValue(i, j)) {
                    g2.fillRect(j * Wd + Wd / 5, i * Hg + Hg / 5, Wd - Wd / 5 * 2, Hg - Hg / 5 * 2);
                }
            }
        }

        g2.dispose();

    }


}
