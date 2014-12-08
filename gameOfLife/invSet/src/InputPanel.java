
import javax.swing.*;
import javax.xml.transform.Source;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by dan on 11/17/14.
 */

public class InputPanel extends JPanel
        implements PropertyChangeListener {

    private double re;
    private double im;
    private int it;

    private JLabel reLabel;
    private JLabel imLabel;
    private JLabel itLabel;
    private JLabel label;

    private static String reString = "Input the real part of the parametric variable c: ";
    private static String imString = "Input the imaginary part of the parametric variable c: ";
    private static String itString = "Input the number of iterations: ";
    private static String mainText = "This program is created to compute and picture invariant sets for complex functions that can be expressed as f(x) = x ^ 2 + c";

    private JFormattedTextField reField;
    private JFormattedTextField imField;
    private JFormattedTextField itField;

    private NumberFormat reFormat;
    private NumberFormat imFormat;
    private NumberFormat itFormat;

    private JButton commitButton;

    public InputPanel() {
        super(new BorderLayout());

        label = new JLabel(mainText);
        reLabel = new JLabel(reString);
        imLabel = new JLabel(imString);
        itLabel = new JLabel(itString);

        reField = new JFormattedTextField (reFormat);
        reField.setValue(re);
        reField.setColumns(10);
        reField.addPropertyChangeListener(this);

        imField = new JFormattedTextField (imFormat);
        imField.setValue(im);
        imField.setColumns(10);
        imField.addPropertyChangeListener(this);

        itField = new JFormattedTextField (itFormat);
        itField.setValue(it);
        itField.setColumns(10);
        itField.addPropertyChangeListener(this);

        reLabel.setLabelFor(reField);
        imLabel.setLabelFor(imField);
        itLabel.setLabelFor(itField);

        commitButton = new JButton();
        commitButton.setText("Confirm");
        commitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller controller = new Controller(re, im, it);
            }
        });
        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(reLabel);
        labelPane.add(imLabel);
        labelPane.add(itLabel);

        JPanel fieldPane = new JPanel(new GridLayout(0, 1));
        fieldPane.add(reField);
        fieldPane.add(imField);
        fieldPane.add(itField);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(label, BorderLayout.BEFORE_FIRST_LINE);
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
        add(commitButton, BorderLayout.AFTER_LAST_LINE);
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getSource() == reField) {
            re = ((Number) reField.getValue()).doubleValue();
        }
        else if (e.getSource() == imField) {
            im = ((Number) imField.getValue()).doubleValue();
        }
        else if (e.getSource() == itField) {
            it = ((Number) itField.getValue()).intValue();
        }
    }
}