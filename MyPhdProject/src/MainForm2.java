import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: BKudrin
 * Date: 11.08.14
 * Time: 14:28
 */
public class MainForm2 {
    private JTextField lineNumber;
    private JPanel mainPanel;
    private JSlider distanceSlider;
    private JTextField distanceField;
    private JTextField interpolationResult;
    private JTextField pathToFile;
    private RectPanel rectPanel;

    public void interpolateFromSlider(){
        double distance = distanceSlider.getValue()/100.0* Counter2.lineDoubleParams[12];
        distanceField.setText(Double.toString(distance));
        Counter2.distanceToPoint = Double.parseDouble(distanceField.getText());

        InterpolationResultCounter interpolationResultCounter = new InterpolationResultCounter();
        interpolationResultCounter.execute();
    }

    public void interpolateFromText(){
        double distance = Double.parseDouble(distanceField.getText());
        distanceSlider.setValue((int) (distance/ Counter2.lineDoubleParams[12]*100));
        Counter2.distanceToPoint = Double.parseDouble(distanceField.getText());

        InterpolationResultCounter interpolationResultCounter = new InterpolationResultCounter();
        interpolationResultCounter.execute();
    }

    public MainForm2() {
        Counter2.pathToFile = pathToFile.getText();
        Counter2.fillParams(1);
        rectPanel.repaint();

        lineNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Counter2.pathToFile = pathToFile.getText();
                Counter2.fillParams(Integer.parseInt(lineNumber.getText()));
                rectPanel.repaint();
                interpolateFromText();
            }
        });

        distanceField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Counter2.pathToFile = pathToFile.getText();
                interpolateFromText();
            }
        });

        distanceSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Counter2.pathToFile = pathToFile.getText();
                interpolateFromSlider();
            }
        });
    }

    //Вспомогательный класс для проведения вычислений для конкретной точки
    class InterpolationResultCounter extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
            double result = Counter2.interpolateQuadric();
            interpolationResult.setText(Double.toString(result));
            return null;
        }

        @Override
        public void done() {
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static class RectPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int maxLength = 187;
            int height = 30;
            int aColorBound = Counter2.aColorBound*maxLength/(Counter2.interpolationResultsLength*2);
            int bColorBound = Counter2.bColorBound*maxLength/(Counter2.interpolationResultsLength*2);
            int cColorBound = Counter2.cColorBound*maxLength/(Counter2.interpolationResultsLength*2);
            int dColorBound = Counter2.dColorBound*maxLength/(Counter2.interpolationResultsLength*2);

            g.setColor(Color.BLUE);
            g.fillRect(aColorBound, 0, maxLength - 2*aColorBound, height);

            g.setColor(new Color(173,216,230));
            g.fillRect(bColorBound, 0, aColorBound-bColorBound, height);
            g.fillRect(maxLength - aColorBound, 0, aColorBound - bColorBound, height);

            g.setColor(Color.YELLOW);
            g.fillRect(cColorBound, 0, bColorBound - cColorBound, height);
            g.fillRect(maxLength - bColorBound, 0, bColorBound - cColorBound, height);

            g.setColor(Color.ORANGE);
            g.fillRect(dColorBound, 0, cColorBound - dColorBound, height);
            g.fillRect(maxLength - cColorBound, 0, cColorBound - dColorBound, height);

            g.setColor(Color.RED);
            g.fillRect(0, 0, dColorBound, height);
            g.fillRect(maxLength - dColorBound, 0, maxLength - dColorBound, height);
        }
    }

}
