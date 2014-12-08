import javax.swing.*;

/**
 * Created by dan on 12/8/14.
 */
public class Main {
    public static void main(String[] args) {
        JFrame inputFrame = new JFrame("Invariant sets");
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InputPanel inputPanel;
        inputPanel = new InputPanel();
        inputFrame.add(inputPanel);
        inputFrame.pack();
        inputFrame.setVisible(true);
    }
}
