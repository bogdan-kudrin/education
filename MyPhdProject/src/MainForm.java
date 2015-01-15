import validation.BaseValidator;
import validation.DoubleValidator;
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

    private JTextField[] areaParams = {
            areaSizeX, areaSizeY, areaSizeZ, stepX, stepY, stepZ
    };


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
    private JTextField pointY;
    private JTextField pointZ;
    private JTextField pointDistributionX;
    private JTextField pointDistributionY;
    private JTextField pointDistributionZ;
    private JTextField pathToOutputFile;
    private JButton countFieldDistributionButton;
    private JButton countCoilDistributionInPointButton;
    private JButton chooseFileButton;
    private JFileChooser fileChooser;
    private boolean hasErrors = false;

    public MainForm() {

        for (int i = 0; i < areaParams.length; i++) {
            areaParams[i].addKeyListener(new UnsignedValidator(countFieldDistributionButton));
        }

        for (int i = 0; i < BaseCounter.coils.length; i++) {
            for (int j = 0; j < coilDoubleFields[i].length; j++) {
                coilDoubleFields[i][j].addKeyListener(new DoubleValidator(countFieldDistributionButton));
            }
            for (int j = 0; j < coilUnsignedFields[i].length; j++) {
                coilUnsignedFields[i][j].addKeyListener(new UnsignedValidator(countFieldDistributionButton));
            }
        }

        pointX.addKeyListener(new DoubleValidator(countFieldDistributionButton));
        pointY.addKeyListener(new DoubleValidator(countFieldDistributionButton));
        pointZ.addKeyListener(new DoubleValidator(countFieldDistributionButton));

        for (int i = 0; i < BaseCounter.coils.length; i++) {
            final int localI = i;
            checkBoxes[i].addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    for (int j = 0; j < coilDoubleFields[localI].length; j++) {
                        coilDoubleFields[localI][j].setEnabled(checkBoxes[localI].isSelected());
                        coilDoubleFields[localI][j].setBackground(checkBoxes[localI].isSelected() ? Color.WHITE : mainPanel.getBackground());
                    }
                    for (int j = 0; j < coilUnsignedFields[localI].length; j++) {
                        coilUnsignedFields[localI][j].setEnabled(checkBoxes[localI].isSelected());
                        coilUnsignedFields[localI][j].setBackground(checkBoxes[localI].isSelected() ? Color.WHITE : mainPanel.getBackground());
                    }
                }
            });
        }

        countFieldDistributionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateErrors();
                validateFilePath();
                if (pathToOutputFile.getText().isEmpty()){
                    pathToOutputFile.setBackground(Color.RED);
                }
                if (!hasErrors) {
                    initCoils();
                    BaseCounter.pathToOutputFile = pathToOutputFile.getText();
                    SwingBaseCounter swingBaseCounter = new SwingBaseCounter();
                    swingBaseCounter.execute();
                }
            }
        });

        countCoilDistributionInPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initCoils();
                initPoint();
                countCoilDistributionInPointButton.setEnabled(false);
                mainPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                PointCounter.countCoilDistributionInPoint();
                pointDistributionX.setText(Double.toString(PointCounter.pointDistribution.getX()).substring(0, 10));
                pointDistributionY.setText(Double.toString(PointCounter.pointDistribution.getY()).substring(0, 10));
                pointDistributionZ.setText(Double.toString(PointCounter.pointDistribution.getZ()).substring(0, 10));
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

    }

    public void validateErrors() {
        for (int i = 0; i < areaParams.length; i++) {
            if (((BaseValidator) areaParams[i].getKeyListeners()[0]).hasError) {
                areaParams[i].setBackground(Color.RED);
                hasErrors = true;
            }
        }
        for (int i = 0; i < BaseCounter.coils.length; i++) {
            if (checkBoxes[i].isSelected()) {
                for (int j = 0; j < coilDoubleFields[i].length; j++) {
                    if (((BaseValidator) coilDoubleFields[i][j].getKeyListeners()[0]).hasError) {
                        coilDoubleFields[i][j].setBackground(Color.RED);
                        hasErrors = true;
                    }
                }
                for (int j = 0; j < coilUnsignedFields[i].length; j++) {
                    if (((BaseValidator) coilUnsignedFields[i][j].getKeyListeners()[0]).hasError) {
                        coilUnsignedFields[i][j].setBackground(Color.RED);
                        hasErrors = true;
                    }
                }
            }
        }
    }

    public void validateFilePath() {
        try {
            File file = new File(pathToOutputFile.getText());
            pathToOutputFile.setForeground(file.exists() ? Color.BLACK : Color.RED);
            pathToOutputFile.setBackground(Color.WHITE);
        } catch (Exception exception) {
            pathToOutputFile.setForeground(Color.RED);
        }
    }


    //Вспомогательный класс для проведения вычислений в основном потоке
    class SwingBaseCounter extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
            countFieldDistributionButton.setEnabled(false);
            mainPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            BaseCounter.countAndWriteFieldDistribution();
            BaseCounter.interpolateAndWriteFieldDistribution();
            BaseCounter.interpolateQuadricAndWriteFieldDistribution();
            return null;
        }

        @Override
        public void done() {
            mainPanel.setCursor(null);
            countFieldDistributionButton.setEnabled(true);
        }
    }

    public void initCoils() {
        for (int i = 0; i < BaseCounter.enabledCoils.length; i++) {
            BaseCounter.enabledCoils[i] = checkBoxes[i].isSelected();
        }

        //Установка размеров области
        BaseCounter.stepX = Integer.parseInt(stepX.getText());
        BaseCounter.stepY = Integer.parseInt(stepY.getText());
        BaseCounter.stepZ = Integer.parseInt(stepZ.getText());

        BaseCounter.areaSizeX = Integer.parseInt(areaSizeX.getText()) / BaseCounter.stepX;
        BaseCounter.areaSizeY = Integer.parseInt(areaSizeY.getText()) / BaseCounter.stepY;
        BaseCounter.areaSizeZ = Integer.parseInt(areaSizeZ.getText()) / BaseCounter.stepZ;

        BaseCounter.scalefactorX = 1000.0 / BaseCounter.stepX;
        BaseCounter.scalefactorY = 1000.0 / BaseCounter.stepY;
        BaseCounter.scalefactorZ = 1000.0 / BaseCounter.stepZ;

        Point3d[] coilZeros = new Point3d[BaseCounter.enabledCoils.length];
        Vector3d[] coilVectors = new Vector3d[BaseCounter.enabledCoils.length];

        for (int i = 0; i < BaseCounter.coils.length; i++) {
            if (BaseCounter.enabledCoils[i]) {
                coilZeros[i] = new Point3d(Double.parseDouble(coilDoubleFields[i][0].getText()) / 1000,
                        Double.parseDouble(coilDoubleFields[i][1].getText()) / 1000,
                        Double.parseDouble(coilDoubleFields[i][2].getText()) / 1000);
                coilVectors[i] = new Vector3d(Double.parseDouble(coilDoubleFields[i][3].getText()),
                        Double.parseDouble(coilDoubleFields[i][4].getText()),
                        Double.parseDouble(coilDoubleFields[i][5].getText()));
                BaseCounter.coils[i] = new Coil(coilZeros[i], coilVectors[i], Double.parseDouble(coilUnsignedFields[i][0].getText()) / 1000,
                        Double.parseDouble(coilUnsignedFields[i][1].getText()) / 1000, Double.parseDouble(coilUnsignedFields[i][2].getText()),
                        Double.parseDouble(coilUnsignedFields[i][3].getText()), Double.parseDouble(coilUnsignedFields[i][4].getText()));

            }
        }
    }

    public void initPoint() {
        PointCounter.globalPoint = new Point3d(Double.parseDouble(pointX.getText()) / 1000, Double.parseDouble(pointY.getText()) / 1000, Double.parseDouble(pointZ.getText()) / 1000);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


}
