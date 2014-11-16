/**
 * Created by Admin on 03.11.2014.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;

public class JDrawPanel extends JPanel {

    Iteration one;
    int sizeGame;
    int speed;
    Boolean gameFlag = true;
    JButton renewButton = new JButton("Возобновить");
    JButton pauseButton = new JButton("Пауза");
    JButton beginButton = new JButton("Начать");
    JButton restartButton = new JButton("Новая игра");
    JButton startButton = new JButton("Начать игру");
    JLabel sizeLabel = new JLabel("Введите размер игрового поля");
    JTextField sizeField = new JTextField();
    JLabel sliderLabel = new JLabel("Выберите скорость анимации");
    JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);


    public JDrawPanel() {

        setLayout(null);

        add(sizeField);

        add(sizeLabel);
        Font font = new Font("Century Gothic", Font.BOLD, 18);
        sizeLabel.setFont(font);

        add(sizeSlider);
        sizeSlider.setMajorTickSpacing(1);
        sizeSlider.setPaintLabels(true);

        add(sliderLabel);
        sliderLabel.setFont(font);

        add(startButton);
        startButton.setVisible(true);

        add(renewButton);
        add(restartButton);
        add(beginButton);
        add(pauseButton);

        startButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                String sizeTry = new String();
                sizeTry = sizeField.getText();
                boolean sizeFlag = true;
                try {
                    sizeGame = Integer.valueOf(sizeTry);
                } catch (NumberFormatException t) {
                    JOptionPane.showMessageDialog(null, "Неверный формат ввода", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    sizeField.setText("");
                    sizeFlag = false;
                }
                if (sizeGame <= 0) {
                    JOptionPane.showMessageDialog(null, "Неверный формат ввода", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    sizeField.setText("");
                    sizeFlag = false;
                }
                if (sizeGame > 30) {
                    JOptionPane.showMessageDialog(null, "Превышен допустимый размер поля", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    sizeField.setText("");
                    sizeFlag = false;
                }
                if (sizeFlag) {
                    speed = sizeSlider.getValue();
                    sizeLabel.setVisible(false);
                    sizeField.setVisible(false);
                    startButton.setVisible(false);
                    gameFlag = false;
                    one = new Iteration(sizeGame);
                    beginButton.setVisible(true);
                    restartButton.setVisible(true);
                    pauseButton.setVisible(false);
                    renewButton.setVisible(false);
                    sizeSlider.setVisible(false);
                    sliderLabel.setVisible(false);
                    repaint();
                }

            }

            public void mouseEntered(MouseEvent event) {
                startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }


        });

        final Timer timer = new javax.swing.Timer( 100, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                repaint();
                one.done();
            }
        } );

        beginButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                beginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                beginButton.setVisible(false);
                restartButton.setVisible(false);
                pauseButton.setVisible(true);
                timer.setDelay(100 * speed);
                timer.start();
            }

            public void mouseEntered(MouseEvent event) {
                beginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }


        });

        pauseButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                pauseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                pauseButton.setVisible(false);
                renewButton.setVisible(true);
                restartButton.setVisible(true);
                timer.stop();
            }

            public void mouseEntered(MouseEvent event) {
                pauseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }


        });

        renewButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                renewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                renewButton.setVisible(false);
                restartButton.setVisible(false);
                pauseButton.setVisible(true);
                timer.start();
            }

            public void mouseEntered(MouseEvent event) {
                renewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }


        });

        restartButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                restartButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                gameFlag = true;
                sizeField.setVisible(true);
                sizeLabel.setVisible(true);
                startButton.setVisible(true);
                sizeSlider.setVisible(true);
                sliderLabel.setVisible(true);
                restartButton.setVisible(false);
                renewButton.setVisible(false);
                beginButton.setVisible(false);
                repaint();
            }

            public void mouseEntered(MouseEvent event) {
                restartButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }


        });





    }

    public void changeCellValue(int x, int y) {
        one.changeCellValue(x, y);
        repaint();
    }

    //метод, который вызывается при перерисовке окна
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        if (gameFlag) {
            sizeLabel.setBounds((getWidth() - 310) / 2, 30, 400, 30);
            sizeField.setBounds((getWidth() - 150) / 2, 15 + getHeight()/ 5 , 150, 30);
            startButton.setBounds((getWidth()- 175) / 2, 30 + 4 * getHeight()/ 5, 180, 30);
            sizeField.setText("");
            sizeSlider.setBounds((getWidth() - 400) / 2, 3 * getHeight() / 5, 400, 30);
            sliderLabel.setBounds((getWidth() - 310) / 2, 15 + 2 * getHeight() / 5, 400, 30);


        } else {

            restartButton.setBounds(getWidth() / 10, 5, 3 * getWidth() / 10, 20);
            pauseButton.setBounds((getWidth() - 160) / 2, 5, 150, 20);
            renewButton.setBounds(6 * getWidth() / 10 , 5, 3 * getWidth() / 10, 20);
            beginButton.setBounds(6 * getWidth() / 10 , 5, 3 * getWidth() / 10, 20);



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


}