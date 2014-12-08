
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

    private int height;
    private int width;

    private JLabel heightLabel;
    private JLabel widthLabel;

    private static String heightString = "Height: ";
    private static String widthString = "Width: ";

    private JFormattedTextField heightField;
    private JFormattedTextField widthField;

    private NumberFormat heightFormat;
    private NumberFormat widthFormat;

    private JButton commitButton;

    public InputPanel() {
        super(new BorderLayout());

        heightLabel = new JLabel(heightString);
        widthLabel = new JLabel(widthString);

        heightField = new JFormattedTextField (heightFormat);
        heightField.setValue(height);
        heightField.setColumns(10);
        heightField.addPropertyChangeListener(this);

        widthField = new JFormattedTextField (widthFormat);
        widthField.setValue(width);
        widthField.setColumns(10);
        widthField.addPropertyChangeListener(this);

        heightLabel.setLabelFor(heightField);
        widthLabel.setLabelFor(widthField);

        commitButton = new JButton();
        commitButton.setText("Confirm");
        commitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.makeField(height, width);
            }
        });
        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(heightLabel);
        labelPane.add(widthLabel);

        JPanel fieldPane = new JPanel(new GridLayout(0, 1));
        fieldPane.add(heightField);
        fieldPane.add(widthField);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
        add(commitButton, BorderLayout.AFTER_LAST_LINE);
    }

    public void changeSize(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getSource() == heightField) {
            height = ((Number) heightField.getValue()).intValue();
        }
        else {
            width = ((Number) widthField.getValue()).intValue();
        }
    }
}
