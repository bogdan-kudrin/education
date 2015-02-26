import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Main {

    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        final JFrame frame = new JFrame("MainForm");
        final JTabbedPane tabbedPane = new JTabbedPane();
        JPanel countPanel = new MainForm().getMainPanel();
        JPanel interpolationPanel = new MainForm2().getMainPanel();
        JPanel interpolateCountedValuesPanel = new MainForm3().getMainPanel();
        tabbedPane.addTab("Расчет", null, countPanel,
                "Интерполяция по результатам эксперимента");

        tabbedPane.addTab("Интерполяция по результатам эксперимента", null, interpolationPanel,
                "Интерполяция по результатам эксперимента");

        tabbedPane.addTab("Интерполяция рассчитанных данных", null, interpolateCountedValuesPanel,
                "Интерполяция рассчитанных данных");

        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Component component = tabbedPane.getSelectedComponent();
                frame.setSize(component.getPreferredSize().width + 100, component.getPreferredSize().height + 150);
            }
        });



        frame.setContentPane(tabbedPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

       /* VtkParser vtkParser = new VtkParser();
        vtkParser.parseFile();
        vtkParser.writeParsedFieldDistribution();*/

    }

}
