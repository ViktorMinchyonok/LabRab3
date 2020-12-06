package bsu.rfe.java.group9.lab3.Minchyonok.var2A;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CalcActionListener implements ActionListener {

	final MainFrame this$0;

	CalcActionListener(MainFrame var1) {
		this.this$0 = var1;
	}

	public void actionPerformed(ActionEvent ev) {
		try {
			Double from = Double.parseDouble(this$0.getTextFieldFrom().getText());
			Double to = Double.parseDouble(this$0.getTextFieldTo().getText());
			Double step = Double.parseDouble(this$0.getTextFieldStep().getText());
			this$0.setData(new GornerTableModel(from, to, step, this$0.setCoefficients()));
			JTable table = new JTable(this$0.getData());

			table.setDefaultRenderer(Double.class, this$0.getRenderer());
			table.setRowHeight(30);
//			table.setDefaultRenderer(Boolean.class, new MyRenderBoolean());
//			table.setRowHeight(30);
			this$0.getBoxResult().removeAll();
			this$0.getBoxResult().add(new JScrollPane(table));
			this$0.getContentPane().validate();
			this$0.getSaveToTextMenuItem().setEnabled(true);
			this$0.getSaveToGraphicsMenuItem().setEnabled(true);
			this$0.getSearchValueMenuItem().setEnabled(true);
		} catch (NumberFormatException var6) {
			JOptionPane.showMessageDialog(this.this$0, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", 2);
		}
	}
}
