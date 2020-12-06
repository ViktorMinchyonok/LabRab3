package bsu.rfe.java.group9.lab3.Minchyonok.var2A;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private static final int WIDTH = 700;
	private static final int HEIGHT = 500;

	// Массив коэффициентов многочлена 
	private Double[] coefficients;

	// Объект диалогового окна для выбора файлов
	// Компонент не создаѐтся изначально, т.к. может и не понадобиться // пользователю если тот не собирается сохранять данные в файл 
	private JFileChooser fileChooser = null;

	// Элементы меню вынесены в поля данных класса, так как ими необходимо // манипулировать из разных мест 
	private JMenuItem saveToTextMenuItem;
	private JMenuItem saveToGraphicsMenuItem;
	private JMenuItem searchValueMenuItem;

	// Поля ввода для считывания значений переменных
	private JTextField textFieldFrom;
	private JTextField textFieldTo;
	private JTextField textFieldStep;
	private Box hBoxResult;

	// Визуализатор ячеек таблицы
	private GornerTableCellRenderer renderer = new GornerTableCellRenderer(); 

	// Модель данных с результатами вычислений
	private GornerTableModel data;

	@SuppressWarnings("deprecation")
	public MainFrame(Double[] coefficients) {
		super("Табулирование многочлена по схеме Горнера");
		this.coefficients = coefficients;
		setSize(WIDTH, HEIGHT);
		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);
		
		// Настройка меню
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("Файл");
		menuBar.add(fileMenu);
		JMenu tableMenu = new JMenu("Таблица");
		menuBar.add(tableMenu);
		JMenu helpMenu = new JMenu("Справка");
		menuBar.add(helpMenu);

		// Файл
		Action saveToTextAction = new SaveToTextAction(this, "Сохранить в текстовый файл");
		saveToTextMenuItem = fileMenu.add(saveToTextAction);
		saveToTextMenuItem.setEnabled(false);
		Action saveToGraphicsAction = new SaveToGraphicsAction(this, "Сохранить данные для построения графика");
		saveToGraphicsMenuItem = fileMenu.add(saveToGraphicsAction);
		saveToGraphicsMenuItem.setEnabled(false);

		// Справка
		Action afftarAction = new AboutAction(this, "О программе");
		searchValueMenuItem = helpMenu.add(afftarAction);
		searchValueMenuItem.setEnabled(true);

		// Таблица
		JLabel labelInterFrom = new JLabel("X   от:");
		textFieldFrom = new JTextField("0.0", 10);
		textFieldFrom.setMaximumSize(textFieldFrom.getPreferredSize());
		JLabel labelInterTo = new JLabel("по:");
		textFieldTo = new JTextField("10", 10);
		textFieldTo.setMaximumSize(textFieldTo.getPreferredSize());
		JLabel labelInterStep = new JLabel("шаг:");
		textFieldStep = new JTextField("0.1", 10);
		textFieldStep.setMaximumSize(this.textFieldStep.getPreferredSize());

		// Поля ввода параметров
		Box hBoxField = Box.createHorizontalBox();
		hBoxField.setBorder(BorderFactory.createBevelBorder(1));
		hBoxField.add(Box.createHorizontalGlue());
		hBoxField.add(labelInterFrom);
		hBoxField.add(Box.createHorizontalStrut(10));
		hBoxField.add(textFieldFrom);
		hBoxField.add(Box.createHorizontalStrut(20));
		hBoxField.add(labelInterTo);
		hBoxField.add(Box.createHorizontalStrut(10));
		hBoxField.add(textFieldTo);
		hBoxField.add(Box.createHorizontalStrut(20));
		hBoxField.add(labelInterStep);
		hBoxField.add(Box.createHorizontalStrut(10));
		hBoxField.add(textFieldStep);
		hBoxField.add(Box.createHorizontalGlue());
		hBoxField.setPreferredSize(new Dimension((new Double(hBoxField.getMaximumSize().getWidth())).intValue(), (new Double(hBoxField.getMinimumSize().getHeight())).intValue() * 2));
		getContentPane().add(hBoxField, "North");

		// Кнопки
		JButton butonCalc = new JButton("Вычислить");
		butonCalc.addActionListener(new CalcActionListener(this));
		JButton buttonReset = new JButton("Очистить поля");
		buttonReset.addActionListener(new ResetActionListener(this));
		Box hboxButtons = Box.createHorizontalBox();
		hboxButtons.setBorder(BorderFactory.createBevelBorder(1));
		hboxButtons.add(Box.createHorizontalGlue());
		hboxButtons.add(butonCalc);
		hboxButtons.add(Box.createHorizontalStrut(30));
		hboxButtons.add(buttonReset);
		hboxButtons.add(Box.createHorizontalGlue());
		hboxButtons.setPreferredSize(new Dimension((new Double(hboxButtons.getMaximumSize().getWidth())).intValue(), (new Double(hboxButtons.getMinimumSize().getHeight())).intValue() * 2));
		getContentPane().add(hboxButtons, "South");
		hBoxResult = Box.createHorizontalBox();
		hBoxResult.add(new JPanel());
		getContentPane().add(this.hBoxResult, "Center");
	}

	protected void saveToTextFile(File selectedFile) {
		try {
			@SuppressWarnings("resource")
			PrintStream out = new PrintStream(selectedFile);
			out.println("Результаты многочлена по схеме Горнера");
			out.print("Многочлен: ");

			int i;
			for(i = 0; i < this.coefficients.length; ++i) {
				out.print(this.coefficients[i] + "*X^" + (this.coefficients.length - i - 1));
				if (i != this.coefficients.length - 1) {
					out.print(" + ");
				}
			}

			out.println("");
			out.println("Интервал от " + data.getFrom() + " до " + data.getTo() + " с шагом " + data.getStep());
			out.println("====================================================");

			for(i = 0; i < this.data.getRowCount(); ++i) {
				out.println("Значение в точке " + data.getValueAt(i, 0) + " равно " + data.getValueAt(i, 1));
			}
          out.close();

		} catch (FileNotFoundException var4) {
		}

	}

	protected void saveToGraphicsFile(File selctedFile) {
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(selctedFile));

			for(int i = 0; i < data.getRowCount(); ++i) {
				out.writeDouble((Double)this.data.getValueAt(i, 0));
				out.writeDouble((Double)this.data.getValueAt(i, 1));
			}

			out.close();
		} catch (Exception var4) {
		}

	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Невозможно табулировать многочлен: не заданы коэффициенты!");
			System.exit(-1);
		}

		Double[] coeff = new Double[args.length];
		int i = 0;

		try {
			String[] var6 = args;
			int var5 = args.length;

			for(int var4 = 0; var4 < var5; ++var4) {
				String arg = var6[var4];
				coeff[i++] = Double.parseDouble(arg);
			}
		} catch (NumberFormatException var7) {
			System.out.println("Ошибка преобразования строки '" + args[i] + "' в число типа Double");
			System.exit(-2);
		}

		MainFrame frame = new MainFrame(coeff);
		frame.setDefaultCloseOperation(3);
		frame.setVisible(true);
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	public JTextField getTextFieldFrom() {
		return textFieldFrom;
	}

	public JTextField getTextFieldTo() {
		return textFieldTo;
	}

	public JTextField getTextFieldStep() {
		return textFieldStep;
	}

	public Double[] setCoefficients() {
		return coefficients;
	}

	public void setData(GornerTableModel data) {
		this.data = data;
	}

	public GornerTableModel getData() {
		return data;
	}

	public GornerTableCellRenderer getRenderer() {
		return renderer;
	}

	public Box getBoxResult() {
		return hBoxResult;
	}

	public JMenuItem getSaveToTextMenuItem() {
		return saveToTextMenuItem;
	}

	public JMenuItem getSaveToGraphicsMenuItem() {
		return saveToGraphicsMenuItem;
	}

	public JMenuItem getSearchValueMenuItem() {
		return searchValueMenuItem;
	}
}
