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
    private JTextField sixthCoilCurrent;
    private JTextField sixthCoilVerticalLoops;
    private JTextField sixthCoilHorizontalLoops;


    private JTextField pointX;
    private JButton countCoilDistributionInPointButton;
    private JButton countFieldDistributionButton;
    private JTextField pathToOutputFile;
    private JTextField thirdCoilHeight;
    private JTextField fourthCoilHeight;
    private JTextField fifthCoilHeight;
    private JTextField sixthCoilHeight;
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

        firstCoilZeroX.addKeyListener(new DoubleValidator());
        firstCoilZeroY.addKeyListener(new DoubleValidator());
        firstCoilZeroZ.addKeyListener(new DoubleValidator());
        firstCoilVectorX.addKeyListener(new DoubleValidator());
        firstCoilVectorY.addKeyListener(new DoubleValidator());
        firstCoilVectorZ.addKeyListener(new DoubleValidator());
        firstCoilRadius.addKeyListener(new UnsignedValidator());
        firstCoilHeight.addKeyListener(new UnsignedValidator());
        firstCoilCurrent.addKeyListener(new UnsignedValidator());
        firstCoilVerticalLoops.addKeyListener(new UnsignedValidator());
        firstCoilHorizontalLoops.addKeyListener(new UnsignedValidator());

        secondCoilZeroX.addKeyListener(new DoubleValidator());
        secondCoilZeroY.addKeyListener(new DoubleValidator());
        secondCoilZeroZ.addKeyListener(new DoubleValidator());
        secondCoilVectorX.addKeyListener(new DoubleValidator());
        secondCoilVectorY.addKeyListener(new DoubleValidator());
        secondCoilVectorZ.addKeyListener(new DoubleValidator());
        secondCoilRadius.addKeyListener(new UnsignedValidator());
        secondCoilHeight.addKeyListener(new UnsignedValidator());
        secondCoilCurrent.addKeyListener(new UnsignedValidator());
        secondCoilVerticalLoops.addKeyListener(new UnsignedValidator());
        secondCoilHorizontalLoops.addKeyListener(new UnsignedValidator());

        thirdCoilZeroX.addKeyListener(new DoubleValidator());
        thirdCoilZeroY.addKeyListener(new DoubleValidator());
        thirdCoilZeroZ.addKeyListener(new DoubleValidator());
        thirdCoilVectorX.addKeyListener(new DoubleValidator());
        thirdCoilVectorY.addKeyListener(new DoubleValidator());
        thirdCoilVectorZ.addKeyListener(new DoubleValidator());
        thirdCoilRadius.addKeyListener(new UnsignedValidator());
        thirdCoilCurrent.addKeyListener(new UnsignedValidator());
        thirdCoilVerticalLoops.addKeyListener(new UnsignedValidator());
        thirdCoilHorizontalLoops.addKeyListener(new UnsignedValidator());

        fourthCoilZeroX.addKeyListener(new DoubleValidator());
        fourthCoilZeroY.addKeyListener(new DoubleValidator());
        fourthCoilZeroZ.addKeyListener(new DoubleValidator());
        fourthCoilVectorX.addKeyListener(new DoubleValidator());
        fourthCoilVectorY.addKeyListener(new DoubleValidator());
        fourthCoilVectorZ.addKeyListener(new DoubleValidator());
        fourthCoilRadius.addKeyListener(new UnsignedValidator());
        fourthCoilCurrent.addKeyListener(new UnsignedValidator());
        fourthCoilVerticalLoops.addKeyListener(new UnsignedValidator());
        fourthCoilHorizontalLoops.addKeyListener(new UnsignedValidator());

        fifthCoilZeroX.addKeyListener(new DoubleValidator());
        fifthCoilZeroY.addKeyListener(new DoubleValidator());
        fifthCoilZeroZ.addKeyListener(new DoubleValidator());
        fifthCoilVectorX.addKeyListener(new DoubleValidator());
        fifthCoilVectorY.addKeyListener(new DoubleValidator());
        fifthCoilVectorZ.addKeyListener(new DoubleValidator());
        fifthCoilRadius.addKeyListener(new UnsignedValidator());
        fifthCoilCurrent.addKeyListener(new UnsignedValidator());
        fifthCoilVerticalLoops.addKeyListener(new UnsignedValidator());
        fifthCoilHorizontalLoops.addKeyListener(new UnsignedValidator());

        sixthCoilZeroX.addKeyListener(new DoubleValidator());
        sixthCoilZeroY.addKeyListener(new DoubleValidator());
        sixthCoilZeroZ.addKeyListener(new DoubleValidator());
        sixthCoilVectorX.addKeyListener(new DoubleValidator());
        sixthCoilVectorY.addKeyListener(new DoubleValidator());
        sixthCoilVectorZ.addKeyListener(new DoubleValidator());
        sixthCoilRadius.addKeyListener(new UnsignedValidator());
        sixthCoilCurrent.addKeyListener(new UnsignedValidator());
        sixthCoilVerticalLoops.addKeyListener(new UnsignedValidator());
        sixthCoilHorizontalLoops.addKeyListener(new UnsignedValidator());


        pointX.addKeyListener(new DoubleValidator());
        thirdCoilHeight.addKeyListener(new UnsignedValidator());
        fourthCoilHeight.addKeyListener(new UnsignedValidator());
        fifthCoilHeight.addKeyListener(new UnsignedValidator());
        sixthCoilHeight.addKeyListener(new UnsignedValidator());
        pointY.addKeyListener(new DoubleValidator());
        pointZ.addKeyListener(new DoubleValidator());

        firstCoilCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                firstCoilZeroX.setEnabled(firstCoilCheckBox.isSelected());
                firstCoilZeroY.setEnabled(firstCoilCheckBox.isSelected());
                firstCoilZeroZ.setEnabled(firstCoilCheckBox.isSelected());
                firstCoilVectorX.setEnabled(firstCoilCheckBox.isSelected());
                firstCoilVectorY.setEnabled(firstCoilCheckBox.isSelected());
                firstCoilVectorZ.setEnabled(firstCoilCheckBox.isSelected());
                firstCoilRadius.setEnabled(firstCoilCheckBox.isSelected());
                firstCoilHeight.setEnabled(firstCoilCheckBox.isSelected());
                firstCoilCurrent.setEnabled(firstCoilCheckBox.isSelected());
                firstCoilVerticalLoops.setEnabled(firstCoilCheckBox.isSelected());
                firstCoilHorizontalLoops.setEnabled(firstCoilCheckBox.isSelected());
            }
        });

        secondCoilCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                secondCoilZeroX.setEnabled(secondCoilCheckBox.isSelected());
                secondCoilZeroY.setEnabled(secondCoilCheckBox.isSelected());
                secondCoilZeroZ.setEnabled(secondCoilCheckBox.isSelected());
                secondCoilVectorX.setEnabled(secondCoilCheckBox.isSelected());
                secondCoilVectorY.setEnabled(secondCoilCheckBox.isSelected());
                secondCoilVectorZ.setEnabled(secondCoilCheckBox.isSelected());
                secondCoilRadius.setEnabled(secondCoilCheckBox.isSelected());
                secondCoilHeight.setEnabled(secondCoilCheckBox.isSelected());
                secondCoilCurrent.setEnabled(secondCoilCheckBox.isSelected());
                secondCoilVerticalLoops.setEnabled(secondCoilCheckBox.isSelected());
                secondCoilHorizontalLoops.setEnabled(secondCoilCheckBox.isSelected());
            }
        });

        thirdCoilCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                thirdCoilZeroX.setEnabled(thirdCoilCheckBox.isSelected());
                thirdCoilZeroY.setEnabled(thirdCoilCheckBox.isSelected());
                thirdCoilZeroZ.setEnabled(thirdCoilCheckBox.isSelected());
                thirdCoilVectorX.setEnabled(thirdCoilCheckBox.isSelected());
                thirdCoilVectorY.setEnabled(thirdCoilCheckBox.isSelected());
                thirdCoilVectorZ.setEnabled(thirdCoilCheckBox.isSelected());
                thirdCoilRadius.setEnabled(thirdCoilCheckBox.isSelected());
                thirdCoilHeight.setEnabled(thirdCoilCheckBox.isSelected());
                thirdCoilCurrent.setEnabled(thirdCoilCheckBox.isSelected());
                thirdCoilVerticalLoops.setEnabled(thirdCoilCheckBox.isSelected());
                thirdCoilHorizontalLoops.setEnabled(thirdCoilCheckBox.isSelected());
            }
        });

        fourthCoilCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fourthCoilZeroX.setEnabled(fourthCoilCheckBox.isSelected());
                fourthCoilZeroY.setEnabled(fourthCoilCheckBox.isSelected());
                fourthCoilZeroZ.setEnabled(fourthCoilCheckBox.isSelected());
                fourthCoilVectorX.setEnabled(fourthCoilCheckBox.isSelected());
                fourthCoilVectorY.setEnabled(fourthCoilCheckBox.isSelected());
                fourthCoilVectorZ.setEnabled(fourthCoilCheckBox.isSelected());
                fourthCoilRadius.setEnabled(fourthCoilCheckBox.isSelected());
                fourthCoilHeight.setEnabled(fourthCoilCheckBox.isSelected());
                fourthCoilCurrent.setEnabled(fourthCoilCheckBox.isSelected());
                fourthCoilVerticalLoops.setEnabled(fourthCoilCheckBox.isSelected());
                fourthCoilHorizontalLoops.setEnabled(fourthCoilCheckBox.isSelected());
            }
        });

        fifthCoilCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fifthCoilZeroX.setEnabled(fifthCoilCheckBox.isSelected());
                fifthCoilZeroY.setEnabled(fifthCoilCheckBox.isSelected());
                fifthCoilZeroZ.setEnabled(fifthCoilCheckBox.isSelected());
                fifthCoilVectorX.setEnabled(fifthCoilCheckBox.isSelected());
                fifthCoilVectorY.setEnabled(fifthCoilCheckBox.isSelected());
                fifthCoilVectorZ.setEnabled(fifthCoilCheckBox.isSelected());
                fifthCoilRadius.setEnabled(fifthCoilCheckBox.isSelected());
                fifthCoilHeight.setEnabled(fifthCoilCheckBox.isSelected());
                fifthCoilCurrent.setEnabled(fifthCoilCheckBox.isSelected());
                fifthCoilVerticalLoops.setEnabled(fifthCoilCheckBox.isSelected());
                fifthCoilHorizontalLoops.setEnabled(fifthCoilCheckBox.isSelected());
            }
        });

        sixthCoilCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sixthCoilZeroX.setEnabled(sixthCoilCheckBox.isSelected());
                sixthCoilZeroY.setEnabled(sixthCoilCheckBox.isSelected());
                sixthCoilZeroZ.setEnabled(sixthCoilCheckBox.isSelected());
                sixthCoilVectorX.setEnabled(sixthCoilCheckBox.isSelected());
                sixthCoilVectorY.setEnabled(sixthCoilCheckBox.isSelected());
                sixthCoilVectorZ.setEnabled(sixthCoilCheckBox.isSelected());
                sixthCoilRadius.setEnabled(sixthCoilCheckBox.isSelected());
                sixthCoilHeight.setEnabled(sixthCoilCheckBox.isSelected());
                sixthCoilCurrent.setEnabled(sixthCoilCheckBox.isSelected());
                sixthCoilVerticalLoops.setEnabled(sixthCoilCheckBox.isSelected());
                sixthCoilHorizontalLoops.setEnabled(sixthCoilCheckBox.isSelected());
            }
        });

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

    public void validateFilePath(){
        try {
            File file = new File(pathToOutputFile.getText());
            countFieldDistributionButton.setEnabled(file.exists());
        } catch (Exception exception){
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
        Counter.enabledCoils[0] = firstCoilCheckBox.isSelected();
        Counter.enabledCoils[1] = secondCoilCheckBox.isSelected();
        Counter.enabledCoils[2] = thirdCoilCheckBox.isSelected();
        Counter.enabledCoils[3] = fourthCoilCheckBox.isSelected();
        Counter.enabledCoils[4] = fifthCoilCheckBox.isSelected();
        Counter.enabledCoils[5] = sixthCoilCheckBox.isSelected();

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

        //Создание первой катушки
        Point3d firstCoilZero = new Point3d(Double.parseDouble(firstCoilZeroX.getText()) / 1000,
                Double.parseDouble(firstCoilZeroY.getText()) / 1000,
                Double.parseDouble(firstCoilZeroZ.getText()) / 1000);
        Vector3d firstCoilVector = new Vector3d(Double.parseDouble(firstCoilVectorX.getText()),
                Double.parseDouble(firstCoilVectorY.getText()),
                Double.parseDouble(firstCoilVectorZ.getText()));
        Counter.firstCoil = new Coil(firstCoilZero, firstCoilVector, Double.parseDouble(firstCoilRadius.getText()) / 1000,
                Double.parseDouble(firstCoilHeight.getText()) / 1000, Double.parseDouble(firstCoilCurrent.getText()),
                Double.parseDouble(firstCoilHorizontalLoops.getText()), Double.parseDouble(firstCoilVerticalLoops.getText()));

        //Создание второй катушки
        Point3d secondCoilZero = new Point3d(Double.parseDouble(secondCoilZeroX.getText()) / 1000,
                Double.parseDouble(secondCoilZeroY.getText()) / 1000,
                Double.parseDouble(secondCoilZeroZ.getText()) / 1000);
        Vector3d secondCoilVector = new Vector3d(Double.parseDouble(secondCoilVectorX.getText()),
                Double.parseDouble(secondCoilVectorY.getText()),
                Double.parseDouble(secondCoilVectorZ.getText()));
        Counter.secondCoil = new Coil(secondCoilZero, secondCoilVector, Double.parseDouble(secondCoilRadius.getText()) / 1000,
                Double.parseDouble(secondCoilHeight.getText()) / 1000, Double.parseDouble(secondCoilCurrent.getText()),
                Double.parseDouble(secondCoilHorizontalLoops.getText()), Double.parseDouble(secondCoilVerticalLoops.getText()));

        //Создание третьей катушки
        Point3d thirdCoilZero = new Point3d(Double.parseDouble(thirdCoilZeroX.getText()) / 1000,
                Double.parseDouble(thirdCoilZeroY.getText()) / 1000,
                Double.parseDouble(thirdCoilZeroZ.getText()) / 1000);
        Vector3d thirdCoilVector = new Vector3d(Double.parseDouble(thirdCoilVectorX.getText()),
                Double.parseDouble(thirdCoilVectorY.getText()),
                Double.parseDouble(thirdCoilVectorZ.getText()));
        Counter.thirdCoil = new Coil(thirdCoilZero, thirdCoilVector, Double.parseDouble(thirdCoilRadius.getText()) / 1000,
                Double.parseDouble(thirdCoilHeight.getText()) / 1000, Double.parseDouble(thirdCoilCurrent.getText()),
                Double.parseDouble(thirdCoilHorizontalLoops.getText()), Double.parseDouble(thirdCoilVerticalLoops.getText()));

        //Создание четвертой катушки
        Point3d fourthCoilZero = new Point3d(Double.parseDouble(fourthCoilZeroX.getText()) / 1000,
                Double.parseDouble(fourthCoilZeroY.getText()) / 1000,
                Double.parseDouble(fourthCoilZeroZ.getText()) / 1000);
        Vector3d fourthCoilVector = new Vector3d(Double.parseDouble(fourthCoilVectorX.getText()),
                Double.parseDouble(fourthCoilVectorY.getText()),
                Double.parseDouble(fourthCoilVectorZ.getText()));
        Counter.fourthCoil = new Coil(fourthCoilZero, fourthCoilVector, Double.parseDouble(fourthCoilRadius.getText()) / 1000,
                Double.parseDouble(fourthCoilHeight.getText()) / 1000, Double.parseDouble(fourthCoilCurrent.getText()),
                Double.parseDouble(fourthCoilHorizontalLoops.getText()), Double.parseDouble(fourthCoilVerticalLoops.getText()));

        //Создание пятой катушки
        Point3d fifthCoilZero = new Point3d(Double.parseDouble(fifthCoilZeroX.getText()) / 1000,
                Double.parseDouble(fifthCoilZeroY.getText()) / 1000,
                Double.parseDouble(fifthCoilZeroZ.getText()) / 1000);
        Vector3d fifthCoilVector = new Vector3d(Double.parseDouble(fifthCoilVectorX.getText()),
                Double.parseDouble(fifthCoilVectorY.getText()),
                Double.parseDouble(fifthCoilVectorZ.getText()));
        Counter.fifthCoil = new Coil(fifthCoilZero, fifthCoilVector, Double.parseDouble(fifthCoilRadius.getText()) / 1000,
                Double.parseDouble(fifthCoilHeight.getText()) / 1000, Double.parseDouble(fifthCoilCurrent.getText()),
                Double.parseDouble(fifthCoilHorizontalLoops.getText()), Double.parseDouble(fifthCoilVerticalLoops.getText()));

        //Создание шестой катушки
        Point3d sixthCoilZero = new Point3d(Double.parseDouble(sixthCoilZeroX.getText()) / 1000,
                Double.parseDouble(sixthCoilZeroY.getText()) / 1000,
                Double.parseDouble(sixthCoilZeroZ.getText()) / 1000);
        Vector3d sixthCoilVector = new Vector3d(Double.parseDouble(sixthCoilVectorX.getText()),
                Double.parseDouble(sixthCoilVectorY.getText()),
                Double.parseDouble(sixthCoilVectorZ.getText()));
        Counter.thirdCoil = new Coil(sixthCoilZero, sixthCoilVector, Double.parseDouble(sixthCoilRadius.getText()) / 1000,
                Double.parseDouble(sixthCoilHeight.getText()) / 1000, Double.parseDouble(sixthCoilCurrent.getText()),
                Double.parseDouble(sixthCoilHorizontalLoops.getText()), Double.parseDouble(sixthCoilVerticalLoops.getText()));

    }

    public void initPoint() {
        Counter.globalPoint = new Point3d(Double.parseDouble(pointX.getText()) / 1000, Double.parseDouble(pointY.getText()) / 1000, Double.parseDouble(pointZ.getText()) / 1000);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


}
