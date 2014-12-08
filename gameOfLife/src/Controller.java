import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dan on 11/17/14.
 */
public class Controller {


    private static Model model;
    private static JFrame frame;
    private static FieldOnFrame fieldOnFrame;

    public static void main(String[] args) {
        JFrame inputFrame = new JFrame("The game of life");
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InputPanel inputPanel;
        inputPanel = new InputPanel();
        inputFrame.add(inputPanel);
        inputFrame.pack();
        inputFrame.setVisible(true);
    }

    public static void changeSquare(int r, int c) {
        model.changeSquare(r, c);
        fieldOnFrame.changeSquare(r, c);
    }

    public static void makeField(int height, int width) {
        model = new Model(height, width);
        boolean [][] blank = new boolean[height][width];
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        int squareSize = Math.min((int) Math.min(sSize.getHeight() / 1.2 / height, sSize.getWidth() / 1.2 / width), 100);
        System.err.println(squareSize);
        fieldOnFrame = new FieldOnFrame(height, width, blank, squareSize);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(fieldOnFrame);
        frame.setSize(sSize);
        JButton commitButton = new JButton("Confirm");
        commitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        frame.getContentPane().add(BorderLayout.AFTER_LAST_LINE,commitButton);
        //frame.pack();
        fieldOnFrame.repaint();
        frame.setVisible(true);
    }

    private static void startGame() {
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.makeIteration();
                fieldOnFrame.applyChanges(model.getHeight(), model.getWidth(), model.getField());
                frame.repaint();
            }
        });
        timer.start();
    }
}
