import validation.DoubleValidator;
import validation.IntegerValidator;
import validation.UnsignedValidator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * User: BKudrin
 * Date: 25.03.13
 * Time: 15:25
 */
public class MainForm {
    private JPanel mainPanel;

    //Общие параметры
    private JTextField areaSizeX;
    private JTextField areaSizeY;
    private JTextField areaSizeZ;
    private JTextField stepX;
    private JTextField stepY;
    private JTextField stepZ;

    //Параметры первой катушки
    private JCheckBox firstCoilCheckBox;
    private JTextField firstCoilZeroX;
    private JTextField firstCoilZeroY;
    private JTextField firstCoilZeroZ;
    private JTextField firstCoilVectorX;
    private JTextField firstCoilVectorY;
    private JTextField firstCoilVectorZ;
    private JTextField firstCoilRadius;
    private JTextField firstCoilHeight;
    private JTextField firstCoilCurrent;
    private JTextField firstCoilVerticalLoops;
    private JTextField firstCoilHorizontalLoops;

    //Параметры второй катушки
    private JCheckBox secondCoilCheckBox;
    private JTextField secondCoilZeroX;
    private JTextField secondCoilZeroY;
    private JTextField secondCoilZeroZ;
    private JTextField secondCoilVectorX;
    private JTextField secondCoilVectorY;
    private JTextField secondCoilVectorZ;
    private JTextField secondCoilRadius;
    private JTextField secondCoilHeight;
    private JTextField secondCoilCurrent;
    private JTextField secondCoilVerticalLoops;
    private JTextField secondCoilHorizontalLoops;

    //Параметры третьей катушки
    private JCheckBox thirdCoilCheckBox;
    private JTextField thirdCoilZeroX;
    private JTextField thirdCoilZeroY;
    private JTextField thirdCoilZeroZ;
    private JTextField thirdCoilVectorX;
    private JTextField thirdCoilVectorY;
    private JTextField thirdCoilVectorZ;
    private JTextField thirdCoilRadius;
    private JTextField thirdCoilHeight;
    private JTextField thirdCoilCurrent;
    private JTextField thirdCoilVerticalLoops;
    private JTextField thirdCoilHorizontalLoops;

    //Параметры четвертой катушки
    private JCheckBox fourthCoilCheckBox;
    private JTextField fourthCoilZeroX;
    private JTextField fourthCoilZeroY;
    private JTextField fourthCoilZeroZ;
    private JTextField fourthCoilVectorX;
    private JTextField fourthCoilVectorY;
    private JTextField fourthCoilVectorZ;
    private JTextField fourthCoilRadius;
    private JTextField fourthCoilHeight;
    private JTextField fourthCoilCurrent;
    private JTextField fourthCoilVerticalLoops;
    private JTextField fourthCoilHorizontalLoops;


    //Параметры пятой катушки
    private JCheckBox fifthCoilCheckBox;
    private JTextField fifthCoilZeroX;
    private JTextField fifthCoilZeroY;
    private JTextField fifthCoilZeroZ;
    private JTextField fifthCoilVectorX;
    private JTextField fifthCoilVectorY;
    private JTextField fifthCoilVectorZ;
    private JTextField fifthCoilRadius;
    private JTextField fifthCoilHeight;
    private JTextField fifthCoilCurrent;
    private JTextField fifthCoilVerticalLoops;
    private JTextField fifthCoilHorizontalLoops;

    //Параметры шестой катушки
    private JCheckBox sixthCoilCheckBox;
    private JTextField sixthCoilZeroX;
    private JTextField sixthCoilZeroY;
    private JTextField sixthCoilZeroZ;
    private JTextField sixthCoilVectorX;
    private JTextField sixthCoilVectorY;
    private JTextField sixthCoilVectorZ;
    private JTextField sixthCoilRadius;
    private JTextField sixthCoilHeight;
    private JTextField sixthCoilCurrent;
    private JTextField sixthCoilVerticalLoops;
    private JTextField sixthCoilHorizontalLoops;

    private JCheckBox[] checkBoxes = {
            firstCoilCheckBox, secondCoilCheckBox, thirdCoilCheckBox, fourthCoilCheckBox, fifthCoilCheckBox, sixthCoilCheckBox
    };

    private JTextField[][] coilDoubleFields = {
            {firstCoilZeroX, firstCoilZeroY, firstCoilZeroZ, firstCoilVectorX, firstCoilVectorY, firstCoilVectorZ},
            {secondCoilZeroX, secondCoilZeroY, secondCoilZeroZ, secondCoilVectorX, secondCoilVectorY, secondCoilVectorZ},
            {thirdCoilZeroX, thirdCoilZeroY, thirdCoilZeroZ, thirdCoilVectorX, thirdCoilVectorY, thirdCoilVectorZ},
            {fourthCoilZeroX, fourthCoilZeroY, fourthCoilZeroZ, fourthCoilVectorX, fourthCoilVectorY, fourthCoilVectorZ},
            {fifthCoilZeroX, fifthCoilZeroY, fifthCoilZeroZ, fifthCoilVectorX, fifthCoilVectorY, fifthCoilVectorZ},
            {sixthCoilZeroX, sixthCoilZeroY, sixthCoilZeroZ, sixthCoilVectorX, sixthCoilVectorY, sixthCoilVectorZ}
    };

    private JTextField[][] coilUnsignedFields = {
            {firstCoilRadius, firstCoilHeight, firstCoilCurrent, firstCoilVerticalLoops, firstCoilHorizontalLoops},
            {secondCoilRadius, secondCoilHeight, secondCoilCurrent, secondCoilVerticalLoops, secondCoilHorizontalLoops},
            {thirdCoilRadius, thirdCoilHeight, thirdCoilCurrent, thirdCoilVerticalLoops, thirdCoilHorizontalLoops},
            {fourthCoilRadius, fourthCoilHeight, fourthCoilCurrent, fourthCoilVerticalLoops, fourthCoilHorizontalLoops},
            {fifthCoilRadius, fifthCoilHeight, fifthCoilCurrent, fifthCoilVerticalLoops, fifthCoilHorizontalLoops},
            {sixthCoilRadius, sixthCoilHeight, sixthCoilCurrent, sixthCoilVerticalLoops, sixthCoilHorizontalLoops}
    };


    private JTextField pointX;
    private JButton countCoilDistributionInPointButton;
    private JButton countFieldDistributionButton;
    private JTextField pathToOutputFile;
    private JTextField pointY;
    private JTextField pointZ;
    private JTextField pointDistributionX;
    private JTextField pointDistributionY;
    private JTextField pointDistributionZ;
    private JButton chooseFileButton;
    private JFileChooser fileChooser;

    public MainForm() {

        areaSizeX.addKeyListener(new UnsignedValidator());
        areaSizeY.addKeyListener(new UnsignedValidator());
        areaSizeZ.addKeyListener(new UnsignedValidator());
        stepX.addKeyListener(new UnsignedValidator());
        stepY.addKeyListener(new UnsignedValidator());
        stepZ.addKeyListener(new UnsignedValidator());

        for (int i = 0; i < coilDoubleFields.length; i++) {
            for (int j = 0; j < coilDoubleFields[i].length; j++) {
                coilDoubleFields[i][j].addKeyListener(new DoubleValidator());
            }
            for (int j = 0; j < coilUnsignedFields[i].length; j++) {
                coilUnsignedFields[i][j].addKeyListener(new UnsignedValidator());
            }
        }

        pointX.addKeyListener(new DoubleValidator());
        pointY.addKeyListener(new DoubleValidator());
        pointZ.addKeyListener(new DoubleValidator());

        for (int i = 0; i < checkBoxes.length; i++) {
            final int localI = i;
            checkBoxes[i].addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    for (int j = 0; j < coilDoubleFields[localI].length; j++) {
                        coilDoubleFields[localI][j].setEnabled(checkBoxes[localI].isSelected());
                    }
                    for (int j = 0; j < coilUnsignedFields[localI].length; j++) {
                        coilUnsignedFields[localI][j].setEnabled(checkBoxes[localI].isSelected());
                    }
                }
            });
        }

        countFieldDistributionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                initCoils();
                Counter.pathToOutputFile = pathToOutputFile.getText();

                SwingCounter swingCounter = new SwingCounter();
                swingCounter.execute();
            }


        });

        countCoilDistributionInPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initCoils();
                initPoint();
                countCoilDistributionInPointButton.setEnabled(false);
                mainPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Counter.countCoilDistributionInPoint();
                pointDistributionX.setText(Double.toString(Counter.pointDistribution.getX()).substring(0, 10));
                pointDistributionY.setText(Double.toString(Counter.pointDistribution.getY()).substring(0, 10));
                pointDistributionZ.setText(Double.toString(Counter.pointDistribution.getZ()).substring(0, 10));
                mainPanel.setCursor(null);
                countCoilDistributionInPointButton.setEnabled(true);
            }
        });

        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                ".vtk", "vtk");
        fileChooser.setFileFilter(filter);

        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(chooseFileButton);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    pathToOutputFile.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    validateFilePath();
                }
            }
        });

        pathToOutputFile.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateFilePath();
            }
        });

        validateFilePath();
    }

    public void validateFilePath() {
        try {
            File file = new File(pathToOutputFile.getText());
            countFieldDistributionButton.setEnabled(file.exists());
        } catch (Exception exception) {
            countFieldDistributionButton.setEnabled(false);
        }
    }


    //Вспомогательный класс для проведения вычислений в основном потоке
    class SwingCounter extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
            countFieldDistributionButton.setEnabled(false);
            mainPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Counter.countAndWriteFieldDistribution();
            Counter.interpolateAndWriteFieldDistribution();
            Counter.interpolateQuadricAndWriteFieldDistribution();
            return null;
        }

        @Override
        public void done() {
            mainPanel.setCursor(null);
            countFieldDistributionButton.setEnabled(true);
        }
    }

    public void initCoils() {
        for (int i = 0; i < Counter.enabledCoils.length; i++) {
            Counter.enabledCoils[i] = checkBoxes[i].isSelected();
        }

        //Установка размеров области
        Counter.stepX = Integer.parseInt(stepX.getText());
        Counter.stepY = Integer.parseInt(stepY.getText());
        Counter.stepZ = Integer.parseInt(stepZ.getText());

        Counter.areaSizeX = Integer.parseInt(areaSizeX.getText()) / Counter.stepX;
        Counter.areaSizeY = Integer.parseInt(areaSizeY.getText()) / Counter.stepY;
        Counter.areaSizeZ = Integer.parseInt(areaSizeZ.getText()) / Counter.stepZ;

        Counter.scalefactorX = 1000.0 / Counter.stepX;
        Counter.scalefactorY = 1000.0 / Counter.stepY;
        Counter.scalefactorZ = 1000.0 / Counter.stepZ;

        for (int i = 0; i < coilDoubleFields.length; i++) {
            for (int j = 0; j < coilDoubleFields[i].length; j++) {
                coilDoubleFields[i][j].addKeyListener(new DoubleValidator());
            }
            for (int j = 0; j < coilUnsignedFields[i].length; j++) {
                coilUnsignedFields[i][j].addKeyListener(new UnsignedValidator());
            }
        }

        Point3d[] coilZeros = new Point3d[Counter.enabledCoils.length];
        Vector3d[] coilVectors = new Vector3d[Counter.enabledCoils.length];

        for (int i = 0; i < Counter.coils.length; i++) {
            if (Counter.enabledCoils[i]){
                coilZeros[i] = new Point3d(Double.parseDouble(coilDoubleFields[i][0].getText()) / 1000,
                        Double.parseDouble(coilDoubleFields[i][1].getText()) / 1000,
                        Double.parseDouble(coilDoubleFields[i][2].getText()) / 1000);
                coilVectors[i] = new Vector3d(Double.parseDouble(coilDoubleFields[i][3].getText()),
                        Double.parseDouble(coilDoubleFields[i][4].getText()),
                        Double.parseDouble(coilDoubleFields[i][5].getText()));
                Counter.coils[i] = new Coil(coilZeros[i], coilVectors[i], Double.parseDouble(coilUnsignedFields[i][0].getText()) / 1000,
                        Double.parseDouble(coilUnsignedFields[i][1].getText()) / 1000, Double.parseDouble(coilUnsignedFields[i][2].getText()),
                        Double.parseDouble(coilUnsignedFields[i][3].getText()), Double.parseDouble(coilUnsignedFields[i][4].getText()));

            }
        }
    }

    public void initPoint() {
        Counter.globalPoint = new Point3d(Double.parseDouble(pointX.getText()) / 1000, Double.parseDouble(pointY.getText()) / 1000, Double.parseDouble(pointZ.getText()) / 1000);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


}
