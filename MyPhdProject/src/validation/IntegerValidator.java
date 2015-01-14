package validation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

/**
 * User: BKudrin
 * Date: 13.01.2015
 * Time: 21:34
 */
public class IntegerValidator extends KeyAdapter {

    @Override
    public void keyReleased(KeyEvent e) {
        JTextField source = (JTextField) e.getSource();
        try  {
            Integer.parseInt(source.getText());
            source.setForeground(Color.BLACK);
        } catch (NumberFormatException ex) {
            source.setForeground(Color.RED);
        }
    }
}


