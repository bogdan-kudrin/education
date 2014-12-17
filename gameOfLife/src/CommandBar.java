import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;


public class CommandBar extends JPanel {

    private Button startButton;

    public CommandBar() {
        setLayout(new BorderLayout(10, 10));

        startButton = new Button("Start/Stop");
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

            }
        });
        this.add(startButton);

    }
}
