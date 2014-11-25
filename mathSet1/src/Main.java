import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {
	Animation mainPanel;
	JPanel createButtonsPanel;
	JRadioButton henonMap;
	JRadioButton someMap;
	JRadioButton complexNumbersMap;
	JTextField aTextField;
	JTextField bTextField;
	JTextField widthTextField;
	JTextField heightTextField;
	JTextField iterateTextField;

	public void startHenon() {
		mainPanel.setType("henon");
	}

	public void startSomeMap() {
		mainPanel.setType("someMap");
	}

	public void startComplexNumbers() {
		mainPanel.setType("complexNumbers");
	}
	
	private final ActionListener textFieldListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			double a = Double.parseDouble(aTextField.getText());
			double b = Double.parseDouble(bTextField.getText());
			int width = Integer.parseInt(widthTextField.getText());
			int height = Integer.parseInt(heightTextField.getText());
			mainPanel.setProperties(a, b, width, height);
			int iterate = Integer.parseInt(iterateTextField.getText());
			mainPanel.setIterateSteps(iterate);
		}
	};

	private final ActionListener henonListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			aTextField.setText("2");
			bTextField.setText("0.3");
			widthTextField.setText(Integer.toString(Model.defaultWidth));
			heightTextField.setText(Integer.toString(Model.defaultHeight));
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
			aTextField.setText("0.4");
			bTextField.setText("0.4");
			widthTextField.setText(Integer.toString(Model.defaultWidth));
			heightTextField.setText(Integer.toString(Model.defaultHeight));
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
		JPanel upperPanel = new JPanel(new BorderLayout());
		JPanel panel = new JPanel();
		JPanel radioButtonsPanel = new JPanel();
		radioButtonsPanel.setLayout(new BoxLayout(radioButtonsPanel, BoxLayout.PAGE_AXIS));
		panel.setLayout(new GridLayout(0, 1));
		
		createRadioButtons();
		radioButtonsPanel.add(henonMap, Component.LEFT_ALIGNMENT);
		radioButtonsPanel.add(complexNumbersMap, Component.LEFT_ALIGNMENT);
		radioButtonsPanel.add(someMap, Component.LEFT_ALIGNMENT);
		panel.add(radioButtonsPanel);
		
		GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setHgap(5);
		gridLayout.setVgap(5);
		JPanel textFieldPanel = new JPanel(gridLayout);
		
		JLabel aLabel = new JLabel("Введите значение a: ");
		JLabel bLabel = new JLabel("Введите значение b: ");
		JLabel widthLabel = new JLabel("Ширина поля: ");
		JLabel heightLabel = new JLabel("Высота поля: ");
		JLabel iterateLabel = new JLabel("Скорость: ");
		aTextField = new JTextField(10);
		bTextField = new JTextField(10);
		widthTextField = new JTextField(10);
		heightTextField = new JTextField(10);
		iterateTextField = new JTextField(10);
		JButton button = new JButton("Обновить");
		
		aTextField.addActionListener(textFieldListener);
		bTextField.addActionListener(textFieldListener);
		widthTextField.addActionListener(textFieldListener);
		heightTextField.addActionListener(textFieldListener);
		button.addActionListener(textFieldListener);
		iterateTextField.addActionListener(textFieldListener);
		aTextField.setText("0.4");
		bTextField.setText("0.4");
		widthTextField.setText(Integer.toString(Model.defaultWidth));
		heightTextField.setText(Integer.toString(Model.defaultHeight));
		iterateTextField.setText("1");
		
		
		textFieldPanel.add(aLabel);
		textFieldPanel.add(aTextField);
		textFieldPanel.add(bLabel);
		textFieldPanel.add(bTextField);
		textFieldPanel.add(widthLabel);
		textFieldPanel.add(widthTextField);
		textFieldPanel.add(heightLabel);
		textFieldPanel.add(heightTextField);
		textFieldPanel.add(iterateLabel);
		textFieldPanel.add(iterateTextField);
		panel.add(textFieldPanel);
		panel.add(button);
		upperPanel.add(panel, BorderLayout.NORTH);
		return upperPanel;
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