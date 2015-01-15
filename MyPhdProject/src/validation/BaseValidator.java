package validation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * User: BKudrin
 * Date: 15.01.2015
 * Time: 0:15
 */
public class BaseValidator extends KeyAdapter {
    public BaseValidator(JButton button) {
        this.button = button;
    }

    public boolean hasError = false;
    public JButton button;

}
