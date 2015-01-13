package validation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * User: BKudrin
 * Date: 13.01.2015
 * Time: 21:34
 */
public class UnsignedValidator implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        JTextField source = (JTextField) e.getSource();
        try  {
            Integer.parseUnsignedInt(source.getText());
            source.setForeground(Color.BLACK);
        } catch (NumberFormatException ex) {
            source.setForeground(Color.RED);
        }
    }
}


