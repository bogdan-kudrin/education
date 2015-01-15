package validation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * User: BKudrin
 * Date: 13.01.2015
 * Time: 21:34
 */
public class UnsignedValidator extends BaseValidator {

    public UnsignedValidator(JButton button) {
        super(button);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        JTextField source = (JTextField) e.getSource();
        source.setBackground(Color.WHITE);
        try  {
            Integer.parseUnsignedInt(source.getText());
            source.setForeground(Color.BLACK);
            this.hasError = false;
            this.button.setEnabled(true);
        } catch (NumberFormatException ex) {
            source.setForeground(Color.RED);
            this.hasError = true;
        }
    }
}


