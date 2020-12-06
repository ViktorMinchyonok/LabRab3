package bsu.rfe.java.group9.lab3.Minchyonok.var2A;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Spravka extends JFrame {
	Toolkit kit = Toolkit.getDefaultToolkit();

	public Spravka() {
		JButton buttonOk = new JButton("OK");
		Box contentBox = Box.createVerticalBox();
		Box hboxLab234 = Box.createVerticalBox();
		Box hboxButton = Box.createHorizontalBox();
		JPanel panel = new JPanel();

		setAlwaysOnTop(true);
		setLocation((kit.getScreenSize().width - 1) / 2 - 150, (kit.getScreenSize().height - 2) / 2 - 100);
		panel.setSize(400, 200);
		panel.setVisible(true);

		buttonOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		hboxLab234.add(Box.createVerticalStrut(50));
		hboxLab234.add(new JLabel("Минчёнок Виктор Валентинович"));
		hboxLab234.add(new JLabel("Лаба №3 Вариант А1"));
		hboxLab234.add(new JLabel("Группа №9"));

		hboxButton.add(Box.createVerticalStrut(50));
		hboxButton.add(buttonOk);
		hboxButton.add(Box.createHorizontalGlue());

		contentBox.add(hboxLab234);
		contentBox.add(hboxButton);
		panel.add(contentBox);
		getContentPane().add(panel);
	}
}
