import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {
	Animation mainPanel;
	JPanel createButtonsPanel;
	JRadioButton henonMap;
	JRadioButton someMap;
	JRadioButton complexNumbersMap;

	public void startHenon() {
		mainPanel.setType("henon");
	}

	public void startSomeMap() {
		mainPanel.setType("someMap");
	}

	public void startComplexNumbers() {
		mainPanel.setType("complexNumbers");
	}

	private final ActionListener henonListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			startHenon();
		}
	};

	private final ActionListener someMapListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			startSomeMap();
		}
	};

	private final ActionListener complexNumbersListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			startComplexNumbers();
		}
	};

	public void createRadioButtons() {
		henonMap = new JRadioButton("Отображение Хенона");
		complexNumbersMap = new JRadioButton("Квадрат числа");
		someMap = new JRadioButton("Какое-то ещё отображение");

		complexNumbersMap.setSelected(true);

		ButtonGroup group = new ButtonGroup();
		group.add(henonMap);
		group.add(complexNumbersMap);
		group.add(someMap);

		henonMap.addActionListener(henonListener);
		complexNumbersMap.addActionListener(complexNumbersListener);
		someMap.addActionListener(someMapListener);
	}

	public JPanel createButtonsPanel() {
		JPanel panel = new JPanel(new GridLayout(0, 1));
		createRadioButtons();
		panel.add(henonMap);
		panel.add(complexNumbersMap);
		panel.add(someMap);
		return panel;
	}

	public Main() {
		super("Dynamic systems");
		mainPanel = new Animation();
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setForeground(Color.BLACK);
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
		add(createButtonsPanel(), BorderLayout.EAST);
		pack();

		setVisible(true);
	}

	public static void main(String s[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame application = new Main();
				application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
}