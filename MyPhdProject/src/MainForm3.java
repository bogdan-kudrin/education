import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

/**
 * User: BKudrin
 * Date: 24.02.2015
 * Time: 19:14
 */
public class MainForm3 {
    private JPanel mainPanel;
    private JButton chooseInputFileButton;
    private JTextField inputFilePath;
    private JButton chooseOutputFileButton;
    private JTextField outputFilePath;
    private JButton interpolateButton;
    private JRadioButton linearRadioButton;
    private JRadioButton quadricRadioButton;
    private JFileChooser fileChooser;
    private boolean hasErrors = false;
    VtkParser vtkParser = new VtkParser();

    public MainForm3() {
        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                ".vtk", "vtk");
        fileChooser.setFileFilter(filter);

        chooseInputFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(chooseInputFileButton);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    inputFilePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    validateInputFilePath();
                }
            }
        });

        inputFilePath.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateInputFilePath();
            }
        });

        chooseOutputFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(chooseOutputFileButton);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    outputFilePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    validateOutputFilePath();
                }
            }
        });

        outputFilePath.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateOutputFilePath();
            }
        });

        interpolateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateInputFilePath();
                validateOutputFilePath();
                if (!hasErrors) {
                    SwingCounter swingCounter = new SwingCounter();
                    swingCounter.execute();
                }
            }
        });

        linearRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quadricRadioButton.setSelected(!linearRadioButton.isSelected());
            }
        });

        quadricRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                linearRadioButton.setSelected(!linearRadioButton.isSelected());
            }
        });
    }

    //Вспомогательный класс для проведения вычислений в основном потоке
    class SwingCounter extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
            interpolateButton.setEnabled(false);
            mainPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            vtkParser.parseFile(inputFilePath.getText());
            vtkParser.baseCounter.interpolateAndWriteFieldDistribution(outputFilePath.getText());
            return null;
        }

        @Override
        public void done() {
            mainPanel.setCursor(null);
            interpolateButton.setEnabled(true);
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void validateInputFilePath() {
        try {
            File file = new File(inputFilePath.getText());
            hasErrors = !file.exists();
            inputFilePath.setForeground(file.exists() ? Color.BLACK : Color.RED);
            inputFilePath.setBackground(Color.WHITE);
        } catch (Exception exception) {
            inputFilePath.setForeground(Color.RED);
            hasErrors = true;
        }
    }

    public void validateOutputFilePath() {
        try {
            File file = new File(outputFilePath.getText());
            hasErrors = !file.exists();
            outputFilePath.setForeground(file.exists() ? Color.BLACK : Color.RED);
            outputFilePath.setBackground(Color.WHITE);
        } catch (Exception exception) {
            outputFilePath.setForeground(Color.RED);
            hasErrors = true;
        }
    }


}
